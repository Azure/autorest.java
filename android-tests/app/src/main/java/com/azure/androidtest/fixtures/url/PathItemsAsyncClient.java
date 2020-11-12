package com.azure.androidtest.fixtures.url;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.url.implementation.AutoRestUrlTestServiceImpl;
import com.azure.androidtest.fixtures.url.implementation.PathItemsImpl;
import com.azure.androidtest.fixtures.url.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Initializes a new instance of the asynchronous AutoRestUrlTestService type.
 */
public final class PathItemsAsyncClient {
    private PathItemsImpl serviceClient;

    /**
     * Initializes an instance of PathItems client.
     */
    PathItemsAsyncClient(PathItemsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getAllWithValues(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery, final Callback<Void> callback) {
        this.serviceClient.getAllWithValues(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery, callback);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getGlobalQueryNull(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery, final Callback<Void> callback) {
        this.serviceClient.getGlobalQueryNull(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery, callback);
    }

    /**
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery=null.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain null value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getGlobalAndLocalQueryNull(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery, final Callback<Void> callback) {
        this.serviceClient.getGlobalAndLocalQueryNull(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery, callback);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null, localStringQuery=null.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery should contain value null.
     * @param localStringQuery should contain value null.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLocalPathItemQueryNull(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery, final Callback<Void> callback) {
        this.serviceClient.getLocalPathItemQueryNull(pathItemStringPath, localStringPath, pathItemStringQuery, localStringQuery, callback);
    }

    /**
     * A builder for creating a new instance of the PathItemsAsyncClient type.
     */
    public static final class Builder {
        /*
         * A string value 'globalItemStringPath' that appears in the path
         */
        private String globalStringPath;

        /**
         * Sets A string value 'globalItemStringPath' that appears in the path.
         * 
         * @param globalStringPath the globalStringPath value.
         * @return the Builder.
         */
        public Builder globalStringPath(String globalStringPath) {
            this.globalStringPath = globalStringPath;
            return this;
        }

        /*
         * should contain value null
         */
        private String globalStringQuery;

        /**
         * Sets should contain value null.
         * 
         * @param globalStringQuery the globalStringQuery value.
         * @return the Builder.
         */
        public Builder globalStringQuery(String globalStringQuery) {
            this.globalStringQuery = globalStringQuery;
            return this;
        }

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
         * Builds an instance of PathItemsAsyncClient with the provided parameters.
         * 
         * @return an instance of PathItemsAsyncClient.
         */
        public PathItemsAsyncClient build() {
            if (host == null) {
                this.host = "http://localhost:3000";
            }
            if (serviceClientBuilder == null) {
                if (host == null) {
                    throw new IllegalArgumentException("Missing required parameters 'host'.");
                }
                this.serviceClientBuilder = new ServiceClient.Builder();
            }
            if (host != null) {
                final String retrofitBaseUrl = this.host.replace("{host}", host);
                serviceClientBuilder.setBaseUrl(retrofitBaseUrl);
            }
            if (credentialInterceptor != null) {
                serviceClientBuilder.setCredentialsInterceptor(credentialInterceptor);
            }
            AutoRestUrlTestServiceImpl internalClient = new AutoRestUrlTestServiceImpl(serviceClientBuilder.build(), globalStringPath, globalStringQuery, host);
            return new PathItemsAsyncClient(internalClient.getPathItems());
        }
    }
}
