package com.azure.ai.textanalytics;

import com.azure.ai.textanalytics.models.EntitiesBatchResult;
import com.azure.ai.textanalytics.models.KeyPhraseBatchResult;
import com.azure.ai.textanalytics.models.LanguageBatchInput;
import com.azure.ai.textanalytics.models.LanguageBatchResult;
import com.azure.ai.textanalytics.models.MultiLanguageBatchInput;
import com.azure.ai.textanalytics.models.SentimentBatchResult;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClientBuilder;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.SimpleResponse;
import reactor.core.publisher.Mono;

/**
 * A builder for creating a new instance of the TextAnalyticsClient type.
 */
@ServiceClientBuilder(serviceClients = TextAnalyticsClient.class)
public final class TextAnalyticsClientBuilder {
    /*
     * Supported Cognitive Services endpoints (protocol and hostname, for example: https://westus.api.cognitive.microsoft.com).
     */
    private String endpoint;

    /**
     * Sets Supported Cognitive Services endpoints (protocol and hostname, for example: https://westus.api.cognitive.microsoft.com).
     * 
     * @param endpoint the endpoint value.
     * @return the TextAnalyticsClientBuilder.
     */
    public TextAnalyticsClientBuilder endpoint(String endpoint) {
        this.endpoint = endpoint;
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
     * @return the TextAnalyticsClientBuilder.
     */
    public TextAnalyticsClientBuilder pipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }

    /**
     * Builds an instance of TextAnalyticsClient with the provided parameters.
     * 
     * @return an instance of TextAnalyticsClient.
     */
    public TextAnalyticsClient build() {
        if (pipeline == null) {
            this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
        }
        TextAnalyticsClient client = new TextAnalyticsClient(pipeline);
        client.setEndpoint(this.endpoint);
        return client;
    }

    /**
     * Builds an instance of TextAnalyticsClientAsync async client.
     * 
     * @return an instance of TextAnalyticsClientAsync.
     */
    public TextAnalyticsClientAsync buildAsyncClient() {
        return new TextAnalyticsClientAsync(build());
    }

    /**
     * Builds an instance of TextAnalyticsClientSync sync client.
     * 
     * @return an instance of TextAnalyticsClientSync.
     */
    public TextAnalyticsClientSync buildSyncClient() {
        return new TextAnalyticsClientSync(build());
    }
}
