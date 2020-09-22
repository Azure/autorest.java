package fixtures.bodyboolean;

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
import fixtures.bodyboolean.models.ErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Bools. */
public final class Bools {
    /** The proxy service used to perform REST calls. */
    private final BoolsService service;

    /** The service client containing this operation class. */
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
    @ServiceInterface(name = "AutoRestBoolTestServ")
    private interface BoolsService {
        @Get("/bool/true")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Boolean>> getTrue(@HostParam("$host") String host, Context context);

        @Put("/bool/true")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putTrue(
                @HostParam("$host") String host, @BodyParam("application/json") boolean boolBody, Context context);

        @Get("/bool/false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Boolean>> getFalse(@HostParam("$host") String host, Context context);

        @Put("/bool/false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFalse(
                @HostParam("$host") String host, @BodyParam("application/json") boolean boolBody, Context context);

        @Get("/bool/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Boolean>> getNull(@HostParam("$host") String host, Context context);

        @Get("/bool/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Boolean>> getInvalid(@HostParam("$host") String host, Context context);
    }

    /**
     * Get true Boolean value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getTrueWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getTrue(this.client.getHost(), context));
    }

    /**
     * Get true Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Boolean>> getTrueWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return service.getTrue(this.client.getHost(), context);
    }

    /**
     * Get true Boolean value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getTrueAsync() {
        return getTrueWithResponseAsync()
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get true Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getTrueAsync(Context context) {
        return getTrueWithResponseAsync(context)
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
        Boolean value = getTrueAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get true Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getTrueWithResponse(Context context) {
        return getTrueWithResponseAsync(context).block();
    }

    /**
     * Set Boolean value true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putTrueWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final boolean boolBody = true;
        return FluxUtil.withContext(context -> service.putTrue(this.client.getHost(), boolBody, context));
    }

    /**
     * Set Boolean value true.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Void>> putTrueWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final boolean boolBody = true;
        return service.putTrue(this.client.getHost(), boolBody, context);
    }

    /**
     * Set Boolean value true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putTrueAsync() {
        return putTrueWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set Boolean value true.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putTrueAsync(Context context) {
        return putTrueWithResponseAsync(context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set Boolean value true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putTrue() {
        putTrueAsync().block();
    }

    /**
     * Set Boolean value true.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putTrueWithResponse(Context context) {
        return putTrueWithResponseAsync(context).block();
    }

    /**
     * Get false Boolean value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getFalseWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getFalse(this.client.getHost(), context));
    }

    /**
     * Get false Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Boolean>> getFalseWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return service.getFalse(this.client.getHost(), context);
    }

    /**
     * Get false Boolean value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getFalseAsync() {
        return getFalseWithResponseAsync()
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get false Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getFalseAsync(Context context) {
        return getFalseWithResponseAsync(context)
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
        Boolean value = getFalseAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get false Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getFalseWithResponse(Context context) {
        return getFalseWithResponseAsync(context).block();
    }

    /**
     * Set Boolean value false.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFalseWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final boolean boolBody = false;
        return FluxUtil.withContext(context -> service.putFalse(this.client.getHost(), boolBody, context));
    }

    /**
     * Set Boolean value false.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Void>> putFalseWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final boolean boolBody = false;
        return service.putFalse(this.client.getHost(), boolBody, context);
    }

    /**
     * Set Boolean value false.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFalseAsync() {
        return putFalseWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set Boolean value false.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFalseAsync(Context context) {
        return putFalseWithResponseAsync(context).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set Boolean value false.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFalse() {
        putFalseAsync().block();
    }

    /**
     * Set Boolean value false.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putFalseWithResponse(Context context) {
        return putFalseWithResponseAsync(context).block();
    }

    /**
     * Get null Boolean value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), context));
    }

    /**
     * Get null Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Boolean>> getNullWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return service.getNull(this.client.getHost(), context);
    }

    /**
     * Get null Boolean value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getNullAsync() {
        return getNullWithResponseAsync()
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get null Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getNullAsync(Context context) {
        return getNullWithResponseAsync(context)
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
        Boolean value = getNullAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get null Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getNullWithResponse(Context context) {
        return getNullWithResponseAsync(context).block();
    }

    /**
     * Get invalid Boolean value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Boolean>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getInvalid(this.client.getHost(), context));
    }

    /**
     * Get invalid Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<Boolean>> getInvalidWithResponseAsync(Context context) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return service.getInvalid(this.client.getHost(), context);
    }

    /**
     * Get invalid Boolean value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getInvalidAsync() {
        return getInvalidWithResponseAsync()
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get invalid Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getInvalidAsync(Context context) {
        return getInvalidWithResponseAsync(context)
                .flatMap(
                        (Response<Boolean> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
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
        Boolean value = getInvalidAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get invalid Boolean value.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> getInvalidWithResponse(Context context) {
        return getInvalidWithResponseAsync(context).block();
    }
}
