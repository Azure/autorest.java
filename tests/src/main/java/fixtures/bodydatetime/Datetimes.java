package fixtures.bodydatetime;

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
import fixtures.bodydatetime.models.ErrorException;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Datetimes.
 */
public final class Datetimes {
    /**
     * The proxy service used to perform REST calls.
     */
    private DatetimesService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestDateTimeTestService client;

    /**
     * Initializes an instance of Datetimes.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Datetimes(AutoRestDateTimeTestService client) {
        this.service = RestProxy.create(DatetimesService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestDateTimeTestServiceDatetimes to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestDateTimeTestServiceDatetimes")
    private interface DatetimesService {
        @Get("/datetime/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getNull(@HostParam("$host") String host);

        @Get("/datetime/invalid")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getInvalid(@HostParam("$host") String host);

        @Get("/datetime/overflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getOverflow(@HostParam("$host") String host);

        @Get("/datetime/underflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUnderflow(@HostParam("$host") String host);

        @Put("/datetime/max/utc")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMaxDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody);

        @Put("/datetime/max/utc7ms")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMaxDateTime7Digits(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody);

        @Get("/datetime/max/utc/lowercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcLowercaseMaxDateTime(@HostParam("$host") String host);

        @Get("/datetime/max/utc/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTime(@HostParam("$host") String host);

        @Get("/datetime/max/utc7ms/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTime7Digits(@HostParam("$host") String host);

        @Put("/datetime/max/localpositiveoffset")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLocalPositiveOffsetMaxDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody);

        @Get("/datetime/max/localpositiveoffset/lowercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetLowercaseMaxDateTime(@HostParam("$host") String host);

        @Get("/datetime/max/localpositiveoffset/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetUppercaseMaxDateTime(@HostParam("$host") String host);

        @Put("/datetime/max/localnegativeoffset")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLocalNegativeOffsetMaxDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody);

        @Get("/datetime/max/localnegativeoffset/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetUppercaseMaxDateTime(@HostParam("$host") String host);

        @Get("/datetime/max/localnegativeoffset/lowercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetLowercaseMaxDateTime(@HostParam("$host") String host);

        @Put("/datetime/min/utc")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMinDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody);

        @Get("/datetime/min/utc")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcMinDateTime(@HostParam("$host") String host);

        @Put("/datetime/min/localpositiveoffset")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLocalPositiveOffsetMinDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody);

        @Get("/datetime/min/localpositiveoffset")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetMinDateTime(@HostParam("$host") String host);

        @Put("/datetime/min/localnegativeoffset")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLocalNegativeOffsetMinDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody);

        @Get("/datetime/min/localnegativeoffset")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetMinDateTime(@HostParam("$host") String host);
    }

    /**
     * Get null datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getNull(this.client.getHost());
    }

    /**
     * Get null datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get null datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getNull() {
        return getNullAsync().block();
    }

    /**
     * Get invalid datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getInvalid(this.client.getHost());
    }

    /**
     * Get invalid datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getInvalidAsync() {
        return getInvalidWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get invalid datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getInvalid() {
        return getInvalidAsync().block();
    }

    /**
     * Get overflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getOverflowWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getOverflow(this.client.getHost());
    }

    /**
     * Get overflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getOverflowAsync() {
        return getOverflowWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get overflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getOverflow() {
        return getOverflowAsync().block();
    }

    /**
     * Get underflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUnderflowWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getUnderflow(this.client.getHost());
    }

    /**
     * Get underflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUnderflowAsync() {
        return getUnderflowWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get underflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUnderflow() {
        return getUnderflowAsync().block();
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUtcMaxDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (datetimeBody == null) {
            throw new IllegalArgumentException("Parameter datetimeBody is required and cannot be null.");
        }
        return service.putUtcMaxDateTime(this.client.getHost(), datetimeBody);
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUtcMaxDateTimeAsync(OffsetDateTime datetimeBody) {
        return putUtcMaxDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUtcMaxDateTime(OffsetDateTime datetimeBody) {
        putUtcMaxDateTimeAsync(datetimeBody).block();
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.9999999Z.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUtcMaxDateTime7DigitsWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (datetimeBody == null) {
            throw new IllegalArgumentException("Parameter datetimeBody is required and cannot be null.");
        }
        return service.putUtcMaxDateTime7Digits(this.client.getHost(), datetimeBody);
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.9999999Z.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUtcMaxDateTime7DigitsAsync(OffsetDateTime datetimeBody) {
        return putUtcMaxDateTime7DigitsWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.9999999Z.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUtcMaxDateTime7Digits(OffsetDateTime datetimeBody) {
        putUtcMaxDateTime7DigitsAsync(datetimeBody).block();
    }

    /**
     * Get max datetime value 9999-12-31t23:59:59.999z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcLowercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getUtcLowercaseMaxDateTime(this.client.getHost());
    }

    /**
     * Get max datetime value 9999-12-31t23:59:59.999z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUtcLowercaseMaxDateTimeAsync() {
        return getUtcLowercaseMaxDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get max datetime value 9999-12-31t23:59:59.999z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcLowercaseMaxDateTime() {
        return getUtcLowercaseMaxDateTimeAsync().block();
    }

    /**
     * Get max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getUtcUppercaseMaxDateTime(this.client.getHost());
    }

    /**
     * Get max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUtcUppercaseMaxDateTimeAsync() {
        return getUtcUppercaseMaxDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcUppercaseMaxDateTime() {
        return getUtcUppercaseMaxDateTimeAsync().block();
    }

    /**
     * Get max datetime value 9999-12-31T23:59:59.9999999Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTime7DigitsWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getUtcUppercaseMaxDateTime7Digits(this.client.getHost());
    }

    /**
     * Get max datetime value 9999-12-31T23:59:59.9999999Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUtcUppercaseMaxDateTime7DigitsAsync() {
        return getUtcUppercaseMaxDateTime7DigitsWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get max datetime value 9999-12-31T23:59:59.9999999Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcUppercaseMaxDateTime7Digits() {
        return getUtcUppercaseMaxDateTime7DigitsAsync().block();
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999+14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLocalPositiveOffsetMaxDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (datetimeBody == null) {
            throw new IllegalArgumentException("Parameter datetimeBody is required and cannot be null.");
        }
        return service.putLocalPositiveOffsetMaxDateTime(this.client.getHost(), datetimeBody);
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999+14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLocalPositiveOffsetMaxDateTimeAsync(OffsetDateTime datetimeBody) {
        return putLocalPositiveOffsetMaxDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999+14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLocalPositiveOffsetMaxDateTime(OffsetDateTime datetimeBody) {
        putLocalPositiveOffsetMaxDateTimeAsync(datetimeBody).block();
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetLowercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getLocalPositiveOffsetLowercaseMaxDateTime(this.client.getHost());
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getLocalPositiveOffsetLowercaseMaxDateTimeAsync() {
        return getLocalPositiveOffsetLowercaseMaxDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalPositiveOffsetLowercaseMaxDateTime() {
        return getLocalPositiveOffsetLowercaseMaxDateTimeAsync().block();
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetUppercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getLocalPositiveOffsetUppercaseMaxDateTime(this.client.getHost());
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getLocalPositiveOffsetUppercaseMaxDateTimeAsync() {
        return getLocalPositiveOffsetUppercaseMaxDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalPositiveOffsetUppercaseMaxDateTime() {
        return getLocalPositiveOffsetUppercaseMaxDateTimeAsync().block();
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999-14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLocalNegativeOffsetMaxDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (datetimeBody == null) {
            throw new IllegalArgumentException("Parameter datetimeBody is required and cannot be null.");
        }
        return service.putLocalNegativeOffsetMaxDateTime(this.client.getHost(), datetimeBody);
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999-14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLocalNegativeOffsetMaxDateTimeAsync(OffsetDateTime datetimeBody) {
        return putLocalNegativeOffsetMaxDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999-14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLocalNegativeOffsetMaxDateTime(OffsetDateTime datetimeBody) {
        putLocalNegativeOffsetMaxDateTimeAsync(datetimeBody).block();
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetUppercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getLocalNegativeOffsetUppercaseMaxDateTime(this.client.getHost());
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getLocalNegativeOffsetUppercaseMaxDateTimeAsync() {
        return getLocalNegativeOffsetUppercaseMaxDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalNegativeOffsetUppercaseMaxDateTime() {
        return getLocalNegativeOffsetUppercaseMaxDateTimeAsync().block();
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetLowercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getLocalNegativeOffsetLowercaseMaxDateTime(this.client.getHost());
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getLocalNegativeOffsetLowercaseMaxDateTimeAsync() {
        return getLocalNegativeOffsetLowercaseMaxDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalNegativeOffsetLowercaseMaxDateTime() {
        return getLocalNegativeOffsetLowercaseMaxDateTimeAsync().block();
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00Z.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUtcMinDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (datetimeBody == null) {
            throw new IllegalArgumentException("Parameter datetimeBody is required and cannot be null.");
        }
        return service.putUtcMinDateTime(this.client.getHost(), datetimeBody);
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00Z.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUtcMinDateTimeAsync(OffsetDateTime datetimeBody) {
        return putUtcMinDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00Z.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUtcMinDateTime(OffsetDateTime datetimeBody) {
        putUtcMinDateTimeAsync(datetimeBody).block();
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcMinDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getUtcMinDateTime(this.client.getHost());
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getUtcMinDateTimeAsync() {
        return getUtcMinDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcMinDateTime() {
        return getUtcMinDateTimeAsync().block();
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLocalPositiveOffsetMinDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (datetimeBody == null) {
            throw new IllegalArgumentException("Parameter datetimeBody is required and cannot be null.");
        }
        return service.putLocalPositiveOffsetMinDateTime(this.client.getHost(), datetimeBody);
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLocalPositiveOffsetMinDateTimeAsync(OffsetDateTime datetimeBody) {
        return putLocalPositiveOffsetMinDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLocalPositiveOffsetMinDateTime(OffsetDateTime datetimeBody) {
        putLocalPositiveOffsetMinDateTimeAsync(datetimeBody).block();
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetMinDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getLocalPositiveOffsetMinDateTime(this.client.getHost());
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getLocalPositiveOffsetMinDateTimeAsync() {
        return getLocalPositiveOffsetMinDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalPositiveOffsetMinDateTime() {
        return getLocalPositiveOffsetMinDateTimeAsync().block();
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLocalNegativeOffsetMinDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (datetimeBody == null) {
            throw new IllegalArgumentException("Parameter datetimeBody is required and cannot be null.");
        }
        return service.putLocalNegativeOffsetMinDateTime(this.client.getHost(), datetimeBody);
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLocalNegativeOffsetMinDateTimeAsync(OffsetDateTime datetimeBody) {
        return putLocalNegativeOffsetMinDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @param datetimeBody MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putLocalNegativeOffsetMinDateTime(OffsetDateTime datetimeBody) {
        putLocalNegativeOffsetMinDateTimeAsync(datetimeBody).block();
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetMinDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getLocalNegativeOffsetMinDateTime(this.client.getHost());
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getLocalNegativeOffsetMinDateTimeAsync() {
        return getLocalNegativeOffsetMinDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalNegativeOffsetMinDateTime() {
        return getLocalNegativeOffsetMinDateTimeAsync().block();
    }
}
