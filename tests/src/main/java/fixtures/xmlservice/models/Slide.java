package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * The Slide model.
 */
@JacksonXmlRootElement(localName = "slide")
@Fluent
public final class Slide {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JacksonXmlProperty(localName = "type", isAttribute = true)
    private String type;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "title")
    private String title;

    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty("item")
    private List<String> items = new ArrayList<>();

    /**
     * Get the type property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the type value.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Set the type property.
     * 
     * @param type the type value to set.
     * @return the Slide object itself.
     */
    public Slide setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get the title property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the title value.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the title property.
     * 
     * @param title the title value to set.
     * @return the Slide object itself.
     */
    public Slide setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the items property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the items value.
     */
    public List<String> getItems() {
        return this.items;
    }

    /**
     * Set the items property.
     * 
     * @param items the items value to set.
     * @return the Slide object itself.
     */
    public Slide setItems(List<String> items) {
        this.items = items;
        return this;
    }

    public void validate() {
    }
}
