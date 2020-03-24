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
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.mediatypes.models.ContentType;
import fixtures.mediatypes.models.SourcePath;
import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the MediaTypesClient type.
 */
public final class MediaTypesClient {
    /**
     * The proxy service used to perform REST calls.
     */
    private MediaTypesClientService service;

    /**
     * server parameter.
     */
    private String host;

    /**
     * Gets server parameter.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets server parameter.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    MediaTypesClient setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * Initializes an instance of MediaTypesClient client.
     */
    public MediaTypesClient() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of MediaTypesClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public MediaTypesClient(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.service = RestProxy.create(MediaTypesClientService.class, this.httpPipeline);
    }

    /**
     * The interface defining all the services for MediaTypesClient to be used
     * by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "MediaTypesClient")
    private interface MediaTypesClientService {
        @Post("/mediatypes/analyze")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<String>> analyzeBody(@HostParam("$host") String host, @HeaderParam("Content-Type") ContentType contentType, @BodyParam("application/octet-stream") Flux<ByteBuffer> input, @HeaderParam("Content-Length") Long contentLength, Context context);

        @Post("/mediatypes/analyze")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<String>> analyzeBody(@HostParam("$host") String host, @BodyParam("application/json") SourcePath input, Context context);
    }

    /**
     * Analyze body, that could be different media types.
     * 
     * @param contentType Content type for upload.
     * @param input Uri or local path to source data.
     * @param contentLength null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> analyzeBodyWithResponseAsync(ContentType contentType, Flux<ByteBuffer> input, Long contentLength) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.analyzeBody(this.getHost(), contentType, input, contentLength, context));
    }

    /**
     * Analyze body, that could be different media types.
     * 
     * @param contentType Content type for upload.
     * @param input Uri or local path to source data.
     * @param contentLength null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> analyzeBodyAsync(ContentType contentType, Flux<ByteBuffer> input, Long contentLength) {
        return analyzeBodyWithResponseAsync(contentType, input, contentLength)
            .flatMap((SimpleResponse<String> res) -> {
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
     * @param contentLength null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String analyzeBody(ContentType contentType, Flux<ByteBuffer> input, Long contentLength) {
        return analyzeBodyAsync(contentType, input, contentLength).block();
    }

    /**
     * Analyze body, that could be different media types.
     * 
     * @param source File source path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<String>> analyzeBodyWithResponseAsync(String source) {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> analyzeBodyAsync(String source) {
        return analyzeBodyWithResponseAsync(source)
            .flatMap((SimpleResponse<String> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String analyzeBody(String source) {
        return analyzeBodyAsync(source).block();
    }
}
