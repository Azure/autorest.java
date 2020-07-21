package fixtures.bodydatetimerfc1123;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestRFC1123DateTimeTestService type. */
public final class AutoRestRFC1123DateTimeTestService {
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

    /** The Datetimerfc1123s object to access its operations. */
    private final Datetimerfc1123s datetimerfc1123s;

    /**
     * Gets the Datetimerfc1123s object to access its operations.
     *
     * @return the Datetimerfc1123s object.
     */
    public Datetimerfc1123s getDatetimerfc1123s() {
        return this.datetimerfc1123s;
    }

    /** Initializes an instance of AutoRestRFC1123DateTimeTestService client. */
    AutoRestRFC1123DateTimeTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestRFC1123DateTimeTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestRFC1123DateTimeTestService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.datetimerfc1123s = new Datetimerfc1123s(this);
    }
}
