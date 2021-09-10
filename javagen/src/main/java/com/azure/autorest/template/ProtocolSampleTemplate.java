// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ProtocolExample;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProtocolSampleTemplate implements IJavaTemplate<ProtocolExample, JavaFile> {
    private static final ProtocolSampleTemplate _instance = new ProtocolSampleTemplate();

    protected ProtocolSampleTemplate() {}

    public static ProtocolSampleTemplate getInstance() {
        return _instance;
    }

    public void write(ProtocolExample protocolExample, JavaFile javaFile) {
        ClientMethod method = protocolExample.getClientMethod();
        MethodGroupClient client = protocolExample.getMethodGroupClient();
        String builderName = protocolExample.getBuilderName();
        String filename = protocolExample.getFilename();
        ProxyMethodExample example = protocolExample.getProxyMethodExample();

        // Import
        List<String> imports = new ArrayList<>();
        imports.add("com.azure.core.http.rest.PagedIterable");
        imports.add("com.azure.core.http.rest.RequestOptions");
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

        StringBuilder binaryDataStmt = new StringBuilder();

        List<String> requestOptionsStmts = new ArrayList<>();

        example.getParameters().forEach((key, value) -> {
            boolean match = false;
            for (int i = 0; i < numParam; i++) {
                ClientMethodParameter p = method.getParameters().get(i);
                if (p.getName().equals(key)) {
                    if (p.getClientType() != ClassType.BinaryData) {
                        // Simple type
                        params.set(i, '"' + value.getObjectValue().toString() + '"');
                    } else {
                        // BinaryData
                        String binaryDataValue = '"' + value.getJsonString().replace("\"", "\\\"") + '"';
                        binaryDataStmt.append(String.format(
                                "BinaryData %s = BinaryData.fromString(%s);", key, binaryDataValue));
                        params.set(i, key);
                    }
                    match = true;
                    break;
                }
            }
            if (!match) {
                method.getProxyMethod().getAllParameters().stream().filter(p -> p.getName().equals(key)).findFirst().ifPresent(p -> {
                    if (p.getRequestParameterLocation() == RequestParameterLocation.Query) {
                        requestOptionsStmts.add(String.format("requestOptions.addQueryParam(\"%s\", \"%s\");",
                                key, value.getObjectValue().toString()));
                    } else if (p.getRequestParameterLocation() == RequestParameterLocation.Header) {
                        requestOptionsStmts.add(String.format("requestOptions.addHeader(\"%s\", \"%s\");",
                                key, value.getObjectValue().toString()));
                    }
                });
            }
        });

        javaFile.publicClass(null, filename, classBlock -> {
            classBlock.publicStaticMethod("void main(String[] args)", methodBlock -> {
                String clientName = client.getInterfaceName() + "Client";
                String credentialExpr;
                Set<JavaSettings.CredentialType> credentialTypes = JavaSettings.getInstance().getCredentialTypes();
                if (credentialTypes.contains(JavaSettings.CredentialType.TOKEN_CREDENTIAL)) {
                    credentialExpr = "new DefaultAzureCredentialBuilder().build()";
                } else if (credentialTypes.contains(JavaSettings.CredentialType.AZURE_KEY_CREDENTIAL)) {
                    credentialExpr = "new AzureKeyCredential(System.getenv(\"API_KEY\"))";
                } else {
                    credentialExpr = "new DefaultAzureCredentialBuilder().build()";
                }

                String clientInit = "%s client = new %s()" +
                        ".endpoint(System.getenv(\"ENDPOINT\"))" +
                        ".credential(%s)" +
                        ".build%s();";
                methodBlock.line(String.format(clientInit, clientName, builderName, credentialExpr, clientName));
                if (binaryDataStmt.length() > 0) {
                    methodBlock.line(binaryDataStmt.toString());
                }
                if (requestOptionsStmts.size() > 0) {
                    methodBlock.line("RequestOptions requestOptions = new RequestOptions();");
                    requestOptionsStmts.forEach(methodBlock::line);
                }
                methodBlock.line(String.format(
                        "%s response = client.%s(%s);",
                        method.getReturnValue().getType(),
                        method.getName(),
                        String.join(", ", params)));
            });
        });
    }
}
