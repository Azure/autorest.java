package com.azure.androidtest.fixtures.report;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.androidtest.fixtures.report.implementation.AutoRestReportServiceImpl;
import com.azure.androidtest.fixtures.report.models.ErrorException;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Initializes a new instance of the asynchronous AutoRestReportService type.
 */
public final class AutoRestReportServiceAsyncClient {
    private AutoRestReportServiceImpl serviceClient;

    /**
     * Initializes an instance of AutoRestReportService client.
     */
    AutoRestReportServiceAsyncClient(AutoRestReportServiceImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get test coverage report.
     * 
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getReport(String qualifier, final Callback<Map<String, Integer>> callback) {
        this.serviceClient.getReport(qualifier, callback);
    }

    /**
     * Get optional test coverage report.
     * 
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getOptionalReport(String qualifier, final Callback<Map<String, Integer>> callback) {
        this.serviceClient.getOptionalReport(qualifier, callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestReportServiceAsyncClient type.
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
         * Builds an instance of AutoRestReportServiceAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestReportServiceAsyncClient.
         */
        public AutoRestReportServiceAsyncClient build() {
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
            AutoRestReportServiceImpl internalClient = new AutoRestReportServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestReportServiceAsyncClient(internalClient);
        }
    }
}
