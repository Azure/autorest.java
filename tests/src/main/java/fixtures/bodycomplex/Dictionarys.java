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
import fixtures.bodycomplex.models.DictionaryWrapper;
import fixtures.bodycomplex.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Dictionarys.
 */
public final class Dictionarys {
    /**
     * The proxy service used to perform REST calls.
     */
    private DictionarysService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestComplexTestService client;

    /**
     * Initializes an instance of Dictionarys.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Dictionarys(AutoRestComplexTestService client) {
        this.service = RestProxy.create(DictionarysService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServiceDictionarys to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServiceDictionarys")
    private interface DictionarysService {
        @Get("/complex/dictionary/typed/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(DictionaryWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DictionaryWrapper>> getValid(@HostParam("$host") String Host);

        @Put("/complex/dictionary/typed/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String Host, @BodyParam("application/json") DictionaryWrapper ComplexBody);

        @Get("/complex/dictionary/typed/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(DictionaryWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DictionaryWrapper>> getEmpty(@HostParam("$host") String Host);

        @Put("/complex/dictionary/typed/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@HostParam("$host") String Host, @BodyParam("application/json") DictionaryWrapper ComplexBody);

        @Get("/complex/dictionary/typed/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(DictionaryWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DictionaryWrapper>> getNull(@HostParam("$host") String Host);

        @Get("/complex/dictionary/typed/notprovided")
        @ExpectedResponses({200})
        @ReturnValueWireType(DictionaryWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DictionaryWrapper>> getNotProvided(@HostParam("$host") String Host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DictionaryWrapper>> getValidWithResponseAsync() {
        return service.getValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getValidAsync() {
        return getValidWithResponseAsync()
            .flatMap((SimpleResponse<DictionaryWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getValid() {
        return getValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(DictionaryWrapper ComplexBody) {
        return service.putValid(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(DictionaryWrapper ComplexBody) {
        return putValidWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(DictionaryWrapper ComplexBody) {
        putValidAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DictionaryWrapper>> getEmptyWithResponseAsync() {
        return service.getEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getEmptyAsync() {
        return getEmptyWithResponseAsync()
            .flatMap((SimpleResponse<DictionaryWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getEmpty() {
        return getEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(DictionaryWrapper ComplexBody) {
        return service.putEmpty(this.client.getHost(), ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(DictionaryWrapper ComplexBody) {
        return putEmptyWithResponseAsync(ComplexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(DictionaryWrapper ComplexBody) {
        putEmptyAsync(ComplexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DictionaryWrapper>> getNullWithResponseAsync() {
        return service.getNull(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<DictionaryWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getNull() {
        return getNullAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DictionaryWrapper>> getNotProvidedWithResponseAsync() {
        return service.getNotProvided(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNotProvidedAsync() {
        return getNotProvidedWithResponseAsync()
            .flatMap((SimpleResponse<DictionaryWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getNotProvided() {
        return getNotProvidedAsync().block();
    }
}
