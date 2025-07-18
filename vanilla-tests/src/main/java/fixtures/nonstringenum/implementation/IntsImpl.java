// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.nonstringenum.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.nonstringenum.models.IntEnum;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Ints.
 */
public final class IntsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final IntsService service;

    /**
     * The service client containing this operation class.
     */
    private final NonStringEnumsClientImpl client;

    /**
     * Initializes an instance of IntsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    IntsImpl(NonStringEnumsClientImpl client) {
        this.service = RestProxy.create(IntsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for NonStringEnumsClientInts to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "NonStringEnumsClientInts")
    public interface IntsService {
        @Put("/nonStringEnums/int/put")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> put(@HostParam("$host") String host, @BodyParam("application/json") IntEnum input,
            @HeaderParam("Accept") String accept, Context context);

        @Get("/nonStringEnums/int/get")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<IntEnum>> get(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);
    }

    /**
     * Put an int enum.
     * 
     * @param input Input int enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putWithResponseAsync(IntEnum input) {
        return FluxUtil.withContext(context -> putWithResponseAsync(input, context));
    }

    /**
     * Put an int enum.
     * 
     * @param input Input int enum.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putWithResponseAsync(IntEnum input, Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.put(this.client.getHost(), input, accept, context);
    }

    /**
     * Put an int enum.
     * 
     * @param input Input int enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putAsync(IntEnum input) {
        return putWithResponseAsync(input).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Put an int enum.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putAsync() {
        final IntEnum input = null;
        return putWithResponseAsync(input).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Put an int enum.
     * 
     * @param input Input int enum.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putAsync(IntEnum input, Context context) {
        return putWithResponseAsync(input, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Put an int enum.
     * 
     * @param input Input int enum.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<String> putWithResponse(IntEnum input, Context context) {
        return putWithResponseAsync(input, context).block();
    }

    /**
     * Put an int enum.
     * 
     * @param input Input int enum.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String put(IntEnum input) {
        return putWithResponse(input, Context.NONE).getValue();
    }

    /**
     * Put an int enum.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String put() {
        final IntEnum input = null;
        return putWithResponse(input, Context.NONE).getValue();
    }

    /**
     * Get an int enum.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an int enum along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<IntEnum>> getWithResponseAsync() {
        return FluxUtil.withContext(context -> getWithResponseAsync(context));
    }

    /**
     * Get an int enum.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an int enum along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<IntEnum>> getWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.get(this.client.getHost(), accept, context);
    }

    /**
     * Get an int enum.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an int enum on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<IntEnum> getAsync() {
        return getWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get an int enum.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an int enum on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<IntEnum> getAsync(Context context) {
        return getWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get an int enum.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an int enum along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<IntEnum> getWithResponse(Context context) {
        return getWithResponseAsync(context).block();
    }

    /**
     * Get an int enum.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an int enum.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public IntEnum get() {
        return getWithResponse(Context.NONE).getValue();
    }
}
