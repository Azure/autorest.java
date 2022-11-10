// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.javamodel.JavaBlock;

import java.util.Objects;

public class ConvenienceAsyncMethodTemplate extends ConvenienceMethodTemplateBase {

    private static final ConvenienceAsyncMethodTemplate INSTANCE = new ConvenienceAsyncMethodTemplate();

    protected ConvenienceAsyncMethodTemplate() {
    }

    public static ConvenienceAsyncMethodTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean isMethodIncluded(ClientMethod method) {
        return isMethodAsync(method) && isMethodVisible(method);
    }

    @Override
    protected boolean isMethodIncluded(ConvenienceMethod method) {
        return isMethodAsync(method.getProtocolMethod()) && isMethodVisible(method.getProtocolMethod())
                && method.getProtocolMethod().getMethodParameters().stream().noneMatch(p -> p.getClientType() == ClassType.Context);
    }

    protected void writeInvocationAndConversion(
            ClientMethod convenienceMethod, ClientMethod protocolMethod,
            String invocationExpression,
            JavaBlock methodBlock) {

        ClientMethodType methodType = protocolMethod.getType();

        IType responseBodyType = getResponseBodyType(convenienceMethod);

        String returnTypeConversionExpression = expressionConvertFromBinaryData(responseBodyType);

        if (methodType == ClientMethodType.PagingAsync) {
            String expressionMapFromBinaryData = expressionMapFromBinaryData(responseBodyType);
            if (expressionMapFromBinaryData == null) {
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
        } else {
            // return type is Mono<Void>, Response::getValue would be null
            String returnExpression = responseBodyType.asNullable() == ClassType.Void
                    ? "%1$s(%2$s).then()%3$s"   // return type is Mono<Void>, Response::getValue would be null
                    : "%1$s(%2$s).map(Response::getValue)%3$s";

            methodBlock.methodReturn(
                    String.format(returnExpression,
                            getMethodName(protocolMethod),
                            invocationExpression,
                            returnTypeConversionExpression));
        }
    }

    private IType getResponseBodyType(ClientMethod method) {
        // Mono<T>
        return ((GenericType) method.getReturnValue().getType()).getTypeArguments()[0];
    }

    private String expressionConvertFromBinaryData(IType responseBodyType) {
        String expressionMapFromBinaryData = expressionMapFromBinaryData(responseBodyType);
        if (expressionMapFromBinaryData != null) {
            return String.format(".map(%s)", expressionMapFromBinaryData);
        } else {
            return "";
        }
    }

    private String expressionMapFromBinaryData(IType responseBodyType) {
        String mapExpression = null;
        if (responseBodyType instanceof EnumType) {
            // enum
            mapExpression = String.format("%1$s::from%2$s", responseBodyType, ((EnumType) responseBodyType).getElementType());
        } else if (responseBodyType instanceof GenericType) {
            // generic, e.g. list, map
            mapExpression = String.format("protocolMethodData -> protocolMethodData.toObject(new TypeReference<%1$s>() {})", responseBodyType);
        } else if (responseBodyType == ClassType.BinaryData) {
            // BinaryData, no need to do the map in expressionConvertFromBinaryData
            mapExpression = null;
        } else if (isModelOrBuiltin(responseBodyType)) {
            // class
            mapExpression = String.format("protocolMethodData -> protocolMethodData.toObject(%1$s.class)", responseBodyType);
        }
        return mapExpression;
    }
}
