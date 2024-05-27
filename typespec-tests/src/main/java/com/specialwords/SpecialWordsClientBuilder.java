// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.specialwords;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.client.traits.ConfigurationTrait;
import com.azure.core.client.traits.HttpTrait;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.HttpPipelinePosition;
import com.azure.core.http.policy.AddDatePolicy;
import com.azure.core.http.policy.AddHeadersFromContextPolicy;
import com.azure.core.http.policy.AddHeadersPolicy;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.policy.HttpPolicyProviders;
import com.azure.core.http.policy.RequestIdPolicy;
import com.azure.core.http.policy.RetryOptions;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.builder.ClientBuilderUtil;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.serializer.JacksonAdapter;
import com.specialwords.implementation.SpecialWordsClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A builder for creating a new instance of the SpecialWordsClient type.
 */
@ServiceClientBuilder(
    serviceClients = {
        ModelsClient.class,
        ModelPropertiesClient.class,
        OperationsClient.class,
        ParametersClient.class,
        ModelsAsyncClient.class,
        ModelPropertiesAsyncClient.class,
        OperationsAsyncClient.class,
        ParametersAsyncClient.class })
public final class SpecialWordsClientBuilder
    implements HttpTrait<SpecialWordsClientBuilder>, ConfigurationTrait<SpecialWordsClientBuilder> {
    @Generated
    private static final String SDK_NAME = "name";

    @Generated
    private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES = CoreUtils.getProperties("specialwords.properties");

    @Generated
    private final List<HttpPipelinePolicy> pipelinePolicies;

    /**
     * Create an instance of the SpecialWordsClientBuilder.
     */
    @Generated
    public SpecialWordsClientBuilder() {
        this.pipelinePolicies = new ArrayList<>();
    }

    /*
     * The HTTP pipeline to send requests through.
     */
    @Generated
    private HttpPipeline pipeline;

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public SpecialWordsClientBuilder pipeline(HttpPipeline pipeline) {
        if (this.pipeline != null && pipeline == null) {
            LOGGER.atInfo().log("HttpPipeline is being set to 'null' when it was previously configured.");
        }
        this.pipeline = pipeline;
        return this;
    }

    /*
     * The HTTP client used to send the request.
     */
    @Generated
    private HttpClient httpClient;

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public SpecialWordsClientBuilder httpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /*
     * The logging configuration for HTTP requests and responses.
     */
    @Generated
    private HttpLogOptions httpLogOptions;

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public SpecialWordsClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
        this.httpLogOptions = httpLogOptions;
        return this;
    }

    /*
     * The client options such as application ID and custom headers to set on a request.
     */
    @Generated
    private ClientOptions clientOptions;

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public SpecialWordsClientBuilder clientOptions(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
        return this;
    }

    /*
     * The retry options to configure retry policy for failed requests.
     */
    @Generated
    private RetryOptions retryOptions;

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public SpecialWordsClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public SpecialWordsClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
        Objects.requireNonNull(customPolicy, "'customPolicy' cannot be null.");
        pipelinePolicies.add(customPolicy);
        return this;
    }

    /*
     * The configuration store that is used during construction of the service client.
     */
    @Generated
    private Configuration configuration;

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public SpecialWordsClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * The retry policy that will attempt to retry failed requests, if applicable.
     */
    @Generated
    private RetryPolicy retryPolicy;

    /**
     * Sets The retry policy that will attempt to retry failed requests, if applicable.
     * 
     * @param retryPolicy the retryPolicy value.
     * @return the SpecialWordsClientBuilder.
     */
    @Generated
    public SpecialWordsClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of SpecialWordsClientImpl with the provided parameters.
     * 
     * @return an instance of SpecialWordsClientImpl.
     */
    @Generated
    private SpecialWordsClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        SpecialWordsClientImpl client
            = new SpecialWordsClientImpl(localPipeline, JacksonAdapter.createDefaultSerializerAdapter());
        return client;
    }

    @Generated
    private HttpPipeline createHttpPipeline() {
        Configuration buildConfiguration
            = (configuration == null) ? Configuration.getGlobalConfiguration() : configuration;
        HttpLogOptions localHttpLogOptions = this.httpLogOptions == null ? new HttpLogOptions() : this.httpLogOptions;
        ClientOptions localClientOptions = this.clientOptions == null ? new ClientOptions() : this.clientOptions;
        List<HttpPipelinePolicy> policies = new ArrayList<>();
        String clientName = PROPERTIES.getOrDefault(SDK_NAME, "UnknownName");
        String clientVersion = PROPERTIES.getOrDefault(SDK_VERSION, "UnknownVersion");
        String applicationId = CoreUtils.getApplicationId(localClientOptions, localHttpLogOptions);
        policies.add(new UserAgentPolicy(applicationId, clientName, clientVersion, buildConfiguration));
        policies.add(new RequestIdPolicy());
        policies.add(new AddHeadersFromContextPolicy());
        HttpHeaders headers = CoreUtils.createHttpHeadersFromClientOptions(localClientOptions);
        if (headers != null) {
            policies.add(new AddHeadersPolicy(headers));
        }
        this.pipelinePolicies.stream()
            .filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_CALL)
            .forEach(p -> policies.add(p));
        HttpPolicyProviders.addBeforeRetryPolicies(policies);
        policies.add(ClientBuilderUtil.validateAndGetRetryPolicy(retryPolicy, retryOptions, new RetryPolicy()));
        policies.add(new AddDatePolicy());
        this.pipelinePolicies.stream()
            .filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_RETRY)
            .forEach(p -> policies.add(p));
        HttpPolicyProviders.addAfterRetryPolicies(policies);
        policies.add(new HttpLoggingPolicy(localHttpLogOptions));
        HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(policies.toArray(new HttpPipelinePolicy[0]))
            .httpClient(httpClient)
            .clientOptions(localClientOptions)
            .build();
        return httpPipeline;
    }

    /**
     * Builds an instance of ModelsAsyncClient class.
     * 
     * @return an instance of ModelsAsyncClient.
     */
    @Generated
    public ModelsAsyncClient buildModelsAsyncClient() {
        return new ModelsAsyncClient(buildInnerClient().getModels());
    }

    /**
     * Builds an instance of ModelPropertiesAsyncClient class.
     * 
     * @return an instance of ModelPropertiesAsyncClient.
     */
    @Generated
    public ModelPropertiesAsyncClient buildModelPropertiesAsyncClient() {
        return new ModelPropertiesAsyncClient(buildInnerClient().getModelProperties());
    }

    /**
     * Builds an instance of OperationsAsyncClient class.
     * 
     * @return an instance of OperationsAsyncClient.
     */
    @Generated
    public OperationsAsyncClient buildOperationsAsyncClient() {
        return new OperationsAsyncClient(buildInnerClient().getOperations());
    }

    /**
     * Builds an instance of ParametersAsyncClient class.
     * 
     * @return an instance of ParametersAsyncClient.
     */
    @Generated
    public ParametersAsyncClient buildParametersAsyncClient() {
        return new ParametersAsyncClient(buildInnerClient().getParameters());
    }

    /**
     * Builds an instance of ModelsClient class.
     * 
     * @return an instance of ModelsClient.
     */
    @Generated
    public ModelsClient buildModelsClient() {
        return new ModelsClient(buildInnerClient().getModels());
    }

    /**
     * Builds an instance of ModelPropertiesClient class.
     * 
     * @return an instance of ModelPropertiesClient.
     */
    @Generated
    public ModelPropertiesClient buildModelPropertiesClient() {
        return new ModelPropertiesClient(buildInnerClient().getModelProperties());
    }

    /**
     * Builds an instance of OperationsClient class.
     * 
     * @return an instance of OperationsClient.
     */
    @Generated
    public OperationsClient buildOperationsClient() {
        return new OperationsClient(buildInnerClient().getOperations());
    }

    /**
     * Builds an instance of ParametersClient class.
     * 
     * @return an instance of ParametersClient.
     */
    @Generated
    public ParametersClient buildParametersClient() {
        return new ParametersClient(buildInnerClient().getParameters());
    }

    private static final ClientLogger LOGGER = new ClientLogger(SpecialWordsClientBuilder.class);
}
