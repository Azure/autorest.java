package com.azure.androidtest.fixtures.bodycomplex;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
import com.azure.androidtest.fixtures.bodycomplex.implementation.DictionarysImpl;
import com.azure.androidtest.fixtures.bodycomplex.models.DictionaryWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.ErrorException;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the asynchronous AutoRestComplexTestService type.
 */
public final class DictionaryAsyncClient {
    private DictionarysImpl serviceClient;

    /**
     * Initializes an instance of Dictionarys client.
     */
    DictionaryAsyncClient(DictionarysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getValid(final Callback<DictionaryWrapper> callback) {
        this.serviceClient.getValid(callback);
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param defaultProgram Dictionary of &lt;string&gt;.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putValid(Map<String, String> defaultProgram, final Callback<Void> callback) {
        this.serviceClient.putValid(defaultProgram, callback);
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getEmpty(final Callback<DictionaryWrapper> callback) {
        this.serviceClient.getEmpty(callback);
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param defaultProgram Dictionary of &lt;string&gt;.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putEmpty(Map<String, String> defaultProgram, final Callback<Void> callback) {
        this.serviceClient.putEmpty(defaultProgram, callback);
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNull(final Callback<DictionaryWrapper> callback) {
        this.serviceClient.getNull(callback);
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNotProvided(final Callback<DictionaryWrapper> callback) {
        this.serviceClient.getNotProvided(callback);
    }

    /**
     * A builder for creating a new instance of the DictionaryAsyncClient type.
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
         * Builds an instance of DictionaryAsyncClient with the provided parameters.
         * 
         * @return an instance of DictionaryAsyncClient.
         */
        public DictionaryAsyncClient build() {
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
            AutoRestComplexTestServiceImpl internalClient = new AutoRestComplexTestServiceImpl(serviceClientBuilder.build(), host);
            return new DictionaryAsyncClient(internalClient.getDictionarys());
        }
    }
}
