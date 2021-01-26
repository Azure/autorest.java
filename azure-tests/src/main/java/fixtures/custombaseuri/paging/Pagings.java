package fixtures.custombaseuri.paging;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.custombaseuri.paging.models.Product;
import fixtures.custombaseuri.paging.models.ProductResult;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Pagings. */
public final class Pagings {
    /** The proxy service used to perform REST calls. */
    private final PagingsService service;

    /** The service client containing this operation class. */
    private final AutoRestParameterizedHostTestPagingClient client;

    /**
     * Initializes an instance of Pagings.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Pagings(AutoRestParameterizedHostTestPagingClient client) {
        this.service = RestProxy.create(PagingsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestParameterizedHostTestPagingClientPagings to be used by the
     * proxy service to perform REST calls.
     */
    @Host("http://{accountName}{host}")
    @ServiceInterface(name = "AutoRestParameterize")
    private interface PagingsService {
        @Get("/paging/customurl/partialnextlink")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getPagesPartialUrl(
                @HostParam("accountName") String accountName,
                @HostParam("host") String host,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/paging/customurl/partialnextlinkop")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getPagesPartialUrlOperation(
                @HostParam("accountName") String accountName,
                @HostParam("host") String host,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/paging/customurl/{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getPagesPartialUrlOperationNext(
                @HostParam("accountName") String accountName,
                @HostParam("host") String host,
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getPagesPartialUrlNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("accountName") String accountName,
                @HostParam("host") String host,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * A paging operation that combines custom url, paging and partial URL and expect to concat after host.
     *
     * @param accountName Account Name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getPagesPartialUrlSinglePageAsync(String accountName) {
        if (accountName == null) {
            return Mono.error(new IllegalArgumentException("Parameter accountName is required and cannot be null."));
        }
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                        context -> service.getPagesPartialUrl(accountName, this.client.getHost(), accept, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getNextLink(),
                                        null));
    }

    /**
     * A paging operation that combines custom url, paging and partial URL and expect to concat after host.
     *
     * @param accountName Account Name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getPagesPartialUrlAsync(String accountName) {
        return new PagedFlux<>(
                () -> getPagesPartialUrlSinglePageAsync(accountName),
                nextLink -> getPagesPartialUrlNextSinglePageAsync(nextLink, accountName));
    }

    /**
     * A paging operation that combines custom url, paging and partial URL and expect to concat after host.
     *
     * @param accountName Account Name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getPagesPartialUrl(String accountName) {
        return new PagedIterable<>(getPagesPartialUrlAsync(accountName));
    }

    /**
     * A paging operation that combines custom url, paging and partial URL with next operation.
     *
     * @param accountName Account Name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getPagesPartialUrlOperationSinglePageAsync(String accountName) {
        if (accountName == null) {
            return Mono.error(new IllegalArgumentException("Parameter accountName is required and cannot be null."));
        }
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                        context ->
                                service.getPagesPartialUrlOperation(
                                        accountName, this.client.getHost(), accept, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getNextLink(),
                                        null));
    }

    /**
     * A paging operation that combines custom url, paging and partial URL with next operation.
     *
     * @param accountName Account Name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getPagesPartialUrlOperationAsync(String accountName) {
        return new PagedFlux<>(
                () -> getPagesPartialUrlOperationSinglePageAsync(accountName),
                nextLink -> getPagesPartialUrlOperationNextSinglePageAsync(accountName, nextLink));
    }

    /**
     * A paging operation that combines custom url, paging and partial URL with next operation.
     *
     * @param accountName Account Name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getPagesPartialUrlOperation(String accountName) {
        return new PagedIterable<>(getPagesPartialUrlOperationAsync(accountName));
    }

    /**
     * A paging operation that combines custom url, paging and partial URL.
     *
     * @param accountName Account Name.
     * @param nextLink Next link for the list operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getPagesPartialUrlOperationNextSinglePageAsync(
            String accountName, String nextLink) {
        if (accountName == null) {
            return Mono.error(new IllegalArgumentException("Parameter accountName is required and cannot be null."));
        }
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                        context ->
                                service.getPagesPartialUrlOperationNext(
                                        accountName, this.client.getHost(), nextLink, accept, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getNextLink(),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @param accountName Account Name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getPagesPartialUrlNextSinglePageAsync(String nextLink, String accountName) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        if (accountName == null) {
            return Mono.error(new IllegalArgumentException("Parameter accountName is required and cannot be null."));
        }
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                        context ->
                                service.getPagesPartialUrlNext(
                                        nextLink, accountName, this.client.getHost(), accept, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getNextLink(),
                                        null));
    }
}
