// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaModifier;

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
    private static ModelTemplate _instance = new ModelTemplate();

    private ModelTemplate() {
    }

    public static ModelTemplate getInstance() {
        return _instance;
    }

    public final void write(ClientModel model, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<String>();
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
            javaFile.annotation(String.format("JacksonXmlRootElement(localName = \"%1$s\")", model.getXmlName()));
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
        javaFile.annotation("Fluent");
        javaFile.publicClass(classModifiers, classNameWithBaseType, (classBlock) ->
        {
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
                classBlock.publicMethod(String.format("%1$s %2$s()", propertyClientType, property.getGetterName()), (methodBlock) ->
                {
                    String sourceTypeName = propertyType.toString();
                    String targetTypeName = propertyClientType.toString();
                    String expression = String.format("this.%s", property.getName());
                    if (propertyClientType.equals(ArrayType.ByteArray)) {
                        expression = String.format("ImplUtils.clone(%s)", expression);
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
                        comment.description(String.format("Set the %s property: %s", property.getName(), property.getDescription()));
                        comment.param(property.getName(), String.format("the %s value to set", property.getName()));
                        comment.methodReturns(String.format("the %s object itself.", model.getName()));
                    });
                    classBlock.publicMethod(String.format("%s %s(%s %s)",
                            model.getName(), property.getSetterName(), propertyClientType, property.getName()), (methodBlock) -> {
                        String expression;
                        if (propertyClientType.equals(ArrayType.ByteArray)) {
                            expression = String.format("ImplUtils.clone(%s)", property.getName());
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
            }
        });
    }
}