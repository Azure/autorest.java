package fixtures.httpinfrastructure;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import fixtures.httpinfrastructure.models.ErrorException;
import fixtures.httpinfrastructure.models.MyException;
import fixtures.httpinfrastructure.models.MyExceptionException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * MultipleResponses.
 */
public final class MultipleResponses {
    /**
     * The proxy service used to perform REST calls.
     */
    private MultipleResponsesService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestHttpInfrastructureTestService client;

    /**
     * Initializes an instance of MultipleResponses.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public MultipleResponses(AutoRestHttpInfrastructureTestService client) {
        this.service = RestProxy.create(MultipleResponsesService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestHttpInfrastructureTestServiceMultipleResponses to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestHttpInfrastructureTestServiceMultipleResponses")
    private interface MultipleResponsesService {
        @Get("/http/payloads/200/A/204/none/default/Error/response/200/valid")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError200Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/204/none/default/Error/response/204/none")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError204Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/204/none/default/Error/response/201/valid")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError201Invalid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/204/none/default/Error/response/202/none")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError202None(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/204/none/default/Error/response/400/valid")
        @ExpectedResponses({200, 204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError400Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/201/B/default/Error/response/200/valid")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<MyException>> get200Model201ModelDefaultError200Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/201/B/default/Error/response/201/valid")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<MyException>> get200Model201ModelDefaultError201Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/201/B/default/Error/response/400/valid")
        @ExpectedResponses({200, 201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<MyException>> get200Model201ModelDefaultError400Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/201/C/404/D/default/Error/response/200/valid")
        @ExpectedResponses({200, 201, 404})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Object>> get200ModelA201ModelC404ModelDDefaultError200Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/201/C/404/D/default/Error/response/201/valid")
        @ExpectedResponses({200, 201, 404})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Object>> get200ModelA201ModelC404ModelDDefaultError201Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/201/C/404/D/default/Error/response/404/valid")
        @ExpectedResponses({200, 201, 404})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Object>> get200ModelA201ModelC404ModelDDefaultError404Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/201/C/404/D/default/Error/response/400/valid")
        @ExpectedResponses({200, 201, 404})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Object>> get200ModelA201ModelC404ModelDDefaultError400Valid(@HostParam("$host") String host);

        @Get("/http/payloads/202/none/204/none/default/Error/response/202/none")
        @ExpectedResponses({202, 204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get202None204NoneDefaultError202None(@HostParam("$host") String host);

        @Get("/http/payloads/202/none/204/none/default/Error/response/204/none")
        @ExpectedResponses({202, 204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get202None204NoneDefaultError204None(@HostParam("$host") String host);

        @Get("/http/payloads/202/none/204/none/default/Error/response/400/valid")
        @ExpectedResponses({202, 204})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> get202None204NoneDefaultError400Valid(@HostParam("$host") String host);

        @Get("/http/payloads/202/none/204/none/default/none/response/202/invalid")
        @ExpectedResponses({202, 204})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> get202None204NoneDefaultNone202Invalid(@HostParam("$host") String host);

        @Get("/http/payloads/202/none/204/none/default/none/response/204/none")
        @ExpectedResponses({202, 204})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> get202None204NoneDefaultNone204None(@HostParam("$host") String host);

        @Get("/http/payloads/202/none/204/none/default/none/response/400/none")
        @ExpectedResponses({202, 204})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> get202None204NoneDefaultNone400None(@HostParam("$host") String host);

        @Get("/http/payloads/202/none/204/none/default/none/response/400/invalid")
        @ExpectedResponses({202, 204})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> get202None204NoneDefaultNone400Invalid(@HostParam("$host") String host);

        @Get("/http/payloads/default/A/response/200/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyException>> getDefaultModelA200Valid(@HostParam("$host") String host);

        @Get("/http/payloads/default/A/response/200/none")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyException>> getDefaultModelA200None(@HostParam("$host") String host);

        @Get("/http/payloads/default/A/response/400/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(MyExceptionException.class)
        Mono<Response<Void>> getDefaultModelA400Valid(@HostParam("$host") String host);

        @Get("/http/payloads/default/A/response/400/none")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(MyExceptionException.class)
        Mono<Response<Void>> getDefaultModelA400None(@HostParam("$host") String host);

        @Get("/http/payloads/default/none/response/200/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> getDefaultNone200Invalid(@HostParam("$host") String host);

        @Get("/http/payloads/default/none/response/200/none")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> getDefaultNone200None(@HostParam("$host") String host);

        @Get("/http/payloads/default/none/response/400/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> getDefaultNone400Invalid(@HostParam("$host") String host);

        @Get("/http/payloads/default/none/response/400/none")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> getDefaultNone400None(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/response/200/none")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyException>> get200ModelA200None(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/response/200/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyException>> get200ModelA200Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/response/200/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyException>> get200ModelA200Invalid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/response/400/none")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyException>> get200ModelA400None(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/response/400/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyException>> get200ModelA400Valid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/response/400/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyException>> get200ModelA400Invalid(@HostParam("$host") String host);

        @Get("/http/payloads/200/A/response/202/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<MyException>> get200ModelA202Valid(@HostParam("$host") String host);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError200ValidWithResponseAsync() {
        return service.get200Model204NoModelDefaultError200Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200Model204NoModelDefaultError200ValidAsync() {
        return get200Model204NoModelDefaultError200ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200Model204NoModelDefaultError200Valid() {
        return get200Model204NoModelDefaultError200ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError204ValidWithResponseAsync() {
        return service.get200Model204NoModelDefaultError204Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200Model204NoModelDefaultError204ValidAsync() {
        return get200Model204NoModelDefaultError204ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200Model204NoModelDefaultError204Valid() {
        return get200Model204NoModelDefaultError204ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError201InvalidWithResponseAsync() {
        return service.get200Model204NoModelDefaultError201Invalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200Model204NoModelDefaultError201InvalidAsync() {
        return get200Model204NoModelDefaultError201InvalidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200Model204NoModelDefaultError201Invalid() {
        return get200Model204NoModelDefaultError201InvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError202NoneWithResponseAsync() {
        return service.get200Model204NoModelDefaultError202None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200Model204NoModelDefaultError202NoneAsync() {
        return get200Model204NoModelDefaultError202NoneWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200Model204NoModelDefaultError202None() {
        return get200Model204NoModelDefaultError202NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200Model204NoModelDefaultError400ValidWithResponseAsync() {
        return service.get200Model204NoModelDefaultError400Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200Model204NoModelDefaultError400ValidAsync() {
        return get200Model204NoModelDefaultError400ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200Model204NoModelDefaultError400Valid() {
        return get200Model204NoModelDefaultError400ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200Model201ModelDefaultError200ValidWithResponseAsync() {
        return service.get200Model201ModelDefaultError200Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200Model201ModelDefaultError200ValidAsync() {
        return get200Model201ModelDefaultError200ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200Model201ModelDefaultError200Valid() {
        return get200Model201ModelDefaultError200ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200Model201ModelDefaultError201ValidWithResponseAsync() {
        return service.get200Model201ModelDefaultError201Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200Model201ModelDefaultError201ValidAsync() {
        return get200Model201ModelDefaultError201ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200Model201ModelDefaultError201Valid() {
        return get200Model201ModelDefaultError201ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200Model201ModelDefaultError400ValidWithResponseAsync() {
        return service.get200Model201ModelDefaultError400Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200Model201ModelDefaultError400ValidAsync() {
        return get200Model201ModelDefaultError400ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200Model201ModelDefaultError400Valid() {
        return get200Model201ModelDefaultError400ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Object>> get200ModelA201ModelC404ModelDDefaultError200ValidWithResponseAsync() {
        return service.get200ModelA201ModelC404ModelDDefaultError200Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> get200ModelA201ModelC404ModelDDefaultError200ValidAsync() {
        return get200ModelA201ModelC404ModelDDefaultError200ValidWithResponseAsync()
            .flatMap((SimpleResponse<Object> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Object get200ModelA201ModelC404ModelDDefaultError200Valid() {
        return get200ModelA201ModelC404ModelDDefaultError200ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Object>> get200ModelA201ModelC404ModelDDefaultError201ValidWithResponseAsync() {
        return service.get200ModelA201ModelC404ModelDDefaultError201Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> get200ModelA201ModelC404ModelDDefaultError201ValidAsync() {
        return get200ModelA201ModelC404ModelDDefaultError201ValidWithResponseAsync()
            .flatMap((SimpleResponse<Object> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Object get200ModelA201ModelC404ModelDDefaultError201Valid() {
        return get200ModelA201ModelC404ModelDDefaultError201ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Object>> get200ModelA201ModelC404ModelDDefaultError404ValidWithResponseAsync() {
        return service.get200ModelA201ModelC404ModelDDefaultError404Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> get200ModelA201ModelC404ModelDDefaultError404ValidAsync() {
        return get200ModelA201ModelC404ModelDDefaultError404ValidWithResponseAsync()
            .flatMap((SimpleResponse<Object> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Object get200ModelA201ModelC404ModelDDefaultError404Valid() {
        return get200ModelA201ModelC404ModelDDefaultError404ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Object>> get200ModelA201ModelC404ModelDDefaultError400ValidWithResponseAsync() {
        return service.get200ModelA201ModelC404ModelDDefaultError400Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Object> get200ModelA201ModelC404ModelDDefaultError400ValidAsync() {
        return get200ModelA201ModelC404ModelDDefaultError400ValidWithResponseAsync()
            .flatMap((SimpleResponse<Object> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Object get200ModelA201ModelC404ModelDDefaultError400Valid() {
        return get200ModelA201ModelC404ModelDDefaultError400ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError202NoneWithResponseAsync() {
        return service.get202None204NoneDefaultError202None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultError202NoneAsync() {
        return get202None204NoneDefaultError202NoneWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultError202None() {
        get202None204NoneDefaultError202NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError204NoneWithResponseAsync() {
        return service.get202None204NoneDefaultError204None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultError204NoneAsync() {
        return get202None204NoneDefaultError204NoneWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultError204None() {
        get202None204NoneDefaultError204NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError400ValidWithResponseAsync() {
        return service.get202None204NoneDefaultError400Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultError400ValidAsync() {
        return get202None204NoneDefaultError400ValidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultError400Valid() {
        get202None204NoneDefaultError400ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone202InvalidWithResponseAsync() {
        return service.get202None204NoneDefaultNone202Invalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone202InvalidAsync() {
        return get202None204NoneDefaultNone202InvalidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone202Invalid() {
        get202None204NoneDefaultNone202InvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone204NoneWithResponseAsync() {
        return service.get202None204NoneDefaultNone204None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone204NoneAsync() {
        return get202None204NoneDefaultNone204NoneWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone204None() {
        get202None204NoneDefaultNone204NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone400NoneWithResponseAsync() {
        return service.get202None204NoneDefaultNone400None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone400NoneAsync() {
        return get202None204NoneDefaultNone400NoneWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone400None() {
        get202None204NoneDefaultNone400NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone400InvalidWithResponseAsync() {
        return service.get202None204NoneDefaultNone400Invalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> get202None204NoneDefaultNone400InvalidAsync() {
        return get202None204NoneDefaultNone400InvalidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone400Invalid() {
        get202None204NoneDefaultNone400InvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> getDefaultModelA200ValidWithResponseAsync() {
        return service.getDefaultModelA200Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> getDefaultModelA200ValidAsync() {
        return getDefaultModelA200ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException getDefaultModelA200Valid() {
        return getDefaultModelA200ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> getDefaultModelA200NoneWithResponseAsync() {
        return service.getDefaultModelA200None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> getDefaultModelA200NoneAsync() {
        return getDefaultModelA200NoneWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException getDefaultModelA200None() {
        return getDefaultModelA200NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultModelA400ValidWithResponseAsync() {
        return service.getDefaultModelA400Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultModelA400ValidAsync() {
        return getDefaultModelA400ValidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultModelA400Valid() {
        getDefaultModelA400ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultModelA400NoneWithResponseAsync() {
        return service.getDefaultModelA400None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultModelA400NoneAsync() {
        return getDefaultModelA400NoneWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultModelA400None() {
        getDefaultModelA400NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone200InvalidWithResponseAsync() {
        return service.getDefaultNone200Invalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone200InvalidAsync() {
        return getDefaultNone200InvalidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone200Invalid() {
        getDefaultNone200InvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone200NoneWithResponseAsync() {
        return service.getDefaultNone200None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone200NoneAsync() {
        return getDefaultNone200NoneWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone200None() {
        getDefaultNone200NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone400InvalidWithResponseAsync() {
        return service.getDefaultNone400Invalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone400InvalidAsync() {
        return getDefaultNone400InvalidWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone400Invalid() {
        getDefaultNone400InvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone400NoneWithResponseAsync() {
        return service.getDefaultNone400None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getDefaultNone400NoneAsync() {
        return getDefaultNone400NoneWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone400None() {
        getDefaultNone400NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200ModelA200NoneWithResponseAsync() {
        return service.get200ModelA200None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200ModelA200NoneAsync() {
        return get200ModelA200NoneWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200ModelA200None() {
        return get200ModelA200NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200ModelA200ValidWithResponseAsync() {
        return service.get200ModelA200Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200ModelA200ValidAsync() {
        return get200ModelA200ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200ModelA200Valid() {
        return get200ModelA200ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200ModelA200InvalidWithResponseAsync() {
        return service.get200ModelA200Invalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200ModelA200InvalidAsync() {
        return get200ModelA200InvalidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200ModelA200Invalid() {
        return get200ModelA200InvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200ModelA400NoneWithResponseAsync() {
        return service.get200ModelA400None(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200ModelA400NoneAsync() {
        return get200ModelA400NoneWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200ModelA400None() {
        return get200ModelA400NoneAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200ModelA400ValidWithResponseAsync() {
        return service.get200ModelA400Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200ModelA400ValidAsync() {
        return get200ModelA400ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200ModelA400Valid() {
        return get200ModelA400ValidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200ModelA400InvalidWithResponseAsync() {
        return service.get200ModelA400Invalid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200ModelA400InvalidAsync() {
        return get200ModelA400InvalidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200ModelA400Invalid() {
        return get200ModelA400InvalidAsync().block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<MyException>> get200ModelA202ValidWithResponseAsync() {
        return service.get200ModelA202Valid(this.client.getHost());
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MyException> get200ModelA202ValidAsync() {
        return get200ModelA202ValidWithResponseAsync()
            .flatMap((SimpleResponse<MyException> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public MyException get200ModelA202Valid() {
        return get200ModelA202ValidAsync().block();
    }
}
