package com.azure.autorest.customization.implementation.ls.models;

import java.util.List;

public class CompletionProvider
{
    private boolean resolveProvider;

    private List<String> triggerCharacters;

    public void setResolveProvider(boolean resolveProvider){
        this.resolveProvider = resolveProvider;
    }
    public boolean getResolveProvider(){
        return this.resolveProvider;
    }
    public void setTriggerCharacters(List<String> triggerCharacters){
        this.triggerCharacters = triggerCharacters;
    }
    public List<String> getTriggerCharacters(){
        return this.triggerCharacters;
    }
}

