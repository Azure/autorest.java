/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.xml.implementation;

import com.microsoft.rest.v2.RestProxy;
import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.ServiceCallback;
import com.microsoft.rest.v2.ServiceFuture;
import com.microsoft.rest.v2.Validator;
import com.microsoft.rest.v2.annotations.BodyParam;
import com.microsoft.rest.v2.annotations.ExpectedResponses;
import com.microsoft.rest.v2.annotations.GET;
import com.microsoft.rest.v2.annotations.Host;
import com.microsoft.rest.v2.annotations.PUT;
import com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType;
import fixtures.xml.Xmls;
import fixtures.xml.models.AppleBarrel;
import fixtures.xml.models.Banana;
import fixtures.xml.models.ErrorException;
import fixtures.xml.models.Slideshow;
import fixtures.xml.models.XmlGetHeadersHeaders;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

/**
 * An instance of this class provides access to all the operations defined in
 * Xmls.
 */
public final class XmlsImpl implements Xmls {
    /**
     * The proxy service used to perform REST calls.
     */
    private XmlsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestSwaggerBATXMLServiceImpl client;

    /**
     * Initializes an instance of XmlsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public XmlsImpl(AutoRestSwaggerBATXMLServiceImpl client) {
        this.service = RestProxy.create(XmlsService.class, client);
        this.client = client;
    }

    /**
     * The interface defining all the services for Xmls to be used by the proxy
     * service to perform REST calls.
     */
    @Host("http://localhost")
    private interface XmlsService {
        @GET("xml/simple")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<RestResponse<Void, Slideshow>> getSimple();

        @PUT("xml/simple")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<RestResponse<Void, Void>> putSimple(@BodyParam("application/xml; charset=utf-8") Slideshow wrappedLists);

        @GET("xml/wrapped-lists")
        @ExpectedResponses({200})
        Single<RestResponse<Void, AppleBarrel>> getWrappedLists();

        @PUT("xml/wrapped-lists")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<RestResponse<Void, Void>> putWrappedLists(@BodyParam("application/xml; charset=utf-8") AppleBarrel wrappedLists);

        @GET("xml/headers")
        @ExpectedResponses({200})
        Single<RestResponse<XmlGetHeadersHeaders, Void>> getHeaders();

        @GET("xml/empty-list")
        @ExpectedResponses({200})
        Single<RestResponse<Void, Slideshow>> getEmptyList();

        @GET("xml/empty-wrapped-lists")
        @ExpectedResponses({200})
        Single<RestResponse<Void, AppleBarrel>> getEmptyWrappedLists();

        @GET("xml/root-list")
        @ExpectedResponses({200})
        Single<RestResponse<Void, List<Banana>>> getRootList();

        @PUT("xml/root-list")
        @ExpectedResponses({201})
        Single<RestResponse<Void, Void>> putRootList(@BodyParam("application/xml; charset=utf-8") BananasWrapper bananas);

        @GET("xml/empty-root-list")
        @ExpectedResponses({200})
        Single<RestResponse<Void, List<Banana>>> getEmptyRootList();

        @PUT("xml/empty-root-list")
        @ExpectedResponses({201})
        Single<RestResponse<Void, Void>> putEmptyRootList(@BodyParam("application/xml; charset=utf-8") BananasWrapper bananas);

        @GET("xml/empty-child-element")
        @ExpectedResponses({200})
        Single<RestResponse<Void, Banana>> getEmptyChildElement();

        @PUT("xml/empty-child-element")
        @ExpectedResponses({201})
        Single<RestResponse<Void, Void>> putEmptyChildElement(@BodyParam("application/xml; charset=utf-8") Banana banana);
    }

    /**
     * Get a simple XML document.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the Slideshow object if successful.
     */
    public Slideshow getSimple() {
        return getSimpleAsync().blockingGet();
    }

    /**
     * Get a simple XML document.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Slideshow> getSimpleAsync(ServiceCallback<Slideshow> serviceCallback) {
        return ServiceFuture.fromBody(getSimpleAsync(), serviceCallback);
    }

    /**
     * Get a simple XML document.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, Slideshow>> getSimpleWithRestResponseAsync() {
        return service.getSimple();
    }

    /**
     * Get a simple XML document.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<Slideshow> getSimpleAsync() {
        return getSimpleWithRestResponseAsync()
            .flatMapMaybe(new Function<RestResponse<Void, Slideshow>, Maybe<Slideshow>>() {
                public Maybe<Slideshow> apply(RestResponse<Void, Slideshow> restResponse) {
                    if (restResponse.body() == null) {
                        return Maybe.empty();
                    } else {
                        return Maybe.just(restResponse.body());
                    }
                }
            });
    }

    /**
     * Put a simple XML document.
     *
     * @param wrappedLists the Slideshow value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putSimple(@NonNull Slideshow wrappedLists) {
        putSimpleAsync(wrappedLists).blockingAwait();
    }

    /**
     * Put a simple XML document.
     *
     * @param wrappedLists the Slideshow value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putSimpleAsync(@NonNull Slideshow wrappedLists, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putSimpleAsync(wrappedLists), serviceCallback);
    }

    /**
     * Put a simple XML document.
     *
     * @param wrappedLists the Slideshow value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, Void>> putSimpleWithRestResponseAsync(@NonNull Slideshow wrappedLists) {
        if (wrappedLists == null) {
            throw new IllegalArgumentException("Parameter wrappedLists is required and cannot be null.");
        }
        Validator.validate(wrappedLists);
        return service.putSimple(wrappedLists);
    }

    /**
     * Put a simple XML document.
     *
     * @param wrappedLists the Slideshow value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putSimpleAsync(@NonNull Slideshow wrappedLists) {
        return putSimpleWithRestResponseAsync(wrappedLists)
            .toCompletable();
    }

    /**
     * Get an XML document with multiple wrapped lists.
     *
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the AppleBarrel object if successful.
     */
    public AppleBarrel getWrappedLists() {
        return getWrappedListsAsync().blockingGet();
    }

    /**
     * Get an XML document with multiple wrapped lists.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<AppleBarrel> getWrappedListsAsync(ServiceCallback<AppleBarrel> serviceCallback) {
        return ServiceFuture.fromBody(getWrappedListsAsync(), serviceCallback);
    }

    /**
     * Get an XML document with multiple wrapped lists.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, AppleBarrel>> getWrappedListsWithRestResponseAsync() {
        return service.getWrappedLists();
    }

    /**
     * Get an XML document with multiple wrapped lists.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<AppleBarrel> getWrappedListsAsync() {
        return getWrappedListsWithRestResponseAsync()
            .flatMapMaybe(new Function<RestResponse<Void, AppleBarrel>, Maybe<AppleBarrel>>() {
                public Maybe<AppleBarrel> apply(RestResponse<Void, AppleBarrel> restResponse) {
                    if (restResponse.body() == null) {
                        return Maybe.empty();
                    } else {
                        return Maybe.just(restResponse.body());
                    }
                }
            });
    }

    /**
     * Put an XML document with multiple wrapped lists.
     *
     * @param wrappedLists the AppleBarrel value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putWrappedLists(@NonNull AppleBarrel wrappedLists) {
        putWrappedListsAsync(wrappedLists).blockingAwait();
    }

    /**
     * Put an XML document with multiple wrapped lists.
     *
     * @param wrappedLists the AppleBarrel value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putWrappedListsAsync(@NonNull AppleBarrel wrappedLists, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putWrappedListsAsync(wrappedLists), serviceCallback);
    }

    /**
     * Put an XML document with multiple wrapped lists.
     *
     * @param wrappedLists the AppleBarrel value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, Void>> putWrappedListsWithRestResponseAsync(@NonNull AppleBarrel wrappedLists) {
        if (wrappedLists == null) {
            throw new IllegalArgumentException("Parameter wrappedLists is required and cannot be null.");
        }
        Validator.validate(wrappedLists);
        return service.putWrappedLists(wrappedLists);
    }

    /**
     * Put an XML document with multiple wrapped lists.
     *
     * @param wrappedLists the AppleBarrel value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putWrappedListsAsync(@NonNull AppleBarrel wrappedLists) {
        return putWrappedListsWithRestResponseAsync(wrappedLists)
            .toCompletable();
    }

    /**
     * Get strongly-typed response headers.
     *
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void getHeaders() {
        getHeadersAsync().blockingAwait();
    }

    /**
     * Get strongly-typed response headers.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> getHeadersAsync(ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(getHeadersAsync(), serviceCallback);
    }

    /**
     * Get strongly-typed response headers.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<XmlGetHeadersHeaders, Void>> getHeadersWithRestResponseAsync() {
        return service.getHeaders();
    }

    /**
     * Get strongly-typed response headers.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Completable getHeadersAsync() {
        return getHeadersWithRestResponseAsync()
            .toCompletable();
    }

    /**
     * Get an empty list.
     *
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the Slideshow object if successful.
     */
    public Slideshow getEmptyList() {
        return getEmptyListAsync().blockingGet();
    }

    /**
     * Get an empty list.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Slideshow> getEmptyListAsync(ServiceCallback<Slideshow> serviceCallback) {
        return ServiceFuture.fromBody(getEmptyListAsync(), serviceCallback);
    }

    /**
     * Get an empty list.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, Slideshow>> getEmptyListWithRestResponseAsync() {
        return service.getEmptyList();
    }

    /**
     * Get an empty list.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<Slideshow> getEmptyListAsync() {
        return getEmptyListWithRestResponseAsync()
            .flatMapMaybe(new Function<RestResponse<Void, Slideshow>, Maybe<Slideshow>>() {
                public Maybe<Slideshow> apply(RestResponse<Void, Slideshow> restResponse) {
                    if (restResponse.body() == null) {
                        return Maybe.empty();
                    } else {
                        return Maybe.just(restResponse.body());
                    }
                }
            });
    }

    /**
     * Gets some empty wrapped lists.
     *
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the AppleBarrel object if successful.
     */
    public AppleBarrel getEmptyWrappedLists() {
        return getEmptyWrappedListsAsync().blockingGet();
    }

    /**
     * Gets some empty wrapped lists.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<AppleBarrel> getEmptyWrappedListsAsync(ServiceCallback<AppleBarrel> serviceCallback) {
        return ServiceFuture.fromBody(getEmptyWrappedListsAsync(), serviceCallback);
    }

    /**
     * Gets some empty wrapped lists.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, AppleBarrel>> getEmptyWrappedListsWithRestResponseAsync() {
        return service.getEmptyWrappedLists();
    }

    /**
     * Gets some empty wrapped lists.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<AppleBarrel> getEmptyWrappedListsAsync() {
        return getEmptyWrappedListsWithRestResponseAsync()
            .flatMapMaybe(new Function<RestResponse<Void, AppleBarrel>, Maybe<AppleBarrel>>() {
                public Maybe<AppleBarrel> apply(RestResponse<Void, AppleBarrel> restResponse) {
                    if (restResponse.body() == null) {
                        return Maybe.empty();
                    } else {
                        return Maybe.just(restResponse.body());
                    }
                }
            });
    }

    /**
     * Gets a list as the root element.
     *
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the List&lt;Banana&gt; object if successful.
     */
    public List<Banana> getRootList() {
        return getRootListAsync().blockingGet();
    }

    /**
     * Gets a list as the root element.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<List<Banana>> getRootListAsync(ServiceCallback<List<Banana>> serviceCallback) {
        return ServiceFuture.fromBody(getRootListAsync(), serviceCallback);
    }

    /**
     * Gets a list as the root element.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, List<Banana>>> getRootListWithRestResponseAsync() {
        return service.getRootList();
    }

    /**
     * Gets a list as the root element.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<List<Banana>> getRootListAsync() {
        return getRootListWithRestResponseAsync()
            .flatMapMaybe(new Function<RestResponse<Void, List<Banana>>, Maybe<List<Banana>>>() {
                public Maybe<List<Banana>> apply(RestResponse<Void, List<Banana>> restResponse) {
                    if (restResponse.body() == null) {
                        return Maybe.empty();
                    } else {
                        return Maybe.just(restResponse.body());
                    }
                }
            });
    }

    /**
     * Puts a list as the root element.
     *
     * @param bananas the List&lt;Banana&gt; value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putRootList(@NonNull List<Banana> bananas) {
        putRootListAsync(bananas).blockingAwait();
    }

    /**
     * Puts a list as the root element.
     *
     * @param bananas the List&lt;Banana&gt; value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putRootListAsync(@NonNull List<Banana> bananas, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putRootListAsync(bananas), serviceCallback);
    }

    /**
     * Puts a list as the root element.
     *
     * @param bananas the List&lt;Banana&gt; value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, Void>> putRootListWithRestResponseAsync(@NonNull List<Banana> bananas) {
        if (bananas == null) {
            throw new IllegalArgumentException("Parameter bananas is required and cannot be null.");
        }
        Validator.validate(bananas);
        return service.putRootList(new BananasWrapper(bananas));
    }

    /**
     * Puts a list as the root element.
     *
     * @param bananas the List&lt;Banana&gt; value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putRootListAsync(@NonNull List<Banana> bananas) {
        return putRootListWithRestResponseAsync(bananas)
            .toCompletable();
    }

    /**
     * Gets an empty list as the root element.
     *
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the List&lt;Banana&gt; object if successful.
     */
    public List<Banana> getEmptyRootList() {
        return getEmptyRootListAsync().blockingGet();
    }

    /**
     * Gets an empty list as the root element.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<List<Banana>> getEmptyRootListAsync(ServiceCallback<List<Banana>> serviceCallback) {
        return ServiceFuture.fromBody(getEmptyRootListAsync(), serviceCallback);
    }

    /**
     * Gets an empty list as the root element.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, List<Banana>>> getEmptyRootListWithRestResponseAsync() {
        return service.getEmptyRootList();
    }

    /**
     * Gets an empty list as the root element.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<List<Banana>> getEmptyRootListAsync() {
        return getEmptyRootListWithRestResponseAsync()
            .flatMapMaybe(new Function<RestResponse<Void, List<Banana>>, Maybe<List<Banana>>>() {
                public Maybe<List<Banana>> apply(RestResponse<Void, List<Banana>> restResponse) {
                    if (restResponse.body() == null) {
                        return Maybe.empty();
                    } else {
                        return Maybe.just(restResponse.body());
                    }
                }
            });
    }

    /**
     * Puts an empty list as the root element.
     *
     * @param bananas the List&lt;Banana&gt; value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putEmptyRootList(@NonNull List<Banana> bananas) {
        putEmptyRootListAsync(bananas).blockingAwait();
    }

    /**
     * Puts an empty list as the root element.
     *
     * @param bananas the List&lt;Banana&gt; value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putEmptyRootListAsync(@NonNull List<Banana> bananas, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putEmptyRootListAsync(bananas), serviceCallback);
    }

    /**
     * Puts an empty list as the root element.
     *
     * @param bananas the List&lt;Banana&gt; value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, Void>> putEmptyRootListWithRestResponseAsync(@NonNull List<Banana> bananas) {
        if (bananas == null) {
            throw new IllegalArgumentException("Parameter bananas is required and cannot be null.");
        }
        Validator.validate(bananas);
        return service.putEmptyRootList(new BananasWrapper(bananas));
    }

    /**
     * Puts an empty list as the root element.
     *
     * @param bananas the List&lt;Banana&gt; value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putEmptyRootListAsync(@NonNull List<Banana> bananas) {
        return putEmptyRootListWithRestResponseAsync(bananas)
            .toCompletable();
    }

    /**
     * Gets an XML document with an empty child element.
     *
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the Banana object if successful.
     */
    public Banana getEmptyChildElement() {
        return getEmptyChildElementAsync().blockingGet();
    }

    /**
     * Gets an XML document with an empty child element.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Banana> getEmptyChildElementAsync(ServiceCallback<Banana> serviceCallback) {
        return ServiceFuture.fromBody(getEmptyChildElementAsync(), serviceCallback);
    }

    /**
     * Gets an XML document with an empty child element.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, Banana>> getEmptyChildElementWithRestResponseAsync() {
        return service.getEmptyChildElement();
    }

    /**
     * Gets an XML document with an empty child element.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<Banana> getEmptyChildElementAsync() {
        return getEmptyChildElementWithRestResponseAsync()
            .flatMapMaybe(new Function<RestResponse<Void, Banana>, Maybe<Banana>>() {
                public Maybe<Banana> apply(RestResponse<Void, Banana> restResponse) {
                    if (restResponse.body() == null) {
                        return Maybe.empty();
                    } else {
                        return Maybe.just(restResponse.body());
                    }
                }
            });
    }

    /**
     * Puts a value with an empty child element.
     *
     * @param banana the Banana value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putEmptyChildElement(@NonNull Banana banana) {
        putEmptyChildElementAsync(banana).blockingAwait();
    }

    /**
     * Puts a value with an empty child element.
     *
     * @param banana the Banana value.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putEmptyChildElementAsync(@NonNull Banana banana, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putEmptyChildElementAsync(banana), serviceCallback);
    }

    /**
     * Puts a value with an empty child element.
     *
     * @param banana the Banana value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<RestResponse<Void, Void>> putEmptyChildElementWithRestResponseAsync(@NonNull Banana banana) {
        if (banana == null) {
            throw new IllegalArgumentException("Parameter banana is required and cannot be null.");
        }
        Validator.validate(banana);
        return service.putEmptyChildElement(banana);
    }

    /**
     * Puts a value with an empty child element.
     *
     * @param banana the Banana value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putEmptyChildElementAsync(@NonNull Banana banana) {
        return putEmptyChildElementWithRestResponseAsync(banana)
            .toCompletable();
    }
}
