// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.specialheader.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
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
import com.azure.core.util.polling.DefaultPollingStrategy;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.SyncPoller;
import com.azure.core.util.serializer.TypeReference;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Headers. */
public final class HeadersImpl {
    /** The proxy service used to perform REST calls. */
    private final HeadersService service;

    /** The service client containing this operation class. */
    private final SpecialHeaderClientImpl client;

    /**
     * Initializes an instance of HeadersImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    HeadersImpl(SpecialHeaderClientImpl client) {
        this.service = RestProxy.create(HeadersService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for SpecialHeaderHeaders to be used by the proxy service to perform REST
     * calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "SpecialHeaderHeaders")
    private interface HeadersService {
        @Post("/status/500")
        @ExpectedResponses({200})
        Mono<Response<BinaryData>> paramRepeatabilityRequest(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/status/500")
        @ExpectedResponses({200})
        Mono<Response<BinaryData>> paramRepeatabilityRequestPut(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/status/500")
        @ExpectedResponses({200})
        Mono<Response<BinaryData>> paramRepeatabilityRequestGet(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/specialHeader/repeatabilityRequestLRO")
        @ExpectedResponses({200, 202})
        Mono<Response<BinaryData>> paramRepeatabilityRequestLro(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/specialHeader/repeatabilityRequestPageable")
        @ExpectedResponses({200})
        Mono<Response<BinaryData>> paramRepeatabilityRequestPageable(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
        Mono<Response<BinaryData>> paramRepeatabilityRequestPageableNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> paramRepeatabilityRequestWithResponseAsync(RequestOptions requestOptions) {
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.setHeader("repeatability-request-id", UUID.randomUUID().toString());
        requestOptionsLocal.setHeader(
                "repeatability-first-sent",
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                        .withZone(ZoneId.of("GMT"))
                        .format(OffsetDateTime.now()));
        return FluxUtil.withContext(
                context -> service.paramRepeatabilityRequest(this.client.getHost(), requestOptionsLocal, context));
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> paramRepeatabilityRequestWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.setHeader("repeatability-request-id", UUID.randomUUID().toString());
        requestOptionsLocal.setHeader(
                "repeatability-first-sent",
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                        .withZone(ZoneId.of("GMT"))
                        .format(OffsetDateTime.now()));
        return service.paramRepeatabilityRequest(this.client.getHost(), requestOptionsLocal, context);
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> paramRepeatabilityRequestWithResponse(RequestOptions requestOptions) {
        return paramRepeatabilityRequestWithResponseAsync(requestOptions).block();
    }

    /**
     * Send a put request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> paramRepeatabilityRequestPutWithResponseAsync(RequestOptions requestOptions) {
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.setHeader("repeatability-request-id", UUID.randomUUID().toString());
        requestOptionsLocal.setHeader(
                "repeatability-first-sent",
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                        .withZone(ZoneId.of("GMT"))
                        .format(OffsetDateTime.now()));
        return FluxUtil.withContext(
                context -> service.paramRepeatabilityRequestPut(this.client.getHost(), requestOptionsLocal, context));
    }

    /**
     * Send a put request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> paramRepeatabilityRequestPutWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.setHeader("repeatability-request-id", UUID.randomUUID().toString());
        requestOptionsLocal.setHeader(
                "repeatability-first-sent",
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                        .withZone(ZoneId.of("GMT"))
                        .format(OffsetDateTime.now()));
        return service.paramRepeatabilityRequestPut(this.client.getHost(), requestOptionsLocal, context);
    }

    /**
     * Send a put request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> paramRepeatabilityRequestPutWithResponse(RequestOptions requestOptions) {
        return paramRepeatabilityRequestPutWithResponseAsync(requestOptions).block();
    }

    /**
     * Send a get request without header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> paramRepeatabilityRequestGetWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.paramRepeatabilityRequestGet(this.client.getHost(), requestOptions, context));
    }

    /**
     * Send a get request without header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> paramRepeatabilityRequestGetWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.paramRepeatabilityRequestGet(this.client.getHost(), requestOptions, context);
    }

    /**
     * Send a get request without header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> paramRepeatabilityRequestGetWithResponse(RequestOptions requestOptions) {
        return paramRepeatabilityRequestGetWithResponseAsync(requestOptions).block();
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> paramRepeatabilityRequestLroWithResponseAsync(RequestOptions requestOptions) {
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.setHeader("repeatability-request-id", UUID.randomUUID().toString());
        requestOptionsLocal.setHeader(
                "repeatability-first-sent",
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                        .withZone(ZoneId.of("GMT"))
                        .format(OffsetDateTime.now()));
        return FluxUtil.withContext(
                context -> service.paramRepeatabilityRequestLro(this.client.getHost(), requestOptionsLocal, context));
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return any object along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> paramRepeatabilityRequestLroWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.setHeader("repeatability-request-id", UUID.randomUUID().toString());
        requestOptionsLocal.setHeader(
                "repeatability-first-sent",
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                        .withZone(ZoneId.of("GMT"))
                        .format(OffsetDateTime.now()));
        return service.paramRepeatabilityRequestLro(this.client.getHost(), requestOptionsLocal, context);
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link PollerFlux} for polling of any object.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginParamRepeatabilityRequestLroAsync(RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.paramRepeatabilityRequestLroWithResponseAsync(requestOptions),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link PollerFlux} for polling of any object.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginParamRepeatabilityRequestLroAsync(
            RequestOptions requestOptions, Context context) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.paramRepeatabilityRequestLroWithResponseAsync(requestOptions, context),
                new DefaultPollingStrategy<>(this.client.getHttpPipeline()),
                new TypeReferenceBinaryData(),
                new TypeReferenceBinaryData());
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the {@link SyncPoller} for polling of any object.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginParamRepeatabilityRequestLro(RequestOptions requestOptions) {
        return this.beginParamRepeatabilityRequestLroAsync(requestOptions).getSyncPoller();
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         Object
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link PagedResponse} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> paramRepeatabilityRequestPageableSinglePageAsync(
            RequestOptions requestOptions) {
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.setHeader("repeatability-request-id", UUID.randomUUID().toString());
        requestOptionsLocal.setHeader(
                "repeatability-first-sent",
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                        .withZone(ZoneId.of("GMT"))
                        .format(OffsetDateTime.now()));
        return FluxUtil.withContext(
                        context ->
                                service.paramRepeatabilityRequestPageable(
                                        this.client.getHost(), requestOptionsLocal, context))
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
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         Object
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link PagedResponse} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> paramRepeatabilityRequestPageableSinglePageAsync(
            RequestOptions requestOptions, Context context) {
        RequestOptions requestOptionsLocal = requestOptions == null ? new RequestOptions() : requestOptions;
        requestOptionsLocal.setHeader("repeatability-request-id", UUID.randomUUID().toString());
        requestOptionsLocal.setHeader(
                "repeatability-first-sent",
                DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                        .withZone(ZoneId.of("GMT"))
                        .format(OffsetDateTime.now()));
        return service.paramRepeatabilityRequestPageable(this.client.getHost(), requestOptionsLocal, context)
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
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         Object
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the paginated response with {@link PagedFlux}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> paramRepeatabilityRequestPageableAsync(RequestOptions requestOptions) {
        return new PagedFlux<>(
                () -> paramRepeatabilityRequestPageableSinglePageAsync(requestOptions),
                nextLink -> paramRepeatabilityRequestPageableNextSinglePageAsync(nextLink, null));
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         Object
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the paginated response with {@link PagedFlux}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> paramRepeatabilityRequestPageableAsync(
            RequestOptions requestOptions, Context context) {
        return new PagedFlux<>(
                () -> paramRepeatabilityRequestPageableSinglePageAsync(requestOptions, context),
                nextLink -> paramRepeatabilityRequestPageableNextSinglePageAsync(nextLink, null, context));
    }

    /**
     * Send a post request with header Repeatability-Request-ID and Repeatability-First-Sent.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         Object
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> paramRepeatabilityRequestPageable(RequestOptions requestOptions) {
        return new PagedIterable<>(paramRepeatabilityRequestPageableAsync(requestOptions));
    }

    /**
     * Get the next page of items.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     value: [
     *         Object
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link PagedResponse} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> paramRepeatabilityRequestPageableNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                        context ->
                                service.paramRepeatabilityRequestPageableNext(
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
     *         Object
     *     ]
     *     nextLink: String
     * }
     * }</pre>
     *
     * @param nextLink The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response body along with {@link PagedResponse} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PagedResponse<BinaryData>> paramRepeatabilityRequestPageableNextSinglePageAsync(
            String nextLink, RequestOptions requestOptions, Context context) {
        return service.paramRepeatabilityRequestPageableNext(nextLink, this.client.getHost(), requestOptions, context)
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

    private static final class TypeReferenceBinaryData extends TypeReference<BinaryData> {
        // empty
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
