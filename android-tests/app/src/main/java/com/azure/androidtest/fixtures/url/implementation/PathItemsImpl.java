package com.azure.androidtest.fixtures.url.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.androidtest.fixtures.url.models.ErrorException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * An instance of this class provides access to all the operations defined in
 * PathItems.
 */
public final class PathItemsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final PathItemsService service;

    /**
     * The service client containing this operation class.
     */
    private final AutoRestUrlTestServiceImpl client;

    /**
     * Initializes an instance of PathItemsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    PathItemsImpl(AutoRestUrlTestServiceImpl client) {
        this.client = client;
        this.service = this.client.getServiceClient().getRetrofit().create(PathItemsService.class);
    }

    /**
     * The interface defining all the services for
     * AutoRestUrlTestServicePathItems to be used by the proxy service to
     * perform REST calls.
     */
    private interface PathItemsService {
        @GET("/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/globalStringQuery/pathItemStringQuery/localStringQuery")
        Call<ResponseBody> getAllWithValues(@Path("pathItemStringPath") String pathItemStringPath, @Path("globalStringPath") String globalStringPath, @Path("localStringPath") String localStringPath, @Query("pathItemStringQuery") String pathItemStringQuery, @Query("globalStringQuery") String globalStringQuery, @Query("localStringQuery") String localStringQuery);

        @GET("/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/null/pathItemStringQuery/localStringQuery")
        Call<ResponseBody> getGlobalQueryNull(@Path("pathItemStringPath") String pathItemStringPath, @Path("globalStringPath") String globalStringPath, @Path("localStringPath") String localStringPath, @Query("pathItemStringQuery") String pathItemStringQuery, @Query("globalStringQuery") String globalStringQuery, @Query("localStringQuery") String localStringQuery);

        @GET("/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/null/pathItemStringQuery/null")
        Call<ResponseBody> getGlobalAndLocalQueryNull(@Path("pathItemStringPath") String pathItemStringPath, @Path("globalStringPath") String globalStringPath, @Path("localStringPath") String localStringPath, @Query("pathItemStringQuery") String pathItemStringQuery, @Query("globalStringQuery") String globalStringQuery, @Query("localStringQuery") String localStringQuery);

        @GET("/pathitem/nullable/globalStringPath/{globalStringPath}/pathItemStringPath/{pathItemStringPath}/localStringPath/{localStringPath}/globalStringQuery/null/null")
        Call<ResponseBody> getLocalPathItemQueryNull(@Path("pathItemStringPath") String pathItemStringPath, @Path("globalStringPath") String globalStringPath, @Path("localStringPath") String localStringPath, @Query("pathItemStringQuery") String pathItemStringQuery, @Query("globalStringQuery") String globalStringQuery, @Query("localStringQuery") String localStringQuery);
    }

    /**
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getAllWithValues(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery, final Callback<Void> callback) {
        Call<ResponseBody> call = service.getAllWithValues(pathItemStringPath, this.client.getGlobalStringPath(), localStringPath, pathItemStringQuery, this.client.getGlobalStringQuery(), localStringQuery);
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
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> getAllWithValuesWithRestResponse(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getAllWithValues(pathItemStringPath, this.client.getGlobalStringPath(), localStringPath, pathItemStringQuery, this.client.getGlobalStringQuery(), localStringQuery));
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
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getGlobalQueryNull(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery, final Callback<Void> callback) {
        Call<ResponseBody> call = service.getGlobalQueryNull(pathItemStringPath, this.client.getGlobalStringPath(), localStringPath, pathItemStringQuery, this.client.getGlobalStringQuery(), localStringQuery);
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
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery='localStringQuery'.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain value 'localStringQuery'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> getGlobalQueryNullWithRestResponse(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getGlobalQueryNull(pathItemStringPath, this.client.getGlobalStringPath(), localStringPath, pathItemStringQuery, this.client.getGlobalStringQuery(), localStringQuery));
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
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery=null.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain null value.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getGlobalAndLocalQueryNull(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery, final Callback<Void> callback) {
        Call<ResponseBody> call = service.getGlobalAndLocalQueryNull(pathItemStringPath, this.client.getGlobalStringPath(), localStringPath, pathItemStringQuery, this.client.getGlobalStringQuery(), localStringQuery);
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
     * send globalStringPath=globalStringPath, pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery=null, pathItemStringQuery='pathItemStringQuery', localStringQuery=null.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery A string value 'pathItemStringQuery' that appears as a query parameter.
     * @param localStringQuery should contain null value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> getGlobalAndLocalQueryNullWithRestResponse(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getGlobalAndLocalQueryNull(pathItemStringPath, this.client.getGlobalStringPath(), localStringPath, pathItemStringQuery, this.client.getGlobalStringQuery(), localStringQuery));
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
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null, localStringQuery=null.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery should contain value null.
     * @param localStringQuery should contain value null.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getLocalPathItemQueryNull(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery, final Callback<Void> callback) {
        Call<ResponseBody> call = service.getLocalPathItemQueryNull(pathItemStringPath, this.client.getGlobalStringPath(), localStringPath, pathItemStringQuery, this.client.getGlobalStringQuery(), localStringQuery);
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
     * send globalStringPath='globalStringPath', pathItemStringPath='pathItemStringPath', localStringPath='localStringPath', globalStringQuery='globalStringQuery', pathItemStringQuery=null, localStringQuery=null.
     * 
     * @param pathItemStringPath A string value 'pathItemStringPath' that appears in the path.
     * @param localStringPath should contain value 'localStringPath'.
     * @param pathItemStringQuery should contain value null.
     * @param localStringQuery should contain value null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> getLocalPathItemQueryNullWithRestResponse(String pathItemStringPath, String localStringPath, String pathItemStringQuery, String localStringQuery) {
        final retrofit2.Response<ResponseBody> response = this.client.executeRetrofitCall(service.getLocalPathItemQueryNull(pathItemStringPath, this.client.getGlobalStringPath(), localStringPath, pathItemStringQuery, this.client.getGlobalStringQuery(), localStringQuery));
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
