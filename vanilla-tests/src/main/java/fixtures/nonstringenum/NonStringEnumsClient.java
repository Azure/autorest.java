package fixtures.nonstringenum;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the NonStringEnumsClient type. */
public final class NonStringEnumsClient {
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

    /** The Ints object to access its operations. */
    private final Ints ints;

    /**
     * Gets the Ints object to access its operations.
     *
     * @return the Ints object.
     */
    public Ints getInts() {
        return this.ints;
    }

    /** The FloatOperations object to access its operations. */
    private final FloatOperations floatOperations;

    /**
     * Gets the FloatOperations object to access its operations.
     *
     * @return the FloatOperations object.
     */
    public FloatOperations getFloatOperations() {
        return this.floatOperations;
    }

    /** Initializes an instance of NonStringEnumsClient client. */
    NonStringEnumsClient(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of NonStringEnumsClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    NonStringEnumsClient(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.ints = new Ints(this);
        this.floatOperations = new FloatOperations(this);
    }
}
