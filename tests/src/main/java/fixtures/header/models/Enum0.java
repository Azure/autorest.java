package fixtures.header.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for Enum0.
 */
public enum Enum0 {
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
     * The actual serialized value for a Enum0 instance.
     */
    private final String value;

    Enum0(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a Enum0 instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed Enum0 object, or null if unable to parse.
     */
    @JsonCreator
    public static Enum0 fromString(String value) {
        Enum0[] items = Enum0.values();
        for (Enum0 item : items) {
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
