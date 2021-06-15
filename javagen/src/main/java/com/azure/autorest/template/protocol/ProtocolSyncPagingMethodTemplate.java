package com.azure.autorest.template.protocol;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.javamodel.JavaClass;

public class ProtocolSyncPagingMethodTemplate extends ProtocolMethodBaseTemplate {
    private static ProtocolSyncPagingMethodTemplate _instance = new ProtocolSyncPagingMethodTemplate();

    protected ProtocolSyncPagingMethodTemplate() {
    }

    public static ProtocolSyncPagingMethodTemplate getInstance() {
        return _instance;
    }

    @Override
    public void write(ClientMethod clientMethod, JavaClass typeBlock) {

    }
}
