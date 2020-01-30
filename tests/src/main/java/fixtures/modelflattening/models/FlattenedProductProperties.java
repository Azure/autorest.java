package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The FlattenedProductProperties model.
 */
@Fluent
public final class FlattenedProductProperties {
    /*
     * The p.name property.
     */
    @JsonProperty(value = "p\\.name")
    private String pName;

    /*
     * The type property.
     */
    @JsonProperty(value = "type")
    private String type;

    /*
     * The provisioningStateValues property.
     */
    @JsonProperty(value = "provisioningStateValues", access = JsonProperty.Access.WRITE_ONLY)
    private FlattenedProductPropertiesProvisioningStateValues provisioningStateValues;

    /*
     * The provisioningState property.
     */
    @JsonProperty(value = "provisioningState")
    private String provisioningState;

    /**
     * Get the pName property: The p.name property.
     * 
     * @return the pName value.
     */
    public String getPName() {
        return this.pName;
    }

    /**
     * Set the pName property: The p.name property.
     * 
     * @param pName the pName value to set.
     * @return the FlattenedProductProperties object itself.
     */
    public FlattenedProductProperties setPName(String pName) {
        this.pName = pName;
        return this;
    }

    /**
     * Get the type property: The type property.
     * 
     * @return the type value.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     * 
     * @param type the type value to set.
     * @return the FlattenedProductProperties object itself.
     */
    public FlattenedProductProperties setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get the provisioningStateValues property: The provisioningStateValues
     * property.
     * 
     * @return the provisioningStateValues value.
     */
    public FlattenedProductPropertiesProvisioningStateValues getProvisioningStateValues() {
        return this.provisioningStateValues;
    }

    /**
     * Get the provisioningState property: The provisioningState property.
     * 
     * @return the provisioningState value.
     */
    public String getProvisioningState() {
        return this.provisioningState;
    }

    /**
     * Set the provisioningState property: The provisioningState property.
     * 
     * @param provisioningState the provisioningState value to set.
     * @return the FlattenedProductProperties object itself.
     */
    public FlattenedProductProperties setProvisioningState(String provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    public void validate() {
    }
}
