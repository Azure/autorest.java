package fixtures.url.implementation;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in PathItems. */
public final class PathItemsImpl {
    /** The proxy service used to perform REST calls. */
    private final PathItemsService service;

    /** The service client containing this operation class. */
    private final AutoRestUrlTestServiceImpl client;

    /**
     * Initializes an instance of PathItemsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    PathItemsImpl(AutoRestUrlTestServiceImpl client) {
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
        Mono<Response<Void>> getAllWithValues(
                @HostParam("$host") String host,
                @PathParam("pathItemStringPath") String pathItemStringPath,
                @PathParam("globalStringPath") String globalStringPath,
                @PathParam("localStringPath") String localStringPath,
                RequestOptions requestOptions,
                Context context);

        @Get(
                "/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/null/pathItemStringQuery/localStringQuery")
        Mono<Response<Void>> getGlobalQueryNull(
                @HostParam("$host") String host,
                @PathParam("pathItemStringPath") String pathItemStringPath,
                @PathParam("globalStringPath") String globalStringPath,
                @PathParam("localStringPath") String localStringPath,
                RequestOptions requestOptions,
                Context context);

        @Get(
                "/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/null/pathItemStringQuery/null")
        Mono<Response<Void>> getGlobalAndLocalQueryNull(
                @HostParam("$host") String host,
                @PathParam("pathItemStringPath") String pathItemStringPath,
                @PathParam("globalStringPath") String globalStringPath,
                @PathParam("localStringPath") String localStringPath,
                RequestOptions requestOptions,
                Context context);

        @Get(
                "/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/globalStringQuery/null/null")
        Mono<Response<Void>> getLocalPathItemQueryNull(
                @HostParam("$host") String host,
                @PathParam("pathItemStringPath") String pathItemStringPath,
                @PathParam("globalStringPath") String globalStringPath,
                @PathParam("localStringPath") String localStringPath,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery',
     * pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>A string value 'pathItemStringQuery' that appears as a query parameter</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain value 'localStringQuery'</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getAllWithValuesWithResponseAsync(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.getAllWithValues(
                                this.client.getHost(),
                                pathItemStringPath,
                                this.client.getGlobalStringPath(),
                                localStringPath,
                                requestOptions,
                                context));
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery',
     * pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>A string value 'pathItemStringQuery' that appears as a query parameter</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain value 'localStringQuery'</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getAllWithValuesWithResponseAsync(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions, Context context) {
        return service.getAllWithValues(
                this.client.getHost(),
                pathItemStringPath,
                this.client.getGlobalStringPath(),
                localStringPath,
                requestOptions,
                context);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery',
     * pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>A string value 'pathItemStringQuery' that appears as a query parameter</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain value 'localStringQuery'</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getAllWithValuesWithResponse(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions, Context context) {
        return getAllWithValuesWithResponseAsync(pathItemStringPath, localStringPath, requestOptions, context).block();
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>A string value 'pathItemStringQuery' that appears as a query parameter</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain value 'localStringQuery'</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalQueryNullWithResponseAsync(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.getGlobalQueryNull(
                                this.client.getHost(),
                                pathItemStringPath,
                                this.client.getGlobalStringPath(),
                                localStringPath,
                                requestOptions,
                                context));
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>A string value 'pathItemStringQuery' that appears as a query parameter</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain value 'localStringQuery'</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalQueryNullWithResponseAsync(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions, Context context) {
        return service.getGlobalQueryNull(
                this.client.getHost(),
                pathItemStringPath,
                this.client.getGlobalStringPath(),
                localStringPath,
                requestOptions,
                context);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>A string value 'pathItemStringQuery' that appears as a query parameter</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain value 'localStringQuery'</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getGlobalQueryNullWithResponse(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions, Context context) {
        return getGlobalQueryNullWithResponseAsync(pathItemStringPath, localStringPath, requestOptions, context)
                .block();
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery=null.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>A string value 'pathItemStringQuery' that appears as a query parameter</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain null value</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalAndLocalQueryNullWithResponseAsync(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.getGlobalAndLocalQueryNull(
                                this.client.getHost(),
                                pathItemStringPath,
                                this.client.getGlobalStringPath(),
                                localStringPath,
                                requestOptions,
                                context));
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery=null.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>A string value 'pathItemStringQuery' that appears as a query parameter</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain null value</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalAndLocalQueryNullWithResponseAsync(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions, Context context) {
        return service.getGlobalAndLocalQueryNull(
                this.client.getHost(),
                pathItemStringPath,
                this.client.getGlobalStringPath(),
                localStringPath,
                requestOptions,
                context);
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery=null.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>A string value 'pathItemStringQuery' that appears as a query parameter</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain null value</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getGlobalAndLocalQueryNullWithResponse(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions, Context context) {
        return getGlobalAndLocalQueryNullWithResponseAsync(pathItemStringPath, localStringPath, requestOptions, context)
                .block();
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null,
     * localStringQuery=null.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getLocalPathItemQueryNullWithResponseAsync(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.getLocalPathItemQueryNull(
                                this.client.getHost(),
                                pathItemStringPath,
                                this.client.getGlobalStringPath(),
                                localStringPath,
                                requestOptions,
                                context));
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null,
     * localStringQuery=null.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getLocalPathItemQueryNullWithResponseAsync(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions, Context context) {
        return service.getLocalPathItemQueryNull(
                this.client.getHost(),
                pathItemStringPath,
                this.client.getGlobalStringPath(),
                localStringPath,
                requestOptions,
                context);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath',
     * localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null,
     * localStringQuery=null.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>pathItemStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>globalStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     *     <tr><td>localStringQuery</td><td>String</td><td>No</td><td>should contain value null</td></tr>
     * </table>
     *
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getLocalPathItemQueryNullWithResponse(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions, Context context) {
        return getLocalPathItemQueryNullWithResponseAsync(pathItemStringPath, localStringPath, requestOptions, context)
                .block();
    }
}
