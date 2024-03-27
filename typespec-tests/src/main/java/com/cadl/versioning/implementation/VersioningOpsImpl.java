// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.versioning.implementation;

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
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.PollingStrategyOptions;
import com.azure.core.util.polling.PollOperationDetails;
import com.azure.core.util.polling.SyncPoller;
import com.azure.core.util.serializer.TypeReference;
import com.cadl.versioning.VersioningServiceVersion;
import com.cadl.versioning.models.ExportedResource;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in VersioningOps.
 */
public final class VersioningOpsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final VersioningOpsService service;

    /**
     * The service client containing this operation class.
     */
    private final VersioningClientImpl client;

    /**
     * Initializes an instance of VersioningOpsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    VersioningOpsImpl(VersioningClientImpl client) {
        this.service
            = RestProxy.create(VersioningOpsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public VersioningServiceVersion getServiceVersion() {
        return client.getServiceVersion();
    }

    /**
     * The interface defining all the services for VersioningClientVersioningOps to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "VersioningClientVers")
    public interface VersioningOpsService {
        @Post("/versioning/resources/{name}:export")
        @ExpectedResponses({ 202 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> export(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @PathParam("name") String name,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Post("/versioning/resources/{name}:export")
        @ExpectedResponses({ 202 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> exportSync(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @PathParam("name") String name,
            @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/versioning/resources")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> list(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/versioning/resources")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> listSync(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @HeaderParam("accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> listNext(@PathParam(value = "nextLink", encoded = true) String nextLink,
            @HostParam("endpoint") String endpoint, @HeaderParam("accept") String accept, RequestOptions requestOptions,
            Context context);

        @Get("{nextLink}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> listNextSync(@PathParam(value = "nextLink", encoded = true) String nextLink,
            @HostParam("endpoint") String endpoint, @HeaderParam("accept") String accept, RequestOptions requestOptions,
            Context context);
    }

    /**
     * Long-running resource action operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>projectFileVersion</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * <tr><td>projectedFileFormat</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String (Required)
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     * }
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return status details for long running operations along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<BinaryData>> exportWithResponseAsync(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.export(this.client.getEndpoint(),
            this.client.getServiceVersion().getVersion(), name, accept, requestOptions, context));
    }

    /**
     * Long-running resource action operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>projectFileVersion</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * <tr><td>projectedFileFormat</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String (Required)
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     * }
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return status details for long running operations along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Response<BinaryData> exportWithResponse(String name, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.exportSync(this.client.getEndpoint(), this.client.getServiceVersion().getVersion(), name, accept,
            requestOptions, Context.NONE);
    }

    /**
     * Long-running resource action operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>projectFileVersion</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * <tr><td>projectedFileFormat</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String (Required)
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     * }
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of status details for long running operations.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginExportAsync(String name, RequestOptions requestOptions) {
        return PollerFlux.create(Duration.ofSeconds(1), () -> this.exportWithResponseAsync(name, requestOptions),
            new com.azure.core.experimental.util.polling.OperationLocationPollingStrategy<>(
                new PollingStrategyOptions(this.client.getHttpPipeline())
                    .setEndpoint("{endpoint}".replace("{endpoint}", this.client.getEndpoint()))
                    .setContext(requestOptions != null && requestOptions.getContext() != null
                        ? requestOptions.getContext()
                        : Context.NONE)
                    .setServiceVersion(this.client.getServiceVersion().getVersion())),
            TypeReference.createInstance(BinaryData.class), TypeReference.createInstance(BinaryData.class));
    }

    /**
     * Long-running resource action operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>projectFileVersion</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * <tr><td>projectedFileFormat</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String (Required)
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     * }
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of status details for long running operations.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginExport(String name, RequestOptions requestOptions) {
        return SyncPoller.createPoller(Duration.ofSeconds(1), () -> this.exportWithResponse(name, requestOptions),
            new com.azure.core.experimental.util.polling.SyncOperationLocationPollingStrategy<>(
                new PollingStrategyOptions(this.client.getHttpPipeline())
                    .setEndpoint("{endpoint}".replace("{endpoint}", this.client.getEndpoint()))
                    .setContext(requestOptions != null && requestOptions.getContext() != null
                        ? requestOptions.getContext()
                        : Context.NONE)
                    .setServiceVersion(this.client.getServiceVersion().getVersion())),
            TypeReference.createInstance(BinaryData.class), TypeReference.createInstance(BinaryData.class));
    }

    /**
     * Long-running resource action operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>projectFileVersion</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * <tr><td>projectedFileFormat</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String (Required)
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     * }
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of status details for long running operations.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<PollOperationDetails, ExportedResource> beginExportWithModelAsync(String name,
        RequestOptions requestOptions) {
        return PollerFlux.create(Duration.ofSeconds(1), () -> this.exportWithResponseAsync(name, requestOptions),
            new com.azure.core.experimental.util.polling.OperationLocationPollingStrategy<>(
                new PollingStrategyOptions(this.client.getHttpPipeline())
                    .setEndpoint("{endpoint}".replace("{endpoint}", this.client.getEndpoint()))
                    .setContext(requestOptions != null && requestOptions.getContext() != null
                        ? requestOptions.getContext()
                        : Context.NONE)
                    .setServiceVersion(this.client.getServiceVersion().getVersion())),
            TypeReference.createInstance(PollOperationDetails.class),
            TypeReference.createInstance(ExportedResource.class));
    }

    /**
     * Long-running resource action operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>projectFileVersion</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * <tr><td>projectedFileFormat</td><td>String</td><td>No</td><td>A sequence of textual characters.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     status: String (Required)
     *     error (Optional): {
     *         code: String (Required)
     *         message: String (Required)
     *         target: String (Optional)
     *         details (Optional): [
     *             (recursive schema, see above)
     *         ]
     * }
     * }
     * }</pre>
     * 
     * @param name A sequence of textual characters.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of status details for long running operations.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<PollOperationDetails, ExportedResource> beginExportWithModel(String name,
        RequestOptions requestOptions) {
        return SyncPoller.createPoller(Duration.ofSeconds(1), () -> this.exportWithResponse(name, requestOptions),
            new com.azure.core.experimental.util.polling.SyncOperationLocationPollingStrategy<>(
                new PollingStrategyOptions(this.client.getHttpPipeline())
                    .setEndpoint("{endpoint}".replace("{endpoint}", this.client.getEndpoint()))
                    .setContext(requestOptions != null && requestOptions.getContext() != null
                        ? requestOptions.getContext()
                        : Context.NONE)
                    .setServiceVersion(this.client.getServiceVersion().getVersion())),
            TypeReference.createInstance(PollOperationDetails.class),
            TypeReference.createInstance(ExportedResource.class));
    }

    /**
     * Resource list operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>select</td><td>List&lt;String&gt;</td><td>No</td><td>Select the specified fields to be included in the
     * response. Call {@link RequestOptions#addQueryParam} to add string to array.</td></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>Filter the result list using the given expression.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of Resource items along with {@link PagedResponse} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<PagedResponse<BinaryData>> listSinglePageAsync(RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.list(this.client.getEndpoint(),
                this.client.getServiceVersion().getVersion(), accept, requestOptions, context))
            .map(res -> new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
                getValues(res.getValue(), "value"), getNextLink(res.getValue(), "nextLink"), null));
    }

    /**
     * Resource list operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>select</td><td>List&lt;String&gt;</td><td>No</td><td>Select the specified fields to be included in the
     * response. Call {@link RequestOptions#addQueryParam} to add string to array.</td></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>Filter the result list using the given expression.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of Resource items as paginated response with {@link PagedFlux}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> listAsync(RequestOptions requestOptions) {
        RequestOptions requestOptionsForNextPage = new RequestOptions();
        requestOptionsForNextPage.setContext(
            requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext() : Context.NONE);
        return new PagedFlux<>(() -> listSinglePageAsync(requestOptions),
            nextLink -> listNextSinglePageAsync(nextLink, requestOptionsForNextPage));
    }

    /**
     * Resource list operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>select</td><td>List&lt;String&gt;</td><td>No</td><td>Select the specified fields to be included in the
     * response. Call {@link RequestOptions#addQueryParam} to add string to array.</td></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>Filter the result list using the given expression.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of Resource items along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<BinaryData> listSinglePage(RequestOptions requestOptions) {
        final String accept = "application/json";
        Response<BinaryData> res = service.listSync(this.client.getEndpoint(),
            this.client.getServiceVersion().getVersion(), accept, requestOptions, Context.NONE);
        return new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
            getValues(res.getValue(), "value"), getNextLink(res.getValue(), "nextLink"), null);
    }

    /**
     * Resource list operation template.
     * <p><strong>Query Parameters</strong></p>
     * <table border="1">
     * <caption>Query Parameters</caption>
     * <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     * <tr><td>select</td><td>List&lt;String&gt;</td><td>No</td><td>Select the specified fields to be included in the
     * response. Call {@link RequestOptions#addQueryParam} to add string to array.</td></tr>
     * <tr><td>filter</td><td>String</td><td>No</td><td>Filter the result list using the given expression.</td></tr>
     * </table>
     * You can add these to a request with {@link RequestOptions#addQueryParam}
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of Resource items as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> list(RequestOptions requestOptions) {
        RequestOptions requestOptionsForNextPage = new RequestOptions();
        requestOptionsForNextPage.setContext(
            requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext() : Context.NONE);
        return new PagedIterable<>(() -> listSinglePage(requestOptions),
            nextLink -> listNextSinglePage(nextLink, requestOptionsForNextPage));
    }

    /**
     * Get the next page of items.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param nextLink The URL to get the next list of items
     * 
     * The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of Resource items along with {@link PagedResponse} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<PagedResponse<BinaryData>> listNextSinglePageAsync(String nextLink, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.listNext(nextLink, this.client.getEndpoint(), accept, requestOptions, context))
            .map(res -> new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
                getValues(res.getValue(), "value"), getNextLink(res.getValue(), "nextLink"), null));
    }

    /**
     * Get the next page of items.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     id: String (Required)
     *     name: String (Required)
     *     type: String (Required)
     * }
     * }</pre>
     * 
     * @param nextLink The URL to get the next list of items
     * 
     * The nextLink parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of Resource items along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<BinaryData> listNextSinglePage(String nextLink, RequestOptions requestOptions) {
        final String accept = "application/json";
        Response<BinaryData> res
            = service.listNextSync(nextLink, this.client.getEndpoint(), accept, requestOptions, Context.NONE);
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
