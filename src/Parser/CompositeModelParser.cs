// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    public class CompositeModelParser
    {
        private JavaSettings settings;
        private ServiceModels serviceModels = ServiceModels.Instance;

        public CompositeModelParser(JavaSettings settings)
        {
            this.settings = settings;
        }

        public ServiceModel Parse(CompositeTypeJv compositeType)
        {
            ServiceModel result = serviceModels.GetModel(compositeType.ModelTypeName);
            if (result == null)
            {
                string modelSubPackage = !settings.IsFluent ? settings.ModelsSubpackage : (compositeType.IsInnerModel ? settings.ImplementationSubpackage : "");
                string modelPackage = CodeGeneratorJv.GetPackage(settings, modelSubPackage);

                bool isPolymorphic = compositeType.BaseIsPolymorphic;

                ServiceModel parentModel = null;
                if (compositeType.BaseModelType != null)
                {
                    parentModel = ((CompositeTypeJv)compositeType.BaseModelType).GenerateModel(settings);
                }

                HashSet<string> modelImports = new HashSet<string>();
                IEnumerable<Property> compositeTypeProperties = compositeType.Properties;
                foreach (Property autoRestProperty in compositeTypeProperties)
                {
                    IType propertyType = ((IModelTypeJv)autoRestProperty.ModelType).GenerateType(settings);
                    propertyType.AddImportsTo(modelImports, false);

                    IType propertyClientType = ((IModelTypeJv)autoRestProperty.ModelType).ConvertToClientType().GenerateType(settings);
                    propertyClientType.AddImportsTo(modelImports, false);
                }

                if (compositeTypeProperties.Any())
                {
                    if (settings.ShouldGenerateXmlSerialization)
                    {
                        modelImports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");

                        if (compositeTypeProperties.Any(p => p.ModelType is SequenceTypeJv))
                        {
                            modelImports.Add("java.util.ArrayList");
                        }

                        if (compositeTypeProperties.Any(p => p.XmlIsAttribute))
                        {
                            modelImports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
                        }

                        if (compositeTypeProperties.Any(p => !p.XmlIsAttribute))
                        {
                            modelImports.Add("com.fasterxml.jackson.annotation.JsonProperty");
                        }

                        if (compositeTypeProperties.Any(p => p.XmlIsWrapped))
                        {
                            modelImports.Add("com.fasterxml.jackson.annotation.JsonCreator");
                        }
                    }
                    else
                    {
                        modelImports.Add("com.fasterxml.jackson.annotation.JsonProperty");
                    }
                }

                string modelDescription;
                if (string.IsNullOrEmpty(compositeType.Summary) && string.IsNullOrEmpty(compositeType.Documentation))
                {
                    modelDescription = $"The {compositeType.ModelTypeName} model.";
                }
                else
                {
                    modelDescription = $"{compositeType.Summary}{compositeType.Documentation}";
                }

                string polymorphicDiscriminator = compositeType.BasePolymorphicDiscriminator;

                string modelSerializedName = compositeType.SerializedName;

                IEnumerable<ServiceModel> derivedTypes = serviceModels.GetDerivedTypes(compositeType.ModelTypeName);

                string modelXmlName = compositeType.XmlName;

                bool needsFlatten = false;
                List<ServiceModelProperty> properties = new List<ServiceModelProperty>();
                foreach (PropertyJv property in compositeTypeProperties)
                {
                    properties.Add(property.GenerateProperty(settings));
                    if (!needsFlatten && property.WasFlattened())
                    {
                        needsFlatten = true;
                    }
                }

                result = new ServiceModel(modelPackage, compositeType.ModelTypeName, modelImports, modelDescription, isPolymorphic, polymorphicDiscriminator, modelSerializedName, needsFlatten, parentModel, derivedTypes, modelXmlName, properties);

                serviceModels.AddModel(result);
            }

            return result;
        }
    }
}