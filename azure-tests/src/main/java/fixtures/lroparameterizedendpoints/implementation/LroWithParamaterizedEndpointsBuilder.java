package fixtures.lroparameterizedendpoints.implementation;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.serializer.SerializerFactory;
import com.azure.core.util.serializer.SerializerAdapter;
import java.time.Duration;

/** A builder for creating a new instance of the LroWithParamaterizedEndpointsImpl type. */
@ServiceClientBuilder(serviceClients = {LroWithParamaterizedEndpointsImpl.class})
public final class LroWithParamaterizedEndpointsBuilder {
    /*
     * A string value that is used as a global part of the parameterized host.
     * Pass in 'host:3000' to pass test.
     */
    private String host;

    /**
     * Sets A string value that is used as a global part of the parameterized host. Pass in 'host:3000' to pass test.
     *
     * @param host the host value.
     * @return the LroWithParamaterizedEndpointsBuilder.
     */
    public LroWithParamaterizedEndpointsBuilder host(String host) {
        this.host = host;
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
     * @return the LroWithParamaterizedEndpointsBuilder.
     */
    public LroWithParamaterizedEndpointsBuilder environment(AzureEnvironment environment) {
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
     * @return the LroWithParamaterizedEndpointsBuilder.
     */
    public LroWithParamaterizedEndpointsBuilder defaultPollInterval(Duration defaultPollInterval) {
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
     * @return the LroWithParamaterizedEndpointsBuilder.
     */
    public LroWithParamaterizedEndpointsBuilder pipeline(HttpPipeline pipeline) {
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
     * @return the LroWithParamaterizedEndpointsBuilder.
     */
    public LroWithParamaterizedEndpointsBuilder serializerAdapter(SerializerAdapter serializerAdapter) {
        this.serializerAdapter = serializerAdapter;
        return this;
    }

    /**
     * Builds an instance of LroWithParamaterizedEndpointsImpl with the provided parameters.
     *
     * @return an instance of LroWithParamaterizedEndpointsImpl.
     */
    public LroWithParamaterizedEndpointsImpl buildClient() {
        if (host == null) {
            this.host = "host";
        }
        if (environment == null) {
            this.environment = AzureEnvironment.AZURE;
        }
        if (defaultPollInterval == null) {
            this.defaultPollInterval = Duration.ofSeconds(30);
        }
        if (pipeline == null) {
            this.pipeline =
                new HttpPipelineBuilder()
                    .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                    .build();
        }
        if (serializerAdapter == null) {
            this.serializerAdapter = SerializerFactory.createDefaultManagementSerializerAdapter();
        }
        LroWithParamaterizedEndpointsImpl client =
            new LroWithParamaterizedEndpointsImpl(pipeline, serializerAdapter, defaultPollInterval, environment, host);
        return client;
    }
}
