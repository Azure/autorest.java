package com.azure.androidtest.fixtures.bodycomplex;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
import com.azure.androidtest.fixtures.bodycomplex.implementation.PolymorphismsImpl;
import com.azure.androidtest.fixtures.bodycomplex.models.DotFish;
import com.azure.androidtest.fixtures.bodycomplex.models.DotFishMarket;
import com.azure.androidtest.fixtures.bodycomplex.models.ErrorException;
import com.azure.androidtest.fixtures.bodycomplex.models.Fish;
import com.azure.androidtest.fixtures.bodycomplex.models.Salmon;
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
public final class PolymorphismClient {
    private PolymorphismsImpl serviceClient;

    /**
     * Initializes an instance of Polymorphisms client.
     */
    PolymorphismClient(PolymorphismsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types that are polymorphic.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic.
     */
    public Response<Fish> getValidWithRestResponse() {
        return this.serviceClient.getValidWithRestResponse();
    }

    /**
     * Put complex types that are polymorphic.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     *         'fishtype':'Salmon',
     *         'location':'alaska',
     *         'iswild':true,
     *         'species':'king',
     *         'length':1.0,
     *         'siblings':[
     *           {
     *             'fishtype':'Shark',
     *             'age':6,
     *             'birthday': '2012-01-05T01:00:00Z',
     *             'length':20.0,
     *             'species':'predator',
     *           },
     *           {
     *             'fishtype':'Sawshark',
     *             'age':105,
     *             'birthday': '1900-01-05T01:00:00Z',
     *             'length':10.0,
     *             'picture': new Buffer([255, 255, 255, 255, 254]).toString('base64'),
     *             'species':'dangerous',
     *           },
     *           {
     *             'fishtype': 'goblin',
     *             'age': 1,
     *             'birthday': '2015-08-08T00:00:00Z',
     *             'length': 30.0,
     *             'species': 'scary',
     *             'jawsize': 5
     *           }
     *         ]
     *       };.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putValidWithRestResponse(Fish complexBody) {
        return this.serviceClient.putValidWithRestResponse(complexBody);
    }

    /**
     * Get complex types that are polymorphic, JSON key contains a dot.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, JSON key contains a dot.
     */
    public Response<DotFish> getDotSyntaxWithRestResponse() {
        return this.serviceClient.getDotSyntaxWithRestResponse();
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, with discriminator specified. Deserialization must NOT fail and use the discriminator type specified on the wire.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type, with discriminator specified.
     */
    public Response<DotFishMarket> getComposedWithDiscriminatorWithRestResponse() {
        return this.serviceClient.getComposedWithDiscriminatorWithRestResponse();
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, without discriminator specified on wire. Deserialization must NOT fail and use the explicit type of the property.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type, without discriminator specified on wire.
     */
    public Response<DotFishMarket> getComposedWithoutDiscriminatorWithRestResponse() {
        return this.serviceClient.getComposedWithoutDiscriminatorWithRestResponse();
    }

    /**
     * Get complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     */
    public Response<Salmon> getComplicatedWithRestResponse() {
        return this.serviceClient.getComplicatedWithRestResponse();
    }

    /**
     * Put complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     * 
     * @param complexBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putComplicatedWithRestResponse(Salmon complexBody) {
        return this.serviceClient.putComplicatedWithRestResponse(complexBody);
    }

    /**
     * Put complex types that are polymorphic, omitting the discriminator.
     * 
     * @param complexBody 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Salmon> putMissingDiscriminatorWithRestResponse(Salmon complexBody) {
        return this.serviceClient.putMissingDiscriminatorWithRestResponse(complexBody);
    }

    /**
     * Put complex types that are polymorphic, attempting to omit required 'birthday' field - the request should not be allowed from the client.
     * 
     * @param complexBody Please attempt put a sawshark that looks like this, the client should not allow this data to be sent:
     * {
     *     "fishtype": "sawshark",
     *     "species": "snaggle toothed",
     *     "length": 18.5,
     *     "age": 2,
     *     "birthday": "2013-06-01T01:00:00Z",
     *     "location": "alaska",
     *     "picture": base64(FF FF FF FF FE),
     *     "siblings": [
     *         {
     *             "fishtype": "shark",
     *             "species": "predator",
     *             "birthday": "2012-01-05T01:00:00Z",
     *             "length": 20,
     *             "age": 6
     *         },
     *         {
     *             "fishtype": "sawshark",
     *             "species": "dangerous",
     *             "picture": base64(FF FF FF FF FE),
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
    public Response<Void> putValidMissingRequiredWithRestResponse(Fish complexBody) {
        return this.serviceClient.putValidMissingRequiredWithRestResponse(complexBody);
    }

    /**
     * A builder for creating a new instance of the PolymorphismClient type.
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
         * Builds an instance of PolymorphismClient with the provided parameters.
         * 
         * @return an instance of PolymorphismClient.
         */
        public PolymorphismClient build() {
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
            return new PolymorphismClient(internalClient.getPolymorphisms());
        }
    }
}
