package fixtures.bodyfile;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestSwaggerBATFileService type.
 */
public final class AutoRestSwaggerBATFileService {
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
    public AutoRestSwaggerBATFileService setHost(String host) {
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
     * The Files object to access its operations.
     */
    private final Files files;

    /**
     * Gets the Files object to access its operations.
     * 
     * @return the Files object.
     */
    public Files files() {
        return this.files;
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATFileService client.
     */
    public AutoRestSwaggerBATFileService() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATFileService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestSwaggerBATFileService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.files = new Files(this);
    }
}
