package com.azure.androidtest.fixtures.bodydate;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodydate.implementation.AutoRestDateTestServiceImpl;
import com.azure.androidtest.fixtures.bodydate.implementation.DateOperationsImpl;
import com.azure.androidtest.fixtures.bodydate.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.threeten.bp.LocalDate;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the asynchronous AutoRestDateTestService type.
 */
public final class AutoRestDateTestServiceAsyncClient {
    private DateOperationsImpl serviceClient;

    /**
     * Initializes an instance of DateOperations client.
     */
    AutoRestDateTestServiceAsyncClient(DateOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null date value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNull(final Callback<LocalDate> callback) {
        this.serviceClient.getNull(callback);
    }

    /**
     * Get invalid date value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalidDate(final Callback<LocalDate> callback) {
        this.serviceClient.getInvalidDate(callback);
    }

    /**
     * Get overflow date value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getOverflowDate(final Callback<LocalDate> callback) {
        this.serviceClient.getOverflowDate(callback);
    }

    /**
     * Get underflow date value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUnderflowDate(final Callback<LocalDate> callback) {
        this.serviceClient.getUnderflowDate(callback);
    }

    /**
     * Put max date value 9999-12-31.
     * 
     * @param dateBody date body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putMaxDate(LocalDate dateBody, final Callback<Void> callback) {
        this.serviceClient.putMaxDate(dateBody, callback);
    }

    /**
     * Get max date value 9999-12-31.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMaxDate(final Callback<LocalDate> callback) {
        this.serviceClient.getMaxDate(callback);
    }

    /**
     * Put min date value 0000-01-01.
     * 
     * @param dateBody date body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putMinDate(LocalDate dateBody, final Callback<Void> callback) {
        this.serviceClient.putMinDate(dateBody, callback);
    }

    /**
     * Get min date value 0000-01-01.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMinDate(final Callback<LocalDate> callback) {
        this.serviceClient.getMinDate(callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestDateTestServiceAsyncClient type.
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
         * Builds an instance of AutoRestDateTestServiceAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestDateTestServiceAsyncClient.
         */
        public AutoRestDateTestServiceAsyncClient build() {
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
            AutoRestDateTestServiceImpl internalClient = new AutoRestDateTestServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestDateTestServiceAsyncClient(internalClient.getDateOperations());
        }
    }
}
