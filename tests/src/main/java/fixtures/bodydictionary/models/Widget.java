package fixtures.bodydictionary.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Widget model.
 */
@Fluent
public final class Widget {
    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "integer")
    private Integer integer;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "string")
    private String string;

    /**
     * Get the integer property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @return the integer value.
     */
    public Integer getInteger() {
        return this.integer;
    }

    /**
     * Set the integer property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @param integer the integer value to set.
     * @return the Widget object itself.
     */
    public Widget setInteger(Integer integer) {
        this.integer = integer;
        return this;
    }

    /**
     * Get the string property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the string value.
     */
    public String getString() {
        return this.string;
    }

    /**
     * Set the string property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param string the string value to set.
     * @return the Widget object itself.
     */
    public Widget setString(String string) {
        this.string = string;
        return this;
    }

    public void validate() {
    }
}
