package fixtures.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The PetAPInPropertiesWithAPString model.
 */
@Fluent
public final class PetAPInPropertiesWithAPString {
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

    /*
     * The status property.
     */
    @JsonProperty(value = "status", access = JsonProperty.Access.WRITE_ONLY)
    private Boolean status;

    /*
     * The @odata.location property.
     */
    @JsonProperty(value = "@odata.location", required = true)
    private String odataLocation;

    /*
     * Dictionary of <number>
     */
    @JsonProperty(value = "additionalProperties-original")
    private Map<String, Float> AdditionalPropertiesProperty;

    /*
     * Dictionary of <string>
     */
    @JsonProperty(value = "")
    private Map<String, String> additionalProperties;

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
     * @return the PetAPInPropertiesWithAPString object itself.
     */
    public PetAPInPropertiesWithAPString setId(int id) {
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
     * @return the PetAPInPropertiesWithAPString object itself.
     */
    public PetAPInPropertiesWithAPString setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the status property: The status property.
     * 
     * @return the status value.
     */
    public Boolean isStatus() {
        return this.status;
    }

    /**
     * Get the odataLocation property: The @odata.location property.
     * 
     * @return the odataLocation value.
     */
    public String getOdataLocation() {
        return this.odataLocation;
    }

    /**
     * Set the odataLocation property: The @odata.location property.
     * 
     * @param odataLocation the odataLocation value to set.
     * @return the PetAPInPropertiesWithAPString object itself.
     */
    public PetAPInPropertiesWithAPString setOdataLocation(String odataLocation) {
        this.odataLocation = odataLocation;
        return this;
    }

    /**
     * Get the AdditionalPropertiesProperty property: Dictionary of
     * &lt;number&gt;.
     * 
     * @return the AdditionalPropertiesProperty value.
     */
    public Map<String, Float> getAdditionalPropertiesProperty() {
        return this.AdditionalPropertiesProperty;
    }

    /**
     * Set the AdditionalPropertiesProperty property: Dictionary of
     * &lt;number&gt;.
     * 
     * @param AdditionalPropertiesProperty the AdditionalPropertiesProperty
     * value to set.
     * @return the PetAPInPropertiesWithAPString object itself.
     */
    public PetAPInPropertiesWithAPString setAdditionalPropertiesProperty(Map<String, Float> AdditionalPropertiesProperty) {
        this.AdditionalPropertiesProperty = AdditionalPropertiesProperty;
        return this;
    }

    /**
     * Get the additionalProperties property: Dictionary of &lt;string&gt;.
     * 
     * @return the additionalProperties value.
     */
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Dictionary of &lt;string&gt;.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the PetAPInPropertiesWithAPString object itself.
     */
    public PetAPInPropertiesWithAPString setAdditionalProperties(Map<String, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    public void validate() {
        if (getOdataLocation() == null) {
            throw new IllegalArgumentException("Missing required property odataLocation in model PetAPInPropertiesWithAPString");
        }
    }
}
