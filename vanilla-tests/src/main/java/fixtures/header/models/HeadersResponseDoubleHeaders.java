package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseDoubleHeaders model.
 */
@Fluent
public final class HeadersResponseDoubleHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Double value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public Double getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseDoubleHeaders object itself.
     */
    public HeadersResponseDoubleHeaders setValue(Double value) {
        this.value = value;
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
