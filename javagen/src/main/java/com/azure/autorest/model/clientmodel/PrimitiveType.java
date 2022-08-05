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
        "false", "writeBooleanField", "writeBoolean", "writeBooleanAttribute", "writeBooleanElement");

    public static final PrimitiveType Byte = new PrimitiveType("byte", ClassType.Byte, Function.identity(), "0",
        "writeIntField", "writeInt", "writeIntAttribute", "writeIntElement");

    public static final PrimitiveType Int = new PrimitiveType("int", ClassType.Integer, Function.identity(), "0",
        "writeIntField", "writeInt", "writeIntAttribute", "writeIntElement");

    public static final PrimitiveType Long = new PrimitiveType("long", ClassType.Long,
        defaultValueExpression -> defaultValueExpression + 'L', "0", "writeLongField", "writeLong",
        "writeLongAttribute", "writeLongElement");

    public static final PrimitiveType Float = new PrimitiveType("float", ClassType.Float,
        defaultValueExpression -> defaultValueExpression + "f", "0.0", "writeFloatField", "writeFloat",
        "writeFloatAttribute", "writeFloatElement");

    public static final PrimitiveType Double = new PrimitiveType("double", ClassType.Double,
        defaultValueExpression -> java.lang.Double.toString(java.lang.Double.parseDouble(defaultValueExpression)),
        "0.0", "writeDoubleField", "writeDouble", "writeDoubleAttribute", "writeDoubleElement");

    public static final PrimitiveType Char = new PrimitiveType("char", ClassType.Character,
        defaultValueExpression -> java.lang.Integer.toString(defaultValueExpression.charAt(0)), "\u0000",
        "writeStringField", "writeString", "writeStringAttribute", "writeStringElement");

    public static final PrimitiveType UnixTimeLong = new PrimitiveType("long", ClassType.UnixTimeLong, null, null,
        "writeLongField", "writeLong", "writeLongAttribute", "writeLongElement");

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
    private final String jsonFieldSerializationMethod;
    private final String jsonValueSerializationMethod;
    private final String xmlAttributeSerializationMethod;
    private final String xmlElementSerializationMethod;

    /**
     * Create a new PrimitiveType from the provided properties.
     * @param name The name of this type.
     */
    private PrimitiveType(String name, ClassType nullableType) {
        this(name, nullableType, null, null, null, null, null, null);
    }

    private PrimitiveType(String name, ClassType nullableType, Function<String, String> defaultValueExpressionConverter,
        String defaultValue, String jsonFieldSerializationMethod, String jsonValueSerializationMethod,
        String xmlAttributeSerializationMethod, String xmlElementSerializationMethod,
        String... importsToAdd) {
        this.name = name;
        this.nullableType = nullableType;
        this.defaultValueExpressionConverter = defaultValueExpressionConverter;
        this.defaultValue = defaultValue;
        this.jsonFieldSerializationMethod = jsonFieldSerializationMethod;
        this.jsonValueSerializationMethod = jsonValueSerializationMethod;
        this.xmlAttributeSerializationMethod = xmlAttributeSerializationMethod;
        this.xmlElementSerializationMethod = xmlElementSerializationMethod;
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
    public final boolean deserializationNeedsNullGuarding() {
        return false;
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
    public String streamStyleJsonFieldSerializationMethod() {
        return jsonFieldSerializationMethod;
    }

    @Override
    public String streamStyleJsonValueSerializationMethod() {
        return jsonValueSerializationMethod;
    }

    @Override
    public String streamStyleXmlAttributeSerializationMethod() {
        return xmlAttributeSerializationMethod;
    }

    @Override
    public String streamStyleXmlElementSerializationMethod() {
        return xmlElementSerializationMethod;
    }

    @Override
    public String toString() {
        return getName();
    }
}
