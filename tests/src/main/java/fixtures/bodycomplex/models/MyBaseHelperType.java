package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The MyBaseHelperType model.
 */
@Fluent
public final class MyBaseHelperType {
    /*
     * The propBH1 property.
     */
    @JsonProperty(value = "propBH1")
    private String propBh1;

    /**
     * Get the propBh1 property: The propBH1 property.
     * 
     * @return the propBh1 value.
     */
    public String getPropBh1() {
        return this.propBh1;
    }

    /**
     * Set the propBh1 property: The propBH1 property.
     * 
     * @param propBh1 the propBh1 value to set.
     * @return the MyBaseHelperType object itself.
     */
    public MyBaseHelperType setPropBh1(String propBh1) {
        this.propBh1 = propBh1;
        return this;
    }

    public void validate() {
    }
}
