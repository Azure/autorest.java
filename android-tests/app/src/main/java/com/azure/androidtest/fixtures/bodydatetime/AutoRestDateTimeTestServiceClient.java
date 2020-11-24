package com.azure.androidtest.fixtures.bodydatetime;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodydatetime.implementation.AutoRestDateTimeTestServiceImpl;
import com.azure.androidtest.fixtures.bodydatetime.implementation.DatetimeOperationsImpl;
import com.azure.androidtest.fixtures.bodydatetime.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.threeten.bp.OffsetDateTime;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestDateTimeTestService type.
 */
public final class AutoRestDateTimeTestServiceClient {
    private DatetimeOperationsImpl serviceClient;

    /**
     * Initializes an instance of DatetimeOperations client.
     */
    AutoRestDateTimeTestServiceClient(DatetimeOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null datetime value.
     */
    public Response<OffsetDateTime> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get invalid datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid datetime value.
     */
    public Response<OffsetDateTime> getInvalidWithRestResponse() {
        return this.serviceClient.getInvalidWithRestResponse();
    }

    /**
     * Get overflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow datetime value.
     */
    public Response<OffsetDateTime> getOverflowWithRestResponse() {
        return this.serviceClient.getOverflowWithRestResponse();
    }

    /**
     * Get underflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow datetime value.
     */
    public Response<OffsetDateTime> getUnderflowWithRestResponse() {
        return this.serviceClient.getUnderflowWithRestResponse();
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putUtcMaxDateTimeWithRestResponse(OffsetDateTime datetimeBody) {
        return this.serviceClient.putUtcMaxDateTimeWithRestResponse(datetimeBody);
    }

    /**
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putUtcMaxDateTime7DigitsWithRestResponse(OffsetDateTime datetimeBody) {
        return this.serviceClient.putUtcMaxDateTime7DigitsWithRestResponse(datetimeBody);
    }

    /**
     * Get max datetime value 9999-12-31t23:59:59.999z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value 9999-12-31t23:59:59.
     */
    public Response<OffsetDateTime> getUtcLowercaseMaxDateTimeWithRestResponse() {
        return this.serviceClient.getUtcLowercaseMaxDateTimeWithRestResponse();
    }

    /**
     * Get max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value 9999-12-31T23:59:59.
     */
    public Response<OffsetDateTime> getUtcUppercaseMaxDateTimeWithRestResponse() {
        return this.serviceClient.getUtcUppercaseMaxDateTimeWithRestResponse();
    }

    /**
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<OffsetDateTime> getUtcUppercaseMaxDateTime7DigitsWithRestResponse() {
        return this.serviceClient.getUtcUppercaseMaxDateTime7DigitsWithRestResponse();
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999+14:00.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putLocalPositiveOffsetMaxDateTimeWithRestResponse(OffsetDateTime datetimeBody) {
        return this.serviceClient.putLocalPositiveOffsetMaxDateTimeWithRestResponse(datetimeBody);
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value with positive num offset 9999-12-31t23:59:59.
     */
    public Response<OffsetDateTime> getLocalPositiveOffsetLowercaseMaxDateTimeWithRestResponse() {
        return this.serviceClient.getLocalPositiveOffsetLowercaseMaxDateTimeWithRestResponse();
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value with positive num offset 9999-12-31T23:59:59.
     */
    public Response<OffsetDateTime> getLocalPositiveOffsetUppercaseMaxDateTimeWithRestResponse() {
        return this.serviceClient.getLocalPositiveOffsetUppercaseMaxDateTimeWithRestResponse();
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999-14:00.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putLocalNegativeOffsetMaxDateTimeWithRestResponse(OffsetDateTime datetimeBody) {
        return this.serviceClient.putLocalNegativeOffsetMaxDateTimeWithRestResponse(datetimeBody);
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value with positive num offset 9999-12-31T23:59:59.
     */
    public Response<OffsetDateTime> getLocalNegativeOffsetUppercaseMaxDateTimeWithRestResponse() {
        return this.serviceClient.getLocalNegativeOffsetUppercaseMaxDateTimeWithRestResponse();
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value with positive num offset 9999-12-31t23:59:59.
     */
    public Response<OffsetDateTime> getLocalNegativeOffsetLowercaseMaxDateTimeWithRestResponse() {
        return this.serviceClient.getLocalNegativeOffsetLowercaseMaxDateTimeWithRestResponse();
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00Z.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putUtcMinDateTimeWithRestResponse(OffsetDateTime datetimeBody) {
        return this.serviceClient.putUtcMinDateTimeWithRestResponse(datetimeBody);
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00Z.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00Z.
     */
    public Response<OffsetDateTime> getUtcMinDateTimeWithRestResponse() {
        return this.serviceClient.getUtcMinDateTimeWithRestResponse();
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putLocalPositiveOffsetMinDateTimeWithRestResponse(OffsetDateTime datetimeBody) {
        return this.serviceClient.putLocalPositiveOffsetMinDateTimeWithRestResponse(datetimeBody);
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00+14:00.
     */
    public Response<OffsetDateTime> getLocalPositiveOffsetMinDateTimeWithRestResponse() {
        return this.serviceClient.getLocalPositiveOffsetMinDateTimeWithRestResponse();
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putLocalNegativeOffsetMinDateTimeWithRestResponse(OffsetDateTime datetimeBody) {
        return this.serviceClient.putLocalNegativeOffsetMinDateTimeWithRestResponse(datetimeBody);
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00-14:00.
     */
    public Response<OffsetDateTime> getLocalNegativeOffsetMinDateTimeWithRestResponse() {
        return this.serviceClient.getLocalNegativeOffsetMinDateTimeWithRestResponse();
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value 0001-01-01T00:00:00.
     */
    public Response<OffsetDateTime> getLocalNoOffsetMinDateTimeWithRestResponse() {
        return this.serviceClient.getLocalNoOffsetMinDateTimeWithRestResponse();
    }

    /**
     * A builder for creating a new instance of the AutoRestDateTimeTestServiceClient type.
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
         * Builds an instance of AutoRestDateTimeTestServiceClient with the provided parameters.
         * 
         * @return an instance of AutoRestDateTimeTestServiceClient.
         */
        public AutoRestDateTimeTestServiceClient build() {
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
            AutoRestDateTimeTestServiceImpl internalClient = new AutoRestDateTimeTestServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestDateTimeTestServiceClient(internalClient.getDatetimeOperations());
        }
    }
}
