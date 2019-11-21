package fixtures.bodystring;

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
import fixtures.bodystring.models.Colors;
import fixtures.bodystring.models.ErrorException;
import fixtures.bodystring.models.RefColorConstant;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Enums.
 */
public final class Enums {
    /**
     * The proxy service used to perform REST calls.
     */
    private EnumsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestSwaggerBATService client;

    /**
     * Initializes an instance of Enums.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Enums(AutoRestSwaggerBATService client) {
        this.service = RestProxy.create(EnumsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBATServiceEnums to be used by the proxy service to
     * perform REST calls.
     */
    @Host("http://localhost:3000")
    @ServiceInterface(name = "AutoRestSwaggerBATServiceEnums")
    private interface EnumsService {
        @Get("/string/enum/notExpandable")
        @ExpectedResponses({200})
        @ReturnValueWireType(Colors.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Colors>> getNotExpandable();

        @Put("/string/enum/notExpandable")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putNotExpandable(@BodyParam("application/json") Colors StringBody);

        @Get("/string/enum/Referenced")
        @ExpectedResponses({200})
        @ReturnValueWireType(Colors.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Colors>> getReferenced();

        @Put("/string/enum/Referenced")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putReferenced(@BodyParam("application/json") Colors EnumStringBody);

        @Get("/string/enum/ReferencedConstant")
        @ExpectedResponses({200})
        @ReturnValueWireType(RefColorConstant.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<RefColorConstant>> getReferencedConstant();

        @Put("/string/enum/ReferencedConstant")
        @ExpectedResponses({200})
        @ReturnValueWireType(void.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putReferencedConstant(@BodyParam("application/json") RefColorConstant EnumStringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Colors>> getNotExpandableWithResponseAsync() {
        return service.getNotExpandable();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNotExpandableWithResponseAsync(Colors StringBody) {
        return service.putNotExpandable(StringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Colors>> getReferencedWithResponseAsync() {
        return service.getReferenced();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedWithResponseAsync(Colors EnumStringBody) {
        return service.putReferenced(EnumStringBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<RefColorConstant>> getReferencedConstantWithResponseAsync() {
        return service.getReferencedConstant();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedConstantWithResponseAsync(RefColorConstant EnumStringBody) {
        return service.putReferencedConstant(EnumStringBody);
    }
}
