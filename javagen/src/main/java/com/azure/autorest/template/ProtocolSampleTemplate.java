package com.azure.autorest.template;

import com.azure.autorest.model.javamodel.JavaFile;

public class ProtocolSampleTemplate {
    private static ProtocolSampleTemplate _instance = new ProtocolSampleTemplate();

    protected ProtocolSampleTemplate() {}

    public static ProtocolSampleTemplate getInstance() {
        return _instance;
    }

    public void write(JavaFile javaFile) {

    }

}
