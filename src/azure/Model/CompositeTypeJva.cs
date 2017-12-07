using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.DanModel;
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

        protected string _azureRuntimePackage = "com.microsoft.azure.v2";

        [JsonIgnore]
        public override string Package => IsResource
            ? _azureRuntimePackage
            : base.Package.Replace(".models", "");

        [JsonIgnore]
        public bool IsResource =>
            (DanCodeGenerator.GetIModelTypeName(this) == "Resource" || DanCodeGenerator.GetIModelTypeName(this) == "SubResource") &&
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
                return DanCodeGenerator.GetIModelTypeName(this) + "Exception";
            }
        }

        [JsonIgnore]
        public override IEnumerable<string> Imports
        {
            get
            {
                var imports = new List<string>();
                if (DanCodeGenerator.GetIModelTypeName(this).Contains('<'))
                {
                    imports.AddRange(ParseGenericType().SelectMany(t => DanCodeGenerator.GetIModelTypeImports(t)));
                }
                else
                {
                    if (IsResource || Extensions.Get<bool>(ExternalExtension) == true)
                    {
                        imports.Add(string.Join(".", Package, DanCodeGenerator.GetIModelTypeName(this)));
                    }
                    else
                    {
                        imports.Add(string.Join(".", Package, "models", DanCodeGenerator.GetIModelTypeName(this)));
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
                    if (DanCodeGenerator.GetPropertyModelType(property).IsResource())
                    {
                        imports.Add("com.microsoft.azure.v2." + DanCodeGenerator.GetIModelTypeName(DanCodeGenerator.GetPropertyModelType(property)));
                    }
                }
                string baseModelTypeName = DanCodeGenerator.GetIModelTypeName(BaseModelType);
                if (baseModelTypeName == "Resource" || baseModelTypeName == "SubResource")
                {
                    imports.Add("com.microsoft.azure.v2." + baseModelTypeName);
                }
                return imports.Distinct();
            }
        }
    }
}
