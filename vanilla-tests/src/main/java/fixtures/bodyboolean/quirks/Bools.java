package fixtures.bodyboolean.quirks;

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
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodyboolean.quirks.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Bools.
 */
public final class Bools {
    /**
     * The proxy service used to perform REST calls.
     */
    private final BoolsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestBoolTestService client;

    /**
     * Initializes an instance of Bools.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Bools(AutoRestBoolTestService client) {
        this.service = RestProxy.create(BoolsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestBoolTestServiceBools
     * to be used by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestBoolTestServ")
    private interface BoolsService {
        @Get("/bool/true")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getTrue(@HostParam("$host") String host, Context context);

        @Put("/bool/true")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putTrue(@HostParam("$host") String host, @BodyParam("application/json") boolean boolBody, Context context);

        @Get("/bool/false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getFalse(@HostParam("$host") String host, Context context);

        @Put("/bool/false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFalse(@HostParam("$host") String host, @BodyParam("application/json") boolean boolBody, Context context);

        @Get("/bool/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getNull(@HostParam("$host") String host, Context context);

        @Get("/bool/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getInvalid(@HostParam("$host") String host, Context context);
    }

    /**
     * Get true Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getTrueWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getTrue(this.client.getHost(), context));
    }

    /**
     * Get true Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getTrueAsync() {
        return getTrueWithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get true Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getTrue() {
        Boolean value = getTrueAsync().block();
        return value == null ? false : value;
    }

    /**
     * Set Boolean value true.
     * 
     * @param boolBody The boolBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putTrueWithResponseAsync(boolean boolBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putTrue(this.client.getHost(), boolBody, context));
    }

    /**
     * Set Boolean value true.
     * 
     * @param boolBody The boolBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putTrueAsync(boolean boolBody) {
        return putTrueWithResponseAsync(boolBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set Boolean value true.
     * 
     * @param boolBody The boolBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putTrue(boolean boolBody) {
        putTrueAsync(boolBody).block();
    }

    /**
     * Get false Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getFalseWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getFalse(this.client.getHost(), context));
    }

    /**
     * Get false Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getFalseAsync() {
        return getFalseWithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get false Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getFalse() {
        Boolean value = getFalseAsync().block();
        return value == null ? false : value;
    }

    /**
     * Set Boolean value false.
     * 
     * @param boolBody The boolBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFalseWithResponseAsync(boolean boolBody) {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.putFalse(this.client.getHost(), boolBody, context));
    }

    /**
     * Set Boolean value false.
     * 
     * @param boolBody The boolBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFalseAsync(boolean boolBody) {
        return putFalseWithResponseAsync(boolBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Set Boolean value false.
     * 
     * @param boolBody The boolBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFalse(boolean boolBody) {
        putFalseAsync(boolBody).block();
    }

    /**
     * Get null Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), context));
    }

    /**
     * Get null Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get null Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getNull() {
        Boolean value = getNullAsync().block();
        return value == null ? false : value;
    }

    /**
     * Get invalid Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getInvalid(this.client.getHost(), context));
    }

    /**
     * Get invalid Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> getInvalidAsync() {
        return getInvalidWithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get invalid Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getInvalid() {
        Boolean value = getInvalidAsync().block();
        return value == null ? false : value;
    }
}
