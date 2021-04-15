package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProxyMethodMapper implements IMapper<Operation, Map<Request, ProxyMethod>> {
    private static final List<IType> unixTimeTypes = Arrays.asList(PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong
        , ClassType.UnixTimeDateTime);
    private static final List<IType> returnValueWireTypeOptions = Stream.concat(Stream.of(ClassType.Base64Url, ClassType.DateTimeRfc1123), unixTimeTypes.stream()).collect(Collectors.toList());
    private static ProxyMethodMapper instance = new ProxyMethodMapper();

//    private static final jdk.nashorn.internal.runtime.regexp.joni.Regex methodTypeLeading = new Regex("^/+");
//    private static final Regex methodTypeTrailing = new Regex("/+$");
    private Map<Request, ProxyMethod> parsed = new HashMap<>();
    protected ProxyMethodMapper() {
    }

    public static ProxyMethodMapper getInstance() {
        return instance;
    }

    @Override
    public Map<Request, ProxyMethod> map(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();
        Map<Request, ProxyMethod> result = new LinkedHashMap<>();

        ProxyMethod.Builder builder = createProxyMethodBuilder()
                .description(operation.getDescription())
                .name(operation.getLanguage().getJava().getName())
                .isResumable(false);

        List<HttpResponseStatus> expectedStatusCodes = operation.getResponses().stream()
                .flatMap(r -> r.getProtocol().getHttp().getStatusCodes().stream())
                .map(s -> s.replaceAll("'", ""))
                .map(s -> HttpResponseStatus.valueOf(Integer.parseInt(s)))
                .sorted().collect(Collectors.toList());
        builder.responseExpectedStatusCodes(expectedStatusCodes);

        IType responseBodyType = SchemaUtil.getOperationResponseType(operation);

        if (operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation() && settings.isFluent()
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

        for (Request request : operation.getRequests()) {
            if (parsed.containsKey(request)) {
                result.put(request, parsed.get(request));
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

            builder.urlPath(request.getProtocol().getHttp().getPath());
            builder.httpMethod(HttpMethod.valueOf(request.getProtocol().getHttp().getMethod().toUpperCase()));

            List<ProxyMethodParameter> parameters = new ArrayList<>();
            for (Parameter parameter : request.getParameters().stream()
                    .filter(p -> p.getProtocol() != null && p.getProtocol().getHttp() != null)
                    .collect(Collectors.toList())) {
                parameter.setOperation(operation);
                ProxyMethodParameter proxyMethodParameter = Mappers.getProxyParameterMapper().map(parameter);
                if (requestContentType.startsWith("application/json-patch+json")) {
                    proxyMethodParameter = CustomProxyParameterMapper.getInstance().map(parameter);
                }
                parameters.add(proxyMethodParameter);
            }
            if (settings.getAddContextParameter()) {
                ClassType contextClassType = getContextClass();
                ProxyMethodParameter contextParameter = new ProxyMethodParameter.Builder()
                        .description("The context to associate with this operation.")
                        .wireType(contextClassType)
                        .clientType(contextClassType)
                        .name("context")
                        .requestParameterLocation(RequestParameterLocation.None)
                        .requestParameterName("context")
                        .alreadyEncoded(true)
                        .isConstant(false)
                        .isRequired(false)
                        .isNullable(false)
                        .fromClient(false)
                        .parameterReference("context")
                        .build();
                parameters.add(contextParameter);
            }
            appendCallbackParameter(parameters, responseBodyType);
            builder.parameters(parameters);

            ProxyMethod proxyMethod = builder.build();

            result.put(request, proxyMethod);
            parsed.put(request, proxyMethod);
        }
        return result;
    }

    protected ClassType getContextClass() {
        return ClassType.Context;
    }

    protected void appendCallbackParameter(java.util.List<ProxyMethodParameter> parameters, IType responseBodyType) {
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
    protected void buildUnexpectedResponseExceptionTypes(ProxyMethod.Builder builder,
                                                         Operation operation, List<HttpResponseStatus> expectedStatusCodes,
                                                         JavaSettings settings) {
        ClassType errorType = null;
        Map<ClassType, List<HttpResponseStatus>> exceptionTypeMap = new HashMap<>();

        /*
        1. If exception has valid numeric status codes, group them to unexpectedResponseExceptionTypes
        2. If exception does not have status codes, or have 'default' or invalid number, put the first to unexpectedResponseExceptionType, ignore the rest
        3. After processing, if no model in unexpectedResponseExceptionType, take any from unexpectedResponseExceptionTypes and put it to unexpectedResponseExceptionType
         */

        if (operation.getExceptions() != null && !operation.getExceptions().isEmpty()) {
            for (Response exception : operation.getExceptions()) {
                boolean isDefaultError = true;
                if (exception.getProtocol() != null && exception.getProtocol().getHttp() != null) {
                    List<String> statusCodes = exception.getProtocol().getHttp().getStatusCodes();
                    if (statusCodes != null && !statusCodes.isEmpty()) {
                        try {
                            ClassType exceptionType = getExceptionType(exception, settings);
                            List<HttpResponseStatus> statusCodeList = statusCodes.stream()
                                    .map(code -> HttpResponseStatus.valueOf(Integer.parseInt(code)))
                                    .collect(Collectors.toList());

                            if (exceptionTypeMap.containsKey(exceptionType)) {
                                exceptionTypeMap.get(exceptionType).addAll(statusCodeList);
                            } else {
                                exceptionTypeMap.put(exceptionType, statusCodeList);
                            }

                            isDefaultError = false;
                        } catch (NumberFormatException ex) {
                            // statusCodes can be 'default'
                            //logger.warn("Failed to parse status code, exception {}", ex.toString());
                            isDefaultError = true;
                        }
                    }
                }

                if (errorType == null && isDefaultError && exception.getSchema() != null) {
                    errorType = (ClassType) Mappers.getSchemaMapper().map(exception.getSchema());
                }
            }

            if (errorType == null) {
                // no default error, use the 1st to keep backward compatibility
                errorType = (ClassType) Mappers.getSchemaMapper().map(operation.getExceptions().get(0).getSchema());
            }
        }

        if (errorType != null) {
            String exceptionName = errorType.getExtensions() == null ? null : errorType.getExtensions().getXmsClientName();
            if (exceptionName == null || exceptionName.isEmpty()) {
                exceptionName = errorType.getName();
                exceptionName += "Exception";
            }

            String exceptionPackage;
            if (settings.isCustomType(exceptionName)) {
                exceptionPackage = settings.getPackage(settings.getCustomTypesSubpackage());
            } else {
                exceptionPackage = settings.getPackage(settings.getModelsSubpackage());
            }

            builder.unexpectedResponseExceptionType(new ClassType.Builder()
                    .packageName(exceptionPackage)
                    .name(exceptionName)
                    .build());
        } else {
            builder.unexpectedResponseExceptionType(getHttpResponseExceptionType());
        }

        if (!exceptionTypeMap.isEmpty()) {
            builder.unexpectedResponseExceptionTypes(exceptionTypeMap);
        }
    }

    private static ClassType getExceptionType(Response exception, JavaSettings settings) {
        ClassType exceptionType = ClassType.HttpResponseException;  // default as HttpResponseException

        if (exception != null && exception.getSchema() != null) {
            ClassType errorType = (ClassType) Mappers.getSchemaMapper().map(exception.getSchema());
            if (errorType != null) {
                String exceptionName = errorType.getExtensions() == null ? null : errorType.getExtensions().getXmsClientName();
                if (exceptionName == null || exceptionName.isEmpty()) {
                    exceptionName = errorType.getName();
                    exceptionName += "Exception";
                }

                String exceptionPackage;
                if (settings.isCustomType(exceptionName)) {
                    exceptionPackage = settings.getPackage(settings.getCustomTypesSubpackage());
                } else {
                    exceptionPackage = settings.getPackage(settings.getModelsSubpackage());
                }

                exceptionType = new ClassType.Builder()
                        .packageName(exceptionPackage)
                        .name(exceptionName)
                        .build();
            }
        }

        return exceptionType;
    }

    protected ClassType getHttpResponseExceptionType() {
        return ClassType.HttpResponseException;
    }
}
