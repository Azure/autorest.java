// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.mediatypes;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.mediatypes.implementation.MediaTypesClientImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous MediaTypesClient type. */
@ServiceClient(builder = MediaTypesClientBuilder.class, isAsync = true)
public final class MediaTypesAsyncClient {
    @Generated private final MediaTypesClientImpl serviceClient;

    /**
     * Initializes an instance of MediaTypesAsyncClient client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    MediaTypesAsyncClient(MediaTypesClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Analyze body, that could be different media types.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>Content-Type</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>Content-Length</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
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
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> analyzeBodyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.analyzeBodyWithResponseAsync(requestOptions);
    }

    /**
     * Analyze body, that could be different media types. Adds to AnalyzeBody by not having an accept type.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>Content-Type</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>Content-Length</td><td>String</td><td>No</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> analyzeBodyNoAcceptHeaderWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.analyzeBodyNoAcceptHeaderWithResponseAsync(requestOptions);
    }

    /**
     * Pass in contentType 'text/plain; charset=UTF-8' to pass test. Value for input does not matter.
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
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> contentTypeWithEncodingWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.contentTypeWithEncodingWithResponseAsync(requestOptions);
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
     *     <tr><td>Content-Type</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>Content-Length</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
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
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> binaryBodyWithTwoContentTypesWithResponse(
            BinaryData message, RequestOptions requestOptions) {
        return this.serviceClient.binaryBodyWithTwoContentTypesWithResponseAsync(message, requestOptions);
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
     *     <tr><td>Content-Type</td><td>String</td><td>Yes</td><td>Upload file type</td></tr>
     *     <tr><td>Content-Length</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
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
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> binaryBodyWithThreeContentTypesWithResponse(
            BinaryData message, RequestOptions requestOptions) {
        return this.serviceClient.binaryBodyWithThreeContentTypesWithResponseAsync(message, requestOptions);
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
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putTextAndJsonBodyWithResponse(
            BinaryData message, RequestOptions requestOptions) {
        return this.serviceClient.putTextAndJsonBodyWithResponseAsync(message, requestOptions);
    }
}
