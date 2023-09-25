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
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
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

        imports.add(JsonCreator.class.getName());

        if (settings.isGettersAndSettersAnnotatedForSerialization()) {
            imports.add(JsonGetter.class.getName());
            imports.add(JsonSetter.class.getName());
        }

        String lastParentName = model.getName();
        ClientModel parentModel = ClientModelUtil.getClientModel(model.getParentModelName());
        while (parentModel != null && !lastParentName.equals(parentModel.getName())) {
            imports.addAll(parentModel.getImports());
            lastParentName = parentModel.getName();
            parentModel = ClientModelUtil.getClientModel(parentModel.getParentModelName());
        }

        List<ClientModelPropertyReference> propertyReferences = this.getClientModelPropertyReferences(model);

        propertyReferences.forEach(p -> p.addImportsTo(imports, false));

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
            classModifiers.add(JavaModifier.Final);
        }

        String classNameWithBaseType = model.getName();
        if (model.getParentModelName() != null) {
            classNameWithBaseType += String.format(" extends %1$s", model.getParentModelName());
        }
        final String patchClassNameWithBaseType = classNameWithBaseType + "Patch";

        if (model.getProperties().stream().anyMatch(p -> !p.isReadOnly())
                || propertyReferences.stream().anyMatch(p -> !p.isReadOnly())) {
            javaFile.annotation("Fluent");
        }

        javaFile.publicClass(classModifiers, patchClassNameWithBaseType, (classBlock) ->
        {
            addProperties(model, classBlock, settings);

            for (ClientModelProperty property : model.getProperties()) {
                IType propertyType = property.getWireType();
                IType propertyClientType = propertyType.getClientType();

                JavaVisibility methodVisibility = property.getClientFlatten() ? JavaVisibility.Private : JavaVisibility.Public;

                // setter
                if (!property.isReadOnly() && !(settings.isRequiredFieldsAsConstructorArgs() && property.isRequired()) && methodVisibility == JavaVisibility.Public) {
                    generateSetterJavadoc(classBlock, model, property);
                    TemplateUtil.addJsonSetter(classBlock, settings, property.getSerializedName());
                    IType setterPropertyType = ClientModelUtil.isClientModel(property.getWireType()) ? new ClassType.Builder().packageName(((ClassType) property.getWireType()).getPackage()).name(((ClassType) property.getWireType()).getName() + "Patch").build() : property.getWireType();
                    IType setterPropertyOptionType = new GenericType("com.azure.core.implementation", "Option", setterPropertyType);
                    classBlock.method(methodVisibility, null, String.format("%s %s(%s %s)",
                                    patchClassNameWithBaseType, property.getSetterName(), setterPropertyOptionType, property.getName()),
                            (methodBlock) -> {
                                String expression;
                                if (propertyClientType.equals(ArrayType.BYTE_ARRAY)) {
                                    expression = String.format("CoreUtils.clone(%s)", property.getName());
                                } else {
                                    expression = property.getName();
                                }
                                if (setterPropertyType.isNullable()) {
                                    String propertyConversion = propertyType.convertFromClientType(expression);
                                    methodBlock.line("this.%s = %s;", property.getName(), propertyConversion);
                                } else {
                                    String propertyConversion = propertyType.convertFromClientType(expression);
                                    methodBlock.line("this.%s = Option.of(%s);", property.getName(), propertyConversion);
                                }
                                methodBlock.methodReturn("this");
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

            if (property.getAnnotationArguments() != null && !property.getAnnotationArguments().isEmpty()) {
                classBlock.annotation(String.format("JsonProperty(%1$s)", property.getAnnotationArguments()));
            }

            if (!property.isAdditionalProperties() && property.getClientType() instanceof MapType && settings.isFluent()) {
                classBlock.annotation("JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.ALWAYS)");
            }

            IType propertyType = ClientModelUtil.isClientModel(property.getWireType()) ? new ClassType.Builder().packageName(((ClassType) property.getWireType()).getPackage()).name(((ClassType) property.getWireType()).getName() + "Patch").build() : property.getWireType();
            classBlock.privateMemberVariable(String.format("%1$s %2$s", new GenericType("com.azure.core.implementation", "Option", propertyType), property.getName()));
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
