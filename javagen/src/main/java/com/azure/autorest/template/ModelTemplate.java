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

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/** 
 Writes a ClientModel to a JavaFile.
*/
public class ModelTemplate implements IJavaTemplate<ClientModel, JavaFile>
{
    private static ModelTemplate _instance = new ModelTemplate();
    public static ModelTemplate getInstance()
    {
        return _instance;
    }

    private ModelTemplate()
    {
    }

    public final void write(ClientModel model, JavaFile javaFile)
    {
        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<String>();
        model.addImportsTo(imports, settings);

        javaFile.declareImport(imports);

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
        {
                comment.description(model.getDescription());
        });

        boolean hasDerivedModels = !model.getDerivedModels().isEmpty();
        if (model.getIsPolymorphic())
        {
            javaFile.annotation(String.format("JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = \"%1$s\"%2$s)", model.getPolymorphicDiscriminator(), (hasDerivedModels ? String.format(", defaultImpl = %1$s.class", model.getName()) : "")));
            javaFile.annotation(String.format("JsonTypeName(\"%1$s\")", model.getSerializedName()));

            if (hasDerivedModels)
            {
                javaFile.line("@JsonSubTypes({");
                javaFile.indent(() -> {
                        Function<ClientModel, String> getDerivedTypeAnnotation = (ClientModel derivedType) -> String.format("@JsonSubTypes.Type(name = \"%1$s\", value = %2$s.class)", derivedType.getSerializedName(), derivedType.getName());

                        for (int i = 0; i != model.getDerivedModels().size() - 1; i++)
                        {
                            ClientModel derivedModel = model.getDerivedModels().get(i);
                            javaFile.line(getDerivedTypeAnnotation.apply(derivedModel) + ',');
                        }
                        javaFile.line(getDerivedTypeAnnotation.apply(model.getDerivedModels().get(model.getDerivedModels().size() - 1)));
                });
                javaFile.line("})");
            }
        }

        if (settings.getShouldGenerateXmlSerialization())
        {
            javaFile.annotation(String.format("JacksonXmlRootElement(localName = \"%1$s\")", model.getXmlName()));
        }

        if (model.getNeedsFlatten())
        {
            javaFile.annotation("JsonFlatten");
        }

        ArrayList<JavaModifier> classModifiers = new ArrayList<JavaModifier>();
        if (!hasDerivedModels && !model.getNeedsFlatten())
        {
            classModifiers.add(JavaModifier.Final);
        }

        String classNameWithBaseType = model.getName();
        if (model.getParentModel() != null)
        {
            classNameWithBaseType += String.format(" extends %1$s", model.getParentModel().getName());
        }
        javaFile.annotation("Fluent");
        javaFile.publicClass(classModifiers, classNameWithBaseType, (classBlock) ->
            {
                Function<ClientModelProperty, String> propertyXmlWrapperClassName = (ClientModelProperty property) -> property.getXmlName() + "Wrapper";

                for (ClientModelProperty property : model.getProperties())
                {
                    String xmlWrapperClassName = propertyXmlWrapperClassName.apply(property);
                    if (settings.getShouldGenerateXmlSerialization() && property.getIsXmlWrapper())
                    {
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

                    if (property.getHeaderCollectionPrefix() != null && !property.getHeaderCollectionPrefix().isEmpty())
                    {
                        classBlock.annotation("HeaderCollection(\"" + property.getHeaderCollectionPrefix() + "\")");
                    }
                    else if (settings.getShouldGenerateXmlSerialization() && property.getIsXmlAttribute())
                    {
                        String localName = settings.getShouldGenerateXmlSerialization() ? property.getXmlName() : property.getSerializedName();
                        classBlock.annotation(String.format("JacksonXmlProperty(localName = \"%1$s\", isAttribute = true)", localName));
                    }
                    else if (settings.getShouldGenerateXmlSerialization() && property.getWireType() instanceof ListType && !property.getIsXmlWrapper())
                    {
                        classBlock.annotation(String.format("JsonProperty(\"%1$s\")", property.getXmlListElementName()));
                    }
                    else if (property.getAnnotationArguments() != null && !property.getAnnotationArguments().isEmpty())
                    {
                        classBlock.annotation(String.format("JsonProperty(%1$s)", property.getAnnotationArguments()));
                    }

                    if (settings.getShouldGenerateXmlSerialization())
                    {
                        if (property.getIsXmlWrapper())
                        {
                            classBlock.privateMemberVariable(String.format("%1$s %2$s", xmlWrapperClassName, property.getName()));
                        }
                        else if (property.getWireType() instanceof ListType)
                        {
                            classBlock.privateMemberVariable(String.format("%1$s %2$s = new ArrayList<>()", property.getWireType(), property.getName()));
                        }
                        else
                        {
                            classBlock.privateMemberVariable(String.format("%1$s %2$s", property.getWireType(), property.getName()));
                        }
                    }
                    else
                    {
                        classBlock.privateMemberVariable(String.format("%1$s %2$s", property.getWireType(), property.getName()));
                    }
                }

                List<ClientModelProperty> constantProperties = model.getProperties().stream().filter(ClientModelProperty::getIsConstant).collect(Collectors.toList());
                if (!constantProperties.isEmpty())
                {
                    classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                    {
                        comment.description(String.format("Creates an instance of %1$s class.", model.getName()));
                    });
                    classBlock.publicConstructor(String.format("%1$s()", model.getName()), (constructor) ->
                    {
                        for (ClientModelProperty constantProperty : constantProperties)
                        {
                            constructor.line(String.format("%1$s = %2$s;", constantProperty.getName(), constantProperty.getDefaultValue()));
                        }
                    });
                }

                for (ClientModelProperty property : model.getProperties())
                {
                    IType propertyType = property.getWireType();
                    IType propertyClientType = propertyType.getClientType();

                    classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                    {
                        comment.description(String.format("Get the %1$s property: %2$s", property.getName(), property.getDescription()));
                        comment.methodReturns(String.format("the %1$s value", property.getName()));
                    });
                    classBlock.publicMethod(String.format("%1$s %2$s()", propertyClientType, property.getName()), (methodBlock) ->
                    {
                        String sourceTypeName = propertyType.toString();
                        String targetTypeName = propertyClientType.toString();
                        String expression = "this.{property.Name}";
                        if (propertyClientType.equals(ArrayType.ByteArray))
                        {
                            expression = "ImplUtils.clone({expression})";
                        }
                        if (sourceTypeName.equals(targetTypeName))
                        {
                            if (settings.getShouldGenerateXmlSerialization() && property.getIsXmlWrapper() && property.getWireType() instanceof ListType)
                            {
                                methodBlock.ifBlock("this.{property.Name} == null", ifBlock ->
                                        ifBlock.line("this.{property.Name} = new {propertyXmlWrapperClassName(property)}(new ArrayList<{listType.ElementType}>());"));
                                methodBlock.methodReturn("this.{property.Name}.items");
                            }
                            else
                            {
                                methodBlock.methodReturn("{expression}");
                            }
                        }
                        else
                        {
                            methodBlock.ifBlock("{expression} == null", (ifBlock) -> ifBlock.methodReturn("null"));

                            String propertyConversion = propertyType.convertToClientType(expression);

                            methodBlock.methodReturn(propertyConversion);
                        }
                    });

                    if (!property.getIsReadOnly())
                    {
                        classBlock.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                                {
                                        comment.description("Set the {property.Name} property: {property.Description}");
                        comment.param(property.getName(), "the {property.Name} value to set");
                        comment.methodReturns("the {model.Name} object itself.");
                        });
                        classBlock.publicMethod("{model.Name} {property.Name.ToCamelCase()}({propertyClientType} {property.Name})", (methodBlock) -> {
                                String expression;
                                if (propertyClientType.equals(ArrayType.ByteArray)) {
                                    expression = "ImplUtils.clone({expression})";
                                } else {
                                    expression = property.getName();
                                }
                                if (propertyClientType != propertyType) {
                                    methodBlock.ifBlock("{property.Name} == null", (ifBlock) -> ifBlock.line("this.{property.Name} = null;"))
                                        .elseBlock((elseBlock) -> {
                                                String sourceTypeName = propertyClientType.toString();
                                                String targetTypeName = propertyType.toString();
                                                String propertyConversion = propertyType.convertFromClientType(expression);
                                                elseBlock.line("this.{property.Name} = {propertyConversion};");
                                            });
                                } else {
                                    if (settings.getShouldGenerateXmlSerialization() && property.getIsXmlWrapper()) {
                                        methodBlock.line("this.{property.Name} = new {propertyXmlWrapperClassName(property)}({expression});");
                                    } else {
                                        methodBlock.line("this.{property.Name} = {expression};");
                                    }
                                }
                                methodBlock.methodReturn("this");
                            });
                    }
                }
            });
    }
}