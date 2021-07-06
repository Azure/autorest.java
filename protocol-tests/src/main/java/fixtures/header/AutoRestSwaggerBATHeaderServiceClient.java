package fixtures.header;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.header.implementation.HeadersImpl;

/** Initializes a new instance of the synchronous AutoRestSwaggerBATHeaderService type. */
@ServiceClient(builder = AutoRestSwaggerBATHeaderServiceBuilder.class)
public final class AutoRestSwaggerBATHeaderServiceClient {
    private final HeadersImpl serviceClient;

    /**
     * Initializes an instance of Headers client.
     *
     * @param serviceClient the service client implementation.
     */
    AutoRestSwaggerBATHeaderServiceClient(HeadersImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramExistingKey(String userAgent, RequestOptions requestOptions) {
        this.serviceClient.paramExistingKey(userAgent, requestOptions);
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * @param userAgent Send a post request with header value "User-Agent": "overwrite".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramExistingKeyWithResponse(
            String userAgent, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramExistingKeyWithResponse(userAgent, requestOptions, context);
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseExistingKey(RequestOptions requestOptions) {
        this.serviceClient.responseExistingKey(requestOptions);
    }

    /**
     * Get a response with header value "User-Agent": "overwrite".
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "User-Agent": "overwrite".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseExistingKeyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseExistingKeyWithResponse(requestOptions, context);
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramProtectedKey(String contentType, RequestOptions requestOptions) {
        this.serviceClient.paramProtectedKey(contentType, requestOptions);
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * @param contentType Send a post request with header value "Content-Type": "text/html".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramProtectedKeyWithResponse(
            String contentType, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramProtectedKeyWithResponse(contentType, requestOptions, context);
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseProtectedKey(RequestOptions requestOptions) {
        this.serviceClient.responseProtectedKey(requestOptions);
    }

    /**
     * Get a response with header value "Content-Type": "text/html".
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "Content-Type": "text/html".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseProtectedKeyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseProtectedKeyWithResponse(requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramInteger(String scenario, int value, RequestOptions requestOptions) {
        this.serviceClient.paramInteger(scenario, value, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 1 or -2.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramIntegerWithResponse(
            String scenario, int value, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramIntegerWithResponse(scenario, value, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseInteger(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseInteger(scenario, requestOptions);
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 1 or -2.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseIntegerWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseIntegerWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramLong(String scenario, long value, RequestOptions requestOptions) {
        this.serviceClient.paramLong(scenario, value, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 105 or -2.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramLongWithResponse(
            String scenario, long value, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramLongWithResponse(scenario, value, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseLong(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseLong(scenario, requestOptions);
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 105 or -2.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseLongWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseLongWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramFloat(String scenario, float value, RequestOptions requestOptions) {
        this.serviceClient.paramFloat(scenario, value, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 0.07 or -3.0.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramFloatWithResponse(
            String scenario, float value, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramFloatWithResponse(scenario, value, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseFloat(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseFloat(scenario, requestOptions);
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 0.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseFloatWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseFloatWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDouble(String scenario, double value, RequestOptions requestOptions) {
        this.serviceClient.paramDouble(scenario, value, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param value Send a post request with header values 7e120 or -3.0.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDoubleWithResponse(
            String scenario, double value, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDoubleWithResponse(scenario, value, requestOptions, context);
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDouble(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseDouble(scenario, requestOptions);
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * @param scenario Send a post request with header values "scenario": "positive" or "negative".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": 7e120 or -3.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDoubleWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDoubleWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramBool(String scenario, boolean value, RequestOptions requestOptions) {
        this.serviceClient.paramBool(scenario, value, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param value Send a post request with header values true or false.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramBoolWithResponse(
            String scenario, boolean value, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramBoolWithResponse(scenario, value, requestOptions, context);
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseBool(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseBool(scenario, requestOptions);
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * @param scenario Send a post request with header values "scenario": "true" or "false".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header value "value": true or false.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseBoolWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseBoolWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramString(String scenario, RequestOptions requestOptions) {
        this.serviceClient.paramString(scenario, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramStringWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramStringWithResponse(scenario, requestOptions, context);
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseString(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseString(scenario, requestOptions);
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseStringWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseStringWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDate(String scenario, String value, RequestOptions requestOptions) {
        this.serviceClient.paramDate(scenario, value, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01" or "0001-01-01".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDateWithResponse(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDateWithResponse(scenario, value, requestOptions, context);
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDate(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseDate(scenario, requestOptions);
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "2010-01-01" or "0001-01-01".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDateWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDateWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetime(String scenario, String value, RequestOptions requestOptions) {
        this.serviceClient.paramDatetime(scenario, value, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param value Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDatetimeWithResponse(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDatetimeWithResponse(scenario, value, requestOptions, context);
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetime(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseDatetime(scenario, requestOptions);
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDatetimeWithResponse(
            String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDatetimeWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDatetimeRfc1123(String scenario, RequestOptions requestOptions) {
        this.serviceClient.paramDatetimeRfc1123(scenario, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDatetimeRfc1123WithResponse(
            String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDatetimeRfc1123WithResponse(scenario, requestOptions, context);
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDatetimeRfc1123(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseDatetimeRfc1123(scenario, requestOptions);
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "min".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDatetimeRfc1123WithResponse(
            String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDatetimeRfc1123WithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramDuration(String scenario, String value, RequestOptions requestOptions) {
        this.serviceClient.paramDuration(scenario, value, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "P123DT22H14M12.011S".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramDurationWithResponse(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDurationWithResponse(scenario, value, requestOptions, context);
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseDuration(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseDuration(scenario, requestOptions);
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "P123DT22H14M12.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseDurationWithResponse(
            String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDurationWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramByte(String scenario, String value, RequestOptions requestOptions) {
        this.serviceClient.paramByte(scenario, value, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param value Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramByteWithResponse(
            String scenario, String value, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramByteWithResponse(scenario, value, requestOptions, context);
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseByte(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseByte(scenario, requestOptions);
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * @param scenario Send a post request with header values "scenario": "valid".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseByteWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseByteWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramEnum(String scenario, RequestOptions requestOptions) {
        this.serviceClient.paramEnum(scenario, requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> paramEnumWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramEnumWithResponse(scenario, requestOptions, context);
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseEnum(String scenario, RequestOptions requestOptions) {
        this.serviceClient.responseEnum(scenario, requestOptions);
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * @param scenario Send a post request with header values "scenario": "valid" or "null" or "empty".
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a response with header values "GREY" or null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseEnumWithResponse(String scenario, RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseEnumWithResponse(scenario, requestOptions, context);
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void customRequestId(RequestOptions requestOptions) {
        this.serviceClient.customRequestId(requestOptions);
    }

    /**
     * Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> customRequestIdWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.customRequestIdWithResponse(requestOptions, context);
    }
}
