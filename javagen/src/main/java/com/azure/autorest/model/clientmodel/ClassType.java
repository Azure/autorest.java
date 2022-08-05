// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;


import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The details of a class type that is used by a client.
 */
public class ClassType implements IType {
    public static final ClassType Void = new ClassType.Builder(false).knownClass(Void.class).build();

    public static final ClassType Boolean = new Builder(false).knownClass(Boolean.class)
        .defaultValueExpressionConverter(java.lang.String::toLowerCase)
        .serializationNeedsNullGuarding(false)
        .streamStyleJsonFieldSerializationMethod("writeBooleanField")
        .streamStyleJsonValueSerializationMethod("writeBoolean")
        .streamStyleXmlAttributeSerializationMethod("writeBooleanAttribute")
        .streamStyleXmlElementSerializationMethod("writeBooleanElement")
        .build();

    public static final ClassType Byte = new ClassType.Builder(false).knownClass(Byte.class)
        .streamStyleJsonFieldSerializationMethod("writeIntegerField")
        .streamStyleJsonValueSerializationMethod("writeInteger")
        .streamStyleXmlAttributeSerializationMethod("writeNumberAttribute")
        .streamStyleXmlElementSerializationMethod("writeNumberElement")
        .build();

    public static final ClassType Integer = new ClassType.Builder(false).knownClass(Integer.class)
        .defaultValueExpressionConverter(java.util.function.Function.identity())
        .serializationNeedsNullGuarding(false)
        .streamStyleJsonFieldSerializationMethod("writeIntegerField")
        .streamStyleJsonValueSerializationMethod("writeInteger")
        .streamStyleXmlAttributeSerializationMethod("writeNumberAttribute")
        .streamStyleXmlElementSerializationMethod("writeNumberElement")
        .build();

    public static final ClassType Long = new ClassType.Builder(false).knownClass(Long.class)
        .defaultValueExpressionConverter(defaultValueExpression -> defaultValueExpression + 'L')
        .serializationNeedsNullGuarding(false)
        .streamStyleJsonFieldSerializationMethod("writeLongField")
        .streamStyleJsonValueSerializationMethod("writeLong")
        .streamStyleXmlAttributeSerializationMethod("writeNumberAttribute")
        .streamStyleXmlElementSerializationMethod("writeNumberElement")
        .build();

    public static final ClassType Float = new ClassType.Builder(false).knownClass(Float.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.valueOf(java.lang.Float.parseFloat(defaultValueExpression)) + 'F')
        .serializationNeedsNullGuarding(false)
        .streamStyleJsonFieldSerializationMethod("writeFloatField")
        .streamStyleJsonValueSerializationMethod("writeFloat")
        .streamStyleXmlAttributeSerializationMethod("writeNumberAttribute")
        .streamStyleXmlElementSerializationMethod("writeNumberElement")
        .build();

    public static final ClassType Double = new ClassType.Builder(false).knownClass(Double.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.valueOf(java.lang.Double.parseDouble(defaultValueExpression)) + 'D')
        .serializationNeedsNullGuarding(false)
        .streamStyleJsonFieldSerializationMethod("writeDoubleField")
        .streamStyleJsonValueSerializationMethod("writeDouble")
        .streamStyleXmlAttributeSerializationMethod("writeNumberAttribute")
        .streamStyleXmlElementSerializationMethod("writeNumberElement")
        .build();

    public static final ClassType Character = new ClassType.Builder(false).knownClass(Character.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.valueOf((defaultValueExpression.charAt(0))))
        .streamStyleJsonFieldSerializationMethod("writeStringField")
        .streamStyleJsonValueSerializationMethod("writeString")
        .streamStyleXmlAttributeSerializationMethod("writeStringAttribute")
        .streamStyleXmlElementSerializationMethod("writeStringElement")
        .build();

    public static final ClassType String = new ClassType.Builder(false).knownClass(String.class)
        .defaultValueExpressionConverter(defaultValueExpression -> "\"" + escapeString(defaultValueExpression) + "\"")
        .serializationNeedsNullGuarding(false)
        .streamStyleJsonFieldSerializationMethod("writeStringField")
        .streamStyleJsonValueSerializationMethod("writeString")
        .streamStyleXmlAttributeSerializationMethod("writeStringAttribute")
        .streamStyleXmlElementSerializationMethod("writeStringElement")
        .build();

    public static final ClassType Base64Url = new ClassType.Builder(false)
        .knownClass(com.azure.core.util.Base64Url.class)
        .build();

    public static final ClassType AndroidBase64Url = new ClassType.Builder(false)
        .packageName("com.azure.android.core.util").name("Base64Url")
        .build();

    public static final ClassType LocalDate = new ClassType.Builder(false).knownClass(java.time.LocalDate.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("LocalDate.parse(\"%1$s\")", defaultValueExpression))
        .streamStyleJsonFieldSerializationMethod("writeStringField")
        .streamStyleJsonValueSerializationMethod("writeString")
        .streamStyleXmlAttributeSerializationMethod("writeStringAttribute")
        .streamStyleXmlElementSerializationMethod("writeStringElement")
        .build();

    public static final ClassType AndroidLocalDate = new ClassType.Builder(false)
        .packageName("org.threeten.bp").name("LocalDate")
        .build();

    public static final ClassType DateTime = new ClassType.Builder(false).knownClass(java.time.OffsetDateTime.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("OffsetDateTime.parse(\"%1$s\")", defaultValueExpression))
        .streamStyleJsonFieldSerializationMethod("writeStringField")
        .streamStyleJsonValueSerializationMethod("writeString")
        .streamStyleXmlAttributeSerializationMethod("writeStringAttribute")
        .streamStyleXmlElementSerializationMethod("writeStringElement")
        .build();

    public static final ClassType Duration = new ClassType.Builder(false).knownClass(java.time.Duration.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("Duration.parse(\"%1$s\")", defaultValueExpression))
        .streamStyleJsonFieldSerializationMethod("writeStringField")
        .streamStyleJsonValueSerializationMethod("writeString")
        .streamStyleXmlAttributeSerializationMethod("writeStringAttribute")
        .streamStyleXmlElementSerializationMethod("writeStringElement")
        .build();

    public static final ClassType AndroidDuration = new ClassType.Builder(false)
        .packageName("org.threeten.bp").name("Duration")
        .build();

    public static final ClassType DateTimeRfc1123 = new ClassType.Builder(false)
        .knownClass(com.azure.core.util.DateTimeRfc1123.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("new DateTimeRfc1123(\"%1$s\")", defaultValueExpression))
        .streamStyleJsonFieldSerializationMethod("writeStringField")
        .streamStyleJsonValueSerializationMethod("writeString")
        .streamStyleXmlAttributeSerializationMethod("writeStringAttribute")
        .streamStyleXmlElementSerializationMethod("writeStringElement")
        .build();

    public static final ClassType AndroidDateTimeRfc1123 = new ClassType.Builder(false)
        .packageName("com.azure.android.core.util").name("DateTimeRfc1123")
        .build();

    public static final ClassType BigDecimal = new ClassType.Builder(false).knownClass(java.math.BigDecimal.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("new BigDecimal(\"%1$s\")", defaultValueExpression))
        .build();

    public static final ClassType UUID = new ClassType.Builder(false).knownClass(java.util.UUID.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("UUID.fromString(\"%1$s\")", defaultValueExpression))
        .streamStyleJsonFieldSerializationMethod("writeStringField")
        .streamStyleJsonValueSerializationMethod("writeString")
        .streamStyleXmlAttributeSerializationMethod("writeStringAttribute")
        .streamStyleXmlElementSerializationMethod("writeStringElement")
        .build();

    public static final ClassType Object = new ClassType.Builder(false)
        .knownClass(java.lang.Object.class)
        .build();

    public static final ClassType TokenCredential = new ClassType.Builder(false)
        .knownClass(com.azure.core.credential.TokenCredential.class)
        .build();

    public static final ClassType AndroidHttpResponseException = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http.exception").name("HttpResponseException")
        .build();

    public static final ClassType UnixTimeDateTime = new ClassType.Builder(false)
        .knownClass(java.time.OffsetDateTime.class)
        .build();

    public static final ClassType AndroidDateTime = new ClassType.Builder(false)
        .packageName("org.threeten.bp").name("OffsetDateTime")
        .build();

    public static final ClassType UnixTimeLong = new ClassType.Builder(false).knownClass(java.lang.Long.class).build();

    public static final ClassType HttpPipeline = new ClassType.Builder(false)
        .knownClass(com.azure.core.http.HttpPipeline.class)
        .build();

    public static final ClassType AndroidHttpPipeline = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http").name("HttpPipeline")
        .build();

    public static final ClassType RestProxy = new ClassType.Builder(false)
        .knownClass(com.azure.core.http.rest.RestProxy.class)
        .build();

    public static final ClassType AndroidRestProxy = new ClassType.Builder(false)
        .packageName("com.azure.android.core.rest").name("RestProxy")
        .build();

    public static final ClassType SerializerAdapter = new ClassType.Builder(false)
        .knownClass(com.azure.core.util.serializer.SerializerAdapter.class)
        .build();

    public static final ClassType AndroidJacksonSerder = new ClassType.Builder(false)
        .packageName("com.azure.android.core.serde.jackson").name("JacksonSerder")
        .build();

    public static final ClassType Function = new ClassType.Builder(false)
        .knownClass(java.util.function.Function.class)
        .build();

    public static final ClassType ByteBuffer = new ClassType.Builder(false)
        .knownClass(java.nio.ByteBuffer.class)
        .build();

    public static final ClassType URL = new ClassType.Builder(false)
        .knownClass(java.net.URL.class)
        .streamStyleJsonFieldSerializationMethod("writeStringField")
        .streamStyleJsonValueSerializationMethod("writeString")
        .streamStyleXmlAttributeSerializationMethod("writeStringAttribute")
        .streamStyleXmlElementSerializationMethod("writeStringElement")
        .build();

    public static final ClassType NonNull = new ClassType.Builder(false)
        .knownClass(reactor.util.annotation.NonNull.class)
        .build();

    public static final ClassType StreamResponse = new ClassType.Builder(false)
        .knownClass(com.azure.core.http.rest.StreamResponse.class)
        .build();

    public static final ClassType InputStream = new ClassType.Builder(false)
        .knownClass(java.io.InputStream.class)
        .build();

    public static final ClassType Context = new Builder(false).knownClass(com.azure.core.util.Context.class)
        .defaultValueExpressionConverter(epr -> "Context.NONE")
        .build();

    public static final ClassType AndroidContext = new ClassType.Builder(false)
        .packageName("com.azure.android.core.util").name("Context")
        .build();

    public static final ClassType ClientLogger = new ClassType.Builder(false)
        .knownClass(com.azure.core.util.logging.ClientLogger.class)
        .build();

    public static final ClassType AzureEnvironment = new ClassType.Builder(false)
        .packageName("com.azure.core.management").name("AzureEnvironment")
        .build();

    public static final ClassType HttpClient = new ClassType.Builder(false)
        .knownClass(com.azure.core.http.HttpClient.class)
        .build();

    public static final ClassType AndroidHttpClient = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http").name("HttpClient")
        .build();

    public static final ClassType HttpPipelinePolicy = new ClassType.Builder(false)
        .knownClass(com.azure.core.http.policy.HttpPipelinePolicy.class)
        .build();

    public static final ClassType AndroidHttpPipelinePolicy = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http").name("HttpPipelinePolicy")
        .build();

    public static final ClassType HttpLogOptions = new ClassType.Builder(false)
        .knownClass(com.azure.core.http.policy.HttpLogOptions.class)
        .build();

    public static final ClassType AndroidHttpLogOptions = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http.policy").name("HttpLogOptions")
        .build();

    public static final ClassType Configuration = new ClassType.Builder(false)
        .knownClass(com.azure.core.util.Configuration.class)
        .build();

    public static final ClassType ServiceVersion = new ClassType.Builder(false)
        .knownClass(com.azure.core.util.ServiceVersion.class)
        .build();

    public static final ClassType AzureKeyCredential = new ClassType.Builder(false)
        .knownClass(com.azure.core.credential.AzureKeyCredential.class)
        .build();

    public static final ClassType RetryPolicy = new ClassType.Builder(false)
        .knownClass(com.azure.core.http.policy.RetryPolicy.class)
        .build();

    public static final ClassType RetryOptions = new ClassType.Builder(false)
        .knownClass(com.azure.core.http.policy.RetryOptions.class)
        .build();

    public static final ClassType AndroidRetryPolicy = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http.policy").name("RetryPolicy")
        .build();

    public static final ClassType JsonPatchDocument = new ClassType.Builder(false)
        .knownClass(com.azure.core.models.JsonPatchDocument.class)
        .build();

    public static final ClassType BinaryData = new ClassType.Builder(false)
        .knownClass(com.azure.core.util.BinaryData.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("BinaryData.fromObject(\"%s\")", defaultValueExpression))
        .build();

    public static final ClassType RequestOptions = new Builder(false)
        .packageName("com.azure.core.http.rest").name("RequestOptions")
        .build();

    public static final ClassType ClientOptions = new Builder(false)
        .knownClass(com.azure.core.util.ClientOptions.class)
        .build();

    public static final ClassType HttpRequest = new Builder(false)
        .knownClass(com.azure.core.http.HttpRequest.class)
        .build();

    public static final ClassType HttpHeaders = new Builder(false)
        .knownClass(com.azure.core.http.HttpHeaders.class)
        .build();


    // Java exception types
    public static final ClassType HttpResponseException = new Builder(false)
        .knownClass(com.azure.core.exception.HttpResponseException.class)
        .build();

    public static final ClassType ClientAuthenticationException = new Builder(false)
        .knownClass(com.azure.core.exception.ClientAuthenticationException.class)
        .build();

    public static final ClassType ResourceExistsException = new Builder(false)
        .knownClass(com.azure.core.exception.ResourceExistsException.class)
        .build();

    public static final ClassType ResourceModifiedException = new Builder(false)
        .knownClass(com.azure.core.exception.ResourceModifiedException.class)
        .build();

    public static final ClassType ResourceNotFoundException = new Builder(false)
        .knownClass(com.azure.core.exception.ResourceNotFoundException.class)
        .build();

    public static final ClassType TooManyRedirectsException = new Builder(false)
        .knownClass(com.azure.core.exception.TooManyRedirectsException.class)
        .build();

    public static final ClassType ResponseError = new Builder()
        .knownClass(com.azure.core.models.ResponseError.class)
        .build();
    public static final ClassType ResponseInnerError = new Builder()
        .packageName("com.azure.core.models").name("ResponseInnerError")
        .build();

    private final String fullName;
    private final String packageName;
    private final String name;
    private final List<String> implementationImports;
    private final XmsExtensions extensions;
    private final java.util.function.Function<String, String> defaultValueExpressionConverter;
    private final boolean isSwaggerType;
    private final boolean serializationNeedsNullGuarding;
    private final String jsonFieldSerializationMethod;
    private final String jsonValueSerializationMethod;
    private final String xmlAttributeSerializationMethod;
    private final String xmlElementSerializationMethod;

    private ClassType(String packageKeyword, String name, List<String> implementationImports, XmsExtensions extensions,
        java.util.function.Function<String, String> defaultValueExpressionConverter, boolean isSwaggerType,
        boolean serializationNeedsNullGuarding, String jsonFieldSerializationMethod,
        String jsonValueSerializationMethod, String xmlAttributeSerializationMethod,
        String xmlElementSerializationMethod) {
        this.fullName = packageKeyword + "." + name;
        this.packageName = packageKeyword;
        this.name = name;
        this.implementationImports = implementationImports;
        this.extensions = extensions;
        this.defaultValueExpressionConverter = defaultValueExpressionConverter;
        this.isSwaggerType = isSwaggerType;
        this.serializationNeedsNullGuarding = serializationNeedsNullGuarding;
        this.jsonFieldSerializationMethod = jsonFieldSerializationMethod;
        this.jsonValueSerializationMethod = jsonValueSerializationMethod;
        this.xmlAttributeSerializationMethod = xmlAttributeSerializationMethod;
        this.xmlElementSerializationMethod = xmlElementSerializationMethod;
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
        // TODO (alzimmer): This should be a property on the ClassType
        return this.equals(ClassType.Void)
            || this.equals(ClassType.Boolean)
            || this.equals(ClassType.Byte)
            || this.equals(ClassType.Integer)
            || this.equals(ClassType.Long)
            || this.equals(ClassType.Float)
            || this.equals(ClassType.Double);
    }

    @Override
    public boolean deserializationNeedsNullGuarding() {
        return serializationNeedsNullGuarding;
    }

    @Override
    public String toString() {
        return name;
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
        return fullName;
    }

    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (!getPackage().equals("java.lang")) {
            imports.add(fullName);
        }

        if (includeImplementationImports && getImplementationImports() != null) {
            imports.addAll(getImplementationImports());
        }
    }

    public final String defaultValueExpression(String sourceExpression) {
        String result = sourceExpression;
        if (result != null) {
            if (getDefaultValueExpressionConverter() != null) {
                result = defaultValueExpressionConverter.apply(sourceExpression);
            } else {
                result = java.lang.String.format("new %1$s()", this);
            }
        }
        return result;
    }

    @Override
    public String defaultValueExpression() {
        return "null";
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
        if (this == ClassType.DateTimeRfc1123
            || this == ClassType.AndroidDateTimeRfc1123) {
            expression = java.lang.String.format("%s.getDateTime()", expression);
        } else if (this == ClassType.Base64Url) {
            expression = java.lang.String.format("%s.decodedBytes()", expression);
        } else if (this == ClassType.URL) {
            expression = java.lang.String.format("new URL(%s)", expression);
        }

        return expression;
    }

    public String convertFromClientType(String expression) {
        if (this == ClassType.DateTimeRfc1123
            || this == ClassType.AndroidDateTimeRfc1123) {
            expression = java.lang.String.format("new DateTimeRfc1123(%s)", expression);
        } else if (this == ClassType.Base64Url) {
            expression = java.lang.String.format("Base64Url.encode(%s)", expression);
        } else if (this == ClassType.URL) {
            expression = java.lang.String.format("%s.toString()", expression);
        }

        return expression;
    }

    public String validate(String expression) {
        if (packageName.startsWith(JavaSettings.getInstance().getPackage())) {
            return expression + ".validate()";
        } else {
            return null;
        }
    }

    public boolean isSwaggerType() {
        return isSwaggerType;
    }

    @Override
    public String streamStyleJsonFieldSerializationMethod() {
        return jsonFieldSerializationMethod;
    }

    @Override
    public String streamStyleJsonValueSerializationMethod() {
        return jsonValueSerializationMethod;
    }

    @Override
    public java.lang.String streamStyleXmlAttributeSerializationMethod() {
        return xmlAttributeSerializationMethod;
    }

    @Override
    public java.lang.String streamStyleXmlElementSerializationMethod() {
        return xmlElementSerializationMethod;
    }

    public static class Builder {
        /*
         * Used to indicate if the class type is generated based on a Swagger definition and isn't a pre-defined,
         * handwritten type.
         */
        private final boolean isSwaggerType;

        private String packageName;
        private String name;
        private List<String> implementationImports;
        private XmsExtensions extensions;
        private java.util.function.Function<String, String> defaultValueExpressionConverter;
        // By default, class types need null guarding in serialization.
        private boolean serializationNeedsNullGuarding = true;
        private String jsonFieldSerializationMethod;
        private String jsonValueSerializationMethod;
        private String xmlAttributeSerializationMethod;
        private String xmlElementSerializationMethod;

        public Builder() {
            this(true);
        }

        private Builder(boolean isSwaggerType) {
            this.isSwaggerType = isSwaggerType;
        }

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

        public Builder serializationNeedsNullGuarding(boolean serializationNeedsNullGuarding) {
            this.serializationNeedsNullGuarding = serializationNeedsNullGuarding;
            return this;
        }

        public Builder streamStyleJsonFieldSerializationMethod(String jsonFieldSerializationMethod) {
            this.jsonFieldSerializationMethod = jsonFieldSerializationMethod;
            return this;
        }

        public Builder streamStyleJsonValueSerializationMethod(String jsonValueSerializationMethod) {
            this.jsonValueSerializationMethod = jsonValueSerializationMethod;
            return this;
        }

        public Builder streamStyleXmlAttributeSerializationMethod(String xmlAttributeSerializationMethod) {
            this.xmlAttributeSerializationMethod = xmlAttributeSerializationMethod;
            return this;
        }

        public Builder streamStyleXmlElementSerializationMethod(String xmlElementSerializationMethod) {
            this.xmlElementSerializationMethod = xmlElementSerializationMethod;
            return this;
        }

        public ClassType build() {
            // Types that are based on Swagger will use writeJsonField and writeJson as they should extend
            // JsonSerializable.
            String jsonFieldSerializationMethod =
                (this.jsonFieldSerializationMethod == null && isSwaggerType)
                    ? "writeJsonField"
                    : this.jsonFieldSerializationMethod;
            String jsonValueSerializationMethod =
                (this.jsonValueSerializationMethod == null && isSwaggerType)
                    ? "writeJson"
                    : this.jsonValueSerializationMethod;

            // Types that are based on Swagger won't have any XML serialization methods as they're handled differently
            // than JSON. In JSON the containing class determines the JSON property name that'll be written but in XML
            // the class itself handles writing the wrapping XML element.
            String xmlAttributeSerializationMethod = isSwaggerType ? null : this.xmlAttributeSerializationMethod;
            String xmlElementSerializationMethod = isSwaggerType ? null : this.xmlElementSerializationMethod;

            return new ClassType(packageName, name, implementationImports, extensions, defaultValueExpressionConverter,
                isSwaggerType, serializationNeedsNullGuarding && !isSwaggerType,
                jsonFieldSerializationMethod, jsonValueSerializationMethod, xmlAttributeSerializationMethod,
                xmlElementSerializationMethod);
        }
    }

    private static final Map<Character, String> ESCAPE_REPLACEMENT = new HashMap<Character, String>() {{
        put('\\', "\\\\");
        put('\t', "\\t");
        put('\b', "\\b");
        put('\n', "\\n");
        put('\r', "\\r");
        put('\f', "\\f");
        put('\"', "\\\"");
    }};

    private static String escapeString(String str) {
        StringBuilder builder = null;

        int last = 0;
        for (int i = 0; i < str.length(); i++) {
            String replacement = ESCAPE_REPLACEMENT.get(str.charAt(i));

            if (replacement == null) {
                continue;
            }

            if (builder == null) {
                builder = new StringBuilder(str.length() * 2);
            }

            if (last != i) {
                builder.append(str, last, i);
            }

            builder.append(replacement);
            last = i + 1;
        }

        if (builder == null) {
            return str;
        }

        builder.append(str, last, str.length());
        return builder.toString();
    }
}
