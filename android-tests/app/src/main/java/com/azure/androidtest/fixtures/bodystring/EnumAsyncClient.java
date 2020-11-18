package com.azure.androidtest.fixtures.bodystring;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodystring.implementation.AutoRestSwaggerBATServiceImpl;
import com.azure.androidtest.fixtures.bodystring.implementation.EnumsImpl;
import com.azure.androidtest.fixtures.bodystring.models.Colors;
import com.azure.androidtest.fixtures.bodystring.models.ErrorException;
import com.azure.androidtest.fixtures.bodystring.models.RefColorConstant;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the asynchronous AutoRestSwaggerBATService type.
 */
public final class EnumAsyncClient {
    private EnumsImpl serviceClient;

    /**
     * Initializes an instance of Enums client.
     */
    EnumAsyncClient(EnumsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNotExpandable(final Callback<Colors> callback) {
        this.serviceClient.getNotExpandable(callback);
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     * 
     * @param stringBody string body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putNotExpandable(Colors stringBody, final Callback<Void> callback) {
        this.serviceClient.putNotExpandable(stringBody, callback);
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getReferenced(final Callback<Colors> callback) {
        this.serviceClient.getReferenced(callback);
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color', 'blue_color'.
     * 
     * @param enumStringBody enum string body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putReferenced(Colors enumStringBody, final Callback<Void> callback) {
        this.serviceClient.putReferenced(enumStringBody, callback);
    }

    /**
     * Get value 'green-color' from the constant.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getReferencedConstant(final Callback<RefColorConstant> callback) {
        this.serviceClient.getReferencedConstant(callback);
    }

    /**
     * Sends value 'green-color' from a constant.
     * 
     * @param field1 Sample string.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putReferencedConstant(String field1, final Callback<Void> callback) {
        this.serviceClient.putReferencedConstant(field1, callback);
    }

    /**
     * A builder for creating a new instance of the EnumAsyncClient type.
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
         * Builds an instance of EnumAsyncClient with the provided parameters.
         * 
         * @return an instance of EnumAsyncClient.
         */
        public EnumAsyncClient build() {
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
                final String retrofitBaseUrl = host.replace("{host}", host);
                serviceClientBuilder.setBaseUrl(retrofitBaseUrl);
            }
            if (credentialInterceptor != null) {
                serviceClientBuilder.setCredentialsInterceptor(credentialInterceptor);
            }
            AutoRestSwaggerBATServiceImpl internalClient = new AutoRestSwaggerBATServiceImpl(serviceClientBuilder.build(), host);
            return new EnumAsyncClient(internalClient.getEnums());
        }
    }
}
