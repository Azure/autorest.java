// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodystring.codeupdate;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodystring.codeupdate.implementation.EnumsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestSwaggerBatService type. */
@ServiceClient(builder = AutoRestSwaggerBatServiceBuilder.class, isAsync = true)
public final class EnumAsyncClient {
    @Generated private final EnumsImpl serviceClient;

    /**
     * Initializes an instance of Enums client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    EnumAsyncClient(EnumsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> getNotExpandableWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNotExpandableWithResponseAsync(requestOptions);
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the completion.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNotExpandableWithResponse(BinaryData stringBody, RequestOptions requestOptions) {
        return this.serviceClient.putNotExpandableWithResponseAsync(stringBody, requestOptions);
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> getReferencedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getReferencedWithResponseAsync(requestOptions);
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the completion.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedWithResponse(BinaryData enumStringBody, RequestOptions requestOptions) {
        return this.serviceClient.putReferencedWithResponseAsync(enumStringBody, requestOptions);
    }

    /**
     * Get value 'green-color' from the constant.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     colorConstant: String
     *     field1: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return value 'green-color' from the constant.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getReferencedConstantWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getReferencedConstantWithResponseAsync(requestOptions);
    }

    /**
     * Sends value 'green-color' from a constant.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     colorConstant: String
     *     field1: String
     * }
     * }</pre>
     *
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the completion.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedConstantWithResponse(
            BinaryData enumStringBody, RequestOptions requestOptions) {
        return this.serviceClient.putReferencedConstantWithResponseAsync(enumStringBody, requestOptions);
    }
}
