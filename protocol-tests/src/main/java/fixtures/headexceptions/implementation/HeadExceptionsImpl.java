// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.headexceptions.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.headexceptions.AutoRestHeadExceptionTestServiceVersion;
import fixtures.headexceptions.models.CustomizedException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in HeadExceptions.
 */
public final class HeadExceptionsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final HeadExceptionsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestHeadExceptionTestServiceClientImpl client;

    /**
     * Initializes an instance of HeadExceptionsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    HeadExceptionsImpl(AutoRestHeadExceptionTestServiceClientImpl client) {
        this.service
            = RestProxy.create(HeadExceptionsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * Gets Service version.
     * 
     * @return the serviceVersion value.
     */
    public AutoRestHeadExceptionTestServiceVersion getServiceVersion() {
        return client.getServiceVersion();
    }

    /**
     * The interface defining all the services for AutoRestHeadExceptionTestServiceHeadExceptions to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHeadExceptionTestServiceHeadExceptions")
    public interface HeadExceptionsService {
        @Head("/http/success/200")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(CustomizedException.class)
        Mono<Response<Void>> head200(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/success/200")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(CustomizedException.class)
        Response<Void> head200Sync(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/success/204")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(CustomizedException.class)
        Mono<Response<Void>> head204(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/success/204")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(CustomizedException.class)
        Response<Void> head204Sync(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/success/404")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(CustomizedException.class)
        Mono<Response<Void>> head404(@HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Head("/http/success/404")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(value = ClientAuthenticationException.class, code = { 401 })
        @UnexpectedResponseExceptionType(value = ResourceNotFoundException.class, code = { 404 })
        @UnexpectedResponseExceptionType(value = ResourceModifiedException.class, code = { 409 })
        @UnexpectedResponseExceptionType(CustomizedException.class)
        Response<Void> head404Sync(@HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Return 200 status code if successful.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws CustomizedException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head200(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 200 status code if successful.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws CustomizedException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head200WithResponse(RequestOptions requestOptions) {
        return service.head200Sync(this.client.getHost(), requestOptions, Context.NONE);
    }

    /**
     * Return 204 status code if successful.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws CustomizedException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head204WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head204(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 204 status code if successful.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws CustomizedException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head204WithResponse(RequestOptions requestOptions) {
        return service.head204Sync(this.client.getHost(), requestOptions, Context.NONE);
    }

    /**
     * Return 404 status code if successful.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws CustomizedException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head404WithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.head404(this.client.getHost(), requestOptions, context));
    }

    /**
     * Return 404 status code if successful.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws CustomizedException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head404WithResponse(RequestOptions requestOptions) {
        return service.head404Sync(this.client.getHost(), requestOptions, Context.NONE);
    }
}
