package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProxyMethodMapper implements IMapper<Operation, ProxyMethod> {
    private ProxyMethodMapper() {
    }

    private static ProxyMethodMapper instance = new ProxyMethodMapper();

    public static ProxyMethodMapper getInstance() {
        return instance;
    }

//    private static final jdk.nashorn.internal.runtime.regexp.joni.Regex methodTypeLeading = new Regex("^/+");
//    private static final Regex methodTypeTrailing = new Regex("/+$");

    private static final List<IType> unixTimeTypes = Arrays.asList(PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong, ClassType.UnixTimeDateTime);
    private static final List<IType> returnValueWireTypeOptions = Stream.concat(Stream.of(ClassType.Base64Url, ClassType.DateTimeRfc1123), unixTimeTypes.stream()).collect(Collectors.toList());

    private Map<Operation, ProxyMethod> parsed = new HashMap<Operation, ProxyMethod>();

    @Override
    public ProxyMethod map(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();
        if (parsed.containsKey(operation))
        {
            return parsed.get(operation);
        }
        String requestContentType = "application/json";
        if (operation.getRequest().getProtocol().getHttp().getMediaTypes() != null && !operation.getRequest().getProtocol().getHttp().getMediaTypes().isEmpty()) {
            requestContentType = operation.getRequest().getProtocol().getHttp().getMediaTypes().get(0);
        }

        // TODO: Paging
//        boolean restAPIMethodIsPagingNextOperation = method.Extensions?.Get<bool>("nextLinkMethod") == true;

        String urlPath = operation.getRequest().getProtocol().getHttp().getPath().replaceFirst("^/", "");

        String httpMethod = operation.getRequest().getProtocol().getHttp().getMethod();

        List<HttpResponseStatus> responseExpectedStatusCodes = operation.getResponses().stream()
                .flatMap(r -> r.getProtocol().getHttp().getStatusCodes().stream())
                .map(s -> s.replaceAll("'", ""))
                .map(s -> HttpResponseStatus.valueOf(Integer.parseInt(s)))
                .sorted().collect(Collectors.toList());

        IType responseBodyType = Mappers.getSchemaMapper().map(SchemaUtil.getLowestCommonParent(
                operation.getResponses().stream().map(Response::getSchema).collect(Collectors.toList())));

        if (responseBodyType == null) {
            responseBodyType = PrimitiveType.Void;
        }

        IType singleValueType;
        if (responseBodyType.equals(GenericType.FluxByteBuffer))
        {
            singleValueType = ClassType.StreamResponse;
        }
        else if (responseBodyType.equals(PrimitiveType.Void))
        {
            singleValueType = GenericType.Response(ClassType.Void);
        }
        else
        {
            singleValueType = GenericType.BodyResponse(responseBodyType);
        }
        IType returnType = GenericType.Mono(singleValueType);

        ClassType unexpectedResponseExceptionType = null;
        if (operation.getExceptions() != null && !operation.getExceptions().isEmpty()) {
            unexpectedResponseExceptionType = (ClassType) Mappers.getSchemaMapper().map(operation.getExceptions().get(0).getSchema());
        }

        List<ProxyMethodParameter> parameters = new ArrayList<>();
        for (Parameter parameter : operation.getRequest().getParameters()) {
            parameters.add(Mappers.getProxyParameterMapper().map(parameter));
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
                responseBodyType,
                false);

        parsed.put(operation, proxyMethod);
        return proxyMethod;
    }
}
