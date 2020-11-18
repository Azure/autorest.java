package com.azure.androidtest.fixtures.url;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.internal.util.serializer.JacksonAdapter;
import com.azure.android.core.internal.util.serializer.SerializerAdapter;
import com.azure.android.core.util.Base64Util;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.url.implementation.AutoRestUrlTestServiceImpl;
import com.azure.androidtest.fixtures.url.implementation.QueriesImpl;
import com.azure.androidtest.fixtures.url.models.ErrorException;
import com.azure.androidtest.fixtures.url.models.UriColor;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.ResponseBody;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Initializes a new instance of the synchronous AutoRestUrlTestService type.
 */
public final class QueriesClient {
    private QueriesImpl serviceClient;

    /**
     * Initializes an instance of Queries client.
     */
    QueriesClient(QueriesImpl serviceClient) {
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
     * Get null Boolean value on query (query string should be absent).
     * 
     * @param boolQuery null boolean value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null Boolean value on query (query string should be absent).
     */
    public Response<Void> getBooleanNullWithRestResponse(Boolean boolQuery) {
        return this.serviceClient.getBooleanNullWithRestResponse(boolQuery);
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
     * Get null integer value (no query parameter).
     * 
     * @param intQuery null integer value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null integer value (no query parameter).
     */
    public Response<Void> getIntNullWithRestResponse(Integer intQuery) {
        return this.serviceClient.getIntNullWithRestResponse(intQuery);
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
     * Get 'null 64 bit integer value (no query param in uri).
     * 
     * @param longQuery null 64 bit integer value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return 'null 64 bit integer value (no query param in uri).
     */
    public Response<Void> getLongNullWithRestResponse(Long longQuery) {
        return this.serviceClient.getLongNullWithRestResponse(longQuery);
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
     * Get null numeric value (no query parameter).
     * 
     * @param floatQuery null numeric value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null numeric value (no query parameter).
     */
    public Response<Void> floatNullWithRestResponse(Float floatQuery) {
        return this.serviceClient.floatNullWithRestResponse(floatQuery);
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
     * Get null numeric value (no query parameter).
     * 
     * @param doubleQuery null numeric value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null numeric value (no query parameter).
     */
    public Response<Void> doubleNullWithRestResponse(Double doubleQuery) {
        return this.serviceClient.doubleNullWithRestResponse(doubleQuery);
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
     * Get null (no query parameter in url).
     * 
     * @param stringQuery null string value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null (no query parameter in url).
     */
    public Response<Void> stringNullWithRestResponse(String stringQuery) {
        return this.serviceClient.stringNullWithRestResponse(stringQuery);
    }

    /**
     * Get using uri with query parameter 'green color'.
     * 
     * @param enumQuery 'green color' enum value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return using uri with query parameter 'green color'.
     */
    public Response<Void> enumValidWithRestResponse(UriColor enumQuery) {
        return this.serviceClient.enumValidWithRestResponse(enumQuery);
    }

    /**
     * Get null (no query parameter in url).
     * 
     * @param enumQuery null string value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null (no query parameter in url).
     */
    public Response<Void> enumNullWithRestResponse(UriColor enumQuery) {
        return this.serviceClient.enumNullWithRestResponse(enumQuery);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * 
     * @param byteQuery '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     */
    public Response<Void> byteMultiByteWithRestResponse(byte[] byteQuery) {
        return this.serviceClient.byteMultiByteWithRestResponse(byteQuery);
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
     * Get null as byte array (no query parameters in uri).
     * 
     * @param byteQuery null as byte array (no query parameters in uri).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null as byte array (no query parameters in uri).
     */
    public Response<Void> byteNullWithRestResponse(byte[] byteQuery) {
        return this.serviceClient.byteNullWithRestResponse(byteQuery);
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
     * Get null as date - this should result in no query parameters in uri.
     * 
     * @param dateQuery null as date (no query parameters in uri).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null as date - this should result in no query parameters in uri.
     */
    public Response<Void> dateNullWithRestResponse(LocalDate dateQuery) {
        return this.serviceClient.dateNullWithRestResponse(dateQuery);
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
     * Get null as date-time, should result in no query parameters in uri.
     * 
     * @param dateTimeQuery null as date-time (no query parameters).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return null as date-time, should result in no query parameters in uri.
     */
    public Response<Void> dateTimeNullWithRestResponse(OffsetDateTime dateTimeQuery) {
        return this.serviceClient.dateTimeNullWithRestResponse(dateTimeQuery);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' ,
     * null, ''] using the csv-array format.
     * 
     * @param arrayQuery Array of String.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end'
     *         , null, ''] using the csv-array format.
     */
    public Response<Void> arrayStringCsvValidWithRestResponse(List<String> arrayQuery) {
        return this.serviceClient.arrayStringCsvValidWithRestResponse(arrayQuery);
    }

    /**
     * Get a null array of string using the csv-array format.
     * 
     * @param arrayQuery Array of String.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return a null array of string using the csv-array format.
     */
    public Response<Void> arrayStringCsvNullWithRestResponse(List<String> arrayQuery) {
        return this.serviceClient.arrayStringCsvNullWithRestResponse(arrayQuery);
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     * 
     * @param arrayQuery Array of String.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return an empty array [] of string using the csv-array format.
     */
    public Response<Void> arrayStringCsvEmptyWithRestResponse(List<String> arrayQuery) {
        return this.serviceClient.arrayStringCsvEmptyWithRestResponse(arrayQuery);
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in
     * ['hello', 'nihao', 'bonjour'] for the 'arrayQuery' parameter to the service.
     * 
     * @param arrayQuery Array of String.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> arrayStringNoCollectionFormatEmptyWithRestResponse(List<String> arrayQuery) {
        return this.serviceClient.arrayStringNoCollectionFormatEmptyWithRestResponse(arrayQuery);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' ,
     * null, ''] using the ssv-array format.
     * 
     * @param arrayQuery Array of String.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end'
     *         , null, ''] using the ssv-array format.
     */
    public Response<Void> arrayStringSsvValidWithRestResponse(List<String> arrayQuery) {
        return this.serviceClient.arrayStringSsvValidWithRestResponse(arrayQuery);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' ,
     * null, ''] using the tsv-array format.
     * 
     * @param arrayQuery Array of String.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end'
     *         , null, ''] using the tsv-array format.
     */
    public Response<Void> arrayStringTsvValidWithRestResponse(List<String> arrayQuery) {
        return this.serviceClient.arrayStringTsvValidWithRestResponse(arrayQuery);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' ,
     * null, ''] using the pipes-array format.
     * 
     * @param arrayQuery Array of String.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end'
     *         , null, ''] using the pipes-array format.
     */
    public Response<Void> arrayStringPipesValidWithRestResponse(List<String> arrayQuery) {
        return this.serviceClient.arrayStringPipesValidWithRestResponse(arrayQuery);
    }

    /**
     * A builder for creating a new instance of the QueriesClient type.
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
         * Builds an instance of QueriesClient with the provided parameters.
         * 
         * @return an instance of QueriesClient.
         */
        public QueriesClient build() {
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
            return new QueriesClient(internalClient.getQueries());
        }
    }
}
