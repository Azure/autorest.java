package fixtures.httpinfrastructure;

import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * Initializes a new instance of the AutoRestHttpInfrastructureTestService type.
 */
public final class AutoRestHttpInfrastructureTestService {
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
    AutoRestHttpInfrastructureTestService setHost(String host) {
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
     * The HttpFailures object to access its operations.
     */
    private HttpFailures httpFailures;

    /**
     * Gets the HttpFailures object to access its operations.
     * 
     * @return the HttpFailures object.
     */
    public HttpFailures httpFailures() {
        return this.httpFailures;
    }

    /**
     * The HttpSuccess object to access its operations.
     */
    private HttpSuccess httpSuccess;

    /**
     * Gets the HttpSuccess object to access its operations.
     * 
     * @return the HttpSuccess object.
     */
    public HttpSuccess httpSuccess() {
        return this.httpSuccess;
    }

    /**
     * The HttpRedirects object to access its operations.
     */
    private HttpRedirects httpRedirects;

    /**
     * Gets the HttpRedirects object to access its operations.
     * 
     * @return the HttpRedirects object.
     */
    public HttpRedirects httpRedirects() {
        return this.httpRedirects;
    }

    /**
     * The HttpClientFailures object to access its operations.
     */
    private HttpClientFailures httpClientFailures;

    /**
     * Gets the HttpClientFailures object to access its operations.
     * 
     * @return the HttpClientFailures object.
     */
    public HttpClientFailures httpClientFailures() {
        return this.httpClientFailures;
    }

    /**
     * The HttpServerFailures object to access its operations.
     */
    private HttpServerFailures httpServerFailures;

    /**
     * Gets the HttpServerFailures object to access its operations.
     * 
     * @return the HttpServerFailures object.
     */
    public HttpServerFailures httpServerFailures() {
        return this.httpServerFailures;
    }

    /**
     * The HttpRetrys object to access its operations.
     */
    private HttpRetrys httpRetrys;

    /**
     * Gets the HttpRetrys object to access its operations.
     * 
     * @return the HttpRetrys object.
     */
    public HttpRetrys httpRetrys() {
        return this.httpRetrys;
    }

    /**
     * The MultipleResponses object to access its operations.
     */
    private MultipleResponses multipleResponses;

    /**
     * Gets the MultipleResponses object to access its operations.
     * 
     * @return the MultipleResponses object.
     */
    public MultipleResponses multipleResponses() {
        return this.multipleResponses;
    }

    /**
     * Initializes an instance of AutoRestHttpInfrastructureTestService client.
     */
    public AutoRestHttpInfrastructureTestService() {
        this(RestProxy.createDefaultPipeline());
    }

    /**
     * Initializes an instance of AutoRestHttpInfrastructureTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestHttpInfrastructureTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.httpFailures = new HttpFailures(this);
        this.httpSuccess = new HttpSuccess(this);
        this.httpRedirects = new HttpRedirects(this);
        this.httpClientFailures = new HttpClientFailures(this);
        this.httpServerFailures = new HttpServerFailures(this);
        this.httpRetrys = new HttpRetrys(this);
        this.multipleResponses = new MultipleResponses(this);
    }
}
