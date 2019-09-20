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
    /// Maps a MethodGroupJv to a MethodGroupClient.
    /// </summary>
    public class MethodGroupMapper : IMapper<MethodGroupJv, MethodGroupClient>
    {
        private MethodGroupMapper()
        {
        }

        private static MethodGroupMapper _instance = new MethodGroupMapper();
        public static MethodGroupMapper Instance => _instance;

        public MethodGroupClient Map(MethodGroupJv methodGroup)
        {
            var settings = JavaSettings.Instance;
            
            string interfaceName = methodGroup.Name;
            if (ClientModels.Instance.Any(cm => cm.Name == interfaceName)) {
                interfaceName += "Operations";
            }
            string className = interfaceName;
            if (settings.IsFluent)
            {
                className += "Inner";
            }
            else if (settings.GenerateClientAsImpl)
            {
                className += "Impl";
            }

            string restAPIName = methodGroup.Name.ToString().ToPascalCase();
            if (!restAPIName.EndsWith('s'))
            {
                restAPIName += 's';
            }
            restAPIName += "Service";
            string serviceClientClassName = settings.ClientTypePrefix??"" + methodGroup.CodeModel.Name;
            string restAPIBaseURL = methodGroup.CodeModel.BaseUrl;
            List<ProxyMethod> restAPIMethods = new List<ProxyMethod>();
            foreach (MethodJv method in methodGroup.Methods)
            {
                restAPIMethods.Add(Mappers.ProxyMethodMapper.Map(method));
            }
            Proxy restAPI = new Proxy(restAPIName, serviceClientClassName + methodGroup.Name, restAPIBaseURL, restAPIMethods);

            List<string> implementedInterfaces = new List<string>();
            if (!settings.IsFluent && settings.GenerateClientInterfaces)
            {
                implementedInterfaces.Add(methodGroup.Name);
            }

            string variableType = settings.GenerateClientInterfaces ? interfaceName : className;
            string variableName = methodGroup.Name.ToCamelCase();

            IEnumerable<ClientMethod> clientMethods = methodGroup.Methods
                .Cast<MethodJv>()
                .SelectMany(m => Mappers.ClientMethodMapper.Map(m));

            if (settings.GenerateClientAsImpl)
            {
                serviceClientClassName += "Impl";
            }

            bool isCustomType = settings.IsCustomType(className);
            string package = settings.GetPackage(isCustomType ? settings.CustomTypesSubpackage : (settings.GenerateClientAsImpl ? settings.ImplementationSubpackage : null));

            return new MethodGroupClient(package, className, methodGroup.Name, implementedInterfaces, restAPI, serviceClientClassName, variableType, variableName, clientMethods);
        }
    }
}