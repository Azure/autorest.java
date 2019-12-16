package fixtures.url;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * A builder for creating a new instance of the AutoRestUrlTestService type.
 */
@ServiceClientBuilder(serviceClients = AutoRestUrlTestService.class)
public final class AutoRestUrlTestServiceBuilder {
    /*
     * null
     */
    private String globalStringPath;

    /**
     * Sets null.
     * 
     * @param globalStringPath the globalStringPath value.
     * @return the AutoRestUrlTestServiceBuilder.
     */
    public AutoRestUrlTestServiceBuilder globalStringPath(String globalStringPath) {
        this.globalStringPath = globalStringPath;
        return this;
    }

    /*
     * null
     */
    private String globalStringQuery;

    /**
     * Sets null.
     * 
     * @param globalStringQuery the globalStringQuery value.
     * @return the AutoRestUrlTestServiceBuilder.
     */
    public AutoRestUrlTestServiceBuilder globalStringQuery(String globalStringQuery) {
        this.globalStringQuery = globalStringQuery;
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
     * @return the AutoRestUrlTestServiceBuilder.
     */
    public AutoRestUrlTestServiceBuilder host(String host) {
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
     * @return the AutoRestUrlTestServiceBuilder.
     */
    public AutoRestUrlTestServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestUrlTestService with the provided parameters.
     * 
     * @return an instance of AutoRestUrlTestService.
     */
    public AutoRestUrlTestService build() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        AutoRestUrlTestService client = new AutoRestUrlTestService(pipeline);
        client.setGlobalStringPath(this.globalStringPath);
        client.setGlobalStringQuery(this.globalStringQuery);
        client.setHost(this.host);
        return client;
    }
}
