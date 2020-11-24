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
 * Initializes a new instance of the asynchronous AutoRestDateTimeTestService type.
 */
public final class AutoRestDateTimeTestServiceAsyncClient {
    private DatetimeOperationsImpl serviceClient;

    /**
     * Initializes an instance of DatetimeOperations client.
     */
    AutoRestDateTimeTestServiceAsyncClient(DatetimeOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNull(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getNull(callback);
    }

    /**
     * Get invalid datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalid(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getInvalid(callback);
    }

    /**
     * Get overflow datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getOverflow(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getOverflow(callback);
    }

    /**
     * Get underflow datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUnderflow(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getUnderflow(callback);
    }

    /**
     * Put max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @param datetimeBody datetime body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putUtcMaxDateTime(OffsetDateTime datetimeBody, final Callback<Void> callback) {
        this.serviceClient.putUtcMaxDateTime(datetimeBody, callback);
    }

    /**
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @param datetimeBody datetime body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putUtcMaxDateTime7Digits(OffsetDateTime datetimeBody, final Callback<Void> callback) {
        this.serviceClient.putUtcMaxDateTime7Digits(datetimeBody, callback);
    }

    /**
     * Get max datetime value 9999-12-31t23:59:59.999z.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUtcLowercaseMaxDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getUtcLowercaseMaxDateTime(callback);
    }

    /**
     * Get max datetime value 9999-12-31T23:59:59.999Z.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUtcUppercaseMaxDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getUtcUppercaseMaxDateTime(callback);
    }

    /**
     * This is against the recommendation that asks for 3 digits, but allow to test what happens in that scenario.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUtcUppercaseMaxDateTime7Digits(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getUtcUppercaseMaxDateTime7Digits(callback);
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999+14:00.
     * 
     * @param datetimeBody datetime body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putLocalPositiveOffsetMaxDateTime(OffsetDateTime datetimeBody, final Callback<Void> callback) {
        this.serviceClient.putLocalPositiveOffsetMaxDateTime(datetimeBody, callback);
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999+14:00.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLocalPositiveOffsetLowercaseMaxDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getLocalPositiveOffsetLowercaseMaxDateTime(callback);
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999+14:00.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLocalPositiveOffsetUppercaseMaxDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getLocalPositiveOffsetUppercaseMaxDateTime(callback);
    }

    /**
     * Put max datetime value with positive numoffset 9999-12-31t23:59:59.999-14:00.
     * 
     * @param datetimeBody datetime body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putLocalNegativeOffsetMaxDateTime(OffsetDateTime datetimeBody, final Callback<Void> callback) {
        this.serviceClient.putLocalNegativeOffsetMaxDateTime(datetimeBody, callback);
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31T23:59:59.999-14:00.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLocalNegativeOffsetUppercaseMaxDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getLocalNegativeOffsetUppercaseMaxDateTime(callback);
    }

    /**
     * Get max datetime value with positive num offset 9999-12-31t23:59:59.999-14:00.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLocalNegativeOffsetLowercaseMaxDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getLocalNegativeOffsetLowercaseMaxDateTime(callback);
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00Z.
     * 
     * @param datetimeBody datetime body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putUtcMinDateTime(OffsetDateTime datetimeBody, final Callback<Void> callback) {
        this.serviceClient.putUtcMinDateTime(datetimeBody, callback);
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00Z.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getUtcMinDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getUtcMinDateTime(callback);
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @param datetimeBody datetime body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putLocalPositiveOffsetMinDateTime(OffsetDateTime datetimeBody, final Callback<Void> callback) {
        this.serviceClient.putLocalPositiveOffsetMinDateTime(datetimeBody, callback);
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00+14:00.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLocalPositiveOffsetMinDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getLocalPositiveOffsetMinDateTime(callback);
    }

    /**
     * Put min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @param datetimeBody datetime body.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putLocalNegativeOffsetMinDateTime(OffsetDateTime datetimeBody, final Callback<Void> callback) {
        this.serviceClient.putLocalNegativeOffsetMinDateTime(datetimeBody, callback);
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00-14:00.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLocalNegativeOffsetMinDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getLocalNegativeOffsetMinDateTime(callback);
    }

    /**
     * Get min datetime value 0001-01-01T00:00:00.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLocalNoOffsetMinDateTime(final Callback<OffsetDateTime> callback) {
        this.serviceClient.getLocalNoOffsetMinDateTime(callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestDateTimeTestServiceAsyncClient type.
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
         * Builds an instance of AutoRestDateTimeTestServiceAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestDateTimeTestServiceAsyncClient.
         */
        public AutoRestDateTimeTestServiceAsyncClient build() {
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
            return new AutoRestDateTimeTestServiceAsyncClient(internalClient.getDatetimeOperations());
        }
    }
}
