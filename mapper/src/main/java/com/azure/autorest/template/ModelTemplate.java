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

    public final void Write(ClientModel model, JavaFile javaFile)
    {
        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<String>();
        model.AddImportsTo(imports, settings);

        javaFile.Import(imports);

        javaFile.JavadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
        {
                comment.Description(model.getDescription());
        });

        boolean hasDerivedModels = !model.getDerivedModels().isEmpty();
        if (model.getIsPolymorphic())
        {
            javaFile.Annotation(String.format("JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = \"%1$s\"%2$s)", model.getPolymorphicDiscriminator(), (hasDerivedModels ? String.format(", defaultImpl = %1$s.class", model.getName()) : "")));
            javaFile.Annotation(String.format("JsonTypeName(\"%1$s\")", model.getSerializedName()));

            if (hasDerivedModels)
            {
                javaFile.Line("@JsonSubTypes({");
                javaFile.Indent(() -> {
                        Function<ClientModel, String> getDerivedTypeAnnotation = (ClientModel derivedType) -> String.format("@JsonSubTypes.Type(name = \"%1$s\", value = %2$s.class)", derivedType.getSerializedName(), derivedType.getName());

                        for (int i = 0; i != model.getDerivedModels().size() - 1; i++)
                        {
                            ClientModel derivedModel = model.getDerivedModels().get(i);
                            javaFile.Line(getDerivedTypeAnnotation.apply(derivedModel) + ',');
                        }
                        javaFile.Line(getDerivedTypeAnnotation.apply(model.getDerivedModels().get(model.getDerivedModels().size() - 1)));
                });
                javaFile.Line("})");
            }
        }

        if (settings.getShouldGenerateXmlSerialization())
        {
            javaFile.Annotation(String.format("JacksonXmlRootElement(localName = \"%1$s\")", model.getXmlName()));
        }

        if (model.getNeedsFlatten())
        {
            javaFile.Annotation("JsonFlatten");
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
        javaFile.Annotation("Fluent");
        javaFile.PublicClass(classModifiers, classNameWithBaseType, (classBlock) ->
            {
                Function<ClientModelProperty, String> propertyXmlWrapperClassName = (ClientModelProperty property) -> property.getXmlName() + "Wrapper";

                for (ClientModelProperty property : model.getProperties())
                {
                    String xmlWrapperClassName = propertyXmlWrapperClassName.apply(property);
                    if (settings.getShouldGenerateXmlSerialization() && property.getIsXmlWrapper())
                    {
                        classBlock.PrivateStaticFinalClass(xmlWrapperClassName, innerClass ->
                        {
                            IType propertyClientType = property.getWireType().getClientType();

                            innerClass.Annotation(String.format("JacksonXmlProperty(localName = \"%1$s\")", property.getXmlListElementName()));
                            innerClass.PrivateFinalMemberVariable(propertyClientType.toString(), "items");

                            innerClass.Annotation("JsonCreator");
                            innerClass.PrivateConstructor(String.format("%1$s(@JacksonXmlProperty(localName = \"%2$s\") %3$s items)", xmlWrapperClassName, property.getXmlListElementName(), propertyClientType), constructor -> constructor.Line("this.items = items;"));
                        });
                    }

                    classBlock.BlockComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                    {
                        comment.Line(property.getDescription());
                    });

                    if (!tangible.StringHelper.isNullOrEmpty(property.getHeaderCollectionPrefix()))
                    {
                        classBlock.Annotation("HeaderCollection(\"" + property.getHeaderCollectionPrefix() + "\")");
                    }
                    else if (settings.getShouldGenerateXmlSerialization() && property.getIsXmlAttribute())
                    {
                        String localName = settings.getShouldGenerateXmlSerialization() ? property.getXmlName() : property.getSerializedName();
                        classBlock.Annotation(String.format("JacksonXmlProperty(localName = \"%1$s\", isAttribute = true)", localName));
                    }
                    else if (settings.getShouldGenerateXmlSerialization() && property.getWireType() instanceof ListType && !property.getIsXmlWrapper())
                    {
                        classBlock.Annotation(String.format("JsonProperty(\"%1$s\")", property.getXmlListElementName()));
                    }
                    else if (!tangible.StringHelper.isNullOrEmpty(property.getAnnotationArguments()))
                    {
                        classBlock.Annotation(String.format("JsonProperty(%1$s)", property.getAnnotationArguments()));
                    }

                    if (settings.getShouldGenerateXmlSerialization())
                    {
                        if (property.getIsXmlWrapper())
                        {
                            classBlock.PrivateMemberVariable(String.format("%1$s %2$s", xmlWrapperClassName, property.getName()));
                        }
                        else if (property.getWireType() instanceof ListType)
                        {
                            classBlock.PrivateMemberVariable(String.format("%1$s %2$s = new ArrayList<>()", property.getWireType(), property.getName()));
                        }
                        else
                        {
                            classBlock.PrivateMemberVariable(String.format("%1$s %2$s", property.getWireType(), property.getName()));
                        }
                    }
                    else
                    {
                        classBlock.PrivateMemberVariable(String.format("%1$s %2$s", property.getWireType(), property.getName()));
                    }
                }

                List<ClientModelProperty> constantProperties = model.getProperties().stream().filter(ClientModelProperty::getIsConstant).collect(Collectors.toList());
                if (!constantProperties.isEmpty())
                {
                    classBlock.JavadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                    {
                        comment.Description(String.format("Creates an instance of %1$s class.", model.getName()));
                    });
                    classBlock.PublicConstructor(String.format("%1$s()", model.getName()), (constructor) ->
                    {
                        for (ClientModelProperty constantProperty : constantProperties)
                        {
                            constructor.Line(String.format("%1$s = %2$s;", constantProperty.getName(), constantProperty.getDefaultValue()));
                        }
                    });
                }

                for (ClientModelProperty property : model.getProperties())
                {
                    IType propertyType = property.getWireType();
                    IType propertyClientType = propertyType.getClientType();

                    classBlock.JavadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                    {
                        comment.Description(String.format("Get the %1$s property: %2$s", property.getName(), property.getDescription()));
                        comment.Return(String.format("the %1$s value", property.getName()));
                    });
                    classBlock.PublicMethod(String.format("%1$s %2$s()", propertyClientType, property.getName()), (methodBlock) ->
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
                                methodBlock.If("this.{property.Name} == null", ifBlock ->
                                        ifBlock.Line("this.{property.Name} = new {propertyXmlWrapperClassName(property)}(new ArrayList<{listType.ElementType}>());"));
                                methodBlock.Return("this.{property.Name}.items");
                            }
                            else
                            {
                                methodBlock.Return("{expression}");
                            }
                        }
                        else
                        {
                            methodBlock.If("{expression} == null", (ifBlock) -> ifBlock.Return("null"));

                            String propertyConversion = propertyType.ConvertToClientType(expression);

                            methodBlock.Return(propertyConversion);
                        }
                    });

                    if (!property.getIsReadOnly())
                    {
                        classBlock.JavadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                                {
                                        comment.Description("Set the {property.Name} property: {property.Description}");
                        comment.Param(property.getName(), "the {property.Name} value to set");
                        comment.Return("the {model.Name} object itself.");
                        });
                        classBlock.PublicMethod("{model.Name} {property.Name.ToCamelCase()}({propertyClientType} {property.Name})", (methodBlock) -> {
                                String expression;
                                if (propertyClientType.equals(ArrayType.ByteArray)) {
                                    expression = "ImplUtils.clone({expression})";
                                } else {
                                    expression = property.getName();
                                }
                                if (propertyClientType != propertyType) {
                                    methodBlock.If("{property.Name} == null", (ifBlock) -> ifBlock.Line("this.{property.Name} = null;"))
                                        .Else((elseBlock) -> {
                                                String sourceTypeName = propertyClientType.toString();
                                                String targetTypeName = propertyType.toString();
                                                String propertyConversion = propertyType.ConvertFromClientType(expression);
                                                elseBlock.Line("this.{property.Name} = {propertyConversion};");
                                            });
                                } else {
                                    if (settings.getShouldGenerateXmlSerialization() && property.getIsXmlWrapper()) {
                                        methodBlock.Line("this.{property.Name} = new {propertyXmlWrapperClassName(property)}({expression});");
                                    } else {
                                        methodBlock.Line("this.{property.Name} = {expression};");
                                    }
                                }
                                methodBlock.Return("this");
                            });
                    }
                }
            });
    }
}