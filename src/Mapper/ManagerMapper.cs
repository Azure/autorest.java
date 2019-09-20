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
    /// Maps a CodeModelJv to a Manager.
    /// </summary>
    public class ManagerMapper : IMapper<CodeModelJv, Manager>
    {
        private ManagerMapper()
        {
        }

        private static ManagerMapper _instance = new ManagerMapper();
        public static ManagerMapper Instance => _instance;

        public Manager Map(CodeModelJv codeModel)
        {
            var settings = JavaSettings.Instance;
            Manager manager = null;
            if (settings.IsFluent && settings.RegenerateManagers)
            {
                string serviceName = codeModel.GetServiceName();
                bool isCustomType = settings.IsCustomType($"{serviceName}Manager");
                string package = settings.GetPackage(isCustomType ? settings.CustomTypesSubpackage : settings.ImplementationSubpackage);
                manager = new Manager(package, codeModel.Name, serviceName, codeModel.AzureTokenCredentialsParameter, codeModel.HttpPipelineParameter);
            }
            return manager;
        }
    }
}