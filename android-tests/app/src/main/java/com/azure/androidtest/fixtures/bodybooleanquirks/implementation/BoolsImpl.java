package com.azure.androidtest.fixtures.bodybooleanquirks.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.androidtest.fixtures.bodybooleanquirks.models.ErrorException;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * An instance of this class provides access to all the operations defined in
 * Bools.
 */
public final class BoolsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final BoolsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestBoolTestServiceImpl client;

    /**
     * Initializes an instance of BoolsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    BoolsImpl(AutoRestBoolTestServiceImpl client) {
        this.client = client;
        this.service = this.client.getServiceClient().getRetrofit().create(BoolsService.class);
    }

    /**
     * The interface defining all the services for AutoRestBoolTestServiceBools
     * to be used by the proxy service to perform REST calls.
     */
    private interface BoolsService {
        @GET("/bool/true")
        Call<ResponseBody> getTrue();

        @PUT("/bool/true")
        Call<ResponseBody> putTrue(@Body RequestBody boolBody);

        @GET("/bool/false")
        Call<ResponseBody> getFalse();

        @PUT("/bool/false")
        Call<ResponseBody> putFalse(@Body RequestBody boolBody);

        @GET("/bool/null")
        Call<ResponseBody> getNull();

        @GET("/bool/invalid")
        Call<ResponseBody> getInvalid();
    }

    /**
     * Get true Boolean value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getTrue(final Callback<Boolean> callback) {
        Call<ResponseBody> call = service.getTrue();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final boolean decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), boolean.class);
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
     * Get true Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return true Boolean value.
     */
    public Response<Boolean> getTrueWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getTrue());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), boolean.class));
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
     * Set Boolean value true.
     * 
     * @param boolBody The boolBody parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putTrue(boolean boolBody, final Callback<Void> callback) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), client.serializerAdapter.serialize(boolBody, client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            callback.onFailure(new RuntimeException(ioe), null);
            return;
        }
        Call<ResponseBody> call = service.putTrue(okHttp3RequestBody);
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
     * Set Boolean value true.
     * 
     * @param boolBody The boolBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putTrueWithRestResponse(boolean boolBody) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), this.client.serializerAdapter.serialize(boolBody, this.client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.putTrue(okHttp3RequestBody));
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
     * Get false Boolean value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getFalse(final Callback<Boolean> callback) {
        Call<ResponseBody> call = service.getFalse();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final boolean decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), boolean.class);
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
     * Get false Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return false Boolean value.
     */
    public Response<Boolean> getFalseWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getFalse());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), boolean.class));
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
     * Set Boolean value false.
     * 
     * @param boolBody The boolBody parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putFalse(boolean boolBody, final Callback<Void> callback) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), client.serializerAdapter.serialize(boolBody, client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            callback.onFailure(new RuntimeException(ioe), null);
            return;
        }
        Call<ResponseBody> call = service.putFalse(okHttp3RequestBody);
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
     * Set Boolean value false.
     * 
     * @param boolBody The boolBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putFalseWithRestResponse(boolean boolBody) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), this.client.serializerAdapter.serialize(boolBody, this.client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.putFalse(okHttp3RequestBody));
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
     * Get null Boolean value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getNull(final Callback<Boolean> callback) {
        Call<ResponseBody> call = service.getNull();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final boolean decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), boolean.class);
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
     * Get null Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null Boolean value.
     */
    public Response<Boolean> getNullWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getNull());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), boolean.class));
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
     * Get invalid Boolean value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getInvalid(final Callback<Boolean> callback) {
        Call<ResponseBody> call = service.getInvalid();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final boolean decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), boolean.class);
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
     * Get invalid Boolean value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return invalid Boolean value.
     */
    public Response<Boolean> getInvalidWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getInvalid());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), boolean.class));
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
