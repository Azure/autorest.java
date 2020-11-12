package com.azure.autorest.customization.implementation.ls.models;

import java.util.List;

public class ExecuteCommandProvider
{
    private List<String> commands;

    public void setCommands(List<String> commands){
        this.commands = commands;
    }
    public List<String> getCommands(){
        return this.commands;
    }
}
