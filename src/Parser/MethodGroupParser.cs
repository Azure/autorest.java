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
    public class MethodGroupParser : IParser<MethodGroupJv, MethodGroupClient>
    {
        private JavaSettings settings;
        private ParserFactory factory;

        public MethodGroupParser(ParserFactory factory)
        {
            this.settings = factory.Settings;
            this.factory = factory;
        }

        public MethodGroupClient Parse(MethodGroupJv methodGroup)
        {
            string package = settings.GetPackage(settings.GenerateClientInterfaces ? settings.ImplementationSubpackage : null);
            string className = methodGroup.Name;
            if (settings.IsFluent)
            {
                className += "Inner";
            }
            else if (settings.GenerateClientInterfaces)
            {
                className += "Impl";
            }

            string restAPIName = methodGroup.Name.ToString().ToPascalCase();
            if (!restAPIName.EndsWith('s'))
            {
                restAPIName += 's';
            }
            restAPIName += "Service";
            string restAPIBaseURL = methodGroup.CodeModel.BaseUrl;
            List<ProxyMethod> restAPIMethods = new List<ProxyMethod>();
            foreach (MethodJv method in methodGroup.Methods)
            {
                restAPIMethods.Add(factory.GetParser<MethodJv, ProxyMethod>().Parse(method));
            }
            Proxy restAPI = new Proxy(restAPIName, methodGroup.Name, restAPIBaseURL, restAPIMethods);

            List<string> implementedInterfaces = new List<string>();
            if (!settings.IsFluent && settings.GenerateClientInterfaces)
            {
                implementedInterfaces.Add(methodGroup.Name);
            }

            string variableType = methodGroup.Name + (settings.IsFluent ? "Inner" : "");
            string variableName = methodGroup.Name.ToCamelCase();

            IEnumerable<ClientMethod> clientMethods = methodGroup.Methods
                .Cast<MethodJv>()
                .SelectMany(m => factory.GetParser<MethodJv, IEnumerable<ClientMethod>>().Parse(m));

            string serviceClientClassName = settings.ClientTypePrefix??"" + methodGroup.CodeModel.Name;
            if (settings.GenerateClientInterfaces)
            {
                serviceClientClassName += "Impl";
            }

            return new MethodGroupClient(package, className, methodGroup.Name, implementedInterfaces, restAPI, serviceClientClassName, variableType, variableName, clientMethods);
        }
    }
}