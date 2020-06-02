package fixtures.bodystring;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestSwaggerBATService type. */
public final class AutoRestSwaggerBATService {
    /** server parameter. */
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
    AutoRestSwaggerBATService setHost(String host) {
        this.host = host;
        return this;
    }

    /** The HTTP pipeline to send requests through. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** The StringOperations object to access its operations. */
    private final StringOperations stringOperations;

    /**
     * Gets the StringOperations object to access its operations.
     *
     * @return the StringOperations object.
     */
    public StringOperations getStringOperations() {
        return this.stringOperations;
    }

    /** The Enums object to access its operations. */
    private final Enums enums;

    /**
     * Gets the Enums object to access its operations.
     *
     * @return the Enums object.
     */
    public Enums getEnums() {
        return this.enums;
    }

    /** Initializes an instance of AutoRestSwaggerBATService client. */
    AutoRestSwaggerBATService() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestSwaggerBATService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.stringOperations = new StringOperations(this);
        this.enums = new Enums(this);
    }
}
