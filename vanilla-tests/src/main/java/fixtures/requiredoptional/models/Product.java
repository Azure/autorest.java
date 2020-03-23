package fixtures.requiredoptional.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Product model.
 */
@Fluent
public final class Product {
    /*
     * The id property.
     */
    @JsonProperty(value = "id", required = true)
    private int id;

    /*
     * The name property.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     * 
     * @param id the id value to set.
     * @return the Product object itself.
     */
    public Product setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Get the name property: The name property.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     * 
     * @param name the name value to set.
     * @return the Product object itself.
     */
    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public void validate() {
    }
}
