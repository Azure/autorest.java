package fixtures.header;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.header.implementation.HeadersImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestSwaggerBATHeaderService type. */
@ServiceClient(builder = AutoRestSwaggerBATHeaderServiceBuilder.class, isAsync = true)
public final class AutoRestSwaggerBATHeaderServiceAsyncClient {
    private final HeadersImpl serviceClient;

    /**
     * Initializes an instance of Headers client.
     *
     * @param serviceClient the service client implementation.
     */
    AutoRestSwaggerBATHeaderServiceAsyncClient(HeadersImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>userAgent</td><td>String</td><td>Yes</td><td>Send a post request with header value "User-Agent": "overwrite"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramExistingKeyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramExistingKeyWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header value "User-Agent": "overwrite".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>userAgent</td><td>String</td><td>Yes</td><td>Send a post request with header value "User-Agent": "overwrite"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramExistingKey(RequestOptions requestOptions) {
        return this.serviceClient.paramExistingKeyAsync(requestOptions);
    }

    /** Get a response with header value "User-Agent": "overwrite". */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseExistingKeyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseExistingKeyWithResponseAsync(requestOptions);
    }

    /** Get a response with header value "User-Agent": "overwrite". */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseExistingKey(RequestOptions requestOptions) {
        return this.serviceClient.responseExistingKeyAsync(requestOptions);
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Send a post request with header value "Content-Type": "text/html"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramProtectedKeyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramProtectedKeyWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header value "Content-Type": "text/html".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>contentType</td><td>String</td><td>Yes</td><td>Send a post request with header value "Content-Type": "text/html"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramProtectedKey(RequestOptions requestOptions) {
        return this.serviceClient.paramProtectedKeyAsync(requestOptions);
    }

    /** Get a response with header value "Content-Type": "text/html". */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseProtectedKeyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseProtectedKeyWithResponseAsync(requestOptions);
    }

    /** Get a response with header value "Content-Type": "text/html". */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseProtectedKey(RequestOptions requestOptions) {
        return this.serviceClient.responseProtectedKeyAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>int</td><td>Yes</td><td>Send a post request with header values 1 or -2</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramIntegerWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramIntegerWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 1 or "scenario": "negative", "value": -2.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>int</td><td>Yes</td><td>Send a post request with header values 1 or -2</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramInteger(RequestOptions requestOptions) {
        return this.serviceClient.paramIntegerAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseIntegerWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseIntegerWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": 1 or -2.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseInteger(RequestOptions requestOptions) {
        return this.serviceClient.responseIntegerAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>long</td><td>Yes</td><td>Send a post request with header values 105 or -2</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramLongWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramLongWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 105 or "scenario": "negative", "value":
     * -2.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>long</td><td>Yes</td><td>Send a post request with header values 105 or -2</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramLong(RequestOptions requestOptions) {
        return this.serviceClient.paramLongAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseLongWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseLongWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": 105 or -2.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseLong(RequestOptions requestOptions) {
        return this.serviceClient.responseLongAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>float</td><td>Yes</td><td>Send a post request with header values 0.07 or -3.0</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramFloatWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramFloatWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 0.07 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>float</td><td>Yes</td><td>Send a post request with header values 0.07 or -3.0</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramFloat(RequestOptions requestOptions) {
        return this.serviceClient.paramFloatAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseFloatWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseFloatWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": 0.07 or -3.0.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseFloat(RequestOptions requestOptions) {
        return this.serviceClient.responseFloatAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>double</td><td>Yes</td><td>Send a post request with header values 7e120 or -3.0</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDoubleWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramDoubleWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "positive", "value": 7e120 or "scenario": "negative", "value":
     * -3.0.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     *     <tr><td>value</td><td>double</td><td>Yes</td><td>Send a post request with header values 7e120 or -3.0</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDouble(RequestOptions requestOptions) {
        return this.serviceClient.paramDoubleAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDoubleWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseDoubleWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": 7e120 or -3.0.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "positive" or "negative"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDouble(RequestOptions requestOptions) {
        return this.serviceClient.responseDoubleAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>value</td><td>boolean</td><td>Yes</td><td>Send a post request with header values true or false</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramBoolWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramBoolWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "true", "value": true or "scenario": "false", "value": false.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     *     <tr><td>value</td><td>boolean</td><td>Yes</td><td>Send a post request with header values true or false</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramBool(RequestOptions requestOptions) {
        return this.serviceClient.paramBoolAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseBoolWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseBoolWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header value "value": true or false.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "true" or "false"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseBool(RequestOptions requestOptions) {
        return this.serviceClient.responseBoolAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>No</td><td>Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or ""</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramStringWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramStringWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "The quick brown fox jumps over the lazy
     * dog" or "scenario": "null", "value": null or "scenario": "empty", "value": "".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>No</td><td>Send a post request with header values "The quick brown fox jumps over the lazy dog" or null or ""</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramString(RequestOptions requestOptions) {
        return this.serviceClient.paramStringAsync(requestOptions);
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseStringWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseStringWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header values "The quick brown fox jumps over the lazy dog" or null or "".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseString(RequestOptions requestOptions) {
        return this.serviceClient.responseStringAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Yes</td><td>Send a post request with header values "2010-01-01" or "0001-01-01"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDateWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramDateWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01" or "scenario": "min", "value":
     * "0001-01-01".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Yes</td><td>Send a post request with header values "2010-01-01" or "0001-01-01"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDate(RequestOptions requestOptions) {
        return this.serviceClient.paramDateAsync(requestOptions);
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDateWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseDateWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header values "2010-01-01" or "0001-01-01".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDate(RequestOptions requestOptions) {
        return this.serviceClient.responseDateAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Yes</td><td>Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramDatetimeWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "2010-01-01T12:34:56Z" or "scenario": "min",
     * "value": "0001-01-01T00:00:00Z".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Yes</td><td>Send a post request with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetime(RequestOptions requestOptions) {
        return this.serviceClient.paramDatetimeAsync(requestOptions);
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseDatetimeWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header values "2010-01-01T12:34:56Z" or "0001-01-01T00:00:00Z".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetime(RequestOptions requestOptions) {
        return this.serviceClient.responseDatetimeAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>No</td><td>Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDatetimeRfc1123WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramDatetimeRfc1123WithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "Wed, 01 Jan 2010 12:34:56 GMT" or
     * "scenario": "min", "value": "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     *     <tr><td>value</td><td>String</td><td>No</td><td>Send a post request with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDatetimeRfc1123(RequestOptions requestOptions) {
        return this.serviceClient.paramDatetimeRfc1123Async(requestOptions);
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDatetimeRfc1123WithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseDatetimeRfc1123WithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header values "Wed, 01 Jan 2010 12:34:56 GMT" or "Mon, 01 Jan 0001 00:00:00 GMT".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "min"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDatetimeRfc1123(RequestOptions requestOptions) {
        return this.serviceClient.responseDatetimeRfc1123Async(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Yes</td><td>Send a post request with header values "P123DT22H14M12.011S"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramDurationWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramDurationWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "P123DT22H14M12.011S".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Yes</td><td>Send a post request with header values "P123DT22H14M12.011S"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramDuration(RequestOptions requestOptions) {
        return this.serviceClient.paramDurationAsync(requestOptions);
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseDurationWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseDurationWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header values "P123DT22H14M12.011S".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseDuration(RequestOptions requestOptions) {
        return this.serviceClient.responseDurationAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Yes</td><td>Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramByteWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramByteWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     *     <tr><td>value</td><td>String</td><td>Yes</td><td>Send a post request with header values "啊齄丂狛狜隣郎隣兀﨩"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramByte(RequestOptions requestOptions) {
        return this.serviceClient.paramByteAsync(requestOptions);
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseByteWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseByteWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header values "啊齄丂狛狜隣郎隣兀﨩".
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseByte(RequestOptions requestOptions) {
        return this.serviceClient.responseByteAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>No</td><td>Send a post request with header values 'GREY' </td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> paramEnumWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.paramEnumWithResponseAsync(requestOptions);
    }

    /**
     * Send a post request with header values "scenario": "valid", "value": "GREY" or "scenario": "null", "value": null.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     *     <tr><td>value</td><td>String</td><td>No</td><td>Send a post request with header values 'GREY' </td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> paramEnum(RequestOptions requestOptions) {
        return this.serviceClient.paramEnumAsync(requestOptions);
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> responseEnumWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.responseEnumWithResponseAsync(requestOptions);
    }

    /**
     * Get a response with header values "GREY" or null.
     *
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>scenario</td><td>String</td><td>Yes</td><td>Send a post request with header values "scenario": "valid" or "null" or "empty"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> responseEnum(RequestOptions requestOptions) {
        return this.serviceClient.responseEnumAsync(requestOptions);
    }

    /** Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> customRequestIdWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.customRequestIdWithResponseAsync(requestOptions);
    }

    /** Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> customRequestId(RequestOptions requestOptions) {
        return this.serviceClient.customRequestIdAsync(requestOptions);
    }
}
