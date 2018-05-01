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
    public class FluentConfig
    {
        [JsonIgnore]
        private static FluentConfig fluentConfig;

        [JsonProperty]
        private string moduleName { get; set; }
        [JsonProperty]
        private List<string> knownPlurals { get; set; }
        [JsonProperty]
        private List<string> knownSingulars { get; set; }

        [JsonIgnore]
        public static readonly string ConfigKey = "fconfig";

        public static FluentConfig Create()
        {
            if (fluentConfig == null)
            {
                string cfg = Settings.Instance.CustomSettings.GetValue<string>(FluentConfig.ConfigKey);
                if (cfg == null)
                {
                    fluentConfig = new FluentConfig();
                }
                else
                {
                    fluentConfig = JsonConvert.DeserializeObject<FluentConfig>(cfg);
                }
            }
            return fluentConfig;
        }

        public string ModuleName
        {
            get
            {
                return this.moduleName;
            }
        }

        public bool IsKnownPlural(string str)
        {
            if (knownPlurals == null)
            {
                return false;
            }
            return this.knownPlurals.Contains(str, StringComparer.OrdinalIgnoreCase);
        }

        public bool IsKnownSingular(string str)
        {
            if (knownSingulars == null)
            {
                return false;
            }
            return this.knownSingulars.Contains(str, StringComparer.OrdinalIgnoreCase);
        }
    }
}
