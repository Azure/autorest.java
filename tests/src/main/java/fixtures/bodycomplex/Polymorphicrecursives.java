package fixtures.bodycomplex;

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
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServicePolymorphicrecursives")
    private interface PolymorphicrecursivesService {
        @Get("/complex/polymorphicrecursive/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Fish>> getValid(@HostParam("$host") String host);

        @Put("/complex/polymorphicrecursive/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String host, @BodyParam("application/json") Fish complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Fish>> getValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getValidAsync() {
        return getValidWithResponseAsync()
            .flatMap((SimpleResponse<Fish> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Fish getValid() {
        return getValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Fish complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putValid(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Fish complexBody) {
        return putValidWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(Fish complexBody) {
        putValidAsync(complexBody).block();
    }
}
