using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Java.DanModel;
using Newtonsoft.Json;
using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    public class EnumTypeJv : EnumType, IModelTypeJv
    {
        public EnumTypeJv()
        {
            Name.OnGet += name => string.IsNullOrEmpty(name) || name == "enum" ? "String" : CodeNamer.Instance.GetTypeName(name);
        }

        [JsonIgnore]
        public virtual string ModelsPackage => DanCodeGenerator.modelsPackage;

        [JsonIgnore]
        public virtual IEnumerable<string> Imports
        {
            get
            {
                if (Name != "String")
                {
                    yield return string.Join(".",
                        CodeModel?.Namespace.ToLowerInvariant(),
                        "models", Name);
                }
            }
        }

        [JsonIgnore]
        public IModelTypeJv ResponseVariant => this;

        [JsonIgnore]
        public IModelTypeJv ParameterVariant => this;

        [JsonIgnore]
        public IModelTypeJv NonNullableVariant => this;
    }
}
