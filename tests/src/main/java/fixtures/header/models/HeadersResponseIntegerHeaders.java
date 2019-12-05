package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseIntegerHeaders model.
 */
@Fluent
public final class HeadersResponseIntegerHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private int value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseIntegerHeaders object itself.
     */
    public HeadersResponseIntegerHeaders setValue(int value) {
        this.value = value;
        return this;
    }
}
