// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.payload.pageable;

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
import com.azure.core.util.BinaryData;
import com.payload.pageable.implementation.PageableClientImpl;
import com.payload.pageable.models.User;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;

/** Initializes a new instance of the asynchronous PageableClient type. */
@ServiceClient(builder = PageableClientBuilder.class, isAsync = true)
public final class PageableAsyncClient {

    @Generated private final PageableClientImpl serviceClient;

    /**
     * Initializes an instance of PageableAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    PageableAsyncClient(PageableClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * List users.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>maxpagesize</td><td>Integer</td><td>No</td><td>The maximum number of result items per page.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
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
     * List users.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of User items as paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<User> list() {
        // Generated convenience method for list
        RequestOptions requestOptions = new RequestOptions();
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
