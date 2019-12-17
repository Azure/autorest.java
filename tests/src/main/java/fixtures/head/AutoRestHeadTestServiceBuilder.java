package fixtures.head;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RestProxy;

/**
 * A builder for creating a new instance of the AutoRestHeadTestService type.
 */
@ServiceClientBuilder(serviceClients = AutoRestHeadTestService.class)
public final class AutoRestHeadTestServiceBuilder {
    /*
     * http://localhost:3000
     */
    private String host;

    /**
     * Sets http://localhost:3000.
     * 
     * @param host the host value.
     * @return the AutoRestHeadTestServiceBuilder.
     */
    public AutoRestHeadTestServiceBuilder host(String host) {
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
     * @return the AutoRestHeadTestServiceBuilder.
     */
    public AutoRestHeadTestServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestHeadTestService with the provided parameters.
     * 
     * @return an instance of AutoRestHeadTestService.
     */
    public AutoRestHeadTestService build() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        AutoRestHeadTestService client = new AutoRestHeadTestService(pipeline);
        client.setHost(this.host);
        return client;
    }
}
