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
    public Pets(PetStoreInc client) {
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
        Mono<SimpleResponse<Pet>> getByPetId(@HostParam("$host") String host, @PathParam("petId") String petId);

        @Post("/extensibleenums/pet/addPet")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<SimpleResponse<Pet>> addPet(@HostParam("$host") String host, @BodyParam("application/json") Pet petParam);
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Pet>> getByPetIdWithResponseAsync(String petId) {
        return service.getByPetId(this.client.getHost(), petId);
    }

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

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Pet getByPetId(String petId) {
        return getByPetIdAsync(petId).block();
    }

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Pet>> addPetWithResponseAsync(Pet petParam) {
        return service.addPet(this.client.getHost(), petParam);
    }

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

    @ServiceMethod(returns = ReturnType.SINGLE)
    public Pet addPet(Pet petParam) {
        return addPetAsync(petParam).block();
    }
}
