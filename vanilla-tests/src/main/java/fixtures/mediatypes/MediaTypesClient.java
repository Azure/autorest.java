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
import fixtures.mediatypes.models.ContentType1;
import fixtures.mediatypes.models.SourcePath;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

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

    /**
     * Initializes an instance of MediaTypesClient client.
     *
     * @param host server parameter.
     */
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
     * @param host server parameter.
     */
    MediaTypesClient(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of MediaTypesClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
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
                @BodyParam("application/octet-stream") Flux<ByteBuffer> input,
                @HeaderParam("Content-Length") Long contentLength,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/mediatypes/analyze")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> analyzeBody(
                @HostParam("$host") String host,
                @BodyParam("application/json") SourcePath input,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/mediatypes/analyzeNoAccept")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> analyzeBodyNoAcceptHeader(
                @HostParam("$host") String host,
                @HeaderParam("Content-Type") ContentType contentType,
                @BodyParam("application/octet-stream") Flux<ByteBuffer> input,
                @HeaderParam("Content-Length") Long contentLength,
                Context context);

        @Post("/mediatypes/analyzeNoAccept")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> analyzeBodyNoAcceptHeader(
                @HostParam("$host") String host, @BodyParam("application/json") SourcePath input, Context context);

        @Post("/mediatypes/contentTypeWithEncoding")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> contentTypeWithEncoding(
                @HostParam("$host") String host,
                @BodyParam("text/plain; charset=UTF-8") String input,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/mediatypes/binaryBodyTwoContentTypes")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> binaryBodyWithTwoContentTypes(
                @HostParam("$host") String host,
                @HeaderParam("Content-Type") ContentType1 contentType,
                @BodyParam("application/octet-stream") Flux<ByteBuffer> message,
                @HeaderParam("Content-Length") long contentLength,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/mediatypes/binaryBodyThreeContentTypes")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> binaryBodyWithThreeContentTypes(
                @HostParam("$host") String host,
                @HeaderParam("Content-Type") ContentType1 contentType,
                @BodyParam("application/octet-stream") Flux<ByteBuffer> message,
                @HeaderParam("Content-Length") long contentLength,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/mediatypes/binaryBodyThreeContentTypes")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> binaryBodyWithThreeContentTypes(
                @HostParam("$host") String host,
                @BodyParam("text/plain") String message,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/mediatypes/textAndJson")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> putTextAndJsonBody(
                @HostParam("$host") String host,
                @BodyParam("text/plain") String message,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/mediatypes/textAndJson")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> putTextAndJsonBodyApplicationJson(
                @HostParam("$host") String host,
                @BodyParam("application/json") String message,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Analyze body, that could be different media types.
     *
     * @param contentType Upload file type.
     * @param input Input parameter.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> analyzeBodyWithResponseAsync(
            ContentType contentType, Flux<ByteBuffer> input, Long contentLength) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (contentType == null) {
            return Mono.error(new IllegalArgumentException("Parameter contentType is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.analyzeBody(this.getHost(), contentType, input, contentLength, accept, context));
    }

    /**
     * Analyze body, that could be different media types.
     *
     * @param contentType Upload file type.
     * @param input Input parameter.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> analyzeBodyAsync(ContentType contentType, Flux<ByteBuffer> input, Long contentLength) {
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
     * @param contentType Upload file type.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> analyzeBodyAsync(ContentType contentType) {
        final Flux<ByteBuffer> input = null;
        final Long contentLength = null;
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
     * @param contentType Upload file type.
     * @param input Input parameter.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String analyzeBody(ContentType contentType, Flux<ByteBuffer> input, Long contentLength) {
        return analyzeBodyAsync(contentType, input, contentLength).block();
    }

    /**
     * Analyze body, that could be different media types.
     *
     * @param contentType Upload file type.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String analyzeBody(ContentType contentType) {
        final Flux<ByteBuffer> input = null;
        final Long contentLength = null;
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
        final String accept = "application/json";
        SourcePath inputInternal = null;
        if (source != null) {
            inputInternal = new SourcePath();
            inputInternal.setSource(source);
        }
        SourcePath input = inputInternal;
        return FluxUtil.withContext(context -> service.analyzeBody(this.getHost(), input, accept, context));
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
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> analyzeBodyAsync() {
        final String source = null;
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
     * Analyze body, that could be different media types.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String analyzeBody() {
        final String source = null;
        return analyzeBodyAsync(source).block();
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @param contentType Upload file type.
     * @param input Input parameter.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> analyzeBodyNoAcceptHeaderWithResponseAsync(
            ContentType contentType, Flux<ByteBuffer> input, Long contentLength) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (contentType == null) {
            return Mono.error(new IllegalArgumentException("Parameter contentType is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context ->
                        service.analyzeBodyNoAcceptHeader(this.getHost(), contentType, input, contentLength, context));
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @param contentType Upload file type.
     * @param input Input parameter.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> analyzeBodyNoAcceptHeaderAsync(
            ContentType contentType, Flux<ByteBuffer> input, Long contentLength) {
        return analyzeBodyNoAcceptHeaderWithResponseAsync(contentType, input, contentLength)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @param contentType Upload file type.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> analyzeBodyNoAcceptHeaderAsync(ContentType contentType) {
        final Flux<ByteBuffer> input = null;
        final Long contentLength = null;
        return analyzeBodyNoAcceptHeaderWithResponseAsync(contentType, input, contentLength)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @param contentType Upload file type.
     * @param input Input parameter.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void analyzeBodyNoAcceptHeader(ContentType contentType, Flux<ByteBuffer> input, Long contentLength) {
        analyzeBodyNoAcceptHeaderAsync(contentType, input, contentLength).block();
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @param contentType Upload file type.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void analyzeBodyNoAcceptHeader(ContentType contentType) {
        final Flux<ByteBuffer> input = null;
        final Long contentLength = null;
        analyzeBodyNoAcceptHeaderAsync(contentType, input, contentLength).block();
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @param source File source path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> analyzeBodyNoAcceptHeaderWithResponseAsync(String source) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        SourcePath inputInternal = null;
        if (source != null) {
            inputInternal = new SourcePath();
            inputInternal.setSource(source);
        }
        SourcePath input = inputInternal;
        return FluxUtil.withContext(context -> service.analyzeBodyNoAcceptHeader(this.getHost(), input, context));
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @param source File source path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> analyzeBodyNoAcceptHeaderAsync(String source) {
        return analyzeBodyNoAcceptHeaderWithResponseAsync(source).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> analyzeBodyNoAcceptHeaderAsync() {
        final String source = null;
        return analyzeBodyNoAcceptHeaderWithResponseAsync(source).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @param source File source path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void analyzeBodyNoAcceptHeader(String source) {
        analyzeBodyNoAcceptHeaderAsync(source).block();
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void analyzeBodyNoAcceptHeader() {
        final String source = null;
        analyzeBodyNoAcceptHeaderAsync(source).block();
    }

    /**
     * Pass in contentType 'text/plain; charset=UTF-8' to pass test. Value for input does not matter.
     *
     * @param input Input parameter.
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
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.contentTypeWithEncoding(this.getHost(), input, accept, context));
    }

    /**
     * Pass in contentType 'text/plain; charset=UTF-8' to pass test. Value for input does not matter.
     *
     * @param input Input parameter.
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
     * Pass in contentType 'text/plain; charset=UTF-8' to pass test. Value for input does not matter.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> contentTypeWithEncodingAsync() {
        final String input = null;
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
     * Pass in contentType 'text/plain; charset=UTF-8' to pass test. Value for input does not matter.
     *
     * @param input Input parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String contentTypeWithEncoding(String input) {
        return contentTypeWithEncodingAsync(input).block();
    }

    /**
     * Pass in contentType 'text/plain; charset=UTF-8' to pass test. Value for input does not matter.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String contentTypeWithEncoding() {
        final String input = null;
        return contentTypeWithEncodingAsync(input).block();
    }

    /**
     * Binary body with two content types. Pass in of {'hello': 'world'} for the application/json content type, and a
     * byte stream of 'hello, world!' for application/octet-stream.
     *
     * @param contentType Upload file type.
     * @param message The payload body.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> binaryBodyWithTwoContentTypesWithResponseAsync(
            ContentType1 contentType, Flux<ByteBuffer> message, long contentLength) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (contentType == null) {
            return Mono.error(new IllegalArgumentException("Parameter contentType is required and cannot be null."));
        }
        if (message == null) {
            return Mono.error(new IllegalArgumentException("Parameter message is required and cannot be null."));
        }
        final String accept = "text/plain";
        return FluxUtil.withContext(
                context ->
                        service.binaryBodyWithTwoContentTypes(
                                this.getHost(), contentType, message, contentLength, accept, context));
    }

    /**
     * Binary body with two content types. Pass in of {'hello': 'world'} for the application/json content type, and a
     * byte stream of 'hello, world!' for application/octet-stream.
     *
     * @param contentType Upload file type.
     * @param message The payload body.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> binaryBodyWithTwoContentTypesAsync(
            ContentType1 contentType, Flux<ByteBuffer> message, long contentLength) {
        return binaryBodyWithTwoContentTypesWithResponseAsync(contentType, message, contentLength)
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
     * Binary body with two content types. Pass in of {'hello': 'world'} for the application/json content type, and a
     * byte stream of 'hello, world!' for application/octet-stream.
     *
     * @param contentType Upload file type.
     * @param message The payload body.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String binaryBodyWithTwoContentTypes(
            ContentType1 contentType, Flux<ByteBuffer> message, long contentLength) {
        return binaryBodyWithTwoContentTypesAsync(contentType, message, contentLength).block();
    }

    /**
     * Binary body with three content types. Pass in string 'hello, world' with content type 'text/plain', {'hello':
     * world'} with content type 'application/json' and a byte string for 'application/octet-stream'.
     *
     * @param contentType Upload file type.
     * @param message The payload body.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> binaryBodyWithThreeContentTypesWithResponseAsync(
            ContentType1 contentType, Flux<ByteBuffer> message, long contentLength) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (contentType == null) {
            return Mono.error(new IllegalArgumentException("Parameter contentType is required and cannot be null."));
        }
        if (message == null) {
            return Mono.error(new IllegalArgumentException("Parameter message is required and cannot be null."));
        }
        final String accept = "text/plain";
        return FluxUtil.withContext(
                context ->
                        service.binaryBodyWithThreeContentTypes(
                                this.getHost(), contentType, message, contentLength, accept, context));
    }

    /**
     * Binary body with three content types. Pass in string 'hello, world' with content type 'text/plain', {'hello':
     * world'} with content type 'application/json' and a byte string for 'application/octet-stream'.
     *
     * @param contentType Upload file type.
     * @param message The payload body.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> binaryBodyWithThreeContentTypesAsync(
            ContentType1 contentType, Flux<ByteBuffer> message, long contentLength) {
        return binaryBodyWithThreeContentTypesWithResponseAsync(contentType, message, contentLength)
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
     * Binary body with three content types. Pass in string 'hello, world' with content type 'text/plain', {'hello':
     * world'} with content type 'application/json' and a byte string for 'application/octet-stream'.
     *
     * @param contentType Upload file type.
     * @param message The payload body.
     * @param contentLength The contentLength parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String binaryBodyWithThreeContentTypes(
            ContentType1 contentType, Flux<ByteBuffer> message, long contentLength) {
        return binaryBodyWithThreeContentTypesAsync(contentType, message, contentLength).block();
    }

    /**
     * Binary body with three content types. Pass in string 'hello, world' with content type 'text/plain', {'hello':
     * world'} with content type 'application/json' and a byte string for 'application/octet-stream'.
     *
     * @param message The payload body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> binaryBodyWithThreeContentTypesWithResponseAsync(String message) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (message == null) {
            return Mono.error(new IllegalArgumentException("Parameter message is required and cannot be null."));
        }
        final String accept = "text/plain";
        return FluxUtil.withContext(
                context -> service.binaryBodyWithThreeContentTypes(this.getHost(), message, accept, context));
    }

    /**
     * Binary body with three content types. Pass in string 'hello, world' with content type 'text/plain', {'hello':
     * world'} with content type 'application/json' and a byte string for 'application/octet-stream'.
     *
     * @param message The payload body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> binaryBodyWithThreeContentTypesAsync(String message) {
        return binaryBodyWithThreeContentTypesWithResponseAsync(message)
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
     * Binary body with three content types. Pass in string 'hello, world' with content type 'text/plain', {'hello':
     * world'} with content type 'application/json' and a byte string for 'application/octet-stream'.
     *
     * @param message The payload body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String binaryBodyWithThreeContentTypes(String message) {
        return binaryBodyWithThreeContentTypesAsync(message).block();
    }

    /**
     * Body that's either text/plain or application/json.
     *
     * @param message The payload body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putTextAndJsonBodyWithResponseAsync(String message) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (message == null) {
            return Mono.error(new IllegalArgumentException("Parameter message is required and cannot be null."));
        }
        final String accept = "text/plain";
        return FluxUtil.withContext(context -> service.putTextAndJsonBody(this.getHost(), message, accept, context));
    }

    /**
     * Body that's either text/plain or application/json.
     *
     * @param message The payload body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putTextAndJsonBodyAsync(String message) {
        return putTextAndJsonBodyWithResponseAsync(message)
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
     * Body that's either text/plain or application/json.
     *
     * @param message The payload body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String putTextAndJsonBody(String message) {
        return putTextAndJsonBodyAsync(message).block();
    }

    /**
     * Body that's either text/plain or application/json.
     *
     * @param message The payload body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putTextAndJsonBodyApplicationJsonWithResponseAsync(String message) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (message == null) {
            return Mono.error(new IllegalArgumentException("Parameter message is required and cannot be null."));
        }
        final String accept = "text/plain";
        return FluxUtil.withContext(
                context -> service.putTextAndJsonBodyApplicationJson(this.getHost(), message, accept, context));
    }

    /**
     * Body that's either text/plain or application/json.
     *
     * @param message The payload body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putTextAndJsonBodyApplicationJsonAsync(String message) {
        return putTextAndJsonBodyApplicationJsonWithResponseAsync(message)
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
     * Body that's either text/plain or application/json.
     *
     * @param message The payload body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String putTextAndJsonBodyApplicationJson(String message) {
        return putTextAndJsonBodyApplicationJsonAsync(message).block();
    }
}
