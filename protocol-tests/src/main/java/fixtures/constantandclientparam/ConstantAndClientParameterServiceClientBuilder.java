// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constantandclientparam;

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
import fixtures.constantandclientparam.implementation.ConstantAndClientParameterServiceClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** A builder for creating a new instance of the ConstantAndClientParameterServiceClient type. */
@ServiceClientBuilder(
        serviceClients = {
            ConstantAndClientParameterServiceClient.class,
            ConstantAndClientParameterServiceAsyncClient.class
        })
public final class ConstantAndClientParameterServiceClientBuilder
        implements HttpTrait<ConstantAndClientParameterServiceClientBuilder>,
                ConfigurationTrait<ConstantAndClientParameterServiceClientBuilder> {
    @Generated private static final String SDK_NAME = "name";

    @Generated private static final String SDK_VERSION = "version";

    @Generated
    private final Map<String, String> properties =
            CoreUtils.getProperties("fixtures-constantandclientparam.properties");

    @Generated private final List<HttpPipelinePolicy> pipelinePolicies;

    /** Create an instance of the ConstantAndClientParameterServiceClientBuilder. */
    @Generated
    public ConstantAndClientParameterServiceClientBuilder() {
        this.pipelinePolicies = new ArrayList<>();
    }

    /*
     * The HTTP pipeline to send requests through.
     */
    @Generated private HttpPipeline pipeline;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ConstantAndClientParameterServiceClientBuilder pipeline(HttpPipeline pipeline) {
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
    public ConstantAndClientParameterServiceClientBuilder httpClient(HttpClient httpClient) {
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
    public ConstantAndClientParameterServiceClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
        this.httpLogOptions = httpLogOptions;
        return this;
    }

    /*
     * The client options such as application ID and custom headers to set on a
     * request.
     */
    @Generated private ClientOptions clientOptions;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ConstantAndClientParameterServiceClientBuilder clientOptions(ClientOptions clientOptions) {
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
    public ConstantAndClientParameterServiceClientBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ConstantAndClientParameterServiceClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
        pipelinePolicies.add(customPolicy);
        return this;
    }

    /*
     * The configuration store that is used during construction of the service
     * client.
     */
    @Generated private Configuration configuration;

    /** {@inheritDoc}. */
    @Generated
    @Override
    public ConstantAndClientParameterServiceClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * Query parameter on the client that is required
     */
    @Generated private int queryRequiredClientParam;

    /**
     * Sets Query parameter on the client that is required.
     *
     * @param queryRequiredClientParam the queryRequiredClientParam value.
     * @return the ConstantAndClientParameterServiceClientBuilder.
     */
    @Generated
    public ConstantAndClientParameterServiceClientBuilder queryRequiredClientParam(int queryRequiredClientParam) {
        this.queryRequiredClientParam = queryRequiredClientParam;
        return this;
    }

    /*
     * Query parameter on the client that is required and have default value
     */
    @Generated private int queryRequiredDefaultValueClientParam = 300;

    /**
     * Sets Query parameter on the client that is required and have default value.
     *
     * @param queryRequiredDefaultValueClientParam the queryRequiredDefaultValueClientParam value.
     * @return the ConstantAndClientParameterServiceClientBuilder.
     */
    @Generated
    public ConstantAndClientParameterServiceClientBuilder queryRequiredDefaultValueClientParam(
            int queryRequiredDefaultValueClientParam) {
        this.queryRequiredDefaultValueClientParam = queryRequiredDefaultValueClientParam;
        return this;
    }

    /*
     * Query parameter on the client that is not required
     */
    @Generated private int queryNonRequiredClientParam;

    /**
     * Sets Query parameter on the client that is not required.
     *
     * @param queryNonRequiredClientParam the queryNonRequiredClientParam value.
     * @return the ConstantAndClientParameterServiceClientBuilder.
     */
    @Generated
    public ConstantAndClientParameterServiceClientBuilder queryNonRequiredClientParam(int queryNonRequiredClientParam) {
        this.queryNonRequiredClientParam = queryNonRequiredClientParam;
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
     * @return the ConstantAndClientParameterServiceClientBuilder.
     */
    @Generated
    public ConstantAndClientParameterServiceClientBuilder host(String host) {
        this.host = host;
        return this;
    }

    /*
     * The retry policy that will attempt to retry failed requests, if
     * applicable.
     */
    @Generated private RetryPolicy retryPolicy;

    /**
     * Sets The retry policy that will attempt to retry failed requests, if applicable.
     *
     * @param retryPolicy the retryPolicy value.
     * @return the ConstantAndClientParameterServiceClientBuilder.
     */
    @Generated
    public ConstantAndClientParameterServiceClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of ConstantAndClientParameterServiceClientImpl with the provided parameters.
     *
     * @return an instance of ConstantAndClientParameterServiceClientImpl.
     */
    @Generated
    private ConstantAndClientParameterServiceClientImpl buildInnerClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        String localHost = (host != null) ? host : "http://localhost:3000";
        ConstantAndClientParameterServiceClientImpl client =
                new ConstantAndClientParameterServiceClientImpl(
                        buildPipeline,
                        JacksonAdapter.createDefaultSerializerAdapter(),
                        queryRequiredClientParam,
                        queryRequiredDefaultValueClientParam,
                        queryNonRequiredClientParam,
                        localHost);
        return client;
    }

    @Generated
    private HttpPipeline createHttpPipeline() {
        Configuration buildConfiguration =
                (configuration == null) ? Configuration.getGlobalConfiguration() : configuration;
        if (httpLogOptions == null) {
            httpLogOptions = new HttpLogOptions();
        }
        if (clientOptions == null) {
            clientOptions = new ClientOptions();
        }
        List<HttpPipelinePolicy> policies = new ArrayList<>();
        String clientName = properties.getOrDefault(SDK_NAME, "UnknownName");
        String clientVersion = properties.getOrDefault(SDK_VERSION, "UnknownVersion");
        String applicationId = CoreUtils.getApplicationId(clientOptions, httpLogOptions);
        policies.add(new UserAgentPolicy(applicationId, clientName, clientVersion, buildConfiguration));
        policies.add(new RequestIdPolicy());
        policies.add(new AddHeadersFromContextPolicy());
        HttpHeaders headers = new HttpHeaders();
        clientOptions.getHeaders().forEach(header -> headers.set(header.getName(), header.getValue()));
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
                        .clientOptions(clientOptions)
                        .build();
        return httpPipeline;
    }

    /**
     * Builds an instance of ConstantAndClientParameterServiceAsyncClient class.
     *
     * @return an instance of ConstantAndClientParameterServiceAsyncClient.
     */
    @Generated
    public ConstantAndClientParameterServiceAsyncClient buildAsyncClient() {
        return new ConstantAndClientParameterServiceAsyncClient(buildInnerClient());
    }

    /**
     * Builds an instance of ConstantAndClientParameterServiceClient class.
     *
     * @return an instance of ConstantAndClientParameterServiceClient.
     */
    @Generated
    public ConstantAndClientParameterServiceClient buildClient() {
        return new ConstantAndClientParameterServiceClient(
                new ConstantAndClientParameterServiceAsyncClient(buildInnerClient()));
    }
}
