package fixtures.subscriptionidapiversion;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.subscriptionidapiversion.models.ErrorException;
import fixtures.subscriptionidapiversion.models.SampleResourceGroup;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in Groups. */
public final class Groups {
    /** The proxy service used to perform REST calls. */
    private final GroupsService service;

    /** The service client containing this operation class. */
    private final MicrosoftAzureTestUrl client;

    /**
     * Initializes an instance of Groups.
     *
     * @param client the instance of the service client containing this operation class.
     */
    Groups(MicrosoftAzureTestUrl client) {
        this.service = RestProxy.create(GroupsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for MicrosoftAzureTestUrlGroups to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "MicrosoftAzureTestUr")
    private interface GroupsService {
        @Get("/subscriptions/{subscriptionId}/resourcegroups/{resourceGroupName}")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<SampleResourceGroup>> getSampleResourceGroup(
                @HostParam("$host") String host,
                @PathParam("subscriptionId") String subscriptionId,
                @PathParam("resourceGroupName") String resourceGroupName,
                @QueryParam("api-version") String apiVersion,
                @HeaderParam("Accept") String accept,
                Context context);
    }

    /**
     * Provides a resouce group with name 'testgroup101' and location 'West US'.
     *
     * @param resourceGroupName Resource Group name 'testgroup101'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<SampleResourceGroup>> getSampleResourceGroupWithResponseAsync(String resourceGroupName) {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        if (resourceGroupName == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(
                context ->
                        service.getSampleResourceGroup(
                                this.client.getHost(),
                                this.client.getSubscriptionId(),
                                resourceGroupName,
                                this.client.getApiVersion(),
                                accept,
                                context));
    }

    /**
     * Provides a resouce group with name 'testgroup101' and location 'West US'.
     *
     * @param resourceGroupName Resource Group name 'testgroup101'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SampleResourceGroup> getSampleResourceGroupAsync(String resourceGroupName) {
        return getSampleResourceGroupWithResponseAsync(resourceGroupName)
                .flatMap(
                        (Response<SampleResourceGroup> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Provides a resouce group with name 'testgroup101' and location 'West US'.
     *
     * @param resourceGroupName Resource Group name 'testgroup101'.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SampleResourceGroup getSampleResourceGroup(String resourceGroupName) {
        return getSampleResourceGroupAsync(resourceGroupName).block();
    }
}
