package fixtures.llcupdate1.implementation;

import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Params. */
public final class ParamsImpl {
    /** The proxy service used to perform REST calls. */
    private final ParamsService service;

    /** The service client containing this operation class. */
    private final LLCClientImpl client;

    /**
     * Initializes an instance of ParamsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    ParamsImpl(LLCClientImpl client) {
        this.service = RestProxy.create(ParamsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for LLCClientParams to be used by the proxy service to perform REST
     * calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "LLCClientParams")
    private interface ParamsService {
        @Get("/llc/parameters")
        Mono<Response<BinaryData>> getRequired(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter1</td><td>String</td><td>Yes</td><td>I am a required parameter with a client default value</td></tr>
     *     <tr><td>parameter2</td><td>String</td><td>No</td><td>I was a required parameter, but now I'm optional</td></tr>
     *     <tr><td>parameter3</td><td>String</td><td>Yes</td><td>I am a required parameter and I'm last in Swagger</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getRequiredWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getRequired(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter1</td><td>String</td><td>Yes</td><td>I am a required parameter with a client default value</td></tr>
     *     <tr><td>parameter2</td><td>String</td><td>No</td><td>I was a required parameter, but now I'm optional</td></tr>
     *     <tr><td>parameter3</td><td>String</td><td>Yes</td><td>I am a required parameter and I'm last in Swagger</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getRequiredWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getRequired(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>parameter1</td><td>String</td><td>Yes</td><td>I am a required parameter with a client default value</td></tr>
     *     <tr><td>parameter2</td><td>String</td><td>No</td><td>I was a required parameter, but now I'm optional</td></tr>
     *     <tr><td>parameter3</td><td>String</td><td>Yes</td><td>I am a required parameter and I'm last in Swagger</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getRequiredWithResponse(RequestOptions requestOptions, Context context) {
        return getRequiredWithResponseAsync(requestOptions, context).block();
    }
}
