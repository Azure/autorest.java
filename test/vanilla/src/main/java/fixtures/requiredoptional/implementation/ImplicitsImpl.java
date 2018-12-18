/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.requiredoptional.implementation;

import com.microsoft.rest.v2.BodyResponse;
import com.microsoft.rest.v2.RestProxy;
import com.microsoft.rest.v2.ServiceCallback;
import com.microsoft.rest.v2.ServiceFuture;
import com.microsoft.rest.v2.VoidResponse;
import com.microsoft.rest.v2.annotations.BodyParam;
import com.microsoft.rest.v2.annotations.ExpectedResponses;
import com.microsoft.rest.v2.annotations.GET;
import com.microsoft.rest.v2.annotations.HeaderParam;
import com.microsoft.rest.v2.annotations.Host;
import com.microsoft.rest.v2.annotations.PathParam;
import com.microsoft.rest.v2.annotations.PUT;
import com.microsoft.rest.v2.annotations.QueryParam;
import com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType;
import fixtures.requiredoptional.Implicits;
import fixtures.requiredoptional.models.Error;
import fixtures.requiredoptional.models.ErrorException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * An instance of this class provides access to all the operations defined in
 * Implicits.
 */
public final class ImplicitsImpl implements Implicits {
    /**
     * The proxy service used to perform REST calls.
     */
    private ImplicitsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestRequiredOptionalTestServiceImpl client;

    /**
     * Initializes an instance of ImplicitsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public ImplicitsImpl(AutoRestRequiredOptionalTestServiceImpl client) {
        this.service = RestProxy.create(ImplicitsService.class, client);
        this.client = client;
    }

    /**
     * The interface defining all the services for Implicits to be used by the
     * proxy service to perform REST calls.
     */
    @Host("http://localhost:3000")
    private interface ImplicitsService {
        @GET("reqopt/implicit/required/path/{pathParameter}")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<Error>> getRequiredPath(@PathParam("pathParameter") String pathParameter);

        @PUT("reqopt/implicit/optional/query")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<VoidResponse> putOptionalQuery(@QueryParam("queryParameter") String queryParameter);

        @PUT("reqopt/implicit/optional/header")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<VoidResponse> putOptionalHeader(@HeaderParam("queryParameter") String queryParameter);

        @PUT("reqopt/implicit/optional/body")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<VoidResponse> putOptionalBody(@BodyParam("application/json; charset=utf-8") String bodyParameter);

        @GET("reqopt/global/required/path/{required-global-path}")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<Error>> getRequiredGlobalPath(@PathParam("required-global-path") String requiredGlobalPath);

        @GET("reqopt/global/required/query")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<Error>> getRequiredGlobalQuery(@QueryParam("required-global-query") String requiredGlobalQuery);

        @GET("reqopt/global/optional/query")
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<Error>> getOptionalGlobalQuery(@QueryParam("optional-global-query") Integer optionalGlobalQuery);
    }

    /**
     * Test implicitly required path parameter.
     *
     * @param pathParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the Error object if successful.
     */
    public Error getRequiredPath(@NonNull String pathParameter) {
        return getRequiredPathAsync(pathParameter).blockingGet();
    }

    /**
     * Test implicitly required path parameter.
     *
     * @param pathParameter the String value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Error> getRequiredPathAsync(@NonNull String pathParameter, ServiceCallback<Error> serviceCallback) {
        return ServiceFuture.fromBody(getRequiredPathAsync(pathParameter), serviceCallback);
    }

    /**
     * Test implicitly required path parameter.
     *
     * @param pathParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<Error>> getRequiredPathWithRestResponseAsync(@NonNull String pathParameter) {
        if (pathParameter == null) {
            throw new IllegalArgumentException("Parameter pathParameter is required and cannot be null.");
        }
        return service.getRequiredPath(pathParameter);
    }

    /**
     * Test implicitly required path parameter.
     *
     * @param pathParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<Error> getRequiredPathAsync(@NonNull String pathParameter) {
        return getRequiredPathWithRestResponseAsync(pathParameter)
            .flatMapMaybe((BodyResponse<Error> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putOptionalQuery() {
        putOptionalQueryAsync().blockingAwait();
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putOptionalQueryAsync(ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putOptionalQueryAsync(), serviceCallback);
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putOptionalQueryWithRestResponseAsync() {
        final String queryParameter = null;
        return service.putOptionalQuery(queryParameter);
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putOptionalQueryAsync() {
        return putOptionalQueryWithRestResponseAsync()
            .toCompletable();
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @param queryParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putOptionalQuery(String queryParameter) {
        putOptionalQueryAsync(queryParameter).blockingAwait();
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @param queryParameter the String value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putOptionalQueryAsync(String queryParameter, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putOptionalQueryAsync(queryParameter), serviceCallback);
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @param queryParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putOptionalQueryWithRestResponseAsync(String queryParameter) {
        return service.putOptionalQuery(queryParameter);
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @param queryParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putOptionalQueryAsync(String queryParameter) {
        return putOptionalQueryWithRestResponseAsync(queryParameter)
            .toCompletable();
    }

    /**
     * Test implicitly optional header parameter.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putOptionalHeader() {
        putOptionalHeaderAsync().blockingAwait();
    }

    /**
     * Test implicitly optional header parameter.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putOptionalHeaderAsync(ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putOptionalHeaderAsync(), serviceCallback);
    }

    /**
     * Test implicitly optional header parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putOptionalHeaderWithRestResponseAsync() {
        final String queryParameter = null;
        return service.putOptionalHeader(queryParameter);
    }

    /**
     * Test implicitly optional header parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putOptionalHeaderAsync() {
        return putOptionalHeaderWithRestResponseAsync()
            .toCompletable();
    }

    /**
     * Test implicitly optional header parameter.
     *
     * @param queryParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putOptionalHeader(String queryParameter) {
        putOptionalHeaderAsync(queryParameter).blockingAwait();
    }

    /**
     * Test implicitly optional header parameter.
     *
     * @param queryParameter the String value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putOptionalHeaderAsync(String queryParameter, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putOptionalHeaderAsync(queryParameter), serviceCallback);
    }

    /**
     * Test implicitly optional header parameter.
     *
     * @param queryParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putOptionalHeaderWithRestResponseAsync(String queryParameter) {
        return service.putOptionalHeader(queryParameter);
    }

    /**
     * Test implicitly optional header parameter.
     *
     * @param queryParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putOptionalHeaderAsync(String queryParameter) {
        return putOptionalHeaderWithRestResponseAsync(queryParameter)
            .toCompletable();
    }

    /**
     * Test implicitly optional body parameter.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putOptionalBody() {
        putOptionalBodyAsync().blockingAwait();
    }

    /**
     * Test implicitly optional body parameter.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putOptionalBodyAsync(ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putOptionalBodyAsync(), serviceCallback);
    }

    /**
     * Test implicitly optional body parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putOptionalBodyWithRestResponseAsync() {
        final String bodyParameter = null;
        return service.putOptionalBody(bodyParameter);
    }

    /**
     * Test implicitly optional body parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putOptionalBodyAsync() {
        return putOptionalBodyWithRestResponseAsync()
            .toCompletable();
    }

    /**
     * Test implicitly optional body parameter.
     *
     * @param bodyParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putOptionalBody(String bodyParameter) {
        putOptionalBodyAsync(bodyParameter).blockingAwait();
    }

    /**
     * Test implicitly optional body parameter.
     *
     * @param bodyParameter the String value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putOptionalBodyAsync(String bodyParameter, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putOptionalBodyAsync(bodyParameter), serviceCallback);
    }

    /**
     * Test implicitly optional body parameter.
     *
     * @param bodyParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putOptionalBodyWithRestResponseAsync(String bodyParameter) {
        return service.putOptionalBody(bodyParameter);
    }

    /**
     * Test implicitly optional body parameter.
     *
     * @param bodyParameter the String value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putOptionalBodyAsync(String bodyParameter) {
        return putOptionalBodyWithRestResponseAsync(bodyParameter)
            .toCompletable();
    }

    /**
     * Test implicitly required path parameter.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the Error object if successful.
     */
    public Error getRequiredGlobalPath() {
        return getRequiredGlobalPathAsync().blockingGet();
    }

    /**
     * Test implicitly required path parameter.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Error> getRequiredGlobalPathAsync(ServiceCallback<Error> serviceCallback) {
        return ServiceFuture.fromBody(getRequiredGlobalPathAsync(), serviceCallback);
    }

    /**
     * Test implicitly required path parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<Error>> getRequiredGlobalPathWithRestResponseAsync() {
        if (this.client.requiredGlobalPath() == null) {
            throw new IllegalArgumentException("Parameter this.client.requiredGlobalPath() is required and cannot be null.");
        }
        return service.getRequiredGlobalPath(this.client.requiredGlobalPath());
    }

    /**
     * Test implicitly required path parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<Error> getRequiredGlobalPathAsync() {
        return getRequiredGlobalPathWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<Error> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Test implicitly required query parameter.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the Error object if successful.
     */
    public Error getRequiredGlobalQuery() {
        return getRequiredGlobalQueryAsync().blockingGet();
    }

    /**
     * Test implicitly required query parameter.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Error> getRequiredGlobalQueryAsync(ServiceCallback<Error> serviceCallback) {
        return ServiceFuture.fromBody(getRequiredGlobalQueryAsync(), serviceCallback);
    }

    /**
     * Test implicitly required query parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<Error>> getRequiredGlobalQueryWithRestResponseAsync() {
        if (this.client.requiredGlobalQuery() == null) {
            throw new IllegalArgumentException("Parameter this.client.requiredGlobalQuery() is required and cannot be null.");
        }
        return service.getRequiredGlobalQuery(this.client.requiredGlobalQuery());
    }

    /**
     * Test implicitly required query parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<Error> getRequiredGlobalQueryAsync() {
        return getRequiredGlobalQueryWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<Error> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the Error object if successful.
     */
    public Error getOptionalGlobalQuery() {
        return getOptionalGlobalQueryAsync().blockingGet();
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Error> getOptionalGlobalQueryAsync(ServiceCallback<Error> serviceCallback) {
        return ServiceFuture.fromBody(getOptionalGlobalQueryAsync(), serviceCallback);
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<Error>> getOptionalGlobalQueryWithRestResponseAsync() {
        return service.getOptionalGlobalQuery(this.client.optionalGlobalQuery());
    }

    /**
     * Test implicitly optional query parameter.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<Error> getOptionalGlobalQueryAsync() {
        return getOptionalGlobalQueryWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<Error> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }
}
