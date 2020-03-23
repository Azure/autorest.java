package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The StringOptionalWrapper model.
 */
@Fluent
public final class StringOptionalWrapper {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private String value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the StringOptionalWrapper object itself.
     */
    public StringOptionalWrapper setValue(String value) {
        this.value = value;
        return this;
    }

    public void validate() {
    }
}
