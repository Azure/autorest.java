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
import com.azure.core.implementation.RestProxy;
import fixtures.paging.models.Product;
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
        @Get("/paging/single")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getSinglePages(@HostParam("$host") String host);

        @Get("/paging/multiple")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePages(@HostParam("$host") String host, @HeaderParam("client-request-id") String clientRequestId, @HeaderParam("maxresults") int maxresults, @HeaderParam("timeout") int timeout);

        @Get("/paging/multiple/odata")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getOdataMultiplePages(@HostParam("$host") String host, @HeaderParam("client-request-id") String clientRequestId, @HeaderParam("maxresults") int maxresults, @HeaderParam("timeout") int timeout);

        @Get("/paging/multiple/withpath/{offset}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesWithOffset(@HostParam("$host") String host, @HeaderParam("client-request-id") String clientRequestId, @HeaderParam("maxresults") int maxresults, @PathParam("offset") int offset, @HeaderParam("timeout") int timeout);

        @Get("/paging/multiple/retryfirst")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesRetryFirst(@HostParam("$host") String host);

        @Get("/paging/multiple/retrysecond")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesRetrySecond(@HostParam("$host") String host);

        @Get("/paging/single/failure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getSinglePagesFailure(@HostParam("$host") String host);

        @Get("/paging/multiple/failure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesFailure(@HostParam("$host") String host);

        @Get("/paging/multiple/failureuri")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesFailureUri(@HostParam("$host") String host);

        @Get("/paging/multiple/fragment/{tenant}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesFragmentNextLink(@HostParam("$host") String host, @QueryParam("api_version") String apiVersion, @PathParam("tenant") String tenant);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesFragmentWithGroupingNextLink(@HostParam("$host") String host, @QueryParam("api_version") String apiVersion, @PathParam("tenant") String tenant);

        @Post("/paging/multiple/lro")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesLRO(@HostParam("$host") String host, @HeaderParam("client-request-id") String clientRequestId, @HeaderParam("maxresults") int maxresults, @HeaderParam("timeout") int timeout);

        @Get("/paging/multiple/fragment/{tenant}/{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> nextFragment(@HostParam("$host") String host, @QueryParam("api_version") String apiVersion, @PathParam("tenant") String tenant, @PathParam("nextLink") String nextLink);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}/{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> nextFragmentWithGrouping(@HostParam("$host") String host, @QueryParam("api_version") String apiVersion, @PathParam("tenant") String tenant, @PathParam("nextLink") String nextLink);

        @Get("/paging/single")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getSinglePagesNext(@PathParam("nextLink") String nextLink);

        @Get("/paging/multiple")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesNext(@PathParam("nextLink") String nextLink);

        @Get("/paging/multiple/odata")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getOdataMultiplePagesNext(@PathParam("nextLink") String nextLink);

        @Get("/paging/multiple/withpath/{offset}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesWithOffsetNext(@PathParam("nextLink") String nextLink);

        @Get("/paging/multiple/retryfirst")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesRetryFirstNext(@PathParam("nextLink") String nextLink);

        @Get("/paging/multiple/retrysecond")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesRetrySecondNext(@PathParam("nextLink") String nextLink);

        @Get("/paging/single/failure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getSinglePagesFailureNext(@PathParam("nextLink") String nextLink);

        @Get("/paging/multiple/failure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesFailureNext(@PathParam("nextLink") String nextLink);

        @Get("/paging/multiple/failureuri")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesFailureUriNext(@PathParam("nextLink") String nextLink);

        @Post("/paging/multiple/lro")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<PagedResponse<Product>> getMultiplePagesLRONext(@PathParam("nextLink") String nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesWithResponseAsync() {
        return service.getSinglePages(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePagesAsync() {
        return new PagedFlux<>(
            () -> getSinglePagesWithResponseAsync(),
            nextLink -> getSinglePagesNextWithResponseAsync(nextLink));
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
    public Mono<PagedResponse<Product>> getMultiplePagesWithResponseAsync(String clientRequestId, int maxresults, int timeout) {
        return service.getMultiplePages(this.client.getHost(), clientRequestId, maxresults, timeout);
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesAsync(String clientRequestId, int maxresults, int timeout) {
        return new PagedFlux<>(
            () -> getMultiplePagesWithResponseAsync(clientRequestId, maxresults, timeout),
            nextLink -> getMultiplePagesNextWithResponseAsync(nextLink));
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
    public PagedIterable<Product> getMultiplePages(String clientRequestId, int maxresults, int timeout) {
        return new PagedIterable<>(getMultiplePagesAsync(clientRequestId, maxresults, timeout));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getOdataMultiplePagesWithResponseAsync(String clientRequestId, int maxresults, int timeout) {
        return service.getOdataMultiplePages(this.client.getHost(), clientRequestId, maxresults, timeout);
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getOdataMultiplePagesAsync(String clientRequestId, int maxresults, int timeout) {
        return new PagedFlux<>(
            () -> getOdataMultiplePagesWithResponseAsync(clientRequestId, maxresults, timeout),
            nextLink -> getOdataMultiplePagesNextWithResponseAsync(nextLink));
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
    public PagedIterable<Product> getOdataMultiplePages(String clientRequestId, int maxresults, int timeout) {
        return new PagedIterable<>(getOdataMultiplePagesAsync(clientRequestId, maxresults, timeout));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesWithOffsetWithResponseAsync(String clientRequestId, int maxresults, int offset, int timeout) {
        return service.getMultiplePagesWithOffset(this.client.getHost(), clientRequestId, maxresults, offset, timeout);
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesWithOffsetAsync(String clientRequestId, int maxresults, int offset, int timeout) {
        return new PagedFlux<>(
            () -> getMultiplePagesWithOffsetWithResponseAsync(clientRequestId, maxresults, offset, timeout),
            nextLink -> getMultiplePagesWithOffsetNextWithResponseAsync(nextLink));
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
    public PagedIterable<Product> getMultiplePagesWithOffset(String clientRequestId, int maxresults, int offset, int timeout) {
        return new PagedIterable<>(getMultiplePagesWithOffsetAsync(clientRequestId, maxresults, offset, timeout));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetryFirstWithResponseAsync() {
        return service.getMultiplePagesRetryFirst(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesRetryFirstAsync() {
        return new PagedFlux<>(
            () -> getMultiplePagesRetryFirstWithResponseAsync(),
            nextLink -> getMultiplePagesRetryFirstNextWithResponseAsync(nextLink));
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
    public Mono<PagedResponse<Product>> getMultiplePagesRetrySecondWithResponseAsync() {
        return service.getMultiplePagesRetrySecond(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesRetrySecondAsync() {
        return new PagedFlux<>(
            () -> getMultiplePagesRetrySecondWithResponseAsync(),
            nextLink -> getMultiplePagesRetrySecondNextWithResponseAsync(nextLink));
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
    public Mono<PagedResponse<Product>> getSinglePagesFailureWithResponseAsync() {
        return service.getSinglePagesFailure(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePagesFailureAsync() {
        return new PagedFlux<>(
            () -> getSinglePagesFailureWithResponseAsync(),
            nextLink -> getSinglePagesFailureNextWithResponseAsync(nextLink));
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
    public Mono<PagedResponse<Product>> getMultiplePagesFailureWithResponseAsync() {
        return service.getMultiplePagesFailure(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFailureAsync() {
        return new PagedFlux<>(
            () -> getMultiplePagesFailureWithResponseAsync(),
            nextLink -> getMultiplePagesFailureNextWithResponseAsync(nextLink));
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
    public Mono<PagedResponse<Product>> getMultiplePagesFailureUriWithResponseAsync() {
        return service.getMultiplePagesFailureUri(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFailureUriAsync() {
        return new PagedFlux<>(
            () -> getMultiplePagesFailureUriWithResponseAsync(),
            nextLink -> getMultiplePagesFailureUriNextWithResponseAsync(nextLink));
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
    public Mono<PagedResponse<Product>> getMultiplePagesFragmentNextLinkWithResponseAsync(String apiVersion, String tenant) {
        return service.getMultiplePagesFragmentNextLink(this.client.getHost(), apiVersion, tenant);
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFragmentNextLinkAsync(String apiVersion, String tenant) {
        return new PagedFlux<>(
            () -> getMultiplePagesFragmentNextLinkWithResponseAsync(apiVersion, tenant),
            nextLink -> nextFragmentWithResponseAsync(apiVersion, tenant, nextLink));
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
    public Mono<PagedResponse<Product>> getMultiplePagesFragmentWithGroupingNextLinkWithResponseAsync(String apiVersion, String tenant) {
        return service.getMultiplePagesFragmentWithGroupingNextLink(this.client.getHost(), apiVersion, tenant);
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFragmentWithGroupingNextLinkAsync(String apiVersion, String tenant) {
        return new PagedFlux<>(
            () -> getMultiplePagesFragmentWithGroupingNextLinkWithResponseAsync(apiVersion, tenant),
            nextLink -> nextFragmentWithGroupingWithResponseAsync(apiVersion, tenant, nextLink));
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
    public Mono<PagedResponse<Product>> getMultiplePagesLROWithResponseAsync(String clientRequestId, int maxresults, int timeout) {
        return service.getMultiplePagesLRO(this.client.getHost(), clientRequestId, maxresults, timeout);
    }

    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesLROAsync(String clientRequestId, int maxresults, int timeout) {
        return new PagedFlux<>(
            () -> getMultiplePagesLROWithResponseAsync(clientRequestId, maxresults, timeout),
            nextLink -> getMultiplePagesLRONextWithResponseAsync(nextLink));
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
    public PagedIterable<Product> getMultiplePagesLRO(String clientRequestId, int maxresults, int timeout) {
        return new PagedIterable<>(getMultiplePagesLROAsync(clientRequestId, maxresults, timeout));
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> nextFragmentWithResponseAsync(String apiVersion, String tenant, String nextLink) {
        return service.nextFragment(this.client.getHost(), apiVersion, tenant, nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> nextFragmentWithGroupingWithResponseAsync(String apiVersion, String tenant, String nextLink) {
        return service.nextFragmentWithGrouping(this.client.getHost(), apiVersion, tenant, nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesNextWithResponseAsync(String nextLink) {
        return service.getSinglePagesNext(nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesNextWithResponseAsync(String nextLink) {
        return service.getMultiplePagesNext(nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getOdataMultiplePagesNextWithResponseAsync(String nextLink) {
        return service.getOdataMultiplePagesNext(nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesWithOffsetNextWithResponseAsync(String nextLink) {
        return service.getMultiplePagesWithOffsetNext(nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetryFirstNextWithResponseAsync(String nextLink) {
        return service.getMultiplePagesRetryFirstNext(nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetrySecondNextWithResponseAsync(String nextLink) {
        return service.getMultiplePagesRetrySecondNext(nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesFailureNextWithResponseAsync(String nextLink) {
        return service.getSinglePagesFailureNext(nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureNextWithResponseAsync(String nextLink) {
        return service.getMultiplePagesFailureNext(nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureUriNextWithResponseAsync(String nextLink) {
        return service.getMultiplePagesFailureUriNext(nextLink);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesLRONextWithResponseAsync(String nextLink) {
        return service.getMultiplePagesLRONext(nextLink);
    }
}
