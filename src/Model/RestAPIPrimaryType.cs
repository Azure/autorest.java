// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A basic type used by a REST API method.
    /// </summary>
    public class RestAPIPrimaryType : RestAPIType
    {
        /// <summary>
        /// Create a new RestAPIPrimaryType from the provided properties.
        /// </summary>
        /// <param name="name">The name of this type.</param>
        public RestAPIPrimaryType(string name)
        {
            Name = name;
        }

        /// <summary>
        /// The name of this type.
        /// </summary>
        public string Name { get; }

        public override string ToString()
        {
            return Name;
        }
    }
}
