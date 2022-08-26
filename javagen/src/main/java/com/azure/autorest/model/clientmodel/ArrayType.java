// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.Set;
import java.util.function.Function;


/**
 * The details of an array type that is used by a client.
 */
public class ArrayType implements IType {
    public static final ArrayType ByteArray = new ArrayType(PrimitiveType.Byte,
        defaultValueExpression -> defaultValueExpression == null
            ? "new byte[0]" // TODO (alzimmer): Should this be new byte[0] or null?
            : String.format("\"%1$s\".getBytes()", defaultValueExpression));

    private final String toStringValue;
    private final IType elementType;
    private final Function<String, String> defaultValueExpressionConverter;

    private ArrayType(IType elementType, Function<String, String> defaultValueExpressionConverter) {
        this.toStringValue = elementType + "[]";
        this.elementType = elementType;
        this.defaultValueExpressionConverter = defaultValueExpressionConverter;
    }

    public final IType getElementType() {
        return elementType;
    }

    @Override
    public String toString() {
        return toStringValue;
    }

    public final IType asNullable() {
        return this;
    }

    public final boolean contains(IType type) {
        return this == type || getElementType().contains(type);
    }

    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        getElementType().addImportsTo(imports, includeImplementationImports);
    }

    public final String defaultValueExpression(String sourceExpression) {
        return defaultValueExpressionConverter.apply(sourceExpression);
    }

    @Override
    public String defaultValueExpression() {
        return defaultValueExpression(null);
    }

    public final IType getClientType() {
        // The only supported array type is byte[]
        return this;
    }

    public final String convertToClientType(String expression) {
        // The only supported array type is byte[]
        return expression;
    }

    public final String convertFromClientType(String expression) {
        // The only supported array type is byte[]
        return expression;
    }

    public String validate(String expression) {
        return null;
    }

    @Override
    public String jsonDeserializationMethod() {
        return "getBinary()";
    }

    @Override
    public String jsonFieldSerializationMethod(String jsonWriterName, String fieldName, String valueGetter) {
        return String.format("%s.writeBinaryField(\"%s\", %s)", jsonWriterName, fieldName, valueGetter);
    }

    @Override
    public String jsonValueSerializationMethod(String jsonWriterName, String valueGetter) {
        return String.format("%s.writeBinary(%s)", jsonWriterName, valueGetter);
    }

    @Override
    public String xmlAttributeSerializationMethod() {
        return "writeBinaryAttribute";
    }

    @Override
    public String xmlElementSerializationMethod() {
        return "writeBinaryElement";
    }
}
