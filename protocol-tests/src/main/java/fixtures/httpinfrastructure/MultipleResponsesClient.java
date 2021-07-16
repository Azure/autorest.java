package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.httpinfrastructure.implementation.MultipleResponsesImpl;

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class)
public final class MultipleResponsesClient {
    private final MultipleResponsesImpl serviceClient;

    /**
     * Initializes an instance of MultipleResponses client.
     *
     * @param serviceClient the service client implementation.
     */
    MultipleResponsesClient(MultipleResponsesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError200Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError200Valid(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError200ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200Model204NoModelDefaultError200ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 204 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError204Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError204Valid(requestOptions);
    }

    /**
     * Send a 204 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError204ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200Model204NoModelDefaultError204ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError201Invalid(RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError201Invalid(requestOptions);
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError201InvalidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200Model204NoModelDefaultError201InvalidWithResponse(requestOptions, context);
    }

    /**
     * Send a 202 response with no payload:.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError202None(RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError202None(requestOptions);
    }

    /**
     * Send a 202 response with no payload:.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError202NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200Model204NoModelDefaultError202NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model204NoModelDefaultError400Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200Model204NoModelDefaultError400Valid(requestOptions);
    }

    /**
     * Send a 400 response with valid error payload: {'status': 400, 'message': 'client error'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model204NoModelDefaultError400ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200Model204NoModelDefaultError400ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model201ModelDefaultError200Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200Model201ModelDefaultError200Valid(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model201ModelDefaultError200ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200Model201ModelDefaultError200ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model201ModelDefaultError201Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200Model201ModelDefaultError201Valid(requestOptions);
    }

    /**
     * Send a 201 response with valid payload: {'statusCode': '201', 'textStatusCode': 'Created'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model201ModelDefaultError201ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200Model201ModelDefaultError201ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200Model201ModelDefaultError400Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200Model201ModelDefaultError400Valid(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200Model201ModelDefaultError400ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200Model201ModelDefaultError400ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA201ModelC404ModelDDefaultError200Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError200Valid(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError200ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError200ValidWithResponse(
                requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA201ModelC404ModelDDefaultError201Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError201Valid(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'httpCode': '201'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError201ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError201ValidWithResponse(
                requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA201ModelC404ModelDDefaultError404Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError404Valid(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'httpStatusCode': '404'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError404ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError404ValidWithResponse(
                requestOptions, context);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA201ModelC404ModelDDefaultError400Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError400Valid(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA201ModelC404ModelDDefaultError400ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA201ModelC404ModelDDefaultError400ValidWithResponse(
                requestOptions, context);
    }

    /**
     * Send a 202 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultError202None(RequestOptions requestOptions) {
        this.serviceClient.get202None204NoneDefaultError202None(requestOptions);
    }

    /**
     * Send a 202 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultError202NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get202None204NoneDefaultError202NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 204 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultError204None(RequestOptions requestOptions) {
        this.serviceClient.get202None204NoneDefaultError204None(requestOptions);
    }

    /**
     * Send a 204 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultError204NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get202None204NoneDefaultError204NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultError400Valid(RequestOptions requestOptions) {
        this.serviceClient.get202None204NoneDefaultError400Valid(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'code': '400', 'message': 'client error'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultError400ValidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get202None204NoneDefaultError400ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 202 response with an unexpected payload {'property': 'value'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone202Invalid(RequestOptions requestOptions) {
        this.serviceClient.get202None204NoneDefaultNone202Invalid(requestOptions);
    }

    /**
     * Send a 202 response with an unexpected payload {'property': 'value'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultNone202InvalidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get202None204NoneDefaultNone202InvalidWithResponse(requestOptions, context);
    }

    /**
     * Send a 204 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone204None(RequestOptions requestOptions) {
        this.serviceClient.get202None204NoneDefaultNone204None(requestOptions);
    }

    /**
     * Send a 204 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultNone204NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get202None204NoneDefaultNone204NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone400None(RequestOptions requestOptions) {
        this.serviceClient.get202None204NoneDefaultNone400None(requestOptions);
    }

    /**
     * Send a 400 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultNone400NoneWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get202None204NoneDefaultNone400NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with an unexpected payload {'property': 'value'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get202None204NoneDefaultNone400Invalid(RequestOptions requestOptions) {
        this.serviceClient.get202None204NoneDefaultNone400Invalid(requestOptions);
    }

    /**
     * Send a 400 response with an unexpected payload {'property': 'value'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get202None204NoneDefaultNone400InvalidWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.get202None204NoneDefaultNone400InvalidWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDefaultModelA200Valid(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultModelA200Valid(requestOptions);
    }

    /**
     * Send a 200 response with valid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDefaultModelA200ValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDefaultModelA200ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData getDefaultModelA200None(RequestOptions requestOptions) {
        return this.serviceClient.getDefaultModelA200None(requestOptions);
    }

    /**
     * Send a 200 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> getDefaultModelA200NoneWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDefaultModelA200NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with valid payload: {'statusCode': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultModelA400Valid(RequestOptions requestOptions) {
        this.serviceClient.getDefaultModelA400Valid(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'statusCode': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultModelA400ValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDefaultModelA400ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultModelA400None(RequestOptions requestOptions) {
        this.serviceClient.getDefaultModelA400None(requestOptions);
    }

    /**
     * Send a 400 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultModelA400NoneWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDefaultModelA400NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with invalid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone200Invalid(RequestOptions requestOptions) {
        this.serviceClient.getDefaultNone200Invalid(requestOptions);
    }

    /**
     * Send a 200 response with invalid payload: {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultNone200InvalidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDefaultNone200InvalidWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone200None(RequestOptions requestOptions) {
        this.serviceClient.getDefaultNone200None(requestOptions);
    }

    /**
     * Send a 200 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultNone200NoneWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDefaultNone200NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with valid payload: {'statusCode': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone400Invalid(RequestOptions requestOptions) {
        this.serviceClient.getDefaultNone400Invalid(requestOptions);
    }

    /**
     * Send a 400 response with valid payload: {'statusCode': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultNone400InvalidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDefaultNone400InvalidWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getDefaultNone400None(RequestOptions requestOptions) {
        this.serviceClient.getDefaultNone400None(requestOptions);
    }

    /**
     * Send a 400 response with no payload.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getDefaultNone400NoneWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getDefaultNone400NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA200None(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA200None(requestOptions);
    }

    /**
     * Send a 200 response with no payload, when a payload is expected - client should return a null object of thde type
     * for model A.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA200NoneWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA200NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA200Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA200Valid(requestOptions);
    }

    /**
     * Send a 200 response with payload {'statusCode': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA200ValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA200ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA200Invalid(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA200Invalid(requestOptions);
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '200'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA200InvalidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA200InvalidWithResponse(requestOptions, context);
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA400None(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA400None(requestOptions);
    }

    /**
     * Send a 400 response with no payload client should treat as an http error with no error model.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA400NoneWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA400NoneWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA400Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA400Valid(requestOptions);
    }

    /**
     * Send a 200 response with payload {'statusCode': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA400ValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA400ValidWithResponse(requestOptions, context);
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA400Invalid(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA400Invalid(requestOptions);
    }

    /**
     * Send a 200 response with invalid payload {'statusCodeInvalid': '400'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA400InvalidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA400InvalidWithResponse(requestOptions, context);
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get200ModelA202Valid(RequestOptions requestOptions) {
        return this.serviceClient.get200ModelA202Valid(requestOptions);
    }

    /**
     * Send a 202 response with payload {'statusCode': '202'}.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get200ModelA202ValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200ModelA202ValidWithResponse(requestOptions, context);
    }
}
