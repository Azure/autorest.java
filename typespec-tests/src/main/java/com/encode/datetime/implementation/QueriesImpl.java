// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.encode.datetime.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Queries. */
public final class QueriesImpl {
    /** The proxy service used to perform REST calls. */
    private final QueriesService service;

    /** The service client containing this operation class. */
    private final DatetimeClientImpl client;

    /**
     * Initializes an instance of QueriesImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    QueriesImpl(DatetimeClientImpl client) {
        this.service = RestProxy.create(QueriesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for DatetimeClientQueries to be used by the proxy service to perform REST
     * calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "DatetimeClientQuerie")
    public interface QueriesService {
        @Get("/encode/datetime/query/default")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> defaultMethod(
                @QueryParam("value") OffsetDateTime value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/encode/datetime/query/default")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> defaultMethodSync(
                @QueryParam("value") OffsetDateTime value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/encode/datetime/query/rfc3339")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> rfc3339(
                @QueryParam("value") OffsetDateTime value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/encode/datetime/query/rfc3339")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> rfc3339Sync(
                @QueryParam("value") OffsetDateTime value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/encode/datetime/query/rfc7231")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> rfc7231(
                @QueryParam("value") DateTimeRfc1123 value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/encode/datetime/query/rfc7231")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> rfc7231Sync(
                @QueryParam("value") DateTimeRfc1123 value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/encode/datetime/query/unix-timestamp")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> unixTimestamp(
                @QueryParam("value") long value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/encode/datetime/query/unix-timestamp")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> unixTimestampSync(
                @QueryParam("value") long value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/encode/datetime/query/unix-timestamp-array")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> unixTimestampArray(
                @QueryParam("value") String value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/encode/datetime/query/unix-timestamp-array")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(
                value = ClientAuthenticationException.class,
                code = {401})
        @UnexpectedResponseExceptionType(
                value = ResourceNotFoundException.class,
                code = {404})
        @UnexpectedResponseExceptionType(
                value = ResourceModifiedException.class,
                code = {409})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<Void> unixTimestampArraySync(
                @QueryParam("value") String value,
                @HeaderParam("accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * The defaultMethod operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> defaultMethodWithResponseAsync(OffsetDateTime value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.defaultMethod(value, accept, requestOptions, context));
    }

    /**
     * The defaultMethod operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> defaultMethodWithResponse(OffsetDateTime value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.defaultMethodSync(value, accept, requestOptions, Context.NONE);
    }

    /**
     * The rfc3339 operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> rfc3339WithResponseAsync(OffsetDateTime value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.rfc3339(value, accept, requestOptions, context));
    }

    /**
     * The rfc3339 operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> rfc3339WithResponse(OffsetDateTime value, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.rfc3339Sync(value, accept, requestOptions, Context.NONE);
    }

    /**
     * The rfc7231 operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> rfc7231WithResponseAsync(OffsetDateTime value, RequestOptions requestOptions) {
        final String accept = "application/json";
        DateTimeRfc1123 valueConverted = new DateTimeRfc1123(value);
        return FluxUtil.withContext(context -> service.rfc7231(valueConverted, accept, requestOptions, context));
    }

    /**
     * The rfc7231 operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> rfc7231WithResponse(OffsetDateTime value, RequestOptions requestOptions) {
        final String accept = "application/json";
        DateTimeRfc1123 valueConverted = new DateTimeRfc1123(value);
        return service.rfc7231Sync(valueConverted, accept, requestOptions, Context.NONE);
    }

    /**
     * The unixTimestamp operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> unixTimestampWithResponseAsync(OffsetDateTime value, RequestOptions requestOptions) {
        final String accept = "application/json";
        long valueConverted = value.toEpochSecond();
        return FluxUtil.withContext(context -> service.unixTimestamp(valueConverted, accept, requestOptions, context));
    }

    /**
     * The unixTimestamp operation.
     *
     * @param value An instant in coordinated universal time (UTC)".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> unixTimestampWithResponse(OffsetDateTime value, RequestOptions requestOptions) {
        final String accept = "application/json";
        long valueConverted = value.toEpochSecond();
        return service.unixTimestampSync(valueConverted, accept, requestOptions, Context.NONE);
    }

    /**
     * The unixTimestampArray operation.
     *
     * @param value Array of Value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> unixTimestampArrayWithResponseAsync(
            List<OffsetDateTime> value, RequestOptions requestOptions) {
        final String accept = "application/json";
        String valueConverted =
                JacksonAdapter.createDefaultSerializerAdapter()
                        .serializeIterable(
                                value.stream().map(value -> value.toEpochSecond()).collect(Collectors.toList()),
                                CollectionFormat.CSV);
        return FluxUtil.withContext(
                context -> service.unixTimestampArray(valueConverted, accept, requestOptions, context));
    }

    /**
     * The unixTimestampArray operation.
     *
     * @param value Array of Value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> unixTimestampArrayWithResponse(List<OffsetDateTime> value, RequestOptions requestOptions) {
        final String accept = "application/json";
        String valueConverted =
                JacksonAdapter.createDefaultSerializerAdapter()
                        .serializeIterable(
                                value.stream().map(value -> value.toEpochSecond()).collect(Collectors.toList()),
                                CollectionFormat.CSV);
        return service.unixTimestampArraySync(valueConverted, accept, requestOptions, Context.NONE);
    }
}
