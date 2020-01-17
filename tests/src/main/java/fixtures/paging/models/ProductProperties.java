package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ProductProperties model.
 */
@Fluent
public final class ProductProperties {
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
     * Set the id property.
     * 
     * @param id the id value to set.
     * @return the ProductProperties object itself.
     */
    public ProductProperties setId(Integer id) {
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
     * Set the name property.
     * 
     * @param name the name value to set.
     * @return the ProductProperties object itself.
     */
    public ProductProperties setName(String name) {
        this.name = name;
        return this;
    }

    public void validate() {
    }
}
