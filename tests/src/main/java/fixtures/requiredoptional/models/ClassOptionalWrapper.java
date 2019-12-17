package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ClassOptionalWrapper model.
 */
@Fluent
public final class ClassOptionalWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA
     */
    @JsonProperty(value = "value")
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
     * Set the value property: MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * 
     * @param value the value value to set.
     * @return the ClassOptionalWrapper object itself.
     */
    public ClassOptionalWrapper setValue(Product value) {
        this.value = value;
        return this;
    }

    public void validate() {
        if (getValue() != null) {
            getValue().validate();
        }
    }
}
