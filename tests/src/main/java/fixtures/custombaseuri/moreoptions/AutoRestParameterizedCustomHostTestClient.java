package fixtures.custombaseuri.moreoptions;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestParameterizedCustomHostTestClient type.
 */
public final class AutoRestParameterizedCustomHostTestClient {
    /**
     */
    private String subscriptionId;

    /**
     * Gets null.
     * 
     * @return the subscriptionId value.
     */
    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    /**
     * Sets null.
     * 
     * @param subscriptionId the subscriptionId value.
     * @return the service client itself.
     */
    AutoRestParameterizedCustomHostTestClient setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The Paths object to access its operations.
     */
    private Paths paths;

    /**
     * Gets the Paths object to access its operations.
     * 
     * @return the Paths object.
     */
    public Paths paths() {
        return this.paths;
    }

    /**
     * Initializes an instance of AutoRestParameterizedCustomHostTestClient client.
     */
    public AutoRestParameterizedCustomHostTestClient() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestParameterizedCustomHostTestClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestParameterizedCustomHostTestClient(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.paths = new Paths(this);
    }
}
