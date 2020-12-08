package com.azure.androidtest.fixtures.custombaseurl.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.custombaseurl.models.ErrorException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * An instance of this class provides access to all the operations defined in
 * Paths.
 */
public final class PathsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PathsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestParameterizedHostTestClientImpl client;

    /**
     * Initializes an instance of PathsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    PathsImpl(AutoRestParameterizedHostTestClientImpl client) {
        this.client = client;
        this.service = this.client.getServiceClient().getRetrofit().create(PathsService.class);
    }

    /**
     * The interface defining all the services for
     * AutoRestParameterizedHostTestClientPaths to be used by the proxy service
     * to perform REST calls.
     */
    private interface PathsService {
        @GET("/customuri")
        Call<ResponseBody> getEmpty();
    }

    /**
     * Get a 200 to test a valid base uri.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getEmpty(final Callback<Void> callback) {
        Call<ResponseBody> call = service.getEmpty();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final Void decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), Void.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(decodedResult, response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new ErrorException(strContent, response.raw()), response.raw());
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
     * Get a 200 to test a valid base uri.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a 200 to test a valid base uri.
     */
    public Response<Void> getEmptyWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getEmpty());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), Void.class));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new ErrorException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }
}
