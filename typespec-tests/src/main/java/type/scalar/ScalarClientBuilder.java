// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package type.scalar;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import type.scalar.implementation.ScalarClientImpl;

/**
 * A builder for creating a new instance of the ScalarClient type.
 */
@ServiceClientBuilder(
    serviceClients = {
        StringOperationClient.class,
        BooleanOperationClient.class,
        UnknownClient.class,
        DecimalTypeClient.class,
        Decimal128TypeClient.class,
        DecimalVerifyClient.class,
        Decimal128VerifyClient.class,
        StringOperationAsyncClient.class,
        BooleanOperationAsyncClient.class,
        UnknownAsyncClient.class,
        DecimalTypeAsyncClient.class,
        Decimal128TypeAsyncClient.class,
        DecimalVerifyAsyncClient.class,
        Decimal128VerifyAsyncClient.class })
public final class ScalarClientBuilder implements HttpTrait<ScalarClientBuilder>,
    ConfigurationTrait<ScalarClientBuilder>, EndpointTrait<ScalarClientBuilder> {
    @Generated
    private static final String SDK_NAME = "name";

    @Generated
    private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES = CoreUtils.getProperties("type-scalar.properties");

    @Generated
    private final List<HttpPipelinePolicy> pipelinePolicies;

    /**
     * Create an instance of the ScalarClientBuilder.
     */
    @Generated
    public ScalarClientBuilder() {
        this.pipelinePolicies = new ArrayList<>();
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
    public ScalarClientBuilder httpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
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
    public ScalarClientBuilder pipeline(HttpPipeline pipeline) {
        if (this.pipeline != null && pipeline == null) {
            LOGGER.atInfo().log("HttpPipeline is being set to 'null' when it was previously configured.");
        }
        this.pipeline = pipeline;
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
    public ScalarClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public ScalarClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public ScalarClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public ScalarClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public ScalarClientBuilder configuration(Configuration configuration) {
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
    public ScalarClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
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
     * @return the ScalarClientBuilder.
     */
    @Generated
    public ScalarClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of ScalarClientImpl with the provided parameters.
     * 
     * @return an instance of ScalarClientImpl.
     */
    @Generated
    private ScalarClientImpl buildInnerClient() {
        this.validateClient();
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        String localEndpoint = (endpoint != null) ? endpoint : "http://localhost:3000";
        ScalarClientImpl client
            = new ScalarClientImpl(localPipeline, JacksonAdapter.createDefaultSerializerAdapter(), localEndpoint);
        return client;
    }

    @Generated
    private void validateClient() {
        // This method is invoked from 'buildInnerClient'/'buildClient' method.
        // Developer can customize this method, to validate that the necessary conditions are met for the new client.
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
     * Builds an instance of StringOperationAsyncClient class.
     * 
     * @return an instance of StringOperationAsyncClient.
     */
    @Generated
    public StringOperationAsyncClient buildStringOperationAsyncClient() {
        return new StringOperationAsyncClient(buildInnerClient().getStringOperations());
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
     * Builds an instance of UnknownAsyncClient class.
     * 
     * @return an instance of UnknownAsyncClient.
     */
    @Generated
    public UnknownAsyncClient buildUnknownAsyncClient() {
        return new UnknownAsyncClient(buildInnerClient().getUnknowns());
    }

    /**
     * Builds an instance of DecimalTypeAsyncClient class.
     * 
     * @return an instance of DecimalTypeAsyncClient.
     */
    @Generated
    public DecimalTypeAsyncClient buildDecimalTypeAsyncClient() {
        return new DecimalTypeAsyncClient(buildInnerClient().getDecimalTypes());
    }

    /**
     * Builds an instance of Decimal128TypeAsyncClient class.
     * 
     * @return an instance of Decimal128TypeAsyncClient.
     */
    @Generated
    public Decimal128TypeAsyncClient buildDecimal128TypeAsyncClient() {
        return new Decimal128TypeAsyncClient(buildInnerClient().getDecimal128Types());
    }

    /**
     * Builds an instance of DecimalVerifyAsyncClient class.
     * 
     * @return an instance of DecimalVerifyAsyncClient.
     */
    @Generated
    public DecimalVerifyAsyncClient buildDecimalVerifyAsyncClient() {
        return new DecimalVerifyAsyncClient(buildInnerClient().getDecimalVerifies());
    }

    /**
     * Builds an instance of Decimal128VerifyAsyncClient class.
     * 
     * @return an instance of Decimal128VerifyAsyncClient.
     */
    @Generated
    public Decimal128VerifyAsyncClient buildDecimal128VerifyAsyncClient() {
        return new Decimal128VerifyAsyncClient(buildInnerClient().getDecimal128Verifies());
    }

    /**
     * Builds an instance of StringOperationClient class.
     * 
     * @return an instance of StringOperationClient.
     */
    @Generated
    public StringOperationClient buildStringOperationClient() {
        return new StringOperationClient(buildInnerClient().getStringOperations());
    }

    /**
     * Builds an instance of BooleanOperationClient class.
     * 
     * @return an instance of BooleanOperationClient.
     */
    @Generated
    public BooleanOperationClient buildBooleanOperationClient() {
        return new BooleanOperationClient(buildInnerClient().getBooleanOperations());
    }

    /**
     * Builds an instance of UnknownClient class.
     * 
     * @return an instance of UnknownClient.
     */
    @Generated
    public UnknownClient buildUnknownClient() {
        return new UnknownClient(buildInnerClient().getUnknowns());
    }

    /**
     * Builds an instance of DecimalTypeClient class.
     * 
     * @return an instance of DecimalTypeClient.
     */
    @Generated
    public DecimalTypeClient buildDecimalTypeClient() {
        return new DecimalTypeClient(buildInnerClient().getDecimalTypes());
    }

    /**
     * Builds an instance of Decimal128TypeClient class.
     * 
     * @return an instance of Decimal128TypeClient.
     */
    @Generated
    public Decimal128TypeClient buildDecimal128TypeClient() {
        return new Decimal128TypeClient(buildInnerClient().getDecimal128Types());
    }

    /**
     * Builds an instance of DecimalVerifyClient class.
     * 
     * @return an instance of DecimalVerifyClient.
     */
    @Generated
    public DecimalVerifyClient buildDecimalVerifyClient() {
        return new DecimalVerifyClient(buildInnerClient().getDecimalVerifies());
    }

    /**
     * Builds an instance of Decimal128VerifyClient class.
     * 
     * @return an instance of Decimal128VerifyClient.
     */
    @Generated
    public Decimal128VerifyClient buildDecimal128VerifyClient() {
        return new Decimal128VerifyClient(buildInnerClient().getDecimal128Verifies());
    }

    private static final ClientLogger LOGGER = new ClientLogger(ScalarClientBuilder.class);
}
