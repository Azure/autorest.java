package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Flattened product. */
@Fluent
public final class FlattenedProduct extends Resource {
    /*
     * The p.name property.
     */
    @JsonFlatten
    @JsonProperty(value = "properties.p\\.name")
    private String pName;

    /*
     * The type property.
     */
    @JsonFlatten
    @JsonProperty(value = "properties.type")
    private String typePropertiesType;

    /*
     * The provisioningStateValues property.
     */
    @JsonFlatten
    @JsonProperty(value = "properties.provisioningStateValues", access = JsonProperty.Access.WRITE_ONLY)
    private FlattenedProductPropertiesProvisioningStateValues provisioningStateValues;

    /*
     * The provisioningState property.
     */
    @JsonFlatten
    @JsonProperty(value = "properties.provisioningState")
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
     * @return the FlattenedProduct object itself.
     */
    public FlattenedProduct setPName(String pName) {
        this.pName = pName;
        return this;
    }

    /**
     * Get the typePropertiesType property: The type property.
     *
     * @return the typePropertiesType value.
     */
    public String getTypePropertiesType() {
        return this.typePropertiesType;
    }

    /**
     * Set the typePropertiesType property: The type property.
     *
     * @param typePropertiesType the typePropertiesType value to set.
     * @return the FlattenedProduct object itself.
     */
    public FlattenedProduct setTypePropertiesType(String typePropertiesType) {
        this.typePropertiesType = typePropertiesType;
        return this;
    }

    /**
     * Get the provisioningStateValues property: The provisioningStateValues property.
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
     * @return the FlattenedProduct object itself.
     */
    public FlattenedProduct setProvisioningState(String provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }
}
