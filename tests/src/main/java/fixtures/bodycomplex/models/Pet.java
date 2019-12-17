package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Pet model.
 */
@Fluent
public class Pet {
    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "id")
    private Integer id;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * Get the id property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @return the id value.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Set the id property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @param id the id value to set.
     * @return the Pet object itself.
     */
    public Pet setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get the name property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param name the name value to set.
     * @return the Pet object itself.
     */
    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public void validate() {
    }
}
