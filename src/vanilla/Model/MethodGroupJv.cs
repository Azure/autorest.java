// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using AutoRest.Core.Utilities;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Java.DanModel;

namespace AutoRest.Java.Model
{
    public class MethodGroupJv : MethodGroup
    {
        public MethodGroupJv()
        {
            Name.OnGet += Core.Utilities.Extensions.ToCamelCase;
        }
        public MethodGroupJv(string name) : base(name)
        {
            Name.OnGet += Core.Utilities.Extensions.ToCamelCase;
        }

        [JsonIgnore]
        public string MethodGroupFullType
        {
            get
            {
                return (CodeModel.Namespace.ToLowerInvariant()) + "." + TypeName;
            }
        }

        [JsonIgnore]
        public virtual string MethodGroupDeclarationType
        {
            get
            {
                return TypeName;
            }
        }

        [JsonIgnore]
        public string MethodGroupImplType
        {
            get
            {
                return TypeName + ImplClassSuffix;
            }
        }

        [JsonIgnore]
        public virtual string ImplClassSuffix
        {
            get
            {
                return "Impl";
            }
        }

        [JsonIgnore]
        public virtual string ParentDeclaration
        {
            get
            {
                return " implements " + MethodGroupTypeString;
            }
        }

        [JsonIgnore]
        public virtual string ImplPackage
        {
            get
            {
                return "implementation";
            }
        }

        [JsonIgnore]
        public string MethodGroupTypeString
        {
            get
            {
                if (this.Methods
                    .SelectMany(m => DanCodeGenerator.MethodImplImports(m))
                    .Any(i => i.Split('.').LastOrDefault() == TypeName))
                {
                    return MethodGroupFullType;
                }
                return TypeName;
            }
        }

        [JsonIgnore]
        public string MethodGroupServiceType
        {
            get
            {
                return CodeNamerJv.GetServiceName(Name.ToPascalCase());
            }
        }

        [JsonIgnore]
        public virtual string ServiceClientType
        {
            get
            {
                return CodeModel.Name + "Impl";
            }
        }

        [JsonIgnore]
        public virtual IEnumerable<string> ImplImports
        {
            get
            {
                var imports = new List<string>();
                imports.Add("com.microsoft.rest.v2.RestProxy");
                imports.Add("com.microsoft.rest.v2.RestResponse");
                if (MethodGroupTypeString == TypeName)
                {
                    imports.Add(MethodGroupFullType);
                }
                imports.AddRange(this.Methods
                    .SelectMany(m => DanCodeGenerator.MethodImplImports(m))
                    .OrderBy(i => i).Distinct());
                return imports;
            }
        }
    }
}