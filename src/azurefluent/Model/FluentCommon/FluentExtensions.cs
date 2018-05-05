// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Java.Azure.Fluent.Model;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace AutoRest.Java.azurefluent.Model
{
    public static class FluentExtensions
    {
        public static string FluentUrl(this MethodJvaf methodJvaf)
        {
            string mappingFileName = Path.Combine(Directory.GetCurrentDirectory(), @"fluenturls\fluenturls.txt");
            if (!File.Exists(mappingFileName) || String.IsNullOrEmpty(methodJvaf.Url))
            {
                return methodJvaf.Url;
            }
            else
            {
                string fluentUrl = null;
                string entry;
                using (StreamReader file = new StreamReader(mappingFileName))
                {
                    while ((entry = file.ReadLine()) != null)
                    {
                        if (!string.IsNullOrEmpty(entry))
                        {
                            string[] parts = entry.Split(':');
                            if (parts.Length != 3)
                            {
                                throw new ArgumentException($"entry '{entry}' in {mappingFileName} is invalid");
                            }
                            string methodGroupFullType = ((MethodGroupJvaf) methodJvaf.MethodGroup).MethodGroupFullType;
                            if (methodGroupFullType.Equals(parts[0], StringComparison.OrdinalIgnoreCase))
                            {
                                string aurl = parts[1];
                                string furl = parts[2];
                                if (methodJvaf.Url.StartsWith(aurl, StringComparison.OrdinalIgnoreCase))
                                {
                                    fluentUrl = methodJvaf.Url.Replace(aurl, furl, StringComparison.OrdinalIgnoreCase);
                                    break;
                                }
                            }
                        }
                    }
                }
                if (fluentUrl == null)
                {
                    return methodJvaf.Url;
                }
                else
                {
                    return fluentUrl;
                }
            }
        }


        public static bool AddIfNotExists<T, U>(this Dictionary<T, U> dict, T key, U value)
        {
            if (!dict.ContainsKey(key))
            {
                dict.Add(key, value);
                return true;
            }
            else
            {
                return false;
            }
        }

        public static bool AddIfNotExists<U>(this Dictionary<string, U> dict, string key, U value)
        {
            if (string.IsNullOrEmpty(key) || dict.ContainsKey(key))
            {
                return false;
            }
            else
            {
                dict.Add(key, value);
                return true;
            }
        }

        public static bool ContainsNonEmptyKey<U>(this Dictionary<string, U> dict, string key)
        {
            return !string.IsNullOrEmpty(key) && dict.ContainsKey(key);
        }
    }
}
