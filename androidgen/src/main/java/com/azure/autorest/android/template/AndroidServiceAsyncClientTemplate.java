package com.azure.autorest.android.template;

import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.template.ServiceAsyncClientTemplate;

public class AndroidServiceAsyncClientTemplate extends ServiceAsyncClientTemplate {
    private static AndroidServiceAsyncClientTemplate _instance = new AndroidServiceAsyncClientTemplate();

    protected AndroidServiceAsyncClientTemplate() {
    }

    public static ServiceAsyncClientTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void addServiceClientAnnotationImports(java.util.Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.ServiceClient");
    }

    @Override
    protected void addGeneratedAnnotation(JavaContext classBlock) {
    }
}
