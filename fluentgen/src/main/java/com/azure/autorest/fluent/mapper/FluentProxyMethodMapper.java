/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.mapper.ProxyMethodMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;

public class FluentProxyMethodMapper extends ProxyMethodMapper {

    private static final FluentProxyMethodMapper instance = new FluentProxyMethodMapper();

    public static FluentProxyMethodMapper getInstance() {
        return instance;
    }

    @Override
    protected void buildUnexpectedResponseExceptionTypes(ProxyMethod.Builder builder,
                                                         Operation operation, List<HttpResponseStatus> expectedStatusCodes,
                                                         JavaSettings settings) {
        ClassType errorType = null;
        if (operation.getExceptions() != null && !operation.getExceptions().isEmpty()) {
            errorType = (ClassType) Mappers.getSchemaMapper().map(operation.getExceptions().get(0).getSchema());
        }
        if (errorType != null && !FluentType.nonManagementError(errorType)) {
            builder.unexpectedResponseExceptionType(FluentType.ManagementException);
        } else {
            super.buildUnexpectedResponseExceptionTypes(builder, operation, expectedStatusCodes, settings);
        }

        /*
        final HttpMethod httpMethod = HttpMethod.valueOf(operation.getRequests().get(0).getProtocol().getHttp().getMethod().toUpperCase());
        final boolean isResourceModify = httpMethod == HttpMethod.PUT || httpMethod == HttpMethod.POST || httpMethod == HttpMethod.PATCH || httpMethod == HttpMethod.DELETE;
        final boolean hasETagHeader = operation.getRequests().stream().flatMap(r -> r.getParameters().stream())
                .filter(p -> p.getImplementation() == Parameter.ImplementationLocation.METHOD)
                .filter(p -> p.getProtocol() != null && p.getProtocol().getHttp() != null && p.getProtocol().getHttp().getIn() == RequestParameterLocation.Header)
                .map(p -> p.getLanguage().getDefault().getSerializedName())
                .filter(Objects::nonNull)
                .anyMatch(sn -> sn.equalsIgnoreCase("If-Match") || sn.equalsIgnoreCase("If-None-Match"));

        builder.unexpectedResponseExceptionType(ClassType.HttpResponseException);
        Map<ClassType, List<HttpResponseStatus>> unexpectedResponseExceptionTypes = new HashMap<>();
        if (!expectedStatusCodes.contains(HttpResponseStatus.UNAUTHORIZED)) {
            unexpectedResponseExceptionTypes.put(ClassType.ClientAuthenticationException, Collections.singletonList(HttpResponseStatus.UNAUTHORIZED));
        }
        if (!expectedStatusCodes.contains(HttpResponseStatus.NOT_FOUND)) {
            unexpectedResponseExceptionTypes.put(ClassType.ResourceNotFoundException, Collections.singletonList(HttpResponseStatus.NOT_FOUND));
        }
        if (isResourceModify && !expectedStatusCodes.contains(HttpResponseStatus.CONFLICT)) {
            unexpectedResponseExceptionTypes.put(ClassType.ResourceModifiedException, Collections.singletonList(HttpResponseStatus.CONFLICT));
        }
        if (!expectedStatusCodes.contains(HttpResponseStatus.TOO_MANY_REQUESTS)) {
            unexpectedResponseExceptionTypes.put(ClassType.TooManyRedirectsException, Collections.singletonList(HttpResponseStatus.TOO_MANY_REQUESTS));
        }
        if (hasETagHeader && !expectedStatusCodes.contains(HttpResponseStatus.PRECONDITION_FAILED)) {
            unexpectedResponseExceptionTypes.put(ClassType.ResourceExistsException, Collections.singletonList(HttpResponseStatus.PRECONDITION_FAILED));
        }
        builder.unexpectedResponseExceptionTypes(unexpectedResponseExceptionTypes);
        */
    }
}
