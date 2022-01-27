// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

public class WorkspaceSymbolClientCapabilities {
    private boolean dynamicRegistration;

    private SymbolKindCapabilities symbolKind;

    public boolean isDynamicRegistration() {
        return dynamicRegistration;
    }

    public void setDynamicRegistration(boolean dynamicRegistration) {
        this.dynamicRegistration = dynamicRegistration;
    }

    public SymbolKindCapabilities getSymbolKind() {
        return symbolKind;
    }

    public void setSymbolKind(SymbolKindCapabilities symbolKind) {
        this.symbolKind = symbolKind;
    }
}
