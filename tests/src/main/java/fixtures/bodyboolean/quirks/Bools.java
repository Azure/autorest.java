package fixtures.bodyboolean.quirks;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ReturnValueWireType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.implementation.RestProxy;
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
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestBoolTestServiceBools")
    private interface BoolsService {
        @Get("/bool/true")
        @ExpectedResponses({200})
        @ReturnValueWireType(boolean.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getTrue();

        @Put("/bool/true")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putTrue(@BodyParam("application/json") boolean BoolBody);

        @Get("/bool/false")
        @ExpectedResponses({200})
        @ReturnValueWireType(boolean.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getFalse();

        @Put("/bool/false")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putFalse(@BodyParam("application/json") boolean BoolBody);

        @Get("/bool/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(boolean.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getNull();

        @Get("/bool/invalid")
        @ExpectedResponses({200})
        @ReturnValueWireType(boolean.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> getInvalid();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getTrueWithResponseAsync() {
        return service.getTrue();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putTrueWithResponseAsync(boolean BoolBody) {
        return service.putTrue(BoolBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getFalseWithResponseAsync() {
        return service.getFalse();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putFalseWithResponseAsync(boolean BoolBody) {
        return service.putFalse(BoolBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getNullWithResponseAsync() {
        return service.getNull();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> getInvalidWithResponseAsync() {
        return service.getInvalid();
    }
}
