package fixtures.bodystring;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestSwaggerBATService type. */
public final class AutoRestSwaggerBATService {
    /** server parameter. */
    private final String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
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
    AutoRestSwaggerBATService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestSwaggerBATService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.stringOperations = new StringOperations(this);
        this.enums = new Enums(this);
    }
}
