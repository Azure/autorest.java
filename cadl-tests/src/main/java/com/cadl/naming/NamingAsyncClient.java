// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.naming;

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
import com.cadl.naming.implementation.NamingOpsImpl;
import com.cadl.naming.models.DataResponse;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous NamingClient type. */
@ServiceClient(builder = NamingClientBuilder.class, isAsync = true)
public final class NamingAsyncClient {
    @Generated private final NamingOpsImpl serviceClient;

    /**
     * Initializes an instance of NamingAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    NamingAsyncClient(NamingOpsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * summary of POST op
     *
     * <p>description of POST op.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>etag</td><td>String</td><td>No</td><td>summary of etag header parameter
     *
     * description of etag header parameter</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addHeader}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     name: String (Required)
     *     data (Required): {
     *         data: byte[] (Required)
     *     }
     *     type: String(Blob/File) (Required)
     *     status: String(Running/Completed/Failed) (Required)
     * }
     * }</pre>
     *
     * @param name summary of name query parameter
     *     <p>description of name query parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return summary of Response along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> postWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.postWithResponseAsync(name, requestOptions);
    }

    /**
     * summary of POST op
     *
     * <p>description of POST op.
     *
     * @param name summary of name query parameter
     *     <p>description of name query parameter.
     * @param etag summary of etag header parameter
     *     <p>description of etag header parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return summary of Response on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DataResponse> post(String name, String etag) {
        // Generated convenience method for postWithResponse

        RequestOptions requestOptions = new RequestOptions();
        if (etag != null) {
            requestOptions.setHeader("etag", etag);
        }
        return postWithResponse(name, requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(DataResponse.class));
    }

    /**
     * summary of POST op
     *
     * <p>description of POST op.
     *
     * @param name summary of name query parameter
     *     <p>description of name query parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return summary of Response on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DataResponse> post(String name) {
        // Generated convenience method for postWithResponse

        RequestOptions requestOptions = new RequestOptions();
        return postWithResponse(name, requestOptions)
                .map(Response::getValue)
                .map(protocolMethodData -> protocolMethodData.toObject(DataResponse.class));
    }
}
