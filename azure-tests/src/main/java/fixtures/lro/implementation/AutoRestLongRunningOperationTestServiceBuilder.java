package fixtures.lro.implementation;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.BearerTokenAuthenticationPolicy;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.http.policy.HttpPolicyProviders;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.serializer.SerializerFactory;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.SerializerAdapter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A builder for creating a new instance of the AutoRestLongRunningOperationTestServiceImpl type.
 */
@ServiceClientBuilder(serviceClients = {AutoRestLongRunningOperationTestServiceImpl.class})
public final class AutoRestLongRunningOperationTestServiceBuilder {
    /*
     * server parameter
     */
    private String endpoint;

    /**
     * Sets server parameter.
     * 
     * @param endpoint the endpoint value.
     * @return the AutoRestLongRunningOperationTestServiceBuilder.
     */
    public AutoRestLongRunningOperationTestServiceBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /*
     * The environment to connect to
     */
    private AzureEnvironment environment;

    /**
     * Sets The environment to connect to.
     * 
     * @param environment the environment value.
     * @return the AutoRestLongRunningOperationTestServiceBuilder.
     */
    public AutoRestLongRunningOperationTestServiceBuilder environment(AzureEnvironment environment) {
        this.environment = environment;
        return this;
    }

    /*
     * The default poll interval for long-running operation
     */
    private Duration defaultPollInterval;

    /**
     * Sets The default poll interval for long-running operation.
     * 
     * @param defaultPollInterval the defaultPollInterval value.
     * @return the AutoRestLongRunningOperationTestServiceBuilder.
     */
    public AutoRestLongRunningOperationTestServiceBuilder defaultPollInterval(Duration defaultPollInterval) {
        this.defaultPollInterval = defaultPollInterval;
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
     * @return the AutoRestLongRunningOperationTestServiceBuilder.
     */
    public AutoRestLongRunningOperationTestServiceBuilder pipeline(HttpPipeline pipeline) {
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
     * @return the AutoRestLongRunningOperationTestServiceBuilder.
     */
    public AutoRestLongRunningOperationTestServiceBuilder serializerAdapter(SerializerAdapter serializerAdapter) {
        this.serializerAdapter = serializerAdapter;
        return this;
    }

    /**
     * Builds an instance of AutoRestLongRunningOperationTestServiceImpl with the provided parameters.
     * 
     * @return an instance of AutoRestLongRunningOperationTestServiceImpl.
     */
    public AutoRestLongRunningOperationTestServiceImpl buildClient() {
        if (endpoint == null) {
            this.endpoint = "http://localhost:3000";
        }
        if (environment == null) {
            this.environment = AzureEnvironment.AZURE;
        }
        if (defaultPollInterval == null) {
            this.defaultPollInterval = Duration.ofSeconds(30);
        }
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        if (serializerAdapter == null) {
            this.serializerAdapter = SerializerFactory.createDefaultManagementSerializerAdapter();
        }
        AutoRestLongRunningOperationTestServiceImpl client = new AutoRestLongRunningOperationTestServiceImpl(pipeline, serializerAdapter, defaultPollInterval, environment, endpoint);
        return client;
    }
}
