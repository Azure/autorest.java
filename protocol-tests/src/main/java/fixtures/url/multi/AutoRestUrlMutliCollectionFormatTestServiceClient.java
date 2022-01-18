// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.url.multi;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.url.multi.implementation.QueriesImpl;

/** Initializes a new instance of the synchronous AutoRestUrlMutliCollectionFormatTestServiceClient type. */
@ServiceClient(builder = AutoRestUrlMutliCollectionFormatTestServiceClientBuilder.class)
public final class AutoRestUrlMutliCollectionFormatTestServiceClient {
    @Generated private final QueriesImpl serviceClient;

    /**
     * Initializes an instance of Queries client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    AutoRestUrlMutliCollectionFormatTestServiceClient(QueriesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get a null array of string using the multi-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>a null array of string using the multi-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return a null array of string using the multi-array format along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringMultiNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringMultiNullWithResponse(requestOptions);
    }

    /**
     * Get an empty array [] of string using the multi-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an empty array [] of string using the multi-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return an empty array [] of string using the multi-array format along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringMultiEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringMultiEmptyWithResponse(requestOptions);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the mult-array
     *     format along with {@link Response}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringMultiValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringMultiValidWithResponse(requestOptions);
    }
}
