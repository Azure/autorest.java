package fixtures.bodydictionary;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RestProxy;

/**
 * Initializes a new instance of the AutoRestSwaggerBATdictionaryService type.
 */
public final class AutoRestSwaggerBATdictionaryService {
    /**
     * http://localhost:3000.
     */
    private String host;

    /**
     * Gets http://localhost:3000.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets http://localhost:3000.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    AutoRestSwaggerBATdictionaryService setHost(String host) {
        this.host = host;
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
     * The Dictionarys object to access its operations.
     */
    private Dictionarys dictionarys;

    /**
     * Gets the Dictionarys object to access its operations.
     * 
     * @return the Dictionarys object.
     */
    public Dictionarys dictionarys() {
        return this.dictionarys;
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATdictionaryService client.
     */
    public AutoRestSwaggerBATdictionaryService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestSwaggerBATdictionaryService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestSwaggerBATdictionaryService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.dictionarys = new Dictionarys(this);
    }
}
