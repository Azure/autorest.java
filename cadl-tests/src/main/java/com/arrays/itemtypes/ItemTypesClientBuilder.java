// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.arrays.itemtypes;

import com.arrays.itemtypes.implementation.ItemTypesClientImpl;
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
import com.azure.core.util.serializer.JacksonAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** A builder for creating a new instance of the ItemTypesClient type. */
@ServiceClientBuilder(
        serviceClients = {
            Int32ValueClient.class,
            Int64ValueClient.class,
            BooleanValueClient.class,
            StringValueClient.class,
            Float32ValueClient.class,
            DatetimeValueClient.class,
            DurationValueClient.class,
            UnknownValueClient.class,
            ModelValueClient.class,
            Int32ValueAsyncClient.class,
            Int64ValueAsyncClient.class,
            BooleanValueAsyncClient.class,
            StringValueAsyncClient.class,
            Float32ValueAsyncClient.class,
            DatetimeValueAsyncClient.class,
            DurationValueAsyncClient.class,
            UnknownValueAsyncClient.class,
            ModelValueAsyncClient.class
        })
public final class ItemTypesClientBuilder
        implements HttpTrait<ItemTypesClientBuilder>, ConfigurationTrait<ItemTypesClientBuilder> {
    @Generated private static final String SDK_NAME = "name";

    @Generated private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES = CoreUtils.getProperties("arrays-itemtypes.properties");

    @Generated private final List<HttpPipelinePolicy> pipelinePolicies;

    /** Create an instance of the ItemTypesClientBuilder. */
    @Generated
    public ItemTypesClientBuilder() {
        this.pipelinePolicies = new ArrayList<>();
    }

    /*
     * The HTTP pipeline to send requests through.
     */
    @Generated private HttpPipeline pipeline;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ItemTypesClientBuilder pipeline(HttpPipeline pipeline) {
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
    public ItemTypesClientBuilder httpClient(HttpClient httpClient) {
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
    public ItemTypesClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public ItemTypesClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public ItemTypesClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ItemTypesClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public ItemTypesClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
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
     * @return the ItemTypesClientBuilder.
     */
    @Generated
    public ItemTypesClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of ItemTypesClientImpl with the provided parameters.
     *
     * @return an instance of ItemTypesClientImpl.
     */
    @Generated
    private ItemTypesClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        ItemTypesClientImpl client =
                new ItemTypesClientImpl(localPipeline, JacksonAdapter.createDefaultSerializerAdapter());
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
     * Builds an instance of Int32ValueAsyncClient class.
     *
     * @return an instance of Int32ValueAsyncClient.
     */
    @Generated
    public Int32ValueAsyncClient buildInt32ValueAsyncClient() {
        return new Int32ValueAsyncClient(buildInnerClient().getInt32Values());
    }

    /**
     * Builds an instance of Int64ValueAsyncClient class.
     *
     * @return an instance of Int64ValueAsyncClient.
     */
    @Generated
    public Int64ValueAsyncClient buildInt64ValueAsyncClient() {
        return new Int64ValueAsyncClient(buildInnerClient().getInt64Values());
    }

    /**
     * Builds an instance of BooleanValueAsyncClient class.
     *
     * @return an instance of BooleanValueAsyncClient.
     */
    @Generated
    public BooleanValueAsyncClient buildBooleanValueAsyncClient() {
        return new BooleanValueAsyncClient(buildInnerClient().getBooleanValues());
    }

    /**
     * Builds an instance of StringValueAsyncClient class.
     *
     * @return an instance of StringValueAsyncClient.
     */
    @Generated
    public StringValueAsyncClient buildStringValueAsyncClient() {
        return new StringValueAsyncClient(buildInnerClient().getStringValues());
    }

    /**
     * Builds an instance of Float32ValueAsyncClient class.
     *
     * @return an instance of Float32ValueAsyncClient.
     */
    @Generated
    public Float32ValueAsyncClient buildFloat32ValueAsyncClient() {
        return new Float32ValueAsyncClient(buildInnerClient().getFloat32Values());
    }

    /**
     * Builds an instance of DatetimeValueAsyncClient class.
     *
     * @return an instance of DatetimeValueAsyncClient.
     */
    @Generated
    public DatetimeValueAsyncClient buildDatetimeValueAsyncClient() {
        return new DatetimeValueAsyncClient(buildInnerClient().getDatetimeValues());
    }

    /**
     * Builds an instance of DurationValueAsyncClient class.
     *
     * @return an instance of DurationValueAsyncClient.
     */
    @Generated
    public DurationValueAsyncClient buildDurationValueAsyncClient() {
        return new DurationValueAsyncClient(buildInnerClient().getDurationValues());
    }

    /**
     * Builds an instance of UnknownValueAsyncClient class.
     *
     * @return an instance of UnknownValueAsyncClient.
     */
    @Generated
    public UnknownValueAsyncClient buildUnknownValueAsyncClient() {
        return new UnknownValueAsyncClient(buildInnerClient().getUnknownValues());
    }

    /**
     * Builds an instance of ModelValueAsyncClient class.
     *
     * @return an instance of ModelValueAsyncClient.
     */
    @Generated
    public ModelValueAsyncClient buildModelValueAsyncClient() {
        return new ModelValueAsyncClient(buildInnerClient().getModelValues());
    }

    /**
     * Builds an instance of Int32ValueClient class.
     *
     * @return an instance of Int32ValueClient.
     */
    @Generated
    public Int32ValueClient buildInt32ValueClient() {
        return new Int32ValueClient(new Int32ValueAsyncClient(buildInnerClient().getInt32Values()));
    }

    /**
     * Builds an instance of Int64ValueClient class.
     *
     * @return an instance of Int64ValueClient.
     */
    @Generated
    public Int64ValueClient buildInt64ValueClient() {
        return new Int64ValueClient(new Int64ValueAsyncClient(buildInnerClient().getInt64Values()));
    }

    /**
     * Builds an instance of BooleanValueClient class.
     *
     * @return an instance of BooleanValueClient.
     */
    @Generated
    public BooleanValueClient buildBooleanValueClient() {
        return new BooleanValueClient(new BooleanValueAsyncClient(buildInnerClient().getBooleanValues()));
    }

    /**
     * Builds an instance of StringValueClient class.
     *
     * @return an instance of StringValueClient.
     */
    @Generated
    public StringValueClient buildStringValueClient() {
        return new StringValueClient(new StringValueAsyncClient(buildInnerClient().getStringValues()));
    }

    /**
     * Builds an instance of Float32ValueClient class.
     *
     * @return an instance of Float32ValueClient.
     */
    @Generated
    public Float32ValueClient buildFloat32ValueClient() {
        return new Float32ValueClient(new Float32ValueAsyncClient(buildInnerClient().getFloat32Values()));
    }

    /**
     * Builds an instance of DatetimeValueClient class.
     *
     * @return an instance of DatetimeValueClient.
     */
    @Generated
    public DatetimeValueClient buildDatetimeValueClient() {
        return new DatetimeValueClient(new DatetimeValueAsyncClient(buildInnerClient().getDatetimeValues()));
    }

    /**
     * Builds an instance of DurationValueClient class.
     *
     * @return an instance of DurationValueClient.
     */
    @Generated
    public DurationValueClient buildDurationValueClient() {
        return new DurationValueClient(new DurationValueAsyncClient(buildInnerClient().getDurationValues()));
    }

    /**
     * Builds an instance of UnknownValueClient class.
     *
     * @return an instance of UnknownValueClient.
     */
    @Generated
    public UnknownValueClient buildUnknownValueClient() {
        return new UnknownValueClient(new UnknownValueAsyncClient(buildInnerClient().getUnknownValues()));
    }

    /**
     * Builds an instance of ModelValueClient class.
     *
     * @return an instance of ModelValueClient.
     */
    @Generated
    public ModelValueClient buildModelValueClient() {
        return new ModelValueClient(new ModelValueAsyncClient(buildInnerClient().getModelValues()));
    }
}
