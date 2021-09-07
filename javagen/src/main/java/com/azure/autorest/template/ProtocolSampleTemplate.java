package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.ArrayList;
import java.util.List;

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
        method.getParameters().forEach(p -> System.err.println(p.getName()));
        System.err.println();

        List<String> imports = new ArrayList<>();
        imports.add("com.azure.core.http.rest.PagedIterable");
        imports.add("com.azure.core.http.rest.Response");
        imports.add("com.azure.core.util.BinaryData");
        imports.add("com.azure.identity.DefaultAzureCredentialBuilder");
        javaFile.declareImport(imports);

        int numParam = method.getParameters().size();
        // Parameter values to pass in
        List<String> params = new ArrayList<>();
        for (int i = 0; i < numParam; i++) {
            params.add("null");
        }
        example.getParameters().forEach((key, value) -> {
            for (int i = 0; i < numParam; i++) {
                ClientMethodParameter p = method.getParameters().get(i);
                if (p.getName().equals(key)) {
                    if (p.getClientType() != ClassType.BinaryData) {
                        // Simple type
                        params.set(i, '"' + value.getObjectValue().toString() + '"');
                    } else {
                        // BinaryData

                    }
                }
            }
        });

        String clientName = client.getInterfaceName() + "Client";
        javaFile.publicClass(null, filename, classBlock -> {
            classBlock.publicStaticMethod("void main(String[] args)", methodBlock -> {
                String clientInit = "%s client = new %s()" +
                        ".endpoint(System.getenv(\"ENDPOINT\"))" +
                        ".credential(new DefaultAzureCredentialBuilder().build())" +
                        ".build%s();";
                methodBlock.line(String.format(clientInit, clientName, builderName, clientName));
                methodBlock.line(String.format(
                        "%s response = client.%s(%s);",
                        method.getReturnValue().getType(),
                        method.getName(),
                        String.join(", ", params)));
            });
        });
    }
}
