package fixtures.url;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestUrlTestService type.
 */
public final class AutoRestUrlTestService {
    /**
     * http://localhost:3000.
     */
    private String host;

    /**
     * Gets http://localhost:3000.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets http://localhost:3000.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    AutoRestUrlTestService setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private HttpPipeline httpPipeline;

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
    private Paths paths;

    /**
     * Gets the Paths object to access its operations.
     * 
     * @return the Paths object.
     */
    public Paths paths() {
        return this.paths;
    }

    /**
     * The Queries object to access its operations.
     */
    private Queries queries;

    /**
     * Gets the Queries object to access its operations.
     * 
     * @return the Queries object.
     */
    public Queries queries() {
        return this.queries;
    }

    /**
     * The PathItems object to access its operations.
     */
    private PathItems pathItems;

    /**
     * Gets the PathItems object to access its operations.
     * 
     * @return the PathItems object.
     */
    public PathItems pathItems() {
        return this.pathItems;
    }

    /**
     * Initializes an instance of AutoRestUrlTestService client.
     */
    public AutoRestUrlTestService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestUrlTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestUrlTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.paths = new Paths(this);
        this.queries = new Queries(this);
        this.pathItems = new PathItems(this);
    }
}
