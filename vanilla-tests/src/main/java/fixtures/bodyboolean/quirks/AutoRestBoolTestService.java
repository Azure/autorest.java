package fixtures.bodyboolean.quirks;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestBoolTestService type. */
public final class AutoRestBoolTestService {
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

    /** The Bools object to access its operations. */
    private final Bools bools;

    /**
     * Gets the Bools object to access its operations.
     *
     * @return the Bools object.
     */
    public Bools getBools() {
        return this.bools;
    }

    /** Initializes an instance of AutoRestBoolTestService client. */
    AutoRestBoolTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestBoolTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestBoolTestService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.bools = new Bools(this);
    }
}
