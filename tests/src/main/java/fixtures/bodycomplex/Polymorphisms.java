package fixtures.bodycomplex;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
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
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServicePolymorphisms")
    private interface PolymorphismsService {
        @Get("/complex/polymorphism/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(Fish.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Fish>> getValid(@HostParam("$host") String Host);

        @Put("/complex/polymorphism/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String Host, @BodyParam("application/json") Fish ComplexBody);

        @Get("/complex/polymorphism/dotsyntax")
        @ExpectedResponses({200})
        @ReturnValueWireType(DotFish.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DotFish>> getDotSyntax(@HostParam("$host") String Host);

        @Get("/complex/polymorphism/composedWithDiscriminator")
        @ExpectedResponses({200})
        @ReturnValueWireType(DotFishMarket.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DotFishMarket>> getComposedWithDiscriminator(@HostParam("$host") String Host);

        @Get("/complex/polymorphism/composedWithoutDiscriminator")
        @ExpectedResponses({200})
        @ReturnValueWireType(DotFishMarket.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DotFishMarket>> getComposedWithoutDiscriminator(@HostParam("$host") String Host);

        @Get("/complex/polymorphism/complicated")
        @ExpectedResponses({200})
        @ReturnValueWireType(Salmon.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Salmon>> getComplicated(@HostParam("$host") String Host);

        @Put("/complex/polymorphism/complicated")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putComplicated(@HostParam("$host") String Host, @BodyParam("application/json") Salmon ComplexBody);

        @Put("/complex/polymorphism/missingdiscriminator")
        @ExpectedResponses({200})
        @ReturnValueWireType(Salmon.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Salmon>> putMissingDiscriminator(@HostParam("$host") String Host, @BodyParam("application/json") Salmon ComplexBody);

        @Put("/complex/polymorphism/missingrequired/invalid")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValidMissingRequired(@HostParam("$host") String Host, @BodyParam("application/json") Fish ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Fish>> getValidWithResponseAsync() {
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
    public Mono<Response<Void>> putValidWithResponseAsync(Fish ComplexBody) {
        return service.putValid(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Fish ComplexBody) {
        return putValidWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(Fish ComplexBody) {
        putValidAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DotFish>> getDotSyntaxWithResponseAsync() {
        return service.getDotSyntax(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DotFish> getDotSyntaxAsync() {
        return getDotSyntaxWithResponseAsync()
            .flatMap((SimpleResponse<DotFish> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DotFish getDotSyntax() {
        return getDotSyntaxAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DotFishMarket>> getComposedWithDiscriminatorWithResponseAsync() {
        return service.getComposedWithDiscriminator(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DotFishMarket> getComposedWithDiscriminatorAsync() {
        return getComposedWithDiscriminatorWithResponseAsync()
            .flatMap((SimpleResponse<DotFishMarket> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DotFishMarket getComposedWithDiscriminator() {
        return getComposedWithDiscriminatorAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DotFishMarket>> getComposedWithoutDiscriminatorWithResponseAsync() {
        return service.getComposedWithoutDiscriminator(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DotFishMarket> getComposedWithoutDiscriminatorAsync() {
        return getComposedWithoutDiscriminatorWithResponseAsync()
            .flatMap((SimpleResponse<DotFishMarket> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DotFishMarket getComposedWithoutDiscriminator() {
        return getComposedWithoutDiscriminatorAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Salmon>> getComplicatedWithResponseAsync() {
        return service.getComplicated(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Salmon> getComplicatedAsync() {
        return getComplicatedWithResponseAsync()
            .flatMap((SimpleResponse<Salmon> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Salmon getComplicated() {
        return getComplicatedAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplicatedWithResponseAsync(Salmon ComplexBody) {
        return service.putComplicated(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplicatedAsync(Salmon ComplexBody) {
        return putComplicatedWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putComplicated(Salmon ComplexBody) {
        putComplicatedAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Salmon>> putMissingDiscriminatorWithResponseAsync(Salmon ComplexBody) {
        return service.putMissingDiscriminator(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Salmon> putMissingDiscriminatorAsync(Salmon ComplexBody) {
        return putMissingDiscriminatorWithResponseAsync(ComplexBody)
            .flatMap((SimpleResponse<Salmon> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Salmon putMissingDiscriminator(Salmon ComplexBody) {
        return putMissingDiscriminatorAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidMissingRequiredWithResponseAsync(Fish ComplexBody) {
        return service.putValidMissingRequired(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidMissingRequiredAsync(Fish ComplexBody) {
        return putValidMissingRequiredWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValidMissingRequired(Fish ComplexBody) {
        putValidMissingRequiredAsync(ComplexBody).block();
    }
}
