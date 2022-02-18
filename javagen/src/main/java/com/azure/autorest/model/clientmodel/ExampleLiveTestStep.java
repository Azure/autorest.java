/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.model.clientmodel;

public class ExampleLiveTestStep extends LiveTestStep{

    private final String operationId;
    private ProxyMethodExample example;

    public ExampleLiveTestStep(String operationId, ProxyMethodExample example) {
        this.operationId = operationId;
        this.example = example;
    }

    public String getOperationId() {
        return operationId;
    }

    public ProxyMethodExample getExample() {
        return example;
    }

    public void setExample(ProxyMethodExample example) {
        this.example = example;
    }
}
