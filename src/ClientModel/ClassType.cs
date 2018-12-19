// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using System;
using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    public class ClassType : IType
    {
        public static readonly ClassType Void = new ClassType("java.lang", "Void");
        public static readonly ClassType Boolean = new ClassType("java.lang", "Boolean", defaultValueExpressionConverter: (string defaultValueExpression) => defaultValueExpression.ToLowerInvariant());
        public static readonly ClassType Byte = new ClassType("java.lang", "Byte");
        public static readonly ClassType Integer = new ClassType("java.lang", "Integer");
        public static readonly ClassType Long = new ClassType("java.lang", "Long", defaultValueExpressionConverter: (string defaultValueExpression) => defaultValueExpression + 'L');
        public static readonly ClassType Double = new ClassType("java.lang", "Double", defaultValueExpressionConverter: (string defaultValueExpression) => double.Parse(defaultValueExpression).ToString());
        public static readonly ClassType String = new ClassType("java.lang", "String", defaultValueExpressionConverter: (string defaultValueExpression) => CodeNamer.Instance.QuoteValue(defaultValueExpression));
        public static readonly ClassType Base64Url = new ClassType("com.microsoft.rest.v2", "Base64Url");
        public static readonly ClassType LocalDate = new ClassType("java.time", "LocalDate", defaultValueExpressionConverter: (string defaultValueExpression) => $"LocalDate.parse(\"{defaultValueExpression}\")");
        public static readonly ClassType DateTime = new ClassType("java.time", "OffsetDateTime", defaultValueExpressionConverter: (string defaultValueExpression) => $"OffsetDateTime.parse(\"{defaultValueExpression}\")");
        public static readonly ClassType Duration = new ClassType("java.time", "Duration", defaultValueExpressionConverter: (string defaultValueExpression) => $"Duration.parse(\"{defaultValueExpression}\")");
        public static readonly ClassType DateTimeRfc1123 = new ClassType("com.microsoft.rest.v2", "DateTimeRfc1123", defaultValueExpressionConverter: (string defaultValueExpression) => $"new DateTimeRfc1123(\"{defaultValueExpression}\")");
        public static readonly ClassType BigDecimal = new ClassType("java.math", "BigDecimal");
        public static readonly ClassType UUID = new ClassType("java.util", "UUID");
        public static readonly ClassType Object = new ClassType("java.lang", "Object");
        public static readonly ClassType ServiceClientCredentials = new ClassType("com.microsoft.rest.v2.credentials", "ServiceClientCredentials");
        public static readonly ClassType AzureTokenCredentials = new ClassType("com.microsoft.azure.v2.credentials", "AzureTokenCredentials");
        public static readonly ClassType CloudException = new ClassType("com.microsoft.azure.v2", "CloudException");
        public static readonly ClassType RestException = new ClassType("com.microsoft.azure.v2", "RestException");
        public static readonly ClassType UnixTime = new ClassType("com.microsoft.rest.v2", "UnixTime");
        public static readonly ClassType UnixTimeDateTime = new ClassType("java.time", "OffsetDateTime");
        public static readonly ClassType UnixTimeLong = new ClassType("java.lang", "Long");
        public static readonly ClassType AzureEnvironment = new ClassType("com.microsoft.azure.v2", "AzureEnvironment");
        public static readonly ClassType HttpPipeline = new ClassType("com.microsoft.rest.v2.http", "HttpPipeline");
        public static readonly ClassType Completable = new ClassType("io.reactivex", "Completable");
        public static readonly ClassType AzureProxy = new ClassType("com.microsoft.azure.v2", "AzureProxy");
        public static readonly ClassType RestProxy = new ClassType("com.microsoft.rest.v2", "RestProxy");
        public static readonly ClassType Validator = new ClassType("com.microsoft.rest.v2", "Validator");
        public static readonly ClassType Function = new ClassType("io.reactivex.functions", "Function");
        public static readonly ClassType ByteBuffer = new ClassType("java.nio", "ByteBuffer");
        public static readonly ClassType Resource = new ClassType("com.microsoft.azure.v2", "Resource");
        public static readonly ClassType ProxyResource = new ClassType("com.microsoft.azure.v2", "ProxyResource");
        public static readonly ClassType SubResource = new ClassType("com.microsoft.azure.v2", "SubResource");
        public static readonly ClassType URL = new ClassType("java.net", "URL");
        public static readonly ClassType NonNull = new ClassType("io.reactivex.annotations", "NonNull");
        public static readonly ClassType OperationDescription = new ClassType("com.microsoft.rest.v2", "OperationDescription");
        public static readonly ClassType VoidResponse = new ClassType("com.microsoft.rest.v2", "VoidResponse");
        public static readonly ClassType StreamResponse = new ClassType("com.microsoft.rest.v2", "StreamResponse");
        public static readonly ClassType Context = new ClassType("com.microsoft.rest.v2", "Context");

        public ClassType(string package, string name, IEnumerable<string> implementationImports = null, IDictionary<string,string> extensions = null, bool isInnerModelType = false, Func<string,string> defaultValueExpressionConverter = null)
        {
            Package = package;
            Name = name;
            ImplementationImports = implementationImports;
            Extensions = extensions;
            IsInnerModelType = isInnerModelType;
            DefaultValueExpressionConverter = defaultValueExpressionConverter;
        }

        public string Package { get; }

        public string Name { get; }

        private IEnumerable<string> ImplementationImports { get; }

        private IDictionary<string,string> Extensions { get; }

        public string GetExtensionValue(string extensionKey)
        {
            return Extensions == null || !Extensions.ContainsKey(extensionKey) ? null : Extensions[extensionKey];
        }

        public bool IsInnerModelType { get; }

        private Func<string,string> DefaultValueExpressionConverter { get; }

        public override string ToString()
        {
            return Name;
        }

        public IType AsNullable()
        {
            return this;
        }

        public bool Contains(IType type)
        {
            return this == type;
        }

        public string FullName => $"{Package}.{Name}";

        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            if (Package != "java.lang")
            {
                imports.Add($"{Package}.{Name}");
            }

            if (includeImplementationImports && ImplementationImports != null)
            {
                foreach (string implementationImport in ImplementationImports)
                {
                    imports.Add(implementationImport);
                }
            }
        }

        public string DefaultValueExpression(string sourceExpression)
        {
            string result = sourceExpression;
            if (result != null)
            {
                if (DefaultValueExpressionConverter != null)
                {
                    result = DefaultValueExpressionConverter(sourceExpression);
                }
                else
                {
                    result = $"new {ToString()}()";
                }
            }
            return result;
        }

        public IType ClientType
        {
            get
            {
                IType clientType = this;
                if (this == ClassType.DateTimeRfc1123)
                {
                    clientType = ClassType.DateTime;
                }
                else if (this == ClassType.Base64Url)
                {
                    clientType = ArrayType.ByteArray;
                }
                return clientType;
            }
        }

        public string ConvertToClientType(string expression)
        {
            if (this == ClassType.DateTimeRfc1123)
            {
                expression = $"{expression}.dateTime()";
            }
            else if (this == ClassType.Base64Url)
            {
                expression = $"{expression}.decodedBytes()";
            }

            return expression;
        }

        public string ConvertFromClientType(string expression)
        {
            if (this == ClassType.DateTimeRfc1123)
            {
                expression = $"new DateTimeRfc1123({expression})";
            }
            else if (this == ClassType.Base64Url)
            {
                expression = $"Base64Url.encode({expression})";
            }

            return expression;
        }
    }
}
