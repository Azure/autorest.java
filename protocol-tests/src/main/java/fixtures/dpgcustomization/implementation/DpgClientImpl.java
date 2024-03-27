// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.dpgcustomization.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
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
import com.azure.core.util.polling.PollingStrategyOptions;
import com.azure.core.util.polling.SyncDefaultPollingStrategy;
import com.azure.core.util.polling.SyncPoller;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.TypeReference;
import fixtures.dpgcustomization.DpgServiceVersion;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the DpgClient type.
 */
public final class DpgClientImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final DpgClientService service;

    /**
     * server parameter.
     */
    private final String host;

    /**
     * Gets server parameter.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Service version.
     */
    private final DpgServiceVersion serviceVersion;

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public DpgServiceVersion getServiceVersion() {
        return this.serviceVersion;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The serializer to serialize an object into a string.
     */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     * 
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /**
     * Initializes an instance of DpgClient client.
     * 
     * @param host server parameter.
     * @param serviceVersion Service version.
     */
    public DpgClientImpl(String host, DpgServiceVersion serviceVersion) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), host, serviceVersion);
    }

    /**
     * Initializes an instance of DpgClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     * @param serviceVersion Service version.
     */
    public DpgClientImpl(HttpPipeline httpPipeline, String host, DpgServiceVersion serviceVersion) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host, serviceVersion);
    }

    /**
     * Initializes an instance of DpgClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     * @param serviceVersion Service version.
     */
    public DpgClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host,
        DpgServiceVersion serviceVersion) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.serviceVersion = serviceVersion;
        this.service = RestProxy.create(DpgClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for DpgClient to be used by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "DpgClient")
    public interface DpgClientService {
        @Get("/customization/model/{mode}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getModel(@HostParam("$host") String host,
            @PathParam(value = "mode", encoded = true) String mode, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/customization/model/{mode}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getModelSync(@HostParam("$host") String host,
            @PathParam(value = "mode", encoded = true) String mode, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Post("/customization/model/{mode}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> postModel(@HostParam("$host") String host,
            @PathParam(value = "mode", encoded = true) String mode, @BodyParam("application/json") BinaryData input,
            @HeaderParam("Accept") String accept, RequestOptions requestOptions, Context context);

        @Post("/customization/model/{mode}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> postModelSync(@HostParam("$host") String host,
            @PathParam(value = "mode", encoded = true) String mode, @BodyParam("application/json") BinaryData input,
            @HeaderParam("Accept") String accept, RequestOptions requestOptions, Context context);

        @Get("/customization/paging/{mode}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getPages(@HostParam("$host") String host,
            @PathParam(value = "mode", encoded = true) String mode, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("/customization/paging/{mode}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getPagesSync(@HostParam("$host") String host,
            @PathParam(value = "mode", encoded = true) String mode, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Put("/customization/lro/{mode}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> lro(@HostParam("$host") String host,
            @PathParam(value = "mode", encoded = true) String mode, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Put("/customization/lro/{mode}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> lroSync(@HostParam("$host") String host,
            @PathParam(value = "mode", encoded = true) String mode, @HeaderParam("Accept") String accept,
            RequestOptions requestOptions, Context context);

        @Get("{nextLink}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<BinaryData>> getPagesNext(@PathParam(value = "nextLink", encoded = true) String nextLink,
            @HostParam("$host") String host, @HeaderParam("Accept") String accept, RequestOptions requestOptions,
            Context context);

        @Get("{nextLink}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Response<BinaryData> getPagesNextSync(@PathParam(value = "nextLink", encoded = true) String nextLink,
            @HostParam("$host") String host, @HeaderParam("Accept") String accept, RequestOptions requestOptions,
            Context context);
    }

    /**
     * Get models that you will either return to end users as a raw body, or with a model added during grow up.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that you will either return to end users as a raw body, or with a model added during grow up along
     * with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getModelWithResponseAsync(String mode, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getModel(this.getHost(), mode, accept, requestOptions, context));
    }

    /**
     * Get models that you will either return to end users as a raw body, or with a model added during grow up.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that you will either return to end users as a raw body, or with a model added during grow up along
     * with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getModelWithResponse(String mode, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getModelSync(this.getHost(), mode, accept, requestOptions, Context.NONE);
    }

    /**
     * Post either raw response as a model and pass in 'raw' for mode, or grow up your operation to take a model
     * instead, and put in 'model' as mode.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     hello: String (Required)
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param input Please put {'hello': 'world!'}.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> postModelWithResponseAsync(String mode, BinaryData input,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.postModel(this.getHost(), mode, input, accept, requestOptions, context));
    }

    /**
     * Post either raw response as a model and pass in 'raw' for mode, or grow up your operation to take a model
     * instead, and put in 'model' as mode.
     * <p><strong>Request Body Schema</strong></p>
     * <pre>{@code
     * {
     *     hello: String (Required)
     * }
     * }</pre>
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param input Please put {'hello': 'world!'}.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> postModelWithResponse(String mode, BinaryData input, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.postModelSync(this.getHost(), mode, input, accept, requestOptions, Context.NONE);
    }

    /**
     * Get pages that you will either return to users in pages of raw bodies, or pages of models following growup.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return pages that you will either return to users in pages of raw bodies, or pages of models following growup
     * along with {@link PagedResponse} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<PagedResponse<BinaryData>> getPagesSinglePageAsync(String mode, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getPages(this.getHost(), mode, accept, requestOptions, context))
            .map(res -> new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
                getValues(res.getValue(), "values"), getNextLink(res.getValue(), "nextLink"), null));
    }

    /**
     * Get pages that you will either return to users in pages of raw bodies, or pages of models following growup.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return pages that you will either return to users in pages of raw bodies, or pages of models following growup as
     * paginated response with {@link PagedFlux}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<BinaryData> getPagesAsync(String mode, RequestOptions requestOptions) {
        RequestOptions requestOptionsForNextPage = new RequestOptions();
        requestOptionsForNextPage.setContext(
            requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext() : Context.NONE);
        return new PagedFlux<>(() -> getPagesSinglePageAsync(mode, requestOptions),
            nextLink -> getPagesNextSinglePageAsync(nextLink, requestOptionsForNextPage));
    }

    /**
     * Get pages that you will either return to users in pages of raw bodies, or pages of models following growup.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return pages that you will either return to users in pages of raw bodies, or pages of models following growup
     * along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<BinaryData> getPagesSinglePage(String mode, RequestOptions requestOptions) {
        final String accept = "application/json";
        Response<BinaryData> res = service.getPagesSync(this.getHost(), mode, accept, requestOptions, Context.NONE);
        return new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
            getValues(res.getValue(), "values"), getNextLink(res.getValue(), "nextLink"), null);
    }

    /**
     * Get pages that you will either return to users in pages of raw bodies, or pages of models following growup.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return pages that you will either return to users in pages of raw bodies, or pages of models following growup as
     * paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> getPages(String mode, RequestOptions requestOptions) {
        RequestOptions requestOptionsForNextPage = new RequestOptions();
        requestOptionsForNextPage.setContext(
            requestOptions != null && requestOptions.getContext() != null ? requestOptions.getContext() : Context.NONE);
        return new PagedIterable<>(() -> getPagesSinglePage(mode, requestOptions),
            nextLink -> getPagesNextSinglePage(nextLink, requestOptionsForNextPage));
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<BinaryData>> lroWithResponseAsync(String mode, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.lro(this.getHost(), mode, accept, requestOptions, context));
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Response<BinaryData> lroWithResponse(String mode, RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.lroSync(this.getHost(), mode, accept, requestOptions, Context.NONE);
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginLroAsync(String mode, RequestOptions requestOptions) {
        return PollerFlux.create(Duration.ofSeconds(1), () -> this.lroWithResponseAsync(mode, requestOptions),
            new DefaultPollingStrategy<>(new PollingStrategyOptions(this.getHttpPipeline())

                .setContext(requestOptions != null && requestOptions.getContext() != null
                    ? requestOptions.getContext()
                    : Context.NONE)),
            TypeReference.createInstance(BinaryData.class), TypeReference.createInstance(BinaryData.class));
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     * 
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     * and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginLro(String mode, RequestOptions requestOptions) {
        return SyncPoller.createPoller(Duration.ofSeconds(1), () -> this.lroWithResponse(mode, requestOptions),
            new SyncDefaultPollingStrategy<>(new PollingStrategyOptions(this.getHttpPipeline())

                .setContext(requestOptions != null && requestOptions.getContext() != null
                    ? requestOptions.getContext()
                    : Context.NONE)),
            TypeReference.createInstance(BinaryData.class), TypeReference.createInstance(BinaryData.class));
    }

    /**
     * Get the next page of items.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
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
     * @return the response body along with {@link PagedResponse} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<PagedResponse<BinaryData>> getPagesNextSinglePageAsync(String nextLink,
        RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.getPagesNext(nextLink, this.getHost(), accept, requestOptions, context))
            .map(res -> new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
                getValues(res.getValue(), "values"), getNextLink(res.getValue(), "nextLink"), null));
    }

    /**
     * Get the next page of items.
     * <p><strong>Response Body Schema</strong></p>
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
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
     * @return the response body along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<BinaryData> getPagesNextSinglePage(String nextLink, RequestOptions requestOptions) {
        final String accept = "application/json";
        Response<BinaryData> res
            = service.getPagesNextSync(nextLink, this.getHost(), accept, requestOptions, Context.NONE);
        return new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(),
            getValues(res.getValue(), "values"), getNextLink(res.getValue(), "nextLink"), null);
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
