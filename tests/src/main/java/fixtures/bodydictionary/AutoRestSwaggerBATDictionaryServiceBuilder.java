package fixtures.bodydictionary;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * A builder for creating a new instance of the AutoRestSwaggerBatDictionaryService type.
 */
@ServiceClientBuilder(serviceClients = AutoRestSwaggerBatDictionaryService.class)
public final class AutoRestSwaggerBatDictionaryServiceBuilder {
    /*
     * server parameter
     */
    private String host;

    /**
     * Sets server parameter.
     * 
     * @param host the host value.
     * @return the AutoRestSwaggerBatDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBatDictionaryServiceBuilder host(String host) {
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
     * @return the AutoRestSwaggerBatDictionaryServiceBuilder.
     */
    public AutoRestSwaggerBatDictionaryServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestSwaggerBatDictionaryService with the provided parameters.
     * 
     * @return an instance of AutoRestSwaggerBatDictionaryService.
     */
    public AutoRestSwaggerBatDictionaryService build() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        AutoRestSwaggerBatDictionaryService client = new AutoRestSwaggerBatDictionaryService(pipeline);
        client.setHost(this.host);
        return client;
    }
}
