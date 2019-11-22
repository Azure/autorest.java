package fixtures.bodycomplex;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * A builder for creating a new instance of the AutoRestComplexTestService type.
 */
@ServiceClientBuilder(serviceClients = AutoRestComplexTestService.class)
public final class AutoRestComplexTestServiceBuilder {
    /*
     * http://localhost:3000
     */
    private String host;

    /**
     * Sets http://localhost:3000.
     * 
     * @param host the host value.
     * @return the AutoRestComplexTestServiceBuilder.
     */
    public AutoRestComplexTestServiceBuilder host(String host) {
        this.host = host;
        return this;
    }

    /*
     * null
     */
    private String apiVersion;

    /**
     * Sets null.
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
    public AutoRestComplexTestService build() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = RestProxy.createDefaultPipeline();
        }
        AutoRestComplexTestService client = new AutoRestComplexTestService(pipeline);
        if (this.host != null) {
            client.setHost(this.host);
        }
        if (this.apiVersion != null) {
            client.setApiVersion(this.apiVersion);
        }
        return client;
    }
}
