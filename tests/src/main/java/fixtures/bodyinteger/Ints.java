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
import com.azure.core.http.rest.SimpleResponse;
import fixtures.bodyinteger.models.ErrorException;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Ints.
 */
public final class Ints {
    /**
     * The proxy service used to perform REST calls.
     */
    private IntsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestIntegerTestService client;

    /**
     * Initializes an instance of Ints.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Ints(AutoRestIntegerTestService client) {
        this.service = RestProxy.create(IntsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestIntegerTestServiceInts to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestIntegerTestServiceInts")
    private interface IntsService {
        @Get("/int/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Integer>> getNull(@HostParam("$host") String host);

        @Get("/int/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Integer>> getInvalid(@HostParam("$host") String host);

        @Get("/int/overflowint32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Integer>> getOverflowInt32(@HostParam("$host") String host);

        @Get("/int/underflowint32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Integer>> getUnderflowInt32(@HostParam("$host") String host);

        @Get("/int/overflowint64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Long>> getOverflowInt64(@HostParam("$host") String host);

        @Get("/int/underflowint64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Long>> getUnderflowInt64(@HostParam("$host") String host);

        @Put("/int/max/32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMax32(@HostParam("$host") String host, @BodyParam("application/json") int intBody);

        @Put("/int/max/64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMax64(@HostParam("$host") String host, @BodyParam("application/json") long intBody);

        @Put("/int/min/32")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMin32(@HostParam("$host") String host, @BodyParam("application/json") int intBody);

        @Put("/int/min/64")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMin64(@HostParam("$host") String host, @BodyParam("application/json") long intBody);

        @Get("/int/unixtime")
        @ExpectedResponses({200})
        @ReturnValueWireType(long.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUnixTime(@HostParam("$host") String host);

        @Put("/int/unixtime")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUnixTimeDate(@HostParam("$host") String host, @BodyParam("application/json") long intBody);

        @Get("/int/invalidunixtime")
        @ExpectedResponses({200})
        @ReturnValueWireType(long.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getInvalidUnixTime(@HostParam("$host") String host);

        @Get("/int/nullunixtime")
        @ExpectedResponses({200})
        @ReturnValueWireType(long.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getNullUnixTime(@HostParam("$host") String host);
    }

    /**
     * Get null Int value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Integer>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getNull(this.client.getHost());
    }

    /**
     * Get null Int value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<Integer> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getNull() {
        return getNullAsync().block();
    }

    /**
     * Get invalid Int value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Integer>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getInvalid(this.client.getHost());
    }

    /**
     * Get invalid Int value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getInvalidAsync() {
        return getInvalidWithResponseAsync()
            .flatMap((SimpleResponse<Integer> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getInvalid() {
        return getInvalidAsync().block();
    }

    /**
     * Get overflow Int32 value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Integer>> getOverflowInt32WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getOverflowInt32(this.client.getHost());
    }

    /**
     * Get overflow Int32 value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getOverflowInt32Async() {
        return getOverflowInt32WithResponseAsync()
            .flatMap((SimpleResponse<Integer> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getOverflowInt32() {
        return getOverflowInt32Async().block();
    }

    /**
     * Get underflow Int32 value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Integer>> getUnderflowInt32WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getUnderflowInt32(this.client.getHost());
    }

    /**
     * Get underflow Int32 value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Integer> getUnderflowInt32Async() {
        return getUnderflowInt32WithResponseAsync()
            .flatMap((SimpleResponse<Integer> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public int getUnderflowInt32() {
        return getUnderflowInt32Async().block();
    }

    /**
     * Get overflow Int64 value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Long>> getOverflowInt64WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getOverflowInt64(this.client.getHost());
    }

    /**
     * Get overflow Int64 value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Long> getOverflowInt64Async() {
        return getOverflowInt64WithResponseAsync()
            .flatMap((SimpleResponse<Long> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public long getOverflowInt64() {
        return getOverflowInt64Async().block();
    }

    /**
     * Get underflow Int64 value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Long>> getUnderflowInt64WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getUnderflowInt64(this.client.getHost());
    }

    /**
     * Get underflow Int64 value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Long> getUnderflowInt64Async() {
        return getUnderflowInt64WithResponseAsync()
            .flatMap((SimpleResponse<Long> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public long getUnderflowInt64() {
        return getUnderflowInt64Async().block();
    }

    /**
     * Put max int32 value.
     * 
     * @param intBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMax32WithResponseAsync(int intBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putMax32(this.client.getHost(), intBody);
    }

    /**
     * Put max int32 value.
     * 
     * @param intBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMax32Async(int intBody) {
        return putMax32WithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max int32 value.
     * 
     * @param intBody 
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
     * @param intBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMax64WithResponseAsync(long intBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putMax64(this.client.getHost(), intBody);
    }

    /**
     * Put max int64 value.
     * 
     * @param intBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMax64Async(long intBody) {
        return putMax64WithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max int64 value.
     * 
     * @param intBody 
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
     * @param intBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMin32WithResponseAsync(int intBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putMin32(this.client.getHost(), intBody);
    }

    /**
     * Put min int32 value.
     * 
     * @param intBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMin32Async(int intBody) {
        return putMin32WithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min int32 value.
     * 
     * @param intBody 
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
     * @param intBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMin64WithResponseAsync(long intBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putMin64(this.client.getHost(), intBody);
    }

    /**
     * Put min int64 value.
     * 
     * @param intBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMin64Async(long intBody) {
        return putMin64WithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min int64 value.
     * 
     * @param intBody 
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUnixTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getUnixTime(this.client.getHost());
    }

    /**
     * Get datetime encoded as Unix time value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUnixTimeAsync() {
        return getUnixTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUnixTime() {
        return getUnixTimeAsync().block();
    }

    /**
     * Put datetime encoded as Unix time.
     * 
     * @param intBody date in seconds since 1970-01-01T00:00:00Z.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUnixTimeDateWithResponseAsync(OffsetDateTime intBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (intBody == null) {
            throw new IllegalArgumentException("Parameter intBody is required and cannot be null.");
        }
        long intBodyConverted = intBody.toEpochSecond();
        return service.putUnixTimeDate(this.client.getHost(), intBodyConverted);
    }

    /**
     * Put datetime encoded as Unix time.
     * 
     * @param intBody date in seconds since 1970-01-01T00:00:00Z.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUnixTimeDateAsync(OffsetDateTime intBody) {
        return putUnixTimeDateWithResponseAsync(intBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put datetime encoded as Unix time.
     * 
     * @param intBody date in seconds since 1970-01-01T00:00:00Z.
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getInvalidUnixTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getInvalidUnixTime(this.client.getHost());
    }

    /**
     * Get invalid Unix time value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getInvalidUnixTimeAsync() {
        return getInvalidUnixTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getNullUnixTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getNullUnixTime(this.client.getHost());
    }

    /**
     * Get null Unix time value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getNullUnixTimeAsync() {
        return getNullUnixTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getNullUnixTime() {
        return getNullUnixTimeAsync().block();
    }
}
