package fixtures.mediatypes.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Defines values for ContentType1. */
public enum ContentType1 {
    /** Enum value application/json. */
    APPLICATION_JSON("application/json"),

    /** Enum value application/octet-stream. */
    APPLICATION_OCTET_STREAM("application/octet-stream");

    /** The actual serialized value for a ContentType1 instance. */
    private final String value;

    ContentType1(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a ContentType1 instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed ContentType1 object, or null if unable to parse.
     */
    @JsonCreator
    public static ContentType1 fromString(String value) {
        ContentType1[] items = ContentType1.values();
        for (ContentType1 item : items) {
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
