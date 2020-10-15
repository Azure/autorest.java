/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaModifier;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.AddDatePolicy;
import com.azure.core.http.policy.BearerTokenAuthenticationPolicy;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.policy.HttpPolicyProviders;
import com.azure.core.http.policy.RequestIdPolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.management.AzureEnvironment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentManagerTemplate implements IJavaTemplate<FluentManager, JavaFile> {

    private static final FluentManagerTemplate INSTANCE = new FluentManagerTemplate();

    public static FluentManagerTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(FluentManager manager, JavaFile javaFile) {
        ServiceClient serviceClient = manager.getClient().getServiceClient();

        final boolean hasEndpointParameter = serviceClient.getProperties().stream()
                .anyMatch(p -> p.getName().equals("endpoint"));
        if (!hasEndpointParameter) {
            throw new IllegalStateException("'endpoint' (or '$host') is required in ServiceClient properties, properties are "
                    + serviceClient.getProperties().stream().map(ServiceClientProperty::getName).collect(Collectors.joining(",")));
        }

        final boolean requiresSubscriptionIdParameter = serviceClient.getProperties().stream()
                .anyMatch(p -> p.getName().equals("subscriptionId"));

        String builderPackageName = ClientModelUtil.getServiceClientBuilderPackageName(serviceClient);
        String builderTypeName = serviceClient.getInterfaceName() + ClientModelUtil.getBuilderSuffix();
        String serviceClientPackageName = ClientModelUtil.getServiceClientInterfacePackageName();
        String serviceClientTypeName = serviceClient.getInterfaceName();

        MethodTemplate authenticateMethod = MethodTemplate.builder()
                .imports(Arrays.asList(
                        TokenCredential.class.getName(),
                        AzureEnvironment.class.getName(),
                        Objects.class.getName(),
                        // http pipeline
                        HttpPipeline.class.getName(),
                        HttpPipelinePolicy.class.getName(),
                        List.class.getName(),
                        ArrayList.class.getName(),
                        HttpPolicyProviders.class.getName(),
                        RequestIdPolicy.class.getName(),
                        RetryPolicy.class.getName(),
                        AddDatePolicy.class.getName(),
                        HttpLoggingPolicy.class.getName(),
                        HttpLogOptions.class.getName(),
                        BearerTokenAuthenticationPolicy.class.getName(),
                        HttpPipelineBuilder.class.getName()
                ))
                .modifiers(Collections.singleton(JavaModifier.Static))
                .methodSignature(requiresSubscriptionIdParameter
                        ? String.format("%1$s authenticate(TokenCredential credential, AzureEnvironment environment, String subscriptionId)", manager.getType().getName())
                        : String.format("%1$s authenticate(TokenCredential credential, AzureEnvironment environment)", manager.getType().getName()))
                .comment(comment -> {
                    comment.description(String.format("Creates an instance of %1$s service API entry point.", manager.getType().getName()));
                    comment.param("credential", "the credential to use");
                    comment.param("environment", "the Azure cloud");
                    if (requiresSubscriptionIdParameter) {
                        comment.param("subscriptionId", "the Azure subscription ID");
                    }
                    comment.methodReturns(String.format("the %1$s service API instance", manager.getType().getName()));
                })
                .method(method -> {
                    method.line("Objects.requireNonNull(credential, \"'credential' cannot be null.\");");
                    method.line("Objects.requireNonNull(environment, \"'environment' cannot be null.\");");
                    if (requiresSubscriptionIdParameter) {
                        method.line("Objects.requireNonNull(subscriptionId, \"'subscriptionId' cannot be null.\");");
                    }

                    method.line("List<HttpPipelinePolicy> policies = new ArrayList<>();");
                    method.line("policies.add(new RequestIdPolicy());");
                    method.line("HttpPolicyProviders.addBeforeRetryPolicies(policies);");
                    method.line("policies.add(new RetryPolicy());");
                    method.line("policies.add(new AddDatePolicy());");
                    method.line("policies.add(new BearerTokenAuthenticationPolicy(credential, environment.getManagementEndpoint() + \"/.default\"));");
                    method.line("HttpPolicyProviders.addAfterRetryPolicies(policies);");
                    method.line("policies.add(new HttpLoggingPolicy(new HttpLogOptions()));");
                    method.line("HttpPipeline httpPipeline = new HttpPipelineBuilder()");
                    method.indent(() -> {
                        method.line(".policies(policies.toArray(new HttpPipelinePolicy[0]))");
                        method.line(".build();");
                    });

                    method.methodReturn(requiresSubscriptionIdParameter
                            ? String.format("new %1$s(httpPipeline, environment, subscriptionId)", manager.getType().getName())
                            : String.format("new %1$s(httpPipeline, environment)", manager.getType().getName()));
                })
                .build();

        Set<String> imports = new HashSet<>();
        imports.add(String.format("%1$s.%2$s", builderPackageName, builderTypeName));
        imports.add(String.format("%1$s.%2$s", serviceClientPackageName, serviceClientTypeName));
        authenticateMethod.addImportsTo(imports);
        manager.getProperties().forEach(property -> {
            imports.add(property.getFluentType().getFullName());
            imports.add(property.getFluentImplementType().getFullName());
        });
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment -> {
            comment.description(manager.getDescription());
        });

        javaFile.publicFinalClass(manager.getType().getName(), classBlock -> {
            manager.getProperties().forEach(property -> {
                classBlock.privateMemberVariable(property.getFluentType().getName(), property.getName());
            });

            classBlock.privateFinalMemberVariable(serviceClientTypeName, ModelNaming.MANAGER_PROPERTY_CLIENT);

            classBlock.privateConstructor(requiresSubscriptionIdParameter
                    ? String.format("%1$s(HttpPipeline httpPipeline, AzureEnvironment environment, String subscriptionId)", manager.getType().getName())
                    : String.format("%1$s(HttpPipeline httpPipeline, AzureEnvironment environment)", manager.getType().getName()) , method -> {
                method.line("Objects.requireNonNull(httpPipeline, \"'httpPipeline' cannot be null.\");");
                method.line("Objects.requireNonNull(environment, \"'environment' cannot be null.\");");
                if (requiresSubscriptionIdParameter) {
                    method.line("Objects.requireNonNull(subscriptionId, \"'subscriptionId' cannot be null.\");");
                }
                method.line(String.format("this.%1$s = new %2$s()", ModelNaming.MANAGER_PROPERTY_CLIENT, builderTypeName));
                method.indent(() -> {
                    method.line(".pipeline(httpPipeline)");
                    method.line(".endpoint(environment.getResourceManagerEndpoint())" + (requiresSubscriptionIdParameter ? "" : ";"));
                    if (requiresSubscriptionIdParameter) {
                        method.line(".subscriptionId(subscriptionId)");
                    }
                    method.line(".buildClient();");
                });
            });

            authenticateMethod.writeMethod(classBlock);

            manager.getProperties().forEach(property -> {
                classBlock.javadocComment(comment -> {
                    comment.methodReturns(String.format("Resource collection API of %1$s.", property.getFluentType().getName()));
                });

                classBlock.publicMethod(String.format("%1$s %2$s()", property.getFluentType().getName(), property.getMethodName()), methodBlock -> {
                    methodBlock.ifBlock(String.format("this.%1$s == null", property.getName()), ifBlock -> {
                        methodBlock.line(String.format("this.%1$s = new %2$s(%3$s.%4$s(), this);",
                                property.getName(),
                                property.getFluentImplementType().getName(),
                                ModelNaming.MANAGER_PROPERTY_CLIENT, property.getInnerClientGetMethod()));
                    });
                    methodBlock.methodReturn(property.getName());
                });
            });

            classBlock.javadocComment(comment -> {
                comment.methodReturns(String.format("Wrapped service client %1$s providing direct access to the underlying auto-generated API implementation, based on Azure REST API.", serviceClientTypeName));
            });
            classBlock.publicMethod(String.format("%1$s %2$s()", serviceClientTypeName, ModelNaming.METHOD_SERVICE_CLIENT), methodBlock -> {
                methodBlock.methodReturn(String.format("this.%1$s", ModelNaming.MANAGER_PROPERTY_CLIENT));
            });
        });
    }
}
