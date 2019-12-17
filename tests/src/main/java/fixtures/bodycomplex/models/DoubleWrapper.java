package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The DoubleWrapper model.
 */
@Fluent
public final class DoubleWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-NUMBER
     */
    @JsonProperty(value = "field1")
    private Double field1;

    /*
     * MISSING·SCHEMA-DESCRIPTION-NUMBER
     */
    @JsonProperty(value = "field_56_zeros_after_the_dot_and_negative_zero_before_dot_and_this_is_a_long_field_name_on_purpose")
    private Double field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose;

    /**
     * Get the field1 property: MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * 
     * @return the field1 value.
     */
    public Double getField1() {
        return this.field1;
    }

    /**
     * Set the field1 property: MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * 
     * @param field1 the field1 value to set.
     * @return the DoubleWrapper object itself.
     */
    public DoubleWrapper setField1(Double field1) {
        this.field1 = field1;
        return this;
    }

    /**
     * Get the
     * field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose property: MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * 
     * @return the
     * field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose value.
     */
    public Double getField56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose() {
        return this.field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose;
    }

    /**
     * Set the
     * field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose property: MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * 
     * @param
     * field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose the field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose value to set.
     * @return the DoubleWrapper object itself.
     */
    public DoubleWrapper setField56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose(Double field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose) {
        this.field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose = field56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose;
        return this;
    }

    public void validate() {
    }
}
