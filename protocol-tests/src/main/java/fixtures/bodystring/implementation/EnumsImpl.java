package fixtures.bodystring.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
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

/** An instance of this class provides access to all the operations defined in Enums. */
public final class EnumsImpl {
    /** The proxy service used to perform REST calls. */
    private final EnumsService service;

    /** The service client containing this operation class. */
    private final AutoRestSwaggerBATServiceImpl client;

    /**
     * Initializes an instance of EnumsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    EnumsImpl(AutoRestSwaggerBATServiceImpl client) {
        this.service = RestProxy.create(EnumsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestSwaggerBATServiceEnums to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATSe")
    private interface EnumsService {
        @Get("/string/enum/notExpandable")
        Mono<Response<String>> getNotExpandable(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/string/enum/notExpandable")
        Mono<Response<Void>> putNotExpandable(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData stringBody,
                RequestOptions requestOptions,
                Context context);

        @Get("/string/enum/Referenced")
        Mono<Response<String>> getReferenced(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/string/enum/Referenced")
        Mono<Response<Void>> putReferenced(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData enumStringBody,
                RequestOptions requestOptions,
                Context context);

        @Get("/string/enum/ReferencedConstant")
        Mono<Response<BinaryData>> getReferencedConstant(
                @HostParam("$host") String host, RequestOptions requestOptions, Context context);

        @Put("/string/enum/ReferencedConstant")
        Mono<Response<Void>> putReferencedConstant(
                @HostParam("$host") String host,
                @BodyParam("application/json") BinaryData enumStringBody,
                RequestOptions requestOptions,
                Context context);
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> getNotExpandableWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getNotExpandable(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> getNotExpandableWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getNotExpandable(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<String> getNotExpandableWithResponse(RequestOptions requestOptions, Context context) {
        return getNotExpandableWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNotExpandableWithResponseAsync(
            BinaryData stringBody, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putNotExpandable(this.client.getHost(), stringBody, requestOptions, context));
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNotExpandableWithResponseAsync(
            BinaryData stringBody, RequestOptions requestOptions, Context context) {
        return service.putNotExpandable(this.client.getHost(), stringBody, requestOptions, context);
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param stringBody string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putNotExpandableWithResponse(
            BinaryData stringBody, RequestOptions requestOptions, Context context) {
        return putNotExpandableWithResponseAsync(stringBody, requestOptions, context).block();
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> getReferencedWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(context -> service.getReferenced(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> getReferencedWithResponseAsync(RequestOptions requestOptions, Context context) {
        return service.getReferenced(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<String> getReferencedWithResponse(RequestOptions requestOptions, Context context) {
        return getReferencedWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedWithResponseAsync(
            BinaryData enumStringBody, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.putReferenced(this.client.getHost(), enumStringBody, requestOptions, context));
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedWithResponseAsync(
            BinaryData enumStringBody, RequestOptions requestOptions, Context context) {
        return service.putReferenced(this.client.getHost(), enumStringBody, requestOptions, context);
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * String(red color/green-color/blue_color)
     * }</pre>
     *
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putReferencedWithResponse(
            BinaryData enumStringBody, RequestOptions requestOptions, Context context) {
        return putReferencedWithResponseAsync(enumStringBody, requestOptions, context).block();
    }

    /**
     * Get value 'green-color' from the constant.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     colorConstant: String
     *     field1: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return value 'green-color' from the constant.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getReferencedConstantWithResponseAsync(RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context -> service.getReferencedConstant(this.client.getHost(), requestOptions, context));
    }

    /**
     * Get value 'green-color' from the constant.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     colorConstant: String
     *     field1: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return value 'green-color' from the constant.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getReferencedConstantWithResponseAsync(
            RequestOptions requestOptions, Context context) {
        return service.getReferencedConstant(this.client.getHost(), requestOptions, context);
    }

    /**
     * Get value 'green-color' from the constant.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     colorConstant: String
     *     field1: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return value 'green-color' from the constant.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getReferencedConstantWithResponse(RequestOptions requestOptions, Context context) {
        return getReferencedConstantWithResponseAsync(requestOptions, context).block();
    }

    /**
     * Sends value 'green-color' from a constant.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     colorConstant: String
     *     field1: String
     * }
     * }</pre>
     *
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedConstantWithResponseAsync(
            BinaryData enumStringBody, RequestOptions requestOptions) {
        return FluxUtil.withContext(
                context ->
                        service.putReferencedConstant(this.client.getHost(), enumStringBody, requestOptions, context));
    }

    /**
     * Sends value 'green-color' from a constant.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     colorConstant: String
     *     field1: String
     * }
     * }</pre>
     *
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedConstantWithResponseAsync(
            BinaryData enumStringBody, RequestOptions requestOptions, Context context) {
        return service.putReferencedConstant(this.client.getHost(), enumStringBody, requestOptions, context);
    }

    /**
     * Sends value 'green-color' from a constant.
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     colorConstant: String
     *     field1: String
     * }
     * }</pre>
     *
     * @param enumStringBody enum string body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws HttpResponseException Thrown when status code is 400 or above if requestOptions is null or throwOnError
     *     in requestOptions is true.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> putReferencedConstantWithResponse(
            BinaryData enumStringBody, RequestOptions requestOptions, Context context) {
        return putReferencedConstantWithResponseAsync(enumStringBody, requestOptions, context).block();
    }
}
