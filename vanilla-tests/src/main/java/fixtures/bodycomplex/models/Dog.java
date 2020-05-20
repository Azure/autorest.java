package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Dog model. */
@Fluent
public final class Dog extends Pet {
    /*
     * The food property.
     */
    @JsonProperty(value = "food")
    private String food;

    /**
     * Get the food property: The food property.
     *
     * @return the food value.
     */
    public String getFood() {
        return this.food;
    }

    /**
     * Set the food property: The food property.
     *
     * @param food the food value to set.
     * @return the Dog object itself.
     */
    public Dog setFood(String food) {
        this.food = food;
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
