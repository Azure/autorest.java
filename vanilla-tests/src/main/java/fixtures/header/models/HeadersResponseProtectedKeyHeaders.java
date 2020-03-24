package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseProtectedKeyHeaders model.
 */
@Fluent
public final class HeadersResponseProtectedKeyHeaders {
    /*
     * The Content-Type property.
     */
    @JsonProperty(value = "Content-Type")
    private String contentType;

    /**
     * Get the contentType property: The Content-Type property.
     * 
     * @return the contentType value.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: The Content-Type property.
     * 
     * @param contentType the contentType value to set.
     * @return the HeadersResponseProtectedKeyHeaders object itself.
     */
    public HeadersResponseProtectedKeyHeaders setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public void validate() {
    }
}
