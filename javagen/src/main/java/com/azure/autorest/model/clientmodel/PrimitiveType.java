// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.time.Instant;
import java.util.Set;
import java.util.function.Function;

/**
 * A basic type used by a client.
 */
public class PrimitiveType implements IType {
    public static final PrimitiveType Void = new PrimitiveType("void", ClassType.Void);

    public static final PrimitiveType Boolean = new PrimitiveType("boolean", ClassType.Boolean, String::toLowerCase,
        "false", "writeBoolean", false, "getBoolean()", "getBooleanAttribute(%s, %s)", "getBooleanElement()");

    public static final PrimitiveType Byte = new PrimitiveType("byte", ClassType.Byte, Function.identity(), "0",
        "writeInt", false, "getInt()", "getIntAttribute(%s, %s)", "getIntElement()");

    public static final PrimitiveType Int = new PrimitiveType("int", ClassType.Integer, Function.identity(), "0",
        "writeInt", false, "getInt()", "getIntAttribute(%s, %s)", "getIntElement()");

    public static final PrimitiveType Long = new PrimitiveType("long", ClassType.Long,
        defaultValueExpression -> defaultValueExpression + 'L', "0", "writeLong", false, "getLong()",
        "getLongAttribute(%s, %s)", "getLongElement()");

    public static final PrimitiveType Float = new PrimitiveType("float", ClassType.Float,
        defaultValueExpression -> defaultValueExpression + "f", "0.0", "writeFloat", false, "getFloat()",
        "getFloatAttribute(%s, %s)", "getFloatElement()");

    public static final PrimitiveType Double = new PrimitiveType("double", ClassType.Double,
        defaultValueExpression -> java.lang.Double.toString(java.lang.Double.parseDouble(defaultValueExpression)),
        "0.0", "writeDouble", false, "getDouble()", "getDoubleAttribute(%s, %s)", "getDoubleElement()");

    public static final PrimitiveType Char = new PrimitiveType("char", ClassType.Character,
        defaultValueExpression -> Integer.toString(defaultValueExpression.charAt(0)), "\u0000", "writeString",
        true, "getString().charAt(0)", "getStringAttribute(%s, %s).charAt(0)", "getStringElement().charAt(0)");

    public static final PrimitiveType UnixTimeLong = new PrimitiveType("long", ClassType.UnixTimeLong, defaultValueExpression -> defaultValueExpression + 'L', null,
        "writeString", true, "getNullable(nonNullReader -> new UnixTime(nonNullReader.getLong()))",
        "getNullableAttribute(%s, %s, UnixTime::new)", "getNullableElement(UnixTime::new)");

    /**
     * The name of this type.
     */
    private final String name;
    /**
     * The nullable version of this primitive type.
     */
    private final ClassType nullableType;
    private final Function<String, String> defaultValueExpressionConverter;
    private final String defaultValue;
    private final String serializationMethodBase;
    private final boolean wrapSerializationWithObjectsToString;
    private final String jsonDeserializationMethod;
    private final String xmlAttributeDeserializationTemplate;
    private final String xmlElementDeserializationMethod;

    /**
     * Create a new PrimitiveType from the provided properties.
     * @param name The name of this type.
     */
    private PrimitiveType(String name, ClassType nullableType) {
        this(name, nullableType, null, null, null, false, null, null, null);
    }

    private PrimitiveType(String name, ClassType nullableType, Function<String, String> defaultValueExpressionConverter,
        String defaultValue, String serializationMethodBase, boolean wrapSerializationWithObjectsToString,
        String jsonDeserializationMethod, String xmlAttributeDeserializationTemplate,
        String xmlElementDeserializationMethod, String... importsToAdd) {
        this.name = name;
        this.nullableType = nullableType;
        this.defaultValueExpressionConverter = defaultValueExpressionConverter;
        this.defaultValue = defaultValue;
        this.serializationMethodBase = serializationMethodBase;
        this.wrapSerializationWithObjectsToString = wrapSerializationWithObjectsToString;
        this.jsonDeserializationMethod = jsonDeserializationMethod;
        this.xmlAttributeDeserializationTemplate = xmlAttributeDeserializationTemplate;
        this.xmlElementDeserializationMethod = xmlElementDeserializationMethod;
    }

    public static PrimitiveType fromNullableType(ClassType nullableType) {
        if (nullableType == ClassType.Void) {
            return PrimitiveType.Void;
        } else if (nullableType == ClassType.Boolean) {
            return PrimitiveType.Boolean;
        } else if (nullableType == ClassType.Byte) {
            return PrimitiveType.Byte;
        } else if (nullableType == ClassType.Integer) {
            return PrimitiveType.Int;
        } else if (nullableType == ClassType.Long) {
            return PrimitiveType.Long;
        } else if (nullableType == ClassType.Double) {
            return PrimitiveType.Double;
        } else if (nullableType == ClassType.Float) {
            return PrimitiveType.Float;
        } else {
            throw new IllegalArgumentException("Class type " + nullableType + " is not a boxed type");
        }
    }

    public final String getName() {
        return name;
    }

    private ClassType getNullableType() {
        return nullableType;
    }

    @Override
    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (this == PrimitiveType.UnixTimeLong) {
            imports.add(Instant.class.getName());
        }
    }

    @Override
    public final boolean isNullable() {
        return false;
    }

    @Override
    public final IType asNullable() {
        return getNullableType();
    }

    @Override
    public final boolean contains(IType type) {
        return this == type;
    }

    private java.util.function.Function<String, String> getDefaultValueExpressionConverter() {
        return defaultValueExpressionConverter;
    }

    @Override
    public final String defaultValueExpression(String sourceExpression) {
        String result = sourceExpression;
        if (result != null && getDefaultValueExpressionConverter() != null) {
            result = defaultValueExpressionConverter.apply(sourceExpression);
        }
        return result;
    }

    @Override
    public final String defaultValueExpression() {
        String result = defaultValue;
        if (result != null && getDefaultValueExpressionConverter() != null) {
            result = defaultValueExpressionConverter.apply(defaultValue);
        }
        return result;
    }

    @Override
    public final IType getClientType() {
        IType clientType = this;
        if (this == PrimitiveType.UnixTimeLong) {
            clientType = ClassType.UnixTimeDateTime;
        }
        return clientType;
    }

    @Override
    public final String convertToClientType(String expression) {
        if (getClientType() == this) {
            return expression;
        }

        if (this == PrimitiveType.UnixTimeLong) {
            expression = String.format("OffsetDateTime.from(Instant.ofEpochSecond(%1$s))", expression);
        }
        return expression;
    }

    @Override
    public final String convertFromClientType(String expression) {
        if (getClientType() == this) {
            return expression;
        }

        if (this == PrimitiveType.UnixTimeLong) {
            expression = String.format("%1$s.toEpochSecond()", expression);
        }
        return expression;
    }

    @Override
    public final String validate(String expression) {
        return null;
    }

    @Override
    public String jsonDeserializationMethod(String jsonReaderName) {
        if (jsonDeserializationMethod == null) {
            return null;
        }

        return jsonReaderName + "." + jsonDeserializationMethod;
    }

    @Override
    public java.lang.String jsonSerializationMethodCall(java.lang.String jsonWriterName, java.lang.String fieldName,
        java.lang.String valueGetter) {
        if (wrapSerializationWithObjectsToString) {
            return fieldName == null
                ? java.lang.String.format("%s.%s(Objects.toString(%s, null))", jsonWriterName,
                serializationMethodBase, valueGetter)
                : java.lang.String.format("%s.%sField(\"%s\", Objects.toString(%s, null))", jsonWriterName,
                serializationMethodBase, fieldName, valueGetter);
        }

        return fieldName == null
            ? java.lang.String.format("%s.%s(%s)", jsonWriterName, serializationMethodBase, valueGetter)
            : java.lang.String.format("%s.%sField(\"%s\", %s)", jsonWriterName, serializationMethodBase, fieldName,
            valueGetter);
    }

    @Override
    public String xmlDeserializationMethod(String attributeName, String attributeNamespace) {
        if (attributeName == null) {
            return xmlElementDeserializationMethod;
        } else {
            return (attributeNamespace == null)
                ? String.format(xmlAttributeDeserializationTemplate, "null", "\"" + attributeName + "\"")
                : String.format(xmlAttributeDeserializationTemplate, "\"" + attributeNamespace + "\"",
                    "\"" + attributeName + "\"");
        }
    }

    @Override
    public java.lang.String xmlSerializationMethodCall(java.lang.String xmlWriterName,
        java.lang.String attributeOrElementName, java.lang.String namespaceUri, java.lang.String valueGetter,
        boolean isAttribute, boolean nameIsVariable) {
        String value = wrapSerializationWithObjectsToString
            ? "Objects.toString(" + valueGetter + ", null)" : valueGetter;

        return ClassType.xmlSerializationCallHelper(xmlWriterName, serializationMethodBase, attributeOrElementName,
            namespaceUri, value, isAttribute, nameIsVariable);
    }

    @Override
    public String toString() {
        return getName();
    }
}
