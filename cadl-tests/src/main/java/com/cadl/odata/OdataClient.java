// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.odata;

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
import com.cadl.odata.models.Resource;
import java.util.List;

/** Initializes a new instance of the synchronous OdataClient type. */
@ServiceClient(builder = OdataClientBuilder.class)
public final class OdataClient {
    @Generated private final OdataAsyncClient client;

    /**
     * Initializes an instance of OdataClient class.
     *
     * @param client the async client.
     */
    @Generated
    OdataClient(OdataAsyncClient client) {
        this.client = client;
    }

    /**
     * The list operation.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>filter</td><td>String</td><td>No</td><td>The filter parameter</td></tr>
     *     <tr><td>orderby</td><td>String</td><td>No</td><td>The orderBy parameter</td></tr>
     *     <tr><td>skip</td><td>Integer</td><td>No</td><td>The skip parameter</td></tr>
     *     <tr><td>top</td><td>Integer</td><td>No</td><td>The top parameter</td></tr>
     *     <tr><td>maxpagesize</td><td>Integer</td><td>No</td><td>The maxPageSize parameter</td></tr>
     *     <tr><td>select</td><td>List&lt;String&gt;</td><td>No</td><td>Array of Filter. Call {@link RequestOptions#addQueryParam} to add string to array.</td></tr>
     *     <tr><td>expand</td><td>List&lt;String&gt;</td><td>No</td><td>Array of Filter. Call {@link RequestOptions#addQueryParam} to add string to array.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     *
     * <p><strong>Response Body Schema</strong>
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
     * @return paged collection of Resource items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> list(RequestOptions requestOptions) {
        return new PagedIterable<>(this.client.list(requestOptions));
    }

    /**
     * The list operation.
     *
     * @param filter The filter parameter.
     * @param orderBy The orderBy parameter.
     * @param skip The skip parameter.
     * @param top The top parameter.
     * @param maxPageSize The maxPageSize parameter.
     * @param select Array of Filter.
     * @param expand Array of Filter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of Resource items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Resource> list(
            String filter,
            String orderBy,
            Integer skip,
            Integer top,
            Integer maxPageSize,
            List<String> select,
            List<String> expand) {
        // Generated convenience method for list
        return new PagedIterable<>(client.list(filter, orderBy, skip, top, maxPageSize, select, expand));
    }

    /**
     * The list operation.
     *
     * @throws com.azure.core.exception.HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of Resource items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Resource> list() {
        // Generated convenience method for list
        return new PagedIterable<>(client.list());
    }
}
