package fixtures.headexceptions;

import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * Initializes a new instance of the AutoRestHeadExceptionTestService type.
 */
public final class AutoRestHeadExceptionTestService {
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
    AutoRestHeadExceptionTestService setHost(String host) {
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
     * The HeadExceptions object to access its operations.
     */
    private HeadExceptions headExceptions;

    /**
     * Gets the HeadExceptions object to access its operations.
     * 
     * @return the HeadExceptions object.
     */
    public HeadExceptions headExceptions() {
        return this.headExceptions;
    }

    /**
     * Initializes an instance of AutoRestHeadExceptionTestService client.
     */
    public AutoRestHeadExceptionTestService() {
        this(RestProxy.createDefaultPipeline());
    }

    /**
     * Initializes an instance of AutoRestHeadExceptionTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestHeadExceptionTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.headExceptions = new HeadExceptions(this);
    }
}
