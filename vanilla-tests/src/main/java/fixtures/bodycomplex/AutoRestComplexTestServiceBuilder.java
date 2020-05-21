package fixtures.bodycomplex;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** A builder for creating a new instance of the AutoRestComplexTestService type. */
@ServiceClientBuilder(serviceClients = {AutoRestComplexTestService.class})
public final class AutoRestComplexTestServiceBuilder {
    /*
     * server parameter
     */
    private String host;

    /**
     * Sets server parameter.
     *
     * @param host the host value.
     * @return the AutoRestComplexTestServiceBuilder.
     */
    public AutoRestComplexTestServiceBuilder host(String host) {
        this.host = host;
        return this;
    }

    /*
     * Api Version
     */
    private String apiVersion;

    /**
     * Sets Api Version.
     *
     * @param apiVersion the apiVersion value.
     * @return the AutoRestComplexTestServiceBuilder.
     */
    public AutoRestComplexTestServiceBuilder apiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
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
     * @return the AutoRestComplexTestServiceBuilder.
     */
    public AutoRestComplexTestServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestComplexTestService with the provided parameters.
     *
     * @return an instance of AutoRestComplexTestService.
     */
    public AutoRestComplexTestService buildClient() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline =
                    new HttpPipelineBuilder()
                            .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                            .build();
        }
        AutoRestComplexTestService client = new AutoRestComplexTestService(pipeline);
        client.setHost(this.host);
        client.setApiVersion(this.apiVersion);
        return client;
    }
}
