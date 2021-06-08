package com.azure.autorest.template.protocol;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.javamodel.JavaClass;

public class ProtocolAsyncPagingMethodTemplate extends ProtocolMethodBaseTemplate {
    private static ProtocolAsyncPagingMethodTemplate _instance = new ProtocolAsyncPagingMethodTemplate();

    protected ProtocolAsyncPagingMethodTemplate() {
    }

    public static ProtocolAsyncPagingMethodTemplate getInstance() {
        return _instance;
    }

    @Override
    public void write(ClientMethod model, JavaClass context) {

    }
}
