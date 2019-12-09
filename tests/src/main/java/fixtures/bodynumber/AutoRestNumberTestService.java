package fixtures.bodynumber;

import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * Initializes a new instance of the AutoRestNumberTestService type.
 */
public final class AutoRestNumberTestService {
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
    AutoRestNumberTestService setHost(String host) {
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
     * The Numbers object to access its operations.
     */
    private Numbers numbers;

    /**
     * Gets the Numbers object to access its operations.
     * 
     * @return the Numbers object.
     */
    public Numbers numbers() {
        return this.numbers;
    }

    /**
     * Initializes an instance of AutoRestNumberTestService client.
     */
    public AutoRestNumberTestService() {
        this(RestProxy.createDefaultPipeline());
    }

    /**
     * Initializes an instance of AutoRestNumberTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestNumberTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.numbers = new Numbers(this);
    }
}
