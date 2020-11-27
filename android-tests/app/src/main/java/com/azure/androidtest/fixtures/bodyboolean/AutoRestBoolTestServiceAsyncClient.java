package com.azure.androidtest.fixtures.bodyboolean;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodyboolean.implementation.AutoRestBoolTestServiceImpl;
import com.azure.androidtest.fixtures.bodyboolean.implementation.BoolsImpl;
import com.azure.androidtest.fixtures.bodyboolean.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the asynchronous AutoRestBoolTestService type.
 */
public final class AutoRestBoolTestServiceAsyncClient {
    private BoolsImpl serviceClient;

    /**
     * Initializes an instance of Bools client.
     */
    AutoRestBoolTestServiceAsyncClient(BoolsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get true Boolean value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getTrue(final Callback<Boolean> callback) {
        this.serviceClient.getTrue(callback);
    }

    /**
     * Set Boolean value true.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putTrue(final Callback<Void> callback) {
        this.serviceClient.putTrue(callback);
    }

    /**
     * Get false Boolean value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getFalse(final Callback<Boolean> callback) {
        this.serviceClient.getFalse(callback);
    }

    /**
     * Set Boolean value false.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putFalse(final Callback<Void> callback) {
        this.serviceClient.putFalse(callback);
    }

    /**
     * Get null Boolean value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNull(final Callback<Boolean> callback) {
        this.serviceClient.getNull(callback);
    }

    /**
     * Get invalid Boolean value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalid(final Callback<Boolean> callback) {
        this.serviceClient.getInvalid(callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestBoolTestServiceAsyncClient type.
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
         * Builds an instance of AutoRestBoolTestServiceAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestBoolTestServiceAsyncClient.
         */
        public AutoRestBoolTestServiceAsyncClient build() {
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
            AutoRestBoolTestServiceImpl internalClient = new AutoRestBoolTestServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestBoolTestServiceAsyncClient(internalClient.getBools());
        }
    }
}
