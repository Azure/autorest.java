package fixtures.multipleinheritance.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Horse model. */
@Fluent
public final class Horse extends Pet {
    /*
     * The isAShowHorse property.
     */
    @JsonProperty(value = "isAShowHorse")
    private Boolean isAShowHorse;

    /**
     * Get the isAShowHorse property: The isAShowHorse property.
     *
     * @return the isAShowHorse value.
     */
    public Boolean isAShowHorse() {
        return this.isAShowHorse;
    }

    /**
     * Set the isAShowHorse property: The isAShowHorse property.
     *
     * @param isAShowHorse the isAShowHorse value to set.
     * @return the Horse object itself.
     */
    public Horse setIsAShowHorse(Boolean isAShowHorse) {
        this.isAShowHorse = isAShowHorse;
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
