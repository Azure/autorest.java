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
import fixtures.httpinfrastructure.implementation.HttpClientFailuresImpl;

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class)
public final class HttpClientFailureClient {
    @Generated private final HttpClientFailuresImpl serviceClient;

    /**
     * Initializes an instance of HttpClientFailures client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    HttpClientFailureClient(HttpClientFailuresImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head400WithResponse(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get400WithResponse(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put400WithResponse(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch400WithResponse(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post400WithResponse(requestOptions);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete400WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete400WithResponse(requestOptions);
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head401WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head401WithResponse(requestOptions);
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get402WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get402WithResponse(requestOptions);
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get403WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get403WithResponse(requestOptions);
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put404WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put404WithResponse(requestOptions);
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch405WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch405WithResponse(requestOptions);
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post406WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post406WithResponse(requestOptions);
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete407WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete407WithResponse(requestOptions);
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put409WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put409WithResponse(requestOptions);
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head410WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head410WithResponse(requestOptions);
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get411WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get411WithResponse(requestOptions);
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get412WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get412WithResponse(requestOptions);
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put413WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put413WithResponse(requestOptions);
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch414WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch414WithResponse(requestOptions);
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post415WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post415WithResponse(requestOptions);
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get416WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get416WithResponse(requestOptions);
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete417WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete417WithResponse(requestOptions);
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head429WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head429WithResponse(requestOptions);
    }
}
