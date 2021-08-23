package fixtures.url;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.url.implementation.PathItemsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestUrlTestService type. */
@ServiceClient(builder = AutoRestUrlTestServiceBuilder.class, isAsync = true)
public final class PathItemsAsyncClient {
    private final PathItemsImpl serviceClient;

    /**
     * Initializes an instance of PathItems client.
     *
     * @param serviceClient the service client implementation.
     */
    PathItemsAsyncClient(PathItemsImpl serviceClient) {
        this.serviceClient = serviceClient;
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
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getAllWithValuesWithResponse(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions) {
        return this.serviceClient.getAllWithValuesWithResponseAsync(
                pathItemStringPath, localStringPath, requestOptions);
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
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalQueryNullWithResponse(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions) {
        return this.serviceClient.getGlobalQueryNullWithResponseAsync(
                pathItemStringPath, localStringPath, requestOptions);
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
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getGlobalAndLocalQueryNullWithResponse(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions) {
        return this.serviceClient.getGlobalAndLocalQueryNullWithResponseAsync(
                pathItemStringPath, localStringPath, requestOptions);
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
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getLocalPathItemQueryNullWithResponse(
            String pathItemStringPath, String localStringPath, RequestOptions requestOptions) {
        return this.serviceClient.getLocalPathItemQueryNullWithResponseAsync(
                pathItemStringPath, localStringPath, requestOptions);
    }
}
