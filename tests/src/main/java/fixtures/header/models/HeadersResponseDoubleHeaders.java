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
    private double value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseDoubleHeaders object itself.
     */
    public HeadersResponseDoubleHeaders setValue(double value) {
        this.value = value;
        return this;
    }
}
