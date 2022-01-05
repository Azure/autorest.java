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
import fixtures.httpinfrastructure.implementation.HttpRetriesImpl;

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestServiceClient type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceClientBuilder.class)
public final class HttpRetryClient {
    @Generated private final HttpRetriesImpl serviceClient;

    /**
     * Initializes an instance of HttpRetries client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    HttpRetryClient(HttpRetriesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head408WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head408WithResponse(requestOptions);
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put500WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put500WithResponse(requestOptions);
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch500WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch500WithResponse(requestOptions);
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get502WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get502WithResponse(requestOptions);
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return simple boolean along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> options502WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.options502WithResponse(requestOptions);
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post503WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post503WithResponse(requestOptions);
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete503WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete503WithResponse(requestOptions);
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put504WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put504WithResponse(requestOptions);
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch504WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch504WithResponse(requestOptions);
    }
}
