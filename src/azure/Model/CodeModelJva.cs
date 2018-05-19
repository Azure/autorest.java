// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Linq;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json;
using System.Text.RegularExpressions;
using AutoRest.Core;
using AutoRest.Extensions.Azure;
using static AutoRest.Core.Utilities.DependencyInjection;

namespace AutoRest.Java.Azure.Model
{
    public class CodeModelJva : CodeModelJv
    {
        public IDictionary<KeyValuePair<string, string>, string> pageClasses =
            new Dictionary<KeyValuePair<string, string>, string>();

        public const string ExternalExtension = "x-ms-external";

        internal CompositeTypeJva _resourceType;
        internal CompositeTypeJva _proxyResourceType;
        internal CompositeTypeJva _subResourceType;

        public CodeModelJva()
        {
            var stringType = New<PrimaryType>(KnownPrimaryType.String, new
            {
                Name = "string"
            });

            _proxyResourceType = New<CompositeTypeJva>(new
            {
                SerializedName = "ProxyResource"
            });
            _proxyResourceType.Name.FixedValue = "ProxyResource";
            _proxyResourceType.Add(new PropertyJv { Name = "id", SerializedName = "id", ModelType = stringType, IsReadOnly = true });
            _proxyResourceType.Add(new PropertyJv { Name = "name", SerializedName = "name", ModelType = stringType, IsReadOnly = true });
            _proxyResourceType.Add(new PropertyJv { Name = "type", SerializedName = "type", ModelType = stringType, IsReadOnly = true });
            _proxyResourceType.Extensions[AzureExtensions.AzureResourceExtension] = true;

            _resourceType = New<CompositeTypeJva>(new
            {
                SerializedName = "Resource",
            });
            _resourceType.Name.FixedValue = "Resource";
            _resourceType.BaseModelType = _proxyResourceType;
            _resourceType.Add(new PropertyJv { Name = "location", SerializedName = "location", ModelType = stringType, IsRequired = true });
            _resourceType.Add(new PropertyJv { Name = "tags", SerializedName = "tags", ModelType = New<DictionaryType>(new { ValueType = stringType, NameFormat = "System.Collections.Generic.IDictionary<string, {0}>" }) });
            _resourceType.Extensions[AzureExtensions.AzureResourceExtension] = true;

            _subResourceType = New<CompositeTypeJva>(new
            {
                SerializedName = "SubResource"
            });
            _subResourceType.Name.FixedValue = "SubResource";
            _subResourceType.Add(new PropertyJv { Name = "id", SerializedName = "id", ModelType = stringType, IsReadOnly = true });
            _subResourceType.Extensions[AzureExtensions.AzureResourceExtension] = true;
        }

        [JsonIgnore]
        public IEnumerable<Property> PropertiesEx => Properties.Where(p => p.ModelType.Name != "ServiceClientCredentials");

        [JsonIgnore]
        public virtual string ParentDeclaration
        {
            get
            {
                return " extends AzureServiceClient implements " + Name;
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
                var imports = base.ImplImports.ToList();
                imports.Add("com.microsoft.azure.AzureClient");
                imports.Remove("com.microsoft.rest.ServiceClient");
                imports.Remove("okhttp3.OkHttpClient");
                imports.Remove("retrofit2.Retrofit");
                imports.Add("com.microsoft.azure.AzureServiceClient");
                return imports.OrderBy(i => i).ToList();
            }
        }

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

        public virtual string ModuleName
        {
            get
            {
                return "azure-" + ServiceName.ToLower();
            }
        }

        const string targetVersion = "1.7.1";
        /// <summary>
        /// The Azure SDK version to reference in the generated POM.
        /// </summary>
        [JsonIgnore]
        public virtual string ParentPomVersion
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
    }
}
