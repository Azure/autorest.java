package com.azure.autorest.android.template;

import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.autorest.template.DefaultTemplateFactory;
import com.azure.autorest.template.EnumTemplate;
import com.azure.autorest.template.ExceptionTemplate;
import com.azure.autorest.template.MethodGroupTemplate;
import com.azure.autorest.template.ProxyTemplate;
import com.azure.autorest.template.ServiceClientBuilderTemplate;
import com.azure.autorest.template.ServiceClientTemplate;

public class AndroidTemplateFactory extends DefaultTemplateFactory {

    @Override
    public ProxyTemplate getProxyTemplate() {
        return AndroidProxyTemplate.getInstance();
    }

    @Override
    public ClientMethodTemplate getClientMethodTemplate() {
        return AndroidClientMethodTemplate.getInstance();
    }

    @Override
    public MethodGroupTemplate getMethodGroupTemplate() {
        return AndroidMethodGroupTemplate.getInstance();
    }

    @Override
    public ServiceClientTemplate getServiceClientTemplate() {
        return AndroidServiceClientTemplate.getInstance();
    }

    @Override
    public ServiceClientBuilderTemplate getServiceClientBuilderTemplate() {
        return AndroidServiceClientBuilderTemplate.getInstance();
    }

    @Override
    public EnumTemplate getEnumTemplate() {
        return AndroidEnumTemplate.getInstance();
    }

    @Override
    public ExceptionTemplate getExceptionTemplate() {
        return AndroidExceptionTemplate.getInstance();
    }
}
