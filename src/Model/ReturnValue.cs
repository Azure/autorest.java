// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details of a value that is returned from a REST API method.
    /// </summary>
    public class ReturnValue
    {
        /// <summary>
        /// Create a new ReturnValue with the provided properties.
        /// </summary>
        /// <param name="type">The type of this return value.</param>
        public ReturnValue(IType type)
        {
            Type = type;
        }

        /// <summary>
        /// The type of this return value.
        /// </summary>
        public IType Type { get; }

        /// <summary>
        /// Add this property's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports, JavaSettings settings)
        {
            Type.AddImportsTo(imports, includeImplementationImports);
        }
    }
}
