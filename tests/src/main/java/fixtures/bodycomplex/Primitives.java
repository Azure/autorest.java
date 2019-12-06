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
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.implementation.RestProxy;
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
        Mono<SimpleResponse<IntWrapper>> getInt(@HostParam("$host") String Host);

        @Put("/complex/primitive/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putInt(@HostParam("$host") String Host, @BodyParam("application/json") IntWrapper ComplexBody);

        @Get("/complex/primitive/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<LongWrapper>> getLong(@HostParam("$host") String Host);

        @Put("/complex/primitive/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLong(@HostParam("$host") String Host, @BodyParam("application/json") LongWrapper ComplexBody);

        @Get("/complex/primitive/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<FloatWrapper>> getFloat(@HostParam("$host") String Host);

        @Put("/complex/primitive/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFloat(@HostParam("$host") String Host, @BodyParam("application/json") FloatWrapper ComplexBody);

        @Get("/complex/primitive/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DoubleWrapper>> getDouble(@HostParam("$host") String Host);

        @Put("/complex/primitive/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDouble(@HostParam("$host") String Host, @BodyParam("application/json") DoubleWrapper ComplexBody);

        @Get("/complex/primitive/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<BooleanWrapper>> getBool(@HostParam("$host") String Host);

        @Put("/complex/primitive/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBool(@HostParam("$host") String Host, @BodyParam("application/json") BooleanWrapper ComplexBody);

        @Get("/complex/primitive/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<StringWrapper>> getString(@HostParam("$host") String Host);

        @Put("/complex/primitive/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putString(@HostParam("$host") String Host, @BodyParam("application/json") StringWrapper ComplexBody);

        @Get("/complex/primitive/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DateWrapper>> getDate(@HostParam("$host") String Host);

        @Put("/complex/primitive/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDate(@HostParam("$host") String Host, @BodyParam("application/json") DateWrapper ComplexBody);

        @Get("/complex/primitive/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DatetimeWrapper>> getDateTime(@HostParam("$host") String Host);

        @Put("/complex/primitive/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTime(@HostParam("$host") String Host, @BodyParam("application/json") DatetimeWrapper ComplexBody);

        @Get("/complex/primitive/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Datetimerfc1123Wrapper>> getDateTimeRfc1123(@HostParam("$host") String Host);

        @Put("/complex/primitive/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeRfc1123(@HostParam("$host") String Host, @BodyParam("application/json") Datetimerfc1123Wrapper ComplexBody);

        @Get("/complex/primitive/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DurationWrapper>> getDuration(@HostParam("$host") String Host);

        @Put("/complex/primitive/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDuration(@HostParam("$host") String Host, @BodyParam("application/json") DurationWrapper ComplexBody);

        @Get("/complex/primitive/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ByteWrapper>> getByte(@HostParam("$host") String Host);

        @Put("/complex/primitive/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putByte(@HostParam("$host") String Host, @BodyParam("application/json") ByteWrapper ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<IntWrapper>> getIntWithResponseAsync() {
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
    public Mono<Response<Void>> putIntWithResponseAsync(IntWrapper ComplexBody) {
        return service.putInt(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putIntAsync(IntWrapper ComplexBody) {
        return putIntWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putInt(IntWrapper ComplexBody) {
        putIntAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<LongWrapper>> getLongWithResponseAsync() {
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
    public Mono<Response<Void>> putLongWithResponseAsync(LongWrapper ComplexBody) {
        return service.putLong(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLongAsync(LongWrapper ComplexBody) {
        return putLongWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLong(LongWrapper ComplexBody) {
        putLongAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<FloatWrapper>> getFloatWithResponseAsync() {
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
    public Mono<Response<Void>> putFloatWithResponseAsync(FloatWrapper ComplexBody) {
        return service.putFloat(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFloatAsync(FloatWrapper ComplexBody) {
        return putFloatWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFloat(FloatWrapper ComplexBody) {
        putFloatAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DoubleWrapper>> getDoubleWithResponseAsync() {
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
    public Mono<Response<Void>> putDoubleWithResponseAsync(DoubleWrapper ComplexBody) {
        return service.putDouble(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDoubleAsync(DoubleWrapper ComplexBody) {
        return putDoubleWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDouble(DoubleWrapper ComplexBody) {
        putDoubleAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BooleanWrapper>> getBoolWithResponseAsync() {
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
    public Mono<Response<Void>> putBoolWithResponseAsync(BooleanWrapper ComplexBody) {
        return service.putBool(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBoolAsync(BooleanWrapper ComplexBody) {
        return putBoolWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBool(BooleanWrapper ComplexBody) {
        putBoolAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StringWrapper>> getStringWithResponseAsync() {
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
    public Mono<Response<Void>> putStringWithResponseAsync(StringWrapper ComplexBody) {
        return service.putString(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putStringAsync(StringWrapper ComplexBody) {
        return putStringWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putString(StringWrapper ComplexBody) {
        putStringAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DateWrapper>> getDateWithResponseAsync() {
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
    public Mono<Response<Void>> putDateWithResponseAsync(DateWrapper ComplexBody) {
        return service.putDate(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateAsync(DateWrapper ComplexBody) {
        return putDateWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDate(DateWrapper ComplexBody) {
        putDateAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DatetimeWrapper>> getDateTimeWithResponseAsync() {
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
    public Mono<Response<Void>> putDateTimeWithResponseAsync(DatetimeWrapper ComplexBody) {
        return service.putDateTime(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeAsync(DatetimeWrapper ComplexBody) {
        return putDateTimeWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTime(DatetimeWrapper ComplexBody) {
        putDateTimeAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Datetimerfc1123Wrapper>> getDateTimeRfc1123WithResponseAsync() {
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
    public Mono<Response<Void>> putDateTimeRfc1123WithResponseAsync(Datetimerfc1123Wrapper ComplexBody) {
        return service.putDateTimeRfc1123(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeRfc1123Async(Datetimerfc1123Wrapper ComplexBody) {
        return putDateTimeRfc1123WithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeRfc1123(Datetimerfc1123Wrapper ComplexBody) {
        putDateTimeRfc1123Async(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DurationWrapper>> getDurationWithResponseAsync() {
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
    public Mono<Response<Void>> putDurationWithResponseAsync(DurationWrapper ComplexBody) {
        return service.putDuration(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDurationAsync(DurationWrapper ComplexBody) {
        return putDurationWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDuration(DurationWrapper ComplexBody) {
        putDurationAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ByteWrapper>> getByteWithResponseAsync() {
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
    public Mono<Response<Void>> putByteWithResponseAsync(ByteWrapper ComplexBody) {
        return service.putByte(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putByteAsync(ByteWrapper ComplexBody) {
        return putByteWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putByte(ByteWrapper ComplexBody) {
        putByteAsync(ComplexBody).block();
    }
}
