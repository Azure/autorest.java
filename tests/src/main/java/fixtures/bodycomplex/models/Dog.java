package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Dog model.
 */
@Fluent
public final class Dog extends Pet {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "food")
    private String food;

    /**
     * Get the food property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the food value.
     */
    public String getFood() {
        return this.food;
    }

    /**
     * Set the food property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param food the food value to set.
     * @return the Dog object itself.
     */
    public Dog setFood(String food) {
        this.food = food;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
    }
}
