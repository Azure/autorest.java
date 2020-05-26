// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.javamodel.JavaModifier;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaIfBlock;

import java.util.ArrayList;
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
        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<String>();
        if (settings.shouldClientSideValidations() && settings.shouldClientLogger()) {
            imports.add("com.fasterxml.jackson.annotation.JsonIgnore");
            ClassType.ClientLogger.addImportsTo(imports, false);
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

        if (model.getNeedsFlatten()) {
            javaFile.annotation("JsonFlatten");
        }

        ArrayList<JavaModifier> classModifiers = new ArrayList<JavaModifier>();
        if (!hasDerivedModels && !model.getNeedsFlatten()) {
            classModifiers.add(JavaModifier.Final);
        }

        String classNameWithBaseType = model.getName();
        if (model.getParentModelName() != null) {
            classNameWithBaseType += String.format(" extends %1$s", model.getParentModelName());
        }
        if (model.getProperties().stream().anyMatch(p -> !p.getIsReadOnly())) {
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

                        innerClass.annotation(String.format("JacksonXmlProperty(localName = \"%1$s\")", property.getXmlListElementName()));
                        innerClass.privateFinalMemberVariable(propertyClientType.toString(), "items");

                        innerClass.annotation("JsonCreator");
                        innerClass.privateConstructor(String.format("%1$s(@JacksonXmlProperty(localName = \"%2$s\") %3$s items)", xmlWrapperClassName, property.getXmlListElementName(), propertyClientType), constructor -> constructor.line("this.items = items;"));
                    });
                }

                classBlock.blockComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                {
                    comment.line(property.getDescription());
                });

                if (property.getHeaderCollectionPrefix() != null && !property.getHeaderCollectionPrefix().isEmpty()) {
                    classBlock.annotation("HeaderCollection(\"" + property.getHeaderCollectionPrefix() + "\")");
                } else if (settings.shouldGenerateXmlSerialization() && property.getIsXmlAttribute()) {
                    String localName = settings.shouldGenerateXmlSerialization() ? property.getXmlName() : property.getSerializedName();
                    classBlock.annotation(String.format("JacksonXmlProperty(localName = \"%1$s\", isAttribute = true)", localName));
                } else if (property.isAdditionalProperties()) {
                    classBlock.annotation(String.format("JsonIgnore"));
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

            List<ClientModelProperty> constantProperties = model.getProperties().stream().filter(ClientModelProperty::getIsConstant).collect(Collectors.toList());
            if (!constantProperties.isEmpty()) {
                classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                {
                    comment.description(String.format("Creates an instance of %1$s class.", model.getName()));
                });
                classBlock.publicConstructor(String.format("%1$s()", model.getName()), (constructor) ->
                {
                    for (ClientModelProperty constantProperty : constantProperties) {
                        constructor.line(String.format("%1$s = %2$s;", constantProperty.getName(), constantProperty.getDefaultValue()));
                    }
                });
            }

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
                        } else {
                            methodBlock.methodReturn(expression);
                        }
                    } else {
                        methodBlock.ifBlock(String.format("%s == null", expression), (ifBlock) -> ifBlock.methodReturn("null"));

                        String propertyConversion = propertyType.convertToClientType(expression);

                        methodBlock.methodReturn(propertyConversion);
                    }
                });

                if (!property.getIsReadOnly()) {
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
                            model.getName(), property.getSetterName(), propertyClientType, property.getName()), (methodBlock) -> {
                        String expression;
                        if (propertyClientType.equals(ArrayType.ByteArray)) {
                            expression = String.format("CoreUtils.clone(%s)", property.getName());
                        } else {
                            expression = property.getName();
                        }
                        if (propertyClientType != propertyType) {
                            methodBlock.ifBlock(String.format("%s == null", property.getName()), (ifBlock) -> ifBlock.line("this.%s = null;", property.getName()))
                                    .elseBlock((elseBlock) -> {
                                        String sourceTypeName = propertyClientType.toString();
                                        String targetTypeName = propertyType.toString();
                                        String propertyConversion = propertyType.convertFromClientType(expression);
                                        elseBlock.line("this.%s = %s;", property.getName(), propertyConversion);
                                    });
                        } else {
                            if (settings.shouldGenerateXmlSerialization() && property.getIsXmlWrapper()) {
                                methodBlock.line("this.%s = new %s(%s);", property.getName(), propertyXmlWrapperClassName.apply(property), expression);
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

            addPropertyValidations(classBlock, model, settings);
        });
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
                    String validation = property.getClientType().validate(property.getGetterName() + "()");
                    if (property.isRequired() && !property.getIsReadOnly() && !property.getIsConstant() && !(property.getClientType() instanceof PrimitiveType)) {
                        JavaIfBlock nullCheck = methodBlock.ifBlock(String.format("%s() == null", property.getGetterName()), ifBlock -> {
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
                        methodBlock.ifBlock(String.format("%s() != null", property.getGetterName()), ifBlock -> {
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
}
