package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The BooleanWrapper model.
 */
@Fluent
public final class BooleanWrapper {
    /*
     * The field_true property.
     */
    @JsonProperty(value = "field_true")
    private boolean fieldTrue;

    /*
     * The field_false property.
     */
    @JsonProperty(value = "field_false")
    private boolean fieldFalse;

    /**
     * Get the fieldTrue property: The field_true property.
     * 
     * @return the fieldTrue value.
     */
    public boolean isFieldTrue() {
        return this.fieldTrue;
    }

    /**
     * Set the fieldTrue property: The field_true property.
     * 
     * @param fieldTrue the fieldTrue value to set.
     * @return the BooleanWrapper object itself.
     */
    public BooleanWrapper setFieldTrue(boolean fieldTrue) {
        this.fieldTrue = fieldTrue;
        return this;
    }

    /**
     * Get the fieldFalse property: The field_false property.
     * 
     * @return the fieldFalse value.
     */
    public boolean isFieldFalse() {
        return this.fieldFalse;
    }

    /**
     * Set the fieldFalse property: The field_false property.
     * 
     * @param fieldFalse the fieldFalse value to set.
     * @return the BooleanWrapper object itself.
     */
    public BooleanWrapper setFieldFalse(boolean fieldFalse) {
        this.fieldFalse = fieldFalse;
        return this;
    }
}
