// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.paging;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.PagedFlux;
import fixtures.paging.implementation.PagingsImpl;
import fixtures.paging.models.CustomParameterGroup;
import fixtures.paging.models.PagingGetMultiplePagesLroOptions;
import fixtures.paging.models.PagingGetMultiplePagesOptions;
import fixtures.paging.models.PagingGetMultiplePagesWithOffsetOptions;
import fixtures.paging.models.PagingGetOdataMultiplePagesOptions;
import fixtures.paging.models.Product;

/**
 * Initializes a new instance of the asynchronous AutoRestPagingTestService type.
 */
@ServiceClient(builder = AutoRestPagingTestServiceBuilder.class, isAsync = true)
public final class AutoRestPagingTestServiceAsyncClient {

    @Generated
    private final PagingsImpl serviceClient;

    /**
     * Initializes an instance of AutoRestPagingTestServiceAsyncClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    AutoRestPagingTestServiceAsyncClient(PagingsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getNoItemNamePages() {
        return this.serviceClient.getNoItemNamePagesAsync();
    }

    /**
     * A paging operation that gets an empty next link and should stop after page 1.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getEmptyNextLinkNamePages() {
        return this.serviceClient.getEmptyNextLinkNamePagesAsync();
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getNullNextLinkNamePages() {
        return this.serviceClient.getNullNextLinkNamePagesAsync();
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePages() {
        return this.serviceClient.getSinglePagesAsync();
    }

    /**
     * A paging operation that finishes on the first call with body params without a nextlink.
     *
     * @param name The name parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePagesWithBodyParams(String name) {
        return this.serviceClient.getSinglePagesWithBodyParamsAsync(name);
    }

    /**
     * A paging operation that finishes on the first call with body params without a nextlink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePagesWithBodyParams() {
        return this.serviceClient.getSinglePagesWithBodyParamsAsync();
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> firstResponseEmpty() {
        return this.serviceClient.firstResponseEmptyAsync();
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePages(String clientRequestId,
        PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        return this.serviceClient.getMultiplePagesAsync(clientRequestId, pagingGetMultiplePagesOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePages() {
        return this.serviceClient.getMultiplePagesAsync();
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getWithQueryParams(int requiredQueryParameter) {
        return this.serviceClient.getWithQueryParamsAsync(requiredQueryParameter);
    }

    /**
     * Define `filter` as a query param for all calls. However, the returned next link will also include the `filter` as
     * part of it. Make sure you don't end up duplicating the `filter` param in the url sent.
     *
     * @param filter OData filter options. Pass in 'foo'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> duplicateParams(String filter) {
        return this.serviceClient.duplicateParamsAsync(filter);
    }

    /**
     * Define `filter` as a query param for all calls. However, the returned next link will also include the `filter` as
     * part of it. Make sure you don't end up duplicating the `filter` param in the url sent.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> duplicateParams() {
        return this.serviceClient.duplicateParamsAsync();
    }

    /**
     * Paging with max page size. We don't want to.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> pageWithMaxPageSize() {
        return this.serviceClient.pageWithMaxPageSizeAsync();
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getOdataMultiplePages(String clientRequestId,
        PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        return this.serviceClient.getOdataMultiplePagesAsync(clientRequestId, pagingGetOdataMultiplePagesOptions);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getOdataMultiplePages() {
        return this.serviceClient.getOdataMultiplePagesAsync();
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesWithOffset(
        PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        return this.serviceClient.getMultiplePagesWithOffsetAsync(pagingGetMultiplePagesWithOffsetOptions,
            clientRequestId);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product>
        getMultiplePagesWithOffset(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions) {
        return this.serviceClient.getMultiplePagesWithOffsetAsync(pagingGetMultiplePagesWithOffsetOptions);
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesRetryFirst() {
        return this.serviceClient.getMultiplePagesRetryFirstAsync();
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesRetrySecond() {
        return this.serviceClient.getMultiplePagesRetrySecondAsync();
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePagesFailure() {
        return this.serviceClient.getSinglePagesFailureAsync();
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFailure() {
        return this.serviceClient.getMultiplePagesFailureAsync();
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFailureUri() {
        return this.serviceClient.getMultiplePagesFailureUriAsync();
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFragmentNextLink(String apiVersion, String tenant) {
        return this.serviceClient.getMultiplePagesFragmentNextLinkAsync(apiVersion, tenant);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * @param customParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFragmentWithGroupingNextLink(CustomParameterGroup customParameterGroup) {
        return this.serviceClient.getMultiplePagesFragmentWithGroupingNextLinkAsync(customParameterGroup);
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesLroOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesLRO(String clientRequestId,
        PagingGetMultiplePagesLroOptions pagingGetMultiplePagesLroOptions) {
        return this.serviceClient.getMultiplePagesLROAsync(clientRequestId, pagingGetMultiplePagesLroOptions);
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesLRO() {
        return this.serviceClient.getMultiplePagesLROAsync();
    }

    /**
     * A paging operation with api version. When calling the next link, you want to append your client's api version to
     * the next link.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> appendApiVersion() {
        return this.serviceClient.appendApiVersionAsync();
    }

    /**
     * A paging operation with api version. When calling the next link, you want to reformat it and override the
     * returned api version with your client's api version.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> replaceApiVersion() {
        return this.serviceClient.replaceApiVersionAsync();
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedFlux}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getPagingModelWithItemNameWithXMSClientName() {
        return this.serviceClient.getPagingModelWithItemNameWithXMSClientNameAsync();
    }
}
