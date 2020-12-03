package com.azure.androidtest.fixtures.paging;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataCollection;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseCollection;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.paging.Page;
import com.azure.android.core.util.paging.PagedDataCollection;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.paging.implementation.AutoRestPagingTestServiceImpl;
import com.azure.androidtest.fixtures.paging.implementation.PagingsImpl;
import com.azure.androidtest.fixtures.paging.models.CustomParameterGroup;
import com.azure.androidtest.fixtures.paging.models.OdataProductResult;
import com.azure.androidtest.fixtures.paging.models.PagingGetMultiplePagesLroOptions;
import com.azure.androidtest.fixtures.paging.models.PagingGetMultiplePagesOptions;
import com.azure.androidtest.fixtures.paging.models.PagingGetMultiplePagesWithOffsetOptions;
import com.azure.androidtest.fixtures.paging.models.PagingGetOdataMultiplePagesOptions;
import com.azure.androidtest.fixtures.paging.models.Product;
import com.azure.androidtest.fixtures.paging.models.ProductResult;
import com.azure.androidtest.fixtures.paging.models.ProductResultValue;
import com.azure.androidtest.fixtures.paging.models.ProductResultValueWithXMSClientName;
import okhttp3.Interceptor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Initializes a new instance of the asynchronous AutoRestPagingTestService type.
 */
public final class AutoRestPagingTestServiceAsyncClient {
    private PagingsImpl serviceClient;

    /**
     * Initializes an instance of Pagings client.
     */
    AutoRestPagingTestServiceAsyncClient(PagingsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNoItemNamePagesPages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getNoItemNamePagesPagesAsync(callback);
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNullNextLinkNamePagesPages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getNullNextLinkNamePagesPagesAsync(callback);
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSinglePagesPages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getSinglePagesPagesAsync(callback);
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final) call, will give you an items list of 1.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void firstResponseEmptyPages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.firstResponseEmptyPagesAsync(callback);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param clientRequestId 
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesPages(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getMultiplePagesPagesAsync(clientRequestId, pagingGetMultiplePagesOptions, callback);
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation nextOperationWithQueryParams. Returns a ProductResult.
     * 
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getWithQueryParamsPages(int requiredQueryParameter, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getWithQueryParamsPagesAsync(requiredQueryParameter, callback);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     * 
     * @param clientRequestId 
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getOdataMultiplePagesPages(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getOdataMultiplePagesPagesAsync(clientRequestId, pagingGetOdataMultiplePagesOptions, callback);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesWithOffsetPages(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getMultiplePagesWithOffsetPagesAsync(pagingGetMultiplePagesWithOffsetOptions, clientRequestId, callback);
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a nextLink that has 10 pages.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesRetryFirstPages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getMultiplePagesRetryFirstPagesAsync(callback);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The client should retry and finish all 10 pages eventually.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesRetrySecondPages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getMultiplePagesRetrySecondPagesAsync(callback);
    }

    /**
     * A paging operation that receives a 400 on the first call.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSinglePagesFailurePages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getSinglePagesFailurePagesAsync(callback);
    }

    /**
     * A paging operation that receives a 400 on the second call.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFailurePages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getMultiplePagesFailurePagesAsync(callback);
    }

    /**
     * A paging operation that receives an invalid nextLink.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFailureUriPages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getMultiplePagesFailureUriPagesAsync(callback);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     * 
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFragmentNextLinkPages(String apiVersion, String tenant, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getMultiplePagesFragmentNextLinkPagesAsync(apiVersion, tenant, callback);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     * 
     * @param customParameterGroup Parameter group.
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFragmentWithGroupingNextLinkPages(CustomParameterGroup customParameterGroup, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getMultiplePagesFragmentWithGroupingNextLinkPagesAsync(customParameterGroup, callback);
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getPagingModelWithItemNameWithXMSClientNamePages(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        this.serviceClient.getPagingModelWithItemNameWithXMSClientNamePagesAsync(callback);
    }

    /**
     * A builder for creating a new instance of the AutoRestPagingTestServiceAsyncClient type.
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
         * Builds an instance of AutoRestPagingTestServiceAsyncClient with the provided parameters.
         * 
         * @return an instance of AutoRestPagingTestServiceAsyncClient.
         */
        public AutoRestPagingTestServiceAsyncClient build() {
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
            AutoRestPagingTestServiceImpl internalClient = new AutoRestPagingTestServiceImpl(serviceClientBuilder.build(), host);
            return new AutoRestPagingTestServiceAsyncClient(internalClient.getPagings());
        }
    }
}
