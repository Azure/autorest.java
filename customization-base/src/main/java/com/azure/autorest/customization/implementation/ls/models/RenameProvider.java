// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

public class RenameProvider
{
    private boolean prepareProvider;

    public void setPrepareProvider(boolean prepareProvider){
        this.prepareProvider = prepareProvider;
    }
    public boolean getPrepareProvider(){
        return this.prepareProvider;
    }
}
