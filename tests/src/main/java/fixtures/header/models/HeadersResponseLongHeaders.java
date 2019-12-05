package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseLongHeaders model.
 */
@Fluent
public final class HeadersResponseLongHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private long value;

    /*
     * The Content-Type property.
     */
    @JsonProperty(value = "Content-Type")
    private String contentType;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public long getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseLongHeaders object itself.
     */
    public HeadersResponseLongHeaders setValue(long value) {
        this.value = value;
        return this;
    }

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
     * @return the HeadersResponseLongHeaders object itself.
     */
    public HeadersResponseLongHeaders setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }
}
