// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A REST API type that represents a single value.
    /// </summary>
    public class RestAPIValueType : RestAPIType
    {
        /// <summary>
        /// Create a new RestAPIValueType with the provided properties.
        /// </summary>
        /// <param name="clientTypeName">The name of the type.</param>
        public RestAPIValueType(string clientTypeName)
        {
            ClientTypeName = clientTypeName;
        }

        public string ClientTypeName { get; }
    }
}
