package fixtures.parameterflattening;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestParameterFlattening type. */
public final class AutoRestParameterFlattening {
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

    /** The AvailabilitySets object to access its operations. */
    private final AvailabilitySets availabilitySets;

    /**
     * Gets the AvailabilitySets object to access its operations.
     *
     * @return the AvailabilitySets object.
     */
    public AvailabilitySets getAvailabilitySets() {
        return this.availabilitySets;
    }

    /** Initializes an instance of AutoRestParameterFlattening client. */
    AutoRestParameterFlattening(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestParameterFlattening client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestParameterFlattening(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.availabilitySets = new AvailabilitySets(this);
    }
}
