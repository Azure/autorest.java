// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A generic type that is used by the client.
    /// </summary>
    public class GenericType : IType
    {
        public static GenericType Flux(IType typeArgument) => new GenericType("reactor.core.publisher", "Flux", typeArgument);
        public static GenericType Mono(IType typeArgument) => new GenericType("reactor.core.publisher", "Mono", typeArgument);
        public static GenericType OperationStatus(IType typeArgument) => new GenericType("com.microsoft.azure.v3", "OperationStatus", typeArgument);
        public static GenericType Page(IType elementType) => new GenericType("com.microsoft.azure.v3", "Page", elementType);
        public static GenericType PagedList(IType elementType) => new GenericType("com.microsoft.azure.v3", "PagedList", elementType);
        public static GenericType RestResponse(IType headersType, IType bodyType) => new GenericType("com.azure.common.http.rest", "ResponseBase", headersType, bodyType);
        public static GenericType BodyResponse(IType bodyType) => new GenericType("com.azure.common.http.rest", "SimpleResponse", bodyType);
        public static GenericType Function(IType inputType, IType outputType) => new GenericType("java.util", "Function", inputType, outputType);

        public static readonly GenericType FluxByteBuf = Flux(ClassType.ByteBuf);

        /// <summary>
        /// Create a new GenericType from the provided properties.
        /// </summary>
        /// <param name="name">The main non-generic type of this generic type.</param>
        /// <param name="typeArguments">The type arguments of this generic type.</param>
        internal GenericType(string package, string name, params IType[] typeArguments)
        {
            Name = name;
            Package = package;
            TypeArguments = typeArguments;
        }

        /// <summary>
        /// The main non-generic type of this generic type.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The package that this type belongs to.
        /// </summary>
        public string Package { get; }

        /// <summary>
        /// The type arguments of this generic type.
        /// </summary>
        public IType[] TypeArguments { get; }

        public override string ToString()
        {
            return $"{Name}<{string.Join(", ", TypeArguments.Select((IType typeArgument) => typeArgument.AsNullable().ToString()))}>";
        }

        public override bool Equals(object rhs)
        {
            return rhs is GenericType genericTypeRhs &&
                Package == genericTypeRhs.Package &&
                Name == genericTypeRhs.Name &&
                TypeArguments.SequenceEqual(genericTypeRhs.TypeArguments);
        }

        public override int GetHashCode()
        {
            return Package.GetHashCode() +
                Name.GetHashCode() +
                TypeArguments.Select(typeArgument => typeArgument.GetHashCode()).Sum();
        }

        public IType AsNullable()
        {
            return this;
        }

        public bool Contains(IType type)
        {
            return this == type || TypeArguments.Any((IType typeArgument) => typeArgument.Contains(type));
        }

        public virtual void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            imports.Add($"{Package}.{Name}");
            foreach (IType typeArgument in TypeArguments)
            {
                typeArgument.AddImportsTo(imports, includeImplementationImports);
            }
        }

        public string DefaultValueExpression(string sourceExpression)
        {
            return sourceExpression;
        }

        public IType ClientType
        {
            get
            {
                IType clientType = this;

                IType[] wireTypeArguments = TypeArguments;
                IType[] clientTypeArguments = wireTypeArguments.Select(t => t.ClientType).ToArray();

                for (int i = 0; i < clientTypeArguments.Length; ++i)
                {
                    if (clientTypeArguments[i] != wireTypeArguments[i])
                    {
                        if (this is ListType)
                        {
                            clientType = new ListType(clientTypeArguments[0]);
                        }
                        else if (this is MapType)
                        {
                            clientType = new MapType(clientTypeArguments[1]);
                        }
                        else
                        {
                            clientType = new GenericType(Package, Name, clientTypeArguments);
                        }
                        break;
                    }
                }

                return clientType;
            }
        }

        public string ConvertToClientType(string expression)
        {
            if (this == ClientType)
            {
                return expression;
            }

            IType[] wireTypeArguments = TypeArguments;
            IType[] clientTypeArguments = wireTypeArguments.Select(t => t.ClientType).ToArray();

            for (int i = 0; i < clientTypeArguments.Length; ++i)
            {
                if (clientTypeArguments[i] != wireTypeArguments[i])
                {
                    if (this is ListType)
                    {
                        expression = $"{expression}.stream().map(el -> {wireTypeArguments[i].ConvertToClientType("el")}).collect(java.util.stream.Collectors.toList())";
                    }
                    else if (this is MapType)
                    {
                        // Key is always String in Swagger 2
                        expression = $"{expression}.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, el -> {wireTypeArguments[i].ConvertToClientType("el.getValue()")}))";
                    }
                    else if (this.Package == "io.reactivex")
                    {
                        expression = $"{expression}.map(el => {wireTypeArguments[0].ConvertToClientType("el")})";
                    }
                    else
                    {
                        throw new NotSupportedException($"Instance {expression} of generic type {ToString()} not supported for conversion to client type.");
                    }
                    break;
                }
            }

            return expression;
        }

        public string ConvertFromClientType(string expression)
        {
            if (this == ClientType)
            {
                return expression;
            }

            IType[] wireTypeArguments = TypeArguments;
            IType[] clientTypeArguments = wireTypeArguments.Select(t => t.ClientType).ToArray();

            for (int i = 0; i < clientTypeArguments.Length; ++i)
            {
                if (clientTypeArguments[i] != wireTypeArguments[i])
                {
                    if (this is ListType)
                    {
                        expression = $"{expression}.stream().map(el -> {wireTypeArguments[i].ConvertFromClientType("el")}).collect(java.util.stream.Collectors.toList())";
                    }
                    else if (this is MapType)
                    {
                        // Key is always String in Swagger 2
                        expression = $"{expression}.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, el -> {wireTypeArguments[i].ConvertFromClientType("el.getValue()")}))";
                    }
                    else if (this.Package == "io.reactivex")
                    {
                        expression = $"{expression}.map(el => {wireTypeArguments[0].ConvertFromClientType("el")})";
                    }
                    else
                    {
                        throw new NotSupportedException($"Instance {expression} of generic type {ToString()} not supported for conversion from client type.");
                    }
                    break;
                }
            }

            return expression;
        }
    }
}
