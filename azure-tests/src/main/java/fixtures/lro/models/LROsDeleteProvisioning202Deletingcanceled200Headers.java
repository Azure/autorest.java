package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The LROsDeleteProvisioning202Deletingcanceled200Headers model. */
@Fluent
public final class LROsDeleteProvisioning202Deletingcanceled200Headers {
    @JsonIgnore
    private final ClientLogger logger = new ClientLogger(LROsDeleteProvisioning202Deletingcanceled200Headers.class);

    /*
     * The Retry-After property.
     */
    @JsonProperty(value = "Retry-After")
    private Integer retryAfter;

    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location;

    /**
     * Get the retryAfter property: The Retry-After property.
     *
     * @return the retryAfter value.
     */
    public Integer retryAfter() {
        return this.retryAfter;
    }

    /**
     * Set the retryAfter property: The Retry-After property.
     *
     * @param retryAfter the retryAfter value to set.
     * @return the LROsDeleteProvisioning202Deletingcanceled200Headers object itself.
     */
    public LROsDeleteProvisioning202Deletingcanceled200Headers withRetryAfter(Integer retryAfter) {
        this.retryAfter = retryAfter;
        return this;
    }

    /**
     * Get the location property: The Location property.
     *
     * @return the location value.
     */
    public String location() {
        return this.location;
    }

    /**
     * Set the location property: The Location property.
     *
     * @param location the location value to set.
     * @return the LROsDeleteProvisioning202Deletingcanceled200Headers object itself.
     */
    public LROsDeleteProvisioning202Deletingcanceled200Headers withLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
