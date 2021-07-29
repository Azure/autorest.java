package fixtures.httpinfrastructure;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.httpinfrastructure.implementation.HttpSuccessImpl;

/** Initializes a new instance of the synchronous AutoRestHttpInfrastructureTestService type. */
@ServiceClient(builder = AutoRestHttpInfrastructureTestServiceBuilder.class)
public final class HttpSuccessClient {
    private final HttpSuccessImpl serviceClient;

    /**
     * Initializes an instance of HttpSuccess client.
     *
     * @param serviceClient the service client implementation.
     */
    HttpSuccessClient(HttpSuccessImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Return 200 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head200(RequestOptions requestOptions) {
        this.serviceClient.head200(requestOptions);
    }

    /**
     * Return 200 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head200WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head200WithResponse(requestOptions, context);
    }

    /**
     * Get 200 success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return 200 success.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean get200(RequestOptions requestOptions) {
        return this.serviceClient.get200(requestOptions);
    }

    /**
     * Get 200 success.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return 200 success.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> get200WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.get200WithResponse(requestOptions, context);
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put200(RequestOptions requestOptions) {
        this.serviceClient.put200(requestOptions);
    }

    /**
     * Put boolean value true returning 200 success.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put200WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put200WithResponse(requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch200(RequestOptions requestOptions) {
        this.serviceClient.patch200(requestOptions);
    }

    /**
     * Patch true Boolean value in request returning 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch200WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.patch200WithResponse(requestOptions, context);
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post200(RequestOptions requestOptions) {
        this.serviceClient.post200(requestOptions);
    }

    /**
     * Post bollean value true in request that returns a 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post200WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.post200WithResponse(requestOptions, context);
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete200(RequestOptions requestOptions) {
        this.serviceClient.delete200(requestOptions);
    }

    /**
     * Delete simple boolean value true returns 200.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete200WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.delete200WithResponse(requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put201(RequestOptions requestOptions) {
        this.serviceClient.put201(requestOptions);
    }

    /**
     * Put true Boolean value in request returns 201.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put201WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put201WithResponse(requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post201(RequestOptions requestOptions) {
        this.serviceClient.post201(requestOptions);
    }

    /**
     * Post true Boolean value in request returns 201 (Created).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post201WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.post201WithResponse(requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put202(RequestOptions requestOptions) {
        this.serviceClient.put202(requestOptions);
    }

    /**
     * Put true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put202WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put202WithResponse(requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch202(RequestOptions requestOptions) {
        this.serviceClient.patch202(requestOptions);
    }

    /**
     * Patch true Boolean value in request returns 202.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch202WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.patch202WithResponse(requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post202(RequestOptions requestOptions) {
        this.serviceClient.post202(requestOptions);
    }

    /**
     * Post true Boolean value in request returns 202 (Accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post202WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.post202WithResponse(requestOptions, context);
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete202(RequestOptions requestOptions) {
        this.serviceClient.delete202(requestOptions);
    }

    /**
     * Delete true Boolean value in request returns 202 (accepted).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete202WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.delete202WithResponse(requestOptions, context);
    }

    /**
     * Return 204 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void head204(RequestOptions requestOptions) {
        this.serviceClient.head204(requestOptions);
    }

    /**
     * Return 204 status code if successful.
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> head204WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head204WithResponse(requestOptions, context);
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void put204(RequestOptions requestOptions) {
        this.serviceClient.put204(requestOptions);
    }

    /**
     * Put true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> put204WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.put204WithResponse(requestOptions, context);
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void patch204(RequestOptions requestOptions) {
        this.serviceClient.patch204(requestOptions);
    }

    /**
     * Patch true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> patch204WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.patch204WithResponse(requestOptions, context);
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void post204(RequestOptions requestOptions) {
        this.serviceClient.post204(requestOptions);
    }

    /**
     * Post true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> post204WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.post204WithResponse(requestOptions, context);
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete204(RequestOptions requestOptions) {
        this.serviceClient.delete204(requestOptions);
    }

    /**
     * Delete true Boolean value in request returns 204 (no content).
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> delete204WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.delete204WithResponse(requestOptions, context);
    }

    /**
     * Return 404 status code.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @return whether resource exists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public boolean head404(RequestOptions requestOptions) {
        return this.serviceClient.head404(requestOptions);
    }

    /**
     * Return 404 status code.
     *
     * <p><strong>Response Body Schema</strong>
     *
     * <pre>{@code
     * boolean
     * }</pre>
     *
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @param context The context to associate with this operation.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Boolean> head404WithResponse(RequestOptions requestOptions, Context context) {
        return this.serviceClient.head404WithResponse(requestOptions, context);
    }
}
