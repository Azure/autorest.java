// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    public class ArrayType : IType
    {
        public static readonly ArrayType ByteArray = new ArrayType(PrimitiveType.Byte);

        private ArrayType(IType elementType)
        {
            ElementType = elementType;
        }

        public IType ElementType { get; }

        public override string ToString()
        {
            return $"{ElementType}[]";
        }
        
        public IType AsNullable()
        {
            return this;
        }

        public bool Contains(IType type)
        {
            return this == type || ElementType.Contains(type);
        }

        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            ElementType.AddImportsTo(imports, includeImplementationImports);
        }
    }
}
