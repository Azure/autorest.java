package com.azure.androidtest.fixtures.bodycomplex.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.androidtest.fixtures.bodycomplex.models.MyBaseType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * An instance of this class provides access to all the operations defined in
 * Flattencomplexs.
 */
public final class FlattencomplexsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final FlattencomplexsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestComplexTestServiceImpl client;

    /**
     * Initializes an instance of FlattencomplexsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    FlattencomplexsImpl(AutoRestComplexTestServiceImpl client) {
        this.client = client;
        this.service = this.client.getServiceClient().getRetrofit().create(FlattencomplexsService.class);
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServiceFlattencomplexs to be used by the proxy
     * service to perform REST calls.
     */
    private interface FlattencomplexsService {
        @GET("/complex/flatten/valid")
        Call<ResponseBody> getValid();
    }

    /**
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getValid(final Callback<MyBaseType> callback) {
        Call<ResponseBody> call = service.getValid();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final MyBaseType decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), MyBaseType.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(decodedResult, response.raw());
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
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<MyBaseType> getValidWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getValid());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), MyBaseType.class));
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
