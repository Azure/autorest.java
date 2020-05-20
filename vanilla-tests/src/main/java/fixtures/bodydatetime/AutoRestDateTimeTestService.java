package fixtures.bodydatetime;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestDateTimeTestService type.
 */
public final class AutoRestDateTimeTestService {
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
    public AutoRestDateTimeTestService setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The DatetimeOperations object to access its operations.
     */
    private final DatetimeOperations datetimeOperations;

    /**
     * Gets the DatetimeOperations object to access its operations.
     * 
     * @return the DatetimeOperations object.
     */
    public DatetimeOperations getDatetimeOperations() {
        return this.datetimeOperations;
    }

    /**
     * Initializes an instance of AutoRestDateTimeTestService client.
     */
    public AutoRestDateTimeTestService() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of AutoRestDateTimeTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestDateTimeTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.datetimeOperations = new DatetimeOperations(this);
    }
}
