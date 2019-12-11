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
import com.azure.core.http.rest.RestProxy;
import fixtures.httpinfrastructure.models.ErrorException;
import fixtures.httpinfrastructure.models.HttpRedirectsDelete307Response;
import fixtures.httpinfrastructure.models.HttpRedirectsGet300Response;
import fixtures.httpinfrastructure.models.HttpRedirectsGet301Response;
import fixtures.httpinfrastructure.models.HttpRedirectsGet302Response;
import fixtures.httpinfrastructure.models.HttpRedirectsGet307Response;
import fixtures.httpinfrastructure.models.HttpRedirectsHead300Response;
import fixtures.httpinfrastructure.models.HttpRedirectsHead301Response;
import fixtures.httpinfrastructure.models.HttpRedirectsHead302Response;
import fixtures.httpinfrastructure.models.HttpRedirectsHead307Response;
import fixtures.httpinfrastructure.models.HttpRedirectsPatch302Response;
import fixtures.httpinfrastructure.models.HttpRedirectsPatch307Response;
import fixtures.httpinfrastructure.models.HttpRedirectsPost303Response;
import fixtures.httpinfrastructure.models.HttpRedirectsPost307Response;
import fixtures.httpinfrastructure.models.HttpRedirectsPut301Response;
import fixtures.httpinfrastructure.models.HttpRedirectsPut307Response;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * HttpRedirects.
 */
public final class HttpRedirects {
    /**
     * The proxy service used to perform REST calls.
     */
    private HttpRedirectsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpRedirects.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public HttpRedirects(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(HttpRedirectsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHttpInfrastructureTestServiceHttpRedirects to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastructureTestServiceHttpRedirects")
    private interface HttpRedirectsService {
        @Head("/http/redirect/300")
        @ExpectedResponses({200, 300})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsHead300Response> head300(@HostParam("$host") String host);

        @Get("/http/redirect/300")
        @ExpectedResponses({200, 300})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsGet300Response> get300(@HostParam("$host") String host);

        @Head("/http/redirect/301")
        @ExpectedResponses({200, 301})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsHead301Response> head301(@HostParam("$host") String host);

        @Get("/http/redirect/301")
        @ExpectedResponses({200, 301})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsGet301Response> get301(@HostParam("$host") String host);

        @Put("/http/redirect/301")
        @ExpectedResponses({301})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPut301Response> put301(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Head("/http/redirect/302")
        @ExpectedResponses({200, 302})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsHead302Response> head302(@HostParam("$host") String host);

        @Get("/http/redirect/302")
        @ExpectedResponses({200, 302})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsGet302Response> get302(@HostParam("$host") String host);

        @Patch("/http/redirect/302")
        @ExpectedResponses({302})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPatch302Response> patch302(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Post("/http/redirect/303")
        @ExpectedResponses({200, 303})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPost303Response> post303(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Head("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsHead307Response> head307(@HostParam("$host") String host);

        @Get("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsGet307Response> get307(@HostParam("$host") String host);

        @Put("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPut307Response> put307(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Patch("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPatch307Response> patch307(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Post("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPost307Response> post307(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);

        @Delete("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsDelete307Response> delete307(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsHead300Response> head300WithResponseAsync() {
        return service.head300(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head300Async() {
        return head300WithResponseAsync()
            .flatMap((HttpRedirectsHead300Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head300() {
        head300Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsGet300Response> get300WithResponseAsync() {
        return service.get300(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<String>> get300Async() {
        return get300WithResponseAsync()
            .flatMap((HttpRedirectsGet300Response res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<String> get300() {
        return get300Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsHead301Response> head301WithResponseAsync() {
        return service.head301(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head301Async() {
        return head301WithResponseAsync()
            .flatMap((HttpRedirectsHead301Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head301() {
        head301Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsGet301Response> get301WithResponseAsync() {
        return service.get301(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get301Async() {
        return get301WithResponseAsync()
            .flatMap((HttpRedirectsGet301Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get301() {
        get301Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPut301Response> put301WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.put301(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put301Async() {
        return put301WithResponseAsync()
            .flatMap((HttpRedirectsPut301Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put301() {
        put301Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsHead302Response> head302WithResponseAsync() {
        return service.head302(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head302Async() {
        return head302WithResponseAsync()
            .flatMap((HttpRedirectsHead302Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head302() {
        head302Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsGet302Response> get302WithResponseAsync() {
        return service.get302(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get302Async() {
        return get302WithResponseAsync()
            .flatMap((HttpRedirectsGet302Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get302() {
        get302Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPatch302Response> patch302WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.patch302(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch302Async() {
        return patch302WithResponseAsync()
            .flatMap((HttpRedirectsPatch302Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch302() {
        patch302Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPost303Response> post303WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.post303(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post303Async() {
        return post303WithResponseAsync()
            .flatMap((HttpRedirectsPost303Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post303() {
        post303Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsHead307Response> head307WithResponseAsync() {
        return service.head307(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head307Async() {
        return head307WithResponseAsync()
            .flatMap((HttpRedirectsHead307Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head307() {
        head307Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsGet307Response> get307WithResponseAsync() {
        return service.get307(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get307Async() {
        return get307WithResponseAsync()
            .flatMap((HttpRedirectsGet307Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get307() {
        get307Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPut307Response> put307WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.put307(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put307Async() {
        return put307WithResponseAsync()
            .flatMap((HttpRedirectsPut307Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put307() {
        put307Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPatch307Response> patch307WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.patch307(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch307Async() {
        return patch307WithResponseAsync()
            .flatMap((HttpRedirectsPatch307Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch307() {
        patch307Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPost307Response> post307WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.post307(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post307Async() {
        return post307WithResponseAsync()
            .flatMap((HttpRedirectsPost307Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post307() {
        post307Async().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsDelete307Response> delete307WithResponseAsync() {
        final Boolean booleanValue = true;
        return service.delete307(this.client.getHost(), booleanValue);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete307Async() {
        return delete307WithResponseAsync()
            .flatMap((HttpRedirectsDelete307Response res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete307() {
        delete307Async().block();
    }
}
