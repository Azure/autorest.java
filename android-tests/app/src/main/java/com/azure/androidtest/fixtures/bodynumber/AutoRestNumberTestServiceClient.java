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
 * Initializes a new instance of the synchronous AutoRestNumberTestService type.
 */
public final class AutoRestNumberTestServiceClient {
    private NumbersImpl serviceClient;

    /**
     * Initializes an instance of Numbers client.
     */
    AutoRestNumberTestServiceClient(NumbersImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Number value.
     */
    public Response<Float> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get invalid float Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid float Number value.
     */
    public Response<Float> getInvalidFloatWithRestResponse() {
        return this.serviceClient.getInvalidFloatWithRestResponse();
    }

    /**
     * Get invalid double Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid double Number value.
     */
    public Response<Double> getInvalidDoubleWithRestResponse() {
        return this.serviceClient.getInvalidDoubleWithRestResponse();
    }

    /**
     * Get invalid decimal Number value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid decimal Number value.
     */
    public Response<BigDecimal> getInvalidDecimalWithRestResponse() {
        return this.serviceClient.getInvalidDecimalWithRestResponse();
    }

    /**
     * Put big float value 3.402823e+20.
     * 
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBigFloatWithRestResponse(float numberBody) {
        return this.serviceClient.putBigFloatWithRestResponse(numberBody);
    }

    /**
     * Get big float value 3.402823e+20.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big float value 3.
     */
    public Response<Float> getBigFloatWithRestResponse() {
        return this.serviceClient.getBigFloatWithRestResponse();
    }

    /**
     * Put big double value 2.5976931e+101.
     * 
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBigDoubleWithRestResponse(double numberBody) {
        return this.serviceClient.putBigDoubleWithRestResponse(numberBody);
    }

    /**
     * Get big double value 2.5976931e+101.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 2.
     */
    public Response<Double> getBigDoubleWithRestResponse() {
        return this.serviceClient.getBigDoubleWithRestResponse();
    }

    /**
     * Put big double value 99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBigDoublePositiveDecimalWithRestResponse() {
        return this.serviceClient.putBigDoublePositiveDecimalWithRestResponse();
    }

    /**
     * Get big double value 99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 99999999.
     */
    public Response<Double> getBigDoublePositiveDecimalWithRestResponse() {
        return this.serviceClient.getBigDoublePositiveDecimalWithRestResponse();
    }

    /**
     * Put big double value -99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBigDoubleNegativeDecimalWithRestResponse() {
        return this.serviceClient.putBigDoubleNegativeDecimalWithRestResponse();
    }

    /**
     * Get big double value -99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value -99999999.
     */
    public Response<Double> getBigDoubleNegativeDecimalWithRestResponse() {
        return this.serviceClient.getBigDoubleNegativeDecimalWithRestResponse();
    }

    /**
     * Put big decimal value 2.5976931e+101.
     * 
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBigDecimalWithRestResponse(BigDecimal numberBody) {
        return this.serviceClient.putBigDecimalWithRestResponse(numberBody);
    }

    /**
     * Get big decimal value 2.5976931e+101.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value 2.
     */
    public Response<BigDecimal> getBigDecimalWithRestResponse() {
        return this.serviceClient.getBigDecimalWithRestResponse();
    }

    /**
     * Put big decimal value 99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBigDecimalPositiveDecimalWithRestResponse() {
        return this.serviceClient.putBigDecimalPositiveDecimalWithRestResponse();
    }

    /**
     * Get big decimal value 99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value 99999999.
     */
    public Response<BigDecimal> getBigDecimalPositiveDecimalWithRestResponse() {
        return this.serviceClient.getBigDecimalPositiveDecimalWithRestResponse();
    }

    /**
     * Put big decimal value -99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBigDecimalNegativeDecimalWithRestResponse() {
        return this.serviceClient.putBigDecimalNegativeDecimalWithRestResponse();
    }

    /**
     * Get big decimal value -99999999.99.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big decimal value -99999999.
     */
    public Response<BigDecimal> getBigDecimalNegativeDecimalWithRestResponse() {
        return this.serviceClient.getBigDecimalNegativeDecimalWithRestResponse();
    }

    /**
     * Put small float value 3.402823e-20.
     * 
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putSmallFloatWithRestResponse(float numberBody) {
        return this.serviceClient.putSmallFloatWithRestResponse(numberBody);
    }

    /**
     * Get big double value 3.402823e-20.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 3.
     */
    public Response<Double> getSmallFloatWithRestResponse() {
        return this.serviceClient.getSmallFloatWithRestResponse();
    }

    /**
     * Put small double value 2.5976931e-101.
     * 
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putSmallDoubleWithRestResponse(double numberBody) {
        return this.serviceClient.putSmallDoubleWithRestResponse(numberBody);
    }

    /**
     * Get big double value 2.5976931e-101.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return big double value 2.
     */
    public Response<Double> getSmallDoubleWithRestResponse() {
        return this.serviceClient.getSmallDoubleWithRestResponse();
    }

    /**
     * Put small decimal value 2.5976931e-101.
     * 
     * @param numberBody number body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putSmallDecimalWithRestResponse(BigDecimal numberBody) {
        return this.serviceClient.putSmallDecimalWithRestResponse(numberBody);
    }

    /**
     * Get small decimal value 2.5976931e-101.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return small decimal value 2.
     */
    public Response<BigDecimal> getSmallDecimalWithRestResponse() {
        return this.serviceClient.getSmallDecimalWithRestResponse();
    }

    /**
     * A builder for creating a new instance of the AutoRestNumberTestServiceClient type.
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
         * Builds an instance of AutoRestNumberTestServiceClient with the provided parameters.
         * 
         * @return an instance of AutoRestNumberTestServiceClient.
         */
        public AutoRestNumberTestServiceClient build() {
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
            return new AutoRestNumberTestServiceClient(internalClient.getNumbers());
        }
    }
}
