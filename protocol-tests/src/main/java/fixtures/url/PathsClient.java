// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.url;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import fixtures.url.implementation.PathsImpl;

/** Initializes a new instance of the synchronous AutoRestUrlTestService type. */
@ServiceClient(builder = AutoRestUrlTestServiceBuilder.class)
public final class PathsClient {
    @Generated private final PathsImpl serviceClient;

    /**
     * Initializes an instance of Paths client.
     *
     * @param serviceClient the service client implementation.
     */
    @Generated
    PathsClient(PathsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get true Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return true Boolean value on path.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanTrueWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getBooleanTrueWithResponse(requestOptions);
    }

    /**
     * Get false Boolean value on path.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return false Boolean value on path.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getBooleanFalseWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getBooleanFalseWithResponse(requestOptions);
    }

    /**
     * Get '1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '1000000' integer value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntOneMillionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getIntOneMillionWithResponse(requestOptions);
    }

    /**
     * Get '-1000000' integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '-1000000' integer value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getIntNegativeOneMillionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getIntNegativeOneMillionWithResponse(requestOptions);
    }

    /**
     * Get '10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '10000000000' 64 bit integer value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getTenBillionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getTenBillionWithResponse(requestOptions);
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '-10000000000' 64 bit integer value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> getNegativeTenBillionWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.getNegativeTenBillionWithResponse(requestOptions);
    }

    /**
     * Get '1.034E+20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '1.034E+20' numeric value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificPositiveWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.floatScientificPositiveWithResponse(requestOptions);
    }

    /**
     * Get '-1.034E-20' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '-1.034E-20' numeric value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> floatScientificNegativeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.floatScientificNegativeWithResponse(requestOptions);
    }

    /**
     * Get '9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '9999999.999' numeric value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalPositiveWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.doubleDecimalPositiveWithResponse(requestOptions);
    }

    /**
     * Get '-9999999.999' numeric value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '-9999999.999' numeric value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> doubleDecimalNegativeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.doubleDecimalNegativeWithResponse(requestOptions);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUnicodeWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.stringUnicodeWithResponse(requestOptions);
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return 'begin!*'();:@ &amp;=+$,/?#[]end.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUrlEncodedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.stringUrlEncodedWithResponse(requestOptions);
    }

    /**
     * https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the response.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringUrlNonEncodedWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.stringUrlNonEncodedWithResponse(requestOptions);
    }

    /**
     * Get ''.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return ''.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.stringEmptyWithResponse(requestOptions);
    }

    /**
     * Get null (should throw).
     *
     * @param stringPath null string value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return null (should throw).
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> stringNullWithResponse(String stringPath, RequestOptions requestOptions) {
        return this.serviceClient.stringNullWithResponse(stringPath, requestOptions);
    }

    /**
     * Get using uri with 'green color' in path parameter.
     *
     * @param enumPath send the value green.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return using uri with 'green color' in path parameter.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumValidWithResponse(String enumPath, RequestOptions requestOptions) {
        return this.serviceClient.enumValidWithResponse(enumPath, requestOptions);
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     *
     * @param enumPath send null should throw.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return null (should throw on the client before the request is sent on wire).
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> enumNullWithResponse(String enumPath, RequestOptions requestOptions) {
        return this.serviceClient.enumNullWithResponse(enumPath, requestOptions);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     *
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteMultiByteWithResponse(String bytePath, RequestOptions requestOptions) {
        return this.serviceClient.byteMultiByteWithResponse(bytePath, requestOptions);
    }

    /**
     * Get '' as byte array.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '' as byte array.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteEmptyWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.byteEmptyWithResponse(requestOptions);
    }

    /**
     * Get null as byte array (should throw).
     *
     * @param bytePath null as byte array (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return null as byte array (should throw).
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> byteNullWithResponse(String bytePath, RequestOptions requestOptions) {
        return this.serviceClient.byteNullWithResponse(bytePath, requestOptions);
    }

    /**
     * Get '2012-01-01' as date.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '2012-01-01' as date.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.dateValidWithResponse(requestOptions);
    }

    /**
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     *
     * @param datePath null as date (should throw).
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return null as date - this should throw or be unusable on the client side, depending on date representation.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateNullWithResponse(String datePath, RequestOptions requestOptions) {
        return this.serviceClient.dateNullWithResponse(datePath, requestOptions);
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return '2012-01-01T01:01:01Z' as date-time.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeValidWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.dateTimeValidWithResponse(requestOptions);
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     *
     * @param dateTimePath null as date-time.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return null as date-time, should be disallowed or throw depending on representation of date-time.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> dateTimeNullWithResponse(String dateTimePath, RequestOptions requestOptions) {
        return this.serviceClient.dateTimeNullWithResponse(dateTimePath, requestOptions);
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     *
     * @param base64UrlPath base64url encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return 'lorem' encoded value as 'bG9yZW0' (base64url).
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> base64UrlWithResponse(String base64UrlPath, RequestOptions requestOptions) {
        return this.serviceClient.base64UrlWithResponse(base64UrlPath, requestOptions);
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     *
     * @param arrayPath an array of string ['ArrayPath1', 'begin!*'();:@ &amp;amp;=+$,/?#[]end' , null, ''] using the
     *     csv-array format.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array
     *     format.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> arrayCsvInPathWithResponse(String arrayPath, RequestOptions requestOptions) {
        return this.serviceClient.arrayCsvInPathWithResponse(arrayPath, requestOptions);
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     *
     * @param unixTimeUrlPath Unix time encoded value.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @return the date 2016-04-13 encoded value as '1460505600' (Unix time).
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> unixTimeUrlWithResponse(long unixTimeUrlPath, RequestOptions requestOptions) {
        return this.serviceClient.unixTimeUrlWithResponse(unixTimeUrlPath, requestOptions);
    }
}
