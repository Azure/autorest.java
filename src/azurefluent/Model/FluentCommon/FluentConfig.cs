// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing the configuration that is passed as autorest argument with key "--fconfig."
    /// </summary>
    public class FluentConfig
    {
        [JsonProperty]
        private string moduleName { get; set; }
        [JsonProperty]
        private List<string> knownPlurals { get; set; }
        [JsonProperty]
        private List<string> knownSingulars { get; set; }
        [JsonProperty]
        private List<UrlMapping> urlMappings { get; set; }

        [JsonIgnore]
        public static readonly string ConfigKey = "fconfig";

        /// <summary>
        /// Creates a singleton instance of configuration.
        /// </summary>
        /// <returns></returns>
        public static FluentConfig Create()
        {
            string cfg = Settings.Instance.CustomSettings.GetValue<string>(FluentConfig.ConfigKey);
            if (cfg == null)
            {
                return new FluentConfig();
            }
            else
            {
                return JsonConvert.DeserializeObject<FluentConfig>(cfg);
            }
        }

        /// <summary>
        /// The confgiuration when exists used to derive the manager name and maven artifact id of the module.
        /// 
        /// e.g. {Redis}Manager, azure-mgmt-{redis} where module name is "Redis".
        /// </summary>
        public string ModuleName
        {
            get
            {
                return this.moduleName;
            }
        }

        /// <summary>
        /// Checks the given string value is a known plural declared in the configuration.
        /// </summary>
        /// <param name="str">value to check</param>
        /// <returns>true if string is a known plural, false otherwise</returns>
        public bool IsKnownPlural(string str)
        {
            if (knownPlurals == null)
            {
                return false;
            }
            return this.knownPlurals.Contains(str, StringComparer.OrdinalIgnoreCase);
        }

        /// <summary>
        /// Checks the given string value is a known singular declared in the configuration.
        /// </summary>
        /// <param name="str">value to check</param>
        /// <returns>true if string is a known singular, false otherwise</returns>
        public bool IsKnownSingular(string str)
        {
            if (knownSingulars == null)
            {
                return false;
            }
            return this.knownSingulars.Contains(str, StringComparer.OrdinalIgnoreCase);
        }

        /// <summary>
        /// Maps the given url to a different url if such a mapping specified in the configuration.
        /// </summary>
        /// <param name="url">the url to map</param>
        /// <returns>the mapped url if mapping exists, if mapping does not exists then the input url will be returned</returns>
        public string MappedUrl(string url)
        {
            if (this.urlMappings == null || url == null)
            {
                return url;
            }
            else
            {
                var mappedTo = urlMappings.FirstOrDefault(m => url.StartsWith(m.From, StringComparison.OrdinalIgnoreCase));
                if (mappedTo != null)
                {
                    return url.Replace(mappedTo.From, mappedTo.To);
                }
                else
                {
                    return url;
                }
            }
        }
    }

    /// <summary>
    /// Type of an entry in the fluent configuration that represents url mapping.
    /// </summary>
    public class UrlMapping
    {
        [JsonProperty]
        private string from { get; set; }

        [JsonProperty]
        private string to { get; set; }

        /// <summary>
        /// The url to map.
        /// </summary>
        public string From
        {
            get
            {
                return this.from;
            }
        }

        /// <summary>
        /// The mapped url.
        /// </summary>
        public string To
        {
            get
            {
                return this.to;
            }
        }
    }
}
