package fixtures.extensibleenums.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for DaysOfWeekExtensibleEnum.
 */
public final class DaysOfWeekExtensibleEnum extends ExpandableStringEnum<DaysOfWeekExtensibleEnum> {
    /**
     * Static value Monday for DaysOfWeekExtensibleEnum.
     */
    public static final DaysOfWeekExtensibleEnum MONDAY = fromString("Monday");

    /**
     * Static value Tuesday for DaysOfWeekExtensibleEnum.
     */
    public static final DaysOfWeekExtensibleEnum TUESDAY = fromString("Tuesday");

    /**
     * Static value Wednesday for DaysOfWeekExtensibleEnum.
     */
    public static final DaysOfWeekExtensibleEnum WEDNESDAY = fromString("Wednesday");

    /**
     * Static value Thursday for DaysOfWeekExtensibleEnum.
     */
    public static final DaysOfWeekExtensibleEnum THURSDAY = fromString("Thursday");

    /**
     * Static value Friday for DaysOfWeekExtensibleEnum.
     */
    public static final DaysOfWeekExtensibleEnum FRIDAY = fromString("Friday");

    /**
     * Static value Saturday for DaysOfWeekExtensibleEnum.
     */
    public static final DaysOfWeekExtensibleEnum SATURDAY = fromString("Saturday");

    /**
     * Static value Sunday for DaysOfWeekExtensibleEnum.
     */
    public static final DaysOfWeekExtensibleEnum SUNDAY = fromString("Sunday");

    /**
     * Creates or finds a DaysOfWeekExtensibleEnum from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding DaysOfWeekExtensibleEnum.
     */
    @JsonCreator
    public static DaysOfWeekExtensibleEnum fromString(String name) {
        return fromString(name, DaysOfWeekExtensibleEnum.class);
    }

    /**
     * @return known DaysOfWeekExtensibleEnum values.
     */
    public static Collection<DaysOfWeekExtensibleEnum> values() {
        return values(DaysOfWeekExtensibleEnum.class);
    }
}
