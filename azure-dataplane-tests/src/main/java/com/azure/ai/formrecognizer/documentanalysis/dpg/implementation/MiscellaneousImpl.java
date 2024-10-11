// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.dpg.implementation;

import com.azure.ai.formrecognizer.documentanalysis.dpg.FormRecognizerServiceVersion;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
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

/**
 * An instance of this class provides access to all the operations defined in Miscellaneous.
 */
public final class MiscellaneousImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final MiscellaneousService service;

    /**
     * The service client containing this operation class.
     */
    private final FormRecognizerClientImpl client;

    /**
     * Initializes an instance of MiscellaneousImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    MiscellaneousImpl(FormRecognizerClientImpl client) {
        this.service
            = RestProxy.create(MiscellaneousService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public FormRecognizerServiceVersion getServiceVersion() {
        return client.getServiceVersion();
    }

    /**
     * The interface defining all the services for FormRecognizerClientMiscellaneous to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{endpoint}/formrecognizer")
    @ServiceInterface(name = "FormRecognizerClient")
    public interface MiscellaneousService {
        @Get("/operations")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> listOperations(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/operations")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> listOperationsSync(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/operations/{operationId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getOperation(@HostParam("endpoint") String endpoint,
            @PathParam("operationId") String operationId, @QueryParam("api-version") String apiVersion,
            @HeaderParam("Accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/operations/{operationId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getOperationSync(@HostParam("endpoint") String endpoint,
            @PathParam("operationId") String operationId, @QueryParam("api-version") String apiVersion,
            @HeaderParam("Accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/info")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getResourceInfo(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/info")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getResourceInfoSync(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> listOperationsNext(@PathParam(value = "nextLink", encoded = true) String nextLink,
            @HostParam("endpoint") String endpoint, @HeaderParam("Accept") String accept, RequestOptions requestOptions,
            Context context);

        @Get("{nextLink}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> listOperationsNextSync(@PathParam(value = "nextLink", encoded = true) String nextLink,
            @HostParam("endpoint") String endpoint, @HeaderParam("Accept") String accept, RequestOptions requestOptions,
            Context context);
    }

    /**
     * Lists all operations.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     operationId: String (Required)
     *     status: String(notStarted/running/failed/succeeded/canceled) (Required)
     *     percentCompleted: Integer (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     lastUpdatedDateTime: OffsetDateTime (Required)
     *     kind: String(documentModelBuild/documentModelCompose/documentModelCopyTo) (Required)
     *     resourceLocation: String (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return list Operations response object along with {@link PagedResponse} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<PagedResponse<BinaryData>> listOperationsSinglePageAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.listOperations(this.client.getEndpoint(),
                this.client.getServiceVersion().getVersion(), accept, requestOptions, context))
            .map(res -> new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
                getValues(res.getValue(), "value"), getNextLink(res.getValue(), "nextLink"), null));
    }

    /**
     * Lists all operations.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     operationId: String (Required)
     *     status: String(notStarted/running/failed/succeeded/canceled) (Required)
     *     percentCompleted: Integer (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     lastUpdatedDateTime: OffsetDateTime (Required)
     *     kind: String(documentModelBuild/documentModelCompose/documentModelCopyTo) (Required)
     *     resourceLocation: String (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return list Operations response object as paginated response with {@link PagedFlux}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listOperationsAsync(RequestOptions requestOptions) {
        RequestOptions requestOptionsForNextPage = new RequestOptions();
        requestOptionsForNextPage.setContext(
            requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext() : Context.NONE);
        return new PagedFlux<>(() -> listOperationsSinglePageAsync(requestOptions),
            nextLink -> listOperationsNextSinglePageAsync(nextLink, requestOptionsForNextPage));
    }

    /**
     * Lists all operations.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     operationId: String (Required)
     *     status: String(notStarted/running/failed/succeeded/canceled) (Required)
     *     percentCompleted: Integer (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     lastUpdatedDateTime: OffsetDateTime (Required)
     *     kind: String(documentModelBuild/documentModelCompose/documentModelCopyTo) (Required)
     *     resourceLocation: String (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return list Operations response object along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<BinaryData> listOperationsSinglePage(RequestOptions requestOptions) {
        final String accept = "application/json";
        Response<BinaryData> res = service.listOperationsSync(this.client.getEndpoint(),
            this.client.getServiceVersion().getVersion(), accept, requestOptions, Context.NONE);
        return new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
            getValues(res.getValue(), "value"), getNextLink(res.getValue(), "nextLink"), null);
    }

    /**
     * Lists all operations.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     operationId: String (Required)
     *     status: String(notStarted/running/failed/succeeded/canceled) (Required)
     *     percentCompleted: Integer (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     lastUpdatedDateTime: OffsetDateTime (Required)
     *     kind: String(documentModelBuild/documentModelCompose/documentModelCopyTo) (Required)
     *     resourceLocation: String (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return list Operations response object as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listOperations(RequestOptions requestOptions) {
        RequestOptions requestOptionsForNextPage = new RequestOptions();
        requestOptionsForNextPage.setContext(
            requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext() : Context.NONE);
        return new PagedIterable<>(() -> listOperationsSinglePage(requestOptions),
            nextLink -> listOperationsNextSinglePage(nextLink, requestOptionsForNextPage));
    }

    /**
     * Gets operation info.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     kind: String (Required)
     *     operationId: String (Required)
     *     status: String(notStarted/running/failed/succeeded/canceled) (Required)
     *     percentCompleted: Integer (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     lastUpdatedDateTime: OffsetDateTime (Required)
     *     resourceLocation: String (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     *         innererror (Optional): {
     *             code: String (Required)
     *             message: String (Optional)
     *             innererror (Optional): (recursive schema, see innererror above)
     *         }
     *     }
     * }
     * }
     * </pre>
     * 
     * @param operationId Unique operation ID.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return operation info along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getOperationWithResponseAsync(String operationId, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getOperation(this.client.getEndpoint(), operationId,
            this.client.getServiceVersion().getVersion(), accept, requestOptions, context));
    }

    /**
     * Gets operation info.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     kind: String (Required)
     *     operationId: String (Required)
     *     status: String(notStarted/running/failed/succeeded/canceled) (Required)
     *     percentCompleted: Integer (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     lastUpdatedDateTime: OffsetDateTime (Required)
     *     resourceLocation: String (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     *         innererror (Optional): {
     *             code: String (Required)
     *             message: String (Optional)
     *             innererror (Optional): (recursive schema, see innererror above)
     *         }
     *     }
     * }
     * }
     * </pre>
     * 
     * @param operationId Unique operation ID.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return operation info along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getOperationWithResponse(String operationId, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getOperationSync(this.client.getEndpoint(), operationId,
            this.client.getServiceVersion().getVersion(), accept, requestOptions, Context.NONE);
    }

    /**
     * Return information about the current resource.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     customDocumentModels (Required): {
     *         count: int (Required)
     *         limit: int (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return general information regarding the current resource along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getResourceInfoWithResponseAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getResourceInfo(this.client.getEndpoint(),
            this.client.getServiceVersion().getVersion(), accept, requestOptions, context));
    }

    /**
     * Return information about the current resource.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     customDocumentModels (Required): {
     *         count: int (Required)
     *         limit: int (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return general information regarding the current resource along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getResourceInfoWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getResourceInfoSync(this.client.getEndpoint(), this.client.getServiceVersion().getVersion(),
            accept, requestOptions, Context.NONE);
    }

    /**
     * Get the next page of items.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     operationId: String (Required)
     *     status: String(notStarted/running/failed/succeeded/canceled) (Required)
     *     percentCompleted: Integer (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     lastUpdatedDateTime: OffsetDateTime (Required)
     *     kind: String(documentModelBuild/documentModelCompose/documentModelCopyTo) (Required)
     *     resourceLocation: String (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param nextLink The URL to get the next list of items.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return list Operations response object along with {@link PagedResponse} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<PagedResponse<BinaryData>> listOperationsNextSinglePageAsync(String nextLink,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
            context -> service.listOperationsNext(nextLink, this.client.getEndpoint(), accept, requestOptions, context))
            .map(res -> new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
                getValues(res.getValue(), "value"), getNextLink(res.getValue(), "nextLink"), null));
    }

    /**
     * Get the next page of items.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     operationId: String (Required)
     *     status: String(notStarted/running/failed/succeeded/canceled) (Required)
     *     percentCompleted: Integer (Optional)
     *     createdDateTime: OffsetDateTime (Required)
     *     lastUpdatedDateTime: OffsetDateTime (Required)
     *     kind: String(documentModelBuild/documentModelCompose/documentModelCopyTo) (Required)
     *     resourceLocation: String (Required)
     *     apiVersion: String (Optional)
     *     tags (Optional): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param nextLink The URL to get the next list of items.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return list Operations response object along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<BinaryData> listOperationsNextSinglePage(String nextLink, RequestOptions requestOptions) {
        final String accept = "application/json";
        Response<BinaryData> res
            = service.listOperationsNextSync(nextLink, this.client.getEndpoint(), accept, requestOptions, Context.NONE);
        return new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
            getValues(res.getValue(), "value"), getNextLink(res.getValue(), "nextLink"), null);
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
