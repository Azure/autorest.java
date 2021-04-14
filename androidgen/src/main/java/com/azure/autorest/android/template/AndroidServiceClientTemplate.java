package com.azure.autorest.android.template;

import com.azure.autorest.template.ServiceClientTemplate;

public class AndroidServiceClientTemplate extends ServiceClientTemplate {
    private static ServiceClientTemplate _instance = new AndroidServiceClientTemplate();


    protected AndroidServiceClientTemplate() {
    }

    public static ServiceClientTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void addServiceClientAnnotationImport(java.util.Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.ServiceClient");
    }

    @Override
    protected void addSerializerImport(java.util.Set<String> imports) {
        imports.add("com.azure.android.core.serde.jackson.JacksonSerder");
    }

    @Override
    protected String writeSerializerInitialization() {
        return "JacksonSerder.createDefault()";
    }

    @Override
    protected String writeRetryPolicyInitialization() {
        return "RetryPolicy.withExponentialBackoff()";
    }

    @Override
    protected void writeSerializerMemberInitialization(com.azure.autorest.model.javamodel.JavaBlock constructorBlock) {
        constructorBlock.line("this.jacksonSerder = jacksonSerder;");
    }
}
