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
            
            string package = settings.GetPackage(settings.GenerateClientInterfaces ? settings.ImplementationSubpackage : null);
            string interfaceName = methodGroup.Name;
            
            // Call this instead of overriding CodeNamer::GetMethodGroupName(). This is so that
            // anonymous header and response types can be generated according to the original name
            // of the method groups. This avoids breaking changes to those types when we change
            // the logic of pluralizing method group names in the future.
            if (!interfaceName.EndsWith('s'))
            {
                interfaceName += 's';
            }
            interfaceName = (settings.ClientTypePrefix??"") + interfaceName;

            string className = interfaceName;
            if (settings.IsFluent)
            {
                className += "Inner";
            }
            else if (settings.GenerateClientInterfaces)
            {
                className += "Impl";
            }

            string restAPIName = interfaceName + "Service";
            string restAPIBaseURL = methodGroup.CodeModel.BaseUrl;
            List<ProxyMethod> restAPIMethods = new List<ProxyMethod>();
            foreach (MethodJv method in methodGroup.Methods)
            {
                restAPIMethods.Add(Mappers.ProxyMethodMapper.Map(method));
            }
            Proxy restAPI = new Proxy(restAPIName, interfaceName, restAPIBaseURL, restAPIMethods);

            List<string> implementedInterfaces = new List<string>();
            if (!settings.IsFluent && settings.GenerateClientInterfaces)
            {
                implementedInterfaces.Add(interfaceName);
            }

            string variableType = interfaceName + (settings.IsFluent ? "Inner" : "");
            string variableName = interfaceName.ToCamelCase();

            IEnumerable<ClientMethod> clientMethods = methodGroup.Methods
                .Cast<MethodJv>()
                .SelectMany(m => Mappers.ClientMethodMapper.Map(m));

            string serviceClientClassName = (settings.ClientTypePrefix??"") + methodGroup.CodeModel.Name;
            if (settings.GenerateClientInterfaces)
            {
                serviceClientClassName += "Impl";
            }

            return new MethodGroupClient(package, className, interfaceName, implementedInterfaces, restAPI, serviceClientClassName, variableType, variableName, clientMethods);
        }
    }
}