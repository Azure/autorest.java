// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;

import java.util.Set;
import java.util.function.Function;


/** 
 The details of an array type that is used by a client.
*/
public class ArrayType implements IType
{
    public static final ArrayType ByteArray = new ArrayType(PrimitiveType.Byte, (String defaultValueExpression) -> defaultValueExpression == null ? "new byte[0]" : String.format("\"%1$s\".getBytes()", defaultValueExpression));

    private ArrayType(IType elementType, Function<String,String> defaultValueExpressionConverter)
    {
        ElementType = elementType;
        DefaultValueExpressionConverter = (String arg) -> defaultValueExpressionConverter.apply(arg);
    }

    private IType ElementType;
    public final IType getElementType()
    {
        return ElementType;
    }

    private Function<String, String> DefaultValueExpressionConverter;
    private Function<String, String> getDefaultValueExpressionConverter()
    {
        return DefaultValueExpressionConverter;
    }

    @Override
    public String toString()
    {
        return String.format("%1$s[]", getElementType());
    }

    public final IType asNullable()
    {
        return this;
    }

    public final boolean contains(IType type)
    {
        return this == type || getElementType().contains(type);
    }

    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports)
    {
        getElementType().addImportsTo(imports, includeImplementationImports);
    }

    public final String defaultValueExpression(String sourceExpression)
    {
        return DefaultValueExpressionConverter.apply(sourceExpression);
    }

    public final IType getClientType()
    {
            // The only supported array type is byte[]
        return this;
    }

    public final String convertToClientType(String expression)
    {
        // The only supported array type is byte[]
        return expression;
    }

    public final String convertFromClientType(String expression)
    {
        // The only supported array type is byte[]
        return expression;
    }
}