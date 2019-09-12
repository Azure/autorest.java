package com.azure.autorest.models.clientmodel;

import java.util.*;
import java.util.stream.Collectors;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A generic type that is used by the client.
*/
public class GenericType implements IType
{
	public static GenericType Flux(IType typeArgument)
	{
		return new GenericType("reactor.core.publisher", "Flux", typeArgument);
	}
	public static GenericType Mono(IType typeArgument)
	{
		return new GenericType("reactor.core.publisher", "Mono", typeArgument);
	}
	public static GenericType OperationStatus(IType typeArgument)
	{
		return new GenericType("com.microsoft.azure.v3", "OperationStatus", typeArgument);
	}
	public static GenericType Page(IType elementType)
	{
		return new GenericType("com.microsoft.azure.v3", "Page", elementType);
	}
	public static GenericType PagedList(IType elementType)
	{
		return new GenericType("com.microsoft.azure.v3", "PagedList", elementType);
	}
	public static GenericType RestResponse(IType headersType, IType bodyType)
	{
		return new GenericType("com.azure.core.http.rest", "ResponseBase", headersType, bodyType);
	}
	public static GenericType BodyResponse(IType bodyType)
	{
		return new GenericType("com.azure.core.http.rest", "SimpleResponse", bodyType);
	}
	public static GenericType Function(IType inputType, IType outputType)
	{
		return new GenericType("java.util", "Function", inputType, outputType);
	}

	public static final GenericType FluxByteBuffer = Flux(ClassType.ByteBuffer);

	/** 
	 Create a new GenericType from the provided properties.
	 
	 @param name The main non-generic type of this generic type.
	 @param typeArguments The type arguments of this generic type.
	*/
	public GenericType(String package_Keyword, String name, IType... typeArguments)
	{
		Name = name;
		Package = package_Keyword;
		TypeArguments = typeArguments;
	}

	/** 
	 The main non-generic type of this generic type.
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}

	/** 
	 The package that this type belongs to.
	*/
	private String Package;
	public final String getPackage()
	{
		return Package;
	}

	/** 
	 The type arguments of this generic type.
	*/
	private IType[] TypeArguments;
	public final IType[] getTypeArguments()
	{
		return TypeArguments;
	}

	@Override
	public String toString()
	{
		return String.format("%1$s<%2$s>", getName(), Arrays.stream(getTypeArguments()).map((IType typeArgument) -> typeArgument.AsNullable().toString()).collect(Collectors.joining(", ")));
	}

	@Override
	public boolean equals(Object rhs)
	{
		boolean tempVar = rhs instanceof GenericType;
		GenericType genericTypeRhs = tempVar ? (GenericType)rhs : null;
//C# TO JAVA CONVERTER WARNING: Java Arrays.equals is not always identical to LINQ 'SequenceEqual':
//ORIGINAL LINE: return rhs is GenericType genericTypeRhs && Package == genericTypeRhs.Package && Name == genericTypeRhs.Name && TypeArguments.SequenceEqual(genericTypeRhs.TypeArguments);
		return tempVar && getPackage().equals(genericTypeRhs.Package) && getName().equals(genericTypeRhs.Name) && Arrays.equals(getTypeArguments(), genericTypeRhs.TypeArguments);
	}

	@Override
	public int hashCode()
	{
		return getPackage().hashCode() + getName().hashCode() + Arrays.stream(getTypeArguments()).map(Object::hashCode).reduce(0, (a, b) -> a + b);
	}

	public final IType AsNullable()
	{
		return this;
	}

	public final boolean Contains(IType type)
	{
		return this == type || Arrays.stream(getTypeArguments()).anyMatch((IType typeArgument) -> typeArgument.Contains(type));
	}

	public void AddImportsTo(Set<String> imports, boolean includeImplementationImports)
	{
		imports.add(String.format("%1$s.%2$s", getPackage(), getName()));
		for (IType typeArgument : getTypeArguments())
		{
			typeArgument.AddImportsTo(imports, includeImplementationImports);
		}
	}

	public final String DefaultValueExpression(String sourceExpression)
	{
		return sourceExpression;
	}

	public final IType getClientType()
	{
		IType clientType = this;

		IType[] wireTypeArguments = getTypeArguments();
		IType[] clientTypeArguments = Arrays.stream(wireTypeArguments).map(IType::getClientType).toArray(IType[]::new);

		for (int i = 0; i < clientTypeArguments.length; ++i)
		{
			if (clientTypeArguments[i] != wireTypeArguments[i])
			{
				if (this instanceof ListType)
				{
					clientType = new ListType(clientTypeArguments[0]);
				}
				else if (this instanceof MapType)
				{
					clientType = new MapType(clientTypeArguments[1]);
				}
				else
				{
					clientType = new GenericType(getPackage(), getName(), clientTypeArguments);
				}
				break;
			}
		}

		return clientType;
	}

	public final String ConvertToClientType(String expression)
	{
		if (this == getClientType())
		{
			return expression;
		}

		IType[] wireTypeArguments = getTypeArguments();
		IType[] clientTypeArguments = Arrays.stream(wireTypeArguments).map(IType::getClientType).toArray(IType[]::new);

		for (int i = 0; i < clientTypeArguments.length; ++i)
		{
			if (clientTypeArguments[i] != wireTypeArguments[i])
			{
				if (this instanceof ListType)
				{
					expression = String.format("%1$s.stream().map(el -> %2$s).collect(java.util.stream.Collectors.toList())", expression, wireTypeArguments[i].ConvertToClientType("el"));
				}
				else if (this instanceof MapType)
				{
					// Key is always String in Swagger 2
					expression = String.format("%1$s.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, el -> %2$s))", expression, wireTypeArguments[i].ConvertToClientType("el.getValue()"));
				}
				else if (this.getPackage().equals("io.reactivex"))
				{
					expression = String.format("%1$s.map(el => %2$s)", expression, wireTypeArguments[0].ConvertToClientType("el"));
				}
				else
				{
					throw new UnsupportedOperationException(String.format("Instance %1$s of generic type %2$s not supported for conversion to client type.", expression, toString()));
				}
				break;
			}
		}

		return expression;
	}

	public final String ConvertFromClientType(String expression)
	{
		if (this == getClientType())
		{
			return expression;
		}

		IType[] wireTypeArguments = getTypeArguments();
		IType[] clientTypeArguments = Arrays.stream(wireTypeArguments).map(IType::getClientType).toArray(IType[]::new);

		for (int i = 0; i < clientTypeArguments.length; ++i)
		{
			if (clientTypeArguments[i] != wireTypeArguments[i])
			{
				if (this instanceof ListType)
				{
					expression = String.format("%1$s.stream().map(el -> %2$s).collect(java.util.stream.Collectors.toList())", expression, wireTypeArguments[i].ConvertFromClientType("el"));
				}
				else if (this instanceof MapType)
				{
					// Key is always String in Swagger 2
					expression = String.format("%1$s.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, el -> %2$s))", expression, wireTypeArguments[i].ConvertFromClientType("el.getValue()"));
				}
				else if (this.getPackage().equals("io.reactivex"))
				{
					expression = String.format("%1$s.map(el => %2$s)", expression, wireTypeArguments[0].ConvertFromClientType("el"));
				}
				else
				{
					throw new UnsupportedOperationException(String.format("Instance %1$s of generic type %2$s not supported for conversion from client type.", expression, toString()));
				}
				break;
			}
		}

		return expression;
	}
}