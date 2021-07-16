package fixtures.header;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
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
     * <p><strong>Header Parameters</strong>
     *
     * <table border="1">
     *     <caption>Header Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>userAgent</td><td>String</td><td>Yes</td><td>Send a post request with header value "User-Agent": "overwrite"</td></tr>
     * </table>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void paramExistingKey(RequestOptions requestOptions) {
        this.serviceClient.paramExistingKey(requestOptions);
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
    public Response<Void> paramExistingKeyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramExistingKeyWithResponse(requestOptions, context);
    }

    /** Get a response with header value "User-Agent": "overwrite". */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseExistingKey(RequestOptions requestOptions) {
        this.serviceClient.responseExistingKey(requestOptions);
    }

    /** Get a response with header value "User-Agent": "overwrite". */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseExistingKeyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseExistingKeyWithResponse(requestOptions, context);
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
    public void paramProtectedKey(RequestOptions requestOptions) {
        this.serviceClient.paramProtectedKey(requestOptions);
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
    public Response<Void> paramProtectedKeyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramProtectedKeyWithResponse(requestOptions, context);
    }

    /** Get a response with header value "Content-Type": "text/html". */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void responseProtectedKey(RequestOptions requestOptions) {
        this.serviceClient.responseProtectedKey(requestOptions);
    }

    /** Get a response with header value "Content-Type": "text/html". */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> responseProtectedKeyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseProtectedKeyWithResponse(requestOptions, context);
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
    public void paramInteger(RequestOptions requestOptions) {
        this.serviceClient.paramInteger(requestOptions);
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
    public Response<Void> paramIntegerWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramIntegerWithResponse(requestOptions, context);
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
    public void responseInteger(RequestOptions requestOptions) {
        this.serviceClient.responseInteger(requestOptions);
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
    public Response<Void> responseIntegerWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseIntegerWithResponse(requestOptions, context);
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
    public void paramLong(RequestOptions requestOptions) {
        this.serviceClient.paramLong(requestOptions);
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
    public Response<Void> paramLongWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramLongWithResponse(requestOptions, context);
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
    public void responseLong(RequestOptions requestOptions) {
        this.serviceClient.responseLong(requestOptions);
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
    public Response<Void> responseLongWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseLongWithResponse(requestOptions, context);
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
    public void paramFloat(RequestOptions requestOptions) {
        this.serviceClient.paramFloat(requestOptions);
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
    public Response<Void> paramFloatWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramFloatWithResponse(requestOptions, context);
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
    public void responseFloat(RequestOptions requestOptions) {
        this.serviceClient.responseFloat(requestOptions);
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
    public Response<Void> responseFloatWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseFloatWithResponse(requestOptions, context);
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
    public void paramDouble(RequestOptions requestOptions) {
        this.serviceClient.paramDouble(requestOptions);
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
    public Response<Void> paramDoubleWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDoubleWithResponse(requestOptions, context);
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
    public void responseDouble(RequestOptions requestOptions) {
        this.serviceClient.responseDouble(requestOptions);
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
    public Response<Void> responseDoubleWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDoubleWithResponse(requestOptions, context);
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
    public void paramBool(RequestOptions requestOptions) {
        this.serviceClient.paramBool(requestOptions);
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
    public Response<Void> paramBoolWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramBoolWithResponse(requestOptions, context);
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
    public void responseBool(RequestOptions requestOptions) {
        this.serviceClient.responseBool(requestOptions);
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
    public Response<Void> responseBoolWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseBoolWithResponse(requestOptions, context);
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
    public void paramString(RequestOptions requestOptions) {
        this.serviceClient.paramString(requestOptions);
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
    public Response<Void> paramStringWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramStringWithResponse(requestOptions, context);
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
    public void responseString(RequestOptions requestOptions) {
        this.serviceClient.responseString(requestOptions);
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
    public Response<Void> responseStringWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseStringWithResponse(requestOptions, context);
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
    public void paramDate(RequestOptions requestOptions) {
        this.serviceClient.paramDate(requestOptions);
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
    public Response<Void> paramDateWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDateWithResponse(requestOptions, context);
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
    public void responseDate(RequestOptions requestOptions) {
        this.serviceClient.responseDate(requestOptions);
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
    public Response<Void> responseDateWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDateWithResponse(requestOptions, context);
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
    public void paramDatetime(RequestOptions requestOptions) {
        this.serviceClient.paramDatetime(requestOptions);
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
    public Response<Void> paramDatetimeWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDatetimeWithResponse(requestOptions, context);
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
    public void responseDatetime(RequestOptions requestOptions) {
        this.serviceClient.responseDatetime(requestOptions);
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
    public Response<Void> responseDatetimeWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDatetimeWithResponse(requestOptions, context);
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
    public void paramDatetimeRfc1123(RequestOptions requestOptions) {
        this.serviceClient.paramDatetimeRfc1123(requestOptions);
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
    public Response<Void> paramDatetimeRfc1123WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDatetimeRfc1123WithResponse(requestOptions, context);
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
    public void responseDatetimeRfc1123(RequestOptions requestOptions) {
        this.serviceClient.responseDatetimeRfc1123(requestOptions);
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
    public Response<Void> responseDatetimeRfc1123WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDatetimeRfc1123WithResponse(requestOptions, context);
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
    public void paramDuration(RequestOptions requestOptions) {
        this.serviceClient.paramDuration(requestOptions);
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
    public Response<Void> paramDurationWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramDurationWithResponse(requestOptions, context);
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
    public void responseDuration(RequestOptions requestOptions) {
        this.serviceClient.responseDuration(requestOptions);
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
    public Response<Void> responseDurationWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseDurationWithResponse(requestOptions, context);
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
    public void paramByte(RequestOptions requestOptions) {
        this.serviceClient.paramByte(requestOptions);
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
    public Response<Void> paramByteWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramByteWithResponse(requestOptions, context);
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
    public void responseByte(RequestOptions requestOptions) {
        this.serviceClient.responseByte(requestOptions);
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
    public Response<Void> responseByteWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseByteWithResponse(requestOptions, context);
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
    public void paramEnum(RequestOptions requestOptions) {
        this.serviceClient.paramEnum(requestOptions);
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
    public Response<Void> paramEnumWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.paramEnumWithResponse(requestOptions, context);
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
    public void responseEnum(RequestOptions requestOptions) {
        this.serviceClient.responseEnum(requestOptions);
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
    public Response<Void> responseEnumWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.responseEnumWithResponse(requestOptions, context);
    }

    /** Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void customRequestId(RequestOptions requestOptions) {
        this.serviceClient.customRequestId(requestOptions);
    }

    /** Send x-ms-client-request-id = 9C4D50EE-2D56-4CD3-8152-34347DC9F2B0 in the header of the request. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> customRequestIdWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.customRequestIdWithResponse(requestOptions, context);
    }
}
