package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Siamese model.
 */
@Fluent
public final class Siamese extends Cat {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "breed")
    private String breed;

    /**
     * Get the breed property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the breed value.
     */
    public String getBreed() {
        return this.breed;
    }

    /**
     * Set the breed property.
     * 
     * @param breed the breed value to set.
     * @return the Siamese object itself.
     */
    public Siamese setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
    }
}
