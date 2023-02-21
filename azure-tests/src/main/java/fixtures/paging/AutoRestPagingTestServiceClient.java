// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.paging;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.Context;
import fixtures.paging.implementation.PagingsImpl;
import fixtures.paging.models.CustomParameterGroup;
import fixtures.paging.models.PagingGetMultiplePagesLroOptions;
import fixtures.paging.models.PagingGetMultiplePagesOptions;
import fixtures.paging.models.PagingGetMultiplePagesWithOffsetOptions;
import fixtures.paging.models.PagingGetOdataMultiplePagesOptions;
import fixtures.paging.models.Product;

/** Initializes a new instance of the synchronous AutoRestPagingTestService type. */
@ServiceClient(builder = AutoRestPagingTestServiceBuilder.class)
public final class AutoRestPagingTestServiceClient {
    @Generated private final PagingsImpl serviceClient;

    /**
     * Initializes an instance of AutoRestPagingTestServiceClient class.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    AutoRestPagingTestServiceClient(PagingsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getNoItemNamePages() {
        return this.serviceClient.getNoItemNamePages();
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getNoItemNamePages(Context context) {
        return this.serviceClient.getNoItemNamePages(context);
    }

    /**
     * A paging operation that gets an empty next link and should stop after page 1.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getEmptyNextLinkNamePages() {
        return this.serviceClient.getEmptyNextLinkNamePages();
    }

    /**
     * A paging operation that gets an empty next link and should stop after page 1.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getEmptyNextLinkNamePages(Context context) {
        return this.serviceClient.getEmptyNextLinkNamePages(context);
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getNullNextLinkNamePages() {
        return this.serviceClient.getNullNextLinkNamePages();
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getNullNextLinkNamePages(Context context) {
        return this.serviceClient.getNullNextLinkNamePages(context);
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePages() {
        return this.serviceClient.getSinglePages();
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePages(Context context) {
        return this.serviceClient.getSinglePages(context);
    }

    /**
     * A paging operation that finishes on the first call with body params without a nextlink.
     *
     * @param name The name parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePagesWithBodyParams(String name) {
        return this.serviceClient.getSinglePagesWithBodyParams(name);
    }

    /**
     * A paging operation that finishes on the first call with body params without a nextlink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePagesWithBodyParams() {
        return this.serviceClient.getSinglePagesWithBodyParams();
    }

    /**
     * A paging operation that finishes on the first call with body params without a nextlink.
     *
     * @param name The name parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePagesWithBodyParams(String name, Context context) {
        return this.serviceClient.getSinglePagesWithBodyParams(name, context);
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> firstResponseEmpty() {
        return this.serviceClient.firstResponseEmpty();
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> firstResponseEmpty(Context context) {
        return this.serviceClient.firstResponseEmpty(context);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePages(
            String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        return this.serviceClient.getMultiplePages(clientRequestId, pagingGetMultiplePagesOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePages() {
        return this.serviceClient.getMultiplePages();
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePages(
            String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions, Context context) {
        return this.serviceClient.getMultiplePages(clientRequestId, pagingGetMultiplePagesOptions, context);
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getWithQueryParams(int requiredQueryParameter) {
        return this.serviceClient.getWithQueryParams(requiredQueryParameter);
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getWithQueryParams(int requiredQueryParameter, Context context) {
        return this.serviceClient.getWithQueryParams(requiredQueryParameter, context);
    }

    /**
     * Define `filter` as a query param for all calls. However, the returned next link will also include the `filter` as
     * part of it. Make sure you don't end up duplicating the `filter` param in the url sent.
     *
     * @param filter OData filter options. Pass in 'foo'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> duplicateParams(String filter) {
        return this.serviceClient.duplicateParams(filter);
    }

    /**
     * Define `filter` as a query param for all calls. However, the returned next link will also include the `filter` as
     * part of it. Make sure you don't end up duplicating the `filter` param in the url sent.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> duplicateParams() {
        return this.serviceClient.duplicateParams();
    }

    /**
     * Define `filter` as a query param for all calls. However, the returned next link will also include the `filter` as
     * part of it. Make sure you don't end up duplicating the `filter` param in the url sent.
     *
     * @param filter OData filter options. Pass in 'foo'.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> duplicateParams(String filter, Context context) {
        return this.serviceClient.duplicateParams(filter, context);
    }

    /**
     * Paging with max page size. We don't want to.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> pageWithMaxPageSize() {
        return this.serviceClient.pageWithMaxPageSize();
    }

    /**
     * Paging with max page size. We don't want to.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> pageWithMaxPageSize(Context context) {
        return this.serviceClient.pageWithMaxPageSize(context);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getOdataMultiplePages(
            String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        return this.serviceClient.getOdataMultiplePages(clientRequestId, pagingGetOdataMultiplePagesOptions);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getOdataMultiplePages() {
        return this.serviceClient.getOdataMultiplePages();
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getOdataMultiplePages(
            String clientRequestId,
            PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions,
            Context context) {
        return this.serviceClient.getOdataMultiplePages(clientRequestId, pagingGetOdataMultiplePagesOptions, context);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesWithOffset(
            PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        return this.serviceClient.getMultiplePagesWithOffset(pagingGetMultiplePagesWithOffsetOptions, clientRequestId);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesWithOffset(
            PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions) {
        return this.serviceClient.getMultiplePagesWithOffset(pagingGetMultiplePagesWithOffsetOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesWithOffset(
            PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions,
            String clientRequestId,
            Context context) {
        return this.serviceClient.getMultiplePagesWithOffset(
                pagingGetMultiplePagesWithOffsetOptions, clientRequestId, context);
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesRetryFirst() {
        return this.serviceClient.getMultiplePagesRetryFirst();
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesRetryFirst(Context context) {
        return this.serviceClient.getMultiplePagesRetryFirst(context);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesRetrySecond() {
        return this.serviceClient.getMultiplePagesRetrySecond();
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesRetrySecond(Context context) {
        return this.serviceClient.getMultiplePagesRetrySecond(context);
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePagesFailure() {
        return this.serviceClient.getSinglePagesFailure();
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePagesFailure(Context context) {
        return this.serviceClient.getSinglePagesFailure(context);
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFailure() {
        return this.serviceClient.getMultiplePagesFailure();
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFailure(Context context) {
        return this.serviceClient.getMultiplePagesFailure(context);
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFailureUri() {
        return this.serviceClient.getMultiplePagesFailureUri();
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFailureUri(Context context) {
        return this.serviceClient.getMultiplePagesFailureUri(context);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFragmentNextLink(String apiVersion, String tenant) {
        return this.serviceClient.getMultiplePagesFragmentNextLink(apiVersion, tenant);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFragmentNextLink(String apiVersion, String tenant, Context context) {
        return this.serviceClient.getMultiplePagesFragmentNextLink(apiVersion, tenant, context);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * @param customParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFragmentWithGroupingNextLink(
            CustomParameterGroup customParameterGroup) {
        return this.serviceClient.getMultiplePagesFragmentWithGroupingNextLink(customParameterGroup);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * @param customParameterGroup Parameter group.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFragmentWithGroupingNextLink(
            CustomParameterGroup customParameterGroup, Context context) {
        return this.serviceClient.getMultiplePagesFragmentWithGroupingNextLink(customParameterGroup, context);
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesLroOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesLRO(
            String clientRequestId, PagingGetMultiplePagesLroOptions pagingGetMultiplePagesLroOptions) {
        return this.serviceClient.getMultiplePagesLRO(clientRequestId, pagingGetMultiplePagesLroOptions);
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesLRO() {
        return this.serviceClient.getMultiplePagesLRO();
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesLroOptions Parameter group.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesLRO(
            String clientRequestId,
            PagingGetMultiplePagesLroOptions pagingGetMultiplePagesLroOptions,
            Context context) {
        return this.serviceClient.getMultiplePagesLRO(clientRequestId, pagingGetMultiplePagesLroOptions, context);
    }

    /**
     * A paging operation with api version. When calling the next link, you want to append your client's api version to
     * the next link.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> appendApiVersion() {
        return this.serviceClient.appendApiVersion();
    }

    /**
     * A paging operation with api version. When calling the next link, you want to append your client's api version to
     * the next link.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> appendApiVersion(Context context) {
        return this.serviceClient.appendApiVersion(context);
    }

    /**
     * A paging operation with api version. When calling the next link, you want to reformat it and override the
     * returned api version with your client's api version.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> replaceApiVersion() {
        return this.serviceClient.replaceApiVersion();
    }

    /**
     * A paging operation with api version. When calling the next link, you want to reformat it and override the
     * returned api version with your client's api version.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> replaceApiVersion(Context context) {
        return this.serviceClient.replaceApiVersion(context);
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getPagingModelWithItemNameWithXMSClientName() {
        return this.serviceClient.getPagingModelWithItemNameWithXMSClientName();
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getPagingModelWithItemNameWithXMSClientName(Context context) {
        return this.serviceClient.getPagingModelWithItemNameWithXMSClientName(context);
    }
}
