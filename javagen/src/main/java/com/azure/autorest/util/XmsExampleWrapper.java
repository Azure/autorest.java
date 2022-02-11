// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

public class XmsExampleWrapper {

    private final Object xmsExample;
    private final String operationId;

    public XmsExampleWrapper(Object xmsExample, String operationId) {
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
