package fixtures.url;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
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
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.url.models.ErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in PathItems. */
public final class PathItems {
    /** The proxy service used to perform REST calls. */
    private final PathItemsService service;

    /** The service client containing this operation class. */
    private final AutoRestUrlTestService client;

    /**
     * Initializes an instance of PathItems.
     *
     * @param client the instance of the service client containing this operation class.
     */
    PathItems(AutoRestUrlTestService client) {
        this.service =
                RestProxy.create(PathItemsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestUrlTestServicePathItems to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestUrlTestServi")
    private interface PathItemsService {
        @Get(
                "/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/globalStringQuery/pathItemStringQuery/localStringQuery")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getAllWithValues(
                @HostParam("$host") String host,
                @PathParam("pathItemStringPath") String pathItemStringPath,
                @QueryParam("pathItemStringQuery") String pathItemStringQuery,
                @PathParam("globalStringPath") String globalStringPath,
                @QueryParam("globalStringQuery") String globalStringQuery,
                @PathParam("localStringPath") String localStringPath,
                @QueryParam("localStringQuery") String localStringQuery,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get(
                "/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/null/pathItemStringQuery/localStringQuery")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getGlobalQueryNull(
                @HostParam("$host") String host,
                @PathParam("pathItemStringPath") String pathItemStringPath,
                @QueryParam("pathItemStringQuery") String pathItemStringQuery,
                @PathParam("globalStringPath") String globalStringPath,
                @QueryParam("globalStringQuery") String globalStringQuery,
                @PathParam("localStringPath") String localStringPath,
                @QueryParam("localStringQuery") String localStringQuery,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get(
                "/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/null/pathItemStringQuery/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getGlobalAndLocalQueryNull(
                @HostParam("$host") String host,
                @PathParam("pathItemStringPath") String pathItemStringPath,
                @QueryParam("pathItemStringQuery") String pathItemStringQuery,
                @PathParam("globalStringPath") String globalStringPath,
                @QueryParam("globalStringQuery") String globalStringQuery,
                @PathParam("localStringPath") String localStringPath,
                @QueryParam("localStringQuery") String localStringQuery,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get(
                "/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/globalStringQuery/null/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> getLocalPathItemQueryNull(
                @HostParam("$host") String host,
                @PathParam("pathItemStringPath") String pathItemStringPath,
                @QueryParam("pathItemStringQuery") String pathItemStringQuery,
                @PathParam("globalStringPath") String globalStringPath,
                @QueryParam("globalStringQuery") String globalStringQuery,
                @PathParam("localStringPath") String localStringPath,
                @QueryParam("localStringQuery") String localStringQuery,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery',
     * pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getAllWithValuesWithResponseAsync(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (pathItemStringPath == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter pathItemStringPath is required and cannot be null."));
        }
        if (this.client.getGlobalStringPath() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getGlobalStringPath() is required and cannot be null."));
        }
        if (localStringPath == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter localStringPath is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.getAllWithValues(
                                this.client.getHost(),
                                pathItemStringPath,
                                pathItemStringQuery,
                                this.client.getGlobalStringPath(),
                                this.client.getGlobalStringQuery(),
                                localStringPath,
                                localStringQuery,
                                accept,
                                context));
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery',
     * pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getAllWithValuesAsync(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        return getAllWithValuesWithResponseAsync(
                        pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery',
     * pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getAllWithValuesAsync(String pathItemStringPath, String localStringPath) {
        final String pathItemStringQuery = null;
        final String localStringQuery = null;
        return getAllWithValuesWithResponseAsync(
                        pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery',
     * pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getAllWithValues(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        getAllWithValuesAsync(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery).block();
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery',
     * pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getAllWithValues(String pathItemStringPath, String localStringPath) {
        final String pathItemStringQuery = null;
        final String localStringQuery = null;
        getAllWithValuesAsync(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery).block();
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalQueryNullWithResponseAsync(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (pathItemStringPath == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter pathItemStringPath is required and cannot be null."));
        }
        if (this.client.getGlobalStringPath() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getGlobalStringPath() is required and cannot be null."));
        }
        if (localStringPath == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter localStringPath is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.getGlobalQueryNull(
                                this.client.getHost(),
                                pathItemStringPath,
                                pathItemStringQuery,
                                this.client.getGlobalStringPath(),
                                this.client.getGlobalStringQuery(),
                                localStringPath,
                                localStringQuery,
                                accept,
                                context));
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getGlobalQueryNullAsync(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        return getGlobalQueryNullWithResponseAsync(
                        pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getGlobalQueryNullAsync(String pathItemStringPath, String localStringPath) {
        final String pathItemStringQuery = null;
        final String localStringQuery = null;
        return getGlobalQueryNullWithResponseAsync(
                        pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getGlobalQueryNull(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        getGlobalQueryNullAsync(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery).block();
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getGlobalQueryNull(String pathItemStringPath, String localStringPath) {
        final String pathItemStringQuery = null;
        final String localStringQuery = null;
        getGlobalQueryNullAsync(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery).block();
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain null value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalAndLocalQueryNullWithResponseAsync(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (pathItemStringPath == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter pathItemStringPath is required and cannot be null."));
        }
        if (this.client.getGlobalStringPath() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getGlobalStringPath() is required and cannot be null."));
        }
        if (localStringPath == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter localStringPath is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.getGlobalAndLocalQueryNull(
                                this.client.getHost(),
                                pathItemStringPath,
                                pathItemStringQuery,
                                this.client.getGlobalStringPath(),
                                this.client.getGlobalStringQuery(),
                                localStringPath,
                                localStringQuery,
                                accept,
                                context));
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain null value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getGlobalAndLocalQueryNullAsync(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        return getGlobalAndLocalQueryNullWithResponseAsync(
                        pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getGlobalAndLocalQueryNullAsync(String pathItemStringPath, String localStringPath) {
        final String pathItemStringQuery = null;
        final String localStringQuery = null;
        return getGlobalAndLocalQueryNullWithResponseAsync(
                        pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain null value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getGlobalAndLocalQueryNull(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        getGlobalAndLocalQueryNullAsync(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .block();
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getGlobalAndLocalQueryNull(String pathItemStringPath, String localStringPath) {
        final String pathItemStringQuery = null;
        final String localStringQuery = null;
        getGlobalAndLocalQueryNullAsync(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .block();
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null,
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery should contain value null.
     * @param localStringQuery should contain value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getLocalPathItemQueryNullWithResponseAsync(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (pathItemStringPath == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter pathItemStringPath is required and cannot be null."));
        }
        if (this.client.getGlobalStringPath() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getGlobalStringPath() is required and cannot be null."));
        }
        if (localStringPath == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter localStringPath is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.getLocalPathItemQueryNull(
                                this.client.getHost(),
                                pathItemStringPath,
                                pathItemStringQuery,
                                this.client.getGlobalStringPath(),
                                this.client.getGlobalStringQuery(),
                                localStringPath,
                                localStringQuery,
                                accept,
                                context));
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null,
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery should contain value null.
     * @param localStringQuery should contain value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getLocalPathItemQueryNullAsync(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        return getLocalPathItemQueryNullWithResponseAsync(
                        pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null,
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getLocalPathItemQueryNullAsync(String pathItemStringPath, String localStringPath) {
        final String pathItemStringQuery = null;
        final String localStringQuery = null;
        return getLocalPathItemQueryNullWithResponseAsync(
                        pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null,
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery should contain value null.
     * @param localStringQuery should contain value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getLocalPathItemQueryNull(
            String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        getLocalPathItemQueryNullAsync(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .block();
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null,
     * localStringQuery=null.
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getLocalPathItemQueryNull(String pathItemStringPath, String localStringPath) {
        final String pathItemStringQuery = null;
        final String localStringQuery = null;
        getLocalPathItemQueryNullAsync(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery)
                .block();
    }
}
