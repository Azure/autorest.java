package fixtures.inheritance.passdiscriminator;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.inheritance.passdiscriminator.models.MetricAlertResource;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in MetricAlerts. */
public final class MetricAlerts {
    /** The proxy service used to perform REST calls. */
    private final MetricAlertsService service;

    /** The service client containing this operation class. */
    private final MonitorManagementClient client;

    /**
     * Initializes an instance of MetricAlerts.
     *
     * @param client the instance of the service client containing this operation class.
     */
    MetricAlerts(MonitorManagementClient client) {
        this.service =
                RestProxy.create(MetricAlertsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for MonitorManagementClientMetricAlerts to be used by the proxy service
     * to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "MonitorManagementCli")
    private interface MetricAlertsService {
        @Get("/providers/Microsoft.Insights/metricAlerts")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(HttpResponseException.class)
        Mono<Response<MetricAlertResource>> get(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Retrieve an alert rule definition.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the metric alert resource.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<MetricAlertResource>> getWithResponseAsync() {
        if (this.client.getHost() == null) {
            return Mono.error(
                    new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.get(this.client.getHost(), accept, context));
    }

    /**
     * Retrieve an alert rule definition.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the metric alert resource.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<MetricAlertResource> getAsync() {
        return getWithResponseAsync()
                .flatMap(
                        (Response<MetricAlertResource> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Retrieve an alert rule definition.
     *
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the metric alert resource.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public MetricAlertResource get() {
        return getAsync().block();
    }
}
