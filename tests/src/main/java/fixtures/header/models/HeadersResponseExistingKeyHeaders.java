package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseExistingKeyHeaders model.
 */
@Fluent
public final class HeadersResponseExistingKeyHeaders {
    /*
     * The User-Agent property.
     */
    @JsonProperty(value = "User-Agent")
    private String userAgent;

    /**
     * Get the userAgent property: The User-Agent property.
     * 
     * @return the userAgent value.
     */
    public String getUserAgent() {
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
}
