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
    public static final ClassType void = new ClassType("java.lang", "Void");
    public static final ClassType boolean = new ClassType("java.lang", "Boolean", null, null, false, java.lang.String::toLowerCase);
    public static final ClassType byte = new ClassType("java.lang", "Byte");
    public static final ClassType integer = new ClassType("java.lang", "Integer", null, null, false, (String defaultValueExpression) -> defaultValueExpression);
    public static final ClassType long = new ClassType("java.lang", "Long", null, null, false, (String defaultValueExpression) -> defaultValueExpression + 'L');
    public static final ClassType double = new ClassType("java.lang", "Double", null, null, false, (String defaultValueExpression) -> java.lang.String.valueOf(java.lang.Double.parseDouble(defaultValueExpression)));
    public static final ClassType string = new ClassType("java.lang", "String", null, null, false, (String defaultValueExpression) -> "\"" + defaultValueExpression + "\"");
    public static final ClassType base64Url = new ClassType("com.azure.core.implementation", "Base64Url");
    public static final ClassType localDate = new ClassType("java.time", "LocalDate", null, null, false, (String defaultValueExpression) -> String.format("LocalDate.parse(\"%1$s\")", defaultValueExpression));
    public static final ClassType dateTime = new ClassType("java.time", "OffsetDateTime", null, null, false, (String defaultValueExpression) -> String.format("OffsetDateTime.parse(\"%1$s\")", defaultValueExpression));
    public static final ClassType duration = new ClassType("java.time", "Duration", null, null, false, (String defaultValueExpression) -> String.format("Duration.parse(\"%1$s\")", defaultValueExpression));
    public static final ClassType dateTimeRfc1123 = new ClassType("com.azure.core.implementation", "DateTimeRfc1123", null, null, false, (String defaultValueExpression) -> String.format("new DateTimeRfc1123(\"%1$s\")", defaultValueExpression));
    public static final ClassType bigDecimal = new ClassType("java.math", "BigDecimal", null, null, false, (String defaultValueExpression) -> String.format("new BigDecimal(\"%1$s\")", defaultValueExpression));
    public static final ClassType uUID = new ClassType("java.util", "UUID");
    public static final ClassType object = new ClassType("java.lang", "Object");
    public static final ClassType serviceClientCredentials = new ClassType("com.azure.core.credentials", "ServiceClientCredentials");
    public static final ClassType azureTokenCredentials = new ClassType("com.microsoft.azure.v3.credentials", "AzureTokenCredentials");
    public static final ClassType cloudException = new ClassType("com.microsoft.azure.v3", "CloudException");
    public static final ClassType restException = new ClassType("com.microsoft.azure.v3", "RestException");
    public static final ClassType unixTime = new ClassType("com.azure.core.implementation", "UnixTime");
    public static final ClassType unixTimeDateTime = new ClassType("java.time", "OffsetDateTime");
    public static final ClassType unixTimeLong = new ClassType("java.lang", "Long");
    public static final ClassType azureEnvironment = new ClassType("com.microsoft.azure.v3", "AzureEnvironment");
    public static final ClassType httpPipeline = new ClassType("com.azure.core.http", "HttpPipeline");
    public static final ClassType azureProxy = new ClassType("com.microsoft.azure.v3", "AzureProxy");
    public static final ClassType restProxy = new ClassType("com.azure.core.implementation", "RestProxy");
    public static final ClassType validator = new ClassType("com.azure.core.implementation", "Validator");
    public static final ClassType function = new ClassType("io.reactivex.functions", "Function");
    public static final ClassType byteBuffer = new ClassType("java.nio", "ByteBuffer");
    public static final ClassType resource = new ClassType("com.microsoft.azure.v3", "Resource");
    public static final ClassType proxyResource = new ClassType("com.microsoft.azure.v3", "ProxyResource");
    public static final ClassType subResource = new ClassType("com.microsoft.azure.v3", "SubResource");
    public static final ClassType uRL = new ClassType("java.net", "URL");
    public static final ClassType nonNull = new ClassType("reactor.util.annotation", "NonNull");
    public static final ClassType operationDescription = new ClassType("com.azure.core.implementation", "OperationDescription");
    public static final ClassType voidResponse = new ClassType("com.azure.core.http.rest", "VoidResponse");
    public static final ClassType streamResponse = new ClassType("com.azure.core.http.rest", "StreamResponse");
    public static final ClassType context = new ClassType("com.azure.core.util", "Context");


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
        return String.format("%1$s.%2$s", getPackage(), getName());
    }

    public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (!getPackage().equals("java.lang")) {
            imports.add(String.format("%1$s.%2$s", getPackage(), getName()));
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
                result = String.format("new %1$s()", toString());
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
            expression = String.format("%s.dateTime()", expression);
        } else if (this == ClassType.Base64Url) {
            expression = String.format("%s.decodedBytes()", expression);
        }

        return expression;
    }

    public String ConvertFromClientType(String expression) {
        if (this == ClassType.DateTimeRfc1123) {
            expression = String.format("new DateTimeRfc1123(%s)", expression);
        } else if (this == ClassType.Base64Url) {
            expression = String.format("Base64Url.encode(%s)", expression);
        }

        return expression;
    }
}