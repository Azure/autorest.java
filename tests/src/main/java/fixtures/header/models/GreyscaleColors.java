package fixtures.header.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for GreyscaleColors.
 */
public final class GreyscaleColors extends ExpandableStringEnum<GreyscaleColors> {
    /**
     * Static value White for GreyscaleColors.
     */
    public static final GreyscaleColors WHITE = fromString("White");

    /**
     * Static value black for GreyscaleColors.
     */
    public static final GreyscaleColors BLACK = fromString("black");

    /**
     * Static value GREY for GreyscaleColors.
     */
    public static final GreyscaleColors GREY = fromString("GREY");

    /**
     * Creates or finds a GreyscaleColors from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding GreyscaleColors.
     */
    @JsonCreator
    public static GreyscaleColors fromString(String name) {
        return fromString(name, GreyscaleColors.class);
    }

    /**
     * @return known GreyscaleColors values.
     */
    public static Collection<GreyscaleColors> values() {
        return values(GreyscaleColors.class);
    }
}
