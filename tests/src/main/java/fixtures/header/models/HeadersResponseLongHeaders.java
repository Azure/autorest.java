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
}
