package fixtures.multipleinheritance;

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
import com.azure.core.exception.HttpResponseException;
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
import fixtures.multipleinheritance.models.Cat;
import fixtures.multipleinheritance.models.ErrorException;
import fixtures.multipleinheritance.models.Feline;
import fixtures.multipleinheritance.models.Horse;
import fixtures.multipleinheritance.models.Kitten;
import fixtures.multipleinheritance.models.Pet;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the MultipleInheritanceServiceClient type. */
public final class MultipleInheritanceServiceClient {
    /** The proxy service used to perform REST calls. */
    private final MultipleInheritanceServiceClientService service;

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
     * Initializes an instance of MultipleInheritanceServiceClient client.
     *
     * @param host server parameter.
     */
    MultipleInheritanceServiceClient(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of MultipleInheritanceServiceClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    MultipleInheritanceServiceClient(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of MultipleInheritanceServiceClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    MultipleInheritanceServiceClient(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.service =
                RestProxy.create(
                        MultipleInheritanceServiceClientService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for MultipleInheritanceServiceClient to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "MultipleInheritanceS")
    private interface MultipleInheritanceServiceClientService {
        @Get("/multipleInheritance/horse")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Horse>> getHorse(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/multipleInheritance/horse")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> putHorse(
                @HostParam("$host") String host,
                @BodyParam("application/json") Horse horse,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/multipleInheritance/pet")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Pet>> getPet(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/multipleInheritance/pet")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> putPet(
                @HostParam("$host") String host,
                @BodyParam("application/json") Pet pet,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/multipleInheritance/feline")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Feline>> getFeline(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/multipleInheritance/feline")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> putFeline(
                @HostParam("$host") String host,
                @BodyParam("application/json") Feline feline,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/multipleInheritance/cat")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Cat>> getCat(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/multipleInheritance/cat")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> putCat(
                @HostParam("$host") String host,
                @BodyParam("application/json") Cat cat,
                @HeaderParam("Accept") String accept,
                Context context);

        @Get("/multipleInheritance/kitten")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Kitten>> getKitten(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Put("/multipleInheritance/kitten")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<String>> putKitten(
                @HostParam("$host") String host,
                @BodyParam("application/json") Kitten kitten,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Get a horse with name 'Fred' and isAShowHorse true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a horse with name 'Fred' and isAShowHorse true along with {@link Response} on successful completion of
     *     {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Horse>> getHorseWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getHorse(this.getHost(), accept, context));
    }

    /**
     * Get a horse with name 'Fred' and isAShowHorse true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a horse with name 'Fred' and isAShowHorse true on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Horse> getHorseAsync() {
        return getHorseWithResponseAsync()
                .flatMap(
                        (Response<Horse> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get a horse with name 'Fred' and isAShowHorse true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a horse with name 'Fred' and isAShowHorse true.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Horse getHorse() {
        return getHorseAsync().block();
    }

    /**
     * Put a horse with name 'General' and isAShowHorse false.
     *
     * @param horse Put a horse with name 'General' and isAShowHorse false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putHorseWithResponseAsync(Horse horse) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (horse == null) {
            return Mono.error(new IllegalArgumentException("Parameter horse is required and cannot be null."));
        } else {
            horse.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putHorse(this.getHost(), horse, accept, context));
    }

    /**
     * Put a horse with name 'General' and isAShowHorse false.
     *
     * @param horse Put a horse with name 'General' and isAShowHorse false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putHorseAsync(Horse horse) {
        return putHorseWithResponseAsync(horse)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put a horse with name 'General' and isAShowHorse false.
     *
     * @param horse Put a horse with name 'General' and isAShowHorse false.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String putHorse(Horse horse) {
        return putHorseAsync(horse).block();
    }

    /**
     * Get a pet with name 'Peanut'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a pet with name 'Peanut' along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Pet>> getPetWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getPet(this.getHost(), accept, context));
    }

    /**
     * Get a pet with name 'Peanut'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a pet with name 'Peanut' on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Pet> getPetAsync() {
        return getPetWithResponseAsync()
                .flatMap(
                        (Response<Pet> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get a pet with name 'Peanut'.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a pet with name 'Peanut'.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Pet getPet() {
        return getPetAsync().block();
    }

    /**
     * Put a pet with name 'Butter'.
     *
     * @param pet Put a pet with name 'Butter'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putPetWithResponseAsync(Pet pet) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (pet == null) {
            return Mono.error(new IllegalArgumentException("Parameter pet is required and cannot be null."));
        } else {
            pet.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putPet(this.getHost(), pet, accept, context));
    }

    /**
     * Put a pet with name 'Butter'.
     *
     * @param pet Put a pet with name 'Butter'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putPetAsync(Pet pet) {
        return putPetWithResponseAsync(pet)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put a pet with name 'Butter'.
     *
     * @param pet Put a pet with name 'Butter'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String putPet(Pet pet) {
        return putPetAsync(pet).block();
    }

    /**
     * Get a feline where meows and hisses are true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a feline where meows and hisses are true along with {@link Response} on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Feline>> getFelineWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getFeline(this.getHost(), accept, context));
    }

    /**
     * Get a feline where meows and hisses are true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a feline where meows and hisses are true on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Feline> getFelineAsync() {
        return getFelineWithResponseAsync()
                .flatMap(
                        (Response<Feline> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get a feline where meows and hisses are true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a feline where meows and hisses are true.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Feline getFeline() {
        return getFelineAsync().block();
    }

    /**
     * Put a feline who hisses and doesn't meow.
     *
     * @param feline Put a feline who hisses and doesn't meow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putFelineWithResponseAsync(Feline feline) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (feline == null) {
            return Mono.error(new IllegalArgumentException("Parameter feline is required and cannot be null."));
        } else {
            feline.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putFeline(this.getHost(), feline, accept, context));
    }

    /**
     * Put a feline who hisses and doesn't meow.
     *
     * @param feline Put a feline who hisses and doesn't meow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putFelineAsync(Feline feline) {
        return putFelineWithResponseAsync(feline)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put a feline who hisses and doesn't meow.
     *
     * @param feline Put a feline who hisses and doesn't meow.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String putFeline(Feline feline) {
        return putFelineAsync(feline).block();
    }

    /**
     * Get a cat with name 'Whiskers' where likesMilk, meows, and hisses is true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a cat with name 'Whiskers' where likesMilk, meows, and hisses is true along with {@link Response} on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Cat>> getCatWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getCat(this.getHost(), accept, context));
    }

    /**
     * Get a cat with name 'Whiskers' where likesMilk, meows, and hisses is true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a cat with name 'Whiskers' where likesMilk, meows, and hisses is true on successful completion of {@link
     *     Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Cat> getCatAsync() {
        return getCatWithResponseAsync()
                .flatMap(
                        (Response<Cat> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get a cat with name 'Whiskers' where likesMilk, meows, and hisses is true.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a cat with name 'Whiskers' where likesMilk, meows, and hisses is true.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Cat getCat() {
        return getCatAsync().block();
    }

    /**
     * Put a cat with name 'Boots' where likesMilk and hisses is false, meows is true.
     *
     * @param cat Put a cat with name 'Boots' where likesMilk and hisses is false, meows is true.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putCatWithResponseAsync(Cat cat) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (cat == null) {
            return Mono.error(new IllegalArgumentException("Parameter cat is required and cannot be null."));
        } else {
            cat.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putCat(this.getHost(), cat, accept, context));
    }

    /**
     * Put a cat with name 'Boots' where likesMilk and hisses is false, meows is true.
     *
     * @param cat Put a cat with name 'Boots' where likesMilk and hisses is false, meows is true.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putCatAsync(Cat cat) {
        return putCatWithResponseAsync(cat)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put a cat with name 'Boots' where likesMilk and hisses is false, meows is true.
     *
     * @param cat Put a cat with name 'Boots' where likesMilk and hisses is false, meows is true.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String putCat(Cat cat) {
        return putCatAsync(cat).block();
    }

    /**
     * Get a kitten with name 'Gatito' where likesMilk and meows is true, and hisses and eatsMiceYet is false.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a kitten with name 'Gatito' where likesMilk and meows is true, and hisses and eatsMiceYet is false along
     *     with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Kitten>> getKittenWithResponseAsync() {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getKitten(this.getHost(), accept, context));
    }

    /**
     * Get a kitten with name 'Gatito' where likesMilk and meows is true, and hisses and eatsMiceYet is false.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a kitten with name 'Gatito' where likesMilk and meows is true, and hisses and eatsMiceYet is false on
     *     successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Kitten> getKittenAsync() {
        return getKittenWithResponseAsync()
                .flatMap(
                        (Response<Kitten> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get a kitten with name 'Gatito' where likesMilk and meows is true, and hisses and eatsMiceYet is false.
     *
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a kitten with name 'Gatito' where likesMilk and meows is true, and hisses and eatsMiceYet is false.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Kitten getKitten() {
        return getKittenAsync().block();
    }

    /**
     * Put a kitten with name 'Kitty' where likesMilk and hisses is false, meows and eatsMiceYet is true.
     *
     * @param kitten Put a kitten with name 'Kitty' where likesMilk and hisses is false, meows and eatsMiceYet is true.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<String>> putKittenWithResponseAsync(Kitten kitten) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        if (kitten == null) {
            return Mono.error(new IllegalArgumentException("Parameter kitten is required and cannot be null."));
        } else {
            kitten.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.putKitten(this.getHost(), kitten, accept, context));
    }

    /**
     * Put a kitten with name 'Kitty' where likesMilk and hisses is false, meows and eatsMiceYet is true.
     *
     * @param kitten Put a kitten with name 'Kitty' where likesMilk and hisses is false, meows and eatsMiceYet is true.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<String> putKittenAsync(Kitten kitten) {
        return putKittenWithResponseAsync(kitten)
                .flatMap(
                        (Response<String> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Put a kitten with name 'Kitty' where likesMilk and hisses is false, meows and eatsMiceYet is true.
     *
     * @param kitten Put a kitten with name 'Kitty' where likesMilk and hisses is false, meows and eatsMiceYet is true.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public String putKitten(Kitten kitten) {
        return putKittenAsync(kitten).block();
    }
}
