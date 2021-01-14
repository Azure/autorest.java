package fixtures.azurespecials;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the AutoRestAzureSpecialParametersTestClient type. */
public final class AutoRestAzureSpecialParametersTestClient {
    /**
     * The subscription id, which appears in the path, always modeled in credentials. The value is always
     * '1234-5678-9012-3456'.
     */
    private final String subscriptionId;

    /**
     * Gets The subscription id, which appears in the path, always modeled in credentials. The value is always
     * '1234-5678-9012-3456'.
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

    /** The XMsClientRequestIds object to access its operations. */
    private final XMsClientRequestIds xMsClientRequestIds;

    /**
     * Gets the XMsClientRequestIds object to access its operations.
     *
     * @return the XMsClientRequestIds object.
     */
    public XMsClientRequestIds getXMsClientRequestIds() {
        return this.xMsClientRequestIds;
    }

    /** The SubscriptionInCredentials object to access its operations. */
    private final SubscriptionInCredentials subscriptionInCredentials;

    /**
     * Gets the SubscriptionInCredentials object to access its operations.
     *
     * @return the SubscriptionInCredentials object.
     */
    public SubscriptionInCredentials getSubscriptionInCredentials() {
        return this.subscriptionInCredentials;
    }

    /** The SubscriptionInMethods object to access its operations. */
    private final SubscriptionInMethods subscriptionInMethods;

    /**
     * Gets the SubscriptionInMethods object to access its operations.
     *
     * @return the SubscriptionInMethods object.
     */
    public SubscriptionInMethods getSubscriptionInMethods() {
        return this.subscriptionInMethods;
    }

    /** The ApiVersionDefaults object to access its operations. */
    private final ApiVersionDefaults apiVersionDefaults;

    /**
     * Gets the ApiVersionDefaults object to access its operations.
     *
     * @return the ApiVersionDefaults object.
     */
    public ApiVersionDefaults getApiVersionDefaults() {
        return this.apiVersionDefaults;
    }

    /** The ApiVersionLocals object to access its operations. */
    private final ApiVersionLocals apiVersionLocals;

    /**
     * Gets the ApiVersionLocals object to access its operations.
     *
     * @return the ApiVersionLocals object.
     */
    public ApiVersionLocals getApiVersionLocals() {
        return this.apiVersionLocals;
    }

    /** The SkipUrlEncodings object to access its operations. */
    private final SkipUrlEncodings skipUrlEncodings;

    /**
     * Gets the SkipUrlEncodings object to access its operations.
     *
     * @return the SkipUrlEncodings object.
     */
    public SkipUrlEncodings getSkipUrlEncodings() {
        return this.skipUrlEncodings;
    }

    /** The Odatas object to access its operations. */
    private final Odatas odatas;

    /**
     * Gets the Odatas object to access its operations.
     *
     * @return the Odatas object.
     */
    public Odatas getOdatas() {
        return this.odatas;
    }

    /** The Headers object to access its operations. */
    private final Headers headers;

    /**
     * Gets the Headers object to access its operations.
     *
     * @return the Headers object.
     */
    public Headers getHeaders() {
        return this.headers;
    }

    /**
     * Initializes an instance of AutoRestAzureSpecialParametersTestClient client.
     *
     * @param subscriptionId The subscription id, which appears in the path, always modeled in credentials. The value is
     *     always '1234-5678-9012-3456'.
     * @param host server parameter.
     */
    AutoRestAzureSpecialParametersTestClient(String subscriptionId, String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                subscriptionId,
                host);
    }

    /**
     * Initializes an instance of AutoRestAzureSpecialParametersTestClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param subscriptionId The subscription id, which appears in the path, always modeled in credentials. The value is
     *     always '1234-5678-9012-3456'.
     * @param host server parameter.
     */
    AutoRestAzureSpecialParametersTestClient(HttpPipeline httpPipeline, String subscriptionId, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), subscriptionId, host);
    }

    /**
     * Initializes an instance of AutoRestAzureSpecialParametersTestClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param subscriptionId The subscription id, which appears in the path, always modeled in credentials. The value is
     *     always '1234-5678-9012-3456'.
     * @param host server parameter.
     */
    AutoRestAzureSpecialParametersTestClient(
            HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String subscriptionId, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.subscriptionId = subscriptionId;
        this.host = host;
        this.apiVersion = "2015-07-01-preview";
        this.xMsClientRequestIds = new XMsClientRequestIds(this);
        this.subscriptionInCredentials = new SubscriptionInCredentials(this);
        this.subscriptionInMethods = new SubscriptionInMethods(this);
        this.apiVersionDefaults = new ApiVersionDefaults(this);
        this.apiVersionLocals = new ApiVersionLocals(this);
        this.skipUrlEncodings = new SkipUrlEncodings(this);
        this.odatas = new Odatas(this);
        this.headers = new Headers(this);
    }
}
