package fixtures.bodyarray;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
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
import com.azure.core.util.Base64Url;
import com.azure.core.util.Context;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
import fixtures.bodyarray.models.Enum0;
import fixtures.bodyarray.models.Enum1;
import fixtures.bodyarray.models.ErrorException;
import fixtures.bodyarray.models.FooEnum;
import fixtures.bodyarray.models.Product;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Arrays. */
public final class Arrays {
    /** The proxy service used to perform REST calls. */
    private final ArraysService service;

    /** The service client containing this operation class. */
    private final AutoRestSwaggerBATArrayService client;

    /**
     * Initializes an instance of Arrays.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Arrays(AutoRestSwaggerBATArrayService client) {
        this.service = RestProxy.create(ArraysService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestSwaggerBATArrayServiceArrays to be used by the proxy service
     * to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATAr")
    private interface ArraysService {
        @Get("/array/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Integer>>> getNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Integer>>> getInvalid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Integer>>> getEmpty(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<String> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/boolean/tfft")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Boolean>>> getBooleanTfft(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/boolean/tfft")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBooleanTfft(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Boolean> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/boolean/true.null.false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Boolean>>> getBooleanInvalidNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/boolean/true.boolean.false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Boolean>>> getBooleanInvalidString(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/integer/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Integer>>> getIntegerValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/integer/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putIntegerValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Integer> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/integer/1.null.zero")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Integer>>> getIntInvalidNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/integer/1.integer.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Integer>>> getIntInvalidString(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/long/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Long>>> getLongValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/long/1.-1.3.300")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLongValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Long> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/long/1.null.zero")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Long>>> getLongInvalidNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/long/1.integer.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Long>>> getLongInvalidString(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/float/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Float>>> getFloatValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/float/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFloatValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Float> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/float/0.0-null-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Float>>> getFloatInvalidNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/float/1.number.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Float>>> getFloatInvalidString(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/double/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Double>>> getDoubleValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/double/0--0.01-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDoubleValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Double> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/double/0.0-null-1.2e20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Double>>> getDoubleInvalidNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/double/1.number.0")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Double>>> getDoubleInvalidString(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/string/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<String>>> getStringValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/string/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putStringValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<String> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/enum/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<FooEnum>>> getEnumValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/enum/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEnumValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<FooEnum> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/string-enum/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Enum0>>> getStringEnumValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/string-enum/foo1.foo2.foo3")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putStringEnumValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Enum1> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/string/foo.null.foo2")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<String>>> getStringWithNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/string/foo.123.foo2")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<String>>> getStringWithInvalid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/uuid/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<UUID>>> getUuidValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/uuid/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUuidValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<UUID> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/uuid/invalidchars")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<UUID>>> getUuidInvalidChars(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/date/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<LocalDate>>> getDateValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/date/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<LocalDate> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/date/invalidnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<LocalDate>>> getDateInvalidNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/date/invalidchars")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<LocalDate>>> getDateInvalidChars(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/date-time/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<OffsetDateTime>>> getDateTimeValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/date-time/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<OffsetDateTime> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/date-time/invalidnull")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<OffsetDateTime>>> getDateTimeInvalidNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/date-time/invalidchars")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<OffsetDateTime>>> getDateTimeInvalidChars(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/date-time-rfc1123/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<OffsetDateTime>>> getDateTimeRfc1123Valid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/date-time-rfc1123/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDateTimeRfc1123Valid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<DateTimeRfc1123> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/duration/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Duration>>> getDurationValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/duration/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDurationValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Duration> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/byte/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<byte[]>>> getByteValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/prim/byte/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putByteValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<byte[]> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/prim/byte/invalidnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<byte[]>>> getByteInvalidNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/prim/base64url/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(Base64Url.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<byte[]>>> getBase64Url(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/complex/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Product>>> getComplexNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/complex/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Product>>> getComplexEmpty(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/complex/itemnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Product>>> getComplexItemNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/complex/itemempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Product>>> getComplexItemEmpty(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/complex/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Product>>> getComplexValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/complex/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putComplexValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Product> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/array/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<List<String>>>> getArrayNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/array/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<List<String>>>> getArrayEmpty(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/array/itemnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<List<String>>>> getArrayItemNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/array/itemempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<List<String>>>> getArrayItemEmpty(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<List<String>>>> getArrayValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putArrayValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<List<String>> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/array/dictionary/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Map<String, String>>>> getDictionaryNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/dictionary/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Map<String, String>>>> getDictionaryEmpty(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/dictionary/itemnull")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Map<String, String>>>> getDictionaryItemNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/dictionary/itemempty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Map<String, String>>>> getDictionaryItemEmpty(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/array/dictionary/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<Map<String, String>>>> getDictionaryValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/array/dictionary/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDictionaryValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Map<String, String>> arrayBody,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Get null array value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null array value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Integer>>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), accept, context));
    }

    /**
     * Get null array value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null array value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Integer>> getNullAsync() {
        return getNullWithResponseAsync()
                .flatMap(
                        (Response<List<Integer>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get null array value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null array value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Integer> getNull() {
        return getNullAsync().block();
    }

    /**
     * Get invalid array [1, 2, 3.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid array [1, 2, 3.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Integer>>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getInvalid(this.client.getHost(), accept, context));
    }

    /**
     * Get invalid array [1, 2, 3.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid array [1, 2, 3.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Integer>> getInvalidAsync() {
        return getInvalidWithResponseAsync()
                .flatMap(
                        (Response<List<Integer>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get invalid array [1, 2, 3.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid array [1, 2, 3.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Integer> getInvalid() {
        return getInvalidAsync().block();
    }

    /**
     * Get empty array value [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty array value [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Integer>>> getEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getEmpty(this.client.getHost(), accept, context));
    }

    /**
     * Get empty array value [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty array value [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Integer>> getEmptyAsync() {
        return getEmptyWithResponseAsync()
                .flatMap(
                        (Response<List<Integer>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get empty array value [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty array value [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Integer> getEmpty() {
        return getEmptyAsync().block();
    }

    /**
     * Set array value empty [].
     *
     * @param arrayBody The empty array value [].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(List<String> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putEmpty(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value empty [].
     *
     * @param arrayBody The empty array value [].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(List<String> arrayBody) {
        return putEmptyWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value empty [].
     *
     * @param arrayBody The empty array value [].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(List<String> arrayBody) {
        putEmptyAsync(arrayBody).block();
    }

    /**
     * Get boolean array value [true, false, false, true].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, false, false, true].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Boolean>>> getBooleanTfftWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getBooleanTfft(this.client.getHost(), accept, context));
    }

    /**
     * Get boolean array value [true, false, false, true].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, false, false, true].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Boolean>> getBooleanTfftAsync() {
        return getBooleanTfftWithResponseAsync()
                .flatMap(
                        (Response<List<Boolean>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get boolean array value [true, false, false, true].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, false, false, true].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Boolean> getBooleanTfft() {
        return getBooleanTfftAsync().block();
    }

    /**
     * Set array value empty [true, false, false, true].
     *
     * @param arrayBody The array value [true, false, false, true].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBooleanTfftWithResponseAsync(List<Boolean> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putBooleanTfft(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value empty [true, false, false, true].
     *
     * @param arrayBody The array value [true, false, false, true].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBooleanTfftAsync(List<Boolean> arrayBody) {
        return putBooleanTfftWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value empty [true, false, false, true].
     *
     * @param arrayBody The array value [true, false, false, true].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBooleanTfft(List<Boolean> arrayBody) {
        putBooleanTfftAsync(arrayBody).block();
    }

    /**
     * Get boolean array value [true, null, false].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, null, false].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Boolean>>> getBooleanInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getBooleanInvalidNull(this.client.getHost(), accept, context));
    }

    /**
     * Get boolean array value [true, null, false].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, null, false].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Boolean>> getBooleanInvalidNullAsync() {
        return getBooleanInvalidNullWithResponseAsync()
                .flatMap(
                        (Response<List<Boolean>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get boolean array value [true, null, false].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, null, false].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Boolean> getBooleanInvalidNull() {
        return getBooleanInvalidNullAsync().block();
    }

    /**
     * Get boolean array value [true, 'boolean', false].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, 'boolean', false].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Boolean>>> getBooleanInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getBooleanInvalidString(this.client.getHost(), accept, context));
    }

    /**
     * Get boolean array value [true, 'boolean', false].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, 'boolean', false].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Boolean>> getBooleanInvalidStringAsync() {
        return getBooleanInvalidStringWithResponseAsync()
                .flatMap(
                        (Response<List<Boolean>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get boolean array value [true, 'boolean', false].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [true, 'boolean', false].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Boolean> getBooleanInvalidString() {
        return getBooleanInvalidStringAsync().block();
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, -1, 3, 300].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Integer>>> getIntegerValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getIntegerValid(this.client.getHost(), accept, context));
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, -1, 3, 300].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Integer>> getIntegerValidAsync() {
        return getIntegerValidWithResponseAsync()
                .flatMap(
                        (Response<List<Integer>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, -1, 3, 300].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Integer> getIntegerValid() {
        return getIntegerValidAsync().block();
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     *
     * @param arrayBody The array value [1, -1, 3, 300].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putIntegerValidWithResponseAsync(List<Integer> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putIntegerValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     *
     * @param arrayBody The array value [1, -1, 3, 300].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putIntegerValidAsync(List<Integer> arrayBody) {
        return putIntegerValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     *
     * @param arrayBody The array value [1, -1, 3, 300].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putIntegerValid(List<Integer> arrayBody) {
        putIntegerValidAsync(arrayBody).block();
    }

    /**
     * Get integer array value [1, null, 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, null, 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Integer>>> getIntInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getIntInvalidNull(this.client.getHost(), accept, context));
    }

    /**
     * Get integer array value [1, null, 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, null, 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Integer>> getIntInvalidNullAsync() {
        return getIntInvalidNullWithResponseAsync()
                .flatMap(
                        (Response<List<Integer>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get integer array value [1, null, 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, null, 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Integer> getIntInvalidNull() {
        return getIntInvalidNullAsync().block();
    }

    /**
     * Get integer array value [1, 'integer', 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, 'integer', 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Integer>>> getIntInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getIntInvalidString(this.client.getHost(), accept, context));
    }

    /**
     * Get integer array value [1, 'integer', 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, 'integer', 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Integer>> getIntInvalidStringAsync() {
        return getIntInvalidStringWithResponseAsync()
                .flatMap(
                        (Response<List<Integer>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get integer array value [1, 'integer', 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, 'integer', 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Integer> getIntInvalidString() {
        return getIntInvalidStringAsync().block();
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, -1, 3, 300].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Long>>> getLongValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getLongValid(this.client.getHost(), accept, context));
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, -1, 3, 300].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Long>> getLongValidAsync() {
        return getLongValidWithResponseAsync()
                .flatMap(
                        (Response<List<Long>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get integer array value [1, -1, 3, 300].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value [1, -1, 3, 300].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Long> getLongValid() {
        return getLongValidAsync().block();
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     *
     * @param arrayBody The array value [1, -1, 3, 300].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLongValidWithResponseAsync(List<Long> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putLongValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     *
     * @param arrayBody The array value [1, -1, 3, 300].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLongValidAsync(List<Long> arrayBody) {
        return putLongValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value empty [1, -1, 3, 300].
     *
     * @param arrayBody The array value [1, -1, 3, 300].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLongValid(List<Long> arrayBody) {
        putLongValidAsync(arrayBody).block();
    }

    /**
     * Get long array value [1, null, 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long array value [1, null, 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Long>>> getLongInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getLongInvalidNull(this.client.getHost(), accept, context));
    }

    /**
     * Get long array value [1, null, 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long array value [1, null, 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Long>> getLongInvalidNullAsync() {
        return getLongInvalidNullWithResponseAsync()
                .flatMap(
                        (Response<List<Long>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get long array value [1, null, 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long array value [1, null, 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Long> getLongInvalidNull() {
        return getLongInvalidNullAsync().block();
    }

    /**
     * Get long array value [1, 'integer', 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long array value [1, 'integer', 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Long>>> getLongInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getLongInvalidString(this.client.getHost(), accept, context));
    }

    /**
     * Get long array value [1, 'integer', 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long array value [1, 'integer', 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Long>> getLongInvalidStringAsync() {
        return getLongInvalidStringWithResponseAsync()
                .flatMap(
                        (Response<List<Long>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get long array value [1, 'integer', 0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return long array value [1, 'integer', 0].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Long> getLongInvalidString() {
        return getLongInvalidStringAsync().block();
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0, -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Float>>> getFloatValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getFloatValid(this.client.getHost(), accept, context));
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0, -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Float>> getFloatValidAsync() {
        return getFloatValidWithResponseAsync()
                .flatMap(
                        (Response<List<Float>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0, -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Float> getFloatValid() {
        return getFloatValidAsync().block();
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     *
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFloatValidWithResponseAsync(List<Float> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putFloatValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     *
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFloatValidAsync(List<Float> arrayBody) {
        return putFloatValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     *
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFloatValid(List<Float> arrayBody) {
        putFloatValidAsync(arrayBody).block();
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Float>>> getFloatInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getFloatInvalidNull(this.client.getHost(), accept, context));
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Float>> getFloatInvalidNullAsync() {
        return getFloatInvalidNullWithResponseAsync()
                .flatMap(
                        (Response<List<Float>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Float> getFloatInvalidNull() {
        return getFloatInvalidNullAsync().block();
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Float>>> getFloatInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getFloatInvalidString(this.client.getHost(), accept, context));
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Float>> getFloatInvalidStringAsync() {
        return getFloatInvalidStringWithResponseAsync()
                .flatMap(
                        (Response<List<Float>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Float> getFloatInvalidString() {
        return getFloatInvalidStringAsync().block();
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0, -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Double>>> getDoubleValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDoubleValid(this.client.getHost(), accept, context));
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0, -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Double>> getDoubleValidAsync() {
        return getDoubleValidWithResponseAsync()
                .flatMap(
                        (Response<List<Double>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get float array value [0, -0.01, 1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0, -0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Double> getDoubleValid() {
        return getDoubleValidAsync().block();
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     *
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDoubleValidWithResponseAsync(List<Double> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putDoubleValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     *
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDoubleValidAsync(List<Double> arrayBody) {
        return putDoubleValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value [0, -0.01, 1.2e20].
     *
     * @param arrayBody The array value [0, -0.01, 1.2e20].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDoubleValid(List<Double> arrayBody) {
        putDoubleValidAsync(arrayBody).block();
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Double>>> getDoubleInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDoubleInvalidNull(this.client.getHost(), accept, context));
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Double>> getDoubleInvalidNullAsync() {
        return getDoubleInvalidNullWithResponseAsync()
                .flatMap(
                        (Response<List<Double>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get float array value [0.0, null, -1.2e20].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return float array value [0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Double> getDoubleInvalidNull() {
        return getDoubleInvalidNullAsync().block();
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Double>>> getDoubleInvalidStringWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDoubleInvalidString(this.client.getHost(), accept, context));
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Double>> getDoubleInvalidStringAsync() {
        return getDoubleInvalidStringWithResponseAsync()
                .flatMap(
                        (Response<List<Double>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get boolean array value [1.0, 'number', 0.0].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return boolean array value [1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Double> getDoubleInvalidString() {
        return getDoubleInvalidStringAsync().block();
    }

    /**
     * Get string array value ['foo1', 'foo2', 'foo3'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo1', 'foo2', 'foo3'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<String>>> getStringValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getStringValid(this.client.getHost(), accept, context));
    }

    /**
     * Get string array value ['foo1', 'foo2', 'foo3'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo1', 'foo2', 'foo3'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<String>> getStringValidAsync() {
        return getStringValidWithResponseAsync()
                .flatMap(
                        (Response<List<String>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get string array value ['foo1', 'foo2', 'foo3'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo1', 'foo2', 'foo3'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<String> getStringValid() {
        return getStringValidAsync().block();
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     *
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringValidWithResponseAsync(List<String> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putStringValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     *
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putStringValidAsync(List<String> arrayBody) {
        return putStringValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     *
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putStringValid(List<String> arrayBody) {
        putStringValidAsync(arrayBody).block();
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum array value ['foo1', 'foo2', 'foo3'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<FooEnum>>> getEnumValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getEnumValid(this.client.getHost(), accept, context));
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum array value ['foo1', 'foo2', 'foo3'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<FooEnum>> getEnumValidAsync() {
        return getEnumValidWithResponseAsync()
                .flatMap(
                        (Response<List<FooEnum>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum array value ['foo1', 'foo2', 'foo3'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<FooEnum> getEnumValid() {
        return getEnumValidAsync().block();
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     *
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEnumValidWithResponseAsync(List<FooEnum> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putEnumValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     *
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEnumValidAsync(List<FooEnum> arrayBody) {
        return putEnumValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     *
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEnumValid(List<FooEnum> arrayBody) {
        putEnumValidAsync(arrayBody).block();
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum array value ['foo1', 'foo2', 'foo3'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Enum0>>> getStringEnumValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getStringEnumValid(this.client.getHost(), accept, context));
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum array value ['foo1', 'foo2', 'foo3'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Enum0>> getStringEnumValidAsync() {
        return getStringEnumValidWithResponseAsync()
                .flatMap(
                        (Response<List<Enum0>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get enum array value ['foo1', 'foo2', 'foo3'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return enum array value ['foo1', 'foo2', 'foo3'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Enum0> getStringEnumValid() {
        return getStringEnumValidAsync().block();
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     *
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putStringEnumValidWithResponseAsync(List<Enum1> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putStringEnumValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     *
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putStringEnumValidAsync(List<Enum1> arrayBody) {
        return putStringEnumValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value ['foo1', 'foo2', 'foo3'].
     *
     * @param arrayBody The array value ['foo1', 'foo2', 'foo3'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putStringEnumValid(List<Enum1> arrayBody) {
        putStringEnumValidAsync(arrayBody).block();
    }

    /**
     * Get string array value ['foo', null, 'foo2'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo', null, 'foo2'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<String>>> getStringWithNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getStringWithNull(this.client.getHost(), accept, context));
    }

    /**
     * Get string array value ['foo', null, 'foo2'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo', null, 'foo2'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<String>> getStringWithNullAsync() {
        return getStringWithNullWithResponseAsync()
                .flatMap(
                        (Response<List<String>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get string array value ['foo', null, 'foo2'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo', null, 'foo2'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<String> getStringWithNull() {
        return getStringWithNullAsync().block();
    }

    /**
     * Get string array value ['foo', 123, 'foo2'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo', 123, 'foo2'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<String>>> getStringWithInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getStringWithInvalid(this.client.getHost(), accept, context));
    }

    /**
     * Get string array value ['foo', 123, 'foo2'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo', 123, 'foo2'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<String>> getStringWithInvalidAsync() {
        return getStringWithInvalidWithResponseAsync()
                .flatMap(
                        (Response<List<String>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get string array value ['foo', 123, 'foo2'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string array value ['foo', 123, 'foo2'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<String> getStringWithInvalid() {
        return getStringWithInvalidAsync().block();
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     * 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     *     'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<UUID>>> getUuidValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getUuidValid(this.client.getHost(), accept, context));
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     * 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     *     'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<UUID>> getUuidValidAsync() {
        return getUuidValidWithResponseAsync()
                .flatMap(
                        (Response<List<UUID>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     * 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     *     'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<UUID> getUuidValid() {
        return getUuidValidAsync().block();
    }

    /**
     * Set array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     * 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     *
     * @param arrayBody The array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     *     'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUuidValidWithResponseAsync(List<UUID> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putUuidValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     * 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     *
     * @param arrayBody The array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     *     'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUuidValidAsync(List<UUID> arrayBody) {
        return putUuidValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     * 'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     *
     * @param arrayBody The array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'd1399005-30f7-40d6-8da6-dd7c89ad34db',
     *     'f42f6aa1-a5bc-4ddf-907e-5f915de43205'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUuidValid(List<UUID> arrayBody) {
        putUuidValidAsync(arrayBody).block();
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'foo'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'foo'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<UUID>>> getUuidInvalidCharsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getUuidInvalidChars(this.client.getHost(), accept, context));
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'foo'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'foo'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<UUID>> getUuidInvalidCharsAsync() {
        return getUuidInvalidCharsWithResponseAsync()
                .flatMap(
                        (Response<List<UUID>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'foo'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return uuid array value ['6dcc7237-45fe-45c4-8a6b-3a8a3f625652', 'foo'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<UUID> getUuidInvalidChars() {
        return getUuidInvalidCharsAsync().block();
    }

    /**
     * Get integer array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<LocalDate>>> getDateValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDateValid(this.client.getHost(), accept, context));
    }

    /**
     * Get integer array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<LocalDate>> getDateValidAsync() {
        return getDateValidWithResponseAsync()
                .flatMap(
                        (Response<List<LocalDate>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get integer array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return integer array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<LocalDate> getDateValid() {
        return getDateValidAsync().block();
    }

    /**
     * Set array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     *
     * @param arrayBody The array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateValidWithResponseAsync(List<LocalDate> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putDateValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     *
     * @param arrayBody The array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateValidAsync(List<LocalDate> arrayBody) {
        return putDateValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     *
     * @param arrayBody The array value ['2000-12-01', '1980-01-02', '1492-10-12'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateValid(List<LocalDate> arrayBody) {
        putDateValidAsync(arrayBody).block();
    }

    /**
     * Get date array value ['2012-01-01', null, '1776-07-04'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2012-01-01', null, '1776-07-04'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<LocalDate>>> getDateInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDateInvalidNull(this.client.getHost(), accept, context));
    }

    /**
     * Get date array value ['2012-01-01', null, '1776-07-04'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2012-01-01', null, '1776-07-04'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<LocalDate>> getDateInvalidNullAsync() {
        return getDateInvalidNullWithResponseAsync()
                .flatMap(
                        (Response<List<LocalDate>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get date array value ['2012-01-01', null, '1776-07-04'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2012-01-01', null, '1776-07-04'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<LocalDate> getDateInvalidNull() {
        return getDateInvalidNullAsync().block();
    }

    /**
     * Get date array value ['2011-03-22', 'date'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2011-03-22', 'date'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<LocalDate>>> getDateInvalidCharsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDateInvalidChars(this.client.getHost(), accept, context));
    }

    /**
     * Get date array value ['2011-03-22', 'date'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2011-03-22', 'date'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<LocalDate>> getDateInvalidCharsAsync() {
        return getDateInvalidCharsWithResponseAsync()
                .flatMap(
                        (Response<List<LocalDate>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get date array value ['2011-03-22', 'date'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2011-03-22', 'date'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<LocalDate> getDateInvalidChars() {
        return getDateInvalidCharsAsync().block();
    }

    /**
     * Get date-time array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<OffsetDateTime>>> getDateTimeValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDateTimeValid(this.client.getHost(), accept, context));
    }

    /**
     * Get date-time array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<OffsetDateTime>> getDateTimeValidAsync() {
        return getDateTimeValidWithResponseAsync()
                .flatMap(
                        (Response<List<OffsetDateTime>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get date-time array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<OffsetDateTime> getDateTimeValid() {
        return getDateTimeValidAsync().block();
    }

    /**
     * Set array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     *
     * @param arrayBody The array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00',
     *     '1492-10-12T10:15:01-08:00'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeValidWithResponseAsync(List<OffsetDateTime> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putDateTimeValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     *
     * @param arrayBody The array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00',
     *     '1492-10-12T10:15:01-08:00'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeValidAsync(List<OffsetDateTime> arrayBody) {
        return putDateTimeValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00', '1492-10-12T10:15:01-08:00'].
     *
     * @param arrayBody The array value ['2000-12-01t00:00:01z', '1980-01-02T00:11:35+01:00',
     *     '1492-10-12T10:15:01-08:00'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeValid(List<OffsetDateTime> arrayBody) {
        putDateTimeValidAsync(arrayBody).block();
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', null].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2000-12-01t00:00:01z', null].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<OffsetDateTime>>> getDateTimeInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDateTimeInvalidNull(this.client.getHost(), accept, context));
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', null].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2000-12-01t00:00:01z', null].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<OffsetDateTime>> getDateTimeInvalidNullAsync() {
        return getDateTimeInvalidNullWithResponseAsync()
                .flatMap(
                        (Response<List<OffsetDateTime>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', null].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2000-12-01t00:00:01z', null].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<OffsetDateTime> getDateTimeInvalidNull() {
        return getDateTimeInvalidNullAsync().block();
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', 'date-time'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2000-12-01t00:00:01z', 'date-time'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<OffsetDateTime>>> getDateTimeInvalidCharsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDateTimeInvalidChars(this.client.getHost(), accept, context));
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', 'date-time'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2000-12-01t00:00:01z', 'date-time'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<OffsetDateTime>> getDateTimeInvalidCharsAsync() {
        return getDateTimeInvalidCharsWithResponseAsync()
                .flatMap(
                        (Response<List<OffsetDateTime>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get date array value ['2000-12-01t00:00:01z', 'date-time'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date array value ['2000-12-01t00:00:01z', 'date-time'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<OffsetDateTime> getDateTimeInvalidChars() {
        return getDateTimeInvalidCharsAsync().block();
    }

    /**
     * Get date-time array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492
     * 10:15:01 GMT'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct
     *     1492 10:15:01 GMT'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<OffsetDateTime>>> getDateTimeRfc1123ValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDateTimeRfc1123Valid(this.client.getHost(), accept, context));
    }

    /**
     * Get date-time array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492
     * 10:15:01 GMT'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct
     *     1492 10:15:01 GMT'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<OffsetDateTime>> getDateTimeRfc1123ValidAsync() {
        return getDateTimeRfc1123ValidWithResponseAsync()
                .flatMap(
                        (Response<List<OffsetDateTime>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get date-time array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492
     * 10:15:01 GMT'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return date-time array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct
     *     1492 10:15:01 GMT'].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<OffsetDateTime> getDateTimeRfc1123Valid() {
        return getDateTimeRfc1123ValidAsync().block();
    }

    /**
     * Set array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01
     * GMT'].
     *
     * @param arrayBody The array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct
     *     1492 10:15:01 GMT'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDateTimeRfc1123ValidWithResponseAsync(List<OffsetDateTime> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        List<DateTimeRfc1123> arrayBodyConverted =
                arrayBody.stream().map(el -> new DateTimeRfc1123(el)).collect(java.util.stream.Collectors.toList());
        return FluxUtil.withContext(
                context -> service.putDateTimeRfc1123Valid(this.client.getHost(), arrayBodyConverted, accept, context));
    }

    /**
     * Set array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01
     * GMT'].
     *
     * @param arrayBody The array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct
     *     1492 10:15:01 GMT'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDateTimeRfc1123ValidAsync(List<OffsetDateTime> arrayBody) {
        return putDateTimeRfc1123ValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct 1492 10:15:01
     * GMT'].
     *
     * @param arrayBody The array value ['Fri, 01 Dec 2000 00:00:01 GMT', 'Wed, 02 Jan 1980 00:11:35 GMT', 'Wed, 12 Oct
     *     1492 10:15:01 GMT'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDateTimeRfc1123Valid(List<OffsetDateTime> arrayBody) {
        putDateTimeRfc1123ValidAsync(arrayBody).block();
    }

    /**
     * Get duration array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return duration array value ['P123DT22H14M12.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Duration>>> getDurationValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDurationValid(this.client.getHost(), accept, context));
    }

    /**
     * Get duration array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return duration array value ['P123DT22H14M12.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Duration>> getDurationValidAsync() {
        return getDurationValidWithResponseAsync()
                .flatMap(
                        (Response<List<Duration>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get duration array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return duration array value ['P123DT22H14M12.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Duration> getDurationValid() {
        return getDurationValidAsync().block();
    }

    /**
     * Set array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     *
     * @param arrayBody The array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDurationValidWithResponseAsync(List<Duration> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putDurationValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Set array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     *
     * @param arrayBody The array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDurationValidAsync(List<Duration> arrayBody) {
        return putDurationValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     *
     * @param arrayBody The array value ['P123DT22H14M12.011S', 'P5DT1H0M0S'].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDurationValid(List<Duration> arrayBody) {
        putDurationValidAsync(arrayBody).block();
    }

    /**
     * Get byte array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each item encoded in base64.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each item encoded in base64.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<byte[]>>> getByteValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getByteValid(this.client.getHost(), accept, context));
    }

    /**
     * Get byte array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each item encoded in base64.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each item encoded in base64.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<byte[]>> getByteValidAsync() {
        return getByteValidWithResponseAsync()
                .flatMap(
                        (Response<List<byte[]>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get byte array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each item encoded in base64.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each item encoded in base64.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<byte[]> getByteValid() {
        return getByteValidAsync().block();
    }

    /**
     * Put the array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in base 64.
     *
     * @param arrayBody The array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in
     *     base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putByteValidWithResponseAsync(List<byte[]> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putByteValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Put the array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in base 64.
     *
     * @param arrayBody The array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in
     *     base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putByteValidAsync(List<byte[]> arrayBody) {
        return putByteValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put the array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in base 64.
     *
     * @param arrayBody The array value [hex(FF FF FF FA), hex(01 02 03), hex (25, 29, 43)] with each elementencoded in
     *     base 64.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putByteValid(List<byte[]> arrayBody) {
        putByteValidAsync(arrayBody).block();
    }

    /**
     * Get byte array value [hex(AB, AC, AD), null] with the first item base64 encoded.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte array value [hex(AB, AC, AD), null] with the first item base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<byte[]>>> getByteInvalidNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getByteInvalidNull(this.client.getHost(), accept, context));
    }

    /**
     * Get byte array value [hex(AB, AC, AD), null] with the first item base64 encoded.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte array value [hex(AB, AC, AD), null] with the first item base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<byte[]>> getByteInvalidNullAsync() {
        return getByteInvalidNullWithResponseAsync()
                .flatMap(
                        (Response<List<byte[]>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get byte array value [hex(AB, AC, AD), null] with the first item base64 encoded.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return byte array value [hex(AB, AC, AD), null] with the first item base64 encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<byte[]> getByteInvalidNull() {
        return getByteInvalidNullAsync().block();
    }

    /**
     * Get array value ['a string that gets encoded with base64url', 'test string' 'Lorem ipsum'] with the items
     * base64url encoded.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array value ['a string that gets encoded with base64url', 'test string' 'Lorem ipsum'] with the items
     *     base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<byte[]>>> getBase64UrlWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getBase64Url(this.client.getHost(), accept, context));
    }

    /**
     * Get array value ['a string that gets encoded with base64url', 'test string' 'Lorem ipsum'] with the items
     * base64url encoded.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array value ['a string that gets encoded with base64url', 'test string' 'Lorem ipsum'] with the items
     *     base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<byte[]>> getBase64UrlAsync() {
        return getBase64UrlWithResponseAsync()
                .flatMap(
                        (Response<List<byte[]>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get array value ['a string that gets encoded with base64url', 'test string' 'Lorem ipsum'] with the items
     * base64url encoded.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array value ['a string that gets encoded with base64url', 'test string' 'Lorem ipsum'] with the items
     *     base64url encoded.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<byte[]> getBase64Url() {
        return getBase64UrlAsync().block();
    }

    /**
     * Get array of complex type null value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type null value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Product>>> getComplexNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getComplexNull(this.client.getHost(), accept, context));
    }

    /**
     * Get array of complex type null value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type null value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Product>> getComplexNullAsync() {
        return getComplexNullWithResponseAsync()
                .flatMap(
                        (Response<List<Product>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get array of complex type null value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type null value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Product> getComplexNull() {
        return getComplexNullAsync().block();
    }

    /**
     * Get empty array of complex type [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty array of complex type [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Product>>> getComplexEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getComplexEmpty(this.client.getHost(), accept, context));
    }

    /**
     * Get empty array of complex type [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty array of complex type [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Product>> getComplexEmptyAsync() {
        return getComplexEmptyWithResponseAsync()
                .flatMap(
                        (Response<List<Product>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get empty array of complex type [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty array of complex type [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Product> getComplexEmpty() {
        return getComplexEmptyAsync().block();
    }

    /**
     * Get array of complex type with null item [{'integer': 1 'string': '2'}, null, {'integer': 5, 'string': '6'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with null item [{'integer': 1 'string': '2'}, null, {'integer': 5, 'string': '6'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Product>>> getComplexItemNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getComplexItemNull(this.client.getHost(), accept, context));
    }

    /**
     * Get array of complex type with null item [{'integer': 1 'string': '2'}, null, {'integer': 5, 'string': '6'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with null item [{'integer': 1 'string': '2'}, null, {'integer': 5, 'string': '6'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Product>> getComplexItemNullAsync() {
        return getComplexItemNullWithResponseAsync()
                .flatMap(
                        (Response<List<Product>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get array of complex type with null item [{'integer': 1 'string': '2'}, null, {'integer': 5, 'string': '6'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with null item [{'integer': 1 'string': '2'}, null, {'integer': 5, 'string': '6'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Product> getComplexItemNull() {
        return getComplexItemNullAsync().block();
    }

    /**
     * Get array of complex type with empty item [{'integer': 1 'string': '2'}, {}, {'integer': 5, 'string': '6'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with empty item [{'integer': 1 'string': '2'}, {}, {'integer': 5, 'string': '6'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Product>>> getComplexItemEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getComplexItemEmpty(this.client.getHost(), accept, context));
    }

    /**
     * Get array of complex type with empty item [{'integer': 1 'string': '2'}, {}, {'integer': 5, 'string': '6'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with empty item [{'integer': 1 'string': '2'}, {}, {'integer': 5, 'string': '6'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Product>> getComplexItemEmptyAsync() {
        return getComplexItemEmptyWithResponseAsync()
                .flatMap(
                        (Response<List<Product>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get array of complex type with empty item [{'integer': 1 'string': '2'}, {}, {'integer': 5, 'string': '6'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with empty item [{'integer': 1 'string': '2'}, {}, {'integer': 5, 'string': '6'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Product> getComplexItemEmpty() {
        return getComplexItemEmptyAsync().block();
    }

    /**
     * Get array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5,
     * 'string': '6'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5,
     *     'string': '6'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Product>>> getComplexValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getComplexValid(this.client.getHost(), accept, context));
    }

    /**
     * Get array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5,
     * 'string': '6'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5,
     *     'string': '6'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Product>> getComplexValidAsync() {
        return getComplexValidWithResponseAsync()
                .flatMap(
                        (Response<List<Product>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5,
     * 'string': '6'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'}, {'integer': 5,
     *     'string': '6'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Product> getComplexValid() {
        return getComplexValidAsync().block();
    }

    /**
     * Put an array of complex type with values [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'},
     * {'integer': 5, 'string': '6'}].
     *
     * @param arrayBody array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'},
     *     {'integer': 5, 'string': '6'}].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplexValidWithResponseAsync(List<Product> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        } else {
            arrayBody.forEach(e -> e.validate());
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putComplexValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Put an array of complex type with values [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'},
     * {'integer': 5, 'string': '6'}].
     *
     * @param arrayBody array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'},
     *     {'integer': 5, 'string': '6'}].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplexValidAsync(List<Product> arrayBody) {
        return putComplexValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put an array of complex type with values [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'},
     * {'integer': 5, 'string': '6'}].
     *
     * @param arrayBody array of complex type with [{'integer': 1 'string': '2'}, {'integer': 3, 'string': '4'},
     *     {'integer': 5, 'string': '6'}].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putComplexValid(List<Product> arrayBody) {
        putComplexValidAsync(arrayBody).block();
    }

    /**
     * Get a null array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<List<String>>>> getArrayNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getArrayNull(this.client.getHost(), accept, context));
    }

    /**
     * Get a null array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a null array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<List<String>>> getArrayNullAsync() {
        return getArrayNullWithResponseAsync()
                .flatMap(
                        (Response<List<List<String>>> res) -> {
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
     * @return a null array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<List<String>> getArrayNull() {
        return getArrayNullAsync().block();
    }

    /**
     * Get an empty array [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty array [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<List<String>>>> getArrayEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getArrayEmpty(this.client.getHost(), accept, context));
    }

    /**
     * Get an empty array [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty array [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<List<String>>> getArrayEmptyAsync() {
        return getArrayEmptyWithResponseAsync()
                .flatMap(
                        (Response<List<List<String>>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an empty array [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty array [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<List<String>> getArrayEmpty() {
        return getArrayEmptyAsync().block();
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], null, ['7', '8', '9']].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], null, ['7', '8', '9']].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<List<String>>>> getArrayItemNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getArrayItemNull(this.client.getHost(), accept, context));
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], null, ['7', '8', '9']].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], null, ['7', '8', '9']].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<List<String>>> getArrayItemNullAsync() {
        return getArrayItemNullWithResponseAsync()
                .flatMap(
                        (Response<List<List<String>>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], null, ['7', '8', '9']].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], null, ['7', '8', '9']].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<List<String>> getArrayItemNull() {
        return getArrayItemNullAsync().block();
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], [], ['7', '8', '9']].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], [], ['7', '8', '9']].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<List<String>>>> getArrayItemEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getArrayItemEmpty(this.client.getHost(), accept, context));
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], [], ['7', '8', '9']].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], [], ['7', '8', '9']].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<List<String>>> getArrayItemEmptyAsync() {
        return getArrayItemEmptyWithResponseAsync()
                .flatMap(
                        (Response<List<List<String>>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], [], ['7', '8', '9']].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], [], ['7', '8', '9']].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<List<String>> getArrayItemEmpty() {
        return getArrayItemEmptyAsync().block();
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<List<String>>>> getArrayValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getArrayValid(this.client.getHost(), accept, context));
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<List<String>>> getArrayValidAsync() {
        return getArrayValidWithResponseAsync()
                .flatMap(
                        (Response<List<List<String>>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<List<String>> getArrayValid() {
        return getArrayValidAsync().block();
    }

    /**
     * Put An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     *
     * @param arrayBody An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putArrayValidWithResponseAsync(List<List<String>> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putArrayValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Put An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     *
     * @param arrayBody An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putArrayValidAsync(List<List<String>> arrayBody) {
        return putArrayValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     *
     * @param arrayBody An array of array of strings [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9']].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putArrayValid(List<List<String>> arrayBody) {
        putArrayValidAsync(arrayBody).block();
    }

    /**
     * Get an array of Dictionaries with value null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries with value null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Map<String, String>>>> getDictionaryNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDictionaryNull(this.client.getHost(), accept, context));
    }

    /**
     * Get an array of Dictionaries with value null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries with value null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Map<String, String>>> getDictionaryNullAsync() {
        return getDictionaryNullWithResponseAsync()
                .flatMap(
                        (Response<List<Map<String, String>>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an array of Dictionaries with value null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries with value null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Map<String, String>> getDictionaryNull() {
        return getDictionaryNullAsync().block();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Map<String, String>>>> getDictionaryEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDictionaryEmpty(this.client.getHost(), accept, context));
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Map<String, String>>> getDictionaryEmptyAsync() {
        return getDictionaryEmptyWithResponseAsync()
                .flatMap(
                        (Response<List<Map<String, String>>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Map<String, String>> getDictionaryEmpty() {
        return getDictionaryEmptyAsync().block();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * null, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, null, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Map<String, String>>>> getDictionaryItemNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDictionaryItemNull(this.client.getHost(), accept, context));
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * null, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, null, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Map<String, String>>> getDictionaryItemNullAsync() {
        return getDictionaryItemNullWithResponseAsync()
                .flatMap(
                        (Response<List<Map<String, String>>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * null, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, null, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Map<String, String>> getDictionaryItemNull() {
        return getDictionaryItemNullAsync().block();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * {}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, {}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Map<String, String>>>> getDictionaryItemEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDictionaryItemEmpty(this.client.getHost(), accept, context));
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * {}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, {}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Map<String, String>>> getDictionaryItemEmptyAsync() {
        return getDictionaryItemEmptyWithResponseAsync()
                .flatMap(
                        (Response<List<Map<String, String>>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * {}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, {}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Map<String, String>> getDictionaryItemEmpty() {
        return getDictionaryItemEmptyAsync().block();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Map<String, String>>>> getDictionaryValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDictionaryValid(this.client.getHost(), accept, context));
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Map<String, String>>> getDictionaryValidAsync() {
        return getDictionaryValidWithResponseAsync()
                .flatMap(
                        (Response<List<Map<String, String>>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Map<String, String>> getDictionaryValid() {
        return getDictionaryValidAsync().block();
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @param arrayBody An array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two',
     *     '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDictionaryValidWithResponseAsync(List<Map<String, String>> arrayBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (arrayBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter arrayBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putDictionaryValid(this.client.getHost(), arrayBody, accept, context));
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @param arrayBody An array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two',
     *     '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3':
     *     'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDictionaryValidAsync(List<Map<String, String>> arrayBody) {
        return putDictionaryValidWithResponseAsync(arrayBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Get an array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two', '3': 'three'},
     * {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     *
     * @param arrayBody An array of Dictionaries of type &lt;string, string&gt; with value [{'1': 'one', '2': 'two',
     *     '3': 'three'}, {'4': 'four', '5': 'five', '6': 'six'}, {'7': 'seven', '8': 'eight', '9': 'nine'}].
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDictionaryValid(List<Map<String, String>> arrayBody) {
        putDictionaryValidAsync(arrayBody).block();
    }
}
