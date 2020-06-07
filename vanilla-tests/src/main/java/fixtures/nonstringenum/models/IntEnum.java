package fixtures.nonstringenum.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for IntEnum. */
public final class IntEnum extends ExpandableStringEnum<IntEnum> {
    /** Static value 200 for IntEnum. */
    public static final IntEnum TWO_ZERO_ZERO = fromInt(200);

    /** Static value 403 for IntEnum. */
    public static final IntEnum FOUR_ZERO_THREE = fromInt(403);

    /** Static value 405 for IntEnum. */
    public static final IntEnum FOUR_ZERO_FIVE = fromInt(405);

    /** Static value 406 for IntEnum. */
    public static final IntEnum FOUR_ZERO_SIX = fromInt(406);

    /** Static value 429 for IntEnum. */
    public static final IntEnum FOUR_TWO_NINE = fromInt(429);

    /**
     * Creates or finds a IntEnum from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding IntEnum.
     */
    @JsonCreator
    public static IntEnum fromInt(int name) {
        return fromString(String.valueOf(name), IntEnum.class);
    }

    /** @return known IntEnum values. */
    public static Collection<IntEnum> values() {
        return values(IntEnum.class);
    }
}
