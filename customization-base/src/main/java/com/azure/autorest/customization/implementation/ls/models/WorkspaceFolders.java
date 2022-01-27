// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

public class WorkspaceFolders
{
    private boolean supported;

    private boolean changeNotifications;

    public void setSupported(boolean supported){
        this.supported = supported;
    }
    public boolean getSupported(){
        return this.supported;
    }
    public void setChangeNotifications(boolean changeNotifications){
        this.changeNotifications = changeNotifications;
    }
    public boolean getChangeNotifications(){
        return this.changeNotifications;
    }
}

