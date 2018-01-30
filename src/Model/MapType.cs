// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A map type used by a REST API method.
    /// </summary>
    public class MapType : GenericType
    {
        /// <summary>
        /// Create a new MapType from the provided properties.
        /// </summary>
        /// <param name="valueType">The type of values that are stored in this dictionary.</param>
        public MapType(IType valueType)
            : base("java.util", "Map", ClassType.String, valueType)
        {
        }

        /// <summary>
        /// The type of values that are stored in this map.
        /// </summary>
        public IType ValueType => TypeArguments[1];

        public override void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            base.AddImportsTo(imports, includeImplementationImports);
        }
    }
}
