// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.core.client.traits.AzureKeyCredentialTrait;
import com.azure.core.client.traits.ConfigurationTrait;
import com.azure.core.client.traits.EndpointTrait;
import com.azure.core.client.traits.HttpTrait;
import com.azure.core.client.traits.TokenCredentialTrait;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.policy.RetryOptions;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class representing a builder trait that adds additional context to the {@link ClientBuilder} client model.
 */
public class ClientBuilderTrait {

    public static final ClientBuilderTrait HTTP_TRAIT = createHttpTrait();

    public static final ClientBuilderTrait CONFIGURATION_TRAIT = createConfigurationTrait();

    public static final ClientBuilderTrait AZURE_KEY_CREDENTIAL_TRAIT = createAzureKeyCredentialTrait();

    public static final ClientBuilderTrait TOKEN_CREDENTIAL_TRAIT = createTokenCredentialTrait();

    private static ClientBuilderTrait ENDPOINT_TRAIT;

    private String traitInterfaceName;
    private List<String> importPackages;
    private List<ClientBuilderTraitMethod> clientBuilderTraitMethods;

    /**
     * Returns the trait interface name.
     * @return the trait interface name.
     */
    public String getTraitInterfaceName() {
        return traitInterfaceName;
    }

    /**
     * Sets the trait interface name.
     * @param traitInterfaceName  the trait interface name.
     */
    public void setTraitInterfaceName(String traitInterfaceName) {
        this.traitInterfaceName = traitInterfaceName;
    }

    /**
     * Returns the list of packages that needs to be imported for this trait.
     * @return The list of packages that needs to be imported for this trait.
     */
    public List<String> getImportPackages() {
        return importPackages;
    }

    /**
     * Sets the list of packages that needs to be imported for this trait.
     * @param importPackages the list of packages that needs to be imported for this trait.
     */
    public void setImportPackages(List<String> importPackages) {
        this.importPackages = importPackages;
    }

    /**
     * Returns the list of methods that this trait interface contains.
     * @return the list of methods that this trait interface contains.
     */
    public List<ClientBuilderTraitMethod> getTraitMethods() {
        return clientBuilderTraitMethods;
    }

    /**
     * Sets the list of methods that this trait interface contains.
     * @param clientBuilderTraitMethods the list of methods that this trait interface contains.
     */
    public void setTraitMethods(List<ClientBuilderTraitMethod> clientBuilderTraitMethods) {
        this.clientBuilderTraitMethods = clientBuilderTraitMethods;
    }

    private static ClientBuilderTrait createHttpTrait() {
        ClientBuilderTrait httpTrait = new ClientBuilderTrait();
        httpTrait.setTraitInterfaceName("HttpTrait");
        List<String> importPackages = new ArrayList<>();
        httpTrait.setImportPackages(importPackages);
        importPackages.add(HttpTrait.class.getName());

        List<ClientBuilderTraitMethod> httpClientBuilderTraitMethods = new ArrayList<>();
        httpTrait.setTraitMethods(httpClientBuilderTraitMethods);

        // pipeline
        ServiceClientProperty pipelineProperty = new ServiceClientProperty("The HTTP pipeline to send requests " +
                "through.", ClassType.HttpPipeline, "pipeline", false,
                JavaSettings.getInstance().isAzureOrFluent()
                        ? "new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build()"
                        : "createHttpPipeline()");
        Consumer<JavaBlock> pipelineMethodImpl = function -> {
            function.line(String.format("this.%1$s = %2$s;", "pipeline", "pipeline"));
            function.methodReturn("this");
        };
        ClientBuilderTraitMethod pipelineMethod = createTraitMethod("pipeline", "pipeline", ClassType.HttpPipeline ,
                pipelineProperty, "{@inheritDoc}", pipelineMethodImpl);
        importPackages.add(HttpPipeline.class.getName());

        httpClientBuilderTraitMethods.add(pipelineMethod);


        // httpClient
        ServiceClientProperty httpClientProperty = new ServiceClientProperty("The HTTP client used to send the request.",
                ClassType.HttpClient, "httpClient", false, null);
        Consumer<JavaBlock> httpClientMethodImpl = function -> {
            function.line(String.format("this.%1$s = %2$s;", "httpClient", "httpClient"));
            function.methodReturn("this");
        };
        ClientBuilderTraitMethod httpClientMethod = createTraitMethod("httpClient", "httpClient", ClassType.HttpClient,
                httpClientProperty, "{@inheritDoc}", httpClientMethodImpl);
        importPackages.add(HttpClient.class.getName());

        httpClientBuilderTraitMethods.add(httpClientMethod);

        // httpLogOptions
        ServiceClientProperty httpLogOptionsProperty = new ServiceClientProperty("The logging configuration for HTTP " +
                "requests and responses.",
                ClassType.HttpLogOptions, "httpLogOptions", false, null);
        Consumer<JavaBlock> httpLogOptionsMethodImpl = function -> {
            function.line(String.format("this.%1$s = %2$s;", "httpLogOptions", "httpLogOptions"));
            function.methodReturn("this");
        };
        ClientBuilderTraitMethod httpLogOptionsMethod = createTraitMethod("httpLogOptions", "httpLogOptions", ClassType.HttpLogOptions,
                httpLogOptionsProperty, "{@inheritDoc}", httpLogOptionsMethodImpl);
        importPackages.add(HttpLogOptions.class.getName());

        httpClientBuilderTraitMethods.add(httpLogOptionsMethod);

        // clientOptions
        ServiceClientProperty clientOptionsProperty = new ServiceClientProperty("The client options such as application ID and custom headers to set on a request.",
                ClassType.ClientOptions, "clientOptions", false, null);
        Consumer<JavaBlock> clientOptionsMethodImpl = function -> {
            function.line(String.format("this.%1$s = %2$s;", "clientOptions", "clientOptions"));
            function.methodReturn("this");
        };
        ClientBuilderTraitMethod clientOptionsMethod = createTraitMethod("clientOptions", "clientOptions", ClassType.ClientOptions,
                clientOptionsProperty, "{@inheritDoc}", clientOptionsMethodImpl);
        importPackages.add(ClientOptions.class.getName());

        httpClientBuilderTraitMethods.add(clientOptionsMethod);

        // retryOptions
        ServiceClientProperty retryOptionsProperty =
                new ServiceClientProperty("The retry options to configure retry policy for failed "
                + "requests.",
                ClassType.RetryOptions, "retryOptions", false, null);
        Consumer<JavaBlock> retryOptionsMethodImpl = function -> {
            function.line(String.format("this.%1$s = %2$s;", "retryOptions", "retryOptions"));
            function.methodReturn("this");
        };
        ClientBuilderTraitMethod retryOptionsMethod = createTraitMethod("retryOptions", "retryOptions", ClassType.RetryOptions,
                retryOptionsProperty, "{@inheritDoc}", retryOptionsMethodImpl);
        importPackages.add(RetryOptions.class.getName());
        httpClientBuilderTraitMethods.add(retryOptionsMethod);

        // addPolicy
        Consumer<JavaBlock> addPolicyMethodImpl = function -> {
            function.line("pipelinePolicies.add(customPolicy);");
            function.methodReturn("this");
        };
        ClientBuilderTraitMethod addPolicyMethod = createTraitMethod("addPolicy", "customPolicy", ClassType.HttpPipelinePolicy,
                null, "{@inheritDoc}", addPolicyMethodImpl);
        importPackages.add(HttpPipelinePolicy.class.getName());
        httpClientBuilderTraitMethods.add(addPolicyMethod);
        return httpTrait;
    }

    private static ClientBuilderTrait createConfigurationTrait() {
        ClientBuilderTrait configurationTrait = new ClientBuilderTrait();
        configurationTrait.setTraitInterfaceName("ConfigurationTrait");
        List<String> importPackages = new ArrayList<>();
        configurationTrait.setImportPackages(importPackages);
        importPackages.add(ConfigurationTrait.class.getName());

        List<ClientBuilderTraitMethod> configurationClientBuilderTraitMethods = new ArrayList<>();
        configurationTrait.setTraitMethods(configurationClientBuilderTraitMethods);

        String propertyName = "configuration";
        ServiceClientProperty configurationProperty = new ServiceClientProperty("The configuration store that is used" +
                " during construction of the service client.",
                ClassType.Configuration, propertyName, false, null);

        Consumer<JavaBlock> configurationMethodImpl = function -> {
            function.line(String.format("this.%1$s = %2$s;", propertyName, propertyName));
            function.methodReturn("this");
        };
        ClientBuilderTraitMethod configurationMethod = createTraitMethod(propertyName, propertyName, ClassType.Configuration,
                configurationProperty, "{@inheritDoc}", configurationMethodImpl);
        importPackages.add(Configuration.class.getName());

        configurationClientBuilderTraitMethods.add(configurationMethod);
        return configurationTrait;
    }

    public static ClientBuilderTrait getEndpointTrait(ServiceClientProperty property) {
        ClientBuilderTrait endpointTrait = ENDPOINT_TRAIT;
        if (endpointTrait == null) {
            endpointTrait = new ClientBuilderTrait();
            endpointTrait.setTraitInterfaceName(EndpointTrait.class.getSimpleName());

            List<String> importPackages = new ArrayList<>();
            endpointTrait.setImportPackages(importPackages);
            importPackages.add(EndpointTrait.class.getName());

            List<ClientBuilderTraitMethod> endpointClientBuilderTraitMethods = new ArrayList<>();
            endpointTrait.setTraitMethods(endpointClientBuilderTraitMethods);

            String propertyName = "endpoint";
            ServiceClientProperty endpointProperty = new ServiceClientProperty.Builder()
                    .name(propertyName)
                    .type(ClassType.String)
                    .description("The service endpoint")
                    .readOnly(false)
                    .required(property.isRequired())
                    .defaultValueExpression(property.getDefaultValueExpression())
                    .requestParameterName(property.getRequestParameterName())
                    .build();

            Consumer<JavaBlock> endpointMethodImpl = function -> {
                function.line(String.format("this.%1$s = %2$s;", propertyName, propertyName));
                function.methodReturn("this");
            };
            ClientBuilderTraitMethod endpointMethod = createTraitMethod(propertyName, propertyName, ClassType.String,
                    endpointProperty, "{@inheritDoc}", endpointMethodImpl);

            endpointClientBuilderTraitMethods.add(endpointMethod);
            ENDPOINT_TRAIT = endpointTrait;
        }
        return endpointTrait;
    }

    private static ClientBuilderTrait createTokenCredentialTrait() {
        ClientBuilderTrait tokenCredentialTrait = new ClientBuilderTrait();
        tokenCredentialTrait.setTraitInterfaceName(TokenCredentialTrait.class.getSimpleName());
        List<String> importPackages = new ArrayList<>();
        tokenCredentialTrait.setImportPackages(importPackages);
        importPackages.add(TokenCredentialTrait.class.getName());

        List<ClientBuilderTraitMethod> clientBuilderTraitMethods = new ArrayList<>();
        tokenCredentialTrait.setTraitMethods(clientBuilderTraitMethods);

        String propertyName = "tokenCredential";
        ServiceClientProperty property = new ServiceClientProperty("The TokenCredential used for authentication.",
                ClassType.TokenCredential, propertyName, false, null);

        Consumer<JavaBlock> methodImpl = function -> {
            function.line(String.format("this.%1$s = %2$s;", propertyName, propertyName));
            function.methodReturn("this");
        };
        ClientBuilderTraitMethod clientMethod = createTraitMethod("credential", propertyName, ClassType.TokenCredential,
                property, "{@inheritDoc}", methodImpl);
        importPackages.add(TokenCredential.class.getName());

        clientBuilderTraitMethods.add(clientMethod);
        return tokenCredentialTrait;
    }

    private static ClientBuilderTrait createAzureKeyCredentialTrait() {
        ClientBuilderTrait azureKeyCredentialTrait = new ClientBuilderTrait();
        azureKeyCredentialTrait.setTraitInterfaceName(AzureKeyCredentialTrait.class.getSimpleName());
        List<String> importPackages = new ArrayList<>();
        azureKeyCredentialTrait.setImportPackages(importPackages);
        importPackages.add(AzureKeyCredentialTrait.class.getName());

        List<ClientBuilderTraitMethod> clientBuilderTraitMethods = new ArrayList<>();
        azureKeyCredentialTrait.setTraitMethods(clientBuilderTraitMethods);

        String propertyName = "azureKeyCredential";
        ServiceClientProperty property = new ServiceClientProperty("The AzureKeyCredential used for authentication.",
                ClassType.AzureKeyCredential, propertyName, false, null);

        Consumer<JavaBlock> methodImpl = function -> {
            function.line(String.format("this.%1$s = %2$s;", propertyName, propertyName));
            function.methodReturn("this");
        };
        ClientBuilderTraitMethod clientMethod = createTraitMethod("credential", propertyName, ClassType.AzureKeyCredential,
                property, "{@inheritDoc}", methodImpl);
        importPackages.add(AzureKeyCredential.class.getName());

        clientBuilderTraitMethods.add(clientMethod);
        return azureKeyCredentialTrait;
    }

    private static ClientBuilderTraitMethod createTraitMethod(String methodName, String methodParamName, ClassType paramType,
                                                              ServiceClientProperty property,
                                                              String documentation, Consumer<JavaBlock> methodImpl) {
        ClientBuilderTraitMethod pipelineMethod = new ClientBuilderTraitMethod();
        pipelineMethod.setMethodName(methodName);
        pipelineMethod.setMethodParamName(methodParamName);
        pipelineMethod.setMethodParamType(paramType);
        pipelineMethod.setProperty(property);
        pipelineMethod.setDocumentation(documentation);
        pipelineMethod.setMethodImpl(methodImpl);
        return pipelineMethod;
    }
}
