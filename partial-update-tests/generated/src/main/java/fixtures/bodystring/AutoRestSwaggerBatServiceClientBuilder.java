// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.bodystring;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
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
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.JacksonAdapter;
import fixtures.bodystring.implementation.AutoRestSwaggerBatServiceClientImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** A builder for creating a new instance of the AutoRestSwaggerBatServiceClient type. */
@ServiceClientBuilder(
        serviceClients = {
            StringOperationClient.class,
            EnumClient.class,
            StringOperationAsyncClient.class,
            EnumAsyncClient.class
        })
public final class AutoRestSwaggerBatServiceClientBuilder {

    @Generated private static final String SDK_NAME = "name";

    @Generated private static final String SDK_VERSION = "version";

    @Generated private final Map<String, String> properties = CoreUtils.getProperties("fixtures-bodystring.properties");

    /** Create an instance of the AutoRestSwaggerBatServiceClientBuilder. */
    @Generated
    public AutoRestSwaggerBatServiceClientBuilder() {
        this.pipelinePolicies = new ArrayList<>();
    }

    /*
     * server parameter
     */
    @Generated private String host;

    private String connectionString;

    private AzureKeyCredential keyCredential;

    public AutoRestSwaggerBatServiceClientBuilder connectionString(String host) {
        this.connectionString = connectionString;
        return this;
    }

    /**
     * Sets server parameter.
     *
     * @param host the host value.
     * @return the AutoRestSwaggerBatServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerBatServiceClientBuilder host(String host) {
        this.host = host;
        return this;
    }

    /*
     * The HTTP pipeline to send requests through
     */
    @Generated private HttpPipeline pipeline;

    /**
     * Sets The HTTP pipeline to send requests through.
     *
     * @param pipeline the pipeline value.
     * @return the AutoRestSwaggerBatServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerBatServiceClientBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /*
     * The HTTP client used to send the request.
     */
    @Generated private HttpClient httpClient;

    /**
     * Sets The HTTP client used to send the request.
     *
     * @param httpClient the httpClient value.
     * @return the AutoRestSwaggerBatServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerBatServiceClientBuilder httpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /*
     * The configuration store that is used during construction of the service
     * client.
     */
    @Generated private Configuration configuration;

    /**
     * Sets The configuration store that is used during construction of the service client.
     *
     * @param configuration the configuration value.
     * @return the AutoRestSwaggerBatServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerBatServiceClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * The logging configuration for HTTP requests and responses.
     */
    @Generated private HttpLogOptions httpLogOptions;

    /**
     * Sets The logging configuration for HTTP requests and responses.
     *
     * @param httpLogOptions the httpLogOptions value.
     * @return the AutoRestSwaggerBatServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerBatServiceClientBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
        this.httpLogOptions = httpLogOptions;
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
     * @return the AutoRestSwaggerBatServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerBatServiceClientBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /*
     * The list of Http pipeline policies to add.
     */
    @Generated private final List<HttpPipelinePolicy> pipelinePolicies;

    /*
     * The client options such as application ID and custom headers to set on a
     * request.
     */
    @Generated private ClientOptions clientOptions;

    /**
     * Sets The client options such as application ID and custom headers to set on a request.
     *
     * @param clientOptions the clientOptions value.
     * @return the AutoRestSwaggerBatServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerBatServiceClientBuilder clientOptions(ClientOptions clientOptions) {
        this.clientOptions = clientOptions;
        return this;
    }

    /**
     * Adds a custom Http pipeline policy.
     *
     * @param customPolicy The custom Http pipeline policy to add.
     * @return the AutoRestSwaggerBatServiceClientBuilder.
     */
    @Generated
    public AutoRestSwaggerBatServiceClientBuilder addPolicy(HttpPipelinePolicy customPolicy) {
        pipelinePolicies.add(customPolicy);
        return this;
    }

    /**
     * Builds an instance of AutoRestSwaggerBatServiceClientImpl with the provided parameters.
     *
     * @return an instance of AutoRestSwaggerBatServiceClientImpl.
     */
    private AutoRestSwaggerBatServiceClientImpl buildInnerClient() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (connectionString != null) {
            this.host = connectionString.split(Pattern.quote(";"))[0];
            this.keyCredential = new AzureKeyCredential(connectionString.split(Pattern.quote(";"))[1]);
        }
        if (pipeline == null) {
            this.pipeline = createHttpPipeline();
        }
        AutoRestSwaggerBatServiceClientImpl client =
                new AutoRestSwaggerBatServiceClientImpl(
                        pipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
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
        policies.add(retryPolicy == null ? new RetryPolicy() : retryPolicy);
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
     * Builds an instance of StringOperationAsyncClient class.
     *
     * @return an instance of StringOperationAsyncClient.
     */
    @Generated
    public StringOperationAsyncClient buildStringOperationAsyncClient() {
        return new StringOperationAsyncClient(buildInnerClient().getStringOperations());
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
     * Builds an instance of StringOperationClient class.
     *
     * @return an instance of StringOperationClient.
     */
    @Generated
    public StringOperationClient buildStringOperationClient() {
        return new StringOperationClient(new StringOperationAsyncClient(buildInnerClient().getStringOperations()));
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
}
