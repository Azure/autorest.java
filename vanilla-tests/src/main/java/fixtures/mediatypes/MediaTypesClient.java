package fixtures.mediatypes;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import fixtures.mediatypes.models.ContentType;
import fixtures.mediatypes.models.SourcePath;
import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the MediaTypesClient type. */
public final class MediaTypesClient {
    /** The proxy service used to perform REST calls. */
    private final MediaTypesClientService service;

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

    /** Initializes an instance of MediaTypesClient client. */
    MediaTypesClient(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of MediaTypesClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    MediaTypesClient(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of MediaTypesClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     */
    MediaTypesClient(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.service = RestProxy.create(MediaTypesClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for MediaTypesClient to be used by the proxy service to perform REST
     * calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "MediaTypesClient")
    private interface MediaTypesClientService {
        @Post("/mediatypes/analyze")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> analyzeBody(
                @HostParam("$host") String host,
                @HeaderParam("Content-Type") ContentType contentType,
                @BodyParam("application/pdf") Flux<ByteBuffer> input,
                @HeaderParam("Content-Length") long contentLength,
                Context context);

        @Post("/mediatypes/analyze")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> analyzeBody(
                @HostParam("$host") String host, @BodyParam("application/json") SourcePath input, Context context);

        @Post("/mediatypes/contentTypeWithEncoding")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> contentTypeWithEncoding(
                @HostParam("$host") String host, @BodyParam("text/plain") String input, Context context);
    }

    /**
     * Analyze body, that could be different media types.
     *
     * @param contentType Content type for upload.
     * @param input Uri or local path to source data.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> analyzeBodyWithResponseAsync(
            ContentType contentType, Flux<ByteBuffer> input, long contentLength) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (contentType == null) {
            return Mono.error(new IllegalArgumentException("Parameter contentType is required and cannot be null."));
        }
        if (input == null) {
            return Mono.error(new IllegalArgumentException("Parameter input is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context -> service.analyzeBody(this.getHost(), contentType, input, contentLength, context));
    }

    /**
     * Analyze body, that could be different media types.
     *
     * @param contentType Content type for upload.
     * @param input Uri or local path to source data.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> analyzeBodyAsync(ContentType contentType, Flux<ByteBuffer> input, long contentLength) {
        return analyzeBodyWithResponseAsync(contentType, input, contentLength)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Analyze body, that could be different media types.
     *
     * @param contentType Content type for upload.
     * @param input Uri or local path to source data.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String analyzeBody(ContentType contentType, Flux<ByteBuffer> input, long contentLength) {
        return analyzeBodyAsync(contentType, input, contentLength).block();
    }

    /**
     * Analyze body, that could be different media types.
     *
     * @param source File source path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> analyzeBodyWithResponseAsync(String source) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        SourcePath inputInternal = null;
        if (source != null) {
            inputInternal = new SourcePath();
            inputInternal.setSource(source);
        }
        SourcePath input = inputInternal;
        return FluxUtil.withContext(context -> service.analyzeBody(this.getHost(), input, context));
    }

    /**
     * Analyze body, that could be different media types.
     *
     * @param source File source path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> analyzeBodyAsync(String source) {
        return analyzeBodyWithResponseAsync(source)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Analyze body, that could be different media types.
     *
     * @param source File source path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String analyzeBody(String source) {
        return analyzeBodyAsync(source).block();
    }

    /**
     * Pass in contentType 'text/plain; encoding=UTF-8' to pass test. Value for input does not matter.
     *
     * @param input simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> contentTypeWithEncodingWithResponseAsync(String input) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (input == null) {
            return Mono.error(new IllegalArgumentException("Parameter input is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.contentTypeWithEncoding(this.getHost(), input, context));
    }

    /**
     * Pass in contentType 'text/plain; encoding=UTF-8' to pass test. Value for input does not matter.
     *
     * @param input simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> contentTypeWithEncodingAsync(String input) {
        return contentTypeWithEncodingWithResponseAsync(input)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Pass in contentType 'text/plain; encoding=UTF-8' to pass test. Value for input does not matter.
     *
     * @param input simple string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String contentTypeWithEncoding(String input) {
        return contentTypeWithEncodingAsync(input).block();
    }
}
