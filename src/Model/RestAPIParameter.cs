// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A parameter for a REST API method.
    /// </summary>
    public class RestAPIParameter
    {
        /// <summary>
        /// Create a new RestAPIParameter based on the provided properties.
        /// </summary>
        /// <param name="name">The name of this parameter.</param>
        /// <param name="location">The location within the REST API method's URL where this parameter will be added.</param>
        public RestAPIParameter(string name, string location)
        {
            Name = name;
            Location = location;
        }

        /// <summary>
        /// Get the name of this parameter.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// Get the location within the REST API method's URL where this parameter will be added.
        /// </summary>
        public string Location { get; }
    }
}
