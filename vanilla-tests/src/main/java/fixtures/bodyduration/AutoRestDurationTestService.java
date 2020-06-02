package fixtures.bodyduration;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestDurationTestService type. */
public final class AutoRestDurationTestService {
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
    AutoRestDurationTestService setHost(String host) {
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

    /** The DurationOperations object to access its operations. */
    private final DurationOperations durationOperations;

    /**
     * Gets the DurationOperations object to access its operations.
     *
     * @return the DurationOperations object.
     */
    public DurationOperations getDurationOperations() {
        return this.durationOperations;
    }

    /** Initializes an instance of AutoRestDurationTestService client. */
    AutoRestDurationTestService() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of AutoRestDurationTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestDurationTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.durationOperations = new DurationOperations(this);
    }
}
