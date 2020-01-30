package fixtures.bodycomplex;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
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
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestServiceBasics")
    private interface BasicsService {
        @Get("/complex/basic/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getValid(@HostParam("$host") String host);

        @Put("/complex/basic/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(@HostParam("$host") String host, @QueryParam("api-version") String apiVersion, @BodyParam("application/json") Basic complexBody);

        @Get("/complex/basic/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getInvalid(@HostParam("$host") String host);

        @Get("/complex/basic/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getEmpty(@HostParam("$host") String host);

        @Get("/complex/basic/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getNull(@HostParam("$host") String host);

        @Get("/complex/basic/notprovided")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Basic>> getNotProvided(@HostParam("$host") String host);
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getValid(this.client.getHost());
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getValidAsync() {
        return getValidWithResponseAsync()
            .flatMap((SimpleResponse<Basic> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getValid() {
        return getValidAsync().block();
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Basic complexBody) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (complexBody == null) {
            throw new IllegalArgumentException("Parameter complexBody is required and cannot be null.");
        } else {
            complexBody.validate();
        }
        return service.putValid(this.client.getHost(), this.client.getApiVersion(), complexBody);
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Basic complexBody) {
        return putValidWithResponseAsync(complexBody)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * 
     * @param complexBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(Basic complexBody) {
        putValidAsync(complexBody).block();
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getInvalidWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getInvalid(this.client.getHost());
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getInvalidAsync() {
        return getInvalidWithResponseAsync()
            .flatMap((SimpleResponse<Basic> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getInvalid() {
        return getInvalidAsync().block();
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getEmpty(this.client.getHost());
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getEmptyAsync() {
        return getEmptyWithResponseAsync()
            .flatMap((SimpleResponse<Basic> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get a basic complex type that is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getEmpty() {
        return getEmptyAsync().block();
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getNull(this.client.getHost());
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getNullAsync() {
        return getNullWithResponseAsync()
            .flatMap((SimpleResponse<Basic> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get a basic complex type whose properties are null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getNull() {
        return getNullAsync().block();
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Basic>> getNotProvidedWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getNotProvided(this.client.getHost());
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Basic> getNotProvidedAsync() {
        return getNotProvidedWithResponseAsync()
            .flatMap((SimpleResponse<Basic> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Basic getNotProvided() {
        return getNotProvidedAsync().block();
    }
}
