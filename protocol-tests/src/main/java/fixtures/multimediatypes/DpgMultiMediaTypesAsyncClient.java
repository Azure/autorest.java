// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.multimediatypes;

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
import fixtures.multimediatypes.implementation.DpgMultiMediaTypesClientImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous DpgMultiMediaTypesClient type. */
@ServiceClient(builder = DpgMultiMediaTypesClientBuilder.class, isAsync = true)
public final class DpgMultiMediaTypesAsyncClient {
    @Generated private final DpgMultiMediaTypesClientImpl serviceClient;

    /**
     * Initializes an instance of DpgMultiMediaTypesAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    DpgMultiMediaTypesAsyncClient(DpgMultiMediaTypesClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * json and binary types with json type first.
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
     * @param contentType Upload file type.
     * @param contentLength The contentLength parameter.
     * @param input Input parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> jsonAndBinaryTypesJsonFirstWithResponse(
            String contentType, long contentLength, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndBinaryTypesJsonFirstWithResponseAsync(
                contentType, contentLength, input, requestOptions);
    }

    /**
     * json and binary types with binary type first.
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
     * @param contentType Upload file type.
     * @param contentLength The contentLength parameter.
     * @param input Input parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> jsonAndBinaryTypesBinaryFirstWithResponse(
            String contentType, long contentLength, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndBinaryTypesBinaryFirstWithResponseAsync(
                contentType, contentLength, input, requestOptions);
    }

    /**
     * json and octet media types with json type first.
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
     * @param contentType Upload file type.
     * @param contentLength The contentLength parameter.
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
    public Mono<Response<BinaryData>> jsonAndOctectWithJsonFirstWithResponse(
            String contentType, long contentLength, BinaryData message, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndOctectWithJsonFirstWithResponseAsync(
                contentType, contentLength, message, requestOptions);
    }

    /**
     * json and octet media types with octet type first.
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
     * @param contentType Upload file type.
     * @param contentLength The contentLength parameter.
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
    public Mono<Response<BinaryData>> jsonAndOctectWithOctetFirstWithResponse(
            String contentType, long contentLength, BinaryData message, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndOctectWithOctetFirstWithResponseAsync(
                contentType, contentLength, message, requestOptions);
    }

    /**
     * json and encoding types with json type first.
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
     * @param contentType Upload file type.
     * @param contentLength The contentLength parameter.
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
    public Mono<Response<BinaryData>> jsonAndEncodingWithJsonFirstWithResponse(
            String contentType, long contentLength, BinaryData message, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndEncodingWithJsonFirstWithResponseAsync(
                contentType, contentLength, message, requestOptions);
    }

    /**
     * json and encoding types with encoding type first.
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
     * @param contentType Upload file type.
     * @param input Input parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> jsonAndEncodingWithEncodingFirstWithResponse(
            String contentType, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndEncodingWithEncodingFirstWithResponseAsync(contentType, input, requestOptions);
    }

    /**
     * json and text types with text type first.
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
     * @param contentType Upload file type.
     * @param input Input parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> jsonAndTextWithTextFirstWithResponse(
            String contentType, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndTextWithTextFirstWithResponseAsync(contentType, input, requestOptions);
    }

    /**
     * json and text types with json type first.
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
     * @param contentType Upload file type.
     * @param input Input parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> jsonAndTextWithJsonFirstWithResponse(
            String contentType, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndTextWithJsonFirstWithResponseAsync(contentType, input, requestOptions);
    }
}
