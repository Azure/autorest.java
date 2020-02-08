package fixtures.bodydatetimerfc1123;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * A builder for creating a new instance of the AutoRestRfc1123DateTimeTestService type.
 */
@ServiceClientBuilder(serviceClients = AutoRestRfc1123DateTimeTestService.class)
public final class AutoRestRfc1123DateTimeTestServiceBuilder {
    /*
     * server parameter
     */
    private String host;

    /**
     * Sets server parameter.
     * 
     * @param host the host value.
     * @return the AutoRestRfc1123DateTimeTestServiceBuilder.
     */
    public AutoRestRfc1123DateTimeTestServiceBuilder host(String host) {
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
     * @return the AutoRestRfc1123DateTimeTestServiceBuilder.
     */
    public AutoRestRfc1123DateTimeTestServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestRfc1123DateTimeTestService with the provided parameters.
     * 
     * @return an instance of AutoRestRfc1123DateTimeTestService.
     */
    public AutoRestRfc1123DateTimeTestService build() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        AutoRestRfc1123DateTimeTestService client = new AutoRestRfc1123DateTimeTestService(pipeline);
        client.setHost(this.host);
        return client;
    }
}
