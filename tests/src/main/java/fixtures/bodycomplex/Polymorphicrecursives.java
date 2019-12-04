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
import fixtures.bodycomplex.models.ErrorException;
import fixtures.bodycomplex.models.Fish;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Polymorphicrecursives.
 */
public final class Polymorphicrecursives {
    /**
     * The proxy service used to perform REST calls.
     */
    private PolymorphicrecursivesService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestComplexTestService client;

    /**
     * Initializes an instance of Polymorphicrecursives.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Polymorphicrecursives(AutoRestComplexTestService client) {
        this.service = RestProxy.create(PolymorphicrecursivesService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServicePolymorphicrecursives to be used by the proxy
     * service to perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestComplexTestServicePolymorphicrecursives")
    private interface PolymorphicrecursivesService {
        @Get("complex/polymorphicrecursive/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(Fish.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Fish>> getValid();

        @Put("complex/polymorphicrecursive/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@BodyParam("application/json") Fish ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Fish>> getValidWithResponseAsync() {
        return service.getValid();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Fish ComplexBody) {
        return service.putValid(ComplexBody);
    }
}
