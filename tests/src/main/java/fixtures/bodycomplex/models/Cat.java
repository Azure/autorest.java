package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The Cat model.
 */
@Fluent
public class Cat extends Pet {
    /*
     * The color property.
     */
    @JsonProperty(value = "color")
    private String color;

    /*
     * The hates property.
     */
    @JsonProperty(value = "hates")
    private List<Dog> hates;

    /**
     * Get the color property: The color property.
     * 
     * @return the color value.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Set the color property: The color property.
     * 
     * @param color the color value to set.
     * @return the Cat object itself.
     */
    public Cat setColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Get the hates property: The hates property.
     * 
     * @return the hates value.
     */
    public List<Dog> getHates() {
        return this.hates;
    }

    /**
     * Set the hates property: The hates property.
     * 
     * @param hates the hates value to set.
     * @return the Cat object itself.
     */
    public Cat setHates(List<Dog> hates) {
        this.hates = hates;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
        if (getHates() != null) {
            getHates().forEach(e -> e.validate());
        }
    }
}
