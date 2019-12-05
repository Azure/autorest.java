package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseEnumHeaders model.
 */
@Fluent
public final class HeadersResponseEnumHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Enum0 value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public Enum0 getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseEnumHeaders object itself.
     */
    public HeadersResponseEnumHeaders setValue(Enum0 value) {
        this.value = value;
        return this;
    }
}
