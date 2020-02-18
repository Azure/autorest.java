// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;


import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The details of a class type that is used by a client.
 */
public class ClassType implements IType {
    public static final ClassType Void = new ClassType("java.lang", "Void");
    public static final ClassType Boolean = new ClassType("java.lang", "Boolean", null, null, false, java.lang.String::toLowerCase);
    public static final ClassType Byte = new ClassType("java.lang", "Byte");
    public static final ClassType Integer = new ClassType("java.lang", "Integer", null, null, false, (String defaultValueExpression) -> defaultValueExpression);
    public static final ClassType Long = new ClassType("java.lang", "Long", null, null, false, (String defaultValueExpression) -> defaultValueExpression + 'L');
    public static final ClassType Float = new ClassType("java.lang", "Float", null, null, false, (String defaultValueExpression) -> java.lang.String.valueOf(java.lang.Float.parseFloat(defaultValueExpression)));
    public static final ClassType Double = new ClassType("java.lang", "Double", null, null, false, (String defaultValueExpression) -> java.lang.String.valueOf(java.lang.Double.parseDouble(defaultValueExpression)));
    public static final ClassType Character = new ClassType("java.lang", "Character", null, null, false, (String defaultValueExpression) -> java.lang.String.valueOf((defaultValueExpression.charAt(0))));
    public static final ClassType String = new ClassType("java.lang", "String", null, null, false, (String defaultValueExpression) -> "\"" + defaultValueExpression + "\"");
    public static final ClassType Base64Url = new ClassType("com.azure.core.util", "Base64Url");
    public static final ClassType LocalDate = new ClassType("java.time", "LocalDate", null, null, false, (String defaultValueExpression) -> java.lang.String.format("LocalDate.parse(\"%1$s\")", defaultValueExpression));
    public static final ClassType DateTime = new ClassType("java.time", "OffsetDateTime", null, null, false, (String defaultValueExpression) -> java.lang.String.format("OffsetDateTime.parse(\"%1$s\")", defaultValueExpression));
    public static final ClassType Duration = new ClassType("java.time", "Duration", null, null, false, (String defaultValueExpression) -> java.lang.String.format("Duration.parse(\"%1$s\")", defaultValueExpression));
    public static final ClassType DateTimeRfc1123 = new ClassType("com.azure.core.util", "DateTimeRfc1123", null, null, false, (String defaultValueExpression) -> java.lang.String.format("new DateTimeRfc1123(\"%1$s\")", defaultValueExpression));
    public static final ClassType BigDecimal = new ClassType("java.math", "BigDecimal", null, null, false, (String defaultValueExpression) -> java.lang.String.format("new BigDecimal(\"%1$s\")", defaultValueExpression));
    public static final ClassType UUID = new ClassType("java.util", "UUID");
    public static final ClassType Object = new ClassType("java.lang", "Object");
    public static final ClassType TokenCredential = new ClassType("com.azure.core.credential", "TokenCredential");
    public static final ClassType AzureTokenCredentials = new ClassType("com.microsoft.azure.v3.credentials", "AzureTokenCredentials");
    public static final ClassType CloudException = new ClassType("com.azure.core.management", "CloudException");
    public static final ClassType HttpResponseException = new ClassType("com.azure.core.exception", "HttpResponseException");
    public static final ClassType UnixTime = new ClassType("com.azure.core.implementation", "UnixTime");
    public static final ClassType UnixTimeDateTime = new ClassType("java.time", "OffsetDateTime");
    public static final ClassType UnixTimeLong = new ClassType("java.lang", "Long");
    public static final ClassType AzureEnvironment = new ClassType("com.azure.core.management", "AzureEnvironment");
    public static final ClassType HttpPipeline = new ClassType("com.azure.core.http", "HttpPipeline");
    public static final ClassType AzureProxy = new ClassType("com.microsoft.azure.management", "AzureProxy");
    public static final ClassType RestProxy = new ClassType("com.azure.core.http.rest", "RestProxy");
    public static final ClassType Function = new ClassType("io.reactivex.functions", "Function");
    public static final ClassType ByteBuffer = new ClassType("java.nio", "ByteBuffer");
    public static final ClassType Resource = new ClassType("com.azure.core.management", "Resource");
    public static final ClassType ProxyResource = new ClassType("com.azure.core.management", "ProxyResource");
    public static final ClassType SubResource = new ClassType("com.azure.core.management", "SubResource");
    public static final ClassType URL = new ClassType("java.net", "URL");
    public static final ClassType NonNull = new ClassType("reactor.util.annotation", "NonNull");
    public static final ClassType OperationDescription = new ClassType("com.azure.core.http.rest", "OperationDescription");
    public static final ClassType StreamResponse = new ClassType("com.azure.core.http.rest", "StreamResponse");
    public static final ClassType InputStream = new ClassType("java.io", "InputStream");
    public static final ClassType Context = new ClassType("com.azure.core.util", "Context");
    private String packageName;
    private String name;
    private List<String> implementationImports;
    private XmsExtensions extensions;
    private boolean isInnerModelType;
    private java.util.function.Function<String, String> defaultValueExpressionConverter;

    public ClassType(String package_Keyword, String name, List<String> implementationImports, XmsExtensions extensions, boolean isInnerModelType) {
        this(package_Keyword, name, implementationImports, extensions, isInnerModelType, null);
    }

    public ClassType(String package_Keyword, String name, List<String> implementationImports, XmsExtensions extensions) {
        this(package_Keyword, name, implementationImports, extensions, false, null);
    }

    public ClassType(String package_Keyword, String name, List<String> implementationImports) {
        this(package_Keyword, name, implementationImports, null, false, null);
    }

    public ClassType(String package_Keyword, String name) {
        this(package_Keyword, name, null, null, false, null);
    }

    public ClassType(String package_Keyword, String name, List<String> implementationImports, XmsExtensions extensions, boolean isInnerModelType, java.util.function.Function<String, String> defaultValueExpressionConverter) {
        packageName = package_Keyword;
        this.name = name;
        this.implementationImports = implementationImports;
        this.extensions = extensions;
        this.isInnerModelType = isInnerModelType;
        this.defaultValueExpressionConverter = defaultValueExpressionConverter;
    }

    public final String getPackage() {
        return packageName;
    }

    public final String getName() {
        return name;
    }

    private List<String> getImplementationImports() {
        return implementationImports;
    }

    public XmsExtensions getExtensions() {
        return extensions;
    }

    public final boolean getIsInnerModelType() {
        return isInnerModelType;
    }

    private java.util.function.Function<String, String> getDefaultValueExpressionConverter() {
        return defaultValueExpressionConverter;
    }

    public final boolean isBoxedType() {
        return this.equals(ClassType.Void)
                || this.equals(ClassType.Boolean)
                || this.equals(ClassType.Byte)
                || this.equals(ClassType.Integer)
                || this.equals(ClassType.Long)
                || this.equals(ClassType.Double);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassType)) {
            return false;
        }
        ClassType that = (ClassType) other;
        return Objects.equals(this.name, that.name) && Objects.equals(this.packageName, that.packageName);
    }

    public final IType asNullable() {
        return this;
    }

    public final boolean contains(IType type) {
        return this.equals(type);
    }

    public final String getFullName() {
        return java.lang.String.format("%1$s.%2$s", getPackage(), getName());
    }

    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (!getPackage().equals("java.lang")) {
            imports.add(java.lang.String.format("%1$s.%2$s", getPackage(), getName()));
        }

        if (includeImplementationImports && getImplementationImports() != null) {
            for (String implementationImport : getImplementationImports()) {
                imports.add(implementationImport);
            }
        }
    }

    public final String defaultValueExpression(String sourceExpression) {
        String result = sourceExpression;
        if (result != null) {
            if (getDefaultValueExpressionConverter() != null) {
                result = defaultValueExpressionConverter.apply(sourceExpression);
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

    public String convertToClientType(String expression) {
        if (this == ClassType.DateTimeRfc1123) {
            expression = java.lang.String.format("%s.getDateTime()", expression);
        } else if (this == ClassType.Base64Url) {
            expression = java.lang.String.format("%s.decodedBytes()", expression);
        }

        return expression;
    }

    public String convertFromClientType(String expression) {
        if (this == ClassType.DateTimeRfc1123) {
            expression = java.lang.String.format("new DateTimeRfc1123(%s)", expression);
        } else if (this == ClassType.Base64Url) {
            expression = java.lang.String.format("Base64Url.encode(%s)", expression);
        }

        return expression;
    }

    public String validate(java.lang.String expression) {
        if (packageName.startsWith(JavaSettings.getInstance().getPackage())) {
            return expression + ".validate()";
        } else {
            return null;
        }
    }
}
