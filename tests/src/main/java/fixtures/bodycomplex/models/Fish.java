package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;

/**
 * The Fish model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype", defaultImpl = Fish.class)
@JsonTypeName("Fish")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "salmon", value = Salmon.class),
    @JsonSubTypes.Type(name = "shark", value = Shark.class)
})
@Fluent
public class Fish {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "species")
    private String species;

    /*
     * MISSING·SCHEMA-DESCRIPTION-NUMBER
     */
    @JsonProperty(value = "length", required = true)
    private float length;

    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "siblings")
    private List<Fish> siblings;

    /**
     * Get the species property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the species value.
     */
    public String getSpecies() {
        return this.species;
    }

    /**
     * Set the species property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param species the species value to set.
     * @return the Fish object itself.
     */
    public Fish setSpecies(String species) {
        this.species = species;
        return this;
    }

    /**
     * Get the length property: MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * 
     * @return the length value.
     */
    public float getLength() {
        return this.length;
    }

    /**
     * Set the length property: MISSING·SCHEMA-DESCRIPTION-NUMBER.
     * 
     * @param length the length value to set.
     * @return the Fish object itself.
     */
    public Fish setLength(float length) {
        this.length = length;
        return this;
    }

    /**
     * Get the siblings property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the siblings value.
     */
    public List<Fish> getSiblings() {
        return this.siblings;
    }

    /**
     * Set the siblings property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @param siblings the siblings value to set.
     * @return the Fish object itself.
     */
    public Fish setSiblings(List<Fish> siblings) {
        this.siblings = siblings;
        return this;
    }

    public void validate() {
        if (getSiblings() != null) {
            getSiblings().forEach(e -> e.validate());
        }
    }
}
