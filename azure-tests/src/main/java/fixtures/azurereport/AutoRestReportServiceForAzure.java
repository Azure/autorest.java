package fixtures.azurereport;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.azurereport.models.ErrorException;
import java.util.Map;
import reactor.core.publisher.Mono;

/** Initializes a new instance of the AutoRestReportServiceForAzure type. */
public final class AutoRestReportServiceForAzure {
    /** The proxy service used to perform REST calls. */
    private final AutoRestReportServiceForAzureService service;

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

    /** Initializes an instance of AutoRestReportServiceForAzure client. */
    AutoRestReportServiceForAzure(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestReportServiceForAzure client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestReportServiceForAzure(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.service =
                RestProxy.create(
                        AutoRestReportServiceForAzureService.class, this.httpPipeline, this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for AutoRestReportServiceForAzure to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestReportServic")
    private interface AutoRestReportServiceForAzureService {
        @Get("/report/azure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Map<String, Integer>>> getReport(
                @HostParam("$host") String host, @QueryParam("qualifier") String qualifier, Context context);
    }

    /**
     * Get test coverage report.
     *
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The
     *     only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return test coverage report.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Map<String, Integer>>> getReportWithResponseAsync(String qualifier) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getReport(this.getHost(), qualifier, context));
    }

    /**
     * Get test coverage report.
     *
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The
     *     only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return test coverage report.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getReportAsync(String qualifier) {
        return getReportWithResponseAsync(qualifier)
                .flatMap(
                        (Response<Map<String, Integer>> res) -> {
                            if (res.getValue() != null) {
                                return Mono.just(res.getValue());
                            } else {
                                return Mono.empty();
                            }
                        });
    }

    /**
     * Get test coverage report.
     *
     * @param qualifier If specified, qualifies the generated report further (e.g. '2.7' vs '3.5' in for Python). The
     *     only effect is, that generators that run all tests several times, can distinguish the generated reports.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return test coverage report.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getReport(String qualifier) {
        return getReportAsync(qualifier).block();
    }
}
