package fixtures.bodyduration;

import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * Initializes a new instance of the AutoRestDurationTestService type.
 */
public final class AutoRestDurationTestService {
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
    AutoRestDurationTestService setHost(String host) {
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
     * The Durations object to access its operations.
     */
    private Durations durations;

    /**
     * Gets the Durations object to access its operations.
     * 
     * @return the Durations object.
     */
    public Durations durations() {
        return this.durations;
    }

    /**
     * Initializes an instance of AutoRestDurationTestService client.
     */
    public AutoRestDurationTestService() {
        this(RestProxy.createDefaultPipeline());
    }

    /**
     * Initializes an instance of AutoRestDurationTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestDurationTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.durations = new Durations(this);
    }
}
