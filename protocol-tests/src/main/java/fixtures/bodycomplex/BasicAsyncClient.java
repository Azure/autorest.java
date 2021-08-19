package fixtures.bodycomplex;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.implementation.BasicsImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestComplexTestService type. */
@ServiceClient(builder = AutoRestComplexTestServiceBuilder.class, isAsync = true)
public final class BasicAsyncClient {
    private final BasicsImpl serviceClient;

    /**
     * Initializes an instance of Basics client.
     *
     * @param serviceClient the service client implementation.
     */
    BasicAsyncClient(BasicsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return complex type {id: 2, name: 'abc', color: 'YELLOW'}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getValidWithResponseAsync(requestOptions);
    }

    /**
     * Please put {id: 2, name: 'abc', color: 'Magenta'}.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>apiVersion</td><td>String</td><td>Yes</td><td>Api Version</td></tr>
     * </table>
     *
     * <p><strong>Request Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @param complexBody Please put {id: 2, name: 'abc', color: 'Magenta'}.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponse(BinaryData complexBody, RequestOptions requestOptions) {
        return this.serviceClient.putValidWithResponseAsync(complexBody, requestOptions);
    }

    /**
     * Get a basic complex type that is invalid for the local strong type.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return a basic complex type that is invalid for the local strong type.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getInvalidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getInvalidWithResponseAsync(requestOptions);
    }

    /**
     * Get a basic complex type that is empty.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return a basic complex type that is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getEmptyWithResponseAsync(requestOptions);
    }

    /**
     * Get a basic complex type whose properties are null.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return a basic complex type whose properties are null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNullWithResponseAsync(requestOptions);
    }

    /**
     * Get a basic complex type while the server doesn't provide a response payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     id: Integer
     *     name: String
     *     color: String(cyan/Magenta/YELLOW/blacK)
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return a basic complex type while the server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getNotProvidedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNotProvidedWithResponseAsync(requestOptions);
    }
}
