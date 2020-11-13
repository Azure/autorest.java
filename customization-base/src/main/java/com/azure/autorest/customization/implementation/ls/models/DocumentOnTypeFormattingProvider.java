package com.azure.autorest.customization.implementation.ls.models;

import java.util.List;

public class DocumentOnTypeFormattingProvider
{
    private String firstTriggerCharacter;

    private List<String> moreTriggerCharacter;

    public void setFirstTriggerCharacter(String firstTriggerCharacter){
        this.firstTriggerCharacter = firstTriggerCharacter;
    }
    public String getFirstTriggerCharacter(){
        return this.firstTriggerCharacter;
    }
    public void setMoreTriggerCharacter(List<String> moreTriggerCharacter){
        this.moreTriggerCharacter = moreTriggerCharacter;
    }
    public List<String> getMoreTriggerCharacter(){
        return this.moreTriggerCharacter;
    }
}
