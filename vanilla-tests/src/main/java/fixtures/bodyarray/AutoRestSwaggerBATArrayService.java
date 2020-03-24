package fixtures.bodyarray;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestSwaggerBATArrayService type.
 */
public final class AutoRestSwaggerBATArrayService {
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
    AutoRestSwaggerBATArrayService setHost(String host) {
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
     * The Arrays object to access its operations.
     */
    private Arrays arrays;

    /**
     * Gets the Arrays object to access its operations.
     * 
     * @return the Arrays object.
     */
    public Arrays arrays() {
        return this.arrays;
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATArrayService client.
     */
    public AutoRestSwaggerBATArrayService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATArrayService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestSwaggerBATArrayService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.arrays = new Arrays(this);
    }
}
