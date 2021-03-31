package fixtures.custombaseuri.moreoptions;

import com.azure.android.core.http.HttpPipeline;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the AutoRestParameterizedCustomHostTestClient type. */
public final class AutoRestParameterizedCustomHostTestClient {
    /** The subscription id with value 'test12'. */
    private final String subscriptionId;

    /**
     * Gets The subscription id with value 'test12'.
     *
     * @return the subscriptionId value.
     */
    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    /** A string value that is used as a global part of the parameterized host. Default value 'host'. */
    private final String dnsSuffix;

    /**
     * Gets A string value that is used as a global part of the parameterized host. Default value 'host'.
     *
     * @return the dnsSuffix value.
     */
    public String getDnsSuffix() {
        return this.dnsSuffix;
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

    /** The Paths object to access its operations. */
    private final Paths paths;

    /**
     * Gets the Paths object to access its operations.
     *
     * @return the Paths object.
     */
    public Paths getPaths() {
        return this.paths;
    }

    /**
     * Initializes an instance of AutoRestParameterizedCustomHostTestClient client.
     *
     * @param subscriptionId The subscription id with value 'test12'.
     * @param dnsSuffix A string value that is used as a global part of the parameterized host. Default value 'host'.
     */
    AutoRestParameterizedCustomHostTestClient(String subscriptionId, String dnsSuffix) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                subscriptionId,
                dnsSuffix);
    }

    /**
     * Initializes an instance of AutoRestParameterizedCustomHostTestClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param subscriptionId The subscription id with value 'test12'.
     * @param dnsSuffix A string value that is used as a global part of the parameterized host. Default value 'host'.
     */
    AutoRestParameterizedCustomHostTestClient(HttpPipeline httpPipeline, String subscriptionId, String dnsSuffix) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), subscriptionId, dnsSuffix);
    }

    /**
     * Initializes an instance of AutoRestParameterizedCustomHostTestClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param subscriptionId The subscription id with value 'test12'.
     * @param dnsSuffix A string value that is used as a global part of the parameterized host. Default value 'host'.
     */
    AutoRestParameterizedCustomHostTestClient(
            HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String subscriptionId, String dnsSuffix) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.subscriptionId = subscriptionId;
        this.dnsSuffix = dnsSuffix;
        this.paths = new Paths(this);
    }
}
