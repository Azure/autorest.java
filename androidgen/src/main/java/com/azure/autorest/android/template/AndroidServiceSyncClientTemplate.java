package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.ServiceSyncClientTemplate;

import java.util.Set;

public class AndroidServiceSyncClientTemplate extends ServiceSyncClientTemplate {
    private static final AndroidServiceSyncClientTemplate instance = new AndroidServiceSyncClientTemplate();

    public static AndroidServiceSyncClientTemplate getInstance() { return instance;}

    private AndroidServiceSyncClientTemplate() {
    }

    @Override
    protected void addAnnotationImports(Set<String> imports) {
    }

    @Override
    protected void addClassAnnotation(JavaFile javaFile, ServiceClient serviceClient) {
    }
}
