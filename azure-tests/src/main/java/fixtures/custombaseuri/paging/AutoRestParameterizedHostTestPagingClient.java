package fixtures.custombaseuri.paging;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the AutoRestParameterizedHostTestPagingClient type. */
public final class AutoRestParameterizedHostTestPagingClient {
    /** A string value that is used as a global part of the parameterized host. */
    private final String host;

    /**
     * Gets A string value that is used as a global part of the parameterized host.
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

    /** The Pagings object to access its operations. */
    private final Pagings pagings;

    /**
     * Gets the Pagings object to access its operations.
     *
     * @return the Pagings object.
     */
    public Pagings getPagings() {
        return this.pagings;
    }

    /**
     * Initializes an instance of AutoRestParameterizedHostTestPagingClient client.
     *
     * @param host A string value that is used as a global part of the parameterized host.
     */
    AutoRestParameterizedHostTestPagingClient(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of AutoRestParameterizedHostTestPagingClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host A string value that is used as a global part of the parameterized host.
     */
    AutoRestParameterizedHostTestPagingClient(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of AutoRestParameterizedHostTestPagingClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host A string value that is used as a global part of the parameterized host.
     */
    AutoRestParameterizedHostTestPagingClient(
            HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.pagings = new Pagings(this);
    }
}
