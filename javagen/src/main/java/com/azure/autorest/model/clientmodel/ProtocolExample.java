package com.azure.autorest.model.clientmodel;

public class ProtocolExample {
    private ClientMethod clientMethod;

    private MethodGroupClient methodGroupClient;

    private String builderName;

    private String filename;

    private ProxyMethodExample proxyMethodExample;

    public ProtocolExample(ClientMethod clientMethod, MethodGroupClient methodGroupClient, String builderName, String filename, ProxyMethodExample proxyMethodExample) {
        this.clientMethod = clientMethod;
        this.methodGroupClient = methodGroupClient;
        this.builderName = builderName;
        this.filename = filename;
        this.proxyMethodExample = proxyMethodExample;
    }

    public ClientMethod getClientMethod() {
        return clientMethod;
    }

    public MethodGroupClient getMethodGroupClient() {
        return methodGroupClient;
    }

    public String getBuilderName() {
        return builderName;
    }

    public String getFilename() {
        return filename;
    }

    public ProxyMethodExample getProxyMethodExample() {
        return proxyMethodExample;
    }
}
