package fixtures.header;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestSwaggerBATHeaderService type. */
public final class AutoRestSwaggerBATHeaderService {
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
    public AutoRestSwaggerBATHeaderService setHost(String host) {
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

    /** The Headers object to access its operations. */
    private final Headers headers;

    /**
     * Gets the Headers object to access its operations.
     *
     * @return the Headers object.
     */
    public Headers getHeaders() {
        return this.headers;
    }

    /** Initializes an instance of AutoRestSwaggerBATHeaderService client. */
    public AutoRestSwaggerBATHeaderService() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATHeaderService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestSwaggerBATHeaderService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.headers = new Headers(this);
    }
}
