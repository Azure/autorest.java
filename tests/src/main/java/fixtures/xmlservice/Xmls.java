package fixtures.xmlservice;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import fixtures.xmlservice.implementation.BananasWrapper;
import fixtures.xmlservice.implementation.SignedIdentifiersWrapper;
import fixtures.xmlservice.models.AppleBarrel;
import fixtures.xmlservice.models.Banana;
import fixtures.xmlservice.models.ErrorException;
import fixtures.xmlservice.models.JSONInput;
import fixtures.xmlservice.models.JSONOutput;
import fixtures.xmlservice.models.ListBlobsResponse;
import fixtures.xmlservice.models.ListContainersResponse;
import fixtures.xmlservice.models.RootWithRefAndMeta;
import fixtures.xmlservice.models.RootWithRefAndNoMeta;
import fixtures.xmlservice.models.SignedIdentifier;
import fixtures.xmlservice.models.Slideshow;
import fixtures.xmlservice.models.StorageServiceProperties;
import fixtures.xmlservice.models.XmlsGetHeadersResponse;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Xmls.
 */
public final class Xmls {
    /**
     * The proxy service used to perform REST calls.
     */
    private XmlsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestSwaggerBATXMLService client;

    /**
     * Initializes an instance of Xmls.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Xmls(AutoRestSwaggerBATXMLService client) {
        this.service = RestProxy.create(XmlsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestSwaggerBATXMLServiceXmls to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATXMLServiceXmls")
    private interface XmlsService {
        @Get("/xml/complex-type-ref-no-meta")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<RootWithRefAndNoMeta>> getComplexTypeRefNoMeta(@HostParam("$host") String host);

        @Put("/xml/complex-type-ref-no-meta")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putComplexTypeRefNoMeta(@HostParam("$host") String host, @BodyParam("application/xml") RootWithRefAndNoMeta model);

        @Get("/xml/complex-type-ref-with-meta")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<RootWithRefAndMeta>> getComplexTypeRefWithMeta(@HostParam("$host") String host);

        @Put("/xml/complex-type-ref-with-meta")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putComplexTypeRefWithMeta(@HostParam("$host") String host, @BodyParam("application/xml") RootWithRefAndMeta model);

        @Get("/xml/simple")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Slideshow>> getSimple(@HostParam("$host") String host);

        @Put("/xml/simple")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putSimple(@HostParam("$host") String host, @BodyParam("application/xml") Slideshow slideshow);

        @Get("/xml/wrapped-lists")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<AppleBarrel>> getWrappedLists(@HostParam("$host") String host);

        @Put("/xml/wrapped-lists")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putWrappedLists(@HostParam("$host") String host, @BodyParam("application/xml") AppleBarrel wrappedLists);

        @Get("/xml/headers")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<XmlsGetHeadersResponse> getHeaders(@HostParam("$host") String host);

        @Get("/xml/empty-list")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Slideshow>> getEmptyList(@HostParam("$host") String host);

        @Put("/xml/empty-list")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putEmptyList(@HostParam("$host") String host, @BodyParam("application/xml") Slideshow slideshow);

        @Get("/xml/empty-wrapped-lists")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<AppleBarrel>> getEmptyWrappedLists(@HostParam("$host") String host);

        @Put("/xml/empty-wrapped-lists")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putEmptyWrappedLists(@HostParam("$host") String host, @BodyParam("application/xml") AppleBarrel appleBarrel);

        @Get("/xml/root-list")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<List<Banana>>> getRootList(@HostParam("$host") String host);

        @Put("/xml/root-list")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putRootList(@HostParam("$host") String host, @BodyParam("application/xml") BananasWrapper bananas);

        @Get("/xml/root-list-single-item")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<List<Banana>>> getRootListSingleItem(@HostParam("$host") String host);

        @Put("/xml/root-list-single-item")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putRootListSingleItem(@HostParam("$host") String host, @BodyParam("application/xml") BananasWrapper bananas);

        @Get("/xml/empty-root-list")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<List<Banana>>> getEmptyRootList(@HostParam("$host") String host);

        @Put("/xml/empty-root-list")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putEmptyRootList(@HostParam("$host") String host, @BodyParam("application/xml") BananasWrapper bananas);

        @Get("/xml/empty-child-element")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Banana>> getEmptyChildElement(@HostParam("$host") String host);

        @Put("/xml/empty-child-element")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putEmptyChildElement(@HostParam("$host") String host, @BodyParam("application/xml") Banana banana);

        @Get("/xml/")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ListContainersResponse>> listContainers(@HostParam("$host") String host, @QueryParam("comp") String comp);

        @Get("/xml/")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<StorageServiceProperties>> getServiceProperties(@HostParam("$host") String host, @QueryParam("comp") String comp, @QueryParam("restype") String restype);

        @Put("/xml/")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putServiceProperties(@HostParam("$host") String host, @QueryParam("comp") String comp, @QueryParam("restype") String restype, @BodyParam("application/xml") StorageServiceProperties properties);

        @Get("/xml/mycontainer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<List<SignedIdentifier>>> getAcls(@HostParam("$host") String host, @QueryParam("comp") String comp, @QueryParam("restype") String restype);

        @Put("/xml/mycontainer")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putAcls(@HostParam("$host") String host, @QueryParam("comp") String comp, @QueryParam("restype") String restype, @BodyParam("application/xml") SignedIdentifiersWrapper properties);

        @Get("/xml/mycontainer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<ListBlobsResponse>> listBlobs(@HostParam("$host") String host, @QueryParam("comp") String comp, @QueryParam("restype") String restype);

        @Put("/xml/jsoninput")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> jsonInput(@HostParam("$host") String host, @BodyParam("application/json") JSONInput properties);

        @Get("/xml/jsonoutput")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<JSONOutput>> jsonOutput(@HostParam("$host") String host);
    }

    /**
     * Get a complex type that has a ref to a complex type with no XML node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<RootWithRefAndNoMeta>> getComplexTypeRefNoMetaWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getComplexTypeRefNoMeta(this.client.getHost());
    }

    /**
     * Get a complex type that has a ref to a complex type with no XML node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RootWithRefAndNoMeta> getComplexTypeRefNoMetaAsync() {
        return getComplexTypeRefNoMetaWithResponseAsync()
            .flatMap((SimpleResponse<RootWithRefAndNoMeta> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get a complex type that has a ref to a complex type with no XML node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public RootWithRefAndNoMeta getComplexTypeRefNoMeta() {
        return getComplexTypeRefNoMetaAsync().block();
    }

    /**
     * Puts a complex type that has a ref to a complex type with no XML node.
     * 
     * @param model I am root, and I ref a model with no meta.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplexTypeRefNoMetaWithResponseAsync(RootWithRefAndNoMeta model) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (model == null) {
            throw new IllegalArgumentException("Parameter model is required and cannot be null.");
        } else {
            model.validate();
        }
        return service.putComplexTypeRefNoMeta(this.client.getHost(), model);
    }

    /**
     * Puts a complex type that has a ref to a complex type with no XML node.
     * 
     * @param model I am root, and I ref a model with no meta.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplexTypeRefNoMetaAsync(RootWithRefAndNoMeta model) {
        return putComplexTypeRefNoMetaWithResponseAsync(model)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts a complex type that has a ref to a complex type with no XML node.
     * 
     * @param model I am root, and I ref a model with no meta.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putComplexTypeRefNoMeta(RootWithRefAndNoMeta model) {
        putComplexTypeRefNoMetaAsync(model).block();
    }

    /**
     * Get a complex type that has a ref to a complex type with XML node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<RootWithRefAndMeta>> getComplexTypeRefWithMetaWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getComplexTypeRefWithMeta(this.client.getHost());
    }

    /**
     * Get a complex type that has a ref to a complex type with XML node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RootWithRefAndMeta> getComplexTypeRefWithMetaAsync() {
        return getComplexTypeRefWithMetaWithResponseAsync()
            .flatMap((SimpleResponse<RootWithRefAndMeta> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get a complex type that has a ref to a complex type with XML node.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public RootWithRefAndMeta getComplexTypeRefWithMeta() {
        return getComplexTypeRefWithMetaAsync().block();
    }

    /**
     * Puts a complex type that has a ref to a complex type with XML node.
     * 
     * @param model I am root, and I ref a model WITH meta.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplexTypeRefWithMetaWithResponseAsync(RootWithRefAndMeta model) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (model == null) {
            throw new IllegalArgumentException("Parameter model is required and cannot be null.");
        } else {
            model.validate();
        }
        return service.putComplexTypeRefWithMeta(this.client.getHost(), model);
    }

    /**
     * Puts a complex type that has a ref to a complex type with XML node.
     * 
     * @param model I am root, and I ref a model WITH meta.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplexTypeRefWithMetaAsync(RootWithRefAndMeta model) {
        return putComplexTypeRefWithMetaWithResponseAsync(model)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts a complex type that has a ref to a complex type with XML node.
     * 
     * @param model I am root, and I ref a model WITH meta.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putComplexTypeRefWithMeta(RootWithRefAndMeta model) {
        putComplexTypeRefWithMetaAsync(model).block();
    }

    /**
     * Get a simple XML document.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Slideshow>> getSimpleWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getSimple(this.client.getHost());
    }

    /**
     * Get a simple XML document.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Slideshow> getSimpleAsync() {
        return getSimpleWithResponseAsync()
            .flatMap((SimpleResponse<Slideshow> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get a simple XML document.
     * 
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Slideshow getSimple() {
        return getSimpleAsync().block();
    }

    /**
     * Put a simple XML document.
     * 
     * @param slideshow Data about a slideshow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putSimpleWithResponseAsync(Slideshow slideshow) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (slideshow == null) {
            throw new IllegalArgumentException("Parameter slideshow is required and cannot be null.");
        } else {
            slideshow.validate();
        }
        return service.putSimple(this.client.getHost(), slideshow);
    }

    /**
     * Put a simple XML document.
     * 
     * @param slideshow Data about a slideshow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putSimpleAsync(Slideshow slideshow) {
        return putSimpleWithResponseAsync(slideshow)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put a simple XML document.
     * 
     * @param slideshow Data about a slideshow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putSimple(Slideshow slideshow) {
        putSimpleAsync(slideshow).block();
    }

    /**
     * Get an XML document with multiple wrapped lists.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<AppleBarrel>> getWrappedListsWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getWrappedLists(this.client.getHost());
    }

    /**
     * Get an XML document with multiple wrapped lists.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<AppleBarrel> getWrappedListsAsync() {
        return getWrappedListsWithResponseAsync()
            .flatMap((SimpleResponse<AppleBarrel> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get an XML document with multiple wrapped lists.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public AppleBarrel getWrappedLists() {
        return getWrappedListsAsync().block();
    }

    /**
     * Put an XML document with multiple wrapped lists.
     * 
     * @param wrappedLists A barrel of apples.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWrappedListsWithResponseAsync(AppleBarrel wrappedLists) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (wrappedLists == null) {
            throw new IllegalArgumentException("Parameter wrappedLists is required and cannot be null.");
        } else {
            wrappedLists.validate();
        }
        return service.putWrappedLists(this.client.getHost(), wrappedLists);
    }

    /**
     * Put an XML document with multiple wrapped lists.
     * 
     * @param wrappedLists A barrel of apples.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putWrappedListsAsync(AppleBarrel wrappedLists) {
        return putWrappedListsWithResponseAsync(wrappedLists)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put an XML document with multiple wrapped lists.
     * 
     * @param wrappedLists A barrel of apples.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putWrappedLists(AppleBarrel wrappedLists) {
        putWrappedListsAsync(wrappedLists).block();
    }

    /**
     * Get strongly-typed response headers.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<XmlsGetHeadersResponse> getHeadersWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getHeaders(this.client.getHost());
    }

    /**
     * Get strongly-typed response headers.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getHeadersAsync() {
        return getHeadersWithResponseAsync()
            .flatMap((XmlsGetHeadersResponse res) -> Mono.empty());
    }

    /**
     * Get strongly-typed response headers.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void getHeaders() {
        getHeadersAsync().block();
    }

    /**
     * Get an empty list.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Slideshow>> getEmptyListWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getEmptyList(this.client.getHost());
    }

    /**
     * Get an empty list.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Slideshow> getEmptyListAsync() {
        return getEmptyListWithResponseAsync()
            .flatMap((SimpleResponse<Slideshow> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Get an empty list.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Slideshow getEmptyList() {
        return getEmptyListAsync().block();
    }

    /**
     * Puts an empty list.
     * 
     * @param slideshow Data about a slideshow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyListWithResponseAsync(Slideshow slideshow) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (slideshow == null) {
            throw new IllegalArgumentException("Parameter slideshow is required and cannot be null.");
        } else {
            slideshow.validate();
        }
        return service.putEmptyList(this.client.getHost(), slideshow);
    }

    /**
     * Puts an empty list.
     * 
     * @param slideshow Data about a slideshow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyListAsync(Slideshow slideshow) {
        return putEmptyListWithResponseAsync(slideshow)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts an empty list.
     * 
     * @param slideshow Data about a slideshow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmptyList(Slideshow slideshow) {
        putEmptyListAsync(slideshow).block();
    }

    /**
     * Gets some empty wrapped lists.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<AppleBarrel>> getEmptyWrappedListsWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getEmptyWrappedLists(this.client.getHost());
    }

    /**
     * Gets some empty wrapped lists.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<AppleBarrel> getEmptyWrappedListsAsync() {
        return getEmptyWrappedListsWithResponseAsync()
            .flatMap((SimpleResponse<AppleBarrel> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets some empty wrapped lists.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public AppleBarrel getEmptyWrappedLists() {
        return getEmptyWrappedListsAsync().block();
    }

    /**
     * Puts some empty wrapped lists.
     * 
     * @param appleBarrel A barrel of apples.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWrappedListsWithResponseAsync(AppleBarrel appleBarrel) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (appleBarrel == null) {
            throw new IllegalArgumentException("Parameter appleBarrel is required and cannot be null.");
        } else {
            appleBarrel.validate();
        }
        return service.putEmptyWrappedLists(this.client.getHost(), appleBarrel);
    }

    /**
     * Puts some empty wrapped lists.
     * 
     * @param appleBarrel A barrel of apples.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyWrappedListsAsync(AppleBarrel appleBarrel) {
        return putEmptyWrappedListsWithResponseAsync(appleBarrel)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts some empty wrapped lists.
     * 
     * @param appleBarrel A barrel of apples.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmptyWrappedLists(AppleBarrel appleBarrel) {
        putEmptyWrappedListsAsync(appleBarrel).block();
    }

    /**
     * Gets a list as the root element.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<List<Banana>>> getRootListWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getRootList(this.client.getHost());
    }

    /**
     * Gets a list as the root element.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Banana>> getRootListAsync() {
        return getRootListWithResponseAsync()
            .flatMap((SimpleResponse<List<Banana>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets a list as the root element.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Banana> getRootList() {
        return getRootListAsync().block();
    }

    /**
     * Puts a list as the root element.
     * 
     * @param bananas MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putRootListWithResponseAsync(List<Banana> bananas) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bananas == null) {
            throw new IllegalArgumentException("Parameter bananas is required and cannot be null.");
        } else {
            bananas.forEach(e -> e.validate());
        }
        BananasWrapper bananasConverted = new BananasWrapper(bananas);
        return service.putRootList(this.client.getHost(), bananasConverted);
    }

    /**
     * Puts a list as the root element.
     * 
     * @param bananas MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putRootListAsync(List<Banana> bananas) {
        return putRootListWithResponseAsync(bananas)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts a list as the root element.
     * 
     * @param bananas MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putRootList(List<Banana> bananas) {
        putRootListAsync(bananas).block();
    }

    /**
     * Gets a list with a single item.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<List<Banana>>> getRootListSingleItemWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getRootListSingleItem(this.client.getHost());
    }

    /**
     * Gets a list with a single item.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Banana>> getRootListSingleItemAsync() {
        return getRootListSingleItemWithResponseAsync()
            .flatMap((SimpleResponse<List<Banana>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets a list with a single item.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Banana> getRootListSingleItem() {
        return getRootListSingleItemAsync().block();
    }

    /**
     * Puts a list with a single item.
     * 
     * @param bananas MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putRootListSingleItemWithResponseAsync(List<Banana> bananas) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bananas == null) {
            throw new IllegalArgumentException("Parameter bananas is required and cannot be null.");
        } else {
            bananas.forEach(e -> e.validate());
        }
        BananasWrapper bananasConverted = new BananasWrapper(bananas);
        return service.putRootListSingleItem(this.client.getHost(), bananasConverted);
    }

    /**
     * Puts a list with a single item.
     * 
     * @param bananas MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putRootListSingleItemAsync(List<Banana> bananas) {
        return putRootListSingleItemWithResponseAsync(bananas)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts a list with a single item.
     * 
     * @param bananas MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putRootListSingleItem(List<Banana> bananas) {
        putRootListSingleItemAsync(bananas).block();
    }

    /**
     * Gets an empty list as the root element.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<List<Banana>>> getEmptyRootListWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getEmptyRootList(this.client.getHost());
    }

    /**
     * Gets an empty list as the root element.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Banana>> getEmptyRootListAsync() {
        return getEmptyRootListWithResponseAsync()
            .flatMap((SimpleResponse<List<Banana>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets an empty list as the root element.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Banana> getEmptyRootList() {
        return getEmptyRootListAsync().block();
    }

    /**
     * Puts an empty list as the root element.
     * 
     * @param bananas MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyRootListWithResponseAsync(List<Banana> bananas) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bananas == null) {
            throw new IllegalArgumentException("Parameter bananas is required and cannot be null.");
        } else {
            bananas.forEach(e -> e.validate());
        }
        BananasWrapper bananasConverted = new BananasWrapper(bananas);
        return service.putEmptyRootList(this.client.getHost(), bananasConverted);
    }

    /**
     * Puts an empty list as the root element.
     * 
     * @param bananas MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyRootListAsync(List<Banana> bananas) {
        return putEmptyRootListWithResponseAsync(bananas)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts an empty list as the root element.
     * 
     * @param bananas MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmptyRootList(List<Banana> bananas) {
        putEmptyRootListAsync(bananas).block();
    }

    /**
     * Gets an XML document with an empty child element.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Banana>> getEmptyChildElementWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.getEmptyChildElement(this.client.getHost());
    }

    /**
     * Gets an XML document with an empty child element.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Banana> getEmptyChildElementAsync() {
        return getEmptyChildElementWithResponseAsync()
            .flatMap((SimpleResponse<Banana> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets an XML document with an empty child element.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Banana getEmptyChildElement() {
        return getEmptyChildElementAsync().block();
    }

    /**
     * Puts a value with an empty child element.
     * 
     * @param banana A banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyChildElementWithResponseAsync(Banana banana) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (banana == null) {
            throw new IllegalArgumentException("Parameter banana is required and cannot be null.");
        } else {
            banana.validate();
        }
        return service.putEmptyChildElement(this.client.getHost(), banana);
    }

    /**
     * Puts a value with an empty child element.
     * 
     * @param banana A banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyChildElementAsync(Banana banana) {
        return putEmptyChildElementWithResponseAsync(banana)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts a value with an empty child element.
     * 
     * @param banana A banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmptyChildElement(Banana banana) {
        putEmptyChildElementAsync(banana).block();
    }

    /**
     * Lists containers in a storage account.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ListContainersResponse>> listContainersWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String comp = "list";
        return service.listContainers(this.client.getHost(), comp);
    }

    /**
     * Lists containers in a storage account.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ListContainersResponse> listContainersAsync() {
        return listContainersWithResponseAsync()
            .flatMap((SimpleResponse<ListContainersResponse> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Lists containers in a storage account.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ListContainersResponse listContainers() {
        return listContainersAsync().block();
    }

    /**
     * Gets storage service properties.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<StorageServiceProperties>> getServicePropertiesWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String comp = "properties";
        final String restype = "service";
        return service.getServiceProperties(this.client.getHost(), comp, restype);
    }

    /**
     * Gets storage service properties.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StorageServiceProperties> getServicePropertiesAsync() {
        return getServicePropertiesWithResponseAsync()
            .flatMap((SimpleResponse<StorageServiceProperties> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets storage service properties.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public StorageServiceProperties getServiceProperties() {
        return getServicePropertiesAsync().block();
    }

    /**
     * Puts storage service properties.
     * 
     * @param properties Storage Service Properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putServicePropertiesWithResponseAsync(StorageServiceProperties properties) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (properties == null) {
            throw new IllegalArgumentException("Parameter properties is required and cannot be null.");
        } else {
            properties.validate();
        }
        final String comp = "properties";
        final String restype = "service";
        return service.putServiceProperties(this.client.getHost(), comp, restype, properties);
    }

    /**
     * Puts storage service properties.
     * 
     * @param properties Storage Service Properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putServicePropertiesAsync(StorageServiceProperties properties) {
        return putServicePropertiesWithResponseAsync(properties)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts storage service properties.
     * 
     * @param properties Storage Service Properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putServiceProperties(StorageServiceProperties properties) {
        putServicePropertiesAsync(properties).block();
    }

    /**
     * Gets storage ACLs for a container.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<List<SignedIdentifier>>> getAclsWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String comp = "acl";
        final String restype = "container";
        return service.getAcls(this.client.getHost(), comp, restype);
    }

    /**
     * Gets storage ACLs for a container.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<SignedIdentifier>> getAclsAsync() {
        return getAclsWithResponseAsync()
            .flatMap((SimpleResponse<List<SignedIdentifier>> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Gets storage ACLs for a container.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<SignedIdentifier> getAcls() {
        return getAclsAsync().block();
    }

    /**
     * Puts storage ACLs for a container.
     * 
     * @param properties a collection of signed identifiers.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putAclsWithResponseAsync(List<SignedIdentifier> properties) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (properties == null) {
            throw new IllegalArgumentException("Parameter properties is required and cannot be null.");
        } else {
            properties.forEach(e -> e.validate());
        }
        final String comp = "acl";
        final String restype = "container";
        SignedIdentifiersWrapper propertiesConverted = new SignedIdentifiersWrapper(properties);
        return service.putAcls(this.client.getHost(), comp, restype, propertiesConverted);
    }

    /**
     * Puts storage ACLs for a container.
     * 
     * @param properties a collection of signed identifiers.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putAclsAsync(List<SignedIdentifier> properties) {
        return putAclsWithResponseAsync(properties)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts storage ACLs for a container.
     * 
     * @param properties a collection of signed identifiers.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putAcls(List<SignedIdentifier> properties) {
        putAclsAsync(properties).block();
    }

    /**
     * Lists blobs in a storage container.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<ListBlobsResponse>> listBlobsWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        final String comp = "list";
        final String restype = "container";
        return service.listBlobs(this.client.getHost(), comp, restype);
    }

    /**
     * Lists blobs in a storage container.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ListBlobsResponse> listBlobsAsync() {
        return listBlobsWithResponseAsync()
            .flatMap((SimpleResponse<ListBlobsResponse> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Lists blobs in a storage container.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ListBlobsResponse listBlobs() {
        return listBlobsAsync().block();
    }

    /**
     * A Swagger with XML that has one operation that takes JSON as input. You need to send the ID number 42.
     * 
     * @param properties MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> jsonInputWithResponseAsync(JSONInput properties) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (properties == null) {
            throw new IllegalArgumentException("Parameter properties is required and cannot be null.");
        } else {
            properties.validate();
        }
        return service.jsonInput(this.client.getHost(), properties);
    }

    /**
     * A Swagger with XML that has one operation that takes JSON as input. You need to send the ID number 42.
     * 
     * @param properties MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> jsonInputAsync(JSONInput properties) {
        return jsonInputWithResponseAsync(properties)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * A Swagger with XML that has one operation that takes JSON as input. You need to send the ID number 42.
     * 
     * @param properties MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void jsonInput(JSONInput properties) {
        jsonInputAsync(properties).block();
    }

    /**
     * A Swagger with XML that has one operation that returns JSON. ID number 42.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<JSONOutput>> jsonOutputWithResponseAsync() {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.jsonOutput(this.client.getHost());
    }

    /**
     * A Swagger with XML that has one operation that returns JSON. ID number 42.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<JSONOutput> jsonOutputAsync() {
        return jsonOutputWithResponseAsync()
            .flatMap((SimpleResponse<JSONOutput> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * A Swagger with XML that has one operation that returns JSON. ID number 42.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public JSONOutput jsonOutput() {
        return jsonOutputAsync().block();
    }
}
