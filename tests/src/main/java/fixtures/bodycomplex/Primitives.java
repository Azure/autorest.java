package fixtures.bodycomplex;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ReturnValueWireType;
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
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestComplexTestServicePrimitives")
    private interface PrimitivesService {
        @Get("/complex/primitive/integer")
        @ExpectedResponses({200})
        @ReturnValueWireType(IntWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<IntWrapper>> getInt();

        @Put("/complex/primitive/integer")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putInt(@BodyParam("application/json") IntWrapper ComplexBody);

        @Get("/complex/primitive/long")
        @ExpectedResponses({200})
        @ReturnValueWireType(LongWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<LongWrapper>> getLong();

        @Put("/complex/primitive/long")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLong(@BodyParam("application/json") LongWrapper ComplexBody);

        @Get("/complex/primitive/float")
        @ExpectedResponses({200})
        @ReturnValueWireType(FloatWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<FloatWrapper>> getFloat();

        @Put("/complex/primitive/float")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFloat(@BodyParam("application/json") FloatWrapper ComplexBody);

        @Get("/complex/primitive/double")
        @ExpectedResponses({200})
        @ReturnValueWireType(DoubleWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DoubleWrapper>> getDouble();

        @Put("/complex/primitive/double")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDouble(@BodyParam("application/json") DoubleWrapper ComplexBody);

        @Get("/complex/primitive/bool")
        @ExpectedResponses({200})
        @ReturnValueWireType(BooleanWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<BooleanWrapper>> getBool();

        @Put("/complex/primitive/bool")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBool(@BodyParam("application/json") BooleanWrapper ComplexBody);

        @Get("/complex/primitive/string")
        @ExpectedResponses({200})
        @ReturnValueWireType(StringWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<StringWrapper>> getString();

        @Put("/complex/primitive/string")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putString(@BodyParam("application/json") StringWrapper ComplexBody);

        @Get("/complex/primitive/date")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DateWrapper>> getDate();

        @Put("/complex/primitive/date")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDate(@BodyParam("application/json") DateWrapper ComplexBody);

        @Get("/complex/primitive/datetime")
        @ExpectedResponses({200})
        @ReturnValueWireType(DatetimeWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DatetimeWrapper>> getDateTime();

        @Put("/complex/primitive/datetime")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTime(@BodyParam("application/json") DatetimeWrapper ComplexBody);

        @Get("/complex/primitive/datetimerfc1123")
        @ExpectedResponses({200})
        @ReturnValueWireType(Datetimerfc1123Wrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Datetimerfc1123Wrapper>> getDateTimeRfc1123();

        @Put("/complex/primitive/datetimerfc1123")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeRfc1123(@BodyParam("application/json") Datetimerfc1123Wrapper ComplexBody);

        @Get("/complex/primitive/duration")
        @ExpectedResponses({200})
        @ReturnValueWireType(DurationWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DurationWrapper>> getDuration();

        @Put("/complex/primitive/duration")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDuration(@BodyParam("application/json") DurationWrapper ComplexBody);

        @Get("/complex/primitive/byte")
        @ExpectedResponses({200})
        @ReturnValueWireType(ByteWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ByteWrapper>> getByte();

        @Put("/complex/primitive/byte")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putByte(@BodyParam("application/json") ByteWrapper ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<IntWrapper>> getIntWithResponseAsync() {
        return service.getInt();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putIntWithResponseAsync(IntWrapper ComplexBody) {
        return service.putInt(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<LongWrapper>> getLongWithResponseAsync() {
        return service.getLong();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLongWithResponseAsync(LongWrapper ComplexBody) {
        return service.putLong(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<FloatWrapper>> getFloatWithResponseAsync() {
        return service.getFloat();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFloatWithResponseAsync(FloatWrapper ComplexBody) {
        return service.putFloat(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DoubleWrapper>> getDoubleWithResponseAsync() {
        return service.getDouble();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDoubleWithResponseAsync(DoubleWrapper ComplexBody) {
        return service.putDouble(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BooleanWrapper>> getBoolWithResponseAsync() {
        return service.getBool();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBoolWithResponseAsync(BooleanWrapper ComplexBody) {
        return service.putBool(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StringWrapper>> getStringWithResponseAsync() {
        return service.getString();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringWithResponseAsync(StringWrapper ComplexBody) {
        return service.putString(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DateWrapper>> getDateWithResponseAsync() {
        return service.getDate();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateWithResponseAsync(DateWrapper ComplexBody) {
        return service.putDate(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DatetimeWrapper>> getDateTimeWithResponseAsync() {
        return service.getDateTime();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeWithResponseAsync(DatetimeWrapper ComplexBody) {
        return service.putDateTime(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Datetimerfc1123Wrapper>> getDateTimeRfc1123WithResponseAsync() {
        return service.getDateTimeRfc1123();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeRfc1123WithResponseAsync(Datetimerfc1123Wrapper ComplexBody) {
        return service.putDateTimeRfc1123(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DurationWrapper>> getDurationWithResponseAsync() {
        return service.getDuration();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDurationWithResponseAsync(DurationWrapper ComplexBody) {
        return service.putDuration(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ByteWrapper>> getByteWithResponseAsync() {
        return service.getByte();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putByteWithResponseAsync(ByteWrapper ComplexBody) {
        return service.putByte(ComplexBody);
    }
}
