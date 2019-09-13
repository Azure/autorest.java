package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.javamodel.JavaFile;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 Writes a EnumType to a JavaFile.
*/
public class EnumTemplate implements IJavaTemplate<EnumType, JavaFile>
{
    private static EnumTemplate _instance = new EnumTemplate();
    public static EnumTemplate getInstance()
    {
        return _instance;
    }

    private EnumTemplate()
    {
    }

    public final void Write(EnumType enumType, JavaFile javaFile)
    {
        String enumTypeComment = String.format("Defines values for %1$s.", enumType.getName());
        if (enumType.getExpandable())
        {
            javaFile.Import("java.util.Collection", "com.fasterxml.jackson.annotation.JsonCreator", "com.azure.core.util.ExpandableStringEnum");
            javaFile.JavadocComment(comment ->
            {
                    comment.Description(enumTypeComment);
            });
            javaFile.PublicFinalClass(String.format("%1$s extends ExpandableStringEnum<%2$s>", enumType.getName(), enumType.getName()), (classBlock) ->
            {
                    for (ClientEnumValue enumValue : enumType.getValues())
                    {
                        classBlock.JavadocComment(String.format("Static value %1$s for %2$s.", enumValue.getValue(), enumType.getName()));
                        classBlock.PublicStaticFinalVariable(String.format("%1$s %2$s = fromString(\"%3$s\")", enumType.getName(), enumValue.getName(), enumValue.getValue()));
                    }

                    classBlock.JavadocComment((comment) ->
                    {
                        comment.Description(String.format("Creates or finds a %1$s from its string representation.", enumType.getName()));
                        comment.Param("name", "a name to look for");
                        comment.Return(String.format("the corresponding %1$s", enumType.getName()));
                    });
                    classBlock.Annotation("JsonCreator");
                    classBlock.PublicStaticMethod(String.format("%1$s fromString(String name)", enumType.getName()), (function) ->
                    {
                        function.Return(String.format("fromString(name, %1$s.class)", enumType.getName()));
                    });

                    classBlock.JavadocComment((comment) ->
                    {
                        comment.Return(String.format("known %1$s values", enumType.getName()));
                    });
                    classBlock.PublicStaticMethod(String.format("Collection<%1$s> values()", enumType.getName()), (function) ->
                    {
                        function.Return(String.format("values(%1$s.class)", enumType.getName()));
                    });
            });
        }
        else
        {
            javaFile.Import("com.fasterxml.jackson.annotation.JsonCreator", "com.fasterxml.jackson.annotation.JsonValue");
            javaFile.JavadocComment(comment ->
            {
                    comment.Description(enumTypeComment);
            });
            javaFile.PublicEnum(enumType.getName(), enumBlock ->
            {
                    for (ClientEnumValue value : enumType.getValues())
                    {
                        enumBlock.Value(value.getName(), value.getValue());
                    }

                    enumBlock.JavadocComment(String.format("The actual serialized value for a %1$s instance.", enumType.getName()));
                    enumBlock.PrivateFinalMemberVariable("String", "value");

                    enumBlock.Constructor(String.format("%1$s(String value)", enumType.getName()), (constructor) ->
                    {
                        constructor.Line("this.value = value;");
                    });

                    enumBlock.JavadocComment((comment) ->
                    {
                        comment.Description(String.format("Parses a serialized value to a %1$s instance.", enumType.getName()));
                        comment.Param("value", "the serialized value to parse.");
                        comment.Return(String.format("the parsed %1$s object, or null if unable to parse.", enumType.getName()));
                    });
                    enumBlock.Annotation("JsonCreator");
                    enumBlock.PublicStaticMethod(String.format("%1$s fromString(String value)", enumType.getName()), (function) ->
                    {
                        function.Line(String.format("%1$s[] items = %2$s.values();", enumType.getName(), enumType.getName()));
                        function.Block(String.format("for (%1$s item : items)", enumType.getName()), (foreachBlock) ->
                        {
                            foreachBlock.If("item.toString().equalsIgnoreCase(value)", (ifBlock) ->
                            {
                                ifBlock.Return("item");
                            });
                        });
                        function.Return("null");
                    });

                    enumBlock.Annotation("JsonValue", "Override");
                    enumBlock.PublicMethod("String toString()", (function) ->
                    {
                        function.Return("this.value");
                    });
            });
        }
    }
}