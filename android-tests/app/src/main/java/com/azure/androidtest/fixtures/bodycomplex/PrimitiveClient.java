package com.azure.androidtest.fixtures.bodycomplex;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.androidtest.fixtures.bodycomplex.implementation.AutoRestComplexTestServiceImpl;
import com.azure.androidtest.fixtures.bodycomplex.implementation.PrimitivesImpl;
import com.azure.androidtest.fixtures.bodycomplex.models.BooleanWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.ByteWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.Datetimerfc1123Wrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.DatetimeWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.DateWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.DoubleWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.DurationWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.ErrorException;
import com.azure.androidtest.fixtures.bodycomplex.models.FloatWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.IntWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.LongWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.StringWrapper;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.threeten.bp.Duration;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestComplexTestService type.
 */
public final class PrimitiveClient {
    private PrimitivesImpl serviceClient;

    /**
     * Initializes an instance of Primitives client.
     */
    PrimitiveClient(PrimitivesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with integer properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with integer properties.
     */
    public Response<IntWrapper> getIntWithRestResponse() {
        return this.serviceClient.getIntWithRestResponse();
    }

    /**
     * Put complex types with integer properties.
     * 
     * @param complexBody Please put -1 and 2.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putIntWithRestResponse(IntWrapper complexBody) {
        return this.serviceClient.putIntWithRestResponse(complexBody);
    }

    /**
     * Get complex types with long properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with long properties.
     */
    public Response<LongWrapper> getLongWithRestResponse() {
        return this.serviceClient.getLongWithRestResponse();
    }

    /**
     * Put complex types with long properties.
     * 
     * @param complexBody Please put 1099511627775 and -999511627788.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putLongWithRestResponse(LongWrapper complexBody) {
        return this.serviceClient.putLongWithRestResponse(complexBody);
    }

    /**
     * Get complex types with float properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with float properties.
     */
    public Response<FloatWrapper> getFloatWithRestResponse() {
        return this.serviceClient.getFloatWithRestResponse();
    }

    /**
     * Put complex types with float properties.
     * 
     * @param complexBody Please put 1.05 and -0.003.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putFloatWithRestResponse(FloatWrapper complexBody) {
        return this.serviceClient.putFloatWithRestResponse(complexBody);
    }

    /**
     * Get complex types with double properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with double properties.
     */
    public Response<DoubleWrapper> getDoubleWithRestResponse() {
        return this.serviceClient.getDoubleWithRestResponse();
    }

    /**
     * Put complex types with double properties.
     * 
     * @param complexBody Please put 3e-100 and -0.000000000000000000000000000000000000000000000000000000005.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDoubleWithRestResponse(DoubleWrapper complexBody) {
        return this.serviceClient.putDoubleWithRestResponse(complexBody);
    }

    /**
     * Get complex types with bool properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with bool properties.
     */
    public Response<BooleanWrapper> getBoolWithRestResponse() {
        return this.serviceClient.getBoolWithRestResponse();
    }

    /**
     * Put complex types with bool properties.
     * 
     * @param complexBody Please put true and false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBoolWithRestResponse(BooleanWrapper complexBody) {
        return this.serviceClient.putBoolWithRestResponse(complexBody);
    }

    /**
     * Get complex types with string properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with string properties.
     */
    public Response<StringWrapper> getStringWithRestResponse() {
        return this.serviceClient.getStringWithRestResponse();
    }

    /**
     * Put complex types with string properties.
     * 
     * @param complexBody Please put 'goodrequest', '', and null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putStringWithRestResponse(StringWrapper complexBody) {
        return this.serviceClient.putStringWithRestResponse(complexBody);
    }

    /**
     * Get complex types with date properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with date properties.
     */
    public Response<DateWrapper> getDateWithRestResponse() {
        return this.serviceClient.getDateWithRestResponse();
    }

    /**
     * Put complex types with date properties.
     * 
     * @param complexBody Please put '0001-01-01' and '2016-02-29'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDateWithRestResponse(DateWrapper complexBody) {
        return this.serviceClient.putDateWithRestResponse(complexBody);
    }

    /**
     * Get complex types with datetime properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetime properties.
     */
    public Response<DatetimeWrapper> getDateTimeWithRestResponse() {
        return this.serviceClient.getDateTimeWithRestResponse();
    }

    /**
     * Put complex types with datetime properties.
     * 
     * @param complexBody Please put '0001-01-01T12:00:00-04:00' and '2015-05-18T11:38:00-08:00'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDateTimeWithRestResponse(DatetimeWrapper complexBody) {
        return this.serviceClient.putDateTimeWithRestResponse(complexBody);
    }

    /**
     * Get complex types with datetimeRfc1123 properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with datetimeRfc1123 properties.
     */
    public Response<Datetimerfc1123Wrapper> getDateTimeRfc1123WithRestResponse() {
        return this.serviceClient.getDateTimeRfc1123WithRestResponse();
    }

    /**
     * Put complex types with datetimeRfc1123 properties.
     * 
     * @param complexBody Please put 'Mon, 01 Jan 0001 12:00:00 GMT' and 'Mon, 18 May 2015 11:38:00 GMT'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDateTimeRfc1123WithRestResponse(Datetimerfc1123Wrapper complexBody) {
        return this.serviceClient.putDateTimeRfc1123WithRestResponse(complexBody);
    }

    /**
     * Get complex types with duration properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with duration properties.
     */
    public Response<DurationWrapper> getDurationWithRestResponse() {
        return this.serviceClient.getDurationWithRestResponse();
    }

    /**
     * Put complex types with duration properties.
     * 
     * @param field 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putDurationWithRestResponse(Duration field) {
        return this.serviceClient.putDurationWithRestResponse(field);
    }

    /**
     * Get complex types with byte properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with byte properties.
     */
    public Response<ByteWrapper> getByteWithRestResponse() {
        return this.serviceClient.getByteWithRestResponse();
    }

    /**
     * Put complex types with byte properties.
     * 
     * @param field 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putByteWithRestResponse(byte[] field) {
        return this.serviceClient.putByteWithRestResponse(field);
    }

    /**
     * A builder for creating a new instance of the PrimitiveClient type.
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
         * Builds an instance of PrimitiveClient with the provided parameters.
         * 
         * @return an instance of PrimitiveClient.
         */
        public PrimitiveClient build() {
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
            return new PrimitiveClient(internalClient.getPrimitives());
        }
    }
}
