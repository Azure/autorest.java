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
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.implementation.RestProxy;
import fixtures.httpinfrastructure.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * HttpSuccess.
 */
public final class HttpSuccess {
    /**
     * The proxy service used to perform REST calls.
     */
    private HttpSuccessService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpSuccess.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public HttpSuccess(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(HttpSuccessService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHttpInfrastructureTestServiceHttpSuccess to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastructureTestServiceHttpSuccess")
    private interface HttpSuccessService {
        @Head("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head200(@HostParam("$host") String host);

        @Get("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Boolean>> get200(@HostParam("$host") String host);

        @Put("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put200(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Patch("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch200(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Post("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post200(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Delete("/http/success/200")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete200(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Put("/http/success/201")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put201(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Post("/http/success/201")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post201(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Put("/http/success/202")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put202(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Patch("/http/success/202")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch202(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Post("/http/success/202")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post202(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Delete("/http/success/202")
        @ExpectedResponses({202})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete202(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Head("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head204(@HostParam("$host") String host);

        @Put("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put204(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Patch("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch204(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Post("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post204(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Delete("/http/success/204")
        @ExpectedResponses({204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete204(@HostParam("$host") String host, @BodyParam("application/json") boolean booleanValue);

        @Head("/http/success/404")
        @ExpectedResponses({204, 404})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head404(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head200WithResponseAsync() {
        return service.head200(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head200Async() {
        return head200WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head200() {
        head200Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Boolean>> get200WithResponseAsync() {
        return service.get200(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Boolean> get200Async() {
        return get200WithResponseAsync()
            .flatMap((SimpleResponse<Boolean> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean get200() {
        return get200Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put200WithResponseAsync() {
        final boolean booleanValue = true;
        return service.put200(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put200Async() {
        return put200WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put200() {
        put200Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch200WithResponseAsync() {
        final boolean booleanValue = true;
        return service.patch200(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch200Async() {
        return patch200WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch200() {
        patch200Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post200WithResponseAsync() {
        final boolean booleanValue = true;
        return service.post200(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post200Async() {
        return post200WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post200() {
        post200Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete200WithResponseAsync() {
        final boolean booleanValue = true;
        return service.delete200(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete200Async() {
        return delete200WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete200() {
        delete200Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put201WithResponseAsync() {
        final boolean booleanValue = true;
        return service.put201(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put201Async() {
        return put201WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put201() {
        put201Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post201WithResponseAsync() {
        final boolean booleanValue = true;
        return service.post201(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post201Async() {
        return post201WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post201() {
        post201Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put202WithResponseAsync() {
        final boolean booleanValue = true;
        return service.put202(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put202Async() {
        return put202WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put202() {
        put202Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch202WithResponseAsync() {
        final boolean booleanValue = true;
        return service.patch202(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch202Async() {
        return patch202WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch202() {
        patch202Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post202WithResponseAsync() {
        final boolean booleanValue = true;
        return service.post202(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post202Async() {
        return post202WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202() {
        post202Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete202WithResponseAsync() {
        final boolean booleanValue = true;
        return service.delete202(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete202Async() {
        return delete202WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete202() {
        delete202Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head204WithResponseAsync() {
        return service.head204(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head204Async() {
        return head204WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head204() {
        head204Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put204WithResponseAsync() {
        final boolean booleanValue = true;
        return service.put204(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put204Async() {
        return put204WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put204() {
        put204Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch204WithResponseAsync() {
        final boolean booleanValue = true;
        return service.patch204(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch204Async() {
        return patch204WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch204() {
        patch204Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post204WithResponseAsync() {
        final boolean booleanValue = true;
        return service.post204(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post204Async() {
        return post204WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post204() {
        post204Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete204WithResponseAsync() {
        final boolean booleanValue = true;
        return service.delete204(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete204Async() {
        return delete204WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete204() {
        delete204Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head404WithResponseAsync() {
        return service.head404(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head404Async() {
        return head404WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head404() {
        head404Async().block();
    }
}
