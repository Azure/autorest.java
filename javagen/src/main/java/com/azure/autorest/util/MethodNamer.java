// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

public class MethodNamer {

    private final String baseName;

    public MethodNamer(String baseName) {
        this.baseName = baseName;
    }

    public String getMethodName() {
        return baseName;
    }

    public String getPagingAsyncSinglePageMethodName() {
        return this.getMethodName() + "SinglePageAsync";
    }

    public String getPagingSinglePageMethodName() {
        return this.getMethodName() + "SinglePage";
    }

    public String getSimpleAsyncMethodName() {
        return this.getMethodName() + "Async";
    }

    public String getSimpleAsyncRestResponseMethodName() {
        return this.getMethodName() + "WithResponseAsync";
    }

    public String getSimpleRestResponseMethodName() {
        return this.getMethodName() + "WithResponse";
    }

    public String getLroBeginMethodName() {
        return "begin" + CodeNamer.toPascalCase(this.getMethodName());
    }

    public String getLroBeginAsyncMethodName() {
        return "begin" + CodeNamer.toPascalCase(this.getSimpleAsyncMethodName());
    }
}
