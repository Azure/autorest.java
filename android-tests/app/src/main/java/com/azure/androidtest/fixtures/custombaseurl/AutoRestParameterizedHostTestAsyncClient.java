package com.azure.androidtest.fixtures.custombaseurl;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.androidtest.fixtures.custombaseurl.implementation.AutoRestParameterizedHostTestClientImpl;
import com.azure.androidtest.fixtures.custombaseurl.implementation.PathsImpl;
import com.azure.androidtest.fixtures.custombaseurl.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Initializes a new instance of the asynchronous AutoRestParameterizedHostTestClient type.
 */
public final class AutoRestParameterizedHostTestAsyncClient {
    private PathsImpl serviceClient;

    /**
     * Initializes an instance of Paths client.
     */
    AutoRestParameterizedHostTestAsyncClient(PathsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getEmpty(final Callback<Void> callback) {
        this.serviceClient.getEmpty(callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestParameterizedHostTestAsyncClient type.
     */
    public static final class Builder {
        /*
         * A string value that is used as a global part of the parameterized
         * host
         */
        private String host;

        /**
         * Sets A string value that is used as a global part of the parameterized host.
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

        /*
         * the String value
         */
        private String accountName;

        /**
         * Sets the String value.
         * 
         * @param accountName the accountName value.
         * @return the Builder.
         */
        public Builder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        /*
         * base url of the service
         */
        private String baseUrl;

        /**
         * Sets base url of the service.
         * 
         * @param baseUrl the baseUrl value.
         * @return the Builder.
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Builds an instance of AutoRestParameterizedHostTestAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestParameterizedHostTestAsyncClient.
         */
        public AutoRestParameterizedHostTestAsyncClient build() {
            if (baseUrl == null) {
                this.baseUrl = "http://{accountName}{host}";
            }
            if (host == null) {
                this.host = "host";
            }
            if (serviceClientBuilder == null) {
                if (accountName == null || host == null) {
                    throw new IllegalArgumentException("Missing required parameters 'accountName, host'.");
                }
                this.serviceClientBuilder = new ServiceClient.Builder();
            }
            if (accountName != null && host != null) {
                final String retrofitBaseUrl = baseUrl.replace("{accountName}", accountName).replace("{host}", host);
                serviceClientBuilder.setBaseUrl(retrofitBaseUrl);
            }
            if (credentialInterceptor != null) {
                serviceClientBuilder.setCredentialsInterceptor(credentialInterceptor);
            }
            AutoRestParameterizedHostTestClientImpl internalClient = new AutoRestParameterizedHostTestClientImpl(serviceClientBuilder.build(), host);
            return new AutoRestParameterizedHostTestAsyncClient(internalClient.getPaths());
        }
    }
}
