package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The MyDerivedType model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "kind")
@JsonTypeName("Kind1")
@Fluent
public final class MyDerivedType extends MyBaseType {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "propD1")
    private String propD1;

    /**
     * Get the propD1 property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the propD1 value.
     */
    public String getPropD1() {
        return this.propD1;
    }

    /**
     * Set the propD1 property.
     * 
     * @param propD1 the propD1 value to set.
     * @return the MyDerivedType object itself.
     */
    public MyDerivedType setPropD1(String propD1) {
        this.propD1 = propD1;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
    }
}
