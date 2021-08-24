package fixtures.lro.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for SubProductPropertiesProvisioningStateValues. */
public final class SubProductPropertiesProvisioningStateValues
        extends ExpandableStringEnum<SubProductPropertiesProvisioningStateValues> {
    /** Static value Succeeded for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues SUCCEEDED = fromString("Succeeded");

    /** Static value Failed for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues FAILED = fromString("Failed");

    /** Static value canceled for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues CANCELED = fromString("canceled");

    /** Static value Accepted for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues ACCEPTED = fromString("Accepted");

    /** Static value Creating for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues CREATING = fromString("Creating");

    /** Static value Created for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues CREATED = fromString("Created");

    /** Static value Updating for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues UPDATING = fromString("Updating");

    /** Static value Updated for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues UPDATED = fromString("Updated");

    /** Static value Deleting for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues DELETING = fromString("Deleting");

    /** Static value Deleted for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues DELETED = fromString("Deleted");

    /** Static value OK for SubProductPropertiesProvisioningStateValues. */
    public static final SubProductPropertiesProvisioningStateValues OK = fromString("OK");

    /**
     * Creates or finds a SubProductPropertiesProvisioningStateValues from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding SubProductPropertiesProvisioningStateValues.
     */
    @JsonCreator
    public static SubProductPropertiesProvisioningStateValues fromString(String name) {
        return fromString(name, SubProductPropertiesProvisioningStateValues.class);
    }

    /** @return known SubProductPropertiesProvisioningStateValues values. */
    public static Collection<SubProductPropertiesProvisioningStateValues> values() {
        return values(SubProductPropertiesProvisioningStateValues.class);
    }
}
