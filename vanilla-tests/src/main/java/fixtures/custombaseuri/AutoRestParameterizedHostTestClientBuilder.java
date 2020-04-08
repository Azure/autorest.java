package fixtures.custombaseuri;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * A builder for creating a new instance of the AutoRestParameterizedHostTestClient type.
 */
@ServiceClientBuilder(serviceClients = {AutoRestParameterizedHostTestClient.class})
public final class AutoRestParameterizedHostTestClientBuilder {
    /*
     * A string value that is used as a global part of the parameterized host
     */
    private String host;

    /**
     * Sets A string value that is used as a global part of the parameterized host.
     * 
     * @param host the host value.
     * @return the AutoRestParameterizedHostTestClientBuilder.
     */
    public AutoRestParameterizedHostTestClientBuilder host(String host) {
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
     * @return the AutoRestParameterizedHostTestClientBuilder.
     */
    public AutoRestParameterizedHostTestClientBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestParameterizedHostTestClient with the provided parameters.
     * 
     * @return an instance of AutoRestParameterizedHostTestClient.
     */
    public AutoRestParameterizedHostTestClient buildClient() {
        if (host == null) {
            this.host = "host";
        }
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        AutoRestParameterizedHostTestClient client = new AutoRestParameterizedHostTestClient(pipeline);
        client.setHost(this.host);
        return client;
    }
}
