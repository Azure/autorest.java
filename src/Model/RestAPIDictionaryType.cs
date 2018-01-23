// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A REST API type that represents a mapping from strings to a value type.
    /// </summary>
    public class RestAPIDictionaryType : RestAPIType
    {
        /// <summary>
        /// Create a new RestAPIMapType with the provided properties.
        /// </summary>
        /// <param name="valueType">The type that strings map to in this mapping.</param>
        public RestAPIDictionaryType(RestAPIType valueType)
        {
            ValueType = valueType;
        }

        /// <summary>
        /// Get the type that strings map to in this mapping.
        /// </summary>
        public RestAPIType ValueType { get; }

        public string ClientTypeName => $"Dictionary<string,{ValueType.ClientTypeName}>";
    }
}
