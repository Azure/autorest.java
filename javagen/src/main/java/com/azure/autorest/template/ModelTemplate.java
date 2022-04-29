// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyAccess;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.IterableType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaModifier;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.TemplateUtil;
import com.azure.core.http.HttpHeader;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.JacksonAdapter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Writes a ClientModel to a JavaFile.
 */
public class ModelTemplate implements IJavaTemplate<ClientModel, JavaFile> {

    public static final String MISSING_SCHEMA = "MISSING·SCHEMA";
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

        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<>();

        // If there is client side validation and the model will generate a ClientLogger to log the validation
        // exceptions add an import of 'com.azure.core.util.logging.ClientLogger' and
        // 'com.fasterxml.jackson.annotation.JsonIgnore'.
        //
        // These are added to support adding the ClientLogger and then to JsonIgnore the ClientLogger so it isn't
        // included in serialization.
        if (settings.shouldClientSideValidations() && settings.shouldClientLogger()) {
            ClassType.ClientLogger.addImportsTo(imports, false);
        }

        // TODO: Determine whether imports should be added here.
        imports.add(JsonCreator.class.getName());

        if (settings.isGettersAndSettersAnnotatedForSerialization()) {
            imports.add(JsonGetter.class.getName());
            imports.add(JsonSetter.class.getName());
        }

        // Add HttpHeaders as an import when strongly-typed HTTP header objects are using custom deserialization.
        if (settings.isCustomStronglyTypedHeaderDeserializationUsed() && model.isStronglyTypedHeader()) {
            ClassType.HttpHeaders.addImportsTo(imports, false);

            // Also add any potential imports needed to convert the header to the strong type.
            // If the import isn't used it will be removed later on.
            imports.add(Base64.class.getName());
            imports.add(HashMap.class.getName());
            imports.add(HttpHeader.class.getName());

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

        List<ClientModelPropertyReference> propertyReferences = this.getClientModelPropertyReferences(model);
        if (JavaSettings.getInstance().isOverrideSetterFromSuperclass()) {
            propertyReferences.forEach(p -> p.addImportsTo(imports, false));
        }

        if (!CoreUtils.isNullOrEmpty(model.getPropertyReferences())) {
            if (JavaSettings.getInstance().getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
                model.getPropertyReferences().forEach(p -> p.addImportsTo(imports, false));
            }
            propertyReferences.addAll(model.getPropertyReferences());
        }

        model.addImportsTo(imports, settings);

        javaFile.declareImport(imports);

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
            comment.description(model.getDescription()));

        boolean hasDerivedModels = !model.getDerivedModels().isEmpty();
        handlePolymorphism(model, hasDerivedModels, settings.isDiscriminatorPassedToChildDeserialization(), javaFile);

        if (settings.shouldGenerateXmlSerialization()) {
            if (model.getXmlNamespace() != null && !model.getXmlNamespace().isEmpty()) {
                javaFile.annotation(String.format("JacksonXmlRootElement(localName = \"%1$s\", namespace = \"%2$s\")",
                        model.getXmlName(), model.getXmlNamespace()));
            } else {
                javaFile.annotation(String.format("JacksonXmlRootElement(localName = \"%1$s\")", model.getXmlName()));
            }
        }

        if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.TYPE && model.getNeedsFlatten()) {
            javaFile.annotation("JsonFlatten");
        }

        ArrayList<JavaModifier> classModifiers = new ArrayList<>();
        if (!hasDerivedModels && !model.getNeedsFlatten()) {
            if (!settings.isFluent() || !model.getName().endsWith("Identity")) {    // bug https://github.com/Azure/azure-sdk-for-java/issues/8372
                classModifiers.add(JavaModifier.Final);
            }
        }

        String classNameWithBaseType = model.getName();
        if (model.getParentModelName() != null) {
            classNameWithBaseType += String.format(" extends %1$s", model.getParentModelName());
        }
        if (model.getProperties().stream().anyMatch(p -> !p.getIsReadOnly())
                || propertyReferences.stream().anyMatch(p -> !p.getIsReadOnly())) {
            javaFile.annotation("Fluent");
        } else {
            javaFile.annotation("Immutable");
        }
        javaFile.publicClass(classModifiers, classNameWithBaseType, (classBlock) ->
        {
            Function<ClientModelProperty, String> propertyXmlWrapperClassName = (ClientModelProperty property) -> property.getXmlName() + "Wrapper";

            addProperties(model, classBlock, settings);

            List<ClientModelProperty> constantProperties = model.getProperties().stream()
                    .filter(clientModelProperty -> clientModelProperty.getIsConstant() && clientModelProperty.isRequired())
                    .collect(Collectors.toList());
            List<ClientModelProperty> requiredProperties =
                model.getProperties().stream().filter(ClientModelProperty::isRequired).collect(Collectors.toList());

            addModelConstructor(model, settings, classBlock, constantProperties, requiredProperties);

            for (ClientModelProperty property : model.getProperties()) {
                IType propertyType = property.getWireType();
                IType propertyClientType = propertyType.getClientType();

                JavaVisibility methodVisibility = property.getClientFlatten() ? JavaVisibility.Private : JavaVisibility.Public;

                generateGetterJavadoc(classBlock, model, property);
                if (property.isAdditionalProperties()) {
                    classBlock.annotation("JsonAnyGetter");
                }
                if (!property.getIsReadOnly()) {
                    TemplateUtil.addJsonGetter(classBlock, settings, property.getSerializedName());
                }

                classBlock.method(methodVisibility, null, String.format("%1$s %2$s()", propertyClientType,
                    getGetterName(model, property)), methodBlock -> addGetterMethod(propertyType, propertyClientType,
                    property, settings, methodBlock));

                if(!property.getIsReadOnly() && !(settings.isRequiredFieldsAsConstructorArgs() && property.isRequired()) && methodVisibility == JavaVisibility.Public) {
                    generateSetterJavadoc(classBlock, model, property);
                    TemplateUtil.addJsonSetter(classBlock, settings, property.getSerializedName());
                    classBlock.method(methodVisibility, null, String.format("%s %s(%s %s)",
                        model.getName(), property.getSetterName(), propertyClientType, property.getName()),
                        (methodBlock) -> {
                            String expression;
                            if (propertyClientType.equals(ArrayType.ByteArray)) {
                                expression = String.format("CoreUtils.clone(%s)", property.getName());
                            } else {
                                expression = property.getName();
                            }
                            if (propertyClientType != propertyType) {
                                methodBlock.ifBlock(String.format("%s == null", property.getName()),
                                    (ifBlock) -> ifBlock.line("this.%s = null;", property.getName()))
                                    .elseBlock((elseBlock) -> {
                                        String propertyConversion = propertyType.convertFromClientType(expression);
                                        elseBlock.line("this.%s = %s;", property.getName(), propertyConversion);
                                    });
                            } else {
                                if (settings.shouldGenerateXmlSerialization() && property.getIsXmlWrapper()) {
                                    methodBlock.line("this.%s = new %s(%s);", property.getName(),
                                        propertyXmlWrapperClassName.apply(property), expression);
                                } else {
                                    methodBlock.line("this.%s = %s;", property.getName(), expression);
                                }
                            }
                            methodBlock.methodReturn("this");
                        });
                }

                if (property.isAdditionalProperties()) {
                    classBlock.annotation("JsonAnySetter");
                    MapType mapType = (MapType) property.getClientType();
                    classBlock.packagePrivateMethod(String.format("void %s(String key, %s value)", property.getSetterName(), mapType.getValueType()), (methodBlock) -> {
                        methodBlock.ifBlock(String.format("%s == null", property.getName()), ifBlock ->
                            ifBlock.line("%s = new HashMap<>();", property.getName()));
                        methodBlock.line("%s.put(%s, value);", property.getName(), model.getNeedsFlatten() ? "key.replace(\"\\\\.\", \".\")" : "key");
                    });
                }
            }

            if (settings.isOverrideSetterFromSuperclass()) {
                List<ClientModelPropertyAccess> settersToOverride = getParentSettersToOverride(model, settings, propertyReferences);
                for (ClientModelPropertyAccess parentProperty : settersToOverride) {
                    classBlock.javadocComment(JavaJavadocComment::inheritDoc);
                    classBlock.annotation("Override");
                    classBlock.publicMethod(String.format("%s %s(%s %s)",
                            model.getName(),
                            parentProperty.getSetterName(),
                            parentProperty.getClientType(),
                            parentProperty.getName()),
                            methodBlock -> {
                                methodBlock.line(String.format("super.%1$s(%2$s);", parentProperty.getSetterName(), parentProperty.getName()));
                                methodBlock.methodReturn("this");
                            });
                }
            }

            if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
                // reference to properties from flattened client model
                for (ClientModelPropertyReference propertyReference : propertyReferences.stream().filter(ClientModelPropertyReference::isFromFlattenedProperty).collect(Collectors.toList())) {
                    ClientModelPropertyAccess property = propertyReference.getReferenceProperty();
                    ClientModelProperty targetProperty = propertyReference.getTargetProperty();

                    IType propertyClientType = property.getClientType();

                    if (propertyClientType instanceof PrimitiveType && !targetProperty.isRequired()) {
                        // since the property to flattened client model is optional, the flattened property should be optional
                        propertyClientType = propertyClientType.asNullable();
                    }
                    final IType propertyClientTypeFinal = propertyClientType;

                    // getter
                    generateGetterJavadoc(classBlock, model, property);
                    classBlock.publicMethod(String.format("%1$s %2$s()", propertyClientType, propertyReference.getGetterName()), methodBlock -> {
                        // use ternary operator to avoid directly return null
                        String ifClause = String.format("this.%1$s() == null", targetProperty.getGetterName());
                        String nullClause = propertyClientTypeFinal.defaultValueExpression();
                        String valueClause = String.format("this.%1$s().%2$s()", targetProperty.getGetterName(), property.getGetterName());

                        methodBlock.methodReturn(String.format("%1$s ? %2$s : %3$s", ifClause, nullClause, valueClause));
                    });

                    // setter
                    if (!property.getIsReadOnly()) {
                        generateSetterJavadoc(classBlock, model, property);
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

            if (settings.shouldClientSideValidations() && settings.shouldClientLogger()) {
                TemplateUtil.addClientLogger(classBlock, model.getName(), javaFile.getContents());
            }
        });
    }

    /**
     * Override parent setters if:
     * 1. parent property is not readOnly or required
     * 2. child does not contain property that shadow this parent property, otherwise overridden parent setter methods will collide with child setter methods
     * @see <a href="https://github.com/Azure/autorest.java/issues/1320">Issue 1320</a>
     */
    protected List<ClientModelPropertyAccess> getParentSettersToOverride(ClientModel model, JavaSettings settings, List<ClientModelPropertyReference> propertyReferences) {
        Set<String> modelPropertyNames = model.getProperties().stream().map(ClientModelProperty::getName).collect(Collectors.toSet());
        return propertyReferences.stream()
            .filter(ClientModelPropertyReference::isFromParentModel)
            .map(ClientModelPropertyReference::getReferenceProperty)
            .filter(parentProperty -> {
                // parent property is not readOnly or required
                if (parentProperty.getIsReadOnly() ||
                    (settings.isRequiredFieldsAsConstructorArgs() && parentProperty.isRequired())) {
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
     * @param isDiscriminatorPassedToChildDeserialization Whether the deserialization discriminator, such as odata.type,
     * is passed to children types during deserialization.
     * @param javaFile The JavaFile being generated.
     */
    private static void handlePolymorphism(ClientModel model, boolean hasDerivedModels,
        boolean isDiscriminatorPassedToChildDeserialization, JavaFile javaFile) {
        // Model isn't polymorphic, no work to do here.
        if (!model.getIsPolymorphic()) {
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

        jsonTypeInfo.append(model.getPolymorphicDiscriminator()).append("\"");

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
     * Adds the property fields to a class.
     *
     * @param model The client model.
     * @param classBlock The Java class.
     * @param settings AutoRest configuration settings.
     */
    private static void addProperties(ClientModel model, JavaClass classBlock, JavaSettings settings) {
        for (ClientModelProperty property : model.getProperties()) {
            String xmlWrapperClassName = getPropertyXmlWrapperClassName(property);

            if (settings.shouldGenerateXmlSerialization() && property.getIsXmlWrapper()) {
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
                classBlock.privateStaticFinalClass(xmlWrapperClassName, innerClass ->
                {
                    IType propertyClientType = property.getWireType().getClientType();

                    String listElementName = property.getXmlListElementName();
                    String jacksonAnnotation = String.format("JacksonXmlProperty(localName = \"%1$s\")", listElementName);
                    if (property.getXmlNamespace() != null && !property.getXmlNamespace().isEmpty()) {
                        jacksonAnnotation = String.format("JacksonXmlProperty(localName = \"%1$s\", namespace = " +
                            "\"%2$s\")", listElementName, property.getXmlNamespace());
                    }
                    innerClass.annotation(jacksonAnnotation);
                    innerClass.privateFinalMemberVariable(propertyClientType.toString(), "items");

                    innerClass.annotation("JsonCreator");
                    innerClass.privateConstructor(String.format("%1$s(@%2$s %3$s items)", xmlWrapperClassName, jacksonAnnotation, propertyClientType), constructor -> constructor.line("this.items = items;"));
                });
            }

            classBlock.blockComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                comment.line(property.getDescription()));

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

            if (property.getHeaderCollectionPrefix() != null && !property.getHeaderCollectionPrefix().isEmpty()) {
                classBlock.annotation("HeaderCollection(\"" + property.getHeaderCollectionPrefix() + "\")");
            } else if (settings.shouldGenerateXmlSerialization() && property.getIsXmlAttribute()) {
                classBlock.annotation(String.format("JacksonXmlProperty(localName = \"%1$s\", isAttribute = true)",
                    property.getXmlName()));
            } else if (settings.shouldGenerateXmlSerialization() && property.getXmlNamespace() != null && !property.getXmlNamespace().isEmpty()) {
                classBlock.annotation(String.format("JacksonXmlProperty(localName = \"%1$s\", namespace = \"%2$s\")",
                    property.getXmlName(), property.getXmlNamespace()));
            } else if (settings.shouldGenerateXmlSerialization() && property.isXmlText()) {
                classBlock.annotation("JacksonXmlText");
            } else if (property.isAdditionalProperties()) {
                classBlock.annotation("JsonIgnore");
            } else if (settings.shouldGenerateXmlSerialization() && property.getWireType() instanceof ListType && !property.getIsXmlWrapper()) {
                classBlock.annotation(String.format("JsonProperty(\"%1$s\")", property.getXmlListElementName()));
            } else if (property.getAnnotationArguments() != null && !property.getAnnotationArguments().isEmpty()) {
                classBlock.annotation(String.format("JsonProperty(%1$s)", property.getAnnotationArguments()));
            }

            if (settings.shouldGenerateXmlSerialization()) {
                if (property.getIsXmlWrapper()) {
                    classBlock.privateMemberVariable(String.format("%1$s %2$s", xmlWrapperClassName, property.getName()));
                } else if (property.getWireType() instanceof ListType) {
                    classBlock.privateMemberVariable(String.format("%1$s %2$s = new ArrayList<>()", property.getWireType(), property.getName()));
                } else {
                    classBlock.privateMemberVariable(String.format("%1$s %2$s", property.getWireType(), property.getName()));
                }
            } else {
                if (!property.isAdditionalProperties() && property.getClientType() instanceof MapType && settings.isFluent()) {
                    classBlock.annotation("JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.ALWAYS)");
                }
                if (property.getClientFlatten() && property.isRequired() && property.getClientType() instanceof ClassType) {
                    // if the property of flattened model is required, initialize it
                    classBlock.privateMemberVariable(String.format("%1$s %2$s = new %1$s()", property.getWireType(), property.getName()));
                } else {
                    // handle x-ms-client-default
                    if (property.getDefaultValue() != null) {
                        classBlock.privateMemberVariable(String.format("%1$s %2$s = %3$s", property.getWireType(), property.getName(), property.getDefaultValue()));
                    } else {
                        classBlock.privateMemberVariable(String.format("%1$s %2$s", property.getWireType(), property.getName()));
                    }
                }
            }
        }
    }

    private static void addModelConstructor(ClientModel model, JavaSettings settings, JavaClass classBlock,
        List<ClientModelProperty> constantProperties, List<ClientModelProperty> allRequiredProperties) {

        List<ClientModelProperty> requiredProperties =
                allRequiredProperties.stream().filter(property -> !property.getIsConstant()).collect(Collectors.toList());
        List<ClientModelProperty> requiredParentProperties = ClientModelUtil.getRequiredParentProperties(model);

        if (settings.isRequiredFieldsAsConstructorArgs() && (!requiredProperties.isEmpty() || !requiredParentProperties
            .isEmpty())) {

            String requiredCtorArgs = requiredProperties.stream()
                .map(property -> String.format("@JsonProperty(%1$s) %2$s %3$s", property.getAnnotationArguments(),
                    property.getClientType().toString(), property.getName())).collect(Collectors.joining(", "));

            String requiredParentCtorArgs = "";

            if (!requiredParentProperties.isEmpty()) {
                requiredParentCtorArgs = requiredParentProperties.stream().map(property -> String.format(
                    "@JsonProperty(%1$s) %2$s %3$s", property.getAnnotationArguments(),
                    property.getClientType().toString(), property.getName())).collect(Collectors.joining(", "));
            }

            StringBuilder ctorArgs = new StringBuilder();
            ctorArgs.append(requiredParentCtorArgs);
            if (!(requiredParentProperties.isEmpty() || requiredProperties.isEmpty())) {
                ctorArgs.append(", ");
            }
            ctorArgs.append(requiredCtorArgs);

            classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
            {
                comment.description(String.format("Creates an instance of %1$s class.", model.getName()));

                requiredParentProperties.forEach(property -> comment
                        .param(property.getName(), String.format("the %s value to set", property.getName())));
                requiredProperties.forEach(property -> comment
                        .param(property.getName(), String.format("the %s value to set", property.getName())));
            });

            classBlock.annotation("JsonCreator");
            classBlock.publicConstructor(String.format("%1$s(%2$s)", model.getName(), ctorArgs), (constructor) ->
            {
                if (!requiredParentProperties.isEmpty()) {
                    constructor.line(String.format("super(%1$s);",
                        requiredParentProperties.stream().map(ClientModelProperty::getName)
                            .collect(Collectors.joining(", "))));
                }

                if (!constantProperties.isEmpty()) {
                    for (ClientModelProperty constantProperty : constantProperties) {
                        constructor.line(String
                            .format("%1$s = %2$s;", constantProperty.getName(), constantProperty.getDefaultValue()));
                    }
                }
                for (ClientModelProperty requiredProperty : requiredProperties) {
                    constructor.line(String
                        .format("this.%1$s = %2$s;", requiredProperty.getName(), requiredProperty.getName()));
                }
            });

        } else if (!constantProperties.isEmpty()) {
            classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                comment.description(String.format("Creates an instance of %1$s class.", model.getName())));
            classBlock.publicConstructor(String.format("%1$s()", model.getName()), (constructor) ->
            {
                for (ClientModelProperty constantProperty : constantProperties) {
                    constructor.line(
                        String.format("%1$s = %2$s;", constantProperty.getName(), constantProperty.getDefaultValue()));
                }
            });
        } else if (model.isStronglyTypedHeader() && settings.isCustomStronglyTypedHeaderDeserializationUsed()) {
            classBlock.lineComment("HttpHeaders containing the raw property values.");
            classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), comment -> {
                comment.description(String.format("Creates an instance of %1$s class.", model.getName()));
                comment.param("rawHeaders", "The raw HttpHeaders that will be used to create the property values.");
            });
            classBlock.publicConstructor(String.format("%s(HttpHeaders rawHeaders)", model.getName()), constructor -> {
                for (ClientModelProperty property : model.getProperties()) {
                    addConstructorCustomDeserialization(property, constructor);
                }
            });
        }
    }

    private static void addConstructorCustomDeserialization(ClientModelProperty property, JavaBlock javaBlock) {
        // HeaderCollections need special handling as they may have multiple values that need to be
        // retrieved from the raw headers.
        if (!CoreUtils.isNullOrEmpty(property.getHeaderCollectionPrefix())) {
            generateHeaderCollectionDeserialization(property, javaBlock);
        } else {
            generateHeaderDeserializationFunction(property, javaBlock);
        }
    }

    /**
     * Adds a getter method.
     *
     * @param propertyType The property type.
     * @param propertyClientType The client property type.
     * @param property The property.
     * @param settings AutoRest configuration settings.
     * @param methodBlock Where the getter method is being added.
     */
    private static void addGetterMethod(IType propertyType, IType propertyClientType, ClientModelProperty property,
        JavaSettings settings, JavaBlock methodBlock) {
        String sourceTypeName = propertyType.toString();
        String targetTypeName = propertyClientType.toString();
        String expression = String.format("this.%s", property.getName());
        if (propertyType.equals(ArrayType.ByteArray)) {
            expression = String.format("CoreUtils.clone(%s)", expression);
        }

        if (sourceTypeName.equals(targetTypeName)) {
            if (settings.shouldGenerateXmlSerialization() && property.getIsXmlWrapper()
                && (property.getWireType() instanceof IterableType || property.getWireType() instanceof ListType)) {
                methodBlock.ifBlock(String.format("this.%s == null", property.getName()), ifBlock ->
                    ifBlock.line("this.%s = new %s(new ArrayList<%s>());", property.getName(),
                        getPropertyXmlWrapperClassName(property),
                        ((GenericType) property.getWireType()).getTypeArguments()[0]));
                methodBlock.methodReturn(String.format("this.%s.items", property.getName()));
            } else {
                methodBlock.methodReturn(expression);
            }
        } else {
            // If the wire type was null, return null as the returned conversion could, and most likely would, result
            // in a NullPointerException.
            methodBlock.ifBlock(String.format("%s == null", expression),
                (ifBlock) -> ifBlock.methodReturn(propertyClientType.defaultValueExpression()));

            // Return the conversion of the wire type to the client type. An example would be a wire type of
            // DateTimeRfc1123 and a client type of OffsetDateTime (type a consumer would use), this makes the return
            // "this.value.getDateTime()".
            methodBlock.methodReturn(propertyType.convertToClientType(expression));
        }
    }

    private static void generateHeaderCollectionDeserialization(ClientModelProperty property, JavaBlock block) {
        int headerPrefixLength = property.getHeaderCollectionPrefix().length();
        // HeaderCollections are always Maps that use String as the key.
        MapType wireType = (MapType) property.getWireType();
        String collectionName = property.getName() + "HeaderCollection";

        // Prefix the map with the property name for the cases where multiple header collections exist.
        // TODO: If there are multiple header collections they can be combined into a single loop over the headers.
        // Not a high priority as loop should be relatively cheap, and only constant time.
        block.line("%s %s = new HashMap<>();", wireType, collectionName);
        block.line();
        block.block("for (HttpHeader header : rawHeaders)", body -> {
            body.ifBlock(String.format("!header.getName().startsWith(\"%s\")", property.getHeaderCollectionPrefix()),
                ifBlock -> ifBlock.line("continue;"));
            body.line(collectionName + ".put(header.getName().substring(%d), header.getValue());", headerPrefixLength);
        });
        block.line("this.%s = %s;", property.getName(), collectionName);
    }

    private static void generateHeaderDeserializationFunction(ClientModelProperty property, JavaBlock javaBlock) {
        IType wireType = property.getWireType();

        // No matter the wire type the rawHeaders will need to be accessed.
        String rawHeaderAccess = String.format("rawHeaders.getValue(\"%s\")", property.getSerializedName());

        boolean needsToBeGuardedAgainstNull = false;
        String setter;
        if (wireType == PrimitiveType.Boolean || wireType == ClassType.Boolean) {
            needsToBeGuardedAgainstNull = wireType == ClassType.Boolean;
            setter = String.format("Boolean.parseBoolean(%s)", rawHeaderAccess);
        } else if (wireType == PrimitiveType.Double || wireType == ClassType.Double) {
            needsToBeGuardedAgainstNull = wireType == ClassType.Double;
            setter = String.format("Double.parseDouble(%s)", rawHeaderAccess);
        } else if (wireType == PrimitiveType.Float || wireType == ClassType.Float) {
            needsToBeGuardedAgainstNull = wireType == ClassType.Float;
            setter = String.format("Float.parseFloat(%s)", rawHeaderAccess);
        } else if (wireType == PrimitiveType.Int || wireType == ClassType.Integer) {
            needsToBeGuardedAgainstNull = wireType == ClassType.Integer;
            setter = String.format("Integer.parseInt(%s)", rawHeaderAccess);
        } else if (wireType == PrimitiveType.Long || wireType == ClassType.Long) {
            needsToBeGuardedAgainstNull = wireType == ClassType.Long;
            setter = String.format("Long.parseLong(%s)", rawHeaderAccess);
        } else if (wireType == ArrayType.ByteArray) {
            needsToBeGuardedAgainstNull = true;
            setter = String.format("Base64.getDecoder().decode(%s)", rawHeaderAccess);
        } else if (wireType == ClassType.String) {
            setter = rawHeaderAccess;
        } else if (wireType == ClassType.DateTimeRfc1123) {
            needsToBeGuardedAgainstNull = true;
            setter = String.format("new DateTimeRfc1123(%s)", rawHeaderAccess);
        } else if (wireType == ClassType.DateTime) {
            needsToBeGuardedAgainstNull = true;
            setter = String.format("OffsetDateTime.parse(%s)", rawHeaderAccess);
        } else if (wireType == ClassType.LocalDate) {
            needsToBeGuardedAgainstNull = true;
            setter = String.format("LocalDate.parse(%s)", rawHeaderAccess);
        } else if (wireType == ClassType.Duration) {
            needsToBeGuardedAgainstNull = true;
            setter = String.format("Duration.parse(%s)", rawHeaderAccess);
        } else if (wireType instanceof EnumType) {
            needsToBeGuardedAgainstNull = true;
            EnumType enumType = (EnumType) wireType;
            setter = String.format("%s.%s(%s)", enumType.getName(), enumType.getFromJsonMethodName(), rawHeaderAccess);
        } else {
            setter = String.format("JacksonAdapter.createDefaultSerializerAdapter().deserializeHeader(rawHeaders.get(\"%s\"), %s)",
                property.getSerializedName(), getWireTypeJavaType(wireType));
        }

        if (needsToBeGuardedAgainstNull) {
            javaBlock.ifBlock(String.format("%s != null", rawHeaderAccess),
                ifBlock -> ifBlock.line("this.%s = %s;", property.getName(), setter));
        } else {
            javaBlock.line("this.%s = %s;", property.getName(), setter);
        }
    }

    private static String getWireTypeJavaType(IType iType) {
        if (iType instanceof ArrayType || iType instanceof ClassType) {
            // Both ArrayType and ClassType have toString methods that return the text representation of the type,
            // for example "int[]" or "HttpHeaders". These support adding ".class" to get the Java runtime Class.
            return iType + ".class";
        } else {
            // All other types are GenericTypes. GenericType's toString returns the Java code generic representation,
            // such as "List<Integer>" or "Map<String, Object>".
            //
            // Use a new TypeReference to get the representing Type for the wire type.
            return "new TypeReference<" + iType + ">() {}.getJavaType()";
        }
    }

    private void addPropertyValidations(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        if (settings.shouldClientSideValidations()) {
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
                    if (property.isRequired() && !property.getIsReadOnly() && !property.getIsConstant() && !(property.getClientType() instanceof PrimitiveType)) {
                        JavaIfBlock nullCheck = methodBlock.ifBlock(String.format("%s() == null", getGetterName(model, property)), ifBlock -> {
                            final String errorMessage = String.format("\"Missing required property %s in model %s\"", property.getName(), model.getName());
                            if (settings.shouldClientLogger()) {
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
    private static String getPropertyXmlWrapperClassName(ClientModelProperty property) {
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
                    propertyReferences.addAll(parentModel.getProperties().stream()
                            .filter(p -> !p.getClientFlatten() && !p.isAdditionalProperties())
                            .map(ClientModelPropertyReference::ofParentProperty)
                            .collect(Collectors.toList()));
                }

                if (parentModel.getPropertyReferences() != null) {
                    propertyReferences.addAll(parentModel.getPropertyReferences().stream()
                            .filter(ClientModelPropertyReference::isFromFlattenedProperty)
                            .map(ClientModelPropertyReference::ofParentProperty)
                            .collect(Collectors.toList()));
                }
            }

            lastParentName = parentModelName;
            parentModelName = parentModel == null ? null : parentModel.getParentModelName();
        }
        return propertyReferences;
    }

    // Javadoc for getter method
    private void generateGetterJavadoc(JavaClass classBlock, ClientModel model, ClientModelPropertyAccess property) {
        classBlock.javadocComment(JavaSettings.getInstance().getMaximumJavadocCommentWidth(), comment -> {
            comment.description(String.format("Get the %1$s property: %2$s", property.getName(), property.getDescription()));
            comment.methodReturns(String.format("the %1$s value", property.getName()));
        });
    }

    // Javadoc for setter method
    private void generateSetterJavadoc(JavaClass classBlock, ClientModel model, ClientModelPropertyAccess property) {
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
}
