package fixtures.head;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestHeadTestService type. */
public final class AutoRestHeadTestService {
    /** server parameter. */
    private final String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
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

    /** The HttpSuccess object to access its operations. */
    private final HttpSuccess httpSuccess;

    /**
     * Gets the HttpSuccess object to access its operations.
     *
     * @return the HttpSuccess object.
     */
    public HttpSuccess getHttpSuccess() {
        return this.httpSuccess;
    }

    /** Initializes an instance of AutoRestHeadTestService client. */
    AutoRestHeadTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestHeadTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestHeadTestService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.httpSuccess = new HttpSuccess(this);
    }
}
