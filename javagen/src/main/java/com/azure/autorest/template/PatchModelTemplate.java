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
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
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
import java.util.stream.Collectors;

/**
 * Writes a PatchClientModel to a JavaFile.
 */
public class PatchModelTemplate implements IJavaTemplate<ClientModel, JavaFile> {

    public static final String MISSING_SCHEMA = "MISSING·SCHEMA";

    private static final PatchModelTemplate INSTANCE = new PatchModelTemplate();

    protected PatchModelTemplate() {
    }

    public static PatchModelTemplate getInstance() {
        return INSTANCE;
    }

    @Override
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

        imports.add("com.azure.core.implementation.Option");

        javaFile.declareImport(imports);

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                comment.description(model.getDescription()));

        boolean hasDerivedModels = !model.getDerivedModels().isEmpty();
//        handlePolymorphism(model, hasDerivedModels, settings.isDiscriminatorPassedToChildDeserialization(), javaFile);

//        if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.TYPE && model.getNeedsFlatten()) {
//            javaFile.annotation("JsonFlatten");
//        }

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
        final String patchClassNameWithBaseType = classNameWithBaseType + "Patch";

        if (model.getProperties().stream().anyMatch(p -> !p.getIsReadOnly())
                || propertyReferences.stream().anyMatch(p -> !p.getIsReadOnly())) {
            javaFile.annotation("Fluent");
        } else {
            javaFile.annotation("Immutable");
        }
        javaFile.publicClass(classModifiers, patchClassNameWithBaseType, (classBlock) ->
        {
            addProperties(model, classBlock, settings);

            for (ClientModelProperty property : model.getProperties()) {
                IType propertyType = property.getWireType();
                IType propertyClientType = propertyType.getClientType();

                JavaVisibility methodVisibility = property.getClientFlatten() ? JavaVisibility.Private : JavaVisibility.Public;

                // setter
                if (!property.getIsReadOnly() && !(settings.isRequiredFieldsAsConstructorArgs() && property.isRequired()) && methodVisibility == JavaVisibility.Public) {
                    generateSetterJavadoc(classBlock, model, property);
                    TemplateUtil.addJsonSetter(classBlock, settings, property.getSerializedName());
                    IType setterPropertyType = ClientModelUtil.isClientModel(property.getWireType()) ? new ClassType.Builder().packageName(((ClassType) property.getWireType()).getPackage()).name(((ClassType) property.getWireType()).getName() + "Patch").build() : property.getWireType();
                    classBlock.method(methodVisibility, null, String.format("%s %s(%s %s)",
                                    patchClassNameWithBaseType, property.getSetterName(), setterPropertyType, property.getName()),
                            (methodBlock) -> {
                                String expression;
                                if (propertyClientType.equals(ArrayType.ByteArray)) {
                                    expression = String.format("CoreUtils.clone(%s)", property.getName());
                                } else {
                                    expression = property.getName();
                                }
                                methodBlock.ifBlock(String.format("%s == null", property.getName()),
                                                (ifBlock) -> ifBlock.line("this.%s = Option.empty();", property.getName()))
                                        .elseBlock((elseBlock) -> {
                                            String propertyConversion = propertyType.convertFromClientType(expression);
                                            elseBlock.line("this.%s = Option.of(%s);", property.getName(), propertyConversion);
                                        });
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
        });
    }

    /**
     * Adds the property fields to a class.
     *
     * @param model      The client model.
     * @param classBlock The Java class.
     * @param settings   AutoRest configuration settings.
     */
    private static void addProperties(ClientModel model, JavaClass classBlock, JavaSettings settings) {
        for (ClientModelProperty property : model.getProperties()) {

            classBlock.blockComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                    comment.line(property.getDescription()));

//            if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.FIELD && property.getNeedsFlatten()) {
//                classBlock.annotation("JsonFlatten");
//            }
//
//            // If the property is a polymorphic discriminator for the class add the annotation @JsonTypeId.
//            // This will indicate to Jackson that the discriminator serialization is determined by the property
//            // instead of the class level @JsonTypeName annotation. This prevents the discriminator property from
//            // being serialized twice, once for the class level annotation and again for the property annotation.
//            if (property.isPolymorphicDiscriminator()) {
//                classBlock.annotation("JsonTypeId");
//            }

            if (property.getHeaderCollectionPrefix() != null && !property.getHeaderCollectionPrefix().isEmpty()) {
                classBlock.annotation("HeaderCollection(\"" + property.getHeaderCollectionPrefix() + "\")");
            } else if (property.getAnnotationArguments() != null && !property.getAnnotationArguments().isEmpty()) {
                classBlock.annotation(String.format("JsonProperty(%1$s)", property.getAnnotationArguments()));
            }

            if (!property.isAdditionalProperties() && property.getClientType() instanceof MapType && settings.isFluent()) {
                classBlock.annotation("JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.ALWAYS)");
            }

            // handle x-ms-client-default
            if (property.getDefaultValue() != null) {
                classBlock.privateMemberVariable(String.format("%1$s %2$s = Option.of(%3$s)", new GenericType("com.azure.core.implementation", "Option", property.getWireType()), property.getName(), property.getDefaultValue()));
            } else {
                IType propertyType = ClientModelUtil.isClientModel(property.getWireType()) ? new ClassType.Builder().packageName(((ClassType) property.getWireType()).getPackage()).name(((ClassType) property.getWireType()).getName() + "Patch").build() : property.getWireType();
                classBlock.privateMemberVariable(String.format("%1$s %2$s", new GenericType("com.azure.core.implementation", "Option", propertyType), property.getName()));
            }
        }
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

    // Javadoc for setter method
    private void generateSetterJavadoc(JavaClass classBlock, ClientModel model, ClientModelPropertyAccess property) {
        classBlock.javadocComment(JavaSettings.getInstance().getMaximumJavadocCommentWidth(), (comment) -> {
            if (property.getDescription() == null || property.getDescription().contains(MISSING_SCHEMA)) {
                comment.description(String.format("Set the %s property", property.getName()));
            } else {
                comment.description(String.format("Set the %s property: %s", property.getName(), property.getDescription()));
            }
            comment.param(property.getName(), String.format("the %s value to set", property.getName()));
            comment.methodReturns(String.format("the %s object itself.", model.getName() + "Patch"));
        });
    }


}
