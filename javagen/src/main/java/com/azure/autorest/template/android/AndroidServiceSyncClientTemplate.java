// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template.android;

import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.template.ServiceSyncClientTemplate;

public class AndroidServiceSyncClientTemplate extends ServiceSyncClientTemplate {
    private static final AndroidServiceSyncClientTemplate INSTANCE = new AndroidServiceSyncClientTemplate();

    protected AndroidServiceSyncClientTemplate() {
    }

    public static ServiceSyncClientTemplate getInstance() {
        return INSTANCE;
    }

    protected void addServiceClientAnnotationImport(java.util.Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.ServiceClient");
    }

    @Override
    protected void addGeneratedAnnotation(JavaContext classBlock) {
    }
}
