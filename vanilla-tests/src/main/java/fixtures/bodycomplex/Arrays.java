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
import fixtures.bodycomplex.models.ArrayWrapper;
import fixtures.bodycomplex.models.ErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Arrays. */
public final class Arrays {
    /** The proxy service used to perform REST calls. */
    private final ArraysService service;

    /** The service client containing this operation class. */
    private final AutoRestComplexTestService client;

    /**
     * Initializes an instance of Arrays.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Arrays(AutoRestComplexTestService client) {
        this.service = RestProxy.create(ArraysService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServiceArrays to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestS")
    private interface ArraysService {
        @Get("/complex/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<ArrayWrapper>> getValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") ArrayWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/array/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<ArrayWrapper>> getEmpty(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/array/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(
                @HostParam("$host") String host,
                @BodyParam("application/json") ArrayWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/array/notprovided")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<ArrayWrapper>> getNotProvided(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get complex types with array property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ArrayWrapper>> getValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getValid(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with array property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ArrayWrapper> getValidAsync() {
        return getValidWithResponseAsync()
                .flatMap(
                        (Response<ArrayWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with array property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ArrayWrapper getValid() {
        return getValidAsync().block();
    }

    /**
     * Put complex types with array property.
     *
     * @param complexBody Please put an array with 4 items: "1, 2, 3, 4", "", null, "&amp;S#$(*Y", "The quick brown fox
     *     jumps over the lazy dog".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(ArrayWrapper complexBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putValid(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with array property.
     *
     * @param complexBody Please put an array with 4 items: "1, 2, 3, 4", "", null, "&amp;S#$(*Y", "The quick brown fox
     *     jumps over the lazy dog".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(ArrayWrapper complexBody) {
        return putValidWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with array property.
     *
     * @param complexBody Please put an array with 4 items: "1, 2, 3, 4", "", null, "&amp;S#$(*Y", "The quick brown fox
     *     jumps over the lazy dog".
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(ArrayWrapper complexBody) {
        putValidAsync(complexBody).block();
    }

    /**
     * Get complex types with array property which is empty.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property which is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ArrayWrapper>> getEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getEmpty(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with array property which is empty.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property which is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ArrayWrapper> getEmptyAsync() {
        return getEmptyWithResponseAsync()
                .flatMap(
                        (Response<ArrayWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with array property which is empty.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property which is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ArrayWrapper getEmpty() {
        return getEmptyAsync().block();
    }

    /**
     * Put complex types with array property which is empty.
     *
     * @param complexBody Please put an empty array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(ArrayWrapper complexBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putEmpty(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with array property which is empty.
     *
     * @param complexBody Please put an empty array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(ArrayWrapper complexBody) {
        return putEmptyWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with array property which is empty.
     *
     * @param complexBody Please put an empty array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(ArrayWrapper complexBody) {
        putEmptyAsync(complexBody).block();
    }

    /**
     * Get complex types with array property while server doesn't provide a response payload.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property while server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ArrayWrapper>> getNotProvidedWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getNotProvided(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with array property while server doesn't provide a response payload.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property while server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ArrayWrapper> getNotProvidedAsync() {
        return getNotProvidedWithResponseAsync()
                .flatMap(
                        (Response<ArrayWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with array property while server doesn't provide a response payload.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property while server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ArrayWrapper getNotProvided() {
        return getNotProvidedAsync().block();
    }
}
