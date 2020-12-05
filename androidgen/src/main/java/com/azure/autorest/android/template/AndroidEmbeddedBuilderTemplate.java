// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.Constructor;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AndroidEmbeddedBuilderTemplate {
    private static final String EMBEDDED_BUILDER_CLS_NAME = "Builder";
    private static final String BUILD_METHOD_NAME = "build";
    private static final String BASE_URL_PROPERTY_NAME = "baseUrl";

    private final ServiceClient serviceClient;
    private final AsyncSyncClient asyncSyncClient;
    private final List<ClientMethodParameter> commonProperties;
    private final HostMapping hostMapping;

    AndroidEmbeddedBuilderTemplate(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
        this.hostMapping = HostMapping.create(this.serviceClient);
        this.asyncSyncClient = null;

        final Constructor serviceClientCtr = this.serviceClient.getConstructors().get(0);
        this.commonProperties = commonPropertiesFromCtr(serviceClientCtr);
        this.commonProperties.add(credentialInterceptorParameter());
    }

    AndroidEmbeddedBuilderTemplate(AsyncSyncClient asyncSyncClient) {
        this.serviceClient = asyncSyncClient.getServiceClient();
        this.hostMapping = HostMapping.create(this.serviceClient);
        this.asyncSyncClient = asyncSyncClient;

        final Constructor serviceClientCtr = this.serviceClient.getConstructors().get(0);
        this.commonProperties = commonPropertiesFromCtr(serviceClientCtr);
        this.commonProperties.add(credentialInterceptorParameter());
    }

    void addImportsTo(Set<String> imports) {
        this.serviceClient.addImportsTo(imports, false, true, JavaSettings.getInstance());
        this.commonProperties.stream().forEach(p -> p.addImportsTo(imports, false));
        fixImportForBuilderParameters(this.commonProperties, imports);
    }

    void write(JavaClass clientClass) {
        // Write builder as a embedded class of given containerClass.
        JavaSettings settings = JavaSettings.getInstance();

        final boolean isSyncOrAsyncClient = asyncSyncClient == null ? false : true;
        final String clientClsName = isSyncOrAsyncClient
                ? asyncSyncClient.getClassName()
                : serviceClient.getInterfaceName();

        clientClass.javadocComment(comment -> {
            comment.description(String.format("A builder for creating a new instance of the %1$s type.", clientClsName));
        });

        clientClass.publicStaticFinalClass(EMBEDDED_BUILDER_CLS_NAME, classBlock -> {
            // Add class level variable and setter for all ServiceClient client properties.
            this.serviceClient.getProperties().stream().filter(p -> !p.isReadOnly()).forEach(p -> {
                writeBuilderProperty(settings, classBlock, p.getDescription(), p.getName(), p.getType());
            });

            // Add class level variables and setters for all common properties.
            // e.g. ServiceClient.Builder, Credential Interceptor
            this.commonProperties.stream().forEach(p -> {
                writeBuilderProperty(settings, classBlock, p.getDescription(), p.getName(), p.getWireType());
            });

            hostMapping.getHostParams().stream()
                .filter(h -> !this.serviceClient.getProperties().stream().anyMatch(p -> p.getName().equals(h.getName())))
                .forEach(hp -> {
                    writeBuilderProperty(settings, classBlock, hp.getDescription(), hp.getName(), hp.getWireType());
                });

            if (!this.hostMapping.serviceHostPropertyIsBaseUrl()) {
                writeBuilderProperty(settings, classBlock, "base url of the service", BASE_URL_PROPERTY_NAME,
                        ClassType.String);
            }

            classBlock.javadocComment(comment -> {
                comment.description(String.format("Builds an instance of %1$s with the provided parameters", clientClsName));
                comment.methodReturns(String.format("an instance of %1$s", clientClsName));
            });

            classBlock.method(JavaVisibility.Public, null,
                String.format("%1$s %2$s()", clientClsName, BUILD_METHOD_NAME), function -> {
                    if (!this.hostMapping.serviceHostPropertyIsBaseUrl()) {
                        function.ifBlock(String.format("%1$s == null", BASE_URL_PROPERTY_NAME), ifBlock -> {
                            function.line("this.%1$s = \"%2$s\";", BASE_URL_PROPERTY_NAME, hostMapping.getBaseUrlPattern());
                        });
                    }

                    final List<String> constructorArgsSet1 = new ArrayList<>();
                    this.serviceClient.getProperties().stream().filter(p -> !p.isReadOnly()).forEach(p -> {
                        // 1. Collect ServiceClient Ctr args.
                        constructorArgsSet1.add(p.getName());
                        // 2. Set default value for ServiceClient properties whose builder setters are not called by the app.
                        if (p.getDefaultValueExpression() != null) {
                            function.ifBlock(String.format("%1$s == null", p.getName()), ifBlock -> {
                                function.line("this.%1$s = %2$s;", p.getName(), p.getDefaultValueExpression());
                            });
                        }
                    });

                    final List<String> constructorArgsSet2 = new ArrayList<>();
                    this.commonProperties.stream()
                        .filter(p -> p.getWireType() != ClassType.AndroidOkHttpInterceptor)
                        .forEach(p -> {
                            // 1. Collect ServiceClient Ctr args.
                            if (isBuilderType(p)) {
                                constructorArgsSet2.add(p.getName() + ".build()");
                            } else {
                                constructorArgsSet2.add(p.getName());
                            }
                            // 2. Set-up/Init common properties and
                            // Set default value for common properties whose builder setters are not called
                            // by the app.
                            if (p.getWireType() == ClassType.AndroidRestClientBuilder) {
                                setupRestClientBuilder(function, serviceClient, commonProperties, p);
                            } else {
                                if (p.getDefaultValue() != null) {
                                    function.ifBlock(String.format("%1$s == null", p.getName()), ifBlock -> {
                                        function.line("this.%1$s = %2$s;", p.getName(), p.getDefaultValue());
                                    });
                                }
                            }
                        });

                    final String constructorArgsStr = Stream
                        .concat(constructorArgsSet2.stream(), constructorArgsSet1.stream())
                        .collect(Collectors.joining(", "));

                    if (isSyncOrAsyncClient) {
                        // For separate Sync|Async Client scenario, method call in each of
                        // these Client get delegated to the internal Client implementation.
                        final String internalClientTypeName = JavaSettings.getInstance()
                            .shouldGenerateClientInterfaces()
                                ? serviceClient.getInterfaceName()
                                : serviceClient.getClassName();

                        function.line(String.format("%1$s internalClient = new %2$s(%3$s);", internalClientTypeName,
                            internalClientTypeName, constructorArgsStr));

                        final boolean wrapServiceClient = this.asyncSyncClient.getMethodGroupClient() == null;
                        if (wrapServiceClient) {
                            function.line("return new %1$s(internalClient);", clientClsName);
                        } else {
                            function.line("return new %1$s(internalClient.get%2$s());", clientClsName, CodeNamer
                                    .toPascalCase(asyncSyncClient.getMethodGroupClient().getVariableName()));
                        }
                    } else {
                        // Client composing both sync and async methods.
                        function.line(String.format("return new %2$s(%3$s);", clientClsName, clientClsName,
                                constructorArgsStr));
                    }
                });
        });
    }

    private List<ClientMethodParameter> commonPropertiesFromCtr(final Constructor constructor) {
        return constructor.getParameters()
            .stream()
            .map(p -> {
            if (p.getWireType() == ClassType.AndroidRestClient) {
                return new ClientMethodParameter.Builder()
                    .description("The Azure Core generic ServiceClient Builder.")
                    .isFinal(false)
                    .wireType(ClassType.AndroidRestClientBuilder)
                    .name("serviceClientBuilder")
                    .isRequired(true)
                    .isConstant(false)
                    .fromClient(true)
                    .annotations(new ArrayList<>())
                    .defaultValue("new ServiceClient.Builder()")
                    .build();
            } else {
                return p;
            }
        }).collect(Collectors.toList());
    }

    private ClientMethodParameter credentialInterceptorParameter() {
        return new ClientMethodParameter.Builder()
                .description("The Interceptor to set intercept request and set credentials.")
                .isFinal(false)
                .wireType(ClassType.AndroidOkHttpInterceptor)
                .name("credentialInterceptor")
                .isRequired(true)
                .isConstant(false)
                .fromClient(true)
                .annotations(new ArrayList<>())
                .build();
    }

    private static void writeBuilderProperty(JavaSettings settings,
                                             JavaClass classBlock,
                                             String propDescription,
                                             String propName,
                                             IType propType) {
        classBlock.blockComment(settings.getMaximumJavadocCommentWidth(), comment -> {
            comment.line(propDescription);
        });
        classBlock.privateMemberVariable(String.format("%1$s %2$s", propType, propName));

        classBlock.javadocComment(comment -> {
            comment.description(String.format("Sets %1$s", propDescription));
            comment.param(propName, String.format("the %1$s value.", propName));
            comment.methodReturns(String.format("the %1$s", EMBEDDED_BUILDER_CLS_NAME));
        });
        classBlock.publicMethod(String.format("%1$s %2$s(%3$s %4$s)",
                EMBEDDED_BUILDER_CLS_NAME,
                CodeNamer.toCamelCase(propName),
                propType,
                propName),
            function -> {
                function.line(String.format("this.%1$s = %2$s;", propName, propName));
                function.methodReturn("this");
            });
    }

    private void fixImportForBuilderParameters(List<ClientMethodParameter> parameters, Set<String> imports) {
        parameters.stream().forEach(p -> {
            fixImportIfBuilderParameter(p, imports);
        });
    }

    private void fixImportIfBuilderParameter(ClientMethodParameter parameter, Set<String> imports) {
        if (isBuilderType(parameter)) {
            ClassType builderClsType = ((ClassType) parameter.getWireType());
            final String builderFullName = builderClsType.getFullName();
            if (imports.remove(builderFullName)) {
                final int idx = builderFullName.length() - ".Builder".length();
                imports.add(builderFullName.substring(0, idx));
            }
        }
    }

    private boolean isBuilderType(ClientMethodParameter parameter) {
        if (!(parameter.getWireType() instanceof ClassType)) {
            return false;
        }
        ClassType builderClsType = ((ClassType) parameter.getWireType());
        return builderClsType.getName().endsWith(".Builder");
    }

    private void setupRestClientBuilder(JavaBlock function,
                                        ServiceClient serviceClient,
                                        List<ClientMethodParameter> commonProperties,
                                        ClientMethodParameter restClientBuilder) {
        function.ifBlock(String.format("%1$s == null", restClientBuilder.getName()), ifBlock -> {
            String anyHostParamAbsentExpression = hostMapping.anyHostParamAbsentExpression();
            if (!anyHostParamAbsentExpression.isEmpty()) {
                function.ifBlock(anyHostParamAbsentExpression, illegalArgBlock -> {
                    illegalArgBlock.line("throw new IllegalArgumentException(\"Missing required parameters '%s'.\");",
                            String.join(", ", hostMapping.getHostParams().stream().map(p -> p.getName()).collect(Collectors.toList())));
                });
            }
            ifBlock.line("this.%1$s = %2$s;", restClientBuilder.getName(), restClientBuilder.getDefaultValue());
        });
        String allHostParamPresentExpression = hostMapping.allHostParamPresentExpression();
        if (!allHostParamPresentExpression.isEmpty()) {
            function.ifBlock(allHostParamPresentExpression, ifBlock -> {
                final String baseUrlPropertyName = this.hostMapping.serviceHostPropertyIsBaseUrl()
                        ? this.hostMapping.HOST_PROPERTY_NAME
                        : BASE_URL_PROPERTY_NAME;

                ifBlock.line("final String retrofitBaseUrl = %s", hostMapping.getBaseUrlExpression(baseUrlPropertyName));
                ifBlock.line("%s.setBaseUrl(retrofitBaseUrl);", restClientBuilder.getName());
            });
        } else {
            function.line("%1$s.setBaseUrl(%2$s);", restClientBuilder.getName(), this.hostMapping.HOST_PROPERTY_NAME);
        }

        Optional<ClientMethodParameter> credInterceptorOpt = commonProperties
            .stream()
            .filter(p -> p.getWireType() == ClassType.AndroidOkHttpInterceptor)
            .findFirst();

        if (credInterceptorOpt.isPresent()) {
            ClientMethodParameter credInterceptor = credInterceptorOpt.get();
            function.ifBlock(String.format("%s != null", credInterceptor.getName()), ifBlock -> {
                ifBlock.line("%s.setCredentialsInterceptor(%s);", restClientBuilder.getName(), credInterceptor.getName());
            });
        }
    }
}