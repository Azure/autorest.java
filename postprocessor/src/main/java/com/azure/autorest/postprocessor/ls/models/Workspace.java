package com.azure.autorest.postprocessor.ls.models;

public class Workspace
{
    private WorkspaceFolders workspaceFolders;

    public void setWorkspaceFolders(WorkspaceFolders workspaceFolders){
        this.workspaceFolders = workspaceFolders;
    }
    public WorkspaceFolders getWorkspaceFolders(){
        return this.workspaceFolders;
    }
}