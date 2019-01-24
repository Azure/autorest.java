// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A sequence type used by a client.
    /// </summary>
    public class ListType : GenericType
    {
        /// <summary>
        /// Create a new ListType from the provided properties.
        /// </summary>
        /// <param name="elementType">The type of elements that are stored in this sequence.</param>
        public ListType(IType elementType)
            : base("java.util", "List", elementType)
        {
        }

        /// <summary>
        /// The type of elements that are stored in this sequence.
        /// </summary>
        public IType ElementType => TypeArguments[0];

        public override void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            base.AddImportsTo(imports, includeImplementationImports);

            if (includeImplementationImports)
            {
                imports.Add("java.util.ArrayList");
            }
        }
    }
}
