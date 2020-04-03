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
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
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
    private final HttpRedirectsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of HttpRedirects.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    HttpRedirects(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(HttpRedirectsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHttpInfrastructureTestServiceHttpRedirects to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastr")
    private interface HttpRedirectsService {
        @Head("/http/redirect/300")
        @ExpectedResponses({200, 300})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsHead300Response> head300(@HostParam("$host") String host, Context context);

        @Get("/http/redirect/300")
        @ExpectedResponses({200, 300})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsGet300Response> get300(@HostParam("$host") String host, Context context);

        @Head("/http/redirect/301")
        @ExpectedResponses({200, 301})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsHead301Response> head301(@HostParam("$host") String host, Context context);

        @Get("/http/redirect/301")
        @ExpectedResponses({200, 301})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsGet301Response> get301(@HostParam("$host") String host, Context context);

        @Put("/http/redirect/301")
        @ExpectedResponses({301})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPut301Response> put301(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Head("/http/redirect/302")
        @ExpectedResponses({200, 302})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsHead302Response> head302(@HostParam("$host") String host, Context context);

        @Get("/http/redirect/302")
        @ExpectedResponses({200, 302})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsGet302Response> get302(@HostParam("$host") String host, Context context);

        @Patch("/http/redirect/302")
        @ExpectedResponses({302})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPatch302Response> patch302(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Post("/http/redirect/303")
        @ExpectedResponses({200, 303})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPost303Response> post303(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Head("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsHead307Response> head307(@HostParam("$host") String host, Context context);

        @Get("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsGet307Response> get307(@HostParam("$host") String host, Context context);

        @Put("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPut307Response> put307(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Patch("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPatch307Response> patch307(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Post("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsPost307Response> post307(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);

        @Delete("/http/redirect/307")
        @ExpectedResponses({200, 307})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<HttpRedirectsDelete307Response> delete307(@HostParam("$host") String host, @BodyParam("application/json") Boolean booleanValue, Context context);
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsHead300Response> head300WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head300(this.client.getHost(), context));
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head300Async() {
        return head300WithResponseAsync()
            .flatMap((HttpRedirectsHead300Response res) -> Mono.empty());
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head300() {
        head300Async().block();
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsGet300Response> get300WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.get300(this.client.getHost(), context));
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
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

    /**
     * Return 300 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<String> get300() {
        return get300Async().block();
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsHead301Response> head301WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head301(this.client.getHost(), context));
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head301Async() {
        return head301WithResponseAsync()
            .flatMap((HttpRedirectsHead301Response res) -> Mono.empty());
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head301() {
        head301Async().block();
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsGet301Response> get301WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.get301(this.client.getHost(), context));
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get301Async() {
        return get301WithResponseAsync()
            .flatMap((HttpRedirectsGet301Response res) -> Mono.empty());
    }

    /**
     * Return 301 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get301() {
        get301Async().block();
    }

    /**
     * Put true Boolean value in request returns 301.  This request should not be automatically redirected, but should return the received 301 to the caller for evaluation.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPut301Response> put301WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.put301(this.client.getHost(), booleanValue, context));
    }

    /**
     * Put true Boolean value in request returns 301.  This request should not be automatically redirected, but should return the received 301 to the caller for evaluation.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put301Async() {
        return put301WithResponseAsync()
            .flatMap((HttpRedirectsPut301Response res) -> Mono.empty());
    }

    /**
     * Put true Boolean value in request returns 301.  This request should not be automatically redirected, but should return the received 301 to the caller for evaluation.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put301() {
        put301Async().block();
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsHead302Response> head302WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head302(this.client.getHost(), context));
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head302Async() {
        return head302WithResponseAsync()
            .flatMap((HttpRedirectsHead302Response res) -> Mono.empty());
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head302() {
        head302Async().block();
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsGet302Response> get302WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.get302(this.client.getHost(), context));
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get302Async() {
        return get302WithResponseAsync()
            .flatMap((HttpRedirectsGet302Response res) -> Mono.empty());
    }

    /**
     * Return 302 status code and redirect to /http/success/200.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get302() {
        get302Async().block();
    }

    /**
     * Patch true Boolean value in request returns 302.  This request should not be automatically redirected, but should return the received 302 to the caller for evaluation.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPatch302Response> patch302WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.patch302(this.client.getHost(), booleanValue, context));
    }

    /**
     * Patch true Boolean value in request returns 302.  This request should not be automatically redirected, but should return the received 302 to the caller for evaluation.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch302Async() {
        return patch302WithResponseAsync()
            .flatMap((HttpRedirectsPatch302Response res) -> Mono.empty());
    }

    /**
     * Patch true Boolean value in request returns 302.  This request should not be automatically redirected, but should return the received 302 to the caller for evaluation.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch302() {
        patch302Async().block();
    }

    /**
     * Post true Boolean value in request returns 303.  This request should be automatically redirected usign a get, ultimately returning a 200 status code.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPost303Response> post303WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.post303(this.client.getHost(), booleanValue, context));
    }

    /**
     * Post true Boolean value in request returns 303.  This request should be automatically redirected usign a get, ultimately returning a 200 status code.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post303Async() {
        return post303WithResponseAsync()
            .flatMap((HttpRedirectsPost303Response res) -> Mono.empty());
    }

    /**
     * Post true Boolean value in request returns 303.  This request should be automatically redirected usign a get, ultimately returning a 200 status code.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post303() {
        post303Async().block();
    }

    /**
     * Redirect with 307, resulting in a 200 success.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsHead307Response> head307WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.head307(this.client.getHost(), context));
    }

    /**
     * Redirect with 307, resulting in a 200 success.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> head307Async() {
        return head307WithResponseAsync()
            .flatMap((HttpRedirectsHead307Response res) -> Mono.empty());
    }

    /**
     * Redirect with 307, resulting in a 200 success.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head307() {
        head307Async().block();
    }

    /**
     * Redirect get with 307, resulting in a 200 success.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsGet307Response> get307WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.get307(this.client.getHost(), context));
    }

    /**
     * Redirect get with 307, resulting in a 200 success.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get307Async() {
        return get307WithResponseAsync()
            .flatMap((HttpRedirectsGet307Response res) -> Mono.empty());
    }

    /**
     * Redirect get with 307, resulting in a 200 success.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get307() {
        get307Async().block();
    }

    /**
     * Put redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPut307Response> put307WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.put307(this.client.getHost(), booleanValue, context));
    }

    /**
     * Put redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> put307Async() {
        return put307WithResponseAsync()
            .flatMap((HttpRedirectsPut307Response res) -> Mono.empty());
    }

    /**
     * Put redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put307() {
        put307Async().block();
    }

    /**
     * Patch redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPatch307Response> patch307WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.patch307(this.client.getHost(), booleanValue, context));
    }

    /**
     * Patch redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> patch307Async() {
        return patch307WithResponseAsync()
            .flatMap((HttpRedirectsPatch307Response res) -> Mono.empty());
    }

    /**
     * Patch redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch307() {
        patch307Async().block();
    }

    /**
     * Post redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsPost307Response> post307WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.post307(this.client.getHost(), booleanValue, context));
    }

    /**
     * Post redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> post307Async() {
        return post307WithResponseAsync()
            .flatMap((HttpRedirectsPost307Response res) -> Mono.empty());
    }

    /**
     * Post redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post307() {
        post307Async().block();
    }

    /**
     * Delete redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<HttpRedirectsDelete307Response> delete307WithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final Boolean booleanValue = true;
        return FluxUtil.withContext(context -> service.delete307(this.client.getHost(), booleanValue, context));
    }

    /**
     * Delete redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> delete307Async() {
        return delete307WithResponseAsync()
            .flatMap((HttpRedirectsDelete307Response res) -> Mono.empty());
    }

    /**
     * Delete redirected with 307, resulting in a 200 after redirect.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete307() {
        delete307Async().block();
    }
}
