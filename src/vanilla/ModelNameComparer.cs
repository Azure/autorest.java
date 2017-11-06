// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using AutoRest.Core.Model;

namespace AutoRest.Java
{
    public class ModelNameComparer : IEqualityComparer<ModelType>
    {
        private ModelNameComparer() { }
        internal static ModelNameComparer Instance { get; } = new ModelNameComparer();

        public bool Equals(ModelType x, ModelType y)
        {
            return x.Name.Equals(y.Name) || x.XmlName.Equals(y.XmlName);
        }

        public int GetHashCode(ModelType obj)
        {
            return obj.Name.GetHashCode() ^ obj.XmlName.GetHashCode();
        }
    }
}
