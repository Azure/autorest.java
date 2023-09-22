// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.util.TemplateUtil;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Set;

public class ConvenienceAsyncMethodTemplate extends ConvenienceMethodTemplateBase {

    private static final ConvenienceAsyncMethodTemplate INSTANCE = new ConvenienceAsyncMethodTemplate();

    protected ConvenienceAsyncMethodTemplate() {
    }

    public static ConvenienceAsyncMethodTemplate getInstance() {
        return INSTANCE;
    }

    public void addImports(Set<String> imports, List<ConvenienceMethod> convenienceMethods) {
        if (!CoreUtils.isNullOrEmpty(convenienceMethods)) {
            super.addImports(imports, convenienceMethods);

            // async e.g. FluxUtil::toMono
            imports.add(FluxUtil.class.getName());

            // async pageable
            imports.add(PagedResponse.class.getName());
            imports.add(PagedResponseBase.class.getName());
            imports.add(Flux.class.getName());
        }
    }

    @Override
    protected boolean isMethodIncluded(ClientMethod method) {
        return isMethodAsync(method) && isMethodVisible(method) && !method.isImplementationOnly();
    }

    @Override
    protected boolean isMethodIncluded(ConvenienceMethod method) {
        return isMethodAsync(method.getProtocolMethod()) && isMethodVisible(method.getProtocolMethod())
                // for LRO, we actually choose the protocol method of "WithModel"
                && (method.getProtocolMethod().getType() != ClientMethodType.LongRunningBeginAsync || (method.getProtocolMethod().getImplementationDetails() != null && method.getProtocolMethod().getImplementationDetails().isImplementationOnly()))
                && method.getProtocolMethod().getMethodParameters().stream().noneMatch(p -> p.getClientType() == ClassType.Context);
    }

    protected void writeInvocationAndConversion(
            ClientMethod convenienceMethod, ClientMethod protocolMethod,
            String invocationExpression,
            JavaBlock methodBlock,
            Set<GenericType> typeReferenceStaticClasses) {

        ClientMethodType methodType = protocolMethod.getType();

        IType responseBodyType = getResponseBodyType(convenienceMethod);
        IType rawResponseBodyType = convenienceMethod.getProxyMethod().getRawResponseBodyType();

        String returnTypeConversionExpression = expressionConvertFromBinaryData(responseBodyType, rawResponseBodyType, typeReferenceStaticClasses);

        if (methodType == ClientMethodType.PagingAsync) {
            String expressionMapFromBinaryData = expressionMapFromBinaryData(responseBodyType, rawResponseBodyType, typeReferenceStaticClasses);
            if (expressionMapFromBinaryData == null) {
                // no need to do the map
                methodBlock.methodReturn(String.format("%1$s(%2$s)", getMethodName(protocolMethod), invocationExpression));
            } else {
                methodBlock.line("PagedFlux<BinaryData> pagedFluxResponse = %1$s(%2$s);", getMethodName(protocolMethod), invocationExpression);

                methodBlock.methodReturn(String.format(
                        "PagedFlux.create(() -> (continuationToken, pageSize) -> {\n" +
                                "    Flux<PagedResponse<BinaryData>> flux = (continuationToken == null)\n" +
                                "        ? pagedFluxResponse.byPage().take(1)\n" +
                                "        : pagedFluxResponse.byPage(continuationToken).take(1);\n" +
                                "    return flux.map(pagedResponse -> new PagedResponseBase<Void, %1$s>(pagedResponse.getRequest(),\n" +
                                "        pagedResponse.getStatusCode(),\n" +
                                "        pagedResponse.getHeaders(),\n" +
                                "        pagedResponse.getValue().stream().map(%2$s).collect(Collectors.toList()),\n" +
                                "        pagedResponse.getContinuationToken(),\n" +
                                "        null));\n" +
                                "})",
                        responseBodyType, expressionMapFromBinaryData));
            }
        } else if (methodType == ClientMethodType.LongRunningBeginAsync) {
            String methodName = protocolMethod.getName();
            methodBlock.methodReturn(String.format("serviceClient.%1$s(%2$s)", methodName, invocationExpression));
        } else {
            methodBlock.methodReturn(
                    String.format("%1$s(%2$s).flatMap(FluxUtil::toMono)%3$s",
                            getMethodName(protocolMethod),
                            invocationExpression,
                            returnTypeConversionExpression));
        }
    }

    @Override
    protected void writeThrowException(ClientMethodType methodType, String exceptionExpression, JavaBlock methodBlock) {
        if (methodType == ClientMethodType.PagingAsync) {
            methodBlock.methodReturn(String.format("PagedFlux.create(() -> (continuationToken, pageSize) -> Flux.error(%s))", exceptionExpression));
        } else if (methodType == ClientMethodType.LongRunningBeginAsync) {
            methodBlock.methodReturn(String.format("PollerFlux.error(%s)", exceptionExpression));
        } else {
            methodBlock.methodReturn(String.format("Mono.error(%s)", exceptionExpression));
        }
    }

    private IType getResponseBodyType(ClientMethod method) {
        // Mono<T>
        return ((GenericType) method.getReturnValue().getType()).getTypeArguments()[0];
    }

    private String expressionConvertFromBinaryData(IType responseBodyType, IType rawType, Set<GenericType> typeReferenceStaticClasses) {
        String expressionMapFromBinaryData = expressionMapFromBinaryData(responseBodyType, rawType, typeReferenceStaticClasses);
        if (expressionMapFromBinaryData != null) {
            return String.format(".map(%s)", expressionMapFromBinaryData);
        } else {
            // no need to do the map
            return "";
        }
    }

    private String expressionMapFromBinaryData(IType responseBodyType, IType rawType, Set<GenericType> typeReferenceStaticClasses) {
        String mapExpression = null;
        if (responseBodyType instanceof EnumType) {
            // enum
            mapExpression = String.format("%1$s::from%2$s", responseBodyType, ((EnumType) responseBodyType).getElementType());
        } else if (responseBodyType instanceof GenericType) {
            // generic, e.g. list, map
            typeReferenceStaticClasses.add((GenericType) responseBodyType);
            mapExpression = String.format("protocolMethodData -> protocolMethodData.toObject(%1$s)", TemplateUtil.getTypeReferenceCreation(responseBodyType));
        } else if (responseBodyType == ClassType.BinaryData) {
            // BinaryData, no need to do the map in expressionConvertFromBinaryData
            mapExpression = null;
        } else if (isModelOrBuiltin(responseBodyType)) {
            // class
            mapExpression = String.format("protocolMethodData -> protocolMethodData.toObject(%1$s.class)", responseBodyType);
        } else if (responseBodyType == ArrayType.ByteArray) {
            // byte[]
            if (rawType == ClassType.Base64Url) {
                return "protocolMethodData -> protocolMethodData.toObject(Base64Url.class).decodedBytes()";
            } else {
                return "protocolMethodData -> protocolMethodData.toObject(byte[].class)";
            }
        }
        return mapExpression;
    }
}
