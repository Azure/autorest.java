// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

public class MethodNamer {

    public static String getPagingAsyncSinglePageMethodName(String baseName) {
        return baseName + "SinglePageAsync";
    }

    public static String getPagingSinglePageMethodName(String baseName) {
        return baseName + "SinglePage";
    }

    public static String getSimpleAsyncMethodName(String baseName) {
        return baseName + "Async";
    }

    public static String getSimpleAsyncRestResponseMethodName(String baseName) {
        return baseName + "WithResponseAsync";
    }

    public static String getSimpleRestResponseMethodName(String baseName) {
        return baseName + "WithResponse";
    }

    public static String getLroBeginAsyncMethodName(String baseName) {
        return "begin" + CodeNamer.toPascalCase(baseName) + "Async";
    }

    public static String getLroBeginMethodName(String baseName) {
        return "begin" + CodeNamer.toPascalCase(baseName);
    }

    private final String baseName;

    public MethodNamer(String baseName) {
        this.baseName = baseName;
    }

    public String getMethodName() {
        return baseName;
    }

    public String getPagingAsyncSinglePageMethodName() {
        return getPagingAsyncSinglePageMethodName(this.getMethodName());
    }

    public String getPagingSinglePageMethodName() {
        return getPagingSinglePageMethodName(this.getMethodName());
    }

    public String getSimpleAsyncMethodName() {
        return getSimpleAsyncMethodName(this.getMethodName());
    }

    public String getSimpleAsyncRestResponseMethodName() {
        return getSimpleAsyncRestResponseMethodName(this.getMethodName());
    }

    public String getSimpleRestResponseMethodName() {
        return getSimpleRestResponseMethodName(this.getMethodName());
    }

    public String getLroBeginAsyncMethodName() {
        return getLroBeginAsyncMethodName(this.getSimpleAsyncMethodName());
    }

    public String getLroBeginMethodName() {
        return getLroBeginMethodName(this.getMethodName());
    }

    public String getLroModelBeginMethodName() {
        return "begin" + CodeNamer.toPascalCase(this.getMethodName()) + "WithModel";
    }

    public String getLroModelBeginAsyncMethodName() {
        return "begin" + CodeNamer.toPascalCase(this.getMethodName()) + "WithModel" + "Async";
    }
}
