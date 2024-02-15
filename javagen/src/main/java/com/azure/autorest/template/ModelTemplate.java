// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Annotation;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyAccess;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.IterableType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaModifier;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.util.ModelTemplateHeaderHelper;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.TemplateUtil;
import com.azure.core.http.HttpHeader;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.serializer.JacksonAdapter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Writes a ClientModel to a JavaFile.
 */
public class ModelTemplate implements IJavaTemplate<ClientModel, JavaFile> {

    private static final String MISSING_SCHEMA = "MISSING·SCHEMA";
    private static final ModelTemplate INSTANCE = new ModelTemplate();

    protected ModelTemplate() {
    }

    public static ModelTemplate getInstance() {
        return INSTANCE;
    }

    public final void write(ClientModel model, JavaFile javaFile) {
        if (model.getParentModelName() != null && model.getParentModelName().equals(model.getName())) {
            throw new IllegalStateException("Parent model name is same as model name: " + model.getName());
        }

        final boolean requireSerialization = modelRequireSerialization(model);

        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = settings.isStreamStyleSerialization() ? new StreamStyleImports() : new HashSet<>();

        addImports(imports, model, settings);

        List<ClientModelPropertyReference> propertyReferences = this.getClientModelPropertyReferences(model);
        propertyReferences.forEach(p -> p.addImportsTo(imports, false));

        if (!CoreUtils.isNullOrEmpty(model.getPropertyReferences())) {
            if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
                model.getPropertyReferences().forEach(p -> p.addImportsTo(imports, false));
            }
            propertyReferences.addAll(model.getPropertyReferences());
        }

        javaFile.declareImport(imports);

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(),
            comment -> comment.description(model.getDescription()));

        final boolean hasDerivedModels = !model.getDerivedModels().isEmpty();
        final boolean immutableOutputModel = settings.isOutputModelImmutable()
            && model.getImplementationDetails() != null && !model.getImplementationDetails().isInput();
        boolean treatAsXml = model.isUsedInXml();

        // Handle adding annotations if the model is polymorphic.
        handlePolymorphism(model, hasDerivedModels, settings.isDiscriminatorPassedToChildDeserialization(), javaFile);

        // Add class level annotations for serialization formats such as XML.
        addClassLevelAnnotations(model, javaFile, settings);

        // Add Fluent or Immutable based on whether the model has any setters.
        addFluentOrImmutableAnnotation(model, immutableOutputModel, propertyReferences, javaFile, settings);

        List<JavaModifier> classModifiers = null;
        if (!hasDerivedModels && !model.getNeedsFlatten()) {
            classModifiers = Collections.singletonList(JavaModifier.Final);
        }

        String classNameWithBaseType = model.getName();
        if (model.getParentModelName() != null) {
            classNameWithBaseType += " extends " + model.getParentModelName();
        } else if (requireSerialization) {
            classNameWithBaseType = addSerializationImplementations(classNameWithBaseType, model, settings);
        }

        javaFile.publicClass(classModifiers, classNameWithBaseType, classBlock -> {
            // If the model has any additional properties, needs to be flattened, and isn't being generated with
            // stream-style serialization add a constant Pattern that will be used to escape the additional property
            // keys. Certain versions of the JVM will compile a Pattern each time '.replace' is called which is very
            // expensive.
            if (model.getProperties().stream().anyMatch(ClientModelProperty::isAdditionalProperties)
                && model.getNeedsFlatten()
                && !settings.isStreamStyleSerialization()) {
                addGeneratedAnnotation(classBlock);
                classBlock.privateStaticFinalVariable("Pattern KEY_ESCAPER = Pattern.compile(\"\\\\.\");");
            }

            // If code is being generated with the behavior to return an empty byte array when the default value
            // expression is null and the model has any array types that will need conversion within getter methods
            // generate a static byte[] that will be returned instead of creating a new instance each get.
            if (isGenerateConstantEmptyByteArray(model, settings)) {
                classBlock.privateStaticFinalVariable("byte[] EMPTY_BYTE_ARRAY = new byte[0]");
            }

            // XML namespace constants
            addXmlNamespaceConstants(model, classBlock);

            // properties
            addProperties(model, classBlock, settings);

            // add jsonMergePatch related properties and accessors
            if (ClientModelUtil.isJsonMergePatchModel(model) && settings.isStreamStyleSerialization()) {
                addJsonMergePatchRelatedPropertyAndAccessors(classBlock, model);
            }

            // constructor
            JavaVisibility modelConstructorVisibility = immutableOutputModel
                ? (hasDerivedModels ? JavaVisibility.Protected : JavaVisibility.Private)
                : JavaVisibility.Public;
            addModelConstructor(model, modelConstructorVisibility, settings, classBlock);

            for (ClientModelProperty property : model.getProperties()) {
                final boolean propertyIsReadOnly = immutableOutputModel || property.isReadOnly();

                IType propertyWireType = property.getWireType();
                IType propertyClientType = propertyWireType.getClientType();

                JavaVisibility methodVisibility = property.getClientFlatten()
                    ? JavaVisibility.Private
                    : JavaVisibility.Public;

                if (!property.isPolymorphicDiscriminator() || modelDefinesProperty(model, property)) {
                    // Only the super most parent model should have the polymorphic discriminator getter.
                    // The child models should use the parent's getter.
                    generateGetterJavadoc(classBlock, model, property);
                    addGeneratedAnnotation(classBlock);
                    if (property.isAdditionalProperties() && !settings.isStreamStyleSerialization()) {
                        classBlock.annotation("JsonAnyGetter");
                    }
                    if (!propertyIsReadOnly) {
                        TemplateUtil.addJsonGetter(classBlock, settings, property.getSerializedName());
                    }

                    classBlock.method(methodVisibility, null,
                        propertyClientType + " " + getGetterName(model, property) + "()",
                        methodBlock -> addGetterMethod(propertyWireType, propertyClientType, property, treatAsXml,
                            methodBlock, settings));
                }

                // The model is immutable output only if and only if the immutable output model setting is enabled and
                // the usage of the model include output and does not include input.
                boolean immutableOutputOnlyModel = immutableOutputModel && ClientModelUtil.isOutputOnly(model);

                if (ClientModelUtil.hasSetter(property, settings) && !immutableOutputOnlyModel) {
                    generateSetterJavadoc(classBlock, model, property);
                    addGeneratedAnnotation(classBlock);
                    TemplateUtil.addJsonSetter(classBlock, settings, property.getSerializedName());
                    classBlock.method(methodVisibility, null,
                        model.getName() + " " + property.getSetterName() + "(" + propertyClientType + " " + property.getName() + ")",
                        methodBlock -> addSetterMethod(propertyWireType, propertyClientType, property, treatAsXml,
                            methodBlock, settings, ClientModelUtil.isJsonMergePatchModel(model) && settings.isStreamStyleSerialization()));
                } else {
                    // If stream-style serialization is being generated, some additional setters may need to be added
                    // to support read-only properties that aren't included in the constructor.
                    // Jackson handles this by reflectively setting the value in the parent model, but stream-style
                    // serialization doesn't perform reflective cracking like Jackson Databind does, so it needs a way
                    // to access the readonly property (aka one without a public setter method).
                    //
                    // The package-private setter is added when the property isn't included in the constructor and is
                    // defined by this model.
                    boolean streamStyle = settings.isStreamStyleSerialization();
                    boolean hasDerivedTypes = !CoreUtils.isNullOrEmpty(model.getDerivedModels());
                    boolean notIncludedInConstructor = !ClientModelUtil.includePropertyInConstructor(property,
                        settings);
                    boolean definedByModel = modelDefinesProperty(model, property);
                    if (streamStyle && hasDerivedTypes && notIncludedInConstructor && definedByModel) {
                        generateSetterJavadoc(classBlock, model, property);
                        classBlock.method(JavaVisibility.PackagePrivate, null,
                            model.getName() + " " + property.getSetterName() + "(" + propertyWireType + " "
                                + property.getName() + ")",
                            methodBlock -> addSetterMethod(propertyWireType, propertyWireType, property, treatAsXml,
                                methodBlock, settings,
                                ClientModelUtil.isJsonMergePatchModel(model) && settings.isStreamStyleSerialization()));
                    }
                }

                // If the property is additional properties, and stream-style serialization isn't being used, add a
                // package-private setter that Jackson can use to set values as it deserializes the key-value pairs.
                if (property.isAdditionalProperties() && !settings.isStreamStyleSerialization()) {
                    addGeneratedAnnotation(classBlock);
                    classBlock.annotation("JsonAnySetter");
                    MapType mapType = (MapType) property.getClientType();
                    classBlock.packagePrivateMethod(String.format("void %s(String key, %s value)", property.getSetterName(), mapType.getValueType()), (methodBlock) -> {
                        // The additional properties are null by default, so if this is the first time the value is
                        // being added create the containing map.
                        // TODO (alzimmer): Should we use LinkedHashMap to retain insertion order?
                        methodBlock.ifBlock(property.getName() + " == null",
                            ifBlock -> ifBlock.line("%s = new HashMap<>();", property.getName()));

                        methodBlock.line("%s.put(%s, value);", property.getName(),
                            model.getNeedsFlatten() ? "KEY_ESCAPER.matcher(key).replaceAll(\".\")" : "key");
                    });
                }
            }

            // add setters to override parent setters
            if (!immutableOutputModel) {
                List<ClientModelPropertyAccess> settersToOverride = getParentSettersToOverride(model, settings,
                    propertyReferences);
                for (ClientModelPropertyAccess parentProperty : settersToOverride) {
                    classBlock.javadocComment(JavaJavadocComment::inheritDoc);
                    addGeneratedAnnotation(classBlock);
                    classBlock.annotation("Override");
                    classBlock.publicMethod(String.format("%s %s(%s %s)",
                            model.getName(),
                            parentProperty.getSetterName(),
                            parentProperty.getClientType(),
                            parentProperty.getName()),
                        methodBlock -> {
                            methodBlock.line(String.format("super.%1$s(%2$s);", parentProperty.getSetterName(),
                                parentProperty.getName()));
                            if (ClientModelUtil.isJsonMergePatchModel(model) && settings.isStreamStyleSerialization()) {
                                methodBlock.line(String.format("this.updatedProperties.add(\"%s\");", parentProperty.getName()));
                            }
                            methodBlock.methodReturn("this");
                        });
                }
            }

            if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
                // reference to properties from flattened client model
                for (ClientModelPropertyReference propertyReference : propertyReferences) {
                    if (!propertyReference.isFromFlattenedProperty()) {
                        continue;
                    }

                    ClientModelPropertyAccess property = propertyReference.getReferenceProperty();
                    ClientModelProperty targetProperty = propertyReference.getTargetProperty();

                    IType propertyClientType = property.getClientType();
                    final boolean propertyIsReadOnly = immutableOutputModel || property.isReadOnly();

                    if (propertyClientType instanceof PrimitiveType && !targetProperty.isRequired()) {
                        // since the property to flattened client model is optional, the flattened property should be optional
                        propertyClientType = propertyClientType.asNullable();
                    }
                    final IType propertyClientTypeFinal = propertyClientType;

                    // getter
                    generateGetterJavadoc(classBlock, model, property);
                    addGeneratedAnnotation(classBlock);
                    classBlock.publicMethod(String.format("%1$s %2$s()", propertyClientType, propertyReference.getGetterName()), methodBlock -> {
                        // use ternary operator to avoid directly return null
                        String ifClause = String.format("this.%1$s() == null", targetProperty.getGetterName());
                        String nullClause = propertyClientTypeFinal.defaultValueExpression();
                        String valueClause = String.format("this.%1$s().%2$s()", targetProperty.getGetterName(), property.getGetterName());

                        methodBlock.methodReturn(String.format("%1$s ? %2$s : %3$s", ifClause, nullClause, valueClause));
                    });

                    // setter
                    if (!propertyIsReadOnly) {
                        generateSetterJavadoc(classBlock, model, property);
                        addGeneratedAnnotation(classBlock);
                        classBlock.publicMethod(String.format("%1$s %2$s(%3$s %4$s)", model.getName(), propertyReference.getSetterName(), propertyClientType, property.getName()), methodBlock -> {
                            methodBlock.ifBlock(String.format("this.%1$s() == null", targetProperty.getGetterName()), ifBlock ->
                                methodBlock.line(String.format("this.%1$s = new %2$s();", targetProperty.getName(), propertyReference.getTargetModelType())));

                            methodBlock.line(String.format("this.%1$s().%2$s(%3$s);", targetProperty.getGetterName(), property.getSetterName(), property.getName()));
                            methodBlock.methodReturn("this");
                        });
                    }
                }
            }

            addPropertyValidations(classBlock, model, settings);

            if ((settings.isClientSideValidations() && settings.isUseClientLogger()) || model.isStronglyTypedHeader()) {
                TemplateUtil.addClientLogger(classBlock, model.getName(), javaFile.getContents());
            }

            if (requireSerialization) {
                writeStreamStyleSerialization(classBlock, model, settings);
            }
        });
    }

    private void addImports(Set<String> imports, ClientModel model, JavaSettings settings) {
        // If there is client side validation and the model will generate a ClientLogger to log the validation
        // exceptions add an import of 'com.azure.core.util.logging.ClientLogger' and
        // 'com.fasterxml.jackson.annotation.JsonIgnore'.
        //
        // These are added to support adding the ClientLogger and then to JsonIgnore the ClientLogger so it isn't
        // included in serialization.
        if (settings.isClientSideValidations() && settings.isUseClientLogger()) {
            ClassType.CLIENT_LOGGER.addImportsTo(imports, false);
        }

        addSerializationImports(imports, model, settings);

        // Add HttpHeaders as an import when strongly-typed HTTP header objects use that as a constructor parameter.
        if (model.isStronglyTypedHeader()) {
            ClassType.HTTP_HEADERS.addImportsTo(imports, false);
            ClassType.HTTP_HEADER_NAME.addImportsTo(imports, false);

            // Also add any potential imports needed to convert the header to the strong type.
            // If the import isn't used it will be removed later on.
            imports.add(Base64.class.getName());
            imports.add(HashMap.class.getName());
            imports.add(HttpHeader.class.getName());
            imports.add(UUID.class.getName());
            imports.add(URL.class.getName());
            imports.add(IOException.class.getName());
            imports.add(UncheckedIOException.class.getName());
            imports.add(ClientLogger.class.getName());

            // JacksonAdapter will be removed in the future once model types are converted to using stream-style
            // serialization. For now, it's needed to handle the rare scenario where the strong type is a non-Java
            // base type.
            imports.add(JacksonAdapter.class.getName());
        }

        String lastParentName = model.getName();
        ClientModel parentModel = ClientModelUtil.getClientModel(model.getParentModelName());
        while (parentModel != null && !lastParentName.equals(parentModel.getName())) {
            imports.addAll(parentModel.getImports());
            lastParentName = parentModel.getName();
            parentModel = ClientModelUtil.getClientModel(parentModel.getParentModelName());
        }

        addGeneratedImport(imports);

        model.addImportsTo(imports, settings);

        // add Json merge patch related imports
        if (ClientModelUtil.isJsonMergePatchModel(model) && settings.isStreamStyleSerialization()) {
            imports.add(settings.getPackage(settings.getImplementationSubpackage()) + "." + ClientModelUtil.JSON_MERGE_PATCH_HELPER_CLASS_NAME);
            imports.add(Set.class.getName());
            imports.add(HashSet.class.getName());
        }
    }

    protected void addSerializationImports(Set<String> imports, ClientModel model, JavaSettings settings) {
        imports.add(JsonCreator.class.getName());

        if (settings.isGettersAndSettersAnnotatedForSerialization()) {
            imports.add(JsonGetter.class.getName());
            imports.add(JsonSetter.class.getName());
        }

        imports.add(Pattern.class.getName());
    }

    /**
     * Override parent setters if: 1. parent property has setter 2. child does not contain property that shadow this
     * parent property, otherwise overridden parent setter methods will collide with child setter methods
     *
     * @see <a href="https://github.com/Azure/autorest.java/issues/1320">Issue 1320</a>
     */
    protected List<ClientModelPropertyAccess> getParentSettersToOverride(ClientModel model, JavaSettings settings,
        List<ClientModelPropertyReference> propertyReferences) {
        Set<String> modelPropertyNames = model.getProperties().stream().map(ClientModelProperty::getName)
            .collect(Collectors.toSet());
        return propertyReferences.stream()
            .filter(ClientModelPropertyReference::isFromParentModel)
            .map(ClientModelPropertyReference::getReferenceProperty)
            .filter(parentProperty -> {
                    // parent property doesn't have setter
                    if (!ClientModelUtil.hasSetter(parentProperty, settings)) {
                        return false;
                    }
                    // child does not contain property that shadow this parent property
                    return !modelPropertyNames.contains(parentProperty.getName());
                }
            ).collect(Collectors.toList());
    }

    /**
     * Handles setting up Jackson polymorphism annotations.
     *
     * @param model The client model.
     * @param hasDerivedModels Whether this model has children types.
     * @param isDiscriminatorPassedToChildDeserialization Whether the deserialization discriminator, such as
     * {@code odata.type}, is passed to children types during deserialization.
     * @param javaFile The JavaFile being generated.
     */
    protected void handlePolymorphism(ClientModel model, boolean hasDerivedModels,
        boolean isDiscriminatorPassedToChildDeserialization, JavaFile javaFile) {
        // Model isn't polymorphic, no work to do here.
        if (!model.isPolymorphic()) {
            return;
        }

        StringBuilder jsonTypeInfo = new StringBuilder("JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = ");

        // If the discriminator isn't being passed to child models or this model has derived, children, models
        // include the discriminator property using JsonTypeInfo.As.PROPERTY. Using this will serialize the
        // property using the property attribute of the annotation instead of looking for a @JsonProperty.
        if (!isDiscriminatorPassedToChildDeserialization || hasDerivedModels) {
            jsonTypeInfo.append("JsonTypeInfo.As.PROPERTY, property = \"");
        } else {
            // Otherwise, serialize the discriminator property with an existing property on the class.
            jsonTypeInfo.append("JsonTypeInfo.As.EXISTING_PROPERTY, property = \"");
        }

        jsonTypeInfo.append(model.getPolymorphicDiscriminatorName()).append("\"");

        // If the class has derived models add itself as a default implementation.
        if (hasDerivedModels) {
            jsonTypeInfo.append(", defaultImpl = ").append(model.getName()).append(".class");
        }

        // If the discriminator is passed to child models the discriminator property needs to be set to visible.
        if (isDiscriminatorPassedToChildDeserialization) {
            jsonTypeInfo.append(", visible = true");
        }

        javaFile.annotation(jsonTypeInfo.append(")").toString());
        javaFile.annotation(String.format("JsonTypeName(\"%1$s\")", model.getSerializedName()));

        if (hasDerivedModels) {
            javaFile.line("@JsonSubTypes({");
            javaFile.indent(() -> {
                Function<ClientModel, String> getDerivedTypeAnnotation = (ClientModel derivedType) ->
                    String.format("@JsonSubTypes.Type(name = \"%1$s\", value = %2$s.class)",
                        derivedType.getSerializedName(), derivedType.getName());

                for (int i = 0; i != model.getDerivedModels().size() - 1; i++) {
                    ClientModel derivedModel = model.getDerivedModels().get(i);
                    javaFile.line(getDerivedTypeAnnotation.apply(derivedModel) + ',');
                }
                javaFile.line(getDerivedTypeAnnotation.apply(model.getDerivedModels()
                    .get(model.getDerivedModels().size() - 1)));
            });
            javaFile.line("})");
        }
    }

    /**
     * Adds class level annotations such as XML root element, JsonFlatten based on the configurations of the model.
     *
     * @param model The client model.
     * @param javaFile The Java class file.
     * @param settings Autorest generation settings.
     */
    protected void addClassLevelAnnotations(ClientModel model, JavaFile javaFile, JavaSettings settings) {
        if (model.isUsedInXml()) {
            if (!CoreUtils.isNullOrEmpty(model.getXmlNamespace())) {
                javaFile.annotation(String.format("JacksonXmlRootElement(localName = \"%1$s\", namespace = \"%2$s\")",
                    model.getXmlName(), model.getXmlNamespace()));
            } else {
                javaFile.annotation(String.format("JacksonXmlRootElement(localName = \"%1$s\")", model.getXmlName()));
            }
        }

        if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.TYPE
            && model.getNeedsFlatten()) {
            javaFile.annotation("JsonFlatten");
        }
    }

    /**
     * Adds Fluent or Immutable based on whether model has any setters.
     *
     * @param model The client model.
     * @param immutableOutputModel The model is treated as immutable, as it is output only.
     * @param propertyReferences The client model property reference.
     * @param javaFile The Java class file.
     * @param settings Autorest generation settings.
     */
    private void addFluentOrImmutableAnnotation(ClientModel model, boolean immutableOutputModel,
        List<ClientModelPropertyReference> propertyReferences, JavaFile javaFile, JavaSettings settings) {
        boolean fluent = !immutableOutputModel && Stream
            .concat(model.getProperties().stream(), propertyReferences.stream())
            .anyMatch(p -> ClientModelUtil.hasSetter(p, settings));

        if (fluent) {
            javaFile.annotation("Fluent");
        } else {
            javaFile.annotation("Immutable");
        }
    }

    /**
     * Adds serialization implementations to the class signature.
     *
     * @param classSignature The class signature.
     * @param model The client model.
     * @param settings Autorest generation settings.
     * @return The updated class signature with serialization implementations added.
     */
    protected String addSerializationImplementations(String classSignature, ClientModel model, JavaSettings settings) {
        // no-op as this is an entry point for subclasses of ModelTemplate that provide more specific code generation.
        return classSignature;
    }

    protected void addXmlNamespaceConstants(ClientModel model, JavaClass classBlock) {
        // no-op as this is an entry point for subclasses of ModelTemplate that provide more specific code generation.
    }

    /**
     * Adds the property fields to a class.
     *
     * @param model The client model.
     * @param classBlock The Java class.
     * @param settings AutoRest configuration settings.
     */
    private void addProperties(ClientModel model, JavaClass classBlock, JavaSettings settings) {
        for (ClientModelProperty property : model.getProperties()) {
            if (property.isPolymorphicDiscriminator() && !modelDefinesProperty(model, property)) {
                // Only the super most parent model should have the polymorphic discriminator as a field.
                // The child models should use the parent's field. If the polymorphic property is required, it will be
                // initialized in the parent's constructor. Otherwise, it will be set using the package-private setter.
                continue;
            }

            String propertyName = property.getName();
            IType propertyType = property.getWireType();

            String fieldSignature;
            if (model.isUsedInXml()) {
                if (property.isXmlWrapper()) {
                    if (!settings.isStreamStyleSerialization()) {
                        String xmlWrapperClassName = getPropertyXmlWrapperClassName(property);
                        classBlock.staticFinalClass(JavaVisibility.PackagePrivate, xmlWrapperClassName,
                            innerClass -> addXmlWrapperClass(innerClass, property, xmlWrapperClassName, settings));

                        fieldSignature = xmlWrapperClassName + " " + propertyName;
                    } else {
                        fieldSignature = propertyType + " " + propertyName;
                    }
                } else if (propertyType instanceof ListType) {
                    fieldSignature = propertyType + " " + propertyName + " = new ArrayList<>()";
                } else {
                    // handle x-ms-client-default
                    // Only set the property to a default value if the property isn't included in the constructor.
                    // There can be cases with polymorphic discriminators where they have both a default value and are
                    // required, in which case the default value will be set in the constructor.
                    if (property.getDefaultValue() != null
                        && (!ClientModelUtil.includePropertyInConstructor(property, settings) || property.isConstant())) {
                        if (property.isPolymorphicDiscriminator() && !settings.isStreamStyleSerialization()) {
                            fieldSignature = propertyType + " " + CodeNamer.getEnumMemberName(propertyName) + " = " + property.getDefaultValue();
                        } else {
                            fieldSignature = propertyType + " " + propertyName + " = " + property.getDefaultValue();
                        }
                    } else {
                        fieldSignature = propertyType + " " + propertyName;
                    }
                }
            } else {
                if (property.getClientFlatten() && property.isRequired() && property.getClientType() instanceof ClassType) {
                    // if the property of flattened model is required, initialize it
                    fieldSignature = propertyType + " " + propertyName + " = new " + propertyType + "()";
                } else {
                    // handle x-ms-client-default
                    // Only set the property to a default value if the property isn't included in the constructor.
                    // There can be cases with polymorphic discriminators where they have both a default value and are
                    // required, in which case the default value will be set in the constructor.
                    if (property.getDefaultValue() != null
                        && (!ClientModelUtil.includePropertyInConstructor(property, settings) || property.isConstant())) {
                        if (property.isPolymorphicDiscriminator() && !settings.isStreamStyleSerialization()) {
                            fieldSignature = propertyType + " " + CodeNamer.getEnumMemberName(propertyName) + " = " + property.getDefaultValue();
                        } else {
                            fieldSignature = propertyType + " " + propertyName + " = " + property.getDefaultValue();
                        }
                    } else {
                        fieldSignature = propertyType + " " + propertyName;
                    }
                }
            }

            classBlock.blockComment(settings.getMaximumJavadocCommentWidth(),
                comment -> comment.line(property.getDescription()));

            addGeneratedAnnotation(classBlock);
            addFieldAnnotations(model, property, classBlock, settings);

            if (property.isPolymorphicDiscriminator() && !settings.isStreamStyleSerialization()) {
                classBlock.privateStaticFinalVariable(fieldSignature);
            } else if (ClientModelUtil.includePropertyInConstructor(property, settings)) {
                classBlock.privateFinalMemberVariable(fieldSignature);
            } else {
                classBlock.privateMemberVariable(fieldSignature);
            }
        }
    }

    protected void addXmlWrapperClass(JavaClass classBlock, ClientModelProperty property, String wrapperClassName,
        JavaSettings settings) {
        // While using a wrapping class for XML elements that are wrapped may seem inconvenient it is required.
        // There has been previous attempts to remove this by using JacksonXmlElementWrapper, which based on its
        // documentation should cover this exact scenario, but it doesn't. Jackson unfortunately doesn't always
        // respect the JacksonXmlRootName, or JsonRootName, value when handling types wrapped by an enumeration,
        // such as List<CorsRule> or Iterable<CorsRule>. Instead, it uses the JacksonXmlProperty local name as the
        // root XML node name for each element in the enumeration. There are configurations for ObjectMapper, and
        // XmlMapper, that always forces Jackson to use the root name but those also add the class name as a root
        // XML node name if the class doesn't have a root name annotation which results in an addition XML level
        // resulting in invalid service XML. There is also one last work around to use JacksonXmlElementWrapper
        // and JacksonXmlProperty together as the wrapper will configure the wrapper name and property will configure
        // the element name but this breaks down in cases where the same element name is used in two different
        // wrappers, a case being Storage BlockList which uses two block elements for its committed and uncommitted
        // block lists.
        IType propertyClientType = property.getWireType().getClientType();

        String listElementName = property.getXmlListElementName();
        String jacksonAnnotation = CoreUtils.isNullOrEmpty(property.getXmlNamespace())
            ? "JacksonXmlProperty(localName = \"" + listElementName + "\")"
            : "JacksonXmlProperty(localName = \"" + listElementName + "\", namespace = \"" + property.getXmlNamespace() + "\")";

        classBlock.annotation(jacksonAnnotation);
        classBlock.privateFinalMemberVariable(propertyClientType.toString(), "items");

        classBlock.annotation("JsonCreator");
        classBlock.privateConstructor(
            wrapperClassName + "(@" + jacksonAnnotation + " " + propertyClientType + " items)",
            constructor -> constructor.line("this.items = items;"));
    }

    /**
     * Adds the annotations for a model field.
     *
     * @param model The model.
     * @param property The property that represents the field.
     * @param classBlock The Java class.
     * @param settings Autorest generation settings.
     */
    protected void addFieldAnnotations(ClientModel model, ClientModelProperty property, JavaClass classBlock, JavaSettings settings) {
        if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.FIELD && property.getNeedsFlatten()) {
            classBlock.annotation("JsonFlatten");
        }

        // If the property is a polymorphic discriminator for the class add the annotation @JsonTypeId.
        // This will indicate to Jackson that the discriminator serialization is determined by the property
        // instead of the class level @JsonTypeName annotation. This prevents the discriminator property from
        // being serialized twice, once for the class level annotation and again for the property annotation.
        if (property.isPolymorphicDiscriminator()) {
            classBlock.annotation("JsonTypeId");
        }

        if (settings.isDataPlaneClient()
                && !property.isAdditionalProperties()
                && property.getClientType() instanceof MapType
                && ((MapType) (property.getClientType())).isValueNullable()) {
            classBlock.annotation("JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.ALWAYS)");
        }

        boolean treatAsXml = model.isUsedInXml();
        if (modelRequireSerialization(model)) {
            if (!CoreUtils.isNullOrEmpty(property.getHeaderCollectionPrefix())) {
                classBlock.annotation("HeaderCollection(\"" + property.getHeaderCollectionPrefix() + "\")");
            } else if (treatAsXml && property.isXmlAttribute()) {
                classBlock.annotation("JacksonXmlProperty(localName = \"" + property.getXmlName() + "\", isAttribute = true)");
            } else if (treatAsXml && property.getXmlNamespace() != null && !property.getXmlNamespace().isEmpty()) {
                classBlock.annotation("JacksonXmlProperty(localName = \"" + property.getXmlName() + "\", namespace = \"" + property.getXmlNamespace() + "\")");
            } else if (treatAsXml && property.isXmlText()) {
                classBlock.annotation("JacksonXmlText");
            } else if (property.isAdditionalProperties()) {
                classBlock.annotation("JsonIgnore");
            } else if (treatAsXml && property.getWireType() instanceof ListType && !property.isXmlWrapper()) {
                classBlock.annotation("JsonProperty(\"" + property.getXmlListElementName() + "\")");
            } else if (!CoreUtils.isNullOrEmpty(property.getAnnotationArguments())) {
                classBlock.annotation("JsonProperty(" + property.getAnnotationArguments() + ")");
            }
        }
    }

    /**
     * Adds the model constructor to the Java class file.
     *
     * @param model The model.
     * @param constructorVisibility The visibility of constructor.
     * @param settings AutoRest settings.
     * @param classBlock The Java class file.
     */
    private void addModelConstructor(ClientModel model, JavaVisibility constructorVisibility, JavaSettings settings,
        JavaClass classBlock) {
        final boolean requireSerialization = modelRequireSerialization(model);

        // Early out on custom strongly typed headers constructor as this has different handling that doesn't require
        // inspecting the required and constant properties.
        if (model.isStronglyTypedHeader()) {
            ModelTemplateHeaderHelper.addCustomStronglyTypedHeadersConstructor(classBlock, model, settings);
            return;
        }

        // Get the required properties from the super class structure.
        List<ClientModelProperty> requiredParentProperties = ClientModelUtil.getParentConstructorProperties(model, settings);

        // Required properties are those that are required but not constant.
        List<ClientModelProperty> requiredProperties = new ArrayList<>();

        for (ClientModelProperty property : model.getProperties()) {
            // Property isn't required and won't be bucketed into either constant or required properties.
            if (!property.isConstant() && !ClientModelUtil.includePropertyInConstructor(property, settings)) {
                continue;
            }

            // Property matches a parent property, don't need to include it twice.
            if (requiredParentProperties.stream().anyMatch(p -> p.getName().equals(property.getName()))) {
                continue;
            }

            // Only include non-constant properties.
            if (!property.isConstant()) {
                requiredProperties.add(property);
            }
        }

        // Jackson requires a constructor with @JsonCreator, with parameters in wire type. Ref https://github.com/Azure/autorest.java/issues/2170
        boolean generatePrivateConstructorForJackson = false;

        // Description for the class is always the same, not matter whether there are required properties.
        // If there are required properties, the required properties will extend the consumer to add param Javadocs.
        Consumer<JavaJavadocComment> javadocCommentConsumer = comment ->
            comment.description("Creates an instance of " + model.getName() + " class.");

        final int constructorPropertiesStringBuilderCapacity = 128 * (requiredProperties.size() + requiredParentProperties.size());

        // Use a StringBuilder with an initial capacity of 128 times the total number of required constructor properties.
        // If there are no required constructor properties this will simply be zero and result in a no-args constructor
        // being generated.
        StringBuilder constructorProperties =
            new StringBuilder(constructorPropertiesStringBuilderCapacity);

        StringBuilder superProperties = new StringBuilder(64 * requiredParentProperties.size());

        if (settings.isRequiredFieldsAsConstructorArgs()) {
            final boolean constructorParametersContainsMismatchWireType =
                requiredProperties.stream().anyMatch(p -> ClientModelUtil.isWireTypeMismatch(p, true))
                    || requiredParentProperties.stream().anyMatch(p -> ClientModelUtil.isWireTypeMismatch(p, true));

            if (constructorParametersContainsMismatchWireType && !settings.isStreamStyleSerialization()) {
                generatePrivateConstructorForJackson = requireSerialization;
            }

            final boolean addJsonPropertyAnnotation = !(settings.isStreamStyleSerialization() || generatePrivateConstructorForJackson || !requireSerialization);

            // Properties required by the super class structure come first.
            for (ClientModelProperty property : requiredParentProperties) {
                if (constructorProperties.length() > 0) {
                    constructorProperties.append(", ");
                }

                addModelConstructorParameter(property, constructorProperties, addJsonPropertyAnnotation);

                javadocCommentConsumer = javadocCommentConsumer.andThen(comment -> comment.param(property.getName(),
                    "the " + property.getName() + " value to set"));

                if (superProperties.length() > 0) {
                    superProperties.append(", ");
                }

                superProperties.append(property.getName());
            }

            // Then properties required by this class come next.
            for (ClientModelProperty property : requiredProperties) {
                if (constructorProperties.length() > 0) {
                    constructorProperties.append(", ");
                }

                addModelConstructorParameter(property, constructorProperties, addJsonPropertyAnnotation);

                javadocCommentConsumer = javadocCommentConsumer.andThen(comment -> comment.param(property.getName(),
                    "the " + property.getName() + " value to set"));
            }
        }

        // Add the Javadocs for the constructor.
        classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), javadocCommentConsumer);

        addGeneratedAnnotation(classBlock);
        // If there are any constructor arguments indicate that this is the JsonCreator. No args constructors are
        // implicitly used as the JsonCreator if the class doesn't indicate one.
        if (requireSerialization
            && constructorProperties.length() > 0 && !settings.isStreamStyleSerialization()
            // @JsonCreator will be on the other private constructor
            && !generatePrivateConstructorForJackson) {
            classBlock.annotation("JsonCreator");
        }

        // If immutableOutputModel, make the constructor private, so that adding required properties, or changing model to input-output will not have breaking changes.
        // For user in test, they will need to mock the class.

        // If constructorProperties empty this just becomes an empty constructor.
        classBlock.constructor(constructorVisibility, model.getName() + "(" + constructorProperties + ")", constructor -> {
            // If there are super class properties, call super() first.
            if (superProperties.length() > 0) {
                constructor.line("super(" + superProperties + ");");
            }

            // If there is a polymorphic discriminator and stream-style serialization is being used, add a line to
            // initialize the discriminator.
            ClientModelProperty polymorphicProperty = model.getPolymorphicDiscriminator();
            if (polymorphicProperty != null && !polymorphicProperty.isRequired()
                && settings.isStreamStyleSerialization()) {
                if (modelDefinesProperty(model, polymorphicProperty)) {
                    constructor.line("this." + polymorphicProperty.getName() + " = "
                        + polymorphicProperty.getDefaultValue() + ";");
                } else {
                    constructor.line(polymorphicProperty.getSetterName() + "(" + polymorphicProperty.getDefaultValue() + ");");
                }
            }

            // constant properties should already be initialized in class variable definition
//            // Then, add all constant properties.
//            for (ClientModelProperty property : constantProperties) {
//                constructor.line(property.getName() + " = " + property.getDefaultValue() + ";");
//            }

            // Finally, add all required properties.
            if (settings.isRequiredFieldsAsConstructorArgs()) {
                for (ClientModelProperty property : requiredProperties) {
                    constructor.line("this." + property.getName() + " = " + property.getWireType().convertFromClientType(property.getName()) + ";");
                }
            }
        });

        if (generatePrivateConstructorForJackson) {
            addGeneratedAnnotation(classBlock);
            classBlock.annotation("JsonCreator");

            StringBuilder constructorPropertiesAsWireType =
                    new StringBuilder(constructorPropertiesStringBuilderCapacity);

            StringBuilder constructorPropertiesInvokePublicConstructor =
                    new StringBuilder(constructorPropertiesStringBuilderCapacity);

            final Consumer<ClientModelProperty> addParameterInvokePublicConstructor = p -> {
                if (constructorPropertiesInvokePublicConstructor.length() > 0) {
                    constructorPropertiesInvokePublicConstructor.append(", ");
                }

                if (p.getWireType() == p.getClientType()) {
                    constructorPropertiesInvokePublicConstructor.append(p.getName());
                } else {
                    constructorPropertiesInvokePublicConstructor.append(p.getWireType().convertToClientType(p.getName()));
                }
            };

            for (ClientModelProperty property : requiredParentProperties) {
                if (constructorPropertiesAsWireType.length() > 0) {
                    constructorPropertiesAsWireType.append(", ");
                }

                addModelConstructorParameterAsWireType(property, constructorPropertiesAsWireType);

                addParameterInvokePublicConstructor.accept(property);
            }
            for (ClientModelProperty property : requiredProperties) {
                if (constructorPropertiesAsWireType.length() > 0) {
                    constructorPropertiesAsWireType.append(", ");
                }

                addModelConstructorParameterAsWireType(property, constructorPropertiesAsWireType);

                addParameterInvokePublicConstructor.accept(property);
            }

            classBlock.privateConstructor(model.getName() + "(" + constructorPropertiesAsWireType + ")", constructor -> {
                constructor.line("this(" + constructorPropertiesInvokePublicConstructor + ");");
            });
        }
    }

    /**
     * Adds a constructor parameter to the constructor signature builder.
     * <p>
     * The parameter takes client type of property in constructor.
     *
     * @param property The client model property as constructor parameter.
     * @param constructorSignatureBuilder The constructor signature builder.
     * @param addJsonPropertyAnnotation whether to add {@code @JsonProperty} annotation on parameter.
     */
    private static void addModelConstructorParameter(ClientModelProperty property,
        StringBuilder constructorSignatureBuilder, boolean addJsonPropertyAnnotation) {

        if (addJsonPropertyAnnotation) {
            constructorSignatureBuilder.append("@JsonProperty(").append(property.getAnnotationArguments()).append(") ");
        }
        constructorSignatureBuilder.append(property.getClientType()).append(" ").append(property.getName());
    }

    /**
     * Adds a constructor parameter to the constructor signature builder.
     * <p>
     * The parameter takes wire type of property in constructor.
     *
     * @param property The client model property as constructor parameter.
     * @param constructorSignatureBuilder The constructor signature builder.
     */
    private static void addModelConstructorParameterAsWireType(
        ClientModelProperty property,
        StringBuilder constructorSignatureBuilder) {

        constructorSignatureBuilder
            .append("@JsonProperty(").append(property.getAnnotationArguments()).append(") ")
            .append(property.getWireType()).append(" ").append(property.getName());
    }

    /**
     * Adds a getter method.
     *
     * @param propertyWireType The property wire type.
     * @param propertyClientType The client property type.
     * @param property The property.
     * @param treatAsXml Whether the getter should treat the property as XML.
     * @param methodBlock Where the getter method is being added.
     */
    private static void addGetterMethod(IType propertyWireType, IType propertyClientType, ClientModelProperty property,
        boolean treatAsXml, JavaBlock methodBlock, JavaSettings settings) {
        String sourceTypeName = propertyWireType.toString();
        String targetTypeName = propertyClientType.toString();
        String expression = (property.isPolymorphicDiscriminator() && !settings.isStreamStyleSerialization())
            ? CodeNamer.getEnumMemberName(property.getName())
            : "this." + property.getName();
        if (propertyWireType.equals(ArrayType.BYTE_ARRAY)) {
            expression = TemplateHelper.getByteCloneExpression(expression);
        }

        if (sourceTypeName.equals(targetTypeName)) {
            if (treatAsXml && property.isXmlWrapper() && (property.getWireType() instanceof IterableType)) {
                if (settings.isStreamStyleSerialization()) {
                    methodBlock.ifBlock("this." + property.getName() + " == null", ifBlock ->
                        ifBlock.line("this." + property.getName() + " = new ArrayList<>();"));
                    methodBlock.methodReturn("this." + property.getName());
                } else {
                    methodBlock.ifBlock(String.format("this.%s == null", property.getName()), ifBlock ->
                        ifBlock.line("this.%s = new %s(new ArrayList<%s>());", property.getName(),
                            getPropertyXmlWrapperClassName(property),
                            ((GenericType) property.getWireType()).getTypeArguments()[0]));
                    methodBlock.methodReturn(String.format("this.%s.items", property.getName()));
                }
            } else {
                methodBlock.methodReturn(expression);
            }
        } else {
            // If the wire type was null, return null as the returned conversion could, and most likely would, result
            // in a NullPointerException.
            if (propertyWireType.isNullable()) {
                methodBlock.ifBlock(expression + " == null",
                    ifBlock -> ifBlock.methodReturn(propertyClientType.defaultValueExpression()));
            }

            // Return the conversion of the wire type to the client type. An example would be a wire type of
            // DateTimeRfc1123 and a client type of OffsetDateTime (type a consumer would use), this makes the return
            // "this.value.getDateTime()".
            methodBlock.methodReturn(propertyWireType.convertToClientType(expression));
        }
    }

    /**
     * Adds a setter method.
     *
     * @param propertyWireType      The property wire type.
     * @param propertyClientType    The client property type.
     * @param property              The property.
     * @param treatAsXml            Whether the setter should treat the property as XML.
     * @param methodBlock           Where the setter method is being added.
     * @param isJsonMergePatchModel Whether the client model is a JSON merge patch model.
     */
    private static void addSetterMethod(IType propertyWireType, IType propertyClientType, ClientModelProperty property,
        boolean treatAsXml, JavaBlock methodBlock, JavaSettings settings, boolean isJsonMergePatchModel) {
        String expression = (propertyClientType.equals(ArrayType.BYTE_ARRAY))
            ? TemplateHelper.getByteCloneExpression(property.getName())
            : property.getName();

        if (propertyClientType != propertyWireType) {
            // If the property needs to be converted and the passed value is null, set the field to null as the
            // converter will likely throw a NullPointerException.
            // Otherwise, just convert the value.
            methodBlock.ifBlock(property.getName() + " == null",
                    ifBlock -> ifBlock.line("this.%s = null;", property.getName()))
                .elseBlock(elseBlock ->
                    elseBlock.line("this.%s = %s;", property.getName(), propertyWireType.convertFromClientType(expression)));
        } else {
            if (treatAsXml && property.isXmlWrapper()) {
                if (settings.isStreamStyleSerialization()) {
                    methodBlock.line("this." + property.getName() + " = " + expression + ";");
                } else {
                    methodBlock.line("this.%s = new %s(%s);", property.getName(),
                        getPropertyXmlWrapperClassName(property), expression);
                }
            } else {
                methodBlock.line("this.%s = %s;", property.getName(), expression);
            }
        }

        if (isJsonMergePatchModel) {
            methodBlock.line(String.format("this.updatedProperties.add(\"%s\");", property.getName()));
        }

        methodBlock.methodReturn("this");
    }

    private void addPropertyValidations(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        if (settings.isClientSideValidations()) {
            boolean validateOnParent = this.validateOnParentModel(model.getParentModelName());

            // javadoc
            classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) -> {
                comment.description("Validates the instance.");

                comment.methodThrows("IllegalArgumentException", "thrown if the instance is not valid");
            });

            if (validateOnParent) {
                classBlock.annotation("Override");
            }
            classBlock.publicMethod("void validate()", methodBlock -> {
                if (validateOnParent) {
                    methodBlock.line("super.validate();");
                }
                for (ClientModelProperty property : model.getProperties()) {
                    String validation = property.getClientType().validate(getGetterName(model, property) + "()");
                    if (property.isRequired() && !property.isReadOnly() && !property.isConstant() && !(property.getClientType() instanceof PrimitiveType)) {
                        JavaIfBlock nullCheck = methodBlock.ifBlock(String.format("%s() == null", getGetterName(model, property)), ifBlock -> {
                            final String errorMessage = String.format("\"Missing required property %s in model %s\"", property.getName(), model.getName());
                            if (settings.isUseClientLogger()) {
                                ifBlock.line(String.format(
                                    "throw LOGGER.logExceptionAsError(new IllegalArgumentException(%s));",
                                    errorMessage));
                            } else {
                                ifBlock.line(String.format(
                                    "throw new IllegalArgumentException(%s);",
                                    errorMessage));
                            }
                        });
                        if (validation != null) {
                            nullCheck.elseBlock(elseBlock -> elseBlock.line(validation + ";"));
                        }
                    } else if (validation != null) {
                        methodBlock.ifBlock(String.format("%s() != null", getGetterName(model, property)), ifBlock ->
                            ifBlock.line(validation + ";"));
                    }
                }
            });
        }
    }

    /**
     * Gets the property XML wrapper class name.
     *
     * @param property The property that is getting its XML wrapper class name.
     * @return The property XML wrapper class name.
     */
    static String getPropertyXmlWrapperClassName(ClientModelProperty property) {
        return property.getXmlName() + "Wrapper";
    }

    /**
     * Extension for validation on parent model.
     *
     * @param parentModelName the parent model name
     * @return Whether to call validate on parent model.
     */
    protected boolean validateOnParentModel(String parentModelName) {
        return parentModelName != null;
    }

    /**
     * Extension for property getter method name.
     *
     * @param model the model
     * @param property the property
     * @return The property getter method name.
     */
    protected String getGetterName(ClientModel model, ClientModelProperty property) {
        return property.getGetterName();
    }

    /**
     * Extension for Fluent list of client model property reference.
     *
     * @param model the client model.
     * @return the list of client model property reference.
     */
    protected List<ClientModelPropertyReference> getClientModelPropertyReferences(ClientModel model) {
        List<ClientModelPropertyReference> propertyReferences = new ArrayList<>();
        String lastParentName = model.getName();
        String parentModelName = model.getParentModelName();
        while (parentModelName != null && !lastParentName.equals(parentModelName)) {
            ClientModel parentModel = ClientModelUtil.getClientModel(parentModelName);
            if (parentModel != null) {
                if (parentModel.getProperties() != null) {
                    parentModel.getProperties().stream()
                        .filter(p -> !p.getClientFlatten() && !p.isAdditionalProperties())
                        .map(ClientModelPropertyReference::ofParentProperty)
                        .forEach(propertyReferences::add);
                }

                if (parentModel.getPropertyReferences() != null) {
                    parentModel.getPropertyReferences().stream()
                        .filter(ClientModelPropertyReference::isFromFlattenedProperty)
                        .map(ClientModelPropertyReference::ofParentProperty)
                        .forEach(propertyReferences::add);
                }
            }

            lastParentName = parentModelName;
            parentModelName = parentModel == null ? null : parentModel.getParentModelName();
        }
        return propertyReferences;
    }

    /**
     * Checks whether to generate constant "private final static byte[] EMPTY_BYTE_ARRAY = new byte[0];"
     *
     * @param model the model
     * @param settings Java settings
     * @return Whether to generate the constant.
     */
    private static boolean isGenerateConstantEmptyByteArray(ClientModel model, JavaSettings settings) {
        if (!settings.isNullByteArrayMapsToEmptyArray()) {
            return false;
        }

        boolean ret = model.getProperties().stream()
            .anyMatch(property -> property.getClientType() == ArrayType.BYTE_ARRAY
                && property.getWireType() != property.getClientType());

        if (!ret && !CoreUtils.isNullOrEmpty(model.getParentModelName())) {
            ret = ClientModelUtil.getParentProperties(model).stream()
                .anyMatch(property -> property.getClientType() == ArrayType.BYTE_ARRAY
                    && property.getWireType() != property.getClientType());
        }

        // flatten properties
        if (!ret && settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
            // "return this.innerProperties() == null ? EMPTY_BYTE_ARRAY : this.innerProperties().property1();"
            ret = model.getPropertyReferences().stream()
                .filter(ClientModelPropertyReference::isFromFlattenedProperty)
                .anyMatch(p -> p.getClientType() == ArrayType.BYTE_ARRAY);
        }

        return ret;
    }

    /**
     * Checks whether the serialization code is required for the model.
     *
     * @param model the model.
     * @return whether the serialization code is required for the model.
     */
    private static boolean modelRequireSerialization(ClientModel model) {
        // TODO (weidxu): any other case? "binary"?
        return !ClientModelUtil.isMultipartModel(model);
    }

    /**
     * Writes stream-style serialization logic for serializing to and deserializing from the serialization format that
     * the model uses.
     *
     * @param classBlock The class block where serialization methods will be written.
     * @param model The model.
     * @param settings Autorest generation settings.
     */
    protected void writeStreamStyleSerialization(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        // No-op, meant for StreamSerializationModelTemplate.
    }

    protected void addGeneratedImport(Set<String> imports) {
        if (JavaSettings.getInstance().isDataPlaneClient()) {
            Annotation.GENERATED.addImportsTo(imports);
        }
    }

    protected void addGeneratedAnnotation(JavaContext classBlock) {
        if (JavaSettings.getInstance().isDataPlaneClient()) {
            classBlock.annotation(Annotation.GENERATED.getName());
        }
    }

    // Javadoc for getter method
    private static void generateGetterJavadoc(JavaClass classBlock, ClientModel model,
        ClientModelPropertyAccess property) {
        classBlock.javadocComment(JavaSettings.getInstance().getMaximumJavadocCommentWidth(), comment -> {
            comment.description(String.format("Get the %1$s property: %2$s", property.getName(), property.getDescription()));
            comment.methodReturns(String.format("the %1$s value", property.getName()));
        });
    }

    // Javadoc for setter method
    private static void generateSetterJavadoc(JavaClass classBlock, ClientModel model,
        ClientModelPropertyAccess property) {
        classBlock.javadocComment(JavaSettings.getInstance().getMaximumJavadocCommentWidth(), (comment) -> {
            if (property.getDescription() == null || property.getDescription().contains(MISSING_SCHEMA)) {
                comment.description(String.format("Set the %s property", property.getName()));
            } else {
                comment.description(String.format("Set the %s property: %s", property.getName(), property.getDescription()));
            }
            comment.param(property.getName(), String.format("the %s value to set", property.getName()));
            comment.methodReturns(String.format("the %s object itself.", model.getName()));
        });
    }

    private static final class StreamStyleImports extends HashSet<String> {
        @Override
        public boolean add(String s) {
            if (s != null && s.contains("fasterxml")) {
                return true;
            }

            return super.add(s);
        }
    }

    /**
     * Add json-merge-patch related flag and accessors.
     */
    private void addJsonMergePatchRelatedPropertyAndAccessors(JavaClass classBlock, ClientModel model) {
        // properties
        addGeneratedAnnotation(classBlock);
        classBlock.privateMemberVariable("boolean jsonMergePatch");

        classBlock.javadocComment(comment -> {
            comment.description("Stores updated model property, the value is property name, not serialized name");
        });
        addGeneratedAnnotation(classBlock);
        classBlock.privateFinalMemberVariable("Set<String> updatedProperties = new HashSet<>()");

        // setter
        addGeneratedAnnotation(classBlock);
        classBlock.packagePrivateMethod("void serializeAsJsonMergePatch(boolean jsonMergePatch)", method -> {
            method.line("this.jsonMergePatch = jsonMergePatch;");
        });

        // static code block to access jsonMergePatch setter
        classBlock.staticBlock(staticBlock -> {
            staticBlock.text(String.format("JsonMergePatchHelper.set%sAccessor((model, jsonMergePatchEnabled) -> {\n" +
                    "model.serializeAsJsonMergePatch(jsonMergePatchEnabled);\n" +
                    "return model;\n" + "});", model.getName()));
        });
    }

    private static boolean modelDefinesProperty(ClientModel model, ClientModelProperty property) {
        return ClientModelUtil.getParentProperties(model).stream().noneMatch(parentProperty ->
            Objects.equals(property.getSerializedName(), parentProperty.getSerializedName()));
    }
}
