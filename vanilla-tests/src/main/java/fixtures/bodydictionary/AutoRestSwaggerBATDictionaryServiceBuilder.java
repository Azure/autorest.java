package fixtures.bodydictionary;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import fixtures.bodydictionary.implementation.AutoRestSwaggerBATDictionaryServiceImpl;

/** A builder for creating a new instance of the AutoRestSwaggerBATDictionaryService type. */
@ServiceClientBuilder(
        serviceClients = {
            AutoRestSwaggerBATDictionaryServiceClient.class,
            AutoRestSwaggerBATDictionaryServiceAsyncClient.class
        })
public final class AutoRestSwaggerBATDictionaryServiceBuilder {
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
            this.pipeline =
                    new HttpPipelineBuilder()
                            .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                            .build();
        }
        AutoRestSwaggerBATDictionaryServiceImpl client = new AutoRestSwaggerBATDictionaryServiceImpl(pipeline, host);
        return client;
    }

    /**
     * Builds an instance of AutoRestSwaggerBATDictionaryServiceAsyncClient async client.
     *
     * @return an instance of AutoRestSwaggerBATDictionaryServiceAsyncClient.
     */
    public AutoRestSwaggerBATDictionaryServiceAsyncClient buildAsyncClient() {
        return new AutoRestSwaggerBATDictionaryServiceAsyncClient(buildInnerClient().getDictionarys());
    }

    /**
     * Builds an instance of AutoRestSwaggerBATDictionaryServiceClient sync client.
     *
     * @return an instance of AutoRestSwaggerBATDictionaryServiceClient.
     */
    public AutoRestSwaggerBATDictionaryServiceClient buildClient() {
        return new AutoRestSwaggerBATDictionaryServiceClient(buildInnerClient().getDictionarys());
    }
}
