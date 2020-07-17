package fixtures.url;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** A builder for creating a new instance of the AutoRestUrlTestService type. */
@ServiceClientBuilder(serviceClients = {AutoRestUrlTestService.class})
public final class AutoRestUrlTestServiceBuilder {
    /*
     * A string value 'globalItemStringPath' that appears in the path
     */
    private String globalStringPath;

    /**
     * Sets A string value 'globalItemStringPath' that appears in the path.
     *
     * @param globalStringPath the globalStringPath value.
     * @return the AutoRestUrlTestServiceBuilder.
     */
    public AutoRestUrlTestServiceBuilder globalStringPath(String globalStringPath) {
        this.globalStringPath = globalStringPath;
        return this;
    }

    /*
     * should contain value null
     */
    private String globalStringQuery;

    /**
     * Sets should contain value null.
     *
     * @param globalStringQuery the globalStringQuery value.
     * @return the AutoRestUrlTestServiceBuilder.
     */
    public AutoRestUrlTestServiceBuilder globalStringQuery(String globalStringQuery) {
        this.globalStringQuery = globalStringQuery;
        return this;
    }

    /*
     * server parameter
     */
    private String host;

    /**
     * Sets server parameter.
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

    /*
     * The serializer to serialize an object into a string
     */
    private SerializerAdapter serializerAdapter;

    /**
     * Sets The serializer to serialize an object into a string.
     *
     * @param serializerAdapter the serializerAdapter value.
     * @return the AutoRestUrlTestServiceBuilder.
     */
    public AutoRestUrlTestServiceBuilder serializerAdapter(SerializerAdapter serializerAdapter) {
        this.serializerAdapter = serializerAdapter;
        return this;
    }

    /**
     * Builds an instance of AutoRestUrlTestService with the provided parameters.
     *
     * @return an instance of AutoRestUrlTestService.
     */
    public AutoRestUrlTestService buildClient() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline =
                    new HttpPipelineBuilder()
                            .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                            .build();
        }
        if (serializerAdapter == null) {
            this.serializerAdapter = JacksonAdapter.createDefaultSerializerAdapter();
        }
        AutoRestUrlTestService client =
                new AutoRestUrlTestService(pipeline, serializerAdapter, globalStringPath, globalStringQuery, host);
        return client;
    }
}
