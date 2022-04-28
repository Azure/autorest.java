package petstore;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.Delete;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.serializer.SerializerAdapter;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the PetStoreServiceClient type. */
public final class PetStoreServiceClient {
    /** The proxy service used to perform REST calls. */
    private final PetStoreServiceService service;

    /** HTTP pipeline. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets HTTP pipeline.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** serializer adapter. */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets serializer adapter.
     *
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /** service endpoint. */
    private final String endpoint;

    /**
     * Gets service endpoint.
     *
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
    }

    /**
     * Initializes an instance of PetStoreServiceClient client.
     *
     * @param httpPipeline null
     * @param serializerAdapter null
     * @param endpoint service endpoint.
     */
    public PetStoreServiceClient(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String endpoint) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.endpoint = endpoint;
        this.service = RestProxy.create(PetStoreServiceService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for PetStoreServiceClient to be used by the proxy service to perform REST
     * calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "PetStoreServiceClien")
    private interface PetStoreServiceService {
        @Delete("/pets/{petId}")
        Mono<Response<Void>> delete(@HostParam("$host") String endpoint, @PathParam("petId") Integer petId);

        @Get("/pets")
        Mono<Response<ResponsePagePet>> list(
                @HostParam("$host") String endpoint, @QueryParam("nextLink") String nextLink);

        @Get("/pets/{petId}")
        Mono<Response<Pet>> read(@HostParam("$host") String endpoint, @PathParam("petId") Integer petId);

        @Post("/pets")
        Mono<Response<Pet>> create(@HostParam("$host") String endpoint, @BodyParam("application/json") Pet pet);

        @Get("/pets/{petId}/toys")
        Mono<Response<ResponsePageToy>> list(
                @HostParam("$host") String endpoint,
                @PathParam("petId") String petId,
                @QueryParam("nameFilter") String nameFilter);
    }

    /**
     * Delete a pet.
     *
     * @param petId The petId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteAsyncWithResponse(Integer petId) {
        return service.delete(getEndpoint(), petId);
    }

    /**
     * &lt;blink&gt;List pets.&lt;/blink&gt;.
     *
     * @param nextLink The nextLink parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ResponsePagePet>> listAsyncWithResponse(String nextLink) {
        return service.list(getEndpoint(), nextLink);
    }

    /**
     * Returns a pet. Supports eTags.
     *
     * @param petId The petId parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Pet>> readAsyncWithResponse(Integer petId) {
        return service.read(getEndpoint(), petId);
    }

    /**
     * @param pet The pet parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Pet>> createAsyncWithResponse(Pet pet) {
        return service.create(getEndpoint(), pet);
    }

    /**
     * @param petId The petId parameter.
     * @param nameFilter The nameFilter parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ResponsePageToy>> listAsyncWithResponse(String petId, String nameFilter) {
        return service.list(getEndpoint(), petId, nameFilter);
    }
}
