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
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.paging.models.CustomParameterGroup;
import fixtures.paging.models.OdataProductResult;
import fixtures.paging.models.PagingGetMultiplePagesLroOptions;
import fixtures.paging.models.PagingGetMultiplePagesOptions;
import fixtures.paging.models.PagingGetMultiplePagesWithOffsetOptions;
import fixtures.paging.models.PagingGetOdataMultiplePagesOptions;
import fixtures.paging.models.Product;
import fixtures.paging.models.ProductResult;
import fixtures.paging.models.ProductResultValue;
import fixtures.paging.models.ProductResultValueWithXMSClientName;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Pagings. */
public final class Pagings {
    /** The proxy service used to perform REST calls. */
    private final PagingsService service;

    /** The service client containing this operation class. */
    private final AutoRestPagingTestService client;

    /**
     * Initializes an instance of Pagings.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Pagings(AutoRestPagingTestService client) {
        this.service = RestProxy.create(PagingsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestPagingTestServicePagings to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestPagingTestSe")
    private interface PagingsService {
        @Get("/paging/noitemname")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResultValue>> getNoItemNamePages(@HostParam("$host") String host, Context context);

        @Get("/paging/nullnextlink")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getNullNextLinkNamePages(@HostParam("$host") String host, Context context);

        @Get("/paging/single")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getSinglePages(@HostParam("$host") String host, Context context);

        @Get("/paging/multiple")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePages(
                @HostParam("$host") String host,
                @HeaderParam("client-request-id") String clientRequestId,
                @HeaderParam("maxresults") Integer maxresults,
                @HeaderParam("timeout") Integer timeout,
                Context context);

        @Get("/paging/multiple/getWithQueryParams")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getWithQueryParams(
                @HostParam("$host") String host,
                @QueryParam("requiredQueryParameter") int requiredQueryParameter,
                @QueryParam("queryConstant") boolean queryConstant,
                Context context);

        @Get("/paging/multiple/nextOperationWithQueryParams")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> nextOperationWithQueryParams(
                @HostParam("$host") String host, @QueryParam("queryConstant") boolean queryConstant, Context context);

        @Get("/paging/multiple/odata")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<OdataProductResult>> getOdataMultiplePages(
                @HostParam("$host") String host,
                @HeaderParam("client-request-id") String clientRequestId,
                @HeaderParam("maxresults") Integer maxresults,
                @HeaderParam("timeout") Integer timeout,
                Context context);

        @Get("/paging/multiple/withpath/{offset}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesWithOffset(
                @HostParam("$host") String host,
                @HeaderParam("client-request-id") String clientRequestId,
                @HeaderParam("maxresults") Integer maxresults,
                @PathParam("offset") int offset,
                @HeaderParam("timeout") Integer timeout,
                Context context);

        @Get("/paging/multiple/retryfirst")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesRetryFirst(@HostParam("$host") String host, Context context);

        @Get("/paging/multiple/retrysecond")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesRetrySecond(@HostParam("$host") String host, Context context);

        @Get("/paging/single/failure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getSinglePagesFailure(@HostParam("$host") String host, Context context);

        @Get("/paging/multiple/failure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesFailure(@HostParam("$host") String host, Context context);

        @Get("/paging/multiple/failureuri")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesFailureUri(@HostParam("$host") String host, Context context);

        @Get("/paging/multiple/fragment/{tenant}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<OdataProductResult>> getMultiplePagesFragmentNextLink(
                @HostParam("$host") String host,
                @QueryParam("api_version") String apiVersion,
                @PathParam("tenant") String tenant,
                Context context);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<OdataProductResult>> getMultiplePagesFragmentWithGroupingNextLink(
                @HostParam("$host") String host,
                @QueryParam("api_version") String apiVersion,
                @PathParam("tenant") String tenant,
                Context context);

        @Post("/paging/multiple/lro")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesLRO(
                @HostParam("$host") String host,
                @HeaderParam("client-request-id") String clientRequestId,
                @HeaderParam("maxresults") Integer maxresults,
                @HeaderParam("timeout") Integer timeout,
                Context context);

        @Get("/paging/multiple/fragment/{tenant}/{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<OdataProductResult>> nextFragment(
                @HostParam("$host") String host,
                @QueryParam("api_version") String apiVersion,
                @PathParam("tenant") String tenant,
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                Context context);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}/{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<OdataProductResult>> nextFragmentWithGrouping(
                @HostParam("$host") String host,
                @QueryParam("api_version") String apiVersion,
                @PathParam("tenant") String tenant,
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                Context context);

        @Get("/paging/itemNameWithXMSClientName")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResultValueWithXMSClientName>> getPagingModelWithItemNameWithXMSClientName(
                @HostParam("$host") String host, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResultValue>> getNoItemNamePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getSinglePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<OdataProductResult>> getOdataMultiplePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesWithOffsetNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesRetryFirstNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesRetrySecondNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getSinglePagesFailureNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesFailureNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesFailureUriNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResult>> getMultiplePagesLRONext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ProductResultValueWithXMSClientName>> getPagingModelWithItemNameWithXMSClientNameNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink, Context context);
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getNoItemNamePagesSinglePageAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNoItemNamePages(this.client.getHost(), context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValue(),
                                        res.getValue().getNextLink(),
                                        null));
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getNoItemNamePagesAsync() {
        return new PagedFlux<>(
                () -> getNoItemNamePagesSinglePageAsync(), nextLink -> getNoItemNamePagesNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getNoItemNamePages() {
        return new PagedIterable<>(getNoItemNamePagesAsync());
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getNullNextLinkNamePagesSinglePageAsync() {
        return FluxUtil.withContext(context -> service.getNullNextLinkNamePages(this.client.getHost(), context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        null,
                                        null));
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getNullNextLinkNamePagesAsync() {
        return new PagedFlux<>(() -> getNullNextLinkNamePagesSinglePageAsync());
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getNullNextLinkNamePages() {
        return new PagedIterable<>(getNullNextLinkNamePagesAsync());
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesSinglePageAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getSinglePages(this.client.getHost(), context))
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
     * A paging operation that finishes on the first call without a nextlink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePagesAsync() {
        return new PagedFlux<>(
                () -> getSinglePagesSinglePageAsync(), nextLink -> getSinglePagesNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePages() {
        return new PagedIterable<>(getSinglePagesAsync());
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesSinglePageAsync(
            String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (pagingGetMultiplePagesOptions != null) {
            pagingGetMultiplePagesOptions.validate();
        }
        Integer maxresultsInternal = null;
        if (pagingGetMultiplePagesOptions != null) {
            maxresultsInternal = pagingGetMultiplePagesOptions.getMaxresults();
        }
        Integer maxresults = maxresultsInternal;
        Integer timeoutInternal = null;
        if (pagingGetMultiplePagesOptions != null) {
            timeoutInternal = pagingGetMultiplePagesOptions.getTimeout();
        }
        Integer timeout = timeoutInternal;
        return FluxUtil.withContext(
                        context ->
                                service.getMultiplePages(
                                        this.client.getHost(), clientRequestId, maxresults, timeout, context))
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
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesAsync(
            String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        return new PagedFlux<>(
                () -> getMultiplePagesSinglePageAsync(clientRequestId, pagingGetMultiplePagesOptions),
                nextLink -> getMultiplePagesNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePages(
            String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        return new PagedIterable<>(getMultiplePagesAsync(clientRequestId, pagingGetMultiplePagesOptions));
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getWithQueryParamsSinglePageAsync(int requiredQueryParameter) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final boolean queryConstant = true;
        return FluxUtil.withContext(
                        context ->
                                service.getWithQueryParams(
                                        this.client.getHost(), requiredQueryParameter, queryConstant, context))
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
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getWithQueryParamsAsync(int requiredQueryParameter) {
        return new PagedFlux<>(
                () -> getWithQueryParamsSinglePageAsync(requiredQueryParameter),
                nextLink -> nextOperationWithQueryParamsSinglePageAsync());
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getWithQueryParams(int requiredQueryParameter) {
        return new PagedIterable<>(getWithQueryParamsAsync(requiredQueryParameter));
    }

    /**
     * Next operation for getWithQueryParams. Pass in next=True to pass test. Returns a ProductResult.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> nextOperationWithQueryParamsSinglePageAsync() {
        final boolean queryConstant = true;
        return FluxUtil.withContext(
                        context -> service.nextOperationWithQueryParams(this.client.getHost(), queryConstant, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        null,
                                        null));
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getOdataMultiplePagesSinglePageAsync(
            String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (pagingGetOdataMultiplePagesOptions != null) {
            pagingGetOdataMultiplePagesOptions.validate();
        }
        Integer maxresultsInternal = null;
        if (pagingGetOdataMultiplePagesOptions != null) {
            maxresultsInternal = pagingGetOdataMultiplePagesOptions.getMaxresults();
        }
        Integer maxresults = maxresultsInternal;
        Integer timeoutInternal = null;
        if (pagingGetOdataMultiplePagesOptions != null) {
            timeoutInternal = pagingGetOdataMultiplePagesOptions.getTimeout();
        }
        Integer timeout = timeoutInternal;
        return FluxUtil.withContext(
                        context ->
                                service.getOdataMultiplePages(
                                        this.client.getHost(), clientRequestId, maxresults, timeout, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getOdataNextLink(),
                                        null));
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getOdataMultiplePagesAsync(
            String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        return new PagedFlux<>(
                () -> getOdataMultiplePagesSinglePageAsync(clientRequestId, pagingGetOdataMultiplePagesOptions),
                nextLink -> getOdataMultiplePagesNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getOdataMultiplePages(
            String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        return new PagedIterable<>(getOdataMultiplePagesAsync(clientRequestId, pagingGetOdataMultiplePagesOptions));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesWithOffsetSinglePageAsync(
            PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (pagingGetMultiplePagesWithOffsetOptions == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter pagingGetMultiplePagesWithOffsetOptions is required and cannot be null."));
        } else {
            pagingGetMultiplePagesWithOffsetOptions.validate();
        }
        Integer maxresults = pagingGetMultiplePagesWithOffsetOptions.getMaxresults();
        int offset = pagingGetMultiplePagesWithOffsetOptions.getOffset();
        Integer timeout = pagingGetMultiplePagesWithOffsetOptions.getTimeout();
        return FluxUtil.withContext(
                        context ->
                                service.getMultiplePagesWithOffset(
                                        this.client.getHost(), clientRequestId, maxresults, offset, timeout, context))
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
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesWithOffsetAsync(
            PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        return new PagedFlux<>(
                () ->
                        getMultiplePagesWithOffsetSinglePageAsync(
                                pagingGetMultiplePagesWithOffsetOptions, clientRequestId),
                nextLink -> getMultiplePagesWithOffsetNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesWithOffset(
            PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        return new PagedIterable<>(
                getMultiplePagesWithOffsetAsync(pagingGetMultiplePagesWithOffsetOptions, clientRequestId));
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetryFirstSinglePageAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesRetryFirst(this.client.getHost(), context))
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
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesRetryFirstAsync() {
        return new PagedFlux<>(
                () -> getMultiplePagesRetryFirstSinglePageAsync(),
                nextLink -> getMultiplePagesRetryFirstNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesRetryFirst() {
        return new PagedIterable<>(getMultiplePagesRetryFirstAsync());
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetrySecondSinglePageAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesRetrySecond(this.client.getHost(), context))
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
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesRetrySecondAsync() {
        return new PagedFlux<>(
                () -> getMultiplePagesRetrySecondSinglePageAsync(),
                nextLink -> getMultiplePagesRetrySecondNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesRetrySecond() {
        return new PagedIterable<>(getMultiplePagesRetrySecondAsync());
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesFailureSinglePageAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getSinglePagesFailure(this.client.getHost(), context))
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
     * A paging operation that receives a 400 on the first call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getSinglePagesFailureAsync() {
        return new PagedFlux<>(
                () -> getSinglePagesFailureSinglePageAsync(),
                nextLink -> getSinglePagesFailureNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getSinglePagesFailure() {
        return new PagedIterable<>(getSinglePagesFailureAsync());
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureSinglePageAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesFailure(this.client.getHost(), context))
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
     * A paging operation that receives a 400 on the second call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFailureAsync() {
        return new PagedFlux<>(
                () -> getMultiplePagesFailureSinglePageAsync(),
                nextLink -> getMultiplePagesFailureNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFailure() {
        return new PagedIterable<>(getMultiplePagesFailureAsync());
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureUriSinglePageAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesFailureUri(this.client.getHost(), context))
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
     * A paging operation that receives an invalid nextLink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFailureUriAsync() {
        return new PagedFlux<>(
                () -> getMultiplePagesFailureUriSinglePageAsync(),
                nextLink -> getMultiplePagesFailureUriNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFailureUri() {
        return new PagedIterable<>(getMultiplePagesFailureUriAsync());
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFragmentNextLinkSinglePageAsync(
            String apiVersion, String tenant) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (apiVersion == null) {
            return Mono.error(new IllegalArgumentException("Parameter apiVersion is required and cannot be null."));
        }
        if (tenant == null) {
            return Mono.error(new IllegalArgumentException("Parameter tenant is required and cannot be null."));
        }
        return FluxUtil.withContext(
                        context ->
                                service.getMultiplePagesFragmentNextLink(
                                        this.client.getHost(), apiVersion, tenant, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getOdataNextLink(),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFragmentNextLinkAsync(String apiVersion, String tenant) {
        return new PagedFlux<>(
                () -> getMultiplePagesFragmentNextLinkSinglePageAsync(apiVersion, tenant),
                nextLink -> nextFragmentSinglePageAsync(apiVersion, tenant, nextLink));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFragmentNextLink(String apiVersion, String tenant) {
        return new PagedIterable<>(getMultiplePagesFragmentNextLinkAsync(apiVersion, tenant));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * @param customParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFragmentWithGroupingNextLinkSinglePageAsync(
            CustomParameterGroup customParameterGroup) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (customParameterGroup == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter customParameterGroup is required and cannot be null."));
        } else {
            customParameterGroup.validate();
        }
        String apiVersion = customParameterGroup.getApiVersion();
        String tenant = customParameterGroup.getTenant();
        return FluxUtil.withContext(
                        context ->
                                service.getMultiplePagesFragmentWithGroupingNextLink(
                                        this.client.getHost(), apiVersion, tenant, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getOdataNextLink(),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * @param customParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesFragmentWithGroupingNextLinkAsync(
            CustomParameterGroup customParameterGroup) {
        return new PagedFlux<>(
                () -> getMultiplePagesFragmentWithGroupingNextLinkSinglePageAsync(customParameterGroup),
                nextLink -> nextFragmentWithGroupingSinglePageAsync(nextLink, customParameterGroup));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * @param customParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesFragmentWithGroupingNextLink(
            CustomParameterGroup customParameterGroup) {
        return new PagedIterable<>(getMultiplePagesFragmentWithGroupingNextLinkAsync(customParameterGroup));
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesLroOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesLROSinglePageAsync(
            String clientRequestId, PagingGetMultiplePagesLroOptions pagingGetMultiplePagesLroOptions) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (pagingGetMultiplePagesLroOptions != null) {
            pagingGetMultiplePagesLroOptions.validate();
        }
        Integer maxresultsInternal = null;
        if (pagingGetMultiplePagesLroOptions != null) {
            maxresultsInternal = pagingGetMultiplePagesLroOptions.getMaxresults();
        }
        Integer maxresults = maxresultsInternal;
        Integer timeoutInternal = null;
        if (pagingGetMultiplePagesLroOptions != null) {
            timeoutInternal = pagingGetMultiplePagesLroOptions.getTimeout();
        }
        Integer timeout = timeoutInternal;
        return FluxUtil.withContext(
                        context ->
                                service.getMultiplePagesLRO(
                                        this.client.getHost(), clientRequestId, maxresults, timeout, context))
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
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesLroOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getMultiplePagesLROAsync(
            String clientRequestId, PagingGetMultiplePagesLroOptions pagingGetMultiplePagesLroOptions) {
        return new PagedFlux<>(
                () -> getMultiplePagesLROSinglePageAsync(clientRequestId, pagingGetMultiplePagesLroOptions),
                nextLink -> getMultiplePagesLRONextSinglePageAsync(nextLink));
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesLroOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getMultiplePagesLRO(
            String clientRequestId, PagingGetMultiplePagesLroOptions pagingGetMultiplePagesLroOptions) {
        return new PagedIterable<>(getMultiplePagesLROAsync(clientRequestId, pagingGetMultiplePagesLroOptions));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @param nextLink Next link for list operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> nextFragmentSinglePageAsync(String apiVersion, String tenant, String nextLink) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (apiVersion == null) {
            return Mono.error(new IllegalArgumentException("Parameter apiVersion is required and cannot be null."));
        }
        if (tenant == null) {
            return Mono.error(new IllegalArgumentException("Parameter tenant is required and cannot be null."));
        }
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(
                        context -> service.nextFragment(this.client.getHost(), apiVersion, tenant, nextLink, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getOdataNextLink(),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * @param nextLink Next link for list operation.
     * @param customParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> nextFragmentWithGroupingSinglePageAsync(
            String nextLink, CustomParameterGroup customParameterGroup) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        if (customParameterGroup == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter customParameterGroup is required and cannot be null."));
        } else {
            customParameterGroup.validate();
        }
        String apiVersion = customParameterGroup.getApiVersion();
        String tenant = customParameterGroup.getTenant();
        return FluxUtil.withContext(
                        context ->
                                service.nextFragmentWithGrouping(
                                        this.client.getHost(), apiVersion, tenant, nextLink, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getOdataNextLink(),
                                        null));
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getPagingModelWithItemNameWithXMSClientNameSinglePageAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(
                        context -> service.getPagingModelWithItemNameWithXMSClientName(this.client.getHost(), context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getIndexes(),
                                        res.getValue().getNextLink(),
                                        null));
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<Product> getPagingModelWithItemNameWithXMSClientNameAsync() {
        return new PagedFlux<>(
                () -> getPagingModelWithItemNameWithXMSClientNameSinglePageAsync(),
                nextLink -> getPagingModelWithItemNameWithXMSClientNameNextSinglePageAsync(nextLink));
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<Product> getPagingModelWithItemNameWithXMSClientName() {
        return new PagedIterable<>(getPagingModelWithItemNameWithXMSClientNameAsync());
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getNoItemNamePagesNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNoItemNamePagesNext(nextLink, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValue(),
                                        res.getValue().getNextLink(),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getSinglePagesNext(nextLink, context))
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesNext(nextLink, context))
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getOdataMultiplePagesNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getOdataMultiplePagesNext(nextLink, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getValues(),
                                        res.getValue().getOdataNextLink(),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesWithOffsetNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesWithOffsetNext(nextLink, context))
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetryFirstNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesRetryFirstNext(nextLink, context))
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesRetrySecondNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesRetrySecondNext(nextLink, context))
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getSinglePagesFailureNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getSinglePagesFailureNext(nextLink, context))
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesFailureNext(nextLink, context))
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesFailureUriNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesFailureUriNext(nextLink, context))
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getMultiplePagesLRONextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getMultiplePagesLRONext(nextLink, context))
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
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<Product>> getPagingModelWithItemNameWithXMSClientNameNextSinglePageAsync(
            String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        return FluxUtil.withContext(
                        context -> service.getPagingModelWithItemNameWithXMSClientNameNext(nextLink, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        res.getValue().getIndexes(),
                                        res.getValue().getNextLink(),
                                        null));
    }
}
