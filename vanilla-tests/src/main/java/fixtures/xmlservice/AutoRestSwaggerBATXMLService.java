package fixtures.xmlservice;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestSwaggerBATXMLService type. */
public final class AutoRestSwaggerBATXMLService {
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

    /** The Xmls object to access its operations. */
    private final Xmls xmls;

    /**
     * Gets the Xmls object to access its operations.
     *
     * @return the Xmls object.
     */
    public Xmls getXmls() {
        return this.xmls;
    }

    /** Initializes an instance of AutoRestSwaggerBATXMLService client. */
    AutoRestSwaggerBATXMLService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATXMLService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestSwaggerBATXMLService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.xmls = new Xmls(this);
    }
}
