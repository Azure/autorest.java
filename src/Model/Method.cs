// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    public class Method
    {
        public Method(string description, IType returnType, string name, IEnumerable<Parameter> parameters)
        {
            Description = description;
            ReturnType = returnType;
            Name = name;
            Parameters = parameters;
        }

        /// <summary>
        /// The description of this method.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// The type that this method returns, or null if this is a constructor.
        /// </summary>
        public IType ReturnType { get; }

        /// <summary>
        /// The name of this method.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The parameters of this method.
        /// </summary>
        public IEnumerable<Parameter> Parameters { get; }

        /// <summary>
        /// Add this property's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public virtual void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            ReturnType.AddImportsTo(imports, includeImplementationImports);

            foreach (Parameter parameter in Parameters)
            {
                parameter.AddImportsTo(imports, includeImplementationImports);
            }
        }
    }
}
