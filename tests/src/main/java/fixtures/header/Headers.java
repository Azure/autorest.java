package fixtures.header;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
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
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATHeaderServiceHeaders")
    private interface HeadersService {
        @Post("/header/param/existingkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramExistingKey(@HostParam("$host") String Host, @HeaderParam("User-Agent") String UserAgent);

        @Post("/header/response/existingkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseExistingKeyResponse> responseExistingKey(@HostParam("$host") String Host);

        @Post("/header/param/protectedkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramProtectedKey(@HostParam("$host") String Host, @HeaderParam("Content-Type") String ContentType);

        @Post("/header/response/protectedkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseProtectedKeyResponse> responseProtectedKey(@HostParam("$host") String Host);

        @Post("/header/param/prim/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramInteger(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") int Value);

        @Post("/header/response/prim/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseIntegerResponse> responseInteger(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramLong(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") long Value);

        @Post("/header/response/prim/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseLongResponse> responseLong(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramFloat(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") float Value);

        @Post("/header/response/prim/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseFloatResponse> responseFloat(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDouble(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") double Value);

        @Post("/header/response/prim/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDoubleResponse> responseDouble(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramBool(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") boolean Value);

        @Post("/header/response/prim/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseBoolResponse> responseBool(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramString(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") String Value);

        @Post("/header/response/prim/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseStringResponse> responseString(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDate(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") LocalDate Value);

        @Post("/header/response/prim/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDateResponse> responseDate(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDatetime(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") OffsetDateTime Value);

        @Post("/header/response/prim/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDatetimeResponse> responseDatetime(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDatetimeRfc1123(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") DateTimeRfc1123 Value);

        @Post("/header/response/prim/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDatetimeRfc1123Response> responseDatetimeRfc1123(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDuration(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") Duration Value);

        @Post("/header/response/prim/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDurationResponse> responseDuration(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramByte(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") String Value);

        @Post("/header/response/prim/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseByteResponse> responseByte(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/param/prim/enum")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramEnum(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario, @HeaderParam("value") GreyscaleColors Value);

        @Post("/header/response/prim/enum")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseEnumResponse> responseEnum(@HostParam("$host") String Host, @HeaderParam("scenario") String Scenario);

        @Post("/header/custom/x-ms-client-request-id/9C4D50EE-2D56-4CD3-8152-34347DC9F2B0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> customRequestId(@HostParam("$host") String Host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramExistingKeyWithResponseAsync(String UserAgent) {
        return service.paramExistingKey(this.client.getHost(), UserAgent);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramExistingKeyAsync(String UserAgent) {
        return paramExistingKeyWithResponseAsync(UserAgent)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramExistingKey(String UserAgent) {
        paramExistingKeyAsync(UserAgent).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseExistingKeyResponse> responseExistingKeyWithResponseAsync() {
        return service.responseExistingKey(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseExistingKeyAsync() {
        return responseExistingKeyWithResponseAsync()
            .flatMap((HeadersResponseExistingKeyResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseExistingKey() {
        responseExistingKeyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramProtectedKeyWithResponseAsync(String ContentType) {
        return service.paramProtectedKey(this.client.getHost(), ContentType);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramProtectedKeyAsync(String ContentType) {
        return paramProtectedKeyWithResponseAsync(ContentType)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramProtectedKey(String ContentType) {
        paramProtectedKeyAsync(ContentType).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseProtectedKeyResponse> responseProtectedKeyWithResponseAsync() {
        return service.responseProtectedKey(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseProtectedKeyAsync() {
        return responseProtectedKeyWithResponseAsync()
            .flatMap((HeadersResponseProtectedKeyResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseProtectedKey() {
        responseProtectedKeyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramIntegerWithResponseAsync(String Scenario, int Value) {
        return service.paramInteger(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramIntegerAsync(String Scenario, int Value) {
        return paramIntegerWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramInteger(String Scenario, int Value) {
        paramIntegerAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseIntegerResponse> responseIntegerWithResponseAsync(String Scenario) {
        return service.responseInteger(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseIntegerAsync(String Scenario) {
        return responseIntegerWithResponseAsync(Scenario)
            .flatMap((HeadersResponseIntegerResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseInteger(String Scenario) {
        responseIntegerAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramLongWithResponseAsync(String Scenario, long Value) {
        return service.paramLong(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramLongAsync(String Scenario, long Value) {
        return paramLongWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramLong(String Scenario, long Value) {
        paramLongAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseLongResponse> responseLongWithResponseAsync(String Scenario) {
        return service.responseLong(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseLongAsync(String Scenario) {
        return responseLongWithResponseAsync(Scenario)
            .flatMap((HeadersResponseLongResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseLong(String Scenario) {
        responseLongAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramFloatWithResponseAsync(String Scenario, float Value) {
        return service.paramFloat(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramFloatAsync(String Scenario, float Value) {
        return paramFloatWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramFloat(String Scenario, float Value) {
        paramFloatAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseFloatResponse> responseFloatWithResponseAsync(String Scenario) {
        return service.responseFloat(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseFloatAsync(String Scenario) {
        return responseFloatWithResponseAsync(Scenario)
            .flatMap((HeadersResponseFloatResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseFloat(String Scenario) {
        responseFloatAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDoubleWithResponseAsync(String Scenario, double Value) {
        return service.paramDouble(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDoubleAsync(String Scenario, double Value) {
        return paramDoubleWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDouble(String Scenario, double Value) {
        paramDoubleAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDoubleResponse> responseDoubleWithResponseAsync(String Scenario) {
        return service.responseDouble(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDoubleAsync(String Scenario) {
        return responseDoubleWithResponseAsync(Scenario)
            .flatMap((HeadersResponseDoubleResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDouble(String Scenario) {
        responseDoubleAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramBoolWithResponseAsync(String Scenario, boolean Value) {
        return service.paramBool(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramBoolAsync(String Scenario, boolean Value) {
        return paramBoolWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramBool(String Scenario, boolean Value) {
        paramBoolAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseBoolResponse> responseBoolWithResponseAsync(String Scenario) {
        return service.responseBool(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseBoolAsync(String Scenario) {
        return responseBoolWithResponseAsync(Scenario)
            .flatMap((HeadersResponseBoolResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseBool(String Scenario) {
        responseBoolAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramStringWithResponseAsync(String Scenario, String Value) {
        return service.paramString(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramStringAsync(String Scenario, String Value) {
        return paramStringWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramString(String Scenario, String Value) {
        paramStringAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseStringResponse> responseStringWithResponseAsync(String Scenario) {
        return service.responseString(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseStringAsync(String Scenario) {
        return responseStringWithResponseAsync(Scenario)
            .flatMap((HeadersResponseStringResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseString(String Scenario) {
        responseStringAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDateWithResponseAsync(String Scenario, LocalDate Value) {
        return service.paramDate(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDateAsync(String Scenario, LocalDate Value) {
        return paramDateWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDate(String Scenario, LocalDate Value) {
        paramDateAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDateResponse> responseDateWithResponseAsync(String Scenario) {
        return service.responseDate(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDateAsync(String Scenario) {
        return responseDateWithResponseAsync(Scenario)
            .flatMap((HeadersResponseDateResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDate(String Scenario) {
        responseDateAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeWithResponseAsync(String Scenario, OffsetDateTime Value) {
        return service.paramDatetime(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeAsync(String Scenario, OffsetDateTime Value) {
        return paramDatetimeWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetime(String Scenario, OffsetDateTime Value) {
        paramDatetimeAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDatetimeResponse> responseDatetimeWithResponseAsync(String Scenario) {
        return service.responseDatetime(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeAsync(String Scenario) {
        return responseDatetimeWithResponseAsync(Scenario)
            .flatMap((HeadersResponseDatetimeResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetime(String Scenario) {
        responseDatetimeAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeRfc1123WithResponseAsync(String Scenario, OffsetDateTime Value) {
        DateTimeRfc1123 valueConverted = Value == null ? null : new DateTimeRfc1123(Value);
        return service.paramDatetimeRfc1123(this.client.getHost(), Scenario, valueConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeRfc1123Async(String Scenario, OffsetDateTime Value) {
        return paramDatetimeRfc1123WithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetimeRfc1123(String Scenario, OffsetDateTime Value) {
        paramDatetimeRfc1123Async(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDatetimeRfc1123Response> responseDatetimeRfc1123WithResponseAsync(String Scenario) {
        return service.responseDatetimeRfc1123(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeRfc1123Async(String Scenario) {
        return responseDatetimeRfc1123WithResponseAsync(Scenario)
            .flatMap((HeadersResponseDatetimeRfc1123Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetimeRfc1123(String Scenario) {
        responseDatetimeRfc1123Async(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDurationWithResponseAsync(String Scenario, Duration Value) {
        return service.paramDuration(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDurationAsync(String Scenario, Duration Value) {
        return paramDurationWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDuration(String Scenario, Duration Value) {
        paramDurationAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDurationResponse> responseDurationWithResponseAsync(String Scenario) {
        return service.responseDuration(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDurationAsync(String Scenario) {
        return responseDurationWithResponseAsync(Scenario)
            .flatMap((HeadersResponseDurationResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDuration(String Scenario) {
        responseDurationAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramByteWithResponseAsync(String Scenario, byte[] Value) {
        String valueConverted = Base64Util.encodeToString(Value);
        return service.paramByte(this.client.getHost(), Scenario, valueConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramByteAsync(String Scenario, byte[] Value) {
        return paramByteWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramByte(String Scenario, byte[] Value) {
        paramByteAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseByteResponse> responseByteWithResponseAsync(String Scenario) {
        return service.responseByte(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseByteAsync(String Scenario) {
        return responseByteWithResponseAsync(Scenario)
            .flatMap((HeadersResponseByteResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseByte(String Scenario) {
        responseByteAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramEnumWithResponseAsync(String Scenario, GreyscaleColors Value) {
        return service.paramEnum(this.client.getHost(), Scenario, Value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramEnumAsync(String Scenario, GreyscaleColors Value) {
        return paramEnumWithResponseAsync(Scenario, Value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramEnum(String Scenario, GreyscaleColors Value) {
        paramEnumAsync(Scenario, Value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseEnumResponse> responseEnumWithResponseAsync(String Scenario) {
        return service.responseEnum(this.client.getHost(), Scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseEnumAsync(String Scenario) {
        return responseEnumWithResponseAsync(Scenario)
            .flatMap((HeadersResponseEnumResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseEnum(String Scenario) {
        responseEnumAsync(Scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> customRequestIdWithResponseAsync() {
        return service.customRequestId(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> customRequestIdAsync() {
        return customRequestIdWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void customRequestId() {
        customRequestIdAsync().block();
    }
}
