package com.azure.androidtest.fixtures.bodynumber;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodynumber.implementation.AutoRestNumberTestServiceImpl;
import com.azure.androidtest.fixtures.bodynumber.implementation.NumbersImpl;
import com.azure.androidtest.fixtures.bodynumber.models.ErrorException;
import java.math.BigDecimal;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the asynchronous AutoRestNumberTestService type.
 */
public final class AutoRestNumberTestServiceAsyncClient {
    private NumbersImpl serviceClient;

    /**
     * Initializes an instance of Numbers client.
     */
    AutoRestNumberTestServiceAsyncClient(NumbersImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null Number value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNull(final Callback<Float> callback) {
        this.serviceClient.getNull(callback);
    }

    /**
     * Get invalid float Number value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalidFloat(final Callback<Float> callback) {
        this.serviceClient.getInvalidFloat(callback);
    }

    /**
     * Get invalid double Number value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalidDouble(final Callback<Double> callback) {
        this.serviceClient.getInvalidDouble(callback);
    }

    /**
     * Get invalid decimal Number value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalidDecimal(final Callback<BigDecimal> callback) {
        this.serviceClient.getInvalidDecimal(callback);
    }

    /**
     * Put big float value 3.402823e+20.
     * 
     * @param numberBody number body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBigFloat(float numberBody, final Callback<Void> callback) {
        this.serviceClient.putBigFloat(numberBody, callback);
    }

    /**
     * Get big float value 3.402823e+20.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBigFloat(final Callback<Float> callback) {
        this.serviceClient.getBigFloat(callback);
    }

    /**
     * Put big double value 2.5976931e+101.
     * 
     * @param numberBody number body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBigDouble(double numberBody, final Callback<Void> callback) {
        this.serviceClient.putBigDouble(numberBody, callback);
    }

    /**
     * Get big double value 2.5976931e+101.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBigDouble(final Callback<Double> callback) {
        this.serviceClient.getBigDouble(callback);
    }

    /**
     * Put big double value 99999999.99.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBigDoublePositiveDecimal(final Callback<Void> callback) {
        this.serviceClient.putBigDoublePositiveDecimal(callback);
    }

    /**
     * Get big double value 99999999.99.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBigDoublePositiveDecimal(final Callback<Double> callback) {
        this.serviceClient.getBigDoublePositiveDecimal(callback);
    }

    /**
     * Put big double value -99999999.99.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBigDoubleNegativeDecimal(final Callback<Void> callback) {
        this.serviceClient.putBigDoubleNegativeDecimal(callback);
    }

    /**
     * Get big double value -99999999.99.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBigDoubleNegativeDecimal(final Callback<Double> callback) {
        this.serviceClient.getBigDoubleNegativeDecimal(callback);
    }

    /**
     * Put big decimal value 2.5976931e+101.
     * 
     * @param numberBody number body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBigDecimal(BigDecimal numberBody, final Callback<Void> callback) {
        this.serviceClient.putBigDecimal(numberBody, callback);
    }

    /**
     * Get big decimal value 2.5976931e+101.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBigDecimal(final Callback<BigDecimal> callback) {
        this.serviceClient.getBigDecimal(callback);
    }

    /**
     * Put big decimal value 99999999.99.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBigDecimalPositiveDecimal(final Callback<Void> callback) {
        this.serviceClient.putBigDecimalPositiveDecimal(callback);
    }

    /**
     * Get big decimal value 99999999.99.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBigDecimalPositiveDecimal(final Callback<BigDecimal> callback) {
        this.serviceClient.getBigDecimalPositiveDecimal(callback);
    }

    /**
     * Put big decimal value -99999999.99.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBigDecimalNegativeDecimal(final Callback<Void> callback) {
        this.serviceClient.putBigDecimalNegativeDecimal(callback);
    }

    /**
     * Get big decimal value -99999999.99.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBigDecimalNegativeDecimal(final Callback<BigDecimal> callback) {
        this.serviceClient.getBigDecimalNegativeDecimal(callback);
    }

    /**
     * Put small float value 3.402823e-20.
     * 
     * @param numberBody number body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putSmallFloat(float numberBody, final Callback<Void> callback) {
        this.serviceClient.putSmallFloat(numberBody, callback);
    }

    /**
     * Get big double value 3.402823e-20.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSmallFloat(final Callback<Double> callback) {
        this.serviceClient.getSmallFloat(callback);
    }

    /**
     * Put small double value 2.5976931e-101.
     * 
     * @param numberBody number body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putSmallDouble(double numberBody, final Callback<Void> callback) {
        this.serviceClient.putSmallDouble(numberBody, callback);
    }

    /**
     * Get big double value 2.5976931e-101.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSmallDouble(final Callback<Double> callback) {
        this.serviceClient.getSmallDouble(callback);
    }

    /**
     * Put small decimal value 2.5976931e-101.
     * 
     * @param numberBody number body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putSmallDecimal(BigDecimal numberBody, final Callback<Void> callback) {
        this.serviceClient.putSmallDecimal(numberBody, callback);
    }

    /**
     * Get small decimal value 2.5976931e-101.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSmallDecimal(final Callback<BigDecimal> callback) {
        this.serviceClient.getSmallDecimal(callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestNumberTestServiceAsyncClient type.
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
         * Builds an instance of AutoRestNumberTestServiceAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestNumberTestServiceAsyncClient.
         */
        public AutoRestNumberTestServiceAsyncClient build() {
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
            AutoRestNumberTestServiceImpl internalClient = new AutoRestNumberTestServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestNumberTestServiceAsyncClient(internalClient.getNumbers());
        }
    }
}
