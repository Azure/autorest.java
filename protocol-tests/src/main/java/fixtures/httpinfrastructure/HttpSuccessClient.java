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
import fixtures.httpinfrastructure.implementation.HttpSuccessImpl;

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class)
public final class HttpSuccessClient {
    @Generated private final HttpSuccessImpl serviceClient;

    /**
     * Initializes an instance of HttpSuccess client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    HttpSuccessClient(HttpSuccessImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Return 200 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head200WithResponse(requestOptions);
    }

    /**
     * Get 200 success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return 200 success.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> get200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get200WithResponse(requestOptions);
    }

    /**
     * Options 200 success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return simple boolean.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> options200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.options200WithResponse(requestOptions);
    }

    /**
     * Put boolean value true returning 200 success.
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
    public Response<Void> put200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put200WithResponse(requestOptions);
    }

    /**
     * Patch true Boolean value in request returning 200.
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
    public Response<Void> patch200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch200WithResponse(requestOptions);
    }

    /**
     * Post bollean value true in request that returns a 200.
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
    public Response<Void> post200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post200WithResponse(requestOptions);
    }

    /**
     * Delete simple boolean value true returns 200.
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
    public Response<Void> delete200WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete200WithResponse(requestOptions);
    }

    /**
     * Put true Boolean value in request returns 201.
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
    public Response<Void> put201WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put201WithResponse(requestOptions);
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
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
    public Response<Void> post201WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post201WithResponse(requestOptions);
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
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
    public Response<Void> put202WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put202WithResponse(requestOptions);
    }

    /**
     * Patch true Boolean value in request returns 202.
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
    public Response<Void> patch202WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch202WithResponse(requestOptions);
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
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
    public Response<Void> post202WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post202WithResponse(requestOptions);
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
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
    public Response<Void> delete202WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete202WithResponse(requestOptions);
    }

    /**
     * Return 204 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head204WithResponse(requestOptions);
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
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
    public Response<Void> put204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.put204WithResponse(requestOptions);
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
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
    public Response<Void> patch204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.patch204WithResponse(requestOptions);
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
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
    public Response<Void> post204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.post204WithResponse(requestOptions);
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
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
    public Response<Void> delete204WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.delete204WithResponse(requestOptions);
    }

    /**
     * Return 404 status code.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> head404WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.head404WithResponse(requestOptions);
    }
}
