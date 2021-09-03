package fixtures.url.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the AutoRestUrlTestService type. */
public final class AutoRestUrlTestServiceImpl {
    /** A string value 'globalItemStringPath' that appears in the path. */
    private final String globalStringPath;

    /**
     * Gets A string value 'globalItemStringPath' that appears in the path.
     *
     * @return the globalStringPath value.
     */
    public String getGlobalStringPath() {
        return this.globalStringPath;
    }

    /** should contain value null. */
    private final String globalStringQuery;

    /**
     * Gets should contain value null.
     *
     * @return the globalStringQuery value.
     */
    public String getGlobalStringQuery() {
        return this.globalStringQuery;
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

    /** The PathsImpl object to access its operations. */
    private final PathsImpl paths;

    /**
     * Gets the PathsImpl object to access its operations.
     *
     * @return the PathsImpl object.
     */
    public PathsImpl getPaths() {
        return this.paths;
    }

    /** The QueriesImpl object to access its operations. */
    private final QueriesImpl queries;

    /**
     * Gets the QueriesImpl object to access its operations.
     *
     * @return the QueriesImpl object.
     */
    public QueriesImpl getQueries() {
        return this.queries;
    }

    /** The PathItemsImpl object to access its operations. */
    private final PathItemsImpl pathItems;

    /**
     * Gets the PathItemsImpl object to access its operations.
     *
     * @return the PathItemsImpl object.
     */
    public PathItemsImpl getPathItems() {
        return this.pathItems;
    }

    /**
     * Initializes an instance of AutoRestUrlTestService client.
     *
     * @param globalStringPath A string value 'globalItemStringPath' that appears in the path.
     * @param globalStringQuery should contain value null.
     * @param host server parameter.
     */
    public AutoRestUrlTestServiceImpl(String globalStringPath, String globalStringQuery, String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                globalStringPath,
                globalStringQuery,
                host);
    }

    /**
     * Initializes an instance of AutoRestUrlTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param globalStringPath A string value 'globalItemStringPath' that appears in the path.
     * @param globalStringQuery should contain value null.
     * @param host server parameter.
     */
    public AutoRestUrlTestServiceImpl(
            HttpPipeline httpPipeline, String globalStringPath, String globalStringQuery, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), globalStringPath, globalStringQuery, host);
    }

    /**
     * Initializes an instance of AutoRestUrlTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param globalStringPath A string value 'globalItemStringPath' that appears in the path.
     * @param globalStringQuery should contain value null.
     * @param host server parameter.
     */
    public AutoRestUrlTestServiceImpl(
            HttpPipeline httpPipeline,
            SerializerAdapter serializerAdapter,
            String globalStringPath,
            String globalStringQuery,
            String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.globalStringPath = globalStringPath;
        this.globalStringQuery = globalStringQuery;
        this.host = host;
        this.paths = new PathsImpl(this);
        this.queries = new QueriesImpl(this);
        this.pathItems = new PathItemsImpl(this);
    }
}
