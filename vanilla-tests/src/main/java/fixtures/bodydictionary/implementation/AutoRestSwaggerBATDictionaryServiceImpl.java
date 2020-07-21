package fixtures.bodydictionary.implementation;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestSwaggerBATDictionaryService type. */
public final class AutoRestSwaggerBATDictionaryServiceImpl {
    /** server parameter. */
    private final String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /** The HTTP pipeline to send requests through. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** The DictionarysImpl object to access its operations. */
    private final DictionarysImpl dictionarys;

    /**
     * Gets the DictionarysImpl object to access its operations.
     *
     * @return the DictionarysImpl object.
     */
    public DictionarysImpl getDictionarys() {
        return this.dictionarys;
    }

    /** Initializes an instance of AutoRestSwaggerBATDictionaryService client. */
    public AutoRestSwaggerBATDictionaryServiceImpl(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATDictionaryService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestSwaggerBATDictionaryServiceImpl(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.dictionarys = new DictionarysImpl(this);
    }
}
