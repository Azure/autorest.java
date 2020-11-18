package com.azure.androidtest.fixtures.bodystring;

import com.azure.android.core.http.Callback;
import com.azure.android.core.http.Response;
import com.azure.android.core.http.ServiceClient;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever;
import com.azure.android.core.http.responsepaging.PagedDataResponseRetriever;
import com.azure.android.core.util.Base64Url;
import com.azure.android.core.util.paging.PagedDataRetriever;
import com.azure.androidtest.fixtures.bodystring.implementation.AutoRestSwaggerBATServiceImpl;
import com.azure.androidtest.fixtures.bodystring.implementation.StringOperationsImpl;
import com.azure.androidtest.fixtures.bodystring.models.ErrorException;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Initializes a new instance of the synchronous AutoRestSwaggerBATService type.
 */
public final class StringOperationClient {
    private StringOperationsImpl serviceClient;

    /**
     * Initializes an instance of StringOperations client.
     */
    StringOperationClient(StringOperationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Get null string value value.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null string value value.
     */
    public Response<String> getNullWithRestResponse() {
        return this.serviceClient.getNullWithRestResponse();
    }

    /**
     * Set string value null.
     * 
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putNullWithRestResponse(String stringBody) {
        return this.serviceClient.putNullWithRestResponse(stringBody);
    }

    /**
     * Get empty string value value ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return empty string value value ''.
     */
    public Response<String> getEmptyWithRestResponse() {
        return this.serviceClient.getEmptyWithRestResponse();
    }

    /**
     * Set string value empty ''.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putEmptyWithRestResponse() {
        return this.serviceClient.putEmptyWithRestResponse();
    }

    /**
     * Get mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return mbcs string value '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     */
    public Response<String> getMbcsWithRestResponse() {
        return this.serviceClient.getMbcsWithRestResponse();
    }

    /**
     * Set string value mbcs '啊齄丂狛狜隣郎隣兀﨩ˊ〞〡￤℡㈱‐ー﹡﹢﹫、〓ⅰⅹ⒈€㈠㈩ⅠⅫ！￣ぁんァヶΑ︴АЯаяāɡㄅㄩ─╋︵﹄︻︱︳︴ⅰⅹɑɡ〇〾⿻⺁䜣€'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putMbcsWithRestResponse() {
        return this.serviceClient.putMbcsWithRestResponse();
    }

    /**
     * Get string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     */
    public Response<String> getWhitespaceWithRestResponse() {
        return this.serviceClient.getWhitespaceWithRestResponse();
    }

    /**
     * Set String value with leading and trailing whitespace '&lt;tab&gt;&lt;space&gt;&lt;space&gt;Now is the time for all good men to come to the aid of their country&lt;tab&gt;&lt;space&gt;&lt;space&gt;'.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putWhitespaceWithRestResponse() {
        return this.serviceClient.putWhitespaceWithRestResponse();
    }

    /**
     * Get String value when no string value is sent in response payload.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return string value when no string value is sent in response payload.
     */
    public Response<String> getNotProvidedWithRestResponse() {
        return this.serviceClient.getNotProvidedWithRestResponse();
    }

    /**
     * Get value that is base64 encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64 encoded.
     */
    public Response<byte[]> getBase64EncodedWithRestResponse() {
        return this.serviceClient.getBase64EncodedWithRestResponse();
    }

    /**
     * Get value that is base64url encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return value that is base64url encoded.
     */
    public Response<Base64Url> getBase64UrlEncodedWithRestResponse() {
        return this.serviceClient.getBase64UrlEncodedWithRestResponse();
    }

    /**
     * Put value that is base64url encoded.
     * 
     * @param stringBody string body.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    public Response<Void> putBase64UrlEncodedWithRestResponse(Base64Url stringBody) {
        return this.serviceClient.putBase64UrlEncodedWithRestResponse(stringBody);
    }

    /**
     * Get null value that is expected to be base64url encoded.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return null value that is expected to be base64url encoded.
     */
    public Response<Base64Url> getNullBase64UrlEncodedWithRestResponse() {
        return this.serviceClient.getNullBase64UrlEncodedWithRestResponse();
    }

    /**
     * A builder for creating a new instance of the StringOperationClient type.
     */
    public static final class Builder {
        /*
         * server parameter
         */
        private String host;

        /**
         * Sets server parameter.
         * 
         * @param host the host value.
         * @return the Builder.
         */
        public Builder host(String host) {
            this.host = host;
            return this;
        }

        /*
         * The Azure Core generic ServiceClient Builder.
         */
        private ServiceClient.Builder serviceClientBuilder;

        /**
         * Sets The Azure Core generic ServiceClient Builder.
         * 
         * @param serviceClientBuilder the serviceClientBuilder value.
         * @return the Builder.
         */
        public Builder serviceClientBuilder(ServiceClient.Builder serviceClientBuilder) {
            this.serviceClientBuilder = serviceClientBuilder;
            return this;
        }

        /*
         * The Interceptor to set intercept request and set credentials.
         */
        private Interceptor credentialInterceptor;

        /**
         * Sets The Interceptor to set intercept request and set credentials.
         * 
         * @param credentialInterceptor the credentialInterceptor value.
         * @return the Builder.
         */
        public Builder credentialInterceptor(Interceptor credentialInterceptor) {
            this.credentialInterceptor = credentialInterceptor;
            return this;
        }

        /**
         * Builds an instance of StringOperationClient with the provided parameters.
         * 
         * @return an instance of StringOperationClient.
         */
        public StringOperationClient build() {
            if (host == null) {
                this.host = "http://localhost:3000";
            }
            if (serviceClientBuilder == null) {
                if (host == null) {
                    throw new IllegalArgumentException("Missing required parameters 'host'.");
                }
                this.serviceClientBuilder = new ServiceClient.Builder();
            }
            if (host != null) {
                final String retrofitBaseUrl = host.replace("{host}", host);
                serviceClientBuilder.setBaseUrl(retrofitBaseUrl);
            }
            if (credentialInterceptor != null) {
                serviceClientBuilder.setCredentialsInterceptor(credentialInterceptor);
            }
            AutoRestSwaggerBATServiceImpl internalClient = new AutoRestSwaggerBATServiceImpl(serviceClientBuilder.build(), host);
            return new StringOperationClient(internalClient.getStringOperations());
        }
    }
}
