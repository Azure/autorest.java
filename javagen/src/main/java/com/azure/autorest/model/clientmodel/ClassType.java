// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;


import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The details of a class type that is used by a client.
 */
public class ClassType implements IType {
    public static final ClassType Void = new ClassType.Builder().knownClass(java.lang.Void.class).build();
    public static final ClassType Boolean = new ClassType.Builder().knownClass(java.lang.Boolean.class).defaultValueExpressionConverter(java.lang.String::toLowerCase).build();
    public static final ClassType Byte = new ClassType.Builder().knownClass(java.lang.Byte.class).build();
    public static final ClassType Integer = new ClassType.Builder().knownClass(java.lang.Integer.class).defaultValueExpressionConverter((String defaultValueExpression) -> defaultValueExpression).build();
    public static final ClassType Long = new ClassType.Builder().knownClass(java.lang.Long.class).defaultValueExpressionConverter((String defaultValueExpression) -> defaultValueExpression + 'L').build();
    public static final ClassType Float = new ClassType.Builder().knownClass(java.lang.Float.class).defaultValueExpressionConverter((String defaultValueExpression) -> java.lang.String.valueOf(java.lang.Float.parseFloat(defaultValueExpression))).build();
    public static final ClassType Double = new ClassType.Builder().knownClass(java.lang.Double.class).defaultValueExpressionConverter((String defaultValueExpression) -> java.lang.String.valueOf(java.lang.Double.parseDouble(defaultValueExpression))).build();
    public static final ClassType Character = new ClassType.Builder().knownClass(java.lang.Character.class).defaultValueExpressionConverter((String defaultValueExpression) -> java.lang.String.valueOf((defaultValueExpression.charAt(0)))).build();
    public static final ClassType String = new ClassType.Builder().knownClass(java.lang.String.class).defaultValueExpressionConverter((String defaultValueExpression) -> "\"" + defaultValueExpression + "\"").build();
    public static final ClassType Base64Url = new ClassType.Builder().knownClass(com.azure.core.util.Base64Url.class).build();
    public static final ClassType LocalDate = new ClassType.Builder().knownClass(java.time.LocalDate.class).defaultValueExpressionConverter((String defaultValueExpression) -> java.lang.String.format("LocalDate.parse(\"%1$s\")", defaultValueExpression)).build();
    public static final ClassType DateTime = new ClassType.Builder().knownClass(java.time.OffsetDateTime.class).defaultValueExpressionConverter((String defaultValueExpression) -> java.lang.String.format("OffsetDateTime.parse(\"%1$s\")", defaultValueExpression)).build();
    public static final ClassType Duration = new ClassType.Builder().knownClass(java.time.Duration.class).defaultValueExpressionConverter((String defaultValueExpression) -> java.lang.String.format("Duration.parse(\"%1$s\")", defaultValueExpression)).build();
    public static final ClassType DateTimeRfc1123 = new ClassType.Builder().knownClass(com.azure.core.util.DateTimeRfc1123.class).defaultValueExpressionConverter((String defaultValueExpression) -> java.lang.String.format("new DateTimeRfc1123(\"%1$s\")", defaultValueExpression)).build();
    public static final ClassType BigDecimal = new ClassType.Builder().knownClass(java.math.BigDecimal.class).defaultValueExpressionConverter((String defaultValueExpression) -> java.lang.String.format("new BigDecimal(\"%1$s\")", defaultValueExpression)).build();
    public static final ClassType UUID = new ClassType.Builder().knownClass(java.util.UUID.class).build();
    public static final ClassType Object = new ClassType.Builder().knownClass(java.lang.Object.class).build();
    public static final ClassType TokenCredential = new ClassType.Builder().knownClass(com.azure.core.credential.TokenCredential.class).build();
    public static final ClassType HttpResponseException = new ClassType.Builder().knownClass(com.azure.core.exception.HttpResponseException.class).build();
    public static final ClassType UnixTime = new ClassType.Builder().knownClass(com.azure.core.implementation.UnixTime.class).build();
    public static final ClassType UnixTimeDateTime = new ClassType.Builder().knownClass(java.time.OffsetDateTime.class).build();
    public static final ClassType UnixTimeLong = new ClassType.Builder().knownClass(java.lang.Long.class).build();
    public static final ClassType HttpPipeline = new ClassType.Builder().knownClass(com.azure.core.http.HttpPipeline.class).build();
    public static final ClassType RestProxy = new ClassType.Builder().knownClass(com.azure.core.http.rest.RestProxy.class).build();
    public static final ClassType SerializerAdapter = new ClassType.Builder().knownClass(com.azure.core.util.serializer.SerializerAdapter.class).build();
    public static final ClassType Function = new ClassType.Builder().knownClass(java.util.function.Function.class).build();
    public static final ClassType ByteBuffer = new ClassType.Builder().knownClass(java.nio.ByteBuffer.class).build();
    public static final ClassType URL = new ClassType.Builder().knownClass(java.net.URL.class).build();
    public static final ClassType NonNull = new ClassType.Builder().knownClass(reactor.util.annotation.NonNull.class).build();
    public static final ClassType StreamResponse = new ClassType.Builder().knownClass(com.azure.core.http.rest.StreamResponse.class).build();
    public static final ClassType InputStream = new ClassType.Builder().knownClass(java.io.InputStream.class).build();
    public static final ClassType Context = new ClassType.Builder().knownClass(com.azure.core.util.Context.class).build();
    public static final ClassType ClientLogger = new ClassType.Builder().knownClass(com.azure.core.util.logging.ClientLogger.class).build();
    public static final ClassType AzureEnvironment = new ClassType.Builder().packageName("com.azure.core.management").name("AzureEnvironment").build();
    public static final ClassType HttpClient = new ClassType.Builder().knownClass(com.azure.core.http.HttpClient.class).build();
    public static final ClassType HttpPipelinePolicy = new ClassType.Builder().knownClass(com.azure.core.http.policy.HttpPipelinePolicy.class).build();
    public static final ClassType HttpLogOptions = new ClassType.Builder().knownClass(com.azure.core.http.policy.HttpLogOptions.class).build();
    public static final ClassType Configuration = new ClassType.Builder().knownClass(com.azure.core.util.Configuration.class).build();
    public static final ClassType ServiceVersion = new ClassType.Builder().knownClass(com.azure.core.util.ServiceVersion.class).build();
    public static final ClassType AzureKeyCredential = new ClassType.Builder().knownClass(com.azure.core.credential.AzureKeyCredential.class).build();
    public static final ClassType RetryPolicy = new ClassType.Builder().knownClass(com.azure.core.http.policy.RetryPolicy.class).build();
    public static final ClassType JsonPatchDocument =
            new ClassType.Builder().knownClass(com.azure.core.models.JsonPatchDocument.class).build();
    public static final ClassType ObjectSerializer = new ClassType.Builder().knownClass(com.azure.core.util.serializer.ObjectSerializer.class).build();


    private final String packageName;
    private final String name;
    private final List<String> implementationImports;
    private final XmsExtensions extensions;
    private final java.util.function.Function<String, String> defaultValueExpressionConverter;

    private ClassType(String package_Keyword, String name, List<String> implementationImports, XmsExtensions extensions, java.util.function.Function<String, String> defaultValueExpressionConverter) {
        packageName = package_Keyword;
        this.name = name;
        this.implementationImports = implementationImports;
        this.extensions = extensions;
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

    @Override
    public int hashCode() {
        return Objects.hash(packageName, name);
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

    public static class Builder {
        private String packageName;
        private String name;
        private List<String> implementationImports;
        private XmsExtensions extensions;
        private java.util.function.Function<String, String> defaultValueExpressionConverter;

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder knownClass(Class<?> clazz) {
            return packageName(clazz.getPackage().getName())
                    .name(clazz.getSimpleName());
        }

        public Builder implementationImports(String... implementationImports) {
            this.implementationImports = Arrays.asList(implementationImports);
            return this;
        }

        public Builder extensions(XmsExtensions extensions) {
            this.extensions = extensions;
            return this;
        }

        public Builder defaultValueExpressionConverter(java.util.function.Function<String, String> defaultValueExpressionConverter) {
            this.defaultValueExpressionConverter = defaultValueExpressionConverter;
            return this;
        }

        public ClassType build() {
            return new ClassType(packageName, name, implementationImports, extensions, defaultValueExpressionConverter);
        }
    }
}
