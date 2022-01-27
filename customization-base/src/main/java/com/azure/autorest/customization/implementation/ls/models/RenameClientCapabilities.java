// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

public class RenameClientCapabilities {
    private boolean dynamicRegistration;

    private boolean prepareSupport;

    public boolean isDynamicRegistration() {
        return dynamicRegistration;
    }

    public void setDynamicRegistration(boolean dynamicRegistration) {
        this.dynamicRegistration = dynamicRegistration;
    }

    public boolean isPrepareSupport() {
        return prepareSupport;
    }

    public void setPrepareSupport(boolean prepareSupport) {
        this.prepareSupport = prepareSupport;
    }
}
