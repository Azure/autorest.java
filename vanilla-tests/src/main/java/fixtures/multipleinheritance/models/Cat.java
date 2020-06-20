package fixtures.multipleinheritance.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Cat model. */
@Fluent
public class Cat extends Pet {
    /*
     * The likesMilk property.
     */
    @JsonProperty(value = "likesMilk")
    private Boolean likesMilk;

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
     * Get the likesMilk property: The likesMilk property.
     *
     * @return the likesMilk value.
     */
    public Boolean isLikesMilk() {
        return this.likesMilk;
    }

    /**
     * Set the likesMilk property: The likesMilk property.
     *
     * @param likesMilk the likesMilk value to set.
     * @return the Cat object itself.
     */
    public Cat setLikesMilk(Boolean likesMilk) {
        this.likesMilk = likesMilk;
        return this;
    }

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
     * @return the Cat object itself.
     */
    public Cat setMeows(Boolean meows) {
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
     * @return the Cat object itself.
     */
    public Cat setHisses(Boolean hisses) {
        this.hisses = hisses;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }
}
