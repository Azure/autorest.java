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
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.polling.DefaultPollingStrategy;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.SyncPoller;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.TypeReference;
import fixtures.dpgcustomization.models.LroProduct;
import java.time.Duration;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the DpgClient type. */
public final class DpgClientImpl {
    /** The proxy service used to perform REST calls. */
    private final DpgClientService service;

    /** server parameter. */
    private final String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /** The HTTP pipeline to send requests through. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** The serializer to serialize an object into a string. */
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
     */
    public DpgClientImpl(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of DpgClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    public DpgClientImpl(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of DpgClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    public DpgClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.service = RestProxy.create(DpgClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /** The interface defining all the services for DpgClient to be used by the proxy service to perform REST calls. */
    @Host("{$host}")
    @ServiceInterface(name = "DpgClient")
    private interface DpgClientService {
        @Get("/customization/model/{mode}")
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
        Mono<Response<BinaryData>> getModel(
                @HostParam("$host") String host,
                @PathParam(value = "mode", encoded = true) String mode,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Post("/customization/model/{mode}")
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
        Mono<Response<BinaryData>> postModel(
                @HostParam("$host") String host,
                @PathParam(value = "mode", encoded = true) String mode,
                @BodyParam("application/json") BinaryData input,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Get("/customization/paging/{mode}")
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
        Mono<Response<BinaryData>> getPages(
                @HostParam("$host") String host,
                @PathParam(value = "mode", encoded = true) String mode,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);

        @Put("/customization/lro/{mode}")
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
        Mono<Response<BinaryData>> lro(
                @HostParam("$host") String host,
                @PathParam(value = "mode", encoded = true) String mode,
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
        Mono<Response<BinaryData>> getPagesNext(
                @PathParam(value = "nextLink", encoded = true) String nextLink,
                @HostParam("$host") String host,
                @HeaderParam("Accept") String accept,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Get models that you will either return to end users as a raw body, or with a model added during grow up.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that you will either return to end users as a raw body, or with a model added during grow up along
     *     with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getModelWithResponseAsync(String mode, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getModel(this.getHost(), mode, accept, requestOptions, context));
    }

    /**
     * Get models that you will either return to end users as a raw body, or with a model added during grow up.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return models that you will either return to end users as a raw body, or with a model added during grow up along
     *     with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getModelWithResponse(String mode, RequestOptions requestOptions) {
        return getModelWithResponseAsync(mode, requestOptions).block();
    }

    /**
     * Post either raw response as a model and pass in 'raw' for mode, or grow up your operation to take a model
     * instead, and put in 'model' as mode.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     hello: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param input Please put {'hello': 'world!'}.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> postModelWithResponseAsync(
            String mode, BinaryData input, RequestOptions requestOptions) {
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.postModel(this.getHost(), mode, input, accept, requestOptions, context));
    }

    /**
     * Post either raw response as a model and pass in 'raw' for mode, or grow up your operation to take a model
     * instead, and put in 'model' as mode.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     hello: String (Required)
     * }
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
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
        return postModelWithResponseAsync(mode, input, requestOptions).block();
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
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
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<BinaryData, BinaryData> beginLroAsync(String mode, RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.lroWithResponseAsync(mode, requestOptions),
                new DefaultPollingStrategy<>(
                        this.getHttpPipeline(),
                        null,
                        requestOptions != null && requestOptions.getContext() != null
                                ? requestOptions.getContext()
                                : Context.NONE),
                TypeReference.createInstance(BinaryData.class),
                TypeReference.createInstance(BinaryData.class));
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<BinaryData, BinaryData> beginLro(String mode, RequestOptions requestOptions) {
        return this.beginLroAsync(mode, requestOptions).getSyncPoller();
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link PollerFlux} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public PollerFlux<LroProduct, LroProduct> beginLroWithModelAsync(String mode, RequestOptions requestOptions) {
        return PollerFlux.create(
                Duration.ofSeconds(1),
                () -> this.lroWithResponseAsync(mode, requestOptions),
                new DefaultPollingStrategy<>(
                        this.getHttpPipeline(),
                        null,
                        requestOptions != null && requestOptions.getContext() != null
                                ? requestOptions.getContext()
                                : Context.NONE),
                TypeReference.createInstance(LroProduct.class),
                TypeReference.createInstance(LroProduct.class));
    }

    /**
     * Long running put request that will either return to end users a final payload of a raw body, or a final payload
     * of a model after the SDK has grown up.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     received: String(raw/model) (Required)
     *     provisioningState: String (Required)
     * }
     * }</pre>
     *
     * @param mode The mode with which you'll be handling your returned body. 'raw' for just dealing with the raw body,
     *     and 'model' if you are going to convert the raw body to a customized body before returning to users.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    public SyncPoller<LroProduct, LroProduct> beginLroWithModel(String mode, RequestOptions requestOptions) {
        return this.beginLroWithModelAsync(mode, requestOptions).getSyncPoller();
    }
}
