package fixtures.bodystring.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for Colors.
 */
public final class Colors extends ExpandableStringEnum<Colors> {
    /**
     * Static value red color for Colors.
     */
    public static final Colors RED_COLOR = fromString("red color");

    /**
     * Static value green-color for Colors.
     */
    public static final Colors GREEN_COLOR = fromString("green-color");

    /**
     * Static value blue_color for Colors.
     */
    public static final Colors BLUE_COLOR = fromString("blue_color");

    /**
     * Creates or finds a Colors from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding Colors.
     */
    @JsonCreator
    public static Colors fromString(String name) {
        return fromString(name, Colors.class);
    }

    /**
     * @return known Colors values.
     */
    public static Collection<Colors> values() {
        return values(Colors.class);
    }
}
