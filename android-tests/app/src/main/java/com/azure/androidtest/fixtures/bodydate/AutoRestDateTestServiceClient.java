package com.azure.androidtest.fixtures.bodydate;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodydate.implementation.AutoRestDateTestServiceImpl;
import com.azure.androidtest.fixtures.bodydate.implementation.DateOperationsImpl;
import com.azure.androidtest.fixtures.bodydate.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.threeten.bp.LocalDate;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestDateTestService type.
 */
public final class AutoRestDateTestServiceClient {
    private DateOperationsImpl serviceClient;

    /**
     * Initializes an instance of DateOperations client.
     */
    AutoRestDateTestServiceClient(DateOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null date value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null date value.
     */
    public Response<LocalDate> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get invalid date value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid date value.
     */
    public Response<LocalDate> getInvalidDateWithRestResponse() {
        return this.serviceClient.getInvalidDateWithRestResponse();
    }

    /**
     * Get overflow date value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow date value.
     */
    public Response<LocalDate> getOverflowDateWithRestResponse() {
        return this.serviceClient.getOverflowDateWithRestResponse();
    }

    /**
     * Get underflow date value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow date value.
     */
    public Response<LocalDate> getUnderflowDateWithRestResponse() {
        return this.serviceClient.getUnderflowDateWithRestResponse();
    }

    /**
     * Put max date value 9999-12-31.
     * 
     * @param dateBody date body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putMaxDateWithRestResponse(LocalDate dateBody) {
        return this.serviceClient.putMaxDateWithRestResponse(dateBody);
    }

    /**
     * Get max date value 9999-12-31.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max date value 9999-12-31.
     */
    public Response<LocalDate> getMaxDateWithRestResponse() {
        return this.serviceClient.getMaxDateWithRestResponse();
    }

    /**
     * Put min date value 0000-01-01.
     * 
     * @param dateBody date body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putMinDateWithRestResponse(LocalDate dateBody) {
        return this.serviceClient.putMinDateWithRestResponse(dateBody);
    }

    /**
     * Get min date value 0000-01-01.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min date value 0000-01-01.
     */
    public Response<LocalDate> getMinDateWithRestResponse() {
        return this.serviceClient.getMinDateWithRestResponse();
    }

    /**
     * A builder for creating a new instance of the AutoRestDateTestServiceClient type.
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
         * Builds an instance of AutoRestDateTestServiceClient with the provided parameters.
         * 
         * @return an instance of AutoRestDateTestServiceClient.
         */
        public AutoRestDateTestServiceClient build() {
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
            AutoRestDateTestServiceImpl internalClient = new AutoRestDateTestServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestDateTestServiceClient(internalClient.getDateOperations());
        }
    }
}
