package fixtures.header.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for GreyscaleColors.
 */
public enum GreyscaleColors {
    /**
     * Enum value White.
     */
    WHITE("White"),

    /**
     * Enum value black.
     */
    BLACK("black"),

    /**
     * Enum value GREY.
     */
    GREY("GREY");

    /**
     * The actual serialized value for a GreyscaleColors instance.
     */
    private final String value;

    GreyscaleColors(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a GreyscaleColors instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed GreyscaleColors object, or null if unable to parse.
     */
    @JsonCreator
    public static GreyscaleColors fromString(String value) {
        GreyscaleColors[] items = GreyscaleColors.values();
        for (GreyscaleColors item : items) {
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
