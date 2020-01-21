package fixtures.xmlservice;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestSwaggerBATXMLService type.
 */
public final class AutoRestSwaggerBATXMLService {
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
    AutoRestSwaggerBATXMLService setHost(String host) {
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
     * The Xmls object to access its operations.
     */
    private Xmls xmls;

    /**
     * Gets the Xmls object to access its operations.
     * 
     * @return the Xmls object.
     */
    public Xmls xmls() {
        return this.xmls;
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATXMLService client.
     */
    public AutoRestSwaggerBATXMLService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATXMLService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestSwaggerBATXMLService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.xmls = new Xmls(this);
    }
}
