// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.paging.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Pagings. */
public final class PagingsImpl {
    /** The proxy service used to perform REST calls. */
    private final PagingsService service;

    /** The service client containing this operation class. */
    private final AutoRestPagingTestServiceClientImpl client;

    /**
     * Initializes an instance of PagingsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    PagingsImpl(AutoRestPagingTestServiceClientImpl client) {
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
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getNoItemNamePages(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/nullnextlink")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getNullNextLinkNamePages(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/single")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getSinglePages(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/firstResponseEmpty/1")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> firstResponseEmpty(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePages(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/getWithQueryParams")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getWithQueryParams(
                @HostParam("$host") String host,
                @QueryParam("requiredQueryParameter") int requiredQueryParameter,
                @QueryParam("queryConstant") boolean queryConstant,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/duplicateParams/1")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> duplicateParams(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/maxPageSize")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> pageWithMaxPageSize(
                @HostParam("$host") String host,
                @QueryParam("$maxpagesize") String maxpagesize,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/nextOperationWithQueryParams")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> nextOperationWithQueryParams(
                @HostParam("$host") String host,
                @QueryParam("queryConstant") boolean queryConstant,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/odata")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getOdataMultiplePages(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/withpath/{offset}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesWithOffset(
                @HostParam("$host") String host,
                @PathParam("offset") int offset,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/retryfirst")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesRetryFirst(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/retrysecond")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesRetrySecond(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/single/failure")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getSinglePagesFailure(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/failure")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesFailure(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/failureuri")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesFailureUri(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/fragment/{tenant}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesFragmentNextLink(
                @HostParam("$host") String host,
                @QueryParam("api_version") String apiVersion,
                @PathParam("tenant") String tenant,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesFragmentWithGroupingNextLink(
                @HostParam("$host") String host,
                @QueryParam("api_version") String apiVersion,
                @PathParam("tenant") String tenant,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/paging/multiple/lro")
        @ExpectedResponses({202})
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
        Mono<Response<BinaryData>> getMultiplePagesLro(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/apiVersion/append/1")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> appendApiVersion(
                @HostParam("$host") String host,
                @QueryParam("api-version") String apiVersion,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/apiVersion/replace/1")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> replaceApiVersion(
                @HostParam("$host") String host,
                @QueryParam("api-version") String apiVersion,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/fragment/{tenant}/{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> nextFragment(
                @HostParam("$host") String host,
                @QueryParam("api_version") String apiVersion,
                @PathParam("tenant") String tenant,
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/multiple/fragmentwithgrouping/{tenant}/{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> nextFragmentWithGrouping(
                @HostParam("$host") String host,
                @QueryParam("api_version") String apiVersion,
                @PathParam("tenant") String tenant,
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/paging/itemNameWithXMSClientName")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getPagingModelWithItemNameWithXmsClientName(
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getNoItemNamePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getSinglePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> firstResponseEmptyNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> duplicateParamsNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> pageWithMaxPageSizeNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getOdataMultiplePagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesWithOffsetNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesRetryFirstNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesRetrySecondNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getSinglePagesFailureNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesFailureNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getMultiplePagesFailureUriNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({202})
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
        Mono<Response<BinaryData>> getMultiplePagesLroNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> appendApiVersionNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> replaceApiVersionNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("{nextLink}")
        @ExpectedResponses({200})
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
        Mono<Response<BinaryData>> getPagingModelWithItemNameWithXmsClientNameNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }
}
