package fixtures.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

/**
 * The PetAPString model.
 */
@Fluent
public final class PetAPString {
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
     * Dictionary of <string>
     */
    @JsonIgnore
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
     * @return the PetAPString object itself.
     */
    public PetAPString setId(int id) {
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
     * @return the PetAPString object itself.
     */
    public PetAPString setName(String name) {
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
     * Get the additionalProperties property: Dictionary of &lt;string&gt;.
     * 
     * @return the additionalProperties value.
     */
    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Dictionary of &lt;string&gt;.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the PetAPString object itself.
     */
    public PetAPString setAdditionalProperties(Map<String, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    @JsonAnySetter
    private void setAdditionalProperties(String key, String value) {
        if (additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(key, value);
    }

    public void validate() {
    }
}
