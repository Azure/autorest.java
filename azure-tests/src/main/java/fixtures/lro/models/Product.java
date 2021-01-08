package fixtures.lro.models;

import fixtures.lro.fluent.models.ProductInner;
import java.util.Map;

/**
 * An immutable client-side representation of Product.
 */
public interface Product {
    /**
     * Gets the id property: Fully qualified resource Id for the resource.
     * 
     * @return the id value.
     */
    String id();

    /**
     * Gets the name property: The name of the resource.
     * 
     * @return the name value.
     */
    String name();

    /**
     * Gets the type property: The type of the resource.
     * 
     * @return the type value.
     */
    String type();

    /**
     * Gets the location property: The geo-location where the resource lives.
     * 
     * @return the location value.
     */
    String location();

    /**
     * Gets the tags property: Resource tags.
     * 
     * @return the tags value.
     */
    Map<String, String> tags();

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
    ProductPropertiesProvisioningStateValues provisioningStateValues();

    /**
     * Gets the inner fixtures.lro.fluent.models.ProductInner object.
     * 
     * @return the inner object.
     */
    ProductInner innerModel();
}
