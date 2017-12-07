using AutoRest.Core.Model;
using AutoRest.Java.DanModel;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Model
{
    public class SequenceTypeJv : SequenceType, IModelTypeJv
    {
        [JsonIgnore]
        public IModelTypeJv ResponseVariant
        {
            get
            {
                IModelType respvariant = DanCodeGenerator.GetIModelTypeResponseVariant(ElementType);
                if (respvariant != ElementType && (respvariant as PrimaryTypeJv)?.Nullable != false)
                {
                    return new SequenceTypeJv { ElementType = respvariant };
                }
                return this;
            }
        }

        [JsonIgnore]
        public IModelTypeJv ParameterVariant
        {
            get
            {
                IModelType respvariant = DanCodeGenerator.GetIModelTypeParameterVariant(ElementType);
                if (respvariant != ElementType && (respvariant as PrimaryTypeJv)?.Nullable != false)
                {
                    return new SequenceTypeJv { ElementType = respvariant };
                }
                return this;
            }
        }

        [JsonIgnore]
        public IEnumerable<string> Imports
        {
            get
            {
                List<string> imports = new List<string> { "java.util.List" };
                return imports.Concat(DanCodeGenerator.GetIModelTypeImports(ElementType));
            }
        }

        [JsonIgnore]
        public IModelTypeJv NonNullableVariant => this;
    }
}
