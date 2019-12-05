package fixtures.custombaseuri;

import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * Initializes a new instance of the AutoRestParameterizedHostTestClient type.
 */
public final class AutoRestParameterizedHostTestClient {
    /**
     */
    private String accountName;

    /**
     * Gets null.
     * 
     * @return the accountName value.
     */
    public String getAccountName() {
        return this.accountName;
    }

    /**
     * Sets null.
     * 
     * @param accountName the accountName value.
     * @return the service client itself.
     */
    AutoRestParameterizedHostTestClient setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    /**
     * host.
     */
    private String host;

    /**
     * Gets host.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets host.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    AutoRestParameterizedHostTestClient setHost(String host) {
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
     * The Paths object to access its operations.
     */
    private Paths paths;

    /**
     * Gets the Paths object to access its operations.
     * 
     * @return the Paths object.
     */
    public Paths paths() {
        return this.paths;
    }

    /**
     * Initializes an instance of AutoRestParameterizedHostTestClient client.
     */
    public AutoRestParameterizedHostTestClient() {
        this(RestProxy.createDefaultPipeline());
    }

    /**
     * Initializes an instance of AutoRestParameterizedHostTestClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestParameterizedHostTestClient(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.paths = new Paths(this);
    }
}
