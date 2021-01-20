package fixtures.xmlservice;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
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
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.xmlservice.implementation.BananasWrapper;
import fixtures.xmlservice.implementation.SignedIdentifiersWrapper;
import fixtures.xmlservice.models.AppleBarrel;
import fixtures.xmlservice.models.Banana;
import fixtures.xmlservice.models.ErrorException;
import fixtures.xmlservice.models.JsonInput;
import fixtures.xmlservice.models.JsonOutput;
import fixtures.xmlservice.models.ListBlobsResponse;
import fixtures.xmlservice.models.ListContainersResponse;
import fixtures.xmlservice.models.ObjectWithXMsTextProperty;
import fixtures.xmlservice.models.RootWithRefAndMeta;
import fixtures.xmlservice.models.RootWithRefAndNoMeta;
import fixtures.xmlservice.models.SignedIdentifier;
import fixtures.xmlservice.models.Slideshow;
import fixtures.xmlservice.models.StorageServiceProperties;
import fixtures.xmlservice.models.XmlsGetHeadersResponse;
import java.util.List;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Xmls. */
public final class Xmls {
    /** The proxy service used to perform REST calls. */
    private final XmlsService service;

    /** The service client containing this operation class. */
    private final AutoRestSwaggerBATXMLService client;

    /**
     * Initializes an instance of Xmls.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Xmls(AutoRestSwaggerBATXMLService client) {
        this.service = RestProxy.create(XmlsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestSwaggerBATXMLServiceXmls to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestSwaggerBATXM")
    private interface XmlsService {
        @Get("/xml/complex-type-ref-no-meta")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<RootWithRefAndNoMeta>> getComplexTypeRefNoMeta(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/complex-type-ref-no-meta")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putComplexTypeRefNoMeta(
                @HostParam("$host") String host,
                @BodyParam("application/xml") RootWithRefAndNoMeta model,
                Context context);

        @Get("/xml/complex-type-ref-with-meta")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<RootWithRefAndMeta>> getComplexTypeRefWithMeta(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/complex-type-ref-with-meta")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putComplexTypeRefWithMeta(
                @HostParam("$host") String host,
                @BodyParam("application/xml") RootWithRefAndMeta model,
                Context context);

        @Get("/xml/simple")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Slideshow>> getSimple(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/simple")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putSimple(
                @HostParam("$host") String host,
                @BodyParam("application/xml") Slideshow slideshow,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/xml/wrapped-lists")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<AppleBarrel>> getWrappedLists(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/wrapped-lists")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putWrappedLists(
                @HostParam("$host") String host,
                @BodyParam("application/xml") AppleBarrel wrappedLists,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/xml/headers")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<XmlsGetHeadersResponse> getHeaders(@HostParam("$host") String host, Context context);

        @Get("/xml/empty-list")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Slideshow>> getEmptyList(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/empty-list")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putEmptyList(
                @HostParam("$host") String host, @BodyParam("application/xml") Slideshow slideshow, Context context);

        @Get("/xml/empty-wrapped-lists")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<AppleBarrel>> getEmptyWrappedLists(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/empty-wrapped-lists")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putEmptyWrappedLists(
                @HostParam("$host") String host,
                @BodyParam("application/xml") AppleBarrel appleBarrel,
                Context context);

        @Get("/xml/root-list")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<List<Banana>>> getRootList(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/root-list")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putRootList(
                @HostParam("$host") String host, @BodyParam("application/xml") BananasWrapper bananas, Context context);

        @Get("/xml/root-list-single-item")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<List<Banana>>> getRootListSingleItem(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/root-list-single-item")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putRootListSingleItem(
                @HostParam("$host") String host, @BodyParam("application/xml") BananasWrapper bananas, Context context);

        @Get("/xml/empty-root-list")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<List<Banana>>> getEmptyRootList(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/empty-root-list")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putEmptyRootList(
                @HostParam("$host") String host, @BodyParam("application/xml") BananasWrapper bananas, Context context);

        @Get("/xml/empty-child-element")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Banana>> getEmptyChildElement(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/xml/empty-child-element")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putEmptyChildElement(
                @HostParam("$host") String host, @BodyParam("application/xml") Banana banana, Context context);

        @Get("/xml/")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ListContainersResponse>> listContainers(
                @HostParam("$host") String host,
                @QueryParam("comp") String comp,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/xml/")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<StorageServiceProperties>> getServiceProperties(
                @HostParam("$host") String host,
                @QueryParam("comp") String comp,
                @QueryParam("restype") String restype,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/xml/")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putServiceProperties(
                @HostParam("$host") String host,
                @QueryParam("comp") String comp,
                @QueryParam("restype") String restype,
                @BodyParam("application/xml") StorageServiceProperties properties,
                Context context);

        @Get("/xml/mycontainer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<List<SignedIdentifier>>> getAcls(
                @HostParam("$host") String host,
                @QueryParam("comp") String comp,
                @QueryParam("restype") String restype,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/xml/mycontainer")
        @ExpectedResponses({201})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> putAcls(
                @HostParam("$host") String host,
                @QueryParam("comp") String comp,
                @QueryParam("restype") String restype,
                @BodyParam("application/xml") SignedIdentifiersWrapper properties,
                Context context);

        @Get("/xml/mycontainer")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ListBlobsResponse>> listBlobs(
                @HostParam("$host") String host,
                @QueryParam("comp") String comp,
                @QueryParam("restype") String restype,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/xml/jsoninput")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> jsonInput(
                @HostParam("$host") String host, @BodyParam("application/json") JsonInput properties, Context context);

        @Get("/xml/jsonoutput")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<JsonOutput>> jsonOutput(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/xml/x-ms-text")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<ObjectWithXMsTextProperty>> getXMsText(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get a complex type that has a ref to a complex type with no XML node.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a complex type that has a ref to a complex type with no XML node.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<RootWithRefAndNoMeta>> getComplexTypeRefNoMetaWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getComplexTypeRefNoMeta(this.client.getHost(), accept, context));
    }

    /**
     * Get a complex type that has a ref to a complex type with no XML node.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a complex type that has a ref to a complex type with no XML node.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RootWithRefAndNoMeta> getComplexTypeRefNoMetaAsync() {
        return getComplexTypeRefNoMetaWithResponseAsync()
                .flatMap(
                        (Response<RootWithRefAndNoMeta> res) -> {
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
     * @return a complex type that has a ref to a complex type with no XML node.
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplexTypeRefNoMetaWithResponseAsync(RootWithRefAndNoMeta model) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (model == null) {
            return Mono.error(new IllegalArgumentException("Parameter model is required and cannot be null."));
        } else {
            model.validate();
        }
        return FluxUtil.withContext(context -> service.putComplexTypeRefNoMeta(this.client.getHost(), model, context));
    }

    /**
     * Puts a complex type that has a ref to a complex type with no XML node.
     *
     * @param model I am root, and I ref a model with no meta.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplexTypeRefNoMetaAsync(RootWithRefAndNoMeta model) {
        return putComplexTypeRefNoMetaWithResponseAsync(model).flatMap((Response<Void> res) -> Mono.empty());
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
     * @return a complex type that has a ref to a complex type with XML node.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<RootWithRefAndMeta>> getComplexTypeRefWithMetaWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(
                context -> service.getComplexTypeRefWithMeta(this.client.getHost(), accept, context));
    }

    /**
     * Get a complex type that has a ref to a complex type with XML node.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a complex type that has a ref to a complex type with XML node.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RootWithRefAndMeta> getComplexTypeRefWithMetaAsync() {
        return getComplexTypeRefWithMetaWithResponseAsync()
                .flatMap(
                        (Response<RootWithRefAndMeta> res) -> {
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
     * @return a complex type that has a ref to a complex type with XML node.
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplexTypeRefWithMetaWithResponseAsync(RootWithRefAndMeta model) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (model == null) {
            return Mono.error(new IllegalArgumentException("Parameter model is required and cannot be null."));
        } else {
            model.validate();
        }
        return FluxUtil.withContext(
                context -> service.putComplexTypeRefWithMeta(this.client.getHost(), model, context));
    }

    /**
     * Puts a complex type that has a ref to a complex type with XML node.
     *
     * @param model I am root, and I ref a model WITH meta.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplexTypeRefWithMetaAsync(RootWithRefAndMeta model) {
        return putComplexTypeRefWithMetaWithResponseAsync(model).flatMap((Response<Void> res) -> Mono.empty());
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
     * @return a simple XML document.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Slideshow>> getSimpleWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getSimple(this.client.getHost(), accept, context));
    }

    /**
     * Get a simple XML document.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a simple XML document.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Slideshow> getSimpleAsync() {
        return getSimpleWithResponseAsync()
                .flatMap(
                        (Response<Slideshow> res) -> {
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
     * @return a simple XML document.
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putSimpleWithResponseAsync(Slideshow slideshow) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (slideshow == null) {
            return Mono.error(new IllegalArgumentException("Parameter slideshow is required and cannot be null."));
        } else {
            slideshow.validate();
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.putSimple(this.client.getHost(), slideshow, accept, context));
    }

    /**
     * Put a simple XML document.
     *
     * @param slideshow Data about a slideshow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putSimpleAsync(Slideshow slideshow) {
        return putSimpleWithResponseAsync(slideshow).flatMap((Response<Void> res) -> Mono.empty());
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
     * @return an XML document with multiple wrapped lists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<AppleBarrel>> getWrappedListsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getWrappedLists(this.client.getHost(), accept, context));
    }

    /**
     * Get an XML document with multiple wrapped lists.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an XML document with multiple wrapped lists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<AppleBarrel> getWrappedListsAsync() {
        return getWrappedListsWithResponseAsync()
                .flatMap(
                        (Response<AppleBarrel> res) -> {
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
     * @return an XML document with multiple wrapped lists.
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putWrappedListsWithResponseAsync(AppleBarrel wrappedLists) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (wrappedLists == null) {
            return Mono.error(new IllegalArgumentException("Parameter wrappedLists is required and cannot be null."));
        } else {
            wrappedLists.validate();
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(
                context -> service.putWrappedLists(this.client.getHost(), wrappedLists, accept, context));
    }

    /**
     * Put an XML document with multiple wrapped lists.
     *
     * @param wrappedLists A barrel of apples.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putWrappedListsAsync(AppleBarrel wrappedLists) {
        return putWrappedListsWithResponseAsync(wrappedLists).flatMap((Response<Void> res) -> Mono.empty());
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
     * @return strongly-typed response headers.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<XmlsGetHeadersResponse> getHeadersWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getHeaders(this.client.getHost(), context));
    }

    /**
     * Get strongly-typed response headers.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return strongly-typed response headers.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> getHeadersAsync() {
        return getHeadersWithResponseAsync().flatMap((XmlsGetHeadersResponse res) -> Mono.empty());
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
     * @return an empty list.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Slideshow>> getEmptyListWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getEmptyList(this.client.getHost(), accept, context));
    }

    /**
     * Get an empty list.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty list.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Slideshow> getEmptyListAsync() {
        return getEmptyListWithResponseAsync()
                .flatMap(
                        (Response<Slideshow> res) -> {
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
     * @return an empty list.
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyListWithResponseAsync(Slideshow slideshow) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (slideshow == null) {
            return Mono.error(new IllegalArgumentException("Parameter slideshow is required and cannot be null."));
        } else {
            slideshow.validate();
        }
        return FluxUtil.withContext(context -> service.putEmptyList(this.client.getHost(), slideshow, context));
    }

    /**
     * Puts an empty list.
     *
     * @param slideshow Data about a slideshow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyListAsync(Slideshow slideshow) {
        return putEmptyListWithResponseAsync(slideshow).flatMap((Response<Void> res) -> Mono.empty());
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
     * @return some empty wrapped lists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<AppleBarrel>> getEmptyWrappedListsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getEmptyWrappedLists(this.client.getHost(), accept, context));
    }

    /**
     * Gets some empty wrapped lists.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return some empty wrapped lists.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<AppleBarrel> getEmptyWrappedListsAsync() {
        return getEmptyWrappedListsWithResponseAsync()
                .flatMap(
                        (Response<AppleBarrel> res) -> {
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
     * @return some empty wrapped lists.
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWrappedListsWithResponseAsync(AppleBarrel appleBarrel) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (appleBarrel == null) {
            return Mono.error(new IllegalArgumentException("Parameter appleBarrel is required and cannot be null."));
        } else {
            appleBarrel.validate();
        }
        return FluxUtil.withContext(
                context -> service.putEmptyWrappedLists(this.client.getHost(), appleBarrel, context));
    }

    /**
     * Puts some empty wrapped lists.
     *
     * @param appleBarrel A barrel of apples.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyWrappedListsAsync(AppleBarrel appleBarrel) {
        return putEmptyWrappedListsWithResponseAsync(appleBarrel).flatMap((Response<Void> res) -> Mono.empty());
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
     * @return a list as the root element.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Banana>>> getRootListWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getRootList(this.client.getHost(), accept, context));
    }

    /**
     * Gets a list as the root element.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list as the root element.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Banana>> getRootListAsync() {
        return getRootListWithResponseAsync()
                .flatMap(
                        (Response<List<Banana>> res) -> {
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
     * @return a list as the root element.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Banana> getRootList() {
        return getRootListAsync().block();
    }

    /**
     * Puts a list as the root element.
     *
     * @param bananas Array of Banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putRootListWithResponseAsync(List<Banana> bananas) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (bananas == null) {
            return Mono.error(new IllegalArgumentException("Parameter bananas is required and cannot be null."));
        } else {
            bananas.forEach(e -> e.validate());
        }
        BananasWrapper bananasConverted = new BananasWrapper(bananas);
        return FluxUtil.withContext(context -> service.putRootList(this.client.getHost(), bananasConverted, context));
    }

    /**
     * Puts a list as the root element.
     *
     * @param bananas Array of Banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putRootListAsync(List<Banana> bananas) {
        return putRootListWithResponseAsync(bananas).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts a list as the root element.
     *
     * @param bananas Array of Banana.
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
     * @return a list with a single item.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Banana>>> getRootListSingleItemWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getRootListSingleItem(this.client.getHost(), accept, context));
    }

    /**
     * Gets a list with a single item.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list with a single item.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Banana>> getRootListSingleItemAsync() {
        return getRootListSingleItemWithResponseAsync()
                .flatMap(
                        (Response<List<Banana>> res) -> {
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
     * @return a list with a single item.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Banana> getRootListSingleItem() {
        return getRootListSingleItemAsync().block();
    }

    /**
     * Puts a list with a single item.
     *
     * @param bananas Array of Banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putRootListSingleItemWithResponseAsync(List<Banana> bananas) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (bananas == null) {
            return Mono.error(new IllegalArgumentException("Parameter bananas is required and cannot be null."));
        } else {
            bananas.forEach(e -> e.validate());
        }
        BananasWrapper bananasConverted = new BananasWrapper(bananas);
        return FluxUtil.withContext(
                context -> service.putRootListSingleItem(this.client.getHost(), bananasConverted, context));
    }

    /**
     * Puts a list with a single item.
     *
     * @param bananas Array of Banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putRootListSingleItemAsync(List<Banana> bananas) {
        return putRootListSingleItemWithResponseAsync(bananas).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts a list with a single item.
     *
     * @param bananas Array of Banana.
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
     * @return an empty list as the root element.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<Banana>>> getEmptyRootListWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getEmptyRootList(this.client.getHost(), accept, context));
    }

    /**
     * Gets an empty list as the root element.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an empty list as the root element.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<Banana>> getEmptyRootListAsync() {
        return getEmptyRootListWithResponseAsync()
                .flatMap(
                        (Response<List<Banana>> res) -> {
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
     * @return an empty list as the root element.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<Banana> getEmptyRootList() {
        return getEmptyRootListAsync().block();
    }

    /**
     * Puts an empty list as the root element.
     *
     * @param bananas Array of Banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyRootListWithResponseAsync(List<Banana> bananas) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (bananas == null) {
            return Mono.error(new IllegalArgumentException("Parameter bananas is required and cannot be null."));
        } else {
            bananas.forEach(e -> e.validate());
        }
        BananasWrapper bananasConverted = new BananasWrapper(bananas);
        return FluxUtil.withContext(
                context -> service.putEmptyRootList(this.client.getHost(), bananasConverted, context));
    }

    /**
     * Puts an empty list as the root element.
     *
     * @param bananas Array of Banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyRootListAsync(List<Banana> bananas) {
        return putEmptyRootListWithResponseAsync(bananas).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Puts an empty list as the root element.
     *
     * @param bananas Array of Banana.
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
     * @return an XML document with an empty child element.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Banana>> getEmptyChildElementWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getEmptyChildElement(this.client.getHost(), accept, context));
    }

    /**
     * Gets an XML document with an empty child element.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an XML document with an empty child element.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Banana> getEmptyChildElementAsync() {
        return getEmptyChildElementWithResponseAsync()
                .flatMap(
                        (Response<Banana> res) -> {
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
     * @return an XML document with an empty child element.
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyChildElementWithResponseAsync(Banana banana) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (banana == null) {
            return Mono.error(new IllegalArgumentException("Parameter banana is required and cannot be null."));
        } else {
            banana.validate();
        }
        return FluxUtil.withContext(context -> service.putEmptyChildElement(this.client.getHost(), banana, context));
    }

    /**
     * Puts a value with an empty child element.
     *
     * @param banana A banana.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyChildElementAsync(Banana banana) {
        return putEmptyChildElementWithResponseAsync(banana).flatMap((Response<Void> res) -> Mono.empty());
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
     * @return an enumeration of containers.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ListContainersResponse>> listContainersWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String comp = "list";
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.listContainers(this.client.getHost(), comp, accept, context));
    }

    /**
     * Lists containers in a storage account.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an enumeration of containers.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ListContainersResponse> listContainersAsync() {
        return listContainersWithResponseAsync()
                .flatMap(
                        (Response<ListContainersResponse> res) -> {
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
     * @return an enumeration of containers.
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
     * @return storage service properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<StorageServiceProperties>> getServicePropertiesWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String comp = "properties";
        final String restype = "service";
        final String accept = "application/xml";
        return FluxUtil.withContext(
                context -> service.getServiceProperties(this.client.getHost(), comp, restype, accept, context));
    }

    /**
     * Gets storage service properties.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return storage service properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<StorageServiceProperties> getServicePropertiesAsync() {
        return getServicePropertiesWithResponseAsync()
                .flatMap(
                        (Response<StorageServiceProperties> res) -> {
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
     * @return storage service properties.
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putServicePropertiesWithResponseAsync(StorageServiceProperties properties) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (properties == null) {
            return Mono.error(new IllegalArgumentException("Parameter properties is required and cannot be null."));
        } else {
            properties.validate();
        }
        final String comp = "properties";
        final String restype = "service";
        return FluxUtil.withContext(
                context -> service.putServiceProperties(this.client.getHost(), comp, restype, properties, context));
    }

    /**
     * Puts storage service properties.
     *
     * @param properties Storage Service Properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putServicePropertiesAsync(StorageServiceProperties properties) {
        return putServicePropertiesWithResponseAsync(properties).flatMap((Response<Void> res) -> Mono.empty());
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
     * @return storage ACLs for a container.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<SignedIdentifier>>> getAclsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String comp = "acl";
        final String restype = "container";
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getAcls(this.client.getHost(), comp, restype, accept, context));
    }

    /**
     * Gets storage ACLs for a container.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return storage ACLs for a container.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<SignedIdentifier>> getAclsAsync() {
        return getAclsWithResponseAsync()
                .flatMap(
                        (Response<List<SignedIdentifier>> res) -> {
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
     * @return storage ACLs for a container.
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
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putAclsWithResponseAsync(List<SignedIdentifier> properties) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (properties == null) {
            return Mono.error(new IllegalArgumentException("Parameter properties is required and cannot be null."));
        } else {
            properties.forEach(e -> e.validate());
        }
        final String comp = "acl";
        final String restype = "container";
        SignedIdentifiersWrapper propertiesConverted = new SignedIdentifiersWrapper(properties);
        return FluxUtil.withContext(
                context -> service.putAcls(this.client.getHost(), comp, restype, propertiesConverted, context));
    }

    /**
     * Puts storage ACLs for a container.
     *
     * @param properties a collection of signed identifiers.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putAclsAsync(List<SignedIdentifier> properties) {
        return putAclsWithResponseAsync(properties).flatMap((Response<Void> res) -> Mono.empty());
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
     * @return an enumeration of blobs.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ListBlobsResponse>> listBlobsWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String comp = "list";
        final String restype = "container";
        final String accept = "application/xml";
        return FluxUtil.withContext(
                context -> service.listBlobs(this.client.getHost(), comp, restype, accept, context));
    }

    /**
     * Lists blobs in a storage container.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return an enumeration of blobs.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ListBlobsResponse> listBlobsAsync() {
        return listBlobsWithResponseAsync()
                .flatMap(
                        (Response<ListBlobsResponse> res) -> {
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
     * @return an enumeration of blobs.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ListBlobsResponse listBlobs() {
        return listBlobsAsync().block();
    }

    /**
     * A Swagger with XML that has one operation that takes JSON as input. You need to send the ID number 42.
     *
     * @param properties The properties parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> jsonInputWithResponseAsync(JsonInput properties) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (properties == null) {
            return Mono.error(new IllegalArgumentException("Parameter properties is required and cannot be null."));
        } else {
            properties.validate();
        }
        return FluxUtil.withContext(context -> service.jsonInput(this.client.getHost(), properties, context));
    }

    /**
     * A Swagger with XML that has one operation that takes JSON as input. You need to send the ID number 42.
     *
     * @param properties The properties parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> jsonInputAsync(JsonInput properties) {
        return jsonInputWithResponseAsync(properties).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * A Swagger with XML that has one operation that takes JSON as input. You need to send the ID number 42.
     *
     * @param properties The properties parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void jsonInput(JsonInput properties) {
        jsonInputAsync(properties).block();
    }

    /**
     * A Swagger with XML that has one operation that returns JSON. ID number 42.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<JsonOutput>> jsonOutputWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.jsonOutput(this.client.getHost(), accept, context));
    }

    /**
     * A Swagger with XML that has one operation that returns JSON. ID number 42.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<JsonOutput> jsonOutputAsync() {
        return jsonOutputWithResponseAsync()
                .flatMap(
                        (Response<JsonOutput> res) -> {
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
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public JsonOutput jsonOutput() {
        return jsonOutputAsync().block();
    }

    /**
     * Get back an XML object with an x-ms-text property, which should translate to the returned object's 'language'
     * property being 'english' and its 'content' property being 'I am text'.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return back an XML object with an x-ms-text property, which should translate to the returned object's 'language'
     *     property being 'english' and its 'content' property being 'I am text'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ObjectWithXMsTextProperty>> getXMsTextWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/xml";
        return FluxUtil.withContext(context -> service.getXMsText(this.client.getHost(), accept, context));
    }

    /**
     * Get back an XML object with an x-ms-text property, which should translate to the returned object's 'language'
     * property being 'english' and its 'content' property being 'I am text'.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return back an XML object with an x-ms-text property, which should translate to the returned object's 'language'
     *     property being 'english' and its 'content' property being 'I am text'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ObjectWithXMsTextProperty> getXMsTextAsync() {
        return getXMsTextWithResponseAsync()
                .flatMap(
                        (Response<ObjectWithXMsTextProperty> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get back an XML object with an x-ms-text property, which should translate to the returned object's 'language'
     * property being 'english' and its 'content' property being 'I am text'.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return back an XML object with an x-ms-text property, which should translate to the returned object's 'language'
     *     property being 'english' and its 'content' property being 'I am text'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ObjectWithXMsTextProperty getXMsText() {
        return getXMsTextAsync().block();
    }
}
