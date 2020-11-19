package com.azure.androidtest.fixtures.errorresponse.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.androidtest.fixtures.errorresponse.models.Pet;
import com.azure.androidtest.fixtures.errorresponse.models.PetAction;
import com.azure.androidtest.fixtures.errorresponse.models.PetActionErrorException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * An instance of this class provides access to all the operations defined in
 * Pets.
 */
public final class PetsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PetsService service;

    /**
     * The service client containing this operation class.
     */
    private final XMSErrorResponseExtensionsImpl client;

    /**
     * Initializes an instance of PetsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    PetsImpl(XMSErrorResponseExtensionsImpl client) {
        this.client = client;
        this.service = this.client.getServiceClient().getRetrofit().create(PetsService.class);
    }

    /**
     * The interface defining all the services for
     * XMSErrorResponseExtensionsPets to be used by the proxy service to
     * perform REST calls.
     */
    private interface PetsService {
        @GET("/errorStatusCodes/Pets/{petId}/GetPet")
        Call<ResponseBody> getPetById(@Path("petId") String petId);

        @POST("/errorStatusCodes/Pets/doSomething/{whatAction}")
        Call<ResponseBody> doSomething(@Path("whatAction") String whatAction);

        @POST("/errorStatusCodes/Pets/hasModelsParam")
        Call<ResponseBody> hasModelsParam(@Query("models") String models);
    }

    /**
     * Gets pets by id.
     * 
     * @param petId pet id.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getPetById(String petId, final Callback<Pet> callback) {
        Call<ResponseBody> call = service.getPetById(petId);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 || response.code() == 202) {
                        final Pet decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), Pet.class);
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
     * Gets pets by id.
     * 
     * @param petId pet id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return pets by id.
     */
    public Response<Pet> getPetByIdWithRestResponse(String petId) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getPetById(petId));
        if (response.isSuccessful()) {
            if (response.code() == 200 || response.code() == 202) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), Pet.class));
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
     * Asks pet to do something.
     * 
     * @param whatAction what action the pet should do.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws PetActionErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void doSomething(String whatAction, final Callback<PetAction> callback) {
        Call<ResponseBody> call = service.doSomething(whatAction);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final PetAction decodedResult;
                        try {
                            decodedResult = client.deserializeContent(response.headers(), response.body(), PetAction.class);
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(decodedResult, response.raw());
                    } else {
                        final String strContent = client.readAsString(response.body());
                        callback.onFailure(new PetActionErrorException(strContent, response.raw()), response.raw());
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
     * Asks pet to do something.
     * 
     * @param whatAction what action the pet should do.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws PetActionErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    public Response<PetAction> doSomethingWithRestResponse(String whatAction) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.doSomething(whatAction));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), PetAction.class));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new PetActionErrorException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Ensure you can correctly deserialize the returned PetActionError and deserialization doesn't conflict with the input param name 'models'.
     * 
     * @param models Make sure model deserialization doesn't conflict with this param name, which has input name 'models'. Use client default value in call.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws PetActionErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void hasModelsParam(String models, final Callback<Void> callback) {
        Call<ResponseBody> call = service.hasModelsParam(models);
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
                        callback.onFailure(new PetActionErrorException(strContent, response.raw()), response.raw());
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
     * Ensure you can correctly deserialize the returned PetActionError and deserialization doesn't conflict with the input param name 'models'.
     * 
     * @param models Make sure model deserialization doesn't conflict with this param name, which has input name 'models'. Use client default value in call.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws PetActionErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> hasModelsParamWithRestResponse(String models) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.hasModelsParam(models));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.client.deserializeContent(response.headers(), response.body(), Void.class));
            } else {
                final String strContent = this.client.readAsString(response.body());
                throw new PetActionErrorException(strContent, response.raw());
            }
        } else {
            final String strContent = this.client.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }
}
