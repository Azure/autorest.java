package com.azure.autorest.model.clientmodel;

import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A basic type used by a client.
 */
public class PrimitiveType implements IType {
    public static final PrimitiveType Void = new PrimitiveType("void", ClassType.Void);
    public static final PrimitiveType Boolean = new PrimitiveType("boolean", ClassType.Boolean, String::toLowerCase);
    public static final PrimitiveType Byte = new PrimitiveType("byte", ClassType.Byte);
    public static final PrimitiveType Int = new PrimitiveType("int", ClassType.Integer);
    public static final PrimitiveType Long = new PrimitiveType("long", ClassType.Long, (String defaultValueExpression) -> defaultValueExpression + 'L');
    public static final PrimitiveType Float = new PrimitiveType("float", ClassType.Float, (String defaultValueExpression) -> defaultValueExpression + "f");
    public static final PrimitiveType Double = new PrimitiveType("double", ClassType.Double, (String defaultValueExpression) -> java.lang.Double.toString(java.lang.Double.parseDouble(defaultValueExpression)));
    public static final PrimitiveType Char = new PrimitiveType("char", ClassType.Character, (String defaultValueExpression) -> java.lang.Double.toString(defaultValueExpression.charAt(0)));

    public static final PrimitiveType UnixTimeLong = new PrimitiveType("long", ClassType.UnixTimeLong);
    /**
     * The name of this type.
     */
    private String name;
    /**
     * The nullable version of this primitive type.
     */
    private ClassType nullableType;
    private java.util.function.Function<String, String> defaultValueExpressionConverter;

    /**
     * Create a new PrimitiveType from the provided properties.
     * @param name The name of this type.
     */
    private PrimitiveType(String name, ClassType nullableType) {
        this(name, nullableType, null);
    }

    private PrimitiveType(String name, ClassType nullableType, java.util.function.Function<String, String> defaultValueExpressionConverter) {
        this.name = name;
        this.nullableType = nullableType;
        this.defaultValueExpressionConverter = (String arg) -> defaultValueExpressionConverter.apply(arg);
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

    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
    }

    public final IType asNullable() {
        return getNullableType();
    }

    public final boolean contains(IType type) {
        return this == type;
    }

    private java.util.function.Function<String, String> getDefaultValueExpressionConverter() {
        return defaultValueExpressionConverter;
    }

    public final String defaultValueExpression(String sourceExpression) {
        String result = sourceExpression;
        if (result != null && getDefaultValueExpressionConverter() != null) {
            result = defaultValueExpressionConverter.apply(sourceExpression);
        }
        return result;
    }

    public final IType getClientType() {
        IType clientType = this;
        if (this == PrimitiveType.UnixTimeLong) {
            clientType = ClassType.UnixTimeDateTime;
        }
        return clientType;
    }

    public final String convertToClientType(String expression) {
        if (getClientType() == this) {
            return expression;
        }

        if (this == PrimitiveType.UnixTimeLong) {
            expression = String.format("OffsetDateTime.from(Instant.ofEpochSecond(%1$s));", expression);
        }
        return expression;
    }

    public final String convertFromClientType(String expression) {
        if (getClientType() == this) {
            return expression;
        }

        if (this == PrimitiveType.UnixTimeLong) {
            expression = String.format("%1$s.toEpochSecond()", expression);
        }
        return expression;
    }

    public final String validate(String expression) {
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }
}