// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodyboolean;

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
import fixtures.bodyboolean.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Bools.
 */
public final class Bools {
    /**
     * The proxy service used to perform REST calls.
     */
    private final BoolsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestBoolTestService client;

    /**
     * Initializes an instance of Bools.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Bools(AutoRestBoolTestService client) {
        this.service = RestProxy.create(BoolsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestBoolTestServiceBools to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestBoolTestServiceBools")
    public interface BoolsService {
        @Get("/bool/true")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Boolean>> getTrue(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Put("/bool/true")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putTrue(@HostParam("$host") String host, @BodyParam("application/json") boolean boolBody,
            @HeaderParam("Accept") String accept, Context context);

        @Get("/bool/false")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Boolean>> getFalse(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Put("/bool/false")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFalse(@HostParam("$host") String host, @BodyParam("application/json") boolean boolBody,
            @HeaderParam("Accept") String accept, Context context);

        @Get("/bool/null")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Boolean>> getNull(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Get("/bool/invalid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Boolean>> getInvalid(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);
    }

    /**
     * Get true Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getTrueWithResponseAsync() {
        return FluxUtil.withContext(context -> getTrueWithResponseAsync(context));
    }

    /**
     * Get true Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getTrueWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getTrue(this.client.getHost(), accept, context);
    }

    /**
     * Get true Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getTrueAsync() {
        return getTrueWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get true Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getTrueAsync(Context context) {
        return getTrueWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get true Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getTrueWithResponse(Context context) {
        return getTrueWithResponseAsync(context).block();
    }

    /**
     * Get true Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getTrue() {
        return getTrueWithResponse(Context.NONE).getValue();
    }

    /**
     * Set Boolean value true.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putTrueWithResponseAsync() {
        return FluxUtil.withContext(context -> putTrueWithResponseAsync(context));
    }

    /**
     * Set Boolean value true.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putTrueWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final boolean boolBody = true;
        final String accept = "application/json";
        return service.putTrue(this.client.getHost(), boolBody, accept, context);
    }

    /**
     * Set Boolean value true.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putTrueAsync() {
        return putTrueWithResponseAsync().flatMap(ignored -> Mono.empty());
    }

    /**
     * Set Boolean value true.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putTrueAsync(Context context) {
        return putTrueWithResponseAsync(context).flatMap(ignored -> Mono.empty());
    }

    /**
     * Set Boolean value true.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putTrueWithResponse(Context context) {
        return putTrueWithResponseAsync(context).block();
    }

    /**
     * Set Boolean value true.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putTrue() {
        putTrueWithResponse(Context.NONE);
    }

    /**
     * Get false Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getFalseWithResponseAsync() {
        return FluxUtil.withContext(context -> getFalseWithResponseAsync(context));
    }

    /**
     * Get false Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getFalseWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getFalse(this.client.getHost(), accept, context);
    }

    /**
     * Get false Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getFalseAsync() {
        return getFalseWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get false Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getFalseAsync(Context context) {
        return getFalseWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get false Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getFalseWithResponse(Context context) {
        return getFalseWithResponseAsync(context).block();
    }

    /**
     * Get false Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getFalse() {
        return getFalseWithResponse(Context.NONE).getValue();
    }

    /**
     * Set Boolean value false.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFalseWithResponseAsync() {
        return FluxUtil.withContext(context -> putFalseWithResponseAsync(context));
    }

    /**
     * Set Boolean value false.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFalseWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final boolean boolBody = false;
        final String accept = "application/json";
        return service.putFalse(this.client.getHost(), boolBody, accept, context);
    }

    /**
     * Set Boolean value false.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFalseAsync() {
        return putFalseWithResponseAsync().flatMap(ignored -> Mono.empty());
    }

    /**
     * Set Boolean value false.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFalseAsync(Context context) {
        return putFalseWithResponseAsync(context).flatMap(ignored -> Mono.empty());
    }

    /**
     * Set Boolean value false.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putFalseWithResponse(Context context) {
        return putFalseWithResponseAsync(context).block();
    }

    /**
     * Set Boolean value false.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFalse() {
        putFalseWithResponse(Context.NONE);
    }

    /**
     * Get null Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getNullWithResponseAsync() {
        return FluxUtil.withContext(context -> getNullWithResponseAsync(context));
    }

    /**
     * Get null Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getNullWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getNull(this.client.getHost(), accept, context);
    }

    /**
     * Get null Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getNullAsync() {
        return getNullWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get null Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getNullAsync(Context context) {
        return getNullWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get null Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getNullWithResponse(Context context) {
        return getNullWithResponseAsync(context).block();
    }

    /**
     * Get null Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getNull() {
        return getNullWithResponse(Context.NONE).getValue();
    }

    /**
     * Get invalid Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getInvalidWithResponseAsync() {
        return FluxUtil.withContext(context -> getInvalidWithResponseAsync(context));
    }

    /**
     * Get invalid Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getInvalidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getInvalid(this.client.getHost(), accept, context);
    }

    /**
     * Get invalid Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getInvalidAsync() {
        return getInvalidWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get invalid Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getInvalidAsync(Context context) {
        return getInvalidWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get invalid Boolean value.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getInvalidWithResponse(Context context) {
        return getInvalidWithResponseAsync(context).block();
    }

    /**
     * Get invalid Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getInvalid() {
        return getInvalidWithResponse(Context.NONE).getValue();
    }
}
