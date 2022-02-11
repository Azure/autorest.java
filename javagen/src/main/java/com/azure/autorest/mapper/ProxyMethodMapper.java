// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.XmsExampleWrapper;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.http.HttpMethod;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Maps Swagger definition into the interface methods that RestProxy consumes.
 */
public class ProxyMethodMapper implements IMapper<Operation, Map<Request, ProxyMethod>> {

    private final Logger LOGGER = new PluginLogger(Javagen.getPluginInstance(), ProxyMethodMapper.class);

    private static final List<IType> unixTimeTypes = Arrays.asList(PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong
        , ClassType.UnixTimeDateTime);
    private static final List<IType> returnValueWireTypeOptions = Stream.concat(Stream.of(ClassType.Base64Url, ClassType.DateTimeRfc1123), unixTimeTypes.stream()).collect(Collectors.toList());
    private static final ProxyMethodMapper INSTANCE = new ProxyMethodMapper();

    private static final Pattern APOSTROPHE = Pattern.compile("'");

    private final Map<Request, ProxyMethod> parsed = new ConcurrentHashMap<>();
    protected ProxyMethodMapper() {
    }

    public static ProxyMethodMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Map<Request, ProxyMethod> map(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();
        Map<Request, ProxyMethod> result = new LinkedHashMap<>();

        String operationName = operation.getLanguage().getJava().getName();
        ProxyMethod.Builder builder = createProxyMethodBuilder()
                .description(operation.getDescription())
                .name(operationName)
                .isResumable(false);

        String operationId = null;
        if (operation.getLanguage() != null && operation.getLanguage().getDefault() != null) {  // "default" could be null for generated method like "listNext"
            if (operation.getOperationGroup() != null
                    && operation.getOperationGroup().getLanguage() != null
                    && operation.getOperationGroup().getLanguage().getDefault() != null
                    && !CoreUtils.isNullOrEmpty(operation.getOperationGroup().getLanguage().getDefault().getName())
                    // hack for Fluent, as Lite use "ResourceProvider" if operation group is unnamed
                    && !(settings.isFluent() && "ResourceProvider".equals(operation.getOperationGroup().getLanguage().getDefault().getName()))) {
                operationId = operation.getOperationGroup().getLanguage().getDefault().getName() + "_" + operation.getLanguage().getDefault().getName();
            } else {
                operationId = operation.getLanguage().getDefault().getName();
            }
            builder.operationId(operationId);
        }

        List<Integer> expectedStatusCodes = operation.getResponses().stream()
                .flatMap(r -> r.getProtocol().getHttp().getStatusCodes().stream())
                .map(s -> APOSTROPHE.matcher(s).replaceAll(""))
                .map(Integer::parseInt)
                .sorted().collect(Collectors.toList());
        builder.responseExpectedStatusCodes(expectedStatusCodes);

        IType responseBodyType = SchemaUtil.getOperationResponseType(operation);
        if (settings.isLowLevelClient()) {
            builder.rawResponseBodyType(responseBodyType);
            if (responseBodyType instanceof ClassType || responseBodyType instanceof ListType || responseBodyType instanceof MapType) {
                responseBodyType = ClassType.BinaryData;
            } else if (responseBodyType instanceof EnumType) {
                responseBodyType = ClassType.String;
            }
        }
        builder.responseBodyType(responseBodyType);

        if (settings.isLowLevelClient()) {
            IType singleValueType;
            if (responseBodyType.equals(PrimitiveType.Void)) {
                singleValueType = GenericType.Response(ClassType.Void);
            } else {
                singleValueType = GenericType.Response(responseBodyType);
            }
            builder.returnType(createSingleValueAsyncReturnType(singleValueType));
        } else if (operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation() && settings.isFluent()
                && (operation.getExtensions().getXmsPageable() == null || !(operation.getExtensions().getXmsPageable().getNextOperation() == operation))
                && operation.getResponses().stream().noneMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {  // temporary skip InputStream, no idea how to do this in PollerFlux
            builder.returnType(createBinaryContentAsyncReturnType());
        } else if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
            // BinaryResponse
            IType singleValueType = ClassType.StreamResponse;
            builder.returnType(GenericType.Mono(singleValueType));
        } else if (SchemaUtil.responseContainsHeaderSchemas(operation)) {
            // SchemaResponse
            // method with schema in headers would require a ClientResponse
            ClassType clientResponseClassType = ClientMapper.getClientResponseClassType(operation, settings);
            builder.returnType(createAsyncResponseReturnType(clientResponseClassType));
        } else {
            IType singleValueType;
            if (responseBodyType.equals(GenericType.FluxByteBuffer)) {
                singleValueType = ClassType.StreamResponse;
            } else if (responseBodyType.equals(PrimitiveType.Void)) {
                singleValueType = GenericType.Response(ClassType.Void);
            } else {
                singleValueType = GenericType.Response(responseBodyType);
            }
            builder.returnType(createSingleValueAsyncReturnType(singleValueType));
        }

        buildUnexpectedResponseExceptionTypes(builder, operation, expectedStatusCodes, settings);

        AtomicReference<IType> responseBodyTypeReference = new AtomicReference<>(responseBodyType);
        builder.returnValueWireType(returnValueWireTypeOptions
                .stream()
                .filter(type -> responseBodyTypeReference.get().contains(type))
                .findFirst()
                .orElse(null));

        Set<String> responseContentTypes = operation.getResponses().stream()
                .filter(r -> r.getProtocol() != null && r.getProtocol().getHttp() != null && r.getProtocol().getHttp().getMediaTypes() != null)
                .flatMap(r -> r.getProtocol().getHttp().getMediaTypes().stream())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
        if (!responseContentTypes.contains("application/json")) {
            responseContentTypes.add("application/json;q=0.9");
        }
        builder.responseContentTypes(responseContentTypes);

        // Low-level client only requires one request per operation
        List<Request> requests = operation.getRequests();
        if (settings.isLowLevelClient()) {
            requests = Collections.singletonList(requests.get(0));
        }

        // Used to deduplicate method with same signature.
        // E.g. one request takes "application/json" and another takes "text/plain", which both are String type
        Set<List<String>> methodSignatures = new HashSet<>();

        for (Request request : requests) {
            if (parsed.containsKey(request)) {
                result.put(request, parsed.get(request));
                continue;
            }

            String requestContentType = "application/json";

            // check for mediaTypes first as that is more specific than the knownMediaType
            // if there are multiple, we'll use the generic type
            if (request.getProtocol().getHttp().getMediaTypes() != null
                && request.getProtocol().getHttp().getMediaTypes().size() == 1) {
                requestContentType = request.getProtocol().getHttp().getMediaTypes().get(0);
            } else if (request.getProtocol().getHttp().getKnownMediaType() != null) {
                requestContentType = request.getProtocol().getHttp().getKnownMediaType().getContentType();
            }
            builder.requestContentType(requestContentType);
            builder.baseURL(request.getProtocol().getHttp().getUri());
            builder.urlPath(request.getProtocol().getHttp().getPath());
            builder.httpMethod(HttpMethod.valueOf(request.getProtocol().getHttp().getMethod().toUpperCase()));

            List<ProxyMethodParameter> parameters = new ArrayList<>();
            List<ProxyMethodParameter> allParameters = new ArrayList<>();
            for (Parameter parameter : request.getParameters().stream()
                    .filter(p -> p.getProtocol() != null && p.getProtocol().getHttp() != null)
                    .collect(Collectors.toList())) {
                parameter.setOperation(operation);
                ProxyMethodParameter proxyMethodParameter = Mappers.getProxyParameterMapper().map(parameter);
                if (requestContentType.startsWith("application/json-patch+json")) {
                    proxyMethodParameter = CustomProxyParameterMapper.getInstance().map(parameter);
                }
                allParameters.add(proxyMethodParameter);
                if (!settings.isLowLevelClient()) {
                    parameters.add(proxyMethodParameter);
                } else {
                    // LLC move most query and header parameters to RequestOptions
                    final boolean parameterIsRequiredAndPathOrBody = parameter.isRequired()
                            && parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.HEADER
                            && parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.QUERY;
                    final boolean parameterIsClientOrApiVersion = ClientModelUtil.getClientDefaultValueOrConstantValue(parameter) != null
                            && parameter.getLanguage().getJava().getName().equalsIgnoreCase("apiversion");
                    if (parameterIsRequiredAndPathOrBody || parameterIsClientOrApiVersion) {
                        parameters.add(proxyMethodParameter);
                    }
                }
            }

            String name = deduplicateMethodName(operationName, parameters, requestContentType, methodSignatures);
            builder.name(name);

            // RequestOptions
            if (settings.isLowLevelClient()) {
                ProxyMethodParameter requestOptions = new ProxyMethodParameter.Builder()
                        .description("The options to configure the HTTP request before HTTP client sends it")
                        .wireType(ClassType.RequestOptions)
                        .clientType(ClassType.RequestOptions)
                        .name("requestOptions")
                        .requestParameterLocation(RequestParameterLocation.NONE)
                        .requestParameterName("requestOptions")
                        .alreadyEncoded(true)
                        .isConstant(false)
                        .isRequired(false)
                        .isNullable(false)
                        .fromClient(false)
                        .parameterReference("requestOptions")
                        .build();
                allParameters.add(requestOptions);
                parameters.add(requestOptions);
            }

            if (settings.getAddContextParameter()) {
                ClassType contextClassType = getContextClass();
                ProxyMethodParameter contextParameter = new ProxyMethodParameter.Builder()
                        .description("The context to associate with this operation.")
                        .wireType(contextClassType)
                        .clientType(contextClassType)
                        .name("context")
                        .requestParameterLocation(RequestParameterLocation.NONE)
                        .requestParameterName("context")
                        .alreadyEncoded(true)
                        .isConstant(false)
                        .isRequired(false)
                        .isNullable(false)
                        .fromClient(false)
                        .parameterReference("context")
                        .build();
                allParameters.add(contextParameter);
                parameters.add(contextParameter);
            }
            appendCallbackParameter(parameters, responseBodyType);
            builder.allParameters(allParameters);
            builder.parameters(parameters);

            if (operation.getExtensions() != null && operation.getExtensions().getXmsExamples() != null
                    && operation.getExtensions().getXmsExamples().getExamples() != null
                    && !operation.getExtensions().getXmsExamples().getExamples().isEmpty()) {
                String operationIdLocal = operationId;
                Map<String, ProxyMethodExample> examples = operation.getExtensions().getXmsExamples().getExamples().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> Mappers.getProxyMethodExampleMapper().map(new XmsExampleWrapper(e.getValue(), operationIdLocal))));
                builder.examples(examples);
            }

            ProxyMethod proxyMethod = builder.build();

            result.put(request, proxyMethod);
            parsed.put(request, proxyMethod);
        }
        return result;
    }

    protected ClassType getContextClass() {
        return ClassType.Context;
    }

    protected void appendCallbackParameter(List<ProxyMethodParameter> parameters, IType responseBodyType) {
    }

    protected IType createSingleValueAsyncReturnType(IType singleValueType) {
        return GenericType.Mono(singleValueType);
    }

    protected IType createAsyncResponseReturnType(ClassType clientResponseClassType) {
        return GenericType.Mono(clientResponseClassType);
    }

    protected IType createStreamContentAsyncReturnType() {
        IType singleValueType = ClassType.StreamResponse;
        return GenericType.Mono(singleValueType);
    }

    protected IType createBinaryContentAsyncReturnType() {
        IType returnType = GenericType.Response(GenericType.FluxByteBuffer);    // raw response for LRO
        return GenericType.Mono(returnType);
    }

    protected ProxyMethod.Builder createProxyMethodBuilder() {
        return new ProxyMethod.Builder();
    }

    /**
     * Extension for configure on unexpected response exception types to builder.
     *
     * @param builder the ProxyMethod builder
     * @param operation the operation
     * @param expectedStatusCodes the expected status codes
     * @param settings the settings
     */
    protected void buildUnexpectedResponseExceptionTypes(ProxyMethod.Builder builder, Operation operation,
        List<Integer> expectedStatusCodes, JavaSettings settings) {
        SwaggerExceptionDefinitions swaggerExceptionDefinitions = getSwaggerExceptionDefinitions(operation, settings);
        ClassType settingsDefaultExceptionType = getDefaultHttpExceptionTypeFromSettings(settings);

        // Use the settings defined default exception type over the Swagger defined default exception type.
        ClassType defaultErrorType = (settingsDefaultExceptionType == null)
            ? swaggerExceptionDefinitions.defaultExceptionType
            : settingsDefaultExceptionType;

        if (defaultErrorType != null && !settings.isLowLevelClient()) {
            builder.unexpectedResponseExceptionType(defaultErrorType);
        } else {
            builder.unexpectedResponseExceptionType(getHttpResponseExceptionType());
        }

        Map<Integer, ClassType> settingsExceptionTypeMap =
            getHttpStatusToExceptionTypeMappingFromSettings(settings);

        // Initialize the merged map with the Swagger defined configurations so that the settings configurations
        // overrides it.
        Map<Integer, ClassType> mergedExceptionTypeMapping = new HashMap<>(
            swaggerExceptionDefinitions.exceptionTypeMapping);
        mergedExceptionTypeMapping.putAll(settingsExceptionTypeMap);

        if (!settings.isLowLevelClient()) {
            // Convert the exception type mapping into what code generation uses elsewhere.
            Map<ClassType, List<Integer>> processedMapping = new HashMap<>();
            for (Map.Entry<Integer, ClassType> kvp : mergedExceptionTypeMapping.entrySet()) {
                processedMapping.compute(kvp.getValue(), (errorType, statuses) -> {
                    if (statuses == null) {
                        List<Integer> statusList = new ArrayList<>();
                        statusList.add(kvp.getKey());
                        return statusList;
                    }

                    statuses.add(kvp.getKey());
                    return statuses;
                });
            }

            if (!processedMapping.isEmpty()) {
                builder.unexpectedResponseExceptionTypes(processedMapping);
            }
        }
    }

    private static SwaggerExceptionDefinitions getSwaggerExceptionDefinitions(Operation operation,
        JavaSettings settings) {
        SwaggerExceptionDefinitions exceptionDefinitions = new SwaggerExceptionDefinitions();
        ClassType swaggerDefaultExceptionType = null;
        Map<Integer, ClassType> swaggerExceptionTypeMap = new HashMap<>();

        /*
        1. If exception has valid numeric status codes, group them to unexpectedResponseExceptionTypes
        2. If exception does not have status codes, or have 'default' or invalid number, put the first to unexpectedResponseExceptionType, ignore the rest
        3. After processing, if no model in unexpectedResponseExceptionType, take any from unexpectedResponseExceptionTypes and put it to unexpectedResponseExceptionType
         */
        if (operation.getExceptions() != null && !operation.getExceptions().isEmpty()) {
            for (Response exception : operation.getExceptions()) {
                // Exception doesn't have HTTP configurations, skip it.
                if (exception.getProtocol() == null || exception.getProtocol().getHttp() == null) {
                    continue;
                }

                boolean isDefaultError = true;
                List<String> statusCodes = exception.getProtocol().getHttp().getStatusCodes();
                if (statusCodes != null && !statusCodes.isEmpty()) {
                    try {
                        ClassType exceptionType = getExceptionType(exception, settings);
                        statusCodes.stream().map(Integer::parseInt)
                            .forEach(status -> swaggerExceptionTypeMap.put(status, exceptionType));

                        isDefaultError = false;
                    } catch (NumberFormatException ex) {
                        // statusCodes can be 'default'
                        //logger.warn("Failed to parse status code, exception {}", ex.toString());
                    }
                }

                if (swaggerDefaultExceptionType == null && isDefaultError && exception.getSchema() != null) {
                    swaggerDefaultExceptionType = processExceptionClassType(
                        (ClassType) Mappers.getSchemaMapper().map(exception.getSchema()), settings);
                }
            }

            if (swaggerDefaultExceptionType == null) {
                // no default error, use the 1st to keep backward compatibility
                swaggerDefaultExceptionType = processExceptionClassType(
                    (ClassType) Mappers.getSchemaMapper().map(operation.getExceptions().get(0).getSchema()), settings);
            }
        }

        exceptionDefinitions.defaultExceptionType = swaggerDefaultExceptionType;
        exceptionDefinitions.exceptionTypeMapping = swaggerExceptionTypeMap;

        return exceptionDefinitions;
    }

    private static final class SwaggerExceptionDefinitions {
        private ClassType defaultExceptionType;
        private Map<Integer, ClassType> exceptionTypeMapping;
    }

    private static ClassType getExceptionType(Response exception, JavaSettings settings) {
        ClassType exceptionType = ClassType.HttpResponseException;  // default as HttpResponseException

        if (exception != null && exception.getSchema() != null) {
            ClassType errorType = (ClassType) Mappers.getSchemaMapper().map(exception.getSchema());
            if (errorType != null) {
                exceptionType = processExceptionClassType(errorType, settings);
            }
        }

        return exceptionType;
    }

    private static ClassType processExceptionClassType(ClassType errorType, JavaSettings settings) {
        if (errorType == null) {
            return null;
        }

        String exceptionName = errorType.getExtensions() == null ? null : errorType.getExtensions().getXmsClientName();
        if (exceptionName == null || exceptionName.isEmpty()) {
            exceptionName = errorType.getName();
            exceptionName += "Exception";
        }

        String exceptionPackage = (settings.isCustomType(exceptionName))
            ? settings.getPackage(settings.getCustomTypesSubpackage())
            : settings.getPackage(settings.getModelsSubpackage());

        return new ClassType.Builder()
            .packageName(exceptionPackage)
            .name(exceptionName)
            .build();
    }

    private String deduplicateMethodName(String operationName, List<ProxyMethodParameter> parameters,
                                         String requestContentType,
                                         Set<List<String>> methodSignatures) {
        String name = operationName;
        List<String> methodSignature = new ArrayList<>();
        methodSignature.add(operationName);
        methodSignature.addAll(parameters.stream()
                .map(p -> p.getWireType().toString())   // simple class name should be enough?
                .collect(Collectors.toList()));
        if (methodSignatures.contains(methodSignature)) {
            // got a conflict on method signature
            String conflictMethodSignature = methodSignature.toString();

            // first try to append media type
            if (!CoreUtils.isNullOrEmpty(requestContentType)) {
                methodSignature.set(0,
                        operationName + CodeNamer.toPascalCase(CodeNamer.removeInvalidCharacters(requestContentType)));
            }

            // if not working, then just append increasing index no.
            int indexNo = 1;
            while (methodSignatures.contains(methodSignature)) {
                methodSignature.set(0, operationName + indexNo);
                ++indexNo;
            }

            // let's hope the new name does not conflict with name from another operation
            name = methodSignature.get(0);
            LOGGER.warn("Rename method to '{}', due to conflict on method signature {}",
                    name,
                    conflictMethodSignature);
        }
        methodSignatures.add(methodSignature);
        return name;
    }

    private static ClassType getDefaultHttpExceptionTypeFromSettings(JavaSettings settings) {
        String defaultHttpExceptionType = settings.getDefaultHttpExceptionType();

        return CoreUtils.isNullOrEmpty(defaultHttpExceptionType)
            ? null
            : createExceptionTypeFromFullyQualifiedClass(defaultHttpExceptionType);
    }

    private Map<Integer, ClassType> getHttpStatusToExceptionTypeMappingFromSettings(
        JavaSettings settings) {
        // Use a status code to error type mapping initial so that the custom mapping can override the default mapping,
        // if the default mapping is being used.
        Map<Integer, ClassType> exceptionMapping = new HashMap<>();

        if (settings.isUseDefaultHttpStatusCodeToExceptionTypeMapping()) {
            exceptionMapping.putAll(getDefaultHttpStatusCodeToExceptionTypeMapping());
        }

        Map<Integer, String> customExceptionMapping = settings.getHttpStatusCodeToExceptionTypeMapping();
        if (!CoreUtils.isNullOrEmpty(customExceptionMapping)) {
            customExceptionMapping.forEach((key, value) ->
                exceptionMapping.put(key, createExceptionTypeFromFullyQualifiedClass(value)));
        }

        return exceptionMapping;
    }

    private static ClassType createExceptionTypeFromFullyQualifiedClass(String fullyQualifiedClass) {
        int classStart = fullyQualifiedClass.lastIndexOf(".");
        return new ClassType.Builder()
            .packageName(fullyQualifiedClass.substring(0, classStart))
            .name(fullyQualifiedClass.substring(classStart + 1))
            .build();
    }

    /**
     * Gets the default HTTP status code to exception type mapping.
     * <p>
     * This is only used when {@link JavaSettings#isUseDefaultHttpStatusCodeToExceptionTypeMapping()} is true. The
     * values in this mapping may also be overridden if {@link JavaSettings#getHttpStatusCodeToExceptionTypeMapping()}
     * is configured.
     *
     * @return The default HTTP status code to exception type mapping.
     */
    protected Map<Integer, ClassType> getDefaultHttpStatusCodeToExceptionTypeMapping() {
        Map<Integer, ClassType> defaultMapping = new HashMap<>();
        defaultMapping.put(401, ClassType.ClientAuthenticationException);
        defaultMapping.put(404, ClassType.ResourceNotFoundException);
        defaultMapping.put(409, ClassType.ResourceModifiedException);

        return defaultMapping;
    }

    /**
     * Gets the default HTTP response exception type.
     * <p>
     * The returned exception type is used as the default HTTP exception when both the Swagger doesn't define an HTTP
     * exception type and {@link JavaSettings} doesn't contain {@link JavaSettings#getDefaultHttpExceptionType()}.
     *
     * @return The default HTTP response exception type.
     */
    protected ClassType getHttpResponseExceptionType() {
        return ClassType.HttpResponseException;
    }
}
