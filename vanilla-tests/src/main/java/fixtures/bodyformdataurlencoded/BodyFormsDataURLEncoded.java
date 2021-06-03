package fixtures.bodyformdataurlencoded;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the BodyFormsDataURLEncoded type. */
public final class BodyFormsDataURLEncoded {
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

    /** The Formdataurlencodeds object to access its operations. */
    private final Formdataurlencodeds formdataurlencodeds;

    /**
     * Gets the Formdataurlencodeds object to access its operations.
     *
     * @return the Formdataurlencodeds object.
     */
    public Formdataurlencodeds getFormdataurlencodeds() {
        return this.formdataurlencodeds;
    }

    /**
     * Initializes an instance of BodyFormsDataURLEncoded client.
     *
     * @param host server parameter.
     */
    BodyFormsDataURLEncoded(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of BodyFormsDataURLEncoded client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    BodyFormsDataURLEncoded(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of BodyFormsDataURLEncoded client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    BodyFormsDataURLEncoded(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.formdataurlencodeds = new Formdataurlencodeds(this);
    }
}
