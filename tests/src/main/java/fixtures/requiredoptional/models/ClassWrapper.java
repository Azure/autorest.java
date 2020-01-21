package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ClassWrapper model.
 */
@Fluent
public final class ClassWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA
     */
    @JsonProperty(value = "value", required = true)
    private Product value;

    /**
     * Get the value property: MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * 
     * @return the value value.
     */
    public Product getValue() {
        return this.value;
    }

    /**
     * Set the value property.
     * 
     * @param value the value value to set.
     * @return the ClassWrapper object itself.
     */
    public ClassWrapper setValue(Product value) {
        this.value = value;
        return this;
    }

    public void validate() {
        if (getValue() == null) {
            throw new IllegalArgumentException("Missing required property value in model ClassWrapper");
        } else {
            getValue().validate();
        }
    }
}
