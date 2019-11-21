package fixtures.bodycomplex.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for GoblinSharkColor.
 */
public enum GoblinSharkColor {
    /**
     * Enum value pink.
     */
    PINK("pink"),

    /**
     * Enum value gray.
     */
    GRAY("gray"),

    /**
     * Enum value brown.
     */
    BROWN("brown");

    /**
     * The actual serialized value for a GoblinSharkColor instance.
     */
    private final String value;

    GoblinSharkColor(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a GoblinSharkColor instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed GoblinSharkColor object, or null if unable to parse.
     */
    @JsonCreator
    public static GoblinSharkColor fromString(String value) {
        GoblinSharkColor[] items = GoblinSharkColor.values();
        for (GoblinSharkColor item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
