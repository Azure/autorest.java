package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.ServiceAsyncClientTemplate;

import java.util.Set;

public class AndroidServiceAsyncClientTemplate extends ServiceAsyncClientTemplate {
    private static final AndroidServiceAsyncClientTemplate instance = new AndroidServiceAsyncClientTemplate();

    public static AndroidServiceAsyncClientTemplate getInstance() { return instance;}

    private AndroidServiceAsyncClientTemplate() {
    }

    @Override
    protected void addAnnotationImports(Set<String> imports) {
    }

    @Override
    protected void addClassAnnotation(JavaFile javaFile, ServiceClient serviceClient) {
    }
}
