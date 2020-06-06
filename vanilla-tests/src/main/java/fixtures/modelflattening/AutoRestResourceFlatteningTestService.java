package fixtures.modelflattening;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
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
import fixtures.modelflattening.models.ErrorException;
import fixtures.modelflattening.models.FlattenParameterGroup;
import fixtures.modelflattening.models.FlattenedProduct;
import fixtures.modelflattening.models.ProductWrapper;
import fixtures.modelflattening.models.Resource;
import fixtures.modelflattening.models.ResourceCollection;
import fixtures.modelflattening.models.SimpleProduct;
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

    /** Initializes an instance of AutoRestResourceFlatteningTestService client. */
    AutoRestResourceFlatteningTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestResourceFlatteningTestService(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.service = RestProxy.create(AutoRestResourceFlatteningTestServiceService.class, this.httpPipeline);
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
                Context context);

        @Get("/model-flatten/array")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<FlattenedProduct>>> getArray(@HostParam("$host") String host, Context context);

        @Put("/model-flatten/wrappedarray")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putWrappedArray(
                @HostParam("$host") String host,
                @BodyParam("application/json") List<WrappedProduct> resourceArray,
                Context context);

        @Get("/model-flatten/wrappedarray")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<List<ProductWrapper>>> getWrappedArray(@HostParam("$host") String host, Context context);

        @Put("/model-flatten/dictionary")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDictionary(
                @HostParam("$host") String host,
                @BodyParam("application/json") Map<String, FlattenedProduct> resourceDictionary,
                Context context);

        @Get("/model-flatten/dictionary")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Map<String, FlattenedProduct>>> getDictionary(@HostParam("$host") String host, Context context);

        @Put("/model-flatten/resourcecollection")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putResourceCollection(
                @HostParam("$host") String host,
                @BodyParam("application/json") ResourceCollection resourceComplexObject,
                Context context);

        @Get("/model-flatten/resourcecollection")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<ResourceCollection>> getResourceCollection(@HostParam("$host") String host, Context context);

        @Put("/model-flatten/customFlattening")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<SimpleProduct>> putSimpleProduct(
                @HostParam("$host") String host,
                @BodyParam("application/json") SimpleProduct simpleBodyProduct,
                Context context);

        @Post("/model-flatten/customFlattening")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<SimpleProduct>> postFlattenedSimpleProduct(
                @HostParam("$host") String host,
                @BodyParam("application/json") SimpleProduct simpleBodyProduct,
                Context context);

        @Put("/model-flatten/customFlattening/parametergrouping/{name}/")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<SimpleProduct>> putSimpleProductWithGrouping(
                @HostParam("$host") String host,
                @PathParam("name") String name,
                @BodyParam("application/json") SimpleProduct simpleBodyProduct,
                Context context);
    }

    /**
     * Put External Resource as an Array.
     *
     * @param resourceArray Array of Resource.
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
        return FluxUtil.withContext(context -> service.putArray(this.getHost(), resourceArray, context));
    }

    /**
     * Put External Resource as an Array.
     *
     * @param resourceArray Array of Resource.
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
     * @param resourceArray Array of Resource.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putArray(List<Resource> resourceArray) {
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
        return FluxUtil.withContext(context -> service.getArray(this.getHost(), context));
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
     * @param resourceArray Array of WrappedProduct.
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
        return FluxUtil.withContext(context -> service.putWrappedArray(this.getHost(), resourceArray, context));
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if
     * it's referenced in an array.
     *
     * @param resourceArray Array of WrappedProduct.
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
     * @param resourceArray Array of WrappedProduct.
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
     * @return array of ProductWrapper.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<ProductWrapper>>> getWrappedArrayWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getWrappedArray(this.getHost(), context));
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
     * @param resourceDictionary Dictionary of &lt;FlattenedProduct&gt;.
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
        return FluxUtil.withContext(context -> service.putDictionary(this.getHost(), resourceDictionary, context));
    }

    /**
     * Put External Resource as a Dictionary.
     *
     * @param resourceDictionary Dictionary of &lt;FlattenedProduct&gt;.
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
     * @param resourceDictionary Dictionary of &lt;FlattenedProduct&gt;.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putDictionary(Map<String, FlattenedProduct> resourceDictionary) {
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
        return FluxUtil.withContext(context -> service.getDictionary(this.getHost(), context));
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
        return FluxUtil.withContext(
                context -> service.putResourceCollection(this.getHost(), resourceComplexObject, context));
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
        return FluxUtil.withContext(context -> service.getResourceCollection(this.getHost(), context));
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
     * @param simpleBodyProduct The product documentation.
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
        return FluxUtil.withContext(context -> service.putSimpleProduct(this.getHost(), simpleBodyProduct, context));
    }

    /**
     * Put Simple Product with client flattening true on the model.
     *
     * @param simpleBodyProduct The product documentation.
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
     * @param simpleBodyProduct The product documentation.
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
     * Put Flattened Simple Product with client flattening true on the parameter.
     *
     * @param productId Unique identifier representing a specific product for a given latitude &amp; longitude. For
     *     example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     * @param description Description of product.
     * @param maxProductDisplayName Display name of product.
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
            String genericValue,
            String odataValue) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (productId == null) {
            return Mono.error(new IllegalArgumentException("Parameter productId is required and cannot be null."));
        }
        SimpleProduct simpleBodyProductInternal = null;
        if (description != null || maxProductDisplayName != null || genericValue != null || odataValue != null) {
            simpleBodyProductInternal = new SimpleProduct();
            simpleBodyProductInternal.setProductId(productId);
            simpleBodyProductInternal.setDescription(description);
            simpleBodyProductInternal.setMaxProductDisplayName(maxProductDisplayName);
            simpleBodyProductInternal.setGenericValue(genericValue);
            simpleBodyProductInternal.setOdataValue(odataValue);
        }
        SimpleProduct simpleBodyProduct = simpleBodyProductInternal;
        return FluxUtil.withContext(
                context -> service.postFlattenedSimpleProduct(this.getHost(), simpleBodyProduct, context));
    }

    /**
     * Put Flattened Simple Product with client flattening true on the parameter.
     *
     * @param productId Unique identifier representing a specific product for a given latitude &amp; longitude. For
     *     example, uberX in San Francisco will have a different product_id than uberX in Los Angeles.
     * @param description Description of product.
     * @param maxProductDisplayName Display name of product.
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
            String genericValue,
            String odataValue) {
        return postFlattenedSimpleProductWithResponseAsync(
                        productId, description, maxProductDisplayName, genericValue, odataValue)
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
            String genericValue,
            String odataValue) {
        return postFlattenedSimpleProductAsync(productId, description, maxProductDisplayName, genericValue, odataValue)
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
        String name = flattenParameterGroup.getName();
        SimpleProduct simpleBodyProduct = new SimpleProduct();
        simpleBodyProduct.setProductId(flattenParameterGroup.getProductId());
        simpleBodyProduct.setDescription(flattenParameterGroup.getDescription());
        simpleBodyProduct.setMaxProductDisplayName(flattenParameterGroup.getMaxProductDisplayName());
        simpleBodyProduct.setGenericValue(flattenParameterGroup.getGenericValue());
        simpleBodyProduct.setOdataValue(flattenParameterGroup.getOdataValue());
        return FluxUtil.withContext(
                context -> service.putSimpleProductWithGrouping(this.getHost(), name, simpleBodyProduct, context));
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
