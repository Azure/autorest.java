package fixtures.lro.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for OperationResultStatus. */
public final class OperationResultStatus extends ExpandableStringEnum<OperationResultStatus> {
    /** Static value Succeeded for OperationResultStatus. */
    public static final OperationResultStatus SUCCEEDED = fromString("Succeeded");

    /** Static value Failed for OperationResultStatus. */
    public static final OperationResultStatus FAILED = fromString("Failed");

    /** Static value canceled for OperationResultStatus. */
    public static final OperationResultStatus CANCELED = fromString("canceled");

    /** Static value Accepted for OperationResultStatus. */
    public static final OperationResultStatus ACCEPTED = fromString("Accepted");

    /** Static value Creating for OperationResultStatus. */
    public static final OperationResultStatus CREATING = fromString("Creating");

    /** Static value Created for OperationResultStatus. */
    public static final OperationResultStatus CREATED = fromString("Created");

    /** Static value Updating for OperationResultStatus. */
    public static final OperationResultStatus UPDATING = fromString("Updating");

    /** Static value Updated for OperationResultStatus. */
    public static final OperationResultStatus UPDATED = fromString("Updated");

    /** Static value Deleting for OperationResultStatus. */
    public static final OperationResultStatus DELETING = fromString("Deleting");

    /** Static value Deleted for OperationResultStatus. */
    public static final OperationResultStatus DELETED = fromString("Deleted");

    /** Static value OK for OperationResultStatus. */
    public static final OperationResultStatus OK = fromString("OK");

    /**
     * Creates or finds a OperationResultStatus from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding OperationResultStatus.
     */
    @JsonCreator
    public static OperationResultStatus fromString(String name) {
        return fromString(name, OperationResultStatus.class);
    }

    /** @return known OperationResultStatus values. */
    public static Collection<OperationResultStatus> values() {
        return values(OperationResultStatus.class);
    }
}
