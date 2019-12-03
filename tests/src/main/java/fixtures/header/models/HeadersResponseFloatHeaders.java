package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseFloatHeaders model.
 */
@Fluent
public final class HeadersResponseFloatHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private float value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public float getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseFloatHeaders object itself.
     */
    public HeadersResponseFloatHeaders setValue(float value) {
        this.value = value;
        return this;
    }
}
