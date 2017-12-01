// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace AutoRest.Java.Azure.Model
{
    public class CodeModelJva : CodeModelJv, IMethodGroupJva
    {
        public IDictionary<KeyValuePair<string, string>, string> pageClasses =
            new Dictionary<KeyValuePair<string, string>, string>();

        public const string ExternalExtension = "x-ms-external";

        [JsonIgnore]
        public IEnumerable<Property> PropertiesEx => Properties.Where(p => p.ModelType.Name != "ServiceClientCredentials");

        [JsonIgnore]
        public string SetDefaultHeaders
        {
            get
            {
                return "";
            }
        }

        /// <summary>
        /// Attempts to infer the name of the service referenced by this CodeModel.
        /// </summary>
        [JsonIgnore]
        public string ServiceName
        {
            get
            {
                var serviceNameSetting = Settings.Instance.Host?.GetValue<string>("service-name").Result;
                if (!string.IsNullOrEmpty(serviceNameSetting))
                {
                    return serviceNameSetting;
                }

                var method = Methods[0];
                var match = Regex.Match(input: method.Url, pattern: @"/providers/microsoft\.(\w+)/", options: RegexOptions.IgnoreCase);
                var serviceName = match.Groups[1].Value.ToPascalCase();
                return serviceName;
            }
        }


        const string targetVersion = "1.1.3";
        /// <summary>
        /// The Azure SDK version to reference in the generated POM.
        /// </summary>
        [JsonIgnore]
        public string PomVersion
        {
            get
            {
                return targetVersion + "-SNAPSHOT";
            }
        }

        /// <summary>
        /// The Beta.SinceVersion value to pass to the Beta annotation.
        /// </summary>
        [JsonIgnore]
        public string BetaSinceVersion
        {
            get
            {
                var versionParts = targetVersion.Split('.');
                var minorVersion = int.Parse(versionParts[1]);
                var patchVersion = int.Parse(versionParts[2]);

                var newMinorVersion = patchVersion == 0
                    ? minorVersion
                    : minorVersion + 1;

                var result = "V" + versionParts[0] + "_" + newMinorVersion + "_0";
                return result;
            }
        }

        IEnumerable<MethodJva> IMethodGroupJva.Methods => RootMethods.Cast<MethodJva>();
        string IMethodGroupJva.LoggingContext => FullyQualifiedDomainName;
        string IMethodGroupJva.ServiceType => ServiceClientServiceType;
        string IMethodGroupJva.Name => Name;
        CodeModel IMethodGroupJva.CodeModel => this;
    }
}
