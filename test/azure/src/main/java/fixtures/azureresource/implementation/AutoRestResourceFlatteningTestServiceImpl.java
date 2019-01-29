/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.azureresource.implementation;

import com.microsoft.azure.v2.AzureEnvironment;
import com.microsoft.azure.v2.AzureProxy;
import com.microsoft.azure.v2.AzureServiceClient;
import com.microsoft.azure.v2.Resource;
import com.microsoft.rest.v2.BodyResponse;
import com.microsoft.rest.v2.ServiceCallback;
import com.microsoft.rest.v2.ServiceFuture;
import com.microsoft.rest.v2.Validator;
import com.microsoft.rest.v2.VoidResponse;
import com.microsoft.rest.v2.annotations.BodyParam;
import com.microsoft.rest.v2.annotations.ExpectedResponses;
import com.microsoft.rest.v2.annotations.GET;
import com.microsoft.rest.v2.annotations.HeaderParam;
import com.microsoft.rest.v2.annotations.Host;
import com.microsoft.rest.v2.annotations.PUT;
import com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType;
import com.microsoft.rest.v2.credentials.ServiceClientCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import fixtures.azureresource.AutoRestResourceFlatteningTestService;
import fixtures.azureresource.models.ErrorException;
import fixtures.azureresource.models.FlattenedProduct;
import fixtures.azureresource.models.ResourceCollection;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Initializes a new instance of the AutoRestResourceFlatteningTestService type.
 */
public final class AutoRestResourceFlatteningTestServiceImpl extends AzureServiceClient implements AutoRestResourceFlatteningTestService {
    /**
     * The proxy service used to perform REST calls.
     */
    private AutoRestResourceFlatteningTestServiceService service;

    /**
     * The preferred language for the response.
     */
    private String acceptLanguage;

    /**
     * Gets The preferred language for the response.
     *
     * @return the acceptLanguage value.
     */
    public String acceptLanguage() {
        return this.acceptLanguage;
    }

    /**
     * Sets The preferred language for the response.
     *
     * @param acceptLanguage the acceptLanguage value.
     * @return the service client itself.
     */
    public AutoRestResourceFlatteningTestServiceImpl withAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
        return this;
    }

    /**
     * The retry timeout in seconds for Long Running Operations. Default value is 30.
     */
    private Integer longRunningOperationRetryTimeout;

    /**
     * Gets The retry timeout in seconds for Long Running Operations. Default value is 30.
     *
     * @return the longRunningOperationRetryTimeout value.
     */
    public Integer longRunningOperationRetryTimeout() {
        return this.longRunningOperationRetryTimeout;
    }

    /**
     * Sets The retry timeout in seconds for Long Running Operations. Default value is 30.
     *
     * @param longRunningOperationRetryTimeout the longRunningOperationRetryTimeout value.
     * @return the service client itself.
     */
    public AutoRestResourceFlatteningTestServiceImpl withLongRunningOperationRetryTimeout(Integer longRunningOperationRetryTimeout) {
        this.longRunningOperationRetryTimeout = longRunningOperationRetryTimeout;
        return this;
    }

    /**
     * Whether a unique x-ms-client-request-id should be generated. When set to true a unique x-ms-client-request-id value is generated and included in each request. Default is true.
     */
    private Boolean generateClientRequestId;

    /**
     * Gets Whether a unique x-ms-client-request-id should be generated. When set to true a unique x-ms-client-request-id value is generated and included in each request. Default is true.
     *
     * @return the generateClientRequestId value.
     */
    public Boolean generateClientRequestId() {
        return this.generateClientRequestId;
    }

    /**
     * Sets Whether a unique x-ms-client-request-id should be generated. When set to true a unique x-ms-client-request-id value is generated and included in each request. Default is true.
     *
     * @param generateClientRequestId the generateClientRequestId value.
     * @return the service client itself.
     */
    public AutoRestResourceFlatteningTestServiceImpl withGenerateClientRequestId(Boolean generateClientRequestId) {
        this.generateClientRequestId = generateClientRequestId;
        return this;
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     *
     * @param credentials the management credentials for Azure.
     */
    public AutoRestResourceFlatteningTestServiceImpl(@NonNull ServiceClientCredentials credentials) {
        this(AzureProxy.createDefaultPipeline(AutoRestResourceFlatteningTestServiceImpl.class, credentials));
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     *
     * @param credentials the management credentials for Azure.
     * @param azureEnvironment The environment that requests will target.
     */
    public AutoRestResourceFlatteningTestServiceImpl(@NonNull ServiceClientCredentials credentials, @NonNull AzureEnvironment azureEnvironment) {
        this(AzureProxy.createDefaultPipeline(AutoRestResourceFlatteningTestServiceImpl.class, credentials), azureEnvironment);
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestResourceFlatteningTestServiceImpl(@NonNull HttpPipeline httpPipeline) {
        this(httpPipeline, null);
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param azureEnvironment The environment that requests will target.
     */
    public AutoRestResourceFlatteningTestServiceImpl(@NonNull HttpPipeline httpPipeline, @NonNull AzureEnvironment azureEnvironment) {
        super(httpPipeline, azureEnvironment);
        this.acceptLanguage = "en-US";
        this.longRunningOperationRetryTimeout = 30;
        this.generateClientRequestId = true;
        this.service = AzureProxy.create(AutoRestResourceFlatteningTestServiceService.class, this);
    }

    /**
     * The interface defining all the services for
     * AutoRestResourceFlatteningTestService to be used by the proxy service to
     * perform REST calls.
     */
    @Host("http://localhost:3000")
    private interface AutoRestResourceFlatteningTestServiceService {
        @PUT("azure/resource-flatten/array")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<VoidResponse> putArray(@BodyParam("application/json; charset=utf-8") List<Resource> resourceArray, @HeaderParam("accept-language") String acceptLanguage);

        @GET("azure/resource-flatten/array")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<List<FlattenedProduct>>> getArray(@HeaderParam("accept-language") String acceptLanguage);

        @PUT("azure/resource-flatten/dictionary")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<VoidResponse> putDictionary(@BodyParam("application/json; charset=utf-8") Map<String, FlattenedProduct> resourceDictionary, @HeaderParam("accept-language") String acceptLanguage);

        @GET("azure/resource-flatten/dictionary")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<Map<String, FlattenedProduct>>> getDictionary(@HeaderParam("accept-language") String acceptLanguage);

        @PUT("azure/resource-flatten/resourcecollection")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<VoidResponse> putResourceCollection(@BodyParam("application/json; charset=utf-8") ResourceCollection resourceComplexObject, @HeaderParam("accept-language") String acceptLanguage);

        @GET("azure/resource-flatten/resourcecollection")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Single<BodyResponse<ResourceCollection>> getResourceCollection(@HeaderParam("accept-language") String acceptLanguage);
    }

    /**
     * Put External Resource as an Array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putArray() {
        putArrayAsync().blockingAwait();
    }

    /**
     * Put External Resource as an Array.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putArrayAsync(ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putArrayAsync(), serviceCallback);
    }

    /**
     * Put External Resource as an Array.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putArrayWithRestResponseAsync() {
        final List<Resource> resourceArray = null;
        return service.putArray(resourceArray, this.acceptLanguage());
    }

    /**
     * Put External Resource as an Array.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putArrayAsync() {
        return putArrayWithRestResponseAsync()
            .ignoreElement();
    }

    /**
     * Put External Resource as an Array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putArray(List<Resource> resourceArray) {
        putArrayAsync(resourceArray).blockingAwait();
    }

    /**
     * Put External Resource as an Array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putArrayAsync(List<Resource> resourceArray, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putArrayAsync(resourceArray), serviceCallback);
    }

    /**
     * Put External Resource as an Array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putArrayWithRestResponseAsync(List<Resource> resourceArray) {
        Validator.validate(resourceArray);
        return service.putArray(resourceArray, this.acceptLanguage());
    }

    /**
     * Put External Resource as an Array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putArrayAsync(List<Resource> resourceArray) {
        return putArrayWithRestResponseAsync(resourceArray)
            .ignoreElement();
    }

    /**
     * Get External Resource as an Array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the List&lt;FlattenedProduct&gt; object if successful.
     */
    public List<FlattenedProduct> getArray() {
        return getArrayAsync().blockingGet();
    }

    /**
     * Get External Resource as an Array.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<List<FlattenedProduct>> getArrayAsync(ServiceCallback<List<FlattenedProduct>> serviceCallback) {
        return ServiceFuture.fromBody(getArrayAsync(), serviceCallback);
    }

    /**
     * Get External Resource as an Array.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<List<FlattenedProduct>>> getArrayWithRestResponseAsync() {
        return service.getArray(this.acceptLanguage());
    }

    /**
     * Get External Resource as an Array.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<List<FlattenedProduct>> getArrayAsync() {
        return getArrayWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<List<FlattenedProduct>> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDictionary() {
        putDictionaryAsync().blockingAwait();
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putDictionaryAsync(ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putDictionaryAsync(), serviceCallback);
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putDictionaryWithRestResponseAsync() {
        final Map<String, FlattenedProduct> resourceDictionary = null;
        return service.putDictionary(resourceDictionary, this.acceptLanguage());
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putDictionaryAsync() {
        return putDictionaryWithRestResponseAsync()
            .ignoreElement();
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @param resourceDictionary External Resource as a Dictionary to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putDictionary(Map<String, FlattenedProduct> resourceDictionary) {
        putDictionaryAsync(resourceDictionary).blockingAwait();
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @param resourceDictionary External Resource as a Dictionary to put.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putDictionaryAsync(Map<String, FlattenedProduct> resourceDictionary, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putDictionaryAsync(resourceDictionary), serviceCallback);
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @param resourceDictionary External Resource as a Dictionary to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putDictionaryWithRestResponseAsync(Map<String, FlattenedProduct> resourceDictionary) {
        Validator.validate(resourceDictionary);
        return service.putDictionary(resourceDictionary, this.acceptLanguage());
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @param resourceDictionary External Resource as a Dictionary to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putDictionaryAsync(Map<String, FlattenedProduct> resourceDictionary) {
        return putDictionaryWithRestResponseAsync(resourceDictionary)
            .ignoreElement();
    }

    /**
     * Get External Resource as a Dictionary.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the Map&lt;String, FlattenedProduct&gt; object if successful.
     */
    public Map<String, FlattenedProduct> getDictionary() {
        return getDictionaryAsync().blockingGet();
    }

    /**
     * Get External Resource as a Dictionary.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Map<String, FlattenedProduct>> getDictionaryAsync(ServiceCallback<Map<String, FlattenedProduct>> serviceCallback) {
        return ServiceFuture.fromBody(getDictionaryAsync(), serviceCallback);
    }

    /**
     * Get External Resource as a Dictionary.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<Map<String, FlattenedProduct>>> getDictionaryWithRestResponseAsync() {
        return service.getDictionary(this.acceptLanguage());
    }

    /**
     * Get External Resource as a Dictionary.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<Map<String, FlattenedProduct>> getDictionaryAsync() {
        return getDictionaryWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<Map<String, FlattenedProduct>> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putResourceCollection() {
        putResourceCollectionAsync().blockingAwait();
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putResourceCollectionAsync(ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putResourceCollectionAsync(), serviceCallback);
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putResourceCollectionWithRestResponseAsync() {
        final ResourceCollection resourceComplexObject = null;
        return service.putResourceCollection(resourceComplexObject, this.acceptLanguage());
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putResourceCollectionAsync() {
        return putResourceCollectionWithRestResponseAsync()
            .ignoreElement();
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @param resourceComplexObject External Resource as a ResourceCollection to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    public void putResourceCollection(ResourceCollection resourceComplexObject) {
        putResourceCollectionAsync(resourceComplexObject).blockingAwait();
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @param resourceComplexObject External Resource as a ResourceCollection to put.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<Void> putResourceCollectionAsync(ResourceCollection resourceComplexObject, ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromBody(putResourceCollectionAsync(resourceComplexObject), serviceCallback);
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @param resourceComplexObject External Resource as a ResourceCollection to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Single<VoidResponse> putResourceCollectionWithRestResponseAsync(ResourceCollection resourceComplexObject) {
        Validator.validate(resourceComplexObject);
        return service.putResourceCollection(resourceComplexObject, this.acceptLanguage());
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @param resourceComplexObject External Resource as a ResourceCollection to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a Single which performs the network request upon subscription.
     */
    public Completable putResourceCollectionAsync(ResourceCollection resourceComplexObject) {
        return putResourceCollectionWithRestResponseAsync(resourceComplexObject)
            .ignoreElement();
    }

    /**
     * Get External Resource as a ResourceCollection.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ResourceCollection object if successful.
     */
    public ResourceCollection getResourceCollection() {
        return getResourceCollectionAsync().blockingGet();
    }

    /**
     * Get External Resource as a ResourceCollection.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @return a ServiceFuture which will be completed with the result of the network request.
     */
    public ServiceFuture<ResourceCollection> getResourceCollectionAsync(ServiceCallback<ResourceCollection> serviceCallback) {
        return ServiceFuture.fromBody(getResourceCollectionAsync(), serviceCallback);
    }

    /**
     * Get External Resource as a ResourceCollection.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Single<BodyResponse<ResourceCollection>> getResourceCollectionWithRestResponseAsync() {
        return service.getResourceCollection(this.acceptLanguage());
    }

    /**
     * Get External Resource as a ResourceCollection.
     *
     * @return a Single which performs the network request upon subscription.
     */
    public Maybe<ResourceCollection> getResourceCollectionAsync() {
        return getResourceCollectionWithRestResponseAsync()
            .flatMapMaybe((BodyResponse<ResourceCollection> res) -> res.body() == null ? Maybe.empty() : Maybe.just(res.body()));
    }
}
