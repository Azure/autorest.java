package fixtures.url;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.url.implementation.QueriesImpl;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the asynchronous AutoRestUrlTestService type. */
@ServiceClient(builder = AutoRestUrlTestServiceBuilder.class, isAsync = true)
public final class QueriesAsyncClient {
    private final QueriesImpl serviceClient;

    /**
     * Initializes an instance of Queries client.
     *
     * @param serviceClient the service client implementation.
     */
    QueriesAsyncClient(QueriesImpl serviceClient) {
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return true Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanTrueWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getBooleanTrueWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return false Boolean value on path.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanFalseWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getBooleanFalseWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return null Boolean value on query (query string should be absent).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getBooleanNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getBooleanNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntOneMillionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getIntOneMillionWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '-1000000' integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNegativeOneMillionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getIntNegativeOneMillionWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return null integer value (no query parameter).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getIntNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getIntNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getTenBillionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getTenBillionWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '-10000000000' 64 bit integer value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getNegativeTenBillionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNegativeTenBillionWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return 'null 64 bit integer value (no query param in uri).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> getLongNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getLongNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '1.034E+20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificPositiveWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.floatScientificPositiveWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '-1.034E-20' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatScientificNegativeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.floatScientificNegativeWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return null numeric value (no query parameter).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> floatNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.floatNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalPositiveWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.doubleDecimalPositiveWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '-9999999.999' numeric value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleDecimalNegativeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.doubleDecimalNegativeWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return null numeric value (no query parameter).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> doubleNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.doubleNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUnicodeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.stringUnicodeWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return 'begin!*'();:@ &amp;=+$,/?#[]end.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringUrlEncodedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.stringUrlEncodedWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return ''.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.stringEmptyWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return null (no query parameter in url).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> stringNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.stringNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return using uri with query parameter 'green color'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.enumValidWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return null (no query parameter in url).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> enumNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.enumNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteMultiByteWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.byteMultiByteWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '' as byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.byteEmptyWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return null as byte array (no query parameters in uri).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> byteNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.byteNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '2012-01-01' as date.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.dateValidWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return null as date - this should result in no query parameters in uri.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.dateNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return '2012-01-01T01:01:01Z' as date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.dateTimeValidWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return null as date-time, should result in no query parameters in uri.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> dateTimeNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.dateTimeNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringCsvValidWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return a null array of string using the csv-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvNullWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringCsvNullWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return an empty array [] of string using the csv-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringCsvEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringCsvEmptyWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringNoCollectionFormatEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringNoCollectionFormatEmptyWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringSsvValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringSsvValidWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringTsvValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringTsvValidWithResponseAsync(requestOptions);
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
     * @throws HttpResponseException thrown if status code is 400 or above, if throwOnError in requestOptions is not
     *     false.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array
     *     format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> arrayStringPipesValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.arrayStringPipesValidWithResponseAsync(requestOptions);
    }
}
