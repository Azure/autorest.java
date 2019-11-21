package fixtures.bodycomplex;

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
import fixtures.bodycomplex.models.ArrayWrapper;
import fixtures.bodycomplex.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Arrays.
 */
public final class Arrays {
    /**
     * The proxy service used to perform REST calls.
     */
    private ArraysService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestComplexTestService client;

    /**
     * Initializes an instance of Arrays.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Arrays(AutoRestComplexTestService client) {
        this.service = RestProxy.create(ArraysService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServiceArrays to be used by the proxy service to
     * perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestComplexTestServiceArrays")
    private interface ArraysService {
        @Get("/complex/array/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(ArrayWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ArrayWrapper>> getValid();

        @Put("/complex/array/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@BodyParam("application/json") ArrayWrapper ComplexBody);

        @Get("/complex/array/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(ArrayWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ArrayWrapper>> getEmpty();

        @Put("/complex/array/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@BodyParam("application/json") ArrayWrapper ComplexBody);

        @Get("/complex/array/notprovided")
        @ExpectedResponses({200})
        @ReturnValueWireType(ArrayWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ArrayWrapper>> getNotProvided();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ArrayWrapper>> getValidWithResponseAsync(String Host) {
        return service.getValid();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(String Host, ArrayWrapper ComplexBody) {
        return service.putValid(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ArrayWrapper>> getEmptyWithResponseAsync(String Host) {
        return service.getEmpty();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(String Host, ArrayWrapper ComplexBody) {
        return service.putEmpty(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ArrayWrapper>> getNotProvidedWithResponseAsync(String Host) {
        return service.getNotProvided();
    }
}
