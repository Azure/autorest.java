// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public static class Utils
    {
        public static string TrimInnerSuffix(string str)
        {
            if (!string.IsNullOrEmpty(str) && str.EndsWith("Inner"))
            {
                return str.Substring(0, str.Length - 5);
            }
            else
            {
                return str;
            }
        }

        public static IEnumerable<string> ParameterImports(ParameterJv parameter, List<string> propertiesToSkip)
        {
            foreach (string import in parameter.InterfaceImports)
            {
                yield return import;
            }
            if (parameter.ClientType is CompositeTypeJvaf composite)
            {
                // We do unfolding of parameters only in one level so no recursive unfolding
                //
                var composedPropertiesImports = composite.ComposedProperties
                    .OfType<PropertyJvaf>()
                    .Where(p => !propertiesToSkip.Contains(p.Name.ToLowerInvariant()))
                    .SelectMany(p => p.Imports);
                foreach (string import in composedPropertiesImports)
                {
                    yield return import;
                }
            }
        }

        public static IEnumerable<string> ParameterImportsForImpl(ParameterJv parameter, string package)
        {
            return ParameterImportsForImpl(parameter, package, new List<string>());
        }

        public static IEnumerable<string> ParameterImportsForImpl(ParameterJv parameter, string package, List<string> propertiesToSkip)
        {
            foreach (string import in Utils.ParameterImports(parameter, propertiesToSkip))
            {
                if (!import.StartsWith(package))
                {
                    yield return import;
                }
                else if (!import.Contains(".implementation."))
                {
                    yield return import;
                }
            }
        }

        public static IEnumerable<string> ParameterImportsForInterface(ParameterJv parameter, string package)
        {
            return ParameterImportsForInterface(parameter, package, new List<string>());
        }

        public static IEnumerable<string> ParameterImportsForInterface(ParameterJv parameter, string package, List<string> propertiesToSkip)
        {
            foreach (string import in Utils.ParameterImports(parameter, propertiesToSkip))
            {
                if (!import.StartsWith(package))
                {
                    yield return import;
                }
                else if (import.Contains(".implementation."))
                {
                    yield return import;
                }
            }
        }

        public static IEnumerable<string> ParametersImportsForImpl(IEnumerable<ParameterJv> parameters, string package)
        {
            return parameters
                .SelectMany(parameter => ParameterImportsForImpl(parameter, package));
        }

        public static IEnumerable<string> ParametersImportsForInterface(IEnumerable<ParameterJv> parameters, string package)
        {
            return parameters
                .SelectMany(parameter => ParameterImportsForInterface(parameter, package));
        }

        public static bool IsPlural(string value, FluentConfig fluentConfig)
        {
            value = value ?? throw new ArgumentNullException(nameof(value));
            if (fluentConfig.IsKnownPlural(value))
            {
                return true;
            }
            else
            {
                // TODO: need more reliable way to check the plural
                //
                return value.EndsWith("s");
            }
        }

        public static bool IsTrackedResource(CompositeTypeJvaf model)
        {
            if (model == null)
            {
                return false;
            }
            else
            {
                //
                bool hasId = model.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("id") /*&& p.IsReadOnly*/);
                bool hasName = model.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("name") /*&& p.IsReadOnly*/);
                bool hasType = model.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("type") /*&& p.IsReadOnly*/);
                bool hasLocation = model.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("location"));
                bool hasTags = model.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("tags"));
                //
                return hasId && hasName && hasType && hasLocation && hasTags;
            }
        }

        public static bool HasProperty(IModelTypeJv type, string propertyName)
        {
            if (type is CompositeTypeJv compositeType)
            {
                return compositeType.ComposedProperties.Any(p => p.SerializedName.EqualsIgnoreCase("name"));
            }
            else
            {
                return false;
            }
        }

        public static IEnumerable<ParameterJv> RequiredParametersOfMethod(MethodJvaf method)
        {
            return method.LocalParameters.Where(parameter => parameter.IsRequired && !parameter.IsConstant);
        }

        public static HashSet<string> EmptyStringSet { get; } = new HashSet<string>();
        public static List<string> EmptyStringList { get; } = new List<string>();
        public static List<IModel> EmptyModelList { get; } = new List<IModel>();

        public static string ToStringTemplateForType(IModelTypeJv modelType)
        {
            string template = "{0}";
            string modelTypeName = modelType.DeclarationName;
            if (modelType is AutoRest.Java.Model.PrimaryTypeJv)
            {
                if (modelTypeName.Equals("UUID"))
                {
                    // {
                    //    .......
                    //    "type": "string",
                    //    "format": "uuid"
                    // }
                    template = "UUID.fromString({0})";
                }
                else if (modelTypeName.Equals("int"))
                {
                    // {
                    //    .......
                    //    "type": "integer"
                    // }
                    template = "Integer.parseInt({0})";
                }
                else if (modelTypeName.Equals("double"))
                {
                    // {
                    //    .......
                    //    "type": "number",
                    //    "format": "float"
                    // }
                    template = "Double.parseDouble({0})";
                }
                else if (modelTypeName.Equals("DateTime"))
                {
                    // {
                    //    .......
                    //    "type": "string",
                    //    "format": "date-time"
                    // }
                    template = "DateTime.parse({0})";
                }
            }
            else if (modelType is AutoRest.Java.Azure.Fluent.Model.EnumTypeJvaf mtEnum)
            {
                if (mtEnum.ModelAsString)
                {
                    // com.microsoft.rest.ExpandableStringEnum
                    //
                    template = $"{modelTypeName}.fromString({{0}})";
                }
                else
                {
                    // Native Enum
                    //
                    template = $"{modelTypeName}.valueOf({{0}})";
                }
            }
            return template;
        }
    }
}
