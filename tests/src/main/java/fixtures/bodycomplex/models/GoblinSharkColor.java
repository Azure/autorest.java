package fixtures.bodycomplex.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for GoblinSharkColor.
 */
public final class GoblinSharkColor extends ExpandableStringEnum<GoblinSharkColor> {
    /**
     * Static value pink for GoblinSharkColor.
     */
    public static final GoblinSharkColor PINK = fromString("pink");

    /**
     * Static value gray for GoblinSharkColor.
     */
    public static final GoblinSharkColor GRAY = fromString("gray");

    /**
     * Static value brown for GoblinSharkColor.
     */
    public static final GoblinSharkColor BROWN = fromString("brown");

//    /**
//     * Static value RED for GoblinSharkColor.
//     */
//    public static final GoblinSharkColor RED = fromString("RED");

    /**
     * Static value red for GoblinSharkColor.
     */
    public static final GoblinSharkColor RED = fromString("red");

    /**
     * Creates or finds a GoblinSharkColor from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding GoblinSharkColor.
     */
    @JsonCreator
    public static GoblinSharkColor fromString(String name) {
        return fromString(name, GoblinSharkColor.class);
    }

    /**
     * @return known GoblinSharkColor values.
     */
    public static Collection<GoblinSharkColor> values() {
        return values(GoblinSharkColor.class);
    }
}
