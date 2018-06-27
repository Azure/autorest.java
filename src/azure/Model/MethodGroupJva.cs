// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Linq;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using Newtonsoft.Json;

namespace AutoRest.Java.Azure.Model
{
    public class MethodGroupJva : MethodGroupJv
    {
        public const string ExternalExtension = "x-ms-external";

        public MethodGroupJva()
        {
        }
        public MethodGroupJva(string name) : base(name)
        {
        }

        [JsonIgnore]
        public override IEnumerable<string> ImplImports
        {
            get
            {
                var imports = new List<string>();
                imports.AddRange(base.ImplImports);
                bool hasLroOptions = this.Methods.OfType<MethodJva>().Any(m => m.HasLroOptions);
                if (hasLroOptions)
                {
                    imports.Add("com.microsoft.azure.LongRunningFinalState");
                    imports.Add("com.microsoft.azure.LongRunningOperationOptions");
                }
                return imports;
            }

        }
    }
}