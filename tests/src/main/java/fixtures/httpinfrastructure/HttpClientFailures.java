package fixtures.httpinfrastructure;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Head;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import fixtures.httpinfrastructure.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * HttpClientFailures.
 */
public final class HttpClientFailures {
    /**
     * The proxy service used to perform REST calls.
     */
    private HttpClientFailuresService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpClientFailures.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public HttpClientFailures(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(HttpClientFailuresService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHttpInfrastructureTestServiceHttpClientFailures to be used by
     * the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastructureTestServiceHttpClientFailures")
    private interface HttpClientFailuresService {
        @Head("/http/failure/client/400")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head400(@HostParam("$host") String host);

        @Get("/http/failure/client/400")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get400(@HostParam("$host") String host);

        @Put("/http/failure/client/400")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put400(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Patch("/http/failure/client/400")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch400(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Post("/http/failure/client/400")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post400(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Delete("/http/failure/client/400")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete400(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Head("/http/failure/client/401")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head401(@HostParam("$host") String host);

        @Get("/http/failure/client/402")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get402(@HostParam("$host") String host);

        @Get("/http/failure/client/403")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get403(@HostParam("$host") String host);

        @Put("/http/failure/client/404")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put404(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Patch("/http/failure/client/405")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch405(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Post("/http/failure/client/406")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post406(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Delete("/http/failure/client/407")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete407(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Put("/http/failure/client/409")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put409(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Head("/http/failure/client/410")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head410(@HostParam("$host") String host);

        @Get("/http/failure/client/411")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get411(@HostParam("$host") String host);

        @Get("/http/failure/client/412")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get412(@HostParam("$host") String host);

        @Put("/http/failure/client/413")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put413(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Patch("/http/failure/client/414")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch414(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Post("/http/failure/client/415")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post415(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Get("/http/failure/client/416")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get416(@HostParam("$host") String host);

        @Delete("/http/failure/client/417")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete417(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Head("/http/failure/client/429")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head429(@HostParam("$host") String host);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head400WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.head400(this.client.getHost());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head400Async() {
        return head400WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head400() {
        head400Async().block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get400WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.get400(this.client.getHost());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get400Async() {
        return get400WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get400() {
        get400Async().block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put400WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.put400(this.client.getHost(), booleanValue);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put400Async() {
        return put400WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put400() {
        put400Async().block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch400WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.patch400(this.client.getHost(), booleanValue);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch400Async() {
        return patch400WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch400() {
        patch400Async().block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post400WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.post400(this.client.getHost(), booleanValue);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post400Async() {
        return post400WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post400() {
        post400Async().block();
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete400WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.delete400(this.client.getHost(), booleanValue);
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete400Async() {
        return delete400WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 400 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete400() {
        delete400Async().block();
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head401WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.head401(this.client.getHost());
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head401Async() {
        return head401WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 401 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head401() {
        head401Async().block();
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get402WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.get402(this.client.getHost());
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get402Async() {
        return get402WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 402 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get402() {
        get402Async().block();
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get403WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.get403(this.client.getHost());
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get403Async() {
        return get403WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 403 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get403() {
        get403Async().block();
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put404WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.put404(this.client.getHost(), booleanValue);
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put404Async() {
        return put404WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 404 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put404() {
        put404Async().block();
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch405WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.patch405(this.client.getHost(), booleanValue);
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch405Async() {
        return patch405WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 405 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch405() {
        patch405Async().block();
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post406WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.post406(this.client.getHost(), booleanValue);
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post406Async() {
        return post406WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 406 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post406() {
        post406Async().block();
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete407WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.delete407(this.client.getHost(), booleanValue);
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete407Async() {
        return delete407WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 407 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete407() {
        delete407Async().block();
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put409WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.put409(this.client.getHost(), booleanValue);
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put409Async() {
        return put409WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 409 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put409() {
        put409Async().block();
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head410WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.head410(this.client.getHost());
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head410Async() {
        return head410WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 410 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head410() {
        head410Async().block();
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get411WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.get411(this.client.getHost());
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get411Async() {
        return get411WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 411 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get411() {
        get411Async().block();
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get412WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.get412(this.client.getHost());
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get412Async() {
        return get412WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 412 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get412() {
        get412Async().block();
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put413WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.put413(this.client.getHost(), booleanValue);
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put413Async() {
        return put413WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 413 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put413() {
        put413Async().block();
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch414WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.patch414(this.client.getHost(), booleanValue);
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch414Async() {
        return patch414WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 414 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch414() {
        patch414Async().block();
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post415WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.post415(this.client.getHost(), booleanValue);
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post415Async() {
        return post415WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 415 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post415() {
        post415Async().block();
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get416WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.get416(this.client.getHost());
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get416Async() {
        return get416WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 416 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get416() {
        get416Async().block();
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete417WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final Boolean booleanValue = true;
        return service.delete417(this.client.getHost(), booleanValue);
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete417Async() {
        return delete417WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 417 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete417() {
        delete417Async().block();
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head429WithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.head429(this.client.getHost());
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head429Async() {
        return head429WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Return 429 status code - should be represented in the client as an error.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head429() {
        head429Async().block();
    }
}
