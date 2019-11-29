package fixtures.header;

import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * Initializes a new instance of the AutoRestSwaggerBATHeaderService type.
 */
public final class AutoRestSwaggerBATHeaderService {
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
    AutoRestSwaggerBATHeaderService setHost(String host) {
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
     * The Headers object to access its operations.
     */
    private Headers headers;

    /**
     * Gets the Headers object to access its operations.
     * 
     * @return the Headers object.
     */
    public Headers headers() {
        return this.headers;
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATHeaderService client.
     */
    public AutoRestSwaggerBATHeaderService() {
        this(RestProxy.createDefaultPipeline());
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATHeaderService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestSwaggerBATHeaderService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.headers = new Headers(this);
    }
}
