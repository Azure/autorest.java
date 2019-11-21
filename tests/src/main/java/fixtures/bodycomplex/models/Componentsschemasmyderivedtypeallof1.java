package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Componentsschemasmyderivedtypeallof1 model.
 */
@Fluent
public class Componentsschemasmyderivedtypeallof1 {
    /*
     * The propD1 property.
     */
    @JsonProperty(value = "propD1")
    private String propD1;

    /**
     * Get the propD1 property: The propD1 property.
     * 
     * @return the propD1 value.
     */
    public String getPropD1() {
        return this.propD1;
    }

    /**
     * Set the propD1 property: The propD1 property.
     * 
     * @param propD1 the propD1 value to set.
     * @return the Componentsschemasmyderivedtypeallof1 object itself.
     */
    public Componentsschemasmyderivedtypeallof1 setPropD1(String propD1) {
        this.propD1 = propD1;
        return this;
    }
}
