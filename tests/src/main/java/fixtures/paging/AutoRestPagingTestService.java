package fixtures.paging;

import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * Initializes a new instance of the AutoRestPagingTestService type.
 */
public final class AutoRestPagingTestService {
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
    AutoRestPagingTestService setHost(String host) {
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
     * The Pagings object to access its operations.
     */
    private Pagings pagings;

    /**
     * Gets the Pagings object to access its operations.
     * 
     * @return the Pagings object.
     */
    public Pagings pagings() {
        return this.pagings;
    }

    /**
     * Initializes an instance of AutoRestPagingTestService client.
     */
    public AutoRestPagingTestService() {
        this(RestProxy.createDefaultPipeline());
    }

    /**
     * Initializes an instance of AutoRestPagingTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestPagingTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.pagings = new Pagings(this);
    }
}
