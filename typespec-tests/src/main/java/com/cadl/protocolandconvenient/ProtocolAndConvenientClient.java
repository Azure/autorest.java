// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.protocolandconvenient;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.PollOperationDetails;
import com.azure.core.util.polling.SyncPoller;
import com.cadl.protocolandconvenient.implementation.ProtocolAndConvenienceOpsImpl;
import com.cadl.protocolandconvenient.models.ResourceA;
import com.cadl.protocolandconvenient.models.ResourceB;
import com.cadl.protocolandconvenient.models.ResourceE;
import com.cadl.protocolandconvenient.models.ResourceF;
import com.cadl.protocolandconvenient.models.ResourceI;
import com.cadl.protocolandconvenient.models.ResourceJ;

/**
 * Initializes a new instance of the synchronous ProtocolAndConvenientClient type.
 */
@ServiceClient(builder = ProtocolAndConvenientClientBuilder.class)
public final class ProtocolAndConvenientClient {
    @Generated
    private final ProtocolAndConvenienceOpsImpl serviceClient;

    /**
     * Initializes an instance of ProtocolAndConvenientClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    ProtocolAndConvenientClient(ProtocolAndConvenienceOpsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * When set protocol false and convenient true, then the protocol method should be package private.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<BinaryData> onlyConvenientWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.onlyConvenientWithResponse(body, requestOptions);
    }

    /**
     * When set protocol true and convenient false, only the protocol method should be generated, ResourceC and
     * ResourceD should not be generated.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> onlyProtocolWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.onlyProtocolWithResponse(body, requestOptions);
    }

    /**
     * Setting protocol true and convenient true, both convenient and protocol methods will be generated.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> bothConvenientAndProtocolWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.bothConvenientAndProtocolWithResponse(body, requestOptions);
    }

    /**
     * When set protocol false and convenient false.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     * }
     * }</pre>
     * 
     * @param body The body parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<BinaryData> errorSettingWithResponse(BinaryData body, RequestOptions requestOptions) {
        return this.serviceClient.errorSettingWithResponse(body, requestOptions);
    }

    /**
     * Long running operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
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
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<BinaryData, BinaryData> beginCreateOrReplace(String name, BinaryData resource,
        RequestOptions requestOptions) {
        return this.serviceClient.beginCreateOrReplace(name, resource, requestOptions);
    }

    /**
     * Paging operation.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>maxresults</td><td>Long</td><td>No</td><td>An integer that can be serialized to JSON (`−9007199254740991
     * (−(2^53 − 1))` to `9007199254740991 (2^53 − 1)` )</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of ResourceJ items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    PagedIterable<BinaryData> list(RequestOptions requestOptions) {
        return this.serviceClient.list(requestOptions);
    }

    /**
     * When set protocol false and convenient true, then the protocol method should be package private.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ResourceB onlyConvenient(ResourceA body) {
        // Generated convenience method for onlyConvenientWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return onlyConvenientWithResponse(BinaryData.fromObject(body), requestOptions).getValue()
            .toObject(ResourceB.class);
    }

    /**
     * Setting protocol true and convenient true, both convenient and protocol methods will be generated.
     * 
     * @param body The body parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ResourceF bothConvenientAndProtocol(ResourceE body) {
        // Generated convenience method for bothConvenientAndProtocolWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return bothConvenientAndProtocolWithResponse(BinaryData.fromObject(body), requestOptions).getValue()
            .toObject(ResourceF.class);
    }

    /**
     * Long running operation.
     * 
     * @param name A sequence of textual characters.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<PollOperationDetails, ResourceI> beginCreateOrReplace(String name, ResourceI resource) {
        // Generated convenience method for beginCreateOrReplaceWithModel
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.beginCreateOrReplaceWithModel(name, BinaryData.fromObject(resource), requestOptions);
    }

    /**
     * Paging operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of ResourceJ items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<ResourceJ> list() {
        // Generated convenience method for list
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.list(requestOptions).mapPage(bodyItemValue -> bodyItemValue.toObject(ResourceJ.class));
    }
}
