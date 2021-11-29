package fixtures.deferredheaderdeserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersResponseExistingKeyHeaders model. */
@Fluent
public final class HeadersResponseExistingKeyHeaders {
    /*
     * The User-Agent property.
     */
    @JsonProperty(value = "User-Agent")
    private String userAgent;

    // Maintains deserialization status for userAgent.
    private boolean userAgentHasBeenDeserialized;

    // HttpHeaders containing the raw property values.
    private final HttpHeaders rawHeaders;

    /** Creates an instance of HeadersResponseExistingKeyHeaders class. */
    HeadersResponseExistingKeyHeaders(HttpHeaders rawHeaders) {
        this.rawHeaders = rawHeaders;
    }

    /**
     * Get the userAgent property: The User-Agent property.
     *
     * @return the userAgent value.
     */
    public String getUserAgent() {
        if (!this.userAgentHasBeenDeserialized) {
            this.userAgent = rawHeaders.getValue("User-Agent");
            this.userAgentHasBeenDeserialized = true;
        }
        return this.userAgent;
    }

    /**
     * Set the userAgent property: The User-Agent property.
     *
     * @param userAgent the userAgent value to set.
     * @return the HeadersResponseExistingKeyHeaders object itself.
     */
    public HeadersResponseExistingKeyHeaders setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
