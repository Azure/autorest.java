using AutoRest.Core.Model;
using AutoRest.Java.DanModel;
using Newtonsoft.Json;
using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    public class CompositeTypeJv : CompositeType, IModelTypeJv
    {
        public CompositeTypeJv()
        {
        }

        public CompositeTypeJv(string name) : base(name)
        {
        }

        [JsonIgnore]
        public virtual IEnumerable<string> Imports => DanCodeGenerator.GetIModelTypeImports(this);

        [JsonIgnore]
        public IModelTypeJv ResponseVariant => this;

        [JsonIgnore]
        public IModelTypeJv ParameterVariant => this;

        [JsonIgnore]
        public IModelTypeJv NonNullableVariant => this;
    }
}
