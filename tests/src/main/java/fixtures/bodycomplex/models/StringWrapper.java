package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The StringWrapper model.
 */
@Fluent
public final class StringWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "field")
    private String field;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "empty")
    private String empty;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "null")
    private String nullProperty;

    /**
     * Get the field property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the field value.
     */
    public String getField() {
        return this.field;
    }

    /**
     * Set the field property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param field the field value to set.
     * @return the StringWrapper object itself.
     */
    public StringWrapper setField(String field) {
        this.field = field;
        return this;
    }

    /**
     * Get the empty property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the empty value.
     */
    public String getEmpty() {
        return this.empty;
    }

    /**
     * Set the empty property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param empty the empty value to set.
     * @return the StringWrapper object itself.
     */
    public StringWrapper setEmpty(String empty) {
        this.empty = empty;
        return this;
    }

    /**
     * Get the nullProperty property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the nullProperty value.
     */
    public String getNullProperty() {
        return this.nullProperty;
    }

    /**
     * Set the nullProperty property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param nullProperty the nullProperty value to set.
     * @return the StringWrapper object itself.
     */
    public StringWrapper setNullProperty(String nullProperty) {
        this.nullProperty = nullProperty;
        return this;
    }

    public void validate() {
    }
}
