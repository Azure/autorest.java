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
