// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class DeferredFluentMethodGroupNamePrefix
    {
        private static string Prefix = "<Defered_FluentMethodGroup_Resolution>:";

        public static string AddPrefix(string name)
        {
            return $"{Prefix}{name}";
        }

        public static string RemovePrefix(string nameWithPrefix)
        {
            return nameWithPrefix.Substring(Prefix.Length);
        }

        public static bool HasPrefix(string name)
        {
            return name.StartsWith(Prefix);
        }
    }
}
