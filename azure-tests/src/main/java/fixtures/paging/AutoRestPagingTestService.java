package fixtures.paging;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestPagingTestService type. */
public final class AutoRestPagingTestService {
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

    /** The Pagings object to access its operations. */
    private final Pagings pagings;

    /**
     * Gets the Pagings object to access its operations.
     *
     * @return the Pagings object.
     */
    public Pagings getPagings() {
        return this.pagings;
    }

    /** Initializes an instance of AutoRestPagingTestService client. */
    AutoRestPagingTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestPagingTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestPagingTestService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.pagings = new Pagings(this);
    }
}
