package fixtures.bodystring.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The RefColorConstant model.
 */
@Fluent
public final class RefColorConstant {
    /*
     * The ColorConstant property.
     */
    @JsonProperty(value = "ColorConstant", required = true)
    private String colorConstant;

    /*
     * The field1 property.
     */
    @JsonProperty(value = "field1")
    private String field1;

    /**
     * Get the colorConstant property: The ColorConstant property.
     * 
     * @return the colorConstant value.
     */
    public String getColorConstant() {
        return this.colorConstant;
    }

    /**
     * Set the colorConstant property: The ColorConstant property.
     * 
     * @param colorConstant the colorConstant value to set.
     * @return the RefColorConstant object itself.
     */
    public RefColorConstant setColorConstant(String colorConstant) {
        this.colorConstant = colorConstant;
        return this;
    }

    /**
     * Get the field1 property: The field1 property.
     * 
     * @return the field1 value.
     */
    public String getField1() {
        return this.field1;
    }

    /**
     * Set the field1 property: The field1 property.
     * 
     * @param field1 the field1 value to set.
     * @return the RefColorConstant object itself.
     */
    public RefColorConstant setField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public void validate() {
        if (getColorConstant() == null) {
            throw new IllegalArgumentException("Missing required property colorConstant in model RefColorConstant");
        }
    }
}
