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

    public final void write(EnumType enumType, JavaFile javaFile)
    {
        String enumTypeComment = String.format("Defines values for %1$s.", enumType.getName());
        if (enumType.getExpandable())
        {
            javaFile.declareImport("java.util.Collection", "com.fasterxml.jackson.annotation.JsonCreator", "com.azure.core.util.ExpandableStringEnum");
            javaFile.javadocComment(comment ->
            {
                    comment.description(enumTypeComment);
            });
            javaFile.publicFinalClass(String.format("%1$s extends ExpandableStringEnum<%2$s>", enumType.getName(), enumType.getName()), (classBlock) ->
            {
                    for (ClientEnumValue enumValue : enumType.getValues())
                    {
                        classBlock.javadocComment(String.format("Static value %1$s for %2$s.", enumValue.getValue(), enumType.getName()));
                        classBlock.publicStaticFinalVariable(String.format("%1$s %2$s = fromString(\"%3$s\")", enumType.getName(), enumValue.getName(), enumValue.getValue()));
                    }

                    classBlock.javadocComment((comment) ->
                    {
                        comment.description(String.format("Creates or finds a %1$s from its string representation.", enumType.getName()));
                        comment.param("name", "a name to look for");
                        comment.methodReturns(String.format("the corresponding %1$s", enumType.getName()));
                    });
                    classBlock.annotation("JsonCreator");
                    classBlock.publicStaticMethod(String.format("%1$s fromString(String name)", enumType.getName()), (function) ->
                    {
                        function.methodReturn(String.format("fromString(name, %1$s.class)", enumType.getName()));
                    });

                    classBlock.javadocComment((comment) ->
                    {
                        comment.methodReturns(String.format("known %1$s values", enumType.getName()));
                    });
                    classBlock.publicStaticMethod(String.format("Collection<%1$s> values()", enumType.getName()), (function) ->
                    {
                        function.methodReturn(String.format("values(%1$s.class)", enumType.getName()));
                    });
            });
        }
        else
        {
            javaFile.declareImport("com.fasterxml.jackson.annotation.JsonCreator", "com.fasterxml.jackson.annotation.JsonValue");
            javaFile.javadocComment(comment ->
            {
                    comment.description(enumTypeComment);
            });
            javaFile.publicEnum(enumType.getName(), enumBlock ->
            {
                    for (ClientEnumValue value : enumType.getValues())
                    {
                        enumBlock.value(value.getName(), value.getValue());
                    }

                    enumBlock.javadocComment(String.format("The actual serialized value for a %1$s instance.", enumType.getName()));
                    enumBlock.privateFinalMemberVariable("String", "value");

                    enumBlock.constructor(String.format("%1$s(String value)", enumType.getName()), (constructor) ->
                    {
                        constructor.line("this.value = value;");
                    });

                    enumBlock.javadocComment((comment) ->
                    {
                        comment.description(String.format("Parses a serialized value to a %1$s instance.", enumType.getName()));
                        comment.param("value", "the serialized value to parse.");
                        comment.methodReturns(String.format("the parsed %1$s object, or null if unable to parse.", enumType.getName()));
                    });
                    enumBlock.annotation("JsonCreator");
                    enumBlock.PublicStaticMethod(String.format("%1$s fromString(String value)", enumType.getName()), (function) ->
                    {
                        function.line(String.format("%1$s[] items = %2$s.values();", enumType.getName(), enumType.getName()));
                        function.block(String.format("for (%1$s item : items)", enumType.getName()), (foreachBlock) ->
                        {
                            foreachBlock.ifBlock("item.toString().equalsIgnoreCase(value)", (ifBlock) ->
                            {
                                ifBlock.methodReturn("item");
                            });
                        });
                        function.methodReturn("null");
                    });

                    enumBlock.annotation("JsonValue", "Override");
                    enumBlock.PublicMethod("String toString()", (function) ->
                    {
                        function.methodReturn("this.value");
                    });
            });
        }
    }
}