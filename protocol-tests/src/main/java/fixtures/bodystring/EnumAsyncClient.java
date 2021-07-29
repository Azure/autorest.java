package fixtures.bodystring;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodystring.implementation.EnumsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestSwaggerBATService type. */
@ServiceClient(builder = AutoRestSwaggerBATServiceBuilder.class, isAsync = true)
public final class EnumAsyncClient {
    private final EnumsImpl serviceClient;

    /**
     * Initializes an instance of Enums client.
     *
     * @param serviceClient the service client implementation.
     */
    EnumAsyncClient(EnumsImpl serviceClient) {
        this.serviceClient = serviceClient;
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
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> getNotExpandableWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNotExpandableWithResponseAsync(requestOptions);
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
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> getNotExpandable(RequestOptions requestOptions) {
        return this.serviceClient.getNotExpandableAsync(requestOptions);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putNotExpandableWithResponse(BinaryData stringBody, RequestOptions requestOptions) {
        return this.serviceClient.putNotExpandableWithResponseAsync(stringBody, requestOptions);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putNotExpandable(BinaryData stringBody, RequestOptions requestOptions) {
        return this.serviceClient.putNotExpandableAsync(stringBody, requestOptions);
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
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> getReferencedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getReferencedWithResponseAsync(requestOptions);
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
     * @return enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> getReferenced(RequestOptions requestOptions) {
        return this.serviceClient.getReferencedAsync(requestOptions);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedWithResponse(BinaryData enumStringBody, RequestOptions requestOptions) {
        return this.serviceClient.putReferencedWithResponseAsync(enumStringBody, requestOptions);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putReferenced(BinaryData enumStringBody, RequestOptions requestOptions) {
        return this.serviceClient.putReferencedAsync(enumStringBody, requestOptions);
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
     * @return value 'green-color' from the constant.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getReferencedConstantWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getReferencedConstantWithResponseAsync(requestOptions);
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
     * @return value 'green-color' from the constant.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> getReferencedConstant(RequestOptions requestOptions) {
        return this.serviceClient.getReferencedConstantAsync(requestOptions);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putReferencedConstantWithResponse(
            BinaryData enumStringBody, RequestOptions requestOptions) {
        return this.serviceClient.putReferencedConstantWithResponseAsync(enumStringBody, requestOptions);
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putReferencedConstant(BinaryData enumStringBody, RequestOptions requestOptions) {
        return this.serviceClient.putReferencedConstantAsync(enumStringBody, requestOptions);
    }
}
