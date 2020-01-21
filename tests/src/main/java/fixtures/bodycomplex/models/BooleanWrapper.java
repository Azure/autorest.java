package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The BooleanWrapper model.
 */
@Fluent
public final class BooleanWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-BOOLEAN
     */
    @JsonProperty(value = "field_true")
    private Boolean fieldTrue;

    /*
     * MISSING·SCHEMA-DESCRIPTION-BOOLEAN
     */
    @JsonProperty(value = "field_false")
    private Boolean fieldFalse;

    /**
     * Get the fieldTrue property: MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * 
     * @return the fieldTrue value.
     */
    public Boolean isFieldTrue() {
        return this.fieldTrue;
    }

    /**
     * Set the fieldTrue property.
     * 
     * @param fieldTrue the fieldTrue value to set.
     * @return the BooleanWrapper object itself.
     */
    public BooleanWrapper setFieldTrue(Boolean fieldTrue) {
        this.fieldTrue = fieldTrue;
        return this;
    }

    /**
     * Get the fieldFalse property: MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * 
     * @return the fieldFalse value.
     */
    public Boolean isFieldFalse() {
        return this.fieldFalse;
    }

    /**
     * Set the fieldFalse property.
     * 
     * @param fieldFalse the fieldFalse value to set.
     * @return the BooleanWrapper object itself.
     */
    public BooleanWrapper setFieldFalse(Boolean fieldFalse) {
        this.fieldFalse = fieldFalse;
        return this;
    }

    public void validate() {
    }
}
