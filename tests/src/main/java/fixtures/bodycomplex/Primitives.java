package fixtures.bodycomplex;

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
import com.azure.core.http.rest.SimpleResponse;
import fixtures.bodycomplex.models.BooleanWrapper;
import fixtures.bodycomplex.models.ByteWrapper;
import fixtures.bodycomplex.models.Datetimerfc1123Wrapper;
import fixtures.bodycomplex.models.DatetimeWrapper;
import fixtures.bodycomplex.models.DateWrapper;
import fixtures.bodycomplex.models.DoubleWrapper;
import fixtures.bodycomplex.models.DurationWrapper;
import fixtures.bodycomplex.models.ErrorException;
import fixtures.bodycomplex.models.FloatWrapper;
import fixtures.bodycomplex.models.IntWrapper;
import fixtures.bodycomplex.models.LongWrapper;
import fixtures.bodycomplex.models.StringWrapper;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Primitives.
 */
public final class Primitives {
    /**
     * The proxy service used to perform REST calls.
     */
    private PrimitivesService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestComplexTestService client;

    /**
     * Initializes an instance of Primitives.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Primitives(AutoRestComplexTestService client) {
        this.service = RestProxy.create(PrimitivesService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServicePrimitives to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServicePrimitives")
    private interface PrimitivesService {
        @Get("/complex/primitive/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<IntWrapper>> getInt(@HostParam("$host") String host);

        @Put("/complex/primitive/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putInt(@HostParam("$host") String host, @BodyParam("application/json") IntWrapper complexBody);

        @Get("/complex/primitive/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<LongWrapper>> getLong(@HostParam("$host") String host);

        @Put("/complex/primitive/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLong(@HostParam("$host") String host, @BodyParam("application/json") LongWrapper complexBody);

        @Get("/complex/primitive/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<FloatWrapper>> getFloat(@HostParam("$host") String host);

        @Put("/complex/primitive/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFloat(@HostParam("$host") String host, @BodyParam("application/json") FloatWrapper complexBody);

        @Get("/complex/primitive/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DoubleWrapper>> getDouble(@HostParam("$host") String host);

        @Put("/complex/primitive/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDouble(@HostParam("$host") String host, @BodyParam("application/json") DoubleWrapper complexBody);

        @Get("/complex/primitive/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<BooleanWrapper>> getBool(@HostParam("$host") String host);

        @Put("/complex/primitive/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBool(@HostParam("$host") String host, @BodyParam("application/json") BooleanWrapper complexBody);

        @Get("/complex/primitive/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<StringWrapper>> getString(@HostParam("$host") String host);

        @Put("/complex/primitive/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putString(@HostParam("$host") String host, @BodyParam("application/json") StringWrapper complexBody);

        @Get("/complex/primitive/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DateWrapper>> getDate(@HostParam("$host") String host);

        @Put("/complex/primitive/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDate(@HostParam("$host") String host, @BodyParam("application/json") DateWrapper complexBody);

        @Get("/complex/primitive/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DatetimeWrapper>> getDateTime(@HostParam("$host") String host);

        @Put("/complex/primitive/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTime(@HostParam("$host") String host, @BodyParam("application/json") DatetimeWrapper complexBody);

        @Get("/complex/primitive/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Datetimerfc1123Wrapper>> getDateTimeRfc1123(@HostParam("$host") String host);

        @Put("/complex/primitive/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeRfc1123(@HostParam("$host") String host, @BodyParam("application/json") Datetimerfc1123Wrapper complexBody);

        @Get("/complex/primitive/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DurationWrapper>> getDuration(@HostParam("$host") String host);

        @Put("/complex/primitive/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDuration(@HostParam("$host") String host, @BodyParam("application/json") DurationWrapper complexBody);

        @Get("/complex/primitive/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ByteWrapper>> getByte(@HostParam("$host") String host);

        @Put("/complex/primitive/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putByte(@HostParam("$host") String host, @BodyParam("application/json") ByteWrapper complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<IntWrapper>> getIntWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getInt(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<IntWrapper> getIntAsync() {
        return getIntWithResponseAsync()
            .flatMap((SimpleResponse<IntWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public IntWrapper getInt() {
        return getIntAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putIntWithResponseAsync(IntWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putInt(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putIntAsync(IntWrapper complexBody) {
        return putIntWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putInt(IntWrapper complexBody) {
        putIntAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<LongWrapper>> getLongWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getLong(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LongWrapper> getLongAsync() {
        return getLongWithResponseAsync()
            .flatMap((SimpleResponse<LongWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public LongWrapper getLong() {
        return getLongAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLongWithResponseAsync(LongWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putLong(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLongAsync(LongWrapper complexBody) {
        return putLongWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLong(LongWrapper complexBody) {
        putLongAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<FloatWrapper>> getFloatWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getFloat(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<FloatWrapper> getFloatAsync() {
        return getFloatWithResponseAsync()
            .flatMap((SimpleResponse<FloatWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public FloatWrapper getFloat() {
        return getFloatAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFloatWithResponseAsync(FloatWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putFloat(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFloatAsync(FloatWrapper complexBody) {
        return putFloatWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFloat(FloatWrapper complexBody) {
        putFloatAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DoubleWrapper>> getDoubleWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getDouble(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DoubleWrapper> getDoubleAsync() {
        return getDoubleWithResponseAsync()
            .flatMap((SimpleResponse<DoubleWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DoubleWrapper getDouble() {
        return getDoubleAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDoubleWithResponseAsync(DoubleWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putDouble(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDoubleAsync(DoubleWrapper complexBody) {
        return putDoubleWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDouble(DoubleWrapper complexBody) {
        putDoubleAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BooleanWrapper>> getBoolWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getBool(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BooleanWrapper> getBoolAsync() {
        return getBoolWithResponseAsync()
            .flatMap((SimpleResponse<BooleanWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public BooleanWrapper getBool() {
        return getBoolAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBoolWithResponseAsync(BooleanWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putBool(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBoolAsync(BooleanWrapper complexBody) {
        return putBoolWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBool(BooleanWrapper complexBody) {
        putBoolAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StringWrapper>> getStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getString(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StringWrapper> getStringAsync() {
        return getStringWithResponseAsync()
            .flatMap((SimpleResponse<StringWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public StringWrapper getString() {
        return getStringAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringWithResponseAsync(StringWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putString(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putStringAsync(StringWrapper complexBody) {
        return putStringWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putString(StringWrapper complexBody) {
        putStringAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DateWrapper>> getDateWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getDate(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DateWrapper> getDateAsync() {
        return getDateWithResponseAsync()
            .flatMap((SimpleResponse<DateWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DateWrapper getDate() {
        return getDateAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateWithResponseAsync(DateWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putDate(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateAsync(DateWrapper complexBody) {
        return putDateWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDate(DateWrapper complexBody) {
        putDateAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DatetimeWrapper>> getDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getDateTime(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DatetimeWrapper> getDateTimeAsync() {
        return getDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<DatetimeWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DatetimeWrapper getDateTime() {
        return getDateTimeAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeWithResponseAsync(DatetimeWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putDateTime(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeAsync(DatetimeWrapper complexBody) {
        return putDateTimeWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTime(DatetimeWrapper complexBody) {
        putDateTimeAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Datetimerfc1123Wrapper>> getDateTimeRfc1123WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getDateTimeRfc1123(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Datetimerfc1123Wrapper> getDateTimeRfc1123Async() {
        return getDateTimeRfc1123WithResponseAsync()
            .flatMap((SimpleResponse<Datetimerfc1123Wrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Datetimerfc1123Wrapper getDateTimeRfc1123() {
        return getDateTimeRfc1123Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeRfc1123WithResponseAsync(Datetimerfc1123Wrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putDateTimeRfc1123(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeRfc1123Async(Datetimerfc1123Wrapper complexBody) {
        return putDateTimeRfc1123WithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeRfc1123(Datetimerfc1123Wrapper complexBody) {
        putDateTimeRfc1123Async(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DurationWrapper>> getDurationWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getDuration(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DurationWrapper> getDurationAsync() {
        return getDurationWithResponseAsync()
            .flatMap((SimpleResponse<DurationWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DurationWrapper getDuration() {
        return getDurationAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDurationWithResponseAsync(DurationWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putDuration(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDurationAsync(DurationWrapper complexBody) {
        return putDurationWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDuration(DurationWrapper complexBody) {
        putDurationAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ByteWrapper>> getByteWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getByte(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ByteWrapper> getByteAsync() {
        return getByteWithResponseAsync()
            .flatMap((SimpleResponse<ByteWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public ByteWrapper getByte() {
        return getByteAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putByteWithResponseAsync(ByteWrapper complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putByte(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putByteAsync(ByteWrapper complexBody) {
        return putByteWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putByte(ByteWrapper complexBody) {
        putByteAsync(complexBody).block();
    }
}
