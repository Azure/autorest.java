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
        Mono<Response<Void>> paramExistingKey(@HostParam("$host") String host, @HeaderParam("User-Agent") String userAgent);

        @Post("/header/response/existingkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseExistingKeyResponse> responseExistingKey(@HostParam("$host") String host);

        @Post("/header/param/protectedkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramProtectedKey(@HostParam("$host") String host, @HeaderParam("Content-Type") String contentType);

        @Post("/header/response/protectedkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseProtectedKeyResponse> responseProtectedKey(@HostParam("$host") String host);

        @Post("/header/param/prim/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramInteger(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") int value);

        @Post("/header/response/prim/integer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseIntegerResponse> responseInteger(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramLong(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") long value);

        @Post("/header/response/prim/long")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseLongResponse> responseLong(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramFloat(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") float value);

        @Post("/header/response/prim/float")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseFloatResponse> responseFloat(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDouble(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") double value);

        @Post("/header/response/prim/double")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDoubleResponse> responseDouble(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramBool(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") boolean value);

        @Post("/header/response/prim/bool")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseBoolResponse> responseBool(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramString(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") String value);

        @Post("/header/response/prim/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseStringResponse> responseString(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDate(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") LocalDate value);

        @Post("/header/response/prim/date")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDateResponse> responseDate(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDatetime(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") OffsetDateTime value);

        @Post("/header/response/prim/datetime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDatetimeResponse> responseDatetime(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDatetimeRfc1123(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") DateTimeRfc1123 value);

        @Post("/header/response/prim/datetimerfc1123")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDatetimeRfc1123Response> responseDatetimeRfc1123(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramDuration(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") Duration value);

        @Post("/header/response/prim/duration")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseDurationResponse> responseDuration(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramByte(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") String value);

        @Post("/header/response/prim/byte")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseByteResponse> responseByte(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/param/prim/enum")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> paramEnum(@HostParam("$host") String host, @HeaderParam("scenario") String scenario, @HeaderParam("value") GreyscaleColors value);

        @Post("/header/response/prim/enum")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HeadersResponseEnumResponse> responseEnum(@HostParam("$host") String host, @HeaderParam("scenario") String scenario);

        @Post("/header/custom/x-ms-client-request-id/9C4D50EE-2D56-4CD3-8152-34347DC9F2B0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> customRequestId(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramExistingKeyWithResponseAsync(String userAgent) {
        return service.paramExistingKey(this.client.getHost(), userAgent);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramExistingKeyAsync(String userAgent) {
        return paramExistingKeyWithResponseAsync(userAgent)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramExistingKey(String userAgent) {
        paramExistingKeyAsync(userAgent).block();
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
    public Mono<Response<Void>> paramProtectedKeyWithResponseAsync(String contentType) {
        return service.paramProtectedKey(this.client.getHost(), contentType);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramProtectedKeyAsync(String contentType) {
        return paramProtectedKeyWithResponseAsync(contentType)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramProtectedKey(String contentType) {
        paramProtectedKeyAsync(contentType).block();
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
    public Mono<Response<Void>> paramIntegerWithResponseAsync(String scenario, int value) {
        return service.paramInteger(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramIntegerAsync(String scenario, int value) {
        return paramIntegerWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramInteger(String scenario, int value) {
        paramIntegerAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseIntegerResponse> responseIntegerWithResponseAsync(String scenario) {
        return service.responseInteger(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseIntegerAsync(String scenario) {
        return responseIntegerWithResponseAsync(scenario)
            .flatMap((HeadersResponseIntegerResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseInteger(String scenario) {
        responseIntegerAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramLongWithResponseAsync(String scenario, long value) {
        return service.paramLong(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramLongAsync(String scenario, long value) {
        return paramLongWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramLong(String scenario, long value) {
        paramLongAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseLongResponse> responseLongWithResponseAsync(String scenario) {
        return service.responseLong(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseLongAsync(String scenario) {
        return responseLongWithResponseAsync(scenario)
            .flatMap((HeadersResponseLongResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseLong(String scenario) {
        responseLongAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramFloatWithResponseAsync(String scenario, float value) {
        return service.paramFloat(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramFloatAsync(String scenario, float value) {
        return paramFloatWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramFloat(String scenario, float value) {
        paramFloatAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseFloatResponse> responseFloatWithResponseAsync(String scenario) {
        return service.responseFloat(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseFloatAsync(String scenario) {
        return responseFloatWithResponseAsync(scenario)
            .flatMap((HeadersResponseFloatResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseFloat(String scenario) {
        responseFloatAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDoubleWithResponseAsync(String scenario, double value) {
        return service.paramDouble(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDoubleAsync(String scenario, double value) {
        return paramDoubleWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDouble(String scenario, double value) {
        paramDoubleAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDoubleResponse> responseDoubleWithResponseAsync(String scenario) {
        return service.responseDouble(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDoubleAsync(String scenario) {
        return responseDoubleWithResponseAsync(scenario)
            .flatMap((HeadersResponseDoubleResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDouble(String scenario) {
        responseDoubleAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramBoolWithResponseAsync(String scenario, boolean value) {
        return service.paramBool(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramBoolAsync(String scenario, boolean value) {
        return paramBoolWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramBool(String scenario, boolean value) {
        paramBoolAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseBoolResponse> responseBoolWithResponseAsync(String scenario) {
        return service.responseBool(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseBoolAsync(String scenario) {
        return responseBoolWithResponseAsync(scenario)
            .flatMap((HeadersResponseBoolResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseBool(String scenario) {
        responseBoolAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramStringWithResponseAsync(String scenario, String value) {
        return service.paramString(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramStringAsync(String scenario, String value) {
        return paramStringWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramString(String scenario, String value) {
        paramStringAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseStringResponse> responseStringWithResponseAsync(String scenario) {
        return service.responseString(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseStringAsync(String scenario) {
        return responseStringWithResponseAsync(scenario)
            .flatMap((HeadersResponseStringResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseString(String scenario) {
        responseStringAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDateWithResponseAsync(String scenario, LocalDate value) {
        return service.paramDate(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDateAsync(String scenario, LocalDate value) {
        return paramDateWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDate(String scenario, LocalDate value) {
        paramDateAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDateResponse> responseDateWithResponseAsync(String scenario) {
        return service.responseDate(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDateAsync(String scenario) {
        return responseDateWithResponseAsync(scenario)
            .flatMap((HeadersResponseDateResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDate(String scenario) {
        responseDateAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeWithResponseAsync(String scenario, OffsetDateTime value) {
        return service.paramDatetime(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeAsync(String scenario, OffsetDateTime value) {
        return paramDatetimeWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetime(String scenario, OffsetDateTime value) {
        paramDatetimeAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDatetimeResponse> responseDatetimeWithResponseAsync(String scenario) {
        return service.responseDatetime(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeAsync(String scenario) {
        return responseDatetimeWithResponseAsync(scenario)
            .flatMap((HeadersResponseDatetimeResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetime(String scenario) {
        responseDatetimeAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeRfc1123WithResponseAsync(String scenario, OffsetDateTime value) {
        DateTimeRfc1123 valueConverted = value == null ? null : new DateTimeRfc1123(value);
        return service.paramDatetimeRfc1123(this.client.getHost(), scenario, valueConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeRfc1123Async(String scenario, OffsetDateTime value) {
        return paramDatetimeRfc1123WithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetimeRfc1123(String scenario, OffsetDateTime value) {
        paramDatetimeRfc1123Async(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDatetimeRfc1123Response> responseDatetimeRfc1123WithResponseAsync(String scenario) {
        return service.responseDatetimeRfc1123(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeRfc1123Async(String scenario) {
        return responseDatetimeRfc1123WithResponseAsync(scenario)
            .flatMap((HeadersResponseDatetimeRfc1123Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetimeRfc1123(String scenario) {
        responseDatetimeRfc1123Async(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDurationWithResponseAsync(String scenario, Duration value) {
        return service.paramDuration(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDurationAsync(String scenario, Duration value) {
        return paramDurationWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDuration(String scenario, Duration value) {
        paramDurationAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseDurationResponse> responseDurationWithResponseAsync(String scenario) {
        return service.responseDuration(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDurationAsync(String scenario) {
        return responseDurationWithResponseAsync(scenario)
            .flatMap((HeadersResponseDurationResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDuration(String scenario) {
        responseDurationAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramByteWithResponseAsync(String scenario, byte[] value) {
        String valueConverted = Base64Util.encodeToString(value);
        return service.paramByte(this.client.getHost(), scenario, valueConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramByteAsync(String scenario, byte[] value) {
        return paramByteWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramByte(String scenario, byte[] value) {
        paramByteAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseByteResponse> responseByteWithResponseAsync(String scenario) {
        return service.responseByte(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseByteAsync(String scenario) {
        return responseByteWithResponseAsync(scenario)
            .flatMap((HeadersResponseByteResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseByte(String scenario) {
        responseByteAsync(scenario).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramEnumWithResponseAsync(String scenario, GreyscaleColors value) {
        return service.paramEnum(this.client.getHost(), scenario, value);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramEnumAsync(String scenario, GreyscaleColors value) {
        return paramEnumWithResponseAsync(scenario, value)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramEnum(String scenario, GreyscaleColors value) {
        paramEnumAsync(scenario, value).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HeadersResponseEnumResponse> responseEnumWithResponseAsync(String scenario) {
        return service.responseEnum(this.client.getHost(), scenario);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseEnumAsync(String scenario) {
        return responseEnumWithResponseAsync(scenario)
            .flatMap((HeadersResponseEnumResponse res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseEnum(String scenario) {
        responseEnumAsync(scenario).block();
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
