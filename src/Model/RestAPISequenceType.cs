// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A sequence type used by a REST API method.
    /// </summary>
    public class RestAPISequenceType : RestAPIGenericType
    {
        /// <summary>
        /// Create a new RestAPISequenceType from the provided properties.
        /// </summary>
        /// <param name="elementType">The type of elements that are stored in this sequence.</param>
        public RestAPISequenceType(RestAPIType elementType)
            : base("List", new[] { elementType })
        {
        }

        /// <summary>
        /// The type of elements that are stored in this sequence.
        /// </summary>
        public RestAPIType ElementType => TypeArguments[0];
    }
}
