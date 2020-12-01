package com.azure.androidtest.fixtures.bodystring;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.androidtest.fixtures.bodystring.implementation.AutoRestSwaggerBATServiceImpl;
import com.azure.androidtest.fixtures.bodystring.implementation.EnumsImpl;
import com.azure.androidtest.fixtures.bodystring.models.Colors;
import com.azure.androidtest.fixtures.bodystring.models.ErrorException;
import com.azure.androidtest.fixtures.bodystring.models.RefColorConstant;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestSwaggerBATService type.
 */
public final class EnumClient {
    private EnumsImpl serviceClient;

    /**
     * Initializes an instance of Enums client.
     */
    EnumClient(EnumsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color',
     * 'blue_color'.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return enum value 'red color' from enumeration of 'red color',
     *         'green-color', 'blue_color'.
     */
    public Response<Colors> getNotExpandableWithRestResponse() {
        return this.serviceClient.getNotExpandableWithRestResponse();
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color',
     * 'blue_color'.
     * 
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putNotExpandableWithRestResponse(Colors stringBody) {
        return this.serviceClient.putNotExpandableWithRestResponse(stringBody);
    }

    /**
     * Get enum value 'red color' from enumeration of 'red color', 'green-color',
     * 'blue_color'.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return enum value 'red color' from enumeration of 'red color',
     *         'green-color', 'blue_color'.
     */
    public Response<Colors> getReferencedWithRestResponse() {
        return this.serviceClient.getReferencedWithRestResponse();
    }

    /**
     * Sends value 'red color' from enumeration of 'red color', 'green-color',
     * 'blue_color'.
     * 
     * @param enumStringBody enum string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putReferencedWithRestResponse(Colors enumStringBody) {
        return this.serviceClient.putReferencedWithRestResponse(enumStringBody);
    }

    /**
     * Get value 'green-color' from the constant.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return value 'green-color' from the constant.
     */
    public Response<RefColorConstant> getReferencedConstantWithRestResponse() {
        return this.serviceClient.getReferencedConstantWithRestResponse();
    }

    /**
     * Sends value 'green-color' from a constant.
     * 
     * @param field1 Sample string.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putReferencedConstantWithRestResponse(String field1) {
        return this.serviceClient.putReferencedConstantWithRestResponse(field1);
    }

    /**
     * A builder for creating a new instance of the EnumClient type.
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
         * Builds an instance of EnumClient with the provided parameters.
         * 
         * @return an instance of EnumClient.
         */
        public EnumClient build() {
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
            AutoRestSwaggerBATServiceImpl internalClient = new AutoRestSwaggerBATServiceImpl(
                    serviceClientBuilder.build(), host);
            return new EnumClient(internalClient.getEnums());
        }
    }
}
