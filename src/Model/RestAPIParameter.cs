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
        /// <param name="serializedName">The name of this parameter when it is serialized.</param>
        /// <param name="variableName">The name of this parameter when it is used as a variable.</param>
        /// <param name="clientTypeName">The name of this parameter's type when it is used within the client application.</param>
        public RestAPIParameter(string name, string location, string serializedName, string variableName, string clientTypeName, bool skipUrlEncodingExtension)
        {
            Name = name;
            Location = location;
            SerializedName = serializedName;
            VariableName = variableName;
            ClientTypeName = clientTypeName;
            SkipUrlEncodingExtension = skipUrlEncodingExtension;
        }

        /// <summary>
        /// Get the name of this parameter.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// Get the location within the REST API method's URL where this parameter will be added.
        /// </summary>
        public string Location { get; }

        /// <summary>
        /// Get the name of this parameter when it is serialized.
        /// </summary>
        public string SerializedName { get; }

        /// <summary>
        /// Get the name of this parameter when it is used as a variable.
        /// </summary>
        public string VariableName { get; }

        /// <summary>
        /// Get the name of this parameter's type when it is used within the client application.
        /// </summary>
        public string ClientTypeName { get; }

        public bool SkipUrlEncodingExtension { get; }
    }
}
