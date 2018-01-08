// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details of an enumerated type that is used by a service.
    /// </summary>
    public class ServiceEnum
    {
        private readonly string name;
        private readonly string subpackage;
        private readonly bool expandable;
        private readonly IEnumerable<ServiceEnumValue> values;

        /// <summary>
        /// Create a new Enum with the provided properties.
        /// </summary>
        /// <param name="name">The name of the new Enum.</param>
        /// <param name="subpackage">The subpackage that this Enum will appear in.</param>
        /// <param name="expandable">Whether or not this will be an ExpandableStringEnum type.</param>
        /// <param name="values">The values of the Enum.</param>
        public ServiceEnum(string name, string subpackage, bool expandable, IEnumerable<ServiceEnumValue> values)
        {
            this.name = name;
            this.subpackage = subpackage;
            this.expandable = expandable;
            this.values = values;
        }

        /// <summary>
        /// The name of the new Enum.
        /// </summary>
        public string Name => name;

        /// <summary>
        /// The subpackage that this Enum will appear in.
        /// </summary>
        public string Subpackage => subpackage;

        /// <summary>
        /// Whether or not this will be an ExpandableStringEnum type.
        /// </summary>
        public bool Expandable => expandable;

        /// <summary>
        /// The values of the Enum.
        /// </summary>
        public IEnumerable<ServiceEnumValue> Values => values;
    }
}
