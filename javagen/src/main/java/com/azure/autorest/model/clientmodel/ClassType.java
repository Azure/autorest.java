// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.util.ExtensionUtils;
import com.azure.autorest.util.TemplateUtil;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/**
 * The details of a class type that is used by a client.
 */
public class ClassType implements IType {


    private static class ClassDetails {

        private final String azureClass;
        private final String genericClass;

        public ClassDetails(String azureClass, String genericClass) {
            this.azureClass = azureClass;
            this.genericClass = genericClass;
        }

        public String getAzureClass() {
            return azureClass;
        }

        public String getGenericClass() {
            return genericClass;
        }

    }

    private static final Map<String, ClassDetails> CLASS_TYPE_MAPPING = new HashMap<String, ClassDetails>() {{
        put("com.azure.core.http.rest.RestProxy", new ClassDetails("com.azure.core.http.rest.RestProxy", "com.generic.core.implementation.http.RestProxy"));
        put("com.azure.core.http.HttpPipeline", new ClassDetails("com.azure.core.http.HttpPipeline", "com.generic.core.http.pipeline.HttpPipeline"));
        put("com.azure.core.http.HttpPipelineBuilder", new ClassDetails("com.azure.core.http.HttpPipelineBuilder", "com.generic.core.http.pipeline.HttpPipelineBuilder"));
        put("com.azure.core.util.Context", new ClassDetails("com.azure.core.util.Context", "com.generic.core.models.Context"));
        put("com.azure.core.http.HttpClient", new ClassDetails("com.azure.core.http.HttpClient", "com.generic.core.http.client.HttpClient"));
        put("com.azure.core.http.policy.HttpLogOptions", new ClassDetails("com.azure.core.http.policy.HttpLogOptions", "com.generic.core.http.policy.HttpLoggingPolicy.HttpLogOptions"));
        put("com.azure.core.http.policy.HttpPipelinePolicy", new ClassDetails("com.azure.core.http.policy.HttpPipelinePolicy", "com.generic.core.http.pipeline.HttpPipelinePolicy"));
        put("com.azure.core.http.policy.KeyCredentialPolicy", new ClassDetails("com.azure.core.http.policy.KeyCredentialPolicy", "com.generic.core.http.policy.KeyCredentialPolicy"));
        put("com.azure.core.util.Configuration", new ClassDetails("com.azure.core.util.Configuration", "com.generic.core.util.configuration.Configuration"));
        put("com.azure.core.http.HttpHeaders", new ClassDetails("com.azure.core.http.HttpHeaders", "com.generic.core.models.Headers"));
        put("com.azure.core.http.HttpHeaderName", new ClassDetails("com.azure.core.http.HttpHeaderName", "com.generic.core.http.models.HttpHeaderName"));
        put("com.azure.core.http.HttpRequest", new ClassDetails("com.azure.core.http.HttpRequest", "com.generic.core.http.models.HttpRequest"));
        put("com.azure.core.http.rest.RequestOptions", new ClassDetails("com.azure.core.http.rest.RequestOptions", "com.generic.core.http.models.RequestOptions"));
        put("com.azure.core.util.BinaryData", new ClassDetails("com.azure.core.util.BinaryData", "com.generic.core.models.BinaryData"));
        put("com.azure.core.http.policy.RetryOptions", new ClassDetails("com.azure.core.http.policy.RetryOptions", "com.generic.core.http.models.RetryOptions"));
        put("com.azure.core.http.rest.Response", new ClassDetails("com.azure.core.http.rest.Response", "com.generic.core.http.Response"));
        put("com.azure.core.http.rest.SimpleResponse", new ClassDetails("com.azure.core.http.rest.SimpleResponse", "com.generic.core.http.SimpleResponse"));
        put("com.azure.core.util.ExpandableStringEnum", new ClassDetails("com.azure.core.util.ExpandableStringEnum", "com.generic.core.models.ExpandableStringEnum"));
        put("com.azure.core.exception.HttpResponseException", new ClassDetails("com.azure.core.exception.HttpResponseException", "com.generic.core.http.exception.HttpResponseException"));
        put("com.azure.core.client.traits.HttpTrait", new ClassDetails("com.azure.core.client.traits.HttpTrait", "com.generic.core.models.traits.HttpTrait"));
        put("com.azure.core.client.traits.ConfigurationTrait", new ClassDetails("com.azure.core.client.traits.ConfigurationTrait", "com.generic.core.models.traits.ConfigurationTrait"));
        put("com.azure.core.client.traits.EndpointTrait", new ClassDetails("com.azure.core.client.traits.EndpointTrait", "com.generic.core.models.traits.EndpointTrait"));
        put("com.azure.core.client.traits.KeyCredentialTrait", new ClassDetails("com.azure.core.client.traits.KeyCredentialTrait", "com.generic.core.models.traits.KeyCredentialTrait"));
        put("com.azure.core.util.serializer.TypeReference", new ClassDetails("com.azure.core.util.serializer.TypeReference", "com.generic.core.models.TypeReference"));
        put("com.azure.core.util.logging.ClientLogger", new ClassDetails("com.azure.core.util.logging.ClientLogger", "com.generic.core.util.ClientLogger"));
        put("com.azure.core.util.logging.LogLevel", new ClassDetails("com.azure.core.util.logging.LogLevel", "com.generic.core.util.ClientLogger.LogLevel"));
    }};

    private static ClassType.Builder getClassTypeBuilder(String packageName, String className) {
        String classKey = packageName + "." + className;
        if (!JavaSettings.getInstance().isBranded()) {
            if (CLASS_TYPE_MAPPING.containsKey(classKey)) {
                return new ClassType.Builder(false).knownClass(CLASS_TYPE_MAPPING.get(classKey).getGenericClass());
            } else {
                return new Builder(false).packageName(packageName
                        .replace(ExternalPackage.AZURE_CORE_PACKAGE_NAME, ExternalPackage.GENERIC_CORE_PACKAGE_NAME)
                        .replace(ExternalPackage.AZURE_JSON_PACKAGE_NAME, ExternalPackage.GENERIC_JSON_PACKAGE_NAME))
                        .name(className);
            }
        } else {
            if (CLASS_TYPE_MAPPING.containsKey(classKey)) {
                return new ClassType.Builder(false).knownClass(CLASS_TYPE_MAPPING.get(classKey).getAzureClass());
            } else {
                return new Builder(false).packageName(packageName).name(className);
            }
        }
    }

    public static final ClassType REQUEST_CONDITIONS = new Builder().packageName("com.azure.core.http").name("RequestConditions").build();
    public static final ClassType MATCH_CONDITIONS = new Builder().packageName("com.azure.core.http").name("MatchConditions").build();
    public static final ClassType CORE_UTILS = getClassTypeBuilder("com.azure.core.util", "CoreUtils").build();
    public static final ClassType RESPONSE = getClassTypeBuilder("com.azure.core.http.rest", "Response").build();
    public static final ClassType SIMPLE_RESPONSE = getClassTypeBuilder("com.azure.core.http.rest", "SimpleResponse").build();
    public static final ClassType EXPANDABLE_STRING_ENUM = getClassTypeBuilder("com.azure.core.util", "ExpandableStringEnum").build();
    public static final ClassType HTTP_PIPELINE_BUILDER = getClassTypeBuilder("com.azure.core.http", "HttpPipelineBuilder").build();
    public static final ClassType KEY_CREDENTIAL_POLICY = getClassTypeBuilder("com.azure.core.http.policy", "KeyCredentialPolicy").build();
    public static final ClassType KEY_CREDENTIAL_TRAIT = getClassTypeBuilder("com.azure.core.client.traits", "KeyCredentialTrait").build();
    public static final ClassType ENDPOINT_TRAIT = getClassTypeBuilder("com.azure.core.client.traits", "EndpointTrait").build();
    public static final ClassType HTTP_TRAIT = getClassTypeBuilder("com.azure.core.client.traits", "HttpTrait").build();
    public static final ClassType CONFIGURATION_TRAIT = getClassTypeBuilder("com.azure.core.client.traits", "ConfigurationTrait").build();
    public static final ClassType POLL_OPERATION_DETAILS = getClassTypeBuilder("com.azure.core.util.polling", "PollOperationDetails").build();
    public static final ClassType JSON_SERIALIZABLE = getClassTypeBuilder("com.azure.json", "JsonSerializable").build();
    public static final ClassType JSON_WRITER = getClassTypeBuilder("com.azure.json", "JsonWriter").build();
    public static final ClassType JSON_READER = getClassTypeBuilder("com.azure.json", "JsonReader").build();
    public static final ClassType JSON_TOKEN = getClassTypeBuilder("com.azure.json", "JsonToken").build();
    public static final ClassType TYPE_REFERENCE = getClassTypeBuilder("com.azure.core.util.serializer", "TypeReference").build();

    public static final ClassType VOID = new ClassType.Builder(false).knownClass(Void.class).build();

    public static final ClassType BOOLEAN = new Builder(false).knownClass(Boolean.class)
        .defaultValueExpressionConverter(String::toLowerCase)
        .jsonToken("JsonToken.BOOLEAN")
        .jsonDeserializationMethod("getNullable(JsonReader::getBoolean)")
        .serializationMethodBase("writeBoolean")
        .xmlElementDeserializationMethod("getNullableElement(Boolean::parseBoolean)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, Boolean::parseBoolean)")
        .build();

    public static final ClassType BYTE = new Builder(false).knownClass(Byte.class)
        .jsonDeserializationMethod("getNullable(JsonReader::getInt)")
        .jsonToken("JsonToken.NUMBER")
        .serializationMethodBase("writeNumber")
        .xmlElementDeserializationMethod("getNullableElement(Byte::parseByte)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, Byte::parseByte)")
        .build();

    public static final ClassType INTEGER = new Builder(false).knownClass(Integer.class)
        .defaultValueExpressionConverter(Function.identity())
        .jsonToken("JsonToken.NUMBER")
        .jsonDeserializationMethod("getNullable(JsonReader::getInt)")
        .serializationMethodBase("writeNumber")
        .xmlElementDeserializationMethod("getNullableElement(Integer::parseInt)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, Integer::parseInt)")
        .build();

    public static final ClassType LONG = new Builder(false)
        .prototypeAsLong()
        .build();

    public static final ClassType FLOAT = new Builder(false).knownClass(Float.class)
        .defaultValueExpressionConverter(defaultValueExpression -> Float.parseFloat(defaultValueExpression) + "F")
        .jsonToken("JsonToken.NUMBER")
        .jsonDeserializationMethod("getNullable(JsonReader::getFloat)")
        .serializationMethodBase("writeNumber")
        .xmlElementDeserializationMethod("getNullableElement(Float::parseFloat)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, Float::parseFloat)")
        .build();

    public static final ClassType DOUBLE = new Builder(false).knownClass(Double.class)
        .prototypeAsDouble()
        .build();

    public static final ClassType CHARACTER = new Builder(false).knownClass(Character.class)
        .defaultValueExpressionConverter(defaultValueExpression -> String.valueOf((defaultValueExpression.charAt(0))))
        .jsonToken("JsonToken.STRING")
        .serializationValueGetterModifier(valueGetter -> "Objects.toString(" + valueGetter + ", null)")
        .jsonDeserializationMethod("getNullable(nonNullReader -> nonNullReader.getString().charAt(0))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(nonNullString -> nonNullString.charAt(0))")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, nonNullString -> nonNullString.charAt(0))")
        .build();

    public static final ClassType STRING = new Builder(false).knownClass(String.class)
        .defaultValueExpressionConverter(defaultValueExpression -> "\"" + TemplateUtil.escapeString(defaultValueExpression) + "\"")
        .jsonToken("JsonToken.STRING")
        .jsonDeserializationMethod("getString()")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getStringElement()")
        .xmlAttributeDeserializationTemplate("%s.getStringAttribute(%s, %s)")
        .build();

    public static final ClassType BASE_64_URL = getClassTypeBuilder("com.azure.core.util", "Base64Url")
        .serializationValueGetterModifier(valueGetter -> "Objects.toString(" + valueGetter + ", null)")
        .jsonToken("JsonToken.STRING")
        .jsonDeserializationMethod("getNullable(nonNullReader -> new Base64Url(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(Base64Url::new)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, Base64Url::new)")
        .build();

    public static final ClassType ANDROID_BASE_64_URL = new ClassType.Builder(false)
        .packageName("com.azure.android.core.util").name("Base64Url")
        .build();

    public static final ClassType LOCAL_DATE = new Builder(false).knownClass(java.time.LocalDate.class)
        .defaultValueExpressionConverter(defaultValueExpression -> "LocalDate.parse(\"" + defaultValueExpression + "\")")
        .jsonToken("JsonToken.STRING")
        .serializationValueGetterModifier(valueGetter -> "Objects.toString(" + valueGetter + ", null)")
        .jsonDeserializationMethod("getNullable(nonNullReader -> LocalDate.parse(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(LocalDate::parse)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, LocalDate::parse)")
        .build();

    public static final ClassType ANDROID_LOCAL_DATE = new ClassType.Builder(false)
        .packageName("org.threeten.bp").name("LocalDate")
        .build();

    public static final ClassType DATE_TIME = new Builder(false).knownClass(OffsetDateTime.class)
        .defaultValueExpressionConverter(defaultValueExpression -> "OffsetDateTime.parse(\"" + defaultValueExpression + "\")")
        .jsonToken("JsonToken.STRING")
        .serializationValueGetterModifier(valueGetter -> valueGetter + " == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(" + valueGetter + ")")
        .jsonDeserializationMethod("getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(dateString -> OffsetDateTime.parse(dateString))")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, dateString -> OffsetDateTime.parse(dateString))")
        .build();

    public static final ClassType DURATION = new Builder(false).knownClass(Duration.class)
        .defaultValueExpressionConverter(defaultValueExpression -> "Duration.parse(\"" + defaultValueExpression + "\")")
        .jsonToken("JsonToken.STRING")
        .serializationValueGetterModifier(valueGetter -> CORE_UTILS.getName() + ".durationToStringWithDays(" + valueGetter + ")")
        .jsonDeserializationMethod("getNullable(nonNullReader -> Duration.parse(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(Duration::parse)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, Duration::parse)")
        .build();

    public static final ClassType ANDROID_DURATION = new ClassType.Builder(false)
        .packageName("org.threeten.bp").name("Duration")
        .build();

    public static final ClassType DATE_TIME_RFC_1123 = getClassTypeBuilder("com.azure.core.util", "DateTimeRfc1123")
        .defaultValueExpressionConverter(defaultValueExpression -> "new DateTimeRfc1123(\"" + defaultValueExpression + "\")")
        .jsonToken("JsonToken.STRING")
        .serializationValueGetterModifier(valueGetter -> "Objects.toString(" + valueGetter + ", null)")
        .jsonDeserializationMethod("getNullable(nonNullReader -> new DateTimeRfc1123(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(DateTimeRfc1123::new)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, DateTimeRfc1123::new)")
        .build();

    public static final ClassType ANDROID_DATE_TIME_RFC_1123 = new ClassType.Builder(false)
        .packageName("com.azure.android.core.util").name("DateTimeRfc1123")
        .build();

    public static final ClassType BIG_DECIMAL = new Builder(false).knownClass(BigDecimal.class)
        .defaultValueExpressionConverter(defaultValueExpression -> "new BigDecimal(\"" + defaultValueExpression + "\")")
        .jsonToken("JsonToken.NUMBER")
        .serializationMethodBase("writeNumber")
        .jsonDeserializationMethod("getNullable(nonNullReader -> new BigDecimal(nonNullReader.getString()))")
        .xmlElementDeserializationMethod("getNullableElement(BigDecimal::new)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, BigDecimal::new)")
        .build();

    public static final ClassType UUID = new Builder(false).knownClass(java.util.UUID.class)
        .defaultValueExpressionConverter(defaultValueExpression -> "UUID.fromString(\"" + defaultValueExpression + "\")")
        .jsonToken("JsonToken.STRING")
        .serializationValueGetterModifier(valueGetter -> "Objects.toString(" + valueGetter + ", null)")
        .jsonDeserializationMethod("getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(UUID::fromString)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, UUID::fromString)")
        .build();

    public static final ClassType OBJECT = new ClassType.Builder(false)
        .knownClass(Object.class)
        .build();

    public static final ClassType TOKEN_CREDENTIAL = new Builder(false)
        .packageName("com.azure.core.credential").name("TokenCredential")
        .build();

    public static final ClassType ANDROID_HTTP_RESPONSE_EXCEPTION = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http.exception").name("HttpResponseException")
        .build();

    public static final ClassType UNIX_TIME_DATE_TIME = new ClassType.Builder(false)
        .defaultValueExpressionConverter(defaultValueExpression -> "OffsetDateTime.parse(\"" + defaultValueExpression + "\")")
        .jsonToken("JsonToken.STRING")
        .knownClass(OffsetDateTime.class)
        .serializationValueGetterModifier(valueGetter -> "Objects.toString(" + valueGetter + ", null)")
        .jsonDeserializationMethod("getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(OffsetDateTime::parse)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, OffsetDateTime::parse)")
        .build();

    public static final ClassType ANDROID_DATE_TIME = new ClassType.Builder(false)
        .packageName("org.threeten.bp").name("OffsetDateTime")
        .build();

    public static final ClassType UNIX_TIME_LONG = new ClassType.Builder(false)
        .prototypeAsLong()
        .build();

    public static final ClassType DURATION_LONG = new ClassType.Builder(false)
        .prototypeAsLong()
        .build();

    public static final ClassType DURATION_DOUBLE = new ClassType.Builder(false)
        .prototypeAsDouble()
        .build();

    public static final ClassType HTTP_PIPELINE = getClassTypeBuilder("com.azure.core.http", "HttpPipeline").build();

    public static final ClassType ANDROID_HTTP_PIPELINE = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http").name("HttpPipeline")
        .build();

    public static final ClassType REST_PROXY = getClassTypeBuilder("com.azure.core.http.rest", "RestProxy").build();

    public static final ClassType ANDROID_REST_PROXY = new ClassType.Builder(false)
        .packageName("com.azure.android.core.rest").name("RestProxy")
        .build();

    public static final ClassType SERIALIZER_ADAPTER = new Builder(false).packageName("com.azure.core.util.serializer")
        .name("SerializerAdapter")
        .build();
    public static final ClassType JSON_SERIALIZER = getClassTypeBuilder("com.azure.core.util.serializer", "JsonSerializer")
        .build();

    public static final ClassType ANDROID_JACKSON_SERDER = new ClassType.Builder(false)
        .packageName("com.azure.android.core.serde.jackson").name("JacksonSerder")
        .build();

    public static final ClassType FUNCTION = new ClassType.Builder(false).knownClass(Function.class).build();

    public static final ClassType BYTE_BUFFER = new ClassType.Builder(false).knownClass(ByteBuffer.class).build();

    public static final ClassType URL = new Builder(false)
        .defaultValueExpressionConverter(defaultValueExpression -> "new URL(\"" + defaultValueExpression + "\")")
        .knownClass(java.net.URL.class)
        .jsonToken("JsonToken.STRING")
        .serializationValueGetterModifier(valueGetter -> "Objects.toString(" + valueGetter + ", null)")
        .jsonDeserializationMethod("getNullable(nonNullReader -> new URL(nonNullReader.getString()))")
        .serializationMethodBase("writeString")
        .xmlElementDeserializationMethod("getNullableElement(URL::new)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, URL::new)")
        .build();

    public static final ClassType STREAM_RESPONSE = new Builder(false).packageName("com.azure.core.http.rest")
        .name("StreamResponse")
        .build();

    public static final ClassType INPUT_STREAM = new ClassType.Builder(false).knownClass(InputStream.class)
        .build();

    public static final ClassType CONTEXT = ClassType.getClassTypeBuilder("com.azure.core.util", "Context")
        .defaultValueExpressionConverter(epr -> "com.azure.core.util.Context.NONE")
        .build();

    public static final ClassType ANDROID_CONTEXT = new ClassType.Builder(false)
        .packageName("com.azure.android.core.util").name("Context")
        .build();

    public static final ClassType CLIENT_LOGGER = ClassType.getClassTypeBuilder("com.azure.core.util.logging", "ClientLogger").build();
    public static final ClassType LOG_LEVEL = ClassType.getClassTypeBuilder("com.azure.core.util.logging", "LogLevel").build();

    public static final ClassType AZURE_ENVIRONMENT = new ClassType.Builder(false)
        .packageName("com.azure.core.management").name("AzureEnvironment")
        .build();

    public static final ClassType HTTP_CLIENT = getClassTypeBuilder("com.azure.core.http", "HttpClient").build();

    public static final ClassType ANDROID_HTTP_CLIENT = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http").name("HttpClient")
        .build();

    public static final ClassType HTTP_PIPELINE_POLICY = getClassTypeBuilder("com.azure.core.http.policy", "HttpPipelinePolicy").build();

    public static final ClassType ANDROID_HTTP_PIPELINE_POLICY = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http").name("HttpPipelinePolicy")
        .build();

    public static final ClassType HTTP_LOG_OPTIONS = getClassTypeBuilder("com.azure.core.http.policy", "HttpLogOptions").build();

    public static final ClassType ANDROID_HTTP_LOG_OPTIONS = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http.policy").name("HttpLogOptions")
        .build();

    public static final ClassType CONFIGURATION = getClassTypeBuilder("com.azure.core.util", "Configuration").build();

    public static final ClassType SERVICE_VERSION = new ClassType.Builder(false).knownClass(ServiceVersion.class)
        .build();

    public static final ClassType AZURE_KEY_CREDENTIAL = new Builder(false)
        .packageName("com.azure.core.credential").name("AzureKeyCredential")
        .build();

    public static final ClassType KEY_CREDENTIAL = getClassTypeBuilder("com.azure.core.credential", "KeyCredential").build();

    public static final ClassType RETRY_POLICY = new Builder(false).packageName("com.azure.core.http.policy").name("RetryPolicy").build();

    public static final ClassType RETRY_OPTIONS = getClassTypeBuilder("com.azure.core.http.policy", "RetryOptions").build();

    public static final ClassType ANDROID_RETRY_POLICY = new ClassType.Builder(false)
        .packageName("com.azure.android.core.http.policy").name("RetryPolicy")
        .build();

    public static final ClassType JSON_PATCH_DOCUMENT = new Builder(false).packageName("com.azure.core.models")
        .name("JsonPatchDocument")
        .jsonToken("JsonToken.START_OBJECT")
        .build();

    public static final ClassType BINARY_DATA = getClassTypeBuilder("com.azure.core.util", "BinaryData")
        .defaultValueExpressionConverter(defaultValueExpression -> "BinaryData.fromObject(\"" + defaultValueExpression + "\")")
        // do not use the "writeUntyped(nullableVar)", because some backend would fail the request on "null" value
//        .serializationMethodBase("writeUntyped")
//        .serializationValueGetterModifier(valueGetter -> valueGetter + " == null ? null : " + valueGetter + ".toObject(Object.class)")
        .jsonDeserializationMethod("getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()))")
        .xmlElementDeserializationMethod("getNullableElement(BinaryData::fromObject)")
        .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, BinaryData::fromObject)")
        .build();

    public static final ClassType REQUEST_OPTIONS = getClassTypeBuilder("com.azure.core.http.rest", "RequestOptions").build();
    public static final ClassType CLIENT_OPTIONS = getClassTypeBuilder("com.azure.core.util", "ClientOptions").build();
    public static final ClassType HTTP_REQUEST = getClassTypeBuilder("com.azure.core.http", "HttpRequest").build();
    public static final ClassType HTTP_HEADERS = getClassTypeBuilder("com.azure.core.http", "HttpHeaders").build();
    public static final ClassType HTTP_HEADER_NAME = getClassTypeBuilder("com.azure.core.http", "HttpHeaderName").build();

    // Java exception types
    public static final ClassType HTTP_RESPONSE_EXCEPTION = getClassTypeBuilder("com.azure.core.exception", "HttpResponseException").build();
    public static final ClassType CLIENT_AUTHENTICATION_EXCEPTION = getClassTypeBuilder("com.azure.core.exception", "ClientAuthenticationException")
        .build();
    public static final ClassType RESOURCE_EXISTS_EXCEPTION = getClassTypeBuilder("com.azure.core.exception", "ResourceExistsException")
        .build();
    public static final ClassType RESOURCE_MODIFIED_EXCEPTION = getClassTypeBuilder("com.azure.core.exception", "ResourceModifiedException")
        .build();
    public static final ClassType RESOURCE_NOT_FOUND_EXCEPTION = getClassTypeBuilder("com.azure.core.exception", "ResourceNotFoundException")
        .build();
    public static final ClassType TOO_MANY_REDIRECTS_EXCEPTION = getClassTypeBuilder("com.azure.core.exception", "TooManyRedirectsException")
        .build();
    public static final ClassType RESPONSE_ERROR = new Builder()
        .packageName("com.azure.core.models").name("ResponseError")
        .jsonToken("JsonToken.START_OBJECT")
        .build();
    public static final ClassType RESPONSE_INNER_ERROR = new Builder()
        .packageName("com.azure.core.models").name("ResponseInnerError")
        .jsonToken("JsonToken.START_OBJECT")
        .build();

    private final String fullName;
    private final String packageName;
    private final String name;
    private final List<String> implementationImports;
    private final XmsExtensions extensions;
    private final Function<String, String> defaultValueExpressionConverter;
    private final boolean isSwaggerType;
    private final Function<String, String> serializationValueGetterModifier;
    private final String jsonToken;
    private final String serializationMethodBase;
    private final String jsonDeserializationMethod;
    private final String xmlAttributeDeserializationTemplate;
    private final String xmlElementDeserializationMethod;
    private final boolean usedInXml;

    private ClassType(String packageKeyword, String name, List<String> implementationImports, XmsExtensions extensions,
        Function<String, String> defaultValueExpressionConverter, boolean isSwaggerType, String jsonToken,
        String serializationMethodBase, Function<String, String> serializationValueGetterModifier,
        String jsonDeserializationMethod, String xmlAttributeDeserializationTemplate,
        String xmlElementDeserializationMethod, boolean usedInXml) {
        this.fullName = packageKeyword + "." + name;
        this.packageName = packageKeyword;
        this.name = name;
        this.implementationImports = implementationImports;
        this.extensions = extensions;
        this.defaultValueExpressionConverter = defaultValueExpressionConverter;
        this.isSwaggerType = isSwaggerType;
        this.jsonToken = jsonToken;
        this.serializationMethodBase = serializationMethodBase;
        this.serializationValueGetterModifier = serializationValueGetterModifier;
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

    private Function<String, String> getDefaultValueExpressionConverter() {
        return defaultValueExpressionConverter;
    }

    public final boolean isBoxedType() {
        // TODO (alzimmer): This should be a property on the ClassType
        return this.equals(ClassType.VOID)
            || this.equals(ClassType.BOOLEAN)
            || this.equals(ClassType.BYTE)
            || this.equals(ClassType.INTEGER)
            || this.equals(ClassType.LONG)
            || this.equals(ClassType.FLOAT)
            || this.equals(ClassType.DOUBLE);
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

        if (this == ClassType.UNIX_TIME_LONG) {
            imports.add(Instant.class.getName());
            imports.add(ZoneOffset.class.getName());
        }

        if (this == ClassType.DATE_TIME) {
            imports.add(DateTimeFormatter.class.getName());
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
                result = "new " + this + "()";
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
        if (this == ClassType.DATE_TIME_RFC_1123) {
            clientType = ClassType.DATE_TIME;
        } else if (this == ClassType.UNIX_TIME_LONG) {
            clientType = ClassType.DATE_TIME;
        } else if (this == ClassType.BASE_64_URL) {
            clientType = ArrayType.BYTE_ARRAY;
        } else if (this == ClassType.DURATION_LONG) {
            clientType = ClassType.DURATION;
        } else if (this == ClassType.DURATION_DOUBLE) {
            clientType = ClassType.DURATION;
        }
        return clientType;
    }

    public String convertToClientType(String expression) {
        if (this == ClassType.DATE_TIME_RFC_1123 || this == ClassType.ANDROID_DATE_TIME_RFC_1123) {
            expression = expression + ".getDateTime()";
        } else if (this == ClassType.UNIX_TIME_LONG) {
            expression = "OffsetDateTime.ofInstant(Instant.ofEpochSecond(" + expression + "), ZoneOffset.UTC)";
        } else if (this == ClassType.BASE_64_URL) {
            expression = expression + ".decodedBytes()";
        } else if (this == ClassType.URL) {
            expression = "new URL(" + expression + ")";
        } else if (this == ClassType.DURATION_LONG) {
            expression = "Duration.ofSeconds(" + expression + ")";
        } else if (this == ClassType.DURATION_DOUBLE) {
            expression = "Duration.ofNanos((long) (" + expression + " * 1000_000_000L))";
        }

        return expression;
    }

    public String convertFromClientType(String expression) {
        if (this == ClassType.DATE_TIME_RFC_1123 || this == ClassType.ANDROID_DATE_TIME_RFC_1123) {
            expression = "new DateTimeRfc1123(" + expression + ")";
        } else if (this == ClassType.UNIX_TIME_LONG) {
            expression = expression + ".toEpochSecond()";
        } else if (this == ClassType.BASE_64_URL) {
            expression = "Base64Url.encode(" + expression + ")";
        } else if (this == ClassType.URL) {
            expression = expression + ".toString()";
        } else if (this == ClassType.DURATION_LONG) {
            expression = expression + ".getSeconds()";
        } else if (this == ClassType.DURATION_DOUBLE) {
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
    public String jsonToken() {
        return jsonToken;
    }

    @Override
    public String jsonDeserializationMethod(String jsonReaderName) {
        if (jsonDeserializationMethod == null) {
            return null;
        }

        return jsonReaderName + "." + jsonDeserializationMethod;
    }

    @Override
    public String jsonSerializationMethodCall(String jsonWriterName, String fieldName, String valueGetter) {
        if (!isSwaggerType && ExtensionUtils.isNullOrEmpty(serializationMethodBase)) {
            return null;
        }

        String methodBase = isSwaggerType ? "writeJson" : serializationMethodBase;
        String value = serializationValueGetterModifier != null
            ? serializationValueGetterModifier.apply(valueGetter) : valueGetter;

        return fieldName == null
            ? jsonWriterName + "." + methodBase + "(" + value + ")"
            : jsonWriterName + "." + methodBase + "Field(\"" + fieldName + "\", " + value + ")";
    }

    @Override
    public String xmlDeserializationMethod(String xmlReaderName, String attributeName, String attributeNamespace,
        boolean namespaceIsConstant) {
        if (attributeName == null) {
            return xmlReaderName + "." + xmlElementDeserializationMethod;
        } else if (attributeNamespace == null) {
            return String.format(xmlAttributeDeserializationTemplate, xmlReaderName, "null",
                "\"" + attributeName + "\"");
        } else {
            String namespace = namespaceIsConstant ? attributeNamespace : "\"" + attributeNamespace + "\"";
            return String.format(xmlAttributeDeserializationTemplate, xmlReaderName, namespace,
                "\"" + attributeName + "\"");
        }
    }

    @Override
    public String xmlSerializationMethodCall(String xmlWriterName, String attributeOrElementName, String namespaceUri,
        String valueGetter, boolean isAttribute, boolean nameIsVariable, boolean namespaceIsConstant) {
        if (isSwaggerType) {
            if (isAttribute) {
                throw new RuntimeException("Swagger types cannot be written as attributes.");
            }

            return xmlWriterName + ".writeXml(" + valueGetter + ", \"" + attributeOrElementName + "\")";
        }

        String value = serializationValueGetterModifier != null
            ? serializationValueGetterModifier.apply(valueGetter) : valueGetter;
        return xmlSerializationCallHelper(xmlWriterName, serializationMethodBase, attributeOrElementName, namespaceUri,
            value, isAttribute, nameIsVariable, namespaceIsConstant);
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
        private Function<String, String> defaultValueExpressionConverter;
        private Function<String, String> serializationValueGetterModifier;
        private String jsonToken;
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
                .jsonToken("JsonToken.NUMBER")
                .serializationMethodBase("writeNumber")
                .jsonDeserializationMethod("getNullable(JsonReader::getLong)")
                .xmlElementDeserializationMethod("getNullableElement(Long::parseLong)")
                .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, Long::parseLong)");
        }

        public Builder prototypeAsDouble() {
            return this.knownClass(Double.class)
                .defaultValueExpressionConverter(defaultValueExpression -> java.lang.String.valueOf(java.lang.Double.parseDouble(defaultValueExpression)) + 'D')
                .jsonToken("JsonToken.NUMBER")
                .serializationMethodBase("writeNumber")
                .jsonDeserializationMethod("getNullable(JsonReader::getDouble)")
                .xmlElementDeserializationMethod("getNullableElement(Double::parseDouble)")
                .xmlAttributeDeserializationTemplate("%s.getNullableAttribute(%s, %s, Double::parseDouble)");
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

        public Builder defaultValueExpressionConverter(Function<String, String> defaultValueExpressionConverter) {
            this.defaultValueExpressionConverter = defaultValueExpressionConverter;
            return this;
        }

        public Builder jsonToken(String jsonToken) {
            this.jsonToken = jsonToken;
            return this;
        }

        public Builder serializationValueGetterModifier(Function<String, String> serializationValueGetterModifier) {
            this.serializationValueGetterModifier = serializationValueGetterModifier;
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
                isSwaggerType, jsonToken, serializationMethodBase, serializationValueGetterModifier,
                jsonDeserializationMethod, xmlAttributeDeserializationTemplate, xmlElementDeserializationMethod,
                usedInXml);
        }
    }

    static String xmlSerializationCallHelper(String writer, String method, String xmlName, String namespace,
        String value, boolean isAttribute, boolean nameIsVariable, boolean namespaceIsConstant) {
        String name = (xmlName == null) ? null
            : nameIsVariable ? xmlName : "\"" + xmlName + "\"";
        namespace = (namespace == null) ? null
            : namespaceIsConstant ? namespace : "\"" + namespace + "\"";

        if (isAttribute) {
            method = method + "Attribute";
            return (namespace == null)
                ? writer + "." + method + "(" + name + ", " + value + ")"
                : writer + "." + method + "(" + namespace + ", " + name + ", " + value + ")";
        }

        if (name == null) {
            return writer + "." + method + "(" + value + ")";
        } else {
            method = method + "Element";
            return (namespace == null)
                ? writer + "." + method + "(" + name + ", " + value + ")"
                : writer + "." + method + "(" + namespace + ", " + name + ", " + value + ")";
        }
    }
}
