package fixtures.lro.models;

import fixtures.lro.fluent.models.SubProductInner;

/** An immutable client-side representation of SubProduct. */
public interface SubProduct {
    /**
     * Gets the id property: Fully qualified resource Id for the resource.
     *
     * @return the id value.
     */
    String id();

    /**
     * Gets the provisioningState property: The provisioningState property.
     *
     * @return the provisioningState value.
     */
    String provisioningState();

    /**
     * Gets the provisioningStateValues property: The provisioningStateValues property.
     *
     * @return the provisioningStateValues value.
     */
    SubProductPropertiesProvisioningStateValues provisioningStateValues();

    /**
     * Gets the inner fixtures.lro.fluent.models.SubProductInner object.
     *
     * @return the inner object.
     */
    SubProductInner innerModel();
}
