package fixtures.requiredoptional;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RestProxy;

/**
 * Initializes a new instance of the AutoRestRequiredOptionalTestService type.
 */
public final class AutoRestRequiredOptionalTestService {
    /**
     */
    private String requiredGlobalPath;

    /**
     * Gets null.
     * 
     * @return the requiredGlobalPath value.
     */
    public String getRequiredGlobalPath() {
        return this.requiredGlobalPath;
    }

    /**
     * Sets null.
     * 
     * @param requiredGlobalPath the requiredGlobalPath value.
     * @return the service client itself.
     */
    AutoRestRequiredOptionalTestService setRequiredGlobalPath(String requiredGlobalPath) {
        this.requiredGlobalPath = requiredGlobalPath;
        return this;
    }

    /**
     */
    private String requiredGlobalQuery;

    /**
     * Gets null.
     * 
     * @return the requiredGlobalQuery value.
     */
    public String getRequiredGlobalQuery() {
        return this.requiredGlobalQuery;
    }

    /**
     * Sets null.
     * 
     * @param requiredGlobalQuery the requiredGlobalQuery value.
     * @return the service client itself.
     */
    AutoRestRequiredOptionalTestService setRequiredGlobalQuery(String requiredGlobalQuery) {
        this.requiredGlobalQuery = requiredGlobalQuery;
        return this;
    }

    /**
     */
    private int optionalGlobalQuery;

    /**
     * Gets null.
     * 
     * @return the optionalGlobalQuery value.
     */
    public int getOptionalGlobalQuery() {
        return this.optionalGlobalQuery;
    }

    /**
     * Sets null.
     * 
     * @param optionalGlobalQuery the optionalGlobalQuery value.
     * @return the service client itself.
     */
    AutoRestRequiredOptionalTestService setOptionalGlobalQuery(int optionalGlobalQuery) {
        this.optionalGlobalQuery = optionalGlobalQuery;
        return this;
    }

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
