// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Model
{
    public class MethodGroupJva : MethodGroupJv, IMethodGroupJva
    {
        public MethodGroupJva()
        {
        }
        public MethodGroupJva(string name) : base(name)
        {
        }

        public override IEnumerable<string> ImplImports
        {
            get
            {
                var imports = base.ImplImports.ToList();
                imports.Add("com.microsoft.azure.v2.AzureProxy");
                imports.Remove("com.microsoft.rest.v2.RestProxy");
                return imports;
            }
        }

        IEnumerable<MethodJva> IMethodGroupJva.Methods => Methods.Cast<MethodJva>().ToList();
        string IMethodGroupJva.LoggingContext => MethodGroupFullType;
        string IMethodGroupJva.ServiceType => MethodGroupServiceType;
        string IMethodGroupJva.Name => TypeName;
        CodeModel IMethodGroupJva.CodeModel => CodeModel;
    }
}
