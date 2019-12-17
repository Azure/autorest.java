package fixtures.bodyinteger;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RestProxy;

/**
 * Initializes a new instance of the AutoRestIntegerTestService type.
 */
public final class AutoRestIntegerTestService {
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
    AutoRestIntegerTestService setHost(String host) {
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
     * The Ints object to access its operations.
     */
    private Ints ints;

    /**
     * Gets the Ints object to access its operations.
     * 
     * @return the Ints object.
     */
    public Ints ints() {
        return this.ints;
    }

    /**
     * Initializes an instance of AutoRestIntegerTestService client.
     */
    public AutoRestIntegerTestService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestIntegerTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestIntegerTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.ints = new Ints(this);
    }
}
