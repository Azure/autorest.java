// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// An individual value within an enumerated type of a service.
    /// </summary>
    public class ClientEnumValue
    {
        private readonly string name;
        private readonly string value;

        /// <summary>
        /// Create a new EnumValue with the provided name and value.
        /// </summary>
        /// <param name="name">The name of this EnumValue.</param>
        /// <param name="value">The value of this EnumValue.</param>
        public ClientEnumValue(string name, string value)
        {
            this.name = name;
            this.value = value;
        }

        /// <summary>
        /// The name of this EnumValue.
        /// </summary>
        public string Name => name;

        /// <summary>
        /// The value of this EnumValue.
        /// </summary>
        public string Value => value;
    }
}
