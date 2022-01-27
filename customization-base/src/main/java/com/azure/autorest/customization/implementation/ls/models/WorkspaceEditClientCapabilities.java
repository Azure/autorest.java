// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

public class WorkspaceEditClientCapabilities {
    private boolean documentChanges;

    public boolean isDocumentChanges() {
        return documentChanges;
    }

    public void setDocumentChanges(boolean documentChanges) {
        this.documentChanges = documentChanges;
    }
}
