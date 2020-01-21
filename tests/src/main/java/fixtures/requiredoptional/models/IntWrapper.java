package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The IntWrapper model.
 */
@Fluent
public final class IntWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "value", required = true)
    private int value;

    /**
     * Get the value property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @return the value value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Set the value property.
     * 
     * @param value the value value to set.
     * @return the IntWrapper object itself.
     */
    public IntWrapper setValue(int value) {
        this.value = value;
        return this;
    }

    public void validate() {
    }
}
