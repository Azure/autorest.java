// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.serviceoperation;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.FluxUtil;
import com.cadl.serviceoperation.implementation.FooClientImpl;
import com.cadl.serviceoperation.models.ListOptions;
import com.cadl.serviceoperation.models.User;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous FooClient type. */
@ServiceClient(builder = FooClientBuilder.class, isAsync = true)
public final class FooAsyncClient {
    @Generated private final FooClientImpl serviceClient;

    /**
     * Initializes an instance of FooAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    FooAsyncClient(FooClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Create or replace operation template.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param id The user's id.
     * @param resource The resource instance.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return details about a user along with {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> createOrReplaceWithResponse(
            int id, BinaryData resource, RequestOptions requestOptions) {
        return this.serviceClient.createOrReplaceWithResponseAsync(id, resource, requestOptions);
    }

    /**
     * Resource list operation template.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>top</td><td>Integer</td><td>No</td><td>The number of result items to return.</td></tr>
     *     <tr><td>skip</td><td>Integer</td><td>No</td><td>The number of result items to skip.</td></tr>
     *     <tr><td>maxpagesize</td><td>Integer</td><td>No</td><td>The maximum number of result items per page.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: int (Required)
     *     name: String (Required)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of User items as paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> list(RequestOptions requestOptions) {
        return this.serviceClient.listAsync(requestOptions);
    }

    /**
     * Create or replace operation template.
     *
     * @param id The user's id.
     * @param resource The resource instance.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return details about a user on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<User> createOrReplace(int id, User resource) {
        // Generated convenience method for createOrReplaceWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return createOrReplaceWithResponse(id, BinaryData.fromObject(resource), requestOptions)
                .flatMap(FluxUtil::toMono)
                .map(protocolMethodData -> protocolMethodData.toObject(User.class));
    }

    /**
     * Resource list operation template.
     *
     * @param options Options for list API.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of User items as paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<User> list(ListOptions options) {
        // Generated convenience method for list
        RequestOptions requestOptions = new RequestOptions();
        Integer top = options.getTop();
        Integer skip = options.getSkip();
        if (top != null) {
            requestOptions.addQueryParam("top", String.valueOf(top), false);
        }
        if (skip != null) {
            requestOptions.addQueryParam("skip", String.valueOf(skip), false);
        }
        PagedFlux<BinaryData> pagedFluxResponse = list(requestOptions);
        return PagedFlux.create(
                () ->
                        (continuationToken, pageSize) -> {
                            Flux<PagedResponse<BinaryData>> flux =
                                    (continuationToken == null)
                                            ? pagedFluxResponse.byPage().take(1)
                                            : pagedFluxResponse.byPage(continuationToken).take(1);
                            return flux.map(
                                    pagedResponse ->
                                            new PagedResponseBase<Void, User>(
                                                    pagedResponse.getRequest(),
                                                    pagedResponse.getStatusCode(),
                                                    pagedResponse.getHeaders(),
                                                    pagedResponse.getValue().stream()
                                                            .map(
                                                                    protocolMethodData ->
                                                                            protocolMethodData.toObject(User.class))
                                                            .collect(Collectors.toList()),
                                                    pagedResponse.getContinuationToken(),
                                                    null));
                        });
    }
}
