package com.azure.androidtest.fixtures.bodycomplex;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.androidtest.fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
import com.azure.androidtest.fixtures.bodycomplex.implementation.PolymorphicrecursivesImpl;
import com.azure.androidtest.fixtures.bodycomplex.models.ErrorException;
import com.azure.androidtest.fixtures.bodycomplex.models.Fish;
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
public final class PolymorphicrecursiveClient {
    private PolymorphicrecursivesImpl serviceClient;

    /**
     * Initializes an instance of Polymorphicrecursives client.
     */
    PolymorphicrecursiveClient(PolymorphicrecursivesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types that are polymorphic and have recursive references.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic and have recursive references.
     */
    public Response<Fish> getValidWithRestResponse() {
        return this.serviceClient.getValidWithRestResponse();
    }

    /**
     * Put complex types that are polymorphic and have recursive references.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     *     "fishtype": "salmon",
     *     "species": "king",
     *     "length": 1,
     *     "age": 1,
     *     "location": "alaska",
     *     "iswild": true,
     *     "siblings": [
     *         {
     *             "fishtype": "shark",
     *             "species": "predator",
     *             "length": 20,
     *             "age": 6,
     *             "siblings": [
     *                 {
     *                     "fishtype": "salmon",
     *                     "species": "coho",
     *                     "length": 2,
     *                     "age": 2,
     *                     "location": "atlantic",
     *                     "iswild": true,
     *                     "siblings": [
     *                         {
     *                             "fishtype": "shark",
     *                             "species": "predator",
     *                             "length": 20,
     *                             "age": 6
     *                         },
     *                         {
     *                             "fishtype": "sawshark",
     *                             "species": "dangerous",
     *                             "length": 10,
     *                             "age": 105
     *                         }
     *                     ]
     *                 },
     *                 {
     *                     "fishtype": "sawshark",
     *                     "species": "dangerous",
     *                     "length": 10,
     *                     "age": 105
     *                 }
     *             ]
     *         },
     *         {
     *             "fishtype": "sawshark",
     *             "species": "dangerous",
     *             "length": 10,
     *             "age": 105
     *         }
     *     ]
     * }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putValidWithRestResponse(Fish complexBody) {
        return this.serviceClient.putValidWithRestResponse(complexBody);
    }

    /**
     * A builder for creating a new instance of the PolymorphicrecursiveClient type.
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
         * Builds an instance of PolymorphicrecursiveClient with the provided parameters.
         * 
         * @return an instance of PolymorphicrecursiveClient.
         */
        public PolymorphicrecursiveClient build() {
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
            return new PolymorphicrecursiveClient(internalClient.getPolymorphicrecursives());
        }
    }
}
