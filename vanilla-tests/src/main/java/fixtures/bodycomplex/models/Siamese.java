package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The Siamese model. */
@Fluent
public final class Siamese extends Cat {
    /*
     * The breed property.
     */
    @JsonProperty(value = "breed")
    private String breed;

    /**
     * Get the breed property: The breed property.
     *
     * @return the breed value.
     */
    public String getBreed() {
        return this.breed;
    }

    /**
     * Set the breed property: The breed property.
     *
     * @param breed the breed value to set.
     * @return the Siamese object itself.
     */
    public Siamese setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Siamese setColor(String color) {
        super.setColor(color);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Siamese setHates(List<Dog> hates) {
        super.setHates(hates);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Siamese setId(Integer id) {
        super.setId(id);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Siamese setName(String name) {
        super.setName(name);
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
