package fixtures.bodydictionary;

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
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.implementation.Base64Url;
import com.azure.core.implementation.DateTimeRfc1123;
import com.azure.core.implementation.RestProxy;
import fixtures.bodydictionary.models.ErrorException;
import fixtures.bodydictionary.models.Widget;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Dictionarys.
 */
public final class Dictionarys {
    /**
     * The proxy service used to perform REST calls.
     */
    private DictionarysService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestSwaggerBATdictionaryService client;

    /**
     * Initializes an instance of Dictionarys.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Dictionarys(AutoRestSwaggerBATdictionaryService client) {
        this.service = RestProxy.create(DictionarysService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBATdictionaryServiceDictionarys to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATdictionaryServiceDictionarys")
    private interface DictionarysService {
        @Get("/dictionary/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getNull(@HostParam("$host") String host);

        @Get("/dictionary/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getEmpty(@HostParam("$host") String host);

        @Put("/dictionary/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@HostParam("$host") String host, @BodyParam("application/json") Map<String, String> arrayBody);

        @Get("/dictionary/nullvalue")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getNullValue(@HostParam("$host") String host);

        @Get("/dictionary/nullkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getNullKey(@HostParam("$host") String host);

        @Get("/dictionary/keyemptystring")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getEmptyStringKey(@HostParam("$host") String host);

        @Get("/dictionary/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getInvalid(@HostParam("$host") String host);

        @Get("/dictionary/prim/boolean/tfft")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Boolean>>> getBooleanTfft(@HostParam("$host") String host);

        @Put("/dictionary/prim/boolean/tfft")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBooleanTfft(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Boolean> arrayBody);

        @Get("/dictionary/prim/boolean/true.null.false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidNull(@HostParam("$host") String host);

        @Get("/dictionary/prim/boolean/true.boolean.false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidString(@HostParam("$host") String host);

        @Get("/dictionary/prim/integer/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getIntegerValid(@HostParam("$host") String host);

        @Put("/dictionary/prim/integer/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putIntegerValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Integer> arrayBody);

        @Get("/dictionary/prim/integer/1.null.zero")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidNull(@HostParam("$host") String host);

        @Get("/dictionary/prim/integer/1.integer.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidString(@HostParam("$host") String host);

        @Get("/dictionary/prim/long/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Long>>> getLongValid(@HostParam("$host") String host);

        @Put("/dictionary/prim/long/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLongValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Long> arrayBody);

        @Get("/dictionary/prim/long/1.null.zero")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Long>>> getLongInvalidNull(@HostParam("$host") String host);

        @Get("/dictionary/prim/long/1.integer.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Long>>> getLongInvalidString(@HostParam("$host") String host);

        @Get("/dictionary/prim/float/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Float>>> getFloatValid(@HostParam("$host") String host);

        @Put("/dictionary/prim/float/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFloatValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Float> arrayBody);

        @Get("/dictionary/prim/float/0.0-null-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidNull(@HostParam("$host") String host);

        @Get("/dictionary/prim/float/1.number.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidString(@HostParam("$host") String host);

        @Get("/dictionary/prim/double/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Double>>> getDoubleValid(@HostParam("$host") String host);

        @Put("/dictionary/prim/double/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDoubleValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Double> arrayBody);

        @Get("/dictionary/prim/double/0.0-null-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidNull(@HostParam("$host") String host);

        @Get("/dictionary/prim/double/1.number.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidString(@HostParam("$host") String host);

        @Get("/dictionary/prim/string/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getStringValid(@HostParam("$host") String host);

        @Put("/dictionary/prim/string/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putStringValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, String> arrayBody);

        @Get("/dictionary/prim/string/foo.null.foo2")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getStringWithNull(@HostParam("$host") String host);

        @Get("/dictionary/prim/string/foo.123.foo2")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getStringWithInvalid(@HostParam("$host") String host);

        @Get("/dictionary/prim/date/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, LocalDate>>> getDateValid(@HostParam("$host") String host);

        @Put("/dictionary/prim/date/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, LocalDate> arrayBody);

        @Get("/dictionary/prim/date/invalidnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidNull(@HostParam("$host") String host);

        @Get("/dictionary/prim/date/invalidchars")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidChars(@HostParam("$host") String host);

        @Get("/dictionary/prim/date-time/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeValid(@HostParam("$host") String host);

        @Put("/dictionary/prim/date-time/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, OffsetDateTime> arrayBody);

        @Get("/dictionary/prim/date-time/invalidnull")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidNull(@HostParam("$host") String host);

        @Get("/dictionary/prim/date-time/invalidchars")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidChars(@HostParam("$host") String host);

        @Get("/dictionary/prim/date-time-rfc1123/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeRfc1123Valid(@HostParam("$host") String host);

        @Put("/dictionary/prim/date-time-rfc1123/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeRfc1123Valid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, DateTimeRfc1123> arrayBody);

        @Get("/dictionary/prim/duration/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Duration>>> getDurationValid(@HostParam("$host") String host);

        @Put("/dictionary/prim/duration/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDurationValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Duration> arrayBody);

        @Get("/dictionary/prim/byte/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, byte[]>>> getByteValid(@HostParam("$host") String host);

        @Put("/dictionary/prim/byte/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putByteValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, byte[]> arrayBody);

        @Get("/dictionary/prim/byte/invalidnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, byte[]>>> getByteInvalidNull(@HostParam("$host") String host);

        @Get("/dictionary/prim/base64url/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, byte[]>>> getBase64Url(@HostParam("$host") String host);

        @Get("/dictionary/complex/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexNull(@HostParam("$host") String host);

        @Get("/dictionary/complex/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexEmpty(@HostParam("$host") String host);

        @Get("/dictionary/complex/itemnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexItemNull(@HostParam("$host") String host);

        @Get("/dictionary/complex/itemempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexItemEmpty(@HostParam("$host") String host);

        @Get("/dictionary/complex/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexValid(@HostParam("$host") String host);

        @Put("/dictionary/complex/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putComplexValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Widget> arrayBody);

        @Get("/dictionary/array/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayNull(@HostParam("$host") String host);

        @Get("/dictionary/array/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayEmpty(@HostParam("$host") String host);

        @Get("/dictionary/array/itemnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemNull(@HostParam("$host") String host);

        @Get("/dictionary/array/itemempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemEmpty(@HostParam("$host") String host);

        @Get("/dictionary/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayValid(@HostParam("$host") String host);

        @Put("/dictionary/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putArrayValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, List<String>> arrayBody);

        @Get("/dictionary/dictionary/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryNull(@HostParam("$host") String host);

        @Get("/dictionary/dictionary/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryEmpty(@HostParam("$host") String host);

        @Get("/dictionary/dictionary/itemnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemNull(@HostParam("$host") String host);

        @Get("/dictionary/dictionary/itemempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemEmpty(@HostParam("$host") String host);

        @Get("/dictionary/dictionary/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryValid(@HostParam("$host") String host);

        @Put("/dictionary/dictionary/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDictionaryValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Object> arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getNullWithResponseAsync() {
        return service.getNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Integer>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getNull() {
        return getNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getEmptyWithResponseAsync() {
        return service.getEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getEmptyAsync() {
        return getEmptyWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Integer>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getEmpty() {
        return getEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(Map<String, String> arrayBody) {
        return service.putEmpty(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(Map<String, String> arrayBody) {
        return putEmptyWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(Map<String, String> arrayBody) {
        putEmptyAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getNullValueWithResponseAsync() {
        return service.getNullValue(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getNullValueAsync() {
        return getNullValueWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, String>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getNullValue() {
        return getNullValueAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getNullKeyWithResponseAsync() {
        return service.getNullKey(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getNullKeyAsync() {
        return getNullKeyWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, String>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getNullKey() {
        return getNullKeyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getEmptyStringKeyWithResponseAsync() {
        return service.getEmptyStringKey(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getEmptyStringKeyAsync() {
        return getEmptyStringKeyWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, String>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getEmptyStringKey() {
        return getEmptyStringKeyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getInvalidWithResponseAsync() {
        return service.getInvalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getInvalidAsync() {
        return getInvalidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, String>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getInvalid() {
        return getInvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanTfftWithResponseAsync() {
        return service.getBooleanTfft(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Boolean>> getBooleanTfftAsync() {
        return getBooleanTfftWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Boolean>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Boolean> getBooleanTfft() {
        return getBooleanTfftAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBooleanTfftWithResponseAsync(Map<String, Boolean> arrayBody) {
        return service.putBooleanTfft(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBooleanTfftAsync(Map<String, Boolean> arrayBody) {
        return putBooleanTfftWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBooleanTfft(Map<String, Boolean> arrayBody) {
        putBooleanTfftAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidNullWithResponseAsync() {
        return service.getBooleanInvalidNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Boolean>> getBooleanInvalidNullAsync() {
        return getBooleanInvalidNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Boolean>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Boolean> getBooleanInvalidNull() {
        return getBooleanInvalidNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidStringWithResponseAsync() {
        return service.getBooleanInvalidString(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Boolean>> getBooleanInvalidStringAsync() {
        return getBooleanInvalidStringWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Boolean>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Boolean> getBooleanInvalidString() {
        return getBooleanInvalidStringAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntegerValidWithResponseAsync() {
        return service.getIntegerValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getIntegerValidAsync() {
        return getIntegerValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Integer>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getIntegerValid() {
        return getIntegerValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putIntegerValidWithResponseAsync(Map<String, Integer> arrayBody) {
        return service.putIntegerValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putIntegerValidAsync(Map<String, Integer> arrayBody) {
        return putIntegerValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putIntegerValid(Map<String, Integer> arrayBody) {
        putIntegerValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidNullWithResponseAsync() {
        return service.getIntInvalidNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getIntInvalidNullAsync() {
        return getIntInvalidNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Integer>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getIntInvalidNull() {
        return getIntInvalidNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidStringWithResponseAsync() {
        return service.getIntInvalidString(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getIntInvalidStringAsync() {
        return getIntInvalidStringWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Integer>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getIntInvalidString() {
        return getIntInvalidStringAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongValidWithResponseAsync() {
        return service.getLongValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Long>> getLongValidAsync() {
        return getLongValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Long>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Long> getLongValid() {
        return getLongValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLongValidWithResponseAsync(Map<String, Long> arrayBody) {
        return service.putLongValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLongValidAsync(Map<String, Long> arrayBody) {
        return putLongValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLongValid(Map<String, Long> arrayBody) {
        putLongValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongInvalidNullWithResponseAsync() {
        return service.getLongInvalidNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Long>> getLongInvalidNullAsync() {
        return getLongInvalidNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Long>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Long> getLongInvalidNull() {
        return getLongInvalidNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongInvalidStringWithResponseAsync() {
        return service.getLongInvalidString(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Long>> getLongInvalidStringAsync() {
        return getLongInvalidStringWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Long>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Long> getLongInvalidString() {
        return getLongInvalidStringAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatValidWithResponseAsync() {
        return service.getFloatValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Float>> getFloatValidAsync() {
        return getFloatValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Float>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Float> getFloatValid() {
        return getFloatValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFloatValidWithResponseAsync(Map<String, Float> arrayBody) {
        return service.putFloatValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFloatValidAsync(Map<String, Float> arrayBody) {
        return putFloatValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFloatValid(Map<String, Float> arrayBody) {
        putFloatValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidNullWithResponseAsync() {
        return service.getFloatInvalidNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Float>> getFloatInvalidNullAsync() {
        return getFloatInvalidNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Float>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Float> getFloatInvalidNull() {
        return getFloatInvalidNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidStringWithResponseAsync() {
        return service.getFloatInvalidString(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Float>> getFloatInvalidStringAsync() {
        return getFloatInvalidStringWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Float>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Float> getFloatInvalidString() {
        return getFloatInvalidStringAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleValidWithResponseAsync() {
        return service.getDoubleValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Double>> getDoubleValidAsync() {
        return getDoubleValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Double>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Double> getDoubleValid() {
        return getDoubleValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDoubleValidWithResponseAsync(Map<String, Double> arrayBody) {
        return service.putDoubleValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDoubleValidAsync(Map<String, Double> arrayBody) {
        return putDoubleValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDoubleValid(Map<String, Double> arrayBody) {
        putDoubleValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidNullWithResponseAsync() {
        return service.getDoubleInvalidNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Double>> getDoubleInvalidNullAsync() {
        return getDoubleInvalidNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Double>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Double> getDoubleInvalidNull() {
        return getDoubleInvalidNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidStringWithResponseAsync() {
        return service.getDoubleInvalidString(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Double>> getDoubleInvalidStringAsync() {
        return getDoubleInvalidStringWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Double>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Double> getDoubleInvalidString() {
        return getDoubleInvalidStringAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringValidWithResponseAsync() {
        return service.getStringValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getStringValidAsync() {
        return getStringValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, String>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getStringValid() {
        return getStringValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringValidWithResponseAsync(Map<String, String> arrayBody) {
        return service.putStringValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putStringValidAsync(Map<String, String> arrayBody) {
        return putStringValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putStringValid(Map<String, String> arrayBody) {
        putStringValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringWithNullWithResponseAsync() {
        return service.getStringWithNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getStringWithNullAsync() {
        return getStringWithNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, String>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getStringWithNull() {
        return getStringWithNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringWithInvalidWithResponseAsync() {
        return service.getStringWithInvalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, String>> getStringWithInvalidAsync() {
        return getStringWithInvalidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, String>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getStringWithInvalid() {
        return getStringWithInvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateValidWithResponseAsync() {
        return service.getDateValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, LocalDate>> getDateValidAsync() {
        return getDateValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, LocalDate>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, LocalDate> getDateValid() {
        return getDateValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateValidWithResponseAsync(Map<String, LocalDate> arrayBody) {
        return service.putDateValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateValidAsync(Map<String, LocalDate> arrayBody) {
        return putDateValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateValid(Map<String, LocalDate> arrayBody) {
        putDateValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidNullWithResponseAsync() {
        return service.getDateInvalidNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, LocalDate>> getDateInvalidNullAsync() {
        return getDateInvalidNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, LocalDate>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, LocalDate> getDateInvalidNull() {
        return getDateInvalidNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidCharsWithResponseAsync() {
        return service.getDateInvalidChars(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, LocalDate>> getDateInvalidCharsAsync() {
        return getDateInvalidCharsWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, LocalDate>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, LocalDate> getDateInvalidChars() {
        return getDateInvalidCharsAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeValidWithResponseAsync() {
        return service.getDateTimeValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeValidAsync() {
        return getDateTimeValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, OffsetDateTime>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeValid() {
        return getDateTimeValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeValidWithResponseAsync(Map<String, OffsetDateTime> arrayBody) {
        return service.putDateTimeValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeValidAsync(Map<String, OffsetDateTime> arrayBody) {
        return putDateTimeValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeValid(Map<String, OffsetDateTime> arrayBody) {
        putDateTimeValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidNullWithResponseAsync() {
        return service.getDateTimeInvalidNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeInvalidNullAsync() {
        return getDateTimeInvalidNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, OffsetDateTime>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeInvalidNull() {
        return getDateTimeInvalidNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidCharsWithResponseAsync() {
        return service.getDateTimeInvalidChars(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeInvalidCharsAsync() {
        return getDateTimeInvalidCharsWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, OffsetDateTime>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeInvalidChars() {
        return getDateTimeInvalidCharsAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeRfc1123ValidWithResponseAsync() {
        return service.getDateTimeRfc1123Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, OffsetDateTime>> getDateTimeRfc1123ValidAsync() {
        return getDateTimeRfc1123ValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, OffsetDateTime>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeRfc1123Valid() {
        return getDateTimeRfc1123ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeRfc1123ValidWithResponseAsync(Map<String, OffsetDateTime> arrayBody) {
        Map<String, DateTimeRfc1123> arrayBodyConverted = arrayBody.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, el -> new DateTimeRfc1123(el.getValue())));
        return service.putDateTimeRfc1123Valid(this.client.getHost(), arrayBodyConverted);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeRfc1123ValidAsync(Map<String, OffsetDateTime> arrayBody) {
        return putDateTimeRfc1123ValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeRfc1123Valid(Map<String, OffsetDateTime> arrayBody) {
        putDateTimeRfc1123ValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Duration>>> getDurationValidWithResponseAsync() {
        return service.getDurationValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Duration>> getDurationValidAsync() {
        return getDurationValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Duration>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Duration> getDurationValid() {
        return getDurationValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDurationValidWithResponseAsync(Map<String, Duration> arrayBody) {
        return service.putDurationValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDurationValidAsync(Map<String, Duration> arrayBody) {
        return putDurationValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDurationValid(Map<String, Duration> arrayBody) {
        putDurationValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getByteValidWithResponseAsync() {
        return service.getByteValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, byte[]>> getByteValidAsync() {
        return getByteValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, byte[]>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, byte[]> getByteValid() {
        return getByteValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putByteValidWithResponseAsync(Map<String, byte[]> arrayBody) {
        return service.putByteValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putByteValidAsync(Map<String, byte[]> arrayBody) {
        return putByteValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putByteValid(Map<String, byte[]> arrayBody) {
        putByteValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getByteInvalidNullWithResponseAsync() {
        return service.getByteInvalidNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, byte[]>> getByteInvalidNullAsync() {
        return getByteInvalidNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, byte[]>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, byte[]> getByteInvalidNull() {
        return getByteInvalidNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getBase64UrlWithResponseAsync() {
        return service.getBase64Url(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, byte[]>> getBase64UrlAsync() {
        return getBase64UrlWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, byte[]>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, byte[]> getBase64Url() {
        return getBase64UrlAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexNullWithResponseAsync() {
        return service.getComplexNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexNullAsync() {
        return getComplexNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Widget>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexNull() {
        return getComplexNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexEmptyWithResponseAsync() {
        return service.getComplexEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexEmptyAsync() {
        return getComplexEmptyWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Widget>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexEmpty() {
        return getComplexEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexItemNullWithResponseAsync() {
        return service.getComplexItemNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexItemNullAsync() {
        return getComplexItemNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Widget>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexItemNull() {
        return getComplexItemNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexItemEmptyWithResponseAsync() {
        return service.getComplexItemEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexItemEmptyAsync() {
        return getComplexItemEmptyWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Widget>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexItemEmpty() {
        return getComplexItemEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexValidWithResponseAsync() {
        return service.getComplexValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Widget>> getComplexValidAsync() {
        return getComplexValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Widget>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexValid() {
        return getComplexValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplexValidWithResponseAsync(Map<String, Widget> arrayBody) {
        return service.putComplexValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplexValidAsync(Map<String, Widget> arrayBody) {
        return putComplexValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putComplexValid(Map<String, Widget> arrayBody) {
        putComplexValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayNullWithResponseAsync() {
        return service.getArrayNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayNullAsync() {
        return getArrayNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, List<String>>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayNull() {
        return getArrayNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayEmptyWithResponseAsync() {
        return service.getArrayEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayEmptyAsync() {
        return getArrayEmptyWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, List<String>>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayEmpty() {
        return getArrayEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemNullWithResponseAsync() {
        return service.getArrayItemNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayItemNullAsync() {
        return getArrayItemNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, List<String>>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayItemNull() {
        return getArrayItemNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemEmptyWithResponseAsync() {
        return service.getArrayItemEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayItemEmptyAsync() {
        return getArrayItemEmptyWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, List<String>>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayItemEmpty() {
        return getArrayItemEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayValidWithResponseAsync() {
        return service.getArrayValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, List<String>>> getArrayValidAsync() {
        return getArrayValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, List<String>>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayValid() {
        return getArrayValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putArrayValidWithResponseAsync(Map<String, List<String>> arrayBody) {
        return service.putArrayValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putArrayValidAsync(Map<String, List<String>> arrayBody) {
        return putArrayValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putArrayValid(Map<String, List<String>> arrayBody) {
        putArrayValidAsync(arrayBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryNullWithResponseAsync() {
        return service.getDictionaryNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryNullAsync() {
        return getDictionaryNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Object>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryNull() {
        return getDictionaryNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryEmptyWithResponseAsync() {
        return service.getDictionaryEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryEmptyAsync() {
        return getDictionaryEmptyWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Object>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryEmpty() {
        return getDictionaryEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemNullWithResponseAsync() {
        return service.getDictionaryItemNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryItemNullAsync() {
        return getDictionaryItemNullWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Object>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryItemNull() {
        return getDictionaryItemNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemEmptyWithResponseAsync() {
        return service.getDictionaryItemEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryItemEmptyAsync() {
        return getDictionaryItemEmptyWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Object>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryItemEmpty() {
        return getDictionaryItemEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryValidWithResponseAsync() {
        return service.getDictionaryValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Object>> getDictionaryValidAsync() {
        return getDictionaryValidWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, Object>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryValid() {
        return getDictionaryValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDictionaryValidWithResponseAsync(Map<String, Object> arrayBody) {
        return service.putDictionaryValid(this.client.getHost(), arrayBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDictionaryValidAsync(Map<String, Object> arrayBody) {
        return putDictionaryValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDictionaryValid(Map<String, Object> arrayBody) {
        putDictionaryValidAsync(arrayBody).block();
    }
}
