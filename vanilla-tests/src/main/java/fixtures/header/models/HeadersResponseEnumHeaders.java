package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersResponseEnumHeaders model. */
@Fluent
public final class HeadersResponseEnumHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private GreyscaleColors value;

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public GreyscaleColors getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the HeadersResponseEnumHeaders object itself.
     */
    public HeadersResponseEnumHeaders setValue(GreyscaleColors value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
