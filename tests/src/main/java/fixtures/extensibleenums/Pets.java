package fixtures.extensibleenums;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.extensibleenums.models.Pet;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Pets.
 */
public final class Pets {
    /**
     * The proxy service used to perform REST calls.
     */
    private PetsService service;

    /**
     * The service client containing this operation class.
     */
    private PetStoreInc client;

    /**
     * Initializes an instance of Pets.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    Pets(PetStoreInc client) {
        this.service = RestProxy.create(PetsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for PetStoreIncPets to be used
     * by the proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "PetStoreIncPets")
    private interface PetsService {
        @Get("/extensibleenums/pet/{petId}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Pet>> getByPetId(@HostParam("$host") String host, @PathParam("petId") String petId, Context context);

        @Post("/extensibleenums/pet/addPet")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Pet>> addPet(@HostParam("$host") String host, @BodyParam("application/json") Pet petParam, Context context);
    }

    /**
     * @param petId 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Pet>> getByPetIdWithResponseAsync(String petId) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (petId == null) {
            throw new IllegalArgumentException("Parameter petId is required and cannot be null.");
        }
        return FluxUtil.withContext(context -> service.getByPetId(this.client.getHost(), petId, context));
    }

    /**
     * @param petId 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Pet> getByPetIdAsync(String petId) {
        return getByPetIdWithResponseAsync(petId)
            .flatMap((SimpleResponse<Pet> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * @param petId 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Pet getByPetId(String petId) {
        return getByPetIdAsync(petId).block();
    }

    /**
     * @param petParam 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Pet>> addPetWithResponseAsync(Pet petParam) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (petParam != null) {
            petParam.validate();
        }
        return FluxUtil.withContext(context -> service.addPet(this.client.getHost(), petParam, context));
    }

    /**
     * @param petParam 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Pet> addPetAsync(Pet petParam) {
        return addPetWithResponseAsync(petParam)
            .flatMap((SimpleResponse<Pet> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * @param petParam 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Pet addPet(Pet petParam) {
        return addPetAsync(petParam).block();
    }
}
