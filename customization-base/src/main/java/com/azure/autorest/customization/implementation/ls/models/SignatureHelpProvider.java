package com.azure.autorest.customization.implementation.ls.models;

import java.util.List;

public class SignatureHelpProvider
{
    private List<String> triggerCharacters;

    public void setTriggerCharacters(List<String> triggerCharacters){
        this.triggerCharacters = triggerCharacters;
    }
    public List<String> getTriggerCharacters(){
        return this.triggerCharacters;
    }
}
