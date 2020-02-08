package fixtures.header;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * A builder for creating a new instance of the AutoRestSwaggerBatHeaderService type.
 */
@ServiceClientBuilder(serviceClients = AutoRestSwaggerBatHeaderService.class)
public final class AutoRestSwaggerBatHeaderServiceBuilder {
    /*
     * server parameter
     */
    private String host;

    /**
     * Sets server parameter.
     * 
     * @param host the host value.
     * @return the AutoRestSwaggerBatHeaderServiceBuilder.
     */
    public AutoRestSwaggerBatHeaderServiceBuilder host(String host) {
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
     * @return the AutoRestSwaggerBatHeaderServiceBuilder.
     */
    public AutoRestSwaggerBatHeaderServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestSwaggerBatHeaderService with the provided parameters.
     * 
     * @return an instance of AutoRestSwaggerBatHeaderService.
     */
    public AutoRestSwaggerBatHeaderService build() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        AutoRestSwaggerBatHeaderService client = new AutoRestSwaggerBatHeaderService(pipeline);
        client.setHost(this.host);
        return client;
    }
}
