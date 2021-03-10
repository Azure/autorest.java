package com.azure.autorest.android.template;

import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.autorest.template.DefaultTemplateFactory;
import com.azure.autorest.template.MethodGroupTemplate;
import com.azure.autorest.template.ProxyTemplate;

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
}
