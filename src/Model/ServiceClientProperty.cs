// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

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
        /// <param name="clientType">The type of this property that is exposed via the client.</param>
        /// <param name="name">The name of this property.</param>
        /// <param name="isReadOnly">Whether or not this property's value can be changed by the client library.</param>
        /// <param name="defaultValueExpression">The expression that evaluates to this property's default value.</param>
        public ServiceClientProperty(string description, string clientType, string name, bool isReadOnly, string defaultValueExpression)
        {
            Description = description;
            ClientType = clientType;
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
        public string ClientType { get; }

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
    }
}
