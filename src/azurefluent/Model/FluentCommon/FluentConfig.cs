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
        [JsonProperty]
        private List<string> KnownPlurals { get; set; }
        [JsonProperty]
        private List<string> KnownSingulars { get; set; }

        [JsonIgnore]
        public static readonly string ConfigKey = "fconfig";
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

        public bool IsKnownPlural(string str)
        {
            if (KnownPlurals == null)
            {
                return false;
            }
            return this.KnownPlurals.Contains(str, StringComparer.OrdinalIgnoreCase);
        }

        public bool IsKnownSingular(string str)
        {
            if (KnownSingulars == null)
            {
                return false;
            }
            return this.KnownSingulars.Contains(str, StringComparer.OrdinalIgnoreCase);
        }
    }
}
