package fixtures.headexceptions;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestHeadExceptionTestService type. */
public final class AutoRestHeadExceptionTestService {
    /** server parameter. */
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
    public AutoRestHeadExceptionTestService setHost(String host) {
        this.host = host;
        return this;
    }

    /** The HTTP pipeline to send requests through. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** The HeadExceptions object to access its operations. */
    private final HeadExceptions headExceptions;

    /**
     * Gets the HeadExceptions object to access its operations.
     *
     * @return the HeadExceptions object.
     */
    public HeadExceptions getHeadExceptions() {
        return this.headExceptions;
    }

    /** Initializes an instance of AutoRestHeadExceptionTestService client. */
    public AutoRestHeadExceptionTestService() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of AutoRestHeadExceptionTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestHeadExceptionTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.headExceptions = new HeadExceptions(this);
    }
}
