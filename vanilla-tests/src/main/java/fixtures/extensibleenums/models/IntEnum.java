package fixtures.extensibleenums.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for IntEnum. */
public final class IntEnum extends ExpandableStringEnum<IntEnum> {
    /** Static value 1 for IntEnum. */
    public static final IntEnum ONE = fromString("1");

    /** Static value 2 for IntEnum. */
    public static final IntEnum TWO = fromString("2");

    /** Static value 3 for IntEnum. */
    public static final IntEnum THREE = fromString("3");

    /**
     * Creates or finds a IntEnum from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding IntEnum.
     */
    @JsonCreator
    public static IntEnum fromString(String name) {
        return fromString(name, IntEnum.class);
    }

    /** @return known IntEnum values. */
    public static Collection<IntEnum> values() {
        return values(IntEnum.class);
    }
}
