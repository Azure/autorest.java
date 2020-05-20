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
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodydatetime.models.ErrorException;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * DatetimeOperations.
 */
public final class DatetimeOperations {
    /**
     * The proxy service used to perform REST calls.
     */
    private final DatetimeOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestDateTimeTestService client;

    /**
     * Initializes an instance of DatetimeOperations.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    DatetimeOperations(AutoRestDateTimeTestService client) {
        this.service = RestProxy.create(DatetimeOperationsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestDateTimeTestServiceDatetimeOperations to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestDateTimeTest")
    private interface DatetimeOperationsService {
        @Get("/datetime/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getNull(@HostParam("$host") String host, Context context);

        @Get("/datetime/invalid")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getInvalid(@HostParam("$host") String host, Context context);

        @Get("/datetime/overflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getOverflow(@HostParam("$host") String host, Context context);

        @Get("/datetime/underflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUnderflow(@HostParam("$host") String host, Context context);

        @Put("/datetime/max/utc")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMaxDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody, Context context);

        @Put("/datetime/max/utc7ms")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMaxDateTime7Digits(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody, Context context);

        @Get("/datetime/max/utc/lowercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcLowercaseMaxDateTime(@HostParam("$host") String host, Context context);

        @Get("/datetime/max/utc/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTime(@HostParam("$host") String host, Context context);

        @Get("/datetime/max/utc7ms/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTime7Digits(@HostParam("$host") String host, Context context);

        @Put("/datetime/max/localpositiveoffset")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLocalPositiveOffsetMaxDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody, Context context);

        @Get("/datetime/max/localpositiveoffset/lowercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetLowercaseMaxDateTime(@HostParam("$host") String host, Context context);

        @Get("/datetime/max/localpositiveoffset/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetUppercaseMaxDateTime(@HostParam("$host") String host, Context context);

        @Put("/datetime/max/localnegativeoffset")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLocalNegativeOffsetMaxDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody, Context context);

        @Get("/datetime/max/localnegativeoffset/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetUppercaseMaxDateTime(@HostParam("$host") String host, Context context);

        @Get("/datetime/max/localnegativeoffset/lowercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetLowercaseMaxDateTime(@HostParam("$host") String host, Context context);

        @Put("/datetime/min/utc")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMinDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody, Context context);

        @Get("/datetime/min/utc")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcMinDateTime(@HostParam("$host") String host, Context context);

        @Put("/datetime/min/localpositiveoffset")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLocalPositiveOffsetMinDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody, Context context);

        @Get("/datetime/min/localpositiveoffset")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetMinDateTime(@HostParam("$host") String host, Context context);

        @Put("/datetime/min/localnegativeoffset")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putLocalNegativeOffsetMinDateTime(@HostParam("$host") String host, @BodyParam("application/json") OffsetDateTime datetimeBody, Context context);

        @Get("/datetime/min/localnegativeoffset")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetMinDateTime(@HostParam("$host") String host, Context context);

        @Get("/datetime/min/localnooffset")
        @ExpectedResponses({200})
        @ReturnValueWireType(OffsetDateTime.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getLocalNoOffsetMinDateTime(@HostParam("$host") String host, Context context);
    }

    /**
     * Get null datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null datetime value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), context));
    }

    /**
     * Get null datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null datetime value.
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
     * @return null datetime value.
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
     * @return invalid datetime value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getInvalid(this.client.getHost(), context));
    }

    /**
     * Get invalid datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid datetime value.
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
     * @return invalid datetime value.
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
     * @return overflow datetime value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getOverflowWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getOverflow(this.client.getHost(), context));
    }

    /**
     * Get overflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow datetime value.
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
     * @return overflow datetime value.
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
     * @return underflow datetime value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUnderflowWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUnderflow(this.client.getHost(), context));
    }

    /**
     * Get underflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow datetime value.
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
     * @return underflow datetime value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUnderflow() {
        return getUnderflowAsync().block();
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUtcMaxDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (datetimeBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter datetimeBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putUtcMaxDateTime(this.client.getHost(), datetimeBody, context));
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUtcMaxDateTimeAsync(OffsetDateTime datetimeBody) {
        return putUtcMaxDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putUtcMaxDateTime(OffsetDateTime datetimeBody) {
        putUtcMaxDateTimeAsync(datetimeBody).block();
    }

    /**
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUtcMaxDateTime7DigitsWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (datetimeBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter datetimeBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putUtcMaxDateTime7Digits(this.client.getHost(), datetimeBody, context));
    }

    /**
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUtcMaxDateTime7DigitsAsync(OffsetDateTime datetimeBody) {
        return putUtcMaxDateTime7DigitsWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @param datetimeBody The datetimeBody parameter.
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
     * @return max datetime value 9999-12-31t23:59:59.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcLowercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUtcLowercaseMaxDateTime(this.client.getHost(), context));
    }

    /**
     * Get max datetime value 9999-12-31t23:59:59.999z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value 9999-12-31t23:59:59.
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
     * @return max datetime value 9999-12-31t23:59:59.
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
     * @return max datetime value 9999-12-31T23:59:59.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUtcUppercaseMaxDateTime(this.client.getHost(), context));
    }

    /**
     * Get max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value 9999-12-31T23:59:59.
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
     * @return max datetime value 9999-12-31T23:59:59.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcUppercaseMaxDateTime() {
        return getUtcUppercaseMaxDateTimeAsync().block();
    }

    /**
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTime7DigitsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUtcUppercaseMaxDateTime7Digits(this.client.getHost(), context));
    }

    /**
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
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
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcUppercaseMaxDateTime7Digits() {
        return getUtcUppercaseMaxDateTime7DigitsAsync().block();
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999+14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLocalPositiveOffsetMaxDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (datetimeBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter datetimeBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putLocalPositiveOffsetMaxDateTime(this.client.getHost(), datetimeBody, context));
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999+14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLocalPositiveOffsetMaxDateTimeAsync(OffsetDateTime datetimeBody) {
        return putLocalPositiveOffsetMaxDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999+14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
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
     * @return max datetime value with positive num offset 9999-12-31t23:59:59.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetLowercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLocalPositiveOffsetLowercaseMaxDateTime(this.client.getHost(), context));
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value with positive num offset 9999-12-31t23:59:59.
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
     * @return max datetime value with positive num offset 9999-12-31t23:59:59.
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
     * @return max datetime value with positive num offset 9999-12-31T23:59:59.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetUppercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLocalPositiveOffsetUppercaseMaxDateTime(this.client.getHost(), context));
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value with positive num offset 9999-12-31T23:59:59.
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
     * @return max datetime value with positive num offset 9999-12-31T23:59:59.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalPositiveOffsetUppercaseMaxDateTime() {
        return getLocalPositiveOffsetUppercaseMaxDateTimeAsync().block();
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999-14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLocalNegativeOffsetMaxDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (datetimeBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter datetimeBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putLocalNegativeOffsetMaxDateTime(this.client.getHost(), datetimeBody, context));
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999-14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLocalNegativeOffsetMaxDateTimeAsync(OffsetDateTime datetimeBody) {
        return putLocalNegativeOffsetMaxDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999-14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
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
     * @return max datetime value with positive num offset 9999-12-31T23:59:59.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetUppercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLocalNegativeOffsetUppercaseMaxDateTime(this.client.getHost(), context));
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value with positive num offset 9999-12-31T23:59:59.
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
     * @return max datetime value with positive num offset 9999-12-31T23:59:59.
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
     * @return max datetime value with positive num offset 9999-12-31t23:59:59.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetLowercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLocalNegativeOffsetLowercaseMaxDateTime(this.client.getHost(), context));
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value with positive num offset 9999-12-31t23:59:59.
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
     * @return max datetime value with positive num offset 9999-12-31t23:59:59.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalNegativeOffsetLowercaseMaxDateTime() {
        return getLocalNegativeOffsetLowercaseMaxDateTimeAsync().block();
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00Z.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putUtcMinDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (datetimeBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter datetimeBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putUtcMinDateTime(this.client.getHost(), datetimeBody, context));
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00Z.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putUtcMinDateTimeAsync(OffsetDateTime datetimeBody) {
        return putUtcMinDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00Z.
     * 
     * @param datetimeBody The datetimeBody parameter.
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
     * @return min datetime value 0001-01-01T00:00:00Z.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcMinDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUtcMinDateTime(this.client.getHost(), context));
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00Z.
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
     * @return min datetime value 0001-01-01T00:00:00Z.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcMinDateTime() {
        return getUtcMinDateTimeAsync().block();
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLocalPositiveOffsetMinDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (datetimeBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter datetimeBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putLocalPositiveOffsetMinDateTime(this.client.getHost(), datetimeBody, context));
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLocalPositiveOffsetMinDateTimeAsync(OffsetDateTime datetimeBody) {
        return putLocalPositiveOffsetMinDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
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
     * @return min datetime value 0001-01-01T00:00:00+14:00.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalPositiveOffsetMinDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLocalPositiveOffsetMinDateTime(this.client.getHost(), context));
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00+14:00.
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
     * @return min datetime value 0001-01-01T00:00:00+14:00.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalPositiveOffsetMinDateTime() {
        return getLocalPositiveOffsetMinDateTimeAsync().block();
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putLocalNegativeOffsetMinDateTimeWithResponseAsync(OffsetDateTime datetimeBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (datetimeBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter datetimeBody is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putLocalNegativeOffsetMinDateTime(this.client.getHost(), datetimeBody, context));
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putLocalNegativeOffsetMinDateTimeAsync(OffsetDateTime datetimeBody) {
        return putLocalNegativeOffsetMinDateTimeWithResponseAsync(datetimeBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @param datetimeBody The datetimeBody parameter.
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
     * @return min datetime value 0001-01-01T00:00:00-14:00.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalNegativeOffsetMinDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLocalNegativeOffsetMinDateTime(this.client.getHost(), context));
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00-14:00.
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
     * @return min datetime value 0001-01-01T00:00:00-14:00.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalNegativeOffsetMinDateTime() {
        return getLocalNegativeOffsetMinDateTimeAsync().block();
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getLocalNoOffsetMinDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getLocalNoOffsetMinDateTime(this.client.getHost(), context));
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<OffsetDateTime> getLocalNoOffsetMinDateTimeAsync() {
        return getLocalNoOffsetMinDateTimeWithResponseAsync()
            .flatMap((SimpleResponse<OffsetDateTime> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getLocalNoOffsetMinDateTime() {
        return getLocalNoOffsetMinDateTimeAsync().block();
    }
}
