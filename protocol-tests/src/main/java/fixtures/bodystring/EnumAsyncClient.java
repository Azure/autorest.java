// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodystring;

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
import fixtures.bodystring.implementation.EnumsImpl;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous AutoRestSwaggerBatServiceClient type.
 */
@ServiceClient(builder = EnumClientBuilder.class, isAsync = true)
public final class EnumAsyncClient {
    @Generated
    private final EnumsImpl serviceClient;

    /**
     * Initializes an instance of EnumAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    EnumAsyncClient(EnumsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color' along with
     * {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNotExpandableWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNotExpandableWithResponseAsync(requestOptions);
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     * 
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNotExpandableWithResponse(BinaryData stringBody, RequestOptions requestOptions) {
        return this.serviceClient.putNotExpandableWithResponseAsync(stringBody, requestOptions);
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color' along with
     * {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getReferencedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getReferencedWithResponseAsync(requestOptions);
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     * 
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedWithResponse(BinaryData enumStringBody, RequestOptions requestOptions) {
        return this.serviceClient.putReferencedWithResponseAsync(enumStringBody, requestOptions);
    }

    /**
     * Get value 'green-color' from the constant.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     ColorConstant: String (Required)
     *     field1: String (Optional)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return value 'green-color' from the constant along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getReferencedConstantWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getReferencedConstantWithResponseAsync(requestOptions);
    }

    /**
     * Sends value 'green-color' from a constant.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     ColorConstant: String (Required)
     *     field1: String (Optional)
     * }
     * }</pre>
     * 
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedConstantWithResponse(BinaryData enumStringBody,
        RequestOptions requestOptions) {
        return this.serviceClient.putReferencedConstantWithResponseAsync(enumStringBody, requestOptions);
    }
}
