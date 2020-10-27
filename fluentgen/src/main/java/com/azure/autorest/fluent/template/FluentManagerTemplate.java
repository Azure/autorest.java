/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.projectmodel.Project;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpClient;
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
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.util.Configuration;
import com.azure.core.util.logging.ClientLogger;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentManagerTemplate {

    private static final FluentManagerTemplate INSTANCE = new FluentManagerTemplate();

    public static FluentManagerTemplate getInstance() {
        return INSTANCE;
    }

    public void write(FluentManager manager, Project project, JavaFile javaFile) {
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

        String managerName = manager.getType().getName();

        Set<String> imports = new HashSet<>(Arrays.asList(
                // java
                Objects.class.getName(),
                Duration.class.getName(),
                ChronoUnit.class.getName(),
                List.class.getName(),
                ArrayList.class.getName(),
                // azure-core
                TokenCredential.class.getName(),
                ClientLogger.class.getName(),
                Configuration.class.getName(),
                HttpClient.class.getName(),
                HttpPipeline.class.getName(),
                HttpPipelineBuilder.class.getName(),
                HttpPipelinePolicy.class.getName(),
                HttpPolicyProviders.class.getName(),
                RequestIdPolicy.class.getName(),
                RetryPolicy.class.getName(),
                AddDatePolicy.class.getName(),
                HttpLoggingPolicy.class.getName(),
                HttpLogOptions.class.getName(),
                BearerTokenAuthenticationPolicy.class.getName(),
                UserAgentPolicy.class.getName(),
                // azure-core-management
                AzureProfile.class.getName()
        ));

        imports.add(String.format("%1$s.%2$s", builderPackageName, builderTypeName));
        imports.add(String.format("%1$s.%2$s", serviceClientPackageName, serviceClientTypeName));

        manager.getProperties().forEach(property -> {
            imports.add(property.getFluentType().getFullName());
            imports.add(property.getFluentImplementType().getFullName());
        });
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment -> {
            comment.description(manager.getDescription());
        });

        javaFile.publicFinalClass(managerName, classBlock -> {
            manager.getProperties().forEach(property -> {
                classBlock.privateMemberVariable(property.getFluentType().getName(), property.getName());
            });

            classBlock.privateFinalMemberVariable(serviceClientTypeName, ModelNaming.MANAGER_PROPERTY_CLIENT);

            // Constructor
            classBlock.privateConstructor(String.format("%1$s(HttpPipeline httpPipeline, AzureProfile profile, Duration defaultPollInterval)", managerName) , methodBlock -> {
                methodBlock.line("Objects.requireNonNull(httpPipeline, \"'httpPipeline' cannot be null.\");");
                methodBlock.line("Objects.requireNonNull(profile, \"'profile' cannot be null.\");");
                methodBlock.line(String.format("this.%1$s = new %2$s()", ModelNaming.MANAGER_PROPERTY_CLIENT, builderTypeName));
                methodBlock.indent(() -> {
                    methodBlock.line(".pipeline(httpPipeline)");
                    methodBlock.line(".endpoint(profile.getEnvironment().getResourceManagerEndpoint())");
                    if (requiresSubscriptionIdParameter) {
                        methodBlock.line(".subscriptionId(profile.getSubscriptionId())");
                    }
                    methodBlock.line(".defaultPollInterval(defaultPollInterval)");
                    methodBlock.line(".buildClient();");
                });
            });

            // authenticate()
            classBlock.javadocComment(comment -> {
                comment.description(String.format("Creates an instance of %1$s service API entry point.", manager.getServiceName()));
                comment.param("credential", "the credential to use");
                comment.param("profile", "the Azure profile for client");
                comment.methodReturns(String.format("the %1$s service API instance", manager.getServiceName()));
            });
            classBlock.publicStaticMethod(String.format("%1$s authenticate(TokenCredential credential, AzureProfile profile)", managerName), methodBlock -> {
                methodBlock.line("Objects.requireNonNull(credential, \"'credential' cannot be null.\");");
                methodBlock.line("Objects.requireNonNull(profile, \"'profile' cannot be null.\");");
                methodBlock.methodReturn("configure().authenticate(credential, profile)");
            });

            // configure()
            classBlock.javadocComment(comment -> {
                comment.description(String.format("Gets a Configurable instance that can be used to create %1$s with optional configuration.", managerName));
                comment.methodReturns("the Configurable instance allowing configurations");
            });
            classBlock.publicStaticMethod("Configurable configure()", methodBlock -> {
                methodBlock.methodReturn(String.format("new %1$s.Configurable()", managerName));
            });

            // Configurable class
            javaFile.line();
            String configurableClassText = FluentUtils.loadTextFromResource("Manager_Configurable.txt",
                    "service-name", manager.getServiceName(),
                    "manager-class", manager.getType().getName(),
                    "package-name", project.getNamespace(),
                    "package-version", project.getVersion()
            );
            javaFile.text(configurableClassText);

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
