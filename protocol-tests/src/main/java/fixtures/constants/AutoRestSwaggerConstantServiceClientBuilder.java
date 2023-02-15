// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants;

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
import com.azure.core.util.TracingOptions;
import com.azure.core.util.builder.ClientBuilderUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.tracing.Tracer;
import com.azure.core.util.tracing.TracerProvider;
import fixtures.constants.implementation.AutoRestSwaggerConstantServiceClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** A builder for creating a new instance of the AutoRestSwaggerConstantServiceClient type. */
@ServiceClientBuilder(
        serviceClients = {AutoRestSwaggerConstantServiceClient.class, AutoRestSwaggerConstantServiceAsyncClient.class})
public final class AutoRestSwaggerConstantServiceClientBuilder
        implements HttpTrait<AutoRestSwaggerConstantServiceClientBuilder>,
                ConfigurationTrait<AutoRestSwaggerConstantServiceClientBuilder> {
    @Generated private static final String SDK_NAME = "name";

    @Generated private static final String SDK_VERSION = "version";

    @Generated private static final String RESOURCE_PROVIDER_NAMESPACE = null;

    @Generated
    private static final Map<String, String> PROPERTIES = CoreUtils.getProperties("fixtures-constants.properties");

    @Generated private final List<HttpPipelinePolicy> pipelinePolicies;

    /** Create an instance of the AutoRestSwaggerConstantServiceClientBuilder. */
    @Generated
    public AutoRestSwaggerConstantServiceClientBuilder() {
        this.pipelinePolicies = new ArrayList<>();
    }

    /*
     * The HTTP pipeline to send requests through.
     */
    @Generated private HttpPipeline pipeline;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public AutoRestSwaggerConstantServiceClientBuilder pipeline(HttpPipeline pipeline) {
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
    public AutoRestSwaggerConstantServiceClientBuilder httpClient(HttpClient httpClient) {
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
    public AutoRestSwaggerConstantServiceClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public AutoRestSwaggerConstantServiceClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public AutoRestSwaggerConstantServiceClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /** {@inheritDoc}. */
    @Generated
    @Override
    public AutoRestSwaggerConstantServiceClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public AutoRestSwaggerConstantServiceClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * Constant header property on the client that is a required parameter for operation 'constants_putClientConstants'
     */
    @Generated private boolean headerConstant = true;

    /**
     * Sets Constant header property on the client that is a required parameter for operation
     * 'constants_putClientConstants'.
     *
     * @param headerConstant the headerConstant value.
     * @return the AutoRestSwaggerConstantServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerConstantServiceClientBuilder headerConstant(boolean headerConstant) {
        this.headerConstant = headerConstant;
        return this;
    }

    /*
     * Constant query property on the client that is a required parameter for operation 'constants_putClientConstants'
     */
    @Generated private int queryConstant = 100;

    /**
     * Sets Constant query property on the client that is a required parameter for operation
     * 'constants_putClientConstants'.
     *
     * @param queryConstant the queryConstant value.
     * @return the AutoRestSwaggerConstantServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerConstantServiceClientBuilder queryConstant(int queryConstant) {
        this.queryConstant = queryConstant;
        return this;
    }

    /*
     * Constant path property on the client that is a required parameter for operation 'constants_putClientConstants'
     */
    @Generated private String pathConstant;

    /**
     * Sets Constant path property on the client that is a required parameter for operation
     * 'constants_putClientConstants'.
     *
     * @param pathConstant the pathConstant value.
     * @return the AutoRestSwaggerConstantServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerConstantServiceClientBuilder pathConstant(String pathConstant) {
        this.pathConstant = pathConstant;
        return this;
    }

    /*
     * server parameter
     */
    @Generated private String host;

    /**
     * Sets server parameter.
     *
     * @param host the host value.
     * @return the AutoRestSwaggerConstantServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerConstantServiceClientBuilder host(String host) {
        this.host = host;
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
     * @return the AutoRestSwaggerConstantServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerConstantServiceClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of AutoRestSwaggerConstantServiceClientImpl with the provided parameters.
     *
     * @return an instance of AutoRestSwaggerConstantServiceClientImpl.
     */
    @Generated
    private AutoRestSwaggerConstantServiceClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        String localPathConstant = (pathConstant != null) ? pathConstant : "path";
        String localHost = (host != null) ? host : "http://localhost:3000";
        AutoRestSwaggerConstantServiceClientImpl client =
                new AutoRestSwaggerConstantServiceClientImpl(
                        localPipeline,
                        JacksonAdapter.createDefaultSerializerAdapter(),
                        headerConstant,
                        queryConstant,
                        localPathConstant,
                        localHost);
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
        TracingOptions tracingOptions = null;
        if (localClientOptions != null) {
            tracingOptions = localClientOptions.getTracingOptions();
        }
        Tracer tracer =
                TracerProvider.getDefaultProvider()
                        .createTracer(clientName, clientVersion, RESOURCE_PROVIDER_NAMESPACE, tracingOptions);
        HttpPolicyProviders.addAfterRetryPolicies(policies);
        policies.add(new HttpLoggingPolicy(httpLogOptions));
        HttpPipeline httpPipeline =
                new HttpPipelineBuilder()
                        .policies(policies.toArray(new HttpPipelinePolicy[0]))
                        .httpClient(httpClient)
                        .tracer(tracer)
                        .clientOptions(localClientOptions)
                        .build();
        return httpPipeline;
    }

    /**
     * Builds an instance of AutoRestSwaggerConstantServiceAsyncClient class.
     *
     * @return an instance of AutoRestSwaggerConstantServiceAsyncClient.
     */
    @Generated
    public AutoRestSwaggerConstantServiceAsyncClient buildAsyncClient() {
        return new AutoRestSwaggerConstantServiceAsyncClient(buildInnerClient().getContants());
    }

    /**
     * Builds an instance of AutoRestSwaggerConstantServiceClient class.
     *
     * @return an instance of AutoRestSwaggerConstantServiceClient.
     */
    @Generated
    public AutoRestSwaggerConstantServiceClient buildClient() {
        return new AutoRestSwaggerConstantServiceClient(
                new AutoRestSwaggerConstantServiceAsyncClient(buildInnerClient().getContants()));
    }
}
