package fixtures.multipleinheritance.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Feline model. */
@Fluent
public class Feline {
    /*
     * The meows property.
     */
    @JsonProperty(value = "meows")
    private Boolean meows;

    /*
     * The hisses property.
     */
    @JsonProperty(value = "hisses")
    private Boolean hisses;

    /**
     * Get the meows property: The meows property.
     *
     * @return the meows value.
     */
    public Boolean isMeows() {
        return this.meows;
    }

    /**
     * Set the meows property: The meows property.
     *
     * @param meows the meows value to set.
     * @return the Feline object itself.
     */
    public Feline setMeows(Boolean meows) {
        this.meows = meows;
        return this;
    }

    /**
     * Get the hisses property: The hisses property.
     *
     * @return the hisses value.
     */
    public Boolean isHisses() {
        return this.hisses;
    }

    /**
     * Set the hisses property: The hisses property.
     *
     * @param hisses the hisses value to set.
     * @return the Feline object itself.
     */
    public Feline setHisses(Boolean hisses) {
        this.hisses = hisses;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
