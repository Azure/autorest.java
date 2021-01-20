package com.azure.androidtest.fixtures.bodycomplex;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
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
 * Initializes a new instance of the synchronous AutoRestComplexTestService type.
 */
public final class DictionaryClient {
    private DictionarysImpl serviceClient;

    /**
     * Initializes an instance of Dictionarys client.
     */
    DictionaryClient(DictionarysImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with dictionary property.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property.
     */
    public Response<DictionaryWrapper> getValidWithRestResponse() {
        return this.serviceClient.getValidWithRestResponse();
    }

    /**
     * Put complex types with dictionary property.
     * 
     * @param defaultProgram Dictionary of &lt;string&gt;.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putValidWithRestResponse(Map<String, String> defaultProgram) {
        return this.serviceClient.putValidWithRestResponse(defaultProgram);
    }

    /**
     * Get complex types with dictionary property which is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty.
     */
    public Response<DictionaryWrapper> getEmptyWithRestResponse() {
        return this.serviceClient.getEmptyWithRestResponse();
    }

    /**
     * Put complex types with dictionary property which is empty.
     * 
     * @param defaultProgram Dictionary of &lt;string&gt;.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putEmptyWithRestResponse(Map<String, String> defaultProgram) {
        return this.serviceClient.putEmptyWithRestResponse(defaultProgram);
    }

    /**
     * Get complex types with dictionary property which is null.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null.
     */
    public Response<DictionaryWrapper> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload.
     */
    public Response<DictionaryWrapper> getNotProvidedWithRestResponse() {
        return this.serviceClient.getNotProvidedWithRestResponse();
    }

    /**
     * A builder for creating a new instance of the DictionaryClient type.
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
         * Builds an instance of DictionaryClient with the provided parameters.
         * 
         * @return an instance of DictionaryClient.
         */
        public DictionaryClient build() {
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
            return new DictionaryClient(internalClient.getDictionarys());
        }
    }
}
