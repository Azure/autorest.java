﻿using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Model;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Model
{
    public class CompositeTypeJva : CompositeTypeJv
    {
        public CompositeTypeJva()
        {
        }

        public CompositeTypeJva(string name) : base(name)
        {
        }

        protected string _azureRuntimePackage = "com.microsoft.azure";

        [JsonIgnore]
        public override string Package => IsResource
            ? _azureRuntimePackage
            : base.Package.Replace(".models", "");

        [JsonIgnore]
        public bool IsResource =>
            (Name.RawValue == "Resource" || Name.RawValue == "SubResource") &&
            Extensions.ContainsKey(AzureExtensions.AzureResourceExtension) && (bool)Extensions[AzureExtensions.AzureResourceExtension];

        [JsonIgnore]
        public override string ExceptionTypeDefinitionName
        {
            get
            {
                if (this.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                {
                    var ext = this.Extensions[SwaggerExtensions.NameOverrideExtension] as Newtonsoft.Json.Linq.JContainer;
                    if (ext != null && ext["name"] != null)
                    {
                        return ext["name"].ToString();
                    }
                }
                return this.Name + "Exception";
            }
        }

        [JsonIgnore]
        public override IEnumerable<string> Imports
        {
            get
            {
                var imports = new List<string>();
                if (Name.Contains('<'))
                {
                    imports.AddRange(ParseGenericType().SelectMany(t => t.Imports));
                }
                else
                {
                    if (IsResource || Extensions.Get<bool>(ExternalExtension) == true)
                    {
                        imports.Add(string.Join(".", Package, Name));
                    }
                    else
                    {
                        imports.Add(string.Join(".", Package, "models", Name));
                    }
                }
                return imports;
            }
        }

        [JsonIgnore]
        public override IEnumerable<string> ImportList
        {
            get
            {
                var imports = base.ImportList.ToList();
                foreach (var property in this.Properties)
                {
                    if (property.ModelType.IsResource())
                    {
                        imports.Add("com.microsoft.azure." + property.ModelType.Name);
                    }
                }
                if (this.BaseModelType != null && (this.BaseModelType.Name == "Resource" || this.BaseModelType.Name == "SubResource"))
                {
                    imports.Add("com.microsoft.azure." + BaseModelType.Name);
                }
                return imports.Distinct();
            }
        }
    }
}
