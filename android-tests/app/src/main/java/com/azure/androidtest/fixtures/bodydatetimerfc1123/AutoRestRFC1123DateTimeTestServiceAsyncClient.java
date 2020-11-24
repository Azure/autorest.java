package com.azure.androidtest.fixtures.bodydatetimerfc1123;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodydatetimerfc1123.implementation.AutoRestRFC1123DateTimeTestServiceImpl;
import com.azure.androidtest.fixtures.bodydatetimerfc1123.implementation.Datetimerfc1123sImpl;
import com.azure.androidtest.fixtures.bodydatetimerfc1123.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the asynchronous AutoRestRFC1123DateTimeTestService type.
 */
public final class AutoRestRFC1123DateTimeTestServiceAsyncClient {
    private Datetimerfc1123sImpl serviceClient;

    /**
     * Initializes an instance of Datetimerfc1123s client.
     */
    AutoRestRFC1123DateTimeTestServiceAsyncClient(Datetimerfc1123sImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNull(final Callback<DateTimeRfc1123> callback) {
        this.serviceClient.getNull(callback);
    }

    /**
     * Get invalid datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalid(final Callback<DateTimeRfc1123> callback) {
        this.serviceClient.getInvalid(callback);
    }

    /**
     * Get overflow datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getOverflow(final Callback<DateTimeRfc1123> callback) {
        this.serviceClient.getOverflow(callback);
    }

    /**
     * Get underflow datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUnderflow(final Callback<DateTimeRfc1123> callback) {
        this.serviceClient.getUnderflow(callback);
    }

    /**
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
     * 
     * @param datetimeBody datetime body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putUtcMaxDateTime(DateTimeRfc1123 datetimeBody, final Callback<Void> callback) {
        this.serviceClient.putUtcMaxDateTime(datetimeBody, callback);
    }

    /**
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUtcLowercaseMaxDateTime(final Callback<DateTimeRfc1123> callback) {
        this.serviceClient.getUtcLowercaseMaxDateTime(callback);
    }

    /**
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUtcUppercaseMaxDateTime(final Callback<DateTimeRfc1123> callback) {
        this.serviceClient.getUtcUppercaseMaxDateTime(callback);
    }

    /**
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @param datetimeBody datetime body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putUtcMinDateTime(DateTimeRfc1123 datetimeBody, final Callback<Void> callback) {
        this.serviceClient.putUtcMinDateTime(datetimeBody, callback);
    }

    /**
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUtcMinDateTime(final Callback<DateTimeRfc1123> callback) {
        this.serviceClient.getUtcMinDateTime(callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestRFC1123DateTimeTestServiceAsyncClient type.
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
         * Builds an instance of AutoRestRFC1123DateTimeTestServiceAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestRFC1123DateTimeTestServiceAsyncClient.
         */
        public AutoRestRFC1123DateTimeTestServiceAsyncClient build() {
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
            AutoRestRFC1123DateTimeTestServiceImpl internalClient = new AutoRestRFC1123DateTimeTestServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestRFC1123DateTimeTestServiceAsyncClient(internalClient.getDatetimerfc1123s());
        }
    }
}
