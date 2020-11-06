package com.azure.autorest.customization.ls.models;

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
