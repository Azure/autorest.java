// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.extension.base.model.codemodel.Operation;

/**
 */
public class ExampleWrapper {

    private final Object xmsExample;
    private final String operationId;

    public ExampleWrapper(Object xmsExample, String operationId) {
        this.xmsExample = xmsExample;
        this.operationId = operationId;
    }

    public Object getXmsExample() {
        return xmsExample;
    }

    public String getOperationId() {
        return operationId;
    }
}
