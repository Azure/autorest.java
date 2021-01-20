package fixtures.bodydate;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodydate.models.ErrorException;
import java.time.LocalDate;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in DateOperations. */
public final class DateOperations {
    /** The proxy service used to perform REST calls. */
    private final DateOperationsService service;

    /** The service client containing this operation class. */
    private final AutoRestDateTestService client;

    /**
     * Initializes an instance of DateOperations.
     *
     * @param client the instance of the service client containing this operation class.
     */
    DateOperations(AutoRestDateTestService client) {
        this.service =
                RestProxy.create(DateOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestDateTestServiceDateOperations to be used by the proxy service
     * to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestDateTestServ")
    private interface DateOperationsService {
        @Get("/date/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<LocalDate>> getNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/date/invaliddate")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<LocalDate>> getInvalidDate(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/date/overflowdate")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<LocalDate>> getOverflowDate(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/date/underflowdate")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<LocalDate>> getUnderflowDate(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/date/max")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMaxDate(
                @HostParam("$host") String host,
                @BodyParam("application/json") LocalDate dateBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/date/max")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<LocalDate>> getMaxDate(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/date/min")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putMinDate(
                @HostParam("$host") String host,
                @BodyParam("application/json") LocalDate dateBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/date/min")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<LocalDate>> getMinDate(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get null date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<LocalDate>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), accept, context));
    }

    /**
     * Get null date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LocalDate> getNullAsync() {
        return getNullWithResponseAsync()
                .flatMap(
                        (Response<LocalDate> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get null date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LocalDate getNull() {
        return getNullAsync().block();
    }

    /**
     * Get invalid date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<LocalDate>> getInvalidDateWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getInvalidDate(this.client.getHost(), accept, context));
    }

    /**
     * Get invalid date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LocalDate> getInvalidDateAsync() {
        return getInvalidDateWithResponseAsync()
                .flatMap(
                        (Response<LocalDate> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get invalid date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LocalDate getInvalidDate() {
        return getInvalidDateAsync().block();
    }

    /**
     * Get overflow date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<LocalDate>> getOverflowDateWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getOverflowDate(this.client.getHost(), accept, context));
    }

    /**
     * Get overflow date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LocalDate> getOverflowDateAsync() {
        return getOverflowDateWithResponseAsync()
                .flatMap(
                        (Response<LocalDate> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get overflow date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LocalDate getOverflowDate() {
        return getOverflowDateAsync().block();
    }

    /**
     * Get underflow date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<LocalDate>> getUnderflowDateWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getUnderflowDate(this.client.getHost(), accept, context));
    }

    /**
     * Get underflow date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LocalDate> getUnderflowDateAsync() {
        return getUnderflowDateWithResponseAsync()
                .flatMap(
                        (Response<LocalDate> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get underflow date value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow date value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LocalDate getUnderflowDate() {
        return getUnderflowDateAsync().block();
    }

    /**
     * Put max date value 9999-12-31.
     *
     * @param dateBody date body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMaxDateWithResponseAsync(LocalDate dateBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (dateBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter dateBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putMaxDate(this.client.getHost(), dateBody, accept, context));
    }

    /**
     * Put max date value 9999-12-31.
     *
     * @param dateBody date body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMaxDateAsync(LocalDate dateBody) {
        return putMaxDateWithResponseAsync(dateBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put max date value 9999-12-31.
     *
     * @param dateBody date body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMaxDate(LocalDate dateBody) {
        putMaxDateAsync(dateBody).block();
    }

    /**
     * Get max date value 9999-12-31.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max date value 9999-12-31.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<LocalDate>> getMaxDateWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getMaxDate(this.client.getHost(), accept, context));
    }

    /**
     * Get max date value 9999-12-31.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max date value 9999-12-31.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LocalDate> getMaxDateAsync() {
        return getMaxDateWithResponseAsync()
                .flatMap(
                        (Response<LocalDate> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get max date value 9999-12-31.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max date value 9999-12-31.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LocalDate getMaxDate() {
        return getMaxDateAsync().block();
    }

    /**
     * Put min date value 0000-01-01.
     *
     * @param dateBody date body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putMinDateWithResponseAsync(LocalDate dateBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (dateBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter dateBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putMinDate(this.client.getHost(), dateBody, accept, context));
    }

    /**
     * Put min date value 0000-01-01.
     *
     * @param dateBody date body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putMinDateAsync(LocalDate dateBody) {
        return putMinDateWithResponseAsync(dateBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put min date value 0000-01-01.
     *
     * @param dateBody date body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putMinDate(LocalDate dateBody) {
        putMinDateAsync(dateBody).block();
    }

    /**
     * Get min date value 0000-01-01.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min date value 0000-01-01.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<LocalDate>> getMinDateWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getMinDate(this.client.getHost(), accept, context));
    }

    /**
     * Get min date value 0000-01-01.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min date value 0000-01-01.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LocalDate> getMinDateAsync() {
        return getMinDateWithResponseAsync()
                .flatMap(
                        (Response<LocalDate> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get min date value 0000-01-01.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min date value 0000-01-01.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LocalDate getMinDate() {
        return getMinDateAsync().block();
    }
}
