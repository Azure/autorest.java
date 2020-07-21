package fixtures.bodyarray;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestSwaggerBATArrayService type. */
public final class AutoRestSwaggerBATArrayService {
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

    /** The Arrays object to access its operations. */
    private final Arrays arrays;

    /**
     * Gets the Arrays object to access its operations.
     *
     * @return the Arrays object.
     */
    public Arrays getArrays() {
        return this.arrays;
    }

    /** Initializes an instance of AutoRestSwaggerBATArrayService client. */
    AutoRestSwaggerBATArrayService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATArrayService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestSwaggerBATArrayService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.arrays = new Arrays(this);
    }
}
