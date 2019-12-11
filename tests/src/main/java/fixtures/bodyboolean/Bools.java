package fixtures.bodyboolean;

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
import fixtures.bodyboolean.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Bools.
 */
public final class Bools {
    /**
     * The proxy service used to perform REST calls.
     */
    private BoolsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestBoolTestService client;

    /**
     * Initializes an instance of Bools.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Bools(AutoRestBoolTestService client) {
        this.service = RestProxy.create(BoolsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestBoolTestServiceBools
     * to be used by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestBoolTestServiceBools")
    private interface BoolsService {
        @Get("/bool/true")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getTrue(@HostParam("$host") String host);

        @Put("/bool/true")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putTrue(@HostParam("$host") String host, @BodyParam("application/json") boolean boolBody);

        @Get("/bool/false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getFalse(@HostParam("$host") String host);

        @Put("/bool/false")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFalse(@HostParam("$host") String host, @BodyParam("application/json") boolean boolBody);

        @Get("/bool/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getNull(@HostParam("$host") String host);

        @Get("/bool/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getInvalid(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getTrueWithResponseAsync() {
        return service.getTrue(this.client.getHost());
    }

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

    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getTrue() {
        return getTrueAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putTrueWithResponseAsync() {
        final boolean boolBody = true;
        return service.putTrue(this.client.getHost(), boolBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putTrueAsync() {
        return putTrueWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putTrue() {
        putTrueAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getFalseWithResponseAsync() {
        return service.getFalse(this.client.getHost());
    }

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

    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getFalse() {
        return getFalseAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFalseWithResponseAsync() {
        final boolean boolBody = false;
        return service.putFalse(this.client.getHost(), boolBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putFalseAsync() {
        return putFalseWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putFalse() {
        putFalseAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getNullWithResponseAsync() {
        return service.getNull(this.client.getHost());
    }

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

    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getNull() {
        return getNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getInvalidWithResponseAsync() {
        return service.getInvalid(this.client.getHost());
    }

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

    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean getInvalid() {
        return getInvalidAsync().block();
    }
}
