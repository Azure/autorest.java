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
 * Initializes a new instance of the synchronous AutoRestPagingTestService type.
 */
public final class AutoRestPagingTestServiceClient {
    private PagingsImpl serviceClient;

    /**
     * Initializes an instance of Pagings client.
     */
    AutoRestPagingTestServiceClient(PagingsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getNoItemNamePagesWithPageResponse() {
        return this.serviceClient.getNoItemNamePagesWithPageResponse();
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getNoItemNamePagesWithPage() {
        return this.serviceClient.getNoItemNamePagesWithPage();
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getNullNextLinkNamePagesWithPageResponse() {
        return this.serviceClient.getNullNextLinkNamePagesWithPageResponse();
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getNullNextLinkNamePagesWithPage() {
        return this.serviceClient.getNullNextLinkNamePagesWithPage();
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getSinglePagesWithPageResponse() {
        return this.serviceClient.getSinglePagesWithPageResponse();
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getSinglePagesWithPage() {
        return this.serviceClient.getSinglePagesWithPage();
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final) call, will give you an items list of 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> firstResponseEmptyWithPageResponse() {
        return this.serviceClient.firstResponseEmptyWithPageResponse();
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final) call, will give you an items list of 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> firstResponseEmptyWithPage() {
        return this.serviceClient.firstResponseEmptyWithPage();
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param clientRequestId 
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesWithPageResponse(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        return this.serviceClient.getMultiplePagesWithPageResponse(clientRequestId, pagingGetMultiplePagesOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param clientRequestId 
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesWithPage(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        return this.serviceClient.getMultiplePagesWithPage(clientRequestId, pagingGetMultiplePagesOptions);
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation nextOperationWithQueryParams. Returns a ProductResult.
     * 
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getWithQueryParamsWithPageResponse(int requiredQueryParameter) {
        return this.serviceClient.getWithQueryParamsWithPageResponse(requiredQueryParameter);
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation nextOperationWithQueryParams. Returns a ProductResult.
     * 
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getWithQueryParamsWithPage(int requiredQueryParameter) {
        return this.serviceClient.getWithQueryParamsWithPage(requiredQueryParameter);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     * 
     * @param clientRequestId 
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getOdataMultiplePagesWithPageResponse(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        return this.serviceClient.getOdataMultiplePagesWithPageResponse(clientRequestId, pagingGetOdataMultiplePagesOptions);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     * 
     * @param clientRequestId 
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getOdataMultiplePagesWithPage(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        return this.serviceClient.getOdataMultiplePagesWithPage(clientRequestId, pagingGetOdataMultiplePagesOptions);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesWithOffsetWithPageResponse(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        return this.serviceClient.getMultiplePagesWithOffsetWithPageResponse(pagingGetMultiplePagesWithOffsetOptions, clientRequestId);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesWithOffsetWithPage(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        return this.serviceClient.getMultiplePagesWithOffsetWithPage(pagingGetMultiplePagesWithOffsetOptions, clientRequestId);
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a nextLink that has 10 pages.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesRetryFirstWithPageResponse() {
        return this.serviceClient.getMultiplePagesRetryFirstWithPageResponse();
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a nextLink that has 10 pages.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesRetryFirstWithPage() {
        return this.serviceClient.getMultiplePagesRetryFirstWithPage();
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The client should retry and finish all 10 pages eventually.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesRetrySecondWithPageResponse() {
        return this.serviceClient.getMultiplePagesRetrySecondWithPageResponse();
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The client should retry and finish all 10 pages eventually.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesRetrySecondWithPage() {
        return this.serviceClient.getMultiplePagesRetrySecondWithPage();
    }

    /**
     * A paging operation that receives a 400 on the first call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getSinglePagesFailureWithPageResponse() {
        return this.serviceClient.getSinglePagesFailureWithPageResponse();
    }

    /**
     * A paging operation that receives a 400 on the first call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getSinglePagesFailureWithPage() {
        return this.serviceClient.getSinglePagesFailureWithPage();
    }

    /**
     * A paging operation that receives a 400 on the second call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesFailureWithPageResponse() {
        return this.serviceClient.getMultiplePagesFailureWithPageResponse();
    }

    /**
     * A paging operation that receives a 400 on the second call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesFailureWithPage() {
        return this.serviceClient.getMultiplePagesFailureWithPage();
    }

    /**
     * A paging operation that receives an invalid nextLink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesFailureUriWithPageResponse() {
        return this.serviceClient.getMultiplePagesFailureUriWithPageResponse();
    }

    /**
     * A paging operation that receives an invalid nextLink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesFailureUriWithPage() {
        return this.serviceClient.getMultiplePagesFailureUriWithPage();
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     * 
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesFragmentNextLinkWithPageResponse(String apiVersion, String tenant) {
        return this.serviceClient.getMultiplePagesFragmentNextLinkWithPageResponse(apiVersion, tenant);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     * 
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesFragmentNextLinkWithPage(String apiVersion, String tenant) {
        return this.serviceClient.getMultiplePagesFragmentNextLinkWithPage(apiVersion, tenant);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     * 
     * @param customParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesFragmentWithGroupingNextLinkWithPageResponse(CustomParameterGroup customParameterGroup) {
        return this.serviceClient.getMultiplePagesFragmentWithGroupingNextLinkWithPageResponse(customParameterGroup);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     * 
     * @param customParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesFragmentWithGroupingNextLinkWithPage(CustomParameterGroup customParameterGroup) {
        return this.serviceClient.getMultiplePagesFragmentWithGroupingNextLinkWithPage(customParameterGroup);
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getPagingModelWithItemNameWithXMSClientNameWithPageResponse() {
        return this.serviceClient.getPagingModelWithItemNameWithXMSClientNameWithPageResponse();
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getPagingModelWithItemNameWithXMSClientNameWithPage() {
        return this.serviceClient.getPagingModelWithItemNameWithXMSClientNameWithPage();
    }

    /**
     * A builder for creating a new instance of the AutoRestPagingTestServiceClient type.
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
         * Builds an instance of AutoRestPagingTestServiceClient with the provided parameters.
         * 
         * @return an instance of AutoRestPagingTestServiceClient.
         */
        public AutoRestPagingTestServiceClient build() {
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
            return new AutoRestPagingTestServiceClient(internalClient.getPagings());
        }
    }
}
