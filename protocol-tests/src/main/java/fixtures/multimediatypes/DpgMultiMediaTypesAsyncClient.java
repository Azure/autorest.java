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
     * @param contentType Upload file type. Allowed values: "application/pdf", "image/jpeg", "image/png", "image/tiff".
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
            String contentType, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndBinaryTypesJsonFirstWithResponseAsync(contentType, input, requestOptions);
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
     * @param contentType Upload file type. Allowed values: "application/pdf", "image/jpeg", "image/png", "image/tiff".
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
            String contentType, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndBinaryTypesBinaryFirstWithResponseAsync(contentType, input, requestOptions);
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
     * @param contentType Upload file type. Allowed values: "application/json", "application/octet-stream".
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
            String contentType, BinaryData message, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndOctectWithJsonFirstWithResponseAsync(contentType, message, requestOptions);
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
     * @param contentType Upload file type. Allowed values: "application/json", "application/octet-stream".
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
            String contentType, BinaryData message, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndOctectWithOctetFirstWithResponseAsync(contentType, message, requestOptions);
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
     * @param contentType Upload file type. Allowed values: "application/json", "text/plain; charset=UTF-8".
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
            String contentType, BinaryData message, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndEncodingWithJsonFirstWithResponseAsync(contentType, message, requestOptions);
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
     * @param contentType Upload file type. Allowed values: "application/json", "text/plain; charset=UTF-8".
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
     * @param contentType Upload file type. Allowed values: "application/json", "text/plain".
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
     * @param contentType Upload file type. Allowed values: "application/json", "text/plain".
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

    /**
     * json and image types with json type first.
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
     * @param contentType Upload file type. Allowed values: "application/json", "image/jpeg".
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
    public Mono<Response<BinaryData>> jsonAndImageWithJsonFirstWithResponse(
            String contentType, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndImageWithJsonFirstWithResponseAsync(contentType, input, requestOptions);
    }

    /**
     * json and image types with json type first.
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
     * @param contentType The content type. Allowed values: "application/json", "image/jpeg".
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
    public Mono<Response<BinaryData>> jsonAndImageObjectTypeWithJsonFirstWithResponse(
            String contentType, BinaryData input, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndImageObjectTypeWithJsonFirstWithResponseAsync(
                contentType, input, requestOptions);
    }

    /**
     * image type with non-required body.
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
    public Mono<Response<BinaryData>> imageTypeWithNonRequiredBodyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.imageTypeWithNonRequiredBodyWithResponseAsync(requestOptions);
    }

    /**
     * json and image types with non-required body and json type first.
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
     * @param contentType Upload file type. Allowed values: "application/json", "image/jpeg".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> jsonAndImageBinaryTypeJsonFirstWithNonRequiredBodyWithResponse(
            String contentType, RequestOptions requestOptions) {
        return this.serviceClient.jsonAndImageBinaryTypeJsonFirstWithNonRequiredBodyWithResponseAsync(
                contentType, requestOptions);
    }

    /**
     * json and image types with non-required body and json type first.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>Content-Type</td><td>String</td><td>No</td><td>The content type. Allowed values: "application/json", "image/jpeg".</td></tr>
     * </table>
     *
     * You can add these to a request with {@code new RequestOptions.addHeader()}
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
    public Mono<Response<BinaryData>> jsonAndImageObjectTypeJsonFirstWithNonRequiredBodyWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.jsonAndImageObjectTypeJsonFirstWithNonRequiredBodyWithResponseAsync(requestOptions);
    }
}
