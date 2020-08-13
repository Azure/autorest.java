package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.template.WrapperClientMethodTemplate;

public class AndroidWrapperClientMethodTemplate extends WrapperClientMethodTemplate {
    private static final AndroidWrapperClientMethodTemplate instance = new AndroidWrapperClientMethodTemplate();
    protected AndroidWrapperClientMethodTemplate() {
    }

    public static WrapperClientMethodTemplate getInstance() {
        return instance;
    }

    @Override
    protected void addMethodReturnAnnotation(JavaType typeBlock, ClientMethodType methodType) {
    }
}
