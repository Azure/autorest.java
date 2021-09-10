package fixtures.lro.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for ProductPropertiesProvisioningStateValues. */
public final class ProductPropertiesProvisioningStateValues
        extends ExpandableStringEnum<ProductPropertiesProvisioningStateValues> {
    /** Static value Succeeded for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues SUCCEEDED = fromString("Succeeded");

    /** Static value Failed for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues FAILED = fromString("Failed");

    /** Static value canceled for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues CANCELED = fromString("canceled");

    /** Static value Accepted for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues ACCEPTED = fromString("Accepted");

    /** Static value Creating for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues CREATING = fromString("Creating");

    /** Static value Created for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues CREATED = fromString("Created");

    /** Static value Updating for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues UPDATING = fromString("Updating");

    /** Static value Updated for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues UPDATED = fromString("Updated");

    /** Static value Deleting for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues DELETING = fromString("Deleting");

    /** Static value Deleted for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues DELETED = fromString("Deleted");

    /** Static value OK for ProductPropertiesProvisioningStateValues. */
    public static final ProductPropertiesProvisioningStateValues OK = fromString("OK");

    /**
     * Creates or finds a ProductPropertiesProvisioningStateValues from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding ProductPropertiesProvisioningStateValues.
     */
    @JsonCreator
    public static ProductPropertiesProvisioningStateValues fromString(String name) {
        return fromString(name, ProductPropertiesProvisioningStateValues.class);
    }

    /** @return known ProductPropertiesProvisioningStateValues values. */
    public static Collection<ProductPropertiesProvisioningStateValues> values() {
        return values(ProductPropertiesProvisioningStateValues.class);
    }
}
