// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    /// <summary>
    /// Writes a ClientModel to a JavaFile.
    /// </summary>
    public class ModelTemplate : IJavaTemplate<ClientModel, JavaFile>
    {
        private static ModelTemplate _instance = new ModelTemplate();
        public static ModelTemplate Instance => _instance;

        private ModelTemplate()
        {
        }

        public void Write(ClientModel model, JavaFile javaFile)
        {
            var settings = JavaSettings.Instance;
            ISet<string> imports = new HashSet<string>();
            model.AddImportsTo(imports, settings);

            javaFile.Import(imports);

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
            {
                comment.Description(model.Description);
            });

            bool hasDerivedModels = model.DerivedModels.Any();
            if (model.IsPolymorphic)
            {
                javaFile.Annotation($"JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = \"{model.PolymorphicDiscriminator}\"{(hasDerivedModels ? $", defaultImpl = {model.Name}.class" : "")})");
                javaFile.Annotation($"JsonTypeName(\"{model.SerializedName}\")");

                if (hasDerivedModels)
                {
                    javaFile.Line("@JsonSubTypes({");
                    javaFile.Indent(() =>
                    {
                        Func<ClientModel, string> getDerivedTypeAnnotation = (ClientModel derivedType)
                            => $"@JsonSubTypes.Type(name = \"{derivedType.SerializedName}\", value = {derivedType.Name}.class)";

                        foreach (ClientModel derivedModel in model.DerivedModels.SkipLast(1))
                        {
                            javaFile.Line(getDerivedTypeAnnotation(derivedModel) + ',');
                        }
                        javaFile.Line(getDerivedTypeAnnotation(model.DerivedModels.Last()));
                    });
                    javaFile.Line("})");
                }
            }

            if (settings.ShouldGenerateXmlSerialization)
            {
                javaFile.Annotation($"JacksonXmlRootElement(localName = \"{model.XmlName}\")");
            }

            if (model.NeedsFlatten)
            {
                javaFile.Annotation("JsonFlatten");
            }

            List<JavaModifier> classModifiers = new List<JavaModifier>();
            if (!hasDerivedModels && !model.NeedsFlatten)
            {
                classModifiers.Add(JavaModifier.Final);
            }

            string classNameWithBaseType = model.Name;
            if (model.ParentModel != null)
            {
                classNameWithBaseType += $" extends {model.ParentModel.Name}";
            }
            javaFile.Annotation("Fluent");
            javaFile.PublicClass(classModifiers, classNameWithBaseType, (classBlock) =>
            {
                string propertyXmlWrapperClassName(ClientModelProperty property) => property.XmlName + "Wrapper";

                foreach (ClientModelProperty property in model.Properties)
                {
                    string xmlWrapperClassName = propertyXmlWrapperClassName(property);
                    if (settings.ShouldGenerateXmlSerialization && property.IsXmlWrapper)
                    {
                        classBlock.PrivateStaticFinalClass(xmlWrapperClassName, innerClass =>
                        {
                            IType propertyClientType = property.WireType.ClientType;

                            innerClass.Annotation($"JacksonXmlProperty(localName = \"{property.XmlListElementName}\")");
                            innerClass.PrivateFinalMemberVariable(propertyClientType.ToString(), "items");

                            innerClass.Annotation("JsonCreator");
                            innerClass.PrivateConstructor(
                                $"{xmlWrapperClassName}(@JacksonXmlProperty(localName = \"{property.XmlListElementName}\") {propertyClientType} items)",
                                constructor => constructor.Line("this.items = items;"));
                        });
                    }

                    classBlock.BlockComment(settings.MaximumJavadocCommentWidth, (comment) =>
                    {
                        comment.Line(property.Description);
                    });

                    if (!string.IsNullOrEmpty(property.HeaderCollectionPrefix))
                    {
                        classBlock.Annotation("HeaderCollection(\"" + property.HeaderCollectionPrefix + "\")");
                    }
                    else if (settings.ShouldGenerateXmlSerialization && property.IsXmlAttribute)
                    {
                        string localName = settings.ShouldGenerateXmlSerialization ? property.XmlName : property.SerializedName;
                        classBlock.Annotation($"JacksonXmlProperty(localName = \"{localName}\", isAttribute = true)");
                    }
                    else if (settings.ShouldGenerateXmlSerialization && property.WireType is ListType && !property.IsXmlWrapper)
                    {
                        classBlock.Annotation($"JsonProperty(\"{property.XmlListElementName}\")");
                    }
                    else if (!string.IsNullOrEmpty(property.AnnotationArguments))
                    {
                        classBlock.Annotation($"JsonProperty({property.AnnotationArguments})");
                    }

                    if (settings.ShouldGenerateXmlSerialization)
                    {
                        if (property.IsXmlWrapper)
                        {
                            classBlock.PrivateMemberVariable($"{xmlWrapperClassName} {property.Name}");
                        }
                        else if (property.WireType is ListType listType)
                        {
                            classBlock.PrivateMemberVariable($"{property.WireType} {property.Name} = new ArrayList<>()");
                        }
                        else
                        {
                            classBlock.PrivateMemberVariable($"{property.WireType} {property.Name}");
                        }
                    }
                    else
                    {
                        classBlock.PrivateMemberVariable($"{property.WireType} {property.Name}");
                    }
                }

                IEnumerable<ClientModelProperty> constantProperties = model.Properties.Where(property => property.IsConstant);
                if (constantProperties.Any())
                {
                    classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                    {
                        comment.Description($"Creates an instance of {model.Name} class.");
                    });
                    classBlock.PublicConstructor($"{model.Name}()", (constructor) =>
                    {
                        foreach (ClientModelProperty constantProperty in constantProperties)
                        {
                            constructor.Line($"{constantProperty.Name} = {constantProperty.DefaultValue};");
                        }
                    });
                }

                foreach (ClientModelProperty property in model.Properties)
                {
                    IType propertyType = property.WireType;
                    IType propertyClientType = propertyType.ClientType;

                    classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                    {
                        comment.Description($"Get the {property.Name} property: {property.Description}");
                        comment.Return($"the {property.Name} value");
                    });
                    classBlock.PublicMethod($"{propertyClientType} {property.GetterName}()", (methodBlock) =>
                    {
                        string sourceTypeName = propertyType.ToString();
                        string targetTypeName = propertyClientType.ToString();
                        string expression = $"this.{property.Name}";
                        if (propertyClientType.Equals(ArrayType.ByteArray))
                        {
                            expression = $"ImplUtils.clone({expression})";
                        }
                        if (sourceTypeName == targetTypeName)
                        {
                            if (settings.ShouldGenerateXmlSerialization && property.IsXmlWrapper && property.WireType is ListType listType)
                            {
                                methodBlock.If($"this.{property.Name} == null", ifBlock =>
                                {
                                    ifBlock.Line($"this.{property.Name} = new {propertyXmlWrapperClassName(property)}(new ArrayList<{listType.ElementType}>());");
                                });
                                methodBlock.Return($"this.{property.Name}.items");
                            }
                            else
                            {
                                methodBlock.Return($"{expression}");
                            }
                        }
                        else
                        {
                            methodBlock.If($"{expression} == null", (ifBlock) =>
                            {
                                ifBlock.Return("null");
                            });

                            string propertyConversion = propertyType.ConvertToClientType(expression);

                            methodBlock.Return(propertyConversion);
                        }
                    });

                    if (!property.IsReadOnly)
                    {
                        classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                        {
                            comment.Description($"Set the {property.Name} property: {property.Description}");
                            comment.Param(property.Name, $"the {property.Name} value to set");
                            comment.Return($"the {model.Name} object itself.");
                        });
                        classBlock.PublicMethod($"{model.Name} {property.SetterName}({propertyClientType} {property.Name})", (methodBlock) =>
                        {
                            string expression = property.Name;
                            if (propertyClientType.Equals(ArrayType.ByteArray))
                            {
                                expression = $"ImplUtils.clone({expression})";
                            }
                            if (propertyClientType != propertyType)
                            {
                                methodBlock.If($"{property.Name} == null", (ifBlock) =>
                                {
                                    ifBlock.Line($"this.{property.Name} = null;");
                                })
                                .Else((elseBlock) =>
                                {
                                    string sourceTypeName = propertyClientType.ToString();
                                    string targetTypeName = propertyType.ToString();
                                    string propertyConversion = propertyType.ConvertFromClientType(expression);
                                    elseBlock.Line($"this.{property.Name} = {propertyConversion};");
                                });
                            }
                            else
                            {
                                if (settings.ShouldGenerateXmlSerialization && property.IsXmlWrapper)
                                {
                                    methodBlock.Line($"this.{property.Name} = new {propertyXmlWrapperClassName(property)}({expression});");
                                }
                                else
                                {
                                    methodBlock.Line($"this.{property.Name} = {expression};");
                                }
                            }
                            methodBlock.Return("this");
                        });
                    }
                }
            });
        }
    }
}