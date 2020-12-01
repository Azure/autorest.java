package com.azure.androidtest.fixtures.report.implementation;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.internal.util.serializer.SerializerAdapter;
import com.azure.android.core.internal.util.serializer.SerializerFormat;
import com.azure.androidtest.fixtures.report.models.ErrorException;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Initializes a new instance of the AutoRestReportService type.
 */
public final class AutoRestReportServiceImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final AutoRestReportServiceService service;

    /**
     * The serializer.
     */
    final SerializerAdapter serializerAdapter = SerializerAdapter.createDefault();

    /**
     * The Azure Core generic ServiceClient to setup interceptors and produce retrofit proxy.
     */
    private ServiceClient serviceClient;

    /**
     * Gets The Azure Core generic ServiceClient to setup interceptors and produce retrofit proxy.
     * 
     * @return the serviceClient value.
     */
    public ServiceClient getServiceClient() {
        return this.serviceClient;
    }

    /**
     * server parameter.
     */
    private final String host;

    /**
     * Gets server parameter.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Initializes an instance of AutoRestReportService client.
     * 
     * @param serviceClient The Azure Core generic ServiceClient to setup interceptors and produce retrofit proxy.
     * @param host server parameter.
     */
    public AutoRestReportServiceImpl(ServiceClient serviceClient, String host) {
        this.serviceClient = serviceClient;
        this.host = host;
        this.service = serviceClient.getRetrofit().create(AutoRestReportServiceService.class);
    }

    /**
     * The interface defining all the services for AutoRestReportService to be
     * used by the proxy service to perform REST calls.
     */
    private interface AutoRestReportServiceService {
        @GET("/report")
        Call<ResponseBody> getReport(@Query("qualifier") String qualifier);

        @GET("/report/optional")
        Call<ResponseBody> getOptionalReport(@Query("qualifier") String qualifier);
    }

    /**
     * Get test coverage report.
     * 
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getReport(String qualifier, final Callback<Map<String, Integer>> callback) {
        Call<ResponseBody> call = service.getReport(qualifier);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final Map<String, Integer> decodedResult;
                        try {
                            decodedResult = deserializeContent(response.headers(), response.body(), createParameterizedType(java.util.Map.class, String.class, int.class));
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(decodedResult, response.raw());
                    } else {
                        final String strContent = readAsString(response.body());
                        callback.onFailure(new ErrorException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = readAsString(response.errorBody());
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
     * Get test coverage report.
     * 
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return test coverage report.
     */
    public Response<Map<String, Integer>> getReportWithRestResponse(String qualifier) {
        final retrofit2.Response<ResponseBody> response = this.executeRetrofitCall(service.getReport(qualifier));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.deserializeContent(response.headers(), response.body(), this.createParameterizedType(java.util.Map.class, String.class, int.class)));
            } else {
                final String strContent = this.readAsString(response.body());
                throw new ErrorException(strContent, response.raw());
            }
        } else {
            final String strContent = this.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    /**
     * Get optional test coverage report.
     * 
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @param callback the Callback that receives the response.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getOptionalReport(String qualifier, final Callback<Map<String, Integer>> callback) {
        Call<ResponseBody> call = service.getOptionalReport(qualifier);
        retrofit2.Callback<ResponseBody> retrofitCallback = new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<okhttp3.ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        final Map<String, Integer> decodedResult;
                        try {
                            decodedResult = deserializeContent(response.headers(), response.body(), createParameterizedType(java.util.Map.class, String.class, int.class));
                        } catch(Exception ex) {
                            callback.onFailure(ex, response.raw());
                            return;
                        }
                        callback.onSuccess(decodedResult, response.raw());
                    } else {
                        final String strContent = readAsString(response.body());
                        callback.onFailure(new ErrorException(strContent, response.raw()), response.raw());
                    }
                } else {
                    final String strContent = readAsString(response.errorBody());
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
     * Get optional test coverage report.
     * 
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return optional test coverage report.
     */
    public Response<Map<String, Integer>> getOptionalReportWithRestResponse(String qualifier) {
        final retrofit2.Response<ResponseBody> response = this.executeRetrofitCall(service.getOptionalReport(qualifier));
        if (response.isSuccessful()) {
            if (response.code() == 200) {
                return new Response<>(response.raw().request(),
                                        response.code(),
                                        response.headers(),
                                        this.deserializeContent(response.headers(), response.body(), this.createParameterizedType(java.util.Map.class, String.class, int.class)));
            } else {
                final String strContent = this.readAsString(response.body());
                throw new ErrorException(strContent, response.raw());
            }
        } else {
            final String strContent = this.readAsString(response.errorBody());
            throw new HttpResponseException(strContent, response.raw());
        }
    }

    String readAsString(okhttp3.ResponseBody body) {
        if (body == null) {
            return "";
        }
        try {
            return new String(body.bytes());
        } catch(java.io.IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            body.close();
        }
    }

    <T> T deserializeContent(okhttp3.Headers headers, okhttp3.ResponseBody body, java.lang.reflect.Type type) {
        if (type.equals(byte[].class)) {
            try {
                if (body.contentLength() == 0) {
                    return null;
                }
                return (T) body.bytes();
            } catch(java.io.IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        final String str = readAsString(body);
        try {
            final String mimeContentType = headers.get(CONTENT_TYPE);
            return this.serializerAdapter.deserialize(str, type, resolveSerializerFormat(mimeContentType));
        } catch(java.io.IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    <T> T deserializeHeaders(okhttp3.Headers headers, java.lang.reflect.Type type) {
        try {
            return this.serializerAdapter.deserialize(headers, type);
        } catch(java.io.IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    <T> retrofit2.Response<T> executeRetrofitCall(retrofit2.Call<T> call) {
        try {
            return call.execute();
        } catch(java.io.IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    java.lang.reflect.ParameterizedType createParameterizedType(Class<?> rawClass, java.lang.reflect.Type... genericTypes) {
        return new java.lang.reflect.ParameterizedType() {
            @Override
            public java.lang.reflect.Type[] getActualTypeArguments() {
                return genericTypes;
            }
            @Override
            public java.lang.reflect.Type getRawType() {
                return rawClass;
            }
            @Override
            public java.lang.reflect.Type getOwnerType() {
                return null;
            }
        };
    }

    private static final String CONTENT_TYPE = "Content-Type";

    private static final java.util.Map<String, SerializerFormat> SUPPORTED_MIME_TYPES = new java.util.TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private static final java.util.TreeMap<String, SerializerFormat> SUPPORTED_SUFFIXES = new java.util.TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private static final SerializerFormat DEFAULT_ENCODING = SerializerFormat.JSON;
    static {
        SUPPORTED_MIME_TYPES.put("text/xml", SerializerFormat.XML);
        SUPPORTED_MIME_TYPES.put("application/xml", SerializerFormat.XML);
        SUPPORTED_MIME_TYPES.put("application/json", SerializerFormat.JSON);
        SUPPORTED_SUFFIXES.put("xml", SerializerFormat.XML);
        SUPPORTED_SUFFIXES.put("json", SerializerFormat.JSON);
    }

    SerializerFormat resolveSerializerFormat(String mimeContentType) {
        if (mimeContentType == null || mimeContentType.isEmpty()) {
            return DEFAULT_ENCODING;
        }
        final String[] parts = mimeContentType.split(";");
        final SerializerFormat encoding = SUPPORTED_MIME_TYPES.get(parts[0]);
        if (encoding != null) {
            return encoding;
        }
        final String[] mimeTypeParts = parts[0].split("/");
        if (mimeTypeParts.length != 2) {
            return DEFAULT_ENCODING;
        }
        final String subtype = mimeTypeParts[1];
        final int lastIndex = subtype.lastIndexOf("+");
        if (lastIndex == -1) {
            return DEFAULT_ENCODING;
        }
        final String mimeTypeSuffix = subtype.substring(lastIndex + 1);
        final SerializerFormat serializerEncoding = SUPPORTED_SUFFIXES.get(mimeTypeSuffix);
        if (serializerEncoding != null) {
            return serializerEncoding;
        }
        return DEFAULT_ENCODING;
    }
}
