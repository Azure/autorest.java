package com.azure.androidtest.fixtures.bodydatetimerfc1123.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodydatetimerfc1123.models.ErrorException;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * An instance of this class provides access to all the operations defined in
 * Datetimerfc1123s.
 */
public final class Datetimerfc1123sImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final Datetimerfc1123sService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestRFC1123DateTimeTestServiceImpl client;

    /**
     * Initializes an instance of Datetimerfc1123sImpl.
     * 
     * @param client the instance of the service client containing this operation
     *               class.
     */
    Datetimerfc1123sImpl(AutoRestRFC1123DateTimeTestServiceImpl client) {
        this.client = client;
        this.service = this.client.getServiceClient().getRetrofit().create(Datetimerfc1123sService.class);
    }

    /**
     * The interface defining all the services for
     * AutoRestRFC1123DateTimeTestServiceDatetimerfc1123s to be used by the proxy
     * service to perform REST calls.
     */
    private interface Datetimerfc1123sService {
        @GET("/datetimerfc1123/null")
        Call<ResponseBody> getNull();

        @GET("/datetimerfc1123/invalid")
        Call<ResponseBody> getInvalid();

        @GET("/datetimerfc1123/overflow")
        Call<ResponseBody> getOverflow();

        @GET("/datetimerfc1123/underflow")
        Call<ResponseBody> getUnderflow();

        @PUT("/datetimerfc1123/max")
        Call<ResponseBody> putUtcMaxDateTime(@Body RequestBody datetimeBody);

        @GET("/datetimerfc1123/max/lowercase")
        Call<ResponseBody> getUtcLowercaseMaxDateTime();

        @GET("/datetimerfc1123/max/uppercase")
        Call<ResponseBody> getUtcUppercaseMaxDateTime();

        @PUT("/datetimerfc1123/min")
        Call<ResponseBody> putUtcMinDateTime(@Body RequestBody datetimeBody);

        @GET("/datetimerfc1123/min")
        Call<ResponseBody> getUtcMinDateTime();
    }

    /**
     * Get null datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getNull(final Callback<DateTimeRfc1123> callback) {
        Call<ResponseBody> call = service.getNull();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DateTimeRfc1123 decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(),
                                    DateTimeRfc1123.class);
                        } catch (Exception ex) {
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
     * Get null datetime value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return null datetime value.
     */
    public Response<DateTimeRfc1123> getNullWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getNull());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(), response.code(), response.headers(),
                        this.client.deserializeContent(response.headers(), response.body(), DateTimeRfc1123.class));
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
     * Get invalid datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getInvalid(final Callback<DateTimeRfc1123> callback) {
        Call<ResponseBody> call = service.getInvalid();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DateTimeRfc1123 decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(),
                                    DateTimeRfc1123.class);
                        } catch (Exception ex) {
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
     * Get invalid datetime value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return invalid datetime value.
     */
    public Response<DateTimeRfc1123> getInvalidWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getInvalid());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(), response.code(), response.headers(),
                        this.client.deserializeContent(response.headers(), response.body(), DateTimeRfc1123.class));
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
     * Get overflow datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getOverflow(final Callback<DateTimeRfc1123> callback) {
        Call<ResponseBody> call = service.getOverflow();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DateTimeRfc1123 decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(),
                                    DateTimeRfc1123.class);
                        } catch (Exception ex) {
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
     * Get overflow datetime value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return overflow datetime value.
     */
    public Response<DateTimeRfc1123> getOverflowWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getOverflow());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(), response.code(), response.headers(),
                        this.client.deserializeContent(response.headers(), response.body(), DateTimeRfc1123.class));
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
     * Get underflow datetime value.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getUnderflow(final Callback<DateTimeRfc1123> callback) {
        Call<ResponseBody> call = service.getUnderflow();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DateTimeRfc1123 decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(),
                                    DateTimeRfc1123.class);
                        } catch (Exception ex) {
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
     * Get underflow datetime value.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return underflow datetime value.
     */
    public Response<DateTimeRfc1123> getUnderflowWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getUnderflow());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(), response.code(), response.headers(),
                        this.client.deserializeContent(response.headers(), response.body(), DateTimeRfc1123.class));
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
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
     * 
     * @param datetimeBody datetime body.
     * @param callback     the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void putUtcMaxDateTime(DateTimeRfc1123 datetimeBody, final Callback<Void> callback) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), client.serializerAdapter
                    .serialize(datetimeBody, client.resolveSerializerFormat("application/json")));
        } catch (java.io.IOException ioe) {
            callback.onFailure(new RuntimeException(ioe), null);
            return;
        }
        Call<ResponseBody> call = service.putUtcMaxDateTime(okHttp3RequestBody);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final Void decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), Void.class);
                        } catch (Exception ex) {
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
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putUtcMaxDateTimeWithRestResponse(DateTimeRfc1123 datetimeBody) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"),
                    this.client.serializerAdapter.serialize(datetimeBody,
                            this.client.resolveSerializerFormat("application/json")));
        } catch (java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        final retrofit2.Response<ResponseBody> response = this.client
                .executeRetrofitCall(service.putUtcMaxDateTime(okHttp3RequestBody));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(), response.code(), response.headers(),
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
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getUtcLowercaseMaxDateTime(final Callback<DateTimeRfc1123> callback) {
        Call<ResponseBody> call = service.getUtcLowercaseMaxDateTime();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DateTimeRfc1123 decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(),
                                    DateTimeRfc1123.class);
                        } catch (Exception ex) {
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
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return max datetime value fri, 31 dec 9999 23:59:59 gmt.
     */
    public Response<DateTimeRfc1123> getUtcLowercaseMaxDateTimeWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client
                .executeRetrofitCall(service.getUtcLowercaseMaxDateTime());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(), response.code(), response.headers(),
                        this.client.deserializeContent(response.headers(), response.body(), DateTimeRfc1123.class));
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
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getUtcUppercaseMaxDateTime(final Callback<DateTimeRfc1123> callback) {
        Call<ResponseBody> call = service.getUtcUppercaseMaxDateTime();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DateTimeRfc1123 decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(),
                                    DateTimeRfc1123.class);
                        } catch (Exception ex) {
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
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     */
    public Response<DateTimeRfc1123> getUtcUppercaseMaxDateTimeWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client
                .executeRetrofitCall(service.getUtcUppercaseMaxDateTime());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(), response.code(), response.headers(),
                        this.client.deserializeContent(response.headers(), response.body(), DateTimeRfc1123.class));
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
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @param datetimeBody datetime body.
     * @param callback     the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void putUtcMinDateTime(DateTimeRfc1123 datetimeBody, final Callback<Void> callback) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), client.serializerAdapter
                    .serialize(datetimeBody, client.resolveSerializerFormat("application/json")));
        } catch (java.io.IOException ioe) {
            callback.onFailure(new RuntimeException(ioe), null);
            return;
        }
        Call<ResponseBody> call = service.putUtcMinDateTime(okHttp3RequestBody);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final Void decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), Void.class);
                        } catch (Exception ex) {
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
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @param datetimeBody datetime body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putUtcMinDateTimeWithRestResponse(DateTimeRfc1123 datetimeBody) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"),
                    this.client.serializerAdapter.serialize(datetimeBody,
                            this.client.resolveSerializerFormat("application/json")));
        } catch (java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        final retrofit2.Response<ResponseBody> response = this.client
                .executeRetrofitCall(service.putUtcMinDateTime(okHttp3RequestBody));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(), response.code(), response.headers(),
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
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException           thrown if the request is rejected by server.
     * @throws RuntimeException         all other wrapped checked exceptions if the
     *                                  request fails to be sent.
     */
    public void getUtcMinDateTime(final Callback<DateTimeRfc1123> callback) {
        Call<ResponseBody> call = service.getUtcMinDateTime();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DateTimeRfc1123 decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(),
                                    DateTimeRfc1123.class);
                        } catch (Exception ex) {
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
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     * 
     * @throws ErrorException   thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request
     *                          fails to be sent.
     * @return min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     */
    public Response<DateTimeRfc1123> getUtcMinDateTimeWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getUtcMinDateTime());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(), response.code(), response.headers(),
                        this.client.deserializeContent(response.headers(), response.body(), DateTimeRfc1123.class));
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
