package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The StringWrapper model.
 */
@Fluent
public final class StringWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "value", required = true)
    private String value;

    /**
     * Get the value property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property.
     * 
     * @param value the value value to set.
     * @return the StringWrapper object itself.
     */
    public StringWrapper setValue(String value) {
        this.value = value;
        return this;
    }

    public void validate() {
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model StringWrapper");
        }
    }
}
