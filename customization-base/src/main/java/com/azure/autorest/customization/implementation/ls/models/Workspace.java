// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

public class Workspace {
    private WorkspaceFolders workspaceFolders;

    public void setWorkspaceFolders(WorkspaceFolders workspaceFolders){
        this.workspaceFolders = workspaceFolders;
    }
    public WorkspaceFolders getWorkspaceFolders(){
        return this.workspaceFolders;
    }
}
