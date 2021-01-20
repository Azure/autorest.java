package com.azure.androidtest.fixtures.bodybyte;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.androidtest.fixtures.bodybyte.implementation.AutoRestSwaggerBATByteServiceImpl;
import com.azure.androidtest.fixtures.bodybyte.implementation.ByteOperationsImpl;
import com.azure.androidtest.fixtures.bodybyte.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestSwaggerBATByteService type.
 */
public final class AutoRestSwaggerBATByteServiceClient {
    private ByteOperationsImpl serviceClient;

    /**
     * Initializes an instance of ByteOperations client.
     */
    AutoRestSwaggerBATByteServiceClient(ByteOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null byte value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null byte value.
     */
    public Response<byte[]> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get empty byte value ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty byte value ''.
     */
    public Response<byte[]> getEmptyWithRestResponse() {
        return this.serviceClient.getEmptyWithRestResponse();
    }

    /**
     * Get non-ascii byte string hex(FF FE FD FC FB FA F9 F8 F7 F6).
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return non-ascii byte string hex(FF FE FD FC FB FA F9 F8 F7 F6).
     */
    public Response<byte[]> getNonAsciiWithRestResponse() {
        return this.serviceClient.getNonAsciiWithRestResponse();
    }

    /**
     * Put non-ascii byte string hex(FF FE FD FC FB FA F9 F8 F7 F6).
     * 
     * @param byteBody Base64-encoded non-ascii byte string hex(FF FE FD FC FB FA F9 F8 F7 F6).
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putNonAsciiWithRestResponse(byte[] byteBody) {
        return this.serviceClient.putNonAsciiWithRestResponse(byteBody);
    }

    /**
     * Get invalid byte value ':::SWAGGER::::'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid byte value ':::SWAGGER::::'.
     */
    public Response<byte[]> getInvalidWithRestResponse() {
        return this.serviceClient.getInvalidWithRestResponse();
    }

    /**
     * A builder for creating a new instance of the AutoRestSwaggerBATByteServiceClient type.
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
         * Builds an instance of AutoRestSwaggerBATByteServiceClient with the provided parameters.
         * 
         * @return an instance of AutoRestSwaggerBATByteServiceClient.
         */
        public AutoRestSwaggerBATByteServiceClient build() {
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
            AutoRestSwaggerBATByteServiceImpl internalClient = new AutoRestSwaggerBATByteServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestSwaggerBATByteServiceClient(internalClient.getByteOperations());
        }
    }
}
