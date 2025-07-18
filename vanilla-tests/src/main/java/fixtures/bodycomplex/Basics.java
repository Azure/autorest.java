// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodycomplex.models.Basic;
import fixtures.bodycomplex.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Basics.
 */
public final class Basics {
    /**
     * The proxy service used to perform REST calls.
     */
    private final BasicsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestComplexTestService client;

    /**
     * Initializes an instance of Basics.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Basics(AutoRestComplexTestService client) {
        this.service = RestProxy.create(BasicsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServiceBasics to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServiceBasics")
    public interface BasicsService {
        @Get("/complex/basic/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Basic>> getValid(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Put("/complex/basic/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String host, @QueryParam("api-version") String apiVersion,
            @BodyParam("application/json") Basic complexBody, @HeaderParam("Accept") String accept, Context context);

        @Get("/complex/basic/invalid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Basic>> getInvalid(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Get("/complex/basic/empty")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Basic>> getEmpty(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Get("/complex/basic/null")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Basic>> getNull(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Get("/complex/basic/notprovided")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Basic>> getNotProvided(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getValidWithResponseAsync() {
        return FluxUtil.withContext(context -> getValidWithResponseAsync(context));
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} along with {@link Response} on successful completion
     * of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getValid(this.client.getHost(), accept, context);
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getValidAsync() {
        return getValidWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getValidAsync(Context context) {
        return getValidWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'} along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Basic> getValidWithResponse(Context context) {
        return getValidWithResponseAsync(context).block();
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getValid() {
        return getValidWithResponse(Context.NONE).getValue();
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Basic complexBody) {
        return FluxUtil.withContext(context -> putValidWithResponseAsync(complexBody, context));
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Basic complexBody, Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return service.putValid(this.client.getHost(), this.client.getApiVersion(), complexBody, accept, context);
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Basic complexBody) {
        return putValidWithResponseAsync(complexBody).flatMap(ignored -> Mono.empty());
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Basic complexBody, Context context) {
        return putValidWithResponseAsync(complexBody, context).flatMap(ignored -> Mono.empty());
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(Basic complexBody, Context context) {
        return putValidWithResponseAsync(complexBody, context).block();
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(Basic complexBody) {
        putValidWithResponse(complexBody, Context.NONE);
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is invalid for the local strong type along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getInvalidWithResponseAsync() {
        return FluxUtil.withContext(context -> getInvalidWithResponseAsync(context));
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is invalid for the local strong type along with {@link Response} on successful
     * completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getInvalidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getInvalid(this.client.getHost(), accept, context);
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is invalid for the local strong type on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getInvalidAsync() {
        return getInvalidWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is invalid for the local strong type on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getInvalidAsync(Context context) {
        return getInvalidWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is invalid for the local strong type along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Basic> getInvalidWithResponse(Context context) {
        return getInvalidWithResponseAsync(context).block();
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is invalid for the local strong type.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getInvalid() {
        return getInvalidWithResponse(Context.NONE).getValue();
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is empty along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getEmptyWithResponseAsync() {
        return FluxUtil.withContext(context -> getEmptyWithResponseAsync(context));
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is empty along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getEmptyWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getEmpty(this.client.getHost(), accept, context);
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is empty on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getEmptyAsync() {
        return getEmptyWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is empty on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getEmptyAsync(Context context) {
        return getEmptyWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is empty along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Basic> getEmptyWithResponse(Context context) {
        return getEmptyWithResponseAsync(context).block();
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type that is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getEmpty() {
        return getEmptyWithResponse(Context.NONE).getValue();
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type whose properties are null along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getNullWithResponseAsync() {
        return FluxUtil.withContext(context -> getNullWithResponseAsync(context));
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type whose properties are null along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getNullWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getNull(this.client.getHost(), accept, context);
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type whose properties are null on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getNullAsync() {
        return getNullWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type whose properties are null on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getNullAsync(Context context) {
        return getNullWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type whose properties are null along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Basic> getNullWithResponse(Context context) {
        return getNullWithResponseAsync(context).block();
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type whose properties are null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getNull() {
        return getNullWithResponse(Context.NONE).getValue();
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type while the server doesn't provide a response payload along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getNotProvidedWithResponseAsync() {
        return FluxUtil.withContext(context -> getNotProvidedWithResponseAsync(context));
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type while the server doesn't provide a response payload along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Basic>> getNotProvidedWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getNotProvided(this.client.getHost(), accept, context);
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type while the server doesn't provide a response payload on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getNotProvidedAsync() {
        return getNotProvidedWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type while the server doesn't provide a response payload on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getNotProvidedAsync(Context context) {
        return getNotProvidedWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type while the server doesn't provide a response payload along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Basic> getNotProvidedWithResponse(Context context) {
        return getNotProvidedWithResponseAsync(context).block();
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a basic complex type while the server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getNotProvided() {
        return getNotProvidedWithResponse(Context.NONE).getValue();
    }
}
