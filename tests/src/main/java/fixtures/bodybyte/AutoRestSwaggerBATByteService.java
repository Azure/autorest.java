package fixtures.bodybyte;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestSwaggerBATByteService type.
 */
public final class AutoRestSwaggerBATByteService {
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
    AutoRestSwaggerBATByteService setHost(String host) {
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
     * The Bytes object to access its operations.
     */
    private Bytes bytes;

    /**
     * Gets the Bytes object to access its operations.
     * 
     * @return the Bytes object.
     */
    public Bytes bytes() {
        return this.bytes;
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATByteService client.
     */
    public AutoRestSwaggerBATByteService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATByteService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestSwaggerBATByteService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.bytes = new Bytes(this);
    }
}
