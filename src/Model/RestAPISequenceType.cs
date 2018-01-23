// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A REST API type that represents a sequence/list of values.
    /// </summary>
    public class RestAPISequenceType : RestAPIType
    {
        /// <summary>
        /// Create a new RestAPISequenceType with the provided properties.
        /// </summary>
        /// <param name="elementType">The type of values that are stored in this sequence type.</param>
        public RestAPISequenceType(RestAPIType elementType)
        {
            ElementType = elementType;
        }

        /// <summary>
        /// Get the type of the values that are stored in this sequence type.
        /// </summary>
        public RestAPIType ElementType { get; }

        public string ClientTypeName => $"List<{ElementType.ClientTypeName}>";
    }
}
