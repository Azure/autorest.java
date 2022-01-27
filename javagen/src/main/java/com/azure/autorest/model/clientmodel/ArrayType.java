// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.Set;
import java.util.function.Function;


/**
 * The details of an array type that is used by a client.
 */
public class ArrayType implements IType {
    public static final ArrayType ByteArray = new ArrayType(PrimitiveType.Byte, (String defaultValueExpression) -> defaultValueExpression == null ? "new byte[0]" : String.format("\"%1$s\".getBytes()", defaultValueExpression));
    private IType elementType;
    private Function<String, String> defaultValueExpressionConverter;

    private ArrayType(IType elementType, Function<String, String> defaultValueExpressionConverter) {
        this.elementType = elementType;
        this.defaultValueExpressionConverter = (String arg) -> defaultValueExpressionConverter.apply(arg);
    }

    public final IType getElementType() {
        return elementType;
    }

    private Function<String, String> getDefaultValueExpressionConverter() {
        return defaultValueExpressionConverter;
    }

    @Override
    public String toString() {
        return String.format("%1$s[]", getElementType());
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
}
