package fixtures.lro;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the AutoRestLongRunningOperationTestService type. */
public final class AutoRestLongRunningOperationTestService {
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

    /** The LROs object to access its operations. */
    private final LROs lROs;

    /**
     * Gets the LROs object to access its operations.
     *
     * @return the LROs object.
     */
    public LROs getLROs() {
        return this.lROs;
    }

    /** The LRORetrys object to access its operations. */
    private final LRORetrys lRORetrys;

    /**
     * Gets the LRORetrys object to access its operations.
     *
     * @return the LRORetrys object.
     */
    public LRORetrys getLRORetrys() {
        return this.lRORetrys;
    }

    /** The LrosaDs object to access its operations. */
    private final LrosaDs lrosaDs;

    /**
     * Gets the LrosaDs object to access its operations.
     *
     * @return the LrosaDs object.
     */
    public LrosaDs getLrosaDs() {
        return this.lrosaDs;
    }

    /** The LROsCustomHeaders object to access its operations. */
    private final LROsCustomHeaders lROsCustomHeaders;

    /**
     * Gets the LROsCustomHeaders object to access its operations.
     *
     * @return the LROsCustomHeaders object.
     */
    public LROsCustomHeaders getLROsCustomHeaders() {
        return this.lROsCustomHeaders;
    }

    /**
     * Initializes an instance of AutoRestLongRunningOperationTestService client.
     *
     * @param host server parameter.
     */
    AutoRestLongRunningOperationTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of AutoRestLongRunningOperationTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    AutoRestLongRunningOperationTestService(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of AutoRestLongRunningOperationTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    AutoRestLongRunningOperationTestService(
            HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.lROs = new LROs(this);
        this.lRORetrys = new LRORetrys(this);
        this.lrosaDs = new LrosaDs(this);
        this.lROsCustomHeaders = new LROsCustomHeaders(this);
    }
}
