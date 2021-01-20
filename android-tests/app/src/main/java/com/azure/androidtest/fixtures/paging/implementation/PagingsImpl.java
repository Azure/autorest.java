package com.azure.androidtest.fixtures.paging.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataCollection;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseCollection;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.android.core.util.paging.Page;
import com.azure.android.core.util.paging.PagedDataCollection;
import com.azure.android.core.util.paging.PagedDataRetriever;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * An instance of this class provides access to all the operations defined in
 * Pagings.
 */
public final class PagingsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PagingsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestPagingTestServiceImpl client;

    /**
     * Initializes an instance of PagingsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    PagingsImpl(AutoRestPagingTestServiceImpl client) {
        this.client = client;
        this.service = this.client.getServiceClient().getRetrofit().create(PagingsService.class);
    }

    /**
     * The interface defining all the services for
     * AutoRestPagingTestServicePagings to be used by the proxy service to
     * perform REST calls.
     */
    private interface PagingsService {
        @GET("/paging/noitemname")
        Call<ResponseBody> getNoItemNamePages();

        @GET("/paging/nullnextlink")
        Call<ResponseBody> getNullNextLinkNamePages();

        @GET("/paging/single")
        Call<ResponseBody> getSinglePages();

        @GET("/paging/firstResponseEmpty/1")
        Call<ResponseBody> firstResponseEmpty();

        @GET("/paging/multiple")
        Call<ResponseBody> getMultiplePages(@Header("client-request-id") String clientRequestId, @Header("maxresults") Integer maxresults, @Header("timeout") Integer timeout);

        @GET("/paging/multiple/getWithQueryParams")
        Call<ResponseBody> getWithQueryParams(@Query("requiredQueryParameter") int requiredQueryParameter, @Query("queryConstant") boolean queryConstant);

        @GET("/paging/multiple/nextOperationWithQueryParams")
        Call<ResponseBody> nextOperationWithQueryParams(@Query("queryConstant") boolean queryConstant);

        @GET("/paging/multiple/odata")
        Call<ResponseBody> getOdataMultiplePages(@Header("client-request-id") String clientRequestId, @Header("maxresults") Integer maxresults, @Header("timeout") Integer timeout);

        @GET("/paging/multiple/withpath/{offset}")
        Call<ResponseBody> getMultiplePagesWithOffset(@Header("client-request-id") String clientRequestId, @Header("maxresults") Integer maxresults, @Header("timeout") Integer timeout, @Path("offset") int offset);

        @GET("/paging/multiple/retryfirst")
        Call<ResponseBody> getMultiplePagesRetryFirst();

        @GET("/paging/multiple/retrysecond")
        Call<ResponseBody> getMultiplePagesRetrySecond();

        @GET("/paging/single/failure")
        Call<ResponseBody> getSinglePagesFailure();

        @GET("/paging/multiple/failure")
        Call<ResponseBody> getMultiplePagesFailure();

        @GET("/paging/multiple/failureuri")
        Call<ResponseBody> getMultiplePagesFailureUri();

        @GET("/paging/multiple/fragment/{tenant}")
        Call<ResponseBody> getMultiplePagesFragmentNextLink(@Path("tenant") String tenant, @Query("api_version") String apiVersion);

        @GET("/paging/multiple/fragmentwithgrouping/{tenant}")
        Call<ResponseBody> getMultiplePagesFragmentWithGroupingNextLink(@Path("tenant") String tenant, @Query("api_version") String apiVersion);

        @POST("/paging/multiple/lro")
        Call<ResponseBody> getMultiplePagesLRO(@Header("client-request-id") String clientRequestId, @Header("maxresults") Integer maxresults, @Header("timeout") Integer timeout);

        @GET("/paging/multiple/fragment/{tenant}/{nextLink}")
        Call<ResponseBody> nextFragment(@Path("tenant") String tenant, @Path(value = "nextLink", encoded = true) String nextLink, @Query("api_version") String apiVersion);

        @GET("/paging/multiple/fragmentwithgrouping/{tenant}/{nextLink}")
        Call<ResponseBody> nextFragmentWithGrouping(@Path("tenant") String tenant, @Path(value = "nextLink", encoded = true) String nextLink, @Query("api_version") String apiVersion);

        @GET("/paging/itemNameWithXMSClientName")
        Call<ResponseBody> getPagingModelWithItemNameWithXMSClientName();

        @GET("{nextLink}")
        Call<ResponseBody> getNoItemNamePagesNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getSinglePagesNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> firstResponseEmptyNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getMultiplePagesNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getOdataMultiplePagesNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getMultiplePagesWithOffsetNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getMultiplePagesRetryFirstNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getMultiplePagesRetrySecondNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getSinglePagesFailureNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getMultiplePagesFailureNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getMultiplePagesFailureUriNext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getMultiplePagesLRONext(@Path(value = "nextLink", encoded = true) String nextLink);

        @GET("{nextLink}")
        Call<ResponseBody> getPagingModelWithItemNameWithXMSClientNameNext(@Path(value = "nextLink", encoded = true) String nextLink);
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNoItemNamePages(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getNoItemNamePages();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResultValue decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResultValue.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValue(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getNoItemNamePagesWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getNoItemNamePages());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResultValue decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResultValue.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValue(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetNoItemNamePagesWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetNoItemNamePagesWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getNoItemNamePagesWithRestResponse();
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getNoItemNamePagesNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getNoItemNamePagesWithPageResponse() {
        GetNoItemNamePagesWithPageResponseRetriever retriever = new GetNoItemNamePagesWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetNoItemNamePagesWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetNoItemNamePagesWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getNoItemNamePagesWithRestResponse().getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getNoItemNamePagesNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getNoItemNamePagesWithPage() {
        GetNoItemNamePagesWithPageRetriever retriever = new GetNoItemNamePagesWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetNoItemNamePagesPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetNoItemNamePagesPagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getNoItemNamePages(callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getNoItemNamePagesNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that must return result of the default 'value' node.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNoItemNamePagesPagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetNoItemNamePagesPagesAsyncRetriever retriever = new GetNoItemNamePagesPagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNullNextLinkNamePages(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getNullNextLinkNamePages();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), null), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getNullNextLinkNamePagesWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getNullNextLinkNamePages());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), null));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetNullNextLinkNamePagesWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetNullNextLinkNamePagesWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getNullNextLinkNamePagesWithRestResponse();
        }

        public Response<Page<Product>> getPage(String dummy) {
            throw new RuntimeException("No more pages.");
        }
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getNullNextLinkNamePagesWithPageResponse() {
        GetNullNextLinkNamePagesWithPageResponseRetriever retriever = new GetNullNextLinkNamePagesWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetNullNextLinkNamePagesWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetNullNextLinkNamePagesWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getNullNextLinkNamePagesWithRestResponse().getValue();
        }

        public Page<Product> getPage(String dummy) {
            return null;
        }
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getNullNextLinkNamePagesWithPage() {
        GetNullNextLinkNamePagesWithPageRetriever retriever = new GetNullNextLinkNamePagesWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetNullNextLinkNamePagesPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetNullNextLinkNamePagesPagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getNullNextLinkNamePages(callback);
        }

        public void getPage(String dummy, Callback<Page<Product>> callback) {
            callback.onFailure(new RuntimeException("No more pages."), null);
        }
    }

    /**
     * A paging operation that must ignore any kind of nextLink, and stop after page 1.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNullNextLinkNamePagesPagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetNullNextLinkNamePagesPagesAsyncRetriever retriever = new GetNullNextLinkNamePagesPagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSinglePages(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getSinglePages();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getSinglePagesWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getSinglePages());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetSinglePagesWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetSinglePagesWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getSinglePagesWithRestResponse();
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getSinglePagesNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getSinglePagesWithPageResponse() {
        GetSinglePagesWithPageResponseRetriever retriever = new GetSinglePagesWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetSinglePagesWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetSinglePagesWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getSinglePagesWithRestResponse().getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getSinglePagesNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getSinglePagesWithPage() {
        GetSinglePagesWithPageRetriever retriever = new GetSinglePagesWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetSinglePagesPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetSinglePagesPagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getSinglePages(callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getSinglePagesNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that finishes on the first call without a nextlink.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSinglePagesPagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetSinglePagesPagesAsyncRetriever retriever = new GetSinglePagesPagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final) call, will give you an items list of 1.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void firstResponseEmpty(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.firstResponseEmpty();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResultValue decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResultValue.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValue(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final) call, will give you an items list of 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> firstResponseEmptyWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.firstResponseEmpty());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResultValue decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResultValue.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValue(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class FirstResponseEmptyWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public FirstResponseEmptyWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.firstResponseEmptyWithRestResponse();
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.firstResponseEmptyNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final) call, will give you an items list of 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> firstResponseEmptyWithPageResponse() {
        FirstResponseEmptyWithPageResponseRetriever retriever = new FirstResponseEmptyWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class FirstResponseEmptyWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public FirstResponseEmptyWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.firstResponseEmptyWithRestResponse().getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.firstResponseEmptyNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final) call, will give you an items list of 1.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> firstResponseEmptyWithPage() {
        FirstResponseEmptyWithPageRetriever retriever = new FirstResponseEmptyWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class FirstResponseEmptyPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public FirstResponseEmptyPagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.firstResponseEmpty(callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.firstResponseEmptyNext(nextLink, callback);
        }
    }

    /**
     * A paging operation whose first response's items list is empty, but still returns a next link. Second (and final) call, will give you an items list of 1.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void firstResponseEmptyPagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        FirstResponseEmptyPagesAsyncRetriever retriever = new FirstResponseEmptyPagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePages(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions, final Callback<Page<Product>> callback) {
        Integer maxresultsInternal = null;
        if (pagingGetMultiplePagesOptions != null) {
            maxresultsInternal = pagingGetMultiplePagesOptions.getMaxresults();
        }
        Integer maxresults = maxresultsInternal;
        Integer timeoutInternal = null;
        if (pagingGetMultiplePagesOptions != null) {
            timeoutInternal = pagingGetMultiplePagesOptions.getTimeout();
        }
        Integer timeout = timeoutInternal;
        Call<ResponseBody> call = service.getMultiplePages(clientRequestId, maxresults, timeout);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesWithRestResponse(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        Integer maxresultsInternal = null;
        if (pagingGetMultiplePagesOptions != null) {
            maxresultsInternal = pagingGetMultiplePagesOptions.getMaxresults();
        }
        Integer maxresults = maxresultsInternal;
        Integer timeoutInternal = null;
        if (pagingGetMultiplePagesOptions != null) {
            timeoutInternal = pagingGetMultiplePagesOptions.getTimeout();
        }
        Integer timeout = timeoutInternal;
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePages(clientRequestId, maxresults, timeout));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetMultiplePagesWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final String clientRequestId;

        private final PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesWithPageResponseRetriever(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions, PagingsImpl serviceClient) {
            this.clientRequestId = clientRequestId;
            this.pagingGetMultiplePagesOptions = pagingGetMultiplePagesOptions;
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getMultiplePagesWithRestResponse(clientRequestId, pagingGetMultiplePagesOptions);
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getMultiplePagesNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesWithPageResponse(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        GetMultiplePagesWithPageResponseRetriever retriever = new GetMultiplePagesWithPageResponseRetriever(clientRequestId, pagingGetMultiplePagesOptions, this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final String clientRequestId;

        private final PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesWithPageRetriever(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions, PagingsImpl serviceClient) {
            this.clientRequestId = clientRequestId;
            this.pagingGetMultiplePagesOptions = pagingGetMultiplePagesOptions;
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getMultiplePagesWithRestResponse(clientRequestId, pagingGetMultiplePagesOptions).getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getMultiplePagesNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesWithPage(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions) {
        GetMultiplePagesWithPageRetriever retriever = new GetMultiplePagesWithPageRetriever(clientRequestId, pagingGetMultiplePagesOptions, this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final String clientRequestId;

        private final PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesPagesAsyncRetriever(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions, PagingsImpl serviceClient) {
            this.clientRequestId = clientRequestId;
            this.pagingGetMultiplePagesOptions = pagingGetMultiplePagesOptions;
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getMultiplePages(clientRequestId, pagingGetMultiplePagesOptions, callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesOptions Parameter group.
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesPagesAsync(String clientRequestId, PagingGetMultiplePagesOptions pagingGetMultiplePagesOptions, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetMultiplePagesPagesAsyncRetriever retriever = new GetMultiplePagesPagesAsyncRetriever(clientRequestId, pagingGetMultiplePagesOptions, this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that includes a next operation. It has a different query parameter from it's next operation nextOperationWithQueryParams. Returns a ProductResult.
     * 
     * @param requiredQueryParameter A required integer query parameter. Put in value '100' to pass test.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getWithQueryParams(int requiredQueryParameter, final Callback<Page<Product>> callback) {
        final boolean queryConstant = true;
        Call<ResponseBody> call = service.getWithQueryParams(requiredQueryParameter, queryConstant);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
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
    public Response<Page<Product>> getWithQueryParamsWithRestResponse(int requiredQueryParameter) {
        final boolean queryConstant = true;
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getWithQueryParams(requiredQueryParameter, queryConstant));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetWithQueryParamsWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final int requiredQueryParameter;

        private final PagingsImpl serviceClient;

        public GetWithQueryParamsWithPageResponseRetriever(int requiredQueryParameter, PagingsImpl serviceClient) {
            this.requiredQueryParameter = requiredQueryParameter;
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getWithQueryParamsWithRestResponse(requiredQueryParameter);
        }

        public Response<Page<Product>> getPage(String dummy) {
             return serviceClient.nextOperationWithQueryParamsWithRestResponse();
        }
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
        GetWithQueryParamsWithPageResponseRetriever retriever = new GetWithQueryParamsWithPageResponseRetriever(requiredQueryParameter, this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetWithQueryParamsWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final int requiredQueryParameter;

        private final PagingsImpl serviceClient;

        public GetWithQueryParamsWithPageRetriever(int requiredQueryParameter, PagingsImpl serviceClient) {
            this.requiredQueryParameter = requiredQueryParameter;
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getWithQueryParamsWithRestResponse(requiredQueryParameter).getValue();
        }

        public Page<Product> getPage(String dummy) {
             return serviceClient.nextOperationWithQueryParamsWithRestResponse().getValue();
        }
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
        GetWithQueryParamsWithPageRetriever retriever = new GetWithQueryParamsWithPageRetriever(requiredQueryParameter, this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetWithQueryParamsPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final int requiredQueryParameter;

        private final PagingsImpl serviceClient;

        public GetWithQueryParamsPagesAsyncRetriever(int requiredQueryParameter, PagingsImpl serviceClient) {
            this.requiredQueryParameter = requiredQueryParameter;
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getWithQueryParams(requiredQueryParameter, callback);
        }

        public void getPage(String dummy, Callback<Page<Product>> callback) {
            serviceClient.nextOperationWithQueryParams(callback);
        }
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
    public void getWithQueryParamsPagesAsync(int requiredQueryParameter, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetWithQueryParamsPagesAsyncRetriever retriever = new GetWithQueryParamsPagesAsyncRetriever(requiredQueryParameter, this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * Next operation for getWithQueryParams. Pass in next=True to pass test. Returns a ProductResult.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void nextOperationWithQueryParams(final Callback<Page<Product>> callback) {
        final boolean queryConstant = true;
        Call<ResponseBody> call = service.nextOperationWithQueryParams(queryConstant);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), null), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Next operation for getWithQueryParams. Pass in next=True to pass test. Returns a ProductResult.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> nextOperationWithQueryParamsWithRestResponse() {
        final boolean queryConstant = true;
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.nextOperationWithQueryParams(queryConstant));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), null));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getOdataMultiplePages(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions, final Callback<Page<Product>> callback) {
        Integer maxresultsInternal = null;
        if (pagingGetOdataMultiplePagesOptions != null) {
            maxresultsInternal = pagingGetOdataMultiplePagesOptions.getMaxresults();
        }
        Integer maxresults = maxresultsInternal;
        Integer timeoutInternal = null;
        if (pagingGetOdataMultiplePagesOptions != null) {
            timeoutInternal = pagingGetOdataMultiplePagesOptions.getTimeout();
        }
        Integer timeout = timeoutInternal;
        Call<ResponseBody> call = service.getOdataMultiplePages(clientRequestId, maxresults, timeout);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final OdataProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getOdataNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getOdataMultiplePagesWithRestResponse(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        Integer maxresultsInternal = null;
        if (pagingGetOdataMultiplePagesOptions != null) {
            maxresultsInternal = pagingGetOdataMultiplePagesOptions.getMaxresults();
        }
        Integer maxresults = maxresultsInternal;
        Integer timeoutInternal = null;
        if (pagingGetOdataMultiplePagesOptions != null) {
            timeoutInternal = pagingGetOdataMultiplePagesOptions.getTimeout();
        }
        Integer timeout = timeoutInternal;
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getOdataMultiplePages(clientRequestId, maxresults, timeout));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final OdataProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getOdataNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetOdataMultiplePagesWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final String clientRequestId;

        private final PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions;

        private final PagingsImpl serviceClient;

        public GetOdataMultiplePagesWithPageResponseRetriever(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions, PagingsImpl serviceClient) {
            this.clientRequestId = clientRequestId;
            this.pagingGetOdataMultiplePagesOptions = pagingGetOdataMultiplePagesOptions;
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getOdataMultiplePagesWithRestResponse(clientRequestId, pagingGetOdataMultiplePagesOptions);
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getOdataMultiplePagesNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getOdataMultiplePagesWithPageResponse(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        GetOdataMultiplePagesWithPageResponseRetriever retriever = new GetOdataMultiplePagesWithPageResponseRetriever(clientRequestId, pagingGetOdataMultiplePagesOptions, this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetOdataMultiplePagesWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final String clientRequestId;

        private final PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions;

        private final PagingsImpl serviceClient;

        public GetOdataMultiplePagesWithPageRetriever(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions, PagingsImpl serviceClient) {
            this.clientRequestId = clientRequestId;
            this.pagingGetOdataMultiplePagesOptions = pagingGetOdataMultiplePagesOptions;
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getOdataMultiplePagesWithRestResponse(clientRequestId, pagingGetOdataMultiplePagesOptions).getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getOdataMultiplePagesNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getOdataMultiplePagesWithPage(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions) {
        GetOdataMultiplePagesWithPageRetriever retriever = new GetOdataMultiplePagesWithPageRetriever(clientRequestId, pagingGetOdataMultiplePagesOptions, this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetOdataMultiplePagesPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final String clientRequestId;

        private final PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions;

        private final PagingsImpl serviceClient;

        public GetOdataMultiplePagesPagesAsyncRetriever(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions, PagingsImpl serviceClient) {
            this.clientRequestId = clientRequestId;
            this.pagingGetOdataMultiplePagesOptions = pagingGetOdataMultiplePagesOptions;
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getOdataMultiplePages(clientRequestId, pagingGetOdataMultiplePagesOptions, callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getOdataMultiplePagesNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that includes a nextLink in odata format that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetOdataMultiplePagesOptions Parameter group.
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getOdataMultiplePagesPagesAsync(String clientRequestId, PagingGetOdataMultiplePagesOptions pagingGetOdataMultiplePagesOptions, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetOdataMultiplePagesPagesAsyncRetriever retriever = new GetOdataMultiplePagesPagesAsyncRetriever(clientRequestId, pagingGetOdataMultiplePagesOptions, this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesWithOffset(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId, final Callback<Page<Product>> callback) {
        Integer maxresults = pagingGetMultiplePagesWithOffsetOptions.getMaxresults();
        int offset = pagingGetMultiplePagesWithOffsetOptions.getOffset();
        Integer timeout = pagingGetMultiplePagesWithOffsetOptions.getTimeout();
        Call<ResponseBody> call = service.getMultiplePagesWithOffset(clientRequestId, maxresults, timeout, offset);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesWithOffsetWithRestResponse(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        Integer maxresults = pagingGetMultiplePagesWithOffsetOptions.getMaxresults();
        int offset = pagingGetMultiplePagesWithOffsetOptions.getOffset();
        Integer timeout = pagingGetMultiplePagesWithOffsetOptions.getTimeout();
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesWithOffset(clientRequestId, maxresults, timeout, offset));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetMultiplePagesWithOffsetWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions;

        private final String clientRequestId;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesWithOffsetWithPageResponseRetriever(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId, PagingsImpl serviceClient) {
            this.pagingGetMultiplePagesWithOffsetOptions = pagingGetMultiplePagesWithOffsetOptions;
            this.clientRequestId = clientRequestId;
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getMultiplePagesWithOffsetWithRestResponse(pagingGetMultiplePagesWithOffsetOptions, clientRequestId);
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getMultiplePagesWithOffsetNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesWithOffsetWithPageResponse(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        GetMultiplePagesWithOffsetWithPageResponseRetriever retriever = new GetMultiplePagesWithOffsetWithPageResponseRetriever(pagingGetMultiplePagesWithOffsetOptions, clientRequestId, this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesWithOffsetWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions;

        private final String clientRequestId;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesWithOffsetWithPageRetriever(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId, PagingsImpl serviceClient) {
            this.pagingGetMultiplePagesWithOffsetOptions = pagingGetMultiplePagesWithOffsetOptions;
            this.clientRequestId = clientRequestId;
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getMultiplePagesWithOffsetWithRestResponse(pagingGetMultiplePagesWithOffsetOptions, clientRequestId).getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getMultiplePagesWithOffsetNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesWithOffsetWithPage(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId) {
        GetMultiplePagesWithOffsetWithPageRetriever retriever = new GetMultiplePagesWithOffsetWithPageRetriever(pagingGetMultiplePagesWithOffsetOptions, clientRequestId, this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesWithOffsetPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions;

        private final String clientRequestId;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesWithOffsetPagesAsyncRetriever(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId, PagingsImpl serviceClient) {
            this.pagingGetMultiplePagesWithOffsetOptions = pagingGetMultiplePagesWithOffsetOptions;
            this.clientRequestId = clientRequestId;
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesWithOffset(pagingGetMultiplePagesWithOffsetOptions, clientRequestId, callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesWithOffsetNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages.
     * 
     * @param pagingGetMultiplePagesWithOffsetOptions Parameter group.
     * @param clientRequestId The clientRequestId parameter.
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesWithOffsetPagesAsync(PagingGetMultiplePagesWithOffsetOptions pagingGetMultiplePagesWithOffsetOptions, String clientRequestId, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetMultiplePagesWithOffsetPagesAsyncRetriever retriever = new GetMultiplePagesWithOffsetPagesAsyncRetriever(pagingGetMultiplePagesWithOffsetOptions, clientRequestId, this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a nextLink that has 10 pages.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesRetryFirst(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesRetryFirst();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a nextLink that has 10 pages.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesRetryFirstWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesRetryFirst());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetMultiplePagesRetryFirstWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesRetryFirstWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getMultiplePagesRetryFirstWithRestResponse();
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getMultiplePagesRetryFirstNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a nextLink that has 10 pages.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesRetryFirstWithPageResponse() {
        GetMultiplePagesRetryFirstWithPageResponseRetriever retriever = new GetMultiplePagesRetryFirstWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesRetryFirstWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesRetryFirstWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getMultiplePagesRetryFirstWithRestResponse().getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getMultiplePagesRetryFirstNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a nextLink that has 10 pages.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesRetryFirstWithPage() {
        GetMultiplePagesRetryFirstWithPageRetriever retriever = new GetMultiplePagesRetryFirstWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesRetryFirstPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesRetryFirstPagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesRetryFirst(callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesRetryFirstNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that fails on the first call with 500 and then retries and then get a response including a nextLink that has 10 pages.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesRetryFirstPagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetMultiplePagesRetryFirstPagesAsyncRetriever retriever = new GetMultiplePagesRetryFirstPagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The client should retry and finish all 10 pages eventually.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesRetrySecond(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesRetrySecond();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The client should retry and finish all 10 pages eventually.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesRetrySecondWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesRetrySecond());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetMultiplePagesRetrySecondWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesRetrySecondWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getMultiplePagesRetrySecondWithRestResponse();
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getMultiplePagesRetrySecondNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The client should retry and finish all 10 pages eventually.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesRetrySecondWithPageResponse() {
        GetMultiplePagesRetrySecondWithPageResponseRetriever retriever = new GetMultiplePagesRetrySecondWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesRetrySecondWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesRetrySecondWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getMultiplePagesRetrySecondWithRestResponse().getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getMultiplePagesRetrySecondNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The client should retry and finish all 10 pages eventually.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesRetrySecondWithPage() {
        GetMultiplePagesRetrySecondWithPageRetriever retriever = new GetMultiplePagesRetrySecondWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesRetrySecondPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesRetrySecondPagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesRetrySecond(callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesRetrySecondNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that includes a nextLink that has 10 pages, of which the 2nd call fails first with 500. The client should retry and finish all 10 pages eventually.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesRetrySecondPagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetMultiplePagesRetrySecondPagesAsyncRetriever retriever = new GetMultiplePagesRetrySecondPagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that receives a 400 on the first call.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSinglePagesFailure(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getSinglePagesFailure();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that receives a 400 on the first call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getSinglePagesFailureWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getSinglePagesFailure());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetSinglePagesFailureWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetSinglePagesFailureWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getSinglePagesFailureWithRestResponse();
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getSinglePagesFailureNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that receives a 400 on the first call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getSinglePagesFailureWithPageResponse() {
        GetSinglePagesFailureWithPageResponseRetriever retriever = new GetSinglePagesFailureWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetSinglePagesFailureWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetSinglePagesFailureWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getSinglePagesFailureWithRestResponse().getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getSinglePagesFailureNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that receives a 400 on the first call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getSinglePagesFailureWithPage() {
        GetSinglePagesFailureWithPageRetriever retriever = new GetSinglePagesFailureWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetSinglePagesFailurePagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetSinglePagesFailurePagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getSinglePagesFailure(callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getSinglePagesFailureNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that receives a 400 on the first call.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSinglePagesFailurePagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetSinglePagesFailurePagesAsyncRetriever retriever = new GetSinglePagesFailurePagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that receives a 400 on the second call.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFailure(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesFailure();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that receives a 400 on the second call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesFailureWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesFailure());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetMultiplePagesFailureWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesFailureWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getMultiplePagesFailureWithRestResponse();
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getMultiplePagesFailureNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that receives a 400 on the second call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesFailureWithPageResponse() {
        GetMultiplePagesFailureWithPageResponseRetriever retriever = new GetMultiplePagesFailureWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesFailureWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesFailureWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getMultiplePagesFailureWithRestResponse().getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getMultiplePagesFailureNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that receives a 400 on the second call.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesFailureWithPage() {
        GetMultiplePagesFailureWithPageRetriever retriever = new GetMultiplePagesFailureWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesFailurePagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesFailurePagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesFailure(callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesFailureNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that receives a 400 on the second call.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFailurePagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetMultiplePagesFailurePagesAsyncRetriever retriever = new GetMultiplePagesFailurePagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that receives an invalid nextLink.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFailureUri(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesFailureUri();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that receives an invalid nextLink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesFailureUriWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesFailureUri());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetMultiplePagesFailureUriWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesFailureUriWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getMultiplePagesFailureUriWithRestResponse();
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getMultiplePagesFailureUriNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that receives an invalid nextLink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getMultiplePagesFailureUriWithPageResponse() {
        GetMultiplePagesFailureUriWithPageResponseRetriever retriever = new GetMultiplePagesFailureUriWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesFailureUriWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesFailureUriWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getMultiplePagesFailureUriWithRestResponse().getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getMultiplePagesFailureUriNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that receives an invalid nextLink.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getMultiplePagesFailureUriWithPage() {
        GetMultiplePagesFailureUriWithPageRetriever retriever = new GetMultiplePagesFailureUriWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesFailureUriPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetMultiplePagesFailureUriPagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesFailureUri(callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesFailureUriNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that receives an invalid nextLink.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFailureUriPagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetMultiplePagesFailureUriPagesAsyncRetriever retriever = new GetMultiplePagesFailureUriPagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     * 
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFragmentNextLink(String apiVersion, String tenant, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesFragmentNextLink(tenant, apiVersion);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final OdataProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getOdataNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
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
    public Response<Page<Product>> getMultiplePagesFragmentNextLinkWithRestResponse(String apiVersion, String tenant) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesFragmentNextLink(tenant, apiVersion));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final OdataProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getOdataNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetMultiplePagesFragmentNextLinkWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final String apiVersion;

        private final String tenant;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesFragmentNextLinkWithPageResponseRetriever(String apiVersion, String tenant, PagingsImpl serviceClient) {
            this.apiVersion = apiVersion;
            this.tenant = tenant;
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getMultiplePagesFragmentNextLinkWithRestResponse(apiVersion, tenant);
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.nextFragmentWithRestResponse(apiVersion, tenant, nextLink);
        }
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
        GetMultiplePagesFragmentNextLinkWithPageResponseRetriever retriever = new GetMultiplePagesFragmentNextLinkWithPageResponseRetriever(apiVersion, tenant, this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesFragmentNextLinkWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final String apiVersion;

        private final String tenant;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesFragmentNextLinkWithPageRetriever(String apiVersion, String tenant, PagingsImpl serviceClient) {
            this.apiVersion = apiVersion;
            this.tenant = tenant;
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getMultiplePagesFragmentNextLinkWithRestResponse(apiVersion, tenant).getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.nextFragmentWithRestResponse(apiVersion, tenant, nextLink).getValue();
        }
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
        GetMultiplePagesFragmentNextLinkWithPageRetriever retriever = new GetMultiplePagesFragmentNextLinkWithPageRetriever(apiVersion, tenant, this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesFragmentNextLinkPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final String apiVersion;

        private final String tenant;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesFragmentNextLinkPagesAsyncRetriever(String apiVersion, String tenant, PagingsImpl serviceClient) {
            this.apiVersion = apiVersion;
            this.tenant = tenant;
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesFragmentNextLink(apiVersion, tenant, callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.nextFragment(apiVersion, tenant, nextLink, callback);
        }
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
    public void getMultiplePagesFragmentNextLinkPagesAsync(String apiVersion, String tenant, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetMultiplePagesFragmentNextLinkPagesAsyncRetriever retriever = new GetMultiplePagesFragmentNextLinkPagesAsyncRetriever(apiVersion, tenant, this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment with parameters grouped.
     * 
     * @param customParameterGroup Parameter group.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFragmentWithGroupingNextLink(CustomParameterGroup customParameterGroup, final Callback<Page<Product>> callback) {
        String apiVersion = customParameterGroup.getApiVersion();
        String tenant = customParameterGroup.getTenant();
        Call<ResponseBody> call = service.getMultiplePagesFragmentWithGroupingNextLink(tenant, apiVersion);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final OdataProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getOdataNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
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
    public Response<Page<Product>> getMultiplePagesFragmentWithGroupingNextLinkWithRestResponse(CustomParameterGroup customParameterGroup) {
        String apiVersion = customParameterGroup.getApiVersion();
        String tenant = customParameterGroup.getTenant();
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesFragmentWithGroupingNextLink(tenant, apiVersion));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final OdataProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getOdataNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetMultiplePagesFragmentWithGroupingNextLinkWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final CustomParameterGroup customParameterGroup;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesFragmentWithGroupingNextLinkWithPageResponseRetriever(CustomParameterGroup customParameterGroup, PagingsImpl serviceClient) {
            this.customParameterGroup = customParameterGroup;
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getMultiplePagesFragmentWithGroupingNextLinkWithRestResponse(customParameterGroup);
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.nextFragmentWithGroupingWithRestResponse(nextLink, customParameterGroup);
        }
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
        GetMultiplePagesFragmentWithGroupingNextLinkWithPageResponseRetriever retriever = new GetMultiplePagesFragmentWithGroupingNextLinkWithPageResponseRetriever(customParameterGroup, this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesFragmentWithGroupingNextLinkWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final CustomParameterGroup customParameterGroup;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesFragmentWithGroupingNextLinkWithPageRetriever(CustomParameterGroup customParameterGroup, PagingsImpl serviceClient) {
            this.customParameterGroup = customParameterGroup;
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getMultiplePagesFragmentWithGroupingNextLinkWithRestResponse(customParameterGroup).getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.nextFragmentWithGroupingWithRestResponse(nextLink, customParameterGroup).getValue();
        }
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
        GetMultiplePagesFragmentWithGroupingNextLinkWithPageRetriever retriever = new GetMultiplePagesFragmentWithGroupingNextLinkWithPageRetriever(customParameterGroup, this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetMultiplePagesFragmentWithGroupingNextLinkPagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final CustomParameterGroup customParameterGroup;

        private final PagingsImpl serviceClient;

        public GetMultiplePagesFragmentWithGroupingNextLinkPagesAsyncRetriever(CustomParameterGroup customParameterGroup, PagingsImpl serviceClient) {
            this.customParameterGroup = customParameterGroup;
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getMultiplePagesFragmentWithGroupingNextLink(customParameterGroup, callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.nextFragmentWithGrouping(nextLink, customParameterGroup, callback);
        }
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
    public void getMultiplePagesFragmentWithGroupingNextLinkPagesAsync(CustomParameterGroup customParameterGroup, final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetMultiplePagesFragmentWithGroupingNextLinkPagesAsyncRetriever retriever = new GetMultiplePagesFragmentWithGroupingNextLinkPagesAsyncRetriever(customParameterGroup, this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * A long-running paging operation that includes a nextLink that has 10 pages.
     * 
     * @param clientRequestId The clientRequestId parameter.
     * @param pagingGetMultiplePagesLroOptions Parameter group.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesLRO(String clientRequestId, PagingGetMultiplePagesLroOptions pagingGetMultiplePagesLroOptions, final Callback<Page<Product>> callback) {
        Integer maxresultsInternal = null;
        if (pagingGetMultiplePagesLroOptions != null) {
            maxresultsInternal = pagingGetMultiplePagesLroOptions.getMaxresults();
        }
        Integer maxresults = maxresultsInternal;
        Integer timeoutInternal = null;
        if (pagingGetMultiplePagesLroOptions != null) {
            timeoutInternal = pagingGetMultiplePagesLroOptions.getTimeout();
        }
        Integer timeout = timeoutInternal;
        Call<ResponseBody> call = service.getMultiplePagesLRO(clientRequestId, maxresults, timeout);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 202) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     * 
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @param nextLink Next link for list operation.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void nextFragment(String apiVersion, String tenant, String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.nextFragment(tenant, nextLink, apiVersion);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final OdataProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getOdataNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     * 
     * @param apiVersion Sets the api version to use.
     * @param tenant Sets the tenant to use.
     * @param nextLink Next link for list operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> nextFragmentWithRestResponse(String apiVersion, String tenant, String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.nextFragment(tenant, nextLink, apiVersion));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final OdataProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getOdataNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     * 
     * @param nextLink Next link for list operation.
     * @param customParameterGroup Parameter group.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void nextFragmentWithGrouping(String nextLink, CustomParameterGroup customParameterGroup, final Callback<Page<Product>> callback) {
        String apiVersion = customParameterGroup.getApiVersion();
        String tenant = customParameterGroup.getTenant();
        Call<ResponseBody> call = service.nextFragmentWithGrouping(tenant, nextLink, apiVersion);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final OdataProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getOdataNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that doesn't return a full URL, just a fragment.
     * 
     * @param nextLink Next link for list operation.
     * @param customParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> nextFragmentWithGroupingWithRestResponse(String nextLink, CustomParameterGroup customParameterGroup) {
        String apiVersion = customParameterGroup.getApiVersion();
        String tenant = customParameterGroup.getTenant();
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.nextFragmentWithGrouping(tenant, nextLink, apiVersion));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final OdataProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getOdataNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getPagingModelWithItemNameWithXMSClientName(final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getPagingModelWithItemNameWithXMSClientName();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResultValueWithXMSClientName decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResultValueWithXMSClientName.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(response.raw().request().url().toString(), decodedResult.getIndexes(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getPagingModelWithItemNameWithXMSClientNameWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getPagingModelWithItemNameWithXMSClientName());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResultValueWithXMSClientName decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResultValueWithXMSClientName.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(response.raw().request().url().toString(), decodedResult.getIndexes(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    private static final class GetPagingModelWithItemNameWithXMSClientNameWithPageResponseRetriever extends PagedDataResponseRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetPagingModelWithItemNameWithXMSClientNameWithPageResponseRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Response<Page<Product>> getFirstPage() {
             return serviceClient.getPagingModelWithItemNameWithXMSClientNameWithRestResponse();
        }

        public Response<Page<Product>> getPage(String nextLink) {
             return serviceClient.getPagingModelWithItemNameWithXMSClientNameNextWithRestResponse(nextLink);
        }
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataResponseCollection<Product, Page<Product>> getPagingModelWithItemNameWithXMSClientNameWithPageResponse() {
        GetPagingModelWithItemNameWithXMSClientNameWithPageResponseRetriever retriever = new GetPagingModelWithItemNameWithXMSClientNameWithPageResponseRetriever(this);
        return new PagedDataResponseCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetPagingModelWithItemNameWithXMSClientNameWithPageRetriever extends PagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetPagingModelWithItemNameWithXMSClientNameWithPageRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public Page<Product> getFirstPage() {
             return serviceClient.getPagingModelWithItemNameWithXMSClientNameWithRestResponse().getValue();
        }

        public Page<Product> getPage(String nextLink) {
             return serviceClient.getPagingModelWithItemNameWithXMSClientNameNextWithRestResponse(nextLink).getValue();
        }
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public PagedDataCollection<Product, Page<Product>> getPagingModelWithItemNameWithXMSClientNameWithPage() {
        GetPagingModelWithItemNameWithXMSClientNameWithPageRetriever retriever = new GetPagingModelWithItemNameWithXMSClientNameWithPageRetriever(this);
        return new PagedDataCollection<Product, Page<Product>>(retriever);
    }

    private static final class GetPagingModelWithItemNameWithXMSClientNamePagesAsyncRetriever extends AsyncPagedDataRetriever<Product, Page<Product>> {
        private final PagingsImpl serviceClient;

        public GetPagingModelWithItemNameWithXMSClientNamePagesAsyncRetriever(PagingsImpl serviceClient) {
            this.serviceClient = serviceClient;
        }

        public void getFirstPage(Callback<Page<Product>> callback) {
            serviceClient.getPagingModelWithItemNameWithXMSClientName(callback);
        }

        public void getPage(String nextLink, Callback<Page<Product>> callback) {
            serviceClient.getPagingModelWithItemNameWithXMSClientNameNext(nextLink, callback);
        }
    }

    /**
     * A paging operation that returns a paging model whose item name is is overriden by x-ms-client-name 'indexes'.
     * 
     * @param callback the Callback that receives the response collection.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getPagingModelWithItemNameWithXMSClientNamePagesAsync(final Callback<AsyncPagedDataCollection<Product, Page<Product>>> callback) {
        GetPagingModelWithItemNameWithXMSClientNamePagesAsyncRetriever retriever = new GetPagingModelWithItemNameWithXMSClientNamePagesAsyncRetriever(this);
        callback.onSuccess(new AsyncPagedDataCollection<Product, Page<Product>>(retriever), null);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNoItemNamePagesNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getNoItemNamePagesNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResultValue decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResultValue.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValue(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getNoItemNamePagesNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getNoItemNamePagesNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResultValue decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResultValue.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValue(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSinglePagesNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getSinglePagesNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getSinglePagesNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getSinglePagesNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void firstResponseEmptyNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.firstResponseEmptyNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResultValue decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResultValue.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValue(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> firstResponseEmptyNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.firstResponseEmptyNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResultValue decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResultValue.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValue(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getOdataMultiplePagesNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getOdataMultiplePagesNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final OdataProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getOdataNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getOdataMultiplePagesNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getOdataMultiplePagesNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final OdataProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), OdataProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getOdataNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesWithOffsetNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesWithOffsetNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesWithOffsetNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesWithOffsetNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesRetryFirstNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesRetryFirstNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesRetryFirstNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesRetryFirstNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesRetrySecondNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesRetrySecondNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesRetrySecondNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesRetrySecondNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getSinglePagesFailureNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getSinglePagesFailureNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getSinglePagesFailureNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getSinglePagesFailureNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFailureNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesFailureNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesFailureNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesFailureNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesFailureUriNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesFailureUriNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getMultiplePagesFailureUriNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getMultiplePagesFailureUriNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResult decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getMultiplePagesLRONext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getMultiplePagesLRONext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 202) {
                        final ProductResult decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResult.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getValues(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getPagingModelWithItemNameWithXMSClientNameNext(String nextLink, final Callback<Page<Product>> callback) {
        Call<ResponseBody> call = service.getPagingModelWithItemNameWithXMSClientNameNext(nextLink);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ProductResultValueWithXMSClientName decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ProductResultValueWithXMSClientName.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(new Page<Product>(nextLink, decodedResult.getIndexes(), decodedResult.getNextLink()), response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = client.readAsString(response.errorBody());
                    callback.onFailure(new HttpResponseException(strContent, response.raw()), response.raw());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t, null);
            }
        };
        call.enqueue(retrofitCallback);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Page<Product>> getPagingModelWithItemNameWithXMSClientNameNextWithRestResponse(String nextLink) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getPagingModelWithItemNameWithXMSClientNameNext(nextLink));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                final ProductResultValueWithXMSClientName decodedResult;
                try {
                    decodedResult = this.client.deserializeContent(response.headers(), response.body(), ProductResultValueWithXMSClientName.class);
                } catch(Exception ex) {
                    final String strContent = this.client.readAsString(response.body());
                    throw new HttpResponseException(strContent, response.raw());
                }
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        new Page<Product>(nextLink, decodedResult.getIndexes(), decodedResult.getNextLink()));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new HttpResponseException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }
}
