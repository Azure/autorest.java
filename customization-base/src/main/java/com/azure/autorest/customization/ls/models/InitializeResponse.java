package com.azure.autorest.customization.ls.models;

public class InitializeResponse {
    private ServerCapabilities capabilities;

    public void setCapabilities(ServerCapabilities capabilities){
        this.capabilities = capabilities;
    }
    public ServerCapabilities getCapabilities(){
        return this.capabilities;
    }
}
