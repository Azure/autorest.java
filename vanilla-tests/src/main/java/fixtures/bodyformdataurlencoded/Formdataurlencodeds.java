package fixtures.bodyformdataurlencoded;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.FormParam;
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
import fixtures.bodyformdataurlencoded.models.PetFood;
import fixtures.bodyformdataurlencoded.models.PetType;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Formdataurlencodeds. */
public final class Formdataurlencodeds {
    /** The proxy service used to perform REST calls. */
    private final FormdataurlencodedsService service;

    /** The service client containing this operation class. */
    private final BodyFormsDataURLEncoded client;

    /**
     * Initializes an instance of Formdataurlencodeds.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Formdataurlencodeds(BodyFormsDataURLEncoded client) {
        this.service =
                RestProxy.create(
                        FormdataurlencodedsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for BodyFormsDataURLEncodedFormdataurlencodeds to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "BodyFormsDataURLEnco")
    private interface FormdataurlencodedsService {
        // @Multipart not supported by RestProxy
        @Post("/formsdataurlencoded/pet/add/{petId}")
        @ExpectedResponses({200, 405})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> updatePetWithForm(
                @HostParam("$host") String host,
                @PathParam("petId") int petId,
                @FormParam("pet_type") PetType petType,
                @FormParam("pet_food") PetFood petFood,
                @FormParam("pet_age") int petAge,
                @FormParam("name") String name,
                @FormParam("status") String status,
                Context context);

        // @Multipart not supported by RestProxy
        @Post("/formsdataurlencoded/partialConstantBody")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> partialConstantBody(
                @HostParam("$host") String host,
                @FormParam("grant_type") String grantType,
                @FormParam("service") String service,
                @FormParam("access_token") String accessToken,
                Context context);
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated.
     * @param petType Can take a value of dog, or cat, or fish.
     * @param petFood Can take a value of meat, or fish, or plant.
     * @param petAge How many years is it old?.
     * @param name Updated name of the pet.
     * @param status Updated status of the pet.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> updatePetWithFormWithResponseAsync(
            int petId, PetType petType, PetFood petFood, int petAge, String name, String status) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (petType == null) {
            return Mono.error(new IllegalArgumentException("Parameter petType is required and cannot be null."));
        }
        if (petFood == null) {
            return Mono.error(new IllegalArgumentException("Parameter petFood is required and cannot be null."));
        }
        return FluxUtil.withContext(
                context ->
                        service.updatePetWithForm(
                                this.client.getHost(), petId, petType, petFood, petAge, name, status, context));
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated.
     * @param petType Can take a value of dog, or cat, or fish.
     * @param petFood Can take a value of meat, or fish, or plant.
     * @param petAge How many years is it old?.
     * @param name Updated name of the pet.
     * @param status Updated status of the pet.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> updatePetWithFormAsync(
            int petId, PetType petType, PetFood petFood, int petAge, String name, String status) {
        return updatePetWithFormWithResponseAsync(petId, petType, petFood, petAge, name, status)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated.
     * @param petType Can take a value of dog, or cat, or fish.
     * @param petFood Can take a value of meat, or fish, or plant.
     * @param petAge How many years is it old?.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> updatePetWithFormAsync(int petId, PetType petType, PetFood petFood, int petAge) {
        final String name = null;
        final String status = null;
        return updatePetWithFormWithResponseAsync(petId, petType, petFood, petAge, name, status)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated.
     * @param petType Can take a value of dog, or cat, or fish.
     * @param petFood Can take a value of meat, or fish, or plant.
     * @param petAge How many years is it old?.
     * @param name Updated name of the pet.
     * @param status Updated status of the pet.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void updatePetWithForm(int petId, PetType petType, PetFood petFood, int petAge, String name, String status) {
        updatePetWithFormAsync(petId, petType, petFood, petAge, name, status).block();
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated.
     * @param petType Can take a value of dog, or cat, or fish.
     * @param petFood Can take a value of meat, or fish, or plant.
     * @param petAge How many years is it old?.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void updatePetWithForm(int petId, PetType petType, PetFood petFood, int petAge) {
        final String name = null;
        final String status = null;
        updatePetWithFormAsync(petId, petType, petFood, petAge, name, status).block();
    }

    /**
     * Test a partially constant formdata body. Pass in { grant_type: 'access_token', access_token: 'foo', service:
     * 'bar' } to pass the test.
     *
     * @param serviceParam Indicates the name of your Azure container registry.
     * @param accessToken AAD access token, mandatory when grant_type is access_token_refresh_token or access_token.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> partialConstantBodyWithResponseAsync(String serviceParam, String accessToken) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (serviceParam == null) {
            return Mono.error(new IllegalArgumentException("Parameter serviceParam is required and cannot be null."));
        }
        if (accessToken == null) {
            return Mono.error(new IllegalArgumentException("Parameter accessToken is required and cannot be null."));
        }
        final String grantType = "access_token";
        return FluxUtil.withContext(
                context ->
                        service.partialConstantBody(
                                this.client.getHost(), grantType, serviceParam, accessToken, context));
    }

    /**
     * Test a partially constant formdata body. Pass in { grant_type: 'access_token', access_token: 'foo', service:
     * 'bar' } to pass the test.
     *
     * @param serviceParam Indicates the name of your Azure container registry.
     * @param accessToken AAD access token, mandatory when grant_type is access_token_refresh_token or access_token.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> partialConstantBodyAsync(String serviceParam, String accessToken) {
        return partialConstantBodyWithResponseAsync(serviceParam, accessToken)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test a partially constant formdata body. Pass in { grant_type: 'access_token', access_token: 'foo', service:
     * 'bar' } to pass the test.
     *
     * @param serviceParam Indicates the name of your Azure container registry.
     * @param accessToken AAD access token, mandatory when grant_type is access_token_refresh_token or access_token.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void partialConstantBody(String serviceParam, String accessToken) {
        partialConstantBodyAsync(serviceParam, accessToken).block();
    }
}
