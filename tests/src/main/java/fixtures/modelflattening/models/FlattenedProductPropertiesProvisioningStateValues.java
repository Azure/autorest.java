package fixtures.modelflattening.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for FlattenedProductPropertiesProvisioningStateValues.
 */
public final class FlattenedProductPropertiesProvisioningStateValues extends ExpandableStringEnum<FlattenedProductPropertiesProvisioningStateValues> {
    /**
     * Static value Succeeded for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues SUCCEEDED = fromString("Succeeded");

    /**
     * Static value Failed for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues FAILED = fromString("Failed");

    /**
     * Static value canceled for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues CANCELED = fromString("canceled");

    /**
     * Static value Accepted for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues ACCEPTED = fromString("Accepted");

    /**
     * Static value Creating for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues CREATING = fromString("Creating");

    /**
     * Static value Created for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues CREATED = fromString("Created");

    /**
     * Static value Updating for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues UPDATING = fromString("Updating");

    /**
     * Static value Updated for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues UPDATED = fromString("Updated");

    /**
     * Static value Deleting for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues DELETING = fromString("Deleting");

    /**
     * Static value Deleted for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues DELETED = fromString("Deleted");

    /**
     * Static value OK for FlattenedProductPropertiesProvisioningStateValues.
     */
    public static final FlattenedProductPropertiesProvisioningStateValues OK = fromString("OK");

    /**
     * Creates or finds a FlattenedProductPropertiesProvisioningStateValues from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding FlattenedProductPropertiesProvisioningStateValues.
     */
    @JsonCreator
    public static FlattenedProductPropertiesProvisioningStateValues fromString(String name) {
        return fromString(name, FlattenedProductPropertiesProvisioningStateValues.class);
    }

    /**
     * @return known FlattenedProductPropertiesProvisioningStateValues values.
     */
    public static Collection<FlattenedProductPropertiesProvisioningStateValues> values() {
        return values(FlattenedProductPropertiesProvisioningStateValues.class);
    }
}
