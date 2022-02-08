// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.httpinfrastructure;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.httpinfrastructure.implementation.HttpServerFailuresImpl;

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestServiceClient type. */
@ServiceClient(builder = HttpServerFailureClientBuilder.class)
public final class HttpServerFailureClient {
    @Generated private final HttpServerFailuresImpl serviceClient;

    /**
     * Initializes an instance of HttpServerFailures client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    HttpServerFailureClient(HttpServerFailuresImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Return 501 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head501WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head501WithResponse(requestOptions);
    }

    /**
     * Return 501 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get501WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get501WithResponse(requestOptions);
    }

    /**
     * Return 505 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post505WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post505WithResponse(requestOptions);
    }

    /**
     * Return 505 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete505WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete505WithResponse(requestOptions);
    }
}
