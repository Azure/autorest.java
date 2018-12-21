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
    public class CompositeExceptionParser : IParser<CompositeTypeJv, ServiceException>
    {
        private JavaSettings settings;
        private ParserFactory factory;

        public CompositeExceptionParser(ParserFactory factory)
        {
            this.settings = factory.Settings;
            this.factory = factory;
        }

        public ServiceException Parse(CompositeTypeJv compositeType)
        {
            string errorName = compositeType.ModelTypeName;

            string methodOperationExceptionTypeName = errorName + "Exception";

            if (compositeType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
            {
                JContainer ext = compositeType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
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
                    exceptionSubPackage = compositeType.IsInnerModel ? settings.ImplementationSubpackage : "";
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