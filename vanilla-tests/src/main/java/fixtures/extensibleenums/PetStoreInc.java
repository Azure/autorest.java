package fixtures.extensibleenums;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the PetStoreInc type. */
public final class PetStoreInc {
    /** server parameter. */
    private String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets server parameter.
     *
     * @param host the host value.
     * @return the service client itself.
     */
    public PetStoreInc setHost(String host) {
        this.host = host;
        return this;
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
    public PetStoreInc() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of PetStoreInc client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public PetStoreInc(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.pets = new Pets(this);
    }
}
