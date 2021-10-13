// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ProtocolExample;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;

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
        AsyncSyncClient client = protocolExample.getClient();
        String builderName = protocolExample.getBuilderName();
        String filename = protocolExample.getFilename();
        ProxyMethodExample example = protocolExample.getProxyMethodExample();
        String hostName = protocolExample.getHostName();

        // Import
        List<String> imports = new ArrayList<>();
        imports.add(client.getPackageName() + "." + client.getClassName());
        imports.add(client.getPackageName() + "." + builderName);
        imports.add(PagedIterable.class.getName());
        imports.add(Response.class.getName());
        imports.add(BinaryData.class.getName());
        imports.add(Context.class.getName());
        imports.add(ClassType.RequestOptions.getFullName());
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

        example.getParameters().forEach((parameterName, parameterValue) -> {
            boolean matchRequiredParameter = false;
            for (int i = 0; i < numParam; i++) {
                ClientMethodParameter p = method.getParameters().get(i);
                if (p.getName().equalsIgnoreCase(parameterName)) {
                    if (p.getClientType() != ClassType.BinaryData) {
                        // simple type
                        params.set(i, p.getClientType().defaultValueExpression(parameterValue.getObjectValue().toString()));
                    } else {
                        // BinaryData
                        String binaryDataValue = ClassType.String.defaultValueExpression(parameterValue.getJsonString());
                        binaryDataStmt.append(
                                String.format("BinaryData %s = BinaryData.fromString(%s);",
                                        parameterName, binaryDataValue));
                        params.set(i, parameterName);
                    }
                    matchRequiredParameter = true;
                    break;
                }
            }
            if (!matchRequiredParameter) {
                method.getProxyMethod().getAllParameters().stream().filter(p -> !p.getFromClient()).filter(p -> p.getName().equalsIgnoreCase(parameterName)).findFirst().ifPresent(p -> {
                    String clientValue = p.getClientType()
                            .defaultValueExpression(parameterValue.getObjectValue().toString());

                    switch (p.getRequestParameterLocation()) {
                        case Query:
                            requestOptionsStmts.add(
                                    String.format("requestOptions.addQueryParam(\"%s\", %s);",
                                            parameterName, clientValue));
                            break;

                        case Header:
                            requestOptionsStmts.add(
                                    String.format("requestOptions.addHeader(\"%s\", %s);",
                                            parameterName, clientValue));
                            break;

                        case Body:
                            String binaryDataValue = ClassType.String.defaultValueExpression(parameterValue.getJsonString());
                            requestOptionsStmts.add(
                                    String.format("requestOptions.setBody(BinaryData.fromString(%s));",
                                            binaryDataValue));
                            break;

                        // Path cannot be optional
                    }
                });
            }
        });

        javaFile.publicClass(null, filename, classBlock -> {
            classBlock.publicStaticMethod("void main(String[] args)", methodBlock -> {
                String credentialExpr;
                Set<JavaSettings.CredentialType> credentialTypes = JavaSettings.getInstance().getCredentialTypes();
                if (credentialTypes.contains(JavaSettings.CredentialType.TOKEN_CREDENTIAL)) {
                    credentialExpr = ".credential(new DefaultAzureCredentialBuilder().build())";
                } else if (credentialTypes.contains(JavaSettings.CredentialType.AZURE_KEY_CREDENTIAL)) {
                    credentialExpr = ".credential(new AzureKeyCredential(System.getenv(\"API_KEY\")))";
                } else {
                    credentialExpr = "";
                }

                // client
                String clientInit = "%s client = new %s()" +
                        ".%s(System.getenv(\"%s\"))" +
                        "%s" +
                        ".build%s();";
                methodBlock.line(
                        String.format(clientInit,
                                client.getClassName(), builderName, hostName, hostName.toUpperCase(),
                                credentialExpr, client.getClassName()));

                // binaryData
                if (binaryDataStmt.length() > 0) {
                    methodBlock.line(binaryDataStmt.toString());
                }
                // requestOptions and context
                methodBlock.line("RequestOptions requestOptions = new RequestOptions();");
                requestOptionsStmts.forEach(methodBlock::line);
                for (int i = 0; i < numParam; i++) {
                    ClientMethodParameter parameter = method.getParameters().get(i);
                    if (parameter.getClientType() == ClassType.RequestOptions) {
                        params.set(i, "requestOptions");
                    } else if (parameter.getClientType() == ClassType.Context) {
                        params.set(i, "Context.NONE");
                    }
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
