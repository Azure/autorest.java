// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation;

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
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodycomplex.implementation.models.ErrorException;
import fixtures.bodycomplex.implementation.models.ReadonlyObj;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Readonlyproperties. */
public final class ReadonlypropertiesImpl {
    /** The proxy service used to perform REST calls. */
    private final ReadonlypropertiesService service;

    /** The service client containing this operation class. */
    private final AutoRestComplexTestServiceImpl client;

    /**
     * Initializes an instance of ReadonlypropertiesImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    ReadonlypropertiesImpl(AutoRestComplexTestServiceImpl client) {
        this.service =
                RestProxy.create(
                        ReadonlypropertiesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServiceReadonlyproperties to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestS")
    private interface ReadonlypropertiesService {
        @Get("/complex/readonlyproperty/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<ReadonlyObj>> getValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/readonlyproperty/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") ReadonlyObj complexBody,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Get complex types that have readonly properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that have readonly properties along with {@link Response} on successful completion of
     *     {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ReadonlyObj>> getValidWithResponseAsync() {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getValid(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types that have readonly properties.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that have readonly properties along with {@link Response} on successful completion of
     *     {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ReadonlyObj>> getValidWithResponseAsync(Context context) {
        final String accept = "application/json";
        return service.getValid(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types that have readonly properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that have readonly properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ReadonlyObj> getValidAsync() {
        return getValidWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types that have readonly properties.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that have readonly properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ReadonlyObj> getValidAsync(Context context) {
        return getValidWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types that have readonly properties.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that have readonly properties along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ReadonlyObj> getValidWithResponse(Context context) {
        return getValidWithResponseAsync(context).block();
    }

    /**
     * Get complex types that have readonly properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that have readonly properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ReadonlyObj getValid() {
        return getValidWithResponse(Context.NONE).getValue();
    }

    /**
     * Get complex types that have readonly properties.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that have readonly properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ReadonlyObj getValid(Context context) {
        return getValidWithResponse(context).getValue();
    }

    /**
     * Put complex types that have readonly properties.
     *
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(ReadonlyObj complexBody) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putValid(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types that have readonly properties.
     *
     * @param complexBody The complexBody parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(ReadonlyObj complexBody, Context context) {
        final String accept = "application/json";
        return service.putValid(this.client.getHost(), complexBody, accept, context);
    }

    /**
     * Put complex types that have readonly properties.
     *
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(ReadonlyObj complexBody) {
        return putValidWithResponseAsync(complexBody).flatMap(ignored -> Mono.empty());
    }

    /**
     * Put complex types that have readonly properties.
     *
     * @param complexBody The complexBody parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(ReadonlyObj complexBody, Context context) {
        return putValidWithResponseAsync(complexBody, context).flatMap(ignored -> Mono.empty());
    }

    /**
     * Put complex types that have readonly properties.
     *
     * @param complexBody The complexBody parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(ReadonlyObj complexBody, Context context) {
        return putValidWithResponseAsync(complexBody, context).block();
    }

    /**
     * Put complex types that have readonly properties.
     *
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(ReadonlyObj complexBody) {
        putValidWithResponse(complexBody, Context.NONE);
    }

    /**
     * Put complex types that have readonly properties.
     *
     * @param complexBody The complexBody parameter.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(ReadonlyObj complexBody, Context context) {
        putValidWithResponse(complexBody, context);
    }
}
