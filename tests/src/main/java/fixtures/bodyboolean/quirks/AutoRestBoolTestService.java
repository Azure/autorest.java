package fixtures.bodyboolean.quirks;

import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * Initializes a new instance of the AutoRestBoolTestService type.
 */
public final class AutoRestBoolTestService {
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
    AutoRestBoolTestService setHost(String host) {
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
     * The Bools object to access its operations.
     */
    private Bools bools;

    /**
     * Gets the Bools object to access its operations.
     * 
     * @return the Bools object.
     */
    public Bools bools() {
        return this.bools;
    }

    /**
     * Initializes an instance of AutoRestBoolTestService client.
     */
    public AutoRestBoolTestService() {
        this(RestProxy.createDefaultPipeline());
    }

    /**
     * Initializes an instance of AutoRestBoolTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestBoolTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.bools = new Bools(this);
    }
}
