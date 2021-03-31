package fixtures.httpinfrastructure;

import com.azure.android.core.http.HttpPipeline;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the AutoRestHttpInfrastructureTestService type. */
public final class AutoRestHttpInfrastructureTestService {
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

    /** The HttpFailures object to access its operations. */
    private final HttpFailures httpFailures;

    /**
     * Gets the HttpFailures object to access its operations.
     *
     * @return the HttpFailures object.
     */
    public HttpFailures getHttpFailures() {
        return this.httpFailures;
    }

    /** The HttpSuccess object to access its operations. */
    private final HttpSuccess httpSuccess;

    /**
     * Gets the HttpSuccess object to access its operations.
     *
     * @return the HttpSuccess object.
     */
    public HttpSuccess getHttpSuccess() {
        return this.httpSuccess;
    }

    /** The HttpRedirects object to access its operations. */
    private final HttpRedirects httpRedirects;

    /**
     * Gets the HttpRedirects object to access its operations.
     *
     * @return the HttpRedirects object.
     */
    public HttpRedirects getHttpRedirects() {
        return this.httpRedirects;
    }

    /** The HttpClientFailures object to access its operations. */
    private final HttpClientFailures httpClientFailures;

    /**
     * Gets the HttpClientFailures object to access its operations.
     *
     * @return the HttpClientFailures object.
     */
    public HttpClientFailures getHttpClientFailures() {
        return this.httpClientFailures;
    }

    /** The HttpServerFailures object to access its operations. */
    private final HttpServerFailures httpServerFailures;

    /**
     * Gets the HttpServerFailures object to access its operations.
     *
     * @return the HttpServerFailures object.
     */
    public HttpServerFailures getHttpServerFailures() {
        return this.httpServerFailures;
    }

    /** The HttpRetries object to access its operations. */
    private final HttpRetries httpRetries;

    /**
     * Gets the HttpRetries object to access its operations.
     *
     * @return the HttpRetries object.
     */
    public HttpRetries getHttpRetries() {
        return this.httpRetries;
    }

    /** The MultipleResponses object to access its operations. */
    private final MultipleResponses multipleResponses;

    /**
     * Gets the MultipleResponses object to access its operations.
     *
     * @return the MultipleResponses object.
     */
    public MultipleResponses getMultipleResponses() {
        return this.multipleResponses;
    }

    /**
     * Initializes an instance of AutoRestHttpInfrastructureTestService client.
     *
     * @param host server parameter.
     */
    AutoRestHttpInfrastructureTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of AutoRestHttpInfrastructureTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    AutoRestHttpInfrastructureTestService(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of AutoRestHttpInfrastructureTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    AutoRestHttpInfrastructureTestService(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.httpFailures = new HttpFailures(this);
        this.httpSuccess = new HttpSuccess(this);
        this.httpRedirects = new HttpRedirects(this);
        this.httpClientFailures = new HttpClientFailures(this);
        this.httpServerFailures = new HttpServerFailures(this);
        this.httpRetries = new HttpRetries(this);
        this.multipleResponses = new MultipleResponses(this);
    }
}
