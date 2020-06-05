package fixtures.extensibleenums;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the PetStoreInc type. */
public final class PetStoreInc {
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

    /** The Pets object to access its operations. */
    private final Pets pets;

    /**
     * Gets the Pets object to access its operations.
     *
     * @return the Pets object.
     */
    public Pets getPets() {
        return this.pets;
    }

    /** Initializes an instance of PetStoreInc client. */
    PetStoreInc(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                host);
    }

    /**
     * Initializes an instance of PetStoreInc client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    PetStoreInc(HttpPipeline httpPipeline, String host) {
        this.httpPipeline = httpPipeline;
        this.host = host;
        this.pets = new Pets(this);
    }
}
