// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.extensionmodel;

public class XmsLongRunningOperationOptions {

    // azure-async-operation
    // location
    // original-uri
    private String finalStateVia;

    public String getFinalStateVia() {
        return finalStateVia;
    }

    public void setFinalStateVia(String finalStateVia) {
        this.finalStateVia = finalStateVia;
    }
}
