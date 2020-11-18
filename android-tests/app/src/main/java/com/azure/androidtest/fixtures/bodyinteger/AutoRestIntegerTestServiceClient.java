package com.azure.androidtest.fixtures.bodyinteger;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodyinteger.implementation.AutoRestIntegerTestServiceImpl;
import com.azure.androidtest.fixtures.bodyinteger.implementation.IntsImpl;
import com.azure.androidtest.fixtures.bodyinteger.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestIntegerTestService
 * type.
 */
public final class AutoRestIntegerTestServiceClient {
    private IntsImpl serviceClient;

    /**
     * Initializes an instance of Ints client.
     */
    AutoRestIntegerTestServiceClient(IntsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null Int value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return null Int value.
     */
    public Response<Integer> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get invalid Int value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return invalid Int value.
     */
    public Response<Integer> getInvalidWithRestResponse() {
        return this.serviceClient.getInvalidWithRestResponse();
    }

    /**
     * Get overflow Int32 value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return overflow Int32 value.
     */
    public Response<Integer> getOverflowInt32WithRestResponse() {
        return this.serviceClient.getOverflowInt32WithRestResponse();
    }

    /**
     * Get underflow Int32 value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return underflow Int32 value.
     */
    public Response<Integer> getUnderflowInt32WithRestResponse() {
        return this.serviceClient.getUnderflowInt32WithRestResponse();
    }

    /**
     * Get overflow Int64 value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return overflow Int64 value.
     */
    public Response<Long> getOverflowInt64WithRestResponse() {
        return this.serviceClient.getOverflowInt64WithRestResponse();
    }

    /**
     * Get underflow Int64 value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return underflow Int64 value.
     */
    public Response<Long> getUnderflowInt64WithRestResponse() {
        return this.serviceClient.getUnderflowInt64WithRestResponse();
    }

    /**
     * Put max int32 value.
     * 
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putMax32WithRestResponse(int intBody) {
        return this.serviceClient.putMax32WithRestResponse(intBody);
    }

    /**
     * Put max int64 value.
     * 
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putMax64WithRestResponse(long intBody) {
        return this.serviceClient.putMax64WithRestResponse(intBody);
    }

    /**
     * Put min int32 value.
     * 
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putMin32WithRestResponse(int intBody) {
        return this.serviceClient.putMin32WithRestResponse(intBody);
    }

    /**
     * Put min int64 value.
     * 
     * @param intBody int body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putMin64WithRestResponse(long intBody) {
        return this.serviceClient.putMin64WithRestResponse(intBody);
    }

    /**
     * Get datetime encoded as Unix time value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return datetime encoded as Unix time value.
     */
    public Response<OffsetDateTime> getUnixTimeWithRestResponse() {
        return this.serviceClient.getUnixTimeWithRestResponse();
    }

    /**
     * Put datetime encoded as Unix time.
     * 
     * @param intBody date in seconds since 1970-01-01T00:00:00Z.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putUnixTimeDateWithRestResponse(OffsetDateTime intBody) {
        return this.serviceClient.putUnixTimeDateWithRestResponse(intBody);
    }

    /**
     * Get invalid Unix time value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return invalid Unix time value.
     */
    public Response<OffsetDateTime> getInvalidUnixTimeWithRestResponse() {
        return this.serviceClient.getInvalidUnixTimeWithRestResponse();
    }

    /**
     * Get null Unix time value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return null Unix time value.
     */
    public Response<OffsetDateTime> getNullUnixTimeWithRestResponse() {
        return this.serviceClient.getNullUnixTimeWithRestResponse();
    }

    /**
     * A builder for creating a new instance of the AutoRestIntegerTestServiceClient
     * type.
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
         * Builds an instance of AutoRestIntegerTestServiceClient with the provided
         * parameters.
         * 
         * @return an instance of AutoRestIntegerTestServiceClient.
         */
        public AutoRestIntegerTestServiceClient build() {
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
            AutoRestIntegerTestServiceImpl internalClient = new AutoRestIntegerTestServiceImpl(
                    serviceClientBuilder.build(), host);
            return new AutoRestIntegerTestServiceClient(internalClient.getInts());
        }
    }
}
