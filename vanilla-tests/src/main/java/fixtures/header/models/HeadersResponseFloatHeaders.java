package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersResponseFloatHeaders model. */
@Fluent
public final class HeadersResponseFloatHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Float value;

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public Float getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the HeadersResponseFloatHeaders object itself.
     */
    public HeadersResponseFloatHeaders setValue(Float value) {
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
