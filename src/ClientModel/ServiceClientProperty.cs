// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A property that exists within a service's client.
    /// </summary>
    public class ServiceClientProperty
    {
        /// <summary>
        /// Create a new ServiceClientProperty with the provided properties.
        /// </summary>
        /// <param name="description">The description of this property.</param>
        /// <param name="type">The type of this property that is exposed via the client.</param>
        /// <param name="name">The name of this property.</param>
        /// <param name="isReadOnly">Whether or not this property's value can be changed by the client library.</param>
        /// <param name="defaultValueExpression">The expression that evaluates to this property's default value.</param>
        public ServiceClientProperty(string description, IType type, string name, bool isReadOnly, string defaultValueExpression)
        {
            Description = description;
            Type = type;
            Name = name;
            IsReadOnly = isReadOnly;
            DefaultValueExpression = defaultValueExpression;
        }

        /// <summary>
        /// The description of this property.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// The type of this property that is exposed via the client.
        /// </summary>
        public IType Type { get; }

        /// <summary>
        /// The name of this property.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// Get whether or not this property's value can be changed by the client library.
        /// </summary>
        public bool IsReadOnly { get; }

        /// <summary>
        /// Get the expression that evaluates to this property's default value.
        /// </summary>
        public string DefaultValueExpression { get; }

        /// <summary>
        /// Add this property's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            Type.AddImportsTo(imports, includeImplementationImports);
        }
    }
}
