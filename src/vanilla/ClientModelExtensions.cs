// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Text;

namespace AutoRest.Java
{
    public static class ClientModelExtensions
    {
        public static string Period(this string documentation)
        {
            if (string.IsNullOrEmpty(documentation))
            {
                return documentation;
            }
            documentation = documentation.Trim();
            if (!documentation.EndsWith(".", StringComparison.Ordinal))
            {
                documentation += ".";
            }
            return documentation;
        }

        public static string TrimMultilineHeader(this string header)
        {
            if (string.IsNullOrEmpty(header))
            {
                return header;
            }
            StringBuilder builder = new StringBuilder();
            foreach (var headerLine in header.Split(new string[] { Environment.NewLine }, StringSplitOptions.None))
            {
                builder.Append(headerLine.TrimEnd()).Append(Environment.NewLine);
            }
            return builder.ToString();
        }

        public static string GetJsonProperty(this Property property)
        {
            if (property == null)
            {
                return null;
            }

            string name = string.IsNullOrEmpty(property.XmlName) ? property.SerializedName : property.XmlName;
            List<string> settings = new List<string>();
            settings.Add(string.Format(CultureInfo.InvariantCulture, "value = \"{0}\"", name));
            if (property.IsRequired)
            {
                settings.Add("required = true");
            }
            if (property.IsReadOnly)
            {
                settings.Add("access = JsonProperty.Access.WRITE_ONLY");
            }
            return string.Join(", ", settings);
        }

        public static void AddRange<T>(this HashSet<T> hashSet, IEnumerable<T> range)
        {
            if( hashSet == null || range == null)
            {
                return;
            }

            foreach(var item in range)
            {
                hashSet.Add(item);
            }
        }
    }
}
