// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Linq;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;

namespace AutoRest.Java
{
    internal static class ExtensionMethods
    {
        internal static string TrimMultilineHeader(this string header)
        {
            if (string.IsNullOrEmpty(header))
            {
                return header;
            }
            StringBuilder builder = new StringBuilder();
            foreach (string headerLine in header.Split(new string[] { Environment.NewLine }, StringSplitOptions.None))
            {
                builder.Append(headerLine.TrimEnd()).Append(Environment.NewLine);
            }
            return builder.ToString();
        }

        internal static bool GetBoolSetting(this Settings autoRestSettings, string settingName, bool defaultValue = false)
        {
            bool customSettingValue = defaultValue;

            string settingValueString = GetStringSetting(autoRestSettings, settingName, null);
            if (bool.TryParse(settingValueString, out bool settingValueBool))
            {
                customSettingValue = settingValueBool;
            }

            return customSettingValue;
        }

        internal static string GetStringSetting(this Settings autoRestSettings, string settingName, string defaultValue = null)
        {
            return autoRestSettings.Host.GetValue(settingName).Result ?? defaultValue;
        }

        internal static bool MethodHasSequenceType(this IModelType modelType)
        {
            return modelType is SequenceType ||
                (modelType is CompositeType modelCompositeType &&
                 modelCompositeType.Properties.Any((Property property) => MethodHasSequenceType(property.ModelType)));
        }

        internal static bool IsTopLevelResourceUrl(this string[] urlSplits)
        {
            return urlSplits.Length == 8 &&
                urlSplits[0].EqualsIgnoreCase("subscriptions") &&
                urlSplits[2].EqualsIgnoreCase("resourceGroups") &&
                urlSplits[4].EqualsIgnoreCase("providers");
        }

        internal static bool IsNullable(this IVariable variable)
            => variable.IsXNullable.HasValue ? variable.IsXNullable.Value : !variable.IsRequired;
    }
}
