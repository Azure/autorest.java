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
     *     <tr><td>skip</td><td>Long</td><td>No</td><td>The skip parameter</td></tr>
     *     <tr><td>top</td><td>Long</td><td>No</td><td>The top parameter</td></tr>
     *     <tr><td>maxpagesize</td><td>Long</td><td>No</td><td>The maxPageSize parameter</td></tr>
     *     <tr><td>select</td><td>List&lt;String&gt;</td><td>No</td><td>The select parameter. In the form of "," separated string.</td></tr>
     *     <tr><td>expand</td><td>List&lt;String&gt;</td><td>No</td><td>The expand parameter. In the form of "," separated string.</td></tr>
     * </table>
     *
     * You can add these to a request with {@link RequestOptions#addQueryParam}
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
