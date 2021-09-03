package fixtures.bodynumber;

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
import fixtures.bodynumber.models.ErrorException;
import java.math.BigDecimal;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Numbers. */
public final class Numbers {
    /** The proxy service used to perform REST calls. */
    private final NumbersService service;

    /** The service client containing this operation class. */
    private final AutoRestNumberTestService client;

    /**
     * Initializes an instance of Numbers.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Numbers(AutoRestNumberTestService client) {
        this.service = RestProxy.create(NumbersService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestNumberTestServiceNumbers to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestNumberTestSe")
    private interface NumbersService {
        @Get("/number/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Float>> getNull(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/number/invalidfloat")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Float>> getInvalidFloat(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/number/invaliddouble")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Double>> getInvalidDouble(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/number/invaliddecimal")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<BigDecimal>> getInvalidDecimal(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/big/float/3.402823e+20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigFloat(
                @HostParam("$host") String host,
                @BodyParam("application/json") float numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/big/float/3.402823e+20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Float>> getBigFloat(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/big/double/2.5976931e+101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDouble(
                @HostParam("$host") String host,
                @BodyParam("application/json") double numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/big/double/2.5976931e+101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Double>> getBigDouble(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/big/double/99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDoublePositiveDecimal(
                @HostParam("$host") String host,
                @BodyParam("application/json") double numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/big/double/99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Double>> getBigDoublePositiveDecimal(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/big/double/-99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDoubleNegativeDecimal(
                @HostParam("$host") String host,
                @BodyParam("application/json") double numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/big/double/-99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Double>> getBigDoubleNegativeDecimal(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/big/decimal/2.5976931e+101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDecimal(
                @HostParam("$host") String host,
                @BodyParam("application/json") BigDecimal numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/big/decimal/2.5976931e+101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<BigDecimal>> getBigDecimal(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/big/decimal/99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDecimalPositiveDecimal(
                @HostParam("$host") String host,
                @BodyParam("application/json") BigDecimal numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/big/decimal/99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<BigDecimal>> getBigDecimalPositiveDecimal(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/big/decimal/-99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDecimalNegativeDecimal(
                @HostParam("$host") String host,
                @BodyParam("application/json") BigDecimal numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/big/decimal/-99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<BigDecimal>> getBigDecimalNegativeDecimal(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/small/float/3.402823e-20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putSmallFloat(
                @HostParam("$host") String host,
                @BodyParam("application/json") float numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/small/float/3.402823e-20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Double>> getSmallFloat(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/small/double/2.5976931e-101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putSmallDouble(
                @HostParam("$host") String host,
                @BodyParam("application/json") double numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/small/double/2.5976931e-101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Double>> getSmallDouble(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/number/small/decimal/2.5976931e-101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putSmallDecimal(
                @HostParam("$host") String host,
                @BodyParam("application/json") BigDecimal numberBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/number/small/decimal/2.5976931e-101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<BigDecimal>> getSmallDecimal(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get null Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Float>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), accept, context));
    }

    /**
     * Get null Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Float> getNullAsync() {
        return getNullWithResponseAsync()
                .flatMap(
                        (Response<Float> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get null Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public float getNull() {
        Float value = getNullAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get invalid float Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid float Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Float>> getInvalidFloatWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getInvalidFloat(this.client.getHost(), accept, context));
    }

    /**
     * Get invalid float Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid float Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Float> getInvalidFloatAsync() {
        return getInvalidFloatWithResponseAsync()
                .flatMap(
                        (Response<Float> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get invalid float Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid float Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public float getInvalidFloat() {
        Float value = getInvalidFloatAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get invalid double Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid double Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Double>> getInvalidDoubleWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getInvalidDouble(this.client.getHost(), accept, context));
    }

    /**
     * Get invalid double Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid double Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getInvalidDoubleAsync() {
        return getInvalidDoubleWithResponseAsync()
                .flatMap(
                        (Response<Double> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get invalid double Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid double Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getInvalidDouble() {
        Double value = getInvalidDoubleAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Get invalid decimal Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid decimal Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BigDecimal>> getInvalidDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getInvalidDecimal(this.client.getHost(), accept, context));
    }

    /**
     * Get invalid decimal Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid decimal Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getInvalidDecimalAsync() {
        return getInvalidDecimalWithResponseAsync()
                .flatMap(
                        (Response<BigDecimal> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get invalid decimal Number value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid decimal Number value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BigDecimal getInvalidDecimal() {
        return getInvalidDecimalAsync().block();
    }

    /**
     * Put big float value 3.402823e+20.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigFloatWithResponseAsync(float numberBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putBigFloat(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put big float value 3.402823e+20.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigFloatAsync(float numberBody) {
        return putBigFloatWithResponseAsync(numberBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big float value 3.402823e+20.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBigFloat(float numberBody) {
        putBigFloatAsync(numberBody).block();
    }

    /**
     * Get big float value 3.402823e+20.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big float value 3.402823e+20.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Float>> getBigFloatWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getBigFloat(this.client.getHost(), accept, context));
    }

    /**
     * Get big float value 3.402823e+20.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big float value 3.402823e+20.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Float> getBigFloatAsync() {
        return getBigFloatWithResponseAsync()
                .flatMap(
                        (Response<Float> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get big float value 3.402823e+20.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big float value 3.402823e+20.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public float getBigFloat() {
        Float value = getBigFloatAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Put big double value 2.5976931e+101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDoubleWithResponseAsync(double numberBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putBigDouble(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put big double value 2.5976931e+101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDoubleAsync(double numberBody) {
        return putBigDoubleWithResponseAsync(numberBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big double value 2.5976931e+101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBigDouble(double numberBody) {
        putBigDoubleAsync(numberBody).block();
    }

    /**
     * Get big double value 2.5976931e+101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 2.5976931e+101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Double>> getBigDoubleWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getBigDouble(this.client.getHost(), accept, context));
    }

    /**
     * Get big double value 2.5976931e+101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 2.5976931e+101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getBigDoubleAsync() {
        return getBigDoubleWithResponseAsync()
                .flatMap(
                        (Response<Double> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get big double value 2.5976931e+101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 2.5976931e+101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getBigDouble() {
        Double value = getBigDoubleAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Put big double value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDoublePositiveDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final double numberBody = 9.999999999E7;
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putBigDoublePositiveDecimal(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put big double value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDoublePositiveDecimalAsync() {
        return putBigDoublePositiveDecimalWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big double value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBigDoublePositiveDecimal() {
        putBigDoublePositiveDecimalAsync().block();
    }

    /**
     * Get big double value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Double>> getBigDoublePositiveDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getBigDoublePositiveDecimal(this.client.getHost(), accept, context));
    }

    /**
     * Get big double value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getBigDoublePositiveDecimalAsync() {
        return getBigDoublePositiveDecimalWithResponseAsync()
                .flatMap(
                        (Response<Double> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get big double value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getBigDoublePositiveDecimal() {
        Double value = getBigDoublePositiveDecimalAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Put big double value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDoubleNegativeDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final double numberBody = -9.999999999E7;
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putBigDoubleNegativeDecimal(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put big double value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDoubleNegativeDecimalAsync() {
        return putBigDoubleNegativeDecimalWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big double value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBigDoubleNegativeDecimal() {
        putBigDoubleNegativeDecimalAsync().block();
    }

    /**
     * Get big double value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value -99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Double>> getBigDoubleNegativeDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getBigDoubleNegativeDecimal(this.client.getHost(), accept, context));
    }

    /**
     * Get big double value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value -99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getBigDoubleNegativeDecimalAsync() {
        return getBigDoubleNegativeDecimalWithResponseAsync()
                .flatMap(
                        (Response<Double> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get big double value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value -99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getBigDoubleNegativeDecimal() {
        Double value = getBigDoubleNegativeDecimalAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Put big decimal value 2.5976931e+101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDecimalWithResponseAsync(BigDecimal numberBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (numberBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter numberBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putBigDecimal(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put big decimal value 2.5976931e+101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDecimalAsync(BigDecimal numberBody) {
        return putBigDecimalWithResponseAsync(numberBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big decimal value 2.5976931e+101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBigDecimal(BigDecimal numberBody) {
        putBigDecimalAsync(numberBody).block();
    }

    /**
     * Get big decimal value 2.5976931e+101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value 2.5976931e+101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BigDecimal>> getBigDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getBigDecimal(this.client.getHost(), accept, context));
    }

    /**
     * Get big decimal value 2.5976931e+101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value 2.5976931e+101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getBigDecimalAsync() {
        return getBigDecimalWithResponseAsync()
                .flatMap(
                        (Response<BigDecimal> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get big decimal value 2.5976931e+101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value 2.5976931e+101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BigDecimal getBigDecimal() {
        return getBigDecimalAsync().block();
    }

    /**
     * Put big decimal value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDecimalPositiveDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final BigDecimal numberBody = new BigDecimal("9.999999999E7");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putBigDecimalPositiveDecimal(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put big decimal value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDecimalPositiveDecimalAsync() {
        return putBigDecimalPositiveDecimalWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big decimal value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBigDecimalPositiveDecimal() {
        putBigDecimalPositiveDecimalAsync().block();
    }

    /**
     * Get big decimal value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value 99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BigDecimal>> getBigDecimalPositiveDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getBigDecimalPositiveDecimal(this.client.getHost(), accept, context));
    }

    /**
     * Get big decimal value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value 99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getBigDecimalPositiveDecimalAsync() {
        return getBigDecimalPositiveDecimalWithResponseAsync()
                .flatMap(
                        (Response<BigDecimal> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get big decimal value 99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value 99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BigDecimal getBigDecimalPositiveDecimal() {
        return getBigDecimalPositiveDecimalAsync().block();
    }

    /**
     * Put big decimal value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDecimalNegativeDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final BigDecimal numberBody = new BigDecimal("-9.999999999E7");
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putBigDecimalNegativeDecimal(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put big decimal value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDecimalNegativeDecimalAsync() {
        return putBigDecimalNegativeDecimalWithResponseAsync().flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big decimal value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putBigDecimalNegativeDecimal() {
        putBigDecimalNegativeDecimalAsync().block();
    }

    /**
     * Get big decimal value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value -99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BigDecimal>> getBigDecimalNegativeDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getBigDecimalNegativeDecimal(this.client.getHost(), accept, context));
    }

    /**
     * Get big decimal value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value -99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getBigDecimalNegativeDecimalAsync() {
        return getBigDecimalNegativeDecimalWithResponseAsync()
                .flatMap(
                        (Response<BigDecimal> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get big decimal value -99999999.99.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value -99999999.99.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BigDecimal getBigDecimalNegativeDecimal() {
        return getBigDecimalNegativeDecimalAsync().block();
    }

    /**
     * Put small float value 3.402823e-20.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putSmallFloatWithResponseAsync(float numberBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putSmallFloat(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put small float value 3.402823e-20.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putSmallFloatAsync(float numberBody) {
        return putSmallFloatWithResponseAsync(numberBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put small float value 3.402823e-20.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putSmallFloat(float numberBody) {
        putSmallFloatAsync(numberBody).block();
    }

    /**
     * Get big double value 3.402823e-20.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 3.402823e-20.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Double>> getSmallFloatWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getSmallFloat(this.client.getHost(), accept, context));
    }

    /**
     * Get big double value 3.402823e-20.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 3.402823e-20.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getSmallFloatAsync() {
        return getSmallFloatWithResponseAsync()
                .flatMap(
                        (Response<Double> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get big double value 3.402823e-20.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 3.402823e-20.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getSmallFloat() {
        Double value = getSmallFloatAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Put small double value 2.5976931e-101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putSmallDoubleWithResponseAsync(double numberBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putSmallDouble(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put small double value 2.5976931e-101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putSmallDoubleAsync(double numberBody) {
        return putSmallDoubleWithResponseAsync(numberBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put small double value 2.5976931e-101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putSmallDouble(double numberBody) {
        putSmallDoubleAsync(numberBody).block();
    }

    /**
     * Get big double value 2.5976931e-101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 2.5976931e-101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Double>> getSmallDoubleWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getSmallDouble(this.client.getHost(), accept, context));
    }

    /**
     * Get big double value 2.5976931e-101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 2.5976931e-101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getSmallDoubleAsync() {
        return getSmallDoubleWithResponseAsync()
                .flatMap(
                        (Response<Double> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get big double value 2.5976931e-101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 2.5976931e-101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getSmallDouble() {
        Double value = getSmallDoubleAsync().block();
        if (value != null) {
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Put small decimal value 2.5976931e-101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putSmallDecimalWithResponseAsync(BigDecimal numberBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (numberBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter numberBody is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putSmallDecimal(this.client.getHost(), numberBody, accept, context));
    }

    /**
     * Put small decimal value 2.5976931e-101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putSmallDecimalAsync(BigDecimal numberBody) {
        return putSmallDecimalWithResponseAsync(numberBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put small decimal value 2.5976931e-101.
     *
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putSmallDecimal(BigDecimal numberBody) {
        putSmallDecimalAsync(numberBody).block();
    }

    /**
     * Get small decimal value 2.5976931e-101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return small decimal value 2.5976931e-101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BigDecimal>> getSmallDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getSmallDecimal(this.client.getHost(), accept, context));
    }

    /**
     * Get small decimal value 2.5976931e-101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return small decimal value 2.5976931e-101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getSmallDecimalAsync() {
        return getSmallDecimalWithResponseAsync()
                .flatMap(
                        (Response<BigDecimal> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get small decimal value 2.5976931e-101.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return small decimal value 2.5976931e-101.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BigDecimal getSmallDecimal() {
        return getSmallDecimalAsync().block();
    }
}
