package fixtures.parameterflattening;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.parameterflattening.models.AvailabilitySetUpdateParameters;
import java.util.Map;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in AvailabilitySets. */
public final class AvailabilitySets {
    /** The proxy service used to perform REST calls. */
    private final AvailabilitySetsService service;

    /** The service client containing this operation class. */
    private final AutoRestParameterFlattening client;

    /**
     * Initializes an instance of AvailabilitySets.
     *
     * @param client the instance of the service client containing this operation class.
     */
    AvailabilitySets(AutoRestParameterFlattening client) {
        this.service =
                RestProxy.create(
                        AvailabilitySetsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AutoRestParameterFlatteningAvailabilitySets to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestParameterFla")
    private interface AvailabilitySetsService {
        @Patch("/parameterFlattening/{resourceGroupName}/{availabilitySetName}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<Void>> update(
                @HostParam("$host") String host,
                @PathParam("resourceGroupName") String resourceGroupName,
                @PathParam("availabilitySetName") String avset,
                @BodyParam("application/json") AvailabilitySetUpdateParameters tags,
                Context context);
    }

    /**
     * Updates the tags for an availability set.
     *
     * @param resourceGroupName The name of the resource group.
     * @param avset The name of the storage availability set.
     * @param availabilitySetUpdateParametersTags A description about the set of tags.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> updateWithResponseAsync(
            String resourceGroupName, String avset, Map<String, String> availabilitySetUpdateParametersTags) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (resourceGroupName == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null."));
        }
        if (avset == null) {
            return Mono.error(new IllegalArgumentException("Parameter avset is required and cannot be null."));
        }
        if (availabilitySetUpdateParametersTags == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter availabilitySetUpdateParametersTags is required and cannot be null."));
        }
        AvailabilitySetUpdateParameters tags = new AvailabilitySetUpdateParameters();
        tags.setTags(availabilitySetUpdateParametersTags);
        return FluxUtil.withContext(
                context -> service.update(this.client.getHost(), resourceGroupName, avset, tags, context));
    }

    /**
     * Updates the tags for an availability set.
     *
     * @param resourceGroupName The name of the resource group.
     * @param avset The name of the storage availability set.
     * @param availabilitySetUpdateParametersTags A description about the set of tags.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> updateAsync(
            String resourceGroupName, String avset, Map<String, String> availabilitySetUpdateParametersTags) {
        return updateWithResponseAsync(resourceGroupName, avset, availabilitySetUpdateParametersTags)
                .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Updates the tags for an availability set.
     *
     * @param resourceGroupName The name of the resource group.
     * @param avset The name of the storage availability set.
     * @param availabilitySetUpdateParametersTags A description about the set of tags.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void update(
            String resourceGroupName, String avset, Map<String, String> availabilitySetUpdateParametersTags) {
        updateAsync(resourceGroupName, avset, availabilitySetUpdateParametersTags).block();
    }
}
