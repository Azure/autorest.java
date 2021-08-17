package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import fixtures.httpinfrastructure.implementation.MultipleResponsesImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class, isAsync = true)
public final class MultipleResponsesAsyncClient {
    private final MultipleResponsesImpl serviceClient;

    /**
     * Initializes an instance of MultipleResponses client.
     *
     * @param serviceClient the service client implementation.
     */
    MultipleResponsesAsyncClient(MultipleResponsesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError200ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError200ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 204 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError204ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError204ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError201InvalidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError201InvalidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 202 response with no payload:.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError202NoneWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError202NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model204NoModelDefaultError400ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError400ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model201ModelDefaultError200ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200Model201ModelDefaultError200ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model201ModelDefaultError201ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200Model201ModelDefaultError201ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200Model201ModelDefaultError400ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200Model201ModelDefaultError400ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError200ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError200ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError201ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError201ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError404ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError404ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * Object
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA201ModelC404ModelDDefaultError400ValidWithResponse(
            RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError400ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 202 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError202NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get202None204NoneDefaultError202NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 204 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError204NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get202None204NoneDefaultError204NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultError400ValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get202None204NoneDefaultError400ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 202 response with an unexpected payload {'property': 'value'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone202InvalidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get202None204NoneDefaultNone202InvalidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 204 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone204NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get202None204NoneDefaultNone204NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone400NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get202None204NoneDefaultNone400NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with an unexpected payload {'property': 'value'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> get202None204NoneDefaultNone400InvalidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get202None204NoneDefaultNone400InvalidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDefaultModelA200ValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultModelA200ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with no payload.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> getDefaultModelA200NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultModelA200NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'statusCode': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultModelA400ValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultModelA400ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultModelA400NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultModelA400NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with invalid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone200InvalidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultNone200InvalidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone200NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultNone200NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'statusCode': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone400InvalidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultNone400InvalidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getDefaultNone400NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultNone400NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA200NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA200NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA200ValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA200ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA200InvalidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA200InvalidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA400NoneWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA400NoneWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA400ValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA400ValidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA400InvalidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA400InvalidWithResponseAsync(requestOptions);
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * {
     *     statusCode: String
     * }
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> get200ModelA202ValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA202ValidWithResponseAsync(requestOptions);
    }
}
