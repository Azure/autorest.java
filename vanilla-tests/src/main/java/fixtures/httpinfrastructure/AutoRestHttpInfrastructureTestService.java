package fixtures.httpinfrastructure;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestHttpInfrastructureTestService type.
 */
public final class AutoRestHttpInfrastructureTestService {
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
    public AutoRestHttpInfrastructureTestService setHost(String host) {
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
     * The HttpFailures object to access its operations.
     */
    private final HttpFailures httpFailures;

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
    private final HttpSuccess httpSuccess;

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
    private final HttpRedirects httpRedirects;

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
    private final HttpClientFailures httpClientFailures;

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
    private final HttpServerFailures httpServerFailures;

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
    private final HttpRetrys httpRetrys;

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
    private final MultipleResponses multipleResponses;

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
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
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
