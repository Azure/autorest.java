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
import fixtures.bodycomplex.models.BooleanWrapper;
import fixtures.bodycomplex.models.ByteWrapper;
import fixtures.bodycomplex.models.DateWrapper;
import fixtures.bodycomplex.models.DatetimeWrapper;
import fixtures.bodycomplex.models.Datetimerfc1123Wrapper;
import fixtures.bodycomplex.models.DoubleWrapper;
import fixtures.bodycomplex.models.DurationWrapper;
import fixtures.bodycomplex.models.ErrorException;
import fixtures.bodycomplex.models.FloatWrapper;
import fixtures.bodycomplex.models.IntWrapper;
import fixtures.bodycomplex.models.LongWrapper;
import fixtures.bodycomplex.models.StringWrapper;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Primitives. */
public final class Primitives {
    /** The proxy service used to perform REST calls. */
    private final PrimitivesService service;

    /** The service client containing this operation class. */
    private final AutoRestComplexTestService client;

    /**
     * Initializes an instance of Primitives.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Primitives(AutoRestComplexTestService client) {
        this.service =
                RestProxy.create(PrimitivesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServicePrimitives to be used by the proxy service
     * to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestS")
    private interface PrimitivesService {
        @Get("/complex/primitive/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<IntWrapper>> getInt(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putInt(
                @HostParam("$host") String host,
                @BodyParam("application/json") IntWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<LongWrapper>> getLong(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLong(
                @HostParam("$host") String host,
                @BodyParam("application/json") LongWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<FloatWrapper>> getFloat(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFloat(
                @HostParam("$host") String host,
                @BodyParam("application/json") FloatWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DoubleWrapper>> getDouble(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDouble(
                @HostParam("$host") String host,
                @BodyParam("application/json") DoubleWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<BooleanWrapper>> getBool(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBool(
                @HostParam("$host") String host,
                @BodyParam("application/json") BooleanWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<StringWrapper>> getString(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putString(
                @HostParam("$host") String host,
                @BodyParam("application/json") StringWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DateWrapper>> getDate(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDate(
                @HostParam("$host") String host,
                @BodyParam("application/json") DateWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DatetimeWrapper>> getDateTime(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTime(
                @HostParam("$host") String host,
                @BodyParam("application/json") DatetimeWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Datetimerfc1123Wrapper>> getDateTimeRfc1123(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeRfc1123(
                @HostParam("$host") String host,
                @BodyParam("application/json") Datetimerfc1123Wrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DurationWrapper>> getDuration(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDuration(
                @HostParam("$host") String host,
                @BodyParam("application/json") DurationWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/primitive/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<ByteWrapper>> getByte(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/primitive/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putByte(
                @HostParam("$host") String host,
                @BodyParam("application/json") ByteWrapper complexBody,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Get complex types with integer properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with integer properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<IntWrapper>> getIntWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getInt(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with integer properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with integer properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<IntWrapper> getIntAsync() {
        return getIntWithResponseAsync()
                .flatMap(
                        (Response<IntWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with integer properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with integer properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public IntWrapper getInt() {
        return getIntAsync().block();
    }

    /**
     * Put complex types with integer properties.
     *
     * @param complexBody Please put -1 and 2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putIntWithResponseAsync(IntWrapper complexBody) {
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
        return FluxUtil.withContext(context -> service.putInt(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with integer properties.
     *
     * @param complexBody Please put -1 and 2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putIntAsync(IntWrapper complexBody) {
        return putIntWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with integer properties.
     *
     * @param complexBody Please put -1 and 2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putInt(IntWrapper complexBody) {
        putIntAsync(complexBody).block();
    }

    /**
     * Get complex types with long properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with long properties along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<LongWrapper>> getLongWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getLong(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with long properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with long properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LongWrapper> getLongAsync() {
        return getLongWithResponseAsync()
                .flatMap(
                        (Response<LongWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with long properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with long properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LongWrapper getLong() {
        return getLongAsync().block();
    }

    /**
     * Put complex types with long properties.
     *
     * @param complexBody Please put 1099511627775 and -999511627788.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLongWithResponseAsync(LongWrapper complexBody) {
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
        return FluxUtil.withContext(context -> service.putLong(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with long properties.
     *
     * @param complexBody Please put 1099511627775 and -999511627788.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLongAsync(LongWrapper complexBody) {
        return putLongWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with long properties.
     *
     * @param complexBody Please put 1099511627775 and -999511627788.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLong(LongWrapper complexBody) {
        putLongAsync(complexBody).block();
    }

    /**
     * Get complex types with float properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with float properties along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<FloatWrapper>> getFloatWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getFloat(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with float properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with float properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<FloatWrapper> getFloatAsync() {
        return getFloatWithResponseAsync()
                .flatMap(
                        (Response<FloatWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with float properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with float properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public FloatWrapper getFloat() {
        return getFloatAsync().block();
    }

    /**
     * Put complex types with float properties.
     *
     * @param complexBody Please put 1.05 and -0.003.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFloatWithResponseAsync(FloatWrapper complexBody) {
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
        return FluxUtil.withContext(context -> service.putFloat(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with float properties.
     *
     * @param complexBody Please put 1.05 and -0.003.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFloatAsync(FloatWrapper complexBody) {
        return putFloatWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with float properties.
     *
     * @param complexBody Please put 1.05 and -0.003.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFloat(FloatWrapper complexBody) {
        putFloatAsync(complexBody).block();
    }

    /**
     * Get complex types with double properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with double properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DoubleWrapper>> getDoubleWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDouble(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with double properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with double properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DoubleWrapper> getDoubleAsync() {
        return getDoubleWithResponseAsync()
                .flatMap(
                        (Response<DoubleWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with double properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with double properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DoubleWrapper getDouble() {
        return getDoubleAsync().block();
    }

    /**
     * Put complex types with double properties.
     *
     * @param complexBody Please put 3e-100 and -0.000000000000000000000000000000000000000000000000000000005.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDoubleWithResponseAsync(DoubleWrapper complexBody) {
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
        return FluxUtil.withContext(context -> service.putDouble(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with double properties.
     *
     * @param complexBody Please put 3e-100 and -0.000000000000000000000000000000000000000000000000000000005.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDoubleAsync(DoubleWrapper complexBody) {
        return putDoubleWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with double properties.
     *
     * @param complexBody Please put 3e-100 and -0.000000000000000000000000000000000000000000000000000000005.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDouble(DoubleWrapper complexBody) {
        putDoubleAsync(complexBody).block();
    }

    /**
     * Get complex types with bool properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with bool properties along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BooleanWrapper>> getBoolWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getBool(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with bool properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with bool properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BooleanWrapper> getBoolAsync() {
        return getBoolWithResponseAsync()
                .flatMap(
                        (Response<BooleanWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with bool properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with bool properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BooleanWrapper getBool() {
        return getBoolAsync().block();
    }

    /**
     * Put complex types with bool properties.
     *
     * @param complexBody Please put true and false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBoolWithResponseAsync(BooleanWrapper complexBody) {
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
        return FluxUtil.withContext(context -> service.putBool(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with bool properties.
     *
     * @param complexBody Please put true and false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBoolAsync(BooleanWrapper complexBody) {
        return putBoolWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with bool properties.
     *
     * @param complexBody Please put true and false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBool(BooleanWrapper complexBody) {
        putBoolAsync(complexBody).block();
    }

    /**
     * Get complex types with string properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with string properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<StringWrapper>> getStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getString(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with string properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with string properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StringWrapper> getStringAsync() {
        return getStringWithResponseAsync()
                .flatMap(
                        (Response<StringWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with string properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with string properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public StringWrapper getString() {
        return getStringAsync().block();
    }

    /**
     * Put complex types with string properties.
     *
     * @param complexBody Please put 'goodrequest', '', and null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringWithResponseAsync(StringWrapper complexBody) {
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
        return FluxUtil.withContext(context -> service.putString(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with string properties.
     *
     * @param complexBody Please put 'goodrequest', '', and null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putStringAsync(StringWrapper complexBody) {
        return putStringWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with string properties.
     *
     * @param complexBody Please put 'goodrequest', '', and null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putString(StringWrapper complexBody) {
        putStringAsync(complexBody).block();
    }

    /**
     * Get complex types with date properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with date properties along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DateWrapper>> getDateWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDate(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with date properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with date properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DateWrapper> getDateAsync() {
        return getDateWithResponseAsync()
                .flatMap(
                        (Response<DateWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with date properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with date properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DateWrapper getDate() {
        return getDateAsync().block();
    }

    /**
     * Put complex types with date properties.
     *
     * @param complexBody Please put '0001-01-01' and '2016-02-29'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateWithResponseAsync(DateWrapper complexBody) {
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
        return FluxUtil.withContext(context -> service.putDate(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with date properties.
     *
     * @param complexBody Please put '0001-01-01' and '2016-02-29'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateAsync(DateWrapper complexBody) {
        return putDateWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with date properties.
     *
     * @param complexBody Please put '0001-01-01' and '2016-02-29'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDate(DateWrapper complexBody) {
        putDateAsync(complexBody).block();
    }

    /**
     * Get complex types with datetime properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetime properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DatetimeWrapper>> getDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDateTime(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with datetime properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetime properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DatetimeWrapper> getDateTimeAsync() {
        return getDateTimeWithResponseAsync()
                .flatMap(
                        (Response<DatetimeWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with datetime properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetime properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DatetimeWrapper getDateTime() {
        return getDateTimeAsync().block();
    }

    /**
     * Put complex types with datetime properties.
     *
     * @param complexBody Please put '0001-01-01T12:00:00-04:00' and '2015-05-18T11:38:00-08:00'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeWithResponseAsync(DatetimeWrapper complexBody) {
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
        return FluxUtil.withContext(
                context -> service.putDateTime(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with datetime properties.
     *
     * @param complexBody Please put '0001-01-01T12:00:00-04:00' and '2015-05-18T11:38:00-08:00'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeAsync(DatetimeWrapper complexBody) {
        return putDateTimeWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with datetime properties.
     *
     * @param complexBody Please put '0001-01-01T12:00:00-04:00' and '2015-05-18T11:38:00-08:00'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTime(DatetimeWrapper complexBody) {
        putDateTimeAsync(complexBody).block();
    }

    /**
     * Get complex types with datetimeRfc1123 properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetimeRfc1123 properties along with {@link Response} on successful completion of
     *     {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Datetimerfc1123Wrapper>> getDateTimeRfc1123WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDateTimeRfc1123(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with datetimeRfc1123 properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetimeRfc1123 properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Datetimerfc1123Wrapper> getDateTimeRfc1123Async() {
        return getDateTimeRfc1123WithResponseAsync()
                .flatMap(
                        (Response<Datetimerfc1123Wrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with datetimeRfc1123 properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetimeRfc1123 properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Datetimerfc1123Wrapper getDateTimeRfc1123() {
        return getDateTimeRfc1123Async().block();
    }

    /**
     * Put complex types with datetimeRfc1123 properties.
     *
     * @param complexBody Please put 'Mon, 01 Jan 0001 12:00:00 GMT' and 'Mon, 18 May 2015 11:38:00 GMT'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeRfc1123WithResponseAsync(Datetimerfc1123Wrapper complexBody) {
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
        return FluxUtil.withContext(
                context -> service.putDateTimeRfc1123(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with datetimeRfc1123 properties.
     *
     * @param complexBody Please put 'Mon, 01 Jan 0001 12:00:00 GMT' and 'Mon, 18 May 2015 11:38:00 GMT'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeRfc1123Async(Datetimerfc1123Wrapper complexBody) {
        return putDateTimeRfc1123WithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with datetimeRfc1123 properties.
     *
     * @param complexBody Please put 'Mon, 01 Jan 0001 12:00:00 GMT' and 'Mon, 18 May 2015 11:38:00 GMT'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeRfc1123(Datetimerfc1123Wrapper complexBody) {
        putDateTimeRfc1123Async(complexBody).block();
    }

    /**
     * Get complex types with duration properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with duration properties along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DurationWrapper>> getDurationWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDuration(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with duration properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with duration properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DurationWrapper> getDurationAsync() {
        return getDurationWithResponseAsync()
                .flatMap(
                        (Response<DurationWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with duration properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with duration properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DurationWrapper getDuration() {
        return getDurationAsync().block();
    }

    /**
     * Put complex types with duration properties.
     *
     * @param complexBody Please put 'P123DT22H14M12.011S'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDurationWithResponseAsync(DurationWrapper complexBody) {
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
        return FluxUtil.withContext(
                context -> service.putDuration(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with duration properties.
     *
     * @param complexBody Please put 'P123DT22H14M12.011S'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDurationAsync(DurationWrapper complexBody) {
        return putDurationWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with duration properties.
     *
     * @param complexBody Please put 'P123DT22H14M12.011S'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDuration(DurationWrapper complexBody) {
        putDurationAsync(complexBody).block();
    }

    /**
     * Get complex types with byte properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with byte properties along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ByteWrapper>> getByteWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getByte(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types with byte properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with byte properties on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ByteWrapper> getByteAsync() {
        return getByteWithResponseAsync()
                .flatMap(
                        (Response<ByteWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with byte properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with byte properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ByteWrapper getByte() {
        return getByteAsync().block();
    }

    /**
     * Put complex types with byte properties.
     *
     * @param complexBody Please put non-ascii byte string hex(FF FE FD FC 00 FA F9 F8 F7 F6).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putByteWithResponseAsync(ByteWrapper complexBody) {
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
        return FluxUtil.withContext(context -> service.putByte(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types with byte properties.
     *
     * @param complexBody Please put non-ascii byte string hex(FF FE FD FC 00 FA F9 F8 F7 F6).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putByteAsync(ByteWrapper complexBody) {
        return putByteWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with byte properties.
     *
     * @param complexBody Please put non-ascii byte string hex(FF FE FD FC 00 FA F9 F8 F7 F6).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putByte(ByteWrapper complexBody) {
        putByteAsync(complexBody).block();
    }
}
