/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.mapper.ExceptionMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientException;

public class FluentExceptionMapper extends ExceptionMapper {

    private static final FluentExceptionMapper instance = new FluentExceptionMapper();

    protected FluentExceptionMapper() {
    }

    public static FluentExceptionMapper getInstance() {
        return instance;
    }

    protected ClientException buildException(ObjectSchema compositeType, JavaSettings settings) {
        if (!FluentType.nonManagementError(Utils.getJavaName(compositeType))) {
            // Use ManagementException directly, no need to build new Exception class.
            return null;
        }

        String errorName = compositeType.getLanguage().getJava().getName();
        String methodOperationExceptionTypeName = errorName + "Exception";

        boolean isManagementException = compositeType.getParents() != null
                && !FluentType.nonManagementError(Utils.getJavaName(compositeType.getParents().getImmediate().get(0)));

        ClientException exception = new ClientException.Builder()
                .packageName(settings.getPackage(settings.getModelsSubpackage()))
                .name(methodOperationExceptionTypeName)
                .errorName(errorName)
                .parentType(isManagementException ? FluentType.ManagementException : ClassType.HttpResponseException)
                .build();
        return exception;
    }
}
