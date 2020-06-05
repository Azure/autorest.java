package fixtures.bodydate;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestDateTestService type. */
public final class AutoRestDateTestService {
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

    /** The DateOperations object to access its operations. */
    private final DateOperations dateOperations;

    /**
     * Gets the DateOperations object to access its operations.
     *
     * @return the DateOperations object.
     */
    public DateOperations getDateOperations() {
        return this.dateOperations;
    }

    /** Initializes an instance of AutoRestDateTestService client. */
    AutoRestDateTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestDateTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestDateTestService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.dateOperations = new DateOperations(this);
    }
}
