// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredoptional;

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
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A builder for creating a new instance of the AutoRestRequiredOptionalTestService type.
 */
@ServiceClientBuilder(serviceClients = { AutoRestRequiredOptionalTestService.class })
public final class AutoRestRequiredOptionalTestServiceBuilder
    implements HttpTrait<AutoRestRequiredOptionalTestServiceBuilder>,
    ConfigurationTrait<AutoRestRequiredOptionalTestServiceBuilder> {
    @Generated
    private static final String SDK_NAME = "name";

    @Generated
    private static final String SDK_VERSION = "version";

    @Generated
    private static final Map<String, String> PROPERTIES = new HashMap<>();

    @Generated
    private final List<HttpPipelinePolicy> pipelinePolicies;

    /**
     * Create an instance of the AutoRestRequiredOptionalTestServiceBuilder.
     */
    @Generated
    public AutoRestRequiredOptionalTestServiceBuilder() {
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
    public AutoRestRequiredOptionalTestServiceBuilder pipeline(HttpPipeline pipeline) {
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
    public AutoRestRequiredOptionalTestServiceBuilder httpClient(HttpClient httpClient) {
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
    public AutoRestRequiredOptionalTestServiceBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
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
    public AutoRestRequiredOptionalTestServiceBuilder clientOptions(ClientOptions clientOptions) {
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
    public AutoRestRequiredOptionalTestServiceBuilder retryOptions(RetryOptions retryOptions) {
        this.retryOptions = retryOptions;
        return this;
    }

    /**
     * {@inheritDoc}.
     */
    @Generated
    @Override
    public AutoRestRequiredOptionalTestServiceBuilder addPolicy(HttpPipelinePolicy customPolicy) {
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
    public AutoRestRequiredOptionalTestServiceBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * number of items to skip
     */
    @Generated
    private String requiredGlobalPath;

    /**
     * Sets number of items to skip.
     * 
     * @param requiredGlobalPath the requiredGlobalPath value.
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    @Generated
    public AutoRestRequiredOptionalTestServiceBuilder requiredGlobalPath(String requiredGlobalPath) {
        this.requiredGlobalPath = requiredGlobalPath;
        return this;
    }

    /*
     * number of items to skip
     */
    @Generated
    private String requiredGlobalQuery;

    /**
     * Sets number of items to skip.
     * 
     * @param requiredGlobalQuery the requiredGlobalQuery value.
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    @Generated
    public AutoRestRequiredOptionalTestServiceBuilder requiredGlobalQuery(String requiredGlobalQuery) {
        this.requiredGlobalQuery = requiredGlobalQuery;
        return this;
    }

    /*
     * number of items to skip
     */
    @Generated
    private int optionalGlobalQuery;

    /**
     * Sets number of items to skip.
     * 
     * @param optionalGlobalQuery the optionalGlobalQuery value.
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    @Generated
    public AutoRestRequiredOptionalTestServiceBuilder optionalGlobalQuery(int optionalGlobalQuery) {
        this.optionalGlobalQuery = optionalGlobalQuery;
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
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    @Generated
    public AutoRestRequiredOptionalTestServiceBuilder host(String host) {
        this.host = host;
        return this;
    }

    /*
     * The serializer to serialize an object into a string
     */
    @Generated
    private SerializerAdapter serializerAdapter;

    /**
     * Sets The serializer to serialize an object into a string.
     * 
     * @param serializerAdapter the serializerAdapter value.
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    @Generated
    public AutoRestRequiredOptionalTestServiceBuilder serializerAdapter(SerializerAdapter serializerAdapter) {
        this.serializerAdapter = serializerAdapter;
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
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    @Generated
    public AutoRestRequiredOptionalTestServiceBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /**
     * Builds an instance of AutoRestRequiredOptionalTestService with the provided parameters.
     * 
     * @return an instance of AutoRestRequiredOptionalTestService.
     */
    @Generated
    public AutoRestRequiredOptionalTestService buildClient() {
        HttpPipeline localPipeline = (pipeline != null) ? pipeline : createHttpPipeline();
        String localHost = (host != null) ? host : "http://localhost:3000";
        SerializerAdapter localSerializerAdapter
            = (serializerAdapter != null) ? serializerAdapter : JacksonAdapter.createDefaultSerializerAdapter();
        AutoRestRequiredOptionalTestService client
            = new AutoRestRequiredOptionalTestService(localPipeline, localSerializerAdapter, this.requiredGlobalPath,
                this.requiredGlobalQuery, this.optionalGlobalQuery, localHost);
        return client;
    }

    @Generated
    private void validateBuilder() {
        // This method is invoked from 'createHttpPipeline' when preparing the HTTP pipeline for the new client.
        // Developer can customize this method, to validate that the necessary conditions are met for the new client.
        Objects.requireNonNull(requiredGlobalPath, "'requiredGlobalPath' cannot be null.");
        Objects.requireNonNull(requiredGlobalQuery, "'requiredGlobalQuery' cannot be null.");
        Objects.requireNonNull(host, "'host' cannot be null.");
    }

    @Generated
    private HttpPipeline createHttpPipeline() {
        this.validateBuilder();
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
}
