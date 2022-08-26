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
        "false", "writeBoolean", false, "getBoolean()");

    public static final PrimitiveType Byte = new PrimitiveType("byte", ClassType.Byte, Function.identity(), "0",
        "writeInt", false, "getInt()");

    public static final PrimitiveType Int = new PrimitiveType("int", ClassType.Integer, Function.identity(), "0",
        "writeInt", false, "getInt()");

    public static final PrimitiveType Long = new PrimitiveType("long", ClassType.Long,
        defaultValueExpression -> defaultValueExpression + 'L', "0", "writeLong", false, "getLong()");

    public static final PrimitiveType Float = new PrimitiveType("float", ClassType.Float,
        defaultValueExpression -> defaultValueExpression + "f", "0.0", "writeFloat", false, "getFloat()");

    public static final PrimitiveType Double = new PrimitiveType("double", ClassType.Double,
        defaultValueExpression -> java.lang.Double.toString(java.lang.Double.parseDouble(defaultValueExpression)),
        "0.0", "writeDouble", false, "getDouble()");

    public static final PrimitiveType Char = new PrimitiveType("char", ClassType.Character,
        defaultValueExpression -> Integer.toString(defaultValueExpression.charAt(0)), "\u0000", "writeString",
        true, "getString().charAt(0)");

    public static final PrimitiveType UnixTimeLong = new PrimitiveType("long", ClassType.UnixTimeLong, null, null,
        "writeString", true, "getNullable(nonNullReader -> new UnixTime(nonNullReader.getLong()))");

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

    /**
     * Create a new PrimitiveType from the provided properties.
     * @param name The name of this type.
     */
    private PrimitiveType(String name, ClassType nullableType) {
        this(name, nullableType, null, null, null, false, null);
    }

    private PrimitiveType(String name, ClassType nullableType, Function<String, String> defaultValueExpressionConverter,
        String defaultValue, String serializationMethodBase, boolean wrapSerializationWithObjectsToString,
        String jsonDeserializationMethod, String... importsToAdd) {
        this.name = name;
        this.nullableType = nullableType;
        this.defaultValueExpressionConverter = defaultValueExpressionConverter;
        this.defaultValue = defaultValue;
        this.serializationMethodBase = serializationMethodBase;
        this.wrapSerializationWithObjectsToString = wrapSerializationWithObjectsToString;
        this.jsonDeserializationMethod = jsonDeserializationMethod;
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
    public String jsonDeserializationMethod() {
        return jsonDeserializationMethod;
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
    public java.lang.String xmlSerializationMethodCall(java.lang.String xmlWriterName,
        java.lang.String attributeOrElementName, java.lang.String namespaceUri, java.lang.String valueGetter,
        boolean isAttribute) {
        String value = wrapSerializationWithObjectsToString
            ? "Objects.toString(" + valueGetter + ", null)" : valueGetter;
        if (isAttribute) {
            return namespaceUri == null
                ? java.lang.String.format("%s.%sAttribute(\"%s\", %s)", xmlWriterName, serializationMethodBase,
                    attributeOrElementName, value)
                : java.lang.String.format("%s.%sAttribute(\"%s\", \"%s\", %s)", xmlWriterName, serializationMethodBase,
                    namespaceUri, attributeOrElementName, value);
        } else {
            if (attributeOrElementName == null) {
                return java.lang.String.format("%s.%s(%s)", xmlWriterName, serializationMethodBase, value);
            } else {
                return namespaceUri == null
                    ? java.lang.String.format("%s.%sElement(\"%s\", %s)", xmlWriterName, serializationMethodBase,
                        attributeOrElementName, value)
                    : java.lang.String.format("%s.%sElement(\"%s\", \"%s\", %s)", xmlWriterName,
                        serializationMethodBase, namespaceUri, attributeOrElementName, value);
            }
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
