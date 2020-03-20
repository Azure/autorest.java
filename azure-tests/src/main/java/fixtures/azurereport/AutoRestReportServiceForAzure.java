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
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import fixtures.azurereport.models.ErrorException;
import java.util.Map;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the AutoRestReportServiceForAzure type.
 */
public final class AutoRestReportServiceForAzure {
    /**
     * The proxy service used to perform REST calls.
     */
    private AutoRestReportServiceForAzureService service;

    /**
     * server parameter.
     */
    private String host;

    /**
     * Gets server parameter.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets server parameter.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    AutoRestReportServiceForAzure setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * Initializes an instance of AutoRestReportServiceForAzure client.
     */
    public AutoRestReportServiceForAzure() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestReportServiceForAzure client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestReportServiceForAzure(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.service = RestProxy.create(AutoRestReportServiceForAzureService.class, this.httpPipeline);
    }

    /**
     * The interface defining all the services for
     * AutoRestReportServiceForAzure to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestReportServiceForAzure")
    private interface AutoRestReportServiceForAzureService {
        @Get("/report/azure")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<SimpleResponse<Map<String, Integer>>> getReport(@HostParam("$host") String host, @QueryParam("qualifier") String qualifier, Context context);
    }

    /**
     * Get test coverage report.
     * 
     * @param qualifier 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<Map<String, Integer>>> getReportWithResponseAsync(String qualifier) {
        if (this.getHost() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getHost() is required and cannot be null."));
        }
        return FluxUtil.withContext(context -> service.getReport(this.getHost(), qualifier, context));
    }

    /**
     * Get test coverage report.
     * 
     * @param qualifier 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Map<String, Integer>> getReportAsync(String qualifier) {
        return getReportWithResponseAsync(qualifier)
            .flatMap((SimpleResponse<Map<String, Integer>> res) -> {
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
     * @param qualifier 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Map<String, Integer> getReport(String qualifier) {
        return getReportAsync(qualifier).block();
    }
}
