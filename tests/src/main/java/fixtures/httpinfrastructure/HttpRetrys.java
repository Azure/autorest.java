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
 * HttpRetrys.
 */
public final class HttpRetrys {
    /**
     * The proxy service used to perform REST calls.
     */
    private HttpRetrysService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpRetrys.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public HttpRetrys(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(HttpRetrysService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHttpInfrastructureTestServiceHttpRetrys to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastructureTestServiceHttpRetrys")
    private interface HttpRetrysService {
        @Head("/http/retry/408")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> head408(@HostParam("$host") String host);

        @Put("/http/retry/500")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put500(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Patch("/http/retry/500")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch500(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Get("/http/retry/502")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get502(@HostParam("$host") String host);

        @Post("/http/retry/503")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> post503(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Delete("/http/retry/503")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> delete503(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Put("/http/retry/504")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> put504(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Patch("/http/retry/504")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> patch504(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> head408WithResponseAsync() {
        return service.head408(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head408Async() {
        return head408WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head408() {
        head408Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put500WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.put500(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put500Async() {
        return put500WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put500() {
        put500Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch500WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.patch500(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch500Async() {
        return patch500WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch500() {
        patch500Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get502WithResponseAsync() {
        return service.get502(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get502Async() {
        return get502WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get502() {
        get502Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> post503WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.post503(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post503Async() {
        return post503WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post503() {
        post503Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> delete503WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.delete503(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete503Async() {
        return delete503WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete503() {
        delete503Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> put504WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.put504(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put504Async() {
        return put504WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put504() {
        put504Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> patch504WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.patch504(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch504Async() {
        return patch504WithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch504() {
        patch504Async().block();
    }
}
