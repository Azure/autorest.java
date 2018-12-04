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

        public IType Generate(JavaSettings settings)
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
    }
}