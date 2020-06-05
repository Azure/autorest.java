package fixtures.bodydatetimerfc1123;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** A builder for creating a new instance of the AutoRestRFC1123DateTimeTestService type. */
@ServiceClientBuilder(serviceClients = {AutoRestRFC1123DateTimeTestService.class})
public final class AutoRestRFC1123DateTimeTestServiceBuilder {
    /*
     * server parameter
     */
    private String host;

    /**
     * Sets server parameter.
     *
     * @param host the host value.
     * @return the AutoRestRFC1123DateTimeTestServiceBuilder.
     */
    public AutoRestRFC1123DateTimeTestServiceBuilder host(String host) {
        this.host = host;
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
     * @return the AutoRestRFC1123DateTimeTestServiceBuilder.
     */
    public AutoRestRFC1123DateTimeTestServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestRFC1123DateTimeTestService with the provided parameters.
     *
     * @return an instance of AutoRestRFC1123DateTimeTestService.
     */
    public AutoRestRFC1123DateTimeTestService buildClient() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline =
                    new HttpPipelineBuilder()
                            .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                            .build();
        }
        AutoRestRFC1123DateTimeTestService client = new AutoRestRFC1123DateTimeTestService(pipeline, host);
        return client;
    }
}
