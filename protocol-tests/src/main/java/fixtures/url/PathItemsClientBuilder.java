// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.url;

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
import fixtures.url.implementation.AutoRestUrlTestServiceClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A builder for creating a new instance of the PathItemsClient type.
 */
@ServiceClientBuilder(serviceClients = { PathItemsClient.class, PathItemsAsyncClient.class })
public final class PathItemsClientBuilder
    implements HttpTrait<PathItemsClientBuilder>, ConfigurationTrait<PathItemsClientBuilder> {
    @Generated
    private static final String SDK_NAME = "name";

    @Generated
    private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES = CoreUtils.getProperties("fixtures-url.properties");

    @Generated
    private final List<HttpPipelinePolicy> pipelinePolicies;

    /**
     * Create an instance of the PathItemsClientBuilder.
     */
    @Generated
    public PathItemsClientBuilder() {
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
    public PathItemsClientBuilder pipeline(HttpPipeline pipeline) {
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
    public PathItemsClientBuilder httpClient(HttpClient httpClient) {
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
    public PathItemsClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public PathItemsClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public PathItemsClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public PathItemsClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public PathItemsClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * A string value 'globalItemStringPath' that appears in the path
     */
    @Generated
    private String globalStringPath;

    /**
     * Sets A string value 'globalItemStringPath' that appears in the path.
     * 
     * @param globalStringPath the globalStringPath value.
     * @return the PathItemsClientBuilder.
     */
    @Generated
    public PathItemsClientBuilder globalStringPath(String globalStringPath) {
        this.globalStringPath = globalStringPath;
        return this;
    }

    /*
     * should contain value null
     */
    @Generated
    private String globalStringQuery;

    /**
     * Sets should contain value null.
     * 
     * @param globalStringQuery the globalStringQuery value.
     * @return the PathItemsClientBuilder.
     */
    @Generated
    public PathItemsClientBuilder globalStringQuery(String globalStringQuery) {
        this.globalStringQuery = globalStringQuery;
        return this;
    }

    /*
     * server parameter
     */
    @Generated
    private String host;

    /**
     * Sets server parameter.
     * 
     * @param host the host value.
     * @return the PathItemsClientBuilder.
     */
    @Generated
    public PathItemsClientBuilder host(String host) {
        this.host = host;
        return this;
    }

    /*
     * Service version
     */
    @Generated
    private AutoRestUrlTestServiceVersion serviceVersion;

    /**
     * Sets Service version.
     * 
     * @param serviceVersion the serviceVersion value.
     * @return the PathItemsClientBuilder.
     */
    @Generated
    public PathItemsClientBuilder serviceVersion(AutoRestUrlTestServiceVersion serviceVersion) {
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
     * @return the PathItemsClientBuilder.
     */
    @Generated
    public PathItemsClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of AutoRestUrlTestServiceClientImpl with the provided parameters.
     * 
     * @return an instance of AutoRestUrlTestServiceClientImpl.
     */
    @Generated
    private AutoRestUrlTestServiceClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        String localHost = (host != null) ? host : "http://localhost:3000";
        AutoRestUrlTestServiceVersion localServiceVersion
            = (serviceVersion != null) ? serviceVersion : AutoRestUrlTestServiceVersion.getLatest();
        AutoRestUrlTestServiceClientImpl client
            = new AutoRestUrlTestServiceClientImpl(localPipeline, JacksonAdapter.createDefaultSerializerAdapter(),
                this.globalStringPath, this.globalStringQuery, localHost, localServiceVersion);
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
     * Builds an instance of PathItemsAsyncClient class.
     * 
     * @return an instance of PathItemsAsyncClient.
     */
    @Generated
    public PathItemsAsyncClient buildAsyncClient() {
        return new PathItemsAsyncClient(buildInnerClient().getPathItems());
    }

    /**
     * Builds an instance of PathItemsClient class.
     * 
     * @return an instance of PathItemsClient.
     */
    @Generated
    public PathItemsClient buildClient() {
        return new PathItemsClient(buildInnerClient().getPathItems());
    }

    private static final ClientLogger LOGGER = new ClientLogger(PathItemsClientBuilder.class);
}
