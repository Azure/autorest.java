package fixtures.custombaseuri.moreoptions;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** A builder for creating a new instance of the AutoRestParameterizedCustomHostTestClient type. */
@ServiceClientBuilder(serviceClients = {AutoRestParameterizedCustomHostTestClient.class})
public final class AutoRestParameterizedCustomHostTestClientBuilder {
    /*
     * The subscription id with value 'test12'.
     */
    private String subscriptionId;

    /**
     * Sets The subscription id with value 'test12'.
     *
     * @param subscriptionId the subscriptionId value.
     * @return the AutoRestParameterizedCustomHostTestClientBuilder.
     */
    public AutoRestParameterizedCustomHostTestClientBuilder subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    /*
     * A string value that is used as a global part of the parameterized host.
     * Default value 'host'.
     */
    private String dnsSuffix;

    /**
     * Sets A string value that is used as a global part of the parameterized host. Default value 'host'.
     *
     * @param dnsSuffix the dnsSuffix value.
     * @return the AutoRestParameterizedCustomHostTestClientBuilder.
     */
    public AutoRestParameterizedCustomHostTestClientBuilder dnsSuffix(String dnsSuffix) {
        this.dnsSuffix = dnsSuffix;
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
     * @return the AutoRestParameterizedCustomHostTestClientBuilder.
     */
    public AutoRestParameterizedCustomHostTestClientBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestParameterizedCustomHostTestClient with the provided parameters.
     *
     * @return an instance of AutoRestParameterizedCustomHostTestClient.
     */
    public AutoRestParameterizedCustomHostTestClient buildClient() {
        if (dnsSuffix == null) {
            this.dnsSuffix = "host";
        }
        if (pipeline == null) {
            this.pipeline =
                    new HttpPipelineBuilder()
                            .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                            .build();
        }
        AutoRestParameterizedCustomHostTestClient client = new AutoRestParameterizedCustomHostTestClient(pipeline);
        client.setSubscriptionId(this.subscriptionId);
        client.setDnsSuffix(this.dnsSuffix);
        return client;
    }
}
