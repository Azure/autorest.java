package fixtures.bodycomplex;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.bodycomplex.models.DictionaryWrapper;
import fixtures.bodycomplex.models.ErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Dictionaries. */
public final class Dictionaries {
    /** The proxy service used to perform REST calls. */
    private final DictionarysService service;

    /** The service client containing this operation class. */
    private final AutoRestComplexTestService client;

    /**
     * Initializes an instance of Dictionaries.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Dictionaries(AutoRestComplexTestService client) {
        this.service =
                RestProxy.create(DictionarysService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServiceDictionaries to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestS")
    private interface DictionarysService {
        @Get("/complex/dictionary/typed/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DictionaryWrapper>> getValid(@HostParam("$host") String host, Context context);

        @Put("/complex/dictionary/typed/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") DictionaryWrapper complexBody,
                Context context);

        @Get("/complex/dictionary/typed/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DictionaryWrapper>> getEmpty(@HostParam("$host") String host, Context context);

        @Put("/complex/dictionary/typed/empty")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putEmpty(
                @HostParam("$host") String host,
                @BodyParam("application/json") DictionaryWrapper complexBody,
                Context context);

        @Get("/complex/dictionary/typed/null")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DictionaryWrapper>> getNull(@HostParam("$host") String host, Context context);

        @Get("/complex/dictionary/typed/notprovided")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DictionaryWrapper>> getNotProvided(@HostParam("$host") String host, Context context);
    }

    /**
     * Get complex types with dictionary property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getValid(this.client.getHost(), context));
    }

    /**
     * Get complex types with dictionary property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getValidAsync() {
        return getValidWithResponseAsync()
                .flatMap(
                        (Response<DictionaryWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with dictionary property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getValid() {
        return getValidAsync().block();
    }

    /**
     * Put complex types with dictionary property.
     *
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     *     "xls":"excel", "exe":"", "":null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(DictionaryWrapper complexBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        return FluxUtil.withContext(context -> service.putValid(this.client.getHost(), complexBody, context));
    }

    /**
     * Put complex types with dictionary property.
     *
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     *     "xls":"excel", "exe":"", "":null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(DictionaryWrapper complexBody) {
        return putValidWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with dictionary property.
     *
     * @param complexBody Please put a dictionary with 5 key-value pairs: "txt":"notepad", "bmp":"mspaint",
     *     "xls":"excel", "exe":"", "":null.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(DictionaryWrapper complexBody) {
        putValidAsync(complexBody).block();
    }

    /**
     * Get complex types with dictionary property which is empty.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getEmptyWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getEmpty(this.client.getHost(), context));
    }

    /**
     * Get complex types with dictionary property which is empty.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getEmptyAsync() {
        return getEmptyWithResponseAsync()
                .flatMap(
                        (Response<DictionaryWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with dictionary property which is empty.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is empty.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getEmpty() {
        return getEmptyAsync().block();
    }

    /**
     * Put complex types with dictionary property which is empty.
     *
     * @param complexBody Please put an empty dictionary.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putEmptyWithResponseAsync(DictionaryWrapper complexBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        return FluxUtil.withContext(context -> service.putEmpty(this.client.getHost(), complexBody, context));
    }

    /**
     * Put complex types with dictionary property which is empty.
     *
     * @param complexBody Please put an empty dictionary.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putEmptyAsync(DictionaryWrapper complexBody) {
        return putEmptyWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types with dictionary property which is empty.
     *
     * @param complexBody Please put an empty dictionary.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putEmpty(DictionaryWrapper complexBody) {
        putEmptyAsync(complexBody).block();
    }

    /**
     * Get complex types with dictionary property which is null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getNullWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNull(this.client.getHost(), context));
    }

    /**
     * Get complex types with dictionary property which is null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNullAsync() {
        return getNullWithResponseAsync()
                .flatMap(
                        (Response<DictionaryWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with dictionary property which is null.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property which is null.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getNull() {
        return getNullAsync().block();
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DictionaryWrapper>> getNotProvidedWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getNotProvided(this.client.getHost(), context));
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DictionaryWrapper> getNotProvidedAsync() {
        return getNotProvidedWithResponseAsync()
                .flatMap(
                        (Response<DictionaryWrapper> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types with dictionary property while server doesn't provide a response payload.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types with dictionary property while server doesn't provide a response payload.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DictionaryWrapper getNotProvided() {
        return getNotProvidedAsync().block();
    }
}
