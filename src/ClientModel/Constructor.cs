// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    public class Constructor
    {
        public Constructor(params MethodParameter[] parameters)
        {
            Parameters = parameters;
        }

        /// <summary>
        /// The parameters of this constructor.
        /// </summary>
        public IEnumerable<MethodParameter> Parameters { get; }

        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            foreach (MethodParameter parameter in Parameters)
            {
                parameter.AddImportsTo(imports, includeImplementationImports);
            }
        }
    }
}
