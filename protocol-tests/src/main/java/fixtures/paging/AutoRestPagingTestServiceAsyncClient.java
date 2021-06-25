package fixtures.paging;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.RequestOptions;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.util.BinaryData;
import fixtures.paging.implementation.PagingsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestPagingTestService type. */
@ServiceClient(builder = AutoRestPagingTestServiceBuilder.class, isAsync = true)
public final class AutoRestPagingTestServiceAsyncClient {
    private final PagingsImpl serviceClient;

    /**
     * Initializes an instance of Pagings client.
     *
     * @param serviceClient the service client implementation.
     */
    AutoRestPagingTestServiceAsyncClient(PagingsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getNoItemNamePagesSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getNoItemNamePagesSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getNoItemNamePages(RequestOptions requestOptions) {
        return this.serviceClient.getNoItemNamePagesAsync(requestOptions);
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getNullNextLinkNamePagesSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getNullNextLinkNamePagesSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getNullNextLinkNamePages(RequestOptions requestOptions) {
        return this.serviceClient.getNullNextLinkNamePagesAsync(requestOptions);
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getSinglePagesSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getSinglePagesSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getSinglePages(RequestOptions requestOptions) {
        return this.serviceClient.getSinglePagesAsync(requestOptions);
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> firstResponseEmptySinglePage(RequestOptions requestOptions) {
        return this.serviceClient.firstResponseEmptySinglePageAsync(requestOptions);
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> firstResponseEmpty(RequestOptions requestOptions) {
        return this.serviceClient.firstResponseEmptyAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getMultiplePages(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesAsync(requestOptions);
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getWithQueryParamsSinglePage(
            int requiredQueryParameter, RequestOptions requestOptions) {
        return this.serviceClient.getWithQueryParamsSinglePageAsync(requiredQueryParameter, requestOptions);
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getWithQueryParams(int requiredQueryParameter, RequestOptions requestOptions) {
        return this.serviceClient.getWithQueryParamsAsync(requiredQueryParameter, requestOptions);
    }

    /**
     * Next operation for getWithQueryParams. Pass in next=True to pass test. Returns a ProductResult.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> nextOperationWithQueryParamsSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.nextOperationWithQueryParamsSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getOdataMultiplePagesSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getOdataMultiplePagesSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getOdataMultiplePages(RequestOptions requestOptions) {
        return this.serviceClient.getOdataMultiplePagesAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesWithOffsetSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesWithOffsetSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getMultiplePagesWithOffset(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesWithOffsetAsync(requestOptions);
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesRetryFirstSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesRetryFirstSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getMultiplePagesRetryFirst(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesRetryFirstAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesRetrySecondSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesRetrySecondSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getMultiplePagesRetrySecond(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesRetrySecondAsync(requestOptions);
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getSinglePagesFailureSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getSinglePagesFailureSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getSinglePagesFailure(RequestOptions requestOptions) {
        return this.serviceClient.getSinglePagesFailureAsync(requestOptions);
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesFailureSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFailureSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getMultiplePagesFailure(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFailureAsync(requestOptions);
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesFailureUriSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFailureUriSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getMultiplePagesFailureUri(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFailureUriAsync(requestOptions);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesFragmentNextLinkSinglePage(
            String apiVersion, String tenant, RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFragmentNextLinkSinglePageAsync(apiVersion, tenant, requestOptions);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getMultiplePagesFragmentNextLink(
            String apiVersion, String tenant, RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFragmentNextLinkAsync(apiVersion, tenant, requestOptions);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesFragmentWithGroupingNextLinkSinglePage(
            RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFragmentWithGroupingNextLinkSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getMultiplePagesFragmentWithGroupingNextLink(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFragmentWithGroupingNextLinkAsync(requestOptions);
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesLROSinglePage(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesLROSinglePageAsync(requestOptions);
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getMultiplePagesLRO(RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesLROAsync(requestOptions);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @param nextLink Next link for list operation.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> nextFragmentSinglePage(
            String apiVersion, String tenant, String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.nextFragmentSinglePageAsync(apiVersion, tenant, nextLink, requestOptions);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param nextLink Next link for list operation.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> nextFragmentWithGroupingSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.nextFragmentWithGroupingSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getPagingModelWithItemNameWithXMSClientNameSinglePage(
            RequestOptions requestOptions) {
        return this.serviceClient.getPagingModelWithItemNameWithXMSClientNameSinglePageAsync(requestOptions);
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getPagingModelWithItemNameWithXMSClientName(RequestOptions requestOptions) {
        return this.serviceClient.getPagingModelWithItemNameWithXMSClientNameAsync(requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getNoItemNamePagesNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getNoItemNamePagesNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getSinglePagesNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getSinglePagesNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> firstResponseEmptyNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.firstResponseEmptyNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getOdataMultiplePagesNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getOdataMultiplePagesNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesWithOffsetNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesWithOffsetNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesRetryFirstNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesRetryFirstNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesRetrySecondNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesRetrySecondNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getSinglePagesFailureNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getSinglePagesFailureNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesFailureNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFailureNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesFailureUriNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesFailureUriNextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getMultiplePagesLRONextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getMultiplePagesLRONextSinglePageAsync(nextLink, requestOptions);
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> getPagingModelWithItemNameWithXMSClientNameNextSinglePage(
            String nextLink, RequestOptions requestOptions) {
        return this.serviceClient.getPagingModelWithItemNameWithXMSClientNameNextSinglePageAsync(
                nextLink, requestOptions);
    }
}
