package com.azure.androidtest.fixtures.bodyinteger;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodyinteger.implementation.AutoRestIntegerTestServiceImpl;
import com.azure.androidtest.fixtures.bodyinteger.implementation.IntsImpl;
import com.azure.androidtest.fixtures.bodyinteger.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the asynchronous AutoRestIntegerTestService
 * type.
 */
public final class AutoRestIntegerTestServiceAsyncClient {
    private IntsImpl serviceClient;

    /**
     * Initializes an instance of Ints client.
     */
    AutoRestIntegerTestServiceAsyncClient(IntsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null Int value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getNull(final Callback<Integer> callback) {
        this.serviceClient.getNull(callback);
    }

    /**
     * Get invalid Int value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getInvalid(final Callback<Integer> callback) {
        this.serviceClient.getInvalid(callback);
    }

    /**
     * Get overflow Int32 value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getOverflowInt32(final Callback<Integer> callback) {
        this.serviceClient.getOverflowInt32(callback);
    }

    /**
     * Get underflow Int32 value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getUnderflowInt32(final Callback<Integer> callback) {
        this.serviceClient.getUnderflowInt32(callback);
    }

    /**
     * Get overflow Int64 value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getOverflowInt64(final Callback<Long> callback) {
        this.serviceClient.getOverflowInt64(callback);
    }

    /**
     * Get underflow Int64 value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getUnderflowInt64(final Callback<Long> callback) {
        this.serviceClient.getUnderflowInt64(callback);
    }

    /**
     * Put max int32 value.
     * 
     * @param intBody  int body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void putMax32(int intBody, final Callback<Void> callback) {
        this.serviceClient.putMax32(intBody, callback);
    }

    /**
     * Put max int64 value.
     * 
     * @param intBody  int body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void putMax64(long intBody, final Callback<Void> callback) {
        this.serviceClient.putMax64(intBody, callback);
    }

    /**
     * Put min int32 value.
     * 
     * @param intBody  int body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void putMin32(int intBody, final Callback<Void> callback) {
        this.serviceClient.putMin32(intBody, callback);
    }

    /**
     * Put min int64 value.
     * 
     * @param intBody  int body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void putMin64(long intBody, final Callback<Void> callback) {
        this.serviceClient.putMin64(intBody, callback);
    }

    /**
     * Get datetime encoded as Unix time value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getUnixTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getUnixTime(callback);
    }

    /**
     * Put datetime encoded as Unix time.
     * 
     * @param intBody  date in seconds since 1970-01-01T00:00:00Z.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void putUnixTimeDate(OffsetDateTime intBody, final Callback<Void> callback) {
        this.serviceClient.putUnixTimeDate(intBody, callback);
    }

    /**
     * Get invalid Unix time value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getInvalidUnixTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getInvalidUnixTime(callback);
    }

    /**
     * Get null Unix time value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getNullUnixTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getNullUnixTime(callback);
    }

    /**
     * A builder for creating a new instance of the
     * AutoRestIntegerTestServiceAsyncClient type.
     */
    public static final class Builder {
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
         * Builds an instance of AutoRestIntegerTestServiceAsyncClient with the provided
         * parameters.
         * 
         * @return an instance of AutoRestIntegerTestServiceAsyncClient.
         */
        public AutoRestIntegerTestServiceAsyncClient build() {
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
            AutoRestIntegerTestServiceImpl internalClient = new AutoRestIntegerTestServiceImpl(
                    serviceClientBuilder.build(), host);
            return new AutoRestIntegerTestServiceAsyncClient(internalClient.getInts());
        }
    }
}
