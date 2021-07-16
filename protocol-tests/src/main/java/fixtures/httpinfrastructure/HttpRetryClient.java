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

    /** Return 408 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head408(RequestOptions requestOptions) {
        this.serviceClient.head408(requestOptions);
    }

    /** Return 408 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head408WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head408WithResponse(requestOptions, context);
    }

    /** Return 500 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put500(RequestOptions requestOptions) {
        this.serviceClient.put500(requestOptions);
    }

    /** Return 500 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put500WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put500WithResponse(requestOptions, context);
    }

    /** Return 500 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch500(RequestOptions requestOptions) {
        this.serviceClient.patch500(requestOptions);
    }

    /** Return 500 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch500WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.patch500WithResponse(requestOptions, context);
    }

    /** Return 502 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void get502(RequestOptions requestOptions) {
        this.serviceClient.get502(requestOptions);
    }

    /** Return 502 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> get502WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get502WithResponse(requestOptions, context);
    }

    /** Return 503 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post503(RequestOptions requestOptions) {
        this.serviceClient.post503(requestOptions);
    }

    /** Return 503 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post503WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.post503WithResponse(requestOptions, context);
    }

    /** Return 503 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete503(RequestOptions requestOptions) {
        this.serviceClient.delete503(requestOptions);
    }

    /** Return 503 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete503WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.delete503WithResponse(requestOptions, context);
    }

    /** Return 504 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put504(RequestOptions requestOptions) {
        this.serviceClient.put504(requestOptions);
    }

    /** Return 504 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put504WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put504WithResponse(requestOptions, context);
    }

    /** Return 504 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch504(RequestOptions requestOptions) {
        this.serviceClient.patch504(requestOptions);
    }

    /** Return 504 status code, then 200 after retry. */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch504WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.patch504WithResponse(requestOptions, context);
    }
}
