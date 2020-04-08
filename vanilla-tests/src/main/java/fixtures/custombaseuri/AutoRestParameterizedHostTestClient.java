package fixtures.custombaseuri;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestParameterizedHostTestClient type.
 */
public final class AutoRestParameterizedHostTestClient {
    /**
     * A string value that is used as a global part of the parameterized host.
     */
    private String host;

    /**
     * Gets A string value that is used as a global part of the parameterized host.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets A string value that is used as a global part of the parameterized host.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    public AutoRestParameterizedHostTestClient setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The Paths object to access its operations.
     */
    private final Paths paths;

    /**
     * Gets the Paths object to access its operations.
     * 
     * @return the Paths object.
     */
    public Paths paths() {
        return this.paths;
    }

    /**
     * Initializes an instance of AutoRestParameterizedHostTestClient client.
     */
    public AutoRestParameterizedHostTestClient() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of AutoRestParameterizedHostTestClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestParameterizedHostTestClient(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.paths = new Paths(this);
    }
}
