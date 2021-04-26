package fixtures.bodyformdataurlencoded.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/** Defines values for PetFood. */
public final class PetFood extends ExpandableStringEnum<PetFood> {
    /** Static value meat for PetFood. */
    public static final PetFood MEAT = fromString("meat");

    /** Static value fish for PetFood. */
    public static final PetFood FISH = fromString("fish");

    /** Static value plant for PetFood. */
    public static final PetFood PLANT = fromString("plant");

    /**
     * Creates or finds a PetFood from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding PetFood.
     */
    @JsonCreator
    public static PetFood fromString(String name) {
        return fromString(name, PetFood.class);
    }

    /** @return known PetFood values. */
    public static Collection<PetFood> values() {
        return values(PetFood.class);
    }
}
