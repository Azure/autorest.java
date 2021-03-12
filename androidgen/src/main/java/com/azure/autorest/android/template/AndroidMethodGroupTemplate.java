package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.template.MethodGroupTemplate;

public class AndroidMethodGroupTemplate extends MethodGroupTemplate {
    private static MethodGroupTemplate _instance = new AndroidMethodGroupTemplate();

    protected AndroidMethodGroupTemplate() {
    }

    public static MethodGroupTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void writeServiceProxyConstruction(JavaBlock constructor, MethodGroupClient methodGroupClient) {
        ClassType proxyType = ClassType.AndroidRestProxy;
        constructor.line(String.format("this.service = %1$s.create(%2$s.class, client.getHttpPipeline(), client.getJacksonSerder());",
                proxyType.getName(), methodGroupClient.getProxy().getName()));
    }
}

