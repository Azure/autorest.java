package fixtures.url;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.url.implementation.QueriesImpl;

/** Initializes a new instance of the synchronous AutoRestUrlTestService type. */
@ServiceClient(builder = AutoRestUrlTestServiceBuilder.class)
public final class QueriesClient {
    private final QueriesImpl serviceClient;

    /**
     * Initializes an instance of Queries client.
     *
     * @param serviceClient the service client implementation.
     */
    QueriesClient(QueriesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>true boolean value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanTrue(RequestOptions requestOptions) {
        this.serviceClient.getBooleanTrue(requestOptions);
    }

    /**
     * Get true Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>true boolean value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanTrueWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getBooleanTrueWithResponse(requestOptions, context);
    }

    /**
     * Get false Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>false boolean value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanFalse(RequestOptions requestOptions) {
        this.serviceClient.getBooleanFalse(requestOptions);
    }

    /**
     * Get false Boolean value on path.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>boolean</td><td>Yes</td><td>false boolean value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return false Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanFalseWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getBooleanFalseWithResponse(requestOptions, context);
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>String</td><td>No</td><td>null boolean value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanNull(RequestOptions requestOptions) {
        this.serviceClient.getBooleanNull(requestOptions);
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>boolQuery</td><td>String</td><td>No</td><td>null boolean value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null Boolean value on query (query string should be absent).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getBooleanNullWithResponse(requestOptions, context);
    }

    /**
     * Get '1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'1000000' integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntOneMillion(RequestOptions requestOptions) {
        this.serviceClient.getIntOneMillion(requestOptions);
    }

    /**
     * Get '1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'1000000' integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntOneMillionWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getIntOneMillionWithResponse(requestOptions, context);
    }

    /**
     * Get '-1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'-1000000' integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntNegativeOneMillion(RequestOptions requestOptions) {
        this.serviceClient.getIntNegativeOneMillion(requestOptions);
    }

    /**
     * Get '-1000000' integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>int</td><td>Yes</td><td>'-1000000' integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntNegativeOneMillionWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getIntNegativeOneMillionWithResponse(requestOptions, context);
    }

    /**
     * Get null integer value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>String</td><td>No</td><td>null integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntNull(RequestOptions requestOptions) {
        this.serviceClient.getIntNull(requestOptions);
    }

    /**
     * Get null integer value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>intQuery</td><td>String</td><td>No</td><td>null integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null integer value (no query parameter).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getIntNullWithResponse(requestOptions, context);
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'10000000000' 64 bit integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getTenBillion(RequestOptions requestOptions) {
        this.serviceClient.getTenBillion(requestOptions);
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'10000000000' 64 bit integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getTenBillionWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getTenBillionWithResponse(requestOptions, context);
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'-10000000000' 64 bit integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getNegativeTenBillion(RequestOptions requestOptions) {
        this.serviceClient.getNegativeTenBillion(requestOptions);
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>long</td><td>Yes</td><td>'-10000000000' 64 bit integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getNegativeTenBillionWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getNegativeTenBillionWithResponse(requestOptions, context);
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>String</td><td>No</td><td>null 64 bit integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getLongNull(RequestOptions requestOptions) {
        this.serviceClient.getLongNull(requestOptions);
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>longQuery</td><td>String</td><td>No</td><td>null 64 bit integer value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 'null 64 bit integer value (no query param in uri).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getLongNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getLongNullWithResponse(requestOptions, context);
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'1.034E+20'numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificPositive(RequestOptions requestOptions) {
        this.serviceClient.floatScientificPositive(requestOptions);
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'1.034E+20'numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificPositiveWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.floatScientificPositiveWithResponse(requestOptions, context);
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'-1.034E-20'numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificNegative(RequestOptions requestOptions) {
        this.serviceClient.floatScientificNegative(requestOptions);
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>float</td><td>Yes</td><td>'-1.034E-20'numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-1.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificNegativeWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.floatScientificNegativeWithResponse(requestOptions, context);
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatNull(RequestOptions requestOptions) {
        this.serviceClient.floatNull(requestOptions);
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>floatQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null numeric value (no query parameter).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.floatNullWithResponse(requestOptions, context);
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'9999999.999'numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalPositive(RequestOptions requestOptions) {
        this.serviceClient.doubleDecimalPositive(requestOptions);
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'9999999.999'numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '9999999.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalPositiveWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.doubleDecimalPositiveWithResponse(requestOptions, context);
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'-9999999.999'numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalNegative(RequestOptions requestOptions) {
        this.serviceClient.doubleDecimalNegative(requestOptions);
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>double</td><td>Yes</td><td>'-9999999.999'numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '-9999999.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalNegativeWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.doubleDecimalNegativeWithResponse(requestOptions, context);
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleNull(RequestOptions requestOptions) {
        this.serviceClient.doubleNull(requestOptions);
    }

    /**
     * Get null numeric value (no query parameter).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>doubleQuery</td><td>String</td><td>No</td><td>null numeric value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null numeric value (no query parameter).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.doubleNullWithResponse(requestOptions, context);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'啊齄丂狛狜隣郎隣兀﨩'multi-byte string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUnicode(RequestOptions requestOptions) {
        this.serviceClient.stringUnicode(requestOptions);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'啊齄丂狛狜隣郎隣兀﨩'multi-byte string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUnicodeWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.stringUnicodeWithResponse(requestOptions, context);
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'begin!*'();:@ &amp;=+$,/?#[]end' url encoded string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUrlEncoded(RequestOptions requestOptions) {
        this.serviceClient.stringUrlEncoded(requestOptions);
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'begin!*'();:@ &amp;=+$,/?#[]end' url encoded string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 'begin!*'();:@ &amp;=+$,/?#[]end.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUrlEncodedWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.stringUrlEncodedWithResponse(requestOptions, context);
    }

    /**
     * Get ''.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'' string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringEmpty(RequestOptions requestOptions) {
        this.serviceClient.stringEmpty(requestOptions);
    }

    /**
     * Get ''.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>Yes</td><td>'' string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.stringEmptyWithResponse(requestOptions, context);
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringNull(RequestOptions requestOptions) {
        this.serviceClient.stringNull(requestOptions);
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>stringQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null (no query parameter in url).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.stringNullWithResponse(requestOptions, context);
    }

    /**
     * Get using uri with query parameter 'green color'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>'green color' enum value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumValid(RequestOptions requestOptions) {
        this.serviceClient.enumValid(requestOptions);
    }

    /**
     * Get using uri with query parameter 'green color'.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>'green color' enum value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return using uri with query parameter 'green color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.enumValidWithResponse(requestOptions, context);
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumNull(RequestOptions requestOptions) {
        this.serviceClient.enumNull(requestOptions);
    }

    /**
     * Get null (no query parameter in url).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>enumQuery</td><td>String</td><td>No</td><td>null string value</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null (no query parameter in url).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.enumNullWithResponse(requestOptions, context);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>'啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteMultiByte(RequestOptions requestOptions) {
        this.serviceClient.byteMultiByte(requestOptions);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>'啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteMultiByteWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.byteMultiByteWithResponse(requestOptions, context);
    }

    /**
     * Get '' as byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>Yes</td><td>'' as byte array</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteEmpty(RequestOptions requestOptions) {
        this.serviceClient.byteEmpty(requestOptions);
    }

    /**
     * Get '' as byte array.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>Yes</td><td>'' as byte array</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '' as byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.byteEmptyWithResponse(requestOptions, context);
    }

    /**
     * Get null as byte array (no query parameters in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>null as byte array (no query parameters in uri)</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteNull(RequestOptions requestOptions) {
        this.serviceClient.byteNull(requestOptions);
    }

    /**
     * Get null as byte array (no query parameters in uri).
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>byteQuery</td><td>String</td><td>No</td><td>null as byte array (no query parameters in uri)</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as byte array (no query parameters in uri).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.byteNullWithResponse(requestOptions, context);
    }

    /**
     * Get '2012-01-01' as date.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>Yes</td><td>'2012-01-01' as date</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateValid(RequestOptions requestOptions) {
        this.serviceClient.dateValid(requestOptions);
    }

    /**
     * Get '2012-01-01' as date.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>Yes</td><td>'2012-01-01' as date</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '2012-01-01' as date.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.dateValidWithResponse(requestOptions, context);
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>No</td><td>null as date (no query parameters in uri)</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateNull(RequestOptions requestOptions) {
        this.serviceClient.dateNull(requestOptions);
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateQuery</td><td>String</td><td>No</td><td>null as date (no query parameters in uri)</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as date - this should result in no query parameters in uri.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.dateNullWithResponse(requestOptions, context);
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>Yes</td><td>'2012-01-01T01:01:01Z' as date-time</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeValid(RequestOptions requestOptions) {
        this.serviceClient.dateTimeValid(requestOptions);
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>Yes</td><td>'2012-01-01T01:01:01Z' as date-time</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return '2012-01-01T01:01:01Z' as date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.dateTimeValidWithResponse(requestOptions, context);
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>No</td><td>null as date-time (no query parameters)</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeNull(RequestOptions requestOptions) {
        this.serviceClient.dateTimeNull(requestOptions);
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>dateTimeQuery</td><td>String</td><td>No</td><td>null as date-time (no query parameters)</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return null as date-time, should result in no query parameters in uri.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.dateTimeNullWithResponse(requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringCsvValid(RequestOptions requestOptions) {
        this.serviceClient.arrayStringCsvValid(requestOptions);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringCsvValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.arrayStringCsvValidWithResponse(requestOptions, context);
    }

    /**
     * Get a null array of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>a null array of string using the csv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringCsvNull(RequestOptions requestOptions) {
        this.serviceClient.arrayStringCsvNull(requestOptions);
    }

    /**
     * Get a null array of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>a null array of string using the csv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return a null array of string using the csv-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringCsvNullWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.arrayStringCsvNullWithResponse(requestOptions, context);
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an empty array [] of string using the csv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringCsvEmpty(RequestOptions requestOptions) {
        this.serviceClient.arrayStringCsvEmpty(requestOptions);
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an empty array [] of string using the csv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return an empty array [] of string using the csv-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringCsvEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.arrayStringCsvEmptyWithResponse(requestOptions, context);
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in ['hello', 'nihao', 'bonjour'] for
     * the 'arrayQuery' parameter to the service.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>Array-typed query parameter. Pass in ['hello', 'nihao', 'bonjour'].</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringNoCollectionFormatEmpty(RequestOptions requestOptions) {
        this.serviceClient.arrayStringNoCollectionFormatEmpty(requestOptions);
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in ['hello', 'nihao', 'bonjour'] for
     * the 'arrayQuery' parameter to the service.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>Array-typed query parameter. Pass in ['hello', 'nihao', 'bonjour'].</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringNoCollectionFormatEmptyWithResponse(
            RequestOptions requestOptions, Context context) {
        return this.serviceClient.arrayStringNoCollectionFormatEmptyWithResponse(requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringSsvValid(RequestOptions requestOptions) {
        this.serviceClient.arrayStringSsvValid(requestOptions);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringSsvValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.arrayStringSsvValidWithResponse(requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringTsvValid(RequestOptions requestOptions) {
        this.serviceClient.arrayStringTsvValid(requestOptions);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringTsvValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.arrayStringTsvValidWithResponse(requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     * format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayStringPipesValid(RequestOptions requestOptions) {
        this.serviceClient.arrayStringPipesValid(requestOptions);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     * format.
     *
     * <p><strong>Query Parameters</strong>
     *
     * <table border="1">
     *     <caption>Query Parameters</caption>
     *     <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>
     *     <tr><td>arrayQuery</td><td>String</td><td>No</td><td>an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format</td></tr>
     * </table>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayStringPipesValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.arrayStringPipesValidWithResponse(requestOptions, context);
    }
}
