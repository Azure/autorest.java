// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.versioning.removed;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.client.traits.ConfigurationTrait;
import com.azure.core.client.traits.EndpointTrait;
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
import com.versioning.removed.implementation.RemovedClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A builder for creating a new instance of the RemovedClient type.
 */
@ServiceClientBuilder(serviceClients = { RemovedClient.class, RemovedAsyncClient.class })
public final class RemovedClientBuilder implements HttpTrait<RemovedClientBuilder>,
    ConfigurationTrait<RemovedClientBuilder>, EndpointTrait<RemovedClientBuilder> {
    @Generated
    private static final String SDK_NAME = "name";

    @Generated
    private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES = CoreUtils.getProperties("versioning-removed.properties");

    @Generated
    private final List<HttpPipelinePolicy> pipelinePolicies;

    /**
     * Create an instance of the RemovedClientBuilder.
     */
    @Generated
    public RemovedClientBuilder() {
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
    public RemovedClientBuilder pipeline(HttpPipeline pipeline) {
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
    public RemovedClientBuilder httpClient(HttpClient httpClient) {
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
    public RemovedClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public RemovedClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public RemovedClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public RemovedClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public RemovedClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * The service endpoint
     */
    @Generated
    private String endpoint;

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public RemovedClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /*
     * Need to be set as 'v1' or 'v2' in client.
     */
    @Generated
    private String version;

    /**
     * Sets Need to be set as 'v1' or 'v2' in client.
     * 
     * @param version the version value.
     * @return the RemovedClientBuilder.
     */
    @Generated
    public RemovedClientBuilder version(String version) {
        this.version = version;
        return this;
    }

    /*
     * Service version
     */
    @Generated
    private RemovedServiceVersion serviceVersion;

    /**
     * Sets Service version.
     * 
     * @param serviceVersion the serviceVersion value.
     * @return the RemovedClientBuilder.
     */
    @Generated
    public RemovedClientBuilder serviceVersion(RemovedServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
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
     * @return the RemovedClientBuilder.
     */
    @Generated
    public RemovedClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of RemovedClientImpl with the provided parameters.
     * 
     * @return an instance of RemovedClientImpl.
     */
    @Generated
    private RemovedClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        RemovedServiceVersion localServiceVersion
            = (serviceVersion != null) ? serviceVersion : RemovedServiceVersion.getLatest();
        RemovedClientImpl client = new RemovedClientImpl(localPipeline, JacksonAdapter.createDefaultSerializerAdapter(),
            this.endpoint, this.version, localServiceVersion);
        return client;
    }

    @Generated
    private void validateBuilder() {
        // This method is invoked from 'createHttpPipeline' when preparing the HTTP pipeline for the new client.
        // Developer can customize this method, to validate that the necessary conditions are met for the new client.
        Objects.requireNonNull(endpoint, "'endpoint' cannot be null.");
        Objects.requireNonNull(version, "'version' cannot be null.");
    }

    @Generated
    private HttpPipeline createHttpPipeline() {
        this.validateBuilder();
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
     * Builds an instance of RemovedAsyncClient class.
     * 
     * @return an instance of RemovedAsyncClient.
     */
    @Generated
    public RemovedAsyncClient buildAsyncClient() {
        return new RemovedAsyncClient(buildInnerClient());
    }

    /**
     * Builds an instance of RemovedClient class.
     * 
     * @return an instance of RemovedClient.
     */
    @Generated
    public RemovedClient buildClient() {
        return new RemovedClient(buildInnerClient());
    }

    private static final ClientLogger LOGGER = new ClientLogger(RemovedClientBuilder.class);
}
