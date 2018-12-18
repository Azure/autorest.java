/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.bodydatetimerfc1123.implementation;

import com.microsoft.rest.v2.BodyResponse;
import com.microsoft.rest.v2.DateTimeRfc1123;
import com.microsoft.rest.v2.RestProxy;
import com.microsoft.rest.v2.ServiceCallback;
import com.microsoft.rest.v2.ServiceFuture;
import com.microsoft.rest.v2.VoidResponse;
import com.microsoft.rest.v2.annotations.BodyParam;
import com.microsoft.rest.v2.annotations.ExpectedResponses;
import com.microsoft.rest.v2.annotations.GET;
import com.microsoft.rest.v2.annotations.Host;
import com.microsoft.rest.v2.annotations.PUT;
import com.microsoft.rest.v2.annotations.ReturnValueWireType;
import com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType;
import fixtures.bodydatetimerfc1123.Datetimerfc1123s;
import fixtures.bodydatetimerfc1123.models.ErrorException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import java.time.OffsetDateTime;

/**
 * An instance of this class provides access to all the operations defined in
 * Datetimerfc1123s.
 */
public final class Datetimerfc1123sImpl implements Datetimerfc1123s {
    /**
     * The proxy service used to perform REST calls.
     */
    private Datetimerfc1123sService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestRFC1123DateTimeTestServiceImpl client;

    /**
     * Initializes an instance of Datetimerfc1123sImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public Datetimerfc1123sImpl(AutoRestRFC1123DateTimeTestServiceImpl client) {
        this.service = RestProxy.create(Datetimerfc1123sService.class, client);
        this.client = client;
    }

    /**
     * The interface defining all the services for Datetimerfc1123s to be used
     * by the proxy service to perform REST calls.
     */
    @Host("http://localhost:3000")
    private interface Datetimerfc1123sService {
        @GET("datetimerfc1123/null")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<OffsetDateTime>> getNull();

        @GET("datetimerfc1123/invalid")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<OffsetDateTime>> getInvalid();

        @GET("datetimerfc1123/overflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<OffsetDateTime>> getOverflow();

        @GET("datetimerfc1123/underflow")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<OffsetDateTime>> getUnderflow();

        @PUT("datetimerfc1123/max")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<VoidResponse> putUtcMaxDateTime(@BodyParam("application/json; charset=utf-8") DateTimeRfc1123 datetimeBody);

        @GET("datetimerfc1123/max/lowercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<OffsetDateTime>> getUtcLowercaseMaxDateTime();

        @GET("datetimerfc1123/max/uppercase")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<OffsetDateTime>> getUtcUppercaseMaxDateTime();

        @PUT("datetimerfc1123/min")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<VoidResponse> putUtcMinDateTime(@BodyParam("application/json; charset=utf-8") DateTimeRfc1123 datetimeBody);

        @GET("datetimerfc1123/min")
        @ExpectedResponses({200})
        @ReturnValueWireType(DateTimeRfc1123.class)
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<OffsetDateTime>> getUtcMinDateTime();
    }

    /**
     * Get null datetime value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the OffsetDateTime object if successful.
     */
    public OffsetDateTime getNull() {
        return getNullAsync().blockingGet();
    }

    /**
     * Get null datetime value.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<OffsetDateTime> getNullAsync(ServiceCallback<OffsetDateTime> serviceCallback) {
        return ServiceFuture.fromBody(getNullAsync(), serviceCallback);
    }

    /**
     * Get null datetime value.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<OffsetDateTime>> getNullWithRestResponseAsync() {
        return service.getNull();
    }

    /**
     * Get null datetime value.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<OffsetDateTime> getNullAsync() {
        return getNullWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<OffsetDateTime> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Get invalid datetime value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the OffsetDateTime object if successful.
     */
    public OffsetDateTime getInvalid() {
        return getInvalidAsync().blockingGet();
    }

    /**
     * Get invalid datetime value.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<OffsetDateTime> getInvalidAsync(ServiceCallback<OffsetDateTime> serviceCallback) {
        return ServiceFuture.fromBody(getInvalidAsync(), serviceCallback);
    }

    /**
     * Get invalid datetime value.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<OffsetDateTime>> getInvalidWithRestResponseAsync() {
        return service.getInvalid();
    }

    /**
     * Get invalid datetime value.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<OffsetDateTime> getInvalidAsync() {
        return getInvalidWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<OffsetDateTime> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Get overflow datetime value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the OffsetDateTime object if successful.
     */
    public OffsetDateTime getOverflow() {
        return getOverflowAsync().blockingGet();
    }

    /**
     * Get overflow datetime value.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<OffsetDateTime> getOverflowAsync(ServiceCallback<OffsetDateTime> serviceCallback) {
        return ServiceFuture.fromBody(getOverflowAsync(), serviceCallback);
    }

    /**
     * Get overflow datetime value.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<OffsetDateTime>> getOverflowWithRestResponseAsync() {
        return service.getOverflow();
    }

    /**
     * Get overflow datetime value.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<OffsetDateTime> getOverflowAsync() {
        return getOverflowWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<OffsetDateTime> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Get underflow datetime value.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the OffsetDateTime object if successful.
     */
    public OffsetDateTime getUnderflow() {
        return getUnderflowAsync().blockingGet();
    }

    /**
     * Get underflow datetime value.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<OffsetDateTime> getUnderflowAsync(ServiceCallback<OffsetDateTime> serviceCallback) {
        return ServiceFuture.fromBody(getUnderflowAsync(), serviceCallback);
    }

    /**
     * Get underflow datetime value.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<OffsetDateTime>> getUnderflowWithRestResponseAsync() {
        return service.getUnderflow();
    }

    /**
     * Get underflow datetime value.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<OffsetDateTime> getUnderflowAsync() {
        return getUnderflowWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<OffsetDateTime> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
     *
     * @param datetimeBody the OffsetDateTime value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putUtcMaxDateTime(@NonNull OffsetDateTime datetimeBody) {
        putUtcMaxDateTimeAsync(datetimeBody).blockingAwait();
    }

    /**
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
     *
     * @param datetimeBody the OffsetDateTime value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putUtcMaxDateTimeAsync(@NonNull OffsetDateTime datetimeBody, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putUtcMaxDateTimeAsync(datetimeBody), serviceCallback);
    }

    /**
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
     *
     * @param datetimeBody the OffsetDateTime value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putUtcMaxDateTimeWithRestResponseAsync(@NonNull OffsetDateTime datetimeBody) {
        if (datetimeBody == null) {
            throw new IllegalArgumentException("Parameter datetimeBody is required and cannot be null.");
        }
        DateTimeRfc1123 datetimeBodyConverted = new DateTimeRfc1123(datetimeBody);
        return service.putUtcMaxDateTime(datetimeBodyConverted);
    }

    /**
     * Put max datetime value Fri, 31 Dec 9999 23:59:59 GMT.
     *
     * @param datetimeBody the OffsetDateTime value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putUtcMaxDateTimeAsync(@NonNull OffsetDateTime datetimeBody) {
        return putUtcMaxDateTimeWithRestResponseAsync(datetimeBody)
            .toCompletable();
    }

    /**
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the OffsetDateTime object if successful.
     */
    public OffsetDateTime getUtcLowercaseMaxDateTime() {
        return getUtcLowercaseMaxDateTimeAsync().blockingGet();
    }

    /**
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<OffsetDateTime> getUtcLowercaseMaxDateTimeAsync(ServiceCallback<OffsetDateTime> serviceCallback) {
        return ServiceFuture.fromBody(getUtcLowercaseMaxDateTimeAsync(), serviceCallback);
    }

    /**
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<OffsetDateTime>> getUtcLowercaseMaxDateTimeWithRestResponseAsync() {
        return service.getUtcLowercaseMaxDateTime();
    }

    /**
     * Get max datetime value fri, 31 dec 9999 23:59:59 gmt.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<OffsetDateTime> getUtcLowercaseMaxDateTimeAsync() {
        return getUtcLowercaseMaxDateTimeWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<OffsetDateTime> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the OffsetDateTime object if successful.
     */
    public OffsetDateTime getUtcUppercaseMaxDateTime() {
        return getUtcUppercaseMaxDateTimeAsync().blockingGet();
    }

    /**
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<OffsetDateTime> getUtcUppercaseMaxDateTimeAsync(ServiceCallback<OffsetDateTime> serviceCallback) {
        return ServiceFuture.fromBody(getUtcUppercaseMaxDateTimeAsync(), serviceCallback);
    }

    /**
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<OffsetDateTime>> getUtcUppercaseMaxDateTimeWithRestResponseAsync() {
        return service.getUtcUppercaseMaxDateTime();
    }

    /**
     * Get max datetime value FRI, 31 DEC 9999 23:59:59 GMT.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<OffsetDateTime> getUtcUppercaseMaxDateTimeAsync() {
        return getUtcUppercaseMaxDateTimeWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<OffsetDateTime> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     *
     * @param datetimeBody the OffsetDateTime value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putUtcMinDateTime(@NonNull OffsetDateTime datetimeBody) {
        putUtcMinDateTimeAsync(datetimeBody).blockingAwait();
    }

    /**
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     *
     * @param datetimeBody the OffsetDateTime value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putUtcMinDateTimeAsync(@NonNull OffsetDateTime datetimeBody, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putUtcMinDateTimeAsync(datetimeBody), serviceCallback);
    }

    /**
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     *
     * @param datetimeBody the OffsetDateTime value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putUtcMinDateTimeWithRestResponseAsync(@NonNull OffsetDateTime datetimeBody) {
        if (datetimeBody == null) {
            throw new IllegalArgumentException("Parameter datetimeBody is required and cannot be null.");
        }
        DateTimeRfc1123 datetimeBodyConverted = new DateTimeRfc1123(datetimeBody);
        return service.putUtcMinDateTime(datetimeBodyConverted);
    }

    /**
     * Put min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     *
     * @param datetimeBody the OffsetDateTime value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putUtcMinDateTimeAsync(@NonNull OffsetDateTime datetimeBody) {
        return putUtcMinDateTimeWithRestResponseAsync(datetimeBody)
            .toCompletable();
    }

    /**
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the OffsetDateTime object if successful.
     */
    public OffsetDateTime getUtcMinDateTime() {
        return getUtcMinDateTimeAsync().blockingGet();
    }

    /**
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<OffsetDateTime> getUtcMinDateTimeAsync(ServiceCallback<OffsetDateTime> serviceCallback) {
        return ServiceFuture.fromBody(getUtcMinDateTimeAsync(), serviceCallback);
    }

    /**
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<OffsetDateTime>> getUtcMinDateTimeWithRestResponseAsync() {
        return service.getUtcMinDateTime();
    }

    /**
     * Get min datetime value Mon, 1 Jan 0001 00:00:00 GMT.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<OffsetDateTime> getUtcMinDateTimeAsync() {
        return getUtcMinDateTimeWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<OffsetDateTime> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }
}
