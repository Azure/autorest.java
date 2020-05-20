package fixtures.bodyarray.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Defines values for FooEnum. */
public enum FooEnum {
    /** Enum value foo1. */
    FOO1("foo1"),

    /** Enum value foo2. */
    FOO2("foo2"),

    /** Enum value foo3. */
    FOO3("foo3");

    /** The actual serialized value for a FooEnum instance. */
    private final String value;

    FooEnum(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a FooEnum instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed FooEnum object, or null if unable to parse.
     */
    @JsonCreator
    public static FooEnum fromString(String value) {
        FooEnum[] items = FooEnum.values();
        for (FooEnum item : items) {
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
