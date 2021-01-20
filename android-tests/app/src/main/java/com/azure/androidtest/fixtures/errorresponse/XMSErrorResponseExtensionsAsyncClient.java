package com.azure.androidtest.fixtures.errorresponse;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.androidtest.fixtures.errorresponse.implementation.PetsImpl;
import com.azure.androidtest.fixtures.errorresponse.implementation.XMSErrorResponseExtensionsImpl;
import com.azure.androidtest.fixtures.errorresponse.models.Pet;
import com.azure.androidtest.fixtures.errorresponse.models.PetAction;
import com.azure.androidtest.fixtures.errorresponse.models.PetActionErrorException;
import okhttp3.Interceptor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Initializes a new instance of the asynchronous XMSErrorResponseExtensions type.
 */
public final class XMSErrorResponseExtensionsAsyncClient {
    private PetsImpl serviceClient;

    /**
     * Initializes an instance of Pets client.
     */
    XMSErrorResponseExtensionsAsyncClient(PetsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Gets pets by id.
     * 
     * @param petId pet id.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getPetById(String petId, final Callback<Pet> callback) {
        this.serviceClient.getPetById(petId, callback);
    }

    /**
     * Asks pet to do something.
     * 
     * @param whatAction what action the pet should do.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws PetActionErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void doSomething(String whatAction, final Callback<PetAction> callback) {
        this.serviceClient.doSomething(whatAction, callback);
    }

    /**
     * Ensure you can correctly deserialize the returned PetActionError and deserialization doesn't conflict with the input param name 'models'.
     * 
     * @param models Make sure model deserialization doesn't conflict with this param name, which has input name 'models'. Use client default value in call.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws PetActionErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void hasModelsParam(String models, final Callback<Void> callback) {
        this.serviceClient.hasModelsParam(models, callback);
    }

    /**
     * A builder for creating a new instance of the XMSErrorResponseExtensionsAsyncClient type.
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
         * Builds an instance of XMSErrorResponseExtensionsAsyncClient with the provided parameters.
         * 
         * @return an instance of XMSErrorResponseExtensionsAsyncClient.
         */
        public XMSErrorResponseExtensionsAsyncClient build() {
            if (host == null) {
                this.host = "http://localhost";
            }
            if (serviceClientBuilder == null) {
                this.serviceClientBuilder = new ServiceClient.Builder();
            }
            serviceClientBuilder.setBaseUrl(host);
            if (credentialInterceptor != null) {
                serviceClientBuilder.setCredentialsInterceptor(credentialInterceptor);
            }
            XMSErrorResponseExtensionsImpl internalClient = new XMSErrorResponseExtensionsImpl(serviceClientBuilder.build(), host);
            return new XMSErrorResponseExtensionsAsyncClient(internalClient.getPets());
        }
    }
}
