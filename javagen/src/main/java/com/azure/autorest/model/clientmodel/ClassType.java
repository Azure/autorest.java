// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;


import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.util.TemplateUtil;
import com.azure.core.http.HttpHeaderName;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

//    private static class ClassDetails {
//
//        private final Class<?> azureClass;
//        private final Class<?> genericClass;
//
//        public ClassDetails(Class<?> azureClass, Class<?> genericClass) {
//            this.azureClass = azureClass;
//            this.genericClass = genericClass;
//        }
//
//        public Class<?> getAzureClass() {
//            return azureClass;
//        }
//
//        public Class<?> getGenericClass() {
//            return genericClass;
//        }
//
//    }
//
//    private static final Map<Class<?>, ClassDetails> CLASS_TYPE_MAPPING = new HashMap<Class<?>, ClassDetails>() {{
//        put(com.azure.core.http.rest.RestProxy.class, new ClassDetails(com.azure.core.http.rest.RestProxy.class, com.generic.core.http.RestProxy.class));
//        put(com.azure.core.http.HttpPipeline.class, new ClassDetails(com.azure.core.http.HttpPipeline.class, com.generic.core.http.pipeline.HttpPipeline.class));
//        put(com.azure.core.http.HttpPipelineBuilder.class, new ClassDetails(com.azure.core.http.HttpPipelineBuilder.class, com.generic.core.http.pipeline.HttpPipelineBuilder.class));
//        put(com.azure.core.util.Context.class, new ClassDetails(com.azure.core.util.Context.class, com.generic.core.models.Context.class));
//        put(com.azure.core.http.HttpClient.class, new ClassDetails(com.azure.core.http.HttpClient.class, com.generic.core.http.client.HttpClient.class));
//        put(com.azure.core.http.policy.HttpLogOptions.class, new ClassDetails(com.azure.core.http.policy.HttpLogOptions.class, com.generic.core.http.policy.logging.HttpLogOptions.class));
//        put(com.azure.core.http.policy.HttpPipelinePolicy.class, new ClassDetails(com.azure.core.http.policy.HttpPipelinePolicy.class, com.generic.core.http.pipeline.HttpPipelinePolicy.class));
//        put(com.azure.core.http.policy.KeyCredentialPolicy.class, new ClassDetails(com.azure.core.http.policy.KeyCredentialPolicy.class, com.generic.core.http.policy.credential.KeyCredentialPolicy.class));
//        put(com.azure.core.util.Configuration.class, new ClassDetails(com.azure.core.util.Configuration.class, com.generic.core.util.configuration.Configuration.class));
//        put(com.azure.core.http.HttpHeaders.class, new ClassDetails(com.azure.core.http.HttpHeaders.class, com.generic.core.models.Headers.class));
//        put(com.azure.core.http.HttpHeaderName.class, new ClassDetails(com.azure.core.http.HttpHeaderName.class, com.generic.core.http.models.HttpHeaderName.class));
//        put(com.azure.core.http.HttpRequest.class, new ClassDetails(com.azure.core.http.HttpRequest.class, com.generic.core.http.models.HttpRequest.class));
//        // TODO: generic not having ClientOptions
////        put(com.azure.core.util.ClientOptions.class, new ClassDetails(com.azure.core.util.ClientOptions.class, com.generic.core.models.ClientOptions.class));
//        put(com.azure.core.http.rest.RequestOptions.class, new ClassDetails(com.azure.core.http.rest.RequestOptions.class, com.generic.core.http.models.RequestOptions.class));
//        put(com.azure.core.util.BinaryData.class, new ClassDetails(com.azure.core.util.BinaryData.class, com.generic.core.models.BinaryData.class));
//        put(com.azure.core.http.policy.RetryOptions.class, new ClassDetails(com.azure.core.http.policy.RetryOptions.class, com.generic.core.http.policy.retry.RetryOptions.class));
//        put(com.azure.core.http.rest.Response.class, new ClassDetails(com.azure.core.http.rest.Response.class, com.generic.core.http.Response.class));
//        put(com.azure.core.http.rest.SimpleResponse.class, new ClassDetails(com.azure.core.http.rest.SimpleResponse.class, com.generic.core.http.SimpleResponse.class));
//        put(com.azure.core.util.ExpandableStringEnum.class, new ClassDetails(com.azure.core.util.ExpandableStringEnum.class, com.generic.core.models.ExpandableStringEnum.class));
//    }};

    private static class ClassDetails {

        private final Class<?> azureClass;
        private final String genericClass;

        public ClassDetails(Class<?> azureClass, String genericClass) {
            this.azureClass = azureClass;
            this.genericClass = genericClass;
        }

        public String getAzureClass() {
            return azureClass.getName();
        }

        public String getGenericClass() {
            return genericClass;
        }

    }

    private static final Map<Class<?>, ClassDetails> CLASS_TYPE_MAPPING = new HashMap<Class<?>, ClassDetails>() {{
        put(com.azure.core.http.rest.RestProxy.class, new ClassDetails(com.azure.core.http.rest.RestProxy.class, "com.generic.core.http.RestProxy"));
        put(com.azure.core.http.HttpPipeline.class, new ClassDetails(com.azure.core.http.HttpPipeline.class, "com.generic.core.http.pipeline.HttpPipeline"));
        put(com.azure.core.http.HttpPipelineBuilder.class, new ClassDetails(com.azure.core.http.HttpPipelineBuilder.class, "com.generic.core.http.pipeline.HttpPipelineBuilder"));
        put(com.azure.core.util.Context.class, new ClassDetails(com.azure.core.util.Context.class, "com.generic.core.models.Context"));
        put(com.azure.core.http.HttpClient.class, new ClassDetails(com.azure.core.http.HttpClient.class, "com.generic.core.http.client.HttpClient"));
        put(com.azure.core.http.policy.HttpLogOptions.class, new ClassDetails(com.azure.core.http.policy.HttpLogOptions.class, "com.generic.core.http.policy.logging.HttpLogOptions"));
        put(com.azure.core.http.policy.HttpPipelinePolicy.class, new ClassDetails(com.azure.core.http.policy.HttpPipelinePolicy.class, "com.generic.core.http.pipeline.HttpPipelinePolicy"));
        put(com.azure.core.http.policy.KeyCredentialPolicy.class, new ClassDetails(com.azure.core.http.policy.KeyCredentialPolicy.class, "com.generic.core.http.policy.credential.KeyCredentialPolicy"));
        put(com.azure.core.util.Configuration.class, new ClassDetails(com.azure.core.util.Configuration.class, "com.generic.core.util.configuration.Configuration"));
        put(com.azure.core.http.HttpHeaders.class, new ClassDetails(com.azure.core.http.HttpHeaders.class, "com.generic.core.models.Headers"));
        put(com.azure.core.http.HttpHeaderName.class, new ClassDetails(com.azure.core.http.HttpHeaderName.class, "com.generic.core.http.models.HttpHeaderName"));
        put(com.azure.core.http.HttpRequest.class, new ClassDetails(com.azure.core.http.HttpRequest.class, "com.generic.core.http.models.HttpRequest"));
        put(com.azure.core.http.rest.RequestOptions.class, new ClassDetails(com.azure.core.http.rest.RequestOptions.class, "com.generic.core.http.models.RequestOptions"));
        put(com.azure.core.util.BinaryData.class, new ClassDetails(com.azure.core.util.BinaryData.class, "com.generic.core.models.BinaryData"));
        put(com.azure.core.http.policy.RetryOptions.class, new ClassDetails(com.azure.core.http.policy.RetryOptions.class, "com.generic.core.http.policy.retry.RetryOptions"));
        put(com.azure.core.http.rest.Response.class, new ClassDetails(com.azure.core.http.rest.Response.class, "com.generic.core.http.Response"));
        put(com.azure.core.http.rest.SimpleResponse.class, new ClassDetails(com.azure.core.http.rest.SimpleResponse.class, "com.generic.core.http.SimpleResponse"));
        put(com.azure.core.util.ExpandableStringEnum.class, new ClassDetails(com.azure.core.util.ExpandableStringEnum.class, "com.generic.core.models.ExpandableStringEnum"));
    }};

    private static ClassType.Builder getClassTypeBuilder(Class<?> classKey) {
        if (!JavaSettings.getInstance().isBranded()) {
            if (CLASS_TYPE_MAPPING.containsKey(classKey)) {
                return new ClassType.Builder(false)
                        .knownClass(CLASS_TYPE_MAPPING.get(classKey).getGenericClass());
            } else {
                return new Builder(false)
                        .packageName(classKey.getPackage().getName()
                                .replace(ExternalPackage.AZURE_CORE_PACKAGE_NAME, ExternalPackage.GENERIC_CORE_PACKAGE_NAME)
                                .replace(ExternalPackage.AZURE_JSON_PACKAGE_NAME, ExternalPackage.GENERIC_JSON_PACKAGE_NAME))
                        .name(classKey.getSimpleName());
            }
        } else {
            if (CLASS_TYPE_MAPPING.containsKey(classKey)) {
                return new ClassType.Builder(false)
                        .knownClass(CLASS_TYPE_MAPPING.get(classKey).getAzureClass());
            } else {
                return new Builder(false)
                        .packageName(classKey.getPackage().getName()).name(classKey.getSimpleName());
            }
        }
    }


    public static final ClassType Void = new ClassType.Builder(false).knownClass(Void.class).build();

    public static final ClassType Boolean = new Builder(false).knownClass(Boolean.class)
        .defaultValueExpressionConverter(java.lang.String::toLowerCase)
        .jsonDeserializationMethod("getNullable(JsonReader::getBoolean)")
        .serializationMethodBase("writeBoolean")
        .xmlElementDeserializationMethod("getNullableElement(Boolean::parseBoolean)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, Boolean::parseBoolean)")
        .build();

    public static final ClassType Byte = new Builder(false).knownClass(Byte.class)
        .jsonDeserializationMethod("getNullable(JsonReader::getInt)")
        .serializationMethodBase("writeNumber")
        .xmlElementDeserializationMethod("getNullableElement(Byte::parseByte)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, Byte::parseByte)")
        .build();

    public static final ClassType Integer = new Builder(false).knownClass(Integer.class)
        .defaultValueExpressionConverter(java.util.function.Function.identity())
        .jsonDeserializationMethod("getNullable(JsonReader::getInt)")
        .serializationMethodBase("writeNumber")
        .xmlElementDeserializationMethod("getNullableElement(Integer::parseInt)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, Integer::parseInt)")
        .build();

    public static final ClassType Long = new Builder(false)
        .prototypeAsLong()
        .build();

    public static final ClassType Float = new Builder(false).knownClass(Float.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.valueOf(java.lang.Float.parseFloat(defaultValueExpression)) + 'F')
        .jsonDeserializationMethod("getNullable(JsonReader::getFloat)")
        .serializationMethodBase("writeNumber")
        .xmlElementDeserializationMethod("getNullableElement(Float::parseFloat)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, Float::parseFloat)")
        .build();

    public static final ClassType Double = new Builder(false).knownClass(Double.class)
        .prototypeAsDouble()
        .build();

    public static final ClassType Character = new Builder(false).knownClass(Character.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.valueOf((defaultValueExpression.charAt(0))))
        .wrapSerializationWithObjectsToString(true)
        .jsonDeserializationMethod("getNullable(nonNullReader -> nonNullReader.getString().charAt(0))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(nonNullString -> nonNullString.charAt(0))")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, nonNullString -> nonNullString.charAt(0))")
        .build();

    public static final ClassType String = new Builder(false).knownClass(String.class)
        .defaultValueExpressionConverter(defaultValueExpression -> "\"" + TemplateUtil.escapeString(defaultValueExpression) + "\"")
        .jsonDeserializationMethod("getString()")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getStringElement()")
        .xmlAttributeDeserializationTemplate("getStringAttribute(%s, %s)")
        .build();

    public static final ClassType Base64Url = getClassTypeBuilder(com.azure.core.util.Base64Url.class)
        .wrapSerializationWithObjectsToString(true)
        .jsonDeserializationMethod("getNullable(nonNullReader -> new Base64Url(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(Base64Url::new)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, Base64Url::new)")
        .build();

    public static final ClassType AndroidBase64Url = new ClassType.Builder(false)
        .packageName("com.azure.android.core.util").name("Base64Url")
        .build();

    public static final ClassType LocalDate = new Builder(false).knownClass(java.time.LocalDate.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("LocalDate.parse(\"%1$s\")", defaultValueExpression))
        .wrapSerializationWithObjectsToString(true)
        .jsonDeserializationMethod("getNullable(nonNullReader -> LocalDate.parse(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(LocalDate::parse)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, LocalDate::parse)")
        .build();

    public static final ClassType AndroidLocalDate = new ClassType.Builder(false)
        .packageName("org.threeten.bp").name("LocalDate")
        .build();

    public static final ClassType DateTime = new Builder(false).knownClass(OffsetDateTime.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("OffsetDateTime.parse(\"%1$s\")", defaultValueExpression))
        .wrapSerializationWithObjectsToString(true)
        .jsonDeserializationMethod("getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(OffsetDateTime::parse)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, OffsetDateTime::parse)")
        .build();

    public static final ClassType Duration = new Builder(false).knownClass(java.time.Duration.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("Duration.parse(\"%1$s\")", defaultValueExpression))
        .wrapSerializationWithObjectsToString(true)
        .jsonDeserializationMethod("getNullable(nonNullReader -> Duration.parse(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(Duration::parse)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, Duration::parse)")
        .build();

    public static final ClassType AndroidDuration = new ClassType.Builder(false)
        .packageName("org.threeten.bp").name("Duration")
        .build();

    public static final ClassType DateTimeRfc1123 = getClassTypeBuilder(com.azure.core.util.DateTimeRfc1123.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("new DateTimeRfc1123(\"%1$s\")", defaultValueExpression))
        .wrapSerializationWithObjectsToString(true)
        .jsonDeserializationMethod("getNullable(nonNullReader -> new DateTimeRfc1123(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(DateTimeRfc1123::new)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, DateTimeRfc1123::new)")
        .build();

    public static final ClassType AndroidDateTimeRfc1123 = new ClassType.Builder(false)
        .packageName("com.azure.android.core.util").name("DateTimeRfc1123")
        .build();

    public static final ClassType BigDecimal = new ClassType.Builder(false).knownClass(java.math.BigDecimal.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("new BigDecimal(\"%1$s\")", defaultValueExpression))
        .build();

    public static final ClassType UUID = new Builder(false).knownClass(java.util.UUID.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("UUID.fromString(\"%1$s\")", defaultValueExpression))
        .wrapSerializationWithObjectsToString(true)
        .jsonDeserializationMethod("getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(UUID::fromString)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, UUID::fromString)")
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
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("OffsetDateTime.parse(\"%1$s\")", defaultValueExpression))
        .knownClass(java.time.OffsetDateTime.class)
        .wrapSerializationWithObjectsToString(true)
        .jsonDeserializationMethod("getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(OffsetDateTime::parse)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, OffsetDateTime::parse)")
        .build();

    public static final ClassType AndroidDateTime = new ClassType.Builder(false)
        .packageName("org.threeten.bp").name("OffsetDateTime")
        .build();

    public static final ClassType UnixTimeLong = new ClassType.Builder(false)
        .prototypeAsLong()
        .build();

    public static final ClassType DurationLong = new ClassType.Builder(false)
        .prototypeAsLong()
        .build();

    public static final ClassType DurationDouble = new ClassType.Builder(false)
        .prototypeAsDouble()
        .build();

    public static final ClassType HttpPipeline = getClassTypeBuilder(com.azure.core.http.HttpPipeline.class)
            .build();

    public static final ClassType AndroidHttpPipeline = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http").name("HttpPipeline")
        .build();

    public static final ClassType RestProxy = getClassTypeBuilder(com.azure.core.http.rest.RestProxy.class)
            .build();

    public static final ClassType AndroidRestProxy = new ClassType.Builder(false)
        .packageName("com.azure.android.core.rest").name("RestProxy")
        .build();

    public static final ClassType SerializerAdapter = new ClassType.Builder(false)
        .knownClass(com.azure.core.util.serializer.SerializerAdapter.class)
        .build();

    public static final ClassType JsonSerializer = getClassTypeBuilder(com.azure.core.util.serializer.JsonSerializer.class)
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

    public static final ClassType URL = new Builder(false)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("new URL(\"%1$s\")", defaultValueExpression))
        .knownClass(java.net.URL.class)
        .wrapSerializationWithObjectsToString(true)
        .jsonDeserializationMethod("getNullable(nonNullReader -> new URL(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(URL::new)")
        .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, URL::new)")
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

    public static final ClassType Context = ClassType.getClassTypeBuilder(com.azure.core.util.Context.class)
        .defaultValueExpressionConverter(epr -> "com.azure.core.util.Context.NONE")
        .build();

    public static final ClassType AndroidContext = new ClassType.Builder(false)
        .packageName("com.azure.android.core.util").name("Context")
        .build();

    public static final ClassType ClientLogger = ClassType.getClassTypeBuilder(com.azure.core.util.logging.ClientLogger.class)
        .build();

    public static final ClassType AzureEnvironment = new ClassType.Builder(false)
        .packageName("com.azure.core.management").name("AzureEnvironment")
        .build();

    public static final ClassType HttpClient = getClassTypeBuilder(com.azure.core.http.HttpClient.class)
        .build();

    public static final ClassType AndroidHttpClient = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http").name("HttpClient")
        .build();

    public static final ClassType HttpPipelinePolicy = getClassTypeBuilder(com.azure.core.http.policy.HttpPipelinePolicy.class)
        .build();

    public static final ClassType AndroidHttpPipelinePolicy = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http").name("HttpPipelinePolicy")
        .build();

    public static final ClassType HttpLogOptions = getClassTypeBuilder(com.azure.core.http.policy.HttpLogOptions.class)
        .build();

    public static final ClassType AndroidHttpLogOptions = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http.policy").name("HttpLogOptions")
        .build();

    public static final ClassType Configuration = getClassTypeBuilder(com.azure.core.util.Configuration.class)
        .build();

    public static final ClassType ServiceVersion = new ClassType.Builder(false)
        .knownClass(com.azure.core.util.ServiceVersion.class)
        .build();

    public static final ClassType AzureKeyCredential = new ClassType.Builder(false)
        .knownClass(com.azure.core.credential.AzureKeyCredential.class)
        .build();

    public static final ClassType KeyCredential = getClassTypeBuilder(com.azure.core.credential.KeyCredential.class)
            .build();

    public static final ClassType RetryPolicy = new ClassType.Builder(false)
        .knownClass(com.azure.core.http.policy.RetryPolicy.class)
        .build();

    public static final ClassType RetryOptions = getClassTypeBuilder(com.azure.core.http.policy.RetryOptions.class)
        .build();

    public static final ClassType AndroidRetryPolicy = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http.policy").name("RetryPolicy")
        .build();

    public static final ClassType JsonPatchDocument = new ClassType.Builder(false)
        .knownClass(com.azure.core.models.JsonPatchDocument.class)
        .build();

    public static final ClassType BinaryData = getClassTypeBuilder(com.azure.core.util.BinaryData.class)
        .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.format("BinaryData.fromObject(\"%s\")", defaultValueExpression))
        .build();

    public static final ClassType RequestOptions = getClassTypeBuilder(com.azure.core.http.rest.RequestOptions.class)
        .build();

    public static final ClassType ClientOptions = getClassTypeBuilder(com.azure.core.util.ClientOptions.class)
        .build();

    public static final ClassType HttpRequest = getClassTypeBuilder(com.azure.core.http.HttpRequest.class)
        .build();

    public static final ClassType HttpHeaders = getClassTypeBuilder(com.azure.core.http.HttpHeaders.class)
        .build();

    public static final ClassType HttpHeaderName = getClassTypeBuilder(HttpHeaderName.class).build();

    // Java exception types
    public static final ClassType HttpResponseException = getClassTypeBuilder(com.azure.core.exception.HttpResponseException.class)
        .build();

    public static final ClassType ClientAuthenticationException = getClassTypeBuilder(com.azure.core.exception.ClientAuthenticationException.class)
        .build();

    public static final ClassType ResourceExistsException = getClassTypeBuilder(com.azure.core.exception.ResourceExistsException.class)
        .build();

    public static final ClassType ResourceModifiedException = getClassTypeBuilder(com.azure.core.exception.ResourceModifiedException.class)
        .build();

    public static final ClassType ResourceNotFoundException = getClassTypeBuilder(com.azure.core.exception.ResourceNotFoundException.class)
        .build();

    public static final ClassType TooManyRedirectsException = getClassTypeBuilder(com.azure.core.exception.TooManyRedirectsException.class)
        .build();

    public static final ClassType ResponseError = new Builder()
        .knownClass(com.azure.core.models.ResponseError.class)
        .build();
    public static final ClassType ResponseInnerError = new Builder()
        .packageName("com.azure.core.models").name("ResponseInnerError")
        .build();

    public static final ClassType RequestConditions = new Builder()
        .knownClass(com.azure.core.http.RequestConditions.class)
        .build();

    public static final ClassType MatchConditions = new Builder()
        .knownClass(com.azure.core.http.MatchConditions.class)
        .build();

    public static final ClassType CoreUtils = getClassTypeBuilder(com.azure.core.util.CoreUtils.class)
        .build();

    public static final ClassType Response = getClassTypeBuilder(com.azure.core.http.rest.Response.class)
        .build();

    public static final ClassType SimpleResponse = getClassTypeBuilder(com.azure.core.http.rest.SimpleResponse.class)
        .build();

    public static final ClassType ExpandableStringEnum = getClassTypeBuilder(com.azure.core.util.ExpandableStringEnum.class)
        .build();

    public static final ClassType HttpPipelineBuilder = getClassTypeBuilder(com.azure.core.http.HttpPipelineBuilder.class)
        .build();

    public static final ClassType KeyCredentialPolicy = getClassTypeBuilder(com.azure.core.http.policy.KeyCredentialPolicy.class)
        .build();

    public static final ClassType KeyCredentialTrait = getClassTypeBuilder(com.azure.core.client.traits.KeyCredentialTrait.class)
        .build();

    public static final ClassType JsonSerializable = getClassTypeBuilder(com.azure.json.JsonSerializable.class)
        .build();

    public static final ClassType JsonWriter = getClassTypeBuilder(com.azure.json.JsonWriter.class)
        .build();

    public static final ClassType JsonReader = getClassTypeBuilder(com.azure.json.JsonReader.class)
        .build();

    public static final ClassType JsonToken = getClassTypeBuilder(com.azure.json.JsonToken.class)
        .build();

    private final String fullName;
    private final String packageName;
    private final String name;
    private final List<String> implementationImports;
    private final XmsExtensions extensions;
    private final java.util.function.Function<String, String> defaultValueExpressionConverter;
    private final boolean isSwaggerType;
    private final boolean wrapSerializationWithObjectsToString;
    private final String serializationMethodBase;
    private final String jsonDeserializationMethod;
    private final String xmlAttributeDeserializationTemplate;
    private final String xmlElementDeserializationMethod;
    private final boolean usedInXml;

    private ClassType(String packageKeyword, String name, List<String> implementationImports, XmsExtensions extensions,
        java.util.function.Function<String, String> defaultValueExpressionConverter, boolean isSwaggerType,
        String serializationMethodBase, boolean wrapSerializationWithObjectsToString,
        String jsonDeserializationMethod, String xmlAttributeDeserializationTemplate,
        String xmlElementDeserializationMethod, boolean usedInXml) {
        this.fullName = packageKeyword + "." + name;
        this.packageName = packageKeyword;
        this.name = name;
        this.implementationImports = implementationImports;
        this.extensions = extensions;
        this.defaultValueExpressionConverter = defaultValueExpressionConverter;
        this.isSwaggerType = isSwaggerType;
        this.serializationMethodBase = serializationMethodBase;
        this.wrapSerializationWithObjectsToString = wrapSerializationWithObjectsToString;
        this.jsonDeserializationMethod = jsonDeserializationMethod;
        this.xmlAttributeDeserializationTemplate = xmlAttributeDeserializationTemplate;
        this.xmlElementDeserializationMethod = xmlElementDeserializationMethod;
        this.usedInXml = usedInXml;
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

        if (this == ClassType.UnixTimeLong) {
            imports.add(Instant.class.getName());
            imports.add(ZoneOffset.class.getName());
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
        } else if (this == ClassType.UnixTimeLong) {
            clientType = ClassType.DateTime;
        } else if (this == ClassType.Base64Url) {
            clientType = ArrayType.BYTE_ARRAY;
        } else if (this == ClassType.DurationLong) {
            clientType = ClassType.Duration;
        } else if (this == ClassType.DurationDouble) {
            clientType = ClassType.Duration;
        }
        return clientType;
    }

    public String convertToClientType(String expression) {
        if (this == ClassType.DateTimeRfc1123 || this == ClassType.AndroidDateTimeRfc1123) {
            expression = expression + ".getDateTime()";
        } else if (this == ClassType.UnixTimeLong) {
            expression = "OffsetDateTime.ofInstant(Instant.ofEpochSecond(" + expression + "), ZoneOffset.UTC)";
        } else if (this == ClassType.Base64Url) {
            expression = expression + ".decodedBytes()";
        } else if (this == ClassType.URL) {
            expression = "new URL(" + expression + ")";
        } else if (this == ClassType.DurationLong) {
            expression = "Duration.ofSeconds(" + expression + ")";
        } else if (this == ClassType.DurationDouble) {
            expression = "Duration.ofNanos((long) (" + expression + " * 1000_000_000L))";
        }

        return expression;
    }

    public String convertFromClientType(String expression) {
        if (this == ClassType.DateTimeRfc1123 || this == ClassType.AndroidDateTimeRfc1123) {
            expression = "new DateTimeRfc1123(" + expression + ")";
        } else if (this == ClassType.UnixTimeLong) {
            expression = expression + ".toEpochSecond()";
        } else if (this == ClassType.Base64Url) {
            expression = "Base64Url.encode(" + expression + ")";
        } else if (this == ClassType.URL) {
            expression = expression + ".toString()";
        } else if (this == ClassType.DurationLong) {
            expression = expression + ".getSeconds()";
        } else if (this == ClassType.DurationDouble) {
            expression = "(double) " + expression + ".toNanos() / 1000_000_000L";
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
    public java.lang.String jsonDeserializationMethod(String jsonReaderName) {
        if (jsonDeserializationMethod == null) {
            return null;
        }

        return jsonReaderName + "." + jsonDeserializationMethod;
    }

    @Override
    public java.lang.String jsonSerializationMethodCall(java.lang.String jsonWriterName, java.lang.String fieldName,
        java.lang.String valueGetter) {
        if (!isSwaggerType && com.azure.core.util.CoreUtils.isNullOrEmpty(serializationMethodBase)) {
            return null;
        }

        String methodBase = isSwaggerType ? "writeJson" : serializationMethodBase;
        String value = wrapSerializationWithObjectsToString
            ? "Objects.toString(" + valueGetter + ", null)" : valueGetter;

        return fieldName == null
            ? java.lang.String.format("%s.%s(%s)", jsonWriterName, methodBase, value)
            : java.lang.String.format("%s.%sField(\"%s\", %s)", jsonWriterName, methodBase, fieldName, value);
    }

    @Override
    public java.lang.String xmlDeserializationMethod(java.lang.String attributeName, java.lang.String attributeNamespace) {
        if (attributeName == null) {
            return xmlElementDeserializationMethod;
        } else {
            return (attributeNamespace == null)
                ? java.lang.String.format(xmlAttributeDeserializationTemplate, "null", "\"" + attributeName + "\"")
                : java.lang.String.format(xmlAttributeDeserializationTemplate, "\"" + attributeNamespace + "\"",
                "\"" + attributeName + "\"");
        }
    }

    @Override
    public java.lang.String xmlSerializationMethodCall(java.lang.String xmlWriterName,
        java.lang.String attributeOrElementName, java.lang.String namespaceUri, java.lang.String valueGetter,
        boolean isAttribute, boolean nameIsVariable) {
        if (isSwaggerType) {
            if (isAttribute) {
                throw new RuntimeException("Swagger types cannot be written as attributes.");
            }

            return xmlWriterName + ".writeXml(" + valueGetter + ", \"" + attributeOrElementName + "\")";
        }

        String value = wrapSerializationWithObjectsToString ? "Objects.toString(" + valueGetter + ", null)" : valueGetter;
        return xmlSerializationCallHelper(xmlWriterName, serializationMethodBase, attributeOrElementName, namespaceUri,
            value, isAttribute, nameIsVariable);
    }

    @Override
    public boolean isUsedInXml() {
        return usedInXml;
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
        private boolean wrapSerializationWithObjectsToString = false;
        private String jsonDeserializationMethod;
        private String serializationMethodBase;
        private String xmlAttributeDeserializationTemplate;
        private String xmlElementDeserializationMethod;
        private boolean usedInXml;

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

        public Builder prototypeAsLong() {
            return this.knownClass(Long.class)
                .defaultValueExpressionConverter(defaultValueExpression -> defaultValueExpression + 'L')
                .serializationMethodBase("writeNumber")
                .wrapSerializationWithObjectsToString(false)
                .jsonDeserializationMethod("getNullable(JsonReader::getLong)")
                .xmlElementDeserializationMethod("getNullableElement(Long::parseLong)")
                .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, Long::parseLong)");
        }

        public Builder prototypeAsDouble() {
            return this.knownClass(Double.class)
                .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.valueOf(java.lang.Double.parseDouble(defaultValueExpression)) + 'D')
                .serializationMethodBase("writeNumber")
                .wrapSerializationWithObjectsToString(false)
                .jsonDeserializationMethod("getNullable(JsonReader::getDouble)")
                .xmlElementDeserializationMethod("getNullableElement(Double::parseDouble)")
                .xmlAttributeDeserializationTemplate("getNullableAttribute(%s, %s, Double::parseDouble)");
        }

        public Builder knownClass(Class<?> clazz) {
            return packageName(clazz.getPackage().getName())
                .name(clazz.getSimpleName());
        }

        private Builder knownClass(String fullName) {
            int index = fullName.lastIndexOf(".");
            return packageName(fullName.substring(0, index))
                    .name(fullName.substring(index + 1));
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

        public Builder wrapSerializationWithObjectsToString(boolean wrapSerializationWithObjectsToString) {
            this.wrapSerializationWithObjectsToString = wrapSerializationWithObjectsToString;
            return this;
        }

        public Builder jsonDeserializationMethod(String jsonDeserializationMethod) {
            this.jsonDeserializationMethod = jsonDeserializationMethod;
            return this;
        }

        public Builder serializationMethodBase(String serializationMethodBase) {
            this.serializationMethodBase = serializationMethodBase;
            return this;
        }

        public Builder xmlAttributeDeserializationTemplate(String xmlAttributeDeserializationTemplate) {
            this.xmlAttributeDeserializationTemplate = xmlAttributeDeserializationTemplate;
            return this;
        }

        public Builder xmlElementDeserializationMethod(String xmlElementDeserializationMethod) {
            this.xmlElementDeserializationMethod = xmlElementDeserializationMethod;
            return this;
        }

        public Builder usedInXml(boolean usedInXml) {
            this.usedInXml = usedInXml;
            return this;
        }

        public ClassType build() {
            // Deserialization of Swagger types needs to be handled differently as the named reader needs
            // to be passed to the deserialization method and the reader name cannot be determined here.
            String jsonDeserializationMethod = isSwaggerType ? null : this.jsonDeserializationMethod;
            String xmlAttributeDeserializationTemplate = isSwaggerType
                ? null : this.xmlAttributeDeserializationTemplate;
            String xmlElementDeserializationMethod = isSwaggerType ? null : this.xmlElementDeserializationMethod;

            return new ClassType(packageName, name, implementationImports, extensions, defaultValueExpressionConverter,
                isSwaggerType, serializationMethodBase, wrapSerializationWithObjectsToString,
                jsonDeserializationMethod, xmlAttributeDeserializationTemplate, xmlElementDeserializationMethod,
                usedInXml);
        }
    }

    static String xmlSerializationCallHelper(String xmlWriterName, String serializationMethodName,
        String attributeOrElementName, String namespaceUri, String valueGetter, boolean isAttribute,
        boolean nameIsVariable) {
        String name = null;
        if (attributeOrElementName != null) {
            name = nameIsVariable ? attributeOrElementName : "\"" + attributeOrElementName + "\"";
        }
        if (isAttribute) {
            if (namespaceUri == null) {
                return java.lang.String.format("%s.%sAttribute(%s, %s)", xmlWriterName, serializationMethodName,
                    name, valueGetter);
            } else {
                return java.lang.String.format("%s.%sAttribute(\"%s\", %s, %s)", xmlWriterName,
                    serializationMethodName, namespaceUri, name, valueGetter);
            }
        } else {
            if (name == null) {
                return java.lang.String.format("%s.%s(%s)", xmlWriterName, serializationMethodName, valueGetter);
            } else {
                if (namespaceUri == null) {
                    return java.lang.String.format("%s.%sElement(%s, %s)", xmlWriterName, serializationMethodName,
                        name, valueGetter);
                } else {
                    return java.lang.String.format("%s.%sElement(\"%s\", %s, %s)", xmlWriterName,
                        serializationMethodName, namespaceUri, name, valueGetter);
                }
            }
        }
    }
}
