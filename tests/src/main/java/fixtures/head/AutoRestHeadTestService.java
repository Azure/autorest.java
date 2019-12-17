package fixtures.head;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RestProxy;

/**
 * Initializes a new instance of the AutoRestHeadTestService type.
 */
public final class AutoRestHeadTestService {
    /**
     * http://localhost:3000.
     */
    private String host;

    /**
     * Gets http://localhost:3000.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets http://localhost:3000.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    AutoRestHeadTestService setHost(String host) {
        this.host = host;
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
     * The HttpSuccess object to access its operations.
     */
    private HttpSuccess httpSuccess;

    /**
     * Gets the HttpSuccess object to access its operations.
     * 
     * @return the HttpSuccess object.
     */
    public HttpSuccess httpSuccess() {
        return this.httpSuccess;
    }

    /**
     * Initializes an instance of AutoRestHeadTestService client.
     */
    public AutoRestHeadTestService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestHeadTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestHeadTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.httpSuccess = new HttpSuccess(this);
    }
}
