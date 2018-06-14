﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Text;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    public static class ClientModelExtensions
    {
        public const string ExternalExtension = "x-ms-external";

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

        public static string Javadoc(this Property property)
        {
            if (string.IsNullOrEmpty(property.Documentation))
            {
                return "the " + property.Name + " value.";
            }
            else
            {
                var doc = property.Documentation.EscapeXmlComment().Period();
                return doc.Substring(0, 1).ToLower() + doc.Substring(1);
            }
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

            List<string> settings = new List<string>();
            settings.Add(string.Format(CultureInfo.InvariantCulture, "value = \"{0}\"", property.SerializedName));
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

        /// <summary>
        /// A null friendly wrapper around type imports.
        /// </summary>
        /// <param name="type">an instance of IJavaType</param>
        /// <returns>a list of imports to append</returns>
        public static IEnumerable<string> ImportSafe(this IModelType type)
        {
            if (type == null)
            {
                return new List<string>();
            }
            return ((IModelTypeJv) type).Imports;
        }

        public static string ImportFrom(this HttpMethod httpMethod)
        {
            string package = "retrofit2.http.";
            if (httpMethod == HttpMethod.Delete)
            {
                return package + "HTTP";
            }
            else
            {
                return package + httpMethod.ToString().ToUpperInvariant();
            }
        }
    }
}
