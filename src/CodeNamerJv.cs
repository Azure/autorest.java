// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text.RegularExpressions;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Core.Utilities.Collections;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    public class CodeNamerJv : CodeNamer
    {
        private Dictionary<IModelType, IModelType> _visited = new Dictionary<IModelType, IModelType>();

        public static HashSet<string> PrimaryTypes { get; private set; }

        #region constructor

        /// <summary>
        /// Initializes a new instance of CSharpCodeNamingFramework.
        /// </summary>
        public CodeNamerJv()
        {
            // List retrieved from
            // http://docs.oracle.com/javase/tutorial/java/nutsandbolts/_keywords.html
            ReservedWords.AddRange(new []
            {
                "abstract", "assert",   "boolean",  "Boolean",  "break",
                "byte",     "Byte",     "case",     "catch",    "char",
                "Character","class",    "Class",    "const",    "continue",
                "default",  "do",       "double",   "Double",   "else",
                "enum",     "extends",  "false",    "final",    "finally",
                "float",    "Float",    "for",      "goto",     "if",
                "implements","import",  "int",      "Integer",  "long",
                "Long",     "interface","instanceof","native",  "new",
                "null",     "package",  "private",  "protected","public",
                "return",   "short",    "Short",    "static",   "strictfp",
                "super",    "switch",   "synchronized","this",  "throw",
                "throws",   "transient","true",     "try",      "void",
                "Void",     "volatile", "while",    "Date",     "Datetime",
                "OffsetDateTime",       "Duration", "Period",   "Stream",
                "String",   "Object",   "header"
            });

            PrimaryTypes = new HashSet<string>();
            new HashSet<string>
            {
                "int", "Integer",
                "long", "Long",
                "object", "Object",
                "bool", "Boolean",
                "double", "Double",
                "float", "Float",
                "byte", "Byte",
                "byte[]", "Byte[]",
                "String",
                "LocalDate",
                "DateTime",
                "DateTimeRfc1123",
                "Duration",
                "Period",
                "BigDecimal",
                "InputStream"
            }.ForEach(s => PrimaryTypes.Add(s));
            new HashSet<string>
            {
                "int",
                "long",
                "bool",
                "double",
                "float",
                "byte",
                "byte[]"
            }.ForEach(s => PrimaryTypes.Add(s));
        }

        #endregion

        #region naming

        protected override string GetEscapedReservedName(string name, string appendValue)
        {
            if (name == null)
            {
                throw new ArgumentNullException("name");
            }

            if (appendValue == null)
            {
                throw new ArgumentNullException("appendValue");
            }

            if (ReservedWords.Contains(name, StringComparer.Ordinal))
            {
                name += appendValue;
            }

            return name;
        }

        public override string GetFieldName(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
            {
                return name;
            }
            return '_' + GetVariableName(name);
        }

        public override string GetPropertyName(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
            {
                return name;
            }
            return CamelCase(RemoveInvalidCharacters(GetEscapedReservedName(name, "Property")));
        }

        public override string GetMethodName(string name)
        {
            name = GetEscapedReservedName(name, "Method");
            return CamelCase(name);
        }

        public override string GetMethodGroupName(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
            {
                return name;
            }
            name = PascalCase(name);
            if (!name.EndsWith("s", StringComparison.OrdinalIgnoreCase))
            {
                name += "s";
            }
            return GetEscapedReservedName(name, "Operations");
        }

        public override string GetEnumMemberName(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
            {
                return name;
            }
            string result = RemoveInvalidCharacters(new Regex(@"[\\\/\.\+\ \-]+").Replace(name, "_"));
            Func<char, bool> isUpper = new Func<char, bool>(c => c >= 'A' && c <= 'Z');
            Func<char, bool> isLower = new Func<char, bool>(c => c >= 'a' && c <= 'z');
            for (int i = 1; i < result.Length - 1; i++)
            {
                if (isUpper(result[i]))
                {
                    if (result[i - 1] != '_' && isLower(result[i - 1]))
                    {
                        result = result.Insert(i, "_");
                    }
                }
            }
            return result.ToUpperInvariant();
        }

        public override string GetParameterName(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
            {
                return name;
            }
            return base.GetParameterName(GetEscapedReservedName(name, "Parameter"));
        }

        public static string GetServiceName(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
            {
                return name;
            }
            return name + "Service";
        }

        #endregion

        #region type handling

        public static string GetJavaException(string exception, CodeModel cm)
        {
            switch (exception) {
                case "IOException":
                    return "java.io.IOException";
                case "CloudException":
                    return "com.microsoft.azure.CloudException";
                case "RestException":
                    return "com.microsoft.rest.RestException";
                case "IllegalArgumentException":
                    return null;
                case "InterruptedException":
                    return null;
                default:
                    return (cm.Namespace.ToLowerInvariant())
                        + ".models." + exception;
            }
        }

        #endregion

    }
}