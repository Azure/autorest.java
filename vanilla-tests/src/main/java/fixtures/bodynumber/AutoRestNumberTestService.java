package fixtures.bodynumber;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestNumberTestService type. */
public final class AutoRestNumberTestService {
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
    public AutoRestNumberTestService setHost(String host) {
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

    /** The Numbers object to access its operations. */
    private final Numbers numbers;

    /**
     * Gets the Numbers object to access its operations.
     *
     * @return the Numbers object.
     */
    public Numbers getNumbers() {
        return this.numbers;
    }

    /** Initializes an instance of AutoRestNumberTestService client. */
    public AutoRestNumberTestService() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of AutoRestNumberTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestNumberTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.numbers = new Numbers(this);
    }
}
