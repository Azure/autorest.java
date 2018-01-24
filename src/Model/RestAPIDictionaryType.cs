// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A dictionary type used by a REST API method.
    /// </summary>
    public class RestAPIDictionaryType : RestAPIGenericType
    {
        /// <summary>
        /// Create a new RestAPIDictionaryType from the provided properties.
        /// </summary>
        /// <param name="valueType">The type of values that are stored in this dictionary.</param>
        public RestAPIDictionaryType(RestAPIType valueType)
            : base("Map", new[] { new RestAPIPrimaryType("String"), valueType })
        {
        }

        /// <summary>
        /// The type of values that are stored in this dictionary.
        /// </summary>
        public RestAPIType ValueType => TypeArguments[1];
    }
}
