package com.azure.autorest.android.template;

import com.azure.autorest.template.ServiceSyncClientTemplate;

public class AndroidServiceSyncClientTemplate extends ServiceSyncClientTemplate {
    private static AndroidServiceSyncClientTemplate _instance = new AndroidServiceSyncClientTemplate();
    protected AndroidServiceSyncClientTemplate() {
    }

    public static ServiceSyncClientTemplate getInstance() {
        return _instance;
    }

    protected void addServiceClientAnnotationImport(java.util.Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.ServiceClient");
    }
}
