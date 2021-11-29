package fixtures.deferredheaderdeserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersResponseProtectedKeyHeaders model. */
@Fluent
public final class HeadersResponseProtectedKeyHeaders {
    /*
     * The Content-Type property.
     */
    @JsonProperty(value = "Content-Type")
    private String contentType;

    // Maintains deserialization status for contentType.
    private boolean contentTypeHasBeenDeserialized;

    // HttpHeaders containing the raw property values.
    private final HttpHeaders rawHeaders;

    /** Creates an instance of HeadersResponseProtectedKeyHeaders class. */
    HeadersResponseProtectedKeyHeaders(HttpHeaders rawHeaders) {
        this.rawHeaders = rawHeaders;
    }

    /**
     * Get the contentType property: The Content-Type property.
     *
     * @return the contentType value.
     */
    public String getContentType() {
        if (!this.contentTypeHasBeenDeserialized) {
            this.contentType = rawHeaders.getValue("Content-Type");
            this.contentTypeHasBeenDeserialized = true;
        }
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

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
