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
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodycomplex.models.ErrorException;
import fixtures.bodycomplex.models.Fish;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Polymorphicrecursives.
 */
public final class Polymorphicrecursives {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PolymorphicrecursivesService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestComplexTestService client;

    /**
     * Initializes an instance of Polymorphicrecursives.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Polymorphicrecursives(AutoRestComplexTestService client) {
        this.service = RestProxy.create(PolymorphicrecursivesService.class, client.getHttpPipeline(),
            client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServicePolymorphicrecursives to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServicePolymorphicrecursives")
    public interface PolymorphicrecursivesService {
        @Get("/complex/polymorphicrecursive/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Fish>> getValid(@HostParam("$host") String host, @HeaderParam("Accept") String accept,
            Context context);

        @Put("/complex/polymorphicrecursive/valid")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String host, @BodyParam("application/json") Fish complexBody,
            @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Fish>> getValidWithResponseAsync() {
        return FluxUtil.withContext(context -> getValidWithResponseAsync(context));
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references along with {@link Response} on
     * successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Fish>> getValidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getValid(this.client.getHost(), accept, context);
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getValidAsync() {
        return getValidWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getValidAsync(Context context) {
        return getValidWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Fish> getValidWithResponse(Context context) {
        return getValidWithResponseAsync(context).block();
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
        return getValidWithResponse(Context.NONE).getValue();
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     * "fishtype": "salmon",
     * "species": "king",
     * "length": 1,
     * "age": 1,
     * "location": "alaska",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6,
     * "siblings": [
     * {
     * "fishtype": "salmon",
     * "species": "coho",
     * "length": 2,
     * "age": 2,
     * "location": "atlantic",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Fish complexBody) {
        return FluxUtil.withContext(context -> putValidWithResponseAsync(complexBody, context));
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     * "fishtype": "salmon",
     * "species": "king",
     * "length": 1,
     * "age": 1,
     * "location": "alaska",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6,
     * "siblings": [
     * {
     * "fishtype": "salmon",
     * "species": "coho",
     * "length": 2,
     * "age": 2,
     * "location": "atlantic",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * }.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Fish complexBody, Context context) {
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
        return service.putValid(this.client.getHost(), complexBody, accept, context);
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     * "fishtype": "salmon",
     * "species": "king",
     * "length": 1,
     * "age": 1,
     * "location": "alaska",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6,
     * "siblings": [
     * {
     * "fishtype": "salmon",
     * "species": "coho",
     * "length": 2,
     * "age": 2,
     * "location": "atlantic",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Fish complexBody) {
        return putValidWithResponseAsync(complexBody).flatMap(ignored -> Mono.empty());
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     * "fishtype": "salmon",
     * "species": "king",
     * "length": 1,
     * "age": 1,
     * "location": "alaska",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6,
     * "siblings": [
     * {
     * "fishtype": "salmon",
     * "species": "coho",
     * "length": 2,
     * "age": 2,
     * "location": "atlantic",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * }.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Fish complexBody, Context context) {
        return putValidWithResponseAsync(complexBody, context).flatMap(ignored -> Mono.empty());
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     * "fishtype": "salmon",
     * "species": "king",
     * "length": 1,
     * "age": 1,
     * "location": "alaska",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6,
     * "siblings": [
     * {
     * "fishtype": "salmon",
     * "species": "coho",
     * "length": 2,
     * "age": 2,
     * "location": "atlantic",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * }.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putValidWithResponse(Fish complexBody, Context context) {
        return putValidWithResponseAsync(complexBody, context).block();
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     * "fishtype": "salmon",
     * "species": "king",
     * "length": 1,
     * "age": 1,
     * "location": "alaska",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6,
     * "siblings": [
     * {
     * "fishtype": "salmon",
     * "species": "coho",
     * "length": 2,
     * "age": 2,
     * "location": "atlantic",
     * "iswild": true,
     * "siblings": [
     * {
     * "fishtype": "shark",
     * "species": "predator",
     * "length": 20,
     * "age": 6
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * },
     * {
     * "fishtype": "sawshark",
     * "species": "dangerous",
     * "length": 10,
     * "age": 105
     * }
     * ]
     * }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(Fish complexBody) {
        putValidWithResponse(complexBody, Context.NONE);
    }
}
