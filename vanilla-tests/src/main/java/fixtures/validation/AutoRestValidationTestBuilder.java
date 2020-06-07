package fixtures.validation;

import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** A builder for creating a new instance of the AutoRestValidationTest type. */
@ServiceClientBuilder(serviceClients = {AutoRestValidationTest.class})
public final class AutoRestValidationTestBuilder {
    /*
     * Subscription ID.
     */
    private String subscriptionId;

    /**
     * Sets Subscription ID.
     *
     * @param subscriptionId the subscriptionId value.
     * @return the AutoRestValidationTestBuilder.
     */
    public AutoRestValidationTestBuilder subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
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
     * @return the AutoRestValidationTestBuilder.
     */
    public AutoRestValidationTestBuilder host(String host) {
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
     * @return the AutoRestValidationTestBuilder.
     */
    public AutoRestValidationTestBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of AutoRestValidationTest with the provided parameters.
     *
     * @return an instance of AutoRestValidationTest.
     */
    public AutoRestValidationTest buildClient() {
        if (host == null) {
            this.host = "http://localhost:3000";
        }
        if (pipeline == null) {
            this.pipeline =
                    new HttpPipelineBuilder()
                            .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                            .build();
        }
        AutoRestValidationTest client = new AutoRestValidationTest(pipeline, subscriptionId, host);
        return client;
    }
}
