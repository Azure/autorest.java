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

        public IModelTypeJv ClientType => this;
    }
}