package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseBoolHeaders model.
 */
@Fluent
public final class HeadersResponseBoolHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Boolean value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public Boolean isValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseBoolHeaders object itself.
     */
    public HeadersResponseBoolHeaders setValue(Boolean value) {
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
