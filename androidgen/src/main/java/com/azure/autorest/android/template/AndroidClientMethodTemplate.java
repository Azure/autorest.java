package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.template.ClientMethodTemplate;

public class AndroidClientMethodTemplate extends ClientMethodTemplate {
    private static AndroidClientMethodTemplate _instance = new AndroidClientMethodTemplate();

    protected AndroidClientMethodTemplate() {
    }

    public static ClientMethodTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void addMethodReturnAnnotation(JavaType typeBlock, ClientMethodType methodType) {
    }
}
