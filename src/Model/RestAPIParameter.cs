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
        /// <param name="skipUrlEncodingExtension"></param>
        /// <param name="isConstant">Whether or not this parameter is a constant value.</param>
        /// <param name="isRequired">Whether or not this parameter is required.</param>
        /// <param name="description">The description of this parameter.</param>
        /// <param name="clientTypeName">The type of this parameter that is exposed via the client.</param>
        public RestAPIParameter(string name, string location, string serializedName, string variableName, bool skipUrlEncodingExtension, bool isConstant, bool isRequired, string description, string clientTypeName)
        {
            Name = name;
            Location = location;
            SerializedName = serializedName;
            VariableName = variableName;
            SkipUrlEncodingExtension = skipUrlEncodingExtension;
            IsConstant = isConstant;
            IsRequired = isRequired;
            Description = description;
            ClientTypeName = clientTypeName;
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

        public bool SkipUrlEncodingExtension { get; }

        /// <summary>
        /// Get whether or not this parameter is a constant value.
        /// </summary>
        public bool IsConstant { get; }

        /// <summary>
        /// Get whether or not this parameter is required.
        /// </summary>
        public bool IsRequired { get; }

        /// <summary>
        /// Get the description of this parameter.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// Get the type of this parameter that is exposed via the client.
        /// </summary>
        public string ClientTypeName { get; }
    }
}
