using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Java.DanModel;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Model
{
    public class CompositeTypeJv : CompositeType, IModelTypeJv
    {
        public const string ExternalExtension = "x-ms-external";
        protected string _runtimePackage = "com.microsoft.rest.v2";

        public CompositeTypeJv()
        {
        }

        public CompositeTypeJv(string name) : base(name)
        {
        }

        [JsonIgnore]
        public virtual string ModelsPackage => DanCodeGenerator.modelsPackage;

        [JsonIgnore]
        public virtual string Package
        {
            get
            {
                if (Extensions.Get<bool>(ExternalExtension) == true) {
                    return _runtimePackage;
                }
                else
                {
                    return string.Join(
                        ".",
                        CodeModel?.Namespace.ToLowerInvariant(),
                        "models");
                }
            }
        }

        [JsonIgnore]
        public IEnumerable<CompositeType> SubTypes
        {
            get
            {
                if (BaseIsPolymorphic)
                {
                    foreach (CompositeType type in CodeModel.ModelTypes)
                    {
                        if (type.BaseModelType != null &&
                            type.BaseModelType.SerializedName == this.SerializedName)
                        {
                            yield return type;
                        }
                    }
                }
            }
        }

        [JsonIgnore]
        public virtual string ExceptionTypeDefinitionName
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
        public bool NeedsFlatten
        {
            get
            {
                return Properties.Any(p => p.WasFlattened());
            }
        }

        [JsonIgnore]
        public virtual IEnumerable<string> Imports
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
        public virtual IEnumerable<String> ImportList
        {
            get
            {
                var classes = new HashSet<string>();
                classes.AddRange(Properties.SelectMany(pm => DanCodeGenerator.GetImports(pm)));
                if (this.Properties.Any(p => !p.GetJsonProperty().IsNullOrEmpty()))
                {
                    classes.Add("com.fasterxml.jackson.annotation.JsonProperty");
                }
                if (Properties.Any(p => p.XmlIsAttribute))
                {
                    classes.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
                }

                // For polymorphism
                if (BaseIsPolymorphic)
                {
                    classes.Add("com.fasterxml.jackson.annotation.JsonTypeInfo");
                    classes.Add("com.fasterxml.jackson.annotation.JsonTypeName");
                    if (SubTypes.Any())
                    {
                        classes.Add("com.fasterxml.jackson.annotation.JsonSubTypes");
                    }
                }
                // For flattening
                if (NeedsFlatten)
                {
                    classes.Add("com.microsoft.rest.v2.serializer.JsonFlatten");
                }
                return classes.AsEnumerable();
            }
        }

        [JsonIgnore]
        public IModelTypeJv ResponseVariant => this;

        [JsonIgnore]
        public IModelTypeJv ParameterVariant => this;

        [JsonIgnore]
        public IModelTypeJv NonNullableVariant => this;

        protected IEnumerable<IModelTypeJv> ParseGenericType()
        {
            string name = DanCodeGenerator.GetIModelTypeName(this);
            string[] types = DanCodeGenerator.GetIModelTypeName(this).Split(new String[] { "<", ">", ",", ", " }, StringSplitOptions.RemoveEmptyEntries);
            foreach (string innerType in types.Where(t => !string.IsNullOrWhiteSpace(t)))
            {
                if (!CodeNamerJv.PrimaryTypes.Contains(innerType.Trim()))
                {
                    yield return new CompositeTypeJv { Name = innerType.Trim(), CodeModel = CodeModel };
                }
            }
        }
    }
}
