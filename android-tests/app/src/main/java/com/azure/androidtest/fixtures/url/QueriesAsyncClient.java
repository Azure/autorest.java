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
 * Initializes a new instance of the asynchronous AutoRestUrlTestService type.
 */
public final class QueriesAsyncClient {
    private QueriesImpl serviceClient;

    /**
     * Initializes an instance of Queries client.
     */
    QueriesAsyncClient(QueriesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get true Boolean value on path.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBooleanTrue(final Callback<Void> callback) {
        this.serviceClient.getBooleanTrue(callback);
    }

    /**
     * Get false Boolean value on path.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBooleanFalse(final Callback<Void> callback) {
        this.serviceClient.getBooleanFalse(callback);
    }

    /**
     * Get null Boolean value on query (query string should be absent).
     * 
     * @param boolQuery null boolean value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBooleanNull(Boolean boolQuery, final Callback<Void> callback) {
        this.serviceClient.getBooleanNull(boolQuery, callback);
    }

    /**
     * Get '1000000' integer value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getIntOneMillion(final Callback<Void> callback) {
        this.serviceClient.getIntOneMillion(callback);
    }

    /**
     * Get '-1000000' integer value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getIntNegativeOneMillion(final Callback<Void> callback) {
        this.serviceClient.getIntNegativeOneMillion(callback);
    }

    /**
     * Get null integer value (no query parameter).
     * 
     * @param intQuery null integer value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getIntNull(Integer intQuery, final Callback<Void> callback) {
        this.serviceClient.getIntNull(intQuery, callback);
    }

    /**
     * Get '10000000000' 64 bit integer value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getTenBillion(final Callback<Void> callback) {
        this.serviceClient.getTenBillion(callback);
    }

    /**
     * Get '-10000000000' 64 bit integer value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNegativeTenBillion(final Callback<Void> callback) {
        this.serviceClient.getNegativeTenBillion(callback);
    }

    /**
     * Get 'null 64 bit integer value (no query param in uri).
     * 
     * @param longQuery null 64 bit integer value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLongNull(Long longQuery, final Callback<Void> callback) {
        this.serviceClient.getLongNull(longQuery, callback);
    }

    /**
     * Get '1.034E+20' numeric value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void floatScientificPositive(final Callback<Void> callback) {
        this.serviceClient.floatScientificPositive(callback);
    }

    /**
     * Get '-1.034E-20' numeric value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void floatScientificNegative(final Callback<Void> callback) {
        this.serviceClient.floatScientificNegative(callback);
    }

    /**
     * Get null numeric value (no query parameter).
     * 
     * @param floatQuery null numeric value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void floatNull(Float floatQuery, final Callback<Void> callback) {
        this.serviceClient.floatNull(floatQuery, callback);
    }

    /**
     * Get '9999999.999' numeric value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void doubleDecimalPositive(final Callback<Void> callback) {
        this.serviceClient.doubleDecimalPositive(callback);
    }

    /**
     * Get '-9999999.999' numeric value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void doubleDecimalNegative(final Callback<Void> callback) {
        this.serviceClient.doubleDecimalNegative(callback);
    }

    /**
     * Get null numeric value (no query parameter).
     * 
     * @param doubleQuery null numeric value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void doubleNull(Double doubleQuery, final Callback<Void> callback) {
        this.serviceClient.doubleNull(doubleQuery, callback);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multi-byte string value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void stringUnicode(final Callback<Void> callback) {
        this.serviceClient.stringUnicode(callback);
    }

    /**
     * Get 'begin!*'();:@ &amp;=+$,/?#[]end.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void stringUrlEncoded(final Callback<Void> callback) {
        this.serviceClient.stringUrlEncoded(callback);
    }

    /**
     * Get ''.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void stringEmpty(final Callback<Void> callback) {
        this.serviceClient.stringEmpty(callback);
    }

    /**
     * Get null (no query parameter in url).
     * 
     * @param stringQuery null string value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void stringNull(String stringQuery, final Callback<Void> callback) {
        this.serviceClient.stringNull(stringQuery, callback);
    }

    /**
     * Get using uri with query parameter 'green color'.
     * 
     * @param enumQuery 'green color' enum value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void enumValid(UriColor enumQuery, final Callback<Void> callback) {
        this.serviceClient.enumValid(enumQuery, callback);
    }

    /**
     * Get null (no query parameter in url).
     * 
     * @param enumQuery null string value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void enumNull(UriColor enumQuery, final Callback<Void> callback) {
        this.serviceClient.enumNull(enumQuery, callback);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * 
     * @param byteQuery '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void byteMultiByte(byte[] byteQuery, final Callback<Void> callback) {
        this.serviceClient.byteMultiByte(byteQuery, callback);
    }

    /**
     * Get '' as byte array.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void byteEmpty(final Callback<Void> callback) {
        this.serviceClient.byteEmpty(callback);
    }

    /**
     * Get null as byte array (no query parameters in uri).
     * 
     * @param byteQuery null as byte array (no query parameters in uri).
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void byteNull(byte[] byteQuery, final Callback<Void> callback) {
        this.serviceClient.byteNull(byteQuery, callback);
    }

    /**
     * Get '2012-01-01' as date.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void dateValid(final Callback<Void> callback) {
        this.serviceClient.dateValid(callback);
    }

    /**
     * Get null as date - this should result in no query parameters in uri.
     * 
     * @param dateQuery null as date (no query parameters in uri).
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void dateNull(LocalDate dateQuery, final Callback<Void> callback) {
        this.serviceClient.dateNull(dateQuery, callback);
    }

    /**
     * Get '2012-01-01T01:01:01Z' as date-time.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void dateTimeValid(final Callback<Void> callback) {
        this.serviceClient.dateTimeValid(callback);
    }

    /**
     * Get null as date-time, should result in no query parameters in uri.
     * 
     * @param dateTimeQuery null as date-time (no query parameters).
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void dateTimeNull(OffsetDateTime dateTimeQuery, final Callback<Void> callback) {
        this.serviceClient.dateTimeNull(dateTimeQuery, callback);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     * 
     * @param arrayQuery Array of String.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void arrayStringCsvValid(List<String> arrayQuery, final Callback<Void> callback) {
        this.serviceClient.arrayStringCsvValid(arrayQuery, callback);
    }

    /**
     * Get a null array of string using the csv-array format.
     * 
     * @param arrayQuery Array of String.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void arrayStringCsvNull(List<String> arrayQuery, final Callback<Void> callback) {
        this.serviceClient.arrayStringCsvNull(arrayQuery, callback);
    }

    /**
     * Get an empty array [] of string using the csv-array format.
     * 
     * @param arrayQuery Array of String.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void arrayStringCsvEmpty(List<String> arrayQuery, final Callback<Void> callback) {
        this.serviceClient.arrayStringCsvEmpty(arrayQuery, callback);
    }

    /**
     * Array query has no defined collection format, should default to csv. Pass in ['hello', 'nihao', 'bonjour'] for the 'arrayQuery' parameter to the service.
     * 
     * @param arrayQuery Array of String.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void arrayStringNoCollectionFormatEmpty(List<String> arrayQuery, final Callback<Void> callback) {
        this.serviceClient.arrayStringNoCollectionFormatEmpty(arrayQuery, callback);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the ssv-array format.
     * 
     * @param arrayQuery Array of String.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void arrayStringSsvValid(List<String> arrayQuery, final Callback<Void> callback) {
        this.serviceClient.arrayStringSsvValid(arrayQuery, callback);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the tsv-array format.
     * 
     * @param arrayQuery Array of String.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void arrayStringTsvValid(List<String> arrayQuery, final Callback<Void> callback) {
        this.serviceClient.arrayStringTsvValid(arrayQuery, callback);
    }

    /**
     * Get an array of string ['ArrayQuery1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the pipes-array format.
     * 
     * @param arrayQuery Array of String.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void arrayStringPipesValid(List<String> arrayQuery, final Callback<Void> callback) {
        this.serviceClient.arrayStringPipesValid(arrayQuery, callback);
    }

    /**
     * A builder for creating a new instance of the QueriesAsyncClient type.
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
         * Builds an instance of QueriesAsyncClient with the provided parameters.
         * 
         * @return an instance of QueriesAsyncClient.
         */
        public QueriesAsyncClient build() {
            if (host == null) {
                this.host = "http://localhost:3000";
            }
            if (serviceClientBuilder == null) {
                if (host == null) {
                    throw new IllegalArgumentException("Missing required parameters 'host'.");
                }
                this.serviceClientBuilder = new ServiceClient.Builder();
            }
            if (host != null) {
                final String retrofitBaseUrl = this.host.replace("{host}", host);
                serviceClientBuilder.setBaseUrl(retrofitBaseUrl);
            }
            if (credentialInterceptor != null) {
                serviceClientBuilder.setCredentialsInterceptor(credentialInterceptor);
            }
            AutoRestUrlTestServiceImpl internalClient = new AutoRestUrlTestServiceImpl(serviceClientBuilder.build(), globalStringPath, globalStringQuery, host);
            return new QueriesAsyncClient(internalClient.getQueries());
        }
    }
}
