package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.AndroidProxyMethod;
import com.azure.autorest.extension.base.model.codemodel.Header;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ClientMapper;
import com.azure.autorest.mapper.ProxyMethodMapper;
import com.azure.autorest.model.clientmodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AndroidProxyMethodMapper extends ProxyMethodMapper {
    private static AndroidProxyMethodMapper instance = new AndroidProxyMethodMapper();

    public static AndroidProxyMethodMapper getInstance() {
        return instance;
    }

    @Override
    protected ProxyMethod.Builder createProxyMethodBuilder() {
        return new AndroidProxyMethod.Builder();
    }

    @Override
    protected void addOperationReturnType(Operation operation,
                                          JavaSettings settings,
                                          ProxyMethod.Builder builder,
                                          IType responseBodyType) {
        if (operation.getExtensions() != null
                && operation.getExtensions().isXmsLongRunningOperation()
                && settings.isFluent()
                && (operation.getExtensions().getXmsPageable() == null
                    || !(operation.getExtensions().getXmsPageable().getNextOperation() == operation))) {
            throw new UnsupportedOperationException("Long running operation not supported on Android");
        } else if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
            // BinaryResponse
            builder.returnType(ClassType.OkHttp3ResponseBody);
        } else if (operation.getResponses().stream()
                .filter(r -> r.getProtocol() != null
                        && r.getProtocol().getHttp() != null
                        && r.getProtocol().getHttp().getHeaders() != null)
                .flatMap(r -> r.getProtocol().getHttp().getHeaders().stream().map(Header::getSchema))
                .anyMatch(Objects::nonNull)) {
            // SchemaResponse
            // method with schema in headers would require a ClientResponse
            ClassType clientResponseClassType = ClientMapper.getClientResponseClassType(operation, settings);
            builder.returnType(clientResponseClassType);
        } else {
            IType singleValueType;
            if (responseBodyType.equals(PrimitiveType.Void)) {
                singleValueType = ClassType.Void;
            } else {
                singleValueType = responseBodyType;
            }
            builder.returnType(singleValueType);
        }
    }

    @Override
    protected List<ProxyMethodParameter> sortProxyParameters(List<ProxyMethodParameter> parameters) {
        ArrayList<ProxyMethodParameter> sorted = new ArrayList<>();
        sorted.addAll(parameters.stream().filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Header).collect(Collectors.toList()));
        sorted.addAll(parameters.stream().filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Uri).collect(Collectors.toList()));
        sorted.addAll(parameters.stream().filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Path).collect(Collectors.toList()));
        sorted.addAll(parameters.stream().filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Query).collect(Collectors.toList()));
        sorted.addAll(parameters.stream().filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Body).collect(Collectors.toList()));
        return sorted;
    }

    @Override
    protected ClassType getExceptionTypeForString(ClassType errorType) {
        return ClassType.AndroidHttpResponseException;
    }
}
