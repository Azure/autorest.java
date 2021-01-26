package fixtures.subscriptionidapiversion;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the MicrosoftAzureTestUrl type. */
public final class MicrosoftAzureTestUrl {
    /** Subscription Id. */
    private final String subscriptionId;

    /**
     * Gets Subscription Id.
     *
     * @return the subscriptionId value.
     */
    public String getSubscriptionId() {
        return this.subscriptionId;
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

    /** Api Version. */
    private final String apiVersion;

    /**
     * Gets Api Version.
     *
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
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

    /** The Groups object to access its operations. */
    private final Groups groups;

    /**
     * Gets the Groups object to access its operations.
     *
     * @return the Groups object.
     */
    public Groups getGroups() {
        return this.groups;
    }

    /**
     * Initializes an instance of MicrosoftAzureTestUrl client.
     *
     * @param subscriptionId Subscription Id.
     * @param host server parameter.
     */
    MicrosoftAzureTestUrl(String subscriptionId, String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                subscriptionId,
                host);
    }

    /**
     * Initializes an instance of MicrosoftAzureTestUrl client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param subscriptionId Subscription Id.
     * @param host server parameter.
     */
    MicrosoftAzureTestUrl(HttpPipeline httpPipeline, String subscriptionId, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), subscriptionId, host);
    }

    /**
     * Initializes an instance of MicrosoftAzureTestUrl client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param subscriptionId Subscription Id.
     * @param host server parameter.
     */
    MicrosoftAzureTestUrl(
            HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String subscriptionId, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.subscriptionId = subscriptionId;
        this.host = host;
        this.apiVersion = "2014-04-01-preview";
        this.groups = new Groups(this);
    }
}
