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

        String returnTypeConversionExpression = expressionConvertFromBinaryData(responseBodyType, methodType);

        String returnExpression = (methodType == ClientMethodType.PagingAsync)
                ? "%1$s(%2$s)%3$s"
                : "%1$s(%2$s).map(Response::getValue)%3$s";

        methodBlock.methodReturn(
                String.format(returnExpression,
                        getMethodName(protocolMethod),
                        invocationExpression,
                        returnTypeConversionExpression));
    }

    private IType getResponseBodyType(ClientMethod method) {
        // Mono<T>
        return ((GenericType) method.getReturnValue().getType()).getTypeArguments()[0];
    }

    private String expressionConvertFromBinaryData(IType responseBodyType, ClientMethodType methodType) {
        String mapMethod = (methodType == ClientMethodType.PagingAsync) ? "mapPage" : "map";
        if (responseBodyType instanceof EnumType) {
            // enum
            return String.format(".%1$s(%2$s::from%3$s)", mapMethod, responseBodyType, ((EnumType) responseBodyType).getElementType());
        } else if (isModelOrBuiltin(responseBodyType)) {
            // class
            return String.format(".%1$s(protocolMethodData -> protocolMethodData.toObject(%2$s.class))", mapMethod, responseBodyType);
        } else {
            return "";
        }
    }
}
