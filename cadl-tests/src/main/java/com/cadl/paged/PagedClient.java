// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.paged;

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
import com.azure.core.util.BinaryData;

/** Initializes a new instance of the synchronous PagedClient type. */
@ServiceClient(builder = PagedClientBuilder.class)
public final class PagedClient {
    @Generated private final PagedAsyncClient client;

    /**
     * Initializes an instance of PagedClient class.
     *
     * @param client the async client.
     */
    @Generated
    PagedClient(PagedAsyncClient client) {
        this.client = client;
    }

    /**
     * The list operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value (Required): [
     *          (Required){
     *             id: String (Required)
     *             name: String (Required)
     *             type: String (Required)
     *         }
     *     ]
     *     nextLink: String (Optional)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of Resource items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> list(RequestOptions requestOptions) {
        return new PagedIterable<>(this.client.list(requestOptions));
    }
}
