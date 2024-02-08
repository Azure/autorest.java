// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.extensibleenums;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.extensibleenums.models.Pet;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in Pets.
 */
public final class Pets {

    /**
     * The proxy service used to perform REST calls.
     */
    private final PetsService service;

    /**
     * The service client containing this operation class.
     */
    private final PetStoreInc client;

    /**
     * Initializes an instance of Pets.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Pets(PetStoreInc client) {
        this.service = RestProxy.create(PetsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for PetStoreIncPets to be used by the proxy service to perform REST
     * calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "PetStoreIncPets")
    public interface PetsService {

        @Get("/extensibleenums/pet/{petId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Pet>> getByPetId(@HostParam("$host") String host, @PathParam("petId") String petId,
            @HeaderParam("Accept") String accept, Context context);

        @Post("/extensibleenums/pet/addPet")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Pet>> addPet(@HostParam("$host") String host, @BodyParam("application/json") Pet petParam,
            @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * get pet by id.
     *
     * @param petId Pet id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return pet by id along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Pet>> getByPetIdWithResponseAsync(String petId) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (petId == null) {
            return Mono.error(new IllegalArgumentException("Parameter petId is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.getByPetId(this.client.getHost(), petId, accept, context));
    }

    /**
     * get pet by id.
     *
     * @param petId Pet id.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return pet by id along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Pet>> getByPetIdWithResponseAsync(String petId, Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (petId == null) {
            return Mono.error(new IllegalArgumentException("Parameter petId is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getByPetId(this.client.getHost(), petId, accept, context);
    }

    /**
     * get pet by id.
     *
     * @param petId Pet id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return pet by id on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Pet> getByPetIdAsync(String petId) {
        return getByPetIdWithResponseAsync(petId).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * get pet by id.
     *
     * @param petId Pet id.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return pet by id on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Pet> getByPetIdAsync(String petId, Context context) {
        return getByPetIdWithResponseAsync(petId, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * get pet by id.
     *
     * @param petId Pet id.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return pet by id along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Pet> getByPetIdWithResponse(String petId, Context context) {
        return getByPetIdWithResponseAsync(petId, context).block();
    }

    /**
     * get pet by id.
     *
     * @param petId Pet id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return pet by id.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Pet getByPetId(String petId) {
        return getByPetIdWithResponse(petId, Context.NONE).getValue();
    }

    /**
     * add pet.
     *
     * @param petParam pet param.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Pet>> addPetWithResponseAsync(Pet petParam) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (petParam != null) {
            petParam.validate();
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.addPet(this.client.getHost(), petParam, accept, context));
    }

    /**
     * add pet.
     *
     * @param petParam pet param.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Pet>> addPetWithResponseAsync(Pet petParam, Context context) {
        if (this.client.getHost() == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (petParam != null) {
            petParam.validate();
        }
        final String accept = "application/json";
        return service.addPet(this.client.getHost(), petParam, accept, context);
    }

    /**
     * add pet.
     *
     * @param petParam pet param.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Pet> addPetAsync(Pet petParam) {
        return addPetWithResponseAsync(petParam).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * add pet.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Pet> addPetAsync() {
        final Pet petParam = null;
        return addPetWithResponseAsync(petParam).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * add pet.
     *
     * @param petParam pet param.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Pet> addPetAsync(Pet petParam, Context context) {
        return addPetWithResponseAsync(petParam, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * add pet.
     *
     * @param petParam pet param.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Pet> addPetWithResponse(Pet petParam, Context context) {
        return addPetWithResponseAsync(petParam, context).block();
    }

    /**
     * add pet.
     *
     * @param petParam pet param.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Pet addPet(Pet petParam) {
        return addPetWithResponse(petParam, Context.NONE).getValue();
    }

    /**
     * add pet.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Pet addPet() {
        final Pet petParam = null;
        return addPetWithResponse(petParam, Context.NONE).getValue();
    }
}
