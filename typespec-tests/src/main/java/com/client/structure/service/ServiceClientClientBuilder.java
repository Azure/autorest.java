// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.client.structure.service;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.client.traits.ConfigurationTrait;
import com.azure.core.client.traits.EndpointTrait;
import com.azure.core.client.traits.HttpTrait;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeaderName;
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

import com.client.structure.service.implementation.ServiceClientClientImpl;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A builder for creating a new instance of the ServiceClientClient type.
 */
@ServiceClientBuilder(
    serviceClients = {
        ServiceClientClient.class,
        BazFooClient.class,
        QuxClient.class,
        QuxBarClient.class,
        FooClient.class,
        BarClient.class,
        ServiceClientAsyncClient.class,
        BazFooAsyncClient.class,
        QuxAsyncClient.class,
        QuxBarAsyncClient.class,
        FooAsyncClient.class,
        BarAsyncClient.class })
public final class ServiceClientClientBuilder implements HttpTrait<ServiceClientClientBuilder>,
    ConfigurationTrait<ServiceClientClientBuilder>, EndpointTrait<ServiceClientClientBuilder> {
    @Generated
    private static final String SDK_NAME = "name";

    @Generated
    private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES
        = CoreUtils.getProperties("client-structure-service.properties");

    @Generated
    private final List<HttpPipelinePolicy> pipelinePolicies;

    /**
     * Create an instance of the ServiceClientClientBuilder.
     */
    @Generated
    public ServiceClientClientBuilder() {
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
    public ServiceClientClientBuilder pipeline(HttpPipeline pipeline) {
        if (this.pipeline != null && pipeline == null) {
            LOGGER.info("HttpPipeline is being set to 'null' when it was previously configured.");
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
    public ServiceClientClientBuilder httpClient(HttpClient httpClient) {
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
    public ServiceClientClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public ServiceClientClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public ServiceClientClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public ServiceClientClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public ServiceClientClientBuilder configuration(Configuration configuration) {
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
    public ServiceClientClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /*
     * Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     */
    @Generated
    private String client;

    /**
     * Sets Need to be set as 'default', 'multi-client', 'renamed-operation', 'two-operation-group' in client.
     * 
     * @param client the client value.
     * @return the ServiceClientClientBuilder.
     */
    @Generated
    public ServiceClientClientBuilder client(String client) {
        this.client = client;
        return this;
    }

    /*
     * Service version
     */
    @Generated
    private ServiceServiceVersion serviceVersion;

    /**
     * Sets Service version.
     * 
     * @param serviceVersion the serviceVersion value.
     * @return the ServiceClientClientBuilder.
     */
    @Generated
    public ServiceClientClientBuilder serviceVersion(ServiceServiceVersion serviceVersion) {
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
     * @return the ServiceClientClientBuilder.
     */
    @Generated
    public ServiceClientClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of ServiceClientClientImpl with the provided parameters.
     * 
     * @return an instance of ServiceClientClientImpl.
     */
    @Generated
    private ServiceClientClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        ServiceServiceVersion localServiceVersion
            = (serviceVersion != null) ? serviceVersion : ServiceServiceVersion.getLatest();
        ServiceClientClientImpl client = new ServiceClientClientImpl(localPipeline,
            JacksonAdapter.createDefaultSerializerAdapter(), this.endpoint, this.client, localServiceVersion);
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
        HttpHeaders headers = new HttpHeaders();
        localClientOptions.getHeaders()
            .forEach(header -> headers.set(HttpHeaderName.fromString(header.getName()), header.getValue()));
        if (headers.getSize() > 0) {
            policies.add(new AddHeadersPolicy(headers));
        }
        this.pipelinePolicies.stream().filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_CALL)
            .forEach(p -> policies.add(p));
        HttpPolicyProviders.addBeforeRetryPolicies(policies);
        policies.add(ClientBuilderUtil.validateAndGetRetryPolicy(retryPolicy, retryOptions, new RetryPolicy()));
        policies.add(new AddDatePolicy());
        this.pipelinePolicies.stream().filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_RETRY)
            .forEach(p -> policies.add(p));
        HttpPolicyProviders.addAfterRetryPolicies(policies);
        policies.add(new HttpLoggingPolicy(localHttpLogOptions));
        HttpPipeline httpPipeline = new HttpPipelineBuilder().policies(policies.toArray(new HttpPipelinePolicy[0]))
            .httpClient(httpClient).clientOptions(localClientOptions).build();
        return httpPipeline;
    }

    /**
     * Builds an instance of ServiceClientAsyncClient class.
     * 
     * @return an instance of ServiceClientAsyncClient.
     */
    @Generated
    public ServiceClientAsyncClient buildAsyncClient() {
        return new ServiceClientAsyncClient(buildInnerClient());
    }

    /**
     * Builds an instance of BazFooAsyncClient class.
     * 
     * @return an instance of BazFooAsyncClient.
     */
    @Generated
    public BazFooAsyncClient buildBazFooAsyncClient() {
        return new BazFooAsyncClient(buildInnerClient().getBazFoos());
    }

    /**
     * Builds an instance of QuxAsyncClient class.
     * 
     * @return an instance of QuxAsyncClient.
     */
    @Generated
    public QuxAsyncClient buildQuxAsyncClient() {
        return new QuxAsyncClient(buildInnerClient().getQuxes());
    }

    /**
     * Builds an instance of QuxBarAsyncClient class.
     * 
     * @return an instance of QuxBarAsyncClient.
     */
    @Generated
    public QuxBarAsyncClient buildQuxBarAsyncClient() {
        return new QuxBarAsyncClient(buildInnerClient().getQuxBars());
    }

    /**
     * Builds an instance of FooAsyncClient class.
     * 
     * @return an instance of FooAsyncClient.
     */
    @Generated
    public FooAsyncClient buildFooAsyncClient() {
        return new FooAsyncClient(buildInnerClient().getFoos());
    }

    /**
     * Builds an instance of BarAsyncClient class.
     * 
     * @return an instance of BarAsyncClient.
     */
    @Generated
    public BarAsyncClient buildBarAsyncClient() {
        return new BarAsyncClient(buildInnerClient().getBars());
    }

    /**
     * Builds an instance of ServiceClientClient class.
     * 
     * @return an instance of ServiceClientClient.
     */
    @Generated
    public ServiceClientClient buildClient() {
        return new ServiceClientClient(buildInnerClient());
    }

    /**
     * Builds an instance of BazFooClient class.
     * 
     * @return an instance of BazFooClient.
     */
    @Generated
    public BazFooClient buildBazFooClient() {
        return new BazFooClient(buildInnerClient().getBazFoos());
    }

    /**
     * Builds an instance of QuxClient class.
     * 
     * @return an instance of QuxClient.
     */
    @Generated
    public QuxClient buildQuxClient() {
        return new QuxClient(buildInnerClient().getQuxes());
    }

    /**
     * Builds an instance of QuxBarClient class.
     * 
     * @return an instance of QuxBarClient.
     */
    @Generated
    public QuxBarClient buildQuxBarClient() {
        return new QuxBarClient(buildInnerClient().getQuxBars());
    }

    /**
     * Builds an instance of FooClient class.
     * 
     * @return an instance of FooClient.
     */
    @Generated
    public FooClient buildFooClient() {
        return new FooClient(buildInnerClient().getFoos());
    }

    /**
     * Builds an instance of BarClient class.
     * 
     * @return an instance of BarClient.
     */
    @Generated
    public BarClient buildBarClient() {
        return new BarClient(buildInnerClient().getBars());
    }

    private static final ClientLogger LOGGER = new ClientLogger(ServiceClientClientBuilder.class);
}
