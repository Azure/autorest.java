package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.httpinfrastructure.implementation.HttpRedirectsImpl;

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class)
public final class HttpRedirectsClient {
    private final HttpRedirectsImpl serviceClient;

    /**
     * Initializes an instance of HttpRedirects client.
     *
     * @param serviceClient the service client implementation.
     */
    HttpRedirectsClient(HttpRedirectsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /** Return 300 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head300(RequestOptions requestOptions) {
        this.serviceClient.head300(requestOptions);
    }

    /** Return 300 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head300WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head300WithResponse(requestOptions, context);
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     String
     * ]
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData get300(RequestOptions requestOptions) {
        return this.serviceClient.get300(requestOptions);
    }

    /**
     * Return 300 status code and redirect to /http/success/200.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * [
     *     String
     * ]
     * }</pre>
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> get300WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get300WithResponse(requestOptions, context);
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head301(RequestOptions requestOptions) {
        this.serviceClient.head301(requestOptions);
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head301WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head301WithResponse(requestOptions, context);
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get301(RequestOptions requestOptions) {
        this.serviceClient.get301(requestOptions);
    }

    /** Return 301 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get301WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get301WithResponse(requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put301(RequestOptions requestOptions) {
        this.serviceClient.put301(requestOptions);
    }

    /**
     * Put true Boolean value in request returns 301. This request should not be automatically redirected, but should
     * return the received 301 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put301WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put301WithResponse(requestOptions, context);
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head302(RequestOptions requestOptions) {
        this.serviceClient.head302(requestOptions);
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head302WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head302WithResponse(requestOptions, context);
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get302(RequestOptions requestOptions) {
        this.serviceClient.get302(requestOptions);
    }

    /** Return 302 status code and redirect to /http/success/200. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get302WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get302WithResponse(requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch302(RequestOptions requestOptions) {
        this.serviceClient.patch302(requestOptions);
    }

    /**
     * Patch true Boolean value in request returns 302. This request should not be automatically redirected, but should
     * return the received 302 to the caller for evaluation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch302WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.patch302WithResponse(requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post303(RequestOptions requestOptions) {
        this.serviceClient.post303(requestOptions);
    }

    /**
     * Post true Boolean value in request returns 303. This request should be automatically redirected usign a get,
     * ultimately returning a 200 status code.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post303WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.post303WithResponse(requestOptions, context);
    }

    /** Redirect with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head307(RequestOptions requestOptions) {
        this.serviceClient.head307(requestOptions);
    }

    /** Redirect with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head307WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head307WithResponse(requestOptions, context);
    }

    /** Redirect get with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get307(RequestOptions requestOptions) {
        this.serviceClient.get307(requestOptions);
    }

    /** Redirect get with 307, resulting in a 200 success. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get307WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get307WithResponse(requestOptions, context);
    }

    /** Put redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put307(RequestOptions requestOptions) {
        this.serviceClient.put307(requestOptions);
    }

    /** Put redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put307WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put307WithResponse(requestOptions, context);
    }

    /** Patch redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch307(RequestOptions requestOptions) {
        this.serviceClient.patch307(requestOptions);
    }

    /** Patch redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch307WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.patch307WithResponse(requestOptions, context);
    }

    /** Post redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post307(RequestOptions requestOptions) {
        this.serviceClient.post307(requestOptions);
    }

    /** Post redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post307WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.post307WithResponse(requestOptions, context);
    }

    /** Delete redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete307(RequestOptions requestOptions) {
        this.serviceClient.delete307(requestOptions);
    }

    /** Delete redirected with 307, resulting in a 200 after redirect. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete307WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.delete307WithResponse(requestOptions, context);
    }
}
