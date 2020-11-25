package com.azure.androidtest.fixtures.url;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.internal.util.serializer.JacksonAdapter;
import com.azure.android.core.internal.util.serializer.SerializerAdapter;
import com.azure.android.core.util.Base64Url;
import com.azure.android.core.util.Base64Util;
import com.azure.android.core.util.paging.PagedDataRetriever;
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
 * Initializes a new instance of the asynchronous AutoRestUrlTestService type.
 */
public final class PathsAsyncClient {
    private PathsImpl serviceClient;

    /**
     * Initializes an instance of Paths client.
     */
    PathsAsyncClient(PathsImpl serviceClient) {
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
     * https://tools.ietf.org/html/rfc3986#appendix-A 'path' accept any 'pchar' not encoded.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void stringUrlNonEncoded(final Callback<Void> callback) {
        this.serviceClient.stringUrlNonEncoded(callback);
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
     * Get null (should throw).
     * 
     * @param stringPath null string value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void stringNull(String stringPath, final Callback<Void> callback) {
        this.serviceClient.stringNull(stringPath, callback);
    }

    /**
     * Get using uri with 'green color' in path parameter.
     * 
     * @param enumPath send the value green.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void enumValid(UriColor enumPath, final Callback<Void> callback) {
        this.serviceClient.enumValid(enumPath, callback);
    }

    /**
     * Get null (should throw on the client before the request is sent on wire).
     * 
     * @param enumPath send null should throw.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void enumNull(UriColor enumPath, final Callback<Void> callback) {
        this.serviceClient.enumNull(enumPath, callback);
    }

    /**
     * Get '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * 
     * @param bytePath '啊齄丂狛狜隣郎隣兀﨩' multibyte value as utf-8 encoded byte array.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void byteMultiByte(byte[] bytePath, final Callback<Void> callback) {
        this.serviceClient.byteMultiByte(bytePath, callback);
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
     * Get null as byte array (should throw).
     * 
     * @param bytePath null as byte array (should throw).
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void byteNull(byte[] bytePath, final Callback<Void> callback) {
        this.serviceClient.byteNull(bytePath, callback);
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
     * Get null as date - this should throw or be unusable on the client side, depending on date representation.
     * 
     * @param datePath null as date (should throw).
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void dateNull(LocalDate datePath, final Callback<Void> callback) {
        this.serviceClient.dateNull(datePath, callback);
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
     * Get null as date-time, should be disallowed or throw depending on representation of date-time.
     * 
     * @param dateTimePath null as date-time.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void dateTimeNull(OffsetDateTime dateTimePath, final Callback<Void> callback) {
        this.serviceClient.dateTimeNull(dateTimePath, callback);
    }

    /**
     * Get 'lorem' encoded value as 'bG9yZW0' (base64url).
     * 
     * @param base64UrlPath base64url encoded value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void base64Url(Base64Url base64UrlPath, final Callback<Void> callback) {
        this.serviceClient.base64Url(base64UrlPath, callback);
    }

    /**
     * Get an array of string ['ArrayPath1', 'begin!*'();:@ &amp;=+$,/?#[]end' , null, ''] using the csv-array format.
     * 
     * @param arrayPath Array of Get0ItemsItem.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void arrayCsvInPath(List<String> arrayPath, final Callback<Void> callback) {
        this.serviceClient.arrayCsvInPath(arrayPath, callback);
    }

    /**
     * Get the date 2016-04-13 encoded value as '1460505600' (Unix time).
     * 
     * @param unixTimeUrlPath date in seconds since 1970-01-01T00:00:00Z.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void unixTimeUrl(OffsetDateTime unixTimeUrlPath, final Callback<Void> callback) {
        this.serviceClient.unixTimeUrl(unixTimeUrlPath, callback);
    }

    /**
     * A builder for creating a new instance of the PathsAsyncClient type.
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
         * Builds an instance of PathsAsyncClient with the provided parameters.
         * 
         * @return an instance of PathsAsyncClient.
         */
        public PathsAsyncClient build() {
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
            AutoRestUrlTestServiceImpl internalClient = new AutoRestUrlTestServiceImpl(serviceClientBuilder.build(), globalStringPath, globalStringQuery, host);
            return new PathsAsyncClient(internalClient.getPaths());
        }
    }
}
