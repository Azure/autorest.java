package fixtures.modelflattening;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import fixtures.modelflattening.models.ErrorException;
import fixtures.modelflattening.models.FlattenParameterGroup;
import fixtures.modelflattening.models.FlattenedProduct;
import fixtures.modelflattening.models.ProductWrapper;
import fixtures.modelflattening.models.Resource;
import fixtures.modelflattening.models.ResourceCollection;
import fixtures.modelflattening.models.SimpleProduct;
import fixtures.modelflattening.models.SimpleProductPropertiesMaxProductCapacity;
import fixtures.modelflattening.models.WrappedProduct;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the AutoRestResourceFlatteningTestService type. */
public final class AutoRestResourceFlatteningTestService {
    /** The proxy service used to perform REST calls. */
    private final AutoRestResourceFlatteningTestServiceService service;

    /** server parameter. */
    private final String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /** The HTTP pipeline to send requests through. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** The serializer to serialize an object into a string. */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     *
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     *
     * @param host server parameter.
     */
    AutoRestResourceFlatteningTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    AutoRestResourceFlatteningTestService(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    AutoRestResourceFlatteningTestService(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.service =
                RestProxy.create(
                        AutoRestResourceFlatteningTestServiceService.class,
                        this.httpPipeline,
                        this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for AutoRestResourceFlatteningTestService to be used by the proxy service
     * to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestResourceFlat")
    private interface AutoRestResourceFlatteningTestServiceService {
        @Put("/model-flatten/array")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putArray(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<Resource> resourceArray,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/model-flatten/array")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<FlattenedProduct>>> getArray(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/model-flatten/wrappedarray")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putWrappedArray(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<WrappedProduct> resourceArray,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/model-flatten/wrappedarray")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<ProductWrapper>>> getWrappedArray(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/model-flatten/dictionary")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDictionary(
                @HostParam("$host") String host,
                @BodyParam("application/json") Map<String, FlattenedProduct> resourceDictionary,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/model-flatten/dictionary")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Map<String, FlattenedProduct>>> getDictionary(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/model-flatten/resourcecollection")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putResourceCollection(
                @HostParam("$host") String host,
                @BodyParam("application/json") ResourceCollection resourceComplexObject,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/model-flatten/resourcecollection")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<ResourceCollection>> getResourceCollection(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/model-flatten/customFlattening")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<SimpleProduct>> putSimpleProduct(
                @HostParam("$host") String host,
                @BodyParam("application/json") SimpleProduct simpleBodyProduct,
                @HeaderParam("Accept") String accept,
                Context context);

        @Post("/model-flatten/customFlattening")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<SimpleProduct>> postFlattenedSimpleProduct(
                @HostParam("$host") String host,
                @BodyParam("application/json") SimpleProduct simpleBodyProduct,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/model-flatten/customFlattening/parametergrouping/{name}/")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<SimpleProduct>> putSimpleProductWithGrouping(
                @HostParam("$host") String host,
                @PathParam("name") String name,
                @BodyParam("application/json") SimpleProduct simpleBodyProduct,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Put External Resource as an Array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putArrayWithResponseAsync(List<Resource> resourceArray) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (resourceArray != null) {
            resourceArray.forEach(e -> e.validate());
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putArray(this.getHost(), resourceArray, accept, context));
    }

    /**
     * Put External Resource as an Array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putArrayAsync(List<Resource> resourceArray) {
        return putArrayWithResponseAsync(resourceArray).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put External Resource as an Array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putArrayAsync() {
        final List<Resource> resourceArray = null;
        return putArrayWithResponseAsync(resourceArray).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put External Resource as an Array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putArray(List<Resource> resourceArray) {
        putArrayAsync(resourceArray).block();
    }

    /**
     * Put External Resource as an Array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putArray() {
        final List<Resource> resourceArray = null;
        putArrayAsync(resourceArray).block();
    }

    /**
     * Get External Resource as an Array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return external Resource as an Array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<FlattenedProduct>>> getArrayWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getArray(this.getHost(), accept, context));
    }

    /**
     * Get External Resource as an Array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return external Resource as an Array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<FlattenedProduct>> getArrayAsync() {
        return getArrayWithResponseAsync()
                .flatMap(
                        (Response<List<FlattenedProduct>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get External Resource as an Array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return external Resource as an Array.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<FlattenedProduct> getArray() {
        return getArrayAsync().block();
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if
     * it's referenced in an array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWrappedArrayWithResponseAsync(List<WrappedProduct> resourceArray) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (resourceArray != null) {
            resourceArray.forEach(e -> e.validate());
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putWrappedArray(this.getHost(), resourceArray, accept, context));
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if
     * it's referenced in an array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putWrappedArrayAsync(List<WrappedProduct> resourceArray) {
        return putWrappedArrayWithResponseAsync(resourceArray).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if
     * it's referenced in an array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putWrappedArrayAsync() {
        final List<WrappedProduct> resourceArray = null;
        return putWrappedArrayWithResponseAsync(resourceArray).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if
     * it's referenced in an array.
     *
     * @param resourceArray External Resource as an Array to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putWrappedArray(List<WrappedProduct> resourceArray) {
        putWrappedArrayAsync(resourceArray).block();
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if
     * it's referenced in an array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putWrappedArray() {
        final List<WrappedProduct> resourceArray = null;
        putWrappedArrayAsync(resourceArray).block();
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if
     * it's referenced in an array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of ProductWrapper.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<ProductWrapper>>> getWrappedArrayWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getWrappedArray(this.getHost(), accept, context));
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if
     * it's referenced in an array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of ProductWrapper.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<ProductWrapper>> getWrappedArrayAsync() {
        return getWrappedArrayWithResponseAsync()
                .flatMap(
                        (Response<List<ProductWrapper>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if
     * it's referenced in an array.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return array of ProductWrapper.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<ProductWrapper> getWrappedArray() {
        return getWrappedArrayAsync().block();
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @param resourceDictionary External Resource as a Dictionary to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDictionaryWithResponseAsync(Map<String, FlattenedProduct> resourceDictionary) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (resourceDictionary != null) {
            resourceDictionary
                    .values()
                    .forEach(
                            e -> {
                                if (e != null) {
                                    e.validate();
                                }
                            });
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putDictionary(this.getHost(), resourceDictionary, accept, context));
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @param resourceDictionary External Resource as a Dictionary to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDictionaryAsync(Map<String, FlattenedProduct> resourceDictionary) {
        return putDictionaryWithResponseAsync(resourceDictionary).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putDictionaryAsync() {
        final Map<String, FlattenedProduct> resourceDictionary = null;
        return putDictionaryWithResponseAsync(resourceDictionary).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @param resourceDictionary External Resource as a Dictionary to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDictionary(Map<String, FlattenedProduct> resourceDictionary) {
        putDictionaryAsync(resourceDictionary).block();
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDictionary() {
        final Map<String, FlattenedProduct> resourceDictionary = null;
        putDictionaryAsync(resourceDictionary).block();
    }

    /**
     * Get External Resource as a Dictionary.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return external Resource as a Dictionary.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Map<String, FlattenedProduct>>> getDictionaryWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDictionary(this.getHost(), accept, context));
    }

    /**
     * Get External Resource as a Dictionary.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return external Resource as a Dictionary.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, FlattenedProduct>> getDictionaryAsync() {
        return getDictionaryWithResponseAsync()
                .flatMap(
                        (Response<Map<String, FlattenedProduct>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get External Resource as a Dictionary.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return external Resource as a Dictionary.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, FlattenedProduct> getDictionary() {
        return getDictionaryAsync().block();
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @param resourceComplexObject External Resource as a ResourceCollection to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putResourceCollectionWithResponseAsync(ResourceCollection resourceComplexObject) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (resourceComplexObject != null) {
            resourceComplexObject.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putResourceCollection(this.getHost(), resourceComplexObject, accept, context));
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @param resourceComplexObject External Resource as a ResourceCollection to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putResourceCollectionAsync(ResourceCollection resourceComplexObject) {
        return putResourceCollectionWithResponseAsync(resourceComplexObject)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putResourceCollectionAsync() {
        final ResourceCollection resourceComplexObject = null;
        return putResourceCollectionWithResponseAsync(resourceComplexObject)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @param resourceComplexObject External Resource as a ResourceCollection to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putResourceCollection(ResourceCollection resourceComplexObject) {
        putResourceCollectionAsync(resourceComplexObject).block();
    }

    /**
     * Put External Resource as a ResourceCollection.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putResourceCollection() {
        final ResourceCollection resourceComplexObject = null;
        putResourceCollectionAsync(resourceComplexObject).block();
    }

    /**
     * Get External Resource as a ResourceCollection.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return external Resource as a ResourceCollection.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ResourceCollection>> getResourceCollectionWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getResourceCollection(this.getHost(), accept, context));
    }

    /**
     * Get External Resource as a ResourceCollection.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return external Resource as a ResourceCollection.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ResourceCollection> getResourceCollectionAsync() {
        return getResourceCollectionWithResponseAsync()
                .flatMap(
                        (Response<ResourceCollection> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get External Resource as a ResourceCollection.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return external Resource as a ResourceCollection.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ResourceCollection getResourceCollection() {
        return getResourceCollectionAsync().block();
    }

    /**
     * Put Simple Product with client flattening true on the model.
     *
     * @param simpleBodyProduct Simple body product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<SimpleProduct>> putSimpleProductWithResponseAsync(SimpleProduct simpleBodyProduct) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (simpleBodyProduct != null) {
            simpleBodyProduct.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putSimpleProduct(this.getHost(), simpleBodyProduct, accept, context));
    }

    /**
     * Put Simple Product with client flattening true on the model.
     *
     * @param simpleBodyProduct Simple body product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleProduct> putSimpleProductAsync(SimpleProduct simpleBodyProduct) {
        return putSimpleProductWithResponseAsync(simpleBodyProduct)
                .flatMap(
                        (Response<SimpleProduct> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put Simple Product with client flattening true on the model.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleProduct> putSimpleProductAsync() {
        final SimpleProduct simpleBodyProduct = null;
        return putSimpleProductWithResponseAsync(simpleBodyProduct)
                .flatMap(
                        (Response<SimpleProduct> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put Simple Product with client flattening true on the model.
     *
     * @param simpleBodyProduct Simple body product to put.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SimpleProduct putSimpleProduct(SimpleProduct simpleBodyProduct) {
        return putSimpleProductAsync(simpleBodyProduct).block();
    }

    /**
     * Put Simple Product with client flattening true on the model.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SimpleProduct putSimpleProduct() {
        final SimpleProduct simpleBodyProduct = null;
        return putSimpleProductAsync(simpleBodyProduct).block();
    }

    /**
     * Put Flattened Simple Product with client flattening true on the parameter.
     *
     * @param productId Unique identifier representing a specific product for a given latitude &amp; longitude. For
     *     example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     * @param description Description of product.
     * @param maxProductDisplayName Display name of product.
     * @param capacity Capacity of product. For example, 4 people.
     * @param genericValue Generic URL value.
     * @param odataValue URL value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<SimpleProduct>> postFlattenedSimpleProductWithResponseAsync(
            String productId,
            String description,
            String maxProductDisplayName,
            SimpleProductPropertiesMaxProductCapacity capacity,
            String genericValue,
            String odataValue) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (productId == null) {
            return Mono.error(new IllegalArgumentException("Parameter productId is required and cannot be null."));
        }
        final String accept = "application/json";
        SimpleProduct simpleBodyProductInternal = null;
        if (description != null
                || maxProductDisplayName != null
                || capacity != null
                || genericValue != null
                || odataValue != null) {
            simpleBodyProductInternal = new SimpleProduct();
            simpleBodyProductInternal.setProductId(productId);
            simpleBodyProductInternal.setDescription(description);
            simpleBodyProductInternal.setMaxProductDisplayName(maxProductDisplayName);
            simpleBodyProductInternal.setCapacity(capacity);
            simpleBodyProductInternal.setGenericValue(genericValue);
            simpleBodyProductInternal.setOdataValue(odataValue);
        }
        SimpleProduct simpleBodyProduct = simpleBodyProductInternal;
        return FluxUtil.withContext(
                context -> service.postFlattenedSimpleProduct(this.getHost(), simpleBodyProduct, accept, context));
    }

    /**
     * Put Flattened Simple Product with client flattening true on the parameter.
     *
     * @param productId Unique identifier representing a specific product for a given latitude &amp; longitude. For
     *     example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     * @param description Description of product.
     * @param maxProductDisplayName Display name of product.
     * @param capacity Capacity of product. For example, 4 people.
     * @param genericValue Generic URL value.
     * @param odataValue URL value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleProduct> postFlattenedSimpleProductAsync(
            String productId,
            String description,
            String maxProductDisplayName,
            SimpleProductPropertiesMaxProductCapacity capacity,
            String genericValue,
            String odataValue) {
        return postFlattenedSimpleProductWithResponseAsync(
                        productId, description, maxProductDisplayName, capacity, genericValue, odataValue)
                .flatMap(
                        (Response<SimpleProduct> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put Flattened Simple Product with client flattening true on the parameter.
     *
     * @param productId Unique identifier representing a specific product for a given latitude &amp; longitude. For
     *     example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleProduct> postFlattenedSimpleProductAsync(String productId) {
        final String description = null;
        final String maxProductDisplayName = null;
        final SimpleProductPropertiesMaxProductCapacity capacity = null;
        final String genericValue = null;
        final String odataValue = null;
        return postFlattenedSimpleProductWithResponseAsync(
                        productId, description, maxProductDisplayName, capacity, genericValue, odataValue)
                .flatMap(
                        (Response<SimpleProduct> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put Flattened Simple Product with client flattening true on the parameter.
     *
     * @param productId Unique identifier representing a specific product for a given latitude &amp; longitude. For
     *     example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     * @param description Description of product.
     * @param maxProductDisplayName Display name of product.
     * @param capacity Capacity of product. For example, 4 people.
     * @param genericValue Generic URL value.
     * @param odataValue URL value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SimpleProduct postFlattenedSimpleProduct(
            String productId,
            String description,
            String maxProductDisplayName,
            SimpleProductPropertiesMaxProductCapacity capacity,
            String genericValue,
            String odataValue) {
        return postFlattenedSimpleProductAsync(
                        productId, description, maxProductDisplayName, capacity, genericValue, odataValue)
                .block();
    }

    /**
     * Put Flattened Simple Product with client flattening true on the parameter.
     *
     * @param productId Unique identifier representing a specific product for a given latitude &amp; longitude. For
     *     example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SimpleProduct postFlattenedSimpleProduct(String productId) {
        final String description = null;
        final String maxProductDisplayName = null;
        final SimpleProductPropertiesMaxProductCapacity capacity = null;
        final String genericValue = null;
        final String odataValue = null;
        return postFlattenedSimpleProductAsync(
                        productId, description, maxProductDisplayName, capacity, genericValue, odataValue)
                .block();
    }

    /**
     * Put Simple Product with client flattening true on the model.
     *
     * @param flattenParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<SimpleProduct>> putSimpleProductWithGroupingWithResponseAsync(
            FlattenParameterGroup flattenParameterGroup) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (flattenParameterGroup == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter flattenParameterGroup is required and cannot be null."));
        } else {
            flattenParameterGroup.validate();
        }
        final String accept = "application/json";
        String name = flattenParameterGroup.getName();
        SimpleProduct simpleBodyProduct = new SimpleProduct();
        simpleBodyProduct.setProductId(flattenParameterGroup.getProductId());
        simpleBodyProduct.setDescription(flattenParameterGroup.getDescription());
        simpleBodyProduct.setMaxProductDisplayName(flattenParameterGroup.getMaxProductDisplayName());
        simpleBodyProduct.setCapacity(flattenParameterGroup.getCapacity());
        simpleBodyProduct.setGenericValue(flattenParameterGroup.getGenericValue());
        simpleBodyProduct.setOdataValue(flattenParameterGroup.getOdataValue());
        return FluxUtil.withContext(
                context ->
                        service.putSimpleProductWithGrouping(this.getHost(), name, simpleBodyProduct, accept, context));
    }

    /**
     * Put Simple Product with client flattening true on the model.
     *
     * @param flattenParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleProduct> putSimpleProductWithGroupingAsync(FlattenParameterGroup flattenParameterGroup) {
        return putSimpleProductWithGroupingWithResponseAsync(flattenParameterGroup)
                .flatMap(
                        (Response<SimpleProduct> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put Simple Product with client flattening true on the model.
     *
     * @param flattenParameterGroup Parameter group.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the product documentation.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SimpleProduct putSimpleProductWithGrouping(FlattenParameterGroup flattenParameterGroup) {
        return putSimpleProductWithGroupingAsync(flattenParameterGroup).block();
    }
}
