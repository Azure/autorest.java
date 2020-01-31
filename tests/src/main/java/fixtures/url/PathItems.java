package fixtures.url;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import fixtures.url.models.ErrorException;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * PathItems.
 */
public final class PathItems {
    /**
     * The proxy service used to perform REST calls.
     */
    private PathItemsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestUrlTestService client;

    /**
     * Initializes an instance of PathItems.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public PathItems(AutoRestUrlTestService client) {
        this.service = RestProxy.create(PathItemsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestUrlTestServicePathItems to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestUrlTestServicePathItems")
    private interface PathItemsService {
        @Get("/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/globalStringQuery/pathItemStringQuery/localStringQuery")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getAllWithValues(@HostParam("$host") String host, @PathParam("pathItemStringPath") String pathItemStringPath, @QueryParam("pathItemStringQuery") String pathItemStringQuery, @PathParam("globalStringPath") String globalStringPath, @QueryParam("globalStringQuery") String globalStringQuery, @PathParam("localStringPath") String localStringPath, @QueryParam("localStringQuery") String localStringQuery);

        @Get("/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/null/pathItemStringQuery/localStringQuery")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getGlobalQueryNull(@HostParam("$host") String host, @PathParam("pathItemStringPath") String pathItemStringPath, @QueryParam("pathItemStringQuery") String pathItemStringQuery, @PathParam("globalStringPath") String globalStringPath, @QueryParam("globalStringQuery") String globalStringQuery, @PathParam("localStringPath") String localStringPath, @QueryParam("localStringQuery") String localStringQuery);

        @Get("/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/null/pathItemStringQuery/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getGlobalAndLocalQueryNull(@HostParam("$host") String host, @PathParam("pathItemStringPath") String pathItemStringPath, @QueryParam("pathItemStringQuery") String pathItemStringQuery, @PathParam("globalStringPath") String globalStringPath, @QueryParam("globalStringQuery") String globalStringQuery, @PathParam("localStringPath") String localStringPath, @QueryParam("localStringQuery") String localStringQuery);

        @Get("/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/globalStringQuery/null/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getLocalPathItemQueryNull(@HostParam("$host") String host, @PathParam("pathItemStringPath") String pathItemStringPath, @QueryParam("pathItemStringQuery") String pathItemStringQuery, @PathParam("globalStringPath") String globalStringPath, @QueryParam("globalStringQuery") String globalStringQuery, @PathParam("localStringPath") String localStringPath, @QueryParam("localStringQuery") String localStringQuery);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getAllWithValuesWithResponseAsync(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (pathItemStringPath == null) {
            throw new IllegalArgumentException("Parameter pathItemStringPath is required and cannot be null.");
        }
        if (this.client.getGlobalStringPath() == null) {
            throw new IllegalArgumentException("Parameter this.client.getGlobalStringPath() is required and cannot be null.");
        }
        if (localStringPath == null) {
            throw new IllegalArgumentException("Parameter localStringPath is required and cannot be null.");
        }
        return service.getAllWithValues(this.client.getHost(), pathItemStringPath, pathItemStringQuery, this.client.getGlobalStringPath(), this.client.getGlobalStringQuery(), localStringPath, localStringQuery);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getAllWithValuesAsync(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        return getAllWithValuesWithResponseAsync(pathItemStringPath, pathItemStringQuery, localStringPath, localStringQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getAllWithValues(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        getAllWithValuesAsync(pathItemStringPath, pathItemStringQuery, localStringPath, localStringQuery).block();
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalQueryNullWithResponseAsync(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (pathItemStringPath == null) {
            throw new IllegalArgumentException("Parameter pathItemStringPath is required and cannot be null.");
        }
        if (this.client.getGlobalStringPath() == null) {
            throw new IllegalArgumentException("Parameter this.client.getGlobalStringPath() is required and cannot be null.");
        }
        if (localStringPath == null) {
            throw new IllegalArgumentException("Parameter localStringPath is required and cannot be null.");
        }
        return service.getGlobalQueryNull(this.client.getHost(), pathItemStringPath, pathItemStringQuery, this.client.getGlobalStringPath(), this.client.getGlobalStringQuery(), localStringPath, localStringQuery);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getGlobalQueryNullAsync(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        return getGlobalQueryNullWithResponseAsync(pathItemStringPath, pathItemStringQuery, localStringPath, localStringQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getGlobalQueryNull(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        getGlobalQueryNullAsync(pathItemStringPath, pathItemStringQuery, localStringPath, localStringQuery).block();
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery=null.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalAndLocalQueryNullWithResponseAsync(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (pathItemStringPath == null) {
            throw new IllegalArgumentException("Parameter pathItemStringPath is required and cannot be null.");
        }
        if (this.client.getGlobalStringPath() == null) {
            throw new IllegalArgumentException("Parameter this.client.getGlobalStringPath() is required and cannot be null.");
        }
        if (localStringPath == null) {
            throw new IllegalArgumentException("Parameter localStringPath is required and cannot be null.");
        }
        return service.getGlobalAndLocalQueryNull(this.client.getHost(), pathItemStringPath, pathItemStringQuery, this.client.getGlobalStringPath(), this.client.getGlobalStringQuery(), localStringPath, localStringQuery);
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery=null.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getGlobalAndLocalQueryNullAsync(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        return getGlobalAndLocalQueryNullWithResponseAsync(pathItemStringPath, pathItemStringQuery, localStringPath, localStringQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery=null.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getGlobalAndLocalQueryNull(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        getGlobalAndLocalQueryNullAsync(pathItemStringPath, pathItemStringQuery, localStringPath, localStringQuery).block();
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null, localStringQuery=null.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getLocalPathItemQueryNullWithResponseAsync(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (pathItemStringPath == null) {
            throw new IllegalArgumentException("Parameter pathItemStringPath is required and cannot be null.");
        }
        if (this.client.getGlobalStringPath() == null) {
            throw new IllegalArgumentException("Parameter this.client.getGlobalStringPath() is required and cannot be null.");
        }
        if (localStringPath == null) {
            throw new IllegalArgumentException("Parameter localStringPath is required and cannot be null.");
        }
        return service.getLocalPathItemQueryNull(this.client.getHost(), pathItemStringPath, pathItemStringQuery, this.client.getGlobalStringPath(), this.client.getGlobalStringQuery(), localStringPath, localStringQuery);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null, localStringQuery=null.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getLocalPathItemQueryNullAsync(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        return getLocalPathItemQueryNullWithResponseAsync(pathItemStringPath, pathItemStringQuery, localStringPath, localStringQuery)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null, localStringQuery=null.
     * 
     * @param pathItemStringPath 
     * @param pathItemStringQuery 
     * @param localStringPath 
     * @param localStringQuery 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getLocalPathItemQueryNull(String pathItemStringPath, String pathItemStringQuery, String localStringPath, String localStringQuery) {
        getLocalPathItemQueryNullAsync(pathItemStringPath, pathItemStringQuery, localStringPath, localStringQuery).block();
    }
}
