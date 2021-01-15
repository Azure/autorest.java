package fixtures.lro.models;

import fixtures.lro.fluent.models.SkuInner;

/** An immutable client-side representation of Sku. */
public interface Sku {
    /**
     * Gets the name property: The name property.
     *
     * @return the name value.
     */
    String name();

    /**
     * Gets the id property: The id property.
     *
     * @return the id value.
     */
    String id();

    /**
     * Gets the inner fixtures.lro.fluent.models.SkuInner object.
     *
     * @return the inner object.
     */
    SkuInner innerModel();
}
