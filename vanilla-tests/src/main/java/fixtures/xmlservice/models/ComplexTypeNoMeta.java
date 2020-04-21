package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The ComplexTypeNoMeta model.
 */
@JacksonXmlRootElement(localName = "ComplexTypeNoMeta")
@Fluent
public final class ComplexTypeNoMeta {
    /*
     * The id of the res
     */
    @JsonProperty(value = "ID")
    private String id;

    /**
     * Get the id property: The id of the res.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: The id of the res.
     * 
     * @param id the id value to set.
     * @return the ComplexTypeNoMeta object itself.
     */
    public ComplexTypeNoMeta setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
