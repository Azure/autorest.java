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
 * Initializes a new instance of the asynchronous AutoRestComplexTestService type.
 */
public final class PolymorphismAsyncClient {
    private PolymorphismsImpl serviceClient;

    /**
     * Initializes an instance of Polymorphisms client.
     */
    PolymorphismAsyncClient(PolymorphismsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types that are polymorphic.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getValid(final Callback<Fish> callback) {
        this.serviceClient.getValid(callback);
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
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putValid(Fish complexBody, final Callback<Void> callback) {
        this.serviceClient.putValid(complexBody, callback);
    }

    /**
     * Get complex types that are polymorphic, JSON key contains a dot.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDotSyntax(final Callback<DotFish> callback) {
        this.serviceClient.getDotSyntax(callback);
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, with discriminator specified. Deserialization must NOT fail and use the discriminator type specified on the wire.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComposedWithDiscriminator(final Callback<DotFishMarket> callback) {
        this.serviceClient.getComposedWithDiscriminator(callback);
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, without discriminator specified on wire. Deserialization must NOT fail and use the explicit type of the property.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComposedWithoutDiscriminator(final Callback<DotFishMarket> callback) {
        this.serviceClient.getComposedWithoutDiscriminator(callback);
    }

    /**
     * Get complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplicated(final Callback<Salmon> callback) {
        this.serviceClient.getComplicated(callback);
    }

    /**
     * Put complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     * 
     * @param complexBody 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putComplicated(Salmon complexBody, final Callback<Void> callback) {
        this.serviceClient.putComplicated(complexBody, callback);
    }

    /**
     * Put complex types that are polymorphic, omitting the discriminator.
     * 
     * @param complexBody 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putMissingDiscriminator(Salmon complexBody, final Callback<Salmon> callback) {
        this.serviceClient.putMissingDiscriminator(complexBody, callback);
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
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putValidMissingRequired(Fish complexBody, final Callback<Void> callback) {
        this.serviceClient.putValidMissingRequired(complexBody, callback);
    }

    /**
     * A builder for creating a new instance of the PolymorphismAsyncClient type.
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
         * Builds an instance of PolymorphismAsyncClient with the provided parameters.
         * 
         * @return an instance of PolymorphismAsyncClient.
         */
        public PolymorphismAsyncClient build() {
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
            return new PolymorphismAsyncClient(internalClient.getPolymorphisms());
        }
    }
}
