package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ReadonlyObj model.
 */
@Fluent
public final class ReadonlyObj {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "size")
    private Integer size;

    /**
     * Get the id property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the size property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @return the size value.
     */
    public Integer getSize() {
        return this.size;
    }

    /**
     * Set the size property.
     * 
     * @param size the size value to set.
     * @return the ReadonlyObj object itself.
     */
    public ReadonlyObj setSize(Integer size) {
        this.size = size;
        return this;
    }

    public void validate() {
    }
}
