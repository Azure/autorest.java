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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "null", defaultImpl = Fish.class)
@JsonTypeName("Fish")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Salmon", value = Salmon.class),
    @JsonSubTypes.Type(name = "SmartSalmon", value = SmartSalmon.class),
    @JsonSubTypes.Type(name = "Shark", value = Shark.class),
    @JsonSubTypes.Type(name = "Sawshark", value = Sawshark.class),
    @JsonSubTypes.Type(name = "Goblinshark", value = Goblinshark.class),
    @JsonSubTypes.Type(name = "Cookiecuttershark", value = Cookiecuttershark.class)
})
@Fluent
public class Fish {
    /*
     * The fishtype property.
     */
    @JsonProperty(value = "fishtype", required = true)
    private String fishtype;

    /*
     * The species property.
     */
    @JsonProperty(value = "species")
    private String species;

    /*
     * The length property.
     */
    @JsonProperty(value = "length", required = true)
    private double length;

    /*
     * The siblings property.
     */
    @JsonProperty(value = "siblings")
    private List<Fish> siblings;

    /**
     * Get the fishtype property: The fishtype property.
     * 
     * @return the fishtype value.
     */
    public String getFishtype() {
        return this.fishtype;
    }

    /**
     * Set the fishtype property: The fishtype property.
     * 
     * @param fishtype the fishtype value to set.
     * @return the Fish object itself.
     */
    public Fish setFishtype(String fishtype) {
        this.fishtype = fishtype;
        return this;
    }

    /**
     * Get the species property: The species property.
     * 
     * @return the species value.
     */
    public String getSpecies() {
        return this.species;
    }

    /**
     * Set the species property: The species property.
     * 
     * @param species the species value to set.
     * @return the Fish object itself.
     */
    public Fish setSpecies(String species) {
        this.species = species;
        return this;
    }

    /**
     * Get the length property: The length property.
     * 
     * @return the length value.
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Set the length property: The length property.
     * 
     * @param length the length value to set.
     * @return the Fish object itself.
     */
    public Fish setLength(double length) {
        this.length = length;
        return this;
    }

    /**
     * Get the siblings property: The siblings property.
     * 
     * @return the siblings value.
     */
    public List<Fish> getSiblings() {
        return this.siblings;
    }

    /**
     * Set the siblings property: The siblings property.
     * 
     * @param siblings the siblings value to set.
     * @return the Fish object itself.
     */
    public Fish setSiblings(List<Fish> siblings) {
        this.siblings = siblings;
        return this;
    }
}
