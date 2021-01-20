package com.azure.androidtest.fixtures.bodycomplex.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.androidtest.fixtures.bodycomplex.models.DotFish;
import com.azure.androidtest.fixtures.bodycomplex.models.DotFishMarket;
import com.azure.androidtest.fixtures.bodycomplex.models.ErrorException;
import com.azure.androidtest.fixtures.bodycomplex.models.Fish;
import com.azure.androidtest.fixtures.bodycomplex.models.Salmon;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * An instance of this class provides access to all the operations defined in
 * Polymorphisms.
 */
public final class PolymorphismsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PolymorphismsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestComplexTestServiceImpl client;

    /**
     * Initializes an instance of PolymorphismsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    PolymorphismsImpl(AutoRestComplexTestServiceImpl client) {
        this.client = client;
        this.service = this.client.getServiceClient().getRetrofit().create(PolymorphismsService.class);
    }

    /**
     * The interface defining all the services for
     * AutoRestComplexTestServicePolymorphisms to be used by the proxy service
     * to perform REST calls.
     */
    private interface PolymorphismsService {
        @GET("/complex/polymorphism/valid")
        Call<ResponseBody> getValid();

        @PUT("/complex/polymorphism/valid")
        Call<ResponseBody> putValid(@Body RequestBody complexBody);

        @GET("/complex/polymorphism/dotsyntax")
        Call<ResponseBody> getDotSyntax();

        @GET("/complex/polymorphism/composedWithDiscriminator")
        Call<ResponseBody> getComposedWithDiscriminator();

        @GET("/complex/polymorphism/composedWithoutDiscriminator")
        Call<ResponseBody> getComposedWithoutDiscriminator();

        @GET("/complex/polymorphism/complicated")
        Call<ResponseBody> getComplicated();

        @PUT("/complex/polymorphism/complicated")
        Call<ResponseBody> putComplicated(@Body RequestBody complexBody);

        @PUT("/complex/polymorphism/missingdiscriminator")
        Call<ResponseBody> putMissingDiscriminator(@Body RequestBody complexBody);

        @PUT("/complex/polymorphism/missingrequired/invalid")
        Call<ResponseBody> putValidMissingRequired(@Body RequestBody complexBody);
    }

    /**
     * Get complex types that are polymorphic.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getValid(final Callback<Fish> callback) {
        Call<ResponseBody> call = service.getValid();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final Fish decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), Fish.class);
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
     * Get complex types that are polymorphic.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic.
     */
    public Response<Fish> getValidWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getValid());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), Fish.class));
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
     * Put complex types that are polymorphic.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     *         'fishtype':'Salmon',
     *         'location':'alaska',
     *         'iswild':true,
     *         'species':'king',
     *         'length':1.0,
     *         'siblings':[
     *           {
     *             'fishtype':'Shark',
     *             'age':6,
     *             'birthday': '2012-01-05T01:00:00Z',
     *             'length':20.0,
     *             'species':'predator',
     *           },
     *           {
     *             'fishtype':'Sawshark',
     *             'age':105,
     *             'birthday': '1900-01-05T01:00:00Z',
     *             'length':10.0,
     *             'picture': new Buffer([255, 255, 255, 255, 254]).toString('base64'),
     *             'species':'dangerous',
     *           },
     *           {
     *             'fishtype': 'goblin',
     *             'age': 1,
     *             'birthday': '2015-08-08T00:00:00Z',
     *             'length': 30.0,
     *             'species': 'scary',
     *             'jawsize': 5
     *           }
     *         ]
     *       };.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putValid(Fish complexBody, final Callback<Void> callback) {
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
     * Put complex types that are polymorphic.
     * 
     * @param complexBody Please put a salmon that looks like this:
     * {
     *         'fishtype':'Salmon',
     *         'location':'alaska',
     *         'iswild':true,
     *         'species':'king',
     *         'length':1.0,
     *         'siblings':[
     *           {
     *             'fishtype':'Shark',
     *             'age':6,
     *             'birthday': '2012-01-05T01:00:00Z',
     *             'length':20.0,
     *             'species':'predator',
     *           },
     *           {
     *             'fishtype':'Sawshark',
     *             'age':105,
     *             'birthday': '1900-01-05T01:00:00Z',
     *             'length':10.0,
     *             'picture': new Buffer([255, 255, 255, 255, 254]).toString('base64'),
     *             'species':'dangerous',
     *           },
     *           {
     *             'fishtype': 'goblin',
     *             'age': 1,
     *             'birthday': '2015-08-08T00:00:00Z',
     *             'length': 30.0,
     *             'species': 'scary',
     *             'jawsize': 5
     *           }
     *         ]
     *       };.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putValidWithRestResponse(Fish complexBody) {
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
     * Get complex types that are polymorphic, JSON key contains a dot.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getDotSyntax(final Callback<DotFish> callback) {
        Call<ResponseBody> call = service.getDotSyntax();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DotFish decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), DotFish.class);
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
     * Get complex types that are polymorphic, JSON key contains a dot.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, JSON key contains a dot.
     */
    public Response<DotFish> getDotSyntaxWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getDotSyntax());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), DotFish.class));
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
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, with discriminator specified. Deserialization must NOT fail and use the discriminator type specified on the wire.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComposedWithDiscriminator(final Callback<DotFishMarket> callback) {
        Call<ResponseBody> call = service.getComposedWithDiscriminator();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DotFishMarket decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), DotFishMarket.class);
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
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, with discriminator specified. Deserialization must NOT fail and use the discriminator type specified on the wire.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type, with discriminator specified.
     */
    public Response<DotFishMarket> getComposedWithDiscriminatorWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getComposedWithDiscriminator());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), DotFishMarket.class));
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
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, without discriminator specified on wire. Deserialization must NOT fail and use the explicit type of the property.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComposedWithoutDiscriminator(final Callback<DotFishMarket> callback) {
        Call<ResponseBody> call = service.getComposedWithoutDiscriminator();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final DotFishMarket decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), DotFishMarket.class);
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
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, without discriminator specified on wire. Deserialization must NOT fail and use the explicit type of the property.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type, without discriminator specified on wire.
     */
    public Response<DotFishMarket> getComposedWithoutDiscriminatorWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getComposedWithoutDiscriminator());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), DotFishMarket.class));
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
     * Get complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     * 
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getComplicated(final Callback<Salmon> callback) {
        Call<ResponseBody> call = service.getComplicated();
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final Salmon decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), Salmon.class);
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
     * Get complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     */
    public Response<Salmon> getComplicatedWithRestResponse() {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getComplicated());
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), Salmon.class));
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
     * Put complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     * 
     * @param complexBody The complexBody parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putComplicated(Salmon complexBody, final Callback<Void> callback) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), client.serializerAdapter.serialize(complexBody, client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            callback.onFailure(new RuntimeException(ioe), null);
            return;
        }
        Call<ResponseBody> call = service.putComplicated(okHttp3RequestBody);
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
     * Put complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     * 
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putComplicatedWithRestResponse(Salmon complexBody) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), this.client.serializerAdapter.serialize(complexBody, this.client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.putComplicated(okHttp3RequestBody));
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
     * Put complex types that are polymorphic, omitting the discriminator.
     * 
     * @param complexBody The complexBody parameter.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putMissingDiscriminator(Salmon complexBody, final Callback<Salmon> callback) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), client.serializerAdapter.serialize(complexBody, client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            callback.onFailure(new RuntimeException(ioe), null);
            return;
        }
        Call<ResponseBody> call = service.putMissingDiscriminator(okHttp3RequestBody);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final Salmon decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), Salmon.class);
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
     * Put complex types that are polymorphic, omitting the discriminator.
     * 
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<Salmon> putMissingDiscriminatorWithRestResponse(Salmon complexBody) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), this.client.serializerAdapter.serialize(complexBody, this.client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.putMissingDiscriminator(okHttp3RequestBody));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), Salmon.class));
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
     * Put complex types that are polymorphic, attempting to omit required 'birthday' field - the request should not be allowed from the client.
     * 
     * @param complexBody Please attempt put a sawshark that looks like this, the client should not allow this data to be sent:
     * {
     *     "fishtype": "sawshark",
     *     "species": "snaggle toothed",
     *     "length": 18.5,
     *     "age": 2,
     *     "birthday": "2013-06-01T01:00:00Z",
     *     "location": "alaska",
     *     "picture": base64(FF FF FF FF FE),
     *     "siblings": [
     *         {
     *             "fishtype": "shark",
     *             "species": "predator",
     *             "birthday": "2012-01-05T01:00:00Z",
     *             "length": 20,
     *             "age": 6
     *         },
     *         {
     *             "fishtype": "sawshark",
     *             "species": "dangerous",
     *             "picture": base64(FF FF FF FF FE),
     *             "length": 10,
     *             "age": 105
     *         }
     *     ]
     * }.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putValidMissingRequired(Fish complexBody, final Callback<Void> callback) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), client.serializerAdapter.serialize(complexBody, client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            callback.onFailure(new RuntimeException(ioe), null);
            return;
        }
        Call<ResponseBody> call = service.putValidMissingRequired(okHttp3RequestBody);
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
     * Put complex types that are polymorphic, attempting to omit required 'birthday' field - the request should not be allowed from the client.
     * 
     * @param complexBody Please attempt put a sawshark that looks like this, the client should not allow this data to be sent:
     * {
     *     "fishtype": "sawshark",
     *     "species": "snaggle toothed",
     *     "length": 18.5,
     *     "age": 2,
     *     "birthday": "2013-06-01T01:00:00Z",
     *     "location": "alaska",
     *     "picture": base64(FF FF FF FF FE),
     *     "siblings": [
     *         {
     *             "fishtype": "shark",
     *             "species": "predator",
     *             "birthday": "2012-01-05T01:00:00Z",
     *             "length": 20,
     *             "age": 6
     *         },
     *         {
     *             "fishtype": "sawshark",
     *             "species": "dangerous",
     *             "picture": base64(FF FF FF FF FE),
     *             "length": 10,
     *             "age": 105
     *         }
     *     ]
     * }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putValidMissingRequiredWithRestResponse(Fish complexBody) {
        final okhttp3.RequestBody okHttp3RequestBody;
        try {
            okHttp3RequestBody = RequestBody.create(okhttp3.MediaType.get("application/json"), this.client.serializerAdapter.serialize(complexBody, this.client.resolveSerializerFormat("application/json")));
        } catch(java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.putValidMissingRequired(okHttp3RequestBody));
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
