package fixtures.requiredoptional;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * A builder for creating a new instance of the AutoRestRequiredOptionalTestService type.
 */
@ServiceClientBuilder(serviceClients = AutoRestRequiredOptionalTestService.class)
public final class AutoRestRequiredOptionalTestServiceBuilder {
    /*
     * null
     */
    private String requiredGlobalPath;

    /**
     * Sets null.
     * 
     * @param requiredGlobalPath the requiredGlobalPath value.
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    public AutoRestRequiredOptionalTestServiceBuilder requiredGlobalPath(String requiredGlobalPath) {
        this.requiredGlobalPath = requiredGlobalPath;
        return this;
    }

    /*
     * null
     */
    private String requiredGlobalQuery;

    /**
     * Sets null.
     * 
     * @param requiredGlobalQuery the requiredGlobalQuery value.
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    public AutoRestRequiredOptionalTestServiceBuilder requiredGlobalQuery(String requiredGlobalQuery) {
        this.requiredGlobalQuery = requiredGlobalQuery;
        return this;
    }

    /*
     * null
     */
    private int optionalGlobalQuery;

    /**
     * Sets null.
     * 
     * @param optionalGlobalQuery the optionalGlobalQuery value.
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    public AutoRestRequiredOptionalTestServiceBuilder optionalGlobalQuery(int optionalGlobalQuery) {
        this.optionalGlobalQuery = optionalGlobalQuery;
        return this;
    }

    /*
     * http://localhost:3000
     */
    private String host;

    /**
     * Sets http://localhost:3000.
     * 
     * @param host the host value.
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    public AutoRestRequiredOptionalTestServiceBuilder host(String host) {
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
     * @return the AutoRestRequiredOptionalTestServiceBuilder.
     */
    public AutoRestRequiredOptionalTestServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestRequiredOptionalTestService with the provided parameters.
     * 
     * @return an instance of AutoRestRequiredOptionalTestService.
     */
    public AutoRestRequiredOptionalTestService build() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        AutoRestRequiredOptionalTestService client = new AutoRestRequiredOptionalTestService(pipeline);
        client.setRequiredGlobalPath(this.requiredGlobalPath);
        client.setRequiredGlobalQuery(this.requiredGlobalQuery);
        client.setOptionalGlobalQuery(this.optionalGlobalQuery);
        client.setHost(this.host);
        return client;
    }
}
