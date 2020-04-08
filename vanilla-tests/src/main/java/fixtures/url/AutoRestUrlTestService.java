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
     * A string value 'globalItemStringPath' that appears in the path.
     */
    private String globalStringPath;

    /**
     * Gets A string value 'globalItemStringPath' that appears in the path.
     * 
     * @return the globalStringPath value.
     */
    public String getGlobalStringPath() {
        return this.globalStringPath;
    }

    /**
     * Sets A string value 'globalItemStringPath' that appears in the path.
     * 
     * @param globalStringPath the globalStringPath value.
     * @return the service client itself.
     */
    public AutoRestUrlTestService setGlobalStringPath(String globalStringPath) {
        this.globalStringPath = globalStringPath;
        return this;
    }

    /**
     * should contain value null.
     */
    private String globalStringQuery;

    /**
     * Gets should contain value null.
     * 
     * @return the globalStringQuery value.
     */
    public String getGlobalStringQuery() {
        return this.globalStringQuery;
    }

    /**
     * Sets should contain value null.
     * 
     * @param globalStringQuery the globalStringQuery value.
     * @return the service client itself.
     */
    public AutoRestUrlTestService setGlobalStringQuery(String globalStringQuery) {
        this.globalStringQuery = globalStringQuery;
        return this;
    }

    /**
     * server parameter.
     */
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
    public AutoRestUrlTestService setHost(String host) {
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
     * The Queries object to access its operations.
     */
    private final Queries queries;

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
    private final PathItems pathItems;

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
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
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
