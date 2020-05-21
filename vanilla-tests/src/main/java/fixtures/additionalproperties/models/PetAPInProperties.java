package fixtures.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/** The PetAPInProperties model. */
@Fluent
public final class PetAPInProperties {
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
     * Dictionary of <number>
     */
    @JsonProperty(value = "additionalProperties")
    private Map<String, Float> additionalProperties;

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
     * @return the PetAPInProperties object itself.
     */
    public PetAPInProperties setId(int id) {
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
     * @return the PetAPInProperties object itself.
     */
    public PetAPInProperties setName(String name) {
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
     * Get the additionalProperties property: Dictionary of &lt;number&gt;.
     *
     * @return the additionalProperties value.
     */
    public Map<String, Float> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Dictionary of &lt;number&gt;.
     *
     * @param additionalProperties the additionalProperties value to set.
     * @return the PetAPInProperties object itself.
     */
    public PetAPInProperties setAdditionalProperties(Map<String, Float> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
