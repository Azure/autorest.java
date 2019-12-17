package fixtures.bodydatetime;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RestProxy;

/**
 * Initializes a new instance of the AutoRestDateTimeTestService type.
 */
public final class AutoRestDateTimeTestService {
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
    AutoRestDateTimeTestService setHost(String host) {
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
     * The Datetimes object to access its operations.
     */
    private Datetimes datetimes;

    /**
     * Gets the Datetimes object to access its operations.
     * 
     * @return the Datetimes object.
     */
    public Datetimes datetimes() {
        return this.datetimes;
    }

    /**
     * Initializes an instance of AutoRestDateTimeTestService client.
     */
    public AutoRestDateTimeTestService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestDateTimeTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestDateTimeTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.datetimes = new Datetimes(this);
    }
}
