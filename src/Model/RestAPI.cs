// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// Details that describe the REST API for a service.
    /// </summary>
    public class RestAPI
    {
        /// <summary>
        /// Create a new RestAPI using the provided properties.
        /// </summary>
        /// <param name="name">The name of the REST API interface.</param>
        public RestAPI(string name)
        {
            Name = name;
        }

        /// <summary>
        /// Get the name of the REST API interface.
        /// </summary>
        public string Name { get; }
    }
}
