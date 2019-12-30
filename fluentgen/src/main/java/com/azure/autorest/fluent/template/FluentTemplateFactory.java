package com.azure.autorest.fluent.template;

import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.autorest.template.DefaultTemplateFactory;

public class FluentTemplateFactory extends DefaultTemplateFactory {

    @Override
    public ClientMethodTemplate getClientMethodTemplate() {
        return FluentClientMethodTemplate.getInstance();
    }
}
