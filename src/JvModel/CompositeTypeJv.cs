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
using AutoRest.Extensions.Azure;

namespace AutoRest.Java.Model
{
    public class CompositeTypeJv : CompositeType, IModelTypeJv
    {
        public CompositeTypeJv()
            : base()
        {}

        public CompositeTypeJv(string name)
            : base(name)
        {}

        private static readonly bool _isAzure = Settings.Instance.GetBoolSetting("azure-arm");

        private static readonly bool _isFluent = Settings.Instance.GetBoolSetting("fluent");

        public bool IsInnerModel { get; internal set; }

        public string ModelTypeName
        {
            get
            {
                var autoRestCompositeTypeName = this.Name.ToString();
                if (_isFluent && !string.IsNullOrEmpty(autoRestCompositeTypeName) && IsInnerModel)
                {
                    autoRestCompositeTypeName += "Inner";
                }
                return autoRestCompositeTypeName;
            }
        }

        private ResourceType? _modelResourceType;
        public ResourceType ModelResourceType
        {
            get
            {
                if (_modelResourceType != null)
                {
                    return _modelResourceType.Value;
                }
                if (Name.RawValue == "SubResource")
                {
                    _modelResourceType = ResourceType.SubResource;
                }
                else if (Name.RawValue == "TrackedResource")
                {
                    _modelResourceType = ResourceType.Resource;
                }
                else if (Name.RawValue == "ProxyResource")
                {
                    _modelResourceType = ResourceType.ProxyResource;
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
                            _modelResourceType = ResourceType.SubResource;
                        }
                        else
                        {
                            _modelResourceType = ResourceType.ProxyResource;
                        }
                    }
                    else
                    {
                        _modelResourceType = ResourceType.Resource;
                    }
                }
                else
                {
                    _modelResourceType = ResourceType.None;
                }
                return _modelResourceType.Value;
            }
        }

        public bool ShouldGenerateModel
        {
            get
            {
                bool shouldParseModelType = false;
                if (!_isAzure)
                {
                    shouldParseModelType = true;
                }
                else if (Extensions.Get<bool>(SwaggerExtensions.ExternalExtension) != true)
                {
                    shouldParseModelType = ModelResourceType != ResourceType.None;
                }
                return shouldParseModelType;
            }
        }

        public IType GenerateType(JavaSettings settings)
        {
            IType result = null;
            if (settings.IsAzureOrFluent)
            {
                // TODO: Not that simple
                if (ModelTypeName == ClassType.Resource.Name)
                {
                    result = ClassType.Resource;
                }
                else if (ModelTypeName == ClassType.ProxyResource.Name)
                {
                    result = ClassType.ProxyResource;
                }
                else if (ModelTypeName == ClassType.SubResource.Name)
                {
                    result = ClassType.SubResource;
                }
            }

            if (result == null)
            {
                string classPackage;
                if (!settings.IsFluent)
                {
                    classPackage = CodeGeneratorJv.GetPackage(settings, settings.ModelsSubpackage);
                }
                else if (IsInnerModel)
                {
                    classPackage = CodeGeneratorJv.GetPackage(settings, settings.ImplementationSubpackage);
                }
                else
                {
                    classPackage = CodeGeneratorJv.GetPackage(settings);
                }

                IDictionary<string, string> extensions = null;
                if (Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                {
                    JContainer ext = Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                    if (ext != null && ext["name"] != null)
                    {
                        extensions = new Dictionary<string, string>();
                        extensions[SwaggerExtensions.NameOverrideExtension] = ext["name"].ToString();
                    }
                }
                result = new ClassType(classPackage, ModelTypeName, null, extensions, IsInnerModel);
            }

            return result;
        }

        public ServiceModel GenerateModel(JavaSettings settings)
        {
            ServiceModel result = ServiceModels.Instance.GetModel(ModelTypeName);
            if (result == null)
            {
                string modelSubPackage = !settings.IsFluent ? settings.ModelsSubpackage : (IsInnerModel ? settings.ImplementationSubpackage : "");
                string modelPackage = CodeGeneratorJv.GetPackage(settings, modelSubPackage);

                bool isPolymorphic = BaseIsPolymorphic;

                ServiceModel parentModel = null;
                if (BaseModelType != null)
                {
                    parentModel = ((CompositeTypeJv)BaseModelType).GenerateModel(settings);
                }

                HashSet<string> modelImports = new HashSet<string>();
                IEnumerable<Property> compositeTypeProperties = Properties;
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
                if (string.IsNullOrEmpty(Summary) && string.IsNullOrEmpty(Documentation))
                {
                    modelDescription = $"The {ModelTypeName} model.";
                }
                else
                {
                    modelDescription = $"{Summary}{Documentation}";
                }

                string polymorphicDiscriminator = BasePolymorphicDiscriminator;

                string modelSerializedName = SerializedName;

                IEnumerable<ServiceModel> derivedTypes = ServiceModels.Instance.GetDerivedTypes(ModelTypeName);

                string modelXmlName = XmlName;

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

                result = new ServiceModel(modelPackage, ModelTypeName, modelImports, modelDescription, isPolymorphic, polymorphicDiscriminator, modelSerializedName, needsFlatten, parentModel, derivedTypes, modelXmlName, properties);

                ServiceModels.Instance.AddModel(result);
            }

            return result;
        }

        public IModelTypeJv ConvertToClientType()
        {
            return this;
        }

        public ServiceException GenerateException(JavaSettings settings)
        {
            string errorName = ModelTypeName;

            string methodOperationExceptionTypeName = errorName + "Exception";

            if (Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
            {
                JContainer ext = Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                if (ext != null && ext["name"] != null)
                {
                    methodOperationExceptionTypeName = ext["name"].ToString();
                }
            }

            // Skip any exceptions that are named "CloudErrorException" or have a body named
            // "CloudError" because those types already exist in the runtime.
            if (methodOperationExceptionTypeName != "CloudErrorException" && errorName != "CloudError")
            {
                string exceptionSubPackage;
                if (settings.IsFluent)
                {
                    exceptionSubPackage = IsInnerModel ? settings.ImplementationSubpackage : "";
                }
                else
                {
                    exceptionSubPackage = settings.ModelsSubpackage;
                }

                return new ServiceException(methodOperationExceptionTypeName, errorName, exceptionSubPackage);
            }

            return null;
        }
    }
}