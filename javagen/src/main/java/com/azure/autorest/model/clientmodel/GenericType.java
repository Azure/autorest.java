package com.azure.autorest.model.clientmodel;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A generic type that is used by the client.
 */
public class GenericType implements IType {
    public static final GenericType FluxByteBuffer = Flux(ClassType.ByteBuffer);
    /**
     * The main non-generic type of this generic type.
     */
    private String name;
    /**
     * The package that this type belongs to.
     */
    private String packageName;
    /**
     * The type arguments of this generic type.
     */
    private IType[] typeArguments;

    /**
     * Create a new GenericType from the provided properties.
     * @param name The main non-generic type of this generic type.
     * @param typeArguments The type arguments of this generic type.
     */
    public GenericType(String package_Keyword, String name, IType... typeArguments) {
        this.name = name;
        packageName = package_Keyword;
        this.typeArguments = typeArguments;
    }

    public static GenericType Flux(IType typeArgument) {
        return new GenericType("reactor.core.publisher", "Flux", typeArgument);
    }

    public static GenericType Mono(IType typeArgument) {
        return new GenericType("reactor.core.publisher", "Mono", typeArgument);
    }

    public static GenericType OperationStatus(IType typeArgument) {
        return new GenericType("com.microsoft.azure.v3", "OperationStatus", typeArgument);
    }

    public static GenericType Page(IType elementType) {
        return new GenericType("com.microsoft.azure.v3", "Page", elementType);
    }

    public static GenericType PagedList(IType elementType) {
        return new GenericType("com.microsoft.azure.v3", "PagedList", elementType);
    }

    public static GenericType Response(IType bodyType) {
        return new GenericType("com.azure.core.http.rest", "Response", bodyType);
    }

    public static GenericType RestResponse(IType headersType, IType bodyType) {
        return new GenericType("com.azure.core.http.rest", "ResponseBase", headersType, bodyType);
    }

    public static GenericType BodyResponse(IType bodyType) {
        return new GenericType("com.azure.core.http.rest", "SimpleResponse", bodyType);
    }

    public static GenericType Function(IType inputType, IType outputType) {
        return new GenericType("java.util", "Function", inputType, outputType);
    }

    public final String getName() {
        return name;
    }

    public final String getPackage() {
        return packageName;
    }

    public final IType[] getTypeArguments() {
        return typeArguments;
    }

    @Override
    public String toString() {
        return String.format("%1$s<%2$s>", getName(), Arrays.stream(getTypeArguments()).map((IType typeArgument) -> typeArgument.asNullable().toString()).collect(Collectors.joining(", ")));
    }

    @Override
    public boolean equals(Object rhs) {
        boolean tempVar = rhs instanceof GenericType;
        GenericType genericTypeRhs = tempVar ? (GenericType) rhs : null;
//C# TO JAVA CONVERTER WARNING: Java Arrays.equals is not always identical to LINQ 'SequenceEqual':
//ORIGINAL LINE: return rhs is GenericType genericTypeRhs && Package == genericTypeRhs.Package && Name == genericTypeRhs.Name && TypeArguments.SequenceEqual(genericTypeRhs.TypeArguments);
        return tempVar && getPackage().equals(genericTypeRhs.packageName) && getName().equals(genericTypeRhs.name) && Arrays.equals(getTypeArguments(), genericTypeRhs.typeArguments);
    }

    @Override
    public int hashCode() {
        return getPackage().hashCode() + getName().hashCode() + Arrays.stream(getTypeArguments()).map(Object::hashCode).reduce(0, (a, b) -> a + b);
    }

    public final IType asNullable() {
        return this;
    }

    public final boolean contains(IType type) {
        return this == type || Arrays.stream(getTypeArguments()).anyMatch((IType typeArgument) -> typeArgument.contains(type));
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        imports.add(String.format("%1$s.%2$s", getPackage(), getName()));
        for (IType typeArgument : getTypeArguments()) {
            typeArgument.addImportsTo(imports, includeImplementationImports);
        }
    }

    public final String defaultValueExpression(String sourceExpression) {
        return sourceExpression;
    }

    public final IType getClientType() {
        IType clientType = this;

        IType[] wireTypeArguments = getTypeArguments();
        IType[] clientTypeArguments = Arrays.stream(wireTypeArguments).map(IType::getClientType).toArray(IType[]::new);

        for (int i = 0; i < clientTypeArguments.length; ++i) {
            if (clientTypeArguments[i] != wireTypeArguments[i]) {
                if (this instanceof ListType) {
                    clientType = new ListType(clientTypeArguments[0]);
                } else if (this instanceof MapType) {
                    clientType = new MapType(clientTypeArguments[1]);
                } else {
                    clientType = new GenericType(getPackage(), getName(), clientTypeArguments);
                }
                break;
            }
        }

        return clientType;
    }

    public final String convertToClientType(String expression) {
        if (this == getClientType()) {
            return expression;
        }

        IType[] wireTypeArguments = getTypeArguments();
        IType[] clientTypeArguments = Arrays.stream(wireTypeArguments).map(IType::getClientType).toArray(IType[]::new);

        for (int i = 0; i < clientTypeArguments.length; ++i) {
            if (clientTypeArguments[i] != wireTypeArguments[i]) {
                if (this instanceof ListType) {
                    expression = String.format("%1$s.stream().map(el -> %2$s).collect(java.util.stream.Collectors.toList())", expression, wireTypeArguments[i].convertToClientType("el"));
                } else if (this instanceof MapType) {
                    // Key is always String in Swagger 2
                    expression = String.format("%1$s.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, el -> %2$s))", expression, wireTypeArguments[i].convertToClientType("el.getValue()"));
                } else if (this.getPackage().equals("io.reactivex")) {
                    expression = String.format("%1$s.map(el => %2$s)", expression, wireTypeArguments[0].convertToClientType("el"));
                } else {
                    throw new UnsupportedOperationException(String.format("Instance %1$s of generic type %2$s not supported for conversion to client type.", expression, toString()));
                }
                break;
            }
        }

        return expression;
    }

    public final String convertFromClientType(String expression) {
        if (this == getClientType()) {
            return expression;
        }

        IType[] wireTypeArguments = getTypeArguments();
        IType[] clientTypeArguments = Arrays.stream(wireTypeArguments).map(IType::getClientType).toArray(IType[]::new);

        for (int i = 0; i < clientTypeArguments.length; ++i) {
            if (clientTypeArguments[i] != wireTypeArguments[i]) {
                if (this instanceof ListType) {
                    expression = String.format("%1$s.stream().map(el -> %2$s).collect(java.util.stream.Collectors.toList())", expression, wireTypeArguments[i].convertFromClientType("el"));
                } else if (this instanceof MapType) {
                    // Key is always String in Swagger 2
                    expression = String.format("%1$s.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, el -> %2$s))", expression, wireTypeArguments[i].convertFromClientType("el.getValue()"));
                } else if (this.getPackage().equals("io.reactivex")) {
                    expression = String.format("%1$s.map(el => %2$s)", expression, wireTypeArguments[0].convertFromClientType("el"));
                } else {
                    throw new UnsupportedOperationException(String.format("Instance %1$s of generic type %2$s not supported for conversion from client type.", expression, toString()));
                }
                break;
            }
        }

        return expression;
    }
}