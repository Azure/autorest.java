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
import com.azure.core.http.rest.SimpleResponse;
import fixtures.modelflattening.models.ErrorException;
import fixtures.modelflattening.models.FlattenedProduct;
import fixtures.modelflattening.models.ProductWrapper;
import fixtures.modelflattening.models.Resource;
import fixtures.modelflattening.models.ResourceCollection;
import fixtures.modelflattening.models.SimpleProduct;
import fixtures.modelflattening.models.WrappedProduct;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the AutoRestResourceFlatteningTestService type.
 */
public final class AutoRestResourceFlatteningTestService {
    /**
     * The proxy service used to perform REST calls.
     */
    private AutoRestResourceFlatteningTestServiceService service;

    /**
     * server parameter.
     */
    private String host;

    /**
     * Gets server parameter.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets server parameter.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    AutoRestResourceFlatteningTestService setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     */
    public AutoRestResourceFlatteningTestService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestResourceFlatteningTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestResourceFlatteningTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.service = RestProxy.create(AutoRestResourceFlatteningTestServiceService.class, this.httpPipeline);
    }

    /**
     * The interface defining all the services for
     * AutoRestResourceFlatteningTestService to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestResourceFlatteningTestService")
    private interface AutoRestResourceFlatteningTestServiceService {
        @Put("/model-flatten/array")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putArray(@HostParam("$host") String host, @BodyParam("application/json") List<Resource> resourceArray);

        @Get("/model-flatten/array")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<List<FlattenedProduct>>> getArray(@HostParam("$host") String host);

        @Put("/model-flatten/wrappedarray")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putWrappedArray(@HostParam("$host") String host, @BodyParam("application/json") List<WrappedProduct> resourceArray);

        @Get("/model-flatten/wrappedarray")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<List<ProductWrapper>>> getWrappedArray(@HostParam("$host") String host);

        @Put("/model-flatten/dictionary")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putDictionary(@HostParam("$host") String host, @BodyParam("application/json") Map<String, FlattenedProduct> resourceDictionary);

        @Get("/model-flatten/dictionary")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, FlattenedProduct>>> getDictionary(@HostParam("$host") String host);

        @Put("/model-flatten/resourcecollection")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putResourceCollection(@HostParam("$host") String host, @BodyParam("application/json") ResourceCollection resourceComplexObject);

        @Get("/model-flatten/resourcecollection")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<ResourceCollection>> getResourceCollection(@HostParam("$host") String host);

        @Put("/model-flatten/customFlattening")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<SimpleProduct>> putSimpleProduct(@HostParam("$host") String host, @BodyParam("application/json") SimpleProduct simpleBodyProduct);

        @Post("/model-flatten/customFlattening")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<SimpleProduct>> postFlattenedSimpleProduct(@HostParam("$host") String host, @BodyParam("application/json") SimpleProduct simpleBodyProduct);

        @Put("/model-flatten/customFlattening/parametergrouping/{name}/")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<SimpleProduct>> putSimpleProductWithGrouping(@HostParam("$host") String host, @PathParam("name") String name, @BodyParam("application/json") SimpleProduct simpleBodyProduct);
    }

    /**
     * Put External Resource as an Array.
     * 
     * @param resourceArray 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putArrayWithResponseAsync(List<Resource> resourceArray) {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        if (resourceArray != null) {
            resourceArray.forEach(e -> e.validate());
        }
        return service.putArray(this.getHost(), resourceArray);
    }

    /**
     * Put External Resource as an Array.
     * 
     * @param resourceArray 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putArrayAsync(List<Resource> resourceArray) {
        return putArrayWithResponseAsync(resourceArray)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put External Resource as an Array.
     * 
     * @param resourceArray 
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<List<FlattenedProduct>>> getArrayWithResponseAsync() {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        return service.getArray(this.getHost());
    }

    /**
     * Get External Resource as an Array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<FlattenedProduct>> getArrayAsync() {
        return getArrayWithResponseAsync()
            .flatMap((SimpleResponse<List<FlattenedProduct>> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<FlattenedProduct> getArray() {
        return getArrayAsync().block();
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if it's referenced in an array.
     * 
     * @param resourceArray 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWrappedArrayWithResponseAsync(List<WrappedProduct> resourceArray) {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        if (resourceArray != null) {
            resourceArray.forEach(e -> e.validate());
        }
        return service.putWrappedArray(this.getHost(), resourceArray);
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if it's referenced in an array.
     * 
     * @param resourceArray 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putWrappedArrayAsync(List<WrappedProduct> resourceArray) {
        return putWrappedArrayWithResponseAsync(resourceArray)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if it's referenced in an array.
     * 
     * @param resourceArray 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putWrappedArray(List<WrappedProduct> resourceArray) {
        putWrappedArrayAsync(resourceArray).block();
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if it's referenced in an array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<List<ProductWrapper>>> getWrappedArrayWithResponseAsync() {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        return service.getWrappedArray(this.getHost());
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if it's referenced in an array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<ProductWrapper>> getWrappedArrayAsync() {
        return getWrappedArrayWithResponseAsync()
            .flatMap((SimpleResponse<List<ProductWrapper>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * No need to have a route in Express server for this operation. Used to verify the type flattened is not removed if it's referenced in an array.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putDictionaryWithResponseAsync(Map<String, FlattenedProduct> resourceDictionary) {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        if (resourceDictionary != null) {
            resourceDictionary.values().forEach(e -> e.validate());
        }
        return service.putDictionary(this.getHost(), resourceDictionary);
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
    public Mono<Void> putDictionaryAsync(Map<String, FlattenedProduct> resourceDictionary) {
        return putDictionaryWithResponseAsync(resourceDictionary)
            .flatMap((Response<Void> res) -> Mono.empty());
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, FlattenedProduct>>> getDictionaryWithResponseAsync() {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        return service.getDictionary(this.getHost());
    }

    /**
     * Get External Resource as a Dictionary.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, FlattenedProduct>> getDictionaryAsync() {
        return getDictionaryWithResponseAsync()
            .flatMap((SimpleResponse<Map<String, FlattenedProduct>> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, FlattenedProduct> getDictionary() {
        return getDictionaryAsync().block();
    }

    /**
     * Put External Resource as a ResourceCollection.
     * 
     * @param resourceComplexObject 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putResourceCollectionWithResponseAsync(ResourceCollection resourceComplexObject) {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        if (resourceComplexObject != null) {
            resourceComplexObject.validate();
        }
        return service.putResourceCollection(this.getHost(), resourceComplexObject);
    }

    /**
     * Put External Resource as a ResourceCollection.
     * 
     * @param resourceComplexObject 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putResourceCollectionAsync(ResourceCollection resourceComplexObject) {
        return putResourceCollectionWithResponseAsync(resourceComplexObject)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put External Resource as a ResourceCollection.
     * 
     * @param resourceComplexObject 
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ResourceCollection>> getResourceCollectionWithResponseAsync() {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        return service.getResourceCollection(this.getHost());
    }

    /**
     * Get External Resource as a ResourceCollection.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ResourceCollection> getResourceCollectionAsync() {
        return getResourceCollectionWithResponseAsync()
            .flatMap((SimpleResponse<ResourceCollection> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SimpleProduct>> putSimpleProductWithResponseAsync(SimpleProduct simpleBodyProduct) {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        if (simpleBodyProduct != null) {
            simpleBodyProduct.validate();
        }
        return service.putSimpleProduct(this.getHost(), simpleBodyProduct);
    }

    /**
     * Put Simple Product with client flattening true on the model.
     * 
     * @param simpleBodyProduct The product documentation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleProduct> putSimpleProductAsync(SimpleProduct simpleBodyProduct) {
        return putSimpleProductWithResponseAsync(simpleBodyProduct)
            .flatMap((SimpleResponse<SimpleProduct> res) -> {
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
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SimpleProduct putSimpleProduct(SimpleProduct simpleBodyProduct) {
        return putSimpleProductAsync(simpleBodyProduct).block();
    }

    /**
     * Put Flattened Simple Product with client flattening true on the parameter.
     * 
     * @param maxProductDisplayName Display name of product.
     * @param genericValue Generic URL value.
     * @param odataValue URL value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SimpleProduct>> postFlattenedSimpleProductWithResponseAsync(String maxProductDisplayName, String genericValue, String odataValue) {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        SimpleProduct simpleBodyProduct = null;
        if (maxProductDisplayName != null || genericValue != null || odataValue != null) {
            simpleBodyProduct = new SimpleProduct();
            simpleBodyProduct.setMaxProductDisplayName(maxProductDisplayName);
            simpleBodyProduct.setGenericValue(genericValue);
            simpleBodyProduct.setOdataValue(odataValue);
        }
        return service.postFlattenedSimpleProduct(this.getHost(), simpleBodyProduct);
    }

    /**
     * Put Flattened Simple Product with client flattening true on the parameter.
     * 
     * @param maxProductDisplayName Display name of product.
     * @param genericValue Generic URL value.
     * @param odataValue URL value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleProduct> postFlattenedSimpleProductAsync(String maxProductDisplayName, String genericValue, String odataValue) {
        return postFlattenedSimpleProductWithResponseAsync(maxProductDisplayName, genericValue, odataValue)
            .flatMap((SimpleResponse<SimpleProduct> res) -> {
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
     * @param maxProductDisplayName Display name of product.
     * @param genericValue Generic URL value.
     * @param odataValue URL value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SimpleProduct postFlattenedSimpleProduct(String maxProductDisplayName, String genericValue, String odataValue) {
        return postFlattenedSimpleProductAsync(maxProductDisplayName, genericValue, odataValue).block();
    }

    /**
     * Put Simple Product with client flattening true on the model.
     * 
     * @param name 
     * @param maxProductDisplayName Display name of product.
     * @param genericValue Generic URL value.
     * @param odataValue URL value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SimpleProduct>> putSimpleProductWithGroupingWithResponseAsync(String name, String maxProductDisplayName, String genericValue, String odataValue) {
        if (this.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.getHost() is required and cannot be null.");
        }
        if (name == null) {
            throw new IllegalArgumentException("Parameter name is required and cannot be null.");
        }
        SimpleProduct simpleBodyProduct = null;
        if (maxProductDisplayName != null || genericValue != null || odataValue != null) {
            simpleBodyProduct = new SimpleProduct();
            simpleBodyProduct.setMaxProductDisplayName(maxProductDisplayName);
            simpleBodyProduct.setGenericValue(genericValue);
            simpleBodyProduct.setOdataValue(odataValue);
        }
        return service.putSimpleProductWithGrouping(this.getHost(), name, simpleBodyProduct);
    }

    /**
     * Put Simple Product with client flattening true on the model.
     * 
     * @param name 
     * @param maxProductDisplayName Display name of product.
     * @param genericValue Generic URL value.
     * @param odataValue URL value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleProduct> putSimpleProductWithGroupingAsync(String name, String maxProductDisplayName, String genericValue, String odataValue) {
        return putSimpleProductWithGroupingWithResponseAsync(name, maxProductDisplayName, genericValue, odataValue)
            .flatMap((SimpleResponse<SimpleProduct> res) -> {
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
     * @param name 
     * @param maxProductDisplayName Display name of product.
     * @param genericValue Generic URL value.
     * @param odataValue URL value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SimpleProduct putSimpleProductWithGrouping(String name, String maxProductDisplayName, String genericValue, String odataValue) {
        return putSimpleProductWithGroupingAsync(name, maxProductDisplayName, genericValue, odataValue).block();
    }
}
