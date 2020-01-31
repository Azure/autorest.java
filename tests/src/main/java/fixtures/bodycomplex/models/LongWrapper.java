package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The LongWrapper model.
 */
@Fluent
public final class LongWrapper {
    /*
     * The field1 property.
     */
    @JsonProperty(value = "field1")
    private Long field1;

    /*
     * The field2 property.
     */
    @JsonProperty(value = "field2")
    private Long field2;

    /**
     * Get the field1 property: The field1 property.
     * 
     * @return the field1 value.
     */
    public Long getField1() {
        return this.field1;
    }

    /**
     * Set the field1 property: The field1 property.
     * 
     * @param field1 the field1 value to set.
     * @return the LongWrapper object itself.
     */
    public LongWrapper setField1(Long field1) {
        this.field1 = field1;
        return this;
    }

    /**
     * Get the field2 property: The field2 property.
     * 
     * @return the field2 value.
     */
    public Long getField2() {
        return this.field2;
    }

    /**
     * Set the field2 property: The field2 property.
     * 
     * @param field2 the field2 value to set.
     * @return the LongWrapper object itself.
     */
    public LongWrapper setField2(Long field2) {
        this.field2 = field2;
        return this;
    }

    public void validate() {
    }
}
