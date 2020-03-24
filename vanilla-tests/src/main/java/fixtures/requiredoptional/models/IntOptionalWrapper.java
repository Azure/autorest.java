package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The IntOptionalWrapper model.
 */
@Fluent
public final class IntOptionalWrapper {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Integer value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the IntOptionalWrapper object itself.
     */
    public IntOptionalWrapper setValue(Integer value) {
        this.value = value;
        return this;
    }

    public void validate() {
    }
}
