package com.azure.autorest.model.clientmodel;

import java.util.*;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A basic type used by a client.
*/
public class PrimitiveType implements IType
{
    public static final PrimitiveType Void = new PrimitiveType("void", ClassType.Void);
    public static final PrimitiveType Boolean = new PrimitiveType("boolean", ClassType.Boolean, (String defaultValueExpression) -> defaultValueExpression.ToLowerInvariant());
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public static readonly PrimitiveType Byte = new PrimitiveType("byte", ClassType.Byte);
    public static final PrimitiveType Byte = new PrimitiveType("byte", ClassType.Byte);
    public static final PrimitiveType Int = new PrimitiveType("int", ClassType.Integer);
    public static final PrimitiveType Long = new PrimitiveType("long", ClassType.Long, (String defaultValueExpression) -> defaultValueExpression + 'L');
    public static final PrimitiveType Double = new PrimitiveType("double", ClassType.Double, (String defaultValueExpression) -> Double.parseDouble(defaultValueExpression).toString());

    public static final PrimitiveType UnixTimeLong = new PrimitiveType("long", ClassType.UnixTimeLong);

    /**
     Create a new PrimitiveType from the provided properties.

     @param name The name of this type.
    */

    private PrimitiveType(String name, ClassType nullableType)
    {
        this(name, nullableType, null);
    }

//C# TO JAVA CONVERTER NOTE: Java does not support optional parameters. Overloaded method(s) are created above:
//ORIGINAL LINE: private PrimitiveType(string name, ClassType nullableType, Func<string,string> defaultValueExpressionConverter = null)
    private PrimitiveType(String name, ClassType nullableType, java.util.function.Function<String,String> defaultValueExpressionConverter)
    {
        Name = name;
        NullableType = nullableType;
        DefaultValueExpressionConverter = (String arg) -> defaultValueExpressionConverter.invoke(arg);
    }

    /**
     The name of this type.
    */
    private String Name;
    public final String getName()
    {
        return Name;
    }

    /**
     The nullable version of this primitive type.
    */
    private ClassType NullableType;
    private ClassType getNullableType()
    {
        return NullableType;
    }

    public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports)
    {
    }

    public final IType AsNullable()
    {
        return getNullableType();
    }

    public final boolean Contains(IType type)
    {
        return this == type;
    }

    private java.util.function.Function<String,String> DefaultValueExpressionConverter;
    private java.util.function.Function<String,String> getDefaultValueExpressionConverter()
    {
        return DefaultValueExpressionConverter;
    }

    public final String DefaultValueExpression(String sourceExpression)
    {
        String result = sourceExpression;
        if (result != null && getDefaultValueExpressionConverter() != null)
        {
            result = DefaultValueExpressionConverter(sourceExpression);
        }
        return result;
    }

    public final IType getClientType()
    {
        IType clientType = this;
        if (this == PrimitiveType.UnixTimeLong)
        {
            clientType = ClassType.UnixTimeDateTime;
        }
        return clientType;
    }

    public final String ConvertToClientType(String expression)
    {
        if (getClientType() == this)
        {
            return expression;
        }

        if (this == PrimitiveType.UnixTimeLong)
        {
            expression = String.format("OffsetDateTime.from(Instant.ofEpochSecond(%1$s));", expression);
        }
        return expression;
    }

    public final String ConvertFromClientType(String expression)
    {
        if (getClientType() == this)
        {
            return expression;
        }

        if (this == PrimitiveType.UnixTimeLong)
        {
            expression = String.format("%1$s.toEpochSecond()", expression);
        }
        return expression;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}