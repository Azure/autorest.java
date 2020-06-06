package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ClassOptionalWrapper model. */
@Fluent
public final class ClassOptionalWrapper {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Product value;

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public Product getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the ClassOptionalWrapper object itself.
     */
    public ClassOptionalWrapper setValue(Product value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getValue() != null) {
            getValue().validate();
        }
    }
}
