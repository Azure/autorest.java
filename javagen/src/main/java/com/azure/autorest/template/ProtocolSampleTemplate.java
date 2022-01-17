// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ProtocolExample;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Configuration;
import com.azure.core.util.Context;
import com.azure.core.util.serializer.CollectionFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ProtocolSampleTemplate implements IJavaTemplate<ProtocolExample, JavaFile> {
    private static final ProtocolSampleTemplate _instance = new ProtocolSampleTemplate();

    protected ProtocolSampleTemplate() {}

    public static ProtocolSampleTemplate getInstance() {
        return _instance;
    }

    @SuppressWarnings("unchecked")
    public void write(ProtocolExample protocolExample, JavaFile javaFile) {
        ClientMethod method = protocolExample.getClientMethod();
        AsyncSyncClient client = protocolExample.getClient();
        ServiceClient serviceClient = protocolExample.getServiceClient();
        String builderName = protocolExample.getBuilderName();
        String filename = protocolExample.getFilename();
        ProxyMethodExample example = protocolExample.getProxyMethodExample();

        // Import
        List<String> imports = new ArrayList<>();
        imports.add(client.getPackageName() + "." + client.getClassName());
        imports.add(client.getPackageName() + "." + builderName);
        imports.add(PagedIterable.class.getName());
        imports.add(Response.class.getName());
        imports.add(BinaryData.class.getName());
        imports.add(Context.class.getName());
        imports.add(Configuration.class.getName());
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

        List<String> clientParameterLines = new ArrayList<>();
        Set<ServiceClientProperty> processedServiceClientProperties = new HashSet<>();

        example.getParameters().forEach((parameterName, parameterValue) -> {
            boolean matchRequiredParameter = false;
            for (int i = 0; i < numParam; i++) {
                ClientMethodParameter p = method.getParameters().get(i);
                // TODO: should use getRequestParameterName from proxy method parameter, instead of getName from client method parameter
                if (p.getName().equalsIgnoreCase(parameterName)) {
                    if (p.getClientType() != ClassType.BinaryData) {
                        // TODO: handle query with array

                        String exampleValue = p.getLocation() == RequestParameterLocation.Query
                                ? parameterValue.getUnescapedQueryValue().toString()
                                : parameterValue.getObjectValue().toString();
                        params.set(i, p.getClientType().defaultValueExpression(exampleValue));
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
                method.getProxyMethod().getAllParameters().stream().filter(p -> !p.getFromClient()).filter(p -> getSerializedName(p).equalsIgnoreCase(parameterName)).findFirst().ifPresent(p -> {
                    switch (p.getRequestParameterLocation()) {
                        case Query:
                            if (parameterValue.getUnescapedQueryValue() instanceof List && p.getCollectionFormat() != null) {
                                List<Object> elements = (List<Object>) parameterValue.getUnescapedQueryValue();
                                if (p.getExplode()) {
                                    // collectionFormat: multi
                                    for (Object element : elements) {
                                        requestOptionsStmts.add(
                                                String.format("requestOptions.addQueryParam(\"%s\", %s);",
                                                        parameterName,
                                                        p.getClientType().defaultValueExpression(element.toString())));
                                    }
                                } else {
                                    // collectionFormat: csv, ssv, tsv, pipes
                                    String delimiter = p.getCollectionFormat().getDelimiter();
                                    String exampleValue = elements.stream()
                                            .map(Object::toString)
                                            .collect(Collectors.joining(delimiter));
                                    requestOptionsStmts.add(
                                            String.format("requestOptions.addQueryParam(\"%s\", %s);",
                                                    parameterName,
                                                    p.getClientType().defaultValueExpression(exampleValue)));
                                }
                            } else {
                                requestOptionsStmts.add(
                                        String.format("requestOptions.addQueryParam(\"%s\", %s);",
                                                parameterName,
                                                p.getClientType().defaultValueExpression(parameterValue.getUnescapedQueryValue().toString())));
                            }
                            break;

                        case Header:
                            requestOptionsStmts.add(
                                    String.format("requestOptions.addHeader(\"%s\", %s);",
                                            parameterName,
                                            p.getClientType().defaultValueExpression(parameterValue.getObjectValue().toString())));
                            break;

                        case Body:
                            requestOptionsStmts.add(
                                    String.format("requestOptions.setBody(BinaryData.fromString(%s));",
                                            ClassType.String.defaultValueExpression(parameterValue.getJsonString())));
                            break;

                        // Path cannot be optional
                    }
                });

                method.getProxyMethod().getAllParameters().stream().filter(ProxyMethodParameter::getFromClient).filter(p -> p.getName().equalsIgnoreCase(parameterName)).findFirst().ifPresent(p -> {
                    String clientValue = p.getClientType()
                            .defaultValueExpression(parameterValue.getObjectValue().toString());

                    serviceClient.getProperties().stream().filter(p1 -> Objects.equals(p.getName(), p1.getName())).findFirst().ifPresent(serviceClientProperty -> {
                        processedServiceClientProperties.add(serviceClientProperty);

                        clientParameterLines.add(
                                String.format(".%1$s(%2$s)", serviceClientProperty.getAccessorMethodSuffix(), clientValue));
                    });
                });
            }
        });

        // required service client properties
        serviceClient.getProperties().stream().filter(ServiceClientProperty::isRequired).filter(p -> !processedServiceClientProperties.contains(p)).forEach(serviceClientProperty -> {
            String defaultValueExpression = serviceClientProperty.getDefaultValueExpression();
            if (defaultValueExpression == null) {
                defaultValueExpression = String.format("Configuration.getGlobalConfiguration().get(\"%1$s\")",
                        serviceClientProperty.getName().toUpperCase(Locale.ROOT));
            }

            clientParameterLines.add(
                    String.format(".%1$s(%2$s)", serviceClientProperty.getAccessorMethodSuffix(), defaultValueExpression));
        });
        String clientParameterExpr = String.join("", clientParameterLines);

        // credentials
        String credentialExpr;
        Set<JavaSettings.CredentialType> credentialTypes = JavaSettings.getInstance().getCredentialTypes();
        if (credentialTypes.contains(JavaSettings.CredentialType.TOKEN_CREDENTIAL)) {
            credentialExpr = ".credential(new DefaultAzureCredentialBuilder().build())";
        } else if (credentialTypes.contains(JavaSettings.CredentialType.AZURE_KEY_CREDENTIAL)) {
            credentialExpr = ".credential(new AzureKeyCredential(Configuration.getGlobalConfiguration().get(\"API_KEY\")))";
        } else {
            credentialExpr = "";
        }

        javaFile.publicClass(null, filename, classBlock -> {
            classBlock.publicStaticMethod("void main(String[] args)", methodBlock -> {
                // client
                String clientInit = "%1$s client = new %2$s()" +
                        "%3$s" +  // credentials
                        "%4$s" +  // client properties
                        ".%5$s();";
                methodBlock.line(
                        String.format(clientInit,
                                client.getClassName(), builderName,
                                credentialExpr,
                                clientParameterExpr,
                                protocolExample.getBuildMethodName()));

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

    private static String getSerializedName(ProxyMethodParameter parameter) {
        String serializedName = parameter.getRequestParameterName();
        if (serializedName == null && parameter.getRequestParameterLocation() == RequestParameterLocation.Body) {
            serializedName = parameter.getName();
        }
        return serializedName;
    }
}
