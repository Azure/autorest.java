package com.azure.autorest.template.protocol;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.javamodel.JavaClass;

public class ProtocolAsyncPagingSinglePageMethodTemplate extends ProtocolMethodBaseTemplate {
    private static ProtocolAsyncPagingSinglePageMethodTemplate _instance = new ProtocolAsyncPagingSinglePageMethodTemplate();

    protected ProtocolAsyncPagingSinglePageMethodTemplate() {
    }

    public static ProtocolAsyncPagingSinglePageMethodTemplate getInstance() {
        return _instance;
    }

    @Override
    public void write(ClientMethod clientMethod, JavaClass typeBlock) {

    }
}
