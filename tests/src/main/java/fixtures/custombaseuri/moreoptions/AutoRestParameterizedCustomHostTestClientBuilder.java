package fixtures.custombaseuri.moreoptions;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * A builder for creating a new instance of the AutoRestParameterizedCustomHostTestClient type.
 */
@ServiceClientBuilder(serviceClients = AutoRestParameterizedCustomHostTestClient.class)
public final class AutoRestParameterizedCustomHostTestClientBuilder {
    /*
     * null
     */
    private String subscriptionId;

    /**
     * Sets null.
     * 
     * @param subscriptionId the subscriptionId value.
     * @return the AutoRestParameterizedCustomHostTestClientBuilder.
     */
    public AutoRestParameterizedCustomHostTestClientBuilder subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
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
     * @return the AutoRestParameterizedCustomHostTestClientBuilder.
     */
    public AutoRestParameterizedCustomHostTestClientBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestParameterizedCustomHostTestClient with the provided parameters.
     * 
     * @return an instance of AutoRestParameterizedCustomHostTestClient.
     */
    public AutoRestParameterizedCustomHostTestClient build() {
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        AutoRestParameterizedCustomHostTestClient client = new AutoRestParameterizedCustomHostTestClient(pipeline);
        if (this.subscriptionId != null) {
            client.setSubscriptionId(this.subscriptionId);
        }
        return client;
    }
}
