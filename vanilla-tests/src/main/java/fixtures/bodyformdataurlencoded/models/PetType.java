package fixtures.bodyformdataurlencoded.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Defines values for PetType. */
public enum PetType {
    /** Enum value dog. */
    DOG("dog"),

    /** Enum value cat. */
    CAT("cat"),

    /** Enum value fish. */
    FISH("fish");

    /** The actual serialized value for a PetType instance. */
    private final String value;

    PetType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a PetType instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed PetType object, or null if unable to parse.
     */
    @JsonCreator
    public static PetType fromString(String value) {
        PetType[] items = PetType.values();
        for (PetType item : items) {
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
