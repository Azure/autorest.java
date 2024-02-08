// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.specialheaders;

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
import com.cadl.specialheaders.implementation.SpecialHeadersClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A builder for creating a new instance of the SpecialHeadersClient type.
 */
@ServiceClientBuilder(
    serviceClients = {
        RepeatabilityHeadersClient.class,
        EtagHeadersClient.class,
        EtagHeadersOptionalBodyClient.class,
        SkipSpecialHeadersClient.class,
        RepeatabilityHeadersAsyncClient.class,
        EtagHeadersAsyncClient.class,
        EtagHeadersOptionalBodyAsyncClient.class,
        SkipSpecialHeadersAsyncClient.class })
public final class SpecialHeadersClientBuilder implements HttpTrait<SpecialHeadersClientBuilder>,
    ConfigurationTrait<SpecialHeadersClientBuilder>, EndpointTrait<SpecialHeadersClientBuilder> {

    @Generated
    private static final String SDK_NAME = "name";

    @Generated
    private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES = CoreUtils.getProperties("cadl-specialheaders.properties");

    @Generated
    private final List<HttpPipelinePolicy> pipelinePolicies;

    /**
     * Create an instance of the SpecialHeadersClientBuilder.
     */
    @Generated
    public SpecialHeadersClientBuilder() {
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
    public SpecialHeadersClientBuilder pipeline(HttpPipeline pipeline) {
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
    public SpecialHeadersClientBuilder httpClient(HttpClient httpClient) {
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
    public SpecialHeadersClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public SpecialHeadersClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public SpecialHeadersClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public SpecialHeadersClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public SpecialHeadersClientBuilder configuration(Configuration configuration) {
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
    public SpecialHeadersClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /*
     * Service version
     */
    @Generated
    private SpecialHeadersServiceVersion serviceVersion;

    /**
     * Sets Service version.
     *
     * @param serviceVersion the serviceVersion value.
     * @return the SpecialHeadersClientBuilder.
     */
    @Generated
    public SpecialHeadersClientBuilder serviceVersion(SpecialHeadersServiceVersion serviceVersion) {
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
     * @return the SpecialHeadersClientBuilder.
     */
    @Generated
    public SpecialHeadersClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of SpecialHeadersClientImpl with the provided parameters.
     *
     * @return an instance of SpecialHeadersClientImpl.
     */
    @Generated
    private SpecialHeadersClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        SpecialHeadersServiceVersion localServiceVersion
            = (serviceVersion != null) ? serviceVersion : SpecialHeadersServiceVersion.getLatest();
        SpecialHeadersClientImpl client = new SpecialHeadersClientImpl(localPipeline,
            JacksonAdapter.createDefaultSerializerAdapter(), this.endpoint, localServiceVersion);
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
        policies.add(new RequestIdPolicy("client-request-id"));
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
     * Builds an instance of RepeatabilityHeadersAsyncClient class.
     *
     * @return an instance of RepeatabilityHeadersAsyncClient.
     */
    @Generated
    public RepeatabilityHeadersAsyncClient buildRepeatabilityHeadersAsyncClient() {
        return new RepeatabilityHeadersAsyncClient(buildInnerClient().getRepeatabilityHeaders());
    }

    /**
     * Builds an instance of EtagHeadersAsyncClient class.
     *
     * @return an instance of EtagHeadersAsyncClient.
     */
    @Generated
    public EtagHeadersAsyncClient buildEtagHeadersAsyncClient() {
        return new EtagHeadersAsyncClient(buildInnerClient().getEtagHeaders());
    }

    /**
     * Builds an instance of EtagHeadersOptionalBodyAsyncClient class.
     *
     * @return an instance of EtagHeadersOptionalBodyAsyncClient.
     */
    @Generated
    public EtagHeadersOptionalBodyAsyncClient buildEtagHeadersOptionalBodyAsyncClient() {
        return new EtagHeadersOptionalBodyAsyncClient(buildInnerClient().getEtagHeadersOptionalBodies());
    }

    /**
     * Builds an instance of SkipSpecialHeadersAsyncClient class.
     *
     * @return an instance of SkipSpecialHeadersAsyncClient.
     */
    @Generated
    public SkipSpecialHeadersAsyncClient buildSkipSpecialHeadersAsyncClient() {
        return new SkipSpecialHeadersAsyncClient(buildInnerClient().getSkipSpecialHeaders());
    }

    /**
     * Builds an instance of RepeatabilityHeadersClient class.
     *
     * @return an instance of RepeatabilityHeadersClient.
     */
    @Generated
    public RepeatabilityHeadersClient buildRepeatabilityHeadersClient() {
        return new RepeatabilityHeadersClient(buildInnerClient().getRepeatabilityHeaders());
    }

    /**
     * Builds an instance of EtagHeadersClient class.
     *
     * @return an instance of EtagHeadersClient.
     */
    @Generated
    public EtagHeadersClient buildEtagHeadersClient() {
        return new EtagHeadersClient(buildInnerClient().getEtagHeaders());
    }

    /**
     * Builds an instance of EtagHeadersOptionalBodyClient class.
     *
     * @return an instance of EtagHeadersOptionalBodyClient.
     */
    @Generated
    public EtagHeadersOptionalBodyClient buildEtagHeadersOptionalBodyClient() {
        return new EtagHeadersOptionalBodyClient(buildInnerClient().getEtagHeadersOptionalBodies());
    }

    /**
     * Builds an instance of SkipSpecialHeadersClient class.
     *
     * @return an instance of SkipSpecialHeadersClient.
     */
    @Generated
    public SkipSpecialHeadersClient buildSkipSpecialHeadersClient() {
        return new SkipSpecialHeadersClient(buildInnerClient().getSkipSpecialHeaders());
    }

    private static final ClientLogger LOGGER = new ClientLogger(SpecialHeadersClientBuilder.class);
}
