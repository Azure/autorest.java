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
import fixtures.bodycomplex.models.DotFish;
import fixtures.bodycomplex.models.DotFishMarket;
import fixtures.bodycomplex.models.ErrorException;
import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.Salmon;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Polymorphisms.
 */
public final class Polymorphisms {
    /**
     * The proxy service used to perform REST calls.
     */
    private PolymorphismsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestComplexTestService client;

    /**
     * Initializes an instance of Polymorphisms.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Polymorphisms(AutoRestComplexTestService client) {
        this.service = RestProxy.create(PolymorphismsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServicePolymorphisms to be used by the proxy service
     * to perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestComplexTestServicePolymorphisms")
    private interface PolymorphismsService {
        @Get("/complex/polymorphism/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(Fish.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Fish>> getValid();

        @Put("/complex/polymorphism/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@BodyParam("application/json") Fish ComplexBody);

        @Get("/complex/polymorphism/dotsyntax")
        @ExpectedResponses({200})
        @ReturnValueWireType(DotFish.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DotFish>> getDotSyntax();

        @Get("/complex/polymorphism/composedWithDiscriminator")
        @ExpectedResponses({200})
        @ReturnValueWireType(DotFishMarket.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DotFishMarket>> getComposedWithDiscriminator();

        @Get("/complex/polymorphism/composedWithoutDiscriminator")
        @ExpectedResponses({200})
        @ReturnValueWireType(DotFishMarket.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DotFishMarket>> getComposedWithoutDiscriminator();

        @Get("/complex/polymorphism/complicated")
        @ExpectedResponses({200})
        @ReturnValueWireType(Salmon.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Salmon>> getComplicated();

        @Put("/complex/polymorphism/complicated")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putComplicated(@BodyParam("application/json") Salmon ComplexBody);

        @Put("/complex/polymorphism/missingdiscriminator")
        @ExpectedResponses({200})
        @ReturnValueWireType(Salmon.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Salmon>> putMissingDiscriminator(@BodyParam("application/json") Salmon ComplexBody);

        @Put("/complex/polymorphism/missingrequired/invalid")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValidMissingRequired(@BodyParam("application/json") Fish ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Fish>> getValidWithResponseAsync(String Host) {
        return service.getValid();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(String Host, Fish ComplexBody) {
        return service.putValid(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DotFish>> getDotSyntaxWithResponseAsync(String Host) {
        return service.getDotSyntax();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DotFishMarket>> getComposedWithDiscriminatorWithResponseAsync(String Host) {
        return service.getComposedWithDiscriminator();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DotFishMarket>> getComposedWithoutDiscriminatorWithResponseAsync(String Host) {
        return service.getComposedWithoutDiscriminator();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Salmon>> getComplicatedWithResponseAsync(String Host) {
        return service.getComplicated();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplicatedWithResponseAsync(String Host, Salmon ComplexBody) {
        return service.putComplicated(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Salmon>> putMissingDiscriminatorWithResponseAsync(String Host, Salmon ComplexBody) {
        return service.putMissingDiscriminator(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidMissingRequiredWithResponseAsync(String Host, Fish ComplexBody) {
        return service.putValidMissingRequired(ComplexBody);
    }
}
