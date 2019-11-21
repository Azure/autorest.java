package fixtures.bodycomplex.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for CMYKColors.
 */
public enum CMYKColors {
    /**
     * Enum value cyan.
     */
    CYAN("cyan"),

    /**
     * Enum value Magenta.
     */
    MAGENTA("Magenta"),

    /**
     * Enum value YELLOW.
     */
    YELLOW("YELLOW"),

    /**
     * Enum value blacK.
     */
    BLACK("blacK");

    /**
     * The actual serialized value for a CMYKColors instance.
     */
    private final String value;

    CMYKColors(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a CMYKColors instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed CMYKColors object, or null if unable to parse.
     */
    @JsonCreator
    public static CMYKColors fromString(String value) {
        CMYKColors[] items = CMYKColors.values();
        for (CMYKColors item : items) {
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
