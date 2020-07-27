package fixtures.url.multi;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** A builder for creating a new instance of the AutoRestUrlMutliCollectionFormatTestService type. */
@ServiceClientBuilder(serviceClients = {AutoRestUrlMutliCollectionFormatTestService.class})
public final class AutoRestUrlMutliCollectionFormatTestServiceBuilder {
    /*
     * server parameter
     */
    private String host;

    /**
     * Sets server parameter.
     *
     * @param host the host value.
     * @return the AutoRestUrlMutliCollectionFormatTestServiceBuilder.
     */
    public AutoRestUrlMutliCollectionFormatTestServiceBuilder host(String host) {
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
     * @return the AutoRestUrlMutliCollectionFormatTestServiceBuilder.
     */
    public AutoRestUrlMutliCollectionFormatTestServiceBuilder pipeline(HttpPipeline pipeline) {
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
     * @return the AutoRestUrlMutliCollectionFormatTestServiceBuilder.
     */
    public AutoRestUrlMutliCollectionFormatTestServiceBuilder serializerAdapter(SerializerAdapter serializerAdapter) {
        this.serializerAdapter = serializerAdapter;
        return this;
    }

    /**
     * Builds an instance of AutoRestUrlMutliCollectionFormatTestService with the provided parameters.
     *
     * @return an instance of AutoRestUrlMutliCollectionFormatTestService.
     */
    public AutoRestUrlMutliCollectionFormatTestService buildClient() {
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
        AutoRestUrlMutliCollectionFormatTestService client =
                new AutoRestUrlMutliCollectionFormatTestService(pipeline, serializerAdapter, host);
        return client;
    }
}
