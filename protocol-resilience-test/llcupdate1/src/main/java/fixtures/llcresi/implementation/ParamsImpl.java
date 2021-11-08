// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.llcresi.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
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
        @Get("/servicedriven/parameters")
        Mono<Response<BinaryData>> getRequired(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Post("/servicedriven/parameters")
        Mono<Response<BinaryData>> postParameters(
                @HostParam("$host") String host,
                @BodyParam("image/jpeg") BinaryData parameter,
                RequestOptions requestOptions,
                Context context);

        @Delete("/servicedriven/parameters")
        Mono<Response<Void>> deleteParameters(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Get("/servicedriven/newpath")
        Mono<Response<BinaryData>> getNewOperation(
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
     *     <tr><td>parameter</td><td>String</td><td>Yes</td><td>I am a required parameter</td></tr>
     *     <tr><td>newParameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
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
     *     <tr><td>parameter</td><td>String</td><td>Yes</td><td>I am a required parameter</td></tr>
     *     <tr><td>newParameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
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
     *     <tr><td>parameter</td><td>String</td><td>Yes</td><td>I am a required parameter</td></tr>
     *     <tr><td>newParameter</td><td>String</td><td>No</td><td>I'm a new input optional parameter</td></tr>
     * </table>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getRequiredWithResponse(RequestOptions requestOptions) {
        return getRequiredWithResponseAsync(requestOptions).block();
    }

    /**
     * POST a JSON or a JPEG.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentLength</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param parameter I am a body parameter with a new content type. My only valid JSON entry is { url:
     *     "http://example.org/myimage.jpeg" }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return any object.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> postParametersWithResponseAsync(
            BinaryData parameter, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.postParameters(this.client.getHost(), parameter, requestOptions, context));
    }

    /**
     * POST a JSON or a JPEG.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentLength</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param parameter I am a body parameter with a new content type. My only valid JSON entry is { url:
     *     "http://example.org/myimage.jpeg" }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return any object.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> postParametersWithResponseAsync(
            BinaryData parameter, RequestOptions requestOptions, Context context) {
        return service.postParameters(this.client.getHost(), parameter, requestOptions, context);
    }

    /**
     * POST a JSON or a JPEG.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentLength</td><td>long</td><td>Yes</td><td>The contentLength parameter</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * Flux<ByteBuffer>
     * }</pre>
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param parameter I am a body parameter with a new content type. My only valid JSON entry is { url:
     *     "http://example.org/myimage.jpeg" }.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return any object.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> postParametersWithResponse(BinaryData parameter, RequestOptions requestOptions) {
        return postParametersWithResponseAsync(parameter, requestOptions).block();
    }

    /**
     * Delete something.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteParametersWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.deleteParameters(this.client.getHost(), requestOptions, context));
    }

    /**
     * Delete something.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteParametersWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.deleteParameters(this.client.getHost(), requestOptions, context);
    }

    /**
     * Delete something.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteParametersWithResponse(RequestOptions requestOptions) {
        return deleteParametersWithResponseAsync(requestOptions).block();
    }

    /**
     * I'm a new operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return any object.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNewOperationWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getNewOperation(this.client.getHost(), requestOptions, context));
    }

    /**
     * I'm a new operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return any object.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNewOperationWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getNewOperation(this.client.getHost(), requestOptions, context);
    }

    /**
     * I'm a new operation.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return any object.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getNewOperationWithResponse(RequestOptions requestOptions) {
        return getNewOperationWithResponseAsync(requestOptions).block();
    }
}
