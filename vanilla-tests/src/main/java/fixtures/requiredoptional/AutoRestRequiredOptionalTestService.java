package fixtures.requiredoptional;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestRequiredOptionalTestService type. */
public final class AutoRestRequiredOptionalTestService {
    /** number of items to skip. */
    private final String requiredGlobalPath;

    /**
     * Gets number of items to skip.
     *
     * @return the requiredGlobalPath value.
     */
    public String getRequiredGlobalPath() {
        return this.requiredGlobalPath;
    }

    /** number of items to skip. */
    private final String requiredGlobalQuery;

    /**
     * Gets number of items to skip.
     *
     * @return the requiredGlobalQuery value.
     */
    public String getRequiredGlobalQuery() {
        return this.requiredGlobalQuery;
    }

    /** number of items to skip. */
    private final int optionalGlobalQuery;

    /**
     * Gets number of items to skip.
     *
     * @return the optionalGlobalQuery value.
     */
    public int getOptionalGlobalQuery() {
        return this.optionalGlobalQuery;
    }

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

    /** The Implicits object to access its operations. */
    private final Implicits implicits;

    /**
     * Gets the Implicits object to access its operations.
     *
     * @return the Implicits object.
     */
    public Implicits getImplicits() {
        return this.implicits;
    }

    /** The Explicits object to access its operations. */
    private final Explicits explicits;

    /**
     * Gets the Explicits object to access its operations.
     *
     * @return the Explicits object.
     */
    public Explicits getExplicits() {
        return this.explicits;
    }

    /** Initializes an instance of AutoRestRequiredOptionalTestService client. */
    AutoRestRequiredOptionalTestService(
            String requiredGlobalPath, String requiredGlobalQuery, int optionalGlobalQuery, String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                requiredGlobalPath,
                requiredGlobalQuery,
                optionalGlobalQuery,
                host);
    }

    /**
     * Initializes an instance of AutoRestRequiredOptionalTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestRequiredOptionalTestService(
            HttpPipeline httpPipeline,
            String requiredGlobalPath,
            String requiredGlobalQuery,
            int optionalGlobalQuery,
            String host) {
        this.httpPipeline = httpPipeline;
        this.requiredGlobalPath = requiredGlobalPath;
        this.requiredGlobalQuery = requiredGlobalQuery;
        this.optionalGlobalQuery = optionalGlobalQuery;
        this.host = host;
        this.implicits = new Implicits(this);
        this.explicits = new Explicits(this);
    }
}
