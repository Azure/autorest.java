// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template.android;

import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.model.javamodel.JavaEnum;
import com.azure.autorest.template.EnumTemplate;

import java.util.Set;

public class AndroidEnumTemplate extends EnumTemplate {

    private static final EnumTemplate INSTANCE = new AndroidEnumTemplate();

    private AndroidEnumTemplate() {
    }

    public static EnumTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected String getStringEnumImport() {
        return "com.azure.android.core.util.ExpandableStringEnum";
    }

    @Override
    protected void addGeneratedImport(Set<String> imports) {
    }

    @Override
    protected void addGeneratedAnnotation(JavaContext context) {
    }

    @Override
    protected void addGeneratedAnnotation(JavaEnum context) {
    }
}
