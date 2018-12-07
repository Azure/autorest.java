// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using AutoRest.Core.Utilities;
using AutoRest.Core.Model;
using Newtonsoft.Json;

namespace AutoRest.Java.Model
{
    public class MethodGroupJv : MethodGroup
    {
        public MethodGroupJv()
            : base()
        {}

        public MethodGroupJv(string name)
            : base()
        {
        }

        public MethodGroupClient GenerateMethodGroup(JavaSettings settings)
        {
            string className = Name;
            if (settings.IsFluent)
            {
                className += "Inner";
            }
            else if (settings.GenerateClientInterfaces)
            {
                className += "Impl";
            }

            string restAPIName = Name.ToString().ToPascalCase();
            if (!restAPIName.EndsWith('s'))
            {
                restAPIName += 's';
            }
            restAPIName += "Service";
            string restAPIBaseURL = CodeModel.BaseUrl;
            List<RestAPIMethod> restAPIMethods = new List<RestAPIMethod>();
            foreach (MethodJv method in Methods)
            {
                restAPIMethods.Add(method.GenerateRestAPIMethod(settings));
            }
            RestAPI restAPI = new RestAPI(restAPIName, restAPIBaseURL, restAPIMethods);

            List<string> implementedInterfaces = new List<string>();
            if (!settings.IsFluent && settings.GenerateClientInterfaces)
            {
                implementedInterfaces.Add(Name);
            }

            string variableType = Name + (settings.IsFluent ? "Inner" : "");
            string variableName = Name.ToCamelCase();

            IEnumerable<ClientMethod> clientMethods = Methods
                .Cast<MethodJv>()
                .SelectMany(m => m.GenerateClientMethods(settings));

            string serviceClientClassName = settings.ClientTypePrefix??"" + CodeModel.Name;
            if (settings.GenerateClientInterfaces)
            {
                serviceClientClassName += "Impl";
            }

            return new MethodGroupClient(className, Name, implementedInterfaces, restAPI, serviceClientClassName, variableType, variableName, clientMethods);
        }
    }
}