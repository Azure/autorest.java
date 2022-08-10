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
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.http.rest.Response;

public class ConvenienceSyncMethodTemplate extends ConvenienceMethodTemplateBase {

    private static final ConvenienceSyncMethodTemplate INSTANCE = new ConvenienceSyncMethodTemplate();

    protected ConvenienceSyncMethodTemplate() {
    }

    public static ConvenienceSyncMethodTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean isConvenienceMethod(ClientMethod method) {
        return !isMethodAsync(method) && isMethodVisible(method);
    }

    @Override
    protected boolean isConvenienceMethod(ConvenienceMethod method) {
        return !isMethodAsync(method.getClientMethod()) && isMethodVisible(method.getClientMethod())
                && method.getClientMethod().getMethodInputParameters().stream().noneMatch(p -> p.getClientType() == ClassType.Context);
    }

    @Override
    protected void writeInvocationAndConversion(
            ClientMethod convenienceMethod, ClientMethod clientMethod,
            String invocationExpression,
            JavaBlock methodBlock) {

        IType responseBodyType = getResponseBodyType(convenienceMethod);

        String convertFromResponse = convenienceMethod.getType() == ClientMethodType.SimpleSyncRestResponse
                ? "" : ".getValue()";

        if (convenienceMethod.getType() == ClientMethodType.SimpleSyncRestResponse
                && !(responseBodyType.asNullable() == ClassType.Void || responseBodyType == ClassType.BinaryData)) {

            String statement = String.format("%1$s(%2$s)",
                    getMethodName(clientMethod),
                    invocationExpression);

            methodBlock.line(String.format(
                    "%1$s protocolMethodResponse = %2$s;",
                    clientMethod.getReturnValue().getType(), statement));
            String expressConversion = expressionConvertFromBinaryData(responseBodyType, "protocolMethodResponse.getValue()");
            methodBlock.methodReturn(String.format(
                    "new SimpleResponse<>(protocolMethodResponse, %s)", expressConversion));
        } else {
            String statement = String.format("%1$s(%2$s)%3$s",
                    getMethodName(clientMethod),
                    invocationExpression,
                    convertFromResponse);
            statement = expressionConvertFromBinaryData(responseBodyType, statement);
            if (convenienceMethod.getType() != ClientMethodType.SimpleSyncRestResponse && responseBodyType.asNullable() == ClassType.Void) {
                methodBlock.line(statement + ";");
            } else {
                methodBlock.methodReturn(statement);
            }
        }
    }

    private IType getResponseBodyType(ClientMethod method) {
        IType type =  method.getReturnValue().getType();
        if (type instanceof GenericType && Response.class.getSimpleName().equals(((GenericType) type).getName())) {
            type = ((GenericType) type).getTypeArguments()[0];
        }
        return type;
    }

    protected String expressionConvertFromBinaryData(IType responseBodyType, String invocationExpression) {
        if (responseBodyType instanceof EnumType) {
            // enum
            return String.format("%1$s.from%2$s(%3$s)", responseBodyType, ((EnumType) responseBodyType).getElementType(), invocationExpression);
        } else if (ClientModelUtil.isClientModel(responseBodyType)) {
            // class
            return String.format("%2$s.toObject(%1$s.class)", responseBodyType, invocationExpression);
        } else {
            return invocationExpression;
        }
    }
}
