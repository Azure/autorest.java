package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The IntWrapper model. */
@Fluent
public final class IntWrapper {
    /*
     * The value property.
     */
    @JsonProperty(value = "value", required = true)
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
     * @return the IntWrapper object itself.
     */
    public IntWrapper setValue(int value) {
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
