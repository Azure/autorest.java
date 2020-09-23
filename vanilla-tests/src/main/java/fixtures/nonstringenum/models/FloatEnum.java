package fixtures.nonstringenum.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for FloatEnum. */
public final class FloatEnum extends ExpandableStringEnum<FloatEnum> {
    /** Static value 200.4 for FloatEnum. */
    public static final FloatEnum TWO_HUNDRED4 = fromFloat(200.4f);

    /** Static value 403.4 for FloatEnum. */
    public static final FloatEnum FOUR_HUNDRED_THREE4 = fromFloat(403.4f);

    /** Static value 405.3 for FloatEnum. */
    public static final FloatEnum FOUR_HUNDRED_FIVE3 = fromFloat(405.3f);

    /** Static value 406.2 for FloatEnum. */
    public static final FloatEnum FOUR_HUNDRED_SIX2 = fromFloat(406.2f);

    /** Static value 429.1 for FloatEnum. */
    public static final FloatEnum FOUR_HUNDRED_TWENTY_NINE1 = fromFloat(429.1f);

    /**
     * Creates or finds a FloatEnum from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding FloatEnum.
     */
    @JsonCreator
    public static FloatEnum fromFloat(float name) {
        return fromString(String.valueOf(name), FloatEnum.class);
    }

    /** @return known FloatEnum values. */
    public static Collection<FloatEnum> values() {
        return values(FloatEnum.class);
    }
}
