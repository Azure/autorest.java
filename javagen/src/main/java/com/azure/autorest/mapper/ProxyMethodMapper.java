package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.Header;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProxyMethodMapper implements IMapper<Operation, ProxyMethod> {
    private static final List<IType> unixTimeTypes = Arrays.asList(PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong, ClassType.UnixTimeDateTime);
    private static final List<IType> returnValueWireTypeOptions = Stream.concat(Stream.of(ClassType.Base64Url, ClassType.DateTimeRfc1123), unixTimeTypes.stream()).collect(Collectors.toList());
    private static ProxyMethodMapper instance = new ProxyMethodMapper();

//    private static final jdk.nashorn.internal.runtime.regexp.joni.Regex methodTypeLeading = new Regex("^/+");
//    private static final Regex methodTypeTrailing = new Regex("/+$");
    private Map<Operation, ProxyMethod> parsed = new HashMap<Operation, ProxyMethod>();
    private ProxyMethodMapper() {
    }

    public static ProxyMethodMapper getInstance() {
        return instance;
    }

    @Override
    public ProxyMethod map(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();
        if (parsed.containsKey(operation)) {
            return parsed.get(operation);
        }
        String requestContentType = "application/json";
        if (operation.getRequest().getProtocol().getHttp().getMediaTypes() != null && !operation.getRequest().getProtocol().getHttp().getMediaTypes().isEmpty()) {
            requestContentType = operation.getRequest().getProtocol().getHttp().getMediaTypes().get(0);
        }

        // TODO: Paging
//        boolean restAPIMethodIsPagingNextOperation = method.Extensions?.Get<bool>("nextLinkMethod") == true;

        String urlPath = operation.getRequest().getProtocol().getHttp().getPath();

        String httpMethod = operation.getRequest().getProtocol().getHttp().getMethod();

        List<HttpResponseStatus> responseExpectedStatusCodes = operation.getResponses().stream()
                .flatMap(r -> r.getProtocol().getHttp().getStatusCodes().stream())
                .map(s -> s.replaceAll("'", ""))
                .map(s -> HttpResponseStatus.valueOf(Integer.parseInt(s)))
                .sorted().collect(Collectors.toList());

        IType responseBodyType = SchemaUtil.operationResponseType(operation);

        IType returnType;
        if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
            // BinaryResponse
            IType singleValueType = ClassType.StreamResponse;
            returnType = GenericType.Mono(singleValueType);
        } else if (operation.getResponses().stream()
                .filter(r -> r.getProtocol() != null && r.getProtocol().getHttp() != null && r.getProtocol().getHttp().getHeaders() != null)
                .flatMap(r -> r.getProtocol().getHttp().getHeaders().stream().map(Header::getSchema))
                .anyMatch(Objects::nonNull)) {
            // SchemaResponse
            // method with schema in headers would require a ClientResponse
            ClassType clientResponseClassType = ClientMapper.getClientResponseClassType(operation, settings);
            returnType = GenericType.Mono(clientResponseClassType);
        } else {
            IType singleValueType;
            if (responseBodyType.equals(GenericType.FluxByteBuffer)) {
                singleValueType = ClassType.StreamResponse;
            } else if (responseBodyType.equals(PrimitiveType.Void)) {
                singleValueType = GenericType.Response(ClassType.Void);
            } else {
                singleValueType = GenericType.BodyResponse(responseBodyType);
            }
            returnType = GenericType.Mono(singleValueType);
        }

        ClassType errorType = null;
        if (operation.getExceptions() != null && !operation.getExceptions().isEmpty()) {
            errorType = (ClassType) Mappers.getSchemaMapper().map(operation.getExceptions().get(0).getSchema());
        }

        ClassType unexpectedResponseExceptionType;
        if (settings.isAzureOrFluent() && (errorType == null || errorType.getName().equals("CloudError"))) {
            unexpectedResponseExceptionType = ClassType.CloudException;
        } else if (errorType != null) {
            String exceptionName = errorType.getExtensions() == null ? null : errorType.getExtensions().getXmsClientName();
            if (exceptionName == null || exceptionName.isEmpty()) {
                exceptionName = errorType.getName();
                // TODO: Fluent
//                if (settings.isFluent() && exceptionName != null && !exceptionName.isEmpty() && errorType.IsInnerModelType)
//                {
//                    exceptionName += "Inner";
//                }
                exceptionName += "Exception";
            }

            String exceptionPackage = settings.getPackage();
            if (settings.isCustomType(exceptionName)) {
                exceptionPackage = settings.getPackage(settings.getCustomTypesSubpackage());
            } else if (settings.isFluent()) {
                exceptionPackage = settings.getPackage();
            } else {
                exceptionPackage = settings.getPackage(settings.getModelsSubpackage());
            }

            unexpectedResponseExceptionType = new ClassType(exceptionPackage, exceptionName, null, null, false);
        } else {
            unexpectedResponseExceptionType = ClassType.HttpResponseException;
        }

        List<ProxyMethodParameter> parameters = new ArrayList<>();
        for (Parameter parameter : operation.getRequest().getParameters().stream()
                .filter(p -> p.getProtocol() != null && p.getProtocol().getHttp() != null).collect(Collectors.toList())) {
            parameter.setOperation(operation);
            parameters.add(Mappers.getProxyParameterMapper().map(parameter));
        }

        AtomicReference<IType> responseBodyTypeReference = new AtomicReference<>(responseBodyType);
        IType returnValueWireType = returnValueWireTypeOptions
            .stream()
            .filter(type -> responseBodyTypeReference.get().contains(type))
            .findFirst()
            .orElse(null);

        Set<String> responseContentTypes = operation.getResponses().stream()
                .filter(r -> r.getProtocol() != null && r.getProtocol().getHttp() != null && r.getProtocol().getHttp().getMediaTypes() != null)
                .flatMap(r -> r.getProtocol().getHttp().getMediaTypes().stream())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
        if (!responseContentTypes.contains("application/json")) {
            responseContentTypes.add("application/json;q=0.9");
        }

        ProxyMethod proxyMethod = new ProxyMethod(
                requestContentType,
                returnType,
                false,
                HttpMethod.valueOf(httpMethod.toUpperCase()),
                urlPath,
                responseExpectedStatusCodes,
                unexpectedResponseExceptionType,
                operation.getLanguage().getJava().getName(),
                parameters,
                operation.getDescription(),
                returnValueWireType,
                false,
                responseContentTypes);

        parsed.put(operation, proxyMethod);
        return proxyMethod;
    }
}
