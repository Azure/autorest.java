// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using Newtonsoft.Json;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class CodeModelJvaf : CodeModelJva
    {
        [JsonIgnore]
        public override string Namespace
        {
            get
            {
                if (!string.IsNullOrEmpty(Settings.Instance.Namespace))
                {
                    // By default 'base.Namespace' is populated, but the namespace value comes with underscore
                    // removed, Check: why AutoRest is removing this? 
                    //
                    return Settings.Instance.Namespace.ToLower();
                }
                else
                {
                    return base.Namespace;
                }
            }
            set => base.Namespace = value;
        }


        [JsonIgnore]
        public override string ImplPackage
        {
            get
            {
                return "implementation";
            }
        }

        [JsonIgnore]
        public override string ParentDeclaration
        {
            get
            {
                return " extends AzureServiceClient";
            }
        }

        public bool IsMultiApi => Settings.Instance.Host?.GetValue<bool?>("multiapi").Result ?? false;

        [JsonIgnore]
        public string GroupId
        {
            get
            {
                if (this.IsMultiApi)
                {
                    var ns = Settings.Instance.Host?.GetValue<string>("namespace").Result;
                    var groupId = ns.Replace(".management", "");
                    return $"<groupId>{groupId}</groupId>";
                }
                else
                {
                    // FluentPremium module will inherit groupId from it's parent
                    // POM hence there is no module specific <groupId></groupId> 
                    // entry in it's POM.
                    //
                    return string.Empty;
                }
            }
        }

        [JsonIgnore]
        public override string ModuleName
        {
            get
            {
                FluentConfig fluentConfig = FluentConfig.Create();
                //
                // e.g. <artifactId>azure-mgmt-compute</artifactId>
                //
                return fluentConfig.ModuleName == null ? 
                    $"azure-mgmt-{ServiceName.ToLower()}" : 
                    $"azure-mgmt-{fluentConfig.ModuleName.ToLower()}";
            }
        }


        private const string autogenedLibPomVersion = "1.0.0-beta";
        [JsonIgnore]
        public string Version
        {
            get
            {
                if (this.IsMultiApi)
                {
                    return $"<version>{autogenedLibPomVersion}</version>";
                }
                else
                {
                    // FluentPremium module will inherit version from it's parent
                    // POM hence there is no module specific <version></version> 
                    // entry in it's POM.
                    //
                    return string.Empty;
                }
            }
        }

        private const string autogenedLibParentPomVersion = "1.0.1";
        private const string fluentPremiumLibParentPomVersion = "1.16.0";

        [JsonIgnore]
        public override string ParentPomVersion
        {
            get
            {
                if (IsMultiApi)
                {
                    return $"{autogenedLibParentPomVersion}";
                }
                else
                {
                    return fluentPremiumLibParentPomVersion + "-SNAPSHOT";
                }
            }
        }

        [JsonIgnore]
        public string ParentPomRelativePath
        {
            get
            {
                if (IsMultiApi)
                {
                    return $"../../../pom.xml";
                }
                else
                {
                    return "../pom.xml";
                }
            }
        }

        [JsonIgnore]
        public string ParentPomArtifactId
        {
            get
            {
                if (IsMultiApi)
                {
                    return $"azure-arm-parent";
                }
                else
                {
                    return "azure-parent";
                }
            }
        }

        [JsonIgnore]
        public override List<string> InterfaceImports
        {
            get
            {
                var imports = base.InterfaceImports;
                imports.Add("com.microsoft.azure.AzureClient");
                return imports.OrderBy(i => i).ToList();
            }
        }

        [JsonIgnore]
        public override IEnumerable<string> ImplImports
        {
            get
            {
                var imports = new List<string>();
                var ns = Namespace.ToLowerInvariant();
                foreach (var i in base.ImplImports.ToList())
                {
                    if (i.StartsWith(ns + "." + ImplPackage, StringComparison.OrdinalIgnoreCase))
                    {
                        // Same package, do nothing
                    }
                    else if (Operations.Any(m => i.EndsWith(m.TypeName, StringComparison.OrdinalIgnoreCase)))
                    {
                        // do nothing
                    }
                    else if (i.EndsWith(this.Name, StringComparison.OrdinalIgnoreCase))
                    {
                        // do nothing
                    }
                    else
                    {
                        imports.Add(i);
                    }
                }
                return imports.OrderBy(i => i).ToList();
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
                var versionParts = fluentPremiumLibParentPomVersion.Split('.');
                var minorVersion = int.Parse(versionParts[1]);
                var patchVersion = int.Parse(versionParts[2]);

                var newMinorVersion = patchVersion == 0
                    ? minorVersion
                    : minorVersion + 1;

                var result = "V" + versionParts[0] + "_" + newMinorVersion + "_0";
                return result;
            }
        }

        public override string ApiVersion
        {
            get
            {
                if (base.ApiVersion != null)
                {
                    return base.ApiVersion;
                }

                if (IsMultiApi)
                {
                    // Calculate api version from namespace
                    var ns = Settings.Instance.Host?.GetValue<string>("namespace").Result;
                    var apiVersion = ns.Split('.').Last().Replace("v", "").Replace('_', '-');
                    return apiVersion;
                }
                else
                {
                    return null;
                }
            }
            set
            {
                base.ApiVersion = value;
            }
        }

        [JsonIgnore]
        public override string UserAgentString
        {
            get
            {
                if (IsMultiApi)
                {
                    return base.UserAgentString.Replace("%s)\"", "%s, auto-generated)\"");
                }
                else
                {
                    return base.UserAgentString;
                }
            }
        }
    }
}
