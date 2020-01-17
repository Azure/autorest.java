package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The DotFish model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fish.type", defaultImpl = DotFish.class)
@JsonTypeName("DotFish")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "DotSalmon", value = DotSalmon.class)
})
@Fluent
public class DotFish {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "species")
    private String species;

    /**
     * Get the species property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the species value.
     */
    public String getSpecies() {
        return this.species;
    }

    /**
     * Set the species property.
     * 
     * @param species the species value to set.
     * @return the DotFish object itself.
     */
    public DotFish setSpecies(String species) {
        this.species = species;
        return this;
    }

    public void validate() {
    }
}
