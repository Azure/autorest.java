package fixtures.bodyinteger;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ReturnValueWireType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodyinteger.models.ErrorException;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Ints. */
public final class Ints {
    /** The proxy service used to perform REST calls. */
    private final IntsService service;

    /** The service client containing this operation class. */
    private final AutoRestIntegerTestService client;

    /**
     * Initializes an instance of Ints.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Ints(AutoRestIntegerTestService client) {
        this.service = RestProxy.create(IntsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestIntegerTestServiceInts to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestIntegerTestS")
    private interface IntsService {
        @Get("/int/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Integer>> getNull(@HostParam("$host") String host, Context context);

        @Get("/int/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Integer>> getInvalid(@HostParam("$host") String host, Context context);

        @Get("/int/overflowint32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Integer>> getOverflowInt32(@HostParam("$host") String host, Context context);

        @Get("/int/underflowint32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Integer>> getUnderflowInt32(@HostParam("$host") String host, Context context);

        @Get("/int/overflowint64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Long>> getOverflowInt64(@HostParam("$host") String host, Context context);

        @Get("/int/underflowint64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Long>> getUnderflowInt64(@HostParam("$host") String host, Context context);

        @Put("/int/max/32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMax32(
                @HostParam("$host") String host, @BodyParam("application/json") int intBody, Context context);

        @Put("/int/max/64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMax64(
                @HostParam("$host") String host, @BodyParam("application/json") long intBody, Context context);

        @Put("/int/min/32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMin32(
                @HostParam("$host") String host, @BodyParam("application/json") int intBody, Context context);

        @Put("/int/min/64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMin64(
                @HostParam("$host") String host, @BodyParam("application/json") long intBody, Context context);

        @Get("/int/unixtime")
        @ExpectedResponses({200})
        @ReturnValueWireType(long.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<OffsetDateTime>> getUnixTime(@HostParam("$host") String host, Context context);

        @Put("/int/unixtime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUnixTimeDate(
                @HostParam("$host") String host, @BodyParam("application/json") long intBody, Context context);

        @Get("/int/invalidunixtime")
        @ExpectedResponses({200})
        @ReturnValueWireType(long.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<OffsetDateTime>> getInvalidUnixTime(@HostParam("$host") String host, Context context);

        @Get("/int/nullunixtime")
        @ExpectedResponses({200})
        @ReturnValueWireType(long.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<OffsetDateTime>> getNullUnixTime(@HostParam("$host") String host, Context context);
    }

    /**
     * Get null Int value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Int value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Integer>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), context));
    }

    /**
     * Get null Int value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Int value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getNullAsync() {
        return getNullWithResponseAsync()
                .flatMap(
                        (Response<Integer> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get null Int value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Int value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getNull() {
        Integer value = getNullAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get invalid Int value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Int value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Integer>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getInvalid(this.client.getHost(), context));
    }

    /**
     * Get invalid Int value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Int value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getInvalidAsync() {
        return getInvalidWithResponseAsync()
                .flatMap(
                        (Response<Integer> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get invalid Int value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Int value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getInvalid() {
        Integer value = getInvalidAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get overflow Int32 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow Int32 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Integer>> getOverflowInt32WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getOverflowInt32(this.client.getHost(), context));
    }

    /**
     * Get overflow Int32 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow Int32 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getOverflowInt32Async() {
        return getOverflowInt32WithResponseAsync()
                .flatMap(
                        (Response<Integer> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get overflow Int32 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow Int32 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getOverflowInt32() {
        Integer value = getOverflowInt32Async().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get underflow Int32 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow Int32 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Integer>> getUnderflowInt32WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUnderflowInt32(this.client.getHost(), context));
    }

    /**
     * Get underflow Int32 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow Int32 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getUnderflowInt32Async() {
        return getUnderflowInt32WithResponseAsync()
                .flatMap(
                        (Response<Integer> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get underflow Int32 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow Int32 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getUnderflowInt32() {
        Integer value = getUnderflowInt32Async().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get overflow Int64 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow Int64 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Long>> getOverflowInt64WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getOverflowInt64(this.client.getHost(), context));
    }

    /**
     * Get overflow Int64 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow Int64 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Long> getOverflowInt64Async() {
        return getOverflowInt64WithResponseAsync()
                .flatMap(
                        (Response<Long> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get overflow Int64 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow Int64 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public long getOverflowInt64() {
        Long value = getOverflowInt64Async().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get underflow Int64 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow Int64 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Long>> getUnderflowInt64WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUnderflowInt64(this.client.getHost(), context));
    }

    /**
     * Get underflow Int64 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow Int64 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Long> getUnderflowInt64Async() {
        return getUnderflowInt64WithResponseAsync()
                .flatMap(
                        (Response<Long> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get underflow Int64 value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow Int64 value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public long getUnderflowInt64() {
        Long value = getUnderflowInt64Async().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Put max int32 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMax32WithResponseAsync(int intBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putMax32(this.client.getHost(), intBody, context));
    }

    /**
     * Put max int32 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMax32Async(int intBody) {
        return putMax32WithResponseAsync(intBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max int32 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMax32(int intBody) {
        putMax32Async(intBody).block();
    }

    /**
     * Put max int64 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMax64WithResponseAsync(long intBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putMax64(this.client.getHost(), intBody, context));
    }

    /**
     * Put max int64 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMax64Async(long intBody) {
        return putMax64WithResponseAsync(intBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max int64 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMax64(long intBody) {
        putMax64Async(intBody).block();
    }

    /**
     * Put min int32 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMin32WithResponseAsync(int intBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putMin32(this.client.getHost(), intBody, context));
    }

    /**
     * Put min int32 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMin32Async(int intBody) {
        return putMin32WithResponseAsync(intBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min int32 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMin32(int intBody) {
        putMin32Async(intBody).block();
    }

    /**
     * Put min int64 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMin64WithResponseAsync(long intBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putMin64(this.client.getHost(), intBody, context));
    }

    /**
     * Put min int64 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMin64Async(long intBody) {
        return putMin64WithResponseAsync(intBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min int64 value.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMin64(long intBody) {
        putMin64Async(intBody).block();
    }

    /**
     * Get datetime encoded as Unix time value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return datetime encoded as Unix time value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<OffsetDateTime>> getUnixTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUnixTime(this.client.getHost(), context));
    }

    /**
     * Get datetime encoded as Unix time value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return datetime encoded as Unix time value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUnixTimeAsync() {
        return getUnixTimeWithResponseAsync()
                .flatMap(
                        (Response<OffsetDateTime> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get datetime encoded as Unix time value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return datetime encoded as Unix time value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUnixTime() {
        return getUnixTimeAsync().block();
    }

    /**
     * Put datetime encoded as Unix time.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUnixTimeDateWithResponseAsync(OffsetDateTime intBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (intBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter intBody is required and cannot be null."));
        }
        long intBodyConverted = intBody.toEpochSecond();
        return FluxUtil.withContext(
                context -> service.putUnixTimeDate(this.client.getHost(), intBodyConverted, context));
    }

    /**
     * Put datetime encoded as Unix time.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUnixTimeDateAsync(OffsetDateTime intBody) {
        return putUnixTimeDateWithResponseAsync(intBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put datetime encoded as Unix time.
     *
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUnixTimeDate(OffsetDateTime intBody) {
        putUnixTimeDateAsync(intBody).block();
    }

    /**
     * Get invalid Unix time value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Unix time value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<OffsetDateTime>> getInvalidUnixTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getInvalidUnixTime(this.client.getHost(), context));
    }

    /**
     * Get invalid Unix time value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Unix time value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getInvalidUnixTimeAsync() {
        return getInvalidUnixTimeWithResponseAsync()
                .flatMap(
                        (Response<OffsetDateTime> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get invalid Unix time value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Unix time value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getInvalidUnixTime() {
        return getInvalidUnixTimeAsync().block();
    }

    /**
     * Get null Unix time value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Unix time value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<OffsetDateTime>> getNullUnixTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNullUnixTime(this.client.getHost(), context));
    }

    /**
     * Get null Unix time value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Unix time value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getNullUnixTimeAsync() {
        return getNullUnixTimeWithResponseAsync()
                .flatMap(
                        (Response<OffsetDateTime> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get null Unix time value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Unix time value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getNullUnixTime() {
        return getNullUnixTimeAsync().block();
    }
}
