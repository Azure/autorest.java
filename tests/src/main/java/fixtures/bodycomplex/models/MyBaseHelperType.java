package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The MyBaseHelperType model.
 */
@Fluent
public final class MyBaseHelperType {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "propBH1")
    private String propBH1;

    /**
     * Get the propBH1 property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the propBH1 value.
     */
    public String getPropBH1() {
        return this.propBH1;
    }

    /**
     * Set the propBH1 property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param propBH1 the propBH1 value to set.
     * @return the MyBaseHelperType object itself.
     */
    public MyBaseHelperType setPropBH1(String propBH1) {
        this.propBH1 = propBH1;
        return this;
    }

    public void validate() {
    }
}
