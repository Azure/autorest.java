// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.IterableType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaModifier;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.Collections;
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
    private static ModelTemplate _instance = new ModelTemplate();

    protected ModelTemplate() {
    }

    public static ModelTemplate getInstance() {
        return _instance;
    }

    public final void write(ClientModel model, JavaFile javaFile) {
        if (model.getParentModelName() != null && model.getParentModelName().equals(model.getName())) {
            throw new IllegalStateException("Parent model name is same as model name: " + model.getName());
        }

        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<String>();
        if (settings.shouldClientSideValidations() && settings.shouldClientLogger()) {
            imports.add("com.fasterxml.jackson.annotation.JsonIgnore");
            ClassType.ClientLogger.addImportsTo(imports, false);
        }

        imports.add("com.fasterxml.jackson.annotation.JsonCreator");
        String lastParentName = model.getName();
        ClientModel parentModel = ClientModels.Instance.getModel(model.getParentModelName());
        while (parentModel != null && !lastParentName.equals(parentModel.getName())) {
            imports.addAll(parentModel.getImports());
            lastParentName = parentModel.getName();
            parentModel = ClientModels.Instance.getModel(parentModel.getParentModelName());
        }

        List<ClientModelPropertyReference> propertyReferences = this.getClientModelPropertyReferences(model);
        if (JavaSettings.getInstance().isOverrideSetterFromSuperclass()) {
            propertyReferences.forEach(p -> p.getReferenceProperty().addImportsTo(imports, false));
        }

        model.addImportsTo(imports, settings);

        javaFile.declareImport(imports);

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
        {
            comment.description(model.getDescription());
        });

        boolean hasDerivedModels = !model.getDerivedModels().isEmpty();
        if (model.getIsPolymorphic()) {
            javaFile.annotation(String.format("JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = \"%1$s\"%2$s)", model.getPolymorphicDiscriminator(), (hasDerivedModels ? String.format(", defaultImpl = %1$s.class", model.getName()) : "")));
            javaFile.annotation(String.format("JsonTypeName(\"%1$s\")", model.getSerializedName()));

            if (hasDerivedModels) {
                javaFile.line("@JsonSubTypes({");
                javaFile.indent(() -> {
                    Function<ClientModel, String> getDerivedTypeAnnotation = (ClientModel derivedType) -> String.format("@JsonSubTypes.Type(name = \"%1$s\", value = %2$s.class)", derivedType.getSerializedName(), derivedType.getName());

                    for (int i = 0; i != model.getDerivedModels().size() - 1; i++) {
                        ClientModel derivedModel = model.getDerivedModels().get(i);
                        javaFile.line(getDerivedTypeAnnotation.apply(derivedModel) + ',');
                    }
                    javaFile.line(getDerivedTypeAnnotation.apply(model.getDerivedModels().get(model.getDerivedModels().size() - 1)));
                });
                javaFile.line("})");
            }
        }

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

        ArrayList<JavaModifier> classModifiers = new ArrayList<JavaModifier>();
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
                || propertyReferences.stream().anyMatch(p -> !p.getReferenceProperty().getIsReadOnly())) {
            javaFile.annotation("Fluent");
        } else {
            javaFile.annotation("Immutable");
        }
        javaFile.publicClass(classModifiers, classNameWithBaseType, (classBlock) ->
        {
            if (settings.shouldClientSideValidations() && settings.shouldClientLogger()) {
                classBlock.annotation("JsonIgnore");
                classBlock.privateFinalMemberVariable(ClassType.ClientLogger.toString(), String.format("logger = new ClientLogger(%1$s.class)", model.getName()));
            }

            Function<ClientModelProperty, String> propertyXmlWrapperClassName = (ClientModelProperty property) -> property.getXmlName() + "Wrapper";

            for (ClientModelProperty property : model.getProperties()) {
                String xmlWrapperClassName = propertyXmlWrapperClassName.apply(property);
                if (settings.shouldGenerateXmlSerialization() && property.getIsXmlWrapper()) {
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
                {
                    comment.line(property.getDescription());
                });

                if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.FIELD && property.getNeedsFlatten()) {
                    classBlock.annotation("JsonFlatten");
                }
                if (property.getHeaderCollectionPrefix() != null && !property.getHeaderCollectionPrefix().isEmpty()) {
                    classBlock.annotation("HeaderCollection(\"" + property.getHeaderCollectionPrefix() + "\")");
                } else if (settings.shouldGenerateXmlSerialization() && property.getIsXmlAttribute()) {
                    classBlock.annotation(String.format("JacksonXmlProperty(localName = \"%1$s\", isAttribute = true)",
                            property.getXmlName()));
                } else if (settings.shouldGenerateXmlSerialization() && property.getXmlNamespace() != null && !property.getXmlNamespace().isEmpty()) {
                    classBlock.annotation(String.format("JacksonXmlProperty(localName = \"%1$s\", namespace = \"%2$s\")",
                            property.getXmlName(), property.getXmlNamespace()));
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
                    classBlock.privateMemberVariable(String.format("%1$s %2$s", property.getWireType(), property.getName()));
                }
            }

            List<ClientModelProperty> constantProperties = model.getProperties().stream()
                    .filter(clientModelProperty -> clientModelProperty.getIsConstant() && clientModelProperty.isRequired())
                    .collect(Collectors.toList());
            List<ClientModelProperty> requiredProperties =
                model.getProperties().stream().filter(ClientModelProperty::isRequired).collect(Collectors.toList());

            addModelConstructor(model, settings, classBlock, constantProperties, requiredProperties);

            for (ClientModelProperty property : model.getProperties()) {
                IType propertyType = property.getWireType();
                IType propertyClientType = propertyType.getClientType();

                classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                {
                    comment.description(String.format("Get the %1$s property: %2$s", property.getName(), property.getDescription()));
                    comment.methodReturns(String.format("the %1$s value", property.getName()));
                });
                if (property.isAdditionalProperties()) {
                    classBlock.annotation("JsonAnyGetter");
                }
                classBlock.publicMethod(String.format("%1$s %2$s()", propertyClientType, getGetterName(model, property)), (methodBlock) ->
                {
                    String sourceTypeName = propertyType.toString();
                    String targetTypeName = propertyClientType.toString();
                    String expression = String.format("this.%s", property.getName());
                    if (propertyType.equals(ArrayType.ByteArray)) {
                        expression = String.format("CoreUtils.clone(%s)", expression);
                    }
                    if (sourceTypeName.equals(targetTypeName)) {
                        if (settings.shouldGenerateXmlSerialization() && property.getIsXmlWrapper() && property.getWireType() instanceof ListType) {
                            methodBlock.ifBlock(String.format("this.%s == null", property.getName()), ifBlock ->
                                    ifBlock.line("this.%s = new %s(new ArrayList<%s>());",
                                            property.getName(),
                                            propertyXmlWrapperClassName.apply(property),
                                            ((ListType) property.getWireType()).getElementType()));
                            methodBlock.methodReturn(String.format("this.%s.items", property.getName()));
                        } else if (settings.shouldGenerateXmlSerialization() && property.getIsXmlWrapper() && property.getWireType() instanceof IterableType) {
                            methodBlock.ifBlock(String.format("this.%s == null", property.getName()), ifBlock ->
                                    ifBlock.line("this.%s = new %s(new ArrayList<%s>());",
                                            property.getName(),
                                            propertyXmlWrapperClassName.apply(property),
                                            ((IterableType) property.getWireType()).getElementType()));
                            methodBlock.methodReturn(String.format("this.%s.items", property.getName()));
                        } else {
                            methodBlock.methodReturn(expression);
                        }
                    } else {
                        methodBlock.ifBlock(String.format("%s == null", expression), (ifBlock) -> ifBlock.methodReturn("null"));

                        String propertyConversion = propertyType.convertToClientType(expression);

                        methodBlock.methodReturn(propertyConversion);
                    }
                });

                if(!property.getIsReadOnly() && !(settings.isRequiredFieldsAsConstructorArgs() && property.isRequired())) {
                    classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) -> {
                        if (property.getDescription() == null || property.getDescription().contains(MISSING_SCHEMA)) {
                            comment.description(String.format("Set the %s property", property.getName()));
                        } else {
                            comment.description(String
                                .format("Set the %s property: %s", property.getName(), property.getDescription()));
                        }
                        comment.param(property.getName(), String.format("the %s value to set", property.getName()));
                        comment.methodReturns(String.format("the %s object itself.", model.getName()));
                    });

                    classBlock.publicMethod(String.format("%s %s(%s %s)",
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
                        methodBlock.ifBlock(String.format("%s == null", property.getName()), ifBlock -> {
                           ifBlock.line("%s = new HashMap<>();", property.getName());
                        });
                        methodBlock.line("%s.put(%s, value);", property.getName(), model.getNeedsFlatten() ? "key.replace(\"\\\\.\", \".\")" : "key");
                    });
                }
            }

            if (JavaSettings.getInstance().isOverrideSetterFromSuperclass()) {
                for (ClientModelPropertyReference propertyReference : propertyReferences) {
                    ClientModelProperty parentProperty = propertyReference.getReferenceProperty();
                    if (!parentProperty.getIsReadOnly() && !(settings.isRequiredFieldsAsConstructorArgs() && parentProperty.isRequired())) {
                        classBlock.javadocComment(JavaJavadocComment::inheritDoc);
                        classBlock.annotation("Override");
                        classBlock.publicMethod(String.format("%s %s(%s %s)",
                                model.getName(),
                                parentProperty.getSetterName(),
                                parentProperty.getWireType() == null ? parentProperty.getClientType() : parentProperty.getWireType().getClientType(),
                                parentProperty.getName()),
                                methodBlock -> {
                                    methodBlock.line(String.format("super.%1$s(%2$s);", parentProperty.getSetterName(), parentProperty.getName()));
                                    methodBlock.methodReturn("this");
                                });
                    }
                }
            }

            addPropertyValidations(classBlock, model, settings);
        });
    }

    private void addModelConstructor(ClientModel model, JavaSettings settings, JavaClass classBlock,
        List<ClientModelProperty> constantProperties, List<ClientModelProperty> allRequiredProperties) {

        List<ClientModelProperty> requiredProperties =
                allRequiredProperties.stream().filter(property -> !property.getIsConstant()).collect(Collectors.toList());
        String lastParentName = model.getName();
        ClientModel parentModel = ClientModels.Instance.getModel(model.getParentModelName());
        List<ClientModelProperty> requiredParentProperties = new ArrayList<>();
        while (parentModel != null && !lastParentName.equals(parentModel.getName())) {
            List<ClientModelProperty> ctorArgs =
                parentModel.getProperties().stream().filter(ClientModelProperty::isRequired)
                    .filter(property -> !property.getIsConstant())
                    .collect(Collectors.toList());
            // this will be reversed again, so, it will be in the right order if a
            // super class has multiple ctor args
            Collections.reverse(ctorArgs);
            requiredParentProperties.addAll(ctorArgs);

            lastParentName = parentModel.getName();
            parentModel = ClientModels.Instance.getModel(parentModel.getParentModelName());
        }

        if (settings.isRequiredFieldsAsConstructorArgs() && (!requiredProperties.isEmpty() || !requiredParentProperties
            .isEmpty())) {

            String requiredCtorArgs = requiredProperties.stream()
                .map(property -> String.format("@JsonProperty(%1$s )%2$s %3$s", property.getAnnotationArguments(),
                    property.getClientType().toString(), property.getName())).collect(Collectors.joining(", "));

            String requiredParentCtorArgs = "";

            if (!requiredParentProperties.isEmpty()) {
                Collections.reverse(requiredParentProperties);
                requiredParentCtorArgs = requiredParentProperties.stream().map(property -> String.format(
                    "@JsonProperty(%1$s )%2$s %3$s", property.getAnnotationArguments(),
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

                requiredParentProperties.stream().forEach(property -> comment
                        .param(property.getName(), String.format("the %s value to set", property.getName())));
                requiredProperties.stream().forEach(property -> comment
                        .param(property.getName(), String.format("the %s value to set", property.getName())));
            });


            classBlock.annotation("JsonCreator");
            classBlock.publicConstructor(String.format("%1$s(%2$s)", model.getName(), ctorArgs.toString()), (constructor) ->
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
            {
                comment.description(String.format("Creates an instance of %1$s class.", model.getName()));
            });
            classBlock.publicConstructor(String.format("%1$s()", model.getName()), (constructor) ->
            {
                for (ClientModelProperty constantProperty : constantProperties) {
                    constructor.line(
                        String.format("%1$s = %2$s;", constantProperty.getName(), constantProperty.getDefaultValue()));
                }
            });
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
                                        "throw logger.logExceptionAsError(new IllegalArgumentException(%s));",
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
                        methodBlock.ifBlock(String.format("%s() != null", getGetterName(model, property)), ifBlock -> {
                            ifBlock.line(validation + ";");
                        });
                    }
                }
            });
        }
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
            ClientModel parentModel = ClientModels.Instance.getModel(parentModelName);
            if (parentModel != null) {
                if (parentModel.getProperties() != null) {
                    propertyReferences.addAll(parentModel.getProperties().stream()
                            .filter(p -> !("additionalProperties".equals(p.getName()) && CoreUtils.isNullOrEmpty(p.getSerializedName())))   // exclude `additionalProperties`
                            .map(ClientModelPropertyReference::new)
                            .collect(Collectors.toList()));
                }
            }

            lastParentName = parentModelName;
            parentModelName = parentModel == null ? null : parentModel.getParentModelName();
        }
        return propertyReferences;
    }
}
