package com.azure.androidtest.fixtures.bodydatetimerfc1123;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodydatetimerfc1123.implementation.AutoRestRFC1123DateTimeTestServiceImpl;
import com.azure.androidtest.fixtures.bodydatetimerfc1123.implementation.Datetimerfc1123sImpl;
import com.azure.androidtest.fixtures.bodydatetimerfc1123.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestRFC1123DateTimeTestService type.
 */
public final class AutoRestRFC1123DateTimeTestServiceClient {
    private Datetimerfc1123sImpl serviceClient;

    /**
     * Initializes an instance of Datetimerfc1123s client.
     */
    AutoRestRFC1123DateTimeTestServiceClient(Datetimerfc1123sImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null datetime value.
     */
    public Response<DateTimeRfc1123> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Get invalid datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid datetime value.
     */
    public Response<DateTimeRfc1123> getInvalidWithRestResponse() {
        return this.serviceClient.getInvalidWithRestResponse();
    }

    /**
     * Get overflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return overflow datetime value.
     */
    public Response<DateTimeRfc1123> getOverflowWithRestResponse() {
        return this.serviceClient.getOverflowWithRestResponse();
    }

    /**
     * Get underflow datetime value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return underflow datetime value.
     */
    public Response<DateTimeRfc1123> getUnderflowWithRestResponse() {
        return this.serviceClient.getUnderflowWithRestResponse();
    }

    /**
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putUtcMaxDateTimeWithRestResponse(DateTimeRfc1123 datetimeBody) {
        return this.serviceClient.putUtcMaxDateTimeWithRestResponse(datetimeBody);
    }

    /**
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value fri, 31 dec 9999 23:59:59 gmt.
     */
    public Response<DateTimeRfc1123> getUtcLowercaseMaxDateTimeWithRestResponse() {
        return this.serviceClient.getUtcLowercaseMaxDateTimeWithRestResponse();
    }

    /**
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     */
    public Response<DateTimeRfc1123> getUtcUppercaseMaxDateTimeWithRestResponse() {
        return this.serviceClient.getUtcUppercaseMaxDateTimeWithRestResponse();
    }

    /**
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putUtcMinDateTimeWithRestResponse(DateTimeRfc1123 datetimeBody) {
        return this.serviceClient.putUtcMinDateTimeWithRestResponse(datetimeBody);
    }

    /**
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     */
    public Response<DateTimeRfc1123> getUtcMinDateTimeWithRestResponse() {
        return this.serviceClient.getUtcMinDateTimeWithRestResponse();
    }

    /**
     * A builder for creating a new instance of the AutoRestRFC1123DateTimeTestServiceClient type.
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
         * Builds an instance of AutoRestRFC1123DateTimeTestServiceClient with the provided parameters.
         * 
         * @return an instance of AutoRestRFC1123DateTimeTestServiceClient.
         */
        public AutoRestRFC1123DateTimeTestServiceClient build() {
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
            AutoRestRFC1123DateTimeTestServiceImpl internalClient = new AutoRestRFC1123DateTimeTestServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestRFC1123DateTimeTestServiceClient(internalClient.getDatetimerfc1123s());
        }
    }
}
