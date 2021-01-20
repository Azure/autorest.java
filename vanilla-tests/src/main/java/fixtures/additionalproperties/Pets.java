package fixtures.additionalproperties;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
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
import fixtures.additionalproperties.models.CatAPTrue;
import fixtures.additionalproperties.models.ErrorException;
import fixtures.additionalproperties.models.PetAPInProperties;
import fixtures.additionalproperties.models.PetAPInPropertiesWithAPString;
import fixtures.additionalproperties.models.PetAPObject;
import fixtures.additionalproperties.models.PetAPString;
import fixtures.additionalproperties.models.PetAPTrue;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Pets. */
public final class Pets {
    /** The proxy service used to perform REST calls. */
    private final PetsService service;

    /** The service client containing this operation class. */
    private final AdditionalPropertiesClient client;

    /**
     * Initializes an instance of Pets.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Pets(AdditionalPropertiesClient client) {
        this.service = RestProxy.create(PetsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AdditionalPropertiesClientPets to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AdditionalProperties")
    private interface PetsService {
        @Put("/additionalProperties/true")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<PetAPTrue>> createAPTrue(
                @HostParam("$host") String host,
                @BodyParam("application/json") PetAPTrue createParameters,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/additionalProperties/true-subclass")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<CatAPTrue>> createCatAPTrue(
                @HostParam("$host") String host,
                @BodyParam("application/json") CatAPTrue createParameters,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/additionalProperties/type/object")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<PetAPObject>> createAPObject(
                @HostParam("$host") String host,
                @BodyParam("application/json") PetAPObject createParameters,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/additionalProperties/type/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<PetAPString>> createAPString(
                @HostParam("$host") String host,
                @BodyParam("application/json") PetAPString createParameters,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/additionalProperties/in/properties")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<PetAPInProperties>> createAPInProperties(
                @HostParam("$host") String host,
                @BodyParam("application/json") PetAPInProperties createParameters,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/additionalProperties/in/properties/with/additionalProperties/string")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<PetAPInPropertiesWithAPString>> createAPInPropertiesWithAPString(
                @HostParam("$host") String host,
                @BodyParam("application/json") PetAPInPropertiesWithAPString createParameters,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<PetAPTrue>> createAPTrueWithResponseAsync(PetAPTrue createParameters) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (createParameters == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter createParameters is required and cannot be null."));
        } else {
            createParameters.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.createAPTrue(this.client.getHost(), createParameters, accept, context));
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PetAPTrue> createAPTrueAsync(PetAPTrue createParameters) {
        return createAPTrueWithResponseAsync(createParameters)
                .flatMap(
                        (Response<PetAPTrue> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PetAPTrue createAPTrue(PetAPTrue createParameters) {
        return createAPTrueAsync(createParameters).block();
    }

    /**
     * Create a CatAPTrue which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<CatAPTrue>> createCatAPTrueWithResponseAsync(CatAPTrue createParameters) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (createParameters == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter createParameters is required and cannot be null."));
        } else {
            createParameters.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.createCatAPTrue(this.client.getHost(), createParameters, accept, context));
    }

    /**
     * Create a CatAPTrue which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<CatAPTrue> createCatAPTrueAsync(CatAPTrue createParameters) {
        return createCatAPTrueWithResponseAsync(createParameters)
                .flatMap(
                        (Response<CatAPTrue> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Create a CatAPTrue which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public CatAPTrue createCatAPTrue(CatAPTrue createParameters) {
        return createCatAPTrueAsync(createParameters).block();
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<PetAPObject>> createAPObjectWithResponseAsync(PetAPObject createParameters) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (createParameters == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter createParameters is required and cannot be null."));
        } else {
            createParameters.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.createAPObject(this.client.getHost(), createParameters, accept, context));
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PetAPObject> createAPObjectAsync(PetAPObject createParameters) {
        return createAPObjectWithResponseAsync(createParameters)
                .flatMap(
                        (Response<PetAPObject> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PetAPObject createAPObject(PetAPObject createParameters) {
        return createAPObjectAsync(createParameters).block();
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<PetAPString>> createAPStringWithResponseAsync(PetAPString createParameters) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (createParameters == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter createParameters is required and cannot be null."));
        } else {
            createParameters.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.createAPString(this.client.getHost(), createParameters, accept, context));
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PetAPString> createAPStringAsync(PetAPString createParameters) {
        return createAPStringWithResponseAsync(createParameters)
                .flatMap(
                        (Response<PetAPString> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PetAPString createAPString(PetAPString createParameters) {
        return createAPStringAsync(createParameters).block();
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<PetAPInProperties>> createAPInPropertiesWithResponseAsync(PetAPInProperties createParameters) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (createParameters == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter createParameters is required and cannot be null."));
        } else {
            createParameters.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.createAPInProperties(this.client.getHost(), createParameters, accept, context));
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PetAPInProperties> createAPInPropertiesAsync(PetAPInProperties createParameters) {
        return createAPInPropertiesWithResponseAsync(createParameters)
                .flatMap(
                        (Response<PetAPInProperties> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PetAPInProperties createAPInProperties(PetAPInProperties createParameters) {
        return createAPInPropertiesAsync(createParameters).block();
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<PetAPInPropertiesWithAPString>> createAPInPropertiesWithAPStringWithResponseAsync(
            PetAPInPropertiesWithAPString createParameters) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (createParameters == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter createParameters is required and cannot be null."));
        } else {
            createParameters.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.createAPInPropertiesWithAPString(
                                this.client.getHost(), createParameters, accept, context));
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PetAPInPropertiesWithAPString> createAPInPropertiesWithAPStringAsync(
            PetAPInPropertiesWithAPString createParameters) {
        return createAPInPropertiesWithAPStringWithResponseAsync(createParameters)
                .flatMap(
                        (Response<PetAPInPropertiesWithAPString> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Create a Pet which contains more properties than what is defined.
     *
     * @param createParameters The createParameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PetAPInPropertiesWithAPString createAPInPropertiesWithAPString(
            PetAPInPropertiesWithAPString createParameters) {
        return createAPInPropertiesWithAPStringAsync(createParameters).block();
    }
}
