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
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServiceArrays")
    private interface ArraysService {
        @Get("/complex/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ArrayWrapper>> getValid(@HostParam("$host") String host);

        @Put("/complex/array/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String host, @BodyParam("application/json") ArrayWrapper complexBody);

        @Get("/complex/array/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ArrayWrapper>> getEmpty(@HostParam("$host") String host);

        @Put("/complex/array/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(@HostParam("$host") String host, @BodyParam("application/json") ArrayWrapper complexBody);

        @Get("/complex/array/notprovided")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ArrayWrapper>> getNotProvided(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ArrayWrapper>> getValidWithResponseAsync() {
        return service.getValid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ArrayWrapper> getValidAsync() {
        return getValidWithResponseAsync()
            .flatMap((SimpleResponse<ArrayWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public ArrayWrapper getValid() {
        return getValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(ArrayWrapper complexBody) {
        return service.putValid(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(ArrayWrapper complexBody) {
        return putValidWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(ArrayWrapper complexBody) {
        putValidAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ArrayWrapper>> getEmptyWithResponseAsync() {
        return service.getEmpty(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ArrayWrapper> getEmptyAsync() {
        return getEmptyWithResponseAsync()
            .flatMap((SimpleResponse<ArrayWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public ArrayWrapper getEmpty() {
        return getEmptyAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(ArrayWrapper complexBody) {
        return service.putEmpty(this.client.getHost(), complexBody);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(ArrayWrapper complexBody) {
        return putEmptyWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(ArrayWrapper complexBody) {
        putEmptyAsync(complexBody).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ArrayWrapper>> getNotProvidedWithResponseAsync() {
        return service.getNotProvided(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ArrayWrapper> getNotProvidedAsync() {
        return getNotProvidedWithResponseAsync()
            .flatMap((SimpleResponse<ArrayWrapper> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public ArrayWrapper getNotProvided() {
        return getNotProvidedAsync().block();
    }
}
