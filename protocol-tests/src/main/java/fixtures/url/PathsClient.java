package fixtures.url;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.url.implementation.PathsImpl;

/** Initializes a new instance of the synchronous AutoRestUrlTestService type. */
@ServiceClient(builder = AutoRestUrlTestServiceBuilder.class)
public final class PathsClient {
    private final PathsImpl serviceClient;

    /**
     * Initializes an instance of Paths client.
     *
     * @param serviceClient the service client implementation.
     */
    PathsClient(PathsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /** Get true Boolean value on path. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanTrue(RequestOptions requestOptions) {
        this.serviceClient.getBooleanTrue(requestOptions);
    }

    /** Get true Boolean value on path. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanTrueWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getBooleanTrueWithResponse(requestOptions, context);
    }

    /** Get false Boolean value on path. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getBooleanFalse(RequestOptions requestOptions) {
        this.serviceClient.getBooleanFalse(requestOptions);
    }

    /** Get false Boolean value on path. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanFalseWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getBooleanFalseWithResponse(requestOptions, context);
    }

    /** Get '1000000' integer value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntOneMillion(RequestOptions requestOptions) {
        this.serviceClient.getIntOneMillion(requestOptions);
    }

    /** Get '1000000' integer value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntOneMillionWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getIntOneMillionWithResponse(requestOptions, context);
    }

    /** Get '-1000000' integer value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getIntNegativeOneMillion(RequestOptions requestOptions) {
        this.serviceClient.getIntNegativeOneMillion(requestOptions);
    }

    /** Get '-1000000' integer value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntNegativeOneMillionWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getIntNegativeOneMillionWithResponse(requestOptions, context);
    }

    /** Get '10000000000' 64 bit integer value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getTenBillion(RequestOptions requestOptions) {
        this.serviceClient.getTenBillion(requestOptions);
    }

    /** Get '10000000000' 64 bit integer value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getTenBillionWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getTenBillionWithResponse(requestOptions, context);
    }

    /** Get '-10000000000' 64 bit integer value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getNegativeTenBillion(RequestOptions requestOptions) {
        this.serviceClient.getNegativeTenBillion(requestOptions);
    }

    /** Get '-10000000000' 64 bit integer value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getNegativeTenBillionWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.getNegativeTenBillionWithResponse(requestOptions, context);
    }

    /** Get '1.034E+20' numeric value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificPositive(RequestOptions requestOptions) {
        this.serviceClient.floatScientificPositive(requestOptions);
    }

    /** Get '1.034E+20' numeric value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificPositiveWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.floatScientificPositiveWithResponse(requestOptions, context);
    }

    /** Get '-1.034E-20' numeric value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void floatScientificNegative(RequestOptions requestOptions) {
        this.serviceClient.floatScientificNegative(requestOptions);
    }

    /** Get '-1.034E-20' numeric value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificNegativeWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.floatScientificNegativeWithResponse(requestOptions, context);
    }

    /** Get '9999999.999' numeric value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalPositive(RequestOptions requestOptions) {
        this.serviceClient.doubleDecimalPositive(requestOptions);
    }

    /** Get '9999999.999' numeric value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalPositiveWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.doubleDecimalPositiveWithResponse(requestOptions, context);
    }

    /** Get '-9999999.999' numeric value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void doubleDecimalNegative(RequestOptions requestOptions) {
        this.serviceClient.doubleDecimalNegative(requestOptions);
    }

    /** Get '-9999999.999' numeric value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalNegativeWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.doubleDecimalNegativeWithResponse(requestOptions, context);
    }

    /** Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUnicode(RequestOptions requestOptions) {
        this.serviceClient.stringUnicode(requestOptions);
    }

    /** Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUnicodeWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.stringUnicodeWithResponse(requestOptions, context);
    }

    /** Get 'begin!*'();:@ &amp;=+$,/?#[]end. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUrlEncoded(RequestOptions requestOptions) {
        this.serviceClient.stringUrlEncoded(requestOptions);
    }

    /** Get 'begin!*'();:@ &amp;=+$,/?#[]end. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUrlEncodedWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.stringUrlEncodedWithResponse(requestOptions, context);
    }

    /** https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringUrlNonEncoded(RequestOptions requestOptions) {
        this.serviceClient.stringUrlNonEncoded(requestOptions);
    }

    /** https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUrlNonEncodedWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.stringUrlNonEncodedWithResponse(requestOptions, context);
    }

    /** Get ''. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringEmpty(RequestOptions requestOptions) {
        this.serviceClient.stringEmpty(requestOptions);
    }

    /** Get ''. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.stringEmptyWithResponse(requestOptions, context);
    }

    /**
     * Get null (should throw).
     *
     * @param stringPath null string value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void stringNull(String stringPath, RequestOptions requestOptions) {
        this.serviceClient.stringNull(stringPath, requestOptions);
    }

    /**
     * Get null (should throw).
     *
     * @param stringPath null string value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringNullWithResponse(String stringPath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.stringNullWithResponse(stringPath, requestOptions, context);
    }

    /**
     * Get using uri with 'green color' in path parameter.
     *
     * @param enumPath send the value green.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumValid(String enumPath, RequestOptions requestOptions) {
        this.serviceClient.enumValid(enumPath, requestOptions);
    }

    /**
     * Get using uri with 'green color' in path parameter.
     *
     * @param enumPath send the value green.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumValidWithResponse(String enumPath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.enumValidWithResponse(enumPath, requestOptions, context);
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     *
     * @param enumPath send null should throw.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void enumNull(String enumPath, RequestOptions requestOptions) {
        this.serviceClient.enumNull(enumPath, requestOptions);
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     *
     * @param enumPath send null should throw.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumNullWithResponse(String enumPath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.enumNullWithResponse(enumPath, requestOptions, context);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteMultiByte(String bytePath, RequestOptions requestOptions) {
        this.serviceClient.byteMultiByte(bytePath, requestOptions);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteMultiByteWithResponse(String bytePath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.byteMultiByteWithResponse(bytePath, requestOptions, context);
    }

    /** Get '' as byte array. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteEmpty(RequestOptions requestOptions) {
        this.serviceClient.byteEmpty(requestOptions);
    }

    /** Get '' as byte array. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteEmptyWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.byteEmptyWithResponse(requestOptions, context);
    }

    /**
     * Get null as byte array (should throw).
     *
     * @param bytePath null as byte array (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void byteNull(String bytePath, RequestOptions requestOptions) {
        this.serviceClient.byteNull(bytePath, requestOptions);
    }

    /**
     * Get null as byte array (should throw).
     *
     * @param bytePath null as byte array (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteNullWithResponse(String bytePath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.byteNullWithResponse(bytePath, requestOptions, context);
    }

    /** Get '2012-01-01' as date. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateValid(RequestOptions requestOptions) {
        this.serviceClient.dateValid(requestOptions);
    }

    /** Get '2012-01-01' as date. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.dateValidWithResponse(requestOptions, context);
    }

    /**
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     *
     * @param datePath null as date (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateNull(String datePath, RequestOptions requestOptions) {
        this.serviceClient.dateNull(datePath, requestOptions);
    }

    /**
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     *
     * @param datePath null as date (should throw).
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateNullWithResponse(String datePath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.dateNullWithResponse(datePath, requestOptions, context);
    }

    /** Get '2012-01-01T01:01:01Z' as date-time. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeValid(RequestOptions requestOptions) {
        this.serviceClient.dateTimeValid(requestOptions);
    }

    /** Get '2012-01-01T01:01:01Z' as date-time. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeValidWithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.dateTimeValidWithResponse(requestOptions, context);
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     *
     * @param dateTimePath null as date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void dateTimeNull(String dateTimePath, RequestOptions requestOptions) {
        this.serviceClient.dateTimeNull(dateTimePath, requestOptions);
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     *
     * @param dateTimePath null as date-time.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeNullWithResponse(
            String dateTimePath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.dateTimeNullWithResponse(dateTimePath, requestOptions, context);
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     *
     * @param base64UrlPath base64url encoded value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void base64Url(String base64UrlPath, RequestOptions requestOptions) {
        this.serviceClient.base64Url(base64UrlPath, requestOptions);
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     *
     * @param base64UrlPath base64url encoded value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> base64UrlWithResponse(String base64UrlPath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.base64UrlWithResponse(base64UrlPath, requestOptions, context);
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * @param arrayPath an array of string ['ArrayPath1', 'begin!*'();:@ &amp;amp;=+$,/?#[]end' , null, ''] using the
     *     csv-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void arrayCsvInPath(String arrayPath, RequestOptions requestOptions) {
        this.serviceClient.arrayCsvInPath(arrayPath, requestOptions);
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * @param arrayPath an array of string ['ArrayPath1', 'begin!*'();:@ &amp;amp;=+$,/?#[]end' , null, ''] using the
     *     csv-array format.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayCsvInPathWithResponse(String arrayPath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.arrayCsvInPathWithResponse(arrayPath, requestOptions, context);
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     *
     * @param unixTimeUrlPath Unix time encoded value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void unixTimeUrl(long unixTimeUrlPath, RequestOptions requestOptions) {
        this.serviceClient.unixTimeUrl(unixTimeUrlPath, requestOptions);
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     *
     * @param unixTimeUrlPath Unix time encoded value.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> unixTimeUrlWithResponse(
            long unixTimeUrlPath, RequestOptions requestOptions, Context context) {
        return this.serviceClient.unixTimeUrlWithResponse(unixTimeUrlPath, requestOptions, context);
    }
}
