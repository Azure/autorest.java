// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.additionalproperties;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.client.traits.ConfigurationTrait;
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
import com.type.property.additionalproperties.implementation.AdditionalPropertiesClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A builder for creating a new instance of the AdditionalPropertiesClient type.
 */
@ServiceClientBuilder(
    serviceClients = {
        ExtendsUnknownClient.class,
        ExtendsUnknownDerivedClient.class,
        ExtendsUnknownDiscriminatedClient.class,
        IsUnknownClient.class,
        IsUnknownDerivedClient.class,
        IsUnknownDiscriminatedClient.class,
        ExtendsStringClient.class,
        IsStringClient.class,
        ExtendsFloatClient.class,
        IsFloatClient.class,
        ExtendsModelClient.class,
        IsModelClient.class,
        ExtendsModelArrayClient.class,
        IsModelArrayClient.class,
        ExtendsUnknownAsyncClient.class,
        ExtendsUnknownDerivedAsyncClient.class,
        ExtendsUnknownDiscriminatedAsyncClient.class,
        IsUnknownAsyncClient.class,
        IsUnknownDerivedAsyncClient.class,
        IsUnknownDiscriminatedAsyncClient.class,
        ExtendsStringAsyncClient.class,
        IsStringAsyncClient.class,
        ExtendsFloatAsyncClient.class,
        IsFloatAsyncClient.class,
        ExtendsModelAsyncClient.class,
        IsModelAsyncClient.class,
        ExtendsModelArrayAsyncClient.class,
        IsModelArrayAsyncClient.class })
public final class AdditionalPropertiesClientBuilder
    implements HttpTrait<AdditionalPropertiesClientBuilder>, ConfigurationTrait<AdditionalPropertiesClientBuilder> {
    @Generated
    private static final String SDK_NAME = "name";

    @Generated
    private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES
        = CoreUtils.getProperties("type-property-additionalproperties.properties");

    @Generated
    private final List<HttpPipelinePolicy> pipelinePolicies;

    /**
     * Create an instance of the AdditionalPropertiesClientBuilder.
     */
    @Generated
    public AdditionalPropertiesClientBuilder() {
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
    public AdditionalPropertiesClientBuilder pipeline(HttpPipeline pipeline) {
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
    public AdditionalPropertiesClientBuilder httpClient(HttpClient httpClient) {
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
    public AdditionalPropertiesClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public AdditionalPropertiesClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public AdditionalPropertiesClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public AdditionalPropertiesClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public AdditionalPropertiesClientBuilder configuration(Configuration configuration) {
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
     * @return the AdditionalPropertiesClientBuilder.
     */
    @Generated
    public AdditionalPropertiesClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of AdditionalPropertiesClientImpl with the provided parameters.
     * 
     * @return an instance of AdditionalPropertiesClientImpl.
     */
    @Generated
    private AdditionalPropertiesClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        AdditionalPropertiesClientImpl client
            = new AdditionalPropertiesClientImpl(localPipeline, JacksonAdapter.createDefaultSerializerAdapter());
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
     * Builds an instance of ExtendsUnknownAsyncClient class.
     * 
     * @return an instance of ExtendsUnknownAsyncClient.
     */
    @Generated
    public ExtendsUnknownAsyncClient buildExtendsUnknownAsyncClient() {
        return new ExtendsUnknownAsyncClient(buildInnerClient().getExtendsUnknowns());
    }

    /**
     * Builds an instance of ExtendsUnknownDerivedAsyncClient class.
     * 
     * @return an instance of ExtendsUnknownDerivedAsyncClient.
     */
    @Generated
    public ExtendsUnknownDerivedAsyncClient buildExtendsUnknownDerivedAsyncClient() {
        return new ExtendsUnknownDerivedAsyncClient(buildInnerClient().getExtendsUnknownDeriveds());
    }

    /**
     * Builds an instance of ExtendsUnknownDiscriminatedAsyncClient class.
     * 
     * @return an instance of ExtendsUnknownDiscriminatedAsyncClient.
     */
    @Generated
    public ExtendsUnknownDiscriminatedAsyncClient buildExtendsUnknownDiscriminatedAsyncClient() {
        return new ExtendsUnknownDiscriminatedAsyncClient(buildInnerClient().getExtendsUnknownDiscriminateds());
    }

    /**
     * Builds an instance of IsUnknownAsyncClient class.
     * 
     * @return an instance of IsUnknownAsyncClient.
     */
    @Generated
    public IsUnknownAsyncClient buildIsUnknownAsyncClient() {
        return new IsUnknownAsyncClient(buildInnerClient().getIsUnknowns());
    }

    /**
     * Builds an instance of IsUnknownDerivedAsyncClient class.
     * 
     * @return an instance of IsUnknownDerivedAsyncClient.
     */
    @Generated
    public IsUnknownDerivedAsyncClient buildIsUnknownDerivedAsyncClient() {
        return new IsUnknownDerivedAsyncClient(buildInnerClient().getIsUnknownDeriveds());
    }

    /**
     * Builds an instance of IsUnknownDiscriminatedAsyncClient class.
     * 
     * @return an instance of IsUnknownDiscriminatedAsyncClient.
     */
    @Generated
    public IsUnknownDiscriminatedAsyncClient buildIsUnknownDiscriminatedAsyncClient() {
        return new IsUnknownDiscriminatedAsyncClient(buildInnerClient().getIsUnknownDiscriminateds());
    }

    /**
     * Builds an instance of ExtendsStringAsyncClient class.
     * 
     * @return an instance of ExtendsStringAsyncClient.
     */
    @Generated
    public ExtendsStringAsyncClient buildExtendsStringAsyncClient() {
        return new ExtendsStringAsyncClient(buildInnerClient().getExtendsStrings());
    }

    /**
     * Builds an instance of IsStringAsyncClient class.
     * 
     * @return an instance of IsStringAsyncClient.
     */
    @Generated
    public IsStringAsyncClient buildIsStringAsyncClient() {
        return new IsStringAsyncClient(buildInnerClient().getIsStrings());
    }

    /**
     * Builds an instance of ExtendsFloatAsyncClient class.
     * 
     * @return an instance of ExtendsFloatAsyncClient.
     */
    @Generated
    public ExtendsFloatAsyncClient buildExtendsFloatAsyncClient() {
        return new ExtendsFloatAsyncClient(buildInnerClient().getExtendsFloats());
    }

    /**
     * Builds an instance of IsFloatAsyncClient class.
     * 
     * @return an instance of IsFloatAsyncClient.
     */
    @Generated
    public IsFloatAsyncClient buildIsFloatAsyncClient() {
        return new IsFloatAsyncClient(buildInnerClient().getIsFloats());
    }

    /**
     * Builds an instance of ExtendsModelAsyncClient class.
     * 
     * @return an instance of ExtendsModelAsyncClient.
     */
    @Generated
    public ExtendsModelAsyncClient buildExtendsModelAsyncClient() {
        return new ExtendsModelAsyncClient(buildInnerClient().getExtendsModels());
    }

    /**
     * Builds an instance of IsModelAsyncClient class.
     * 
     * @return an instance of IsModelAsyncClient.
     */
    @Generated
    public IsModelAsyncClient buildIsModelAsyncClient() {
        return new IsModelAsyncClient(buildInnerClient().getIsModels());
    }

    /**
     * Builds an instance of ExtendsModelArrayAsyncClient class.
     * 
     * @return an instance of ExtendsModelArrayAsyncClient.
     */
    @Generated
    public ExtendsModelArrayAsyncClient buildExtendsModelArrayAsyncClient() {
        return new ExtendsModelArrayAsyncClient(buildInnerClient().getExtendsModelArrays());
    }

    /**
     * Builds an instance of IsModelArrayAsyncClient class.
     * 
     * @return an instance of IsModelArrayAsyncClient.
     */
    @Generated
    public IsModelArrayAsyncClient buildIsModelArrayAsyncClient() {
        return new IsModelArrayAsyncClient(buildInnerClient().getIsModelArrays());
    }

    /**
     * Builds an instance of ExtendsUnknownClient class.
     * 
     * @return an instance of ExtendsUnknownClient.
     */
    @Generated
    public ExtendsUnknownClient buildExtendsUnknownClient() {
        return new ExtendsUnknownClient(buildInnerClient().getExtendsUnknowns());
    }

    /**
     * Builds an instance of ExtendsUnknownDerivedClient class.
     * 
     * @return an instance of ExtendsUnknownDerivedClient.
     */
    @Generated
    public ExtendsUnknownDerivedClient buildExtendsUnknownDerivedClient() {
        return new ExtendsUnknownDerivedClient(buildInnerClient().getExtendsUnknownDeriveds());
    }

    /**
     * Builds an instance of ExtendsUnknownDiscriminatedClient class.
     * 
     * @return an instance of ExtendsUnknownDiscriminatedClient.
     */
    @Generated
    public ExtendsUnknownDiscriminatedClient buildExtendsUnknownDiscriminatedClient() {
        return new ExtendsUnknownDiscriminatedClient(buildInnerClient().getExtendsUnknownDiscriminateds());
    }

    /**
     * Builds an instance of IsUnknownClient class.
     * 
     * @return an instance of IsUnknownClient.
     */
    @Generated
    public IsUnknownClient buildIsUnknownClient() {
        return new IsUnknownClient(buildInnerClient().getIsUnknowns());
    }

    /**
     * Builds an instance of IsUnknownDerivedClient class.
     * 
     * @return an instance of IsUnknownDerivedClient.
     */
    @Generated
    public IsUnknownDerivedClient buildIsUnknownDerivedClient() {
        return new IsUnknownDerivedClient(buildInnerClient().getIsUnknownDeriveds());
    }

    /**
     * Builds an instance of IsUnknownDiscriminatedClient class.
     * 
     * @return an instance of IsUnknownDiscriminatedClient.
     */
    @Generated
    public IsUnknownDiscriminatedClient buildIsUnknownDiscriminatedClient() {
        return new IsUnknownDiscriminatedClient(buildInnerClient().getIsUnknownDiscriminateds());
    }

    /**
     * Builds an instance of ExtendsStringClient class.
     * 
     * @return an instance of ExtendsStringClient.
     */
    @Generated
    public ExtendsStringClient buildExtendsStringClient() {
        return new ExtendsStringClient(buildInnerClient().getExtendsStrings());
    }

    /**
     * Builds an instance of IsStringClient class.
     * 
     * @return an instance of IsStringClient.
     */
    @Generated
    public IsStringClient buildIsStringClient() {
        return new IsStringClient(buildInnerClient().getIsStrings());
    }

    /**
     * Builds an instance of ExtendsFloatClient class.
     * 
     * @return an instance of ExtendsFloatClient.
     */
    @Generated
    public ExtendsFloatClient buildExtendsFloatClient() {
        return new ExtendsFloatClient(buildInnerClient().getExtendsFloats());
    }

    /**
     * Builds an instance of IsFloatClient class.
     * 
     * @return an instance of IsFloatClient.
     */
    @Generated
    public IsFloatClient buildIsFloatClient() {
        return new IsFloatClient(buildInnerClient().getIsFloats());
    }

    /**
     * Builds an instance of ExtendsModelClient class.
     * 
     * @return an instance of ExtendsModelClient.
     */
    @Generated
    public ExtendsModelClient buildExtendsModelClient() {
        return new ExtendsModelClient(buildInnerClient().getExtendsModels());
    }

    /**
     * Builds an instance of IsModelClient class.
     * 
     * @return an instance of IsModelClient.
     */
    @Generated
    public IsModelClient buildIsModelClient() {
        return new IsModelClient(buildInnerClient().getIsModels());
    }

    /**
     * Builds an instance of ExtendsModelArrayClient class.
     * 
     * @return an instance of ExtendsModelArrayClient.
     */
    @Generated
    public ExtendsModelArrayClient buildExtendsModelArrayClient() {
        return new ExtendsModelArrayClient(buildInnerClient().getExtendsModelArrays());
    }

    /**
     * Builds an instance of IsModelArrayClient class.
     * 
     * @return an instance of IsModelArrayClient.
     */
    @Generated
    public IsModelArrayClient buildIsModelArrayClient() {
        return new IsModelArrayClient(buildInnerClient().getIsModelArrays());
    }

    private static final ClientLogger LOGGER = new ClientLogger(AdditionalPropertiesClientBuilder.class);
}
