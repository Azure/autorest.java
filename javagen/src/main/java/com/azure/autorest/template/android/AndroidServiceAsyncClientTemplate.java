// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template.android;

import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.template.ServiceAsyncClientTemplate;

public class AndroidServiceAsyncClientTemplate extends ServiceAsyncClientTemplate {
    private static final AndroidServiceAsyncClientTemplate INSTANCE = new AndroidServiceAsyncClientTemplate();

    protected AndroidServiceAsyncClientTemplate() {
    }

    public static ServiceAsyncClientTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void addServiceClientAnnotationImports(java.util.Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.ServiceClient");
    }

    @Override
    protected void addGeneratedAnnotation(JavaContext classBlock) {
    }
}
