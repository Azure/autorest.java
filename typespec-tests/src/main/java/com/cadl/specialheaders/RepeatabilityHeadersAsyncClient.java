// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.specialheaders;

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
import com.azure.core.util.FluxUtil;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.PollOperationDetails;
import com.cadl.specialheaders.implementation.JsonMergePatchHelper;
import com.cadl.specialheaders.implementation.RepeatabilityHeadersImpl;
import com.cadl.specialheaders.models.Resource;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the asynchronous SpecialHeadersClient type.
 */
@ServiceClient(builder = SpecialHeadersClientBuilder.class, isAsync = true)
public final class RepeatabilityHeadersAsyncClient {
    @Generated
    private final RepeatabilityHeadersImpl serviceClient;

    /**
     * Initializes an instance of RepeatabilityHeadersAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    RepeatabilityHeadersAsyncClient(RepeatabilityHeadersImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Resource read operation template.
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Optional, Required on create)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.getWithResponseAsync(name, requestOptions);
    }

    /**
     * Send a put request with header Repeatability-Request-ID and Repeatability-First-Sent.
     * <p>
     * <strong>Header Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>repeatability-request-id</td>
     * <td>String</td>
     * <td>No</td>
     * <td>Repeatability request ID header</td>
     * </tr>
     * <tr>
     * <td>repeatability-first-sent</td>
     * <td>String</td>
     * <td>No</td>
     * <td>Repeatability first sent header as HTTP-date</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Optional, Required on create)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Optional, Required on create)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> putWithResponse(String name, BinaryData resource, RequestOptions requestOptions) {
        return this.serviceClient.putWithResponseAsync(name, resource, requestOptions);
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     * <p>
     * <strong>Header Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>repeatability-request-id</td>
     * <td>String</td>
     * <td>No</td>
     * <td>Repeatability request ID header</td>
     * </tr>
     * <tr>
     * <td>repeatability-first-sent</td>
     * <td>String</td>
     * <td>No</td>
     * <td>Repeatability first sent header as HTTP-date</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Optional, Required on create)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> postWithResponse(String name, RequestOptions requestOptions) {
        return this.serviceClient.postWithResponseAsync(name, requestOptions);
    }

    /**
     * Send a LRO request with header Repeatability-Request-ID and Repeatability-First-Sent.
     * <p>
     * <strong>Header Parameters</strong>
     * </p>
     * <table border="1">
     * <caption>Header Parameters</caption>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>repeatability-request-id</td>
     * <td>String</td>
     * <td>No</td>
     * <td>Repeatability request ID header</td>
     * </tr>
     * <tr>
     * <td>repeatability-first-sent</td>
     * <td>String</td>
     * <td>No</td>
     * <td>Repeatability first sent header as HTTP-date</td>
     * </tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addHeader}
     * <p>
     * <strong>Request Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Optional, Required on create)
     * }
     * }</pre>
     * <p>
     * <strong>Response Body Schema</strong>
     * </p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     description: String (Optional)
     *     type: String (Optional, Required on create)
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginCreateLro(String name, BinaryData resource,
        RequestOptions requestOptions) {
        return this.serviceClient.beginCreateLroAsync(name, resource, requestOptions);
    }

    /**
     * Resource read operation template.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> get(String name) {
        // Generated convenience method for getWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return getWithResponse(name, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * Send a put request with header Repeatability-Request-ID and Repeatability-First-Sent.
     * 
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> put(String name, Resource resource) {
        // Generated convenience method for putWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return putWithResponse(name, BinaryData.fromObject(resource), requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     * 
     * @param name A sequence of textual characters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Resource> post(String name) {
        // Generated convenience method for postWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return postWithResponse(name, requestOptions).flatMap(FluxUtil::toMono)
            .map(protocolMethodData -> protocolMethodData.toObject(Resource.class));
    }

    /**
     * Send a LRO request with header Repeatability-Request-ID and Repeatability-First-Sent.
     * 
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<PollOperationDetails, Resource> beginCreateLro(String name, Resource resource) {
        // Generated convenience method for beginCreateLroWithModel
        RequestOptions requestOptions = new RequestOptions();
        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
        BinaryData resourceInBinaryData = BinaryData.fromBytes(BinaryData.fromObject(resource).toBytes());
        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, false);
        return serviceClient.beginCreateLroWithModelAsync(name, resourceInBinaryData, requestOptions);
    }
}
