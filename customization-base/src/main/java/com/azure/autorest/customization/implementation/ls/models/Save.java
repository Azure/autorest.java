// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

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
