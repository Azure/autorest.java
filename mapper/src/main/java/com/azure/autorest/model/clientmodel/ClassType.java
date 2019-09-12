// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 The details of a class type that is used by a client.
*/
public class ClassType implements IType {
    public static final ClassType Void = new ClassType("java.lang", "Void");
    public static final ClassType Boolean = new ClassType("java.lang", "Boolean", null, null, false, java.lang.String::toLowerCase);
    public static final ClassType Byte = new ClassType("java.lang", "Byte");
    public static final ClassType Integer = new ClassType("java.lang", "Integer", null, null, false, (String defaultValueExpression) -> defaultValueExpression);
    public static final ClassType Long = new ClassType("java.lang", "Long", null, null, false, (String defaultValueExpression) -> defaultValueExpression + 'L');
    public static final ClassType Double = new ClassType("java.lang", "Double", null, null, false, (String defaultValueExpression) -> java.lang.String.valueOf(java.lang.Double.parseDouble(defaultValueExpression)));
    public static final ClassType String = new ClassType("java.lang", "String", null, null, false, (String defaultValueExpression) -> "\"" + defaultValueExpression + "\"");
    public static final ClassType Base64Url = new ClassType("com.azure.core.implementation", "Base64Url");
    public static final ClassType LocalDate = new ClassType("java.time", "LocalDate", null, null, false, (String defaultValueExpression) -> java.lang.String.format("LocalDate.parse(\"%1$s\")", defaultValueExpression));
    public static final ClassType DateTime = new ClassType("java.time", "OffsetDateTime", null, null, false, (String defaultValueExpression) -> java.lang.String.format("OffsetDateTime.parse(\"%1$s\")", defaultValueExpression));
    public static final ClassType Duration = new ClassType("java.time", "Duration", null, null, false, (String defaultValueExpression) -> java.lang.String.format("Duration.parse(\"%1$s\")", defaultValueExpression));
    public static final ClassType DateTimeRfc1123 = new ClassType("com.azure.core.implementation", "DateTimeRfc1123", null, null, false, (String defaultValueExpression) -> java.lang.String.format("new DateTimeRfc1123(\"%1$s\")", defaultValueExpression));
    public static final ClassType BigDecimal = new ClassType("java.math", "BigDecimal", null, null, false, (String defaultValueExpression) -> java.lang.String.format("new BigDecimal(\"%1$s\")", defaultValueExpression));
    public static final ClassType UUID = new ClassType("java.util", "UUID");
    public static final ClassType Object = new ClassType("java.lang", "Object");
    public static final ClassType ServiceClientCredentials = new ClassType("com.azure.core.credentials", "ServiceClientCredentials");
    public static final ClassType AzureTokenCredentials = new ClassType("com.microsoft.azure.v3.credentials", "AzureTokenCredentials");
    public static final ClassType CloudException = new ClassType("com.microsoft.azure.v3", "CloudException");
    public static final ClassType RestException = new ClassType("com.microsoft.azure.v3", "RestException");
    public static final ClassType UnixTime = new ClassType("com.azure.core.implementation", "UnixTime");
    public static final ClassType UnixTimeDateTime = new ClassType("java.time", "OffsetDateTime");
    public static final ClassType UnixTimeLong = new ClassType("java.lang", "Long");
    public static final ClassType AzureEnvironment = new ClassType("com.microsoft.azure.v3", "AzureEnvironment");
    public static final ClassType HttpPipeline = new ClassType("com.azure.core.http", "HttpPipeline");
    public static final ClassType AzureProxy = new ClassType("com.microsoft.azure.v3", "AzureProxy");
    public static final ClassType RestProxy = new ClassType("com.azure.core.implementation", "RestProxy");
    public static final ClassType Validator = new ClassType("com.azure.core.implementation", "Validator");
    public static final ClassType Function = new ClassType("io.reactivex.functions", "Function");
    public static final ClassType ByteBuffer = new ClassType("java.nio", "ByteBuffer");
    public static final ClassType Resource = new ClassType("com.microsoft.azure.v3", "Resource");
    public static final ClassType ProxyResource = new ClassType("com.microsoft.azure.v3", "ProxyResource");
    public static final ClassType SubResource = new ClassType("com.microsoft.azure.v3", "SubResource");
    public static final ClassType URL = new ClassType("java.net", "URL");
    public static final ClassType NonNull = new ClassType("reactor.util.annotation", "NonNull");
    public static final ClassType OperationDescription = new ClassType("com.azure.core.implementation", "OperationDescription");
    public static final ClassType VoidResponse = new ClassType("com.azure.core.http.rest", "VoidResponse");
    public static final ClassType StreamResponse = new ClassType("com.azure.core.http.rest", "StreamResponse");
    public static final ClassType Context = new ClassType("com.azure.core.util", "Context");


    public ClassType(String package_Keyword, String name, List<String> implementationImports, java.util.Map<String, String> extensions, boolean isInnerModelType) {
        this(package_Keyword, name, implementationImports, extensions, isInnerModelType, null);
    }

    public ClassType(String package_Keyword, String name, List<String> implementationImports, java.util.Map<String, String> extensions) {
        this(package_Keyword, name, implementationImports, extensions, false, null);
    }

    public ClassType(String package_Keyword, String name, List<String> implementationImports) {
        this(package_Keyword, name, implementationImports, null, false, null);
    }

    public ClassType(String package_Keyword, String name) {
        this(package_Keyword, name, null, null, false, null);
    }

    //C# TO JAVA CONVERTER NOTE: Java does not support optional parameters. Overloaded method(s) are created above:
//ORIGINAL LINE: public ClassType(string package, string name, IEnumerable<string> implementationImports = null, IDictionary<string,string> extensions = null, bool isInnerModelType = false, Func<string,string> defaultValueExpressionConverter = null)
    public ClassType(String package_Keyword, String name, List<String> implementationImports, Map<String, String> extensions, boolean isInnerModelType, java.util.function.Function<String, String> defaultValueExpressionConverter) {
        Package = package_Keyword;
        Name = name;
        ImplementationImports = implementationImports;
        Extensions = extensions;
        IsInnerModelType = isInnerModelType;
        DefaultValueExpressionConverter = defaultValueExpressionConverter::apply;
    }

    private String Package;

    public final String getPackage() {
        return Package;
    }

    private String Name;

    public final String getName() {
        return Name;
    }

    private List<String> ImplementationImports;

    private List<String> getImplementationImports() {
        return ImplementationImports;
    }

    private Map<String, String> Extensions;

    private Map<String, String> getExtensions() {
        return Extensions;
    }

    public final String GetExtensionValue(String extensionKey) {
        return getExtensions() == null || !getExtensions().containsKey(extensionKey) ? null : getExtensions().get(extensionKey);
    }

    private boolean IsInnerModelType;

    public final boolean getIsInnerModelType() {
        return IsInnerModelType;
    }

    private java.util.function.Function<String, String> DefaultValueExpressionConverter;

    private java.util.function.Function<String, String> getDefaultValueExpressionConverter() {
        return DefaultValueExpressionConverter;
    }

    @Override
    public String toString() {
        return getName();
    }

    public final IType AsNullable() {
        return this;
    }

    public final boolean Contains(IType type) {
        return this == type;
    }

    public final String getFullName() {
        return java.lang.String.format("%1$s.%2$s", getPackage(), getName());
    }

    public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (!getPackage().equals("java.lang")) {
            imports.add(java.lang.String.format("%1$s.%2$s", getPackage(), getName()));
        }

        if (includeImplementationImports && getImplementationImports() != null) {
            for (String implementationImport : getImplementationImports()) {
                imports.add(implementationImport);
            }
        }
    }

    public final String DefaultValueExpression(String sourceExpression) {
        String result = sourceExpression;
        if (result != null) {
            if (getDefaultValueExpressionConverter() != null) {
                result = DefaultValueExpressionConverter.apply(sourceExpression);
            } else {
                result = java.lang.String.format("new %1$s()", toString());
            }
        }
        return result;
    }

    public final IType getClientType() {
        IType clientType = this;
        if (this == ClassType.DateTimeRfc1123) {
            clientType = ClassType.DateTime;
        } else if (this == ClassType.Base64Url) {
            clientType = ArrayType.ByteArray;
        }
        return clientType;
    }

    public String ConvertToClientType(String expression) {
        if (this == ClassType.DateTimeRfc1123) {
            expression = java.lang.String.format("%s.dateTime()", expression);
        } else if (this == ClassType.Base64Url) {
            expression = java.lang.String.format("%s.decodedBytes()", expression);
        }

        return expression;
    }

    public String ConvertFromClientType(String expression) {
        if (this == ClassType.DateTimeRfc1123) {
            expression = java.lang.String.format("new DateTimeRfc1123(%s)", expression);
        } else if (this == ClassType.Base64Url) {
            expression = java.lang.String.format("Base64Url.encode(%s)", expression);
        }

        return expression;
    }
}