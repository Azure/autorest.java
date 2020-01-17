package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Salmon model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype", defaultImpl = Salmon.class)
@JsonTypeName("salmon")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "smart_salmon", value = SmartSalmon.class)
})
@Fluent
public class Salmon extends Fish {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "location")
    private String location;

    /*
     * MISSING·SCHEMA-DESCRIPTION-BOOLEAN
     */
    @JsonProperty(value = "iswild")
    private Boolean iswild;

    /**
     * Get the location property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property.
     * 
     * @param location the location value to set.
     * @return the Salmon object itself.
     */
    public Salmon setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get the iswild property: MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * 
     * @return the iswild value.
     */
    public Boolean iswild() {
        return this.iswild;
    }

    /**
     * Set the iswild property.
     * 
     * @param iswild the iswild value to set.
     * @return the Salmon object itself.
     */
    public Salmon setIswild(Boolean iswild) {
        this.iswild = iswild;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
    }
}
