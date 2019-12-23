package fixtures.paging;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import fixtures.paging.models.OdataProductResult;
import fixtures.paging.models.Product;
import fixtures.paging.models.ProductResult;
import fixtures.paging.models.ProductResultValue;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Pagings.
 */
public final class Pagings {
    /**
     * The proxy service used to perform REST calls.
     */
    private PagingsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestPagingTestService client;

    /**
     * Initializes an instance of Pagings.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Pagings(AutoRestPagingTestService client) {
        this.service = RestProxy.create(PagingsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestPagingTestServicePagings to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestPagingTestServicePagings")
    private interface PagingsService {
        @Get("/paging/noitemname")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResultValue>> getNoItemNamePages(@HostParam("$host") String host);

        @Get("/paging/nullnextlink")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getNullNextLinkNamePages(@HostParam("$host") String host);

        @Get("/paging/single")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getSinglePages(@HostParam("$host") String host);

        @Get("/paging/multiple")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePages(@HostParam("$host") String host, @HeaderParam("client-request-id") String clientRequestId, @HeaderParam("maxresults") Integer maxresults, @HeaderParam("timeout") Integer timeout);

        @Get("/paging/multiple/odata")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<OdataProductResult>> getOdataMultiplePages(@HostParam("$host") String host, @HeaderParam("client-request-id") String clientRequestId, @HeaderParam("maxresults") Integer maxresults, @HeaderParam("timeout") Integer timeout);

        @Get("/paging/multiple/withpath/{offset}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesWithOffset(@HostParam("$host") String host, @HeaderParam("client-request-id") String clientRequestId, @HeaderParam("maxresults") Integer maxresults, @PathParam("offset") int offset, @HeaderParam("timeout") Integer timeout);

        @Get("/paging/multiple/retryfirst")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesRetryFirst(@HostParam("$host") String host);

        @Get("/paging/multiple/retrysecond")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesRetrySecond(@HostParam("$host") String host);

        @Get("/paging/single/failure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getSinglePagesFailure(@HostParam("$host") String host);

        @Get("/paging/multiple/failure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesFailure(@HostParam("$host") String host);

        @Get("/paging/multiple/failureuri")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesFailureUri(@HostParam("$host") String host);

        @Get("/paging/multiple/fragment/{tenant}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<OdataProductResult>> getMultiplePagesFragmentNextLink(@HostParam("$host") String host, @QueryParam("api_version") String apiVersion, @PathParam("tenant") String tenant);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<OdataProductResult>> getMultiplePagesFragmentWithGroupingNextLink(@HostParam("$host") String host, @QueryParam("api_version") String apiVersion, @PathParam("tenant") String tenant);

        @Post("/paging/multiple/lro")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesLRO(@HostParam("$host") String host, @HeaderParam("client-request-id") String clientRequestId, @HeaderParam("maxresults") Integer maxresults, @HeaderParam("timeout") Integer timeout);

        @Get("/paging/multiple/fragment/{tenant}/{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<OdataProductResult>> nextFragment(@HostParam("$host") String host, @QueryParam("api_version") String apiVersion, @PathParam("tenant") String tenant, @PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}/{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<OdataProductResult>> nextFragmentWithGrouping(@HostParam("$host") String host, @QueryParam("api_version") String apiVersion, @PathParam("tenant") String tenant, @PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResultValue>> getNoItemNamePagesNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getSinglePagesNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<OdataProductResult>> getOdataMultiplePagesNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesWithOffsetNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesRetryFirstNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesRetrySecondNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getSinglePagesFailureNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesFailureNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesFailureUriNext(@PathParam(value = "nextLink", encoded = true) String nextLink);

        @Get("{nextLink}")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ProductResult>> getMultiplePagesLRONext(@PathParam(value = "nextLink", encoded = true) String nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getNoItemNamePagesSinglePageAsync() {
        return service.getNoItemNamePages(this.client.getHost()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getNoItemNamePagesAsync() {
        return new PagedFlux<>(
            () -> getNoItemNamePagesSinglePageAsync(),
            nextLink -> getNoItemNamePagesNextSinglePageAsync(nextLink));
    }

    /**
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getNoItemNamePages() {
        return new PagedIterable<>(getNoItemNamePagesAsync());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getNullNextLinkNamePagesSinglePageAsync() {
        return service.getNullNextLinkNamePages(this.client.getHost()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            null,
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getNullNextLinkNamePagesAsync() {
        return new PagedFlux<>(
            () -> getNullNextLinkNamePagesSinglePageAsync());
    }

    /**
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getNullNextLinkNamePages() {
        return new PagedIterable<>(getNullNextLinkNamePagesAsync());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesSinglePageAsync() {
        return service.getSinglePages(this.client.getHost()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePagesAsync() {
        return new PagedFlux<>(
            () -> getSinglePagesSinglePageAsync(),
            nextLink -> getSinglePagesNextSinglePageAsync(nextLink));
    }

    /**
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePages() {
        return new PagedIterable<>(getSinglePagesAsync());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesSinglePageAsync(String clientRequestId, Integer maxresults, Integer timeout) {
        return service.getMultiplePages(this.client.getHost(), clientRequestId, maxresults, timeout).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesAsync(String clientRequestId, Integer maxresults, Integer timeout) {
        return new PagedFlux<>(
            () -> getMultiplePagesSinglePageAsync(clientRequestId, maxresults, timeout),
            nextLink -> getMultiplePagesNextSinglePageAsync(nextLink));
    }

    /**
     * @param clientRequestId null
     * @param maxresults null
     * @param timeout null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePages(String clientRequestId, Integer maxresults, Integer timeout) {
        return new PagedIterable<>(getMultiplePagesAsync(clientRequestId, maxresults, timeout));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getOdataMultiplePagesSinglePageAsync(String clientRequestId, Integer maxresults, Integer timeout) {
        return service.getOdataMultiplePages(this.client.getHost(), clientRequestId, maxresults, timeout).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getOdatanextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getOdataMultiplePagesAsync(String clientRequestId, Integer maxresults, Integer timeout) {
        return new PagedFlux<>(
            () -> getOdataMultiplePagesSinglePageAsync(clientRequestId, maxresults, timeout),
            nextLink -> getOdataMultiplePagesNextSinglePageAsync(nextLink));
    }

    /**
     * @param clientRequestId null
     * @param maxresults null
     * @param timeout null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getOdataMultiplePages(String clientRequestId, Integer maxresults, Integer timeout) {
        return new PagedIterable<>(getOdataMultiplePagesAsync(clientRequestId, maxresults, timeout));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesWithOffsetSinglePageAsync(String clientRequestId, Integer maxresults, int offset, Integer timeout) {
        return service.getMultiplePagesWithOffset(this.client.getHost(), clientRequestId, maxresults, offset, timeout).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesWithOffsetAsync(String clientRequestId, Integer maxresults, int offset, Integer timeout) {
        return new PagedFlux<>(
            () -> getMultiplePagesWithOffsetSinglePageAsync(clientRequestId, maxresults, offset, timeout),
            nextLink -> getMultiplePagesWithOffsetNextSinglePageAsync(nextLink));
    }

    /**
     * @param clientRequestId null
     * @param maxresults null
     * @param offset null
     * @param timeout null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesWithOffset(String clientRequestId, Integer maxresults, int offset, Integer timeout) {
        return new PagedIterable<>(getMultiplePagesWithOffsetAsync(clientRequestId, maxresults, offset, timeout));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetryFirstSinglePageAsync() {
        return service.getMultiplePagesRetryFirst(this.client.getHost()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesRetryFirstAsync() {
        return new PagedFlux<>(
            () -> getMultiplePagesRetryFirstSinglePageAsync(),
            nextLink -> getMultiplePagesRetryFirstNextSinglePageAsync(nextLink));
    }

    /**
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesRetryFirst() {
        return new PagedIterable<>(getMultiplePagesRetryFirstAsync());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetrySecondSinglePageAsync() {
        return service.getMultiplePagesRetrySecond(this.client.getHost()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesRetrySecondAsync() {
        return new PagedFlux<>(
            () -> getMultiplePagesRetrySecondSinglePageAsync(),
            nextLink -> getMultiplePagesRetrySecondNextSinglePageAsync(nextLink));
    }

    /**
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesRetrySecond() {
        return new PagedIterable<>(getMultiplePagesRetrySecondAsync());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesFailureSinglePageAsync() {
        return service.getSinglePagesFailure(this.client.getHost()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePagesFailureAsync() {
        return new PagedFlux<>(
            () -> getSinglePagesFailureSinglePageAsync(),
            nextLink -> getSinglePagesFailureNextSinglePageAsync(nextLink));
    }

    /**
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePagesFailure() {
        return new PagedIterable<>(getSinglePagesFailureAsync());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureSinglePageAsync() {
        return service.getMultiplePagesFailure(this.client.getHost()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFailureAsync() {
        return new PagedFlux<>(
            () -> getMultiplePagesFailureSinglePageAsync(),
            nextLink -> getMultiplePagesFailureNextSinglePageAsync(nextLink));
    }

    /**
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFailure() {
        return new PagedIterable<>(getMultiplePagesFailureAsync());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureUriSinglePageAsync() {
        return service.getMultiplePagesFailureUri(this.client.getHost()).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFailureUriAsync() {
        return new PagedFlux<>(
            () -> getMultiplePagesFailureUriSinglePageAsync(),
            nextLink -> getMultiplePagesFailureUriNextSinglePageAsync(nextLink));
    }

    /**
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFailureUri() {
        return new PagedIterable<>(getMultiplePagesFailureUriAsync());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFragmentNextLinkSinglePageAsync(String apiVersion, String tenant) {
        return service.getMultiplePagesFragmentNextLink(this.client.getHost(), apiVersion, tenant).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getOdatanextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFragmentNextLinkAsync(String apiVersion, String tenant) {
        return new PagedFlux<>(
            () -> getMultiplePagesFragmentNextLinkSinglePageAsync(apiVersion, tenant),
            nextLink -> nextFragmentSinglePageAsync(apiVersion, tenant, nextLink));
    }

    /**
     * @param apiVersion null
     * @param tenant null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFragmentNextLink(String apiVersion, String tenant) {
        return new PagedIterable<>(getMultiplePagesFragmentNextLinkAsync(apiVersion, tenant));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFragmentWithGroupingNextLinkSinglePageAsync(String apiVersion, String tenant) {
        return service.getMultiplePagesFragmentWithGroupingNextLink(this.client.getHost(), apiVersion, tenant).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getOdatanextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFragmentWithGroupingNextLinkAsync(String apiVersion, String tenant) {
        return new PagedFlux<>(
            () -> getMultiplePagesFragmentWithGroupingNextLinkSinglePageAsync(apiVersion, tenant),
            nextLink -> nextFragmentWithGroupingSinglePageAsync(apiVersion, tenant, nextLink));
    }

    /**
     * @param apiVersion null
     * @param tenant null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFragmentWithGroupingNextLink(String apiVersion, String tenant) {
        return new PagedIterable<>(getMultiplePagesFragmentWithGroupingNextLinkAsync(apiVersion, tenant));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesLROSinglePageAsync(String clientRequestId, Integer maxresults, Integer timeout) {
        return service.getMultiplePagesLRO(this.client.getHost(), clientRequestId, maxresults, timeout).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesLROAsync(String clientRequestId, Integer maxresults, Integer timeout) {
        return new PagedFlux<>(
            () -> getMultiplePagesLROSinglePageAsync(clientRequestId, maxresults, timeout),
            nextLink -> getMultiplePagesLRONextSinglePageAsync(nextLink));
    }

    /**
     * @param clientRequestId null
     * @param maxresults null
     * @param timeout null
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesLRO(String clientRequestId, Integer maxresults, Integer timeout) {
        return new PagedIterable<>(getMultiplePagesLROAsync(clientRequestId, maxresults, timeout));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> nextFragmentSinglePageAsync(String apiVersion, String tenant, String nextLink) {
        return service.nextFragment(this.client.getHost(), apiVersion, tenant, nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getOdatanextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> nextFragmentWithGroupingSinglePageAsync(String apiVersion, String tenant, String nextLink) {
        return service.nextFragmentWithGrouping(this.client.getHost(), apiVersion, tenant, nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getOdatanextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getNoItemNamePagesNextSinglePageAsync(String nextLink) {
        return service.getNoItemNamePagesNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValue(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesNextSinglePageAsync(String nextLink) {
        return service.getSinglePagesNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesNextSinglePageAsync(String nextLink) {
        return service.getMultiplePagesNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getOdataMultiplePagesNextSinglePageAsync(String nextLink) {
        return service.getOdataMultiplePagesNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getOdatanextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesWithOffsetNextSinglePageAsync(String nextLink) {
        return service.getMultiplePagesWithOffsetNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetryFirstNextSinglePageAsync(String nextLink) {
        return service.getMultiplePagesRetryFirstNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetrySecondNextSinglePageAsync(String nextLink) {
        return service.getMultiplePagesRetrySecondNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesFailureNextSinglePageAsync(String nextLink) {
        return service.getSinglePagesFailureNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureNextSinglePageAsync(String nextLink) {
        return service.getMultiplePagesFailureNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureUriNextSinglePageAsync(String nextLink) {
        return service.getMultiplePagesFailureUriNext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesLRONextSinglePageAsync(String nextLink) {
        return service.getMultiplePagesLRONext(nextLink).map(res -> new PagedResponseBase<>(
            res.getRequest(),
            res.getStatusCode(),
            res.getHeaders(),
            res.getValue().getValues(),
            res.getValue().getNextLink(),
            null));
    }
}
