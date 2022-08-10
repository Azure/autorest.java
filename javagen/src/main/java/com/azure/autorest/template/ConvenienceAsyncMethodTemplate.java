// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
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
        return method.getType().name().contains("Async");
    }

    @Override
    protected String getMethodName(ClientMethod method) {
        if (method.getType().name().contains("Async")) {
            return method.getName().endsWith("Async")
                    ? method.getName().substring(0, method.getName().length() - "Async".length())
                    : method.getName();
        } else {
            return method.getName();
        }
    }

    @Override
    protected String expressionConvertFromBinaryData(IType baseReturnType) {
        if (baseReturnType instanceof EnumType) {
            // enum
            return String.format(".map(%s::fromString)", baseReturnType);
        } else if (ClientModelUtil.isClientModel(baseReturnType)) {
            // class
            return String.format(".map(r -> r.toObject(%s.class))", baseReturnType);
        } else {
            return "";
        }
    }
}
