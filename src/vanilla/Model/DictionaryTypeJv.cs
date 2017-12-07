using AutoRest.Core.Model;
using AutoRest.Java.DanModel;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Model
{
    public class DictionaryTypeJv : DictionaryType, IModelTypeJv
    {
        [JsonIgnore]
        public IEnumerable<string> Imports
        {
            get
            {
                List<string> imports = new List<string> { "java.util.Map" };
                return imports.Concat(DanCodeGenerator.GetIModelTypeImports(this.ValueType));
            }
        }

        [JsonIgnore]
        public IModelTypeJv ResponseVariant
        {
            get
            {
                IModelType respvariant = DanCodeGenerator.GetIModelTypeResponseVariant(ValueType);
                if (respvariant != ValueType && (respvariant as PrimaryTypeJv)?.Nullable != false)
                {
                    return new DictionaryTypeJv { ValueType = respvariant };
                }
                return this;
            }
        }

        [JsonIgnore]
        public IModelTypeJv ParameterVariant
        {
            get
            {
                IModelType respvariant = DanCodeGenerator.GetIModelTypeParameterVariant(ValueType);
                if (respvariant != ValueType && (respvariant as PrimaryTypeJv)?.Nullable != false)
                {
                    return new DictionaryTypeJv { ValueType = respvariant };
                }
                return this;
            }
        }

        [JsonIgnore]
        public IModelTypeJv NonNullableVariant => this;
    }
}
