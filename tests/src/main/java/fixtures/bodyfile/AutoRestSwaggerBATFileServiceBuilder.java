package fixtures.bodyfile;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.implementation.RestProxy;

/**
 * A builder for creating a new instance of the AutoRestSwaggerBATFileService type.
 */
@ServiceClientBuilder(serviceClients = AutoRestSwaggerBATFileService.class)
public final class AutoRestSwaggerBATFileServiceBuilder {
    /*
     * http://localhost:3000
     */
    private String host;

    /**
     * Sets http://localhost:3000.
     * 
     * @param host the host value.
     * @return the AutoRestSwaggerBATFileServiceBuilder.
     */
    public AutoRestSwaggerBATFileServiceBuilder host(String host) {
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
     * @return the AutoRestSwaggerBATFileServiceBuilder.
     */
    public AutoRestSwaggerBATFileServiceBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestSwaggerBATFileService with the provided parameters.
     * 
     * @return an instance of AutoRestSwaggerBATFileService.
     */
    public AutoRestSwaggerBATFileService build() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline = RestProxy.createDefaultPipeline();
        }
        AutoRestSwaggerBATFileService client = new AutoRestSwaggerBATFileService(pipeline);
        if (this.host != null) {
            client.setHost(this.host);
        }
        return client;
    }
}
