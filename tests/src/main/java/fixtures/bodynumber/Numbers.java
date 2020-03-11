package fixtures.bodynumber;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import fixtures.bodynumber.models.ErrorException;
import java.math.BigDecimal;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Numbers.
 */
public final class Numbers {
    /**
     * The proxy service used to perform REST calls.
     */
    private NumbersService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestNumberTestService client;

    /**
     * Initializes an instance of Numbers.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Numbers(AutoRestNumberTestService client) {
        this.service = RestProxy.create(NumbersService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestNumberTestServiceNumbers to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestNumberTestServiceNumbers")
    private interface NumbersService {
        @Get("/number/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Float>> getNull(@HostParam("$host") String host);

        @Get("/number/invalidfloat")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Float>> getInvalidFloat(@HostParam("$host") String host);

        @Get("/number/invaliddouble")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Double>> getInvalidDouble(@HostParam("$host") String host);

        @Get("/number/invaliddecimal")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<BigDecimal>> getInvalidDecimal(@HostParam("$host") String host);

        @Put("/number/big/float/3.402823e+20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigFloat(@HostParam("$host") String host, @BodyParam("application/json") float numberBody);

        @Get("/number/big/float/3.402823e+20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Float>> getBigFloat(@HostParam("$host") String host);

        @Put("/number/big/double/2.5976931e+101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDouble(@HostParam("$host") String host, @BodyParam("application/json") double numberBody);

        @Get("/number/big/double/2.5976931e+101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Double>> getBigDouble(@HostParam("$host") String host);

        @Put("/number/big/double/99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDoublePositiveDecimal(@HostParam("$host") String host, @BodyParam("application/json") double numberBody);

        @Get("/number/big/double/99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Double>> getBigDoublePositiveDecimal(@HostParam("$host") String host);

        @Put("/number/big/double/-99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDoubleNegativeDecimal(@HostParam("$host") String host, @BodyParam("application/json") double numberBody);

        @Get("/number/big/double/-99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Double>> getBigDoubleNegativeDecimal(@HostParam("$host") String host);

        @Put("/number/big/decimal/2.5976931e+101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDecimal(@HostParam("$host") String host, @BodyParam("application/json") BigDecimal numberBody);

        @Get("/number/big/decimal/2.5976931e+101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<BigDecimal>> getBigDecimal(@HostParam("$host") String host);

        @Put("/number/big/decimal/99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDecimalPositiveDecimal(@HostParam("$host") String host, @BodyParam("application/json") BigDecimal numberBody);

        @Get("/number/big/decimal/99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<BigDecimal>> getBigDecimalPositiveDecimal(@HostParam("$host") String host);

        @Put("/number/big/decimal/-99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putBigDecimalNegativeDecimal(@HostParam("$host") String host, @BodyParam("application/json") BigDecimal numberBody);

        @Get("/number/big/decimal/-99999999.99")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<BigDecimal>> getBigDecimalNegativeDecimal(@HostParam("$host") String host);

        @Put("/number/small/float/3.402823e-20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putSmallFloat(@HostParam("$host") String host, @BodyParam("application/json") float numberBody);

        @Get("/number/small/float/3.402823e-20")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Double>> getSmallFloat(@HostParam("$host") String host);

        @Put("/number/small/double/2.5976931e-101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putSmallDouble(@HostParam("$host") String host, @BodyParam("application/json") double numberBody);

        @Get("/number/small/double/2.5976931e-101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Double>> getSmallDouble(@HostParam("$host") String host);

        @Put("/number/small/decimal/2.5976931e-101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putSmallDecimal(@HostParam("$host") String host, @BodyParam("application/json") BigDecimal numberBody);

        @Get("/number/small/decimal/2.5976931e-101")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<BigDecimal>> getSmallDecimal(@HostParam("$host") String host);
    }

    /**
     * Get null Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Float>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getNull(this.client.getHost());
    }

    /**
     * Get null Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Float> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<Float> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public float getNull() {
        return getNullAsync().block();
    }

    /**
     * Get invalid float Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Float>> getInvalidFloatWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getInvalidFloat(this.client.getHost());
    }

    /**
     * Get invalid float Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Float> getInvalidFloatAsync() {
        return getInvalidFloatWithResponseAsync()
            .flatMap((SimpleResponse<Float> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public float getInvalidFloat() {
        return getInvalidFloatAsync().block();
    }

    /**
     * Get invalid double Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Double>> getInvalidDoubleWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getInvalidDouble(this.client.getHost());
    }

    /**
     * Get invalid double Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getInvalidDoubleAsync() {
        return getInvalidDoubleWithResponseAsync()
            .flatMap((SimpleResponse<Double> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getInvalidDouble() {
        return getInvalidDoubleAsync().block();
    }

    /**
     * Get invalid decimal Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BigDecimal>> getInvalidDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getInvalidDecimal(this.client.getHost());
    }

    /**
     * Get invalid decimal Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getInvalidDecimalAsync() {
        return getInvalidDecimalWithResponseAsync()
            .flatMap((SimpleResponse<BigDecimal> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BigDecimal getInvalidDecimal() {
        return getInvalidDecimalAsync().block();
    }

    /**
     * Put big float value 3.402823e+20.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigFloatWithResponseAsync(float numberBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putBigFloat(this.client.getHost(), numberBody);
    }

    /**
     * Put big float value 3.402823e+20.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigFloatAsync(float numberBody) {
        return putBigFloatWithResponseAsync(numberBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big float value 3.402823e+20.
     * 
     * @param numberBody 
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Float>> getBigFloatWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getBigFloat(this.client.getHost());
    }

    /**
     * Get big float value 3.402823e+20.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Float> getBigFloatAsync() {
        return getBigFloatWithResponseAsync()
            .flatMap((SimpleResponse<Float> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public float getBigFloat() {
        return getBigFloatAsync().block();
    }

    /**
     * Put big double value 2.5976931e+101.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDoubleWithResponseAsync(double numberBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putBigDouble(this.client.getHost(), numberBody);
    }

    /**
     * Put big double value 2.5976931e+101.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDoubleAsync(double numberBody) {
        return putBigDoubleWithResponseAsync(numberBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big double value 2.5976931e+101.
     * 
     * @param numberBody 
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Double>> getBigDoubleWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getBigDouble(this.client.getHost());
    }

    /**
     * Get big double value 2.5976931e+101.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getBigDoubleAsync() {
        return getBigDoubleWithResponseAsync()
            .flatMap((SimpleResponse<Double> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getBigDouble() {
        return getBigDoubleAsync().block();
    }

    /**
     * Put big double value 99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDoublePositiveDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final double numberBody = 9.999999999E7;
        return service.putBigDoublePositiveDecimal(this.client.getHost(), numberBody);
    }

    /**
     * Put big double value 99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDoublePositiveDecimalAsync() {
        return putBigDoublePositiveDecimalWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Double>> getBigDoublePositiveDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getBigDoublePositiveDecimal(this.client.getHost());
    }

    /**
     * Get big double value 99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getBigDoublePositiveDecimalAsync() {
        return getBigDoublePositiveDecimalWithResponseAsync()
            .flatMap((SimpleResponse<Double> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getBigDoublePositiveDecimal() {
        return getBigDoublePositiveDecimalAsync().block();
    }

    /**
     * Put big double value -99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDoubleNegativeDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final double numberBody = -9.999999999E7;
        return service.putBigDoubleNegativeDecimal(this.client.getHost(), numberBody);
    }

    /**
     * Put big double value -99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDoubleNegativeDecimalAsync() {
        return putBigDoubleNegativeDecimalWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Double>> getBigDoubleNegativeDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getBigDoubleNegativeDecimal(this.client.getHost());
    }

    /**
     * Get big double value -99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getBigDoubleNegativeDecimalAsync() {
        return getBigDoubleNegativeDecimalWithResponseAsync()
            .flatMap((SimpleResponse<Double> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getBigDoubleNegativeDecimal() {
        return getBigDoubleNegativeDecimalAsync().block();
    }

    /**
     * Put big decimal value 2.5976931e+101.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDecimalWithResponseAsync(BigDecimal numberBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (numberBody == null) {
            throw new IllegalArgumentException("Parameter numberBody is required and cannot be null.");
        }
        return service.putBigDecimal(this.client.getHost(), numberBody);
    }

    /**
     * Put big decimal value 2.5976931e+101.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDecimalAsync(BigDecimal numberBody) {
        return putBigDecimalWithResponseAsync(numberBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put big decimal value 2.5976931e+101.
     * 
     * @param numberBody 
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BigDecimal>> getBigDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getBigDecimal(this.client.getHost());
    }

    /**
     * Get big decimal value 2.5976931e+101.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getBigDecimalAsync() {
        return getBigDecimalWithResponseAsync()
            .flatMap((SimpleResponse<BigDecimal> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDecimalPositiveDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final BigDecimal numberBody = new BigDecimal("9.999999999E7");
        return service.putBigDecimalPositiveDecimal(this.client.getHost(), numberBody);
    }

    /**
     * Put big decimal value 99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDecimalPositiveDecimalAsync() {
        return putBigDecimalPositiveDecimalWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BigDecimal>> getBigDecimalPositiveDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getBigDecimalPositiveDecimal(this.client.getHost());
    }

    /**
     * Get big decimal value 99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getBigDecimalPositiveDecimalAsync() {
        return getBigDecimalPositiveDecimalWithResponseAsync()
            .flatMap((SimpleResponse<BigDecimal> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putBigDecimalNegativeDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final BigDecimal numberBody = new BigDecimal("-9.999999999E7");
        return service.putBigDecimalNegativeDecimal(this.client.getHost(), numberBody);
    }

    /**
     * Put big decimal value -99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putBigDecimalNegativeDecimalAsync() {
        return putBigDecimalNegativeDecimalWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BigDecimal>> getBigDecimalNegativeDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getBigDecimalNegativeDecimal(this.client.getHost());
    }

    /**
     * Get big decimal value -99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getBigDecimalNegativeDecimalAsync() {
        return getBigDecimalNegativeDecimalWithResponseAsync()
            .flatMap((SimpleResponse<BigDecimal> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BigDecimal getBigDecimalNegativeDecimal() {
        return getBigDecimalNegativeDecimalAsync().block();
    }

    /**
     * Put small float value 3.402823e-20.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putSmallFloatWithResponseAsync(float numberBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putSmallFloat(this.client.getHost(), numberBody);
    }

    /**
     * Put small float value 3.402823e-20.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putSmallFloatAsync(float numberBody) {
        return putSmallFloatWithResponseAsync(numberBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put small float value 3.402823e-20.
     * 
     * @param numberBody 
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Double>> getSmallFloatWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getSmallFloat(this.client.getHost());
    }

    /**
     * Get big double value 3.402823e-20.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getSmallFloatAsync() {
        return getSmallFloatWithResponseAsync()
            .flatMap((SimpleResponse<Double> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getSmallFloat() {
        return getSmallFloatAsync().block();
    }

    /**
     * Put small double value 2.5976931e-101.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putSmallDoubleWithResponseAsync(double numberBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putSmallDouble(this.client.getHost(), numberBody);
    }

    /**
     * Put small double value 2.5976931e-101.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putSmallDoubleAsync(double numberBody) {
        return putSmallDoubleWithResponseAsync(numberBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put small double value 2.5976931e-101.
     * 
     * @param numberBody 
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Double>> getSmallDoubleWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getSmallDouble(this.client.getHost());
    }

    /**
     * Get big double value 2.5976931e-101.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Double> getSmallDoubleAsync() {
        return getSmallDoubleWithResponseAsync()
            .flatMap((SimpleResponse<Double> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public double getSmallDouble() {
        return getSmallDoubleAsync().block();
    }

    /**
     * Put small decimal value 2.5976931e-101.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putSmallDecimalWithResponseAsync(BigDecimal numberBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (numberBody == null) {
            throw new IllegalArgumentException("Parameter numberBody is required and cannot be null.");
        }
        return service.putSmallDecimal(this.client.getHost(), numberBody);
    }

    /**
     * Put small decimal value 2.5976931e-101.
     * 
     * @param numberBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putSmallDecimalAsync(BigDecimal numberBody) {
        return putSmallDecimalWithResponseAsync(numberBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put small decimal value 2.5976931e-101.
     * 
     * @param numberBody 
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<BigDecimal>> getSmallDecimalWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getSmallDecimal(this.client.getHost());
    }

    /**
     * Get small decimal value 2.5976931e-101.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BigDecimal> getSmallDecimalAsync() {
        return getSmallDecimalWithResponseAsync()
            .flatMap((SimpleResponse<BigDecimal> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BigDecimal getSmallDecimal() {
        return getSmallDecimalAsync().block();
    }
}
