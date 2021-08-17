package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.httpinfrastructure.implementation.HttpRetriesImpl;

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class)
public final class HttpRetryClient {
    private final HttpRetriesImpl serviceClient;

    /**
     * Initializes an instance of HttpRetries client.
     *
     * @param serviceClient the service client implementation.
     */
    HttpRetryClient(HttpRetriesImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Return 408 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head408WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head408WithResponse(requestOptions, context);
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put500WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put500WithResponse(requestOptions, context);
    }

    /**
     * Return 500 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch500WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.patch500WithResponse(requestOptions, context);
    }

    /**
     * Return 502 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get502WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get502WithResponse(requestOptions, context);
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post503WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.post503WithResponse(requestOptions, context);
    }

    /**
     * Return 503 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete503WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.delete503WithResponse(requestOptions, context);
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put504WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put504WithResponse(requestOptions, context);
    }

    /**
     * Return 504 status code, then 200 after retry.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch504WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.patch504WithResponse(requestOptions, context);
    }
}
