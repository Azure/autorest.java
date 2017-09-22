// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Linq;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;

namespace AutoRest.Java.Azure.Model
{
    public class MethodGroupJva : MethodGroupJv, IMethodGroupJva
    {
        public const string ExternalExtension = "x-ms-external";

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
                imports.Add("com.microsoft.azure.AzureProxy");
                imports.Remove("com.microsoft.rest.RestProxy");
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
