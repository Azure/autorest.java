// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.constants;

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
import fixtures.constants.implementation.ContantsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestSwaggerConstantServiceClient type. */
@ServiceClient(builder = AutoRestSwaggerConstantServiceClientBuilder.class, isAsync = true)
public final class AutoRestSwaggerConstantServiceAsyncClient {
    @Generated private final ContantsImpl serviceClient;

    /**
     * Initializes an instance of AutoRestSwaggerConstantServiceAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    AutoRestSwaggerConstantServiceAsyncClient(ContantsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Puts constants to the testserver.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>input</td><td>String</td><td>No</td><td>The input parameter. Allowed values: "value1", "value2".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
    public Mono<Response<Void>> putNoModelAsStringNoRequiredTwoValueNoDefaultWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.putNoModelAsStringNoRequiredTwoValueNoDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>input</td><td>String</td><td>No</td><td>The input parameter. Allowed values: "value1", "value2".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
    public Mono<Response<Void>> putNoModelAsStringNoRequiredTwoValueDefaultWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putNoModelAsStringNoRequiredTwoValueDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
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
    public Mono<Response<Void>> putNoModelAsStringNoRequiredOneValueNoDefaultWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.putNoModelAsStringNoRequiredOneValueNoDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
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
    public Mono<Response<Void>> putNoModelAsStringNoRequiredOneValueDefaultWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putNoModelAsStringNoRequiredOneValueDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * @param input The input parameter. Allowed values: "value1", "value2".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNoModelAsStringRequiredTwoValueNoDefaultWithResponse(
            String input, RequestOptions requestOptions) {
        return this.serviceClient.putNoModelAsStringRequiredTwoValueNoDefaultWithResponseAsync(input, requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * @param input The input parameter. Allowed values: "value1", "value2".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNoModelAsStringRequiredTwoValueDefaultWithResponse(
            String input, RequestOptions requestOptions) {
        return this.serviceClient.putNoModelAsStringRequiredTwoValueDefaultWithResponseAsync(input, requestOptions);
    }

    /**
     * Puts constants to the testserver.
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
    public Mono<Response<Void>> putNoModelAsStringRequiredOneValueNoDefaultWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putNoModelAsStringRequiredOneValueNoDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
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
    public Mono<Response<Void>> putNoModelAsStringRequiredOneValueDefaultWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putNoModelAsStringRequiredOneValueDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>input</td><td>String</td><td>No</td><td>The input parameter. Allowed values: "value1", "value2".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
    public Mono<Response<Void>> putModelAsStringNoRequiredTwoValueNoDefaultWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putModelAsStringNoRequiredTwoValueNoDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>input</td><td>String</td><td>No</td><td>The input parameter. Allowed values: "value1", "value2".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
    public Mono<Response<Void>> putModelAsStringNoRequiredTwoValueDefaultWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putModelAsStringNoRequiredTwoValueDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>input</td><td>String</td><td>No</td><td>The input parameter. Allowed values: "value1".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
    public Mono<Response<Void>> putModelAsStringNoRequiredOneValueNoDefaultWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putModelAsStringNoRequiredOneValueNoDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>input</td><td>String</td><td>No</td><td>The input parameter. Allowed values: "value1".</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
    public Mono<Response<Void>> putModelAsStringNoRequiredOneValueDefaultWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putModelAsStringNoRequiredOneValueDefaultWithResponseAsync(requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * @param input The input parameter. Allowed values: "value1", "value2".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putModelAsStringRequiredTwoValueNoDefaultWithResponse(
            String input, RequestOptions requestOptions) {
        return this.serviceClient.putModelAsStringRequiredTwoValueNoDefaultWithResponseAsync(input, requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * @param input The input parameter. Allowed values: "value1", "value2".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putModelAsStringRequiredTwoValueDefaultWithResponse(
            String input, RequestOptions requestOptions) {
        return this.serviceClient.putModelAsStringRequiredTwoValueDefaultWithResponseAsync(input, requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * @param input The input parameter. Allowed values: "value1".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putModelAsStringRequiredOneValueNoDefaultWithResponse(
            String input, RequestOptions requestOptions) {
        return this.serviceClient.putModelAsStringRequiredOneValueNoDefaultWithResponseAsync(input, requestOptions);
    }

    /**
     * Puts constants to the testserver.
     *
     * @param input The input parameter. Allowed values: "value1".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putModelAsStringRequiredOneValueDefaultWithResponse(
            String input, RequestOptions requestOptions) {
        return this.serviceClient.putModelAsStringRequiredOneValueDefaultWithResponseAsync(input, requestOptions);
    }

    /**
     * Pass constants from the client to this function. Will pass in constant path, query, and header parameters.
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
    public Mono<Response<Void>> putClientConstantsWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.putClientConstantsWithResponseAsync(requestOptions);
    }
}
