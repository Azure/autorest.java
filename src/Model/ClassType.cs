// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    public class ClassType : IType
    {
        public static readonly ClassType Void = new ClassType("java.lang", "Void", null, null, false);
        public static readonly ClassType Boolean = new ClassType("java.lang", "Boolean", null, null, false);
        public static readonly ClassType Byte = new ClassType("java.lang", "Byte", null, null, false);
        public static readonly ClassType Integer = new ClassType("java.lang", "Integer", null, null, false);
        public static readonly ClassType Long = new ClassType("java.lang", "Long", null, null, false);
        public static readonly ClassType Double = new ClassType("java.lang", "Double", null, null, false);
        public static readonly ClassType String = new ClassType("java.lang", "String", null, null, false);
        public static readonly ClassType Base64Url = new ClassType("com.microsoft.rest.v2", "Base64Url", null, null, false);
        public static readonly ClassType LocalDate = new ClassType("org.joda.time", "LocalDate", null, null, false);
        public static readonly ClassType DateTime = new ClassType("org.joda.time", "DateTime", null, null, false);
        public static readonly ClassType DateTimeRfc1123 = new ClassType("com.microsoft.rest.v2", "DateTimeRfc1123", null, null, false);
        public static readonly ClassType BigDecimal = new ClassType("java.math", "BigDecimal", null, null, false);
        public static readonly ClassType Period = new ClassType("org.joda.time", "Period", null, null, false);
        public static readonly ClassType UUID = new ClassType("java.util", "UUID", null, null, false);
        public static readonly ClassType Object = new ClassType("java.lang", "Object", null, null, false);
        public static readonly ClassType ServiceClientCredentials = new ClassType("com.microsoft.rest.v2.credentials", "ServiceClientCredentials", null, null, false);
        public static readonly ClassType AzureTokenCredentials = new ClassType("com.microsoft.azure.v2.credentials", "AzureTokenCredentials", null, null, false);
        public static readonly ClassType CloudException = new ClassType("com.microsoft.azure.v2", "CloudException", null, null, false);
        public static readonly ClassType RestException = new ClassType("com.microsoft.azure.v2", "RestException", null, null, false);
        public static readonly ClassType UnixTime = new ClassType("com.microsoft.rest.v2", "UnixTime", null, null, false);
        public static readonly ClassType UnixTimeDateTime = new ClassType("org.joda.time", "DateTime", new[] { "org.joda.time.DateTimeZone" }, null, false);
        public static readonly ClassType UnixTimeLong = new ClassType("java.lang", "Long", null, null, false);
        public static readonly ClassType AzureEnvironment = new ClassType("com.microsoft.azure.v2", "AzureEnvironment", null, null, false);
        public static readonly ClassType HttpPipeline = new ClassType("com.microsoft.rest.v2.http", "HttpPipeline", null, null, false);
        public static readonly ClassType Completable = new ClassType("io.reactivex", "Completable", null, null, false);
        public static readonly ClassType AzureProxy = new ClassType("com.microsoft.azure.v2", "AzureProxy", null, null, false);
        public static readonly ClassType RestProxy = new ClassType("com.microsoft.rest.v2", "RestProxy", null, null, false);
        public static readonly ClassType Validator = new ClassType("com.microsoft.rest.v2", "Validator", null, null, false);
        public static readonly ClassType Function = new ClassType("io.reactivex.functions", "Function", null, null, false);
        public static readonly ClassType ByteBuffer = new ClassType("java.nio", "ByteBuffer", null, null, false);
        public static readonly ClassType Resource = new ClassType("com.microsoft.azure.v2", "Resource", null, null, false);
        public static readonly ClassType SubResource = new ClassType("com.microsoft.azure.v2", "SubResource", null, null, false);
        public static readonly ClassType URL = new ClassType("java.net", "URL", null, null, false);
        
        public ClassType(string package, string name, IEnumerable<string> implementationImports, IDictionary<string,string> extensions, bool isInnerModelType)
        {
            Package = package;
            Name = name;
            ImplementationImports = implementationImports;
            Extensions = extensions;
            IsInnerModelType = isInnerModelType;
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
    }
}
