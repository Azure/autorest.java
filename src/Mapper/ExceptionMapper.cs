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
    /// <summary>
    /// Maps a CompositeTypeJv to a ClientException.
    /// </summary>
    public class ExceptionMapper : IMapper<CompositeTypeJv, ClientException>
    {
        private ExceptionMapper()
        {
        }

        private static ExceptionMapper _instance = new ExceptionMapper();
        public static ExceptionMapper Instance => _instance;

        public ClientException Map(CompositeTypeJv compositeType)
        {
            var settings = JavaSettings.Instance;

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
                bool isCustomType = settings.IsCustomType(methodOperationExceptionTypeName);
                if (isCustomType)
                {
                    exceptionSubPackage = settings.CustomTypesSubpackage;
                }
                else if (settings.IsFluent)
                {
                    exceptionSubPackage = compositeType.IsInnerModel ? settings.ImplementationSubpackage : "";
                }
                else
                {
                    exceptionSubPackage = settings.ModelsSubpackage;
                }
                string package = settings.GetPackage(exceptionSubPackage);

                return new ClientException(package, methodOperationExceptionTypeName, errorName);
            }

            return null;
        }
    }
}