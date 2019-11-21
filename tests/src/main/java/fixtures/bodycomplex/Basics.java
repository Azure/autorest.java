package fixtures.bodycomplex;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ReturnValueWireType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.implementation.RestProxy;
import fixtures.bodycomplex.models.Basic;
import fixtures.bodycomplex.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Basics.
 */
public final class Basics {
    /**
     * The proxy service used to perform REST calls.
     */
    private BasicsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestComplexTestService client;

    /**
     * Initializes an instance of Basics.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Basics(AutoRestComplexTestService client) {
        this.service = RestProxy.create(BasicsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServiceBasics to be used by the proxy service to
     * perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestComplexTestServiceBasics")
    private interface BasicsService {
        @Get("/complex/basic/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(Basic.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getValid();

        @Put("/complex/basic/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@QueryParam("ApiVersion") String ApiVersion, @BodyParam("application/json") Basic ComplexBody);

        @Get("/complex/basic/invalid")
        @ExpectedResponses({200})
        @ReturnValueWireType(Basic.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getInvalid();

        @Get("/complex/basic/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(Basic.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getEmpty();

        @Get("/complex/basic/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(Basic.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getNull();

        @Get("/complex/basic/notprovided")
        @ExpectedResponses({200})
        @ReturnValueWireType(Basic.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getNotProvided();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getValidWithResponseAsync(String Host) {
        return service.getValid();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(String Host, String ApiVersion, Basic ComplexBody) {
        return service.putValid(this.client.getApiVersion(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getInvalidWithResponseAsync(String Host) {
        return service.getInvalid();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getEmptyWithResponseAsync(String Host) {
        return service.getEmpty();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getNullWithResponseAsync(String Host) {
        return service.getNull();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getNotProvidedWithResponseAsync(String Host) {
        return service.getNotProvided();
    }
}
