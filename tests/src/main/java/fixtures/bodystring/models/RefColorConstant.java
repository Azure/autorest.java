package fixtures.bodystring.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The RefColorConstant model.
 */
@Fluent
public final class RefColorConstant {
    /*
     * Referenced Color Constant Description.
     */
    @JsonProperty(value = "ColorConstant", required = true)
    private String colorConstant;

    /*
     * Sample string.
     */
    @JsonProperty(value = "field1")
    private String field1;

    /**
     * Creates an instance of RefColorConstant class.
     */
    public RefColorConstant() {
        colorConstant = "green-color";
    }

    /**
     * Get the colorConstant property: Referenced Color Constant Description.
     * 
     * @return the colorConstant value.
     */
    public String getColorConstant() {
        return this.colorConstant;
    }

    /**
     * Set the colorConstant property: Referenced Color Constant Description.
     * 
     * @param colorConstant the colorConstant value to set.
     * @return the RefColorConstant object itself.
     */
    public RefColorConstant setColorConstant(String colorConstant) {
        this.colorConstant = colorConstant;
        return this;
    }

    /**
     * Get the field1 property: Sample string.
     * 
     * @return the field1 value.
     */
    public String getField1() {
        return this.field1;
    }

    /**
     * Set the field1 property: Sample string.
     * 
     * @param field1 the field1 value to set.
     * @return the RefColorConstant object itself.
     */
    public RefColorConstant setField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public void validate() {
    }
}
