// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.openai;

import com.generic.core.annotation.Generated;
import com.generic.core.annotation.ServiceClientBuilder;
import com.generic.core.credential.KeyCredential;
import com.generic.core.http.pipeline.HttpPipeline;
import com.generic.core.http.pipeline.HttpPipelineBuilder;
import com.generic.core.http.pipeline.HttpPipelinePolicy;
import com.generic.core.http.policy.credential.KeyCredentialPolicy;
import com.openai.implementation.OpenAIClientImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * A builder for creating a new instance of the OpenAIClient type.
 */
@ServiceClientBuilder(serviceClients = { OpenAIClient.class })
public final class OpenAIClientBuilder {
    @Generated
    private static final String SDK_NAME = "name";

    @Generated
    private static final String SDK_VERSION = "version";

    /**
     * Create an instance of the OpenAIClientBuilder.
     */
    @Generated
    public OpenAIClientBuilder() {
    }

    /*
     * The KeyCredential used for authentication.
     */
    @Generated
    private KeyCredential keyCredential;

    /**
     * {@inheritDoc}.
     */
    @Generated
    public OpenAIClientBuilder credential(KeyCredential keyCredential) {
        this.keyCredential = keyCredential;
        return this;
    }

    /**
     * Builds an instance of OpenAIClientImpl with the provided parameters.
     * 
     * @return an instance of OpenAIClientImpl.
     */
    @Generated
    private OpenAIClientImpl buildInnerClient() {
        OpenAIClientImpl client = new OpenAIClientImpl(this.createHttpPipeline());
        return client;
    }

    @Generated
    private HttpPipeline createHttpPipeline() {
        HttpPipelineBuilder httpPipelineBuilder = new HttpPipelineBuilder();
        List<HttpPipelinePolicy> policies = new ArrayList<>();
        if (keyCredential != null) {
            policies.add(new KeyCredentialPolicy("Authorization", keyCredential, "Bearer"));
        }
        httpPipelineBuilder.policies(policies.toArray(new HttpPipelinePolicy[0]));
        return httpPipelineBuilder.build();
    }

    /**
     * Builds an instance of OpenAIClient class.
     * 
     * @return an instance of OpenAIClient.
     */
    @Generated
    public OpenAIClient buildClient() {
        return new OpenAIClient(buildInnerClient());
    }
}
