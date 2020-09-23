package com.azure.autorest.postprocessor.ls.models;

public class CodeLensProvider
{
    private boolean resolveProvider;

    public void setResolveProvider(boolean resolveProvider){
        this.resolveProvider = resolveProvider;
    }
    public boolean getResolveProvider(){
        return this.resolveProvider;
    }
}