package fixtures.bodycomplex;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
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
import fixtures.bodycomplex.models.DotFish;
import fixtures.bodycomplex.models.DotFishMarket;
import fixtures.bodycomplex.models.ErrorException;
import fixtures.bodycomplex.models.Fish;
import fixtures.bodycomplex.models.Salmon;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Polymorphisms. */
public final class Polymorphisms {
    /** The proxy service used to perform REST calls. */
    private final PolymorphismsService service;

    /** The service client containing this operation class. */
    private final AutoRestComplexTestService client;

    /**
     * Initializes an instance of Polymorphisms.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Polymorphisms(AutoRestComplexTestService client) {
        this.service =
                RestProxy.create(PolymorphismsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestComplexTestServicePolymorphisms to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestComplexTestS")
    private interface PolymorphismsService {
        @Get("/complex/polymorphism/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Fish>> getValid(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/polymorphism/valid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValid(
                @HostParam("$host") String host,
                @BodyParam("application/json") Fish complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/complex/polymorphism/dotsyntax")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DotFish>> getDotSyntax(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/complex/polymorphism/composedWithDiscriminator")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DotFishMarket>> getComposedWithDiscriminator(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/complex/polymorphism/composedWithoutDiscriminator")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<DotFishMarket>> getComposedWithoutDiscriminator(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/complex/polymorphism/complicated")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Salmon>> getComplicated(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/complex/polymorphism/complicated")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putComplicated(
                @HostParam("$host") String host,
                @BodyParam("application/json") Salmon complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/complex/polymorphism/missingdiscriminator")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Salmon>> putMissingDiscriminator(
                @HostParam("$host") String host,
                @BodyParam("application/json") Salmon complexBody,
                @HeaderParam("Accept") String accept,
                Context context);

        @Put("/complex/polymorphism/missingrequired/invalid")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> putValidMissingRequired(
                @HostParam("$host") String host,
                @BodyParam("application/json") Fish complexBody,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Get complex types that are polymorphic.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Fish>> getValidWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getValid(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types that are polymorphic.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Fish> getValidAsync() {
        return getValidWithResponseAsync()
                .flatMap(
                        (Response<Fish> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types that are polymorphic.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Fish getValid() {
        return getValidAsync().block();
    }

    /**
     * Put complex types that are polymorphic.
     *
     * @param complexBody Please put a salmon that looks like this: { 'fishtype':'Salmon', 'location':'alaska',
     *     'iswild':true, 'species':'king', 'length':1.0, 'siblings':[ { 'fishtype':'Shark', 'age':6, 'birthday':
     *     '2012-01-05T01:00:00Z', 'length':20.0, 'species':'predator', }, { 'fishtype':'Sawshark', 'age':105,
     *     'birthday': '1900-01-05T01:00:00Z', 'length':10.0, 'picture': new Buffer([255, 255, 255, 255,
     *     254]).toString('base64'), 'species':'dangerous', }, { 'fishtype': 'goblin', 'age': 1, 'birthday':
     *     '2015-08-08T00:00:00Z', 'length': 30.0, 'species': 'scary', 'jawsize': 5 } ] };.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidWithResponseAsync(Fish complexBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putValid(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types that are polymorphic.
     *
     * @param complexBody Please put a salmon that looks like this: { 'fishtype':'Salmon', 'location':'alaska',
     *     'iswild':true, 'species':'king', 'length':1.0, 'siblings':[ { 'fishtype':'Shark', 'age':6, 'birthday':
     *     '2012-01-05T01:00:00Z', 'length':20.0, 'species':'predator', }, { 'fishtype':'Sawshark', 'age':105,
     *     'birthday': '1900-01-05T01:00:00Z', 'length':10.0, 'picture': new Buffer([255, 255, 255, 255,
     *     254]).toString('base64'), 'species':'dangerous', }, { 'fishtype': 'goblin', 'age': 1, 'birthday':
     *     '2015-08-08T00:00:00Z', 'length': 30.0, 'species': 'scary', 'jawsize': 5 } ] };.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidAsync(Fish complexBody) {
        return putValidWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types that are polymorphic.
     *
     * @param complexBody Please put a salmon that looks like this: { 'fishtype':'Salmon', 'location':'alaska',
     *     'iswild':true, 'species':'king', 'length':1.0, 'siblings':[ { 'fishtype':'Shark', 'age':6, 'birthday':
     *     '2012-01-05T01:00:00Z', 'length':20.0, 'species':'predator', }, { 'fishtype':'Sawshark', 'age':105,
     *     'birthday': '1900-01-05T01:00:00Z', 'length':10.0, 'picture': new Buffer([255, 255, 255, 255,
     *     254]).toString('base64'), 'species':'dangerous', }, { 'fishtype': 'goblin', 'age': 1, 'birthday':
     *     '2015-08-08T00:00:00Z', 'length': 30.0, 'species': 'scary', 'jawsize': 5 } ] };.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValid(Fish complexBody) {
        putValidAsync(complexBody).block();
    }

    /**
     * Get complex types that are polymorphic, JSON key contains a dot.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, JSON key contains a dot.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DotFish>> getDotSyntaxWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getDotSyntax(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types that are polymorphic, JSON key contains a dot.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, JSON key contains a dot.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DotFish> getDotSyntaxAsync() {
        return getDotSyntaxWithResponseAsync()
                .flatMap(
                        (Response<DotFish> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types that are polymorphic, JSON key contains a dot.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, JSON key contains a dot.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DotFish getDotSyntax() {
        return getDotSyntaxAsync().block();
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, with
     * discriminator specified. Deserialization must NOT fail and use the discriminator type specified on the wire.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type,
     *     with discriminator specified.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DotFishMarket>> getComposedWithDiscriminatorWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getComposedWithDiscriminator(this.client.getHost(), accept, context));
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, with
     * discriminator specified. Deserialization must NOT fail and use the discriminator type specified on the wire.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type,
     *     with discriminator specified.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DotFishMarket> getComposedWithDiscriminatorAsync() {
        return getComposedWithDiscriminatorWithResponseAsync()
                .flatMap(
                        (Response<DotFishMarket> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type, with
     * discriminator specified. Deserialization must NOT fail and use the discriminator type specified on the wire.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type,
     *     with discriminator specified.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DotFishMarket getComposedWithDiscriminator() {
        return getComposedWithDiscriminatorAsync().block();
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type,
     * without discriminator specified on wire. Deserialization must NOT fail and use the explicit type of the property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type,
     *     without discriminator specified on wire.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DotFishMarket>> getComposedWithoutDiscriminatorWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.getComposedWithoutDiscriminator(this.client.getHost(), accept, context));
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type,
     * without discriminator specified on wire. Deserialization must NOT fail and use the explicit type of the property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type,
     *     without discriminator specified on wire.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DotFishMarket> getComposedWithoutDiscriminatorAsync() {
        return getComposedWithoutDiscriminatorWithResponseAsync()
                .flatMap(
                        (Response<DotFishMarket> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex object composing a polymorphic scalar property and array property with polymorphic element type,
     * without discriminator specified on wire. Deserialization must NOT fail and use the explicit type of the property.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex object composing a polymorphic scalar property and array property with polymorphic element type,
     *     without discriminator specified on wire.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DotFishMarket getComposedWithoutDiscriminator() {
        return getComposedWithoutDiscriminatorAsync().block();
    }

    /**
     * Get complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, but not at the root of the hierarchy; also have additional
     *     properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Salmon>> getComplicatedWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getComplicated(this.client.getHost(), accept, context));
    }

    /**
     * Get complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, but not at the root of the hierarchy; also have additional
     *     properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Salmon> getComplicatedAsync() {
        return getComplicatedWithResponseAsync()
                .flatMap(
                        (Response<Salmon> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return complex types that are polymorphic, but not at the root of the hierarchy; also have additional
     *     properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Salmon getComplicated() {
        return getComplicatedAsync().block();
    }

    /**
     * Put complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     *
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putComplicatedWithResponseAsync(Salmon complexBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putComplicated(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     *
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putComplicatedAsync(Salmon complexBody) {
        return putComplicatedWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types that are polymorphic, but not at the root of the hierarchy; also have additional properties.
     *
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putComplicated(Salmon complexBody) {
        putComplicatedAsync(complexBody).block();
    }

    /**
     * Put complex types that are polymorphic, omitting the discriminator.
     *
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Salmon>> putMissingDiscriminatorWithResponseAsync(Salmon complexBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putMissingDiscriminator(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types that are polymorphic, omitting the discriminator.
     *
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Salmon> putMissingDiscriminatorAsync(Salmon complexBody) {
        return putMissingDiscriminatorWithResponseAsync(complexBody)
                .flatMap(
                        (Response<Salmon> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put complex types that are polymorphic, omitting the discriminator.
     *
     * @param complexBody The complexBody parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Salmon putMissingDiscriminator(Salmon complexBody) {
        return putMissingDiscriminatorAsync(complexBody).block();
    }

    /**
     * Put complex types that are polymorphic, attempting to omit required 'birthday' field - the request should not be
     * allowed from the client.
     *
     * @param complexBody Please attempt put a sawshark that looks like this, the client should not allow this data to
     *     be sent: { "fishtype": "sawshark", "species": "snaggle toothed", "length": 18.5, "age": 2, "birthday":
     *     "2013-06-01T01:00:00Z", "location": "alaska", "picture": base64(FF FF FF FF FE), "siblings": [ { "fishtype":
     *     "shark", "species": "predator", "birthday": "2012-01-05T01:00:00Z", "length": 20, "age": 6 }, { "fishtype":
     *     "sawshark", "species": "dangerous", "picture": base64(FF FF FF FF FE), "length": 10, "age": 105 } ] }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> putValidMissingRequiredWithResponseAsync(Fish complexBody) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (complexBody == null) {
            return Mono.error(new IllegalArgumentException("Parameter complexBody is required and cannot be null."));
        } else {
            complexBody.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context -> service.putValidMissingRequired(this.client.getHost(), complexBody, accept, context));
    }

    /**
     * Put complex types that are polymorphic, attempting to omit required 'birthday' field - the request should not be
     * allowed from the client.
     *
     * @param complexBody Please attempt put a sawshark that looks like this, the client should not allow this data to
     *     be sent: { "fishtype": "sawshark", "species": "snaggle toothed", "length": 18.5, "age": 2, "birthday":
     *     "2013-06-01T01:00:00Z", "location": "alaska", "picture": base64(FF FF FF FF FE), "siblings": [ { "fishtype":
     *     "shark", "species": "predator", "birthday": "2012-01-05T01:00:00Z", "length": 20, "age": 6 }, { "fishtype":
     *     "sawshark", "species": "dangerous", "picture": base64(FF FF FF FF FE), "length": 10, "age": 105 } ] }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> putValidMissingRequiredAsync(Fish complexBody) {
        return putValidMissingRequiredWithResponseAsync(complexBody).flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Put complex types that are polymorphic, attempting to omit required 'birthday' field - the request should not be
     * allowed from the client.
     *
     * @param complexBody Please attempt put a sawshark that looks like this, the client should not allow this data to
     *     be sent: { "fishtype": "sawshark", "species": "snaggle toothed", "length": 18.5, "age": 2, "birthday":
     *     "2013-06-01T01:00:00Z", "location": "alaska", "picture": base64(FF FF FF FF FE), "siblings": [ { "fishtype":
     *     "shark", "species": "predator", "birthday": "2012-01-05T01:00:00Z", "length": 20, "age": 6 }, { "fishtype":
     *     "sawshark", "species": "dangerous", "picture": base64(FF FF FF FF FE), "length": 10, "age": 105 } ] }.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void putValidMissingRequired(Fish complexBody) {
        putValidMissingRequiredAsync(complexBody).block();
    }
}
