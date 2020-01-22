package fixtures.paging;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * A builder for creating a new instance of the AutoRestPagingTestService type.
 */
@ServiceClientBuilder(serviceClients = AutoRestPagingTestService.class)
public final class AutoRestPagingTestServiceBuilder {
    /*
     * server parameter
     */
    private String _host;

    /**
     * Sets server parameter.
     * 
     * @param _host the _host value.
     * @return the AutoRestPagingTestServiceBuilder.
     */
    public AutoRestPagingTestServiceBuilder _host(String _host) {
        this._host = _host;
        return this;
    }

    /*
     * The HTTP pipeline to send requests through
     */
    private HttpPipeline pipeline;

    /**
     * Sets The HTTP pipeline to send requests through.
     * 
     * @param pipeline the pipeline value.
     * @return the AutoRestPagingTestServiceBuilder.
     */
    public AutoRestPagingTestServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestPagingTestService with the provided parameters.
     * 
     * @return an instance of AutoRestPagingTestService.
     */
    public AutoRestPagingTestService build() {
        if (_host == null) {
            this._host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        AutoRestPagingTestService client = new AutoRestPagingTestService(pipeline);
        client.set_host(this._host);
        return client;
    }
}
