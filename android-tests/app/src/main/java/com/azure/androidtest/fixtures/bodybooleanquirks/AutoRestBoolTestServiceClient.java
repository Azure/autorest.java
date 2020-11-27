package com.azure.androidtest.fixtures.bodybooleanquirks;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodybooleanquirks.implementation.AutoRestBoolTestServiceImpl;
import com.azure.androidtest.fixtures.bodybooleanquirks.implementation.BoolsImpl;
import com.azure.androidtest.fixtures.bodybooleanquirks.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestBoolTestService type.
 */
public final class AutoRestBoolTestServiceClient {
    private BoolsImpl serviceClient;

    /**
     * Initializes an instance of Bools client.
     */
    AutoRestBoolTestServiceClient(BoolsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get true Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    public Response<Boolean> getTrueWithRestResponse() {
        return this.serviceClient.getTrueWithRestResponse();
    }

    /**
     * Set Boolean value true.
     * 
     * @param boolBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putTrueWithRestResponse(boolean boolBody) {
        return this.serviceClient.putTrueWithRestResponse(boolBody);
    }

    /**
     * Get false Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    public Response<Boolean> getFalseWithRestResponse() {
        return this.serviceClient.getFalseWithRestResponse();
    }

    /**
     * Set Boolean value false.
     * 
     * @param boolBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putFalseWithRestResponse(boolean boolBody) {
        return this.serviceClient.putFalseWithRestResponse(boolBody);
    }

    /**
     * Get null Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    public Response<Boolean> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get invalid Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    public Response<Boolean> getInvalidWithRestResponse() {
        return this.serviceClient.getInvalidWithRestResponse();
    }

    /**
     * A builder for creating a new instance of the AutoRestBoolTestServiceClient type.
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
         * Builds an instance of AutoRestBoolTestServiceClient with the provided parameters.
         * 
         * @return an instance of AutoRestBoolTestServiceClient.
         */
        public AutoRestBoolTestServiceClient build() {
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
            return new AutoRestBoolTestServiceClient(internalClient.getBools());
        }
    }
}
