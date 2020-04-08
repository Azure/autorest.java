package fixtures.bodydatetimerfc1123;

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
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.FluxUtil;
import fixtures.bodydatetimerfc1123.models.ErrorException;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Datetimerfc1123s.
 */
public final class Datetimerfc1123s {
    /**
     * The proxy service used to perform REST calls.
     */
    private final Datetimerfc1123sService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestRFC1123DateTimeTestService client;

    /**
     * Initializes an instance of Datetimerfc1123s.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Datetimerfc1123s(AutoRestRFC1123DateTimeTestService client) {
        this.service = RestProxy.create(Datetimerfc1123sService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestRFC1123DateTimeTestServiceDatetimerfc1123s to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestRFC1123DateT")
    private interface Datetimerfc1123sService {
        @Get("/datetimerfc1123/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getNull(@HostParam("$host") String host, Context context);

        @Get("/datetimerfc1123/invalid")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getInvalid(@HostParam("$host") String host, Context context);

        @Get("/datetimerfc1123/overflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getOverflow(@HostParam("$host") String host, Context context);

        @Get("/datetimerfc1123/underflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUnderflow(@HostParam("$host") String host, Context context);

        @Put("/datetimerfc1123/max")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMaxDateTime(@HostParam("$host") String host, @BodyParam("application/json") DateTimeRfc1123 datetimeBody, Context context);

        @Get("/datetimerfc1123/max/lowercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcLowercaseMaxDateTime(@HostParam("$host") String host, Context context);

        @Get("/datetimerfc1123/max/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTime(@HostParam("$host") String host, Context context);

        @Put("/datetimerfc1123/min")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putUtcMinDateTime(@HostParam("$host") String host, @BodyParam("application/json") DateTimeRfc1123 datetimeBody, Context context);

        @Get("/datetimerfc1123/min")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<OffsetDateTime>> getUtcMinDateTime(@HostParam("$host") String host, Context context);
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
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
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
        DateTimeRfc1123 datetimeBodyConverted = new DateTimeRfc1123(datetimeBody);
        return FluxUtil.withContext(context -> service.putUtcMaxDateTime(this.client.getHost(), datetimeBodyConverted, context));
    }

    /**
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
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
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
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
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value fri, 31 dec 9999 23:59:59 gmt.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcLowercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUtcLowercaseMaxDateTime(this.client.getHost(), context));
    }

    /**
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value fri, 31 dec 9999 23:59:59 gmt.
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
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value fri, 31 dec 9999 23:59:59 gmt.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcLowercaseMaxDateTime() {
        return getUtcLowercaseMaxDateTimeAsync().block();
    }

    /**
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcUppercaseMaxDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUtcUppercaseMaxDateTime(this.client.getHost(), context));
    }

    /**
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
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
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcUppercaseMaxDateTime() {
        return getUtcUppercaseMaxDateTimeAsync().block();
    }

    /**
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
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
        DateTimeRfc1123 datetimeBodyConverted = new DateTimeRfc1123(datetimeBody);
        return FluxUtil.withContext(context -> service.putUtcMinDateTime(this.client.getHost(), datetimeBodyConverted, context));
    }

    /**
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
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
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
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
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<OffsetDateTime>> getUtcMinDateTimeWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getUtcMinDateTime(this.client.getHost(), context));
    }

    /**
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
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
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public OffsetDateTime getUtcMinDateTime() {
        return getUtcMinDateTimeAsync().block();
    }
}
