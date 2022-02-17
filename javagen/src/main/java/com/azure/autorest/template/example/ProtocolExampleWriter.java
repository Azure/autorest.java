// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template.example;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Scheme;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProtocolExample;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.http.ContentType;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.SyncPoller;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProtocolExampleWriter {

    private final Logger LOGGER = new PluginLogger(Javagen.getPluginInstance(), ProtocolExampleWriter.class);

    private final Set<String> imports;
    private final Consumer<JavaBlock> clientInitializationWriter;
    private final Consumer<JavaBlock> clientMethodInvocationWriter;
    private final Consumer<JavaBlock> assertionWriter;

    @SuppressWarnings("unchecked")
    public ProtocolExampleWriter(ProtocolExample protocolExample) {
        JavaSettings settings = JavaSettings.getInstance();

        final ClientMethod method = protocolExample.getClientMethod();
        final AsyncSyncClient syncClient = protocolExample.getSyncClient();
        final ServiceClient serviceClient = protocolExample.getClientBuilder().getServiceClient();
        final String builderName = protocolExample.getClientBuilder().getClassName();
        final ProxyMethodExample proxyMethodExample = protocolExample.getProxyMethodExample();
        final String clientVarName = CodeNamer.toCamelCase(syncClient.getClassName());

        // import
        this.imports = new HashSet<>();
        syncClient.addImportsTo(imports, false);
        syncClient.getClientBuilder().addImportsTo(imports, false);
        method.addImportsTo(imports, false, settings);

        // credential
        imports.add("com.azure.identity.DefaultAzureCredentialBuilder");
        ClassType.AzureKeyCredential.addImportsTo(imports, false);
        ClassType.Configuration.addImportsTo(imports, false);

        // assertion
        imports.add("org.junit.jupiter.api.Assertions");
        imports.add(LongRunningOperationStatus.class.getName());

        // method invocation
        // parameter values and required invocation on RequestOptions
        int numParam = method.getParameters().size();
        List<String> params = new ArrayList<>();
        for (int i = 0; i < numParam; i++) {
            params.add("null");
        }

        StringBuilder binaryDataStmt = new StringBuilder();

        List<String> requestOptionsStmts = new ArrayList<>();

        List<String> clientParameterLines = new ArrayList<>();
        Set<ServiceClientProperty> processedServiceClientProperties = new HashSet<>();

        List<ProxyMethodParameter> proxyMethodParameters = getProxyMethodParameters(method.getProxyMethod(), method.getParameters());

        proxyMethodExample.getParameters().forEach((parameterName, parameterValue) -> {
            boolean matchRequiredParameter = false;
            for (int parameterIndex = 0; parameterIndex < numParam; parameterIndex++) {
                ProxyMethodParameter proxyMethodParameter = proxyMethodParameters.get(parameterIndex);
                if (proxyMethodParameter != null) {
                    if (getSerializedName(proxyMethodParameter).equalsIgnoreCase(parameterName)) {
                        // parameter in example found in method signature

                        if (proxyMethodParameter.getClientType() != ClassType.BinaryData) {
                            // ignore query with array, query parameter is not included in LLC method signature

                            String exampleValue = proxyMethodParameter.getRequestParameterLocation() == RequestParameterLocation.QUERY
                                    ? parameterValue.getUnescapedQueryValue().toString()
                                    : parameterValue.getObjectValue().toString();
                            params.set(parameterIndex, proxyMethodParameter.getClientType().defaultValueExpression(exampleValue));
                        } else {
                            // BinaryData
                            String binaryDataValue = ClassType.String.defaultValueExpression(parameterValue.getJsonString());
                            binaryDataStmt.append(
                                    String.format("BinaryData %s = BinaryData.fromString(%s);",
                                            parameterName, binaryDataValue));
                            params.set(parameterIndex, parameterName);
                        }
                        matchRequiredParameter = true;
                        break;
                    }
                }
            }
            if (!matchRequiredParameter) {
                // parameter in example not found in method signature, check those parameters defined in spec but was left out of method signature

                method.getProxyMethod().getAllParameters().stream().filter(p -> !p.getFromClient()).filter(p -> getSerializedName(p).equalsIgnoreCase(parameterName)).findFirst().ifPresent(p -> {
                    switch (p.getRequestParameterLocation()) {
                        case QUERY:
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

                        case HEADER:
                            requestOptionsStmts.add(
                                    String.format("requestOptions.addHeader(\"%s\", %s);",
                                            parameterName,
                                            p.getClientType().defaultValueExpression(parameterValue.getObjectValue().toString())));
                            break;

                        case BODY:
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

        // client initialization
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
        if (serviceClient.getSecurityInfo() != null && serviceClient.getSecurityInfo().getSecurityTypes() != null) {
            if (serviceClient.getSecurityInfo().getSecurityTypes().contains(Scheme.SecuritySchemeType.AADTOKEN)) {
                credentialExpr = ".credential(new DefaultAzureCredentialBuilder().build())";
            } else if (serviceClient.getSecurityInfo().getSecurityTypes().contains(Scheme.SecuritySchemeType.AZUREKEY)) {
                credentialExpr = ".credential(new AzureKeyCredential(Configuration.getGlobalConfiguration().get(\"API_KEY\")))";
            } else {
                credentialExpr = "";
            }
        } else {
            credentialExpr = "";
        }

        this.clientInitializationWriter = methodBlock -> {
            // client
            String clientInit = "%1$s %2$s = new %3$s()" +
                    "%4$s" +  // credentials
                    "%5$s" +  // client properties
                    ".%6$s();";
            methodBlock.line(
                    String.format(clientInit,
                            syncClient.getClassName(), clientVarName,
                            builderName,
                            credentialExpr,
                            clientParameterExpr,
                            protocolExample.getClientBuilder().getBuilderMethodNameForSyncClient(syncClient)));
        };

        this.clientMethodInvocationWriter = methodBlock -> {
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
                    "%1$s response = %2$s.%3$s(%4$s);",
                    method.getReturnValue().getType(),
                    clientVarName,
                    method.getName(),
                    String.join(", ", params)));
        };

        this.assertionWriter = methodBlock -> {
            Optional<ProxyMethodExample.Response> responseOpt = proxyMethodExample.getPrimaryResponse();
            if (responseOpt.isPresent()) {
                ProxyMethodExample.Response response = responseOpt.get();
                IType returnType = method.getReturnValue().getType();
                if (returnType instanceof GenericType) {
                    GenericType responseType = (GenericType) returnType;
                    if (Response.class.getSimpleName().equals(responseType.getName())) {
                        // Response<>

                        // assert status code
                        methodBlock.line(String.format("Assertions.assertEquals(%1$s, response.getStatusCode());", response.getStatusCode()));
                        // assert headers
                        response.getHttpHeaders().stream().forEach(header -> {
                            String expectedValueStr = ClassType.String.defaultValueExpression(header.getValue());
                            String keyStr = ClassType.String.defaultValueExpression(header.getName());
                            methodBlock.line(String.format("Assertions.assertEquals(%1$s, response.getHeaders().get(%2$s).getValue());", expectedValueStr, keyStr));
                        });
                        // assert JSON body
                        if (ContentType.APPLICATION_JSON.equals(method.getProxyMethod().getRequestContentType())
                                && responseType.getTypeArguments().length > 0
                                && responseType.getTypeArguments()[0] == ClassType.BinaryData) {
                            String expectedJsonStr = ClassType.String.defaultValueExpression(response.getJsonBody());
                            methodBlock.line(String.format("Assertions.assertEquals(%1$s, response.getValue().toString());", expectedJsonStr));
                        }
                    } else if (SyncPoller.class.getSimpleName().equals(responseType.getName())) {
                        // SyncPoller<>

                        if (response.getStatusCode() / 100 == 2) {
                            // it should have a 202 leading to SUCCESSFULLY_COMPLETED
                            // but x-ms-examples usually does not include the final result
                            methodBlock.line("Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.waitForCompletion().getStatus());");
                        }
                    } else if (PagedIterable.class.getSimpleName().equals(responseType.getName())) {
                        // PagedIterable<>

                        // assert status code
                        methodBlock.line(String.format("Assertions.assertEquals(%1$s, response.iterableByPage().iterator().next().getStatusCode());", response.getStatusCode()));
                        // assert headers
                        response.getHttpHeaders().stream().forEach(header -> {
                            String expectedValueStr = ClassType.String.defaultValueExpression(header.getValue());
                            String keyStr = ClassType.String.defaultValueExpression(header.getName());
                            methodBlock.line(String.format("Assertions.assertEquals(%1$s, response.iterableByPage().iterator().next().getHeaders().get(%2$s).getValue());", expectedValueStr, keyStr));
                        });
                        // assert JSON of first item, or assert count=0
                        if (ContentType.APPLICATION_JSON.equals(method.getProxyMethod().getRequestContentType())
                                && responseType.getTypeArguments().length > 0
                                && responseType.getTypeArguments()[0] == ClassType.BinaryData
                                && method.getMethodPageDetails() != null
                                && response.getBody() instanceof Map) {
                            Map<String, Object> bodyMap = (Map<String, Object>) response.getBody();
                            if (bodyMap.containsKey(method.getMethodPageDetails().getRawItemName())) {
                                Object items = bodyMap.get(method.getMethodPageDetails().getRawItemName());
                                if (items instanceof List) {
                                    List<Object> itemArray = (List<Object>) items;
                                    if (itemArray.isEmpty()) {
                                        methodBlock.line("Assertions.assertEquals(0, response.stream().count());");
                                    } else {
                                        Object firstItem = itemArray.iterator().next();
                                        String expectedJsonStr = ClassType.String.defaultValueExpression(response.getJson(firstItem));
                                        methodBlock.line(String.format("Assertions.assertEquals(%1$s, response.iterator().next().toString());", expectedJsonStr));
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                methodBlock.line("Assertions.assertNotNull(response);");
            }
        };
    }

    public Set<String> getImports() {
        return imports;
    }

    public void writeClientInitialization(JavaBlock methodBlock) {
        clientInitializationWriter.accept(methodBlock);
    }

    public void writeClientMethodInvocation(JavaBlock methodBlock) {
        clientMethodInvocationWriter.accept(methodBlock);
    }

    public void writeAssertion(JavaBlock methodBlock) {
        assertionWriter.accept(methodBlock);
    }

    private static String getSerializedName(ProxyMethodParameter parameter) {
        String serializedName = parameter.getRequestParameterName();
        if (serializedName == null && parameter.getRequestParameterLocation() == RequestParameterLocation.BODY) {
            serializedName = parameter.getName();
        }
        return serializedName;
    }

    private List<ProxyMethodParameter> getProxyMethodParameters(
            ProxyMethod proxyMethod,
            List<ClientMethodParameter> clientMethodParameters) {
        // the list of proxy method parameters will be 1-1 with list of client method parameters

        Map<String, ProxyMethodParameter> proxyMethodParameterByClientParameterName = proxyMethod.getParameters().stream()
                .collect(Collectors.toMap(p -> CodeNamer.getEscapedReservedClientMethodParameterName(p.getName()), Function.identity()));
        List<ProxyMethodParameter> proxyMethodParameters = new ArrayList<>();
        for (ClientMethodParameter clientMethodParameter : clientMethodParameters) {
            ProxyMethodParameter proxyMethodParameter = proxyMethodParameterByClientParameterName.get(clientMethodParameter.getName());
            proxyMethodParameters.add(proxyMethodParameter);

            if (proxyMethodParameter == null) {
                // this should not happen unless we changed the naming of client method parameter from proxy method parameter
                LOGGER.warn("Failed to find proxy method parameter for client method parameter with name '{}'", clientMethodParameter.getName());
            }
        }
        return proxyMethodParameters;
    }
}
