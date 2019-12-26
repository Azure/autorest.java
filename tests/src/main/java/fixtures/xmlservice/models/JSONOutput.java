package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The JSONOutput model.
 */
@JacksonXmlRootElement(localName = "JSONOutput")
@Fluent
public final class JSONOutput {
    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "id")
    private Integer id;

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
     * @return the JSONOutput object itself.
     */
    public JSONOutput setId(Integer id) {
        this.id = id;
        return this;
    }

    public void validate() {
    }
}
