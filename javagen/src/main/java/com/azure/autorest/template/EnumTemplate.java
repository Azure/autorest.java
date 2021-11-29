package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.CodeNamer;

import java.util.HashSet;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * Writes a EnumType to a JavaFile.
 */
public class EnumTemplate implements IJavaTemplate<EnumType, JavaFile> {
    private static final EnumTemplate INSTANCE = new EnumTemplate();

    protected EnumTemplate() {
    }

    /**
     * @return The global instance of {@link EnumTemplate}.
     */
    public static EnumTemplate getInstance() {
        return INSTANCE;
    }

    /**
     * Writes a declared enum to a specific Java file.
     *
     * @param enumType The enum.
     * @param javaFile The Java file.
     */
    public final void write(EnumType enumType, JavaFile javaFile) {
        String enumTypeComment = String.format("Defines values for %1$s.", enumType.getName());
        if (enumType.getExpandable()) {
            javaFile.declareImport("java.util.Collection", "com.fasterxml.jackson.annotation.JsonCreator", getStringEnumImport());
            javaFile.javadocComment(comment -> comment.description(enumTypeComment));
            javaFile.publicFinalClass(String.format("%1$s extends ExpandableStringEnum<%2$s>", enumType.getName(), enumType.getName()), (classBlock) ->
            {
                String typeName = enumType.getElementType().getClientType().toString();
                for (ClientEnumValue enumValue : enumType.getValues()) {
                    classBlock.javadocComment(String.format("Static value %1$s for %2$s.", enumValue.getValue(), enumType.getName()));
                    classBlock.publicStaticFinalVariable(String.format("%1$s %2$s = from%3$s(%4$s)", enumType.getName(), enumValue.getName(),
                        CodeNamer.toPascalCase(typeName), enumType.getElementType().defaultValueExpression(enumValue.getValue())));
                }

                classBlock.javadocComment((comment) ->
                {
                    comment.description(String.format("Creates or finds a %1$s from its string representation.", enumType.getName()));
                    comment.param("name", "a name to look for");
                    comment.methodReturns(String.format("the corresponding %1$s", enumType.getName()));
                });
                classBlock.annotation("JsonCreator");
                classBlock.publicStaticMethod(String.format("%1$s from%2$s(%3$s name)", enumType.getName(), CodeNamer.toPascalCase(typeName), typeName), (function) ->
                {
                    String stringValue = ClassType.String.equals(enumType.getElementType()) ? "name" : "String.valueOf(name)";
                    function.methodReturn(String.format("fromString(%1$s, %2$s.class)", stringValue, enumType.getName()));
                });

                classBlock.javadocComment((comment) ->
                    comment.methodReturns(String.format("known %1$s values", enumType.getName())));
                classBlock.publicStaticMethod(String.format("Collection<%1$s> values()", enumType.getName()), (function) ->
                    function.methodReturn(String.format("values(%1$s.class)", enumType.getName())));
            });
        } else {
            Set<String> imports = new HashSet<>();
            imports.add("com.fasterxml.jackson.annotation.JsonCreator");
            imports.add("com.fasterxml.jackson.annotation.JsonValue");
            enumType.getElementType().getClientType().addImportsTo(imports, false);

            javaFile.declareImport(imports);
            javaFile.javadocComment(comment -> comment.description(enumTypeComment));
            javaFile.publicEnum(enumType.getName(), enumBlock ->
            {
                for (ClientEnumValue value : enumType.getValues()) {
                    enumBlock.value(value.getName(), value.getValue(), enumType.getElementType());
                }

                String typeName = enumType.getElementType().getClientType().toString();
                String converterName = enumType.getFromJsonMethodName();

                enumBlock.javadocComment(String.format("The actual serialized value for a %1$s instance.", enumType.getName()));
                enumBlock.privateFinalMemberVariable(typeName, "value");

                enumBlock.constructor(String.format("%1$s(%2$s value)", enumType.getName(), typeName), (constructor) ->
                    constructor.line("this.value = value;"));

                enumBlock.javadocComment((comment) ->
                {
                    comment.description(String.format("Parses a serialized value to a %1$s instance.", enumType.getName()));
                    comment.param("value", "the serialized value to parse.");
                    comment.methodReturns(String.format("the parsed %1$s object, or null if unable to parse.", enumType.getName()));
                });
                enumBlock.annotation("JsonCreator");
                enumBlock.PublicStaticMethod(String.format("%1$s %2$s(%3$s value)", enumType.getName(), converterName, typeName), (function) ->
                {
                    function.line(String.format("%1$s[] items = %2$s.values();", enumType.getName(), enumType.getName()));
                    function.block(String.format("for (%1$s item : items)", enumType.getName()), (foreachBlock) ->
                        foreachBlock.ifBlock(createEnumJsonCreatorIfCheck(enumType),
                            ifBlock -> ifBlock.methodReturn("item")));
                    function.methodReturn("null");
                });

                if (enumType.getElementType() == ClassType.String) {
                    enumBlock.annotation("JsonValue", "Override");
                } else {
                    enumBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("De-serializes the instance to %1$s value.", enumType.getElementType()));
                        comment.methodReturns(String.format("the %1$s value", enumType.getElementType()));
                    });
                    enumBlock.annotation("JsonValue");
                }
                enumBlock.PublicMethod(String.format("%1$s %2$s()", typeName, enumType.getToJsonMethodName()), (function) ->
                    function.methodReturn("this.value"));
            });
        }
    }

    /**
     * Gets required import needed to support an expandable string enum.
     * <p>
     * By default, this is {@code com.azure.core.util.ExpandableStringEnum}.
     *
     * @return The required expandable string enum import.
     */
    protected String getStringEnumImport() {
        return "com.azure.core.util.ExpandableStringEnum";
    }

    // TODO: Should this be made into a method on EnumType?
    /**
     * Creates the if check used by the JsonCreator method used in the Enum type.
     *
     * @param enumType The enum type.
     * @return The JsonCreator if check.
     */
    protected String createEnumJsonCreatorIfCheck(EnumType enumType) {
        IType enumElementType = enumType.getElementType();
        String toJsonMethodName = enumType.getToJsonMethodName();
        if (enumElementType == PrimitiveType.Float) {
            return String.format("Float.floatToIntBits(item.%1$s()) == Float.floatToIntBits(value)", toJsonMethodName);
        } else if (enumElementType == PrimitiveType.Double) {
            return String.format("Double.doubleToLongBits(item.%1$s()) == Double.doubleToLongBits(value)", toJsonMethodName);
        } else if (enumElementType instanceof PrimitiveType) {
            return String.format("item.%1$s() == value", toJsonMethodName);
        } else if (enumElementType == ClassType.String) {
            return String.format("item.%1$s().equalsIgnoreCase(value)", toJsonMethodName);
        } else {
            return String.format("item.%1$s().equals(value)", toJsonMethodName);
        }
    }
}
