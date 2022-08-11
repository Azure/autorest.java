// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.util.ClientModelUtil;

public class ConvenienceAsyncMethodTemplate extends ConvenienceMethodTemplateBase {

    private static final ConvenienceAsyncMethodTemplate INSTANCE = new ConvenienceAsyncMethodTemplate();

    protected ConvenienceAsyncMethodTemplate() {
    }

    public static ConvenienceAsyncMethodTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean isConvenienceMethod(ClientMethod method) {
        return isMethodAsync(method) && isMethodVisible(method);
    }

    @Override
    protected boolean isConvenienceMethod(ConvenienceMethod method) {
        return isMethodAsync(method.getProtocolMethod()) && isMethodVisible(method.getProtocolMethod())
                && method.getProtocolMethod().getMethodParameters().stream().noneMatch(p -> p.getClientType() == ClassType.Context);
    }

    protected void writeInvocationAndConversion(
            ClientMethod convenienceMethod, ClientMethod protocolMethod,
            String invocationExpression,
            JavaBlock methodBlock) {

        IType responseBodyType = getResponseBodyType(convenienceMethod);

        String returnTypeConversionExpression = expressionConvertFromBinaryData(responseBodyType);

        methodBlock.methodReturn(
                String.format("%1$s(%2$s).map(Response::getValue)%3$s",
                        getMethodName(protocolMethod),
                        invocationExpression,
                        returnTypeConversionExpression));
    }

    private IType getResponseBodyType(ClientMethod method) {
        // Mono<T>
        return ((GenericType) method.getReturnValue().getType()).getTypeArguments()[0];
    }

    private String expressionConvertFromBinaryData(IType responseBodyType) {
        if (responseBodyType instanceof EnumType) {
            // enum
            return String.format(".map(%1$s::from%2$s)", responseBodyType, ((EnumType) responseBodyType).getElementType());
        } else if (ClientModelUtil.isClientModel(responseBodyType)) {
            // class
            return String.format(".map(protocolMethodData -> protocolMethodData.toObject(%s.class))", responseBodyType);
        } else {
            return "";
        }
    }
}
