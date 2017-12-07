using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.DanModel;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class CompositeTypeJvaf : CompositeTypeJva
    {
        public CompositeTypeJvaf()
        {
        }

        public CompositeTypeJvaf(string name) : base(name)
        {
        }

        public override IEnumerableWithIndex<Property> Properties
        {
            get
            {
                IEnumerableWithIndex<Property> res = base.Properties;
                foreach (Property property in res)
                {
                    if (IsInnerModel)
                    {
                        DanCodeGenerator.innerModelProperties.Add(property);
                    }
                    else
                    {
                        DanCodeGenerator.innerModelProperties.Remove(property);
                    }
                }
                return res;
            }
        }

        [JsonIgnore]
        public override string Package
        {
            get
            {
                if (this.IsResource)
                {
                    return _azureRuntimePackage;
                }
                else if (Extensions.ContainsKey(ExternalExtension) &&
                    (bool)Extensions[ExternalExtension])
                {
                    return _runtimePackage;
                }
                else if (DanCodeGenerator.GetIModelTypeName(this).EndsWith("Inner", StringComparison.Ordinal))
                {
                    return (CodeModel?.Namespace.ToLowerInvariant()) + ".implementation";
                }
                else
                {
                    return (CodeModel?.Namespace.ToLowerInvariant());
                }
            }
        }


        [JsonIgnore]
        public override string ModelsPackage => IsInnerModel ? ".implementation" : "";

        public bool IsInnerModel { get; set; } = false;

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
                    imports.Add(string.Join(".", Package, DanCodeGenerator.GetIModelTypeName(this)));
                }
                return imports;
            }
        }

        [JsonIgnore]
        public override IEnumerable<string> ImportList
        {
            get
            {
                List<string> imports = base.ImportList.ToList();
                if (BaseModelType != null && DanCodeGenerator.GetIModelTypeName(BaseModelType).EndsWith("Inner", StringComparison.Ordinal) ^ IsInnerModel)
                {
                    imports.AddRange(BaseModelType.ImportSafe());
                }
                return imports.Distinct();
            }
        }
    }
}
