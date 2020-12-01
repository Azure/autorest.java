package com.azure.androidtest.fixtures.url;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
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
 * Initializes a new instance of the synchronous AutoRestUrlTestService type.
 */
public final class PathItemsClient {
    private PathItemsImpl serviceClient;

    /**
     * Initializes an instance of PathItems client.
     */
    PathItemsClient(PathItemsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * send globalStringPath='globalStringPath',
     * pathItemStringPath='pathItemStringPath', localStringPath='localStringPath',
     * globalStringQuery='globalStringQuery',
     * pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath  A string value 'pathItemStringPath' that appears
     *                            in the path.
     * @param localStringPath     should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears
     *                            as a query parameter.
     * @param localStringQuery    should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> getAllWithValuesWithRestResponse(String pathItemStringPath, String localStringPath,
            String pathItemStringQuery, String localStringQuery) {
        return this.serviceClient.getAllWithValuesWithRestResponse(pathItemStringPath, localStringPath,
                pathItemStringQuery, localStringQuery);
    }

    /**
     * send globalStringPath='globalStringPath',
     * pathItemStringPath='pathItemStringPath', localStringPath='localStringPath',
     * globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath  A string value 'pathItemStringPath' that appears
     *                            in the path.
     * @param localStringPath     should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears
     *                            as a query parameter.
     * @param localStringQuery    should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> getGlobalQueryNullWithRestResponse(String pathItemStringPath, String localStringPath,
            String pathItemStringQuery, String localStringQuery) {
        return this.serviceClient.getGlobalQueryNullWithRestResponse(pathItemStringPath, localStringPath,
                pathItemStringQuery, localStringQuery);
    }

    /**
     * send globalStringPath=globalStringPath,
     * pathItemStringPath='pathItemStringPath', localStringPath='localStringPath',
     * globalStringQuery=null, pathItemStringQuery='pathItemStringQuery',
     * localStringQuery=null.
     * 
     * @param pathItemStringPath  A string value 'pathItemStringPath' that appears
     *                            in the path.
     * @param localStringPath     should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears
     *                            as a query parameter.
     * @param localStringQuery    should contain null value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> getGlobalAndLocalQueryNullWithRestResponse(String pathItemStringPath, String localStringPath,
            String pathItemStringQuery, String localStringQuery) {
        return this.serviceClient.getGlobalAndLocalQueryNullWithRestResponse(pathItemStringPath, localStringPath,
                pathItemStringQuery, localStringQuery);
    }

    /**
     * send globalStringPath='globalStringPath',
     * pathItemStringPath='pathItemStringPath', localStringPath='localStringPath',
     * globalStringQuery='globalStringQuery', pathItemStringQuery=null,
     * localStringQuery=null.
     * 
     * @param pathItemStringPath  A string value 'pathItemStringPath' that appears
     *                            in the path.
     * @param localStringPath     should contain value 'localStringPath'.
     * @param pathItemStringQuery should contain value null.
     * @param localStringQuery    should contain value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> getLocalPathItemQueryNullWithRestResponse(String pathItemStringPath, String localStringPath,
            String pathItemStringQuery, String localStringQuery) {
        return this.serviceClient.getLocalPathItemQueryNullWithRestResponse(pathItemStringPath, localStringPath,
                pathItemStringQuery, localStringQuery);
    }

    /**
     * A builder for creating a new instance of the PathItemsClient type.
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
         * Builds an instance of PathItemsClient with the provided parameters.
         * 
         * @return an instance of PathItemsClient.
         */
        public PathItemsClient build() {
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
            AutoRestUrlTestServiceImpl internalClient = new AutoRestUrlTestServiceImpl(serviceClientBuilder.build(),
                    globalStringPath, globalStringQuery, host);
            return new PathItemsClient(internalClient.getPathItems());
        }
    }
}
