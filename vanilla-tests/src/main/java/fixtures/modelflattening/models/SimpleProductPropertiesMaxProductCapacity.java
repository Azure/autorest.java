package fixtures.modelflattening.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Defines values for SimpleProductPropertiesMaxProductCapacity. */
public enum SimpleProductPropertiesMaxProductCapacity {
    /** Enum value Large. */
    LARGE("Large");

    /** The actual serialized value for a SimpleProductPropertiesMaxProductCapacity instance. */
    private final String value;

    SimpleProductPropertiesMaxProductCapacity(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a SimpleProductPropertiesMaxProductCapacity instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed SimpleProductPropertiesMaxProductCapacity object, or null if unable to parse.
     */
    @JsonCreator
    public static SimpleProductPropertiesMaxProductCapacity fromString(String value) {
        SimpleProductPropertiesMaxProductCapacity[] items = SimpleProductPropertiesMaxProductCapacity.values();
        for (SimpleProductPropertiesMaxProductCapacity item : items) {
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
