package fixtures.requiredoptional;

import com.azure.android.core.http.HttpPipeline;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

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

    /** The serializer to serialize an object into a string. */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     *
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
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

    /**
     * Initializes an instance of AutoRestRequiredOptionalTestService client.
     *
     * @param requiredGlobalPath number of items to skip.
     * @param requiredGlobalQuery number of items to skip.
     * @param optionalGlobalQuery number of items to skip.
     * @param host server parameter.
     */
    AutoRestRequiredOptionalTestService(
            String requiredGlobalPath, String requiredGlobalQuery, int optionalGlobalQuery, String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                requiredGlobalPath,
                requiredGlobalQuery,
                optionalGlobalQuery,
                host);
    }

    /**
     * Initializes an instance of AutoRestRequiredOptionalTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param requiredGlobalPath number of items to skip.
     * @param requiredGlobalQuery number of items to skip.
     * @param optionalGlobalQuery number of items to skip.
     * @param host server parameter.
     */
    AutoRestRequiredOptionalTestService(
            HttpPipeline httpPipeline,
            String requiredGlobalPath,
            String requiredGlobalQuery,
            int optionalGlobalQuery,
            String host) {
        this(
                httpPipeline,
                JacksonAdapter.createDefaultSerializerAdapter(),
                requiredGlobalPath,
                requiredGlobalQuery,
                optionalGlobalQuery,
                host);
    }

    /**
     * Initializes an instance of AutoRestRequiredOptionalTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param requiredGlobalPath number of items to skip.
     * @param requiredGlobalQuery number of items to skip.
     * @param optionalGlobalQuery number of items to skip.
     * @param host server parameter.
     */
    AutoRestRequiredOptionalTestService(
            HttpPipeline httpPipeline,
            SerializerAdapter serializerAdapter,
            String requiredGlobalPath,
            String requiredGlobalQuery,
            int optionalGlobalQuery,
            String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.requiredGlobalPath = requiredGlobalPath;
        this.requiredGlobalQuery = requiredGlobalQuery;
        this.optionalGlobalQuery = optionalGlobalQuery;
        this.host = host;
        this.implicits = new Implicits(this);
        this.explicits = new Explicits(this);
    }
}
