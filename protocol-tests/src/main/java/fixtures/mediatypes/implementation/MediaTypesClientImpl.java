// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.mediatypes.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the MediaTypesClient type. */
public final class MediaTypesClientImpl {
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
    public MediaTypesClientImpl(String host) {
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
    public MediaTypesClientImpl(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of MediaTypesClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    public MediaTypesClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
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
        Mono<Response<BinaryData>> analyzeBody(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/mediatypes/analyzeNoAccept")
        Mono<Response<Void>> analyzeBodyNoAcceptHeader(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/mediatypes/contentTypeWithEncoding")
        Mono<Response<BinaryData>> contentTypeWithEncoding(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/mediatypes/binaryBodyTwoContentTypes")
        Mono<Response<BinaryData>> binaryBodyWithTwoContentTypes(
                @HostParam("$host") String host,
                @BodyParam("application/octet-stream") BinaryData message,
                RequestOptions requestOptions,
                Context context);

        @Post("/mediatypes/binaryBodyThreeContentTypes")
        Mono<Response<BinaryData>> binaryBodyWithThreeContentTypes(
                @HostParam("$host") String host,
                @BodyParam("application/octet-stream") BinaryData message,
                RequestOptions requestOptions,
                Context context);

        @Post("/mediatypes/textAndJson")
        Mono<Response<BinaryData>> putTextAndJsonBody(
                @HostParam("$host") String host,
                @BodyParam("text/plain") BinaryData message,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Analyze body, that could be different media types.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> analyzeBodyWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.analyzeBody(this.getHost(), requestOptions, context));
    }

    /**
     * Analyze body, that could be different media types.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> analyzeBodyWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.analyzeBody(this.getHost(), requestOptions, context);
    }

    /**
     * Analyze body, that could be different media types.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> analyzeBodyWithResponse(RequestOptions requestOptions) {
        return analyzeBodyWithResponseAsync(requestOptions).block();
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> analyzeBodyNoAcceptHeaderWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.analyzeBodyNoAcceptHeader(this.getHost(), requestOptions, context));
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> analyzeBodyNoAcceptHeaderWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.analyzeBodyNoAcceptHeader(this.getHost(), requestOptions, context);
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> analyzeBodyNoAcceptHeaderWithResponse(RequestOptions requestOptions) {
        return analyzeBodyNoAcceptHeaderWithResponseAsync(requestOptions).block();
    }

    /**
     * Pass in contentType 'text/plain; encoding=UTF-8' to pass test. Value for input does not matter.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> contentTypeWithEncodingWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.contentTypeWithEncoding(this.getHost(), requestOptions, context));
    }

    /**
     * Pass in contentType 'text/plain; encoding=UTF-8' to pass test. Value for input does not matter.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> contentTypeWithEncodingWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.contentTypeWithEncoding(this.getHost(), requestOptions, context);
    }

    /**
     * Pass in contentType 'text/plain; encoding=UTF-8' to pass test. Value for input does not matter.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> contentTypeWithEncodingWithResponse(RequestOptions requestOptions) {
        return contentTypeWithEncodingWithResponseAsync(requestOptions).block();
    }

    /**
     * Binary body with two content types. Pass in of {'hello': 'world'} for the application/json content type, and a
     * byte stream of 'hello, world!' for application/octet-stream.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> binaryBodyWithTwoContentTypesWithResponseAsync(
            BinaryData message, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.binaryBodyWithTwoContentTypes(this.getHost(), message, requestOptions, context));
    }

    /**
     * Binary body with two content types. Pass in of {'hello': 'world'} for the application/json content type, and a
     * byte stream of 'hello, world!' for application/octet-stream.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> binaryBodyWithTwoContentTypesWithResponseAsync(
            BinaryData message, RequestOptions requestOptions, Context context) {
        return service.binaryBodyWithTwoContentTypes(this.getHost(), message, requestOptions, context);
    }

    /**
     * Binary body with two content types. Pass in of {'hello': 'world'} for the application/json content type, and a
     * byte stream of 'hello, world!' for application/octet-stream.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> binaryBodyWithTwoContentTypesWithResponse(
            BinaryData message, RequestOptions requestOptions) {
        return binaryBodyWithTwoContentTypesWithResponseAsync(message, requestOptions).block();
    }

    /**
     * Binary body with three content types. Pass in string 'hello, world' with content type 'text/plain', {'hello':
     * world'} with content type 'application/json' and a byte string for 'application/octet-stream'.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> binaryBodyWithThreeContentTypesWithResponseAsync(
            BinaryData message, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.binaryBodyWithThreeContentTypes(this.getHost(), message, requestOptions, context));
    }

    /**
     * Binary body with three content types. Pass in string 'hello, world' with content type 'text/plain', {'hello':
     * world'} with content type 'application/json' and a byte string for 'application/octet-stream'.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> binaryBodyWithThreeContentTypesWithResponseAsync(
            BinaryData message, RequestOptions requestOptions, Context context) {
        return service.binaryBodyWithThreeContentTypes(this.getHost(), message, requestOptions, context);
    }

    /**
     * Binary body with three content types. Pass in string 'hello, world' with content type 'text/plain', {'hello':
     * world'} with content type 'application/json' and a byte string for 'application/octet-stream'.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>contentLength</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> binaryBodyWithThreeContentTypesWithResponse(
            BinaryData message, RequestOptions requestOptions) {
        return binaryBodyWithThreeContentTypesWithResponseAsync(message, requestOptions).block();
    }

    /**
     * Body that's either text/plain or application/json.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putTextAndJsonBodyWithResponseAsync(
            BinaryData message, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putTextAndJsonBody(this.getHost(), message, requestOptions, context));
    }

    /**
     * Body that's either text/plain or application/json.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putTextAndJsonBodyWithResponseAsync(
            BinaryData message, RequestOptions requestOptions, Context context) {
        return service.putTextAndJsonBody(this.getHost(), message, requestOptions, context);
    }

    /**
     * Body that's either text/plain or application/json.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String
     * }</pre>
     *
     * @param message The payload body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> putTextAndJsonBodyWithResponse(BinaryData message, RequestOptions requestOptions) {
        return putTextAndJsonBodyWithResponseAsync(message, requestOptions).block();
    }
}
