package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.javamodel.JavaFile;

public class ProtocolSampleTemplate {
    private static final ProtocolSampleTemplate _instance = new ProtocolSampleTemplate();

    protected ProtocolSampleTemplate() {}

    public static ProtocolSampleTemplate getInstance() {
        return _instance;
    }

    public void write(ClientMethod method, MethodGroupClient client, String builderName, String filename, ProxyMethodExample example, JavaFile javaFile) {
        System.err.println(method.getName());
        System.err.println(method.getType());
        System.err.println(filename);
        System.err.println(example);
        System.err.println();

        javaFile.declareImport("com.azure.core.util.BinaryData");
        javaFile.declareImport("com.azure.identity.DefaultAzureCredentialBuilder");

        String clientName = client.getInterfaceName() + "Client";
        javaFile.publicClass(null, filename, classBlock -> {
            classBlock.publicStaticMethod("void main(String[] args)", methodBlock -> {
                methodBlock.line(String.format("%s client = new %s()", clientName, builderName));
                methodBlock.line(".endpoint(System.getenv(\"ENDPOINT\"))");
                methodBlock.line(".credential(new DefaultAzureCredentialBuilder().build())");
                methodBlock.line(".buildAccountsClient();");
                methodBlock.line("BinaryData response = client.foo().getValue();");
            });
        });
    }
}
