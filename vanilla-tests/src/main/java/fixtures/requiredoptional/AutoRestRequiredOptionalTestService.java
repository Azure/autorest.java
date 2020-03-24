package fixtures.requiredoptional;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestRequiredOptionalTestService type.
 */
public final class AutoRestRequiredOptionalTestService {
    /**
     * number of items to skip.
     */
    private String requiredGlobalPath;

    /**
     * Gets number of items to skip.
     * 
     * @return the requiredGlobalPath value.
     */
    public String getRequiredGlobalPath() {
        return this.requiredGlobalPath;
    }

    /**
     * Sets number of items to skip.
     * 
     * @param requiredGlobalPath the requiredGlobalPath value.
     * @return the service client itself.
     */
    AutoRestRequiredOptionalTestService setRequiredGlobalPath(String requiredGlobalPath) {
        this.requiredGlobalPath = requiredGlobalPath;
        return this;
    }

    /**
     * number of items to skip.
     */
    private String requiredGlobalQuery;

    /**
     * Gets number of items to skip.
     * 
     * @return the requiredGlobalQuery value.
     */
    public String getRequiredGlobalQuery() {
        return this.requiredGlobalQuery;
    }

    /**
     * Sets number of items to skip.
     * 
     * @param requiredGlobalQuery the requiredGlobalQuery value.
     * @return the service client itself.
     */
    AutoRestRequiredOptionalTestService setRequiredGlobalQuery(String requiredGlobalQuery) {
        this.requiredGlobalQuery = requiredGlobalQuery;
        return this;
    }

    /**
     * number of items to skip.
     */
    private int optionalGlobalQuery;

    /**
     * Gets number of items to skip.
     * 
     * @return the optionalGlobalQuery value.
     */
    public int getOptionalGlobalQuery() {
        return this.optionalGlobalQuery;
    }

    /**
     * Sets number of items to skip.
     * 
     * @param optionalGlobalQuery the optionalGlobalQuery value.
     * @return the service client itself.
     */
    AutoRestRequiredOptionalTestService setOptionalGlobalQuery(int optionalGlobalQuery) {
        this.optionalGlobalQuery = optionalGlobalQuery;
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
    AutoRestRequiredOptionalTestService setHost(String host) {
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
     * The Implicits object to access its operations.
     */
    private Implicits implicits;

    /**
     * Gets the Implicits object to access its operations.
     * 
     * @return the Implicits object.
     */
    public Implicits implicits() {
        return this.implicits;
    }

    /**
     * The Explicits object to access its operations.
     */
    private Explicits explicits;

    /**
     * Gets the Explicits object to access its operations.
     * 
     * @return the Explicits object.
     */
    public Explicits explicits() {
        return this.explicits;
    }

    /**
     * Initializes an instance of AutoRestRequiredOptionalTestService client.
     */
    public AutoRestRequiredOptionalTestService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestRequiredOptionalTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestRequiredOptionalTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.implicits = new Implicits(this);
        this.explicits = new Explicits(this);
    }
}
