package fixtures.bodydate;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestDateTestService type.
 */
public final class AutoRestDateTestService {
    /**
     * server parameter.
     */
    private String host;

    /**
     * Gets server parameter.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets server parameter.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    AutoRestDateTestService setHost(String host) {
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
     * The Dates object to access its operations.
     */
    private Dates dates;

    /**
     * Gets the Dates object to access its operations.
     * 
     * @return the Dates object.
     */
    public Dates dates() {
        return this.dates;
    }

    /**
     * Initializes an instance of AutoRestDateTestService client.
     */
    public AutoRestDateTestService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestDateTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestDateTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.dates = new Dates(this);
    }
}
