// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.models.property.types;

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
import com.models.property.types.implementation.ModelsPropertyTypesClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/** A builder for creating a new instance of the ModelsPropertyTypesClient type. */
@ServiceClientBuilder(
        serviceClients = {
            BooleanOperationClient.class,
            StringOperationClient.class,
            BytesClient.class,
            IntClient.class,
            FloatOperationClient.class,
            DatetimeOperationClient.class,
            DurationOperationClient.class,
            EnumClient.class,
            ExtensibleEnumClient.class,
            ModelClient.class,
            CollectionsStringClient.class,
            CollectionsIntClient.class,
            CollectionsModelClient.class,
            DictionaryStringClient.class,
            BooleanOperationAsyncClient.class,
            StringOperationAsyncClient.class,
            BytesAsyncClient.class,
            IntAsyncClient.class,
            FloatOperationAsyncClient.class,
            DatetimeOperationAsyncClient.class,
            DurationOperationAsyncClient.class,
            EnumAsyncClient.class,
            ExtensibleEnumAsyncClient.class,
            ModelAsyncClient.class,
            CollectionsStringAsyncClient.class,
            CollectionsIntAsyncClient.class,
            CollectionsModelAsyncClient.class,
            DictionaryStringAsyncClient.class
        })
public final class ModelsPropertyTypesClientBuilder
        implements HttpTrait<ModelsPropertyTypesClientBuilder>, ConfigurationTrait<ModelsPropertyTypesClientBuilder> {
    @Generated private static final String SDK_NAME = "name";

    @Generated private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES = CoreUtils.getProperties("models-property-types.properties");

    @Generated private final List<HttpPipelinePolicy> pipelinePolicies;

    /** Create an instance of the ModelsPropertyTypesClientBuilder. */
    @Generated
    public ModelsPropertyTypesClientBuilder() {
        this.pipelinePolicies = new ArrayList<>();
    }

    /*
     * The HTTP pipeline to send requests through.
     */
    @Generated private HttpPipeline pipeline;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ModelsPropertyTypesClientBuilder pipeline(HttpPipeline pipeline) {
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
    public ModelsPropertyTypesClientBuilder httpClient(HttpClient httpClient) {
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
    public ModelsPropertyTypesClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public ModelsPropertyTypesClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public ModelsPropertyTypesClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ModelsPropertyTypesClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public ModelsPropertyTypesClientBuilder configuration(Configuration configuration) {
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
     * @return the ModelsPropertyTypesClientBuilder.
     */
    @Generated
    public ModelsPropertyTypesClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of ModelsPropertyTypesClientImpl with the provided parameters.
     *
     * @return an instance of ModelsPropertyTypesClientImpl.
     */
    @Generated
    private ModelsPropertyTypesClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        ModelsPropertyTypesClientImpl client =
                new ModelsPropertyTypesClientImpl(localPipeline, JacksonAdapter.createDefaultSerializerAdapter());
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
        policies.addAll(
                this.pipelinePolicies.stream()
                        .filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_CALL)
                        .collect(Collectors.toList()));
        HttpPolicyProviders.addBeforeRetryPolicies(policies);
        policies.add(ClientBuilderUtil.validateAndGetRetryPolicy(retryPolicy, retryOptions, new RetryPolicy()));
        policies.add(new AddDatePolicy());
        policies.add(new CookiePolicy());
        policies.addAll(
                this.pipelinePolicies.stream()
                        .filter(p -> p.getPipelinePosition() == HttpPipelinePosition.PER_RETRY)
                        .collect(Collectors.toList()));
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
     * Builds an instance of BooleanOperationAsyncClient class.
     *
     * @return an instance of BooleanOperationAsyncClient.
     */
    @Generated
    public BooleanOperationAsyncClient buildBooleanOperationAsyncClient() {
        return new BooleanOperationAsyncClient(buildInnerClient().getBooleanOperations());
    }

    /**
     * Builds an instance of StringOperationAsyncClient class.
     *
     * @return an instance of StringOperationAsyncClient.
     */
    @Generated
    public StringOperationAsyncClient buildStringOperationAsyncClient() {
        return new StringOperationAsyncClient(buildInnerClient().getStringOperations());
    }

    /**
     * Builds an instance of BytesAsyncClient class.
     *
     * @return an instance of BytesAsyncClient.
     */
    @Generated
    public BytesAsyncClient buildBytesAsyncClient() {
        return new BytesAsyncClient(buildInnerClient().getBytes());
    }

    /**
     * Builds an instance of IntAsyncClient class.
     *
     * @return an instance of IntAsyncClient.
     */
    @Generated
    public IntAsyncClient buildIntAsyncClient() {
        return new IntAsyncClient(buildInnerClient().getInts());
    }

    /**
     * Builds an instance of FloatOperationAsyncClient class.
     *
     * @return an instance of FloatOperationAsyncClient.
     */
    @Generated
    public FloatOperationAsyncClient buildFloatOperationAsyncClient() {
        return new FloatOperationAsyncClient(buildInnerClient().getFloatOperations());
    }

    /**
     * Builds an instance of DatetimeOperationAsyncClient class.
     *
     * @return an instance of DatetimeOperationAsyncClient.
     */
    @Generated
    public DatetimeOperationAsyncClient buildDatetimeOperationAsyncClient() {
        return new DatetimeOperationAsyncClient(buildInnerClient().getDatetimeOperations());
    }

    /**
     * Builds an instance of DurationOperationAsyncClient class.
     *
     * @return an instance of DurationOperationAsyncClient.
     */
    @Generated
    public DurationOperationAsyncClient buildDurationOperationAsyncClient() {
        return new DurationOperationAsyncClient(buildInnerClient().getDurationOperations());
    }

    /**
     * Builds an instance of EnumAsyncClient class.
     *
     * @return an instance of EnumAsyncClient.
     */
    @Generated
    public EnumAsyncClient buildEnumAsyncClient() {
        return new EnumAsyncClient(buildInnerClient().getEnums());
    }

    /**
     * Builds an instance of ExtensibleEnumAsyncClient class.
     *
     * @return an instance of ExtensibleEnumAsyncClient.
     */
    @Generated
    public ExtensibleEnumAsyncClient buildExtensibleEnumAsyncClient() {
        return new ExtensibleEnumAsyncClient(buildInnerClient().getExtensibleEnums());
    }

    /**
     * Builds an instance of ModelAsyncClient class.
     *
     * @return an instance of ModelAsyncClient.
     */
    @Generated
    public ModelAsyncClient buildModelAsyncClient() {
        return new ModelAsyncClient(buildInnerClient().getModels());
    }

    /**
     * Builds an instance of CollectionsStringAsyncClient class.
     *
     * @return an instance of CollectionsStringAsyncClient.
     */
    @Generated
    public CollectionsStringAsyncClient buildCollectionsStringAsyncClient() {
        return new CollectionsStringAsyncClient(buildInnerClient().getCollectionsStrings());
    }

    /**
     * Builds an instance of CollectionsIntAsyncClient class.
     *
     * @return an instance of CollectionsIntAsyncClient.
     */
    @Generated
    public CollectionsIntAsyncClient buildCollectionsIntAsyncClient() {
        return new CollectionsIntAsyncClient(buildInnerClient().getCollectionsInts());
    }

    /**
     * Builds an instance of CollectionsModelAsyncClient class.
     *
     * @return an instance of CollectionsModelAsyncClient.
     */
    @Generated
    public CollectionsModelAsyncClient buildCollectionsModelAsyncClient() {
        return new CollectionsModelAsyncClient(buildInnerClient().getCollectionsModels());
    }

    /**
     * Builds an instance of DictionaryStringAsyncClient class.
     *
     * @return an instance of DictionaryStringAsyncClient.
     */
    @Generated
    public DictionaryStringAsyncClient buildDictionaryStringAsyncClient() {
        return new DictionaryStringAsyncClient(buildInnerClient().getDictionaryStrings());
    }

    /**
     * Builds an instance of BooleanOperationClient class.
     *
     * @return an instance of BooleanOperationClient.
     */
    @Generated
    public BooleanOperationClient buildBooleanOperationClient() {
        return new BooleanOperationClient(new BooleanOperationAsyncClient(buildInnerClient().getBooleanOperations()));
    }

    /**
     * Builds an instance of StringOperationClient class.
     *
     * @return an instance of StringOperationClient.
     */
    @Generated
    public StringOperationClient buildStringOperationClient() {
        return new StringOperationClient(new StringOperationAsyncClient(buildInnerClient().getStringOperations()));
    }

    /**
     * Builds an instance of BytesClient class.
     *
     * @return an instance of BytesClient.
     */
    @Generated
    public BytesClient buildBytesClient() {
        return new BytesClient(new BytesAsyncClient(buildInnerClient().getBytes()));
    }

    /**
     * Builds an instance of IntClient class.
     *
     * @return an instance of IntClient.
     */
    @Generated
    public IntClient buildIntClient() {
        return new IntClient(new IntAsyncClient(buildInnerClient().getInts()));
    }

    /**
     * Builds an instance of FloatOperationClient class.
     *
     * @return an instance of FloatOperationClient.
     */
    @Generated
    public FloatOperationClient buildFloatOperationClient() {
        return new FloatOperationClient(new FloatOperationAsyncClient(buildInnerClient().getFloatOperations()));
    }

    /**
     * Builds an instance of DatetimeOperationClient class.
     *
     * @return an instance of DatetimeOperationClient.
     */
    @Generated
    public DatetimeOperationClient buildDatetimeOperationClient() {
        return new DatetimeOperationClient(
                new DatetimeOperationAsyncClient(buildInnerClient().getDatetimeOperations()));
    }

    /**
     * Builds an instance of DurationOperationClient class.
     *
     * @return an instance of DurationOperationClient.
     */
    @Generated
    public DurationOperationClient buildDurationOperationClient() {
        return new DurationOperationClient(
                new DurationOperationAsyncClient(buildInnerClient().getDurationOperations()));
    }

    /**
     * Builds an instance of EnumClient class.
     *
     * @return an instance of EnumClient.
     */
    @Generated
    public EnumClient buildEnumClient() {
        return new EnumClient(new EnumAsyncClient(buildInnerClient().getEnums()));
    }

    /**
     * Builds an instance of ExtensibleEnumClient class.
     *
     * @return an instance of ExtensibleEnumClient.
     */
    @Generated
    public ExtensibleEnumClient buildExtensibleEnumClient() {
        return new ExtensibleEnumClient(new ExtensibleEnumAsyncClient(buildInnerClient().getExtensibleEnums()));
    }

    /**
     * Builds an instance of ModelClient class.
     *
     * @return an instance of ModelClient.
     */
    @Generated
    public ModelClient buildModelClient() {
        return new ModelClient(new ModelAsyncClient(buildInnerClient().getModels()));
    }

    /**
     * Builds an instance of CollectionsStringClient class.
     *
     * @return an instance of CollectionsStringClient.
     */
    @Generated
    public CollectionsStringClient buildCollectionsStringClient() {
        return new CollectionsStringClient(
                new CollectionsStringAsyncClient(buildInnerClient().getCollectionsStrings()));
    }

    /**
     * Builds an instance of CollectionsIntClient class.
     *
     * @return an instance of CollectionsIntClient.
     */
    @Generated
    public CollectionsIntClient buildCollectionsIntClient() {
        return new CollectionsIntClient(new CollectionsIntAsyncClient(buildInnerClient().getCollectionsInts()));
    }

    /**
     * Builds an instance of CollectionsModelClient class.
     *
     * @return an instance of CollectionsModelClient.
     */
    @Generated
    public CollectionsModelClient buildCollectionsModelClient() {
        return new CollectionsModelClient(new CollectionsModelAsyncClient(buildInnerClient().getCollectionsModels()));
    }

    /**
     * Builds an instance of DictionaryStringClient class.
     *
     * @return an instance of DictionaryStringClient.
     */
    @Generated
    public DictionaryStringClient buildDictionaryStringClient() {
        return new DictionaryStringClient(new DictionaryStringAsyncClient(buildInnerClient().getDictionaryStrings()));
    }
}
