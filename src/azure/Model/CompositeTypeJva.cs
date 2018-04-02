using AutoRest.Core.Model;
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

        public override string DeclarationName => ModelResourceType == ResourceType.None ? Name.Value : ModelResourceType.ToString();

        protected string _azureRuntimePackage = "com.microsoft.azure";

        [JsonIgnore]
        public override string Package => IsResource
            ? _azureRuntimePackage
            : base.Package.Replace(".models", "");

        [JsonIgnore]
        public bool IsResource =>
            ModelResourceType != ResourceType.None;

        [JsonIgnore]
        public ResourceType ModelResourceType
        {
            get
            {
                if (!Extensions.ContainsKey(AzureExtensions.AzureResourceExtension) || !(bool)Extensions[AzureExtensions.AzureResourceExtension])
                {
                    return ResourceType.None;
                }
                else
                {
                    if (Name.RawValue == "SubResource")
                    {
                        return ResourceType.SubResource;
                    }
                    else if (Name.RawValue == "TrackedResource")
                    {
                        return ResourceType.Resource;
                    }
                    else if (Name.RawValue == "ProxyResource")
                    {
                        return ResourceType.ProxyResource;
                    }
                    else if (Name.RawValue == "Resource")
                    {
                        var locationProperty = Properties.Where(p => p.Name == "location").FirstOrDefault();
                        var tagsProperty = Properties.Where(p => p.Name == "tags").FirstOrDefault();
                        if (locationProperty == null || tagsProperty == null)
                        {
                            var idProperty = Properties.Where(p => p.Name == "id").FirstOrDefault();
                            var nameProperty = Properties.Where(p => p.Name == "name").FirstOrDefault();
                            var typeProperty = Properties.Where(p => p.Name == "type").FirstOrDefault();
                            if (idProperty == null || nameProperty == null || typeProperty == null)
                            {
                                return ResourceType.SubResource;
                            }
                            return ResourceType.ProxyResource;
                        }
                        return ResourceType.Resource;
                    }
                    else
                    {
                        return ResourceType.None;
                    }
                }
            }
        }

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
                        imports.Add(string.Join(".", Package, DeclarationName));
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
                        imports.Add("com.microsoft.azure." + ((CompositeTypeJva) property.ModelType).DeclarationName);
                    }
                }
                if (this.BaseModelType != null && this.BaseModelType.IsResource())
                {
                    imports.Add("com.microsoft.azure." + ((CompositeTypeJva) BaseModelType).DeclarationName);
                }
                return imports.Distinct();
            }
        }
    }

    public enum ResourceType
    {
        Resource,
        ProxyResource,
        SubResource,
        None
    }
}
