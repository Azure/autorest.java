package com.azure.autorest.postprocessor.ls.models;

public class Save
{
    private boolean includeText;

    public void setIncludeText(boolean includeText){
        this.includeText = includeText;
    }
    public boolean isIncludeText(){
        return this.includeText;
    }
}
