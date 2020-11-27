package com.azure.androidtest.fixtures.bodycomplex;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
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
 * Initializes a new instance of the asynchronous AutoRestComplexTestService type.
 */
public final class PrimitiveAsyncClient {
    private PrimitivesImpl serviceClient;

    /**
     * Initializes an instance of Primitives client.
     */
    PrimitiveAsyncClient(PrimitivesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get complex types with integer properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInt(final Callback<IntWrapper> callback) {
        this.serviceClient.getInt(callback);
    }

    /**
     * Put complex types with integer properties.
     * 
     * @param complexBody Please put -1 and 2.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putInt(IntWrapper complexBody, final Callback<Void> callback) {
        this.serviceClient.putInt(complexBody, callback);
    }

    /**
     * Get complex types with long properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLong(final Callback<LongWrapper> callback) {
        this.serviceClient.getLong(callback);
    }

    /**
     * Put complex types with long properties.
     * 
     * @param complexBody Please put 1099511627775 and -999511627788.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putLong(LongWrapper complexBody, final Callback<Void> callback) {
        this.serviceClient.putLong(complexBody, callback);
    }

    /**
     * Get complex types with float properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getFloat(final Callback<FloatWrapper> callback) {
        this.serviceClient.getFloat(callback);
    }

    /**
     * Put complex types with float properties.
     * 
     * @param complexBody Please put 1.05 and -0.003.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putFloat(FloatWrapper complexBody, final Callback<Void> callback) {
        this.serviceClient.putFloat(complexBody, callback);
    }

    /**
     * Get complex types with double properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDouble(final Callback<DoubleWrapper> callback) {
        this.serviceClient.getDouble(callback);
    }

    /**
     * Put complex types with double properties.
     * 
     * @param complexBody Please put 3e-100 and -0.000000000000000000000000000000000000000000000000000000005.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDouble(DoubleWrapper complexBody, final Callback<Void> callback) {
        this.serviceClient.putDouble(complexBody, callback);
    }

    /**
     * Get complex types with bool properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getBool(final Callback<BooleanWrapper> callback) {
        this.serviceClient.getBool(callback);
    }

    /**
     * Put complex types with bool properties.
     * 
     * @param complexBody Please put true and false.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putBool(BooleanWrapper complexBody, final Callback<Void> callback) {
        this.serviceClient.putBool(complexBody, callback);
    }

    /**
     * Get complex types with string properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getString(final Callback<StringWrapper> callback) {
        this.serviceClient.getString(callback);
    }

    /**
     * Put complex types with string properties.
     * 
     * @param complexBody Please put 'goodrequest', '', and null.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putString(StringWrapper complexBody, final Callback<Void> callback) {
        this.serviceClient.putString(complexBody, callback);
    }

    /**
     * Get complex types with date properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDate(final Callback<DateWrapper> callback) {
        this.serviceClient.getDate(callback);
    }

    /**
     * Put complex types with date properties.
     * 
     * @param complexBody Please put '0001-01-01' and '2016-02-29'.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDate(DateWrapper complexBody, final Callback<Void> callback) {
        this.serviceClient.putDate(complexBody, callback);
    }

    /**
     * Get complex types with datetime properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTime(final Callback<DatetimeWrapper> callback) {
        this.serviceClient.getDateTime(callback);
    }

    /**
     * Put complex types with datetime properties.
     * 
     * @param complexBody Please put '0001-01-01T12:00:00-04:00' and '2015-05-18T11:38:00-08:00'.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDateTime(DatetimeWrapper complexBody, final Callback<Void> callback) {
        this.serviceClient.putDateTime(complexBody, callback);
    }

    /**
     * Get complex types with datetimeRfc1123 properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDateTimeRfc1123(final Callback<Datetimerfc1123Wrapper> callback) {
        this.serviceClient.getDateTimeRfc1123(callback);
    }

    /**
     * Put complex types with datetimeRfc1123 properties.
     * 
     * @param complexBody Please put 'Mon, 01 Jan 0001 12:00:00 GMT' and 'Mon, 18 May 2015 11:38:00 GMT'.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDateTimeRfc1123(Datetimerfc1123Wrapper complexBody, final Callback<Void> callback) {
        this.serviceClient.putDateTimeRfc1123(complexBody, callback);
    }

    /**
     * Get complex types with duration properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDuration(final Callback<DurationWrapper> callback) {
        this.serviceClient.getDuration(callback);
    }

    /**
     * Put complex types with duration properties.
     * 
     * @param field 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDuration(Duration field, final Callback<Void> callback) {
        this.serviceClient.putDuration(field, callback);
    }

    /**
     * Get complex types with byte properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getByte(final Callback<ByteWrapper> callback) {
        this.serviceClient.getByte(callback);
    }

    /**
     * Put complex types with byte properties.
     * 
     * @param field 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putByte(byte[] field, final Callback<Void> callback) {
        this.serviceClient.putByte(field, callback);
    }

    /**
     * A builder for creating a new instance of the PrimitiveAsyncClient type.
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
         * Builds an instance of PrimitiveAsyncClient with the provided parameters.
         * 
         * @return an instance of PrimitiveAsyncClient.
         */
        public PrimitiveAsyncClient build() {
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
            return new PrimitiveAsyncClient(internalClient.getPrimitives());
        }
    }
}
