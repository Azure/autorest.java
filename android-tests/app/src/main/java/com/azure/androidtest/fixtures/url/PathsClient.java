package com.azure.androidtest.fixtures.url;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.internal.util.serializer.JacksonAdapter;
import com.azure.android.core.internal.util.serializer.SerializerAdapter;
import com.azure.android.core.util.Base64Url;
import com.azure.android.core.util.Base64Util;
import com.azure.androidtest.fixtures.url.implementation.AutoRestUrlTestServiceImpl;
import com.azure.androidtest.fixtures.url.implementation.PathsImpl;
import com.azure.androidtest.fixtures.url.models.ErrorException;
import com.azure.androidtest.fixtures.url.models.UriColor;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.ResponseBody;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Initializes a new instance of the synchronous AutoRestUrlTestService type.
 */
public final class PathsClient {
    private PathsImpl serviceClient;

    /**
     * Initializes an instance of Paths client.
     */
    PathsClient(PathsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get true Boolean value on path.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return true Boolean value on path.
     */
    public Response<Void> getBooleanTrueWithRestResponse() {
        return this.serviceClient.getBooleanTrueWithRestResponse();
    }

    /**
     * Get false Boolean value on path.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return false Boolean value on path.
     */
    public Response<Void> getBooleanFalseWithRestResponse() {
        return this.serviceClient.getBooleanFalseWithRestResponse();
    }

    /**
     * Get '1000000' integer value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '1000000' integer value.
     */
    public Response<Void> getIntOneMillionWithRestResponse() {
        return this.serviceClient.getIntOneMillionWithRestResponse();
    }

    /**
     * Get '-1000000' integer value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '-1000000' integer value.
     */
    public Response<Void> getIntNegativeOneMillionWithRestResponse() {
        return this.serviceClient.getIntNegativeOneMillionWithRestResponse();
    }

    /**
     * Get '10000000000' 64 bit integer value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '10000000000' 64 bit integer value.
     */
    public Response<Void> getTenBillionWithRestResponse() {
        return this.serviceClient.getTenBillionWithRestResponse();
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '-10000000000' 64 bit integer value.
     */
    public Response<Void> getNegativeTenBillionWithRestResponse() {
        return this.serviceClient.getNegativeTenBillionWithRestResponse();
    }

    /**
     * Get '1.034E+20' numeric value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '1.
     */
    public Response<Void> floatScientificPositiveWithRestResponse() {
        return this.serviceClient.floatScientificPositiveWithRestResponse();
    }

    /**
     * Get '-1.034E-20' numeric value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '-1.
     */
    public Response<Void> floatScientificNegativeWithRestResponse() {
        return this.serviceClient.floatScientificNegativeWithRestResponse();
    }

    /**
     * Get '9999999.999' numeric value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '9999999.
     */
    public Response<Void> doubleDecimalPositiveWithRestResponse() {
        return this.serviceClient.doubleDecimalPositiveWithRestResponse();
    }

    /**
     * Get '-9999999.999' numeric value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '-9999999.
     */
    public Response<Void> doubleDecimalNegativeWithRestResponse() {
        return this.serviceClient.doubleDecimalNegativeWithRestResponse();
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     */
    public Response<Void> stringUnicodeWithRestResponse() {
        return this.serviceClient.stringUnicodeWithRestResponse();
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return 'begin!*'();:@ &amp;=+$,/?#[]end.
     */
    public Response<Void> stringUrlEncodedWithRestResponse() {
        return this.serviceClient.stringUrlEncodedWithRestResponse();
    }

    /**
     * https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not
     * encoded.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return the completion.
     */
    public Response<Void> stringUrlNonEncodedWithRestResponse() {
        return this.serviceClient.stringUrlNonEncodedWithRestResponse();
    }

    /**
     * Get ''.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return ''.
     */
    public Response<Void> stringEmptyWithRestResponse() {
        return this.serviceClient.stringEmptyWithRestResponse();
    }

    /**
     * Get null (should throw).
     * 
     * @param stringPath null string value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null (should throw).
     */
    public Response<Void> stringNullWithRestResponse(String stringPath) {
        return this.serviceClient.stringNullWithRestResponse(stringPath);
    }

    /**
     * Get using uri with 'green color' in path parameter.
     * 
     * @param enumPath send the value green.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return using uri with 'green color' in path parameter.
     */
    public Response<Void> enumValidWithRestResponse(UriColor enumPath) {
        return this.serviceClient.enumValidWithRestResponse(enumPath);
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     * 
     * @param enumPath send null should throw.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null (should throw on the client before the request is sent on wire).
     */
    public Response<Void> enumNullWithRestResponse(UriColor enumPath) {
        return this.serviceClient.enumNullWithRestResponse(enumPath);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * 
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    public Response<Void> byteMultiByteWithRestResponse(byte[] bytePath) {
        return this.serviceClient.byteMultiByteWithRestResponse(bytePath);
    }

    /**
     * Get '' as byte array.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '' as byte array.
     */
    public Response<Void> byteEmptyWithRestResponse() {
        return this.serviceClient.byteEmptyWithRestResponse();
    }

    /**
     * Get null as byte array (should throw).
     * 
     * @param bytePath null as byte array (should throw).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null as byte array (should throw).
     */
    public Response<Void> byteNullWithRestResponse(byte[] bytePath) {
        return this.serviceClient.byteNullWithRestResponse(bytePath);
    }

    /**
     * Get '2012-01-01' as date.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '2012-01-01' as date.
     */
    public Response<Void> dateValidWithRestResponse() {
        return this.serviceClient.dateValidWithRestResponse();
    }

    /**
     * Get null as date - this should throw or be unusable on the client side,
     * depending on date representation.
     * 
     * @param datePath null as date (should throw).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null as date - this should throw or be unusable on the client side,
     *         depending on date representation.
     */
    public Response<Void> dateNullWithRestResponse(LocalDate datePath) {
        return this.serviceClient.dateNullWithRestResponse(datePath);
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return '2012-01-01T01:01:01Z' as date-time.
     */
    public Response<Void> dateTimeValidWithRestResponse() {
        return this.serviceClient.dateTimeValidWithRestResponse();
    }

    /**
     * Get null as date-time, should be disallowed or throw depending on
     * representation of date-time.
     * 
     * @param dateTimePath null as date-time.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null as date-time, should be disallowed or throw depending on
     *         representation of date-time.
     */
    public Response<Void> dateTimeNullWithRestResponse(OffsetDateTime dateTimePath) {
        return this.serviceClient.dateTimeNullWithRestResponse(dateTimePath);
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     * 
     * @param base64UrlPath base64url encoded value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return 'lorem' encoded value as 'bG9yZW0' (base64url).
     */
    public Response<Void> base64UrlWithRestResponse(Base64Url base64UrlPath) {
        return this.serviceClient.base64UrlWithRestResponse(base64UrlPath);
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' ,
     * null, ''] using the csv-array format.
     * 
     * @param arrayPath Array of Get0ItemsItem.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' ,
     *         null, ''] using the csv-array format.
     */
    public Response<Void> arrayCsvInPathWithRestResponse(List<String> arrayPath) {
        return this.serviceClient.arrayCsvInPathWithRestResponse(arrayPath);
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     * 
     * @param unixTimeUrlPath date in seconds since 1970-01-01T00:00:00Z.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the date 2016-04-13 encoded value as '1460505600' (Unix time).
     */
    public Response<Void> unixTimeUrlWithRestResponse(OffsetDateTime unixTimeUrlPath) {
        return this.serviceClient.unixTimeUrlWithRestResponse(unixTimeUrlPath);
    }

    /**
     * A builder for creating a new instance of the PathsClient type.
     */
    public static final class Builder {
        /*
         * A string value 'globalItemStringPath' that appears in the path
         */
        private String globalStringPath;

        /**
         * Sets A string value 'globalItemStringPath' that appears in the path.
         * 
         * @param globalStringPath the globalStringPath value.
         * @return the Builder.
         */
        public Builder globalStringPath(String globalStringPath) {
            this.globalStringPath = globalStringPath;
            return this;
        }

        /*
         * should contain value null
         */
        private String globalStringQuery;

        /**
         * Sets should contain value null.
         * 
         * @param globalStringQuery the globalStringQuery value.
         * @return the Builder.
         */
        public Builder globalStringQuery(String globalStringQuery) {
            this.globalStringQuery = globalStringQuery;
            return this;
        }

        /*
         * server parameter
         */
        private String host;

        /**
         * Sets server parameter.
         * 
         * @param host the host value.
         * @return the Builder.
         */
        public Builder host(String host) {
            this.host = host;
            return this;
        }

        /*
         * The Azure Core generic ServiceClient Builder.
         */
        private ServiceClient.Builder serviceClientBuilder;

        /**
         * Sets The Azure Core generic ServiceClient Builder.
         * 
         * @param serviceClientBuilder the serviceClientBuilder value.
         * @return the Builder.
         */
        public Builder serviceClientBuilder(ServiceClient.Builder serviceClientBuilder) {
            this.serviceClientBuilder = serviceClientBuilder;
            return this;
        }

        /*
         * The Interceptor to set intercept request and set credentials.
         */
        private Interceptor credentialInterceptor;

        /**
         * Sets The Interceptor to set intercept request and set credentials.
         * 
         * @param credentialInterceptor the credentialInterceptor value.
         * @return the Builder.
         */
        public Builder credentialInterceptor(Interceptor credentialInterceptor) {
            this.credentialInterceptor = credentialInterceptor;
            return this;
        }

        /**
         * Builds an instance of PathsClient with the provided parameters.
         * 
         * @return an instance of PathsClient.
         */
        public PathsClient build() {
            if (host == null) {
                this.host = "http://localhost:3000";
            }
            if (serviceClientBuilder == null) {
                this.serviceClientBuilder = new ServiceClient.Builder();
            }
            serviceClientBuilder.setBaseUrl(host);
            if (credentialInterceptor != null) {
                serviceClientBuilder.setCredentialsInterceptor(credentialInterceptor);
            }
            AutoRestUrlTestServiceImpl internalClient = new AutoRestUrlTestServiceImpl(serviceClientBuilder.build(),
                    globalStringPath, globalStringQuery, host);
            return new PathsClient(internalClient.getPaths());
        }
    }
}
