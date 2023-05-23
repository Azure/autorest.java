// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.resiliency.servicedriven;

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
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpLoggingPolicy;
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
import com.resiliency.servicedriven.implementation.ResiliencyServiceDrivenClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** A builder for creating a new instance of the ResiliencyServiceDrivenClient type. */
@ServiceClientBuilder(serviceClients = {ResiliencyServiceDrivenClient.class, ResiliencyServiceDrivenAsyncClient.class})
public final class ResiliencyServiceDrivenClientBuilder
        implements HttpTrait<ResiliencyServiceDrivenClientBuilder>,
                ConfigurationTrait<ResiliencyServiceDrivenClientBuilder> {
    @Generated private static final String SDK_NAME = "name";

    @Generated private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES =
            CoreUtils.getProperties("resiliency-servicedriven.properties");

    @Generated private final List<HttpPipelinePolicy> pipelinePolicies;

    /** Create an instance of the ResiliencyServiceDrivenClientBuilder. */
    @Generated
    public ResiliencyServiceDrivenClientBuilder() {
        this.pipelinePolicies = new ArrayList<>();
    }

    /*
     * The HTTP pipeline to send requests through.
     */
    @Generated private HttpPipeline pipeline;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ResiliencyServiceDrivenClientBuilder pipeline(HttpPipeline pipeline) {
        if (this.pipeline != null && pipeline == null) {
            LOGGER.info("HttpPipeline is being set to 'null' when it was previously configured.");
        }
        this.pipeline = pipeline;
        return this;
    }

    /*
     * The HTTP client used to send the request.
     */
    @Generated private HttpClient httpClient;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ResiliencyServiceDrivenClientBuilder httpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /*
     * The logging configuration for HTTP requests and responses.
     */
    @Generated private HttpLogOptions httpLogOptions;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ResiliencyServiceDrivenClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
        this.httpLogOptions = httpLogOptions;
        return this;
    }

    /*
     * The client options such as application ID and custom headers to set on a request.
     */
    @Generated private ClientOptions clientOptions;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ResiliencyServiceDrivenClientBuilder clientOptions(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
        return this;
    }

    /*
     * The retry options to configure retry policy for failed requests.
     */
    @Generated private RetryOptions retryOptions;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ResiliencyServiceDrivenClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ResiliencyServiceDrivenClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
        Objects.requireNonNull(customPolicy, "'customPolicy' cannot be null.");
        pipelinePolicies.add(customPolicy);
        return this;
    }

    /*
     * The configuration store that is used during construction of the service client.
     */
    @Generated private Configuration configuration;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ResiliencyServiceDrivenClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * Pass in either 'v1' or 'v2'. This represents a version of the service deployment in history. 'v1' is for the
     * deployment when the service had only one api version. 'v2' is for the deployment when the service had
     * api-versions 'v1' and 'v2'.
     */
    @Generated private String serviceDeploymentVersion;

    /**
     * Sets Pass in either 'v1' or 'v2'. This represents a version of the service deployment in history. 'v1' is for the
     * deployment when the service had only one api version. 'v2' is for the deployment when the service had
     * api-versions 'v1' and 'v2'.
     *
     * @param serviceDeploymentVersion the serviceDeploymentVersion value.
     * @return the ResiliencyServiceDrivenClientBuilder.
     */
    @Generated
    public ResiliencyServiceDrivenClientBuilder serviceDeploymentVersion(String serviceDeploymentVersion) {
        this.serviceDeploymentVersion = serviceDeploymentVersion;
        return this;
    }

    /*
     * Service version
     */
    @Generated private ServiceDrivenServiceVersion serviceVersion;

    /**
     * Sets Service version.
     *
     * @param serviceVersion the serviceVersion value.
     * @return the ResiliencyServiceDrivenClientBuilder.
     */
    @Generated
    public ResiliencyServiceDrivenClientBuilder serviceVersion(ServiceDrivenServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
        return this;
    }

    /*
     * The retry policy that will attempt to retry failed requests, if applicable.
     */
    @Generated private RetryPolicy retryPolicy;

    /**
     * Sets The retry policy that will attempt to retry failed requests, if applicable.
     *
     * @param retryPolicy the retryPolicy value.
     * @return the ResiliencyServiceDrivenClientBuilder.
     */
    @Generated
    public ResiliencyServiceDrivenClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of ResiliencyServiceDrivenClientImpl with the provided parameters.
     *
     * @return an instance of ResiliencyServiceDrivenClientImpl.
     */
    @Generated
    private ResiliencyServiceDrivenClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        ServiceDrivenServiceVersion localServiceVersion =
                (serviceVersion != null) ? serviceVersion : ServiceDrivenServiceVersion.getLatest();
        ResiliencyServiceDrivenClientImpl client =
                new ResiliencyServiceDrivenClientImpl(
                        localPipeline,
                        JacksonAdapter.createDefaultSerializerAdapter(),
                        serviceDeploymentVersion,
                        localServiceVersion);
        return client;
    }

    @Generated
    private HttpPipeline createHttpPipeline() {
        Configuration buildConfiguration =
                (configuration == null) ? Configuration.getGlobalConfiguration() : configuration;
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
        localClientOptions.getHeaders().forEach(header -> headers.set(header.getName(), header.getValue()));
        if (headers.getSize() > 0) {
            policies.add(new AddHeadersPolicy(headers));
        }
        this.pipelinePolicies.stream()
                .filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_CALL)
                .forEach(p -> policies.add(p));
        HttpPolicyProviders.addBeforeRetryPolicies(policies);
        policies.add(ClientBuilderUtil.validateAndGetRetryPolicy(retryPolicy, retryOptions, new RetryPolicy()));
        policies.add(new AddDatePolicy());
        policies.add(new CookiePolicy());
        this.pipelinePolicies.stream()
                .filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_RETRY)
                .forEach(p -> policies.add(p));
        HttpPolicyProviders.addAfterRetryPolicies(policies);
        policies.add(new HttpLoggingPolicy(httpLogOptions));
        HttpPipeline httpPipeline =
                new HttpPipelineBuilder()
                        .policies(policies.toArray(new HttpPipelinePolicy[0]))
                        .httpClient(httpClient)
                        .clientOptions(localClientOptions)
                        .build();
        return httpPipeline;
    }

    /**
     * Builds an instance of ResiliencyServiceDrivenAsyncClient class.
     *
     * @return an instance of ResiliencyServiceDrivenAsyncClient.
     */
    @Generated
    public ResiliencyServiceDrivenAsyncClient buildAsyncClient() {
        return new ResiliencyServiceDrivenAsyncClient(buildInnerClient());
    }

    /**
     * Builds an instance of ResiliencyServiceDrivenClient class.
     *
     * @return an instance of ResiliencyServiceDrivenClient.
     */
    @Generated
    public ResiliencyServiceDrivenClient buildClient() {
        return new ResiliencyServiceDrivenClient(buildInnerClient());
    }

    private static final ClientLogger LOGGER = new ClientLogger(ResiliencyServiceDrivenClientBuilder.class);
}
