package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The ArrayWrapper model. */
@Fluent
public final class ArrayWrapper {
    /*
     * The value property.
     */
    @JsonProperty(value = "value", required = true)
    private List<String> value;

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public List<String> getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the ArrayWrapper object itself.
     */
    public ArrayWrapper setValue(List<String> value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model ArrayWrapper");
        }
    }
}
