package com.azure.ai.textanalytics;

import com.azure.ai.textanalytics.models.EntitiesBatchResult;
import com.azure.ai.textanalytics.models.ErrorResponseException;
import com.azure.ai.textanalytics.models.KeyPhraseBatchResult;
import com.azure.ai.textanalytics.models.LanguageBatchInput;
import com.azure.ai.textanalytics.models.LanguageBatchResult;
import com.azure.ai.textanalytics.models.MultiLanguageBatchInput;
import com.azure.ai.textanalytics.models.SentimentBatchResult;
import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the TextAnalyticsClient type.
 */
public final class TextAnalyticsClient {
    /**
     * The proxy service used to perform REST calls.
     */
    private TextAnalyticsClientService service;

    /**
     * Supported Cognitive Services endpoints (protocol and hostname, for example: https://westus.api.cognitive.microsoft.com).
     */
    private String endpoint;

    /**
     * Gets Supported Cognitive Services endpoints (protocol and hostname, for example: https://westus.api.cognitive.microsoft.com).
     * 
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
    }

    /**
     * Sets Supported Cognitive Services endpoints (protocol and hostname, for example: https://westus.api.cognitive.microsoft.com).
     * 
     * @param endpoint the endpoint value.
     * @return the service client itself.
     */
    TextAnalyticsClient setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * Initializes an instance of TextAnalyticsClient client.
     */
    public TextAnalyticsClient() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of TextAnalyticsClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public TextAnalyticsClient(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.service = RestProxy.create(TextAnalyticsClientService.class, this.httpPipeline);
    }

    /**
     * The interface defining all the services for TextAnalyticsClient to be
     * used by the proxy service to perform REST calls.
     */
    @Host("{Endpoint}/text/analytics/v2.1")
    @ServiceInterface(name = "TextAnalyticsClient")
    private interface TextAnalyticsClientService {
        @Post("/languages")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorResponseException.class)
        Mono<SimpleResponse<LanguageBatchResult>> detectLanguage(@HostParam("Endpoint") String endpoint, @QueryParam("showStats") Boolean showStats, @BodyParam("application/json") LanguageBatchInput languageBatchInput, Context context);

        @Post("/entities")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorResponseException.class)
        Mono<SimpleResponse<EntitiesBatchResult>> entities(@HostParam("Endpoint") String endpoint, @QueryParam("showStats") Boolean showStats, @BodyParam("application/json") MultiLanguageBatchInput multiLanguageBatchInput, Context context);

        @Post("/keyPhrases")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorResponseException.class)
        Mono<SimpleResponse<KeyPhraseBatchResult>> keyPhrases(@HostParam("Endpoint") String endpoint, @QueryParam("showStats") Boolean showStats, @BodyParam("application/json") MultiLanguageBatchInput multiLanguageBatchInput, Context context);

        @Post("/sentiment")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorResponseException.class)
        Mono<SimpleResponse<SentimentBatchResult>> sentiment(@HostParam("Endpoint") String endpoint, @QueryParam("showStats") Boolean showStats, @BodyParam("application/json") MultiLanguageBatchInput multiLanguageBatchInput, Context context);
    }

    /**
     * Scores close to 1 indicate 100% certainty that the identified language is true. A total of 120 languages are supported.
     * 
     * @param showStats 
     * @param languageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<LanguageBatchResult>> detectLanguageWithResponseAsync(Boolean showStats, LanguageBatchInput languageBatchInput) {
        if (this.getEndpoint() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getEndpoint() is required and cannot be null."));
        }
        if (languageBatchInput != null) {
            languageBatchInput.validate();
        }
        return FluxUtil.withContext(context -> service.detectLanguage(this.getEndpoint(), showStats, languageBatchInput, context));
    }

    /**
     * Scores close to 1 indicate 100% certainty that the identified language is true. A total of 120 languages are supported.
     * 
     * @param showStats 
     * @param languageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<LanguageBatchResult> detectLanguageAsync(Boolean showStats, LanguageBatchInput languageBatchInput) {
        return detectLanguageWithResponseAsync(showStats, languageBatchInput)
            .flatMap((SimpleResponse<LanguageBatchResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Scores close to 1 indicate 100% certainty that the identified language is true. A total of 120 languages are supported.
     * 
     * @param showStats 
     * @param languageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LanguageBatchResult detectLanguage(Boolean showStats, LanguageBatchInput languageBatchInput) {
        return detectLanguageAsync(showStats, languageBatchInput).block();
    }

    /**
     * To get even more information on each recognized entity we recommend using the Bing Entity Search API by querying for the recognized entities names. See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/text-analytics-supported-languages"&gt;Supported languages in Text Analytics API&lt;/a&gt; for the list of enabled languages.
     * 
     * @param showStats 
     * @param multiLanguageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<EntitiesBatchResult>> entitiesWithResponseAsync(Boolean showStats, MultiLanguageBatchInput multiLanguageBatchInput) {
        if (this.getEndpoint() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getEndpoint() is required and cannot be null."));
        }
        if (multiLanguageBatchInput != null) {
            multiLanguageBatchInput.validate();
        }
        return FluxUtil.withContext(context -> service.entities(this.getEndpoint(), showStats, multiLanguageBatchInput, context));
    }

    /**
     * To get even more information on each recognized entity we recommend using the Bing Entity Search API by querying for the recognized entities names. See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/text-analytics-supported-languages"&gt;Supported languages in Text Analytics API&lt;/a&gt; for the list of enabled languages.
     * 
     * @param showStats 
     * @param multiLanguageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<EntitiesBatchResult> entitiesAsync(Boolean showStats, MultiLanguageBatchInput multiLanguageBatchInput) {
        return entitiesWithResponseAsync(showStats, multiLanguageBatchInput)
            .flatMap((SimpleResponse<EntitiesBatchResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * To get even more information on each recognized entity we recommend using the Bing Entity Search API by querying for the recognized entities names. See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/text-analytics-supported-languages"&gt;Supported languages in Text Analytics API&lt;/a&gt; for the list of enabled languages.
     * 
     * @param showStats 
     * @param multiLanguageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public EntitiesBatchResult entities(Boolean showStats, MultiLanguageBatchInput multiLanguageBatchInput) {
        return entitiesAsync(showStats, multiLanguageBatchInput).block();
    }

    /**
     * See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by key phrase extraction.
     * 
     * @param showStats 
     * @param multiLanguageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<KeyPhraseBatchResult>> keyPhrasesWithResponseAsync(Boolean showStats, MultiLanguageBatchInput multiLanguageBatchInput) {
        if (this.getEndpoint() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getEndpoint() is required and cannot be null."));
        }
        if (multiLanguageBatchInput != null) {
            multiLanguageBatchInput.validate();
        }
        return FluxUtil.withContext(context -> service.keyPhrases(this.getEndpoint(), showStats, multiLanguageBatchInput, context));
    }

    /**
     * See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by key phrase extraction.
     * 
     * @param showStats 
     * @param multiLanguageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KeyPhraseBatchResult> keyPhrasesAsync(Boolean showStats, MultiLanguageBatchInput multiLanguageBatchInput) {
        return keyPhrasesWithResponseAsync(showStats, multiLanguageBatchInput)
            .flatMap((SimpleResponse<KeyPhraseBatchResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by key phrase extraction.
     * 
     * @param showStats 
     * @param multiLanguageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KeyPhraseBatchResult keyPhrases(Boolean showStats, MultiLanguageBatchInput multiLanguageBatchInput) {
        return keyPhrasesAsync(showStats, multiLanguageBatchInput).block();
    }

    /**
     * Scores close to 1 indicate positive sentiment, while scores close to 0 indicate negative sentiment. A score of 0.5 indicates the lack of sentiment (e.g. a factoid statement). See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by sentiment analysis.
     * 
     * @param showStats 
     * @param multiLanguageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SimpleResponse<SentimentBatchResult>> sentimentWithResponseAsync(Boolean showStats, MultiLanguageBatchInput multiLanguageBatchInput) {
        if (this.getEndpoint() == null) {
            return Mono.error(new IllegalArgumentException("Parameter this.getEndpoint() is required and cannot be null."));
        }
        if (multiLanguageBatchInput != null) {
            multiLanguageBatchInput.validate();
        }
        return FluxUtil.withContext(context -> service.sentiment(this.getEndpoint(), showStats, multiLanguageBatchInput, context));
    }

    /**
     * Scores close to 1 indicate positive sentiment, while scores close to 0 indicate negative sentiment. A score of 0.5 indicates the lack of sentiment (e.g. a factoid statement). See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by sentiment analysis.
     * 
     * @param showStats 
     * @param multiLanguageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SentimentBatchResult> sentimentAsync(Boolean showStats, MultiLanguageBatchInput multiLanguageBatchInput) {
        return sentimentWithResponseAsync(showStats, multiLanguageBatchInput)
            .flatMap((SimpleResponse<SentimentBatchResult> res) -> {
                if (res.getValue() != null) {
                    return Mono.just(res.getValue());
                } else {
                    return Mono.empty();
                }
            });
    }

    /**
     * Scores close to 1 indicate positive sentiment, while scores close to 0 indicate negative sentiment. A score of 0.5 indicates the lack of sentiment (e.g. a factoid statement). See the &lt;a href="https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/overview#supported-languages"&gt;Text Analytics Documentation&lt;/a&gt; for details about the languages that are supported by sentiment analysis.
     * 
     * @param showStats 
     * @param multiLanguageBatchInput 
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SentimentBatchResult sentiment(Boolean showStats, MultiLanguageBatchInput multiLanguageBatchInput) {
        return sentimentAsync(showStats, multiLanguageBatchInput).block();
    }
}
