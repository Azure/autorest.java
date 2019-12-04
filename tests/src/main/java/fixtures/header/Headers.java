package fixtures.header;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ReturnValueWireType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.implementation.DateTimeRfc1123;
import com.azure.core.implementation.RestProxy;
import com.azure.core.implementation.util.Base64Util;
import fixtures.header.models.ErrorException;
import fixtures.header.models.GreyscaleColors;
import fixtures.header.models.HeadersResponseBoolResponse;
import fixtures.header.models.HeadersResponseByteResponse;
import fixtures.header.models.HeadersResponseDateResponse;
import fixtures.header.models.HeadersResponseDatetimeResponse;
import fixtures.header.models.HeadersResponseDatetimeRfc1123Response;
import fixtures.header.models.HeadersResponseDoubleResponse;
import fixtures.header.models.HeadersResponseDurationResponse;
import fixtures.header.models.HeadersResponseEnumResponse;
import fixtures.header.models.HeadersResponseExistingKeyResponse;
import fixtures.header.models.HeadersResponseFloatResponse;
import fixtures.header.models.HeadersResponseIntegerResponse;
import fixtures.header.models.HeadersResponseLongResponse;
import fixtures.header.models.HeadersResponseProtectedKeyResponse;
import fixtures.header.models.HeadersResponseStringResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Headers.
 */
public final class Headers {
    /**
     * The proxy service used to perform REST calls.
     */
    private HeadersService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestSwaggerBATHeaderService client;

    /**
     * Initializes an instance of Headers.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Headers(AutoRestSwaggerBATHeaderService client) {
        this.service = RestProxy.create(HeadersService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBATHeaderServiceHeaders to be used by the proxy service
     * to perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestSwaggerBATHeaderServiceHeaders")
    private interface HeadersService {
        @Post("/header/param/existingkey")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramExistingKey(@HeaderParam("User-Agent") String UserAgent);

        @Post("/header/response/existingkey")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseExistingKeyResponse> responseExistingKey();

        @Post("/header/param/protectedkey")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramProtectedKey(@HeaderParam("Content-Type") String ContentType);

        @Post("/header/response/protectedkey")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseProtectedKeyResponse> responseProtectedKey();

        @Post("/header/param/prim/integer")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramInteger(@HeaderParam("scenario") String Scenario, @HeaderParam("value") int Value);

        @Post("/header/response/prim/integer")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseIntegerResponse> responseInteger(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/long")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramLong(@HeaderParam("scenario") String Scenario, @HeaderParam("value") long Value);

        @Post("/header/response/prim/long")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseLongResponse> responseLong(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/float")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramFloat(@HeaderParam("scenario") String Scenario, @HeaderParam("value") float Value);

        @Post("/header/response/prim/float")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseFloatResponse> responseFloat(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/double")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDouble(@HeaderParam("scenario") String Scenario, @HeaderParam("value") double Value);

        @Post("/header/response/prim/double")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDoubleResponse> responseDouble(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/bool")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramBool(@HeaderParam("scenario") String Scenario, @HeaderParam("value") boolean Value);

        @Post("/header/response/prim/bool")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseBoolResponse> responseBool(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/string")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramString(@HeaderParam("scenario") String Scenario, @HeaderParam("value") String Value);

        @Post("/header/response/prim/string")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseStringResponse> responseString(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/date")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDate(@HeaderParam("scenario") String Scenario, @HeaderParam("value") LocalDate Value);

        @Post("/header/response/prim/date")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDateResponse> responseDate(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/datetime")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDatetime(@HeaderParam("scenario") String Scenario, @HeaderParam("value") OffsetDateTime Value);

        @Post("/header/response/prim/datetime")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDatetimeResponse> responseDatetime(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/datetimerfc1123")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDatetimeRfc1123(@HeaderParam("scenario") String Scenario, @HeaderParam("value") DateTimeRfc1123 Value);

        @Post("/header/response/prim/datetimerfc1123")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDatetimeRfc1123Response> responseDatetimeRfc1123(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/duration")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDuration(@HeaderParam("scenario") String Scenario, @HeaderParam("value") Duration Value);

        @Post("/header/response/prim/duration")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDurationResponse> responseDuration(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/byte")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramByte(@HeaderParam("scenario") String Scenario, @HeaderParam("value") String Value);

        @Post("/header/response/prim/byte")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseByteResponse> responseByte(@HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/enum")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramEnum(@HeaderParam("scenario") String Scenario, @HeaderParam("value") GreyscaleColors Value);

        @Post("/header/response/prim/enum")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseEnumResponse> responseEnum(@HeaderParam("scenario") String Scenario);

        @Post("/header/custom/x-ms-client-request-id/9C4D50EE-2D56-4CD3-8152-34347DC9F2B0")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> customRequestId();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramExistingKeyWithResponseAsync(String UserAgent) {
        return service.paramExistingKey(UserAgent);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseExistingKeyResponse> responseExistingKeyWithResponseAsync() {
        return service.responseExistingKey();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramProtectedKeyWithResponseAsync(String ContentType) {
        return service.paramProtectedKey(ContentType);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseProtectedKeyResponse> responseProtectedKeyWithResponseAsync() {
        return service.responseProtectedKey();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramIntegerWithResponseAsync(String Scenario, int Value) {
        return service.paramInteger(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseIntegerResponse> responseIntegerWithResponseAsync(String Scenario) {
        return service.responseInteger(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramLongWithResponseAsync(String Scenario, long Value) {
        return service.paramLong(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseLongResponse> responseLongWithResponseAsync(String Scenario) {
        return service.responseLong(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramFloatWithResponseAsync(String Scenario, float Value) {
        return service.paramFloat(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseFloatResponse> responseFloatWithResponseAsync(String Scenario) {
        return service.responseFloat(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDoubleWithResponseAsync(String Scenario, double Value) {
        return service.paramDouble(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDoubleResponse> responseDoubleWithResponseAsync(String Scenario) {
        return service.responseDouble(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramBoolWithResponseAsync(String Scenario, boolean Value) {
        return service.paramBool(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseBoolResponse> responseBoolWithResponseAsync(String Scenario) {
        return service.responseBool(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramStringWithResponseAsync(String Scenario, String Value) {
        return service.paramString(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseStringResponse> responseStringWithResponseAsync(String Scenario) {
        return service.responseString(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDateWithResponseAsync(String Scenario, LocalDate Value) {
        return service.paramDate(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDateResponse> responseDateWithResponseAsync(String Scenario) {
        return service.responseDate(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeWithResponseAsync(String Scenario, OffsetDateTime Value) {
        return service.paramDatetime(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDatetimeResponse> responseDatetimeWithResponseAsync(String Scenario) {
        return service.responseDatetime(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeRfc1123WithResponseAsync(String Scenario, OffsetDateTime Value) {
        DateTimeRfc1123 valueConverted = Value == null ? null : new DateTimeRfc1123(Value);
        return service.paramDatetimeRfc1123(Scenario, valueConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDatetimeRfc1123Response> responseDatetimeRfc1123WithResponseAsync(String Scenario) {
        return service.responseDatetimeRfc1123(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDurationWithResponseAsync(String Scenario, Duration Value) {
        return service.paramDuration(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDurationResponse> responseDurationWithResponseAsync(String Scenario) {
        return service.responseDuration(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramByteWithResponseAsync(String Scenario, byte[] Value) {
        String valueConverted = Base64Util.encodeToString(Value);
        return service.paramByte(Scenario, valueConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseByteResponse> responseByteWithResponseAsync(String Scenario) {
        return service.responseByte(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramEnumWithResponseAsync(String Scenario, GreyscaleColors Value) {
        return service.paramEnum(Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseEnumResponse> responseEnumWithResponseAsync(String Scenario) {
        return service.responseEnum(Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> customRequestIdWithResponseAsync() {
        return service.customRequestId();
    }
}
