// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Extensions;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    /// <summary>
    /// Writes a EnumType to a JavaFile.
    /// </summary>
    public class EnumTemplate : IJavaTemplate<EnumType, JavaFile>
    {
        private static EnumTemplate _instance = new EnumTemplate();
        public static EnumTemplate Instance => _instance;

        private EnumTemplate()
        {
        }

        public void Write(EnumType enumType, JavaFile javaFile)
        {
            string enumTypeComment = $"Defines values for {enumType.Name}.";
            if (enumType.Expandable)
            {
                javaFile.Import("java.util.Collection",
                                "com.fasterxml.jackson.annotation.JsonCreator",
                                "com.azure.core.util.ExpandableStringEnum");
                javaFile.JavadocComment(comment =>
                {
                    comment.Description(enumTypeComment);
                });
                javaFile.PublicFinalClass($"{enumType.Name} extends ExpandableStringEnum<{enumType.Name}>", (classBlock) =>
                {
                    foreach (ClientEnumValue enumValue in enumType.Values)
                    {
                        classBlock.JavadocComment($"Static value {enumValue.Value} for {enumType.Name}.");
                        classBlock.PublicStaticFinalVariable($"{enumType.Name} {enumValue.Name} = fromString(\"{enumValue.Value}\")");
                    }

                    classBlock.JavadocComment((comment) =>
                    {
                        comment.Description($"Creates or finds a {enumType.Name} from its string representation.");
                        comment.Param("name", "a name to look for");
                        comment.Return($"the corresponding {enumType.Name}");
                    });
                    classBlock.Annotation("JsonCreator");
                    classBlock.PublicStaticMethod($"{enumType.Name} fromString(String name)", (function) =>
                    {
                        function.Return($"fromString(name, {enumType.Name}.class)");
                    });

                    classBlock.JavadocComment((comment) =>
                    {
                        comment.Return($"known {enumType.Name} values");
                    });
                    classBlock.PublicStaticMethod($"Collection<{enumType.Name}> values()", (function) =>
                    {
                        function.Return($"values({enumType.Name}.class)");
                    });
                });
            }
            else
            {
                javaFile.Import("com.fasterxml.jackson.annotation.JsonCreator",
                                "com.fasterxml.jackson.annotation.JsonValue");
                javaFile.JavadocComment(comment =>
                {
                    comment.Description(enumTypeComment);
                });
                javaFile.PublicEnum(enumType.Name, enumBlock =>
                {
                    foreach (ClientEnumValue value in enumType.Values)
                    {
                        enumBlock.Value(value.Name, value.Value);
                    }

                    enumBlock.JavadocComment($"The actual serialized value for a {enumType.Name} instance.");
                    enumBlock.PrivateFinalMemberVariable("String", "value");

                    enumBlock.PrivateConstructor($"{enumType.Name}(String value)", (constructor) =>
                    {
                        constructor.Line("this.value = value;");
                    });

                    enumBlock.JavadocComment((comment) =>
                    {
                        comment.Description($"Parses a serialized value to a {enumType.Name} instance.");
                        comment.Param("value", "the serialized value to parse.");
                        comment.Return($"the parsed {enumType.Name} object, or null if unable to parse.");
                    });
                    enumBlock.Annotation("JsonCreator");
                    enumBlock.PublicStaticMethod($"{enumType.Name} fromString(String value)", (function) =>
                    {
                        function.Line($"{enumType.Name}[] items = {enumType.Name}.values();");
                        function.Block($"for ({enumType.Name} item : items)", (foreachBlock) =>
                        {
                            foreachBlock.If("item.toString().equalsIgnoreCase(value)", (ifBlock) =>
                            {
                                ifBlock.Return("item");
                            });
                        });
                        function.Return("null");
                    });

                    enumBlock.Annotation("JsonValue",
                                         "Override");
                    enumBlock.PublicMethod("String toString()", (function) =>
                    {
                        function.Return("this.value");
                    });
                });
            }
        }
    }
}