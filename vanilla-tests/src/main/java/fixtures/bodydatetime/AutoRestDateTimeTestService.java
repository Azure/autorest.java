package fixtures.bodydatetime;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestDateTimeTestService type. */
public final class AutoRestDateTimeTestService {
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

    /** The DatetimeOperations object to access its operations. */
    private final DatetimeOperations datetimeOperations;

    /**
     * Gets the DatetimeOperations object to access its operations.
     *
     * @return the DatetimeOperations object.
     */
    public DatetimeOperations getDatetimeOperations() {
        return this.datetimeOperations;
    }

    /** Initializes an instance of AutoRestDateTimeTestService client. */
    AutoRestDateTimeTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestDateTimeTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestDateTimeTestService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.datetimeOperations = new DatetimeOperations(this);
    }
}
