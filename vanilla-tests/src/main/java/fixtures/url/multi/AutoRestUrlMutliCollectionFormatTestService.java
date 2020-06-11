package fixtures.url.multi;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestUrlMutliCollectionFormatTestService type. */
public final class AutoRestUrlMutliCollectionFormatTestService {
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

    /** The Queries object to access its operations. */
    private final Queries queries;

    /**
     * Gets the Queries object to access its operations.
     *
     * @return the Queries object.
     */
    public Queries getQueries() {
        return this.queries;
    }

    /** Initializes an instance of AutoRestUrlMutliCollectionFormatTestService client. */
    AutoRestUrlMutliCollectionFormatTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestUrlMutliCollectionFormatTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestUrlMutliCollectionFormatTestService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.queries = new Queries(this);
    }
}
