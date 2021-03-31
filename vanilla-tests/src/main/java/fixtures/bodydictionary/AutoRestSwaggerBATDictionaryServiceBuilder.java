package fixtures.bodydictionary;

import com.azure.android.core.http.HttpPipeline;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.policy.HttpPolicyProviders;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.Configuration;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import fixtures.bodydictionary.implementation.AutoRestSwaggerBATDictionaryServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** A builder for creating a new instance of the AutoRestSwaggerBATDictionaryService type. */
@ServiceClientBuilder(
        serviceClients = {
            AutoRestSwaggerBATDictionaryServiceClient.class,
            AutoRestSwaggerBATDictionaryServiceAsyncClient.class
        })
public final class AutoRestSwaggerBATDictionaryServiceBuilder {
    private static final String SDK_NAME = "name";

    private static final String SDK_VERSION = "version";

    private final Map<String, String> properties = new HashMap<>();

    /** Create an instance of the AutoRestSwaggerBATDictionaryServiceBuilder. */
    public AutoRestSwaggerBATDictionaryServiceBuilder() {
        this.pipelinePolicies = new ArrayList<>();
    }

    /*
     * server parameter
     */
    private String host;

    /**
     * Sets server parameter.
     *
     * @param host the host value.
     * @return the AutoRestSwaggerBATDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBATDictionaryServiceBuilder host(String host) {
        this.host = host;
        return this;
    }

    /*
     * The HTTP pipeline to send requests through
     */
    private HttpPipeline pipeline;

    /**
     * Sets The HTTP pipeline to send requests through.
     *
     * @param pipeline the pipeline value.
     * @return the AutoRestSwaggerBATDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBATDictionaryServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /*
     * The serializer to serialize an object into a string
     */
    private SerializerAdapter serializerAdapter;

    /**
     * Sets The serializer to serialize an object into a string.
     *
     * @param serializerAdapter the serializerAdapter value.
     * @return the AutoRestSwaggerBATDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBATDictionaryServiceBuilder serializerAdapter(SerializerAdapter serializerAdapter) {
        this.serializerAdapter = serializerAdapter;
        return this;
    }

    /*
     * The HTTP client used to send the request.
     */
    private HttpClient httpClient;

    /**
     * Sets The HTTP client used to send the request.
     *
     * @param httpClient the httpClient value.
     * @return the AutoRestSwaggerBATDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBATDictionaryServiceBuilder httpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /*
     * The configuration store that is used during construction of the service
     * client.
     */
    private Configuration configuration;

    /**
     * Sets The configuration store that is used during construction of the service client.
     *
     * @param configuration the configuration value.
     * @return the AutoRestSwaggerBATDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBATDictionaryServiceBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    /*
     * The logging configuration for HTTP requests and responses.
     */
    private HttpLogOptions httpLogOptions;

    /**
     * Sets The logging configuration for HTTP requests and responses.
     *
     * @param httpLogOptions the httpLogOptions value.
     * @return the AutoRestSwaggerBATDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBATDictionaryServiceBuilder httpLogOptions(HttpLogOptions httpLogOptions) {
        this.httpLogOptions = httpLogOptions;
        return this;
    }

    /*
     * The retry policy that will attempt to retry failed requests, if
     * applicable.
     */
    private RetryPolicy retryPolicy;

    /**
     * Sets The retry policy that will attempt to retry failed requests, if applicable.
     *
     * @param retryPolicy the retryPolicy value.
     * @return the AutoRestSwaggerBATDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBATDictionaryServiceBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        return this;
    }

    /*
     * The list of Http pipeline policies to add.
     */
    private final List<HttpPipelinePolicy> pipelinePolicies;

    /**
     * Adds a custom Http pipeline policy.
     *
     * @param customPolicy The custom Http pipeline policy to add.
     * @return the AutoRestSwaggerBATDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBATDictionaryServiceBuilder addPolicy(HttpPipelinePolicy customPolicy) {
        pipelinePolicies.add(customPolicy);
        return this;
    }

    /**
     * Builds an instance of AutoRestSwaggerBATDictionaryServiceImpl with the provided parameters.
     *
     * @return an instance of AutoRestSwaggerBATDictionaryServiceImpl.
     */
    private AutoRestSwaggerBATDictionaryServiceImpl buildInnerClient() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = createHttpPipeline();
        }
        if (serializerAdapter == null) {
            this.serializerAdapter = JacksonAdapter.createDefaultSerializerAdapter();
        }
        AutoRestSwaggerBATDictionaryServiceImpl client =
                new AutoRestSwaggerBATDictionaryServiceImpl(pipeline, serializerAdapter, host);
        return client;
    }

    private HttpPipeline createHttpPipeline() {
        Configuration buildConfiguration =
                (configuration == null) ? Configuration.getGlobalConfiguration() : configuration;
        if (httpLogOptions == null) {
            httpLogOptions = new HttpLogOptions();
        }
        List<HttpPipelinePolicy> policies = new ArrayList<>();
        String clientName = properties.getOrDefault(SDK_NAME, "UnknownName");
        String clientVersion = properties.getOrDefault(SDK_VERSION, "UnknownVersion");
        policies.add(
                new UserAgentPolicy(httpLogOptions.getApplicationId(), clientName, clientVersion, buildConfiguration));
        HttpPolicyProviders.addBeforeRetryPolicies(policies);
        policies.add(retryPolicy == null ? new RetryPolicy() : retryPolicy);
        policies.add(new CookiePolicy());
        policies.addAll(this.pipelinePolicies);
        HttpPolicyProviders.addAfterRetryPolicies(policies);
        policies.add(new HttpLoggingPolicy(httpLogOptions));
        HttpPipeline httpPipeline =
                new HttpPipelineBuilder()
                        .policies(policies.toArray(new HttpPipelinePolicy[0]))
                        .httpClient(httpClient)
                        .build();
        return httpPipeline;
    }

    /**
     * Builds an instance of AutoRestSwaggerBATDictionaryServiceAsyncClient async client.
     *
     * @return an instance of AutoRestSwaggerBATDictionaryServiceAsyncClient.
     */
    public AutoRestSwaggerBATDictionaryServiceAsyncClient buildAsyncClient() {
        return new AutoRestSwaggerBATDictionaryServiceAsyncClient(buildInnerClient().getDictionaries());
    }

    /**
     * Builds an instance of AutoRestSwaggerBATDictionaryServiceClient sync client.
     *
     * @return an instance of AutoRestSwaggerBATDictionaryServiceClient.
     */
    public AutoRestSwaggerBATDictionaryServiceClient buildClient() {
        return new AutoRestSwaggerBATDictionaryServiceClient(buildInnerClient().getDictionaries());
    }
}
