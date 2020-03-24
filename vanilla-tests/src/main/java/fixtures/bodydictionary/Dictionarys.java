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
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Base64Url;
import com.azure.core.util.Context;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
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
    private AutoRestSwaggerBATDictionaryService client;

    /**
     * Initializes an instance of Dictionarys.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Dictionarys(AutoRestSwaggerBATDictionaryService client) {
        this.service = RestProxy.create(DictionarysService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBATDictionaryServiceDictionarys to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATDictionaryServiceDictionarys")
    private interface DictionarysService {
        @Get("/dictionary/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getEmpty(@HostParam("$host") String host, Context context);

        @Put("/dictionary/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@HostParam("$host") String host, @BodyParam("application/json") Map<String, String> arrayBody, Context context);

        @Get("/dictionary/nullvalue")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getNullValue(@HostParam("$host") String host, Context context);

        @Get("/dictionary/nullkey")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getNullKey(@HostParam("$host") String host, Context context);

        @Get("/dictionary/keyemptystring")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getEmptyStringKey(@HostParam("$host") String host, Context context);

        @Get("/dictionary/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getInvalid(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/boolean/tfft")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Boolean>>> getBooleanTfft(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/boolean/tfft")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBooleanTfft(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Boolean> arrayBody, Context context);

        @Get("/dictionary/prim/boolean/true.null.false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/boolean/true.boolean.false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidString(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/integer/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getIntegerValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/integer/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putIntegerValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Integer> arrayBody, Context context);

        @Get("/dictionary/prim/integer/1.null.zero")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/integer/1.integer.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidString(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/long/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Long>>> getLongValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/long/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLongValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Long> arrayBody, Context context);

        @Get("/dictionary/prim/long/1.null.zero")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Long>>> getLongInvalidNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/long/1.integer.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Long>>> getLongInvalidString(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/float/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Float>>> getFloatValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/float/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFloatValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Float> arrayBody, Context context);

        @Get("/dictionary/prim/float/0.0-null-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/float/1.number.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidString(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/double/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Double>>> getDoubleValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/double/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDoubleValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Double> arrayBody, Context context);

        @Get("/dictionary/prim/double/0.0-null-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/double/1.number.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidString(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/string/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getStringValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/string/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putStringValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, String> arrayBody, Context context);

        @Get("/dictionary/prim/string/foo.null.foo2")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getStringWithNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/string/foo.123.foo2")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, String>>> getStringWithInvalid(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/date/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, LocalDate>>> getDateValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/date/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, LocalDate> arrayBody, Context context);

        @Get("/dictionary/prim/date/invalidnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/date/invalidchars")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidChars(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/date-time/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/date-time/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, OffsetDateTime> arrayBody, Context context);

        @Get("/dictionary/prim/date-time/invalidnull")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/date-time/invalidchars")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidChars(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/date-time-rfc1123/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeRfc1123Valid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/date-time-rfc1123/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeRfc1123Valid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, DateTimeRfc1123> arrayBody, Context context);

        @Get("/dictionary/prim/duration/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Duration>>> getDurationValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/duration/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDurationValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Duration> arrayBody, Context context);

        @Get("/dictionary/prim/byte/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, byte[]>>> getByteValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/prim/byte/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putByteValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, byte[]> arrayBody, Context context);

        @Get("/dictionary/prim/byte/invalidnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, byte[]>>> getByteInvalidNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/prim/base64url/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, byte[]>>> getBase64Url(@HostParam("$host") String host, Context context);

        @Get("/dictionary/complex/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/complex/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexEmpty(@HostParam("$host") String host, Context context);

        @Get("/dictionary/complex/itemnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexItemNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/complex/itemempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexItemEmpty(@HostParam("$host") String host, Context context);

        @Get("/dictionary/complex/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Widget>>> getComplexValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/complex/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putComplexValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Widget> arrayBody, Context context);

        @Get("/dictionary/array/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/array/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayEmpty(@HostParam("$host") String host, Context context);

        @Get("/dictionary/array/itemnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/array/itemempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemEmpty(@HostParam("$host") String host, Context context);

        @Get("/dictionary/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, List<String>>>> getArrayValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putArrayValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, List<String>> arrayBody, Context context);

        @Get("/dictionary/dictionary/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/dictionary/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryEmpty(@HostParam("$host") String host, Context context);

        @Get("/dictionary/dictionary/itemnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemNull(@HostParam("$host") String host, Context context);

        @Get("/dictionary/dictionary/itemempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemEmpty(@HostParam("$host") String host, Context context);

        @Get("/dictionary/dictionary/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Object>>> getDictionaryValid(@HostParam("$host") String host, Context context);

        @Put("/dictionary/dictionary/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDictionaryValid(@HostParam("$host") String host, @BodyParam("application/json") Map<String, Object> arrayBody, Context context);
    }

    /**
     * Get null dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), context));
    }

    /**
     * Get null dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get null dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getNull() {
        return getNullAsync().block();
    }

    /**
     * Get empty dictionary value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getEmpty(this.client.getHost(), context));
    }

    /**
     * Get empty dictionary value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get empty dictionary value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getEmpty() {
        return getEmptyAsync().block();
    }

    /**
     * Set dictionary value empty {}.
     * 
     * @param arrayBody Dictionary of &lt;string&gt;.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(Map<String, String> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putEmpty(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value empty {}.
     * 
     * @param arrayBody Dictionary of &lt;string&gt;.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(Map<String, String> arrayBody) {
        return putEmptyWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value empty {}.
     * 
     * @param arrayBody Dictionary of &lt;string&gt;.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(Map<String, String> arrayBody) {
        putEmptyAsync(arrayBody).block();
    }

    /**
     * Get Dictionary with null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getNullValueWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNullValue(this.client.getHost(), context));
    }

    /**
     * Get Dictionary with null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get Dictionary with null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getNullValue() {
        return getNullValueAsync().block();
    }

    /**
     * Get Dictionary with null key.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getNullKeyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNullKey(this.client.getHost(), context));
    }

    /**
     * Get Dictionary with null key.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get Dictionary with null key.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getNullKey() {
        return getNullKeyAsync().block();
    }

    /**
     * Get Dictionary with key as empty string.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getEmptyStringKeyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getEmptyStringKey(this.client.getHost(), context));
    }

    /**
     * Get Dictionary with key as empty string.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get Dictionary with key as empty string.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getEmptyStringKey() {
        return getEmptyStringKeyAsync().block();
    }

    /**
     * Get invalid Dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getInvalid(this.client.getHost(), context));
    }

    /**
     * Get invalid Dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get invalid Dictionary value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getInvalid() {
        return getInvalidAsync().block();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanTfftWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getBooleanTfft(this.client.getHost(), context));
    }

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get boolean dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Boolean> getBooleanTfft() {
        return getBooleanTfftAsync().block();
    }

    /**
     * Set dictionary value empty {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @param arrayBody The dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBooleanTfftWithResponseAsync(Map<String, Boolean> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putBooleanTfft(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value empty {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @param arrayBody The dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBooleanTfftAsync(Map<String, Boolean> arrayBody) {
        return putBooleanTfftWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value empty {"0": true, "1": false, "2": false, "3": true }.
     * 
     * @param arrayBody The dictionary value {"0": true, "1": false, "2": false, "3": true }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBooleanTfft(Map<String, Boolean> arrayBody) {
        putBooleanTfftAsync(arrayBody).block();
    }

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getBooleanInvalidNull(this.client.getHost(), context));
    }

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get boolean dictionary value {"0": true, "1": null, "2": false }.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Boolean> getBooleanInvalidNull() {
        return getBooleanInvalidNullAsync().block();
    }

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Boolean>>> getBooleanInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getBooleanInvalidString(this.client.getHost(), context));
    }

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get boolean dictionary value '{"0": true, "1": "boolean", "2": false}'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Boolean> getBooleanInvalidString() {
        return getBooleanInvalidStringAsync().block();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntegerValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getIntegerValid(this.client.getHost(), context));
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getIntegerValid() {
        return getIntegerValidAsync().block();
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The null dictionary value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putIntegerValidWithResponseAsync(Map<String, Integer> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putIntegerValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The null dictionary value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putIntegerValidAsync(Map<String, Integer> arrayBody) {
        return putIntegerValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The null dictionary value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putIntegerValid(Map<String, Integer> arrayBody) {
        putIntegerValidAsync(arrayBody).block();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getIntInvalidNull(this.client.getHost(), context));
    }

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get integer dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getIntInvalidNull() {
        return getIntInvalidNullAsync().block();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getIntInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getIntInvalidString(this.client.getHost(), context));
    }

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get integer dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getIntInvalidString() {
        return getIntInvalidStringAsync().block();
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLongValid(this.client.getHost(), context));
    }

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get integer dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Long> getLongValid() {
        return getLongValidAsync().block();
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLongValidWithResponseAsync(Map<String, Long> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putLongValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLongValidAsync(Map<String, Long> arrayBody) {
        return putLongValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value empty {"0": 1, "1": -1, "2": 3, "3": 300}.
     * 
     * @param arrayBody The dictionary value {"0": 1, "1": -1, "2": 3, "3": 300}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLongValid(Map<String, Long> arrayBody) {
        putLongValidAsync(arrayBody).block();
    }

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLongInvalidNull(this.client.getHost(), context));
    }

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get long dictionary value {"0": 1, "1": null, "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Long> getLongInvalidNull() {
        return getLongInvalidNullAsync().block();
    }

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Long>>> getLongInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLongInvalidString(this.client.getHost(), context));
    }

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get long dictionary value {"0": 1, "1": "integer", "2": 0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Long> getLongInvalidString() {
        return getLongInvalidStringAsync().block();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getFloatValid(this.client.getHost(), context));
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Float> getFloatValid() {
        return getFloatValidAsync().block();
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFloatValidWithResponseAsync(Map<String, Float> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putFloatValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFloatValidAsync(Map<String, Float> arrayBody) {
        return putFloatValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFloatValid(Map<String, Float> arrayBody) {
        putFloatValidAsync(arrayBody).block();
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getFloatInvalidNull(this.client.getHost(), context));
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Float> getFloatInvalidNull() {
        return getFloatInvalidNullAsync().block();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Float>>> getFloatInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getFloatInvalidString(this.client.getHost(), context));
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Float> getFloatInvalidString() {
        return getFloatInvalidStringAsync().block();
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDoubleValid(this.client.getHost(), context));
    }

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get float dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Double> getDoubleValid() {
        return getDoubleValidAsync().block();
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDoubleValidWithResponseAsync(Map<String, Double> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putDoubleValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDoubleValidAsync(Map<String, Double> arrayBody) {
        return putDoubleValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * 
     * @param arrayBody The dictionary value {"0": 0, "1": -0.01, "2": 1.2e20}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDoubleValid(Map<String, Double> arrayBody) {
        putDoubleValidAsync(arrayBody).block();
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDoubleInvalidNull(this.client.getHost(), context));
    }

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get float dictionary value {"0": 0.0, "1": null, "2": 1.2e20}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Double> getDoubleInvalidNull() {
        return getDoubleInvalidNullAsync().block();
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Double>>> getDoubleInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDoubleInvalidString(this.client.getHost(), context));
    }

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get boolean dictionary value {"0": 1.0, "1": "number", "2": 0.0}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Double> getDoubleInvalidString() {
        return getDoubleInvalidStringAsync().block();
    }

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getStringValid(this.client.getHost(), context));
    }

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get string dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getStringValid() {
        return getStringValidAsync().block();
    }

    /**
     * Set dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @param arrayBody Dictionary of &lt;string&gt;.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringValidWithResponseAsync(Map<String, String> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putStringValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @param arrayBody Dictionary of &lt;string&gt;.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putStringValidAsync(Map<String, String> arrayBody) {
        return putStringValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value {"0": "foo1", "1": "foo2", "2": "foo3"}.
     * 
     * @param arrayBody Dictionary of &lt;string&gt;.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putStringValid(Map<String, String> arrayBody) {
        putStringValidAsync(arrayBody).block();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringWithNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getStringWithNull(this.client.getHost(), context));
    }

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get string dictionary value {"0": "foo", "1": null, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getStringWithNull() {
        return getStringWithNullAsync().block();
    }

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, String>>> getStringWithInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getStringWithInvalid(this.client.getHost(), context));
    }

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get string dictionary value {"0": "foo", "1": 123, "2": "foo2"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, String> getStringWithInvalid() {
        return getStringWithInvalidAsync().block();
    }

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDateValid(this.client.getHost(), context));
    }

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get integer dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, LocalDate> getDateValid() {
        return getDateValidAsync().block();
    }

    /**
     * Set dictionary value  {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateValidWithResponseAsync(Map<String, LocalDate> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putDateValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value  {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateValidAsync(Map<String, LocalDate> arrayBody) {
        return putDateValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value  {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01", "1": "1980-01-02", "2": "1492-10-12"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateValid(Map<String, LocalDate> arrayBody) {
        putDateValidAsync(arrayBody).block();
    }

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDateInvalidNull(this.client.getHost(), context));
    }

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get date dictionary value {"0": "2012-01-01", "1": null, "2": "1776-07-04"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, LocalDate> getDateInvalidNull() {
        return getDateInvalidNullAsync().block();
    }

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, LocalDate>>> getDateInvalidCharsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDateInvalidChars(this.client.getHost(), context));
    }

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get date dictionary value {"0": "2011-03-22", "1": "date"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, LocalDate> getDateInvalidChars() {
        return getDateInvalidCharsAsync().block();
    }

    /**
     * Get date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDateTimeValid(this.client.getHost(), context));
    }

    /**
     * Get date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get date-time dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeValid() {
        return getDateTimeValidAsync().block();
    }

    /**
     * Set dictionary value  {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeValidWithResponseAsync(Map<String, OffsetDateTime> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putDateTimeValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value  {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeValidAsync(Map<String, OffsetDateTime> arrayBody) {
        return putDateTimeValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value  {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * 
     * @param arrayBody The dictionary value {"0": "2000-12-01t00:00:01z", "1": "1980-01-02T00:11:35+01:00", "2": "1492-10-12T10:15:01-08:00"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeValid(Map<String, OffsetDateTime> arrayBody) {
        putDateTimeValidAsync(arrayBody).block();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDateTimeInvalidNull(this.client.getHost(), context));
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": null}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeInvalidNull() {
        return getDateTimeInvalidNullAsync().block();
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeInvalidCharsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDateTimeInvalidChars(this.client.getHost(), context));
    }

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get date dictionary value {"0": "2000-12-01t00:00:01z", "1": "date-time"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeInvalidChars() {
        return getDateTimeInvalidCharsAsync().block();
    }

    /**
     * Get date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, OffsetDateTime>>> getDateTimeRfc1123ValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDateTimeRfc1123Valid(this.client.getHost(), context));
    }

    /**
     * Get date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get date-time-rfc1123 dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, OffsetDateTime> getDateTimeRfc1123Valid() {
        return getDateTimeRfc1123ValidAsync().block();
    }

    /**
     * Set dictionary value empty {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @param arrayBody The dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeRfc1123ValidWithResponseAsync(Map<String, OffsetDateTime> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        Map<String, DateTimeRfc1123> arrayBodyConverted = arrayBody.entrySet().stream().collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, el -> new DateTimeRfc1123(el.getValue())));
        return FluxUtil.withContext(context -> service.putDateTimeRfc1123Valid(this.client.getHost(), arrayBodyConverted, context));
    }

    /**
     * Set dictionary value empty {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @param arrayBody The dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeRfc1123ValidAsync(Map<String, OffsetDateTime> arrayBody) {
        return putDateTimeRfc1123ValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value empty {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * 
     * @param arrayBody The dictionary value {"0": "Fri, 01 Dec 2000 00:00:01 GMT", "1": "Wed, 02 Jan 1980 00:11:35 GMT", "2": "Wed, 12 Oct 1492 10:15:01 GMT"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeRfc1123Valid(Map<String, OffsetDateTime> arrayBody) {
        putDateTimeRfc1123ValidAsync(arrayBody).block();
    }

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Duration>>> getDurationValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDurationValid(this.client.getHost(), context));
    }

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get duration dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Duration> getDurationValid() {
        return getDurationValidAsync().block();
    }

    /**
     * Set dictionary value  {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDurationValidWithResponseAsync(Map<String, Duration> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putDurationValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Set dictionary value  {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDurationValidAsync(Map<String, Duration> arrayBody) {
        return putDurationValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set dictionary value  {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * 
     * @param arrayBody The dictionary value {"0": "P123DT22H14M12.011S", "1": "P5DT1H0M0S"}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDurationValid(Map<String, Duration> arrayBody) {
        putDurationValidAsync(arrayBody).block();
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item encoded in base64.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getByteValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getByteValid(this.client.getHost(), context));
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item encoded in base64.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each item encoded in base64.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, byte[]> getByteValid() {
        return getByteValidAsync().block();
    }

    /**
     * Put the dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * 
     * @param arrayBody The dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putByteValidWithResponseAsync(Map<String, byte[]> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putByteValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Put the dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * 
     * @param arrayBody The dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putByteValidAsync(Map<String, byte[]> arrayBody) {
        return putByteValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put the dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * 
     * @param arrayBody The dictionary value {"0": hex(FF FF FF FA), "1": hex(01 02 03), "2": hex (25, 29, 43)} with each elementencoded in base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putByteValid(Map<String, byte[]> arrayBody) {
        putByteValidAsync(arrayBody).block();
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getByteInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getByteInvalidNull(this.client.getHost(), context));
    }

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get byte dictionary value {"0": hex(FF FF FF FA), "1": null} with the first item base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, byte[]> getByteInvalidNull() {
        return getByteInvalidNullAsync().block();
    }

    /**
     * Get base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2": "Lorem ipsum"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, byte[]>>> getBase64UrlWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getBase64Url(this.client.getHost(), context));
    }

    /**
     * Get base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2": "Lorem ipsum"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get base64url dictionary value {"0": "a string that gets encoded with base64url", "1": "test string", "2": "Lorem ipsum"}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, byte[]> getBase64Url() {
        return getBase64UrlAsync().block();
    }

    /**
     * Get dictionary of complex type null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getComplexNull(this.client.getHost(), context));
    }

    /**
     * Get dictionary of complex type null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get dictionary of complex type null value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexNull() {
        return getComplexNullAsync().block();
    }

    /**
     * Get empty dictionary of complex type {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getComplexEmpty(this.client.getHost(), context));
    }

    /**
     * Get empty dictionary of complex type {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get empty dictionary of complex type {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexEmpty() {
        return getComplexEmptyAsync().block();
    }

    /**
     * Get dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexItemNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getComplexItemNull(this.client.getHost(), context));
    }

    /**
     * Get dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get dictionary of complex type with null item {"0": {"integer": 1, "string": "2"}, "1": null, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexItemNull() {
        return getComplexItemNullAsync().block();
    }

    /**
     * Get dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexItemEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getComplexItemEmpty(this.client.getHost(), context));
    }

    /**
     * Get dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get dictionary of complex type with empty item {"0": {"integer": 1, "string": "2"}, "1:" {}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexItemEmpty() {
        return getComplexItemEmptyAsync().block();
    }

    /**
     * Get dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Widget>>> getComplexValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getComplexValid(this.client.getHost(), context));
    }

    /**
     * Get dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get dictionary of complex type with {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Widget> getComplexValid() {
        return getComplexValidAsync().block();
    }

    /**
     * Put an dictionary of complex type with values {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param arrayBody Dictionary of complex type with null value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplexValidWithResponseAsync(Map<String, Widget> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        } else {
            arrayBody.values().forEach(e -> e.validate());
        }
        return FluxUtil.withContext(context -> service.putComplexValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Put an dictionary of complex type with values {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param arrayBody Dictionary of complex type with null value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplexValidAsync(Map<String, Widget> arrayBody) {
        return putComplexValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put an dictionary of complex type with values {"0": {"integer": 1, "string": "2"}, "1": {"integer": 3, "string": "4"}, "2": {"integer": 5, "string": "6"}}.
     * 
     * @param arrayBody Dictionary of complex type with null value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putComplexValid(Map<String, Widget> arrayBody) {
        putComplexValidAsync(arrayBody).block();
    }

    /**
     * Get a null array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getArrayNull(this.client.getHost(), context));
    }

    /**
     * Get a null array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get a null array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayNull() {
        return getArrayNullAsync().block();
    }

    /**
     * Get an empty dictionary {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getArrayEmpty(this.client.getHost(), context));
    }

    /**
     * Get an empty dictionary {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get an empty dictionary {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayEmpty() {
        return getArrayEmptyAsync().block();
    }

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getArrayItemNull(this.client.getHost(), context));
    }

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get an dictionary of array of strings {"0": ["1", "2", "3"], "1": null, "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayItemNull() {
        return getArrayItemNullAsync().block();
    }

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayItemEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getArrayItemEmpty(this.client.getHost(), context));
    }

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get an array of array of strings [{"0": ["1", "2", "3"], "1": [], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayItemEmpty() {
        return getArrayItemEmptyAsync().block();
    }

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, List<String>>>> getArrayValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getArrayValid(this.client.getHost(), context));
    }

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get an array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, List<String>> getArrayValid() {
        return getArrayValidAsync().block();
    }

    /**
     * Put An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @param arrayBody An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putArrayValidWithResponseAsync(Map<String, List<String>> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putArrayValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Put An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @param arrayBody An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putArrayValidAsync(Map<String, List<String>> arrayBody) {
        return putArrayValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * 
     * @param arrayBody An array of array of strings {"0": ["1", "2", "3"], "1": ["4", "5", "6"], "2": ["7", "8", "9"]}.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putArrayValid(Map<String, List<String>> arrayBody) {
        putArrayValidAsync(arrayBody).block();
    }

    /**
     * Get an dictionaries of dictionaries with value null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDictionaryNull(this.client.getHost(), context));
    }

    /**
     * Get an dictionaries of dictionaries with value null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get an dictionaries of dictionaries with value null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryNull() {
        return getDictionaryNullAsync().block();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDictionaryEmpty(this.client.getHost(), context));
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryEmpty() {
        return getDictionaryEmptyAsync().block();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDictionaryItemNull(this.client.getHost(), context));
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": null, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryItemNull() {
        return getDictionaryItemNullAsync().block();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryItemEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDictionaryItemEmpty(this.client.getHost(), context));
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryItemEmpty() {
        return getDictionaryItemEmptyAsync().block();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Object>>> getDictionaryValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getDictionaryValid(this.client.getHost(), context));
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Object> getDictionaryValid() {
        return getDictionaryValidAsync().block();
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param arrayBody An dictionaries of dictionaries with value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDictionaryValidWithResponseAsync(Map<String, Object> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putDictionaryValid(this.client.getHost(), arrayBody, context));
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param arrayBody An dictionaries of dictionaries with value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDictionaryValidAsync(Map<String, Object> arrayBody) {
        return putDictionaryValidWithResponseAsync(arrayBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an dictionaries of dictionaries of type &lt;string, string&gt; with value {"0": {"1": "one", "2": "two", "3": "three"}, "1": {"4": "four", "5": "five", "6": "six"}, "2": {"7": "seven", "8": "eight", "9": "nine"}}.
     * 
     * @param arrayBody An dictionaries of dictionaries with value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDictionaryValid(Map<String, Object> arrayBody) {
        putDictionaryValidAsync(arrayBody).block();
    }
}
