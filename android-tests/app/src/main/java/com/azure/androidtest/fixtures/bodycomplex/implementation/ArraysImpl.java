package com.azure.androidtest.fixtures.bodycomplex.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.androidtest.fixtures.bodycomplex.models.ArrayWrapper;
import com.azure.androidtest.fixtures.bodycomplex.models.ErrorException;
import java.util.List;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * An instance of this class provides access to all the operations defined in
 * Arrays.
 */
public final class ArraysImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final ArraysService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestComplexTestServiceImpl client;

    /**
     * Initializes an instance of ArraysImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    ArraysImpl(AutoRestComplexTestServiceImpl client) {
        this.client = client;
        this.service = this.client.getServiceClient().getRetrofit().create(ArraysService.class);
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServiceArrays to be used by the proxy service to
     * perform REST calls.
     */
    private interface ArraysService {
        @GET("/complex/array/valid")
        Call<ResponseBody> getValid();

        @PUT("/complex/array/valid")
        Call<ResponseBody> putValid(@Body RequestBody complexBody);

        @GET("/complex/array/empty")
        Call<ResponseBody> getEmpty();

        @PUT("/complex/array/empty")
        Call<ResponseBody> putEmpty(@Body RequestBody complexBody);

        @GET("/complex/array/notprovided")
        Call<ResponseBody> getNotProvided();
    }

    /**
     * Get complex types with array property.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getValid(final Callback<ArrayWrapper> callback) {
        Call<ResponseBody> call = service.getValid();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ArrayWrapper decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ArrayWrapper.class);
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
     * Get complex types with array property.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property.
     */
    public Response<ArrayWrapper> getValidWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getValid());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), ArrayWrapper.class));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new ErrorException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Put complex types with array property.
     * 
     * @param array Array of ArrayWrapperArrayItem.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putValid(List<String> array, final Callback<Void> callback) {
        ArrayWrapper complexBody = new ArrayWrapper();
        complexBody.setArray(array);
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), client.serializerAdapter.serialize(complexBody, client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            callback.onFailure(new RuntimeException(ioe), null);
            return;
        }
        Call<ResponseBody> call = service.putValid(okHttp3RequestBody);
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
     * Put complex types with array property.
     * 
     * @param array Array of ArrayWrapperArrayItem.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putValidWithRestResponse(List<String> array) {
        ArrayWrapper complexBody = new ArrayWrapper();
        complexBody.setArray(array);
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), this.client.serializerAdapter.serialize(complexBody, this.client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.putValid(okHttp3RequestBody));
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

    /**
     * Get complex types with array property which is empty.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getEmpty(final Callback<ArrayWrapper> callback) {
        Call<ResponseBody> call = service.getEmpty();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ArrayWrapper decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ArrayWrapper.class);
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
     * Get complex types with array property which is empty.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property which is empty.
     */
    public Response<ArrayWrapper> getEmptyWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getEmpty());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), ArrayWrapper.class));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new ErrorException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Put complex types with array property which is empty.
     * 
     * @param array Array of ArrayWrapperArrayItem.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putEmpty(List<String> array, final Callback<Void> callback) {
        ArrayWrapper complexBody = new ArrayWrapper();
        complexBody.setArray(array);
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), client.serializerAdapter.serialize(complexBody, client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            callback.onFailure(new RuntimeException(ioe), null);
            return;
        }
        Call<ResponseBody> call = service.putEmpty(okHttp3RequestBody);
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
     * Put complex types with array property which is empty.
     * 
     * @param array Array of ArrayWrapperArrayItem.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putEmptyWithRestResponse(List<String> array) {
        ArrayWrapper complexBody = new ArrayWrapper();
        complexBody.setArray(array);
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), this.client.serializerAdapter.serialize(complexBody, this.client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.putEmpty(okHttp3RequestBody));
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

    /**
     * Get complex types with array property while server doesn't provide a response payload.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNotProvided(final Callback<ArrayWrapper> callback) {
        Call<ResponseBody> call = service.getNotProvided();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final ArrayWrapper decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), ArrayWrapper.class);
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
     * Get complex types with array property while server doesn't provide a response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with array property while server doesn't provide a response payload.
     */
    public Response<ArrayWrapper> getNotProvidedWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getNotProvided());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), ArrayWrapper.class));
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
