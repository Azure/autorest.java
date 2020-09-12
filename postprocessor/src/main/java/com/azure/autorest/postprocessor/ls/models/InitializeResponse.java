package com.azure.autorest.postprocessor.ls.models;

public class InitializeResponse {
    private Capabilities capabilities;

    public void setCapabilities(Capabilities capabilities){
        this.capabilities = capabilities;
    }
    public Capabilities getCapabilities(){
        return this.capabilities;
    }
}
