// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
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
import fixtures.bodycomplex.implementation.models.Fish;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Polymorphicrecursives. */
public final class PolymorphicrecursivesImpl {
    /** The proxy service used to perform REST calls. */
    private final PolymorphicrecursivesService service;

    /** The service client containing this operation class. */
    private final AutoRestComplexTestServiceImpl client;

    /**
     * Initializes an instance of PolymorphicrecursivesImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    PolymorphicrecursivesImpl(AutoRestComplexTestServiceImpl client) {
        this.service =
                RestProxy.create(
                        PolymorphicrecursivesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServicePolymorphicrecursives to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestS")
    private interface PolymorphicrecursivesService {
        @Get("/complex/polymorphicrecursive/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Fish>> getValid(@HostParam("$host") String host, Context context);

        @Put("/complex/polymorphicrecursive/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(
                @HostParam("$host") String host, @BodyParam("application/json") Fish complexBody, Context context);
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Fish>> getValidWithResponseAsync() {
        return FluxUtil.withContext(context -> service.getValid(this.client.getHost(), context));
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Fish>> getValidWithResponseAsync(Context context) {
        return service.getValid(this.client.getHost(), context);
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getValidAsync() {
        return getValidWithResponseAsync()
                .flatMap(
                        (Response<Fish> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getValidAsync(Context context) {
        return getValidWithResponseAsync(context)
                .flatMap(
                        (Response<Fish> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Fish getValid() {
        return getValidAsync().block();
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Fish> getValidWithResponse(Context context) {
        return getValidWithResponseAsync(context).block();
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Fish complexBody) {
        return FluxUtil.withContext(context -> service.putValid(this.client.getHost(), complexBody, context));
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Fish complexBody, Context context) {
        return service.putValid(this.client.getHost(), complexBody, context);
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Fish complexBody) {
        return putValidWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Fish complexBody, Context context) {
        return putValidWithResponseAsync(complexBody, context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(Fish complexBody) {
        putValidAsync(complexBody).block();
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     *
     * @param complexBody Please put a salmon that looks like this: { "fishtype": "salmon", "species": "king", "length":
     *     1, "age": 1, "location": "alaska", "iswild": true, "siblings": [ { "fishtype": "shark", "species":
     *     "predator", "length": 20, "age": 6, "siblings": [ { "fishtype": "salmon", "species": "coho", "length": 2,
     *     "age": 2, "location": "atlantic", "iswild": true, "siblings": [ { "fishtype": "shark", "species": "predator",
     *     "length": 20, "age": 6 }, { "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, {
     *     "fishtype": "sawshark", "species": "dangerous", "length": 10, "age": 105 } ] }, { "fishtype": "sawshark",
     *     "species": "dangerous", "length": 10, "age": 105 } ] }.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(Fish complexBody, Context context) {
        return putValidWithResponseAsync(complexBody, context).block();
    }
}
