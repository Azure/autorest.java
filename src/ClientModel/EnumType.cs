// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using AutoRest.Core.Model;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details of an enumerated type that is used by a service.
    /// </summary>
    public class EnumType : IType
    {
        /// <summary>
        /// Create a new Enum with the provided properties.
        /// </summary>
        /// <param name="name">The name of the new Enum.</param>
        /// <param name="subpackage">The subpackage that this Enum will appear in.</param>
        /// <param name="expandable">Whether or not this will be an ExpandableStringEnum type.</param>
        /// <param name="values">The values of the Enum.</param>
        public EnumType(string package, string name, bool expandable, IEnumerable<ServiceEnumValue> values)
        {
            Name = name;
            Package = package;
            Expandable = expandable;
            Values = values;
        }

        /// <summary>
        /// The name of the new Enum.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The package that this enumeration belongs to.
        /// </summary>
        public string Package { get; }

        /// <summary>
        /// Whether or not this will be an ExpandableStringEnum type.
        /// </summary>
        public bool Expandable { get; }

        /// <summary>
        /// The values of the Enum.
        /// </summary>
        public IEnumerable<ServiceEnumValue> Values { get; }

        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            imports.Add($"{Package}.{Name}");
        }

        public IType AsNullable()
        {
            return this;
        }

        public bool Contains(IType type)
        {
            return this == type;
        }

        public string DefaultValueExpression(string sourceExpression)
        {
            return sourceExpression;
        }

        public IType ConvertToClientType()
        {
            return this;
        }

        public override string ToString()
        {
            return Name;
        }
    }
}
