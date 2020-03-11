package fixtures.requiredoptional;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import fixtures.requiredoptional.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Implicits.
 */
public final class Implicits {
    /**
     * The proxy service used to perform REST calls.
     */
    private ImplicitsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestRequiredOptionalTestService client;

    /**
     * Initializes an instance of Implicits.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Implicits(AutoRestRequiredOptionalTestService client) {
        this.service = RestProxy.create(ImplicitsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestRequiredOptionalTestServiceImplicits to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestRequiredOptionalTestServiceImplicits")
    private interface ImplicitsService {
        @Get("/reqopt/implicit/required/path/{pathParameter}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getRequiredPath(@HostParam("$host") String host, @PathParam("pathParameter") String pathParameter);

        @Put("/reqopt/implicit/optional/query")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putOptionalQuery(@HostParam("$host") String host, @QueryParam("queryParameter") String queryParameter);

        @Put("/reqopt/implicit/optional/header")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putOptionalHeader(@HostParam("$host") String host, @HeaderParam("queryParameter") String queryParameter);

        @Put("/reqopt/implicit/optional/body")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putOptionalBody(@HostParam("$host") String host, @BodyParam("application/json") String bodyParameter);

        @Get("/reqopt/global/required/path/{required-global-path}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getRequiredGlobalPath(@HostParam("$host") String host, @PathParam("required-global-path") String requiredGlobalPath);

        @Get("/reqopt/global/required/query")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getRequiredGlobalQuery(@HostParam("$host") String host, @QueryParam("required-global-query") String requiredGlobalQuery);

        @Get("/reqopt/global/optional/query")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getOptionalGlobalQuery(@HostParam("$host") String host, @QueryParam("optional-global-query") Integer optionalGlobalQuery);
    }

    /**
     * Test implicitly required path parameter.
     * 
     * @param pathParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getRequiredPathWithResponseAsync(String pathParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (pathParameter == null) {
            throw new IllegalArgumentException("Parameter pathParameter is required and cannot be null.");
        }
        return service.getRequiredPath(this.client.getHost(), pathParameter);
    }

    /**
     * Test implicitly required path parameter.
     * 
     * @param pathParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getRequiredPathAsync(String pathParameter) {
        return getRequiredPathWithResponseAsync(pathParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test implicitly required path parameter.
     * 
     * @param pathParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getRequiredPath(String pathParameter) {
        getRequiredPathAsync(pathParameter).block();
    }

    /**
     * Test implicitly optional query parameter.
     * 
     * @param queryParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putOptionalQueryWithResponseAsync(String queryParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putOptionalQuery(this.client.getHost(), queryParameter);
    }

    /**
     * Test implicitly optional query parameter.
     * 
     * @param queryParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putOptionalQueryAsync(String queryParameter) {
        return putOptionalQueryWithResponseAsync(queryParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test implicitly optional query parameter.
     * 
     * @param queryParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putOptionalQuery(String queryParameter) {
        putOptionalQueryAsync(queryParameter).block();
    }

    /**
     * Test implicitly optional header parameter.
     * 
     * @param queryParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putOptionalHeaderWithResponseAsync(String queryParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putOptionalHeader(this.client.getHost(), queryParameter);
    }

    /**
     * Test implicitly optional header parameter.
     * 
     * @param queryParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putOptionalHeaderAsync(String queryParameter) {
        return putOptionalHeaderWithResponseAsync(queryParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test implicitly optional header parameter.
     * 
     * @param queryParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putOptionalHeader(String queryParameter) {
        putOptionalHeaderAsync(queryParameter).block();
    }

    /**
     * Test implicitly optional body parameter.
     * 
     * @param bodyParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putOptionalBodyWithResponseAsync(String bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.putOptionalBody(this.client.getHost(), bodyParameter);
    }

    /**
     * Test implicitly optional body parameter.
     * 
     * @param bodyParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putOptionalBodyAsync(String bodyParameter) {
        return putOptionalBodyWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test implicitly optional body parameter.
     * 
     * @param bodyParameter 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putOptionalBody(String bodyParameter) {
        putOptionalBodyAsync(bodyParameter).block();
    }

    /**
     * Test implicitly required path parameter.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getRequiredGlobalPathWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (this.client.getRequiredGlobalPath() == null) {
            throw new IllegalArgumentException("Parameter this.client.getRequiredGlobalPath() is required and cannot be null.");
        }
        return service.getRequiredGlobalPath(this.client.getHost(), this.client.getRequiredGlobalPath());
    }

    /**
     * Test implicitly required path parameter.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getRequiredGlobalPathAsync() {
        return getRequiredGlobalPathWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test implicitly required path parameter.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getRequiredGlobalPath() {
        getRequiredGlobalPathAsync().block();
    }

    /**
     * Test implicitly required query parameter.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getRequiredGlobalQueryWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (this.client.getRequiredGlobalQuery() == null) {
            throw new IllegalArgumentException("Parameter this.client.getRequiredGlobalQuery() is required and cannot be null.");
        }
        return service.getRequiredGlobalQuery(this.client.getHost(), this.client.getRequiredGlobalQuery());
    }

    /**
     * Test implicitly required query parameter.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getRequiredGlobalQueryAsync() {
        return getRequiredGlobalQueryWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test implicitly required query parameter.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getRequiredGlobalQuery() {
        getRequiredGlobalQueryAsync().block();
    }

    /**
     * Test implicitly optional query parameter.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getOptionalGlobalQueryWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getOptionalGlobalQuery(this.client.getHost(), this.client.getOptionalGlobalQuery());
    }

    /**
     * Test implicitly optional query parameter.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getOptionalGlobalQueryAsync() {
        return getOptionalGlobalQueryWithResponseAsync()
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test implicitly optional query parameter.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getOptionalGlobalQuery() {
        getOptionalGlobalQueryAsync().block();
    }
}
