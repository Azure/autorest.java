package fixtures.paging.implementation;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Pagings. */
public final class PagingsImpl {
    /** The proxy service used to perform REST calls. */
    private final PagingsService service;

    /** The service client containing this operation class. */
    private final AutoRestPagingTestServiceImpl client;

    /**
     * Initializes an instance of PagingsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    PagingsImpl(AutoRestPagingTestServiceImpl client) {
        this.service = RestProxy.create(PagingsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
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
        Mono<Response<BinaryData>> listNoItemNamePages(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/nullnextlink")
        Mono<Response<BinaryData>> listNullNextLinkNamePages(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/single")
        Mono<Response<BinaryData>> listSinglePages(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/firstResponseEmpty/1")
        Mono<Response<BinaryData>> firstResponseEmpty(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple")
        Mono<Response<BinaryData>> listMultiplePages(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple/getWithQueryParams")
        Mono<Response<BinaryData>> listWithQueryParams(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple/nextOperationWithQueryParams")
        Mono<Response<BinaryData>> nextOperationWithQueryParams(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple/odata")
        Mono<Response<BinaryData>> listOdataMultiplePages(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple/withpath/{offset}")
        Mono<Response<BinaryData>> listMultiplePagesWithOffset(
                @HostParam("$host") String host,
                @PathParam("offset") int offset,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/retryfirst")
        Mono<Response<BinaryData>> listMultiplePagesRetryFirst(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple/retrysecond")
        Mono<Response<BinaryData>> listMultiplePagesRetrySecond(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/single/failure")
        Mono<Response<BinaryData>> listSinglePagesFailure(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple/failure")
        Mono<Response<BinaryData>> listMultiplePagesFailure(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple/failureuri")
        Mono<Response<BinaryData>> listMultiplePagesFailureUri(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple/fragment/{tenant}")
        Mono<Response<BinaryData>> listMultiplePagesFragmentNextLink(
                @HostParam("$host") String host,
                @PathParam("tenant") String tenant,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}")
        Mono<Response<BinaryData>> listMultiplePagesFragmentWithGroupingNextLink(
                @HostParam("$host") String host,
                @PathParam("tenant") String tenant,
                RequestOptions requestOptions,
                Context context);

        @Post("/paging/multiple/lro")
        Mono<Response<BinaryData>> listMultiplePagesLRO(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/paging/multiple/fragment/{tenant}/{nextLink}")
        Mono<Response<BinaryData>> nextFragment(
                @HostParam("$host") String host,
                @PathParam("tenant") String tenant,
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}/{nextLink}")
        Mono<Response<BinaryData>> nextFragmentWithGrouping(
                @HostParam("$host") String host,
                @PathParam("tenant") String tenant,
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/itemNameWithXMSClientName")
        Mono<Response<BinaryData>> listPagingModelWithItemNameWithXMSClientName(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listNoItemNamePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listSinglePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> firstResponseEmptyNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listMultiplePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listOdataMultiplePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listMultiplePagesWithOffsetNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listMultiplePagesRetryFirstNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listMultiplePagesRetrySecondNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listSinglePagesFailureNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listMultiplePagesFailureNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listMultiplePagesFailureUriNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listMultiplePagesLRONext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        Mono<Response<BinaryData>> listPagingModelWithItemNameWithXMSClientNameNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listNoItemNamePagesSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listNoItemNamePages(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "value"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listNoItemNamePagesSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listNoItemNamePages(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "value"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listNoItemNamePagesAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listNoItemNamePagesSinglePageAsync(requestOptions),
                nextLink -> listNoItemNamePagesNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listNoItemNamePagesAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listNoItemNamePagesSinglePageAsync(requestOptions, context),
                nextLink -> listNoItemNamePagesNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listNoItemNamePages(RequestOptions requestOptions) {
        return new PagedIterable<>(listNoItemNamePagesAsync(requestOptions));
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listNoItemNamePages(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listNoItemNamePagesAsync(requestOptions, context));
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listNullNextLinkNamePagesSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listNullNextLinkNamePages(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        null,
                                        null));
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listNullNextLinkNamePagesSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listNullNextLinkNamePages(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        null,
                                        null));
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listNullNextLinkNamePagesAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(() -> listNullNextLinkNamePagesSinglePageAsync(requestOptions));
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listNullNextLinkNamePagesAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(() -> listNullNextLinkNamePagesSinglePageAsync(requestOptions, context));
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listNullNextLinkNamePages(RequestOptions requestOptions) {
        return new PagedIterable<>(listNullNextLinkNamePagesAsync(requestOptions));
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listNullNextLinkNamePages(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listNullNextLinkNamePagesAsync(requestOptions, context));
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listSinglePagesSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.listSinglePages(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listSinglePagesSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listSinglePages(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listSinglePagesAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listSinglePagesSinglePageAsync(requestOptions),
                nextLink -> listSinglePagesNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listSinglePagesAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listSinglePagesSinglePageAsync(requestOptions, context),
                nextLink -> listSinglePagesNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listSinglePages(RequestOptions requestOptions) {
        return new PagedIterable<>(listSinglePagesAsync(requestOptions));
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listSinglePages(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listSinglePagesAsync(requestOptions, context));
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> firstResponseEmptySinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.firstResponseEmpty(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "value"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> firstResponseEmptySinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.firstResponseEmpty(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "value"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> firstResponseEmptyAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> firstResponseEmptySinglePageAsync(requestOptions),
                nextLink -> firstResponseEmptyNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> firstResponseEmptyAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> firstResponseEmptySinglePageAsync(requestOptions, context),
                nextLink -> firstResponseEmptyNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> firstResponseEmpty(RequestOptions requestOptions) {
        return new PagedIterable<>(firstResponseEmptyAsync(requestOptions));
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final)
     * call, will give you an items list of 1.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> firstResponseEmpty(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(firstResponseEmptyAsync(requestOptions, context));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listMultiplePages(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listMultiplePages(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listMultiplePagesSinglePageAsync(requestOptions),
                nextLink -> listMultiplePagesNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listMultiplePagesSinglePageAsync(requestOptions, context),
                nextLink -> listMultiplePagesNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePages(RequestOptions requestOptions) {
        return new PagedIterable<>(listMultiplePagesAsync(requestOptions));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePages(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listMultiplePagesAsync(requestOptions, context));
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>requiredQueryParameter</td><td>int</td><td>Yes</td><td>A required integer query parameter. Put in value '100' to pass test.</td></tr>
     *     <tr><td>queryConstant</td><td>boolean</td><td>Yes</td><td>A constant. Must be True and will be passed as a query parameter to nextOperationWithQueryParams</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listWithQueryParamsSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listWithQueryParams(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>requiredQueryParameter</td><td>int</td><td>Yes</td><td>A required integer query parameter. Put in value '100' to pass test.</td></tr>
     *     <tr><td>queryConstant</td><td>boolean</td><td>Yes</td><td>A constant. Must be True and will be passed as a query parameter to nextOperationWithQueryParams</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listWithQueryParamsSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listWithQueryParams(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>requiredQueryParameter</td><td>int</td><td>Yes</td><td>A required integer query parameter. Put in value '100' to pass test.</td></tr>
     *     <tr><td>queryConstant</td><td>boolean</td><td>Yes</td><td>A constant. Must be True and will be passed as a query parameter to nextOperationWithQueryParams</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listWithQueryParamsAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listWithQueryParamsSinglePageAsync(requestOptions),
                nextLink -> nextOperationWithQueryParamsSinglePageAsync(null));
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>requiredQueryParameter</td><td>int</td><td>Yes</td><td>A required integer query parameter. Put in value '100' to pass test.</td></tr>
     *     <tr><td>queryConstant</td><td>boolean</td><td>Yes</td><td>A constant. Must be True and will be passed as a query parameter to nextOperationWithQueryParams</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listWithQueryParamsAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listWithQueryParamsSinglePageAsync(requestOptions, context),
                nextLink -> nextOperationWithQueryParamsSinglePageAsync(null, context));
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>requiredQueryParameter</td><td>int</td><td>Yes</td><td>A required integer query parameter. Put in value '100' to pass test.</td></tr>
     *     <tr><td>queryConstant</td><td>boolean</td><td>Yes</td><td>A constant. Must be True and will be passed as a query parameter to nextOperationWithQueryParams</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listWithQueryParams(RequestOptions requestOptions) {
        return new PagedIterable<>(listWithQueryParamsAsync(requestOptions));
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation
     * nextOperationWithQueryParams. Returns a ProductResult.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>requiredQueryParameter</td><td>int</td><td>Yes</td><td>A required integer query parameter. Put in value '100' to pass test.</td></tr>
     *     <tr><td>queryConstant</td><td>boolean</td><td>Yes</td><td>A constant. Must be True and will be passed as a query parameter to nextOperationWithQueryParams</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listWithQueryParams(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listWithQueryParamsAsync(requestOptions, context));
    }

    /**
     * Next operation for getWithQueryParams. Pass in next=True to pass test. Returns a ProductResult.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>queryConstant</td><td>boolean</td><td>Yes</td><td>A constant. Must be True</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> nextOperationWithQueryParamsSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.nextOperationWithQueryParams(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        null,
                                        null));
    }

    /**
     * Next operation for getWithQueryParams. Pass in next=True to pass test. Returns a ProductResult.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>queryConstant</td><td>boolean</td><td>Yes</td><td>A constant. Must be True</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> nextOperationWithQueryParamsSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.nextOperationWithQueryParams(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        null,
                                        null));
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listOdataMultiplePagesSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listOdataMultiplePages(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listOdataMultiplePagesSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listOdataMultiplePages(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listOdataMultiplePagesAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listOdataMultiplePagesSinglePageAsync(requestOptions),
                nextLink -> listOdataMultiplePagesNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listOdataMultiplePagesAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listOdataMultiplePagesSinglePageAsync(requestOptions, context),
                nextLink -> listOdataMultiplePagesNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listOdataMultiplePages(RequestOptions requestOptions) {
        return new PagedIterable<>(listOdataMultiplePagesAsync(requestOptions));
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listOdataMultiplePages(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listOdataMultiplePagesAsync(requestOptions, context));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param offset Offset of return value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesWithOffsetSinglePageAsync(
            int offset, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesWithOffset(
                                        this.client.getHost(), offset, requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param offset Offset of return value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesWithOffsetSinglePageAsync(
            int offset, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesWithOffset(this.client.getHost(), offset, requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param offset Offset of return value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesWithOffsetAsync(int offset, RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listMultiplePagesWithOffsetSinglePageAsync(offset, requestOptions),
                nextLink -> listMultiplePagesWithOffsetNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param offset Offset of return value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesWithOffsetAsync(
            int offset, RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listMultiplePagesWithOffsetSinglePageAsync(offset, requestOptions, context),
                nextLink -> listMultiplePagesWithOffsetNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param offset Offset of return value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesWithOffset(int offset, RequestOptions requestOptions) {
        return new PagedIterable<>(listMultiplePagesWithOffsetAsync(offset, requestOptions));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param offset Offset of return value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesWithOffset(
            int offset, RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listMultiplePagesWithOffsetAsync(offset, requestOptions, context));
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesRetryFirstSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listMultiplePagesRetryFirst(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesRetryFirstSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesRetryFirst(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesRetryFirstAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listMultiplePagesRetryFirstSinglePageAsync(requestOptions),
                nextLink -> listMultiplePagesRetryFirstNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesRetryFirstAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listMultiplePagesRetryFirstSinglePageAsync(requestOptions, context),
                nextLink -> listMultiplePagesRetryFirstNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesRetryFirst(RequestOptions requestOptions) {
        return new PagedIterable<>(listMultiplePagesRetryFirstAsync(requestOptions));
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a
     * nextLink that has 10 pages.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesRetryFirst(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listMultiplePagesRetryFirstAsync(requestOptions, context));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesRetrySecondSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listMultiplePagesRetrySecond(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesRetrySecondSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesRetrySecond(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesRetrySecondAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listMultiplePagesRetrySecondSinglePageAsync(requestOptions),
                nextLink -> listMultiplePagesRetrySecondNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesRetrySecondAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listMultiplePagesRetrySecondSinglePageAsync(requestOptions, context),
                nextLink -> listMultiplePagesRetrySecondNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesRetrySecond(RequestOptions requestOptions) {
        return new PagedIterable<>(listMultiplePagesRetrySecondAsync(requestOptions));
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The
     * client should retry and finish all 10 pages eventually.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesRetrySecond(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listMultiplePagesRetrySecondAsync(requestOptions, context));
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listSinglePagesFailureSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listSinglePagesFailure(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listSinglePagesFailureSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listSinglePagesFailure(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listSinglePagesFailureAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listSinglePagesFailureSinglePageAsync(requestOptions),
                nextLink -> listSinglePagesFailureNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listSinglePagesFailureAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listSinglePagesFailureSinglePageAsync(requestOptions, context),
                nextLink -> listSinglePagesFailureNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listSinglePagesFailure(RequestOptions requestOptions) {
        return new PagedIterable<>(listSinglePagesFailureAsync(requestOptions));
    }

    /**
     * A paging operation that receives a 400 on the first call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listSinglePagesFailure(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listSinglePagesFailureAsync(requestOptions, context));
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFailureSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listMultiplePagesFailure(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFailureSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesFailure(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFailureAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listMultiplePagesFailureSinglePageAsync(requestOptions),
                nextLink -> listMultiplePagesFailureNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFailureAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listMultiplePagesFailureSinglePageAsync(requestOptions, context),
                nextLink -> listMultiplePagesFailureNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesFailure(RequestOptions requestOptions) {
        return new PagedIterable<>(listMultiplePagesFailureAsync(requestOptions));
    }

    /**
     * A paging operation that receives a 400 on the second call.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesFailure(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listMultiplePagesFailureAsync(requestOptions, context));
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFailureUriSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listMultiplePagesFailureUri(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFailureUriSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesFailureUri(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFailureUriAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listMultiplePagesFailureUriSinglePageAsync(requestOptions),
                nextLink -> listMultiplePagesFailureUriNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFailureUriAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listMultiplePagesFailureUriSinglePageAsync(requestOptions, context),
                nextLink -> listMultiplePagesFailureUriNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesFailureUri(RequestOptions requestOptions) {
        return new PagedIterable<>(listMultiplePagesFailureUriAsync(requestOptions));
    }

    /**
     * A paging operation that receives an invalid nextLink.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesFailureUri(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listMultiplePagesFailureUriAsync(requestOptions, context));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFragmentNextLinkSinglePageAsync(
            String tenant, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesFragmentNextLink(
                                        this.client.getHost(), tenant, requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFragmentNextLinkSinglePageAsync(
            String tenant, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesFragmentNextLink(this.client.getHost(), tenant, requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFragmentNextLinkAsync(String tenant, RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listMultiplePagesFragmentNextLinkSinglePageAsync(tenant, requestOptions),
                nextLink -> nextFragmentSinglePageAsync(tenant, nextLink, null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFragmentNextLinkAsync(
            String tenant, RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listMultiplePagesFragmentNextLinkSinglePageAsync(tenant, requestOptions, context),
                nextLink -> nextFragmentSinglePageAsync(tenant, nextLink, null, context));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesFragmentNextLink(String tenant, RequestOptions requestOptions) {
        return new PagedIterable<>(listMultiplePagesFragmentNextLinkAsync(tenant, requestOptions));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesFragmentNextLink(
            String tenant, RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listMultiplePagesFragmentNextLinkAsync(tenant, requestOptions, context));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFragmentWithGroupingNextLinkSinglePageAsync(
            String tenant, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesFragmentWithGroupingNextLink(
                                        this.client.getHost(), tenant, requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFragmentWithGroupingNextLinkSinglePageAsync(
            String tenant, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesFragmentWithGroupingNextLink(
                        this.client.getHost(), tenant, requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFragmentWithGroupingNextLinkAsync(
            String tenant, RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listMultiplePagesFragmentWithGroupingNextLinkSinglePageAsync(tenant, requestOptions),
                nextLink -> nextFragmentWithGroupingSinglePageAsync(tenant, nextLink, null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesFragmentWithGroupingNextLinkAsync(
            String tenant, RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listMultiplePagesFragmentWithGroupingNextLinkSinglePageAsync(tenant, requestOptions, context),
                nextLink -> nextFragmentWithGroupingSinglePageAsync(tenant, nextLink, null, context));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesFragmentWithGroupingNextLink(
            String tenant, RequestOptions requestOptions) {
        return new PagedIterable<>(listMultiplePagesFragmentWithGroupingNextLinkAsync(tenant, requestOptions));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesFragmentWithGroupingNextLink(
            String tenant, RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listMultiplePagesFragmentWithGroupingNextLinkAsync(tenant, requestOptions, context));
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesLROSinglePageAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context -> service.listMultiplePagesLRO(this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesLROSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesLRO(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesLROAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listMultiplePagesLROSinglePageAsync(requestOptions),
                nextLink -> listMultiplePagesLRONextSinglePageAsync(nextLink, null));
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listMultiplePagesLROAsync(RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listMultiplePagesLROSinglePageAsync(requestOptions, context),
                nextLink -> listMultiplePagesLRONextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesLRO(RequestOptions requestOptions) {
        return new PagedIterable<>(listMultiplePagesLROAsync(requestOptions));
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listMultiplePagesLRO(RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listMultiplePagesLROAsync(requestOptions, context));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param nextLink Next link for list operation.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> nextFragmentSinglePageAsync(
            String tenant, String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.nextFragment(this.client.getHost(), tenant, nextLink, requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param nextLink Next link for list operation.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> nextFragmentSinglePageAsync(
            String tenant, String nextLink, RequestOptions requestOptions, Context context) {
        return service.nextFragment(this.client.getHost(), tenant, nextLink, requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param nextLink Next link for list operation.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> nextFragmentWithGroupingSinglePageAsync(
            String tenant, String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.nextFragmentWithGrouping(
                                        this.client.getHost(), tenant, nextLink, requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Sets the api version to use.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param tenant Sets the tenant to use.
     * @param nextLink Next link for list operation.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> nextFragmentWithGroupingSinglePageAsync(
            String tenant, String nextLink, RequestOptions requestOptions, Context context) {
        return service.nextFragmentWithGrouping(this.client.getHost(), tenant, nextLink, requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     indexes: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listPagingModelWithItemNameWithXMSClientNameSinglePageAsync(
            RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listPagingModelWithItemNameWithXMSClientName(
                                        this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     indexes: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listPagingModelWithItemNameWithXMSClientNameSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        return service.listPagingModelWithItemNameWithXMSClientName(this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     indexes: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listPagingModelWithItemNameWithXMSClientNameAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> listPagingModelWithItemNameWithXMSClientNameSinglePageAsync(requestOptions),
                nextLink -> listPagingModelWithItemNameWithXMSClientNameNextSinglePageAsync(nextLink, null));
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     indexes: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listPagingModelWithItemNameWithXMSClientNameAsync(
            RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> listPagingModelWithItemNameWithXMSClientNameSinglePageAsync(requestOptions, context),
                nextLink -> listPagingModelWithItemNameWithXMSClientNameNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     indexes: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listPagingModelWithItemNameWithXMSClientName(RequestOptions requestOptions) {
        return new PagedIterable<>(listPagingModelWithItemNameWithXMSClientNameAsync(requestOptions));
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     indexes: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listPagingModelWithItemNameWithXMSClientName(
            RequestOptions requestOptions, Context context) {
        return new PagedIterable<>(listPagingModelWithItemNameWithXMSClientNameAsync(requestOptions, context));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listNoItemNamePagesNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listNoItemNamePagesNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "value"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listNoItemNamePagesNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listNoItemNamePagesNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "value"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listSinglePagesNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listSinglePagesNext(nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listSinglePagesNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listSinglePagesNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> firstResponseEmptyNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.firstResponseEmptyNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "value"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> firstResponseEmptyNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.firstResponseEmptyNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "value"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesNext(nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listOdataMultiplePagesNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listOdataMultiplePagesNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     odataNextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listOdataMultiplePagesNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listOdataMultiplePagesNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "odata.nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesWithOffsetNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesWithOffsetNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesWithOffsetNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesWithOffsetNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesRetryFirstNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesRetryFirstNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesRetryFirstNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesRetryFirstNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesRetrySecondNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesRetrySecondNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesRetrySecondNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesRetrySecondNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listSinglePagesFailureNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listSinglePagesFailureNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listSinglePagesFailureNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listSinglePagesFailureNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFailureNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesFailureNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFailureNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesFailureNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFailureUriNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesFailureUriNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesFailureUriNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesFailureUriNext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesLRONextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listMultiplePagesLRONext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>clientRequestId</td><td>String</td><td>No</td><td>The clientRequestId parameter</td></tr>
     *     <tr><td>maxresults</td><td>String</td><td>No</td><td>Sets the maximum number of items to return in the response.</td></tr>
     *     <tr><td>timeout</td><td>String</td><td>No</td><td>Sets the maximum time that the server can spend processing the request, in seconds. The default is 30 seconds.</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     values: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listMultiplePagesLRONextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listMultiplePagesLRONext(nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     indexes: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listPagingModelWithItemNameWithXMSClientNameNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.listPagingModelWithItemNameWithXMSClientNameNext(
                                        nextLink, this.client.getHost(), requestOptions, context))
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     indexes: [
     *         {
     *             properties: {
     *                 id: Integer
     *                 name: String
     *             }
     *         }
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> listPagingModelWithItemNameWithXMSClientNameNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.listPagingModelWithItemNameWithXMSClientNameNext(
                        nextLink, this.client.getHost(), requestOptions, context)
                .map(
                        res ->
                                new PagedResponseBase<>(
                                        res.getRequest(),
                                        res.getStatusCode(),
                                        res.getHeaders(),
                                        getValues(res.getValue(), "values"),
                                        getNextLink(res.getValue(), "nextLink"),
                                        null));
    }

    private List<BinaryData> getValues(BinaryData binaryData, String path) {
        try {
            Map<?, ?> obj = binaryData.toObject(Map.class);
            List<?> values = (List<?>) obj.get(path);
            return values.stream().map(BinaryData::fromObject).collect(Collectors.toList());
        } catch (RuntimeException e) {
            return null;
        }
    }

    private String getNextLink(BinaryData binaryData, String path) {
        try {
            Map<?, ?> obj = binaryData.toObject(Map.class);
            return (String) obj.get(path);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
