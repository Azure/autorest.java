// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


namespace AutoRest.Java.Model
{
    /// <summary>
    /// The type of a property or parameter defined within the service.
    /// </summary>
    public class ServiceType
    {
        /// <summary>
        /// Create a new ServiceType with the provided properties.
        /// </summary>
        /// <param name="name">The name of the ServiceType.</param>
        public ServiceType(string name)
        {
            Name = name;
        }

        /// <summary>
        /// Get the name of this type.
        /// </summary>
        public string Name { get; }
    }
}
