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
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestComplexTestServiceDictionarys")
    private interface DictionarysService {
        @Get("/complex/dictionary/typed/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(DictionaryWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DictionaryWrapper>> getValid();

        @Put("/complex/dictionary/typed/valid")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@BodyParam("application/json") DictionaryWrapper ComplexBody);

        @Get("/complex/dictionary/typed/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(DictionaryWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DictionaryWrapper>> getEmpty();

        @Put("/complex/dictionary/typed/empty")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@BodyParam("application/json") DictionaryWrapper ComplexBody);

        @Get("/complex/dictionary/typed/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(DictionaryWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DictionaryWrapper>> getNull();

        @Get("/complex/dictionary/typed/notprovided")
        @ExpectedResponses({200})
        @ReturnValueWireType(DictionaryWrapper.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<DictionaryWrapper>> getNotProvided();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DictionaryWrapper>> getValidWithResponseAsync() {
        return service.getValid();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(DictionaryWrapper ComplexBody) {
        return service.putValid(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DictionaryWrapper>> getEmptyWithResponseAsync() {
        return service.getEmpty();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(DictionaryWrapper ComplexBody) {
        return service.putEmpty(ComplexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DictionaryWrapper>> getNullWithResponseAsync() {
        return service.getNull();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<DictionaryWrapper>> getNotProvidedWithResponseAsync() {
        return service.getNotProvided();
    }
}
