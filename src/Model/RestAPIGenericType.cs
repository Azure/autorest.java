// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Linq;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A generic type that is used by a REST API method.
    /// </summary>
    public class RestAPIGenericType : RestAPIType
    {
        /// <summary>
        /// Create a new RestAPIGenericType from the provided properties.
        /// </summary>
        /// <param name="name">The main non-generic type of this generic type.</param>
        /// <param name="typeArguments">The type arguments of this generic type.</param>
        public RestAPIGenericType(string name, params RestAPIType[] typeArguments)
        {
            Name = name;
            TypeArguments = typeArguments;
        }

        /// <summary>
        /// The main non-generic type of this generic type.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The type arguments of this generic type.
        /// </summary>
        public RestAPIType[] TypeArguments { get; }

        public override string ToString()
        {
            return $"{Name}<{string.Join(", ", TypeArguments.Select((RestAPIType typeArgument) => typeArgument.ToString()))}>";
        }
    }
}
